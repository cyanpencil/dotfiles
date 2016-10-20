package gapp.ulg.Utilities;

import gapp.ulg.game.board.Action;

import java.util.ArrayList;
import java.util.HashMap;
import gapp.ulg.game.board.*;
import java.util.*;

public class Node<P> {
    private final Node parentNode;
    private final ArrayList<Action<P>> prefix;
    private ArrayList<Node<P>> childNodes;

    public Node(Node<P> parentNode, ArrayList<Action<P>> prefix, ArrayList<Node<P>> childNodes){
        this.parentNode = parentNode;
        this.prefix = prefix;
        this.childNodes = childNodes != null ? childNodes : new ArrayList<>();
    }

    /** Ritorna il nodo padre. Nel caso il nodo è la radice, ritorna null
     * @return il genitore. */
    public Node<P> getParentNode(){
        return this.parentNode;
    }

    /** Ritorna il prefisso del nodo corrente.
     * @return una lista di azioni che corrispondono al prefisso del nodo corrente.
     *      La lista ritornata è sempre la stessa.*/
    public ArrayList<Action<P>> getPrefix(){
        return this.prefix;
    }

    /** Ritorna la sottomossa del nodo corrente.
     * Nel caso il nodo è la radice, ritorna il suo prefisso.
     * @return una lista di azioni che corrispondo alla sottomossa rappresentata dal nodo.*/
    public List<Action<P>> getRelativePrefix(){
        if (parentNode == null) return getPrefix();
        return getPrefix().subList(parentNode.getPrefix().size(), getPrefix().size());
    }

    /** Ritorna la lista dei nodi figli.
     * @return la lista dei nodi figli.*/
    public ArrayList<Node<P>> getChildNodes(){
        return this.childNodes;
    }

    /** Imposta i figli del nodo.
     * @param childNodes la lista dei nodi che diventerrano i figli di questo nodo.*/
    public void setChildNodes(ArrayList<Node<P>> childNodes){
        this.childNodes = childNodes;
    }

    /** Genera l'albero delle mosse.
     * Usa la funzione ricorsiva {@link #actionTree()} per generare l'albero.
     * Se la lista di mosse è vuota, allora ritorna subito null.
     * @param allActionList una lista di liste di azioni, che corrispondo alle mosse di {@link gameRuler#validMoves()}
     * @param <P> tipo dei pezzi di gioco.
     * @return la radice dell'albero.*/
    public static <P> Node<P> genTree(ArrayList<ArrayList<Action<P>>> allActionList){
        if(allActionList == null || allActionList.isEmpty()) return null; // TODO da rivedere
        Node root = new Node(null, UsefulMethods.biggestSubList(0, allActionList), null);
        actionTree(root, allActionList);
        return root;
    }


    public static int index = 0;
    private static <P> void actionTree(Node parent, ArrayList<ArrayList<Action<P>>> allActionList){
        int startIndex = UsefulMethods.biggestSubList(index, allActionList).size() + index;// INDICE DELLA PRIMA AZIONE DIVERSA

        HashMap<Action<P>, ArrayList<ArrayList<Action<P>>>> firstActionsMap = new HashMap<>(); 
        allActionList.forEach((move) -> {
            // TODO da capire cosa fare quando rimane solo l'ultima mossa quindi e quindi move.size() == startIndex
            if(move.size() != startIndex) {
                ArrayList<ArrayList<Action<P>>> newValue = firstActionsMap.get(move.get(startIndex));
                if (newValue == null) newValue = new ArrayList<ArrayList<Action<P>>>();
                newValue.add((ArrayList<Action<P>>) move);
                firstActionsMap.put(move.get(startIndex), newValue);
            }
        });


        // Rappresenta una lista di liste di mosse
        // In particolare una lista in cui le mosse vengono raggruppate in base alla prima azione.
        // Tutte le mosse che hanno la prima azione uguale vengono inserite in una stessa lista e così via
        // E poi tutte le liste che contengono mosse con la stessa azione iniziale vengono inserite in un'unica lista che le contiene tutte
        // In tal modo si ottiene una struttura di questo tipo:
        // Qui tutte le mosse che iniziano così, di qua invece tutte le mosse che iniziano in quest'altro modo ecc...
        ArrayList<ArrayList<ArrayList<Action<P>>>> subMovesActionsList = new ArrayList<>();
        for(ArrayList<ArrayList<Action<P>>> subActionsList : firstActionsMap.values()){
            ArrayList<ArrayList<Action<P>>> subMoveActionsList = new ArrayList<>();
            for(ArrayList<Action<P>> subAction : subActionsList){
                subMoveActionsList.add(subAction);
            }
            subMovesActionsList.add(subMoveActionsList);
        }

        ArrayList<Node<P>> childNodes = new ArrayList<>();
        for(ArrayList<ArrayList<Action<P>>> actionList : subMovesActionsList){
            boolean merda = false;

            for(ArrayList<Action<P>> move : actionList){
                if(move.size() <= startIndex) merda = true;
            }
            if (merda) continue;
            ArrayList<Action<P>> maxSub = UsefulMethods.biggestSubList(index, actionList);
            ArrayList<Action<P>> maxSub2 = UsefulMethods.biggestSubList(startIndex, actionList);
            ArrayList<Action<P>> prefix = (ArrayList<Action<P>>) parent.getPrefix().clone();
            prefix.addAll(maxSub2);
            Node childNode = new Node(parent, prefix, null);

            boolean leaf = false, notLastLeaf = false;

            //for(ArrayList<Action<P>> move : actionList){
                //move.removeAll(maxSub);
            //}
            index += maxSub.size();
            actionTree(childNode, actionList);
            index -= maxSub.size();

            childNodes.add(childNode);
        }
        parent.setChildNodes(childNodes);
    }


    /** Stampa in forma testuale una rappresentazione grafica dell'albero delle mosse.
     * @param prefix il prefisso iniziale
     * @param isTail boolean che setta il controllo degli spazi.*/
    private void printTree(String prefix, boolean isTail) {
        if(this.parentNode == null) System.out.println(prefix + (isTail ? "└── " : "├── ") + this.prefix.toString());
        else System.out.println(prefix + (isTail ? "└── " : "├── ") + this.prefix.subList(this.parentNode.getPrefix().size(), this.prefix.size()).toString());
        for (int i = 0; i < this.childNodes.size() - 1; i++) {
            childNodes.get(i).printTree(prefix + (isTail ? "    " : "│   "), false);
        }
        if (childNodes.size() > 0) {
            childNodes.get(childNodes.size() - 1).printTree(prefix + (isTail ?"    " : "│   "), true);
        }
    }


    public void printTree() {
        printTree("", true);
    }
}
