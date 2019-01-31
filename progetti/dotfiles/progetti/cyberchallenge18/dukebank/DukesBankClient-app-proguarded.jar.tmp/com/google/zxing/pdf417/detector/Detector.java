// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.pdf417.detector;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.util.*;

// Referenced classes of package com.google.zxing.pdf417.detector:
//            PDF417DetectorResult

public final class Detector
{

            private Detector()
            {
            }

            public static PDF417DetectorResult detect(BinaryBitmap binarybitmap, Map map, boolean flag)
                throws NotFoundException
            {
/*  80*/        binarybitmap = binarybitmap.getBlackMatrix();
/*  82*/        if((map = detect(flag, ((BitMatrix) (binarybitmap)))).isEmpty())
                {
/*  84*/            (binarybitmap = binarybitmap.clone()).rotate180();
/*  86*/            map = detect(flag, ((BitMatrix) (binarybitmap)));
                }
/*  88*/        return new PDF417DetectorResult(binarybitmap, map);
            }

            private static List detect(boolean flag, BitMatrix bitmatrix)
            {
/*  99*/        ArrayList arraylist = new ArrayList();
/* 100*/        int i = 0;
/* 101*/        int j = 0;
/* 102*/        boolean flag1 = false;
/* 103*/        do
                {
/* 103*/            if(i >= bitmatrix.getHeight())
/* 104*/                break;
                    Object obj;
/* 104*/            if((obj = findVertices(bitmatrix, i, j))[0] == null && obj[3] == null)
                    {
/* 107*/                if(!flag1)
/* 113*/                    break;
/* 113*/                flag1 = false;
/* 114*/                j = 0;
/* 115*/                obj = arraylist.iterator();
/* 115*/                do
                        {
/* 115*/                    if(!((Iterator) (obj)).hasNext())
/* 115*/                        break;
                            ResultPoint aresultpoint[];
/* 115*/                    if((aresultpoint = (ResultPoint[])((Iterator) (obj)).next())[1] != null)
/* 117*/                        i = (int)Math.max(i, aresultpoint[1].getY());
/* 119*/                    if(aresultpoint[3] != null)
/* 120*/                        i = Math.max(i, (int)aresultpoint[3].getY());
                        } while(true);
/* 123*/                i += 5;
/* 124*/                continue;
                    }
/* 126*/            flag1 = true;
/* 127*/            arraylist.add(obj);
/* 128*/            if(!flag)
/* 133*/                break;
/* 133*/            if(obj[2] != null)
                    {
/* 134*/                j = (int)obj[2].getX();
/* 135*/                i = (int)obj[2].getY();
                    } else
                    {
/* 137*/                j = (int)obj[4].getX();
/* 138*/                i = (int)obj[4].getY();
                    }
                } while(true);
/* 141*/        return arraylist;
            }

            private static ResultPoint[] findVertices(BitMatrix bitmatrix, int i, int j)
            {
/* 160*/        int k = bitmatrix.getHeight();
/* 161*/        int l = bitmatrix.getWidth();
                ResultPoint aresultpoint[];
/* 163*/        copyToResult(aresultpoint = new ResultPoint[8], findRowsWithPattern(bitmatrix, k, l, i, j, START_PATTERN), INDEXES_START_PATTERN);
/* 167*/        if(aresultpoint[4] != null)
                {
/* 168*/            j = (int)aresultpoint[4].getX();
/* 169*/            i = (int)aresultpoint[4].getY();
                }
/* 171*/        copyToResult(aresultpoint, findRowsWithPattern(bitmatrix, k, l, i, j, STOP_PATTERN), INDEXES_STOP_PATTERN);
/* 173*/        return aresultpoint;
            }

            private static void copyToResult(ResultPoint aresultpoint[], ResultPoint aresultpoint1[], int ai[])
            {
/* 177*/        for(int i = 0; i < ai.length; i++)
/* 178*/            aresultpoint[ai[i]] = aresultpoint1[i];

            }

