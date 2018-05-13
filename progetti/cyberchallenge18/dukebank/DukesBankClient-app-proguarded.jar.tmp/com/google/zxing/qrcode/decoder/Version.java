// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Version.java

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            ErrorCorrectionLevel, FormatInformation

public final class Version
{
    public static final class ECB
    {

                public final int getCount()
                {
/* 229*/            return count;
                }

                public final int getDataCodewords()
                {
/* 233*/            return dataCodewords;
                }

                private final int count;
                private final int dataCodewords;

                ECB(int i, int j)
                {
/* 224*/            count = i;
/* 225*/            dataCodewords = j;
                }
    }

    public static final class ECBlocks
    {

                public final int getECCodewordsPerBlock()
                {
/* 194*/            return ecCodewordsPerBlock;
                }

                public final int getNumBlocks()
                {
/* 198*/            int i = 0;
                    ECB aecb[];
/* 199*/            int j = (aecb = ecBlocks).length;
/* 199*/            for(int k = 0; k < j; k++)
                    {
/* 199*/                ECB ecb = aecb[k];
/* 200*/                i += ecb.getCount();
                    }

/* 202*/            return i;
                }

                public final int getTotalECCodewords()
                {
/* 206*/            return ecCodewordsPerBlock * getNumBlocks();
                }

                public final ECB[] getECBlocks()
                {
/* 210*/            return ecBlocks;
                }

                private final int ecCodewordsPerBlock;
                private final ECB ecBlocks[];

                transient ECBlocks(int i, ECB aecb[])
                {
/* 189*/            ecCodewordsPerBlock = i;
/* 190*/            ecBlocks = aecb;
                }
    }


            private transient Version(int i, int ai[], ECBlocks aecblocks[])
            {
/*  53*/        versionNumber = i;
/*  54*/        alignmentPatternCenters = ai;
/*  55*/        ecBlocks = aecblocks;
/*  56*/        i = 0;
/*  57*/        ai = aecblocks[0].getECCodewordsPerBlock();
/*  58*/        int j = (aecblocks = aecblocks = aecblocks[0].getECBlocks()).length;
/*  59*/        for(int k = 0; k < j; k++)
                {
/*  59*/            ECBlocks ecblocks = aecblocks[k];
/*  60*/            i += ecblocks.getCount() * (ecblocks.getDataCodewords() + ai);
                }

/*  62*/        totalCodewords = i;
            }

            public final int getVersionNumber()
            {
/*  66*/        return versionNumber;
            }

            public final int[] getAlignmentPatternCenters()
            {
/*  70*/        return alignmentPatternCenters;
            }

            public final int getTotalCodewords()
            {
/*  74*/        return totalCodewords;
            }

            public final int getDimensionForVersion()
            {
/*  78*/        return 17 + 4 * versionNumber;
            }

            public final ECBlocks getECBlocksForLevel(ErrorCorrectionLevel errorcorrectionlevel)
            {
/*  82*/        return ecBlocks[errorcorrectionlevel.ordinal()];
            }

            public static Version getProvisionalVersionForDimension(int i)
                throws FormatException
            {
/*  93*/        if(i % 4 != 1)
/*  94*/            throw FormatException.getFormatInstance();
/*  97*/        return getVersionForNumber(i - 17 >> 2);
/*  98*/        JVM INSTR pop ;
/*  99*/        throw FormatException.getFormatInstance();
            }

            public static Version getVersionForNumber(int i)
            {
/* 104*/        if(i <= 0 || i > 40)
/* 105*/            throw new IllegalArgumentException();
/* 107*/        else
/* 107*/            return VERSIONS[i - 1];
            }

            static Version decodeVersionInformation(int i)
            {
/* 111*/        int j = 0x7fffffff;
/* 112*/        int k = 0;
/* 113*/        for(int l = 0; l < VERSION_DECODE_INFO.length; l++)
                {
                    int i1;
/* 114*/            if((i1 = VERSION_DECODE_INFO[l]) == i)
/* 117*/                return getVersionForNumber(l + 7);
/* 121*/            if((i1 = FormatInformation.numBitsDiffering(i, i1)) < j)
                    {
/* 123*/                k = l + 7;
/* 124*/                j = i1;
                    }
                }

/* 129*/        if(j <= 3)
/* 130*/            return getVersionForNumber(k);
/* 133*/        else
/* 133*/            return null;
            }

