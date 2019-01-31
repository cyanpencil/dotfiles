// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReedSolomonDecoder.java

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GenericGF, GenericGFPoly, ReedSolomonException

public final class ReedSolomonDecoder
{

            public ReedSolomonDecoder(GenericGF genericgf)
            {
/*  46*/        field = genericgf;
            }

            public final void decode(int ai[], int i)
                throws ReedSolomonException
            {
/*  59*/        GenericGFPoly genericgfpoly = new GenericGFPoly(field, ai);
/*  60*/        int ai2[] = new int[i];
/*  61*/        boolean flag = true;
/*  62*/        for(int l = 0; l < i; l++)
                {
/*  63*/            int i1 = genericgfpoly.evaluateAt(field.exp(l + field.getGeneratorBase()));
/*  64*/            ai2[i - 1 - l] = i1;
/*  65*/            if(i1 != 0)
/*  66*/                flag = false;
                }

/*  69*/        if(flag)
/*  70*/            return;
/*  72*/        GenericGFPoly genericgfpoly1 = new GenericGFPoly(field, ai2);
                GenericGFPoly agenericgfpoly[];
/*  73*/        i = (agenericgfpoly = runEuclideanAlgorithm(field.buildMonomial(i, 1), genericgfpoly1, i))[0];
/*  76*/        genericgfpoly = agenericgfpoly[1];
/*  77*/        i = findErrorLocations(i);
/*  78*/        int ai1[] = findErrorMagnitudes(genericgfpoly, i);
/*  79*/        for(int j = 0; j < i.length; j++)
                {
                    int k;
/*  80*/            if((k = ai.length - 1 - field.log(i[j])) < 0)
/*  82*/                throw new ReedSolomonException("Bad error location");
/*  84*/            ai[k] = GenericGF.addOrSubtract(ai[k], ai1[j]);
                }

            }

            private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericgfpoly, GenericGFPoly genericgfpoly1, int i)
                throws ReedSolomonException
            {
/*  91*/        if(genericgfpoly.getDegree() < genericgfpoly1.getDegree())
                {
/*  92*/            GenericGFPoly genericgfpoly2 = genericgfpoly;
/*  93*/            genericgfpoly = genericgfpoly1;
/*  94*/            genericgfpoly1 = genericgfpoly2;
                }
/*  97*/        GenericGFPoly genericgfpoly3 = genericgfpoly;
/*  98*/        genericgfpoly = genericgfpoly1;
/*  99*/        genericgfpoly1 = field.getZero();
/* 100*/        GenericGFPoly genericgfpoly4 = field.getOne();
/* 103*/        while(genericgfpoly.getDegree() >= i / 2) 
                {
/* 104*/            GenericGFPoly genericgfpoly5 = genericgfpoly3;
/* 105*/            GenericGFPoly genericgfpoly7 = genericgfpoly1;
/* 106*/            genericgfpoly3 = genericgfpoly;
/* 107*/            genericgfpoly1 = genericgfpoly4;
/* 110*/            if(genericgfpoly3.isZero())
/* 112*/                throw new ReedSolomonException("r_{i-1} was zero");
/* 114*/            genericgfpoly = genericgfpoly5;
/* 115*/            genericgfpoly4 = field.getZero();
/* 116*/            int j = genericgfpoly3.getCoefficient(genericgfpoly3.getDegree());
/* 117*/            j = field.inverse(j);
                    int i1;
                    int j1;
/* 118*/            for(; genericgfpoly.getDegree() >= genericgfpoly3.getDegree() && !genericgfpoly.isZero(); genericgfpoly = genericgfpoly.addOrSubtract(genericgfpoly3.multiplyByMonomial(i1, j1)))
                    {
/* 119*/                i1 = genericgfpoly.getDegree() - genericgfpoly3.getDegree();
/* 120*/                j1 = field.multiply(genericgfpoly.getCoefficient(genericgfpoly.getDegree()), j);
/* 121*/                genericgfpoly4 = genericgfpoly4.addOrSubtract(field.buildMonomial(i1, j1));
                    }

/* 125*/            genericgfpoly4 = genericgfpoly4.multiply(genericgfpoly1).addOrSubtract(genericgfpoly7);
/* 127*/            if(genericgfpoly.getDegree() >= genericgfpoly3.getDegree())
/* 128*/                throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
                int k;
/* 132*/        if((k = genericgfpoly4.getCoefficient(0)) == 0)
                {
/* 134*/            throw new ReedSolomonException("sigmaTilde(0) was zero");
                } else
                {
/* 137*/            int l = field.inverse(k);
/* 138*/            genericgfpoly4 = genericgfpoly4.multiply(l);
/* 139*/            GenericGFPoly genericgfpoly6 = genericgfpoly.multiply(l);
/* 140*/            return (new GenericGFPoly[] {
/* 140*/                genericgfpoly4, genericgfpoly6
                    });
                }
            }

            private int[] findErrorLocations(GenericGFPoly genericgfpoly)
                throws ReedSolomonException
            {
                int i;
/* 145*/        if((i = genericgfpoly.getDegree()) == 1)
/* 147*/            return (new int[] {
/* 147*/                genericgfpoly.getCoefficient(1)
                    });
/* 149*/        int ai[] = new int[i];
/* 150*/        int j = 0;
/* 151*/        for(int k = 1; k < field.getSize() && j < i; k++)
/* 152*/            if(genericgfpoly.evaluateAt(k) == 0)
                    {
/* 153*/                ai[j] = field.inverse(k);
/* 154*/                j++;
                    }

/* 157*/        if(j != i)
/* 158*/            throw new ReedSolomonException("Error locator degree does not match number of roots");
/* 160*/        else
/* 160*/            return ai;
            }

            private int[] findErrorMagnitudes(GenericGFPoly genericgfpoly, int ai[])
            {
                int i;
/* 165*/        int ai1[] = new int[i = ai.length];
/* 167*/        for(int j = 0; j < i; j++)
                {
/* 168*/            int k = field.inverse(ai[j]);
/* 169*/            int l = 1;
/* 170*/            for(int i1 = 0; i1 < i; i1++)
/* 171*/                if(j != i1)
                        {
                            int j1;
/* 176*/                    j1 = ((j1 = field.multiply(ai[i1], k)) & 1) != 0 ? j1 & -2 : j1 | 1;
/* 178*/                    l = field.multiply(l, j1);
                        }

/* 181*/            ai1[j] = field.multiply(genericgfpoly.evaluateAt(k), field.inverse(l));
/* 183*/            if(field.getGeneratorBase() != 0)
/* 184*/                ai1[j] = field.multiply(ai1[j], k);
                }

/* 187*/        return ai1;
            }

            private final GenericGF field;
}
