// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FinderPatternFinder.java

package com.google.zxing.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern, FinderPatternInfo

public class FinderPatternFinder
{
    static final class CenterComparator
        implements Serializable, Comparator
    {

                public final int compare(FinderPattern finderpattern, FinderPattern finderpattern1)
                {
/* 664*/            if(finderpattern1.getCount() == finderpattern.getCount())
                    {
/* 665*/                finderpattern1 = Math.abs(finderpattern1.getEstimatedModuleSize() - average);
/* 666*/                finderpattern = Math.abs(finderpattern.getEstimatedModuleSize() - average);
/* 667*/                if(finderpattern1 < finderpattern)
/* 667*/                    return 1;
/* 667*/                return finderpattern1 != finderpattern ? -1 : 0;
                    } else
                    {
/* 669*/                return finderpattern1.getCount() - finderpattern.getCount();
                    }
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/* 657*/            return compare((FinderPattern)obj, (FinderPattern)obj1);
                }

                private final float average;

                private CenterComparator(float f)
                {
/* 660*/            average = f;
                }

    }

    static final class FurthestFromAverageComparator
        implements Serializable, Comparator
    {

                public final int compare(FinderPattern finderpattern, FinderPattern finderpattern1)
                {
/* 648*/            finderpattern1 = Math.abs(finderpattern1.getEstimatedModuleSize() - average);
/* 649*/            finderpattern = Math.abs(finderpattern.getEstimatedModuleSize() - average);
/* 650*/            if(finderpattern1 < finderpattern)
/* 650*/                return -1;
/* 650*/            return finderpattern1 != finderpattern ? 1 : 0;
                }

                public final volatile int compare(Object obj, Object obj1)
                {
/* 641*/            return compare((FinderPattern)obj, (FinderPattern)obj1);
                }

                private final float average;

                private FurthestFromAverageComparator(float f)
                {
/* 644*/            average = f;
                }

    }


            public FinderPatternFinder(BitMatrix bitmatrix)
            {
/*  59*/        this(bitmatrix, null);
            }

            public FinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
            {
/*  63*/        image = bitmatrix;
/*  64*/        possibleCenters = new ArrayList();
/*  65*/        crossCheckStateCount = new int[5];
/*  66*/        resultPointCallback = resultpointcallback;
            }

            protected final BitMatrix getImage()
            {
/*  70*/        return image;
            }

            protected final List getPossibleCenters()
            {
/*  74*/        return possibleCenters;
            }

