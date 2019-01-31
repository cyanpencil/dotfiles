// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.*;

public final class Detector
{
    static final class Point
    {

                final ResultPoint toResultPoint()
                {
/* 578*/            return new ResultPoint(getX(), getY());
                }

                final int getX()
                {
/* 587*/            return x;
                }

                final int getY()
                {
/* 591*/            return y;
                }

                public final String toString()
                {
/* 596*/            return (new StringBuilder("<")).append(x).append(' ').append(y).append('>').toString();
                }

                private final int x;
                private final int y;

                Point(int i, int j)
                {
/* 582*/            x = i;
/* 583*/            y = j;
                }
    }


            public Detector(BitMatrix bitmatrix)
            {
/*  48*/        image = bitmatrix;
            }

            public final AztecDetectorResult detect()
                throws NotFoundException
            {
/*  52*/        return detect(false);
            }

            public final AztecDetectorResult detect(boolean flag)
                throws NotFoundException
            {
/*  64*/        Point point = getMatrixCenter();
/*  68*/        ResultPoint aresultpoint[] = getBullsEyeCorners(point);
/*  70*/        if(flag)
                {
/*  71*/            flag = aresultpoint[0];
/*  72*/            aresultpoint[0] = aresultpoint[2];
/*  73*/            aresultpoint[2] = flag;
                }
/*  77*/        extractParameters(aresultpoint);
/*  80*/        flag = sampleGrid(image, aresultpoint[shift % 4], aresultpoint[(shift + 1) % 4], aresultpoint[(shift + 2) % 4], aresultpoint[(shift + 3) % 4]);
/*  87*/        aresultpoint = getMatrixCornerPoints(aresultpoint);
/*  89*/        return new AztecDetectorResult(flag, aresultpoint, compact, nbDataBlocks, nbLayers);
            }

            private void extractParameters(ResultPoint aresultpoint[])
                throws NotFoundException
            {
/*  99*/        if(!isValid(aresultpoint[0]) || !isValid(aresultpoint[1]) || !isValid(aresultpoint[2]) || !isValid(aresultpoint[3]))
/* 101*/            throw NotFoundException.getNotFoundInstance();
/* 103*/        int i = 2 * nbCenterLayers;
/* 105*/        aresultpoint = (new int[] {
/* 105*/            sampleLine(aresultpoint[0], aresultpoint[1], i), sampleLine(aresultpoint[1], aresultpoint[2], i), sampleLine(aresultpoint[2], aresultpoint[3], i), sampleLine(aresultpoint[3], aresultpoint[0], i)
                });
/* 116*/        shift = getRotation(aresultpoint, i);
/* 119*/        long l = 0L;
/* 120*/        for(i = 0; i < 4; i++)
                {
/* 121*/            int j = aresultpoint[(shift + i) % 4];
/* 122*/            if(compact)
/* 124*/                l = (l <<= 7) + (long)(j >> 1 & 0x7f);
/* 128*/            else
/* 128*/                l = (l <<= 10) + (long)((j >> 2 & 0x3e0) + (j >> 1 & 0x1f));
                }

/* 135*/        i = getCorrectedParameterData(l, compact);
/* 137*/        if(compact)
                {
/* 139*/            nbLayers = (i >> 6) + 1;
/* 140*/            nbDataBlocks = (i & 0x3f) + 1;
/* 140*/            return;
                } else
                {
/* 143*/            nbLayers = (i >> 11) + 1;
/* 144*/            nbDataBlocks = (i & 0x7ff) + 1;
/* 146*/            return;
                }
            }

            private static int getRotation(int ai[], int i)
                throws NotFoundException
            {
/* 165*/        int j = 0;
/* 166*/        int k = (ai = ai).length;
/* 166*/        for(int l = 0; l < k; l++)
                {
                    int i1;
/* 166*/            i1 = (((i1 = ai[l]) >> i - 2) << 1) + (i1 & 1);
/* 169*/            j = (j << 3) + i1;
                }

/* 174*/        j = ((j & 1) << 11) + (j >> 1);
/* 178*/        for(ai = 0; ai < 4; ai++)
/* 179*/            if(Integer.bitCount(j ^ EXPECTED_CORNER_BITS[ai]) <= 2)
/* 180*/                return ai;

/* 183*/        throw NotFoundException.getNotFoundInstance();
            }

