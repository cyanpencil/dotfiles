// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MaskUtil.java

package com.google.zxing.qrcode.encoder;


// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix

final class MaskUtil
{

            private MaskUtil()
            {
            }

            static int applyMaskPenaltyRule1(ByteMatrix bytematrix)
            {
/*  41*/        return applyMaskPenaltyRule1Internal(bytematrix, true) + applyMaskPenaltyRule1Internal(bytematrix, false);
            }

            static int applyMaskPenaltyRule2(ByteMatrix bytematrix)
            {
/*  50*/        int i = 0;
/*  51*/        byte abyte0[][] = bytematrix.getArray();
/*  52*/        int j = bytematrix.getWidth();
/*  53*/        bytematrix = bytematrix.getHeight();
/*  54*/        for(int k = 0; k < bytematrix - 1; k++)
                {
/*  55*/            for(int l = 0; l < j - 1; l++)
                    {
                        byte byte0;
/*  56*/                if((byte0 = abyte0[k][l]) == abyte0[k][l + 1] && byte0 == abyte0[k + 1][l] && byte0 == abyte0[k + 1][l + 1])
/*  58*/                    i++;
                    }

                }

/*  62*/        return 3 * i;
            }

            static int applyMaskPenaltyRule3(ByteMatrix bytematrix)
            {
/*  71*/        int i = 0;
/*  72*/        byte abyte0[][] = bytematrix.getArray();
/*  73*/        int j = bytematrix.getWidth();
/*  74*/        bytematrix = bytematrix.getHeight();
/*  75*/        for(int k = 0; k < bytematrix; k++)
                {
/*  76*/            for(int l = 0; l < j; l++)
                    {
/*  77*/                byte abyte1[] = abyte0[k];
/*  78*/                if(l + 6 < j && abyte1[l] == 1 && abyte1[l + 1] == 0 && abyte1[l + 2] == 1 && abyte1[l + 3] == 1 && abyte1[l + 4] == 1 && abyte1[l + 5] == 0 && abyte1[l + 6] == 1 && (isWhiteHorizontal(abyte1, l - 4, l) || isWhiteHorizontal(abyte1, l + 7, l + 11)))
/*  87*/                    i++;
/*  89*/                if(k + 6 < bytematrix && abyte0[k][l] == 1 && abyte0[k + 1][l] == 0 && abyte0[k + 2][l] == 1 && abyte0[k + 3][l] == 1 && abyte0[k + 4][l] == 1 && abyte0[k + 5][l] == 0 && abyte0[k + 6][l] == 1 && (isWhiteVertical(abyte0, l, k - 4, k) || isWhiteVertical(abyte0, l, k + 7, k + 11)))
/*  98*/                    i++;
                    }

                }

/* 102*/        return i * 40;
            }

            private static boolean isWhiteHorizontal(byte abyte0[], int i, int j)
            {
/* 106*/        for(i = i; i < j; i++)
/* 107*/            if(i >= 0 && i < abyte0.length && abyte0[i] == 1)
/* 108*/                return false;

/* 111*/        return true;
            }

            private static boolean isWhiteVertical(byte abyte0[][], int i, int j, int k)
            {
/* 115*/        for(j = j; j < k; j++)
/* 116*/            if(j >= 0 && j < abyte0.length && abyte0[j][i] == 1)
/* 117*/                return false;

/* 120*/        return true;
            }

            static int applyMaskPenaltyRule4(ByteMatrix bytematrix)
            {
/* 128*/        int i = 0;
/* 129*/        byte abyte0[][] = bytematrix.getArray();
/* 130*/        int j = bytematrix.getWidth();
/* 131*/        int k = bytematrix.getHeight();
/* 132*/        for(int l = 0; l < k; l++)
                {
/* 133*/            byte abyte1[] = abyte0[l];
/* 134*/            for(int k1 = 0; k1 < j; k1++)
/* 135*/                if(abyte1[k1] == 1)
/* 136*/                    i++;

                }

/* 140*/        int i1 = bytematrix.getHeight() * bytematrix.getWidth();
                int j1;
/* 141*/        return (j1 = (Math.abs((i << 1) - i1) * 10) / i1) * 10;
            }

            static boolean getDataMaskBit(int i, int j, int k)
            {
/* 152*/        switch(i)
                {
/* 154*/        case 0: // '\0'
/* 154*/            i = k + j & 1;
                    break;

/* 157*/        case 1: // '\001'
/* 157*/            i = k & 1;
                    break;

/* 160*/        case 2: // '\002'
/* 160*/            i = j % 3;
                    break;

/* 163*/        case 3: // '\003'
/* 163*/            i = (k + j) % 3;
                    break;

/* 166*/        case 4: // '\004'
/* 166*/            i = (k >>> 1) + j / 3 & 1;
                    break;

/* 169*/        case 5: // '\005'
/* 169*/            i = ((i = k * j) & 1) + i % 3;
                    break;

/* 173*/        case 6: // '\006'
/* 173*/            i = ((i = k * j) & 1) + i % 3 & 1;
                    break;

/* 177*/        case 7: // '\007'
/* 177*/            i = (i = k * j) % 3 + (k + j & 1) & 1;
                    break;

/* 181*/        default:
/* 181*/            throw new IllegalArgumentException((new StringBuilder("Invalid mask pattern: ")).append(i).toString());
                }
/* 183*/        return i == 0;
            }

            private static int applyMaskPenaltyRule1Internal(ByteMatrix bytematrix, boolean flag)
            {
/* 191*/        int i = 0;
/* 192*/        int j = flag ? bytematrix.getHeight() : bytematrix.getWidth();
/* 193*/        int k = flag ? bytematrix.getWidth() : bytematrix.getHeight();
/* 194*/        bytematrix = bytematrix.getArray();
/* 195*/        for(int l = 0; l < j; l++)
                {
/* 196*/            int i1 = 0;
/* 197*/            byte byte0 = -1;
/* 198*/            for(int j1 = 0; j1 < k; j1++)
                    {
                        byte byte1;
/* 199*/                if((byte1 = flag ? bytematrix[l][j1] : bytematrix[j1][l]) == byte0)
                        {
/* 201*/                    i1++;
/* 201*/                    continue;
                        }
/* 203*/                if(i1 >= 5)
/* 204*/                    i += 3 + (i1 - 5);
/* 206*/                i1 = 1;
/* 207*/                byte0 = byte1;
                    }

/* 210*/            if(i1 >= 5)
/* 211*/                i += 3 + (i1 - 5);
                }

/* 214*/        return i;
            }

            private static final int N1 = 3;
            private static final int N2 = 3;
            private static final int N3 = 40;
            private static final int N4 = 10;
}
