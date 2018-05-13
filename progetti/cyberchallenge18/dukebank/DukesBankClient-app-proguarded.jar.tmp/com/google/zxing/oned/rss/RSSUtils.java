// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RSSUtils.java

package com.google.zxing.oned.rss;


public final class RSSUtils
{

            private RSSUtils()
            {
            }

            public static int getRSSvalue(int ai[], int i, boolean flag)
            {
/*  67*/        int j = ai.length;
/*  68*/        int k = 0;
                int ai1[];
/*  69*/        int i1 = (ai1 = ai).length;
/*  69*/        for(int j1 = 0; j1 < i1; j1++)
                {
/*  69*/            int l1 = ai1[j1];
/*  70*/            k += l1;
                }

/*  72*/        int l = 0;
/*  73*/        i1 = 0;
/*  74*/        for(int k1 = 0; k1 < j - 1; k1++)
                {
/*  76*/            int i2 = 1;
/*  76*/            for(i1 |= 1 << k1; i2 < ai[k1]; i1 &= ~(1 << k1))
                    {
/*  79*/                int j2 = combins(k - i2 - 1, j - k1 - 2);
/*  80*/                if(flag && i1 == 0 && k - i2 - (j - k1 - 1) >= j - k1 - 1)
/*  82*/                    j2 -= combins(k - i2 - (j - k1), j - k1 - 2);
/*  85*/                if(j - k1 - 1 > 1)
                        {
/*  86*/                    int k2 = 0;
/*  87*/                    for(int l2 = k - i2 - (j - k1 - 2); l2 > i; l2--)
/*  89*/                        k2 += combins(k - i2 - l2 - 1, j - k1 - 3);

/*  92*/                    j2 -= k2 * (j - 1 - k1);
                        } else
/*  93*/                if(k - i2 > i)
/*  94*/                    j2--;
/*  96*/                l += j2;
/*  78*/                i2++;
                    }

/*  98*/            k -= i2;
                }

/* 100*/        return l;
            }

            private static int combins(int i, int j)
            {
                int k;
/* 106*/        if(i - j > j)
                {
/* 107*/            k = j;
/* 108*/            j = i - j;
                } else
                {
/* 110*/            k = i - j;
/* 111*/            j = j;
                }
/* 113*/        int l = 1;
/* 114*/        int i1 = 1;
/* 115*/        for(i = i; i > j; i--)
                {
/* 116*/            l *= i;
/* 117*/            if(i1 <= k)
                    {
/* 118*/                l /= i1;
/* 119*/                i1++;
                    }
                }

/* 122*/        for(; i1 <= k; i1++)
/* 123*/            l /= i1;

/* 126*/        return l;
            }
}
