// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RSSExpandedReader.java

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.*;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.*;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            BitArrayBuilder, ExpandedPair, ExpandedRow

public final class RSSExpandedReader extends AbstractRSSReader
{

            public RSSExpandedReader()
            {
/* 121*/        startFromEven = false;
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, FormatException
            {
/* 129*/        pairs.clear();
/* 130*/        startFromEven = false;
/* 132*/        return constructResult(map = decodeRow2pairs(i, bitarray));
/* 134*/        JVM INSTR pop ;
/* 138*/        pairs.clear();
/* 139*/        startFromEven = true;
/* 140*/        return constructResult(map = decodeRow2pairs(i, bitarray));
            }

            public final void reset()
            {
/* 146*/        pairs.clear();
/* 147*/        rows.clear();
            }

            final List decodeRow2pairs(int i, BitArray bitarray)
                throws NotFoundException
            {
/* 154*/        try
                {
/* 154*/            while(true) 
                    {
/* 154*/                ExpandedPair expandedpair = retrieveNextPair(bitarray, pairs, i);
/* 155*/                pairs.add(expandedpair);
                    }
                }
/* 159*/        catch(NotFoundException notfoundexception)
                {
/* 160*/            if(pairs.isEmpty())
/* 161*/                throw notfoundexception;
                }
/* 166*/        if(checkChecksum())
/* 167*/            return pairs;
/* 170*/        boolean flag = !rows.isEmpty();
/* 172*/        storeRow(i, false);
/* 173*/        if(flag)
                {
/* 176*/            if((i = checkRows(false)) != null)
/* 178*/                return i;
/* 180*/            if((i = checkRows(true)) != null)
/* 182*/                return i;
                }
/* 186*/        throw NotFoundException.getNotFoundInstance();
            }

            private List checkRows(boolean flag)
            {
/* 193*/        if(rows.size() > 25)
                {
/* 194*/            rows.clear();
/* 195*/            return null;
                }
/* 198*/        pairs.clear();
/* 199*/        if(flag)
/* 200*/            Collections.reverse(rows);
/* 203*/        List list = null;
/* 205*/        try
                {
/* 205*/            list = checkRows(((List) (new ArrayList())), 0);
                }
/* 206*/        catch(NotFoundException _ex) { }
/* 210*/        if(flag)
/* 211*/            Collections.reverse(rows);
/* 214*/        return list;
            }

            private List checkRows(List list, int i)
                throws NotFoundException
            {
/* 220*/        i = i;
_L3:
/* 220*/        if(i >= rows.size()) goto _L2; else goto _L1
_L1:
                ArrayList arraylist;
/* 221*/        ExpandedRow expandedrow = (ExpandedRow)rows.get(i);
/* 222*/        pairs.clear();
/* 223*/        int j = list.size();
/* 224*/        for(int k = 0; k < j; k++)
/* 225*/            pairs.addAll(((ExpandedRow)list.get(k)).getPairs());

/* 227*/        pairs.addAll(expandedrow.getPairs());
/* 229*/        if(!isValidSequence(pairs))
/* 233*/            continue; /* Loop/switch isn't completed */
/* 233*/        if(checkChecksum())
/* 234*/            return pairs;
/* 237*/        (arraylist = new ArrayList()).addAll(list);
/* 239*/        arraylist.add(expandedrow);
/* 242*/        return checkRows(((List) (arraylist)), i + 1);
/* 243*/        JVM INSTR pop ;
/* 220*/        i++;
                  goto _L3
_L2:
/* 248*/        throw NotFoundException.getNotFoundInstance();
            }

            private static boolean isValidSequence(List list)
            {
                int ai[][];
/* 254*/        int i = (ai = FINDER_PATTERN_SEQUENCES).length;
/* 254*/        for(int j = 0; j < i; j++)
                {
/* 254*/            int ai1[] = ai[j];
/* 255*/            if(list.size() > ai1.length)
/* 259*/                continue;
/* 259*/            boolean flag = true;
/* 260*/            int k = 0;
/* 260*/            do
                    {
/* 260*/                if(k >= list.size())
/* 261*/                    break;
/* 261*/                if(((ExpandedPair)list.get(k)).getFinderPattern().getValue() != ai1[k])
                        {
/* 262*/                    flag = false;
/* 263*/                    break;
                        }
/* 260*/                k++;
                    } while(true);
/* 267*/            if(flag)
/* 268*/                return true;
                }

/* 272*/        return false;
            }

            private void storeRow(int i, boolean flag)
            {
/* 277*/        int j = 0;
/* 278*/        boolean flag1 = false;
/* 279*/        boolean flag2 = false;
/* 280*/        do
                {
/* 280*/            if(j >= rows.size())
/* 281*/                break;
                    ExpandedRow expandedrow;
/* 281*/            if((expandedrow = (ExpandedRow)rows.get(j)).getRowNumber() > i)
                    {
/* 283*/                flag2 = expandedrow.isEquivalent(pairs);
/* 284*/                break;
                    }
/* 286*/            flag1 = expandedrow.isEquivalent(pairs);
/* 287*/            j++;
                } while(true);
/* 289*/        if(flag2 || flag1)
/* 290*/            return;
/* 298*/        if(isPartialRow(pairs, rows))
                {
/* 299*/            return;
                } else
                {
/* 302*/            rows.add(j, new ExpandedRow(pairs, i, flag));
/* 304*/            removePartialRows(pairs, rows);
/* 305*/            return;
                }
            }

            private static void removePartialRows(List list, List list1)
            {
/* 309*/        list1 = list1.iterator();
/* 309*/label0:
/* 309*/        do
                {
                    boolean flag;
/* 309*/label1:
                    {
/* 309*/                if(!list1.hasNext())
/* 310*/                    break label0;
                        Object obj;
/* 310*/                if(((ExpandedRow) (obj = (ExpandedRow)list1.next())).getPairs().size() == list.size())
/* 314*/                    continue;
/* 314*/                flag = true;
/* 315*/                obj = ((ExpandedRow) (obj)).getPairs().iterator();
                        boolean flag1;
/* 315*/label2:
/* 315*/                do
                        {
/* 315*/                    if(!((Iterator) (obj)).hasNext())
/* 315*/                        break label1;
/* 315*/                    ExpandedPair expandedpair = (ExpandedPair)((Iterator) (obj)).next();
/* 316*/                    flag1 = false;
/* 317*/                    Iterator iterator = list.iterator();
                            ExpandedPair expandedpair1;
/* 317*/                    do
                            {
/* 317*/                        if(!iterator.hasNext())
/* 317*/                            continue label2;
/* 317*/                        expandedpair1 = (ExpandedPair)iterator.next();
                            } while(!expandedpair.equals(expandedpair1));
/* 319*/                    flag1 = true;
                        } while(flag1);
/* 324*/                flag = false;
                    }
/* 328*/            if(flag)
/* 330*/                list1.remove();
                } while(true);
            }

            private static boolean isPartialRow(Iterable iterable, Iterable iterable1)
            {
/* 337*/label0:
                {
/* 337*/            iterable1 = iterable1.iterator();
                    boolean flag;
/* 337*/label1:
/* 337*/            do
                    {
/* 337*/                if(!iterable1.hasNext())
/* 337*/                    break label0;
/* 337*/                ExpandedRow expandedrow = (ExpandedRow)iterable1.next();
/* 338*/                flag = true;
/* 339*/                Iterator iterator = iterable.iterator();
                        boolean flag1;
/* 339*/label2:
/* 339*/                do
                        {
/* 339*/                    if(!iterator.hasNext())
/* 339*/                        continue label1;
/* 339*/                    ExpandedPair expandedpair = (ExpandedPair)iterator.next();
/* 340*/                    flag1 = false;
/* 341*/                    Iterator iterator1 = expandedrow.getPairs().iterator();
                            ExpandedPair expandedpair1;
/* 341*/                    do
                            {
/* 341*/                        if(!iterator1.hasNext())
/* 341*/                            continue label2;
/* 341*/                        expandedpair1 = (ExpandedPair)iterator1.next();
                            } while(!expandedpair.equals(expandedpair1));
/* 343*/                    flag1 = true;
                        } while(flag1);
/* 348*/                flag = false;
                    } while(!flag);
/* 354*/            return true;
                }
/* 357*/        return false;
            }

            final List getRows()
            {
/* 362*/        return rows;
            }

            static Result constructResult(List list)
                throws NotFoundException, FormatException
            {
                Object obj;
/* 367*/        obj = ((AbstractExpandedDecoder) (obj = AbstractExpandedDecoder.createDecoder(((BitArray) (obj = BitArrayBuilder.buildBitArray(list)))))).parseInformation();
/* 372*/        ResultPoint aresultpoint[] = ((ExpandedPair)list.get(0)).getFinderPattern().getResultPoints();
/* 373*/        list = ((ExpandedPair)list.get(list.size() - 1)).getFinderPattern().getResultPoints();
/* 375*/        return new Result(((String) (obj)), null, new ResultPoint[] {
/* 375*/            aresultpoint[0], aresultpoint[1], list[0], list[1]
                }, BarcodeFormat.RSS_EXPANDED);
            }

            private boolean checkChecksum()
            {
                Object obj;
/* 384*/        DataCharacter datacharacter = ((ExpandedPair) (obj = (ExpandedPair)pairs.get(0))).getLeftChar();
/* 386*/        if((obj = ((ExpandedPair) (obj)).getRightChar()) == null)
/* 389*/            return false;
/* 392*/        int i = ((DataCharacter) (obj)).getChecksumPortion();
/* 393*/        int j = 2;
/* 395*/        for(int k = 1; k < pairs.size(); k++)
                {
/* 396*/            Object obj1 = (ExpandedPair)pairs.get(k);
/* 397*/            i += ((ExpandedPair) (obj1)).getLeftChar().getChecksumPortion();
/* 398*/            j++;
/* 399*/            if((obj1 = ((ExpandedPair) (obj1)).getRightChar()) != null)
                    {
/* 401*/                i += ((DataCharacter) (obj1)).getChecksumPortion();
/* 402*/                j++;
                    }
                }

/* 406*/        i %= 211;
                int l;
/* 408*/        return (l = 211 * (j - 4) + i) == datacharacter.getValue();
            }

            private static int getNextSecondBar(BitArray bitarray, int i)
            {
/* 415*/        if(bitarray.get(i))
                {
/* 416*/            i = bitarray.getNextUnset(i);
/* 417*/            i = bitarray.getNextSet(i);
                } else
                {
/* 419*/            i = bitarray.getNextSet(i);
/* 420*/            i = bitarray.getNextUnset(i);
                }
/* 422*/        return i;
            }

            final ExpandedPair retrieveNextPair(BitArray bitarray, List list, int i)
                throws NotFoundException
            {
/* 428*/        boolean flag = list.size() % 2 == 0;
/* 429*/        if(startFromEven)
/* 430*/            flag = !flag;
/* 435*/        boolean flag1 = true;
/* 436*/        int j = -1;
                FinderPattern finderpattern;
/* 438*/        do
                {
/* 438*/            findNextPair(bitarray, list, j);
/* 439*/            if((finderpattern = parseFoundFinderPattern(bitarray, i, flag)) == null)
/* 441*/                j = getNextSecondBar(bitarray, startEnd[0]);
/* 443*/            else
/* 443*/                flag1 = false;
                } while(flag1);
/* 450*/        i = decodeDataCharacter(bitarray, finderpattern, flag, true);
/* 452*/        if(!list.isEmpty() && ((ExpandedPair)list.get(list.size() - 1)).mustBeLast())
/* 453*/            throw NotFoundException.getNotFoundInstance();
/* 458*/        try
                {
/* 458*/            bitarray = decodeDataCharacter(bitarray, finderpattern, flag, false);
                }
/* 459*/        catch(NotFoundException _ex)
                {
/* 460*/            bitarray = null;
                }
/* 463*/        return new ExpandedPair(i, bitarray, finderpattern, true);
            }

            private void findNextPair(BitArray bitarray, List list, int i)
                throws NotFoundException
            {
                int ai[];
/* 468*/        (ai = getDecodeFinderCounters())[0] = 0;
/* 470*/        ai[1] = 0;
/* 471*/        ai[2] = 0;
/* 472*/        ai[3] = 0;
/* 474*/        int j = bitarray.getSize();
                ExpandedPair expandedpair;
/* 477*/        if(i >= 0)
/* 478*/            i = i;
/* 479*/        else
/* 479*/        if(list.isEmpty())
/* 480*/            i = 0;
/* 482*/        else
/* 482*/            i = (expandedpair = (ExpandedPair)list.get(list.size() - 1)).getFinderPattern().getStartEnd()[1];
/* 485*/        boolean flag = list.size() % 2 != 0;
/* 486*/        if(startFromEven)
/* 487*/            flag = !flag;
/* 490*/        for(list = 0; i < j && (list = bitarray.get(i) ? 0 : 1) != 0; i++);
/* 499*/        int k = 0;
/* 500*/        int l = i;
/* 501*/        for(i = i; i < j; i++)
                {
/* 502*/            if((bitarray.get(i) ^ list) != 0)
                    {
/* 503*/                ai[k]++;
/* 503*/                continue;
                    }
/* 505*/            if(k == 3)
                    {
/* 506*/                if(flag)
/* 507*/                    reverseCounters(ai);
/* 510*/                if(isFinderPattern(ai))
                        {
/* 511*/                    startEnd[0] = l;
/* 512*/                    startEnd[1] = i;
/* 513*/                    return;
                        }
/* 516*/                if(flag)
/* 517*/                    reverseCounters(ai);
/* 520*/                l += ai[0] + ai[1];
/* 521*/                ai[0] = ai[2];
/* 522*/                ai[1] = ai[3];
/* 523*/                ai[2] = 0;
/* 524*/                ai[3] = 0;
/* 525*/                k--;
                    } else
                    {
/* 527*/                k++;
                    }
/* 529*/            ai[k] = 1;
/* 530*/            list = list != 0 ? 0 : 1;
                }

/* 533*/        throw NotFoundException.getNotFoundInstance();
            }

            private static void reverseCounters(int ai[])
            {
/* 537*/        int i = ai.length;
/* 538*/        for(int j = 0; j < i / 2; j++)
                {
/* 539*/            int k = ai[j];
/* 540*/            ai[j] = ai[i - j - 1];
/* 541*/            ai[i - j - 1] = k;
                }

            }

            private FinderPattern parseFoundFinderPattern(BitArray bitarray, int i, boolean flag)
            {
                int j;
/* 551*/        if(flag)
                {
                    int k;
/* 554*/            for(k = startEnd[0] - 1; k >= 0 && !bitarray.get(k); k--);
/* 560*/            k++;
/* 561*/            bitarray = startEnd[0] - k;
/* 562*/            flag = k;
/* 563*/            j = startEnd[1];
                } else
                {
/* 568*/            flag = startEnd[0];
/* 570*/            bitarray = (j = bitarray.getNextUnset(startEnd[1] + 1)) - startEnd[1];
                }
                int ai[];
/* 575*/        System.arraycopy(ai = getDecodeFinderCounters(), 0, ai, 1, ai.length - 1);
/* 578*/        ai[0] = bitarray;
/* 581*/        try
                {
/* 581*/            bitarray = parseFinderValue(ai, FINDER_PATTERNS);
                }
/* 582*/        catch(NotFoundException _ex)
                {
/* 583*/            return null;
                }
/* 585*/        return new FinderPattern(bitarray, new int[] {
/* 585*/            flag, j
                }, flag, j, i);
            }

            final DataCharacter decodeDataCharacter(BitArray bitarray, FinderPattern finderpattern, boolean flag, boolean flag1)
                throws NotFoundException
            {
                int ai[];
/* 592*/        (ai = getDataCharacterCounters())[0] = 0;
/* 594*/        ai[1] = 0;
/* 595*/        ai[2] = 0;
/* 596*/        ai[3] = 0;
/* 597*/        ai[4] = 0;
/* 598*/        ai[5] = 0;
/* 599*/        ai[6] = 0;
/* 600*/        ai[7] = 0;
/* 602*/        if(flag1)
                {
/* 603*/            recordPatternInReverse(bitarray, finderpattern.getStartEnd()[0], ai);
                } else
                {
/* 605*/            recordPattern(bitarray, finderpattern.getStartEnd()[1], ai);
/* 607*/            bitarray = 0;
/* 607*/            for(int l = ai.length - 1; bitarray < l; l--)
                    {
/* 608*/                int k1 = ai[bitarray];
/* 609*/                ai[bitarray] = ai[l];
/* 610*/                ai[l] = k1;
/* 607*/                bitarray++;
                    }

                }
/* 615*/        float f = (float)count(ai) / 17F;
/* 618*/        float f1 = (float)(finderpattern.getStartEnd()[1] - finderpattern.getStartEnd()[0]) / 15F;
/* 619*/        if(Math.abs(f - f1) / f1 > 0.3F)
/* 620*/            throw NotFoundException.getNotFoundInstance();
/* 623*/        bitarray = getOddCounts();
/* 624*/        int ai1[] = getEvenCounts();
/* 625*/        float af[] = getOddRoundingErrors();
/* 626*/        float af1[] = getEvenRoundingErrors();
/* 628*/        for(int l1 = 0; l1 < ai.length; l1++)
                {
                    float f2;
                    int k2;
/* 629*/            if((k2 = (int)((f2 = (1.0F * (float)ai[l1]) / f) + 0.5F)) <= 0)
                    {
/* 632*/                if(f2 < 0.3F)
/* 633*/                    throw NotFoundException.getNotFoundInstance();
/* 635*/                k2 = 1;
                    } else
/* 636*/            if(k2 > 8)
                    {
/* 637*/                if(f2 > 8.7F)
/* 638*/                    throw NotFoundException.getNotFoundInstance();
/* 640*/                k2 = 8;
                    }
/* 642*/            int i3 = l1 >> 1;
/* 643*/            if((l1 & 1) == 0)
                    {
/* 644*/                bitarray[i3] = k2;
/* 645*/                af[i3] = f2 - (float)k2;
                    } else
                    {
/* 647*/                ai1[i3] = k2;
/* 648*/                af1[i3] = f2 - (float)k2;
                    }
                }

/* 652*/        adjustOddEvenCounts(17);
/* 654*/        int i2 = (4 * finderpattern.getValue() + (flag ? 0 : 2) + (flag1 ? 0 : 1)) - 1;
/* 656*/        int j2 = 0;
/* 657*/        int l2 = 0;
/* 658*/        for(int j3 = bitarray.length - 1; j3 >= 0; j3--)
                {
/* 659*/            if(isNotA1left(finderpattern, flag, flag1))
                    {
/* 660*/                int i = WEIGHTS[i2][2 * j3];
/* 661*/                l2 += bitarray[j3] * i;
                    }
/* 663*/            j2 += bitarray[j3];
                }

/* 665*/        int k3 = 0;
/* 667*/        for(int j = ai1.length - 1; j >= 0; j--)
/* 668*/            if(isNotA1left(finderpattern, flag, flag1))
                    {
/* 669*/                int i1 = WEIGHTS[i2][2 * j + 1];
/* 670*/                k3 += ai1[j] * i1;
                    }

/* 674*/        int k = l2 + k3;
/* 676*/        if((j2 & 1) != 0 || j2 > 13 || j2 < 4)
                {
/* 677*/            throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 680*/            int j1 = (13 - j2) / 2;
/* 681*/            finderpattern = SYMBOL_WIDEST[j1];
/* 682*/            flag = 9 - finderpattern;
/* 683*/            bitarray = RSSUtils.getRSSvalue(bitarray, finderpattern, true);
/* 684*/            finderpattern = RSSUtils.getRSSvalue(ai1, flag, false);
/* 685*/            flag = EVEN_TOTAL_SUBSET[j1];
/* 686*/            flag1 = GSUM[j1];
/* 687*/            bitarray = bitarray * flag + finderpattern + flag1;
/* 689*/            return new DataCharacter(bitarray, k);
                }
            }

            private static boolean isNotA1left(FinderPattern finderpattern, boolean flag, boolean flag1)
            {
/* 694*/        return finderpattern.getValue() != 0 || !flag || !flag1;
            }

            private void adjustOddEvenCounts(int i)
                throws NotFoundException
            {
/* 699*/        int j = count(getOddCounts());
/* 700*/        int k = count(getEvenCounts());
/* 701*/        i = (j + k) - i;
/* 702*/        boolean flag = (j & 1) == 1;
/* 703*/        boolean flag1 = (k & 1) == 0;
/* 705*/        boolean flag2 = false;
/* 706*/        boolean flag3 = false;
/* 708*/        if(j > 13)
/* 709*/            flag3 = true;
/* 710*/        else
/* 710*/        if(j < 4)
/* 711*/            flag2 = true;
/* 713*/        boolean flag4 = false;
/* 714*/        boolean flag5 = false;
/* 715*/        if(k > 13)
/* 716*/            flag5 = true;
/* 717*/        else
/* 717*/        if(k < 4)
/* 718*/            flag4 = true;
/* 721*/        if(i == 1)
                {
/* 722*/            if(flag)
                    {
/* 723*/                if(flag1)
/* 724*/                    throw NotFoundException.getNotFoundInstance();
/* 726*/                flag3 = true;
                    } else
                    {
/* 728*/                if(!flag1)
/* 729*/                    throw NotFoundException.getNotFoundInstance();
/* 731*/                flag5 = true;
                    }
                } else
/* 733*/        if(i == -1)
                {
/* 734*/            if(flag)
                    {
/* 735*/                if(flag1)
/* 736*/                    throw NotFoundException.getNotFoundInstance();
/* 738*/                flag2 = true;
                    } else
                    {
/* 740*/                if(!flag1)
/* 741*/                    throw NotFoundException.getNotFoundInstance();
/* 743*/                flag4 = true;
                    }
                } else
/* 745*/        if(i == 0)
                {
/* 746*/            if(flag)
                    {
/* 747*/                if(!flag1)
/* 748*/                    throw NotFoundException.getNotFoundInstance();
/* 751*/                if(j < k)
                        {
/* 752*/                    flag2 = true;
/* 753*/                    flag5 = true;
                        } else
                        {
/* 755*/                    flag3 = true;
/* 756*/                    flag4 = true;
                        }
                    } else
/* 759*/            if(flag1)
/* 760*/                throw NotFoundException.getNotFoundInstance();
                } else
                {
/* 765*/            throw NotFoundException.getNotFoundInstance();
                }
/* 768*/        if(flag2)
                {
/* 769*/            if(flag3)
/* 770*/                throw NotFoundException.getNotFoundInstance();
/* 772*/            increment(getOddCounts(), getOddRoundingErrors());
                }
/* 774*/        if(flag3)
/* 775*/            decrement(getOddCounts(), getOddRoundingErrors());
/* 777*/        if(flag4)
                {
/* 778*/            if(flag5)
/* 779*/                throw NotFoundException.getNotFoundInstance();
/* 781*/            increment(getEvenCounts(), getOddRoundingErrors());
                }
/* 783*/        if(flag5)
/* 784*/            decrement(getEvenCounts(), getEvenRoundingErrors());
            }

            private static final int SYMBOL_WIDEST[] = {
/*  54*/        7, 5, 4, 3, 1
            };
            private static final int EVEN_TOTAL_SUBSET[] = {
/*  55*/        4, 20, 52, 104, 204
            };
            private static final int GSUM[] = {
/*  56*/        0, 348, 1388, 2948, 3988
            };
            private static final int FINDER_PATTERNS[][] = {
/*  58*/        {
/*  58*/            1, 8, 4, 1
                }, {
/*  58*/            3, 6, 4, 1
                }, {
/*  58*/            3, 4, 6, 1
                }, {
/*  58*/            3, 2, 8, 1
                }, {
/*  58*/            2, 6, 5, 1
                }, {
/*  58*/            2, 2, 9, 1
                }
            };
            private static final int WEIGHTS[][] = {
/*  67*/        {
/*  67*/            1, 3, 9, 27, 81, 32, 96, 77
                }, {
/*  67*/            20, 60, 180, 118, 143, 7, 21, 63
                }, {
/*  67*/            189, 145, 13, 39, 117, 140, 209, 205
                }, {
/*  67*/            193, 157, 49, 147, 19, 57, 171, 91
                }, {
/*  67*/            62, 186, 136, 197, 169, 85, 44, 132
                }, {
/*  67*/            185, 133, 188, 142, 4, 12, 36, 108
                }, {
/*  67*/            113, 128, 173, 97, 80, 29, 87, 50
                }, {
/*  67*/            150, 28, 84, 41, 123, 158, 52, 156
                }, {
/*  67*/            46, 138, 203, 187, 139, 206, 196, 166
                }, {
/*  67*/            76, 17, 51, 153, 37, 111, 122, 155
                }, {
/*  67*/            43, 129, 176, 106, 107, 110, 119, 146
                }, {
/*  67*/            16, 48, 144, 10, 30, 90, 59, 177
                }, {
/*  67*/            109, 116, 137, 200, 178, 112, 125, 164
                }, {
/*  67*/            70, 210, 208, 202, 184, 130, 179, 115
                }, {
/*  67*/            134, 191, 151, 31, 93, 68, 204, 190
                }, {
/*  67*/            148, 22, 66, 198, 172, 94, 71, 2
                }, {
/*  67*/            6, 18, 54, 162, 64, 192, 154, 40
                }, {
/*  67*/            120, 149, 25, 75, 14, 42, 126, 167
                }, {
/*  67*/            79, 26, 78, 23, 69, 207, 199, 175
                }, {
/*  67*/            103, 98, 83, 38, 114, 131, 182, 124
                }, {
/*  67*/            161, 61, 183, 127, 170, 88, 53, 159
                }, {
/*  67*/            55, 165, 73, 8, 24, 72, 5, 15
                }, {
/*  67*/            45, 135, 194, 160, 58, 174, 100, 89
                }
            };
            private static final int FINDER_PAT_A = 0;
            private static final int FINDER_PAT_B = 1;
            private static final int FINDER_PAT_C = 2;
            private static final int FINDER_PAT_D = 3;
            private static final int FINDER_PAT_E = 4;
            private static final int FINDER_PAT_F = 5;
            private static final int FINDER_PATTERN_SEQUENCES[][] = {
/* 100*/        {
/* 100*/            0, 0
                }, {
/* 100*/            0, 1, 1
                }, {
/* 100*/            0, 2, 1, 3
                }, {
/* 100*/            0, 4, 1, 3, 2
                }, {
/* 100*/            0, 4, 1, 3, 3, 5
                }, {
/* 100*/            0, 4, 1, 3, 4, 5, 5
                }, {
/* 100*/            0, 0, 1, 1, 2, 2, 3, 3
                }, {
/* 100*/            0, 0, 1, 1, 2, 2, 3, 4, 4
                }, {
/* 100*/            0, 0, 1, 1, 2, 2, 3, 4, 5, 5
                }, {
/* 100*/            0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 
/* 100*/            5
                }
            };
            private static final int MAX_PAIRS = 11;
            private final List pairs = new ArrayList(11);
            private final List rows = new ArrayList();
            private final int startEnd[] = new int[2];
            private boolean startFromEven;

}
