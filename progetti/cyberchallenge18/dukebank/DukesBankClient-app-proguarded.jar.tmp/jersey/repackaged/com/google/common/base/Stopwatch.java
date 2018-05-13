// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stopwatch.java

package jersey.repackaged.com.google.common.base;

import java.util.concurrent.TimeUnit;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions, Ticker

public final class Stopwatch
{

            public static Stopwatch createUnstarted()
            {
/*  89*/        return new Stopwatch();
            }

            public static Stopwatch createStarted()
            {
/* 109*/        return (new Stopwatch()).start();
            }

            /**
             * @deprecated Method Stopwatch is deprecated
             */

            Stopwatch()
            {
/* 130*/        this(Ticker.systemTicker());
            }

            /**
             * @deprecated Method Stopwatch is deprecated
             */

            Stopwatch(Ticker ticker1)
            {
/* 141*/        ticker = (Ticker)Preconditions.checkNotNull(ticker1, "ticker");
            }

            public final Stopwatch start()
            {
/* 160*/        Preconditions.checkState(!isRunning, "This stopwatch is already running.");
/* 161*/        isRunning = true;
/* 162*/        startTick = ticker.read();
/* 163*/        return this;
            }

            public final Stopwatch stop()
            {
/* 174*/        long l = ticker.read();
/* 175*/        Preconditions.checkState(isRunning, "This stopwatch is already stopped.");
/* 176*/        isRunning = false;
/* 177*/        elapsedNanos += l - startTick;
/* 178*/        return this;
            }

            private long elapsedNanos()
            {
/* 194*/        if(isRunning)
/* 194*/            return (ticker.read() - startTick) + elapsedNanos;
/* 194*/        else
/* 194*/            return elapsedNanos;
            }

            public final long elapsed(TimeUnit timeunit)
            {
/* 208*/        return timeunit.convert(elapsedNanos(), TimeUnit.NANOSECONDS);
            }

            public final String toString()
            {
                long l;
/* 216*/        TimeUnit timeunit = chooseUnit(l = elapsedNanos());
/* 219*/        double d = (double)l / (double)TimeUnit.NANOSECONDS.convert(1L, timeunit);
/* 222*/        return String.format("%.4g %s", new Object[] {
/* 222*/            Double.valueOf(d), abbreviate(timeunit)
                });
            }

            private static TimeUnit chooseUnit(long l)
            {
/* 226*/        if(TimeUnit.DAYS.convert(l, TimeUnit.NANOSECONDS) > 0L)
/* 227*/            return TimeUnit.DAYS;
/* 229*/        if(TimeUnit.HOURS.convert(l, TimeUnit.NANOSECONDS) > 0L)
/* 230*/            return TimeUnit.HOURS;
/* 232*/        if(TimeUnit.MINUTES.convert(l, TimeUnit.NANOSECONDS) > 0L)
/* 233*/            return TimeUnit.MINUTES;
/* 235*/        if(TimeUnit.SECONDS.convert(l, TimeUnit.NANOSECONDS) > 0L)
/* 236*/            return TimeUnit.SECONDS;
/* 238*/        if(TimeUnit.MILLISECONDS.convert(l, TimeUnit.NANOSECONDS) > 0L)
/* 239*/            return TimeUnit.MILLISECONDS;
/* 241*/        if(TimeUnit.MICROSECONDS.convert(l, TimeUnit.NANOSECONDS) > 0L)
/* 242*/            return TimeUnit.MICROSECONDS;
/* 244*/        else
/* 244*/            return TimeUnit.NANOSECONDS;
            }

            private static String abbreviate(TimeUnit timeunit)
            {
        static class _cls1
        {

                    static final int $SwitchMap$java$util$concurrent$TimeUnit[];

                    static 
                    {
/* 248*/                $SwitchMap$java$util$concurrent$TimeUnit = new int[TimeUnit.values().length];
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.NANOSECONDS.ordinal()] = 1;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
/* 248*/                try
                        {
/* 248*/                    $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;
                        }
/* 248*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 248*/        switch(_cls1..SwitchMap.java.util.concurrent.TimeUnit[timeunit.ordinal()])
                {
/* 250*/        case 1: // '\001'
/* 250*/            return "ns";

/* 252*/        case 2: // '\002'
/* 252*/            return "\u03BCs";

/* 254*/        case 3: // '\003'
/* 254*/            return "ms";

/* 256*/        case 4: // '\004'
/* 256*/            return "s";

/* 258*/        case 5: // '\005'
/* 258*/            return "min";

/* 260*/        case 6: // '\006'
/* 260*/            return "h";

/* 262*/        case 7: // '\007'
/* 262*/            return "d";
                }
/* 264*/        throw new AssertionError();
            }

            private final Ticker ticker;
            private boolean isRunning;
            private long elapsedNanos;
            private long startTick;
}
