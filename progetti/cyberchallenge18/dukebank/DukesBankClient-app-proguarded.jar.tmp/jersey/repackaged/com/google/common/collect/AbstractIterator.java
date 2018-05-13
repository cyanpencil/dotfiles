// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.NoSuchElementException;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator

public abstract class AbstractIterator extends UnmodifiableIterator
{
    static final class State extends Enum
    {

                public static State[] values()
                {
/*  70*/            return (State[])$VALUES.clone();
                }

                public static final State READY;
                public static final State NOT_READY;
                public static final State DONE;
                public static final State FAILED;
                private static final State $VALUES[];

                static 
                {
/*  72*/            READY = new State("READY", 0);
/*  75*/            NOT_READY = new State("NOT_READY", 1);
/*  78*/            DONE = new State("DONE", 2);
/*  81*/            FAILED = new State("FAILED", 3);
/*  70*/            $VALUES = (new State[] {
/*  70*/                READY, NOT_READY, DONE, FAILED
                    });
                }

                private State(String s, int i)
                {
/*  70*/            super(s, i);
                }
    }


            protected AbstractIterator()
            {
/*  65*/        state = State.NOT_READY;
            }

            protected abstract Object computeNext();

            protected final Object endOfData()
            {
/* 124*/        state = State.DONE;
/* 125*/        return null;
            }

            public final boolean hasNext()
            {
/* 130*/        Preconditions.checkState(state != State.FAILED);
        static class _cls1
        {

                    static final int $SwitchMap$com$google$common$collect$AbstractIterator$State[];

                    static 
                    {
/* 131*/                $SwitchMap$com$google$common$collect$AbstractIterator$State = new int[State.values().length];
/* 131*/                try
                        {
/* 131*/                    $SwitchMap$com$google$common$collect$AbstractIterator$State[State.DONE.ordinal()] = 1;
                        }
/* 131*/                catch(NoSuchFieldError _ex) { }
/* 131*/                try
                        {
/* 131*/                    $SwitchMap$com$google$common$collect$AbstractIterator$State[State.READY.ordinal()] = 2;
                        }
/* 131*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 131*/        switch(_cls1..SwitchMap.com.google.common.collect.AbstractIterator.State[state.ordinal()])
                {
/* 133*/        case 1: // '\001'
/* 133*/            return false;

/* 135*/        case 2: // '\002'
/* 135*/            return true;
                }
/* 138*/        return tryToComputeNext();
            }

            private boolean tryToComputeNext()
            {
/* 142*/        state = State.FAILED;
/* 143*/        next = computeNext();
/* 144*/        if(state != State.DONE)
                {
/* 145*/            state = State.READY;
/* 146*/            return true;
                } else
                {
/* 148*/            return false;
                }
            }

            public final Object next()
            {
/* 153*/        if(!hasNext())
                {
/* 154*/            throw new NoSuchElementException();
                } else
                {
/* 156*/            state = State.NOT_READY;
/* 157*/            Object obj = next;
/* 158*/            next = null;
/* 159*/            return obj;
                }
            }

            private State state;
            private Object next;
}
