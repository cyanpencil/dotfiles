// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BoundingBox.java

package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

final class BoundingBox
{

            BoundingBox(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
                throws NotFoundException
            {
/*  43*/        if(resultpoint == null && resultpoint2 == null || resultpoint1 == null && resultpoint3 == null || resultpoint != null && resultpoint1 == null || resultpoint2 != null && resultpoint3 == null)
                {
/*  47*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/*  49*/            init(bitmatrix, resultpoint, resultpoint1, resultpoint2, resultpoint3);
/*  50*/            return;
                }
            }

            BoundingBox(BoundingBox boundingbox)
            {
/*  53*/        init(boundingbox.image, boundingbox.topLeft, boundingbox.bottomLeft, boundingbox.topRight, boundingbox.bottomRight);
            }

            private void init(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3)
            {
/*  61*/        image = bitmatrix;
/*  62*/        topLeft = resultpoint;
/*  63*/        bottomLeft = resultpoint1;
/*  64*/        topRight = resultpoint2;
/*  65*/        bottomRight = resultpoint3;
/*  66*/        calculateMinMaxValues();
            }

            static BoundingBox merge(BoundingBox boundingbox, BoundingBox boundingbox1)
                throws NotFoundException
            {
/*  70*/        if(boundingbox == null)
/*  71*/            return boundingbox1;
/*  73*/        if(boundingbox1 == null)
/*  74*/            return boundingbox;
/*  76*/        else
/*  76*/            return new BoundingBox(boundingbox.image, boundingbox.topLeft, boundingbox.bottomLeft, boundingbox1.topRight, boundingbox1.bottomRight);
            }

            final BoundingBox addMissingRows(int i, int j, boolean flag)
                throws NotFoundException
            {
/*  80*/        Object obj = topLeft;
/*  81*/        Object obj1 = bottomLeft;
/*  82*/        Object obj2 = topRight;
/*  83*/        Object obj3 = bottomRight;
/*  85*/        if(i > 0)
                {
                    ResultPoint resultpoint;
/*  86*/            if((i = (int)(resultpoint = flag ? topLeft : topRight).getY() - i) < 0)
/*  89*/                i = 0;
/*  92*/            i = new ResultPoint(resultpoint.getX(), i);
/*  93*/            if(flag)
/*  94*/                obj = i;
/*  96*/            else
/*  96*/                obj2 = i;
                }
/* 100*/        if(j > 0)
                {
                    ResultPoint resultpoint1;
/* 101*/            if((i = (int)(resultpoint1 = flag ? bottomLeft : bottomRight).getY() + j) >= image.getHeight())
/* 104*/                i = image.getHeight() - 1;
/* 107*/            i = new ResultPoint(resultpoint1.getX(), i);
/* 108*/            if(flag)
/* 109*/                obj1 = i;
/* 111*/            else
/* 111*/                obj3 = i;
                }
/* 115*/        calculateMinMaxValues();
/* 116*/        return new BoundingBox(image, ((ResultPoint) (obj)), ((ResultPoint) (obj1)), ((ResultPoint) (obj2)), ((ResultPoint) (obj3)));
            }

            private void calculateMinMaxValues()
            {
/* 120*/        if(topLeft == null)
                {
/* 121*/            topLeft = new ResultPoint(0.0F, topRight.getY());
/* 122*/            bottomLeft = new ResultPoint(0.0F, bottomRight.getY());
                } else
/* 123*/        if(topRight == null)
                {
/* 124*/            topRight = new ResultPoint(image.getWidth() - 1, topLeft.getY());
/* 125*/            bottomRight = new ResultPoint(image.getWidth() - 1, bottomLeft.getY());
                }
/* 128*/        minX = (int)Math.min(topLeft.getX(), bottomLeft.getX());
/* 129*/        maxX = (int)Math.max(topRight.getX(), bottomRight.getX());
/* 130*/        minY = (int)Math.min(topLeft.getY(), topRight.getY());
/* 131*/        maxY = (int)Math.max(bottomLeft.getY(), bottomRight.getY());
            }

            final int getMinX()
            {
/* 147*/        return minX;
            }

            final int getMaxX()
            {
/* 151*/        return maxX;
            }

            final int getMinY()
            {
/* 155*/        return minY;
            }

            final int getMaxY()
            {
/* 159*/        return maxY;
            }

            final ResultPoint getTopLeft()
            {
/* 163*/        return topLeft;
            }

            final ResultPoint getTopRight()
            {
/* 167*/        return topRight;
            }

            final ResultPoint getBottomLeft()
            {
/* 171*/        return bottomLeft;
            }

            final ResultPoint getBottomRight()
            {
/* 175*/        return bottomRight;
            }

            private BitMatrix image;
            private ResultPoint topLeft;
            private ResultPoint bottomLeft;
            private ResultPoint topRight;
            private ResultPoint bottomRight;
            private int minX;
            private int maxX;
            private int minY;
            private int maxY;
}
