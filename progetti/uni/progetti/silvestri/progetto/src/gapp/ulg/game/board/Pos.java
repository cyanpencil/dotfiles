package gapp.ulg.game.board;

import java.io.Serializable;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi nè i campi pubblici.</b>
 * <br>
 * Un oggetto Pos rappresenta una posizione in una {@link Board}. Gli oggetti
 * Pos sono immutabili. Per il significato delle coordinate vedere {@link Board} e
 * la documentazione relative agli elementi di {@link Board.System}. */
public class Pos implements Serializable {
    /** Le coordinate della posizione, b è la coordinata rispetto all'asse base
     * e t quella relativa all'asse trasversale. */
    public final int b, t;

    /** Crea una posizione con le coordinate date.
     * @param b  coordinata asse base (non negativa)
     * @param t  coordinata asse trasversale (non negativa)
     * @throws IllegalArgumentException se una delle coordinate è nagativa */
    public Pos(int b, int t) {
        if(b < 0 || t < 0){
            throw new IllegalArgumentException("Coordinate scorrette: (" + b + ", " + t + ")" );
        }
        else {
            this.b = b;
            this.t = t;
        }
    }

    /** Questa posizione è uguale a x se e solo se x è della classe {@link Pos}
     * ed ha le stesse coordinate.
     * @param x un oggetto (o null)
     * @return true se x è uguale a questa posizione */
    @Override
    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null || getClass() != x.getClass()) return false;

        Pos pos = (Pos) x;

        if (b != pos.b || t != pos.t) return false;
        return t == pos.t;
    }

    /** Ridefinito coerentemente con la ridefinizione di {@link Pos#equals(Object)}.
     * @return l'hash code di questa posizione */
    @Override
    public int hashCode() {
        int result = b;
        result = 31 * result + t;
        return result;
    }

    //------------------------->Inizio Metodi di Utilità<-------------------------
    @Override
    public String toString(){
        return "(" + this.b + ", " + this.t + ")";
    }

    /** Costruttore per una Pos anche negativa
     * a seconda del valore di boolean
     * @param b     coordinata b
     * @param t     coordinata t
     * @param negative  se negative == true le coordinate di Pos possono essere anche negative
     */
    public Pos(int b, int t, boolean negative) {
        if(!negative && (b < 0 || t < 0)){
            throw new IllegalArgumentException("Coordinate scorrette: (" + b + ", " + t + ")" );
        }
        else {
            this.b = b;
            this.t = t;
        }
    }
    //------------------------->Fine Metodi di Utilità<-------------------------
}