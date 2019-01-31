// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BitMatrixParser.java

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

final class BitMatrixParser
{

            BitMatrixParser(BitMatrix bitmatrix)
                throws FormatException
            {
                int i;
/*  36*/        if((i = bitmatrix.getHeight()) < 8 || i > 144 || (i & 1) != 0)
                {
/*  38*/            throw FormatException.getFormatInstance();
                } else
                {
/*  41*/            version = readVersion(bitmatrix);
/*  42*/            mappingBitMatrix = extractDataRegion(bitmatrix);
/*  43*/            readMappingMatrix = new BitMatrix(mappingBitMatrix.getWidth(), mappingBitMatrix.getHeight());
/*  44*/            return;
                }
            }

            final Version getVersion()
            {
/*  47*/        return version;
            }

            private static Version readVersion(BitMatrix bitmatrix)
                throws FormatException
            {
/*  62*/        int i = bitmatrix.getHeight();
/*  63*/        bitmatrix = bitmatrix.getWidth();
/*  64*/        return Version.getVersionForDimensions(i, bitmatrix);
            }

            final byte[] readCodewords()
                throws FormatException
            {
/*  77*/        byte abyte0[] = new byte[version.getTotalCodewords()];
/*  78*/        int i = 0;
/*  80*/        int j = 4;
/*  81*/        int k = 0;
/*  83*/        int l = mappingBitMatrix.getHeight();
/*  84*/        int i1 = mappingBitMatrix.getWidth();
/*  86*/        boolean flag = false;
/*  87*/        boolean flag1 = false;
/*  88*/        boolean flag2 = false;
/*  89*/        boolean flag3 = false;
/*  94*/        do
/*  94*/            if(j == l && k == 0 && !flag)
                    {
/*  95*/                abyte0[i++] = (byte)readCorner1(l, i1);
/*  96*/                j -= 2;
/*  97*/                k += 2;
/*  98*/                flag = true;
                    } else
/*  99*/            if(j == l - 2 && k == 0 && (i1 & 3) != 0 && !flag1)
                    {
/* 100*/                abyte0[i++] = (byte)readCorner2(l, i1);
/* 101*/                j -= 2;
/* 102*/                k += 2;
/* 103*/                flag1 = true;
                    } else
/* 104*/            if(j == l + 4 && k == 2 && (i1 & 7) == 0 && !flag2)
                    {
/* 105*/                abyte0[i++] = (byte)readCorner3(l, i1);
/* 106*/                j -= 2;
/* 107*/                k += 2;
/* 108*/                flag2 = true;
                    } else
/* 109*/            if(j == l - 2 && k == 0 && (i1 & 7) == 4 && !flag3)
                    {
/* 110*/                abyte0[i++] = (byte)readCorner4(l, i1);
/* 111*/                j -= 2;
/* 112*/                k += 2;
/* 113*/                flag3 = true;
                    } else
                    {
/* 117*/                do
                        {
/* 117*/                    if(j < l && k >= 0 && !readMappingMatrix.get(k, j))
/* 118*/                        abyte0[i++] = (byte)readUtah(j, k, l, i1);
/* 120*/                    j -= 2;
/* 121*/                    k += 2;
                        } while(j >= 0 && k < i1);
/* 123*/                j++;
/* 124*/                k += 3;
/* 128*/                do
                        {
/* 128*/                    if(j >= 0 && k < i1 && !readMappingMatrix.get(k, j))
/* 129*/                        abyte0[i++] = (byte)readUtah(j, k, l, i1);
/* 131*/                    j += 2;
/* 132*/                    k -= 2;
                        } while(j < l && k >= 0);
/* 134*/                j += 3;
/* 135*/                k++;
                    }
/* 137*/        while(j < l || k < i1);
/* 139*/        if(i != version.getTotalCodewords())
/* 140*/            throw FormatException.getFormatInstance();
/* 142*/        else
/* 142*/            return abyte0;
            }

