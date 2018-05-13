// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WhiteRectangleDetector.java

package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.common.detector:
//            MathUtils

public final class WhiteRectangleDetector
{

            public WhiteRectangleDetector(BitMatrix bitmatrix)
                throws NotFoundException
            {
/*  47*/        this(bitmatrix, 10, bitmatrix.getWidth() / 2, bitmatrix.getHeight() / 2);
            }

            public WhiteRectangleDetector(BitMatrix bitmatrix, int i, int j, int k)
                throws NotFoundException
            {
/*  54*/        image = bitmatrix;
/*  55*/        height = bitmatrix.getHeight();
/*  56*/        width = bitmatrix.getWidth();
/*  57*/        bitmatrix = i / 2;
/*  58*/        leftInit = j - bitmatrix;
/*  59*/        rightInit = j + bitmatrix;
/*  60*/        upInit = k - bitmatrix;
/*  61*/        downInit = k + bitmatrix;
/*  62*/        if(upInit < 0 || leftInit < 0 || downInit >= height || rightInit >= width)
/*  63*/            throw NotFoundException.getNotFoundInstance();
/*  65*/        else
/*  65*/            return;
            }

            public final ResultPoint[] detect()
                throws NotFoundException
            {
/*  83*/        int i = leftInit;
/*  84*/        int k = rightInit;
/*  85*/        int l = upInit;
/*  86*/        int j1 = downInit;
/*  87*/        boolean flag = false;
/*  88*/        boolean flag1 = true;
/*  89*/        boolean flag2 = false;
/*  91*/        boolean flag3 = false;
/*  92*/        boolean flag4 = false;
/*  93*/        boolean flag5 = false;
/*  94*/        boolean flag6 = false;
/*  96*/        do
                {
/*  96*/            if(!flag1)
/*  98*/                break;
/*  98*/            flag1 = false;
/* 103*/            boolean flag7 = true;
/* 104*/            do
                    {
/* 104*/                if(!flag7 && flag3 || k >= width)
/* 105*/                    break;
/* 105*/                if(flag7 = containsBlackPoint(l, j1, k, false))
                        {
/* 107*/                    k++;
/* 108*/                    flag1 = true;
/* 109*/                    flag3 = true;
                        } else
/* 110*/                if(!flag3)
/* 111*/                    k++;
                    } while(true);
/* 115*/            if(k >= width)
                    {
/* 116*/                flag = true;
/* 117*/                break;
                    }
/* 123*/            boolean flag8 = true;
/* 124*/            do
                    {
/* 124*/                if(!flag8 && flag4 || j1 >= height)
/* 125*/                    break;
/* 125*/                if(flag8 = containsBlackPoint(i, k, j1, true))
                        {
/* 127*/                    j1++;
/* 128*/                    flag1 = true;
/* 129*/                    flag4 = true;
                        } else
/* 130*/                if(!flag4)
/* 131*/                    j1++;
                    } while(true);
/* 135*/            if(j1 >= height)
                    {
/* 136*/                flag = true;
/* 137*/                break;
                    }
/* 143*/            boolean flag9 = true;
/* 144*/            do
                    {
/* 144*/                if(!flag9 && flag5 || i < 0)
/* 145*/                    break;
/* 145*/                if(flag9 = containsBlackPoint(l, j1, i, false))
                        {
/* 147*/                    i--;
/* 148*/                    flag1 = true;
/* 149*/                    flag5 = true;
                        } else
/* 150*/                if(!flag5)
/* 151*/                    i--;
                    } while(true);
/* 155*/            if(i < 0)
                    {
/* 156*/                flag = true;
/* 157*/                break;
                    }
/* 163*/            boolean flag10 = true;
/* 164*/            do
                    {
/* 164*/                if(!flag10 && flag6 || l < 0)
/* 165*/                    break;
/* 165*/                if(flag10 = containsBlackPoint(i, k, l, true))
                        {
/* 167*/                    l--;
/* 168*/                    flag1 = true;
/* 169*/                    flag6 = true;
                        } else
/* 170*/                if(!flag6)
/* 171*/                    l--;
                    } while(true);
/* 175*/            if(l < 0)
                    {
/* 176*/                flag = true;
/* 177*/                break;
                    }
/* 180*/            if(flag1)
/* 181*/                flag2 = true;
                } while(true);
/* 186*/        if(!flag && flag2)
                {
/* 188*/            int k1 = k - i;
/* 190*/            ResultPoint resultpoint1 = null;
/* 191*/            for(int l1 = 1; l1 < k1 && (resultpoint1 = getBlackPointOnSegment(i, j1 - l1, i + l1, j1)) == null; l1++);
/* 198*/            if(resultpoint1 == null)
/* 199*/                throw NotFoundException.getNotFoundInstance();
/* 202*/            ResultPoint resultpoint2 = null;
/* 204*/            for(int i2 = 1; i2 < k1 && (resultpoint2 = getBlackPointOnSegment(i, l + i2, i + i2, l)) == null; i2++);
/* 211*/            if(resultpoint2 == null)
/* 212*/                throw NotFoundException.getNotFoundInstance();
/* 215*/            ResultPoint resultpoint3 = null;
/* 217*/            for(int j = 1; j < k1 && (resultpoint3 = getBlackPointOnSegment(k, l + j, k - j, l)) == null; j++);
/* 224*/            if(resultpoint3 == null)
/* 225*/                throw NotFoundException.getNotFoundInstance();
/* 228*/            ResultPoint resultpoint = null;
/* 230*/            for(int i1 = 1; i1 < k1 && (resultpoint = getBlackPointOnSegment(k, j1 - i1, k - i1, j1)) == null; i1++);
/* 237*/            if(resultpoint == null)
/* 238*/                throw NotFoundException.getNotFoundInstance();
/* 241*/            else
/* 241*/                return centerEdges(resultpoint, resultpoint1, resultpoint3, resultpoint2);
                } else
                {
/* 244*/            throw NotFoundException.getNotFoundInstance();
                }
            }

