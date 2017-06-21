package gapp.ulg.game.util;
import gapp.ulg.game.GameFactory;
import gapp.ulg.game.PlayerFactory;
import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Move;
import gapp.ulg.game.board.Player;
import gapp.ulg.games.GameFactories;
import gapp.ulg.play.PlayerFactories;
import gapp.ulg.game.*;
import static gapp.ulg.game.util.PlayerGUI.MoveChooser;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.*;
import java.util.function.*;


/** Un {@code PlayGUI} è un oggetto che facilita la gestione di partite in una
 * applicazione controllata da GUI. Un {@code PlayGUI} segue lo svolgimento di una
 * partita dalla scelta della {@link GameFactory} e dei {@link PlayerFactory} e di
 * tutte le mosse fino alla fine naturale della partita o alla sua interruzione.
 * Inoltre, un {@code PlayGUI} aiuta sia a mantenere la reattività della GUI che a
 * garantire la thread-safeness usando un thread di confinamento per le invocazioni
 * di tutti i metodi e costruttori degli oggetti coinvolti in una partita.
 * @param <P>  tipo del modello dei pezzi */
public class PlayGUI<P> {


    private long maxBlockTime = -1, tol = -1, minTime = -1,  matchTimeout = -1;
    private volatile long threadTimeout = -1;

    private ExecutorService confiner;
    private Thread controllore = new Thread();

    private GameFactory gameFactory;
    private GameRuler<P> gameRuler;

    Observer<P> observer;

    private List<Player<P>> players = new ArrayList<>();
    private List<PlayerFactory> playerFactories = new ArrayList<>();
    private List<PlayerGUI> playerGUIs = new ArrayList<>();
    private List<ForkJoinPool> forkJoinPools = new ArrayList<>();
    private List<ExecutorService> executors = new ArrayList<>();

    private final Object lock = new Object();
    private final AtomicBoolean playing = new AtomicBoolean(false), executing = new AtomicBoolean(false);



    /** Un {@code Observer} è un oggetto che osserva lo svolgimento di una o più
     * partite. Lo scopo principale è di aggiornare la GUI che visualizza la board
     * ed eventuali altre informazioni a seguito dell'inizio di una nuova partita e
     * di ogni mossa eseguita.
     * @param <P>  tipo del modello dei pezzi */
    public interface Observer<P> {
        /** Comunica allo {@code Observer} il gioco (la partita) che sta iniziando.
         * Può essere nello stato iniziale o in uno stato diverso, ad es. se la
         * partita era stata sospesa ed ora viene ripresa. L'oggetto {@code g} è
         * una copia del {@link GameRuler} ufficiale del gioco. Lo {@code Observer}
         * può usare e modificare {@code g} a piacimento senza che questo abbia
         * effetto sul {@link GameRuler} ufficiale. In particolare lo {@code Observer}
         * può usare {@code g} per mantenersi sincronizzato con lo stato del gioco
         * riportando in {@code g} le mosse dei giocatori, vedi
         * {@link Observer#moved(int, Move)}. L'uso di {@code g} dovrebbe avvenire
         * solamente nel thread in cui il metodo è invocato.
         * <br>
         * <b>Il metodo non blocca, non usa altri thread e ritorna velocemente.</b>
         * @param g  un gioco, cioè una partita
         * @throws NullPointerException se {@code g} è null */
        void setGame(GameRuler<P> g);

        /** Comunica allo {@code Observer} la mossa eseguita da un giocatore. Lo
         * {@code Observer} dovrebbe usare tale informazione per aggiornare la sua
         * copia del {@link GameRuler}. L'uso del GameRuler dovrebbe avvenire
         * solamente nel thread in cui il metodo è invocato.
         * <br>
         * <b>Il metodo non blocca, non usa altri thread e ritorna velocemente.</b>
         * @param i  indice di turnazione di un giocatore
         * @param m  la mossa eseguita dal giocatore
         * @throws IllegalStateException se non c'è un gioco impostato o c'è ma è
         * terminato.
         * @throws NullPointerException se {@code m} è null
         * @throws IllegalArgumentException se {@code i} non è l'indice di turnazione
         * di un giocatore o {@code m} non è una mossa valida nell'attuale situazione
         * di gioco */
        void moved(int i, Move<P> m);