            final FinderPatternInfo find(Map map)
                throws NotFoundException
            {
/*  78*/        boolean flag = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
/*  79*/        map = map == null || !map.containsKey(DecodeHintType.PURE_BARCODE) ? 0 : 1;
/*  80*/        int i = image.getHeight();
/*  81*/        int j = image.getWidth();
                int k;
/*  89*/        if((k = (3 * i) / 228) < 3 || flag)
/*  91*/            k = 3;
/*  94*/        flag = false;
/*  95*/        int ai[] = new int[5];
/*  96*/        for(int l = k - 1; l < i && !flag; l += k)
                {
/*  98*/            ai[0] = 0;
/*  99*/            ai[1] = 0;
/* 100*/            ai[2] = 0;
/* 101*/            ai[3] = 0;
/* 102*/            ai[4] = 0;
/* 103*/            int i1 = 0;
/* 104*/            for(int j1 = 0; j1 < j; j1++)
                    {
/* 105*/                if(image.get(j1, l))
                        {
/* 107*/                    if((i1 & 1) == 1)
/* 108*/                        i1++;
/* 110*/                    ai[i1]++;
/* 110*/                    continue;
                        }
/* 112*/                if((i1 & 1) == 0)
                        {
/* 113*/                    if(i1 == 4)
                            {
/* 114*/                        if(foundPatternCross(ai))
                                {
/* 115*/                            if((i1 = handlePossibleCenter(ai, l, j1, map)) != 0)
                                    {
/* 119*/                                k = 2;
/* 120*/                                if(hasSkipped)
/* 121*/                                    flag = haveMultiplyConfirmedCenters();
/* 123*/                                else
/* 123*/                                if((i1 = findRowSkip()) > ai[2])
                                        {
/* 133*/                                    l += i1 - ai[2] - 2;
/* 134*/                                    j1 = j - 1;
                                        }
                                    } else
                                    {
/* 138*/                                ai[0] = ai[2];
/* 139*/                                ai[1] = ai[3];
/* 140*/                                ai[2] = ai[4];
/* 141*/                                ai[3] = 1;
/* 142*/                                ai[4] = 0;
/* 143*/                                i1 = 3;
/* 144*/                                continue;
                                    }
/* 147*/                            i1 = 0;
/* 148*/                            ai[0] = 0;
/* 149*/                            ai[1] = 0;
/* 150*/                            ai[2] = 0;
/* 151*/                            ai[3] = 0;
/* 152*/                            ai[4] = 0;
                                } else
                                {
/* 154*/                            ai[0] = ai[2];
/* 155*/                            ai[1] = ai[3];
/* 156*/                            ai[2] = ai[4];
/* 157*/                            ai[3] = 1;
/* 158*/                            ai[4] = 0;
/* 159*/                            i1 = 3;
                                }
                            } else
                            {
/* 162*/                        ai[++i1]++;
                            }
                        } else
                        {
/* 165*/                    ai[i1]++;
                        }
                    }

                    boolean flag1;
/* 169*/            if(!foundPatternCross(ai) || !(flag1 = handlePossibleCenter(ai, l, j, map)))
/* 172*/                continue;
/* 172*/            k = ai[0];
/* 173*/            if(hasSkipped)
/* 175*/                flag = haveMultiplyConfirmedCenters();
                }

                FinderPattern afinderpattern[];
/* 181*/        ResultPoint.orderBestPatterns(afinderpattern = selectBestPatterns());
/* 184*/        return new FinderPatternInfo(afinderpattern);
            }

            private static float centerFromEnd(int ai[], int i)
            {
/* 192*/        return (float)(i - ai[4] - ai[3]) - (float)ai[2] / 2.0F;
            }

            protected static boolean foundPatternCross(int ai[])
            {
/* 201*/        int i = 0;
/* 202*/        for(int j = 0; j < 5; j++)
                {
                    int l;
/* 203*/            if((l = ai[j]) == 0)
/* 205*/                return false;
/* 207*/            i += l;
                }

/* 209*/        if(i < 7)
/* 210*/            return false;
                int k;
/* 212*/        int i1 = (k = (i << 8) / 7) / 2;
/* 215*/        return Math.abs(k - (ai[0] << 8)) < i1 && Math.abs(k - (ai[1] << 8)) < i1 && Math.abs(3 * k - (ai[2] << 8)) < 3 * i1 && Math.abs(k - (ai[3] << 8)) < i1 && Math.abs(k - (ai[4] << 8)) < i1;
            }

            private int[] getCrossCheckStateCount()
            {
/* 223*/        crossCheckStateCount[0] = 0;
/* 224*/        crossCheckStateCount[1] = 0;
/* 225*/        crossCheckStateCount[2] = 0;
/* 226*/        crossCheckStateCount[3] = 0;
/* 227*/        crossCheckStateCount[4] = 0;
/* 228*/        return crossCheckStateCount;
            }

