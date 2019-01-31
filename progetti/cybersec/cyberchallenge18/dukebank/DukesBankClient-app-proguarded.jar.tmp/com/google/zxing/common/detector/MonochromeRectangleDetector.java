// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MonochromeRectangleDetector.java

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector
{

            public MonochromeRectangleDetector(BitMatrix bitmatrix)
            {
/*  37*/        image = bitmatrix;
            }

            public final ResultPoint[] detect()
                throws NotFoundException
            {
/*  51*/        int i = image.getHeight();
/*  52*/        int j = image.getWidth();
/*  53*/        int k = i >> 1;
/*  54*/        int l = j >> 1;
/*  55*/        int i1 = Math.max(1, i / 256);
/*  56*/        int j1 = Math.max(1, j / 256);
                ResultPoint resultpoint1;
/*  62*/        int k1 = (int)(resultpoint1 = findCornerFromCenter(l, 0, 0, j, k, -i1, 0, i, l >> 1)).getY() - 1;
                ResultPoint resultpoint2;
/*  65*/        int l1 = (int)(resultpoint2 = findCornerFromCenter(l, -j1, 0, j, k, 0, k1, i, k >> 1)).getX() - 1;
                ResultPoint resultpoint;
/*  68*/        j = (int)(resultpoint = findCornerFromCenter(l, j1, l1, j, k, 0, k1, i, k >> 1)).getX() + 1;
                ResultPoint resultpoint3;
/*  71*/        i = (int)(resultpoint3 = findCornerFromCenter(l, 0, l1, j, k, i1, k1, i, l >> 1)).getY() + 1;
/*  76*/        k1 = findCornerFromCenter(l, 0, l1, j, k, -i1, k1, i, l >> 2);
/*  79*/        return (new ResultPoint[] {
/*  79*/            k1, resultpoint2, resultpoint, resultpoint3
                });
            }

            private ResultPoint findCornerFromCenter(int i, int j, int k, int l, int i1, int j1, int k1, 
                    int l1, int i2)
                throws NotFoundException
            {
/* 109*/        int ai[] = null;
/* 110*/        int j2 = i1;
/* 110*/        for(int k2 = i; j2 < l1 && j2 >= k1 && k2 < l && k2 >= k; k2 += j)
                {
                    int ai1[];
/* 114*/            if(j == 0)
/* 116*/                ai1 = blackWhiteRange(j2, i2, k, l, true);
/* 119*/            else
/* 119*/                ai1 = blackWhiteRange(k2, i2, k1, l1, false);
/* 121*/            if(ai1 == null)
                    {
/* 122*/                if(ai == null)
/* 123*/                    throw NotFoundException.getNotFoundInstance();
/* 126*/                if(j == 0)
                        {
/* 127*/                    k = j2 - j1;
/* 128*/                    if(ai[0] < i)
                            {
/* 129*/                        if(ai[1] > i)
/* 131*/                            return new ResultPoint(j1 <= 0 ? ai[1] : ai[0], k);
/* 133*/                        else
/* 133*/                            return new ResultPoint(ai[0], k);
                            } else
                            {
/* 135*/                        return new ResultPoint(ai[1], k);
                            }
                        }
/* 138*/                k = k2 - j;
/* 139*/                if(ai[0] < i1)
                        {
/* 140*/                    if(ai[1] > i1)
/* 141*/                        return new ResultPoint(k, j >= 0 ? ai[1] : ai[0]);
/* 143*/                    else
/* 143*/                        return new ResultPoint(k, ai[0]);
                        } else
                        {
/* 145*/                    return new ResultPoint(k, ai[1]);
                        }
                    }
/* 149*/            ai = ai1;
/* 112*/            j2 += j1;
                }

/* 151*/        throw NotFoundException.getNotFoundInstance();
            }

            private int[] blackWhiteRange(int i, int j, int k, int l, boolean flag)
            {
                int i1;
/* 170*/        int j1 = i1 = k + l >> 1;
/* 174*/        do
                {
/* 174*/            if(j1 < k)
/* 175*/                break;
/* 175*/            if(flag ? image.get(j1, i) : image.get(i, j1))
                    {
/* 176*/                j1--;
/* 176*/                continue;
                    }
/* 178*/            int k1 = j1;
/* 180*/            while(--j1 >= k && (flag ? !image.get(j1, i) : !image.get(i, j1))) ;
/* 183*/            int i2 = k1 - j1;
/* 184*/            if(j1 >= k && i2 <= j)
/* 185*/                continue;
/* 185*/            j1 = k1;
/* 186*/            break;
                } while(true);
/* 190*/        j1++;
/* 193*/        int l1 = i1;
/* 194*/        do
                {
/* 194*/            if(l1 >= l)
/* 195*/                break;
/* 195*/            if(flag ? image.get(l1, i) : image.get(i, l1))
                    {
/* 196*/                l1++;
/* 196*/                continue;
                    }
/* 198*/            int j2 = l1;
/* 200*/            while(++l1 < l && (flag ? !image.get(l1, i) : !image.get(i, l1))) ;
/* 203*/            k = l1 - j2;
/* 204*/            if(l1 < l && k <= j)
/* 205*/                continue;
/* 205*/            l1 = j2;
/* 206*/            break;
                } while(true);
/* 210*/        if(--l1 > j1)
/* 212*/            return (new int[] {
/* 212*/                j1, l1
                    });
/* 212*/        else
/* 212*/            return null;
            }

            private static final int MAX_MODULES = 32;
            private final BitMatrix image;
}
