// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFinderPatternFinder.java

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.*;
import java.io.Serializable;
import java.util.*;

final class MultiFinderPatternFinder extends FinderPatternFinder
{
    static final class ModuleSizeComparator
        implements Serializable, Comparator
    {

                public final int compare(FinderPattern finderpattern, FinderPattern finderpattern1)
                {
/*  82*/            if((double)(finderpattern = finderpattern1.getEstimatedModuleSize() - finderpattern.getEstimatedModuleSize()) < 0.0D)
/*  83*/                return -1;
/*  83*/            return (double)finderpattern <= 0.0D ? 0 : 1;
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/*  79*/            return compare((FinderPattern)obj, (FinderPattern)obj1);
                }

                private ModuleSizeComparator()
                {
                }

    }


            MultiFinderPatternFinder(BitMatrix bitmatrix)
            {
/*  93*/        super(bitmatrix);
            }

            MultiFinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
            {
/*  97*/        super(bitmatrix, resultpointcallback);
            }

            private FinderPattern[][] selectMutipleBestPatterns()
                throws NotFoundException
            {
                List list;
                int i;
/* 107*/        if((i = (list = getPossibleCenters()).size()) < 3)
/* 112*/            throw NotFoundException.getNotFoundInstance();
/* 118*/        if(i == 3)
/* 119*/            return (new FinderPattern[][] {
/* 119*/                new FinderPattern[] {
/* 119*/                    (FinderPattern)list.get(0), (FinderPattern)list.get(1), (FinderPattern)list.get(2)
                        }
                    });
/* 129*/        Collections.sort(list, new ModuleSizeComparator());
/* 146*/        ArrayList arraylist = new ArrayList();
/* 148*/        for(int j = 0; j < i - 2; j++)
                {
                    FinderPattern finderpattern;
/* 149*/            if((finderpattern = (FinderPattern)list.get(j)) == null)
/* 154*/                continue;
/* 154*/            for(int k = j + 1; k < i - 1; k++)
                    {
                        FinderPattern finderpattern1;
/* 155*/                if((finderpattern1 = (FinderPattern)list.get(k)) == null)
/* 161*/                    continue;
/* 161*/                float f = (finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) / Math.min(finderpattern.getEstimatedModuleSize(), finderpattern1.getEstimatedModuleSize());
                        float f1;
/* 163*/                if((f1 = Math.abs(finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize())) > 0.5F && f >= 0.05F)
/* 170*/                    break;
/* 170*/                for(int l = k + 1; l < i; l++)
                        {
                            FinderPattern finderpattern2;
/* 171*/                    if((finderpattern2 = (FinderPattern)list.get(l)) == null)
/* 177*/                        continue;
/* 177*/                    float f2 = (finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) / Math.min(finderpattern1.getEstimatedModuleSize(), finderpattern2.getEstimatedModuleSize());
                            float f3;
/* 179*/                    if((f3 = Math.abs(finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize())) > 0.5F && f2 >= 0.05F)
/* 186*/                        break;
                            FinderPattern afinderpattern[];
/* 186*/                    ResultPoint.orderBestPatterns(afinderpattern = (new FinderPattern[] {
/* 186*/                        finderpattern, finderpattern1, finderpattern2
                            }));
                            FinderPatternInfo finderpatterninfo;
/* 190*/                    f3 = ResultPoint.distance((finderpatterninfo = new FinderPatternInfo(afinderpattern)).getTopLeft(), finderpatterninfo.getBottomLeft());
/* 192*/                    float f4 = ResultPoint.distance(finderpatterninfo.getTopRight(), finderpatterninfo.getBottomLeft());
/* 193*/                    finderpatterninfo = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getTopRight());
                            float f5;
/* 196*/                    if((f5 = (f3 + finderpatterninfo) / (finderpattern.getEstimatedModuleSize() * 2.0F)) > 180F || f5 < 9F || (f5 = Math.abs((f3 - finderpatterninfo) / Math.min(f3, finderpatterninfo))) >= 0.1F)
/* 209*/                        continue;
/* 209*/                    finderpatterninfo = (float)Math.sqrt(f3 * f3 + finderpatterninfo * finderpatterninfo);
/* 211*/                    if((finderpatterninfo = Math.abs((f4 - finderpatterninfo) / Math.min(f4, finderpatterninfo))) < 0.1F)
/* 218*/                        arraylist.add(afinderpattern);
                        }

                    }

                }

/* 223*/        if(!arraylist.isEmpty())
/* 224*/            return (FinderPattern[][])arraylist.toArray(new FinderPattern[arraylist.size()][]);
/* 228*/        else
/* 228*/            throw NotFoundException.getNotFoundInstance();
            }

            public final FinderPatternInfo[] findMulti(Map map)
                throws NotFoundException
            {
/* 232*/        boolean flag = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
/* 233*/        map = map == null || !map.containsKey(DecodeHintType.PURE_BARCODE) ? 0 : 1;
                BitMatrix bitmatrix;
/* 234*/        int j = (bitmatrix = getImage()).getHeight();
/* 236*/        int k = bitmatrix.getWidth();
                int l;
/* 244*/        if((l = (int)(((float)j / 228F) * 3F)) < 3 || flag)
/* 246*/            l = 3;
/* 249*/        int ai[] = new int[5];
/* 250*/        for(int i1 = l - 1; i1 < j; i1 += l)
                {
/* 252*/            ai[0] = 0;
/* 253*/            ai[1] = 0;
/* 254*/            ai[2] = 0;
/* 255*/            ai[3] = 0;
/* 256*/            ai[4] = 0;
/* 257*/            int j1 = 0;
/* 258*/            for(int k1 = 0; k1 < k; k1++)
                    {
/* 259*/                if(bitmatrix.get(k1, i1))
                        {
/* 261*/                    if((j1 & 1) == 1)
/* 262*/                        j1++;
/* 264*/                    ai[j1]++;
/* 264*/                    continue;
                        }
/* 266*/                if((j1 & 1) == 0)
                        {
/* 267*/                    if(j1 == 4)
                            {
/* 268*/                        if(foundPatternCross(ai) && handlePossibleCenter(ai, i1, k1, map))
                                {
/* 270*/                            j1 = 0;
/* 271*/                            ai[0] = 0;
/* 272*/                            ai[1] = 0;
/* 273*/                            ai[2] = 0;
/* 274*/                            ai[3] = 0;
/* 275*/                            ai[4] = 0;
                                } else
                                {
/* 277*/                            ai[0] = ai[2];
/* 278*/                            ai[1] = ai[3];
/* 279*/                            ai[2] = ai[4];
/* 280*/                            ai[3] = 1;
/* 281*/                            ai[4] = 0;
/* 282*/                            j1 = 3;
                                }
                            } else
                            {
/* 285*/                        ai[++j1]++;
                            }
                        } else
                        {
/* 288*/                    ai[j1]++;
                        }
                    }

/* 293*/            if(foundPatternCross(ai))
/* 294*/                handlePossibleCenter(ai, i1, k, map);
                }

/* 297*/        FinderPattern afinderpattern1[][] = selectMutipleBestPatterns();
/* 298*/        ArrayList arraylist = new ArrayList();
                FinderPattern afinderpattern2[][];
/* 299*/        map = (afinderpattern2 = afinderpattern1).length;
/* 299*/        for(int i = 0; i < map; i++)
                {
                    FinderPattern afinderpattern[];
/* 299*/            ResultPoint.orderBestPatterns(afinderpattern = afinderpattern2[i]);
/* 301*/            arraylist.add(new FinderPatternInfo(afinderpattern));
                }

/* 304*/        if(arraylist.isEmpty())
/* 305*/            return EMPTY_RESULT_ARRAY;
/* 307*/        else
/* 307*/            return (FinderPatternInfo[])arraylist.toArray(new FinderPatternInfo[arraylist.size()]);
            }

            private static final FinderPatternInfo EMPTY_RESULT_ARRAY[] = new FinderPatternInfo[0];
            private static final float MAX_MODULE_COUNT_PER_EDGE = 180F;
            private static final float MIN_MODULE_COUNT_PER_EDGE = 9F;
            private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05F;
            private static final float DIFF_MODSIZE_CUTOFF = 0.5F;

}
