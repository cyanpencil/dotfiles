// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReedSolomonEncoder.java

package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GenericGF, GenericGFPoly

public final class ReedSolomonEncoder
{

            public ReedSolomonEncoder(GenericGF genericgf)
            {
/*  34*/        field = genericgf;
/*  36*/        cachedGenerators.add(new GenericGFPoly(genericgf, new int[] {
/*  36*/            1
                }));
            }

            private GenericGFPoly buildGenerator(int i)
            {
/*  40*/        if(i >= cachedGenerators.size())
                {
/*  41*/            GenericGFPoly genericgfpoly = (GenericGFPoly)cachedGenerators.get(cachedGenerators.size() - 1);
/*  42*/            for(int j = cachedGenerators.size(); j <= i; j++)
                    {
/*  43*/                genericgfpoly = genericgfpoly.multiply(new GenericGFPoly(field, new int[] {
/*  43*/                    1, field.exp((j - 1) + field.getGeneratorBase())
                        }));
/*  45*/                cachedGenerators.add(genericgfpoly);
/*  46*/                genericgfpoly = genericgfpoly;
                    }

                }
/*  49*/        return (GenericGFPoly)cachedGenerators.get(i);
            }

            public final void encode(int ai[], int i)
            {
/*  53*/        if(i == 0)
/*  54*/            throw new IllegalArgumentException("No error correction bytes");
                int j;
/*  56*/        if((j = ai.length - i) <= 0)
/*  58*/            throw new IllegalArgumentException("No data bytes provided");
/*  60*/        GenericGFPoly genericgfpoly = buildGenerator(i);
/*  61*/        int ai2[] = new int[j];
/*  62*/        System.arraycopy(ai, 0, ai2, 0, j);
                GenericGFPoly genericgfpoly1;
/*  63*/        int ai1[] = (genericgfpoly = (genericgfpoly1 = (genericgfpoly1 = new GenericGFPoly(field, ai2)).multiplyByMonomial(i, 1)).divide(genericgfpoly)[1]).getCoefficients();
/*  67*/        i -= ai1.length;
/*  68*/        for(int k = 0; k < i; k++)
/*  69*/            ai[j + k] = 0;

/*  71*/        System.arraycopy(ai1, 0, ai, j + i, ai1.length);
            }

            private final GenericGF field;
            private final List cachedGenerators = new ArrayList();
}
