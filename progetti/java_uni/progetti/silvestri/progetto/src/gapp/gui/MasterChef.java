package gapp.gui;

import gapp.gui.MyBoards.BoardGUI;
import gapp.ulg.Utilities.MyCouple;
import gapp.ulg.game.util.PlayerGUI.*;
import gapp.ulg.game.board.*;
import gapp.ulg.Utilities.UsefulMethods;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.function.Consumer;
import java.util.*;
import java.util.stream.Collectors;

import static gapp.ulg.game.board.Action.Kind.*;

public class MasterChef<P> implements Consumer<MoveChooser<P>> {

    private List<List<Action<P>>> actionHistory = Collections.synchronizedList(new ArrayList<>());
    private List<List<Pos>> masterSelection = Collections.synchronizedList(new ArrayList<>());
    private List<List<Pos>> highlightHistory = Collections.synchronizedList(new ArrayList<>());
    private BoardGUI<P> board;
    private Board<P> boardGr = null;

    private MoveChooser<P> moveChooser;




    @Override
    public synchronized void accept(MoveChooser<P> moveChooser) {
        this.moveChooser = moveChooser;
        // Se il moveChooser appena accettato contiene delle moves di tipo ADD con una sola azione e (finali da aggiungere) mostra i cerchietti e aggiorna la boad
        highlightHistory = new ArrayList<>();
        List<Pos> highlight = new ArrayList<>();
        moveChooser.childrenSubMoves().forEach(move -> {
            if(move.actions.size() == 1 && move.actions.get(0).kind.equals(Action.Kind.ADD))
                highlight.add(move.actions.get(0).pos.get(0));
            else if(move.actions.size() == 2 && move.actions.get(0).kind.equals(ADD) && move.actions.get(1).kind.equals(SWAP)) {
                highlight.add(move.actions.get(0).pos.get(0));
            }
        });
        highlightHistory.add(highlight);
        board.updateBoard(highlight, Collections.emptyList());

        this.masterSelection = new ArrayList<>();
    }
    
