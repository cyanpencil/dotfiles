// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BitMatrixParser.java

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            DataMask, FormatInformation, Version

final class BitMatrixParser
{

            BitMatrixParser(BitMatrix bitmatrix)
                throws FormatException
            {
                int i;
/*  37*/        if((i = bitmatrix.getHeight()) < 21 || (i & 3) != 1)
                {
/*  39*/            throw FormatException.getFormatInstance();
                } else
                {
/*  41*/            bitMatrix = bitmatrix;
/*  42*/            return;
                }
            }

            final FormatInformation readFormatInformation()
                throws FormatException
            {
/*  53*/        if(parsedFormatInfo != null)
/*  54*/            return parsedFormatInfo;
/*  58*/        int i = 0;
/*  59*/        for(int j = 0; j < 6; j++)
/*  60*/            i = copyBit(j, 8, i);

/*  63*/        i = copyBit(7, 8, i);
/*  64*/        i = copyBit(8, 8, i);
/*  65*/        i = copyBit(8, 7, i);
/*  67*/        for(int k = 5; k >= 0; k--)
/*  68*/            i = copyBit(8, k, i);

/*  72*/        int l = bitMatrix.getHeight();
/*  73*/        int i1 = 0;
/*  74*/        int j1 = l - 7;
/*  75*/        for(int k1 = l - 1; k1 >= j1; k1--)
/*  76*/            i1 = copyBit(8, k1, i1);

/*  78*/        for(int l1 = l - 8; l1 < l; l1++)
/*  79*/            i1 = copyBit(l1, 8, i1);

/*  82*/        parsedFormatInfo = FormatInformation.decodeFormatInformation(i, i1);
/*  83*/        if(parsedFormatInfo != null)
/*  84*/            return parsedFormatInfo;
/*  86*/        else
/*  86*/            throw FormatException.getFormatInstance();
            }

            final Version readVersion()
                throws FormatException
            {
/*  98*/        if(parsedVersion != null)
/*  99*/            return parsedVersion;
                int i;
                int j;
/* 102*/        if((j = (i = bitMatrix.getHeight()) - 17 >> 2) <= 6)
/* 106*/            return Version.getVersionForNumber(j);
/* 110*/        j = 0;
/* 111*/        int k = i - 11;
/* 112*/        for(int l = 5; l >= 0; l--)
                {
/* 113*/            for(int j1 = i - 9; j1 >= k; j1--)
/* 114*/                j = copyBit(j1, l, j);

                }

                Version version;
/* 118*/        if((version = Version.decodeVersionInformation(j)) != null && version.getDimensionForVersion() == i)
                {
/* 120*/            parsedVersion = version;
/* 121*/            return version;
                }
/* 125*/        j = 0;
/* 126*/        for(int k1 = 5; k1 >= 0; k1--)
                {
/* 127*/            for(int i1 = i - 9; i1 >= k; i1--)
/* 128*/                j = copyBit(k1, i1, j);

                }

/* 132*/        if((i1 = Version.decodeVersionInformation(j)) != null && i1.getDimensionForVersion() == i)
                {
/* 134*/            parsedVersion = i1;
/* 135*/            return i1;
                } else
                {
/* 137*/            throw FormatException.getFormatInstance();
                }
            }

            private int copyBit(int i, int j, int k)
            {
/* 141*/        if((i = mirror ? ((int) (bitMatrix.get(j, i))) : ((int) (bitMatrix.get(i, j)))) != 0)
/* 142*/            return k << 1 | 1;
/* 142*/        else
/* 142*/            return k << 1;
            }

            final byte[] readCodewords()
                throws FormatException
            {
/* 155*/        Object obj = readFormatInformation();
/* 156*/        Version version = readVersion();
/* 160*/        obj = DataMask.forReference(((FormatInformation) (obj)).getDataMask());
/* 161*/        int i = bitMatrix.getHeight();
/* 162*/        ((DataMask) (obj)).unmaskBitMatrix(bitMatrix, i);
/* 164*/        obj = version.buildFunctionPattern();
/* 166*/        boolean flag = true;
/* 167*/        byte abyte0[] = new byte[version.getTotalCodewords()];
/* 168*/        int j = 0;
/* 169*/        int k = 0;
/* 170*/        int l = 0;
/* 172*/        for(int i1 = i - 1; i1 > 0; i1 -= 2)
                {
/* 173*/            if(i1 == 6)
/* 176*/                i1--;
/* 179*/            for(int j1 = 0; j1 < i; j1++)
                    {
/* 180*/                int k1 = flag ? i - 1 - j1 : j1;
/* 181*/                for(int l1 = 0; l1 < 2; l1++)
                        {
/* 183*/                    if(((BitMatrix) (obj)).get(i1 - l1, k1))
/* 185*/                        continue;
/* 185*/                    l++;
/* 186*/                    k <<= 1;
/* 187*/                    if(bitMatrix.get(i1 - l1, k1))
/* 188*/                        k |= 1;
/* 191*/                    if(l == 8)
                            {
/* 192*/                        abyte0[j++] = (byte)k;
/* 193*/                        l = 0;
/* 194*/                        k = 0;
                            }
                        }

                    }

/* 199*/            flag ^= true;
                }

/* 201*/        if(j != version.getTotalCodewords())
/* 202*/            throw FormatException.getFormatInstance();
/* 204*/        else
/* 204*/            return abyte0;
            }

            final void remask()
            {
/* 211*/        if(parsedFormatInfo == null)
                {
/* 212*/            return;
                } else
                {
/* 214*/            DataMask datamask = DataMask.forReference(parsedFormatInfo.getDataMask());
/* 215*/            int i = bitMatrix.getHeight();
/* 216*/            datamask.unmaskBitMatrix(bitMatrix, i);
/* 217*/            return;
                }
            }

            final void setMirror(boolean flag)
            {
/* 228*/        parsedVersion = null;
/* 229*/        parsedFormatInfo = null;
/* 230*/        mirror = flag;
            }

            final void mirror()
            {
/* 235*/        for(int i = 0; i < bitMatrix.getWidth(); i++)
                {
/* 236*/            for(int j = i + 1; j < bitMatrix.getHeight(); j++)
/* 237*/                if(bitMatrix.get(i, j) != bitMatrix.get(j, i))
                        {
/* 238*/                    bitMatrix.flip(j, i);
/* 239*/                    bitMatrix.flip(i, j);
                        }

                }

            }

            private final BitMatrix bitMatrix;
            private Version parsedVersion;
            private FormatInformation parsedFormatInfo;
            private boolean mirror;
}
