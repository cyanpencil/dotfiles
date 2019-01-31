// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ErrorCorrection.java

package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

// Referenced classes of package com.google.zxing.pdf417.decoder.ec:
//            ModulusGF, ModulusPoly

public final class ErrorCorrection
{

            public ErrorCorrection()
            {
/*  35*/        field = ModulusGF.PDF417_GF;
            }

            public final int decode(int ai[], int i, int ai1[])
                throws ChecksumException
            {
/*  45*/        ModulusPoly moduluspoly = new ModulusPoly(field, ai);
/*  46*/        int ai2[] = new int[i];
/*  47*/        boolean flag = false;
/*  48*/        for(int l = i; l > 0; l--)
                {
/*  49*/            int i1 = moduluspoly.evaluateAt(field.exp(l));
/*  50*/            ai2[i - l] = i1;
/*  51*/            if(i1 != 0)
/*  52*/                flag = true;
                }

/*  56*/        if(!flag)
/*  57*/            return 0;
/*  60*/        ModulusPoly moduluspoly3 = field.getOne();
                int ai4[];
/*  61*/        ai1 = (ai4 = ai1).length;
/*  61*/        for(int j = 0; j < ai1; j++)
                {
/*  61*/            int k = ai4[j];
/*  62*/            int j1 = field.exp(ai.length - 1 - k);
/*  64*/            ModulusPoly moduluspoly1 = new ModulusPoly(field, new int[] {
/*  64*/                field.subtract(0, j1), 1
                    });
/*  65*/            moduluspoly3 = moduluspoly3.multiply(moduluspoly1);
                }

/*  68*/        ModulusPoly moduluspoly4 = new ModulusPoly(field, ai2);
/*  71*/        j = (ai1 = runEuclideanAlgorithm(field.buildMonomial(i, 1), moduluspoly4, i))[0];
/*  74*/        ModulusPoly moduluspoly2 = ai1[1];
/*  78*/        int ai5[] = findErrorLocations(j);
/*  79*/        int ai3[] = findErrorMagnitudes(moduluspoly2, j, ai5);
/*  81*/        for(i = 0; i < ai5.length; i++)
                {
/*  82*/            if((ai1 = ai.length - 1 - field.log(ai5[i])) < 0)
/*  84*/                throw ChecksumException.getChecksumInstance();
/*  86*/            ai[ai1] = field.subtract(ai[ai1], ai3[i]);
                }

/*  88*/        return ai5.length;
            }

            private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly moduluspoly, ModulusPoly moduluspoly1, int i)
                throws ChecksumException
            {
/*  94*/        if(moduluspoly.getDegree() < moduluspoly1.getDegree())
                {
/*  95*/            ModulusPoly moduluspoly2 = moduluspoly;
/*  96*/            moduluspoly = moduluspoly1;
/*  97*/            moduluspoly1 = moduluspoly2;
                }
/* 100*/        ModulusPoly moduluspoly3 = moduluspoly;
/* 101*/        moduluspoly = moduluspoly1;
/* 102*/        moduluspoly1 = field.getZero();
                ModulusPoly moduluspoly4;
                ModulusPoly moduluspoly7;
/* 103*/        for(moduluspoly4 = field.getOne(); moduluspoly.getDegree() >= i / 2; moduluspoly4 = moduluspoly4.multiply(moduluspoly1).subtract(moduluspoly7).negative())
                {
/* 107*/            ModulusPoly moduluspoly5 = moduluspoly3;
/* 108*/            moduluspoly7 = moduluspoly1;
/* 109*/            moduluspoly3 = moduluspoly;
/* 110*/            moduluspoly1 = moduluspoly4;
/* 113*/            if(moduluspoly3.isZero())
/* 115*/                throw ChecksumException.getChecksumInstance();
/* 117*/            moduluspoly = moduluspoly5;
/* 118*/            moduluspoly4 = field.getZero();
/* 119*/            int j = moduluspoly3.getCoefficient(moduluspoly3.getDegree());
/* 120*/            j = field.inverse(j);
                    int i1;
                    int j1;
/* 121*/            for(; moduluspoly.getDegree() >= moduluspoly3.getDegree() && !moduluspoly.isZero(); moduluspoly = moduluspoly.subtract(moduluspoly3.multiplyByMonomial(i1, j1)))
                    {
/* 122*/                i1 = moduluspoly.getDegree() - moduluspoly3.getDegree();
/* 123*/                j1 = field.multiply(moduluspoly.getCoefficient(moduluspoly.getDegree()), j);
/* 124*/                moduluspoly4 = moduluspoly4.add(field.buildMonomial(i1, j1));
                    }

                }

                int k;
/* 131*/        if((k = moduluspoly4.getCoefficient(0)) == 0)
                {
/* 133*/            throw ChecksumException.getChecksumInstance();
                } else
                {
/* 136*/            int l = field.inverse(k);
/* 137*/            moduluspoly4 = moduluspoly4.multiply(l);
/* 138*/            ModulusPoly moduluspoly6 = moduluspoly.multiply(l);
/* 139*/            return (new ModulusPoly[] {
/* 139*/                moduluspoly4, moduluspoly6
                    });
                }
            }

            private int[] findErrorLocations(ModulusPoly moduluspoly)
                throws ChecksumException
            {
                int i;
/* 144*/        int ai[] = new int[i = moduluspoly.getDegree()];
/* 146*/        int j = 0;
/* 147*/        for(int k = 1; k < field.getSize() && j < i; k++)
/* 148*/            if(moduluspoly.evaluateAt(k) == 0)
                    {
/* 149*/                ai[j] = field.inverse(k);
/* 150*/                j++;
                    }

/* 153*/        if(j != i)
/* 154*/            throw ChecksumException.getChecksumInstance();
/* 156*/        else
/* 156*/            return ai;
            }

            private int[] findErrorMagnitudes(ModulusPoly moduluspoly, ModulusPoly moduluspoly1, int ai[])
            {
                int i;
/* 162*/        int ai2[] = new int[i = moduluspoly1.getDegree()];
/* 164*/        for(int k = 1; k <= i; k++)
/* 165*/            ai2[i - k] = field.multiply(k, moduluspoly1.getCoefficient(k));

/* 168*/        ModulusPoly moduluspoly2 = new ModulusPoly(field, ai2);
/* 171*/        int ai1[] = new int[moduluspoly1 = ai.length];
/* 173*/        for(int j = 0; j < moduluspoly1; j++)
                {
/* 174*/            int l = field.inverse(ai[j]);
/* 175*/            int i1 = field.subtract(0, moduluspoly.evaluateAt(l));
/* 176*/            l = field.inverse(moduluspoly2.evaluateAt(l));
/* 177*/            ai1[j] = field.multiply(i1, l);
                }

/* 179*/        return ai1;
            }

            private final ModulusGF field;
}
