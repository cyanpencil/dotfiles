// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HighLevelEncoder.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            State

public final class HighLevelEncoder
{

            public HighLevelEncoder(byte abyte0[])
            {
/* 154*/        text = abyte0;
            }

            public final BitArray encode()
            {
/* 161*/        Object obj = Collections.singletonList(State.INITIAL_STATE);
/* 162*/        for(int i = 0; i < text.length; i++)
                {
/* 164*/            byte byte0 = i + 1 >= text.length ? 0 : text[i + 1];
/* 165*/            switch(text[i])
                    {
/* 167*/            case 13: // '\r'
/* 167*/                byte0 = ((byte)(byte0 != 10 ? 0 : 2));
                        break;

/* 170*/            case 46: // '.'
/* 170*/                byte0 = ((byte)(byte0 != 32 ? 0 : 3));
                        break;

/* 173*/            case 44: // ','
/* 173*/                byte0 = ((byte)(byte0 != 32 ? 0 : 4));
                        break;

/* 176*/            case 58: // ':'
/* 176*/                byte0 = ((byte)(byte0 != 32 ? 0 : 5));
                        break;

/* 179*/            default:
/* 179*/                byte0 = 0;
                        break;
                    }
/* 181*/            if(byte0 > 0)
                    {
/* 184*/                obj = updateStateListForPair(((Iterable) (obj)), i, byte0);
/* 185*/                i++;
                    } else
                    {
/* 188*/                obj = updateStateListForChar(((Iterable) (obj)), i);
                    }
                }

                State state;
/* 192*/        return (state = (State)Collections.min(((Collection) (obj)), new Comparator() {

                    public int compare(State state1, State state2)
                    {
/* 195*/                return state1.getBitCount() - state2.getBitCount();
                    }

                    public volatile int compare(Object obj1, Object obj2)
                    {
/* 192*/                return compare((State)obj1, (State)obj2);
                    }

                    final HighLevelEncoder this$0;

                    
                    {
/* 192*/                this$0 = HighLevelEncoder.this;
/* 192*/                super();
                    }
        })).toBitArray(text);
            }

            private Collection updateStateListForChar(Iterable iterable, int i)
            {
/* 206*/        LinkedList linkedlist = new LinkedList();
                State state;
/* 207*/        for(iterable = iterable.iterator(); iterable.hasNext(); updateStateForChar(state, i, linkedlist))
/* 207*/            state = (State)iterable.next();

/* 210*/        return simplifyStates(linkedlist);
            }

            private void updateStateForChar(State state, int i, Collection collection)
            {
/* 217*/        char c = (char)(text[i] & 0xff);
/* 218*/        boolean flag = CHAR_MAP[state.getMode()][c] > 0;
/* 219*/        State state1 = null;
/* 220*/        for(int j = 0; j <= 4; j++)
                {
                    int k;
/* 221*/            if((k = CHAR_MAP[j][c]) <= 0)
/* 223*/                continue;
/* 223*/            if(state1 == null)
/* 225*/                state1 = state.endBinaryShift(i);
/* 228*/            if(!flag || j == state.getMode() || j == 2)
                    {
/* 233*/                State state3 = state1.latchAndAppend(j, k);
/* 234*/                collection.add(state3);
                    }
/* 237*/            if(!flag && SHIFT_TABLE[state.getMode()][j] >= 0)
                    {
/* 240*/                State state4 = state1.shiftAndAppend(j, k);
/* 241*/                collection.add(state4);
                    }
                }

/* 245*/        if(state.getBinaryShiftByteCount() > 0 || CHAR_MAP[state.getMode()][c] == 0)
                {
/* 249*/            State state2 = state.addBinaryShiftChar(i);
/* 250*/            collection.add(state2);
                }
            }

            private static Collection updateStateListForPair(Iterable iterable, int i, int j)
            {
/* 255*/        LinkedList linkedlist = new LinkedList();
                State state;
/* 256*/        for(iterable = iterable.iterator(); iterable.hasNext(); updateStateForPair(state = (State)iterable.next(), i, j, linkedlist));
/* 259*/        return simplifyStates(linkedlist);
            }

            private static void updateStateForPair(State state, int i, int j, Collection collection)
            {
/* 263*/        State state1 = state.endBinaryShift(i);
/* 265*/        collection.add(state1.latchAndAppend(4, j));
/* 266*/        if(state.getMode() != 4)
/* 269*/            collection.add(state1.shiftAndAppend(4, j));
/* 271*/        if(j == 3 || j == 4)
                {
/* 273*/            j = state1.latchAndAppend(2, 16 - j).latchAndAppend(2, 1);
/* 276*/            collection.add(j);
                }
/* 278*/        if(state.getBinaryShiftByteCount() > 0)
                {
/* 281*/            j = state.addBinaryShiftChar(i).addBinaryShiftChar(i + 1);
/* 282*/            collection.add(j);
                }
            }

