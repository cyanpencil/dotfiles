// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GridSampler.java

package com.google.zxing.common;

import com.google.zxing.NotFoundException;

// Referenced classes of package com.google.zxing.common:
//            BitMatrix, DefaultGridSampler, PerspectiveTransform

public abstract class GridSampler
{

            public GridSampler()
            {
            }

            public static void setGridSampler(GridSampler gridsampler)
            {
/*  48*/        gridSampler = gridsampler;
            }

            public static GridSampler getInstance()
            {
/*  55*/        return gridSampler;
            }

            public abstract BitMatrix sampleGrid(BitMatrix bitmatrix, int i, int j, float f, float f1, float f2, float f3, 
                    float f4, float f5, float f6, float f7, float f8, float f9, float f10, 
                    float f11, float f12, float f13, float f14, float f15)
                throws NotFoundException;

            public abstract BitMatrix sampleGrid(BitMatrix bitmatrix, int i, int j, PerspectiveTransform perspectivetransform)
                throws NotFoundException;

            protected static void checkAndNudgePoints(BitMatrix bitmatrix, float af[])
                throws NotFoundException
            {
/* 102*/        int i = bitmatrix.getWidth();
/* 103*/        bitmatrix = bitmatrix.getHeight();
/* 105*/        boolean flag = true;
/* 106*/        for(int j = 0; j < af.length && flag; j += 2)
                {
/* 107*/            int l = (int)af[j];
/* 108*/            int j1 = (int)af[j + 1];
/* 109*/            if(l < -1 || l > i || j1 < -1 || j1 > bitmatrix)
/* 110*/                throw NotFoundException.getNotFoundInstance();
/* 112*/            flag = false;
/* 113*/            if(l == -1)
                    {
/* 114*/                af[j] = 0.0F;
/* 115*/                flag = true;
                    } else
/* 116*/            if(l == i)
                    {
/* 117*/                af[j] = i - 1;
/* 118*/                flag = true;
                    }
/* 120*/            if(j1 == -1)
                    {
/* 121*/                af[j + 1] = 0.0F;
/* 122*/                flag = true;
/* 122*/                continue;
                    }
/* 123*/            if(j1 == bitmatrix)
                    {
/* 124*/                af[j + 1] = bitmatrix - 1;
/* 125*/                flag = true;
                    }
                }

/* 129*/        flag = true;
/* 130*/        for(int k = af.length - 2; k >= 0 && flag; k -= 2)
                {
/* 131*/            int i1 = (int)af[k];
/* 132*/            int k1 = (int)af[k + 1];
/* 133*/            if(i1 < -1 || i1 > i || k1 < -1 || k1 > bitmatrix)
/* 134*/                throw NotFoundException.getNotFoundInstance();
/* 136*/            flag = false;
/* 137*/            if(i1 == -1)
                    {
/* 138*/                af[k] = 0.0F;
/* 139*/                flag = true;
                    } else
/* 140*/            if(i1 == i)
                    {
/* 141*/                af[k] = i - 1;
/* 142*/                flag = true;
                    }
/* 144*/            if(k1 == -1)
                    {
/* 145*/                af[k + 1] = 0.0F;
/* 146*/                flag = true;
/* 146*/                continue;
                    }
/* 147*/            if(k1 == bitmatrix)
                    {
/* 148*/                af[k + 1] = bitmatrix - 1;
/* 149*/                flag = true;
                    }
                }

            }

            private static GridSampler gridSampler = new DefaultGridSampler();

}