            final BitMatrix buildFunctionPattern()
            {
/* 140*/        int i = getDimensionForVersion();
                BitMatrix bitmatrix;
/* 141*/        (bitmatrix = new BitMatrix(i)).setRegion(0, 0, 9, 9);
/* 146*/        bitmatrix.setRegion(i - 8, 0, 8, 9);
/* 148*/        bitmatrix.setRegion(0, i - 8, 9, 8);
/* 151*/        int j = alignmentPatternCenters.length;
/* 152*/        for(int k = 0; k < j; k++)
                {
/* 153*/            int l = alignmentPatternCenters[k] - 2;
/* 154*/            for(int i1 = 0; i1 < j; i1++)
/* 155*/                if((k != 0 || i1 != 0 && i1 != j - 1) && (k != j - 1 || i1 != 0))
/* 159*/                    bitmatrix.setRegion(alignmentPatternCenters[i1] - 2, l, 5, 5);

                }

/* 164*/        bitmatrix.setRegion(6, 9, 1, i - 17);
/* 166*/        bitmatrix.setRegion(9, 6, i - 17, 1);
/* 168*/        if(versionNumber > 6)
                {
/* 170*/            bitmatrix.setRegion(i - 11, 0, 3, 6);
/* 172*/            bitmatrix.setRegion(0, i - 11, 6, 3);
                }
/* 175*/        return bitmatrix;
            }

            public final String toString()
            {
/* 239*/        return String.valueOf(versionNumber);
            }

            private static Version[] buildVersions()
            {
/* 246*/        return (new Version[] {
/* 246*/            new Version(1, new int[0], new ECBlocks[] {
/* 246*/                new ECBlocks(7, new ECB[] {
/* 246*/                    new ECB(1, 19)
                        }), new ECBlocks(10, new ECB[] {
/* 246*/                    new ECB(1, 16)
                        }), new ECBlocks(13, new ECB[] {
/* 246*/                    new ECB(1, 13)
                        }), new ECBlocks(17, new ECB[] {
/* 246*/                    new ECB(1, 9)
                        })
                    }), new Version(2, new int[] {
/* 246*/                6, 18
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(10, new ECB[] {
/* 246*/                    new ECB(1, 34)
                        }), new ECBlocks(16, new ECB[] {
/* 246*/                    new ECB(1, 28)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(1, 22)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(1, 16)
                        })
                    }), new Version(3, new int[] {
/* 246*/                6, 22
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(15, new ECB[] {
/* 246*/                    new ECB(1, 55)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(1, 44)
                        }), new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(2, 17)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(2, 13)
                        })
                    }), new Version(4, new int[] {
/* 246*/                6, 26
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(20, new ECB[] {
/* 246*/                    new ECB(1, 80)
                        }), new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(2, 32)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(2, 24)
                        }), new ECBlocks(16, new ECB[] {
/* 246*/                    new ECB(4, 9)
                        })
                    }), new Version(5, new int[] {
/* 246*/                6, 30
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(1, 108)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(2, 43)
                        }), new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(2, 15), new ECB(2, 16)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(2, 11), new ECB(2, 12)
                        })
                    }), new Version(6, new int[] {
/* 246*/                6, 34
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(2, 68)
                        }), new ECBlocks(16, new ECB[] {
/* 246*/                    new ECB(4, 27)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(4, 19)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(4, 15)
                        })
                    }), new Version(7, new int[] {
/* 246*/                6, 22, 38
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(20, new ECB[] {
/* 246*/                    new ECB(2, 78)
                        }), new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(4, 31)
                        }), new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(2, 14), new ECB(4, 15)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(4, 13), new ECB(1, 14)
                        })
                    }), new Version(8, new int[] {
/* 246*/                6, 24, 42
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(2, 97)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(2, 38), new ECB(2, 39)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(4, 18), new ECB(2, 19)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(4, 14), new ECB(2, 15)
                        })
                    }), new Version(9, new int[] {
/* 246*/                6, 26, 46
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(2, 116)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(3, 36), new ECB(2, 37)
                        }), new ECBlocks(20, new ECB[] {
/* 246*/                    new ECB(4, 16), new ECB(4, 17)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(4, 12), new ECB(4, 13)
                        })
                    }), new Version(10, new int[] {
/* 246*/                6, 28, 50
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(18, new ECB[] {
/* 246*/                    new ECB(2, 68), new ECB(2, 69)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(4, 43), new ECB(1, 44)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(6, 19), new ECB(2, 20)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(6, 15), new ECB(2, 16)
                        })
                    }), 