            private static int getCorrectedParameterData(long l, boolean flag)
                throws NotFoundException
            {
                byte byte0;
                int i;
                int ai[];
/* 197*/        if(flag)
                {
/* 198*/            flag = 7;
/* 199*/            byte0 = 2;
                } else
                {
/* 201*/            flag = 10;
/* 202*/            byte0 = 4;
                }
/* 205*/        i = flag - byte0;
/* 206*/        ai = new int[flag];
/* 207*/        flag--;
_L4:
/* 207*/        flag;
/* 207*/        JVM INSTR iflt 54;
                   goto _L1 _L2
_L1:
/* 208*/        break MISSING_BLOCK_LABEL_35;
_L2:
/* 208*/        break; /* Loop/switch isn't completed */
/* 208*/        ai[flag] = (int)l & 0xf;
/* 209*/        l >>= 4;
/* 207*/        flag--;
/* 207*/        if(true) goto _L4; else goto _L3
_L3:
/* 212*/        try
                {
/* 212*/            (flag = new ReedSolomonDecoder(GenericGF.AZTEC_PARAM)).decode(ai, i);
                }
/* 214*/        catch(ReedSolomonException _ex)
                {
/* 215*/            throw NotFoundException.getNotFoundInstance();
                }
/* 218*/        flag = false;
/* 219*/        for(l = 0; l < byte0; l++)
/* 220*/            flag = (flag << 4) + ai[l];

/* 222*/        return ((flag) ? 1 : 0);
            }

            private ResultPoint[] getBullsEyeCorners(Point point)
                throws NotFoundException
            {
/* 236*/        Point point1 = point;
/* 237*/        Point point2 = point;
/* 238*/        Point point3 = point;
/* 239*/        point = point;
/* 241*/        boolean flag = true;
/* 243*/        nbCenterLayers = 1;
/* 243*/        do
                {
/* 243*/            if(nbCenterLayers >= 9)
/* 244*/                break;
/* 244*/            Point point4 = getFirstDifferent(point1, flag, 1, -1);
/* 245*/            Point point5 = getFirstDifferent(point2, flag, 1, 1);
/* 246*/            Point point6 = getFirstDifferent(point3, flag, -1, 1);
/* 247*/            Point point7 = getFirstDifferent(point, flag, -1, -1);
                    float f;
/* 253*/            if(nbCenterLayers > 2 && ((double)(f = (distance(point7, point4) * (float)nbCenterLayers) / (distance(point, point1) * (float)(nbCenterLayers + 2))) < 0.75D || (double)f > 1.25D || !isWhiteOrBlackRectangle(point4, point5, point6, point7)))
/* 260*/                break;
/* 260*/            point1 = point4;
/* 261*/            point2 = point5;
/* 262*/            point3 = point6;
/* 263*/            point = point7;
/* 265*/            flag = !flag;
/* 243*/            nbCenterLayers++;
                } while(true);
/* 268*/        if(nbCenterLayers != 5 && nbCenterLayers != 7)
                {
/* 269*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 272*/            compact = nbCenterLayers == 5;
/* 276*/            ResultPoint resultpoint = new ResultPoint((float)point1.getX() + 0.5F, (float)point1.getY() - 0.5F);
/* 277*/            ResultPoint resultpoint1 = new ResultPoint((float)point2.getX() + 0.5F, (float)point2.getY() + 0.5F);
/* 278*/            ResultPoint resultpoint2 = new ResultPoint((float)point3.getX() - 0.5F, (float)point3.getY() + 0.5F);
/* 279*/            ResultPoint resultpoint3 = new ResultPoint((float)point.getX() - 0.5F, (float)point.getY() - 0.5F);
/* 283*/            return expandSquare(new ResultPoint[] {
/* 283*/                resultpoint, resultpoint1, resultpoint2, resultpoint3
                    }, 2 * nbCenterLayers - 3, 2 * nbCenterLayers);
                }
            }

