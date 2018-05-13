// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.*;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.*;

public final class Detector
{
    static final class ResultPointsAndTransitionsComparator
        implements Serializable, Comparator
    {

                public final int compare(ResultPointsAndTransitions resultpointsandtransitions, ResultPointsAndTransitions resultpointsandtransitions1)
                {
/* 436*/            return resultpointsandtransitions.getTransitions() - resultpointsandtransitions1.getTransitions();
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/* 432*/            return compare((ResultPointsAndTransitions)obj, (ResultPointsAndTransitions)obj1);
                }

                private ResultPointsAndTransitionsComparator()
                {
                }

    }

    static final class ResultPointsAndTransitions
    {

                final ResultPoint getFrom()
                {
/* 412*/            return from;
                }

                final ResultPoint getTo()
                {
/* 416*/            return to;
                }

                public final int getTransitions()
                {
/* 420*/            return transitions;
                }

                public final String toString()
                {
/* 425*/            return (new StringBuilder()).append(from).append("/").append(to).append('/').append(transitions).toString();
                }

                private final ResultPoint from;
                private final ResultPoint to;
                private final int transitions;

                private ResultPointsAndTransitions(ResultPoint resultpoint, ResultPoint resultpoint1, int i)
                {
/* 406*/            from = resultpoint;
/* 407*/            to = resultpoint1;
/* 408*/            transitions = i;
                }

    }


            public Detector(BitMatrix bitmatrix)
                throws NotFoundException
            {
/*  47*/        image = bitmatrix;
/*  48*/        rectangleDetector = new WhiteRectangleDetector(bitmatrix);
            }

            public final DetectorResult detect()
                throws NotFoundException
            {
                ResultPoint aresultpoint[];
/*  59*/        ResultPoint resultpoint1 = (aresultpoint = rectangleDetector.detect())[0];
/*  61*/        ResultPoint resultpoint2 = aresultpoint[1];
/*  62*/        ResultPoint resultpoint3 = aresultpoint[2];
/*  63*/        ResultPoint resultpoint = aresultpoint[3];
                Object obj;
/*  68*/        ((List) (obj = new ArrayList(4))).add(transitionsBetween(resultpoint1, resultpoint2));
/*  70*/        ((List) (obj)).add(transitionsBetween(resultpoint1, resultpoint3));
/*  71*/        ((List) (obj)).add(transitionsBetween(resultpoint2, resultpoint));
/*  72*/        ((List) (obj)).add(transitionsBetween(resultpoint3, resultpoint));
/*  73*/        Collections.sort(((List) (obj)), new ResultPointsAndTransitionsComparator());
/*  77*/        Object obj1 = (ResultPointsAndTransitions)((List) (obj)).get(0);
/*  78*/        obj = (ResultPointsAndTransitions)((List) (obj)).get(1);
                HashMap hashmap;
/*  82*/        increment(hashmap = new HashMap(), ((ResultPointsAndTransitions) (obj1)).getFrom());
/*  84*/        increment(hashmap, ((ResultPointsAndTransitions) (obj1)).getTo());
/*  85*/        increment(hashmap, ((ResultPointsAndTransitions) (obj)).getFrom());
/*  86*/        increment(hashmap, ((ResultPointsAndTransitions) (obj)).getTo());
/*  88*/        obj = null;
/*  89*/        obj1 = null;
/*  90*/        ResultPoint resultpoint4 = null;
/*  91*/        for(Iterator iterator = hashmap.entrySet().iterator(); iterator.hasNext();)
                {
                    java.util.Map.Entry entry;
/*  91*/            ResultPoint resultpoint6 = (ResultPoint)(entry = (java.util.Map.Entry)iterator.next()).getKey();
                    Integer integer;
/*  93*/            if((integer = (Integer)entry.getValue()).intValue() == 2)
/*  95*/                obj1 = resultpoint6;
/*  98*/            else
/*  98*/            if(obj == null)
/*  99*/                obj = resultpoint6;
/* 101*/            else
/* 101*/                resultpoint4 = resultpoint6;
                }

/* 106*/        if(obj == null || obj1 == null || resultpoint4 == null)
/* 107*/            throw NotFoundException.getNotFoundInstance();
                ResultPoint aresultpoint1[];
/* 111*/        ResultPoint.orderBestPatterns(aresultpoint1 = (new ResultPoint[] {
/* 111*/            obj, obj1, resultpoint4
                }));
/* 116*/        ResultPoint resultpoint5 = aresultpoint1[0];
/* 117*/        obj1 = aresultpoint1[1];
/* 118*/        ResultPoint resultpoint7 = aresultpoint1[2];
                ResultPoint resultpoint8;
/* 122*/        if(!hashmap.containsKey(resultpoint1))
/* 123*/            resultpoint8 = resultpoint1;
/* 124*/        else
/* 124*/        if(!hashmap.containsKey(resultpoint2))
/* 125*/            resultpoint8 = resultpoint2;
/* 126*/        else
/* 126*/        if(!hashmap.containsKey(resultpoint3))
/* 127*/            resultpoint8 = resultpoint3;
/* 129*/        else
/* 129*/            resultpoint8 = resultpoint;
/* 141*/        int i = transitionsBetween(resultpoint7, resultpoint8).getTransitions();
/* 142*/        int j = transitionsBetween(resultpoint5, resultpoint8).getTransitions();
/* 144*/        if((i & 1) == 1)
/* 146*/            i++;
/* 148*/        i += 2;
/* 150*/        if((j & 1) == 1)
/* 152*/            j++;
/* 154*/        j += 2;
/* 162*/        if(4 * i >= j * 7 || 4 * j >= i * 7)
                {
/* 165*/            if((resultpoint2 = correctTopRightRectangular(((ResultPoint) (obj1)), resultpoint5, resultpoint7, resultpoint8, i, j)) == null)
/* 168*/                resultpoint2 = resultpoint8;
/* 171*/            i = transitionsBetween(resultpoint7, resultpoint2).getTransitions();
/* 172*/            j = transitionsBetween(resultpoint5, resultpoint2).getTransitions();
/* 174*/            if((i & 1) == 1)
/* 176*/                i++;
/* 179*/            if((j & 1) == 1)
/* 181*/                j++;
/* 184*/            i = sampleGrid(image, resultpoint7, ((ResultPoint) (obj1)), resultpoint5, resultpoint2, i, j);
                } else
                {
/* 189*/            i = Math.min(j, i);
/* 191*/            if((resultpoint2 = correctTopRight(((ResultPoint) (obj1)), resultpoint5, resultpoint7, resultpoint8, i)) == null)
/* 193*/                resultpoint2 = resultpoint8;
/* 197*/            i = Math.max(transitionsBetween(resultpoint7, resultpoint2).getTransitions(), transitionsBetween(resultpoint5, resultpoint2).getTransitions());
/* 199*/            if((++i & 1) == 1)
/* 201*/                i++;
/* 204*/            i = sampleGrid(image, resultpoint7, ((ResultPoint) (obj1)), resultpoint5, resultpoint2, i, i);
                }
/* 213*/        return new DetectorResult(i, new ResultPoint[] {
/* 213*/            resultpoint7, obj1, resultpoint5, resultpoint2
                });
            }

