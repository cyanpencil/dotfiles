// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AztecDetectorResult.java

package com.google.zxing.aztec;

import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;

public final class AztecDetectorResult extends DetectorResult
{

            public AztecDetectorResult(BitMatrix bitmatrix, ResultPoint aresultpoint[], boolean flag, int i, int j)
            {
/*  34*/        super(bitmatrix, aresultpoint);
/*  35*/        compact = flag;
/*  36*/        nbDatablocks = i;
/*  37*/        nbLayers = j;
            }

            public final int getNbLayers()
            {
/*  41*/        return nbLayers;
            }

            public final int getNbDatablocks()
            {
/*  45*/        return nbDatablocks;
            }

            public final boolean isCompact()
            {
/*  49*/        return compact;
            }

            private final boolean compact;
            private final int nbDatablocks;
            private final int nbLayers;
}
