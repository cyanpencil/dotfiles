// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Encoder.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

// Referenced classes of package com.google.zxing.aztec.encoder:
//            AztecCode, HighLevelEncoder

public final class Encoder
{

            private Encoder()
            {
            }

            public static AztecCode encode(byte abyte0[])
            {
/*  51*/        return encode(abyte0, 33, 0);
            }

            public static AztecCode encode(byte abyte0[], int i, int j)
            {
/*  65*/        i = ((abyte0 = (new HighLevelEncoder(abyte0)).encode()).getSize() * i) / 100 + 11;
/*  69*/        int k = abyte0.getSize() + i;
                boolean flag;
                int l;
                int i1;
                Object obj;
/*  75*/        if(j != 0)
                {
/*  76*/            flag = j < 0;
/*  77*/            if((l = Math.abs(j)) > (flag ? 4 : 32))
/*  79*/                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[] {
/*  79*/                    Integer.valueOf(j)
                        }));
/*  82*/            j = totalBitsInLayer(l, flag);
/*  83*/            i1 = WORD_SIZE[l];
/*  84*/            int k2 = j - j % i1;
/*  85*/            if(((BitArray) (obj = stuffBits(abyte0, i1))).getSize() + i > k2)
/*  87*/                throw new IllegalArgumentException("Data to large for user specified layer");
/*  89*/            if(flag && ((BitArray) (obj)).getSize() > i1 << 6)
/*  91*/                throw new IllegalArgumentException("Data to large for user specified layer");
                } else
                {
/*  94*/            i1 = 0;
/*  95*/            obj = null;
/*  99*/            int l2 = 0;
/* 100*/            do
                    {
/* 100*/                if(l2 > 32)
/* 101*/                    throw new IllegalArgumentException("Data too large for an Aztec code");
/* 103*/                j = totalBitsInLayer(l = (flag = l2 <= 3) ? l2 + 1 : l2, flag);
/* 106*/                if(k <= j)
                        {
/* 111*/                    if(i1 != WORD_SIZE[l])
                            {
/* 112*/                        i1 = WORD_SIZE[l];
/* 113*/                        obj = stuffBits(abyte0, i1);
                            }
/* 115*/                    int i3 = j - j % i1;
/* 116*/                    if((!flag || ((BitArray) (obj)).getSize() <= i1 << 6) && ((BitArray) (obj)).getSize() + i <= i3)
/*  99*/                        break;
                        }
/*  99*/                l2++;
                    } while(true);
                }
/* 125*/        BitArray bitarray = generateCheckWords(((BitArray) (obj)), j, i1);
/* 128*/        int j3 = ((BitArray) (obj)).getSize() / i1;
/* 129*/        abyte0 = generateModeMessage(flag, l, j3);
/* 132*/        j = new int[i = flag ? 11 + (l << 2) : 14 + (l << 2)];
/* 135*/        if(flag)
                {
/* 137*/            k = i;
/* 138*/            for(int j1 = 0; j1 < j.length; j1++)
/* 139*/                j[j1] = j1;

                } else
                {
/* 142*/            k = i + 1 + 2 * ((i / 2 - 1) / 15);
/* 143*/            int k1 = i / 2;
/* 144*/            int l1 = k / 2;
/* 145*/            for(int k3 = 0; k3 < k1; k3++)
                    {
/* 146*/                int j4 = k3 + k3 / 15;
/* 147*/                j[k1 - k3 - 1] = l1 - j4 - 1;
/* 148*/                j[k1 + k3] = l1 + j4 + 1;
                    }

                }
/* 151*/        BitMatrix bitmatrix = new BitMatrix(k);
/* 154*/        int i2 = 0;
/* 154*/        int l3 = 0;
/* 154*/        for(; i2 < l; i2++)
                {
/* 155*/            int k4 = flag ? (l - i2 << 2) + 9 : (l - i2 << 2) + 12;
/* 156*/            for(int i5 = 0; i5 < k4; i5++)
                    {
/* 157*/                int j5 = i5 << 1;
/* 158*/                for(int k5 = 0; k5 < 2; k5++)
                        {
/* 159*/                    if(bitarray.get(l3 + j5 + k5))
/* 160*/                        bitmatrix.set(j[(i2 << 1) + k5], j[(i2 << 1) + i5]);
/* 162*/                    if(bitarray.get(l3 + (k4 << 1) + j5 + k5))
/* 163*/                        bitmatrix.set(j[(i2 << 1) + i5], j[i - 1 - (i2 << 1) - k5]);
/* 165*/                    if(bitarray.get(l3 + (k4 << 2) + j5 + k5))
/* 166*/                        bitmatrix.set(j[i - 1 - (i2 << 1) - k5], j[i - 1 - (i2 << 1) - i5]);
/* 168*/                    if(bitarray.get(l3 + k4 * 6 + j5 + k5))
/* 169*/                        bitmatrix.set(j[i - 1 - (i2 << 1) - i5], j[(i2 << 1) + k5]);
                        }

                    }

/* 173*/            l3 += k4 << 3;
                }

