// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DetectionResultRowIndicatorColumn.java

package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.ResultPoint;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            DetectionResultColumn, BarcodeMetadata, BarcodeValue, BoundingBox, 
//            Codeword

final class DetectionResultRowIndicatorColumn extends DetectionResultColumn
{

            DetectionResultRowIndicatorColumn(BoundingBox boundingbox, boolean flag)
            {
/*  31*/        super(boundingbox);
/*  32*/        isLeft = flag;
            }

            final void setRowNumbers()
            {
                Codeword acodeword[];
/*  36*/        int i = (acodeword = getCodewords()).length;
/*  36*/        for(int j = 0; j < i; j++)
                {
                    Codeword codeword;
/*  36*/            if((codeword = acodeword[j]) != null)
/*  38*/                codeword.setRowNumberAsRowIndicatorColumn();
                }

            }

            final int adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodemetadata)
            {
/*  48*/        Codeword acodeword[] = getCodewords();
/*  49*/        setRowNumbers();
/*  50*/        removeIncorrectCodewords(acodeword, barcodemetadata);
/*  51*/        Object obj = getBoundingBox();
/*  52*/        ResultPoint resultpoint = isLeft ? ((BoundingBox) (obj)).getTopLeft() : ((BoundingBox) (obj)).getTopRight();
/*  53*/        obj = isLeft ? ((Object) (((BoundingBox) (obj)).getBottomLeft())) : ((Object) (((BoundingBox) (obj)).getBottomRight()));
/*  54*/        int j = imageRowToCodewordIndex((int)resultpoint.getY());
                int i;
/*  55*/        float f = (float)((i = imageRowToCodewordIndex((int)((ResultPoint) (obj)).getY())) - j) / (float)barcodemetadata.getRowCount();
/*  59*/        int k = -1;
/*  60*/        int l = 1;
/*  61*/        int i1 = 0;
/*  62*/        for(j = j; j < i; j++)
                {
/*  63*/            if(acodeword[j] == null)
/*  66*/                continue;
                    Codeword codeword;
                    int j1;
/*  66*/            if((j1 = (codeword = acodeword[j]).getRowNumber() - k) == 0)
                    {
/*  81*/                i1++;
/*  81*/                continue;
                    }
/*  82*/            if(j1 == 1)
                    {
/*  83*/                l = Math.max(l, i1);
/*  84*/                i1 = 1;
/*  85*/                k = codeword.getRowNumber();
/*  85*/                continue;
                    }
/*  86*/            if(j1 < 0 || codeword.getRowNumber() >= barcodemetadata.getRowCount() || j1 > j)
                    {
/*  89*/                acodeword[j] = null;
/*  89*/                continue;
                    }
/*  92*/            if(l > 2)
/*  93*/                j1 = (l - 2) * j1;
/*  95*/            else
/*  95*/                j1 = j1;
/*  97*/            boolean flag = j1 >= j;
/*  98*/            for(int k1 = 1; k1 <= j1 && !flag; k1++)
/* 101*/                flag = acodeword[j - k1] != null;

/* 103*/            if(flag)
                    {
/* 104*/                acodeword[j] = null;
                    } else
                    {
/* 106*/                k = codeword.getRowNumber();
/* 107*/                i1 = 1;
                    }
                }

/* 111*/        return (int)((double)f + 0.5D);
            }

            final int[] getRowHeights()
                throws FormatException
            {
                BarcodeMetadata barcodemetadata;
/* 115*/        if((barcodemetadata = getBarcodeMetadata()) == null)
/* 117*/            return null;
/* 119*/        adjustIncompleteIndicatorColumnRowNumbers(barcodemetadata);
/* 120*/        int ai[] = new int[barcodemetadata.getRowCount()];
                Codeword acodeword[];
/* 121*/        int i = (acodeword = getCodewords()).length;
/* 121*/        for(int j = 0; j < i; j++)
                {
                    Codeword codeword;
/* 121*/            if((codeword = acodeword[j]) == null)
/* 123*/                continue;
                    int k;
/* 123*/            if((k = codeword.getRowNumber()) >= ai.length)
/* 125*/                throw FormatException.getFormatInstance();
/* 127*/            ai[k]++;
                }

/* 130*/        return ai;
            }

            final int adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodemetadata)
            {
/* 137*/        Object obj = getBoundingBox();
/* 138*/        ResultPoint resultpoint = isLeft ? ((BoundingBox) (obj)).getTopLeft() : ((BoundingBox) (obj)).getTopRight();
/* 139*/        obj = isLeft ? ((Object) (((BoundingBox) (obj)).getBottomLeft())) : ((Object) (((BoundingBox) (obj)).getBottomRight()));
/* 140*/        int j = imageRowToCodewordIndex((int)resultpoint.getY());
                int i;
/* 141*/        float f = (float)((i = imageRowToCodewordIndex((int)((ResultPoint) (obj)).getY())) - j) / (float)barcodemetadata.getRowCount();
/* 143*/        Codeword acodeword[] = getCodewords();
/* 144*/        int k = -1;
/* 145*/        int l = 1;
/* 146*/        int i1 = 0;
/* 147*/        for(j = j; j < i; j++)
                {
/* 148*/            if(acodeword[j] == null)
/* 151*/                continue;
                    Codeword codeword;
/* 151*/            (codeword = acodeword[j]).setRowNumberAsRowIndicatorColumn();
                    int j1;
/* 155*/            if((j1 = codeword.getRowNumber() - k) == 0)
                    {
/* 160*/                i1++;
/* 160*/                continue;
                    }
/* 161*/            if(j1 == 1)
                    {
/* 162*/                l = Math.max(l, i1);
/* 163*/                i1 = 1;
/* 164*/                k = codeword.getRowNumber();
/* 164*/                continue;
                    }
/* 165*/            if(codeword.getRowNumber() >= barcodemetadata.getRowCount())
                    {
/* 166*/                acodeword[j] = null;
                    } else
                    {
/* 168*/                k = codeword.getRowNumber();
/* 169*/                i1 = 1;
                    }
                }

/* 172*/        return (int)((double)f + 0.5D);
            }

            final BarcodeMetadata getBarcodeMetadata()
            {
/* 176*/        Codeword acodeword[] = getCodewords();
/* 177*/        BarcodeValue barcodevalue = new BarcodeValue();
/* 178*/        BarcodeValue barcodevalue1 = new BarcodeValue();
/* 179*/        BarcodeValue barcodevalue2 = new BarcodeValue();
/* 180*/        BarcodeValue barcodevalue3 = new BarcodeValue();
                Codeword acodeword1[];
/* 181*/        int i = (acodeword1 = acodeword).length;
/* 181*/        for(int j = 0; j < i; j++)
                {
                    Codeword codeword;
/* 181*/            if((codeword = acodeword1[j]) != null)
                    {
/* 185*/                codeword.setRowNumberAsRowIndicatorColumn();
/* 186*/                int l = codeword.getValue() % 30;
/* 187*/                int k = codeword.getRowNumber();
/* 188*/                if(!isLeft)
/* 189*/                    k += 2;
/* 191*/                switch(k % 3)
                        {
/* 193*/                case 0: // '\0'
/* 193*/                    barcodevalue1.setValue(l * 3 + 1);
                            break;

/* 196*/                case 1: // '\001'
/* 196*/                    barcodevalue3.setValue(l / 3);
/* 197*/                    barcodevalue2.setValue(l % 3);
                            break;

/* 200*/                case 2: // '\002'
/* 200*/                    barcodevalue.setValue(l + 1);
                            break;
                        }
                    }
                }

/* 205*/        if(barcodevalue.getValue().length == 0 || barcodevalue1.getValue().length == 0 || barcodevalue2.getValue().length == 0 || barcodevalue3.getValue().length == 0 || barcodevalue.getValue()[0] <= 0 || barcodevalue1.getValue()[0] + barcodevalue2.getValue()[0] < 3 || barcodevalue1.getValue()[0] + barcodevalue2.getValue()[0] > 90)
                {
/* 212*/            return null;
                } else
                {
/* 214*/            BarcodeMetadata barcodemetadata = new BarcodeMetadata(barcodevalue.getValue()[0], barcodevalue1.getValue()[0], barcodevalue2.getValue()[0], barcodevalue3.getValue()[0]);
/* 216*/            removeIncorrectCodewords(acodeword, barcodemetadata);
/* 217*/            return barcodemetadata;
                }
            }

            private void removeIncorrectCodewords(Codeword acodeword[], BarcodeMetadata barcodemetadata)
            {
/* 223*/        for(int i = 0; i < acodeword.length; i++)
                {
/* 224*/            Codeword codeword = acodeword[i];
/* 225*/            if(acodeword[i] == null)
/* 228*/                continue;
/* 228*/            int k = codeword.getValue() % 30;
                    int j;
/* 229*/            if((j = codeword.getRowNumber()) > barcodemetadata.getRowCount())
                    {
/* 231*/                acodeword[i] = null;
                    } else
                    {
/* 234*/                if(!isLeft)
/* 235*/                    j += 2;
/* 237*/                switch(j % 3)
                        {
/* 223*/                default:
                            break;

/* 239*/                case 0: // '\0'
/* 239*/                    if(k * 3 + 1 != barcodemetadata.getRowCountUpperPart())
/* 240*/                        acodeword[i] = null;
/* 240*/                    break;

/* 244*/                case 1: // '\001'
/* 244*/                    if(k / 3 != barcodemetadata.getErrorCorrectionLevel() || k % 3 != barcodemetadata.getRowCountLowerPart())
/* 246*/                        acodeword[i] = null;
/* 246*/                    break;

/* 250*/                case 2: // '\002'
/* 250*/                    if(k + 1 != barcodemetadata.getColumnCount())
/* 251*/                        acodeword[i] = null;
                            break;
                        }
                    }
                }

            }

            final boolean isLeft()
            {
/* 259*/        return isLeft;
            }

            public final String toString()
            {
/* 264*/        return (new StringBuilder("IsLeft: ")).append(isLeft).append('\n').append(super.toString()).toString();
            }

            private final boolean isLeft;
}
