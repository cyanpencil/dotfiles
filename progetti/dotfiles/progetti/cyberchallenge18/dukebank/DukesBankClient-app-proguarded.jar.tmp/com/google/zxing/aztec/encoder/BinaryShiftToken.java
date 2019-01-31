// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BinaryShiftToken.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            Token

final class BinaryShiftToken extends Token
{

            BinaryShiftToken(Token token, int i, int j)
            {
/*  29*/        super(token);
/*  30*/        binaryShiftStart = (short)i;
/*  31*/        binaryShiftByteCount = (short)j;
            }

            public final void appendTo(BitArray bitarray, byte abyte0[])
            {
/*  36*/        for(int i = 0; i < binaryShiftByteCount; i++)
                {
/*  37*/            if(i == 0 || i == 31 && binaryShiftByteCount <= 62)
                    {
/*  40*/                bitarray.appendBits(31, 5);
/*  41*/                if(binaryShiftByteCount > 62)
/*  42*/                    bitarray.appendBits(binaryShiftByteCount - 31, 16);
/*  43*/                else
/*  43*/                if(i == 0)
/*  45*/                    bitarray.appendBits(Math.min(binaryShiftByteCount, 31), 5);
/*  48*/                else
/*  48*/                    bitarray.appendBits(binaryShiftByteCount - 31, 5);
                    }
/*  51*/            bitarray.appendBits(abyte0[binaryShiftStart + i], 8);
                }

            }

            public final String toString()
            {
/*  57*/        return (new StringBuilder("<")).append(binaryShiftStart).append("::").append((binaryShiftStart + binaryShiftByteCount) - 1).append('>').toString();
            }

            private final short binaryShiftStart;
            private final short binaryShiftByteCount;
}