/* 177*/        drawModeMessage(bitmatrix, flag, k, abyte0);
/* 180*/        if(flag)
                {
/* 181*/            drawBullsEye(bitmatrix, k / 2, 5);
                } else
                {
/* 183*/            drawBullsEye(bitmatrix, k / 2, 7);
/* 184*/            int j2 = 0;
/* 184*/            for(int i4 = 0; j2 < i / 2 - 1; i4 += 16)
                    {
/* 185*/                for(int l4 = k / 2 & 1; l4 < k; l4 += 2)
                        {
/* 186*/                    bitmatrix.set(k / 2 - i4, l4);
/* 187*/                    bitmatrix.set(k / 2 + i4, l4);
/* 188*/                    bitmatrix.set(l4, k / 2 - i4);
/* 189*/                    bitmatrix.set(l4, k / 2 + i4);
                        }

/* 184*/                j2 += 15;
                    }

                }
/* 194*/        (j2 = new AztecCode()).setCompact(flag);
/* 196*/        j2.setSize(k);
/* 197*/        j2.setLayers(l);
/* 198*/        j2.setCodeWords(j3);
/* 199*/        j2.setMatrix(bitmatrix);
/* 200*/        return j2;
            }

            private static void drawBullsEye(BitMatrix bitmatrix, int i, int j)
            {
/* 204*/        for(int k = 0; k < j; k += 2)
                {
/* 205*/            for(int l = i - k; l <= i + k; l++)
                    {
/* 206*/                bitmatrix.set(l, i - k);
/* 207*/                bitmatrix.set(l, i + k);
/* 208*/                bitmatrix.set(i - k, l);
/* 209*/                bitmatrix.set(i + k, l);
                    }

                }

/* 212*/        bitmatrix.set(i - j, i - j);
/* 213*/        bitmatrix.set((i - j) + 1, i - j);
/* 214*/        bitmatrix.set(i - j, (i - j) + 1);
/* 215*/        bitmatrix.set(i + j, i - j);
/* 216*/        bitmatrix.set(i + j, (i - j) + 1);
/* 217*/        bitmatrix.set(i + j, (i + j) - 1);
            }

            static BitArray generateModeMessage(boolean flag, int i, int j)
            {
/* 221*/        BitArray bitarray = new BitArray();
/* 222*/        if(flag)
                {
/* 223*/            bitarray.appendBits(i - 1, 2);
/* 224*/            bitarray.appendBits(j - 1, 6);
/* 225*/            bitarray = generateCheckWords(bitarray, 28, 4);
                } else
                {
/* 227*/            bitarray.appendBits(i - 1, 5);
/* 228*/            bitarray.appendBits(j - 1, 11);
/* 229*/            bitarray = generateCheckWords(bitarray, 40, 4);
                }
/* 231*/        return bitarray;
            }

            private static void drawModeMessage(BitMatrix bitmatrix, boolean flag, int i, BitArray bitarray)
            {
/* 235*/        i /= 2;
/* 236*/        if(flag)
                {
/* 237*/            for(flag = false; flag < 7; flag++)
                    {
/* 238*/                int j = (i - 3) + flag;
/* 239*/                if(bitarray.get(flag))
/* 240*/                    bitmatrix.set(j, i - 5);
/* 242*/                if(bitarray.get(flag + 7))
/* 243*/                    bitmatrix.set(i + 5, j);
/* 245*/                if(bitarray.get(20 - flag))
/* 246*/                    bitmatrix.set(j, i + 5);
/* 248*/                if(bitarray.get(27 - flag))
/* 249*/                    bitmatrix.set(i - 5, j);
                    }

/* 237*/            return;
                }
/* 253*/        for(flag = false; flag < 10; flag++)
                {
/* 254*/            int k = (i - 5) + flag + flag / 5;
/* 255*/            if(bitarray.get(flag))
/* 256*/                bitmatrix.set(k, i - 7);
/* 258*/            if(bitarray.get(flag + 10))
/* 259*/                bitmatrix.set(i + 7, k);
/* 261*/            if(bitarray.get(29 - flag))
/* 262*/                bitmatrix.set(k, i + 7);
/* 264*/            if(bitarray.get(39 - flag))
/* 265*/                bitmatrix.set(i - 7, k);
                }

            }

            private static BitArray generateCheckWords(BitArray bitarray, int i, int j)
            {
/* 273*/        int k = bitarray.getSize() / j;
/* 274*/        ReedSolomonEncoder reedsolomonencoder = new ReedSolomonEncoder(getGF(j));
/* 275*/        int i1 = i / j;
/* 276*/        bitarray = bitsToWords(bitarray, j, i1);
/* 277*/        reedsolomonencoder.encode(bitarray, i1 - k);
/* 278*/        i %= j;
                BitArray bitarray1;
/* 279*/        (bitarray1 = new BitArray()).appendBits(0, i);
/* 281*/        i = (bitarray = bitarray).length;
/* 281*/        for(int l = 0; l < i; l++)
                {
/* 281*/            int j1 = bitarray[l];
/* 282*/            bitarray1.appendBits(j1, j);
                }

/* 284*/        return bitarray1;
            }

            private static int[] bitsToWords(BitArray bitarray, int i, int j)
            {
/* 288*/        j = new int[j];
/* 291*/        int k = 0;
/* 291*/        for(int l = bitarray.getSize() / i; k < l; k++)
                {
/* 292*/            int i1 = 0;
/* 293*/            for(int j1 = 0; j1 < i; j1++)
/* 294*/                i1 |= bitarray.get(k * i + j1) ? 1 << i - j1 - 1 : 0;

/* 296*/            j[k] = i1;
                }

/* 298*/        return j;
            }

            private static GenericGF getGF(int i)
            {
/* 302*/        switch(i)
                {
/* 304*/        case 4: // '\004'
/* 304*/            return GenericGF.AZTEC_PARAM;

/* 306*/        case 6: // '\006'
/* 306*/            return GenericGF.AZTEC_DATA_6;

/* 308*/        case 8: // '\b'
/* 308*/            return GenericGF.AZTEC_DATA_8;

/* 310*/        case 10: // '\n'
/* 310*/            return GenericGF.AZTEC_DATA_10;

/* 312*/        case 12: // '\f'
/* 312*/            return GenericGF.AZTEC_DATA_12;

/* 314*/        case 5: // '\005'
/* 314*/        case 7: // '\007'
/* 314*/        case 9: // '\t'
/* 314*/        case 11: // '\013'
/* 314*/        default:
/* 314*/            return null;
                }
            }

            static BitArray stuffBits(BitArray bitarray, int i)
            {
/* 319*/        BitArray bitarray1 = new BitArray();
/* 321*/        int j = bitarray.getSize();
/* 322*/        int k = (1 << i) - 2;
/* 323*/        for(int l = 0; l < j; l += i)
                {
/* 324*/            int i1 = 0;
/* 325*/            for(int j1 = 0; j1 < i; j1++)
/* 326*/                if(l + j1 >= j || bitarray.get(l + j1))
/* 327*/                    i1 |= 1 << i - 1 - j1;

/* 330*/            if((i1 & k) == k)
                    {
/* 331*/                bitarray1.appendBits(i1 & k, i);
/* 332*/                l--;
/* 332*/                continue;
                    }
/* 333*/            if((i1 & k) == 0)
                    {
/* 334*/                bitarray1.appendBits(i1 | 1, i);
/* 335*/                l--;
                    } else
                    {
/* 337*/                bitarray1.appendBits(i1, i);
                    }
                }

/* 340*/        return bitarray1;
            }

            private static int totalBitsInLayer(int i, boolean flag)
            {
/* 344*/        return ((flag ? 88 : 112) + i * 16) * i;
            }

            public static final int DEFAULT_EC_PERCENT = 33;
            public static final int DEFAULT_AZTEC_LAYERS = 0;
            private static final int MAX_NB_BITS = 32;
            private static final int MAX_NB_BITS_COMPACT = 4;
            private static final int WORD_SIZE[] = {
/*  36*/        4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 
/*  36*/        10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 
/*  36*/        10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 
/*  36*/        12, 12, 12
            };

}
