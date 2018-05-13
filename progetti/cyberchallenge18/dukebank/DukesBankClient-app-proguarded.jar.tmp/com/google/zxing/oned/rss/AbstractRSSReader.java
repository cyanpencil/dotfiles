// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractRSSReader.java

package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader
{

            protected AbstractRSSReader()
            {
/*  42*/        oddCounts = new int[dataCharacterCounters.length / 2];
/*  43*/        evenCounts = new int[dataCharacterCounters.length / 2];
            }

            protected final int[] getDecodeFinderCounters()
            {
/*  47*/        return decodeFinderCounters;
            }

            protected final int[] getDataCharacterCounters()
            {
/*  51*/        return dataCharacterCounters;
            }

            protected final float[] getOddRoundingErrors()
            {
/*  55*/        return oddRoundingErrors;
            }

            protected final float[] getEvenRoundingErrors()
            {
/*  59*/        return evenRoundingErrors;
            }

            protected final int[] getOddCounts()
            {
/*  63*/        return oddCounts;
            }

            protected final int[] getEvenCounts()
            {
/*  67*/        return evenCounts;
            }

            protected static int parseFinderValue(int ai[], int ai1[][])
                throws NotFoundException
            {
/*  72*/        for(int i = 0; i < ai1.length; i++)
/*  73*/            if(patternMatchVariance(ai, ai1[i], 115) < 51)
/*  75*/                return i;

/*  78*/        throw NotFoundException.getNotFoundInstance();
            }

            protected static int count(int ai[])
            {
/*  82*/        int i = 0;
/*  83*/        int j = (ai = ai).length;
/*  83*/        for(int k = 0; k < j; k++)
                {
/*  83*/            int l = ai[k];
/*  84*/            i += l;
                }

/*  86*/        return i;
            }

            protected static void increment(int ai[], float af[])
            {
/*  90*/        int i = 0;
/*  91*/        float f = af[0];
/*  92*/        for(int j = 1; j < ai.length; j++)
/*  93*/            if(af[j] > f)
                    {
/*  94*/                f = af[j];
/*  95*/                i = j;
                    }

/*  98*/        ai[i]++;
            }

            protected static void decrement(int ai[], float af[])
            {
/* 102*/        int i = 0;
/* 103*/        float f = af[0];
/* 104*/        for(int j = 1; j < ai.length; j++)
/* 105*/            if(af[j] < f)
                    {
/* 106*/                f = af[j];
/* 107*/                i = j;
                    }

/* 110*/        ai[i]--;
            }

            protected static boolean isFinderPattern(int ai[])
            {
                float f;
/* 114*/        int j = (f = ai[0] + ai[1]) + ai[2] + ai[3];
/* 116*/        if((f = (float)f / (float)j) >= 0.7916667F && f <= 0.8928571F)
                {
/* 119*/            int i = 0x7fffffff;
/* 120*/            int k = 0x80000000;
/* 121*/            int l = (ai = ai).length;
/* 121*/            for(int i1 = 0; i1 < l; i1++)
                    {
                        int j1;
/* 121*/                if((j1 = ai[i1]) > k)
/* 123*/                    k = j1;
/* 125*/                if(j1 < i)
/* 126*/                    i = j1;
                    }

/* 129*/            return k < i * 10;
                } else
                {
/* 131*/            return false;
                }
            }

            private static final int MAX_AVG_VARIANCE = 51;
            private static final int MAX_INDIVIDUAL_VARIANCE = 115;
            private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667F;
            private static final float MAX_FINDER_PATTERN_RATIO = 0.8928571F;
            private final int decodeFinderCounters[] = new int[4];
            private final int dataCharacterCounters[] = new int[8];
            private final float oddRoundingErrors[] = new float[4];
            private final float evenRoundingErrors[] = new float[4];
            private final int oddCounts[];
            private final int evenCounts[];
}