/* 246*/            new Version(11, new int[] {
/* 246*/                6, 30, 54
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(20, new ECB[] {
/* 246*/                    new ECB(4, 81)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(1, 50), new ECB(4, 51)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(4, 22), new ECB(4, 23)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(3, 12), new ECB(8, 13)
                        })
                    }), new Version(12, new int[] {
/* 246*/                6, 32, 58
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(2, 92), new ECB(2, 93)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(6, 36), new ECB(2, 37)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(4, 20), new ECB(6, 21)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(7, 14), new ECB(4, 15)
                        })
                    }), new Version(13, new int[] {
/* 246*/                6, 34, 62
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(4, 107)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(8, 37), new ECB(1, 38)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(8, 20), new ECB(4, 21)
                        }), new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(12, 11), new ECB(4, 12)
                        })
                    }), new Version(14, new int[] {
/* 246*/                6, 26, 46, 66
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(3, 115), new ECB(1, 116)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(4, 40), new ECB(5, 41)
                        }), new ECBlocks(20, new ECB[] {
/* 246*/                    new ECB(11, 16), new ECB(5, 17)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(11, 12), new ECB(5, 13)
                        })
                    }), new Version(15, new int[] {
/* 246*/                6, 26, 48, 70
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(22, new ECB[] {
/* 246*/                    new ECB(5, 87), new ECB(1, 88)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(5, 41), new ECB(5, 42)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(5, 24), new ECB(7, 25)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(11, 12), new ECB(7, 13)
                        })
                    }), new Version(16, new int[] {
/* 246*/                6, 26, 50, 74
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(5, 98), new ECB(1, 99)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(7, 45), new ECB(3, 46)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(15, 19), new ECB(2, 20)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(3, 15), new ECB(13, 16)
                        })
                    }), new Version(17, new int[] {
/* 246*/                6, 30, 54, 78
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(1, 107), new ECB(5, 108)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(10, 46), new ECB(1, 47)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(1, 22), new ECB(15, 23)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(2, 14), new ECB(17, 15)
                        })
                    }), new Version(18, new int[] {
/* 246*/                6, 30, 56, 82
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(5, 120), new ECB(1, 121)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(9, 43), new ECB(4, 44)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(17, 22), new ECB(1, 23)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(2, 14), new ECB(19, 15)
                        })
                    }), new Version(19, new int[] {
/* 246*/                6, 30, 58, 86
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(3, 113), new ECB(4, 114)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(3, 44), new ECB(11, 45)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(17, 21), new ECB(4, 22)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(9, 13), new ECB(16, 14)
                        })
                    }), new Version(20, new int[] {
/* 246*/                6, 34, 62, 90
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(3, 107), new ECB(5, 108)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(3, 41), new ECB(13, 42)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(15, 24), new ECB(5, 25)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(15, 15), new ECB(10, 16)
                        })
                    }), 
/* 246*/            new Version(21, new int[] {
/* 246*/                6, 28, 50, 72, 94
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(4, 116), new ECB(4, 117)
                        }), new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(17, 42)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(17, 22), new ECB(6, 23)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(19, 16), new ECB(6, 17)
                        })
                    }), new Version(22, new int[] {
/* 246*/                6, 26, 50, 74, 98
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(2, 111), new ECB(7, 112)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(17, 46)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(7, 24), new ECB(16, 25)
                        }), new ECBlocks(24, new ECB[] {
/* 246*/                    new ECB(34, 13)
                        })
                    }), new Version(23, new int[] {
/* 246*/                6, 30, 54, 78, 102
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(4, 121), new ECB(5, 122)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(4, 47), new ECB(14, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(11, 24), new ECB(14, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(16, 15), new ECB(14, 16)
                        })
                    }), new Version(24, new int[] {
/* 246*/                6, 28, 54, 80, 106
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(6, 117), new ECB(4, 118)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(6, 45), new ECB(14, 46)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(11, 24), new ECB(16, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(30, 16), new ECB(2, 17)
                        })
                    }), new Version(25, new int[] {
/* 246*/                6, 32, 58, 84, 110
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(26, new ECB[] {
/* 246*/                    new ECB(8, 106), new ECB(4, 107)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(8, 47), new ECB(13, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(7, 24), new ECB(22, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(22, 15), new ECB(13, 16)
                        })
                    }), new Version(26, new int[] {
/* 246*/                6, 30, 58, 86, 114
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(10, 114), new ECB(2, 115)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(19, 46), new ECB(4, 47)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(28, 22), new ECB(6, 23)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(33, 16), new ECB(4, 17)
                        })
                    }), new Version(27, new int[] {
/* 246*/                6, 34, 62, 90, 118
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(8, 122), new ECB(4, 123)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(22, 45), new ECB(3, 46)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(8, 23), new ECB(26, 24)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(12, 15), new ECB(28, 16)
                        })
                    }), new Version(28, new int[] {
/* 246*/                6, 26, 50, 74, 98, 122
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(3, 117), new ECB(10, 118)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(3, 45), new ECB(23, 46)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(4, 24), new ECB(31, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(11, 15), new ECB(31, 16)
                        })
                    }), new Version(29, new int[] {
/* 246*/                6, 30, 54, 78, 102, 126
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(7, 116), new ECB(7, 117)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(21, 45), new ECB(7, 46)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(1, 23), new ECB(37, 24)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(19, 15), new ECB(26, 16)
                        })
                    }), new Version(30, new int[] {
/* 246*/                6, 26, 52, 78, 104, 130
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(5, 115), new ECB(10, 116)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(19, 47), new ECB(10, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(15, 24), new ECB(25, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(23, 15), new ECB(25, 16)
                        })
                    }), 