            private ResultPoint correctTopRightRectangular(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i, int j)
            {
/* 227*/        float f = (float)distance(resultpoint, resultpoint1) / (float)i;
/* 228*/        float f1 = distance(resultpoint2, resultpoint3);
/* 229*/        float f2 = (resultpoint3.getX() - resultpoint2.getX()) / (float)f1;
/* 230*/        f1 = (resultpoint3.getY() - resultpoint2.getY()) / (float)f1;
/* 232*/        ResultPoint resultpoint4 = new ResultPoint(resultpoint3.getX() + f * f2, resultpoint3.getY() + f * f1);
/* 234*/        f = (float)distance(resultpoint, resultpoint2) / (float)j;
/* 235*/        f1 = distance(resultpoint1, resultpoint3);
/* 236*/        f2 = (resultpoint3.getX() - resultpoint1.getX()) / (float)f1;
/* 237*/        f1 = (resultpoint3.getY() - resultpoint1.getY()) / (float)f1;
/* 239*/        resultpoint = new ResultPoint(resultpoint3.getX() + f * f2, resultpoint3.getY() + f * f1);
/* 241*/        if(!isValid(resultpoint4))
/* 242*/            if(isValid(resultpoint))
/* 243*/                return resultpoint;
/* 245*/            else
/* 245*/                return null;
/* 247*/        if(!isValid(resultpoint))
/* 248*/            return resultpoint4;
/* 251*/        resultpoint3 = Math.abs(i - transitionsBetween(resultpoint2, resultpoint4).getTransitions()) + Math.abs(j - transitionsBetween(resultpoint1, resultpoint4).getTransitions());
/* 253*/        resultpoint1 = Math.abs(i - transitionsBetween(resultpoint2, resultpoint).getTransitions()) + Math.abs(j - transitionsBetween(resultpoint1, resultpoint).getTransitions());
/* 256*/        if(resultpoint3 <= resultpoint1)
/* 257*/            return resultpoint4;
/* 260*/        else
/* 260*/            return resultpoint;
            }

