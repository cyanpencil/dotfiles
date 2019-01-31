// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Binarizer.java

package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing:
//            LuminanceSource, NotFoundException

public abstract class Binarizer
{

            protected Binarizer(LuminanceSource luminancesource)
            {
/*  35*/        source = luminancesource;
            }

            public final LuminanceSource getLuminanceSource()
            {
/*  39*/        return source;
            }

            public abstract BitArray getBlackRow(int i, BitArray bitarray)
                throws NotFoundException;

            public abstract BitMatrix getBlackMatrix()
                throws NotFoundException;

            public abstract Binarizer createBinarizer(LuminanceSource luminancesource);

            public final int getWidth()
            {
/*  78*/        return source.getWidth();
            }

            public final int getHeight()
            {
/*  82*/        return source.getHeight();
            }

            private final LuminanceSource source;
}
