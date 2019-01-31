package gapp.ulg.game.util;

import gapp.ulg.game.board.GameRuler;
import gapp.ulg.game.board.Pos;

import static gapp.ulg.game.board.GameRuler.Situation;
import static gapp.ulg.game.board.GameRuler.Next;
import static gapp.ulg.game.board.GameRuler.Mechanics;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

/** <b>IMPLEMENTARE I METODI INDICATI CON "DA IMPLEMENTARE" SECONDO LE SPECIFICHE
 * DATE NEI JAVADOC. Non modificare le intestazioni dei metodi.</b>
 * <br>
 * Metodi per analizzare giochi */
public class Probe {
    /** Un oggetto {@code EncS} è la codifica compatta di una situazione di gioco
     * {@link GameRuler.Situation}. È utile per mantenere in memoria insiemi con
     * moltissime situazioni minimizzando la memoria richiesta.
     * @param <P>  tipo del modello dei pezzi */
    public static class EncS<P> {

        public byte[] encod;

        /** Crea una codifica compatta della situazione data relativa al gioco la
         * cui meccanica è specificata. La codifica è compatta almeno quanto quella
         * che si ottiene codificando la situazione con un numero e mantenendo in
         * questo oggetto solamente l'array di byte che codificano il numero in
         * binario. Se i parametri di input sono null o non sono tra loro compatibili,
         * il comportamento è indefinito.
         * @param gM  la meccanica di un gioco
         * @param s  una situazione dello stesso gioco */
        public EncS(Mechanics<P> gM, Situation<P> s) {

            List<P> pieceList = gM.pieces;
            BigInteger base = BigInteger.valueOf(pieceList.size()+1);
            BigInteger boardNumber = BigInteger.valueOf(0);
            for (Pos p: gM.positions) {
                boardNumber = boardNumber.multiply(base).add(BigInteger.valueOf(pieceList.indexOf(s.get(p))+1));
            }
            boardNumber = boardNumber.multiply(BigInteger.valueOf(2*gM.np+1)).add(BigInteger.valueOf(s.turn+gM.np));
            encod = boardNumber.toByteArray();
        }

        /** Ritorna la situazione codificata da questo oggetto. Se {@code gM} è null
         * o non è la meccanica del gioco della situazione codificata da questo
         * oggetto, il comportamento è indefinito.
         * @param gM  la meccanica del gioco a cui appartiene la situazione
         * @return la situazione codificata da questo oggetto */
        public Situation<P> decode(Mechanics<P> gM) {
            BigInteger situationNumber = new BigInteger(encod);
            BigInteger[] turnAndBoard = situationNumber.divideAndRemainder(BigInteger.valueOf(2*gM.np+1));
            int turn = turnAndBoard[1].intValue() - gM.np;
            BigInteger boardNumber = new BigInteger(turnAndBoard[0].toString(gM.pieces.size()+1));

            String decoded = String.format("%0" + gM.positions.size() + "d", boardNumber);


            Map<Pos, P> boardMap = new HashMap<>();
            int index = decoded.length()-1;
            for(int i = gM.positions.size()-1 ; i >= 0 ; i--){
                int pieceNumber = Integer.valueOf(String.valueOf(decoded.charAt(index)));
                P piece = (pieceNumber == 0 ? null : gM.pieces.get(pieceNumber-1));
                if(piece != null) boardMap.put(gM.positions.get(i), piece);
                index--;
            }

            return new Situation<>(boardMap, turn);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EncS<?> encS = (EncS<?>) o;

            return Arrays.equals(encod, encS.encod);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(encod);
        }
    }


    /** Un oggetto per rappresentare il risultato del metodo
     * {@link Probe#nextSituations(boolean, Next, Function, Function, Set)}.
     * Chiamiamo grado di una situazione <i>s</i> il numero delle prossime situazioni
     * a cui si può arrivare dalla situazione <i>s</i>.
     * @param <S>  tipo della codifica delle situazioni */
    public static class NSResult<S> {
        /** Insieme delle prossime situazioni */
        public final Set<S> next;
        /** Statistiche: il minimo e il massimo grado delle situazioni di partenza
         * e la somma di tutti gradi */
        public final long min, max, sum;