    public synchronized void  click(Pos p) {
        synchronized (moveChooser) {
            List<Pos> highlight = new ArrayList<>();

            // Aggiungo la posizione alla selezione corrente del master
            List<Pos> currentMasterSelection = masterSelection.size() != 0 ? masterSelection.get(masterSelection.size()-1) : new ArrayList<>();
            currentMasterSelection.add(p);
            if(masterSelection.size() == 0 || !masterSelection.get(masterSelection.size()-1).equals(currentMasterSelection)) masterSelection.add(currentMasterSelection);

            if (currentMasterSelection.size() > 1 && currentMasterSelection.get(currentMasterSelection.size() - 2).equals(p) && moveChooser.isFinal() && moveChooser.childrenSubMoves().size() != 0) {
                makeMoveAndResetEverything();
                return;
            }

            // Calcolo le posizioni della radice se la radice non è nulla ci sia una sola azione valida all'inizio del turno
            List<Pos> rootPositions = new ArrayList<>();
            List<List<Action<P>>> rootActions = new ArrayList<>();
            List<Action<P>> currentNodeSubMove = moveChooser.subMove().isPresent() ? moveChooser.subMove().get().actions : new ArrayList<>();


            int i = 0;
            // Creo la lista delle posizioni "interessanti" per ogni azione del nodo corrente
            for(int index = 0; index < currentNodeSubMove.size(); index++){
                Action<P> action = currentNodeSubMove.get(index);
                if(action.kind.equals(REMOVE) && moveChooser.childrenSubMoves().size() == 0 && currentNodeSubMove.size() == 1) rootPositions.add(action.pos.get(0));
                if(action.kind.equals(ADD))  rootPositions.add(action.pos.get(0)); // TODO: 07/09/2016 potrebbe essere sbagliato perchè ci potrebbero essere casi in cui una add non è una posizione importante
                if(action.kind.equals(JUMP)){
                    if((index == currentNodeSubMove.size()-1) || (index < currentNodeSubMove.size()-1 && !currentNodeSubMove.get(index+1).kind.equals(REMOVE)))
                        rootActions.add(Collections.singletonList(action));
                    else if(index < currentNodeSubMove.size()-1 && currentNodeSubMove.get(index+1).kind.equals(REMOVE))
                        rootActions.add(new ArrayList<Action<P>>(Arrays.asList(currentNodeSubMove.get(index), currentNodeSubMove.get(index+1))));
                    if (i == 0)
                        rootPositions.addAll(Arrays.asList(action.pos.get(0),action.pos.get(1)));
                    else if (action.kind.equals(Action.Kind.JUMP))
                        rootPositions.add(action.pos.get(1));
                }

                // Nel caso di una move calcola la posizione finale e aggiungi quest'ultima e la posizione iniziale alla lista
                // Non si considerano i casi di MOVE multiple (una dopo l'altra)
                if(action.kind.equals(MOVE)) rootPositions.addAll(Arrays.asList(action.pos.get(0), UsefulMethods.calcPos(action.pos.get(0), boardGr::adjacent, action.dir, action.steps)));
                i++;
            }


            int startIndex = rootPositions.size();

            // Se è una situazione in cui un certo numero di azioni sono forzate illuminale gradualmente
            if(startIndex != 0){
                if(startIndex >= currentMasterSelection.size() + 1){
                    highlight = Arrays.asList(rootPositions.get(currentMasterSelection.size()));
                }
                else if((currentMasterSelection.size() >= startIndex && currentMasterSelection.subList(0, startIndex).equals(rootPositions))){
                    ArrayList<Pos> movePositionsToHighLight = new ArrayList<>();
                    for(Move<P> move : moveChooser.childrenSubMoves()){
                        List<Pos> movePositions = new ArrayList<>();
                        int index = 0;
                        for(Action<P> currentAction : move.actions){
                            if( (currentAction.kind.equals(REMOVE) && move.actions.size() == 1)  || (currentAction.kind.equals(ADD) && move.actions.size() <= 2 )) movePositions.add(currentAction.pos.get(0));

                            if(currentAction.kind.equals(JUMP)){
                                if (index == 0)
                                    movePositions.addAll(Arrays.asList(currentAction.pos.get(1), currentAction.pos.get(1)));
                                else if (currentAction.kind.equals(Action.Kind.JUMP))
                                    movePositions.add(currentAction.pos.get(1));
                            }

                            if(currentAction.kind.equals(MOVE)) movePositions.addAll(Arrays.asList(currentAction.pos.get(0), UsefulMethods.calcPos(currentAction.pos.get(0), boardGr::adjacent, currentAction.dir, currentAction.steps)));
                            index++;
                        }

                        if(movePositions.size() > 0 && currentMasterSelection.size() - startIndex == 0){
                            movePositionsToHighLight.addAll(movePositions.subList(1, currentMasterSelection.size() - startIndex + 2));
                        }
                        else if(movePositions.size() > currentMasterSelection.size() - startIndex + 1){
                            movePositionsToHighLight.addAll(movePositions.subList(1, currentMasterSelection.size() - startIndex + 2));
                        }
                        else if(movePositions.size() == currentMasterSelection.size() - startIndex + 1){
                            movePositionsToHighLight.addAll(movePositions.subList(1, movePositions.size()));
                        }
                    }
                    highlight = movePositionsToHighLight;
                }
                board.updateBoard(highlight, Collections.emptyList());
            }


            // Se la radice è finale la eseguo (ovviamente dopo aver controllato che tutte le posizioni chiave della radice siano state selezionate)
            if(currentMasterSelection.equals(rootPositions)){
                if(moveChooser.isFinal()){
                    if (moveChooser.childrenSubMoves().size() == 0) {
                        makeMoveAndResetEverything();
                        return;
                    }
                }
            }

            // Se non sono ancora state selezionate tutte le posizioni della root ed eseguo un click non contenuto in essa ignoro il click, resetto tutto e ritorno
            if(startIndex != 0 && currentMasterSelection.size() < startIndex+1 && (!rootPositions.contains(p) || (currentMasterSelection.size() > 1 && currentMasterSelection.get(currentMasterSelection.size()-2).equals(p)))){
                this.masterSelection = new ArrayList<>();
                resetEverything(); // Per resettare in mezzo alla navigazione
                board.updateBoard(Collections.emptyList(), Collections.emptyList());
                return;
            }

            // Anche se la posizione è contenuta ma non ho ancora selezionate abbastanza ritorno immediatamente
            if(startIndex != 0 && currentMasterSelection.size() < startIndex+1){
                highlightHistory.add(highlight);
                List<Action<P>> currentActionHistory = actionHistory.size() == 0 ? new ArrayList<>() : actionHistory.get(actionHistory.size()-1);
                if(currentMasterSelection.size() >= 2) {
                    List<Action<P>> todoActions = rootActions.get(currentMasterSelection.size()-2);
                    currentActionHistory.addAll(todoActions);
                    board.updateBoard(highlight, todoActions);
                }
                if(actionHistory.size() == 0) actionHistory.add(currentActionHistory);
                return; // Se non sono state ancora selezionate abbastanza posizioni ritorno
            }

            // Calcolo le posizioni per eventuali possibili pezzi che possono catturare al prossimo nodo
            // Serve in Scacchi nel caso in cui un pezzo abbia come unica mossa disponibile la cattura di un altro pezzo
            // Il quale può essere catturato anche da un altro pezzo
            // In tale situazione si verifica che select(posizione del pezzo che cattura).size() == 0 perchè il pezzo non seleziona alcuna mossa valida
            // E non si può direttamente verificare la seconda azione del nodo perchè la REMOVE e la MOVE
            // Sono situate su due nodi differenti in quanto la REMOVE è decisionale
            List<Move<P>> capturingMoves = new ArrayList<>();
            for(Move<P> m : moveChooser.childrenSubMoves()){
                Action<P> firstAction = m.actions.get(0);
                if(firstAction.kind.equals(REMOVE) && m.actions.size() == 1){
                    moveChooser.select(firstAction.pos.get(0));
                    moveChooser.doSelection(null);
                    for(Move<P> subMove : moveChooser.childrenSubMoves()){
                        Action<P> subMoveFirstAction = subMove.actions.get(0);
                        Move<P> captureMove = new Move<>(firstAction, subMoveFirstAction);
                        if(subMoveFirstAction.kind.equals(MOVE) && subMove.actions.size() == 1) capturingMoves.add(captureMove);
                    }
                    moveChooser.back();
                }
            }
            capturingMoves.addAll(
                    moveChooser.childrenSubMoves().stream().filter(m -> {
                        List<Action<P>> actions = m.actions;
                        if(actions.size() == 2 && actions.get(0).kind.equals(REMOVE) && actions.get(1).kind.equals(MOVE)) return true;
                        else return false;
                    }).collect(Collectors.toCollection(ArrayList<Move<P>>::new)));

            boolean isCapturing = currentMasterSelection.size() != 1 ? false : capturingMoves.stream().filter(m -> m.actions.get(1).pos.get(0).equals(p)).findFirst().isPresent();
            boolean isBeingCaptured = currentMasterSelection.size() != 2 ? false : capturingMoves.stream().filter(m -> m.actions.get(0).pos.get(0).equals(p)).findFirst().isPresent();

            List<Pos> removingPos = capturingMoves.stream().filter(m -> m.actions.get(1).pos.get(0).equals(p)).map(m -> m.actions.get(0).pos.get(0)).collect(Collectors.toCollection(ArrayList<Pos>::new));

            if(isCapturing) highlight.addAll(removingPos);
            // Controllo le mosse quasi selezionate e quelle selezionate dalla posizione appena selezionata
            List<Move<P>> quasiSelectedMoves = new ArrayList<>();
            quasiSelectedMoves.addAll(moveChooser.quasiSelected());
            // Seleziona le mosse: se è il primo click seleziona la prima posizione della selezione corrente dopo le posizioni chiave del nodo
            // Non lancia eccezione perchè se ci sono meno posizioni delle posizioni chiave del nodo non ci si arriva proprio qui
            // Invece dopo il primo click bisogna selezionare sempre la penultima posizione perchè
            // <b> dopo aver selezionato tutte le posizioni chiave del nodo corrente </b> si parte sempre dalla penultima per trovare un'altra JUMP decisionale
            List<Move<P>> selectedMoves = currentMasterSelection.size() < 2 ? moveChooser.select(currentMasterSelection.get(startIndex)) :  moveChooser.select(currentMasterSelection.get(currentMasterSelection.size()-2));

            // Controllo se tutte le mosse selezionate sono costituite da una sola azione di tipo remove. In tal caso ignoro il click.
            // Nota: non funziona con i giochi in cui la remove è decisionale
            boolean allSimpleREMOVEActions = selectedMoves.stream().filter(m -> m.actions.get(0).kind.equals(REMOVE)).count() == selectedMoves.size() ? true : false;
            allSimpleREMOVEActions = selectedMoves.size() == 0 ? false : allSimpleREMOVEActions; // Se non ci sono mosse il controllo perde utilità
            if((selectedMoves.size() == 0 && quasiSelectedMoves.size() == 0 && !isCapturing && !isBeingCaptured)
                    || (allSimpleREMOVEActions && selectedMoves.size() > 0)){
                this.masterSelection = new ArrayList<>();
                resetEverything(); // Per resettare in mezzo alla navigazione
                return;
                    }

            // Se è la prima posizione (a partire da startindex)
            // --------------------------------------> SOLO PER ADD E REMOVE <---------------------------------

            if(currentMasterSelection.size() == 1){

                moveChooser.select(currentMasterSelection.get(currentMasterSelection.size() - 1));
                List<P> selectionPieces = moveChooser.selectionPieces();
                if(selectedMoves.size() != 0 && selectionPieces.size() != 0){

                    // TODO: da cambiare dopo per inserire la swap
                    Move<P> move = moveChooser.doSelection(selectionPieces.get(0));

                    // Se è una add la esegue a condizione che sia una add semplice (come in mnk) o una add seguita da swap (come in othello)
                    if(move.actions.get(0).kind.equals(Action.Kind.ADD) && moveChooser.isFinal() && (move.actions.size() == 1 || (move.actions.size() == 2 && move.actions.get(1).kind.equals(Action.Kind.SWAP)))){
                        makeMoveAndResetEverything();
                        return;
                    }

                    // Se è una remove la esegue a condizione che sia una remove semplice per i giochi in cui la remove è decisionale (però bisogna cambiare sopra)
                    else if (move.actions.get(0).kind.equals(REMOVE) && moveChooser.isFinal() && (move.actions.size() == 1)){
                        makeMoveAndResetEverything();
                        return;
                    }

                    // Se non è una add o una remove semplice non la eseguo e torno indietro
                    // TODO: controllare questa cosa per capire bene cosa bisogna selezionare e quale posizione va eliminata nella current master selection
                    moveChooser.back();
                    moveChooser.select(currentMasterSelection.get(startIndex));
                    currentMasterSelection.remove(currentMasterSelection.size()-1);
                }

                // Aggiorno le caselle da illuminare della board
                // TODO: aggiustare
                for(Move<P> move : selectedMoves){
                    Action<P> firstAction = move.actions.get(0);
                    if(firstAction.kind.equals(Action.Kind.MOVE)) highlight.add(UsefulMethods.calcPos(firstAction.pos.get(0), boardGr::adjacent, firstAction.dir, firstAction.steps));
                }
                for(Move<P> move : moveChooser.childrenSubMoves()){
                    Action<P> firstAction = move.actions.get(0);
                    if(firstAction.kind.equals(JUMP) && firstAction.pos.get(0).equals(p)) highlight.add(firstAction.pos.get(1));
                }
                board.updateBoard(highlight, Collections.emptyList());
            }
            // Dalla seconda posizione in poi
            else{

                if (currentMasterSelection.size() >= 2) {
                    // Se le posizioni selezionate oltre a quelle della root sono esattamente due esegui i controlli per la cattura
                    // -----------------------------> CONTROLLO PER CATTURA <-----------------------------------
                    if (currentMasterSelection.size() == startIndex + 2) {
                        // Lista delle mosse di cattura disponibili: Cattura = REMOVE seguita da JUMP. Per le mosse di cattura si usa l'ultima posizione selezionata
                        List<Move<P>> selectedRemoves = moveChooser.select(currentMasterSelection.get(currentMasterSelection.size() - 1));
                        if (selectedRemoves.size() != 0) {
                            Move<P> moveRemove = moveChooser.doSelection(null);
                            if (moveChooser.subMove().isPresent()) {
                                Move<P> currentMove = moveChooser.subMove().get();
                                Action<P> firstAction = currentMove.actions.get(0);
                                boolean FirstCondition = firstAction.kind.equals(REMOVE) && firstAction.pos.get(0).equals(currentMasterSelection.get(currentMasterSelection.size() - 1));
                                MyCouple<Board.Dir, Integer> moveInfo = UsefulMethods.calcDir(currentMasterSelection.get(0), currentMasterSelection.get(currentMasterSelection.size() - 1));
                                if (moveInfo != null && moveRemove != null && FirstCondition) {
                                    if (moveChooser.isFinal()) {
                                        Move<P> chosenMove = moveChooser.subMove().get();
                                        if (chosenMove.actions.size() == 2 && chosenMove.actions.get(1).kind.equals(MOVE) &&
                                                UsefulMethods.calcPos(chosenMove.actions.get(1).pos.get(0), boardGr::adjacent, chosenMove.actions.get(1).dir, chosenMove.actions.get(1).steps).equals(moveRemove.actions.get(0).pos.get(0))) {
                                            makeMoveAndResetEverything();
                                            return;
                                                }
                                    }
                                    else if(moveChooser.select(currentMasterSelection.get(currentMasterSelection.size()-1)).size() != 0 && moveChooser.selectionPieces() != null &&  moveChooser.selectionPieces().size() > 1){
                                        List<P> piecesToSelect = moveChooser.selectionPieces();
                                        ArrayList<ImagePattern> piecesImages = new ArrayList<>();
                                        for(PieceModel<PieceModel.Species> piece : (List<PieceModel< PieceModel.Species>>) piecesToSelect){
                                            String pieceName = (piece.color.equals("bianco") ? "White" : "Black") + piece.species.toString().toLowerCase().substring(0, 1).toUpperCase() + piece.species.toString().toLowerCase().substring(1);
                                            Image img = new Image(getClass().getResource("/Club/50/" + pieceName + ".png").toString());
                                            ImagePattern imagePattern = new ImagePattern(img);
                                            piecesImages.add(imagePattern);
                                        }
                                        // PER LO SWAP
                                        board.sendDialog(new DialogBox("Scegli il pezzo di promozione", null, piecesImages, this::selectPiece));
                                        return;
                                    }
                                    else if (moveChooser.select(currentMasterSelection.get(0)).size() != 0 && moveChooser.moveSelection(moveInfo.getFirst(), moveInfo.getSecond()) != null && moveChooser.isFinal()) {
                                        makeMoveAndResetEverything();
                                        this.masterSelection = new ArrayList<>();
                                        return;
                                    }
                                    // Aggiungere un else se il remove è decisionale ma non finale e non è seguito da una move o comunque non da quella move in particolare
                                }
                                else if (moveRemove != null && FirstCondition) {
                                    if (moveChooser.isFinal()) {
                                        if (currentMove.actions.get(currentMove.actions.size() - 1).equals(new Action<>(currentMasterSelection.get(startIndex), currentMasterSelection.get(startIndex + 1)))) {
                                            makeMoveAndResetEverything();
                                            return;
                                        }

                                    }
                                    if (moveChooser.select(currentMasterSelection.get(currentMasterSelection.size() - 1)).size() != 0
                                            && moveChooser.jumpSelection(currentMasterSelection.get(currentMasterSelection.size() - 1)) != null
                                            && moveChooser.isFinal()) {
                                        makeMoveAndResetEverything();
                                        return;
                                            }
                                }
                            }
                        }
                    }

                    //------------------ DAL SECONDO CLICK IN POI ESCLUSO CATTURA -----------------------------
                    if (selectedMoves.size() == 0 && quasiSelectedMoves.size() == 0) {
                        this.masterSelection = new ArrayList<>();
                        resetEverything(); // Per resettare in mezzo alla navigazione
                        return;
                    }

                    selectedMoves = moveChooser.select(currentMasterSelection.get(currentMasterSelection.size() - 2));
                    if (selectedMoves.size() != 0) {

                        // Per le mosse di tipo move semplici senza catture (non funziona con i giochi che usano move multiple)
                        MyCouple<Board.Dir, Integer> moveInfo = UsefulMethods.calcDir(currentMasterSelection.get(startIndex), p);
                        if (moveInfo != null && moveChooser.moveSelection(moveInfo.getFirst(), moveInfo.getSecond()) != null && moveChooser.isFinal()) {
                            makeMoveAndResetEverything();
                            return;
                        }

                        // Per le mosse di tipo jump anche con remove e anche multiple
                        else if (moveChooser.jumpSelection(currentMasterSelection.get(currentMasterSelection.size() - 1)) != null) {
                            Move<P> currentMove = moveChooser.subMove().get();
                            List<Action<P>> updateActions = new ArrayList<>();
                            if(currentMove.actions.size() >= 2){
                                if(currentMove.actions.get(0).kind.equals(JUMP) && currentMove.actions.get(1).kind.equals(REMOVE))
                                    updateActions.addAll(Arrays.asList(currentMove.actions.get(0), currentMove.actions.get(1)));
                                else if(currentMove.actions.get(0).kind.equals(JUMP)) updateActions.add(currentMove.actions.get(0));
                            }
                            else if(currentMove.actions.size() == 1 && currentMove.actions.get(0).kind.equals(JUMP)) updateActions.add(currentMove.actions.get(0));
                            board.updateBoard(highlight, updateActions);
                            actionHistory.add(updateActions);

                            List<Pos> movePositions = new ArrayList<>();
                            boolean realSelected = true;
                            for (int index = 0; index < currentMove.actions.size(); index++) {
                                if (!currentMove.actions.get(index).kind.equals(Action.Kind.JUMP) && !currentMove.actions.get(index).kind.equals(REMOVE)) {
                                    realSelected = false;
                                    break;
                                }
                                if (currentMasterSelection.size() == 2 && index == 0)
                                    movePositions.addAll(Arrays.asList(currentMove.actions.get(index).pos.get(0), currentMove.actions.get(index).pos.get(1)));
                                else if (currentMove.actions.get(index).kind.equals(Action.Kind.JUMP))
                                    movePositions.add(currentMove.actions.get(index).pos.get(1));
                            }
                            if (!movePositions.equals(currentMasterSelection.subList(startIndex, currentMasterSelection.size()))) {
                                realSelected = false;
                            }


                            if (currentMove.actions.size() == 1 || realSelected) {
                                // Se la sottomossa del nodo è finale ed è costituita da una sola azione JUMP eseguila
                                if (moveChooser.isFinal()) {
                                    if (!(moveChooser.childrenSubMoves().size() > 0)) {
                                        makeMoveAndResetEverything();
                                        return;
                                    }
                                    else if(realSelected) {
                                        masterSelection.add(new ArrayList<>(currentMasterSelection.subList(currentMasterSelection.size()-2, currentMasterSelection.size())));
                                        highlight = moveChooser.childrenSubMoves().stream().filter(move -> move.actions.get(0).kind.equals(JUMP)).map(move -> move.actions.get(0).pos.get(1)).collect(Collectors.toCollection(ArrayList<Pos>::new));
                                        highlightHistory.add(highlight);
                                        board.updateBoard(highlight, Collections.emptyList());
                                        return;
                                    }
                                }
                                // Altrimenti se non è finale ma è comunque costituita da una sola azione lo spostamento del nodo va bene e ritorna
                                else if (!moveChooser.isFinal()) {

                                    masterSelection.add(new ArrayList<>(currentMasterSelection.subList(currentMasterSelection.size()-2, currentMasterSelection.size())));
                                    List<Pos> positionToHighlight = moveChooser.childrenSubMoves().stream().map(move -> move.actions.get(0).pos.get(1)).collect(Collectors.toCollection(ArrayList::new));
                                    highlight = positionToHighlight;
                                    highlightHistory.add(highlight);
                                    board.updateBoard(highlight, Collections.emptyList());
                                    return;
                                }
                            } else if (realSelected == false) {
                                List<Pos> newHighlight = new ArrayList<>();
                                newHighlight.add(movePositions.get(currentMasterSelection.subList(startIndex, currentMasterSelection.size()).size()));
                                highlightHistory.add(newHighlight);
                                board.updateBoard(newHighlight, Collections.emptyList());
                                masterSelection.add(new ArrayList<>(currentMasterSelection.subList(currentMasterSelection.size()-2, currentMasterSelection.size())));
                                //moveChooser.back();
                                return;
                            }
                        } else {
                            highlight = new ArrayList<>();
                            board.updateBoard(highlight, Collections.emptyList());
                            this.masterSelection = new ArrayList<>();
                            resetEverything(); // Per resettare in mezzo alla navigazione
                            return;
                        }
                    }
                    // TODO in teoria questo controllo va eliminato
                    else {
                        this.masterSelection = new ArrayList<>();
                        resetEverything(); // Per resettare in mezzo alla navigazione
                    }

                }
            }
        }
    }


