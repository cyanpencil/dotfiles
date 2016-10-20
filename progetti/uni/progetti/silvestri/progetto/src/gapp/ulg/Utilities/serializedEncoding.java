package gapp.ulg.Utilities;

import gapp.ulg.game.board.GameRuler.*;
import gapp.ulg.game.util.Probe;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.function.BiFunction;

/**
 * Created by Edoardo on 01/06/2016.
 */
public class serializedEncoding <P> implements BiFunction<Mechanics<P>,Situation<P>,BigInteger>, Serializable {
    @Override
    public BigInteger apply(Mechanics<P> pMechanics, Situation<P> pSituation) {
        byte[] situationToByteArray = new Probe.EncS<>(pMechanics, pSituation).encod;
        return new BigInteger(situationToByteArray);
    }
}