            private ResultPoint getBlackPointOnSegment(float f, float f1, float f2, float f3)
            {
/* 249*/        int i = MathUtils.round(MathUtils.distance(f, f1, f2, f3));
/* 250*/        f2 = (f2 - f) / (float)i;
/* 251*/        f3 = (f3 - f1) / (float)i;
/* 253*/        for(int j = 0; j < i; j++)
                {
/* 254*/            int k = MathUtils.round(f + (float)j * f2);
/* 255*/            int l = MathUtils.round(f1 + (float)j * f3);
/* 256*/            if(image.get(k, l))
/* 257*/                return new ResultPoint(k, l);
                }

/* 260*/        return null;
            }

            private ResultPoint[] centerEdges(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
            {
/* 286*/        float f = resultpoint.getX();
/* 287*/        resultpoint = resultpoint.getY();
/* 288*/        float f1 = resultpoint1.getX();
/* 289*/        resultpoint1 = resultpoint1.getY();
/* 290*/        float f2 = resultpoint2.getX();
/* 291*/        resultpoint2 = resultpoint2.getY();
/* 292*/        float f3 = resultpoint3.getX();
/* 293*/        resultpoint3 = resultpoint3.getY();
/* 295*/        if(f < (float)width / 2.0F)
/* 296*/            return (new ResultPoint[] {
/* 296*/                new ResultPoint(f3 - 1.0F, resultpoint3 + 1.0F), new ResultPoint(f1 + 1.0F, resultpoint1 + 1.0F), new ResultPoint(f2 - 1.0F, resultpoint2 - 1.0F), new ResultPoint(f + 1.0F, resultpoint - 1.0F)
                    });
/* 302*/        else
/* 302*/            return (new ResultPoint[] {
/* 302*/                new ResultPoint(f3 + 1.0F, resultpoint3 + 1.0F), new ResultPoint(f1 + 1.0F, resultpoint1 - 1.0F), new ResultPoint(f2 - 1.0F, resultpoint2 + 1.0F), new ResultPoint(f - 1.0F, resultpoint - 1.0F)
                    });
            }

            private boolean containsBlackPoint(int i, int j, int k, boolean flag)
            {
/* 321*/        if(flag)
/* 322*/            for(i = i; i <= j; i++)
/* 323*/                if(image.get(i, k))
/* 324*/                    return true;

/* 328*/        else
/* 328*/            for(i = i; i <= j; i++)
/* 329*/                if(image.get(k, i))
/* 330*/                    return true;

/* 335*/        return false;
            }

            private static final int INIT_SIZE = 10;
            private static final int CORR = 1;
            private final BitMatrix image;
            private final int height;
            private final int width;
            private final int leftInit;
            private final int rightInit;
            private final int downInit;
            private final int upInit;
}