            private Point getMatrixCenter()
            {
                ResultPoint resultpoint;
                ResultPoint resultpoint1;
                ResultPoint resultpoint2;
                int i;
/* 303*/        try
                {
                    ResultPoint aresultpoint1[];
/* 303*/            resultpoint = (aresultpoint1 = (new WhiteRectangleDetector(image)).detect())[0];
/* 305*/            resultpoint1 = aresultpoint1[1];
/* 306*/            resultpoint2 = aresultpoint1[2];
/* 307*/            i = aresultpoint1[3];
                }
/* 309*/        catch(NotFoundException _ex)
                {
/* 313*/            int k = image.getWidth() / 2;
/* 314*/            i = image.getHeight() / 2;
/* 315*/            resultpoint = getFirstDifferent(new Point(k + 7, i - 7), false, 1, -1).toResultPoint();
/* 316*/            resultpoint1 = getFirstDifferent(new Point(k + 7, i + 7), false, 1, 1).toResultPoint();
/* 317*/            resultpoint2 = getFirstDifferent(new Point(k - 7, i + 7), false, -1, 1).toResultPoint();
/* 318*/            i = getFirstDifferent(new Point(k - 7, i - 7), false, -1, -1).toResultPoint();
                }
/* 323*/        int j = MathUtils.round((resultpoint.getX() + i.getX() + resultpoint1.getX() + resultpoint2.getX()) / 4F);
/* 324*/        int l = MathUtils.round((resultpoint.getY() + i.getY() + resultpoint1.getY() + resultpoint2.getY()) / 4F);
                ResultPoint resultpoint3;
/* 330*/        try
                {
                    ResultPoint aresultpoint[];
/* 330*/            resultpoint = (aresultpoint = (new WhiteRectangleDetector(image, 15, j, l)).detect())[0];
/* 332*/            resultpoint1 = aresultpoint[1];
/* 333*/            resultpoint2 = aresultpoint[2];
/* 334*/            resultpoint3 = aresultpoint[3];
                }
/* 335*/        catch(NotFoundException _ex)
                {
/* 338*/            resultpoint = getFirstDifferent(new Point(j + 7, l - 7), false, 1, -1).toResultPoint();
/* 339*/            resultpoint1 = getFirstDifferent(new Point(j + 7, l + 7), false, 1, 1).toResultPoint();
/* 340*/            resultpoint2 = getFirstDifferent(new Point(j - 7, l + 7), false, -1, 1).toResultPoint();
/* 341*/            resultpoint3 = getFirstDifferent(new Point(j - 7, l - 7), false, -1, -1).toResultPoint();
                }
/* 345*/        j = MathUtils.round((resultpoint.getX() + resultpoint3.getX() + resultpoint1.getX() + resultpoint2.getX()) / 4F);
/* 346*/        l = MathUtils.round((resultpoint.getY() + resultpoint3.getY() + resultpoint1.getY() + resultpoint2.getY()) / 4F);
/* 348*/        return new Point(j, l);
            }

            private ResultPoint[] getMatrixCornerPoints(ResultPoint aresultpoint[])
            {
/* 358*/        return expandSquare(aresultpoint, 2 * nbCenterLayers, getDimension());
            }