            final boolean readModule(int i, int j, int k, int l)
            {
/* 156*/        if(i < 0)
                {
/* 157*/            i += k;
/* 158*/            j += 4 - (k + 4 & 7);
                }
/* 160*/        if(j < 0)
                {
/* 161*/            j += l;
/* 162*/            i += 4 - (l + 4 & 7);
                }
/* 164*/        readMappingMatrix.set(j, i);
/* 165*/        return mappingBitMatrix.get(j, i);
            }

            final int readUtah(int i, int j, int k, int l)
            {
/* 180*/        int i1 = 0;
/* 181*/        if(readModule(i - 2, j - 2, k, l))
/* 182*/            i1 = 1;
/* 184*/        i1 <<= 1;
/* 185*/        if(readModule(i - 2, j - 1, k, l))
/* 186*/            i1 |= 1;
/* 188*/        i1 <<= 1;
/* 189*/        if(readModule(i - 1, j - 2, k, l))
/* 190*/            i1 |= 1;
/* 192*/        i1 <<= 1;
/* 193*/        if(readModule(i - 1, j - 1, k, l))
/* 194*/            i1 |= 1;
/* 196*/        i1 <<= 1;
/* 197*/        if(readModule(i - 1, j, k, l))
/* 198*/            i1 |= 1;
/* 200*/        i1 <<= 1;
/* 201*/        if(readModule(i, j - 2, k, l))
/* 202*/            i1 |= 1;
/* 204*/        i1 <<= 1;
/* 205*/        if(readModule(i, j - 1, k, l))
/* 206*/            i1 |= 1;
/* 208*/        i1 <<= 1;
/* 209*/        if(readModule(i, j, k, l))
/* 210*/            i1 |= 1;
/* 212*/        return i1;
            }

            final int readCorner1(int i, int j)
            {
/* 225*/        int k = 0;
/* 226*/        if(readModule(i - 1, 0, i, j))
/* 227*/            k = 1;
/* 229*/        k <<= 1;
/* 230*/        if(readModule(i - 1, 1, i, j))
/* 231*/            k |= 1;
/* 233*/        k <<= 1;
/* 234*/        if(readModule(i - 1, 2, i, j))
/* 235*/            k |= 1;
/* 237*/        k <<= 1;
/* 238*/        if(readModule(0, j - 2, i, j))
/* 239*/            k |= 1;
/* 241*/        k <<= 1;
/* 242*/        if(readModule(0, j - 1, i, j))
/* 243*/            k |= 1;
/* 245*/        k <<= 1;
/* 246*/        if(readModule(1, j - 1, i, j))
/* 247*/            k |= 1;
/* 249*/        k <<= 1;
/* 250*/        if(readModule(2, j - 1, i, j))
/* 251*/            k |= 1;
/* 253*/        k <<= 1;
/* 254*/        if(readModule(3, j - 1, i, j))
/* 255*/            k |= 1;
/* 257*/        return k;
            }

            final int readCorner2(int i, int j)
            {
/* 270*/        int k = 0;
/* 271*/        if(readModule(i - 3, 0, i, j))
/* 272*/            k = 1;
/* 274*/        k <<= 1;
/* 275*/        if(readModule(i - 2, 0, i, j))
/* 276*/            k |= 1;
/* 278*/        k <<= 1;
/* 279*/        if(readModule(i - 1, 0, i, j))
/* 280*/            k |= 1;
/* 282*/        k <<= 1;
/* 283*/        if(readModule(0, j - 4, i, j))
/* 284*/            k |= 1;
/* 286*/        k <<= 1;
/* 287*/        if(readModule(0, j - 3, i, j))
/* 288*/            k |= 1;
/* 290*/        k <<= 1;
/* 291*/        if(readModule(0, j - 2, i, j))
/* 292*/            k |= 1;
/* 294*/        k <<= 1;
/* 295*/        if(readModule(0, j - 1, i, j))
/* 296*/            k |= 1;
/* 298*/        k <<= 1;
/* 299*/        if(readModule(1, j - 1, i, j))
/* 300*/            k |= 1;
/* 302*/        return k;
            }