/* 246*/            new Version(31, new int[] {
/* 246*/                6, 30, 56, 82, 108, 134
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(13, 115), new ECB(3, 116)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(2, 46), new ECB(29, 47)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(42, 24), new ECB(1, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(23, 15), new ECB(28, 16)
                        })
                    }), new Version(32, new int[] {
/* 246*/                6, 34, 60, 86, 112, 138
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(17, 115)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(10, 46), new ECB(23, 47)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(10, 24), new ECB(35, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(19, 15), new ECB(35, 16)
                        })
                    }), new Version(33, new int[] {
/* 246*/                6, 30, 58, 86, 114, 142
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(17, 115), new ECB(1, 116)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(14, 46), new ECB(21, 47)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(29, 24), new ECB(19, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(11, 15), new ECB(46, 16)
                        })
                    }), new Version(34, new int[] {
/* 246*/                6, 34, 62, 90, 118, 146
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(13, 115), new ECB(6, 116)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(14, 46), new ECB(23, 47)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(44, 24), new ECB(7, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(59, 16), new ECB(1, 17)
                        })
                    }), new Version(35, new int[] {
/* 246*/                6, 30, 54, 78, 102, 126, 150
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(12, 121), new ECB(7, 122)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(12, 47), new ECB(26, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(39, 24), new ECB(14, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(22, 15), new ECB(41, 16)
                        })
                    }), new Version(36, new int[] {
/* 246*/                6, 24, 50, 76, 102, 128, 154
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(6, 121), new ECB(14, 122)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(6, 47), new ECB(34, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(46, 24), new ECB(10, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(2, 15), new ECB(64, 16)
                        })
                    }), new Version(37, new int[] {
/* 246*/                6, 28, 54, 80, 106, 132, 158
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(17, 122), new ECB(4, 123)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(29, 46), new ECB(14, 47)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(49, 24), new ECB(10, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(24, 15), new ECB(46, 16)
                        })
                    }), new Version(38, new int[] {
/* 246*/                6, 32, 58, 84, 110, 136, 162
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(4, 122), new ECB(18, 123)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(13, 46), new ECB(32, 47)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(48, 24), new ECB(14, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(42, 15), new ECB(32, 16)
                        })
                    }), new Version(39, new int[] {
/* 246*/                6, 26, 54, 82, 110, 138, 166
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(20, 117), new ECB(4, 118)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(40, 47), new ECB(7, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(43, 24), new ECB(22, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(10, 15), new ECB(67, 16)
                        })
                    }), new Version(40, new int[] {
/* 246*/                6, 30, 58, 86, 114, 142, 170
                    }, new ECBlocks[] {
/* 246*/                new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(19, 118), new ECB(6, 119)
                        }), new ECBlocks(28, new ECB[] {
/* 246*/                    new ECB(18, 47), new ECB(31, 48)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(34, 24), new ECB(34, 25)
                        }), new ECBlocks(30, new ECB[] {
/* 246*/                    new ECB(20, 15), new ECB(61, 16)
                        })
                    })
                });
            }

            private static final int VERSION_DECODE_INFO[] = {
/*  33*/        31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 0x10b78, 
/*  33*/        0x1145d, 0x12a17, 0x13532, 0x149a6, 0x15683, 0x168c9, 0x177ec, 0x18ec4, 0x191e1, 0x1afab, 
/*  33*/        0x1b08e, 0x1cc1a, 0x1d33f, 0x1ed75, 0x1f250, 0x209d5, 0x216f0, 0x228ba, 0x2379f, 0x24b0b, 
/*  33*/        0x2542e, 0x26a64, 0x27541, 0x28c69
            };
            private static final Version VERSIONS[] = buildVersions();
            private final int versionNumber;
            private final int alignmentPatternCenters[];
            private final ECBlocks ecBlocks[];
            private final int totalCodewords;

}