            private BitMatrix sampleGrid(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
                throws NotFoundException
            {
/* 372*/        GridSampler gridsampler = GridSampler.getInstance();
                int i;
/* 373*/        float f = (float)(i = getDimension()) / 2.0F - (float)nbCenterLayers;
/* 376*/        float f1 = (float)i / 2.0F + (float)nbCenterLayers;
/* 378*/        return gridsampler.sampleGrid(bitmatrix, i, i, f, f, f1, f, f1, f1, f, f1, resultpoint.getX(), resultpoint.getY(), resultpoint1.getX(), resultpoint1.getY(), resultpoint2.getX(), resultpoint2.getY(), resultpoint3.getX(), resultpoint3.getY());
            }

            private int sampleLine(ResultPoint resultpoint, ResultPoint resultpoint1, int i)
            {
/* 400*/        int j = 0;
                float f;
/* 402*/        float f1 = (f = distance(resultpoint, resultpoint1)) / (float)i;
/* 404*/        float f2 = resultpoint.getX();
/* 405*/        float f3 = resultpoint.getY();
/* 406*/        float f4 = (f1 * (resultpoint1.getX() - resultpoint.getX())) / f;
/* 407*/        resultpoint = (f1 * (resultpoint1.getY() - resultpoint.getY())) / f;
/* 408*/        for(resultpoint1 = 0; resultpoint1 < i; resultpoint1++)
/* 409*/            if(image.get(MathUtils.round(f2 + (float)resultpoint1 * f4), MathUtils.round(f3 + (float)resultpoint1 * resultpoint)))
/* 410*/                j |= 1 << i - resultpoint1 - 1;

/* 413*/        return j;
            }

            private boolean isWhiteOrBlackRectangle(Point point, Point point1, Point point2, Point point3)
            {
/* 427*/        point = new Point(point.getX() - 3, point.getY() + 3);
/* 428*/        point1 = new Point(point1.getX() - 3, point1.getY() - 3);
/* 429*/        point2 = new Point(point2.getX() + 3, point2.getY() - 3);
/* 430*/        point3 = new Point(point3.getX() + 3, point3.getY() + 3);
                int i;
/* 432*/        if((i = getColor(point3, point)) == 0)
/* 435*/            return false;
/* 438*/        if((point = getColor(point, point1)) != i)
/* 441*/            return false;
/* 444*/        if((point = getColor(point1, point2)) != i)
/* 447*/            return false;
/* 450*/        return (point = getColor(point2, point3)) == i;
            }

            private int getColor(Point point, Point point1)
            {
/* 462*/        float f = distance(point, point1);
/* 463*/        float f1 = (float)(point1.getX() - point.getX()) / f;
/* 464*/        point1 = (float)(point1.getY() - point.getY()) / f;
/* 465*/        int i = 0;
/* 467*/        float f2 = point.getX();
/* 468*/        float f3 = point.getY();
/* 470*/        point = image.get(point.getX(), point.getY());
/* 472*/        for(int j = 0; (float)j < f; j++)
                {
/* 473*/            f2 += f1;
/* 474*/            f3 += point1;
/* 475*/            if(image.get(MathUtils.round(f2), MathUtils.round(f3)) != point)
/* 476*/                i++;
                }

                float f4;
/* 480*/        if((f4 = (float)i / f) > 0.1F && f4 < 0.9F)
/* 483*/            return 0;
/* 486*/        return (f4 > 0.1F ? false : 1) != point ? -1 : 1;
            }

            private Point getFirstDifferent(Point point, boolean flag, int i, int j)
            {
/* 493*/        int k = point.getX() + i;
/* 494*/        for(point = point.getY() + j; isValid(k, point) && image.get(k, point) == flag; point += j)
/* 497*/            k += i;

/* 501*/        k -= i;
/* 502*/        for(point -= j; isValid(k, point) && image.get(k, point) == flag; k += i);
/* 507*/        for(k -= i; isValid(k, point) && image.get(k, point) == flag; point += j);
/* 512*/        point -= j;
/* 514*/        return new Point(k, point);
            }

            private static ResultPoint[] expandSquare(ResultPoint aresultpoint[], float f, float f1)
            {
/* 526*/        f = f1 / (f * 2.0F);
/* 527*/        f1 = aresultpoint[0].getX() - aresultpoint[2].getX();
/* 528*/        float f2 = aresultpoint[0].getY() - aresultpoint[2].getY();
/* 529*/        float f3 = (aresultpoint[0].getX() + aresultpoint[2].getX()) / 2.0F;
/* 530*/        float f4 = (aresultpoint[0].getY() + aresultpoint[2].getY()) / 2.0F;
/* 532*/        ResultPoint resultpoint = new ResultPoint(f3 + f * f1, f4 + f * f2);
/* 533*/        ResultPoint resultpoint1 = new ResultPoint(f3 - f * f1, f4 - f * f2);
/* 535*/        f1 = aresultpoint[1].getX() - aresultpoint[3].getX();
/* 536*/        f2 = aresultpoint[1].getY() - aresultpoint[3].getY();
/* 537*/        f3 = (aresultpoint[1].getX() + aresultpoint[3].getX()) / 2.0F;
/* 538*/        f4 = (aresultpoint[1].getY() + aresultpoint[3].getY()) / 2.0F;
/* 539*/        aresultpoint = new ResultPoint(f3 + f * f1, f4 + f * f2);
/* 540*/        f = new ResultPoint(f3 - f * f1, f4 - f * f2);
/* 542*/        return (new ResultPoint[] {
/* 542*/            resultpoint, aresultpoint, resultpoint1, f
                });
            }

            private boolean isValid(int i, int j)
            {
/* 546*/        return i >= 0 && i < image.getWidth() && j > 0 && j < image.getHeight();
            }

            private boolean isValid(ResultPoint resultpoint)
            {
/* 550*/        int i = MathUtils.round(resultpoint.getX());
/* 551*/        resultpoint = MathUtils.round(resultpoint.getY());
/* 552*/        return isValid(i, resultpoint);
            }

            private static float distance(Point point, Point point1)
            {
/* 556*/        return MathUtils.distance(point.getX(), point.getY(), point1.getX(), point1.getY());
            }

            private static float distance(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 560*/        return MathUtils.distance(resultpoint.getX(), resultpoint.getY(), resultpoint1.getX(), resultpoint1.getY());
            }

            private int getDimension()
            {
/* 564*/        if(compact)
/* 565*/            return 4 * nbLayers + 11;
/* 567*/        if(nbLayers <= 4)
/* 568*/            return 4 * nbLayers + 15;
/* 570*/        else
/* 570*/            return 4 * nbLayers + 2 * ((nbLayers - 4) / 8 + 1) + 15;
            }

            private final BitMatrix image;
            private boolean compact;
            private int nbLayers;
            private int nbDataBlocks;
            private int nbCenterLayers;
            private int shift;
            private static final int EXPECTED_CORNER_BITS[] = {
/* 148*/        3808, 476, 2107, 1799
            };

}
