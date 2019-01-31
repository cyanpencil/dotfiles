// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   State.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            HighLevelEncoder, Token

final class State
{

            private State(Token token1, int i, int j, int k)
            {
/*  45*/        token = token1;
/*  46*/        mode = i;
/*  47*/        binaryShiftByteCount = j;
/*  48*/        bitCount = k;
            }

            final int getMode()
            {
/*  58*/        return mode;
            }

            final Token getToken()
            {
/*  62*/        return token;
            }

            final int getBinaryShiftByteCount()
            {
/*  66*/        return binaryShiftByteCount;
            }

            final int getBitCount()
            {
/*  70*/        return bitCount;
            }

            final State latchAndAppend(int i, int j)
            {
/*  77*/        int k = bitCount;
/*  78*/        Token token1 = token;
/*  79*/        if(i != mode)
                {
/*  80*/            int l = HighLevelEncoder.LATCH_TABLE[mode][i];
/*  81*/            token1 = token1.add(l & 0xffff, l >> 16);
/*  82*/            k += l >> 16;
                }
/*  84*/        byte byte0 = ((byte)(i != 2 ? 5 : 4));
/*  85*/        token1 = token1.add(j, byte0);
/*  86*/        return new State(token1, i, 0, k + byte0);
            }

            final State shiftAndAppend(int i, int j)
            {
/*  93*/        Token token1 = token;
/*  94*/        byte byte0 = ((byte)(mode != 2 ? 5 : 4));
/*  96*/        token1 = (token1 = token1.add(HighLevelEncoder.SHIFT_TABLE[mode][i], byte0)).add(j, 5);
/*  98*/        return new State(token1, mode, 0, bitCount + byte0 + 5);
            }

            final State addBinaryShiftChar(int i)
            {
/* 104*/        Object obj = token;
/* 105*/        int j = mode;
/* 106*/        int k = bitCount;
/* 107*/        if(mode == 4 || mode == 2)
                {
/* 109*/            int l = HighLevelEncoder.LATCH_TABLE[j][0];
/* 110*/            obj = ((Token) (obj)).add(l & 0xffff, l >> 16);
/* 111*/            k += l >> 16;
/* 112*/            j = 0;
                }
/* 114*/        byte byte0 = binaryShiftByteCount != 0 && binaryShiftByteCount != 31 ? ((byte)(binaryShiftByteCount != 62 ? 8 : 9)) : 18;
/* 117*/        if(((State) (obj = new State(((Token) (obj)), j, binaryShiftByteCount + 1, k + byte0))).binaryShiftByteCount == 2078)
/* 120*/            obj = ((State) (obj)).endBinaryShift(i + 1);
/* 122*/        return ((State) (obj));
            }

            final State endBinaryShift(int i)
            {
/* 128*/        if(binaryShiftByteCount == 0)
                {
/* 129*/            return this;
                } else
                {
                    Token token1;
/* 131*/            token1 = (token1 = token).addBinaryShift(i - binaryShiftByteCount, binaryShiftByteCount);
/* 134*/            return new State(token1, mode, 0, bitCount);
                }
            }

            final boolean isBetterThanOrEqualTo(State state)
            {
/* 140*/        int i = bitCount + (HighLevelEncoder.LATCH_TABLE[mode][state.mode] >> 16);
/* 141*/        if(state.binaryShiftByteCount > 0 && (binaryShiftByteCount == 0 || binaryShiftByteCount > state.binaryShiftByteCount))
/* 143*/            i += 10;
/* 145*/        return i <= state.bitCount;
            }

            final BitArray toBitArray(byte abyte0[])
            {
/* 151*/        Object obj = new LinkedList();
/* 152*/        for(Token token1 = endBinaryShift(abyte0.length).token; token1 != null; token1 = token1.getPrevious())
/* 153*/            ((Deque) (obj)).addFirst(token1);

/* 155*/        BitArray bitarray = new BitArray();
                Token token2;
/* 157*/        for(obj = ((Deque) (obj)).iterator(); ((Iterator) (obj)).hasNext(); (token2 = (Token)((Iterator) (obj)).next()).appendTo(bitarray, abyte0));
/* 161*/        return bitarray;
            }

            public final String toString()
            {
/* 166*/        return String.format("%s bits=%d bytes=%d", new Object[] {
/* 166*/            HighLevelEncoder.MODE_NAMES[mode], Integer.valueOf(bitCount), Integer.valueOf(binaryShiftByteCount)
                });
            }

            static final State INITIAL_STATE;
            private final int mode;
            private final Token token;
            private final int binaryShiftByteCount;
            private final int bitCount;

            static 
            {
/*  30*/        INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
            }
}
