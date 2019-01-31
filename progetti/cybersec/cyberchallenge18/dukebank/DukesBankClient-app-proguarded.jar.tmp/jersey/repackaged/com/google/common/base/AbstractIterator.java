// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractIterator.java

package jersey.repackaged.com.google.common.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions

abstract class AbstractIterator
    implements Iterator
{
    static final class State extends Enum
    {

                public static State[] values()
                {
/*  36*/            return (State[])$VALUES.clone();
                }

                public static final State READY;
                public static final State NOT_READY;
                public static final State DONE;
                public static final State FAILED;
                private static final State $VALUES[];

                static 
                {
/*  37*/            READY = new State("READY", 0);
/*  37*/            NOT_READY = new State("NOT_READY", 1);
/*  37*/            DONE = new State("DONE", 2);
/*  37*/            FAILED = new State("FAILED", 3);
/*  36*/            $VALUES = (new State[] {
/*  36*/                READY, NOT_READY, DONE, FAILED
                    });
                }

                private State(String s, int i)
                {
/*  36*/            super(s, i);
                }
    }


            protected AbstractIterator()
            {
/*  32*/        state = State.NOT_READY;
            }

            protected abstract Object computeNext();

            protected final Object endOfData()
            {
/*  45*/        state = State.DONE;
/*  46*/        return null;
            }

            public final boolean hasNext()
            {
/*  51*/        Preconditions.checkState(state != State.FAILED);
        static class _cls1
        {

                    static final int $SwitchMap$com$google$common$base$AbstractIterator$State[];

                    static 
                    {
/*  52*/                $SwitchMap$com$google$common$base$AbstractIterator$State = new int[State.values().length];
/*  52*/                try
                        {
/*  52*/                    $SwitchMap$com$google$common$base$AbstractIterator$State[State.DONE.ordinal()] = 1;
                        }
/*  52*/                catch(NoSuchFieldError _ex) { }
/*  52*/                try
                        {
/*  52*/                    $SwitchMap$com$google$common$base$AbstractIterator$State[State.READY.ordinal()] = 2;
                        }
/*  52*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/*  52*/        switch(_cls1..SwitchMap.com.google.common.base.AbstractIterator.State[state.ordinal()])
                {
/*  54*/        case 1: // '\001'
/*  54*/            return false;

/*  56*/        case 2: // '\002'
/*  56*/            return true;
                }
/*  59*/        return tryToComputeNext();
            }

            private boolean tryToComputeNext()
            {
/*  63*/        state = State.FAILED;
/*  64*/        next = computeNext();
/*  65*/        if(state != State.DONE)
                {
/*  66*/            state = State.READY;
/*  67*/            return true;
                } else
                {
/*  69*/            return false;
                }
            }

            public final Object next()
            {
/*  74*/        if(!hasNext())
                {
/*  75*/            throw new NoSuchElementException();
                } else
                {
/*  77*/            state = State.NOT_READY;
/*  78*/            Object obj = next;
/*  79*/            next = null;
/*  80*/            return obj;
                }
            }

            public final void remove()
            {
/*  84*/        throw new UnsupportedOperationException();
            }

            private State state;
            private Object next;
}
