package gapp.ulg.game.board;

import java.util.*;
/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi nè i campi pubblici.</b>
 * <br>
 * Un oggetto Move rappresenta una mossa di un giocatore nel suo turno di gioco.
 * Gli oggetti Move sono immutabili. Le mosse possono essere di vari tipi
 * {@link Move.Kind}, il tipo più importante è {@link Move.Kind#ACTION} che
 * rappresenta una mossa che cambia la disposizione di uno o più pezzi sulla board
 * del gioco. Una mossa di tipo {@link Move.Kind#ACTION} è composta da una sequenza
 * di una o più azioni, cioè oggetti di tipo {@link Action}, ad es. la mossa di una
 * pedina nella Dama che salta e mangia un'altra pedina è composta da un'azione di
 * tipo {@link Action.Kind#JUMP} seguita da un'azione di tipo
 * {@link Action.Kind#REMOVE}.
 * @param <P>  tipo del modello dei pezzi */
public class Move<P> {
    /** Tipi di una mossa */
    public enum Kind {
        /** Effettua una o più azioni ({@link Action}) */
        ACTION,
        /** Passa il turno di gioco */
        PASS,
        /** Abbandona il gioco, cioè si arrende */
        RESIGN
    }

    /** Tipo della mossa, non è mai null */
    public final Kind kind;
    /** Sequenza di azioni della mossa, non è mai null, la lista non è vuota
     * solamente se il tipo della mossa è {@link Kind#ACTION}, la lista è
     * immodificabile */
    public final List<Action<P>> actions;

    /** Crea una mossa che non è di tipo {@link Kind#ACTION}.
     * @param k  tipo della mossa
     * @throws NullPointerException se k è null
     * @throws IllegalArgumentException se k è {@link Kind#ACTION} */
    public Move(Kind k) {
        if(k == null) throw new NullPointerException("Inserire un tipo non nullo");
        if(k == Kind.ACTION) throw new IllegalArgumentException("Inserire un tipo di mossa che non sia ACTION");
        this.kind = k;
        this.actions = Collections.unmodifiableList(Collections.emptyList());
    }

    /** Crea una mossa di tipo {@link Kind#ACTION}.
     * @param aa  la sequenza di azioni della mossa
     * @throws NullPointerException se una delle azioni è null
     * @throws IllegalArgumentException se non è data almeno un'azione */
    @SafeVarargs
    public Move(Action<P>...aa) {
        List<Action<P>> actionList = new ArrayList<>(Arrays.asList(aa));
        if(actionList.contains(null)) throw new NullPointerException("Inserire una sequenza di azioni non nulle");
        if(actionList.isEmpty()) throw new IllegalArgumentException("Inserire almeno un'azione");
        this.kind = Kind.ACTION;
        this.actions = Collections.unmodifiableList(actionList);
    }

    /** Crea una mossa di tipo {@link Kind#ACTION}. La lista aa è solamente letta e
     * non è mantenuta nell'oggetto creato.
     * @param aa  la sequenza di azioni della mossa
     * @throws NullPointerException se aa è null o una delle azioni è null
     * @throws IllegalArgumentException se non è data almeno un'azione */
    public Move(List<Action<P>> aa) {
        if(aa.contains(null)) throw new NullPointerException("Inserire una sequenza di azioni valide");
        if(aa.isEmpty()) throw new IllegalArgumentException("Inserire almeno un'azione");
        this.kind = Kind.ACTION;
        List<Action<P>> actionList = new ArrayList<>();
        for(Action<P> action : aa){
            actionList.add(action);
        }
        this.actions = Collections.unmodifiableList(actionList);
    }

    /** Ritorna true se e solo se x è un oggetto di tipo {@link Move} ed ha gli
     * stessi valori dei campi {@link Move#kind} e {@link Move#actions}.
     * @param x  un oggetto (o null)
     * @return true se x è uguale a questa mossa */
    @Override
    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null || getClass() != x.getClass()) return false;

        Move<?> move = (Move<?>) x;

        if (kind != move.kind) return false;
        return actions != null ? actions.equals(move.actions) : move.actions == null;
    }

    /** Ridefinito coerentemente con la ridefinizione di
     * {@link PieceModel#equals(Object)}.
     * @return hash code di questa mossa */
    @Override
    public int hashCode() {
        int result = kind.hashCode();
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        if(this.kind == Kind.PASS) return "PASS";
        if(this.kind == Kind.RESIGN) return "RESIGN";
        String move = "";
        for(Action<P> action : this.actions){
            move += action.toString() + " | ";
        }
        return move;
    }


    public Move<P> revertedMove(){
        //List<Action<P>> revertedActions = this.actions.stream().map(action -> action.reverted()).collect(Collectors.toList());
        return null;
    }
}
