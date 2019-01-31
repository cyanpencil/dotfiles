// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultPlacement.java

package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

public class DefaultPlacement
{

            public DefaultPlacement(CharSequence charsequence, int i, int j)
            {
/*  39*/        codewords = charsequence;
/*  40*/        numcols = i;
/*  41*/        numrows = j;
/*  42*/        bits = new byte[i * j];
/*  43*/        Arrays.fill(bits, (byte)-1);
            }

            final int getNumrows()
            {
/*  47*/        return numrows;
            }

            final int getNumcols()
            {
/*  51*/        return numcols;
            }

            final byte[] getBits()
            {
/*  55*/        return bits;
            }

            public final boolean getBit(int i, int j)
            {
/*  59*/        return bits[j * numcols + i] == 1;
            }

            final void setBit(int i, int j, boolean flag)
            {
/*  63*/        bits[j * numcols + i] = ((byte)(flag ? 1 : 0));
            }

            final boolean hasBit(int i, int j)
            {
/*  67*/        return bits[j * numcols + i] >= 0;
            }

            public final void place()
            {
/*  71*/        int i = 0;
/*  72*/        int j = 4;
/*  73*/        int k = 0;
/*  77*/        do
                {
/*  77*/            if(j == numrows && k == 0)
/*  78*/                corner1(i++);
/*  80*/            if(j == numrows - 2 && k == 0 && numcols % 4 != 0)
/*  81*/                corner2(i++);
/*  83*/            if(j == numrows - 2 && k == 0 && numcols % 8 == 4)
/*  84*/                corner3(i++);
/*  86*/            if(j == numrows + 4 && k == 2 && numcols % 8 == 0)
/*  87*/                corner4(i++);
/*  91*/            do
                    {
/*  91*/                if(j < numrows && k >= 0 && !hasBit(k, j))
/*  92*/                    utah(j, k, i++);
/*  94*/                j -= 2;
/*  95*/                k += 2;
                    } while(j >= 0 && k < numcols);
/*  97*/            j++;
/*  98*/            k += 3;
/* 102*/            do
                    {
/* 102*/                if(j >= 0 && k < numcols && !hasBit(k, j))
/* 103*/                    utah(j, k, i++);
/* 105*/                j += 2;
/* 106*/                k -= 2;
                    } while(j < numrows && k >= 0);
/* 108*/            j += 3;
/* 109*/            k++;
                } while(j < numrows || k < numcols);
/* 115*/        if(!hasBit(numcols - 1, numrows - 1))
                {
/* 116*/            setBit(numcols - 1, numrows - 1, true);
/* 117*/            setBit(numcols - 2, numrows - 2, true);
                }
            }

            private void module(int i, int j, int k, int l)
            {
/* 122*/        if(i < 0)
                {
/* 123*/            i += numrows;
/* 124*/            j += 4 - (numrows + 4) % 8;
                }
/* 126*/        if(j < 0)
                {
/* 127*/            j += numcols;
/* 128*/            i += 4 - (numcols + 4) % 8;
                }
/* 131*/        k = (k = codewords.charAt(k)) & 1 << 8 - l;
/* 133*/        setBit(j, i, k != 0);
            }

            private void utah(int i, int j, int k)
            {
/* 144*/        module(i - 2, j - 2, k, 1);
/* 145*/        module(i - 2, j - 1, k, 2);
/* 146*/        module(i - 1, j - 2, k, 3);
/* 147*/        module(i - 1, j - 1, k, 4);
/* 148*/        module(i - 1, j, k, 5);
/* 149*/        module(i, j - 2, k, 6);
/* 150*/        module(i, j - 1, k, 7);
/* 151*/        module(i, j, k, 8);
            }

            private void corner1(int i)
            {
/* 155*/        module(numrows - 1, 0, i, 1);
/* 156*/        module(numrows - 1, 1, i, 2);
/* 157*/        module(numrows - 1, 2, i, 3);
/* 158*/        module(0, numcols - 2, i, 4);
/* 159*/        module(0, numcols - 1, i, 5);
/* 160*/        module(1, numcols - 1, i, 6);
/* 161*/        module(2, numcols - 1, i, 7);
/* 162*/        module(3, numcols - 1, i, 8);
            }

            private void corner2(int i)
            {
/* 166*/        module(numrows - 3, 0, i, 1);
/* 167*/        module(numrows - 2, 0, i, 2);
/* 168*/        module(numrows - 1, 0, i, 3);
/* 169*/        module(0, numcols - 4, i, 4);
/* 170*/        module(0, numcols - 3, i, 5);
/* 171*/        module(0, numcols - 2, i, 6);
/* 172*/        module(0, numcols - 1, i, 7);
/* 173*/        module(1, numcols - 1, i, 8);
            }

            private void corner3(int i)
            {
/* 177*/        module(numrows - 3, 0, i, 1);
/* 178*/        module(numrows - 2, 0, i, 2);
/* 179*/        module(numrows - 1, 0, i, 3);
/* 180*/        module(0, numcols - 2, i, 4);
/* 181*/        module(0, numcols - 1, i, 5);
/* 182*/        module(1, numcols - 1, i, 6);
/* 183*/        module(2, numcols - 1, i, 7);
/* 184*/        module(3, numcols - 1, i, 8);
            }

            private void corner4(int i)
            {
/* 188*/        module(numrows - 1, 0, i, 1);
/* 189*/        module(numrows - 1, numcols - 1, i, 2);
/* 190*/        module(0, numcols - 3, i, 3);
/* 191*/        module(0, numcols - 2, i, 4);
/* 192*/        module(0, numcols - 1, i, 5);
/* 193*/        module(1, numcols - 3, i, 6);
/* 194*/        module(1, numcols - 2, i, 7);
/* 195*/        module(1, numcols - 1, i, 8);
            }

            private final CharSequence codewords;
            private final int numrows;
            private final int numcols;
            private final byte bits[];
}
