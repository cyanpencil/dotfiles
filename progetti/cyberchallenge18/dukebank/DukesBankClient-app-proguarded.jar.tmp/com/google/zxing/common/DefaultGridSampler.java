// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultGridSampler.java

package com.google.zxing.common;

import com.google.zxing.NotFoundException;

// Referenced classes of package com.google.zxing.common:
//            GridSampler, BitMatrix, PerspectiveTransform

public final class DefaultGridSampler extends GridSampler
{

            public DefaultGridSampler()
            {
            }

            public final BitMatrix sampleGrid(BitMatrix bitmatrix, int i, int j, float f, float f1, float f2, float f3, 
                    float f4, float f5, float f6, float f7, float f8, float f9, float f10, 
                    float f11, float f12, float f13, float f14, float f15)
                throws NotFoundException
            {
/*  39*/        f = PerspectiveTransform.quadrilateralToQuadrilateral(f, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
/*  43*/        return sampleGrid(bitmatrix, i, j, f);
            }

            public final BitMatrix sampleGrid(BitMatrix bitmatrix, int i, int j, PerspectiveTransform perspectivetransform)
                throws NotFoundException
            {
/*  51*/        if(i <= 0 || j <= 0)
/*  52*/            throw NotFoundException.getNotFoundInstance();
/*  54*/        BitMatrix bitmatrix1 = new BitMatrix(i, j);
/*  55*/        i = new float[i << 1];
/*  56*/        for(int k = 0; k < j; k++)
                {
/*  57*/            int l = i.length;
/*  58*/            float f = (float)k + 0.5F;
/*  59*/            for(int i1 = 0; i1 < l; i1 += 2)
                    {
/*  60*/                i[i1] = (float)(i1 >> 1) + 0.5F;
/*  61*/                i[i1 + 1] = f;
                    }

/*  63*/            perspectivetransform.transformPoints(i);
/*  66*/            checkAndNudgePoints(bitmatrix, i);
/*  68*/            try
                    {
/*  68*/                for(int j1 = 0; j1 < l; j1 += 2)
/*  69*/                    if(bitmatrix.get((int)i[j1], (int)i[j1 + 1]))
/*  71*/                        bitmatrix1.set(j1 >> 1, k);

                    }
/*  74*/            catch(ArrayIndexOutOfBoundsException _ex)
                    {
/*  82*/                throw NotFoundException.getNotFoundInstance();
                    }
                }

/*  85*/        return bitmatrix1;
            }
}
