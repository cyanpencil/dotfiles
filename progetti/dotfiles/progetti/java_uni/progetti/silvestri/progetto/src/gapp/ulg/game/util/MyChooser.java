package gapp.ulg.game.util;

import gapp.ulg.Utilities.Node;
import gapp.ulg.Utilities.UsefulMethods;
import gapp.ulg.game.board.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import java.util.*;
import java.util.Optional;

import static gapp.ulg.game.board.Action.Kind.JUMP;
import static gapp.ulg.game.board.Move.Kind.PASS;
import static gapp.ulg.game.board.Move.Kind.RESIGN;
/**
 * Created by Edoardo & Luca on 18/07/2016.
 */
/** Un oggetto {@code MoveChooser} ha lo scopo di facilitare la scelta tramite
 * GUI di una mossa valida in una certa situazione di gioco. A questo fine
 * rappresenta l'insieme delle mosse valide di tipo {@link Move.Kind#ACTION} di
 * una situazione di gioco con una struttura ad albero navigabile chiamata
 * <i>albero delle mosse</i>.
 * <br>
 * Un oggetto {@code MoveChooser}, mantiene un nodo corrente che all'inizio è la
 * radice dell'albero. Il nodo corrente può essere spostato a uno dei suoi nodi
 * figli con i metodi {@link MyChooser#doSelection(Object)},
 * {@link MyChooser#moveSelection(Board.Dir, int)} e
 * {@link MyChooser#jumpSelection(Pos)} o all'indietro al nodo genitore con
 * {@link MyChooser#back()}. Per facilitare la selezione del nodo figlio,
 * un {@code MoveChooser} mantiene anche un insieme di posizioni, chiamato la
 * <i>selezione corrente</i>, che permette di selezionare un sotto-insieme dei
 * nodi figli del nodo corrente, tramite il metodo
 * {@link MyChooser#select(Pos...)}.
 * <br>
 * Ogniqualvolta il nodo corrente è finale, cioè il metodo
 * {@link MyChooser#isFinal()} ritorna true, si può scegliere la mossa
 * corrispondente con {@link MyChooser#move()}. In ogni momento si può scegliere
 * la resa con {@link MyChooser#resign()} e, se è una mossa valida (lo si può
 * accertare con {@link MyChooser#mayPass()}), si può passare il turno con
 * {@link MyChooser#pass()}. Per maggiori informazioni si può consultare
 * <a href="http://twiki.di.uniroma1.it/pub/Metod_prog/RS2_ESAMI/progetto.html">Progetto</a>.
 * @param <P>  tipo del modello dei pezzi */
public class MyChooser<P> implements PlayerGUI.MoveChooser<P> {

    public final Node radice;
    public Node currentNode = null;
    private List<Pos> currentSelection = Collections.synchronizedList(new ArrayList<>());
    private Move<P> chosenMove = null;

    private final GameRuler<P> gameRuler; 
    private final long timeStart;

    private final boolean mayPass;
    private final List<Move<P>> validMoves;

    private AtomicBoolean disabled = new AtomicBoolean(false);

    public MyChooser(GameRuler<P> gR) {
        this.gameRuler = gR;
        this.timeStart = System.currentTimeMillis();
        validMoves = new ArrayList<>(gR.validMoves());
        validMoves.remove(new Move(RESIGN));
        mayPass = validMoves.remove(new Move(PASS));
        radice = Node.genTree(UsefulMethods.moveListToActionList(validMoves)); 
        currentNode = radice;
    }
    
    public boolean tempoScaduto() {
        return gameRuler.mechanics().time >= 0 ? System.currentTimeMillis() - timeStart > gameRuler.mechanics().time : false;
    }
    
    private Move<P> subMoveNode(Node<P> n) {
        if(n.getParentNode() == null)
            return new Move<>(n.getPrefix());
        ArrayList<Action<P>> pCurrent = n.getPrefix(),  pPadre = n.getParentNode().getPrefix();
        return new Move<P>(pCurrent.subList(pPadre.size(), pCurrent.size()));
    }

