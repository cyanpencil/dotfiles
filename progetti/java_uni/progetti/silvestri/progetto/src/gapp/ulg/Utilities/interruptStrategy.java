package gapp.ulg.Utilities;

import java.util.function.Supplier;

/**
 * Created by Edoardo & Luca on 27/08/2016.
 */
public class interruptStrategy {

    private static volatile boolean value = false;

    private static Supplier<Boolean> interrupted = () -> {
        return value;
    };

    public static void setValue(boolean value){
        interruptStrategy.value = value;
    }
    public static Supplier<Boolean> get() {
        return interrupted;
    }


}