            private boolean crossCheckDiagonal(int i, int j, int k, int l)
            {
/* 244*/        int i1 = image.getHeight();
/* 245*/        int j1 = image.getWidth();
/* 246*/        int ai[] = getCrossCheckStateCount();
                int k1;
/* 249*/        for(k1 = 0; i - k1 >= 0 && image.get(j - k1, i - k1); k1++)
/* 251*/            ai[2]++;

/* 255*/        if(i - k1 < 0 || j - k1 < 0)
/* 256*/            return false;
/* 260*/        for(; i - k1 >= 0 && j - k1 >= 0 && !image.get(j - k1, i - k1) && ai[1] <= k; k1++)
/* 261*/            ai[1]++;

/* 266*/        if(i - k1 < 0 || j - k1 < 0 || ai[1] > k)
/* 267*/            return false;
/* 271*/        for(; i - k1 >= 0 && j - k1 >= 0 && image.get(j - k1, i - k1) && ai[0] <= k; k1++)
/* 272*/            ai[0]++;

/* 275*/        if(ai[0] > k)
/* 276*/            return false;
/* 280*/        for(k1 = 1; i + k1 < i1 && j + k1 < j1 && image.get(j + k1, i + k1); k1++)
/* 282*/            ai[2]++;

/* 287*/        if(i + k1 >= i1 || j + k1 >= j1)
/* 288*/            return false;
/* 291*/        for(; i + k1 < i1 && j + k1 < j1 && !image.get(j + k1, i + k1) && ai[3] < k; k1++)
/* 292*/            ai[3]++;

/* 296*/        if(i + k1 >= i1 || j + k1 >= j1 || ai[3] >= k)
/* 297*/            return false;
/* 300*/        for(; i + k1 < i1 && j + k1 < j1 && image.get(j + k1, i + k1) && ai[4] < k; k1++)
/* 301*/            ai[4]++;

/* 305*/        if(ai[4] >= k)
/* 306*/            return false;
/* 311*/        return Math.abs((i = ai[0] + ai[1] + ai[2] + ai[3] + ai[4]) - l) < 2 * l && foundPatternCross(ai);
            }

            private float crossCheckVertical(int i, int j, int k, int l)
            {
                BitMatrix bitmatrix;
/* 330*/        int i1 = (bitmatrix = image).getHeight();
/* 333*/        int ai[] = getCrossCheckStateCount();
                int j1;
/* 336*/        for(j1 = i; j1 >= 0 && bitmatrix.get(j, j1); j1--)
/* 338*/            ai[2]++;

/* 341*/        if(j1 < 0)
/* 342*/            return (0.0F / 0.0F);
/* 344*/        for(; j1 >= 0 && !bitmatrix.get(j, j1) && ai[1] <= k; j1--)
/* 345*/            ai[1]++;

/* 349*/        if(j1 < 0 || ai[1] > k)
/* 350*/            return (0.0F / 0.0F);
/* 352*/        for(; j1 >= 0 && bitmatrix.get(j, j1) && ai[0] <= k; j1--)
/* 353*/            ai[0]++;

/* 356*/        if(ai[0] > k)
/* 357*/            return (0.0F / 0.0F);
/* 361*/        for(j1 = i + 1; j1 < i1 && bitmatrix.get(j, j1); j1++)
/* 363*/            ai[2]++;

/* 366*/        if(j1 == i1)
/* 367*/            return (0.0F / 0.0F);
/* 369*/        for(; j1 < i1 && !bitmatrix.get(j, j1) && ai[3] < k; j1++)
/* 370*/            ai[3]++;

/* 373*/        if(j1 == i1 || ai[3] >= k)
/* 374*/            return (0.0F / 0.0F);
/* 376*/        for(; j1 < i1 && bitmatrix.get(j, j1) && ai[4] < k; j1++)
/* 377*/            ai[4]++;

/* 380*/        if(ai[4] >= k)
/* 381*/            return (0.0F / 0.0F);
/* 386*/        i = ai[0] + ai[1] + ai[2] + ai[3] + ai[4];
/* 388*/        if(5 * Math.abs(i - l) >= 2 * l)
/* 389*/            return (0.0F / 0.0F);
/* 392*/        if(foundPatternCross(ai))
/* 392*/            return centerFromEnd(ai, j1);
/* 392*/        else
/* 392*/            return (0.0F / 0.0F);
            }