    private List<Node<P>> getSelectedChildNodes() {
        List<Node<P>> selectedNodes = new ArrayList<>();
        if(radice == null || currentSelection.isEmpty() || currentNode.getChildNodes().isEmpty()) return selectedNodes;

        for (Node<P> childNode : (ArrayList<Node<P>>) currentNode.getChildNodes()) {
            Move<P> subMove = subMoveNode(childNode);
            Action<P> firstAction = subMove.actions.get(0);
            Set<Pos> currentSet = new HashSet(currentSelection);
            if (firstAction.kind == JUMP) {
                if (currentSelection.equals(Arrays.asList(firstAction.pos.get(0))))
                    selectedNodes.add(childNode);
            }
            else if (currentSet.equals(new HashSet(firstAction.pos)))
                selectedNodes.add(childNode);
        }
        return selectedNodes;
    }

    /** @return un {@link Optional} con la sotto-mossa del nodo corrente o un
     * {@link Optional} vuoto se il nodo corrente è la radice con prefisso vuoto
     * o null se l'albero è vuoto
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized Optional<Move<P>> subMove() {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if (radice == null) return null;
        ArrayList<Action<P>> pCurrent = currentNode.getPrefix();
        Optional<Move<P>> subMove = pCurrent.size() == 0 ? Optional.empty() : Optional.of(subMoveNode(currentNode));
        return subMove;
    }

    /** Ritorna la lista con le sotto-mosse di tutti i nodi figli del nodo
     * corrente. Se il nodo corrente è una foglia, ritorna la lista vuota. La
     * lista ritornata è sempre creata ex novo. Se l'albero è vuoto, ritorna null.
     * @return la lista con le sotto-mosse di tutti i nodi figli del nodo
     * corrente o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized List<Move<P>> childrenSubMoves() {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if (radice == null) return null;

        List<Move<P>> childrenSubMoves = new ArrayList<>();
        if (currentNode.getChildNodes().size() == 0)
            return childrenSubMoves;
        currentNode.getChildNodes().forEach(nodo -> childrenSubMoves.add(subMoveNode((Node<P>) nodo)));

        return childrenSubMoves;
    }

    /** Seleziona le posizioni specificate e ritorna le sotto-mosse dei nodi figli
     * del nodo corrente selezionati dalle posizioni. Se non ci sono nodi figli
     * selezionati dalle posizioni, ritorna la lista vuota. La selezione corrente
     * diventa sempre uguale alle posizioni specificate anche se non seleziona
     * alcun nodo. La lista ritornata è sempre creata ex novo. Se l'albero è
     * vuoto, ritorna null.
     * @param pp  un insieme di posizioni
     * @return le sotto-mosse dei nodi figli del nodo corrente selezionati dalle
     * posizioni o null
     * @throws NullPointerException se una delle posizioni date è null
     * @throws IllegalArgumentException se non c'è almeno una posizione o ci
     * sono posizioni duplicate o c'è qualche posizione che non è nella board
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized List<Move<P>> select(Pos... pp) {
        // TODO: da ricambiare era solo per il debug
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException("E' stata già scelta una mossa o il tempo è scaduto.");
        if(pp.length == 0 || pp == null) throw new IllegalArgumentException("La lista delle posizioni è vuota.");
        Set<Pos> positionSet = new HashSet<>(Arrays.asList(pp));
        if(positionSet.size() < pp.length) throw new IllegalArgumentException("La lista delle posizioni contiene duplicati.");
        for (Pos p : pp) {
            if (p == null) throw new NullPointerException("La lista delle posizioni contiene una posizione nulla");
            if (!gameRuler.getBoard().isPos(p)) throw new IllegalArgumentException("Pos non contenuta nella board");
        }
        if (radice == null) return null;

        currentSelection = Collections.synchronizedList(Arrays.asList(pp));
        List<Move<P>> selectedSubMoves = new ArrayList<>();
        if (currentNode.getChildNodes().size() == 0) return selectedSubMoves;
        for (Node<P> selectedChild : getSelectedChildNodes())
            selectedSubMoves.add(subMoveNode(selectedChild));
        return selectedSubMoves;
    }


    /** Ritorna le sotto-mosse dei nodi figli del nodo corrente quasi-selezionati
     * dalla selezione corrente. Se non ci sono sotto-mosse quasi-selezionate,
     * ritorna la lista vuota. La lista ritornata è sempre creata ex novo. Se
     * l'albero è vuoto ritorna null.
     * @return le sotto-mosse dei nodi figli del nodo corrente quasi-selezionati
     * dalla selezione corrente o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized List<Move<P>> quasiSelected() {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if (radice == null) return null;
        List<Move<P>> quasiSelectedMoves = new ArrayList<>();
        if (currentSelection.size() == 0) return quasiSelectedMoves;
        for (Node<P> childNode : (ArrayList<Node<P>>) currentNode.getChildNodes()) {
            Move<P> childSubMove = subMoveNode(childNode);
            Action<P> firstAction = childSubMove.actions.get(0);
            Set<Pos> firstActionSet = new HashSet<>(firstAction.pos);
            Set<Pos> currentSelectionSet = new HashSet<>(currentSelection);
            if (currentSelectionSet.size() < firstActionSet.size())  {
                if (firstActionSet.containsAll(currentSelectionSet) && !firstAction.kind.equals(JUMP))
                    quasiSelectedMoves.add(childSubMove);
            }
        }
        return quasiSelectedMoves;
    }

    /** Se l'insieme dei nodi figli del nodo corrente selezionati dalla selezione
     * corrente non è vuoto e le prime azioni delle loro sotto-mosse o sono tutte
     * {@link Action.Kind#ADD} o sono tutte {@link Action.Kind#SWAP}, allora
     * ritorna la lista dei pezzi di tali azioni. Se invece c'è un solo nodo
     * figlio selezionato e la prima azione della sua sotto-mossa è
     * {@link Action.Kind#REMOVE}, ritorna una lista con l'unico elemento null.
     * Altrimenti ritorna la lista vuota. La lista ritornata è sempre creata ex
     * novo. Se l'albero è vuoto, ritorna null.
     * @return la lista dei pezzi o che contiene solamente l'elemento null o la
     * lista vuota o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized List<P> selectionPieces() {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if (radice == null) return null;
        Set<P> selectedPieces = new HashSet<>();
        List<Move<P>> selectionSubMoves = select(currentSelection.toArray(new Pos[currentSelection.size()])); //TODO riaggiustare la chiamata a select()
        boolean AllAdd = true, AllSwap = true;
        for (Move<P> move : selectionSubMoves) {
            if (move.actions.get(0).kind != Action.Kind.ADD) AllAdd = false;
            if (move.actions.get(0).kind != Action.Kind.SWAP) AllSwap = false;
            selectedPieces.add(move.actions.get(0).piece);
        }
        if ((AllAdd || AllSwap) && selectionSubMoves.size() > 0) return new ArrayList<>(selectedPieces);
        if (selectionSubMoves.size() == 1 && selectionSubMoves.get(0).actions.get(0).kind == Action.Kind.REMOVE) {
            selectedPieces.add(null);
            return new ArrayList<>(selectedPieces);
        }
        else return new ArrayList<>();
    }


    /** Annulla la selezione corrente, se presente.
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized void clearSelection() {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        currentSelection = Collections.synchronizedList(new ArrayList<>());
    }


    /** Se le prime azioni delle sotto-mosse dei nodi figli selezionati dalla
     * selezione corrente sono tutte {@link Action.Kind#ADD} o sono tutte
     * {@link Action.Kind#SWAP} e una di queste ha il pezzo {@code pm}, allora
     * sposta il nodo corrente al corrispondente nodo figlio e ritorna la relativa
     * sotto-mossa. Se invece c'è un solo nodo figlio selezionato, la prima azione
     * della sua sotto-mossa è {@link Action.Kind#REMOVE} e {@code pm} è null,
     * allora sposta il nodo corrente al corrispondente nodo figlio e ritorna la
     * relativa sotto-mossa. In entrambi i casi la selezione corrente è annullata.
     * Se nessuno dei due casi è soddisfatto, non fa nulla e ritorna null.
     * @param pm  un pezzo o null
     * @return la sotto-mossa del nuovo nodo corrente o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized Move<P> doSelection(P pm) {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if(radice == null) return null;
        Node<P> newCurrentNode = null;
        List<Node<P>> selectedChilds = getSelectedChildNodes();
        boolean AllAdd = true, AllSwap = true;
        for (Node<P> node : selectedChilds) {
            Move<P> move = subMoveNode(node);
            if (move.actions.get(0).kind != Action.Kind.ADD) AllAdd = false;
            if (move.actions.get(0).kind != Action.Kind.SWAP) AllSwap = false;
            if (move.actions.get(0).piece != null && move.actions.get(0).piece.equals(pm))
                newCurrentNode = node;
        }
        if ((AllAdd || AllSwap) && selectedChilds.size() > 0) {
            if (newCurrentNode != null) {
                this.currentNode = newCurrentNode;
                currentSelection = Collections.synchronizedList(new ArrayList<>());
                return subMoveNode(newCurrentNode);
            }
        }
        if (selectedChilds.size() == 1) {
            newCurrentNode = selectedChilds.get(0);
            Move<P> submove = subMoveNode(newCurrentNode);
            if (pm == null && submove.actions.get(0).kind == Action.Kind.REMOVE) {
                this.currentNode = newCurrentNode;
                currentSelection = Collections.synchronizedList(new ArrayList<>());
                return submove;
            }
        }

        return null;
    }


    /** Se la prima azione della sotto-mossa di uno dei nodi figli selezionati
     * dalla selezione corrente è {@link Action.Kind#JUMP} con la posizione
     * d'arrivo {@code p}, allora sposta il nodo corrente al corrispondente nodo
     * figlio, annulla la selezione corrente e ritorna la relativa sotto-mossa.
     * Altrimenti non fa nulla e ritorna null.
     * @param p  una posizione
     * @return la sotto-mossa del nuovo nodo corrente o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized Move<P> jumpSelection(Pos p) {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if(radice == null) return null;
        List<Node<P>> selectedChilds = getSelectedChildNodes();
        for (Node<P> node : selectedChilds) {
            Move<P> submove = subMoveNode(node);
            if (submove.actions.get(0).kind == JUMP)
                if (submove.actions.get(0).pos.get(1).equals(p)) {
                    currentNode = node;
                    currentSelection = Collections.synchronizedList(new ArrayList<>());
                    return submove;
                }
        }
        return null;
    }


    /** Se la prima azione della sotto-mossa di uno dei nodi figli selezionati
     * dalla selezione corrente è {@link Action.Kind#MOVE} con parametri
     * {@code d} e {@code ns}, allora sposta il nodo corrente al corrispondente
     * nodo figlio, annulla la selezione corrente e ritorna la relativa
     * sotto-mossa. Altrimenti non fa nulla e ritorna null.
     * @param d  direzione di movimento
     * @param ns  numero passi
     * @return la sotto-mossa del nuovo nodo corrente o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized Move<P> moveSelection(Board.Dir d, int ns) {
        if (chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException();
        if(radice == null) return null;
        List<Node<P>> selectedChilds = getSelectedChildNodes();
        for (Node<P> node : selectedChilds) {
            Move<P> submove = subMoveNode(node);
            if (submove.actions.get(0).kind == Action.Kind.MOVE){
                if (submove.actions.get(0).dir.equals(d) && submove.actions.get(0).steps == ns) {
                    currentNode = node;
                    currentSelection = new ArrayList<>();
                    return submove;
                }
            }
        }
        return null;
    }


    /** Sposta il nodo corrente al nodo genitore e ritorna la sotto-mossa inversa
     * della sotto-mossa del precedente nodo corrente. Per la definizione di
     * sotto-mossa inversa si veda
     * <a href="http://twiki.di.uniroma1.it/pub/Metod_prog/RS2_ESAMI/progetto.html">Progetto</a>.
     * Se il nodo corrente è la radice (o l'albero è vuoto), non fa nulla e
     * ritorna null.
     * @return la sotto-mossa inversa della sotto-mossa del precedente nodo
     * corrente o null
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized Move<P> back() {
        if(chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException("E' già stata scelta una mossa oppure è scaduto il tempo");
        if (currentNode == radice || radice == null) return null;

        List<Action<P>> invertedActions = new ArrayList<>();
        try {
            currentSelection = Collections.synchronizedList(new ArrayList<>());
            Board<P> board = gameRuler.getBoard(); 
            Map<Pos, P> boardMap = UsefulMethods.createMap(board); // TODO: eliminare metodo doppione o comunque ricontrollare
            currentNode.getParentNode().getPrefix().forEach(a -> UsefulMethods.doAction(boardMap, (Action<P>)a, board::adjacent));

            Move<P> subMoveNotMethod = subMove().get();

            List<List<P>> affectedPiecesActions = new ArrayList<>();
            for (Action<P> action : subMoveNotMethod.actions) {
                affectedPiecesActions.add(UsefulMethods.doAction(boardMap, action, board::adjacent));
            }
            for (int i = subMoveNotMethod.actions.size() - 1; i >= 0; i--) {
                invertedActions.addAll(UsefulMethods.revertedAction(subMoveNotMethod.actions.get(i), board::adjacent, affectedPiecesActions.get(i)));
            }
        } catch (Exception e) {e.printStackTrace();}
        
        
        currentNode = currentNode.getParentNode();
        return new Move<>(invertedActions);
    }


    /** @return true se il nodo corrente è finale, false altrimenti
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized boolean isFinal() {
        if(chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException("E' già stata scelta una mossa oppure è scaduto il tempo");
        return currentNode.getPrefix().size() != 0 && validMoves.contains(new Move<>(currentNode.getPrefix()));
    }


    /** Se il nodo corrente è finale, sceglie la mossa corrispondente. Dopo che
     * il metodo è stato invocato con successo, questo oggetto non è più
     * utilizzabile e tutti i suoi metodi lanciano {@link IllegalStateException}.
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto o l'albero è vuoto o il nodo corrente non è finale */
    @Override
    public synchronized void move() {
        if (chosenMove != null || radice == null || !isFinal() || disabled.get()) throw new IllegalStateException();
        chosenMove = new Move<>(currentNode.getPrefix());
        this.notifyAll();
    }

