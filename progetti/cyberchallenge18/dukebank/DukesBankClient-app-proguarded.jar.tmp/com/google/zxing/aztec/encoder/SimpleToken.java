// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SimpleToken.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            Token

final class SimpleToken extends Token
{

            SimpleToken(Token token, int i, int j)
            {
/*  28*/        super(token);
/*  29*/        value = (short)i;
/*  30*/        bitCount = (short)j;
            }

            final void appendTo(BitArray bitarray, byte abyte0[])
            {
/*  35*/        bitarray.appendBits(value, bitCount);
            }

            public final String toString()
            {
                int i;
/*  40*/        i = (i = value & (1 << bitCount) - 1) | 1 << bitCount;
/*  42*/        return (new StringBuilder("<")).append(Integer.toBinaryString(i | 1 << bitCount).substring(1)).append('>').toString();
            }

            private final short value;
            private final short bitCount;
}
