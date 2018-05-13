// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ModulusGF.java

package com.google.zxing.pdf417.decoder.ec;


// Referenced classes of package com.google.zxing.pdf417.decoder.ec:
//            ModulusPoly

public final class ModulusGF
{

            private ModulusGF(int i, int j)
            {
/*  38*/        modulus = i;
/*  39*/        expTable = new int[i];
/*  40*/        logTable = new int[i];
/*  41*/        int k = 1;
/*  42*/        for(int l = 0; l < i; l++)
                {
/*  43*/            expTable[l] = k;
/*  44*/            k = (k * j) % i;
                }

/*  46*/        for(int i1 = 0; i1 < i - 1; i1++)
/*  47*/            logTable[expTable[i1]] = i1;

            }

            final ModulusPoly getZero()
            {
/*  56*/        return zero;
            }

            final ModulusPoly getOne()
            {
/*  60*/        return one;
            }

            final ModulusPoly buildMonomial(int i, int j)
            {
/*  64*/        if(i < 0)
/*  65*/            throw new IllegalArgumentException();
/*  67*/        if(j == 0)
                {
/*  68*/            return zero;
                } else
                {
/*  70*/            (i = new int[i + 1])[0] = j;
/*  72*/            return new ModulusPoly(this, i);
                }
            }

            final int add(int i, int j)
            {
/*  76*/        return (i + j) % modulus;
            }

            final int subtract(int i, int j)
            {
/*  80*/        return ((modulus + i) - j) % modulus;
            }

            final int exp(int i)
            {
/*  84*/        return expTable[i];
            }

            final int log(int i)
            {
/*  88*/        if(i == 0)
/*  89*/            throw new IllegalArgumentException();
/*  91*/        else
/*  91*/            return logTable[i];
            }

            final int inverse(int i)
            {
/*  95*/        if(i == 0)
/*  96*/            throw new ArithmeticException();
/*  98*/        else
/*  98*/            return expTable[modulus - logTable[i] - 1];
            }

            final int multiply(int i, int j)
            {
/* 102*/        if(i == 0 || j == 0)
/* 103*/            return 0;
/* 105*/        else
/* 105*/            return expTable[(logTable[i] + logTable[j]) % (modulus - 1)];
            }

            final int getSize()
            {
/* 109*/        return modulus;
            }

            public static final ModulusGF PDF417_GF = new ModulusGF(929, 3);
            private final int expTable[];
            private final int logTable[];
            private final ModulusPoly zero = new ModulusPoly(this, new int[] {
/*  50*/        0
            });
            private final ModulusPoly one = new ModulusPoly(this, new int[] {
/*  51*/        1
            });
            private final int modulus;

}