            private static Collection simplifyStates(Iterable iterable)
            {
/* 287*/        LinkedList linkedlist = new LinkedList();
/* 288*/        iterable = iterable.iterator();
/* 288*/        do
                {
/* 288*/            if(!iterable.hasNext())
/* 288*/                break;
/* 288*/            State state = (State)iterable.next();
/* 289*/            boolean flag = true;
/* 290*/            Iterator iterator = linkedlist.iterator();
/* 290*/            do
                    {
/* 290*/                if(!iterator.hasNext())
/* 291*/                    break;
                        State state1;
/* 291*/                if((state1 = (State)iterator.next()).isBetterThanOrEqualTo(state))
                        {
/* 293*/                    flag = false;
/* 294*/                    break;
                        }
/* 296*/                if(state.isBetterThanOrEqualTo(state1))
/* 297*/                    iterator.remove();
                    } while(true);
/* 300*/            if(flag)
/* 301*/                linkedlist.add(state);
                } while(true);
/* 304*/        return linkedlist;
            }

            static final String MODE_NAMES[] = {
/*  43*/        "UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"
            };
            static final int MODE_UPPER = 0;
            static final int MODE_LOWER = 1;
            static final int MODE_DIGIT = 2;
            static final int MODE_MIXED = 3;
            static final int MODE_PUNCT = 4;
            static final int LATCH_TABLE[][] = {
/*  56*/        {
/*  56*/            0, 0x5001c, 0x5001e, 0x5001d, 0xa03be
                }, {
/*  56*/            0x901ee, 0, 0x5001e, 0x5001d, 0xa03be
                }, {
/*  56*/            0x4000e, 0x901dc, 0, 0x901dd, 0xe3bbe
                }, {
/*  56*/            0x5001d, 0x5001c, 0xa03be, 0, 0x5001e
                }, {
/*  56*/            0x5001f, 0xa03fc, 0xa03fe, 0xa03fd, 0
                }
            };
            private static final int CHAR_MAP[][];
            static final int SHIFT_TABLE[][];
            private final byte text[];

            static 
            {
/*  97*/        (CHAR_MAP = new int[5][256])[0][32] = 1;
/* 100*/        for(int i = 65; i <= 90; i++)
/* 101*/            CHAR_MAP[0][i] = (i - 65) + 2;

/* 103*/        CHAR_MAP[1][32] = 1;
/* 104*/        for(int j = 97; j <= 122; j++)
/* 105*/            CHAR_MAP[1][j] = (j - 97) + 2;

/* 107*/        CHAR_MAP[2][32] = 1;
/* 108*/        for(int k = 48; k <= 57; k++)
/* 109*/            CHAR_MAP[2][k] = (k - 48) + 2;

/* 111*/        CHAR_MAP[2][44] = 12;
/* 112*/        CHAR_MAP[2][46] = 13;
/* 113*/        Object obj = {
/* 113*/            0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 
/* 113*/            9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 
/* 113*/            64, 92, 94, 95, 96, 124, 126, 127
                };
/* 118*/        for(int l = 0; l < 28; l++)
/* 119*/            CHAR_MAP[3][obj[l]] = l;

/* 121*/        int ai[] = {
/* 121*/            0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 
/* 121*/            37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 
/* 121*/            47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 
/* 121*/            125
                };
/* 126*/        for(int j1 = 0; j1 < 31; j1++)
/* 127*/            if(ai[j1] > 0)
/* 128*/                CHAR_MAP[4][ai[j1]] = j1;

/* 135*/        int i1 = (obj = SHIFT_TABLE = new int[6][6]).length;
/* 137*/        for(int k1 = 0; k1 < i1; k1++)
                {
                    int ai1[];
/* 137*/            Arrays.fill(ai1 = obj[k1], -1);
                }

/* 140*/        SHIFT_TABLE[0][4] = 0;
/* 142*/        SHIFT_TABLE[1][4] = 0;
/* 143*/        SHIFT_TABLE[1][0] = 28;
/* 145*/        SHIFT_TABLE[3][4] = 0;
/* 147*/        SHIFT_TABLE[2][4] = 0;
/* 148*/        SHIFT_TABLE[2][0] = 15;
            }
}
