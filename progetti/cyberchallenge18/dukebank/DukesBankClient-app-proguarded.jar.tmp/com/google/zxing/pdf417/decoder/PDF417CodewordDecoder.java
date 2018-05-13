// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417CodewordDecoder.java

package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;

final class PDF417CodewordDecoder
{

            private PDF417CodewordDecoder()
            {
            }

            static int getDecodedValue(int ai[])
            {
                int i;
/*  51*/        if((i = getDecodedCodewordValue(sampleBitCounts(ai))) != -1)
/*  53*/            return i;
/*  55*/        else
/*  55*/            return getClosestDecodedValue(ai);
            }

            private static int[] sampleBitCounts(int ai[])
            {
/*  59*/        float f = PDF417Common.getBitCountSum(ai);
/*  60*/        int ai1[] = new int[8];
/*  61*/        int i = 0;
/*  62*/        int j = 0;
/*  63*/        for(int k = 0; k < 17; k++)
                {
/*  64*/            float f1 = f / 34F + ((float)k * f) / 17F;
/*  67*/            if((float)(j + ai[i]) <= f1)
                    {
/*  68*/                j += ai[i];
/*  69*/                i++;
                    }
/*  71*/            ai1[i]++;
                }

/*  73*/        return ai1;
            }

            private static int getDecodedCodewordValue(int ai[])
            {
/*  77*/        if(PDF417Common.getCodeword(ai = getBitValue(ai)) == -1)
/*  78*/            return -1;
/*  78*/        else
/*  78*/            return ai;
            }

            private static int getBitValue(int ai[])
            {
/*  82*/        long l = 0L;
/*  83*/        for(int i = 0; i < ai.length; i++)
                {
/*  84*/            for(int j = 0; j < ai[i]; j++)
/*  85*/                l = l << 1 | (long)(i % 2 != 0 ? 0 : 1);

                }

/*  88*/        return (int)l;
            }

            private static int getClosestDecodedValue(int ai[])
            {
/*  92*/        int i = PDF417Common.getBitCountSum(ai);
/*  93*/        float af[] = new float[8];
/*  94*/        for(int k = 0; k < 8; k++)
/*  95*/            af[k] = (float)ai[k] / (float)i;

/*  97*/        float f = 3.402823E+38F;
/*  98*/        ai = -1;
/*  99*/        for(int j = 0; j < RATIOS_TABLE.length; j++)
                {
/* 100*/            float f1 = 0.0F;
/* 101*/            float af1[] = RATIOS_TABLE[j];
/* 102*/            int l = 0;
/* 102*/            do
                    {
/* 102*/                if(l >= 8)
/* 103*/                    break;
/* 103*/                float f2 = af1[l] - af[l];
/* 104*/                if((f1 += f2 * f2) >= f)
/* 102*/                    break;
/* 102*/                l++;
                    } while(true);
/* 109*/            if(f1 < f)
                    {
/* 110*/                f = f1;
/* 111*/                ai = PDF417Common.SYMBOL_TABLE[j];
                    }
                }

/* 114*/        return ai;
            }

            private static final float RATIOS_TABLE[][];

            static 
            {
/*  27*/        RATIOS_TABLE = new float[PDF417Common.SYMBOL_TABLE.length][8];
/*  32*/        for(int i = 0; i < PDF417Common.SYMBOL_TABLE.length; i++)
                {
                    int j;
/*  33*/            int k = (j = PDF417Common.SYMBOL_TABLE[i]) & 1;
/*  35*/            for(int l = 0; l < 8; l++)
                    {
/*  36*/                float f = 0.0F;
/*  37*/                for(; (j & 1) == k; j >>= 1)
/*  38*/                    f++;

/*  41*/                k = j & 1;
/*  42*/                RATIOS_TABLE[i][8 - l - 1] = f / 17F;
                    }

                }

            }
}
