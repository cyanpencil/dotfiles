// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CurrentParsingState.java

package com.google.zxing.oned.rss.expanded.decoders;


final class CurrentParsingState
{
    static final class State extends Enum
    {

                public static State[] values()
                {
/*  37*/            return (State[])$VALUES.clone();
                }

                public static State valueOf(String s)
                {
/*  37*/            return (State)Enum.valueOf(com/google/zxing/oned/rss/expanded/decoders/CurrentParsingState$State, s);
                }

                public static final State NUMERIC;
                public static final State ALPHA;
                public static final State ISO_IEC_646;
                private static final State $VALUES[];

                static 
                {
/*  38*/            NUMERIC = new State("NUMERIC", 0);
/*  39*/            ALPHA = new State("ALPHA", 1);
/*  40*/            ISO_IEC_646 = new State("ISO_IEC_646", 2);
/*  37*/            $VALUES = (new State[] {
/*  37*/                NUMERIC, ALPHA, ISO_IEC_646
                    });
                }

                private State(String s, int i)
                {
/*  37*/            super(s, i);
                }
    }


            CurrentParsingState()
            {
/*  44*/        position = 0;
/*  45*/        encoding = State.NUMERIC;
            }

            final int getPosition()
            {
/*  49*/        return position;
            }

            final void setPosition(int i)
            {
/*  53*/        position = i;
            }

            final void incrementPosition(int i)
            {
/*  57*/        position += i;
            }

            final boolean isAlpha()
            {
/*  61*/        return encoding == State.ALPHA;
            }

            final boolean isNumeric()
            {
/*  65*/        return encoding == State.NUMERIC;
            }

            final boolean isIsoIec646()
            {
/*  69*/        return encoding == State.ISO_IEC_646;
            }

            final void setNumeric()
            {
/*  73*/        encoding = State.NUMERIC;
            }

            final void setAlpha()
            {
/*  77*/        encoding = State.ALPHA;
            }

            final void setIsoIec646()
            {
/*  81*/        encoding = State.ISO_IEC_646;
            }

            private int position;
            private State encoding;
}