    public synchronized Move<P> getMove(){
        return this.chosenMove;
    }



    /** @return true se {@link Move.Kind#PASS} è una mossa valida, false
     * altrimenti.
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized boolean mayPass() {
        if(chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException("E' già stata scelta una mossa oppure il tempo è scaduto");
        return mayPass;
    }

    /** Se {@link Move.Kind#PASS} è una mossa valida, sceglie la mossa
     * {@link Move.Kind#PASS}. Dopo che il metodo è stato invocato con successo,
     * questo oggetto non è più utilizzabile e tutti i suoi metodi lanciano
     * {@link IllegalStateException}.
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto o se {@link Move.Kind#PASS} non è una mossa valida */
    @Override
    public synchronized void pass() {
        if(chosenMove != null || tempoScaduto() || disabled.get() || !mayPass) throw new IllegalStateException("Impossibile effettuare il PASS");
        this.chosenMove = new Move(PASS);
        this.notifyAll();
    }

    /** Sceglie la mossa {@link Move.Kind#RESIGN}. Dopo che il metodo è stato
     * invocato con successo, questo oggetto non è più utilizzabile e tutti i
     * suoi metodi lanciano {@link IllegalStateException}.
     * @throws IllegalStateException se è già stata scelta una mossa o il tempo
     * è scaduto */
    @Override
    public synchronized void resign() {
        if(chosenMove != null || tempoScaduto() || disabled.get()) throw new IllegalStateException("E' già stata selezionata una mossa");
        this.chosenMove = new Move(RESIGN);
        this.notifyAll();
    }

    public void reset(){
        currentSelection = Collections.synchronizedList(new ArrayList<>());
        currentNode = radice;
    }

    public void disable() {
        disabled.set(true);
    }
}