            final int readCorner3(int i, int j)
            {
/* 315*/        int k = 0;
/* 316*/        if(readModule(i - 1, 0, i, j))
/* 317*/            k = 1;
/* 319*/        k <<= 1;
/* 320*/        if(readModule(i - 1, j - 1, i, j))
/* 321*/            k |= 1;
/* 323*/        k <<= 1;
/* 324*/        if(readModule(0, j - 3, i, j))
/* 325*/            k |= 1;
/* 327*/        k <<= 1;
/* 328*/        if(readModule(0, j - 2, i, j))
/* 329*/            k |= 1;
/* 331*/        k <<= 1;
/* 332*/        if(readModule(0, j - 1, i, j))
/* 333*/            k |= 1;
/* 335*/        k <<= 1;
/* 336*/        if(readModule(1, j - 3, i, j))
/* 337*/            k |= 1;
/* 339*/        k <<= 1;
/* 340*/        if(readModule(1, j - 2, i, j))
/* 341*/            k |= 1;
/* 343*/        k <<= 1;
/* 344*/        if(readModule(1, j - 1, i, j))
/* 345*/            k |= 1;
/* 347*/        return k;
            }

            final int readCorner4(int i, int j)
            {
/* 360*/        int k = 0;
/* 361*/        if(readModule(i - 3, 0, i, j))
/* 362*/            k = 1;
/* 364*/        k <<= 1;
/* 365*/        if(readModule(i - 2, 0, i, j))
/* 366*/            k |= 1;
/* 368*/        k <<= 1;
/* 369*/        if(readModule(i - 1, 0, i, j))
/* 370*/            k |= 1;
/* 372*/        k <<= 1;
/* 373*/        if(readModule(0, j - 2, i, j))
/* 374*/            k |= 1;
/* 376*/        k <<= 1;
/* 377*/        if(readModule(0, j - 1, i, j))
/* 378*/            k |= 1;
/* 380*/        k <<= 1;
/* 381*/        if(readModule(1, j - 1, i, j))
/* 382*/            k |= 1;
/* 384*/        k <<= 1;
/* 385*/        if(readModule(2, j - 1, i, j))
/* 386*/            k |= 1;
/* 388*/        k <<= 1;
/* 389*/        if(readModule(3, j - 1, i, j))
/* 390*/            k |= 1;
/* 392*/        return k;
            }

            final BitMatrix extractDataRegion(BitMatrix bitmatrix)
            {
/* 403*/        int i = version.getSymbolSizeRows();
/* 404*/        int j = version.getSymbolSizeColumns();
/* 406*/        if(bitmatrix.getHeight() != i)
/* 407*/            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
/* 410*/        int k = version.getDataRegionSizeRows();
/* 411*/        int l = version.getDataRegionSizeColumns();
/* 413*/        i /= k;
/* 414*/        j /= l;
/* 416*/        int i1 = i * k;
/* 417*/        int j1 = j * l;
/* 419*/        BitMatrix bitmatrix1 = new BitMatrix(j1, i1);
/* 420*/        for(int k1 = 0; k1 < i; k1++)
                {
/* 421*/            int l1 = k1 * k;
/* 422*/            for(int i2 = 0; i2 < j; i2++)
                    {
/* 423*/                int j2 = i2 * l;
/* 424*/                for(int k2 = 0; k2 < k; k2++)
                        {
/* 425*/                    int l2 = k1 * (k + 2) + 1 + k2;
/* 426*/                    int i3 = l1 + k2;
/* 427*/                    for(int j3 = 0; j3 < l; j3++)
                            {
/* 428*/                        int k3 = i2 * (l + 2) + 1 + j3;
/* 429*/                        if(bitmatrix.get(k3, l2))
                                {
/* 430*/                            int l3 = j2 + j3;
/* 431*/                            bitmatrix1.set(l3, i3);
                                }
                            }

                        }

                    }

                }

/* 437*/        return bitmatrix1;
            }

            private final BitMatrix mappingBitMatrix;
            private final BitMatrix readMappingMatrix;
            private final Version version;
}