            private ResultPoint correctTopRight(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
            {
/* 273*/        float f = (float)distance(resultpoint, resultpoint1) / (float)i;
/* 274*/        float f1 = distance(resultpoint2, resultpoint3);
/* 275*/        float f2 = (resultpoint3.getX() - resultpoint2.getX()) / (float)f1;
/* 276*/        f1 = (resultpoint3.getY() - resultpoint2.getY()) / (float)f1;
/* 278*/        ResultPoint resultpoint4 = new ResultPoint(resultpoint3.getX() + f * f2, resultpoint3.getY() + f * f1);
/* 280*/        f = (float)distance(resultpoint, resultpoint2) / (float)i;
/* 281*/        f1 = distance(resultpoint1, resultpoint3);
/* 282*/        f2 = (resultpoint3.getX() - resultpoint1.getX()) / (float)f1;
/* 283*/        f1 = (resultpoint3.getY() - resultpoint1.getY()) / (float)f1;
/* 285*/        resultpoint = new ResultPoint(resultpoint3.getX() + f * f2, resultpoint3.getY() + f * f1);
/* 287*/        if(!isValid(resultpoint4))
/* 288*/            if(isValid(resultpoint))
/* 289*/                return resultpoint;
/* 291*/            else
/* 291*/                return null;
/* 293*/        if(!isValid(resultpoint))
/* 294*/            return resultpoint4;
/* 297*/        resultpoint3 = Math.abs(transitionsBetween(resultpoint2, resultpoint4).getTransitions() - transitionsBetween(resultpoint1, resultpoint4).getTransitions());
/* 299*/        resultpoint1 = Math.abs(transitionsBetween(resultpoint2, resultpoint).getTransitions() - transitionsBetween(resultpoint1, resultpoint).getTransitions());
/* 302*/        if(resultpoint3 <= resultpoint1)
/* 302*/            return resultpoint4;
/* 302*/        else
/* 302*/            return resultpoint;
            }

            private boolean isValid(ResultPoint resultpoint)
            {
/* 306*/        return resultpoint.getX() >= 0.0F && resultpoint.getX() < (float)image.getWidth() && resultpoint.getY() > 0.0F && resultpoint.getY() < (float)image.getHeight();
            }

            private static int distance(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 310*/        return MathUtils.round(ResultPoint.distance(resultpoint, resultpoint1));
            }

            private static void increment(Map map, ResultPoint resultpoint)
            {
/* 317*/        Integer integer = (Integer)map.get(resultpoint);
/* 318*/        map.put(resultpoint, Integer.valueOf(integer != null ? integer.intValue() + 1 : 1));
            }

            private static BitMatrix sampleGrid(BitMatrix bitmatrix, ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i, int j)
                throws NotFoundException
            {
                GridSampler gridsampler;
/* 329*/        return (gridsampler = GridSampler.getInstance()).sampleGrid(bitmatrix, i, j, 0.5F, 0.5F, (float)i - 0.5F, 0.5F, (float)i - 0.5F, (float)j - 0.5F, 0.5F, (float)j - 0.5F, resultpoint.getX(), resultpoint.getY(), resultpoint3.getX(), resultpoint3.getY(), resultpoint2.getX(), resultpoint2.getY(), resultpoint1.getX(), resultpoint1.getY());
            }

            private ResultPointsAndTransitions transitionsBetween(ResultPoint resultpoint, ResultPoint resultpoint1)
            {
/* 357*/        int i = (int)resultpoint.getX();
/* 358*/        int j = (int)resultpoint.getY();
/* 359*/        int k = (int)resultpoint1.getX();
                int l;
                boolean flag;
/* 360*/        if(flag = Math.abs((l = (int)resultpoint1.getY()) - j) > Math.abs(k - i))
                {
/* 363*/            int i1 = i;
/* 364*/            i = j;
/* 365*/            j = i1;
/* 366*/            i1 = k;
/* 367*/            k = l;
/* 368*/            l = i1;
                }
/* 371*/        int j1 = Math.abs(k - i);
/* 372*/        int k1 = Math.abs(l - j);
/* 373*/        int l1 = -j1 >> 1;
/* 374*/        byte byte0 = ((byte)(j >= l ? -1 : 1));
/* 375*/        byte byte1 = ((byte)(i >= k ? -1 : 1));
/* 376*/        int i2 = 0;
/* 377*/        boolean flag1 = image.get(flag ? j : i, flag ? i : j);
/* 378*/        i = i;
/* 378*/        j = j;
/* 378*/        for(; i != k; i += byte1)
                {
                    boolean flag2;
/* 379*/            if((flag2 = image.get(flag ? j : i, flag ? i : j)) != flag1)
                    {
/* 381*/                i2++;
/* 382*/                flag1 = flag2;
                    }
/* 384*/            if((l1 += k1) <= 0)
/* 386*/                continue;
/* 386*/            if(j == l)
/* 389*/                break;
/* 389*/            j += byte0;
/* 390*/            l1 -= j1;
                }

/* 393*/        return new ResultPointsAndTransitions(resultpoint, resultpoint1, i2);
            }

            private final BitMatrix image;
            private final WhiteRectangleDetector rectangleDetector;
}