    public synchronized void setBoard(BoardGUI<P> board){
            this.board = board;
            this.boardGr = board.getBoard();
    }

    public synchronized void updateBoardGr(Board<P> boardGr){
            this.boardGr = boardGr;
    }

    public synchronized void selectPiece(int pieceIndex){
        synchronized (moveChooser) {
            board.clearDialog();
            List<P> selectionPieces = moveChooser.selectionPieces();
            moveChooser.doSelection(selectionPieces.get(pieceIndex));
            if(moveChooser.isFinal()) moveChooser.move();
            highlightHistory = new ArrayList<>();
            board.updateBoard(Collections.emptyList(), Collections.emptyList());
            this.masterSelection = new ArrayList<>();
        }
    }


    public synchronized void moveChooserBack(){
        synchronized (moveChooser) {
            Map<Pos, P> startingBoardMap = UsefulMethods.createMap(boardGr);

            List<Pos> previousHighlights = new ArrayList<>();
            if(highlightHistory.size() > 0){
                highlightHistory.remove(highlightHistory.size()-1);
                previousHighlights = highlightHistory.size() > 0 ?  highlightHistory.get(highlightHistory.size()-1) : new ArrayList<>();
            }
            List<Action<P>> currentActionHistory = actionHistory.size() == 0 ? new ArrayList<>() : actionHistory.get(actionHistory.size()-1);
            if(currentActionHistory.size() == 1) {
                moveChooser.back();
                board.updateBoard(previousHighlights, null);
                if(actionHistory.size() != 0) actionHistory.remove(actionHistory.size()-1);
            }
            else if(currentActionHistory.size() > 1){
                Action<P> lastAction = currentActionHistory.get(currentActionHistory.size()-1);
                List<Action<P>> latestActions = new ArrayList<>();
                if(currentActionHistory.size() == 1 && lastAction.kind.equals(JUMP)){
                    latestActions = Collections.singletonList(lastAction);
                    currentActionHistory.remove(currentActionHistory.size()-1);
                }
                else if(currentActionHistory.size() > 1){
                    Action<P> actionBeforeLast = currentActionHistory.get(currentActionHistory.size()-2);
                    if(lastAction.kind.equals(REMOVE) && actionBeforeLast.kind.equals(JUMP)){
                        latestActions = Arrays.asList(lastAction, actionBeforeLast);
                        currentActionHistory.remove(currentActionHistory.size()-1);
                        currentActionHistory.remove(currentActionHistory.size()-1);
                    }
                    else if(lastAction.kind.equals(JUMP)){
                        latestActions = Arrays.asList(lastAction);
                        currentActionHistory.remove(currentActionHistory.size()-1);
                    }
                }

                if(currentActionHistory.size() == 0) actionHistory.remove(actionHistory.size()-1);

                List<Action<P>> invertedActions = new ArrayList<>();

                List<List<P>> affectedPiecesActions = new ArrayList<>();
                for (Action<P> action : latestActions)
                    affectedPiecesActions.add(UsefulMethods.doAction(startingBoardMap, action, boardGr::adjacent));
                for (int i = latestActions.size() - 1; i >= 0; i--) {
                    invertedActions.addAll(UsefulMethods.revertedAction(latestActions.get(i), boardGr::adjacent, affectedPiecesActions.get(i)));
                }
                this.board.updateBoard(previousHighlights, invertedActions);
                if(masterSelection.size() > 0){
                    List<Pos> currentMasterSelection = masterSelection.get(masterSelection.size()-1);
                    if(currentMasterSelection.size() == 0) masterSelection.remove(masterSelection.size()-1);
                    else currentMasterSelection.remove(currentMasterSelection.size()-1);
                }
                return;
            }
            else board.updateBoard(Collections.emptyList(), null);
        }
    }

    private void makeMoveAndResetEverything(){
        moveChooser.move();
        actionHistory = Collections.synchronizedList(new ArrayList<>());
        masterSelection = Collections.synchronizedList(new ArrayList<>());
        highlightHistory = Collections.synchronizedList(new ArrayList<>());
        board.updateBoard(Collections.emptyList(), new ArrayList<Action<P>>());
    }

    private void resetEverything(){
        actionHistory = Collections.synchronizedList(new ArrayList<>());
        masterSelection = Collections.synchronizedList(new ArrayList<>());
        highlightHistory = Collections.synchronizedList(new ArrayList<>());
        Move<P> backActions = moveChooser.back();
        while(backActions != null) backActions = moveChooser.back();
        board.updateBoard(Collections.emptyList(), null);

    }

    public synchronized void moveChooserResign(){
        synchronized (moveChooser) {
            moveChooser.resign();
        }
    }

    public synchronized boolean mayPass(){
        synchronized (moveChooser) {
            return moveChooser.mayPass();
        }
    }

    public synchronized void pass(){
        synchronized (moveChooser) {
            moveChooser.pass();
        }
    }


}
