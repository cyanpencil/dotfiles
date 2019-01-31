// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GenericGF.java

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GenericGFPoly

public final class GenericGF
{

            public GenericGF(int i, int j, int k)
            {
/*  50*/        initialized = false;
/*  64*/        primitive = i;
/*  65*/        size = j;
/*  66*/        generatorBase = k;
/*  68*/        if(j <= 0)
/*  69*/            initialize();
            }

            private void initialize()
            {
/*  74*/        expTable = new int[size];
/*  75*/        logTable = new int[size];
/*  76*/        int i = 1;
/*  77*/        for(int j = 0; j < size; j++)
                {
/*  78*/            expTable[j] = i;
/*  79*/            if((i <<= 1) >= size)
/*  81*/                i = (i ^= primitive) & size - 1;
                }

/*  85*/        for(int k = 0; k < size - 1; k++)
/*  86*/            logTable[expTable[k]] = k;

/*  89*/        zero = new GenericGFPoly(this, new int[] {
/*  89*/            0
                });
/*  90*/        one = new GenericGFPoly(this, new int[] {
/*  90*/            1
                });
/*  91*/        initialized = true;
            }

            private void checkInit()
            {
/*  95*/        if(!initialized)
/*  96*/            initialize();
            }

            final GenericGFPoly getZero()
            {
/* 101*/        checkInit();
/* 103*/        return zero;
            }

            final GenericGFPoly getOne()
            {
/* 107*/        checkInit();
/* 109*/        return one;
            }

            final GenericGFPoly buildMonomial(int i, int j)
            {
/* 116*/        checkInit();
/* 118*/        if(i < 0)
/* 119*/            throw new IllegalArgumentException();
/* 121*/        if(j == 0)
                {
/* 122*/            return zero;
                } else
                {
/* 124*/            (i = new int[i + 1])[0] = j;
/* 126*/            return new GenericGFPoly(this, i);
                }
            }

            static int addOrSubtract(int i, int j)
            {
/* 135*/        return i ^ j;
            }

            final int exp(int i)
            {
/* 142*/        checkInit();
/* 144*/        return expTable[i];
            }

            final int log(int i)
            {
/* 151*/        checkInit();
/* 153*/        if(i == 0)
/* 154*/            throw new IllegalArgumentException();
/* 156*/        else
/* 156*/            return logTable[i];
            }

            final int inverse(int i)
            {
/* 163*/        checkInit();
/* 165*/        if(i == 0)
/* 166*/            throw new ArithmeticException();
/* 168*/        else
/* 168*/            return expTable[size - logTable[i] - 1];
            }

            final int multiply(int i, int j)
            {
/* 175*/        checkInit();
/* 177*/        if(i == 0 || j == 0)
/* 178*/            return 0;
/* 180*/        else
/* 180*/            return expTable[(logTable[i] + logTable[j]) % (size - 1)];
            }

            public final int getSize()
            {
/* 184*/        return size;
            }

            public final int getGeneratorBase()
            {
/* 188*/        return generatorBase;
            }

            public final String toString()
            {
/* 193*/        return (new StringBuilder("GF(0x")).append(Integer.toHexString(primitive)).append(',').append(size).append(')').toString();
            }

            public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
            public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
            public static final GenericGF AZTEC_DATA_6;
            public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
            public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
            public static final GenericGF DATA_MATRIX_FIELD_256;
            public static final GenericGF AZTEC_DATA_8;
            public static final GenericGF MAXICODE_FIELD_64;
            private static final int INITIALIZATION_THRESHOLD = 0;
            private int expTable[];
            private int logTable[];
            private GenericGFPoly zero;
            private GenericGFPoly one;
            private final int size;
            private final int primitive;
            private final int generatorBase;
            private boolean initialized;

            static 
            {
/*  34*/        AZTEC_DATA_6 = new GenericGF(67, 64, 1);
/*  37*/        AZTEC_DATA_8 = DATA_MATRIX_FIELD_256 = new GenericGF(301, 256, 1);
/*  39*/        MAXICODE_FIELD_64 = AZTEC_DATA_6;
            }
}
