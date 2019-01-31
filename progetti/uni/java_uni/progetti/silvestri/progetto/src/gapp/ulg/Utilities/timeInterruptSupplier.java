package gapp.ulg.Utilities;

import java.util.function.Supplier;

/**
 * Created by Edoardo on 12/09/2016.
 */
public class timeInterruptSupplier implements Supplier<Boolean> {

    private final long startingTime;
    private final long millisToWait;


    public timeInterruptSupplier(long startingTime, long millisToWait){
        this.startingTime = startingTime;
        this.millisToWait = millisToWait;
    }
    public Boolean get() {
        if(System.currentTimeMillis() - startingTime > millisToWait) return true;
        return false;
    }


}
