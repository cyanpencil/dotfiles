package gapp.ulg.play;

import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.board.Player;

import java.util.*;

/** <b>IMPLEMENTARE I METODI SECONDO LE SPECIFICHE DATE NEI JAVADOC. Non modificare
 * le intestazioni dei metodi.</b>
 * <br>
 * Un oggetto RandPlayer è un oggetto che può giocare un qualsiasi gioco regolato
 * da un {@link GameRuler} perché, ad ogni suo turno, sceglie in modo random una
 * mossa tra quelle valide esclusa {@link Move.Kind#RESIGN}.
 * @param <P>  tipo del modello dei pezzi */
public class RandPlayer<P> implements Player<P> {
    private final String name;
    private GameRuler<P> game;

    /** Crea un giocatore random, capace di giocare a un qualsiasi gioco, che ad
     * ogni suo turno fa una mossa scelta in modo random tra quelle valide.
     * @param name  il nome del giocatore random
     * @throws NullPointerException se name è null */
    public RandPlayer(String name) {
        if(name == null) throw new NullPointerException("Inserire un nome valido");
        this.name = name;
    }

    @Override
    public String name() { return name; }

    @Override
    public void setGame(GameRuler<P> g) {
        if(g == null) throw new NullPointerException("Inserire una partita valida");
        this.game = g.copy();
    }

    @Override
    public void moved(int i, Move<P> m) {
        if(game == null || game.result() != -1) throw new IllegalStateException("Nessun gioco impostato o partita conclusa");
        if(m == null) throw new NullPointerException("Inserire una mossa non nulla");
        List<Integer> turnsList = new ArrayList<>();
        for(int x = 0; x < game.players().size(); x++){
            turnsList.add(x+1);
        }
        if(!game.isValid(m)) throw new IllegalArgumentException("Mossa invalida");
        if(!turnsList.contains(i)) throw new IllegalArgumentException("Inserire un indice di turnazione e una mossa validi");
        this.game.move(m);
    }

    @Override
    public Move<P> getMove() {
        int playerIndex = this.game.players().indexOf(this.name)+1;
        if(game == null || game.result() != -1) throw new IllegalStateException("Nessun gioco impostato o partita conclusa");
        Set<String> playerSet = new HashSet<>(this.game.players());
        if(game.turn() != playerIndex && playerSet.size() != 1){
            throw new IllegalStateException("Non è il turno di questo giocare");
        }
        if(this.name != this.game.players().get(this.game.turn()-1)) throw new IllegalStateException("Non è il turno di questo giocatore");

        List<Move<P>> validMoves = new ArrayList<Move<P>>(game.validMoves());
        validMoves.remove(new Move<>(Move.Kind.RESIGN));
        int size = validMoves.size();
        int item = new Random().nextInt(size);
        Move<P> move = validMoves.get(item);
        return move;
    }
}