        /** Comunica allo {@code Observer} che il giocatore con indice di turnazione
         * {@code i} ha violato un vincolo sull'esecuzione (ad es. il tempo concesso
         * per una mossa). Dopo questa invocazione il giocatore {@code i} è
         * squalificato e ciò produce gli stessi effetti che si avrebbero se tale
         * giocatore si fosse arreso. Quindi lo {@code Observer} per sincronizzare
         * la sua copia con la partita esegue un {@link Move.Kind#RESIGN} per il
         * giocatore {@code i}. L'uso del GameRuler dovrebbe avvenire solamente nel
         * thread in cui il metodo è invocato.
         * @param i  indice di turnazione di un giocatore
         * @param msg  un messaggio che descrive il tipo di violazione
         * @throws NullPointerException se {@code msg} è null
         * @throws IllegalArgumentException se {@code i} non è l'indice di turnazione
         * di un giocatore */
        void limitBreak(int i, String msg);

        /** Comunica allo {@code Observer} che la partita è stata interrotta. Ad es.
         * è stato invocato il metodo {@link PlayGUI#stop()}.
         * @param msg  una stringa con una descrizione dell'interruzione */
        void interrupted(String msg);
    }

    /** Crea un oggetto {@link PlayGUI} per partite controllate da GUI. L'oggetto
     * {@code PlayGUI} può essere usato per giocare più partite anche con giochi e
     * giocatori diversi. Per garantire che tutti gli oggetti coinvolti
     * {@link GameFactory}, {@link PlayerFactory}, {@link GameRuler} e {@link Player}
     * possano essere usati tranquillamente anche se non sono thread-safe, crea un
     * thread che chiamiamo <i>thread di confinamento</i>, in cui invoca tutti i
     * metodi e costruttori di tali oggetti. Il thread di confinamento può cambiare
     * solo se tutti gli oggetti coinvolti in una partita sono creati ex novo. Se
     * durante una partita un'invocazione (ad es. a {@link Player#getMove()}) blocca
     * il thread di confinamento per un tempo superiore a {@code maxBlockTime}, la
     * partita è interrotta.
     * <br>
     * All'inizio e durante una partita invoca i metodi di {@code obs}, rispettando
     * le specifiche di {@link Observer}, sempre nel thread di confinamento.
     * <br>
     * <b>Tutti i thread usati sono daemon thread</b>
     * @param obs  un osservatore del gioco
     * @param maxBlockTime  tempo massimo in millisecondi di attesa per un blocco
     *                      del thread di confinamento, se < 0, significa nessun
     *                      limite di tempo
     * @throws NullPointerException se {@code obs} è null */
    public PlayGUI(Observer<P> obs, long maxBlockTime) {
        this.maxBlockTime = maxBlockTime;
        this.observer = obs;
    }

    /** Imposta la {@link GameFactory} con il nome dato. Usa {@link GameFactories}
     * per creare la GameFactory nel thread di confinamento. Se già c'era una
     * GameFactory impostata, la sostituisce con la nuova e se c'erano anche
     * PlayerFactory impostate le cancella. Però se c'è una partita in corso,
     * fallisce.
     * @param name  nome di una GameFactory
     * @throws NullPointerException se {@code name} è null
     * @throws IllegalArgumentException se {@code name} non è il nome di una
     * GameFactory
     * @throws IllegalStateException se la creazione della GameFactory fallisce o se
     * c'è una partita in corso. */
    public void setGameFactory(String name) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (name == null) throw new NullPointerException();

        players.clear();
        playerFactories.clear();
        createConfiner();