            private float crossCheckHorizontal(int i, int j, int k, int l)
            {
                BitMatrix bitmatrix;
/* 402*/        int i1 = (bitmatrix = image).getWidth();
/* 405*/        int ai[] = getCrossCheckStateCount();
                int j1;
/* 407*/        for(j1 = i; j1 >= 0 && bitmatrix.get(j1, j); j1--)
/* 409*/            ai[2]++;

/* 412*/        if(j1 < 0)
/* 413*/            return (0.0F / 0.0F);
/* 415*/        for(; j1 >= 0 && !bitmatrix.get(j1, j) && ai[1] <= k; j1--)
/* 416*/            ai[1]++;

/* 419*/        if(j1 < 0 || ai[1] > k)
/* 420*/            return (0.0F / 0.0F);
/* 422*/        for(; j1 >= 0 && bitmatrix.get(j1, j) && ai[0] <= k; j1--)
/* 423*/            ai[0]++;

/* 426*/        if(ai[0] > k)
/* 427*/            return (0.0F / 0.0F);
/* 430*/        for(j1 = i + 1; j1 < i1 && bitmatrix.get(j1, j); j1++)
/* 432*/            ai[2]++;

/* 435*/        if(j1 == i1)
/* 436*/            return (0.0F / 0.0F);
/* 438*/        for(; j1 < i1 && !bitmatrix.get(j1, j) && ai[3] < k; j1++)
/* 439*/            ai[3]++;

/* 442*/        if(j1 == i1 || ai[3] >= k)
/* 443*/            return (0.0F / 0.0F);
/* 445*/        for(; j1 < i1 && bitmatrix.get(j1, j) && ai[4] < k; j1++)
/* 446*/            ai[4]++;

/* 449*/        if(ai[4] >= k)
/* 450*/            return (0.0F / 0.0F);
/* 455*/        i = ai[0] + ai[1] + ai[2] + ai[3] + ai[4];
/* 457*/        if(5 * Math.abs(i - l) >= l)
/* 458*/            return (0.0F / 0.0F);
/* 461*/        if(foundPatternCross(ai))
/* 461*/            return centerFromEnd(ai, j1);
/* 461*/        else
/* 461*/            return (0.0F / 0.0F);
            }

            protected final boolean handlePossibleCenter(int ai[], int i, int j, boolean flag)
            {
/* 482*/        int k = ai[0] + ai[1] + ai[2] + ai[3] + ai[4];
/* 484*/        j = centerFromEnd(ai, j);
/* 485*/        if(!Float.isNaN(i = crossCheckVertical(i, (int)j, ai[2], k)) && !Float.isNaN(j = crossCheckHorizontal((int)j, (int)i, ai[2], k)) && (!flag || crossCheckDiagonal((int)i, (int)j, ai[2], k)))
                {
/* 491*/            ai = (float)k / 7F;
/* 492*/            flag = false;
/* 493*/            k = 0;
/* 493*/            do
                    {
/* 493*/                if(k >= possibleCenters.size())
/* 494*/                    break;
                        FinderPattern finderpattern1;
/* 494*/                if((finderpattern1 = (FinderPattern)possibleCenters.get(k)).aboutEquals(ai, i, j))
                        {
/* 497*/                    possibleCenters.set(k, finderpattern1.combineEstimate(i, j, ai));
/* 498*/                    flag = true;
/* 499*/                    break;
                        }
/* 493*/                k++;
                    } while(true);
/* 502*/            if(!flag)
                    {
/* 503*/                FinderPattern finderpattern = new FinderPattern(j, i, ai);
/* 504*/                possibleCenters.add(finderpattern);
/* 505*/                if(resultPointCallback != null)
/* 506*/                    resultPointCallback.foundPossibleResultPoint(finderpattern);
                    }
/* 509*/            return true;
                } else
                {
/* 512*/            return false;
                }
            }

            private int findRowSkip()
            {
                int i;
/* 522*/        if((i = possibleCenters.size()) <= 1)
/* 524*/            return 0;
/* 526*/        FinderPattern finderpattern = null;
/* 527*/        Iterator iterator = possibleCenters.iterator();
/* 527*/        do
                {
/* 527*/            if(!iterator.hasNext())
/* 527*/                break;
                    FinderPattern finderpattern1;
/* 527*/            if((finderpattern1 = (FinderPattern)iterator.next()).getCount() >= 2)
/* 529*/                if(finderpattern == null)
                        {
/* 530*/                    finderpattern = finderpattern1;
                        } else
                        {
/* 537*/                    hasSkipped = true;
/* 538*/                    return (int)(Math.abs(finderpattern.getX() - finderpattern1.getX()) - Math.abs(finderpattern.getY() - finderpattern1.getY())) / 2;
                        }
                } while(true);
/* 543*/        return 0;
            }

