// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Version.java

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;

public final class Version
{
    static final class ECB
    {

                final int getCount()
                {
/* 156*/            return count;
                }

                final int getDataCodewords()
                {
/* 160*/            return dataCodewords;
                }

                private final int count;
                private final int dataCodewords;

                private ECB(int i, int j)
                {
/* 151*/            count = i;
/* 152*/            dataCodewords = j;
                }

    }

    static final class ECBlocks
    {

                final int getECCodewords()
                {
/* 133*/            return ecCodewords;
                }

                final ECB[] getECBlocks()
                {
/* 137*/            return ecBlocks;
                }

                private final int ecCodewords;
                private final ECB ecBlocks[];

                private ECBlocks(int i, ECB ecb)
                {
/* 123*/            ecCodewords = i;
/* 124*/            ecBlocks = (new ECB[] {
/* 124*/                ecb
                    });
                }

                private ECBlocks(int i, ECB ecb, ECB ecb1)
                {
/* 128*/            ecCodewords = i;
/* 129*/            ecBlocks = (new ECB[] {
/* 129*/                ecb, ecb1
                    });
                }


    }


            private Version(int i, int j, int k, int l, int i1, ECBlocks ecblocks)
            {
/*  45*/        versionNumber = i;
/*  46*/        symbolSizeRows = j;
/*  47*/        symbolSizeColumns = k;
/*  48*/        dataRegionSizeRows = l;
/*  49*/        dataRegionSizeColumns = i1;
/*  50*/        ecBlocks = ecblocks;
/*  53*/        i = 0;
/*  54*/        j = ecblocks.getECCodewords();
/*  55*/        l = (k = k = ecblocks.getECBlocks()).length;
/*  56*/        for(i1 = 0; i1 < l; i1++)
                {
/*  56*/            ecblocks = k[i1];
/*  57*/            i += ecblocks.getCount() * (ecblocks.getDataCodewords() + j);
                }

/*  59*/        totalCodewords = i;
            }

            public final int getVersionNumber()
            {
/*  63*/        return versionNumber;
            }

            public final int getSymbolSizeRows()
            {
/*  67*/        return symbolSizeRows;
            }

            public final int getSymbolSizeColumns()
            {
/*  71*/        return symbolSizeColumns;
            }

            public final int getDataRegionSizeRows()
            {
/*  75*/        return dataRegionSizeRows;
            }

            public final int getDataRegionSizeColumns()
            {
/*  79*/        return dataRegionSizeColumns;
            }

            public final int getTotalCodewords()
            {
/*  83*/        return totalCodewords;
            }

            final ECBlocks getECBlocks()
            {
/*  87*/        return ecBlocks;
            }

            public static Version getVersionForDimensions(int i, int j)
                throws FormatException
            {
/*  99*/        if((i & 1) != 0 || (j & 1) != 0)
/* 100*/            throw FormatException.getFormatInstance();
                Version aversion[];
/* 103*/        int k = (aversion = VERSIONS).length;
/* 103*/        for(int l = 0; l < k; l++)
                {
                    Version version;
/* 103*/            if((version = aversion[l]).symbolSizeRows == i && version.symbolSizeColumns == j)
/* 105*/                return version;
                }

/* 109*/        throw FormatException.getFormatInstance();
            }

            public final String toString()
            {
/* 166*/        return String.valueOf(versionNumber);
            }

            private static Version[] buildVersions()
            {
/* 173*/        return (new Version[] {
/* 173*/            new Version(1, 10, 10, 8, 8, new ECBlocks(5, new ECB(1, 3))), new Version(2, 12, 12, 10, 10, new ECBlocks(7, new ECB(1, 5))), new Version(3, 14, 14, 12, 12, new ECBlocks(10, new ECB(1, 8))), new Version(4, 16, 16, 14, 14, new ECBlocks(12, new ECB(1, 12))), new Version(5, 18, 18, 16, 16, new ECBlocks(14, new ECB(1, 18))), new Version(6, 20, 20, 18, 18, new ECBlocks(18, new ECB(1, 22))), new Version(7, 22, 22, 20, 20, new ECBlocks(20, new ECB(1, 30))), new Version(8, 24, 24, 22, 22, new ECBlocks(24, new ECB(1, 36))), new Version(9, 26, 26, 24, 24, new ECBlocks(28, new ECB(1, 44))), new Version(10, 32, 32, 14, 14, new ECBlocks(36, new ECB(1, 62))), 
/* 173*/            new Version(11, 36, 36, 16, 16, new ECBlocks(42, new ECB(1, 86))), new Version(12, 40, 40, 18, 18, new ECBlocks(48, new ECB(1, 114))), new Version(13, 44, 44, 20, 20, new ECBlocks(56, new ECB(1, 144))), new Version(14, 48, 48, 22, 22, new ECBlocks(68, new ECB(1, 174))), new Version(15, 52, 52, 24, 24, new ECBlocks(42, new ECB(2, 102))), new Version(16, 64, 64, 14, 14, new ECBlocks(56, new ECB(2, 140))), new Version(17, 72, 72, 16, 16, new ECBlocks(36, new ECB(4, 92))), new Version(18, 80, 80, 18, 18, new ECBlocks(48, new ECB(4, 114))), new Version(19, 88, 88, 20, 20, new ECBlocks(56, new ECB(4, 144))), new Version(20, 96, 96, 22, 22, new ECBlocks(68, new ECB(4, 174))), 
/* 173*/            new Version(21, 104, 104, 24, 24, new ECBlocks(56, new ECB(6, 136))), new Version(22, 120, 120, 18, 18, new ECBlocks(68, new ECB(6, 175))), new Version(23, 132, 132, 20, 20, new ECBlocks(62, new ECB(8, 163))), new Version(24, 144, 144, 22, 22, new ECBlocks(62, new ECB(8, 156), new ECB(2, 155))), new Version(25, 8, 18, 6, 16, new ECBlocks(7, new ECB(1, 5))), new Version(26, 8, 32, 6, 14, new ECBlocks(11, new ECB(1, 10))), new Version(27, 12, 26, 10, 24, new ECBlocks(14, new ECB(1, 16))), new Version(28, 12, 36, 10, 16, new ECBlocks(18, new ECB(1, 22))), new Version(29, 16, 36, 14, 16, new ECBlocks(24, new ECB(1, 32))), new Version(30, 16, 48, 14, 22, new ECBlocks(28, new ECB(1, 49)))
                });
            }

            private static final Version VERSIONS[] = buildVersions();
            private final int versionNumber;
            private final int symbolSizeRows;
            private final int symbolSizeColumns;
            private final int dataRegionSizeRows;
            private final int dataRegionSizeColumns;
            private final ECBlocks ecBlocks;
            private final int totalCodewords;

}
