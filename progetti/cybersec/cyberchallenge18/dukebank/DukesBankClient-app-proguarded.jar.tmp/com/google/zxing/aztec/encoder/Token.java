// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Token.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            BinaryShiftToken, SimpleToken

abstract class Token
{

            Token(Token token)
            {
/*  28*/        previous = token;
            }

            final Token getPrevious()
            {
/*  32*/        return previous;
            }

            final Token add(int i, int j)
            {
/*  36*/        return new SimpleToken(this, i, j);
            }

            final Token addBinaryShift(int i, int j)
            {
/*  41*/        return new BinaryShiftToken(this, i, j);
            }

            abstract void appendTo(BitArray bitarray, byte abyte0[]);

            static final Token EMPTY = new SimpleToken(null, 0, 0);
            private final Token previous;

}
