// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Ticker.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Platform

public abstract class Ticker
{

            protected Ticker()
            {
            }

            public abstract long read();

            public static Ticker systemTicker()
            {
/*  54*/        return SYSTEM_TICKER;
            }

            private static final Ticker SYSTEM_TICKER = new Ticker() {

                public final long read()
                {
/*  60*/            return Platform.systemNanoTime();
                }

    };

}
