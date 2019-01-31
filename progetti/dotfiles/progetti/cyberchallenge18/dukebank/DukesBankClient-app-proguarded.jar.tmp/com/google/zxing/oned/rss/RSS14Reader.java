// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RSS14Reader.java

package com.google.zxing.oned.rss;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.oned.rss:
//            AbstractRSSReader, DataCharacter, FinderPattern, Pair, 
//            RSSUtils

public final class RSS14Reader extends AbstractRSSReader
{

            public RSS14Reader()
            {
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException
            {
/*  68*/        Pair pair = decodePair(bitarray, false, i, map);
/*  69*/        addOrTally(possibleLeftPairs, pair);
/*  70*/        bitarray.reverse();
/*  71*/        i = decodePair(bitarray, true, i, map);
/*  72*/        addOrTally(possibleRightPairs, i);
/*  73*/        bitarray.reverse();
/*  74*/        i = possibleLeftPairs.size();
/*  75*/label0:
/*  75*/        for(bitarray = 0; bitarray < i; bitarray++)
                {
/*  76*/            if((map = (Pair)possibleLeftPairs.get(bitarray)).getCount() <= 1)
/*  78*/                continue;
/*  78*/            int j = possibleRightPairs.size();
/*  79*/            int k = 0;
/*  79*/            do
                    {
/*  79*/                if(k >= j)
/*  80*/                    continue label0;
                        Pair pair1;
/*  80*/                if((pair1 = (Pair)possibleRightPairs.get(k)).getCount() > 1 && checkChecksum(map, pair1))
/*  83*/                    return constructResult(map, pair1);
/*  79*/                k++;
                    } while(true);
                }

/*  89*/        throw NotFoundException.getNotFoundInstance();
            }

            private static void addOrTally(Collection collection, Pair pair)
            {
/*  93*/        if(pair == null)
/*  94*/            return;
/*  96*/        boolean flag = false;
/*  97*/        Iterator iterator = collection.iterator();
/*  97*/        do
                {
/*  97*/            if(!iterator.hasNext())
/*  97*/                break;
                    Pair pair1;
/*  97*/            if((pair1 = (Pair)iterator.next()).getValue() != pair.getValue())
/*  99*/                continue;
/*  99*/            pair1.incrementCount();
/* 100*/            flag = true;
/* 101*/            break;
                } while(true);
/* 104*/        if(!flag)
/* 105*/            collection.add(pair);
            }

            public final void reset()
            {
/* 111*/        possibleLeftPairs.clear();
/* 112*/        possibleRightPairs.clear();
            }

            private static Result constructResult(Pair pair, Pair pair1)
            {
                long l;
/* 116*/        String s = String.valueOf(l = 0x453af5L * (long)pair.getValue() + (long)pair1.getValue());
/* 119*/        StringBuilder stringbuilder = new StringBuilder(14);
/* 120*/        for(int j = 13 - s.length(); j > 0; j--)
/* 121*/            stringbuilder.append('0');

/* 123*/        stringbuilder.append(s);
/* 125*/        int k = 0;
/* 126*/        for(int i = 0; i < 13; i++)
                {
/* 127*/            int i1 = stringbuilder.charAt(i) - 48;
/* 128*/            k += (i & 1) != 0 ? i1 : 3 * i1;
                }

/* 130*/        if((k = 10 - k % 10) == 10)
/* 132*/            k = 0;
/* 134*/        stringbuilder.append(k);
/* 136*/        ResultPoint aresultpoint[] = pair.getFinderPattern().getResultPoints();
/* 137*/        ResultPoint aresultpoint1[] = pair1.getFinderPattern().getResultPoints();
/* 138*/        return new Result(String.valueOf(stringbuilder.toString()), null, new ResultPoint[] {
/* 138*/            aresultpoint[0], aresultpoint[1], aresultpoint1[0], aresultpoint1[1]
                }, BarcodeFormat.RSS_14);
            }

            private static boolean checkChecksum(Pair pair, Pair pair1)
            {
/* 151*/        int i = (pair.getChecksumPortion() + 16 * pair1.getChecksumPortion()) % 79;
/* 152*/        if((pair = 9 * pair.getFinderPattern().getValue() + pair1.getFinderPattern().getValue()) > 72)
/* 155*/            pair--;
/* 157*/        if(pair > 8)
/* 158*/            pair--;
/* 160*/        return i == pair;
            }

            private Pair decodePair(BitArray bitarray, boolean flag, int i, Map map)
            {
                DataCharacter datacharacter;
                FinderPattern finderpattern;
/* 165*/        int ai[] = findFinderPattern(bitarray, 0, flag);
/* 166*/        finderpattern = parseFoundFinderPattern(bitarray, i, flag, ai);
/* 168*/        if((map = map != null ? ((Map) ((ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))) : null) != null)
                {
/* 172*/            float f = (float)(ai[0] + ai[1]) / 2.0F;
/* 173*/            if(flag)
/* 175*/                f = (float)(bitarray.getSize() - 1) - f;
/* 177*/            map.foundPossibleResultPoint(new ResultPoint(f, i));
                }
/* 180*/        datacharacter = decodeDataCharacter(bitarray, finderpattern, true);
/* 181*/        bitarray = decodeDataCharacter(bitarray, finderpattern, false);
/* 182*/        return new Pair(1597 * datacharacter.getValue() + bitarray.getValue(), datacharacter.getChecksumPortion() + 4 * bitarray.getChecksumPortion(), finderpattern);
/* 185*/        JVM INSTR pop ;
/* 186*/        return null;
            }

            private DataCharacter decodeDataCharacter(BitArray bitarray, FinderPattern finderpattern, boolean flag)
                throws NotFoundException
            {
                int ai[];
/* 193*/        (ai = getDataCharacterCounters())[0] = 0;
/* 195*/        ai[1] = 0;
/* 196*/        ai[2] = 0;
/* 197*/        ai[3] = 0;
/* 198*/        ai[4] = 0;
/* 199*/        ai[5] = 0;
/* 200*/        ai[6] = 0;
/* 201*/        ai[7] = 0;
/* 203*/        if(flag)
                {
/* 204*/            recordPatternInReverse(bitarray, finderpattern.getStartEnd()[0], ai);
                } else
                {
/* 206*/            recordPattern(bitarray, finderpattern.getStartEnd()[1] + 1, ai);
/* 208*/            bitarray = 0;
/* 208*/            for(finderpattern = ai.length - 1; bitarray < finderpattern; finderpattern--)
                    {
/* 209*/                int k = ai[bitarray];
/* 210*/                ai[bitarray] = ai[finderpattern];
/* 211*/                ai[finderpattern] = k;
/* 208*/                bitarray++;
                    }

                }
/* 215*/        bitarray = flag ? 16 : 15;
/* 216*/        finderpattern = (float)count(ai) / (float)bitarray;
/* 218*/        int l = getOddCounts();
/* 219*/        int ai1[] = getEvenCounts();
/* 220*/        float af[] = getOddRoundingErrors();
/* 221*/        float af1[] = getEvenRoundingErrors();
/* 223*/        for(int i1 = 0; i1 < ai.length; i1++)
                {
                    float f;
                    int l1;
/* 224*/            if((l1 = (int)((f = (float)ai[i1] / finderpattern) + 0.5F)) <= 0)
/* 227*/                l1 = 1;
/* 228*/            else
/* 228*/            if(l1 > 8)
/* 229*/                l1 = 8;
/* 231*/            int k2 = i1 >> 1;
/* 232*/            if((i1 & 1) == 0)
                    {
/* 233*/                l[k2] = l1;
/* 234*/                af[k2] = f - (float)l1;
                    } else
                    {
/* 236*/                ai1[k2] = l1;
/* 237*/                af1[k2] = f - (float)l1;
                    }
                }

/* 241*/        adjustOddEvenCounts(flag, bitarray);
/* 243*/        int j1 = 0;
/* 244*/        int k1 = 0;
/* 245*/        for(int i2 = l.length - 1; i2 >= 0; i2--)
                {
/* 246*/            k1 = (k1 *= 9) + l[i2];
/* 248*/            j1 += l[i2];
                }

/* 250*/        int j2 = 0;
/* 251*/        int l2 = 0;
/* 252*/        for(bitarray = ai1.length - 1; bitarray >= 0; bitarray--)
                {
/* 253*/            j2 = (j2 *= 9) + ai1[bitarray];
/* 255*/            l2 += ai1[bitarray];
                }

/* 257*/        bitarray = k1 + 3 * j2;
/* 259*/        if(flag)
/* 260*/            if((j1 & 1) != 0 || j1 > 12 || j1 < 4)
                    {
/* 261*/                throw NotFoundException.getNotFoundInstance();
                    } else
                    {
/* 263*/                finderpattern = (12 - j1) / 2;
/* 264*/                flag = OUTSIDE_ODD_WIDEST[finderpattern];
/* 265*/                int i = 9 - flag;
/* 266*/                flag = RSSUtils.getRSSvalue(l, flag, false);
/* 267*/                i = RSSUtils.getRSSvalue(ai1, i, true);
/* 268*/                l = OUTSIDE_EVEN_TOTAL_SUBSET[finderpattern];
/* 269*/                finderpattern = OUTSIDE_GSUM[finderpattern];
/* 270*/                return new DataCharacter(flag * l + i + finderpattern, bitarray);
                    }
/* 272*/        if((l2 & 1) != 0 || l2 > 10 || l2 < 4)
                {
/* 273*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 275*/            finderpattern = (10 - l2) / 2;
/* 276*/            flag = INSIDE_ODD_WIDEST[finderpattern];
/* 277*/            int j = 9 - flag;
/* 278*/            flag = RSSUtils.getRSSvalue(l, flag, true);
/* 279*/            j = RSSUtils.getRSSvalue(ai1, j, false);
/* 280*/            l = INSIDE_ODD_TOTAL_SUBSET[finderpattern];
/* 281*/            finderpattern = INSIDE_GSUM[finderpattern];
/* 282*/            return new DataCharacter(j * l + flag + finderpattern, bitarray);
                }
            }

            private int[] findFinderPattern(BitArray bitarray, int i, boolean flag)
                throws NotFoundException
            {
                int ai[];
/* 290*/        (ai = getDecodeFinderCounters())[0] = 0;
/* 292*/        ai[1] = 0;
/* 293*/        ai[2] = 0;
/* 294*/        ai[3] = 0;
/* 296*/        int j = bitarray.getSize();
/* 297*/        boolean flag1 = false;
/* 298*/        do
                {
/* 298*/            if(i >= j)
/* 299*/                break;
/* 299*/            flag1 = !bitarray.get(i);
/* 300*/            if(flag == flag1)
/* 304*/                break;
/* 304*/            i++;
                } while(true);
/* 307*/        flag = false;
/* 308*/        int k = i;
/* 309*/        for(i = i; i < j; i++)
                {
/* 310*/            if(bitarray.get(i) ^ flag1)
                    {
/* 311*/                ai[flag]++;
/* 311*/                continue;
                    }
/* 313*/            if(flag == 3)
                    {
/* 314*/                if(isFinderPattern(ai))
/* 315*/                    return (new int[] {
/* 315*/                        k, i
                            });
/* 317*/                k += ai[0] + ai[1];
/* 318*/                ai[0] = ai[2];
/* 319*/                ai[1] = ai[3];
/* 320*/                ai[2] = 0;
/* 321*/                ai[3] = 0;
/* 322*/                flag--;
                    } else
                    {
/* 324*/                flag++;
                    }
/* 326*/            ai[flag] = 1;
/* 327*/            flag1 = !flag1;
                }

/* 330*/        throw NotFoundException.getNotFoundInstance();
            }

            private FinderPattern parseFoundFinderPattern(BitArray bitarray, int i, boolean flag, int ai[])
                throws NotFoundException
            {
/* 337*/        int j = bitarray.get(ai[0]);
                int k;
/* 338*/        for(k = ai[0] - 1; k >= 0 && j ^ bitarray.get(k); k--);
/* 343*/        k++;
/* 344*/        j = ai[0] - k;
                int ai1[];
/* 346*/        System.arraycopy(ai1 = getDecodeFinderCounters(), 0, ai1, 1, ai1.length - 1);
/* 348*/        ai1[0] = j;
/* 349*/        j = parseFinderValue(ai1, FINDER_PATTERNS);
/* 350*/        int l = k;
/* 351*/        int i1 = ai[1];
/* 352*/        if(flag)
                {
/* 354*/            l = bitarray.getSize() - 1 - l;
/* 355*/            i1 = bitarray.getSize() - 1 - i1;
                }
/* 357*/        return new FinderPattern(j, new int[] {
/* 357*/            k, ai[1]
                }, l, i1, i);
            }

            private void adjustOddEvenCounts(boolean flag, int i)
                throws NotFoundException
            {
/* 362*/        int j = count(getOddCounts());
/* 363*/        int k = count(getEvenCounts());
/* 364*/        i = (j + k) - i;
/* 365*/        boolean flag1 = (j & 1) == (flag ? 1 : 0);
/* 366*/        boolean flag2 = (k & 1) == 1;
/* 368*/        boolean flag3 = false;
/* 369*/        boolean flag4 = false;
/* 370*/        boolean flag5 = false;
/* 371*/        boolean flag6 = false;
/* 373*/        if(flag)
                {
/* 374*/            if(j > 12)
/* 375*/                flag4 = true;
/* 376*/            else
/* 376*/            if(j < 4)
/* 377*/                flag3 = true;
/* 379*/            if(k > 12)
/* 380*/                flag6 = true;
/* 381*/            else
/* 381*/            if(k < 4)
/* 382*/                flag5 = true;
                } else
                {
/* 385*/            if(j > 11)
/* 386*/                flag4 = true;
/* 387*/            else
/* 387*/            if(j < 5)
/* 388*/                flag3 = true;
/* 390*/            if(k > 10)
/* 391*/                flag6 = true;
/* 392*/            else
/* 392*/            if(k < 4)
/* 393*/                flag5 = true;
                }
/* 409*/        if(i == 1)
                {
/* 410*/            if(flag1)
                    {
/* 411*/                if(flag2)
/* 412*/                    throw NotFoundException.getNotFoundInstance();
/* 414*/                flag4 = true;
                    } else
                    {
/* 416*/                if(!flag2)
/* 417*/                    throw NotFoundException.getNotFoundInstance();
/* 419*/                flag6 = true;
                    }
                } else
/* 421*/        if(i == -1)
                {
/* 422*/            if(flag1)
                    {
/* 423*/                if(flag2)
/* 424*/                    throw NotFoundException.getNotFoundInstance();
/* 426*/                flag3 = true;
                    } else
                    {
/* 428*/                if(!flag2)
/* 429*/                    throw NotFoundException.getNotFoundInstance();
/* 431*/                flag5 = true;
                    }
                } else
/* 433*/        if(i == 0)
                {
/* 434*/            if(flag1)
                    {
/* 435*/                if(!flag2)
/* 436*/                    throw NotFoundException.getNotFoundInstance();
/* 439*/                if(j < k)
                        {
/* 440*/                    flag3 = true;
/* 441*/                    flag6 = true;
                        } else
                        {
/* 443*/                    flag4 = true;
/* 444*/                    flag5 = true;
                        }
                    } else
/* 447*/            if(flag2)
/* 448*/                throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 453*/            throw NotFoundException.getNotFoundInstance();
                }
/* 456*/        if(flag3)
                {
/* 457*/            if(flag4)
/* 458*/                throw NotFoundException.getNotFoundInstance();
/* 460*/            increment(getOddCounts(), getOddRoundingErrors());
                }
/* 462*/        if(flag4)
/* 463*/            decrement(getOddCounts(), getOddRoundingErrors());
/* 465*/        if(flag5)
                {
/* 466*/            if(flag6)
/* 467*/                throw NotFoundException.getNotFoundInstance();
/* 469*/            increment(getEvenCounts(), getOddRoundingErrors());
                }
/* 471*/        if(flag6)
/* 472*/            decrement(getEvenCounts(), getEvenRoundingErrors());
            }

            private static final int OUTSIDE_EVEN_TOTAL_SUBSET[] = {
/*  37*/        1, 10, 34, 70, 126
            };
            private static final int INSIDE_ODD_TOTAL_SUBSET[] = {
/*  38*/        4, 20, 48, 81
            };
            private static final int OUTSIDE_GSUM[] = {
/*  39*/        0, 161, 961, 2015, 2715
            };
            private static final int INSIDE_GSUM[] = {
/*  40*/        0, 336, 1036, 1516
            };
            private static final int OUTSIDE_ODD_WIDEST[] = {
/*  41*/        8, 6, 4, 3, 1
            };
            private static final int INSIDE_ODD_WIDEST[] = {
/*  42*/        2, 4, 6, 8
            };
            private static final int FINDER_PATTERNS[][] = {
/*  44*/        {
/*  44*/            3, 8, 2, 1
                }, {
/*  44*/            3, 5, 5, 1
                }, {
/*  44*/            3, 3, 7, 1
                }, {
/*  44*/            3, 1, 9, 1
                }, {
/*  44*/            2, 7, 4, 1
                }, {
/*  44*/            2, 5, 6, 1
                }, {
/*  44*/            2, 3, 8, 1
                }, {
/*  44*/            1, 5, 7, 1
                }, {
/*  44*/            1, 3, 9, 1
                }
            };
            private final List possibleLeftPairs = new ArrayList();
            private final List possibleRightPairs = new ArrayList();

}
