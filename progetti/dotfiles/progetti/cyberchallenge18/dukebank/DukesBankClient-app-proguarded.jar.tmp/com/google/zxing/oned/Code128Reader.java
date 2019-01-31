// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Code128Reader.java

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.*;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code128Reader extends OneDReader
{

            public Code128Reader()
            {
            }

            private static int[] findStartPattern(BitArray bitarray)
                throws NotFoundException
            {
/* 170*/        int i = bitarray.getSize();
/* 171*/        int j = bitarray.getNextSet(0);
/* 173*/        int k = 0;
/* 174*/        int ai[] = new int[6];
/* 175*/        int l = j;
/* 176*/        boolean flag = false;
/* 179*/        for(j = j; j < i; j++)
                {
/* 180*/            if(bitarray.get(j) ^ flag)
                    {
/* 181*/                ai[k]++;
/* 181*/                continue;
                    }
/* 183*/            if(k == 5)
                    {
/* 184*/                int i1 = 64;
/* 185*/                int j1 = -1;
/* 186*/                for(int k1 = 103; k1 <= 105; k1++)
                        {
                            int l1;
/* 187*/                    if((l1 = patternMatchVariance(ai, CODE_PATTERNS[k1], 179)) < i1)
                            {
/* 190*/                        i1 = l1;
/* 191*/                        j1 = k1;
                            }
                        }

/* 195*/                if(j1 >= 0 && bitarray.isRange(Math.max(0, l - (j - l) / 2), l, false))
/* 197*/                    return (new int[] {
/* 197*/                        l, j, j1
                            });
/* 199*/                l += ai[0] + ai[1];
/* 200*/                System.arraycopy(ai, 2, ai, 0, 4);
/* 201*/                ai[4] = 0;
/* 202*/                ai[5] = 0;
/* 203*/                k--;
                    } else
                    {
/* 205*/                k++;
                    }
/* 207*/            ai[k] = 1;
/* 208*/            flag = !flag;
                }

/* 211*/        throw NotFoundException.getNotFoundInstance();
            }

            private static int decodeCode(BitArray bitarray, int ai[], int i)
                throws NotFoundException
            {
/* 216*/        recordPattern(bitarray, i, ai);
/* 217*/        bitarray = 64;
/* 218*/        i = -1;
/* 219*/        for(int j = 0; j < CODE_PATTERNS.length; j++)
                {
/* 220*/            int ai1[] = CODE_PATTERNS[j];
                    int k;
/* 221*/            if((k = patternMatchVariance(ai, ai1, 179)) < bitarray)
                    {
/* 223*/                bitarray = k;
/* 224*/                i = j;
                    }
                }

/* 228*/        if(i >= 0)
/* 229*/            return i;
/* 231*/        else
/* 231*/            throw NotFoundException.getNotFoundInstance();
            }

            public final Result decodeRow(int i, BitArray bitarray, Map map)
                throws NotFoundException, FormatException, ChecksumException
            {
/* 239*/        map = map == null || !map.containsKey(DecodeHintType.ASSUME_GS1) ? 0 : 1;
                int ai[];
/* 241*/        int k = (ai = findStartPattern(bitarray))[2];
                ArrayList arraylist;
/* 244*/        (arraylist = new ArrayList(20)).add(Byte.valueOf((byte)k));
                byte byte0;
/* 248*/        switch(k)
                {
/* 250*/        case 103: // 'g'
/* 250*/            byte0 = 101;
                    break;

/* 253*/        case 104: // 'h'
/* 253*/            byte0 = 100;
                    break;

/* 256*/        case 105: // 'i'
/* 256*/            byte0 = 99;
                    break;

/* 259*/        default:
/* 259*/            throw FormatException.getFormatInstance();
                }
/* 262*/        boolean flag = false;
/* 263*/        boolean flag1 = false;
/* 265*/        StringBuilder stringbuilder = new StringBuilder(20);
/* 267*/        int l = ai[0];
/* 268*/        int i1 = ai[1];
/* 269*/        int ai1[] = new int[6];
/* 271*/        int j1 = 0;
/* 272*/        int k1 = 0;
/* 273*/        k = k;
/* 274*/        int l1 = 0;
/* 275*/        boolean flag2 = true;
/* 276*/        boolean flag3 = false;
/* 277*/        boolean flag4 = false;
/* 279*/        do
                {
/* 279*/            if(flag)
/* 281*/                break;
/* 281*/            boolean flag5 = flag1;
/* 282*/            flag1 = false;
/* 285*/            j1 = k1;
/* 288*/            k1 = decodeCode(bitarray, ai1, i1);
/* 290*/            arraylist.add(Byte.valueOf((byte)k1));
/* 293*/            if(k1 != 106)
/* 294*/                flag2 = true;
/* 298*/            if(k1 != 106)
                    {
/* 299*/                l1++;
/* 300*/                k += l1 * k1;
                    }
/* 304*/            l = i1;
/* 305*/            int ai2[] = ai1;
/* 305*/            for(int k2 = 0; k2 < 6; k2++)
                    {
/* 305*/                int l2 = ai2[k2];
/* 306*/                i1 += l2;
                    }

/* 310*/            switch(k1)
                    {
/* 314*/            case 103: // 'g'
/* 314*/            case 104: // 'h'
/* 314*/            case 105: // 'i'
/* 314*/                throw FormatException.getFormatInstance();
                    }
/* 317*/            switch(byte0)
                    {
/* 320*/            case 101: // 'e'
/* 320*/                if(k1 < 64)
                        {
/* 321*/                    if(flag4 == flag3)
/* 322*/                        stringbuilder.append((char)(k1 + 32));
/* 324*/                    else
/* 324*/                        stringbuilder.append((char)(k1 + 32 + 128));
/* 326*/                    flag4 = false;
                        } else
/* 327*/                if(k1 < 96)
                        {
/* 328*/                    if(flag4 == flag3)
/* 329*/                        stringbuilder.append((char)(k1 - 64));
/* 331*/                    else
/* 331*/                        stringbuilder.append((char)(k1 + 64));
/* 333*/                    flag4 = false;
                        } else
                        {
/* 337*/                    if(k1 != 106)
/* 338*/                        flag2 = false;
/* 340*/                    switch(k1)
                            {
/* 342*/                    case 102: // 'f'
/* 342*/                        if(map != 0)
/* 343*/                            if(stringbuilder.length() == 0)
/* 346*/                                stringbuilder.append("]C1");
/* 349*/                            else
/* 349*/                                stringbuilder.append('\035');
                                break;

/* 358*/                    case 101: // 'e'
/* 358*/                        if(!flag3 && flag4)
                                {
/* 359*/                            flag3 = true;
/* 360*/                            flag4 = false;
                                } else
/* 361*/                        if(flag3 && flag4)
                                {
/* 362*/                            flag3 = false;
/* 363*/                            flag4 = false;
                                } else
                                {
/* 365*/                            flag4 = true;
                                }
                                break;

/* 369*/                    case 98: // 'b'
/* 369*/                        flag1 = true;
/* 370*/                        byte0 = 100;
                                break;

/* 373*/                    case 100: // 'd'
/* 373*/                        byte0 = 100;
                                break;

/* 376*/                    case 99: // 'c'
/* 376*/                        byte0 = 99;
                                break;

/* 379*/                    case 106: // 'j'
/* 379*/                        flag = true;
                                break;
                            }
                        }
                        break;

/* 385*/            case 100: // 'd'
/* 385*/                if(k1 < 96)
                        {
/* 386*/                    if(flag4 == flag3)
/* 387*/                        stringbuilder.append((char)(k1 + 32));
/* 389*/                    else
/* 389*/                        stringbuilder.append((char)(k1 + 32 + 128));
/* 391*/                    flag4 = false;
                        } else
                        {
/* 393*/                    if(k1 != 106)
/* 394*/                        flag2 = false;
/* 396*/                    switch(k1)
                            {
/* 398*/                    case 102: // 'f'
/* 398*/                        if(map != 0)
/* 399*/                            if(stringbuilder.length() == 0)
/* 402*/                                stringbuilder.append("]C1");
/* 405*/                            else
/* 405*/                                stringbuilder.append('\035');
                                break;

/* 414*/                    case 100: // 'd'
/* 414*/                        if(!flag3 && flag4)
                                {
/* 415*/                            flag3 = true;
/* 416*/                            flag4 = false;
                                } else
/* 417*/                        if(flag3 && flag4)
                                {
/* 418*/                            flag3 = false;
/* 419*/                            flag4 = false;
                                } else
                                {
/* 421*/                            flag4 = true;
                                }
                                break;

/* 425*/                    case 98: // 'b'
/* 425*/                        flag1 = true;
/* 426*/                        byte0 = 101;
                                break;

/* 429*/                    case 101: // 'e'
/* 429*/                        byte0 = 101;
                                break;

/* 432*/                    case 99: // 'c'
/* 432*/                        byte0 = 99;
                                break;

/* 435*/                    case 106: // 'j'
/* 435*/                        flag = true;
                                break;
                            }
                        }
                        break;

/* 441*/            case 99: // 'c'
/* 441*/                if(k1 < 100)
                        {
/* 442*/                    if(k1 < 10)
/* 443*/                        stringbuilder.append('0');
/* 445*/                    stringbuilder.append(k1);
                        } else
                        {
/* 447*/                    if(k1 != 106)
/* 448*/                        flag2 = false;
/* 450*/                    switch(k1)
                            {
/* 239*/                    case 103: // 'g'
/* 239*/                    case 104: // 'h'
/* 239*/                    case 105: // 'i'
/* 239*/                    default:
                                break;

/* 452*/                    case 102: // 'f'
/* 452*/                        if(map == 0)
/* 453*/                            break;
/* 453*/                        if(stringbuilder.length() == 0)
/* 456*/                            stringbuilder.append("]C1");
/* 459*/                        else
/* 459*/                            stringbuilder.append('\035');
                                break;

/* 464*/                    case 101: // 'e'
/* 464*/                        byte0 = 101;
                                break;

/* 467*/                    case 100: // 'd'
/* 467*/                        byte0 = 100;
                                break;

/* 470*/                    case 106: // 'j'
/* 470*/                        flag = true;
                                break;
                            }
                        }
                        break;
                    }
/* 478*/            if(flag5)
/* 479*/                byte0 = ((byte)(byte0 != 101 ? 101 : 100));
                } while(true);
/* 484*/        int i2 = i1 - l;
/* 489*/        i1 = bitarray.getNextUnset(i1);
/* 490*/        if(!bitarray.isRange(i1, Math.min(bitarray.getSize(), i1 + (i1 - l) / 2), false))
/* 493*/            throw NotFoundException.getNotFoundInstance();
/* 497*/        if((k -= l1 * j1) % 103 != j1)
/* 500*/            throw ChecksumException.getChecksumInstance();
                int j2;
/* 504*/        if((j2 = stringbuilder.length()) == 0)
/* 507*/            throw NotFoundException.getNotFoundInstance();
/* 512*/        if(j2 > 0 && flag2)
/* 513*/            if(byte0 == 99)
/* 514*/                stringbuilder.delete(j2 - 2, j2);
/* 516*/            else
/* 516*/                stringbuilder.delete(j2 - 1, j2);
/* 520*/        bitarray = (float)(ai[1] + ai[0]) / 2.0F;
/* 521*/        float f = (float)l + (float)i2 / 2.0F;
                int i3;
/* 523*/        map = new byte[i3 = arraylist.size()];
/* 525*/        for(int j = 0; j < i3; j++)
/* 526*/            map[j] = ((Byte)arraylist.get(j)).byteValue();

/* 529*/        return new Result(stringbuilder.toString(), map, new ResultPoint[] {
/* 529*/            new ResultPoint(bitarray, i), new ResultPoint(f, i)
                }, BarcodeFormat.CODE_128);
            }

            static final int CODE_PATTERNS[][] = {
/*  39*/        {
/*  39*/            2, 1, 2, 2, 2, 2
                }, {
/*  39*/            2, 2, 2, 1, 2, 2
                }, {
/*  39*/            2, 2, 2, 2, 2, 1
                }, {
/*  39*/            1, 2, 1, 2, 2, 3
                }, {
/*  39*/            1, 2, 1, 3, 2, 2
                }, {
/*  39*/            1, 3, 1, 2, 2, 2
                }, {
/*  39*/            1, 2, 2, 2, 1, 3
                }, {
/*  39*/            1, 2, 2, 3, 1, 2
                }, {
/*  39*/            1, 3, 2, 2, 1, 2
                }, {
/*  39*/            2, 2, 1, 2, 1, 3
                }, {
/*  39*/            2, 2, 1, 3, 1, 2
                }, {
/*  39*/            2, 3, 1, 2, 1, 2
                }, {
/*  39*/            1, 1, 2, 2, 3, 2
                }, {
/*  39*/            1, 2, 2, 1, 3, 2
                }, {
/*  39*/            1, 2, 2, 2, 3, 1
                }, {
/*  39*/            1, 1, 3, 2, 2, 2
                }, {
/*  39*/            1, 2, 3, 1, 2, 2
                }, {
/*  39*/            1, 2, 3, 2, 2, 1
                }, {
/*  39*/            2, 2, 3, 2, 1, 1
                }, {
/*  39*/            2, 2, 1, 1, 3, 2
                }, {
/*  39*/            2, 2, 1, 2, 3, 1
                }, {
/*  39*/            2, 1, 3, 2, 1, 2
                }, {
/*  39*/            2, 2, 3, 1, 1, 2
                }, {
/*  39*/            3, 1, 2, 1, 3, 1
                }, {
/*  39*/            3, 1, 1, 2, 2, 2
                }, {
/*  39*/            3, 2, 1, 1, 2, 2
                }, {
/*  39*/            3, 2, 1, 2, 2, 1
                }, {
/*  39*/            3, 1, 2, 2, 1, 2
                }, {
/*  39*/            3, 2, 2, 1, 1, 2
                }, {
/*  39*/            3, 2, 2, 2, 1, 1
                }, {
/*  39*/            2, 1, 2, 1, 2, 3
                }, {
/*  39*/            2, 1, 2, 3, 2, 1
                }, {
/*  39*/            2, 3, 2, 1, 2, 1
                }, {
/*  39*/            1, 1, 1, 3, 2, 3
                }, {
/*  39*/            1, 3, 1, 1, 2, 3
                }, {
/*  39*/            1, 3, 1, 3, 2, 1
                }, {
/*  39*/            1, 1, 2, 3, 1, 3
                }, {
/*  39*/            1, 3, 2, 1, 1, 3
                }, {
/*  39*/            1, 3, 2, 3, 1, 1
                }, {
/*  39*/            2, 1, 1, 3, 1, 3
                }, {
/*  39*/            2, 3, 1, 1, 1, 3
                }, {
/*  39*/            2, 3, 1, 3, 1, 1
                }, {
/*  39*/            1, 1, 2, 1, 3, 3
                }, {
/*  39*/            1, 1, 2, 3, 3, 1
                }, {
/*  39*/            1, 3, 2, 1, 3, 1
                }, {
/*  39*/            1, 1, 3, 1, 2, 3
                }, {
/*  39*/            1, 1, 3, 3, 2, 1
                }, {
/*  39*/            1, 3, 3, 1, 2, 1
                }, {
/*  39*/            3, 1, 3, 1, 2, 1
                }, {
/*  39*/            2, 1, 1, 3, 3, 1
                }, {
/*  39*/            2, 3, 1, 1, 3, 1
                }, {
/*  39*/            2, 1, 3, 1, 1, 3
                }, {
/*  39*/            2, 1, 3, 3, 1, 1
                }, {
/*  39*/            2, 1, 3, 1, 3, 1
                }, {
/*  39*/            3, 1, 1, 1, 2, 3
                }, {
/*  39*/            3, 1, 1, 3, 2, 1
                }, {
/*  39*/            3, 3, 1, 1, 2, 1
                }, {
/*  39*/            3, 1, 2, 1, 1, 3
                }, {
/*  39*/            3, 1, 2, 3, 1, 1
                }, {
/*  39*/            3, 3, 2, 1, 1, 1
                }, {
/*  39*/            3, 1, 4, 1, 1, 1
                }, {
/*  39*/            2, 2, 1, 4, 1, 1
                }, {
/*  39*/            4, 3, 1, 1, 1, 1
                }, {
/*  39*/            1, 1, 1, 2, 2, 4
                }, {
/*  39*/            1, 1, 1, 4, 2, 2
                }, {
/*  39*/            1, 2, 1, 1, 2, 4
                }, {
/*  39*/            1, 2, 1, 4, 2, 1
                }, {
/*  39*/            1, 4, 1, 1, 2, 2
                }, {
/*  39*/            1, 4, 1, 2, 2, 1
                }, {
/*  39*/            1, 1, 2, 2, 1, 4
                }, {
/*  39*/            1, 1, 2, 4, 1, 2
                }, {
/*  39*/            1, 2, 2, 1, 1, 4
                }, {
/*  39*/            1, 2, 2, 4, 1, 1
                }, {
/*  39*/            1, 4, 2, 1, 1, 2
                }, {
/*  39*/            1, 4, 2, 2, 1, 1
                }, {
/*  39*/            2, 4, 1, 2, 1, 1
                }, {
/*  39*/            2, 2, 1, 1, 1, 4
                }, {
/*  39*/            4, 1, 3, 1, 1, 1
                }, {
/*  39*/            2, 4, 1, 1, 1, 2
                }, {
/*  39*/            1, 3, 4, 1, 1, 1
                }, {
/*  39*/            1, 1, 1, 2, 4, 2
                }, {
/*  39*/            1, 2, 1, 1, 4, 2
                }, {
/*  39*/            1, 2, 1, 2, 4, 1
                }, {
/*  39*/            1, 1, 4, 2, 1, 2
                }, {
/*  39*/            1, 2, 4, 1, 1, 2
                }, {
/*  39*/            1, 2, 4, 2, 1, 1
                }, {
/*  39*/            4, 1, 1, 2, 1, 2
                }, {
/*  39*/            4, 2, 1, 1, 1, 2
                }, {
/*  39*/            4, 2, 1, 2, 1, 1
                }, {
/*  39*/            2, 1, 2, 1, 4, 1
                }, {
/*  39*/            2, 1, 4, 1, 2, 1
                }, {
/*  39*/            4, 1, 2, 1, 2, 1
                }, {
/*  39*/            1, 1, 1, 1, 4, 3
                }, {
/*  39*/            1, 1, 1, 3, 4, 1
                }, {
/*  39*/            1, 3, 1, 1, 4, 1
                }, {
/*  39*/            1, 1, 4, 1, 1, 3
                }, {
/*  39*/            1, 1, 4, 3, 1, 1
                }, {
/*  39*/            4, 1, 1, 1, 1, 3
                }, {
/*  39*/            4, 1, 1, 3, 1, 1
                }, {
/*  39*/            1, 1, 3, 1, 4, 1
                }, {
/*  39*/            1, 1, 4, 1, 3, 1
                }, {
/*  39*/            3, 1, 1, 1, 4, 1
                }, {
/*  39*/            4, 1, 1, 1, 3, 1
                }, {
/*  39*/            2, 1, 1, 4, 1, 2
                }, {
/*  39*/            2, 1, 1, 2, 1, 4
                }, {
/*  39*/            2, 1, 1, 2, 3, 2
                }, {
/*  39*/            2, 3, 3, 1, 1, 1, 2
                }
            };
            private static final int MAX_AVG_VARIANCE = 64;
            private static final int MAX_INDIVIDUAL_VARIANCE = 179;
            private static final int CODE_SHIFT = 98;
            private static final int CODE_CODE_C = 99;
            private static final int CODE_CODE_B = 100;
            private static final int CODE_CODE_A = 101;
            private static final int CODE_FNC_1 = 102;
            private static final int CODE_FNC_2 = 97;
            private static final int CODE_FNC_3 = 96;
            private static final int CODE_FNC_4_A = 101;
            private static final int CODE_FNC_4_B = 100;
            private static final int CODE_START_A = 103;
            private static final int CODE_START_B = 104;
            private static final int CODE_START_C = 105;
            private static final int CODE_STOP = 106;

}