            private static ResultPoint[] findRowsWithPattern(BitMatrix bitmatrix, int i, int j, int k, int l, int ai[])
            {
/* 188*/        ResultPoint aresultpoint[] = new ResultPoint[4];
/* 189*/        Object obj = 0;
/* 190*/        int ai1[] = new int[ai.length];
/* 191*/label0:
/* 191*/        do
                {
/* 191*/label1:
                    {
                        int ai2[];
/* 191*/label2:
                        {
/* 191*/                    if(k >= i)
/* 192*/                        break label0;
/* 192*/                    if((ai2 = findGuardPattern(bitmatrix, l, k, j, false, ai, ai1)) == null)
/* 194*/                        break label1;
/* 194*/                    do
                            {
/* 194*/                        if(k <= 0)
/* 195*/                            break label2;
/* 195*/                        if((obj = findGuardPattern(bitmatrix, l, --k, j, false, ai, ai1)) == null)
/* 197*/                            break;
/* 197*/                        ai2 = ((int []) (obj));
                            } while(true);
/* 199*/                    k++;
                        }
/* 203*/                aresultpoint[0] = new ResultPoint(ai2[0], k);
/* 204*/                aresultpoint[1] = new ResultPoint(ai2[1], k);
/* 205*/                obj = 1;
/* 206*/                break label0;
                    }
/* 191*/            k += 5;
                } while(true);
/* 209*/        int k1 = k + 1;
/* 211*/        if(obj != 0)
                {
/* 212*/            int i1 = 0;
/* 213*/            l = (new int[] {
/* 213*/                (int)aresultpoint[0].getX(), (int)aresultpoint[1].getX()
                    });
/* 214*/            for(; k1 < i; k1++)
                    {
                        int ai3[];
/* 215*/                if((ai3 = findGuardPattern(bitmatrix, l[0], k1, j, false, ai, ai1)) != null && Math.abs(l[0] - ai3[0]) < 5 && Math.abs(l[1] - ai3[1]) < 5)
                        {
/* 223*/                    l = ai3;
/* 224*/                    i1 = 0;
/* 224*/                    continue;
                        }
/* 226*/                if(i1 > 25)
/* 229*/                    break;
/* 229*/                i1++;
                    }

/* 233*/            k1 -= i1 + 1;
/* 234*/            aresultpoint[2] = new ResultPoint(l[0], k1);
/* 235*/            aresultpoint[3] = new ResultPoint(l[1], k1);
                }
/* 237*/        if(k1 - k < 10)
                {
/* 238*/            for(int j1 = 0; j1 < 4; j1++)
/* 239*/                aresultpoint[j1] = null;

                }
/* 242*/        return aresultpoint;
            }

            private static int[] findGuardPattern(BitMatrix bitmatrix, int i, int j, int k, boolean flag, int ai[], int ai1[])
            {
/* 262*/        Arrays.fill(ai1, 0, ai1.length, 0);
/* 263*/        int l = ai.length;
/* 264*/        flag = flag;
/* 265*/        i = i;
/* 266*/        for(int i1 = 0; bitmatrix.get(i, j) && i > 0 && i1++ < 3; i--);
/* 272*/        int j1 = i;
/* 273*/        int k1 = 0;
/* 274*/        for(; j1 < k; j1++)
                {
                    boolean flag1;
/* 275*/            if((flag1 = bitmatrix.get(j1, j)) ^ flag)
                    {
/* 277*/                ai1[k1]++;
/* 277*/                continue;
                    }
/* 279*/            if(k1 == l - 1)
                    {
/* 280*/                if(patternMatchVariance(ai1, ai, 204) < 107)
/* 281*/                    return (new int[] {
/* 281*/                        i, j1
                            });
/* 283*/                i += ai1[0] + ai1[1];
/* 284*/                System.arraycopy(ai1, 2, ai1, 0, l - 2);
/* 285*/                ai1[l - 2] = 0;
/* 286*/                ai1[l - 1] = 0;
/* 287*/                k1--;
                    } else
                    {
/* 289*/                k1++;
                    }
/* 291*/            ai1[k1] = 1;
/* 292*/            flag = !flag;
                }

/* 295*/        if(k1 == l - 1 && patternMatchVariance(ai1, ai, 204) < 107)
/* 297*/            return (new int[] {
/* 297*/                i, j1 - 1
                    });
/* 300*/        else
/* 300*/            return null;
            }

            private static int patternMatchVariance(int ai[], int ai1[], int i)
            {
/* 319*/        int j = ai.length;
/* 320*/        int k = 0;
/* 321*/        int l = 0;
/* 322*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 323*/            k += ai[i1];
/* 324*/            l += ai1[i1];
                }

/* 326*/        if(k < l)
/* 329*/            return 0x7fffffff;
/* 334*/        int j1 = (k << 8) / l;
/* 335*/        i = i * j1 >> 8;
/* 337*/        l = 0;
/* 338*/        for(int k1 = 0; k1 < j; k1++)
                {
/* 339*/            int l1 = ai[k1] << 8;
/* 340*/            int i2 = ai1[k1] * j1;
/* 341*/            if((l1 = l1 <= i2 ? i2 - l1 : l1 - i2) > i)
/* 343*/                return 0x7fffffff;
/* 345*/            l += l1;
                }

/* 347*/        return l / k;
            }

            private static final int INDEXES_START_PATTERN[] = {
/*  40*/        0, 4, 1, 5
            };
            private static final int INDEXES_STOP_PATTERN[] = {
/*  41*/        6, 2, 7, 3
            };
            private static final int INTEGER_MATH_SHIFT = 8;
            private static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
            private static final int MAX_AVG_VARIANCE = 107;
            private static final int MAX_INDIVIDUAL_VARIANCE = 204;
            private static final int START_PATTERN[] = {
/*  49*/        8, 1, 1, 1, 1, 1, 1, 3
            };
            private static final int STOP_PATTERN[] = {
/*  51*/        7, 1, 1, 3, 1, 1, 1, 2, 1
            };
            private static final int MAX_PIXEL_DRIFT = 3;
            private static final int MAX_PATTERN_DRIFT = 5;
            private static final int SKIPPED_ROW_COUNT_MAX = 25;
            private static final int ROW_STEP = 5;
            private static final int BARCODE_MIN_HEIGHT = 10;

}
