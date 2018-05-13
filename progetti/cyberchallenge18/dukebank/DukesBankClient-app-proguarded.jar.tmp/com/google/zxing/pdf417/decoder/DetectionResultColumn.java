// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DetectionResultColumn.java

package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            BoundingBox, Codeword

class DetectionResultColumn
{

            DetectionResultColumn(BoundingBox boundingbox)
            {
/*  32*/        boundingBox = new BoundingBox(boundingbox);
/*  33*/        codewords = new Codeword[(boundingbox.getMaxY() - boundingbox.getMinY()) + 1];
            }

            final Codeword getCodewordNearby(int i)
            {
                Codeword codeword;
/*  37*/        if((codeword = getCodeword(i)) != null)
/*  39*/            return codeword;
/*  41*/        for(int k = 1; k < 5; k++)
                {
                    int j;
                    Codeword codeword1;
/*  42*/            if((j = imageRowToCodewordIndex(i) - k) >= 0 && (codeword1 = codewords[j]) != null)
/*  46*/                return codeword1;
/*  49*/            if((codeword1 = imageRowToCodewordIndex(i) + k) < codewords.length && (codeword1 = codewords[codeword1]) != null)
/*  53*/                return codeword1;
                }

/*  57*/        return null;
            }

            final int imageRowToCodewordIndex(int i)
            {
/*  61*/        return i - boundingBox.getMinY();
            }

            final void setCodeword(int i, Codeword codeword)
            {
/*  65*/        codewords[imageRowToCodewordIndex(i)] = codeword;
            }

            final Codeword getCodeword(int i)
            {
/*  69*/        return codewords[imageRowToCodewordIndex(i)];
            }

            final BoundingBox getBoundingBox()
            {
/*  73*/        return boundingBox;
            }

            final Codeword[] getCodewords()
            {
/*  77*/        return codewords;
            }

            public String toString()
            {
/*  82*/        Formatter formatter = new Formatter();
/*  83*/        int i = 0;
                Codeword acodeword[];
/*  84*/        int j = (acodeword = codewords).length;
/*  84*/        for(int k = 0; k < j; k++)
                {
                    Codeword codeword;
/*  84*/            if((codeword = acodeword[k]) == null)
/*  86*/                formatter.format("%3d:    |   %n", new Object[] {
/*  86*/                    Integer.valueOf(i++)
                        });
/*  89*/            else
/*  89*/                formatter.format("%3d: %3d|%3d%n", new Object[] {
/*  89*/                    Integer.valueOf(i++), Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue())
                        });
                }

/*  91*/        String s = formatter.toString();
/*  92*/        formatter.close();
/*  93*/        return s;
            }

            private static final int MAX_NEARBY_DISTANCE = 5;
            private final BoundingBox boundingBox;
            private final Codeword codewords[];
}