            private boolean haveMultiplyConfirmedCenters()
            {
/* 552*/        int i = 0;
/* 553*/        float f = 0.0F;
/* 554*/        int j = possibleCenters.size();
/* 555*/        Iterator iterator1 = possibleCenters.iterator();
/* 555*/        do
                {
/* 555*/            if(!iterator1.hasNext())
/* 555*/                break;
                    FinderPattern finderpattern1;
/* 555*/            if((finderpattern1 = (FinderPattern)iterator1.next()).getCount() >= 2)
                    {
/* 557*/                i++;
/* 558*/                f += finderpattern1.getEstimatedModuleSize();
                    }
                } while(true);
/* 561*/        if(i < 3)
/* 562*/            return false;
/* 568*/        float f1 = f / (float)j;
/* 569*/        float f2 = 0.0F;
/* 570*/        for(Iterator iterator = possibleCenters.iterator(); iterator.hasNext();)
                {
/* 570*/            FinderPattern finderpattern = (FinderPattern)iterator.next();
/* 571*/            f2 += Math.abs(finderpattern.getEstimatedModuleSize() - f1);
                }

/* 573*/        return f2 <= 0.05F * f;
            }

            private FinderPattern[] selectBestPatterns()
                throws NotFoundException
            {
                int i;
/* 584*/        if((i = possibleCenters.size()) < 3)
/* 587*/            throw NotFoundException.getNotFoundInstance();
/* 591*/        if(i > 3)
                {
/* 593*/            float f = 0.0F;
/* 594*/            float f2 = 0.0F;
/* 595*/            for(Iterator iterator1 = possibleCenters.iterator(); iterator1.hasNext();)
                    {
                        FinderPattern finderpattern2;
/* 595*/                float f5 = (finderpattern2 = (FinderPattern)iterator1.next()).getEstimatedModuleSize();
/* 597*/                f += f5;
/* 598*/                f2 += f5 * f5;
                    }

/* 600*/            float f4 = f / (float)i;
/* 601*/            float f6 = (float)Math.sqrt(f2 / (float)i - f4 * f4);
/* 603*/            Collections.sort(possibleCenters, new FurthestFromAverageComparator(f4));
/* 605*/            f6 = Math.max(0.2F * f4, f6);
/* 607*/            for(int j = 0; j < possibleCenters.size() && possibleCenters.size() > 3; j++)
                    {
                        FinderPattern finderpattern;
/* 608*/                if(Math.abs((finderpattern = (FinderPattern)possibleCenters.get(j)).getEstimatedModuleSize() - f4) > f6)
                        {
/* 610*/                    possibleCenters.remove(j);
/* 611*/                    j--;
                        }
                    }

                }
/* 616*/        if(possibleCenters.size() > 3)
                {
/* 619*/            float f1 = 0.0F;
/* 620*/            for(Iterator iterator = possibleCenters.iterator(); iterator.hasNext();)
                    {
/* 620*/                FinderPattern finderpattern1 = (FinderPattern)iterator.next();
/* 621*/                f1 += finderpattern1.getEstimatedModuleSize();
                    }

/* 624*/            float f3 = f1 / (float)possibleCenters.size();
/* 626*/            Collections.sort(possibleCenters, new CenterComparator(f3));
/* 628*/            possibleCenters.subList(3, possibleCenters.size()).clear();
                }
/* 631*/        return (new FinderPattern[] {
/* 631*/            (FinderPattern)possibleCenters.get(0), (FinderPattern)possibleCenters.get(1), (FinderPattern)possibleCenters.get(2)
                });
            }

            private static final int CENTER_QUORUM = 2;
            protected static final int MIN_SKIP = 3;
            protected static final int MAX_MODULES = 57;
            private static final int INTEGER_MATH_SHIFT = 8;
            private final BitMatrix image;
            private final List possibleCenters;
            private boolean hasSkipped;
            private final int crossCheckStateCount[];
            private final ResultPointCallback resultPointCallback;
}