        public NSResult(Set<S> nx, long mn, long mx, long s) {
            next = nx;
            min = mn;
            max = mx;
            sum = s;
        }
    }

    /** Ritorna l'insieme delle prossime situazioni dell'insieme di situazioni date.
     * Per ogni situazione nell'insieme {@code start} ottiene le prossime situazioni
     * tramite {@code nextF}, previa decodifica con {@code dec}, e le aggiunge
     * all'insieme che ritorna, previa codifica con {@code cod}. La computazione può
     * richiedere tempi lunghi per questo è sensibile all'interruzione del thread
     * in cui il metodo è invocato. Se il thread è interrotto, il metodo ritorna
     * immediatamente o quasi, sia che l'esecuzione è parallela o meno, e ritorna
     * null. Se qualche parametro è null o non sono coerenti (ad es. {@code dec} non
     * è il decodificatore del codificatore {@code end}), il comportamento è
     * indefinito.
     * @param parallel  se true il metodo cerca di sfruttare il parallelismo della
     *                  macchina
     * @param nextF  la funzione che ritorna le prossime situazioni di una situazione
     * @param dec  funzione che decodifica una situazione
     * @param enc  funzione che codifica una situazione
     * @param start  insieme delle situazioni di partenza
     * @param <P>  tipo del modello dei pezzi
     * @param <S>  tipo della codifica delle situazioni
     * @return l'insieme delle prossime situazioni dell'insieme di situazioni date o
     * null se l'esecuzione è interrotta. */
    public static <P,S> NSResult<S> nextSituations(boolean parallel, Next<P> nextF,
                                                   Function<S,Situation<P>> dec,
                                                   Function<Situation<P>,S> enc,
                                                   Set<S> start) {

        if(parallel == false) return nextSitParallel(nextF, dec, enc, new ArrayList<S>(start), 1, start.size());


        int np = Runtime.getRuntime().availableProcessors();
        int partSize = start.size()/np;

        ExecutorService execService = Executors.newFixedThreadPool(np);

        List<Future<NSResult<S>>> futureTasks = new ArrayList<>();

        for(int partIndex = 0; partIndex < np; partIndex++){
            int pIndex = partIndex;
            futureTasks.add(execService.submit(() -> nextSitParallel(nextF, dec, enc, new ArrayList<S>(start), pIndex, partSize)));
        }

        Set<S> next = new HashSet<>();
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE, sum = 0;
        boolean interrupted = false;
        try {
            for (int i = 0; i < np; i++) {
                NSResult<S> res = futureTasks.get(i).get();
                if (res.min < min) min = res.min;
                if (res.max > max) max = res.max;
                sum += res.sum;
                next.addAll(res.next);
            }
        } catch (InterruptedException e) {
            for (int i = 0; i < np; i++) futureTasks.get(i).cancel(true);
            interrupted = true;
        } catch (ExecutionException e) {
        } finally { execService.shutdown(); }
        return interrupted ? null : new NSResult<S>(next, min, max, sum);
    }

    private static <P, S> NSResult<S> nextSitParallel(Next<P> nextF, Function<S, Situation<P>> dec, Function<Situation<P>,S> enc,
                                                      List<S> start, int partIndex, int partSize){
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long sum = 0;

        int startIndex = (partIndex-1)*partSize;
        int endIndex = startIndex+partSize-1;

        Set<S> nextSituationsSet = Collections.synchronizedSet(new HashSet<>());

        for(int index = startIndex; index <= endIndex; index++){
            S encodedSituation = start.get(index);
            Situation<P> decodedSituation = dec.apply(encodedSituation);
            List<Situation<P>> nextSituationsList = new ArrayList<>(nextF.get(decodedSituation).values());
            for(Situation<P> nextSituation : nextSituationsList){
                nextSituationsSet.add(enc.apply(nextSituation));
            }
            if(min > nextSituationsList.size()) min = nextSituationsList.size();
            if(max < nextSituationsList.size()) max = nextSituationsList.size();
            sum += nextSituationsList.size();

            if(Thread.interrupted()) return null;
        }
        return new NSResult<>(nextSituationsSet, min, max, sum);
    }
}
