// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataMatrixSymbolInfo144.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            SymbolInfo

final class DataMatrixSymbolInfo144 extends SymbolInfo
{

            DataMatrixSymbolInfo144()
            {
/*  22*/        super(false, 1558, 620, 22, 22, 36, -1, 62);
            }

            public final int getInterleavedBlockCount()
            {
/*  27*/        return 10;
            }

            public final int getDataLengthForInterleavedBlock(int i)
            {
/*  32*/        return i > 8 ? 155 : 156;
            }
}
