// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AlignmentPatternFinder.java

package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.*;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            AlignmentPattern

final class AlignmentPatternFinder
{

            AlignmentPatternFinder(BitMatrix bitmatrix, int i, int j, int k, int l, float f, ResultPointCallback resultpointcallback)
            {
/*  69*/        image = bitmatrix;
/*  71*/        startX = i;
/*  72*/        startY = j;
/*  73*/        width = k;
/*  74*/        height = l;
/*  75*/        moduleSize = f;
/*  77*/        resultPointCallback = resultpointcallback;
            }

            final AlignmentPattern find()
                throws NotFoundException
            {
/*  88*/        int i = startX;
/*  89*/        int j = height;
/*  90*/        int k = i + width;
/*  91*/        int l = startY + (j >> 1);
/*  94*/        int ai[] = new int[3];
/*  95*/        for(int i1 = 0; i1 < j; i1++)
                {
/*  97*/            int j1 = l + ((i1 & 1) != 0 ? -(i1 + 1 >> 1) : i1 + 1 >> 1);
/*  98*/            ai[0] = 0;
/*  99*/            ai[1] = 0;
/* 100*/            ai[2] = 0;
                    int k1;
/* 101*/            for(k1 = i; k1 < k && !image.get(k1, j1); k1++);
/* 108*/            int l1 = 0;
/* 109*/            for(; k1 < k; k1++)
                    {
/* 110*/                if(image.get(k1, j1))
                        {
/* 112*/                    if(l1 != 1)
                            {
/* 115*/                        if(l1 == 2)
                                {
/* 116*/                            if(foundPatternCross(ai) && (l1 = handlePossibleCenter(ai, j1, k1)) != null)
/* 119*/                                return l1;
/* 122*/                            ai[0] = ai[2];
/* 123*/                            ai[1] = 1;
/* 124*/                            ai[2] = 0;
/* 125*/                            l1 = 1;
                                } else
                                {
/* 127*/                            ai[++l1]++;
                                }
/* 127*/                        continue;
                            }
                        } else
/* 131*/                if(l1 == 1)
/* 132*/                    l1++;
/* 134*/                ai[l1]++;
                    }

                    AlignmentPattern alignmentpattern;
/* 138*/            if(foundPatternCross(ai) && (alignmentpattern = handlePossibleCenter(ai, j1, k)) != null)
/* 141*/                return alignmentpattern;
                }

/* 149*/        if(!possibleCenters.isEmpty())
/* 150*/            return (AlignmentPattern)possibleCenters.get(0);
/* 153*/        else
/* 153*/            throw NotFoundException.getNotFoundInstance();
            }

            private static float centerFromEnd(int ai[], int i)
            {
/* 161*/        return (float)(i - ai[2]) - (float)ai[1] / 2.0F;
            }

            private boolean foundPatternCross(int ai[])
            {
                float f;
/* 170*/        float f1 = (f = moduleSize) / 2.0F;
/* 172*/        for(int i = 0; i < 3; i++)
/* 173*/            if(Math.abs(f - (float)ai[i]) >= f1)
/* 174*/                return false;

/* 177*/        return true;
            }

            private float crossCheckVertical(int i, int j, int k, int l)
            {
                BitMatrix bitmatrix;
/* 193*/        int i1 = (bitmatrix = image).getHeight();
                int ai[];
/* 196*/        (ai = crossCheckStateCount)[0] = 0;
/* 198*/        ai[1] = 0;
/* 199*/        ai[2] = 0;
                int j1;
/* 202*/        for(j1 = i; j1 >= 0 && bitmatrix.get(j, j1) && ai[1] <= k; j1--)
/* 204*/            ai[1]++;

/* 208*/        if(j1 < 0 || ai[1] > k)
/* 209*/            return (0.0F / 0.0F);
/* 211*/        for(; j1 >= 0 && !bitmatrix.get(j, j1) && ai[0] <= k; j1--)
/* 212*/            ai[0]++;

/* 215*/        if(ai[0] > k)
/* 216*/            return (0.0F / 0.0F);
/* 220*/        for(j1 = i + 1; j1 < i1 && bitmatrix.get(j, j1) && ai[1] <= k; j1++)
/* 222*/            ai[1]++;

/* 225*/        if(j1 == i1 || ai[1] > k)
/* 226*/            return (0.0F / 0.0F);
/* 228*/        for(; j1 < i1 && !bitmatrix.get(j, j1) && ai[2] <= k; j1++)
/* 229*/            ai[2]++;

/* 232*/        if(ai[2] > k)
/* 233*/            return (0.0F / 0.0F);
/* 236*/        i = ai[0] + ai[1] + ai[2];
/* 237*/        if(5 * Math.abs(i - l) >= 2 * l)
/* 238*/            return (0.0F / 0.0F);
/* 241*/        if(foundPatternCross(ai))
/* 241*/            return centerFromEnd(ai, j1);
/* 241*/        else
/* 241*/            return (0.0F / 0.0F);
            }

            private AlignmentPattern handlePossibleCenter(int ai[], int i, int j)
            {
/* 256*/        int k = ai[0] + ai[1] + ai[2];
/* 257*/        j = centerFromEnd(ai, j);
/* 258*/        if(!Float.isNaN(i = crossCheckVertical(i, (int)j, 2 * ai[1], k)))
                {
/* 260*/            ai = (float)(ai[0] + ai[1] + ai[2]) / 3F;
                    AlignmentPattern alignmentpattern1;
/* 261*/            for(Iterator iterator = possibleCenters.iterator(); iterator.hasNext();)
/* 261*/                if((alignmentpattern1 = (AlignmentPattern)iterator.next()).aboutEquals(ai, i, j))
/* 264*/                    return alignmentpattern1.combineEstimate(i, j, ai);

/* 268*/            AlignmentPattern alignmentpattern = new AlignmentPattern(j, i, ai);
/* 269*/            possibleCenters.add(alignmentpattern);
/* 270*/            if(resultPointCallback != null)
/* 271*/                resultPointCallback.foundPossibleResultPoint(alignmentpattern);
                }
/* 274*/        return null;
            }

            private final BitMatrix image;
            private final List possibleCenters = new ArrayList(5);
            private final int startX;
            private final int startY;
            private final int width;
            private final int height;
            private final float moduleSize;
            private final int crossCheckStateCount[] = new int[3];
            private final ResultPointCallback resultPointCallback;
}
