// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DetectionResult.java

package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            BarcodeMetadata, Codeword, DetectionResultColumn, DetectionResultRowIndicatorColumn, 
//            BoundingBox

final class DetectionResult
{

            DetectionResult(BarcodeMetadata barcodemetadata, BoundingBox boundingbox)
            {
/*  36*/        barcodeMetadata = barcodemetadata;
/*  37*/        barcodeColumnCount = barcodemetadata.getColumnCount();
/*  38*/        boundingBox = boundingbox;
/*  39*/        detectionResultColumns = new DetectionResultColumn[barcodeColumnCount + 2];
            }

            final DetectionResultColumn[] getDetectionResultColumns()
            {
/*  43*/        adjustIndicatorColumnRowNumbers(detectionResultColumns[0]);
/*  44*/        adjustIndicatorColumnRowNumbers(detectionResultColumns[barcodeColumnCount + 1]);
/*  45*/        int i = 928;
                char c;
/*  48*/        do
/*  48*/            c = i;
/*  50*/        while((i = adjustRowNumbers()) > 0 && i < c);
/*  51*/        return detectionResultColumns;
            }

            private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionresultcolumn)
            {
/*  55*/        if(detectionresultcolumn != null)
/*  56*/            ((DetectionResultRowIndicatorColumn)detectionresultcolumn).adjustCompleteIndicatorColumnRowNumbers(barcodeMetadata);
            }

            private int adjustRowNumbers()
            {
                int i;
/*  69*/        if((i = adjustRowNumbersByRow()) == 0)
/*  71*/            return 0;
/*  73*/        for(int j = 1; j < barcodeColumnCount + 1; j++)
                {
/*  74*/            Codeword acodeword[] = detectionResultColumns[j].getCodewords();
/*  75*/            for(int k = 0; k < acodeword.length; k++)
/*  76*/                if(acodeword[k] != null && !acodeword[k].hasValidRowNumber())
/*  80*/                    adjustRowNumbers(j, k, acodeword);

                }

/*  84*/        return i;
            }

            private int adjustRowNumbersByRow()
            {
/*  88*/        adjustRowNumbersFromBothRI();
                int i;
/*  93*/        return (i = adjustRowNumbersFromLRI()) + adjustRowNumbersFromRRI();
            }

            private void adjustRowNumbersFromBothRI()
            {
/*  98*/        if(detectionResultColumns[0] == null || detectionResultColumns[barcodeColumnCount + 1] == null)
/*  99*/            return;
/* 101*/        Codeword acodeword[] = detectionResultColumns[0].getCodewords();
/* 102*/        Codeword acodeword1[] = detectionResultColumns[barcodeColumnCount + 1].getCodewords();
/* 103*/        for(int i = 0; i < acodeword.length; i++)
                {
/* 104*/            if(acodeword[i] == null || acodeword1[i] == null || acodeword[i].getRowNumber() != acodeword1[i].getRowNumber())
/* 107*/                continue;
/* 107*/            for(int j = 1; j <= barcodeColumnCount; j++)
                    {
                        Codeword codeword;
/* 108*/                if((codeword = detectionResultColumns[j].getCodewords()[i]) == null)
/* 112*/                    continue;
/* 112*/                codeword.setRowNumber(acodeword[i].getRowNumber());
/* 113*/                if(!codeword.hasValidRowNumber())
/* 114*/                    detectionResultColumns[j].getCodewords()[i] = null;
                    }

                }

            }

            private int adjustRowNumbersFromRRI()
            {
/* 122*/        if(detectionResultColumns[barcodeColumnCount + 1] == null)
/* 123*/            return 0;
/* 125*/        int i = 0;
/* 126*/        Codeword acodeword[] = detectionResultColumns[barcodeColumnCount + 1].getCodewords();
/* 127*/        for(int j = 0; j < acodeword.length; j++)
                {
/* 128*/            if(acodeword[j] == null)
/* 131*/                continue;
/* 131*/            int k = acodeword[j].getRowNumber();
/* 132*/            int l = 0;
/* 133*/            for(int i1 = barcodeColumnCount + 1; i1 > 0 && l < 2; i1--)
                    {
                        Codeword codeword;
/* 134*/                if((codeword = detectionResultColumns[i1].getCodewords()[j]) == null)
/* 136*/                    continue;
/* 136*/                l = adjustRowNumberIfValid(k, l, codeword);
/* 137*/                if(!codeword.hasValidRowNumber())
/* 138*/                    i++;
                    }

                }

/* 143*/        return i;
            }

            private int adjustRowNumbersFromLRI()
            {
/* 147*/        if(detectionResultColumns[0] == null)
/* 148*/            return 0;
/* 150*/        int i = 0;
/* 151*/        Codeword acodeword[] = detectionResultColumns[0].getCodewords();
/* 152*/        for(int j = 0; j < acodeword.length; j++)
                {
/* 153*/            if(acodeword[j] == null)
/* 156*/                continue;
/* 156*/            int k = acodeword[j].getRowNumber();
/* 157*/            int l = 0;
/* 158*/            for(int i1 = 1; i1 < barcodeColumnCount + 1 && l < 2; i1++)
                    {
                        Codeword codeword;
/* 159*/                if((codeword = detectionResultColumns[i1].getCodewords()[j]) == null)
/* 161*/                    continue;
/* 161*/                l = adjustRowNumberIfValid(k, l, codeword);
/* 162*/                if(!codeword.hasValidRowNumber())
/* 163*/                    i++;
                    }

                }

/* 168*/        return i;
            }

            private static int adjustRowNumberIfValid(int i, int j, Codeword codeword)
            {
/* 172*/        if(codeword == null)
/* 173*/            return j;
/* 175*/        if(!codeword.hasValidRowNumber())
/* 176*/            if(codeword.isValidRowNumber(i))
                    {
/* 177*/                codeword.setRowNumber(i);
/* 178*/                j = 0;
                    } else
                    {
/* 180*/                j++;
                    }
/* 183*/        return j;
            }

            private void adjustRowNumbers(int i, int j, Codeword acodeword[])
            {
/* 187*/        Codeword codeword = acodeword[j];
                Codeword acodeword1[];
/* 188*/        Codeword acodeword2[] = acodeword1 = detectionResultColumns[i - 1].getCodewords();
/* 190*/        if(detectionResultColumns[i + 1] != null)
/* 191*/            acodeword2 = detectionResultColumns[i + 1].getCodewords();
/* 194*/        (i = new Codeword[14])[2] = acodeword1[j];
/* 197*/        i[3] = acodeword2[j];
/* 199*/        if(j > 0)
                {
/* 200*/            i[0] = acodeword[j - 1];
/* 201*/            i[4] = acodeword1[j - 1];
/* 202*/            i[5] = acodeword2[j - 1];
                }
/* 204*/        if(j > 1)
                {
/* 205*/            i[8] = acodeword[j - 2];
/* 206*/            i[10] = acodeword1[j - 2];
/* 207*/            i[11] = acodeword2[j - 2];
                }
/* 209*/        if(j < acodeword.length - 1)
                {
/* 210*/            i[1] = acodeword[j + 1];
/* 211*/            i[6] = acodeword1[j + 1];
/* 212*/            i[7] = acodeword2[j + 1];
                }
/* 214*/        if(j < acodeword.length - 2)
                {
/* 215*/            i[9] = acodeword[j + 2];
/* 216*/            i[12] = acodeword1[j + 2];
/* 217*/            i[13] = acodeword2[j + 2];
                }
/* 219*/        i = i;
/* 219*/        for(j = 0; j < 14; j++)
                {
/* 219*/            acodeword = i[j];
/* 220*/            if(adjustRowNumber(codeword, acodeword))
/* 221*/                return;
                }

            }

            private static boolean adjustRowNumber(Codeword codeword, Codeword codeword1)
            {
/* 230*/        if(codeword1 == null)
/* 231*/            return false;
/* 233*/        if(codeword1.hasValidRowNumber() && codeword1.getBucket() == codeword.getBucket())
                {
/* 234*/            codeword.setRowNumber(codeword1.getRowNumber());
/* 235*/            return true;
                } else
                {
/* 237*/            return false;
                }
            }

            final int getBarcodeColumnCount()
            {
/* 241*/        return barcodeColumnCount;
            }

            final int getBarcodeRowCount()
            {
/* 245*/        return barcodeMetadata.getRowCount();
            }

            final int getBarcodeECLevel()
            {
/* 249*/        return barcodeMetadata.getErrorCorrectionLevel();
            }

            public final void setBoundingBox(BoundingBox boundingbox)
            {
/* 253*/        boundingBox = boundingbox;
            }

            final BoundingBox getBoundingBox()
            {
/* 257*/        return boundingBox;
            }

            final void setDetectionResultColumn(int i, DetectionResultColumn detectionresultcolumn)
            {
/* 261*/        detectionResultColumns[i] = detectionresultcolumn;
            }

            final DetectionResultColumn getDetectionResultColumn(int i)
            {
/* 265*/        return detectionResultColumns[i];
            }

            public final String toString()
            {
                DetectionResultColumn detectionresultcolumn;
/* 270*/        if((detectionresultcolumn = detectionResultColumns[0]) == null)
/* 272*/            detectionresultcolumn = detectionResultColumns[barcodeColumnCount + 1];
/* 274*/        Formatter formatter = new Formatter();
/* 275*/        for(int i = 0; i < detectionresultcolumn.getCodewords().length; i++)
                {
/* 276*/            formatter.format("CW %3d:", new Object[] {
/* 276*/                Integer.valueOf(i)
                    });
/* 277*/            for(int j = 0; j < barcodeColumnCount + 2; j++)
                    {
/* 278*/                if(detectionResultColumns[j] == null)
                        {
/* 279*/                    formatter.format("    |   ", new Object[0]);
/* 280*/                    continue;
                        }
                        Codeword codeword;
/* 282*/                if((codeword = detectionResultColumns[j].getCodewords()[i]) == null)
/* 284*/                    formatter.format("    |   ", new Object[0]);
/* 287*/                else
/* 287*/                    formatter.format(" %3d|%3d", new Object[] {
/* 287*/                        Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue())
                            });
                    }

/* 289*/            formatter.format("%n", new Object[0]);
                }

/* 291*/        String s = formatter.toString();
/* 292*/        formatter.close();
/* 293*/        return s;
            }

            private static final int ADJUST_ROW_NUMBER_SKIP = 2;
            private final BarcodeMetadata barcodeMetadata;
            private final DetectionResultColumn detectionResultColumns[];
            private BoundingBox boundingBox;
            private final int barcodeColumnCount;
}