        GameFactory game;
        Future<GameFactory> gameFactory;
        try {
            confiner.submit(() -> {
                if (! Arrays.asList(GameFactories.availableBoardFactories()).contains(name)) throw new IllegalArgumentException();
                GameFactory g;
                try {
                    g = GameFactories.getBoardFactory(name);
                } catch (Exception e) {
                    throw new IllegalStateException();
                }
                if (g == null) throw new IllegalStateException();
                this.gameFactory = g;
            }).get();
        } catch (ExecutionException e) {
                if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
                if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
                else throw new RuntimeException();
        } catch (InterruptedException e) {throw new RuntimeException();}
    }

    /** Ritorna i nomi dei parametri della {@link GameFactory} impostata. Se la
     * GameFactory non ha parametri, ritorna un array vuoto.
     * @return i nomi dei parametri della GameFactory impostata
     * @throws IllegalStateException se non c'è una GameFactory impostata */
    public String[] getGameFactoryParams() {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null) throw new IllegalStateException("GameFactory non impostata");

        try {
            return confiner.submit(() -> {
                if(this.gameFactory == null) throw new IllegalStateException("GameFactory non impostata");
                int paramsSize = this.gameFactory.params().size();
                String[] paramNames = new String[paramsSize];
                for (int i = 0; i < paramsSize; i++) {
                    paramNames[i] = ((Param) this.gameFactory.params().get(i)).name();
                }
                return paramNames;
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalStateException.class)) throw (IllegalStateException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getGameFactoryParams()");
    }

    /** Ritorna il prompt del parametro con il nome specificato della
     * {@link GameFactory} impostata.
     * @param paramName  nome del parametro
     * @return il prompt del parametro con il nome specificato della GameFactory
     * impostata.
     * @throws NullPointerException se {@code paramName} è null
     * @throws IllegalArgumentException se la GameFactory impostata non ha un
     * parametro di nome {@code paramName}
     * @throws IllegalStateException se non c'è una GameFactory impostata */
    public String getGameFactoryParamPrompt(String paramName) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null)  throw new IllegalStateException("GameFactory non impostata");
        if (paramName == null) throw new NullPointerException("Nome del parametro nullo");

        try {
            return confiner.submit(() -> {
                if(this.gameFactory == null) throw new IllegalStateException("GameFactory non impostata");
                int paramsSize = this.gameFactory.params().size();
                for (int i = 0; i < paramsSize; i++) {
                    if (paramName.equals(((Param) (this.gameFactory.params().get(i))).name()))
                        return ((Param) (gameFactory.params().get(i))).prompt();
                }
                throw new IllegalArgumentException("La GameFactory attualmente impostata non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getGameFactoryParamPrompt()");
    }

    /** Ritorna i valori ammissibili per il parametro con nome dato della
     * {@link GameFactory} impostata.
     * @param paramName  nome del parametro
     * @return i valori ammissibili per il parametro della GameFactory impostata
     * @throws NullPointerException se {@code paramName} è null
     * @throws IllegalArgumentException se la GameFactory impostata non ha un
     * parametro di nome {@code paramName}
     * @throws IllegalStateException se non c'è una GameFactory impostata */
    public Object[] getGameFactoryParamValues(String paramName) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null)  throw new IllegalStateException("GameFactory non impostata");
        if (paramName == null) throw new NullPointerException("Nome del parametro nullo");

        try {
            return confiner.submit(() -> {
                if(this.gameFactory == null) throw new IllegalStateException("GameFactory non impostata");
                int paramsSize = this.gameFactory.params().size();
                for (int i = 0; i < paramsSize; i++) {
                    if (paramName.equals(((Param) (this.gameFactory.params().get(i))).name())) {
                        List<Object> list = ((Param) (gameFactory.params().get(i))).values();
                        return list.toArray(new Object[list.size()]);
                    }
                }
                throw new IllegalArgumentException("La GameFactory attualmente impostata non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getGameFactoryParamPrompt()");
    }

    /** Ritorna il valore del parametro di nome dato della {@link GameFactory}
     * impostata.
     * @param paramName  nome del parametro
     * @return il valore del parametro della GameFactory impostata
     * @throws NullPointerException se {@code paramName} è null
     * @throws IllegalArgumentException se la GameFactory impostata non ha un
     * parametro di nome {@code paramName}
     * @throws IllegalStateException se non c'è una GameFactory impostata */
    public Object getGameFactoryParamValue(String paramName) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null)  throw new IllegalStateException("GameFactory non impostata");
        if (paramName == null) throw new NullPointerException("Nome del parametro nullo");

        try {
            return confiner.submit(() -> {
                if(this.gameFactory == null) throw new IllegalStateException("GameFactory non impostata");
                int paramsSize = this.gameFactory.params().size();
                for (int i = 0; i < paramsSize; i++) {
                    if (paramName.equals(((Param) (this.gameFactory.params().get(i))).name()))
                        return ((Param) (gameFactory.params().get(i))).get();
                }
                throw new IllegalArgumentException("La GameFactory attualmente impostata non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getGameFactoryParamPrompt()");
    }

    /** Imposta il valore del parametro di nome dato della {@link GameFactory}
     * impostata.
     * @param paramName  nome del parametro
     * @param value  un valore ammissibile per il parametro
     * @throws NullPointerException se {@code paramName} o {@code value} è null
     * @throws IllegalArgumentException se la GameFactory impostata non ha un
     * parametro di nome {@code paramName} o {@code value} non è un valore
     * ammissibile per il parametro
     * @throws IllegalStateException se non c'è una GameFactory impostata o è già
     * stato impostata la PlayerFactory di un giocatore */
    public void setGameFactoryParamValue(String paramName, Object value) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null) throw new IllegalStateException("GameFactory non impostata");
        if (paramName == null || value == null) throw new NullPointerException("Nome del parametro o valore nulli");

        try {
            confiner.submit(() -> {
                if(this.gameFactory == null) throw new IllegalStateException("GameFactory non impostata");
                playerFactories.forEach(p -> { 
                    if (p != null)
                        throw new IllegalStateException("E' già stata impostata la playerFactory di un giocatore");
                });
                for (Param p : (List<Param>) this.gameFactory.params()) {
                    if (paramName.equals(p.name())) {
                        if (p.values().contains(value)) {
                            p.set(value);
                            return;
                        }
                        else {throw new IllegalArgumentException("Il valore " + value + " non è contenuto nella lista di valori ammissibili");}
                    }
                }
                throw new IllegalArgumentException("La GameFactory attualmente impostata non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
    }


    /** Imposta un {@link PlayerGUI} con il nome e il master dati per il giocatore
     * di indice {@code pIndex}. Se c'era già un giocatore impostato per quell'indice,
     * lo sostituisce.
     * @param pIndex  indice di un giocatore
     * @param pName  nome del giocatore
     * @param master  il master
     * @throws NullPointerException se {@code pName} o {@code master} è null
     * @throws IllegalArgumentException se {@code pIndex} non è un indice di giocatore
     * valido per la GameFactory impostata
     * @throws IllegalStateException se non c'è una GameFactory impostata o se c'è
     * una partita in corso. */
    public void setPlayerGUI(int pIndex, String pName, Consumer<MoveChooser<P>> master) {
        int mIndex = pIndex - 1;
        if(pName == null || master == null) throw new NullPointerException("Master o nome del Player nulli");
        if (confiner == null)               throw new IllegalStateException("Non c'è una gameFactory impostata");
        if(playing.get() == true)           throw new IllegalStateException("attualmente è già in corso una partita");

        try {
            confiner.submit(() -> {
                if(this.gameFactory == null)                              throw new IllegalStateException("GameFactory non impostata");
                if(mIndex >= this.gameFactory.maxPlayers() || mIndex < 0) throw new IllegalArgumentException("Indice di turnazione non valido");
                while (this.playerFactories.size() <= mIndex) this.playerFactories.add(null);
                while (this.players.size() <= mIndex)         this.players.add(null);
                this.playerFactories.set(mIndex, null);
                this.players.set(mIndex, new PlayerGUI(pName, master));
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
    }


    /** Imposta la {@link PlayerFactory} con nome dato per il giocatore di indice
     * {@code pIndex}. Usa {@link PlayerFactories} per creare la PlayerFactory nel
     * thread di confinamento. La PlayerFactory è impostata solamente se il metodo
     * ritorna {@link PlayerFactory.Play#YES}. Se c'era già un giocatore impostato
     * per quell'indice, lo sostituisce.
     * @param pIndex  indice di un giocatore
     * @param fName  nome di una PlayerFactory
     * @param pName  nome del giocatore
     * @param dir  la directory della PlayerFactory o null
     * @return un valore (vedi {@link PlayerFactory.Play}) che informa sulle
     * capacità dei giocatori di questa fabbrica di giocare al gioco specificato.
     * @throws NullPointerException se {@code fName} o {@code pName} è null
     * @throws IllegalArgumentException se {@code pIndex} non è un indice di giocatore
     * valido per la GameFactory impostata o se non esiste una PlayerFactory di nome
     * {@code fName}
     * @throws IllegalStateException se la creazione della PlayerFactory fallisce o
     * se non c'è una GameFactory impostata o se c'è una partita in corso. */
    public PlayerFactory.Play setPlayerFactory(int pIndex, String fName, String pName, Path dir) {
        int mIndex = pIndex - 1;
        if (confiner == null)                                    throw new IllegalStateException("GameFactory non impostata");
        if(fName == null || pName == null)                       throw new NullPointerException("Indice di turnazione o nome del giocatore nulli");
        if(playing.get() == true)                                throw new IllegalStateException("Una partità è già in corso");

        try {
            return confiner.submit(() -> {
                if(this.gameFactory == null)                             throw new IllegalStateException("GameFactory non impostata");
                if(this.gameFactory.maxPlayers() < mIndex || mIndex < 0) throw new IllegalArgumentException("Indice di turnazione non valido");

                PlayerFactory playerFactory;
                try {
                    playerFactory = PlayerFactories.getBoardFactory(fName);
                } catch (Exception e) {throw new IllegalStateException("Si è verificato un problema mentre tentavo di ottenere la PlayerFactory");}
                if (playerFactory == null) throw new IllegalStateException();

                playerFactory.setDir(dir);

                if (playerFactory.canPlay(gameFactory) == PlayerFactory.Play.YES) {
                    while (this.playerFactories.size() <= mIndex) this.playerFactories.add(null);
                    while (this.players.size() <= mIndex)         this.players.add(null);
                    playerFactories.set(mIndex, playerFactory);
                    players.set(mIndex, (Player<P>) playerFactory.newPlayer(this.gameFactory, pName));
                }
                return playerFactory.canPlay(gameFactory);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in setPlayerFactory()");
    }

    /** Ritorna i nomi dei parametri della {@link PlayerFactory} di indice
     * {@code pIndex}. Se la PlayerFactory non ha parametri, ritorna un array vuoto.
     * @param pIndex  indice di un giocatore
     * @return i nomi dei parametri della PlayerFactory di indice dato
     * @throws IllegalArgumentException se non c'è una PlayerFactory di indice
     * {@code pIndex} */
    public String[] getPlayerFactoryParams(int pIndex) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null)  throw new IllegalStateException("GameFactory non impostata");
        int mIndex = pIndex - 1;

        try {
            return confiner.submit(() -> {
                if(this.playerFactories.size() <= mIndex || mIndex < 0) throw new IllegalArgumentException("Indice non valido");
                if (playerFactories.get(mIndex) == null)                throw new IllegalArgumentException("Non c'è nessuna playerFactory a quell'indice");
                int paramsSize = playerFactories.get(mIndex).params().size();
                String[] paramNamesList = new String[paramsSize];
                for (int i = 0; i < paramsSize; i++) {
                    paramNamesList[i] = ((Param) playerFactories.get(mIndex).params().get(i)).name();
                }
                return paramNamesList;
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getPlayerFactoryParams()");
    }

    /** Ritorna il prompt del parametro con il nome specificato della
     * {@link PlayerFactory} di indice {@code pIndex}.
     * @param pIndex  indice di un giocatore
     * @param paramName  nome del parametro
     * @return il prompt del parametro con il nome specificato della PlayerFactory
     * di indice dato
     * @throws NullPointerException se {@code paramName} è null
     * @throws IllegalArgumentException se la PlayerFactory non ha un parametro di
     * nome {@code paramName} o non c'è una PlayerFactory di indice {@code pIndex} */
    public String getPlayerFactoryParamPrompt(int pIndex, String paramName) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null) throw new IllegalStateException("Il thread di confinamento è null");
        if (paramName == null) throw new NullPointerException("Nome del parametro nullo");
        int mIndex = pIndex - 1;

        try {
            return confiner.submit(() -> {
                if(this.playerFactories.size() <= mIndex || mIndex < 0) throw new IllegalArgumentException("Indice non valido");
                if(playerFactories.get(mIndex) == null) throw new IllegalArgumentException("Non esiste una PlayerFactory all'indice " + mIndex);
                for (Param p : (List<Param>) playerFactories.get(mIndex).params()) {
                    if (paramName.equals(p.name()))
                        return p.prompt();
                }
                throw new IllegalArgumentException("La PlayerFactory all'indice " + mIndex + " non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getPlayerFactoryParamPrompt()");
    }

    /** Ritorna i valori ammissibili per il parametro di nome dato della
     * {@link PlayerFactory} di indice {@code pIndex}.
     * @param pIndex  indice di un giocatore
     * @param paramName  nome del parametro
     * @return i valori ammissibili per il parametro di nome dato della PlayerFactory
     * di indice dato.
     * @throws NullPointerException se {@code paramName} è null
     * @throws IllegalArgumentException se la PlayerFactory non ha un parametro di
     * nome {@code paramName} o non c'è una PlayerFactory di indice {@code pIndex} */
    public Object[] getPlayerFactoryParamValues(int pIndex, String paramName) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null) throw new IllegalStateException("Il thread di confinamento è null");
        if (paramName == null) throw new NullPointerException("Nome del parametro nullo");
        int mIndex = pIndex - 1;

        try {
            return confiner.submit(() -> {
                if(this.playerFactories.size() <= mIndex || mIndex < 0) throw new IllegalArgumentException("Indice non valido");
                if(playerFactories.get(mIndex) == null) throw new IllegalArgumentException("Non esiste una PlayerFactory all'indice " + mIndex);
                for (Param p : (List<Param>) playerFactories.get(mIndex).params()) {
                    if (paramName.equals(p.name()))
                        return p.values().toArray(new Object[p.values().size()]);
                }
                throw new IllegalArgumentException("La PlayerFactory all'indice " + mIndex + " non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getPlayerFactoryParamPrompt()");
    }

    /** Ritorna il valore del parametro di nome dato della {@link PlayerFactory} di
     * indice {@code pIndex}.
     * @param pIndex  indice di un giocatore
     * @param paramName  nome del parametro
     * @return il valore del parametro di nome dato della PlayerFactory di indice
     * dato
     * @throws NullPointerException se {@code paramName} è null
     * @throws IllegalArgumentException se la PlayerFactory non ha un parametro di
     * nome {@code paramName} o non c'è una PlayerFactory di indice {@code pIndex} */
    public Object getPlayerFactoryParamValue(int pIndex, String paramName) {
        if (playing.get() == true) throw new IllegalStateException("Attualmente c'è una partita in corso");
        if (confiner == null) throw new IllegalStateException("Il thread di confinamento è null");
        if (paramName == null) throw new NullPointerException("Nome del parametro nullo");
        int mIndex = pIndex - 1;

        try {
            return confiner.submit(() -> {
                if(this.playerFactories.size() <= mIndex || mIndex < 0) throw new IllegalArgumentException("Indice non valido");
                if(playerFactories.get(mIndex) == null) throw new IllegalArgumentException("Non esiste una PlayerFactory all'indice " + mIndex);
                for (Param p : (List<Param>) playerFactories.get(mIndex).params()) {
                    if (paramName.equals(p.name()))
                        return p.get();
                }
                throw new IllegalArgumentException("La PlayerFactory all'indice " + mIndex + " non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
        throw new IllegalStateException("qualcosa è andato storto in getPlayerFactoryParamValue()");
    }

    /** Imposta il valore del parametro di nome dato della {@link PlayerFactory}
     * di indice {@code pIndex}.
     * @param pIndex  indice di un giocatore
     * @param paramName  nome del parametro
     * @param value  un valore ammissibile per il parametro
     * @throws NullPointerException se {@code paramName} o {@code value} è null
     * @throws IllegalArgumentException se la PlayerFactory non ha un parametro di
     * nome {@code paramName} o {@code value} non è un valore ammissibile per il
     * parametro o non c'è una PlayerFactory di indice {@code pIndex}
     * @throws IllegalStateException se c'è una partita in corso */
    public void setPlayerFactoryParamValue(int pIndex, String paramName, Object value) {
        int mIndex = pIndex - 1;
        if (confiner == null) throw new IllegalStateException("Il thread di confinamento è null");
        if(paramName == null || value == null) throw new NullPointerException("Parametro illegale");
        if(playing.get() == true)              throw new IllegalStateException("Attualmente una partita è già in corso");

        try {
            confiner.submit(() -> {
                if (this.playerFactories.size() <= mIndex || mIndex < 0) throw new IllegalArgumentException("Indice non valido" + mIndex);
                if (this.playerFactories.get(mIndex) == null)            throw new IllegalArgumentException("Non c'è una playerFactory di indice " + mIndex);

                for (Param p : (List<Param>) playerFactories.get(mIndex).params()) {
                    if (paramName.equals(p.name())) {
                        if (p.values().contains(value)) {
                            p.set(value);
                            return;
                        }
                        else {throw new IllegalArgumentException("Il valore " + value + " non è contenuto nella lista di valori ammissibili");}
                    }
                }
                throw new IllegalArgumentException("La PlayerFactory all'indice " + mIndex + " non contiene un parametro dal nome: " + paramName);
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalArgumentException.class)) throw (IllegalArgumentException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}
    }


    /** Inizia una partita con un gioco fabbricato dalla GameFactory impostata e i
     * giocatori forniti da {@link PlayerGUI} impostati o fabbricati dalle
     * PlayerFactory impostate. Se non c'è una GameFactory impostata o non ci sono
     * sufficienti giocatori impostati o c'è già una partita in corso, fallisce. Se
     * sono impostati dei vincoli sui thread per le invocazioni di
     * {@link Player#getMove}, allora prima di iniziare la partita invoca i metodi
     * {@link Player#threads(int, ForkJoinPool, ExecutorService)} di tutti i giocatori,
     * ovviamente nel thread di confinamento.
     * <br>
     * Il metodo ritorna immediatamente, non attende che la partita termini. Quindi
     * usa un thread per gestire la partita oltre al thread di confinamento usato
     * per l'invocazione di tutti i metodi del GameRuler e dei Player.
     * @param tol  mssimo numero di millisecondi di tolleranza per le mosse, cioè se
     *             il gioco ha un tempo limite <i>T</i> per le mosse, allora il tempo di
     *             attesa sarà <i>T</i> + {@code tol}; se {@code tol} <= 0, allora
     *             nessuna tolleranza
     * @param timeout  massimo numero di millisecondi per le invocazioni dei metodi
     *                 dei giocatori escluso {@link Player#getMove()}, se <= 0,
     *                 allora nessun limite
     * @param minTime  minimo numero di millisecondi tra una mossa e quella successiva,
     *                 se <= 0, allora nessuna pausa
     * @param maxTh  massimo numero di thread addizionali permessi per
     *               {@link Player#getMove()}, se < 0, nessun limite è imposto
     * @param fjpSize  numero di thread per il {@link ForkJoinTask ForkJoin} pool,
     *                 se == 0, non è permesso alcun pool, se invece è < 0, non c'è
     *                 alcun vincolo e possono usare anche
     *                 {@link ForkJoinPool#commonPool() Common Pool}
     * @param bgExecSize  numero di thread permessi per esecuzioni in background, se
     *                    == 0, non sono permessi, se invece è < 0, non c'è alcun
     *                    vincolo
     * @throws IllegalStateException se non c'è una GameFactory impostata o non ci
     * sono sufficienti PlayerFactory impostate o la creazione del GameRuler o quella
     * di qualche giocatore fallisce o se già c'è una partita in corso. */
    public void play(long tol, long timeout, long minTime, int maxTh, int fjpSize, int bgExecSize) {
        if (gameFactory == null || playing.get() == true) throw new IllegalStateException();
        if (confiner == null) throw new IllegalStateException("Il thread di confinamento è null");
        try {
            confiner.submit(() -> {
                int np = players.size(), ma = gameFactory.maxPlayers(), mi = gameFactory.minPlayers();
                if (np < mi || np > ma) throw new IllegalStateException("Il numero di giocatori è " + np + " mentre dovrebbe essere compreso fra " + mi + " e " + mi);
                players.forEach(p -> {if (p == null) throw new IllegalStateException();});
            }).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(IllegalStateException.class))    throw (IllegalStateException) e.getCause();
        } catch (InterruptedException e) {throw new RuntimeException();}

        this.tol = tol;
        this.minTime = minTime;

        matchTimeout = -1;
        if (timeout > 0) matchTimeout = timeout;
        if (maxBlockTime >= 0) matchTimeout = Math.min(matchTimeout, maxBlockTime);

        if (maxTh >= 0 || fjpSize >= 0 || bgExecSize >= 0) {
            ForkJoinPool fjPool = fjpSize > 0 ? new ForkJoinPool(fjpSize): fjpSize < 0 ? ForkJoinPool.commonPool() : null;
            ExecutorService bgExec = (bgExecSize > 0 ? Executors.newFixedThreadPool(bgExecSize) : (bgExecSize < 0 ? Executors.newCachedThreadPool() : null));
            if (fjPool != null && fjpSize >= 0) forkJoinPools.add(fjPool);
            if (bgExec != null)                 executors.add(bgExec);
            confiner.submit(() -> players.forEach(p -> p.threads(maxTh, fjPool, bgExec)));
        }

        confiner.submit(play_teh_fcuking_gaem);

        playing.set(true);
    }


    private Runnable play_teh_fcuking_gaem = new Runnable (){
        public void run(){
            try {
                String[] names = players.stream().map(Player::name).toArray(String[]::new);
                gameFactory.setPlayerNames(names);

                gameRuler =  (GameRuler<P>) gameFactory.newGame();

                players.forEach(p -> {
                    startTiming(matchTimeout);
                    p.setGame(gameRuler.copy());
                    endTiming();
                });

                synchronized (observer) {
                    observer.setGame(gameRuler.copy());
                }

                while (gameRuler.result() == -1) {
                    int turn = gameRuler.turn();
                    long moveStartTime = System.currentTimeMillis();

                    startTiming(gameRuler.mechanics().time + (tol >= 0 ? tol : 0));
                    Move<P> m = players.get(turn - 1).getMove();
                    endTiming();

                    if (playing.get() == false || m == null) break;

                    synchronized (observer) {
                        observer.moved(turn, m);
                    }

                    gameRuler.move(m);
                    players.forEach(p -> {
                        startTiming(matchTimeout);
                        p.moved(turn, m);
                        endTiming();
                    });

                    try {
                        if (System.currentTimeMillis() - moveStartTime < minTime)
                            Thread.sleep(minTime - (System.currentTimeMillis() - moveStartTime));
                    } catch (InterruptedException e) {}

                }
                if(playing.get()) endGame();
            } catch( Exception e) {e.printStackTrace();stop();}
        }
    };


    private void startTiming(long t) {
        if (t > 0) {
            executing.set(true);
            threadTimeout = t;
            controllore = new Thread(control);
            controllore.setDaemon(true);
            controllore.start();
        }
    }

    private void endTiming() {
        executing.set(false);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private Runnable control = new Runnable () {
        public void run() {
            synchronized (lock) {
                long start = System.currentTimeMillis();
                while (executing.get() == true) {
                    try {
                        long waitTime = (start - System.currentTimeMillis()) + threadTimeout;
                        lock.wait(waitTime > 0 ? waitTime: 1);
                        if (System.currentTimeMillis() - start > threadTimeout) {
                            //blocca tutto
                            tha_limit_broken();
                            return;
                        }
                    } catch (InterruptedException e) {e.printStackTrace();}
                }
            }
        }
    };

    private void tha_limit_broken() {
        stop();
        synchronized (observer) {
            observer.limitBreak(1, "Limit Break!");
        }
    }

    /** Se c'è una partita in corso la termina immediatamente e ritorna true,
     * altrimenti non fa nulla e ritorna false.
     * @return true se termina la partita in corso, false altrimenti */
    public boolean stop() {
        if (playing.get() == true){
            endGame();
            synchronized (observer){
                observer.interrupted("La partita è finita.");
            }
            return true;
        }
        return false;
    }

    private void endGame() {
        forkJoinPools.forEach(pool -> pool.shutdownNow());
        executors.forEach(exec     -> exec.shutdownNow());
        forkJoinPools.clear();
        executors.clear();

        if (confiner != null) 
            confiner.shutdownNow(); confiner = null;
        if (controllore != null) 
            controllore.interrupt();

        playing.set(false);
    }


    private void createConfiner() {
        confiner = Executors.newSingleThreadExecutor(r -> {
            Thread t= new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }
}
