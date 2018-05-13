// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BitMatrix.java

package com.google.zxing.common;

import java.util.Arrays;

// Referenced classes of package com.google.zxing.common:
//            BitArray

public final class BitMatrix
    implements Cloneable
{

            public BitMatrix(int i)
            {
/*  45*/        this(i, i);
            }

            public BitMatrix(int i, int j)
            {
/*  49*/        if(i <= 0 || j <= 0)
                {
/*  50*/            throw new IllegalArgumentException("Both dimensions must be greater than 0");
                } else
                {
/*  52*/            width = i;
/*  53*/            height = j;
/*  54*/            rowSize = i + 31 >> 5;
/*  55*/            bits = new int[rowSize * j];
/*  56*/            return;
                }
            }

            private BitMatrix(int i, int j, int k, int ai[])
            {
/*  59*/        width = i;
/*  60*/        height = j;
/*  61*/        rowSize = k;
/*  62*/        bits = ai;
            }

            public final boolean get(int i, int j)
            {
/*  73*/        j = j * rowSize + (i >> 5);
/*  74*/        return (bits[j] >>> (i & 0x1f) & 1) != 0;
            }

            public final void set(int i, int j)
            {
/*  84*/        j = j * rowSize + (i >> 5);
/*  85*/        bits[j] |= 1 << (i & 0x1f);
            }

            public final void flip(int i, int j)
            {
/*  95*/        j = j * rowSize + (i >> 5);
/*  96*/        bits[j] ^= 1 << (i & 0x1f);
            }

            public final void clear()
            {
/* 103*/        int i = bits.length;
/* 104*/        for(int j = 0; j < i; j++)
/* 105*/            bits[j] = 0;

            }

            public final void setRegion(int i, int j, int k, int l)
            {
/* 118*/        if(j < 0 || i < 0)
/* 119*/            throw new IllegalArgumentException("Left and top must be nonnegative");
/* 121*/        if(l <= 0 || k <= 0)
/* 122*/            throw new IllegalArgumentException("Height and width must be at least 1");
/* 124*/        k = i + k;
/* 125*/        if((l = j + l) > height || k > width)
/* 127*/            throw new IllegalArgumentException("The region must fit inside the matrix");
/* 129*/        for(j = j; j < l; j++)
                {
/* 130*/            int i1 = j * rowSize;
/* 131*/            for(int j1 = i; j1 < k; j1++)
/* 132*/                bits[i1 + (j1 >> 5)] |= 1 << (j1 & 0x1f);

                }

            }

            public final BitArray getRow(int i, BitArray bitarray)
            {
/* 146*/        if(bitarray == null || bitarray.getSize() < width)
/* 147*/            bitarray = new BitArray(width);
/* 149*/        else
/* 149*/            bitarray.clear();
/* 151*/        i *= rowSize;
/* 152*/        for(int j = 0; j < rowSize; j++)
/* 153*/            bitarray.setBulk(j << 5, bits[i + j]);

/* 155*/        return bitarray;
            }

            public final void setRow(int i, BitArray bitarray)
            {
/* 163*/        System.arraycopy(bitarray.getBitArray(), 0, bits, i * rowSize, rowSize);
            }

            public final void rotate180()
            {
/* 170*/        int i = getWidth();
/* 171*/        int j = getHeight();
/* 172*/        BitArray bitarray1 = new BitArray(i);
/* 173*/        BitArray bitarray = new BitArray(i);
/* 174*/        for(int k = 0; k < (j + 1) / 2; k++)
                {
/* 175*/            bitarray1 = getRow(k, bitarray1);
/* 176*/            bitarray = getRow(j - 1 - k, bitarray);
/* 177*/            bitarray1.reverse();
/* 178*/            bitarray.reverse();
/* 179*/            setRow(k, bitarray);
/* 180*/            setRow(j - 1 - k, bitarray1);
                }

            }

            public final int[] getEnclosingRectangle()
            {
/* 190*/        int i = width;
/* 191*/        int j = height;
/* 192*/        int k = -1;
/* 193*/        int l = -1;
/* 195*/        for(int i1 = 0; i1 < height; i1++)
                {
/* 196*/            for(int k1 = 0; k1 < rowSize; k1++)
                    {
                        int i2;
/* 197*/                if((i2 = bits[i1 * rowSize + k1]) == 0)
/* 199*/                    continue;
/* 199*/                if(i1 < j)
/* 200*/                    j = i1;
/* 202*/                if(i1 > l)
/* 203*/                    l = i1;
/* 205*/                if(k1 << 5 < i)
                        {
                            int j2;
/* 206*/                    for(j2 = 0; i2 << 31 - j2 == 0; j2++);
/* 210*/                    if((k1 << 5) + j2 < i)
/* 211*/                        i = (k1 << 5) + j2;
                        }
/* 214*/                if((k1 << 5) + 31 <= k)
/* 215*/                    continue;
                        int k2;
/* 215*/                for(k2 = 31; i2 >>> k2 == 0; k2--);
/* 219*/                if((k1 << 5) + k2 > k)
/* 220*/                    k = (k1 << 5) + k2;
                    }

                }

/* 227*/        int j1 = k - i;
/* 228*/        int l1 = l - j;
/* 230*/        if(j1 < 0 || l1 < 0)
/* 231*/            return null;
/* 234*/        else
/* 234*/            return (new int[] {
/* 234*/                i, j, j1, l1
                    });
            }

            public final int[] getTopLeftOnBit()
            {
                int i;
/* 243*/        for(i = 0; i < bits.length && bits[i] == 0; i++);
/* 247*/        if(i == bits.length)
/* 248*/            return null;
/* 250*/        int j = i / rowSize;
/* 251*/        int k = i % rowSize << 5;
/* 253*/        i = bits[i];
                int l;
/* 254*/        for(l = 0; i << 31 - l == 0; l++);
/* 258*/        k += l;
/* 259*/        return (new int[] {
/* 259*/            k, j
                });
            }

            public final int[] getBottomRightOnBit()
            {
                int i;
/* 263*/        for(i = bits.length - 1; i >= 0 && bits[i] == 0; i--);
/* 267*/        if(i < 0)
/* 268*/            return null;
/* 271*/        int j = i / rowSize;
/* 272*/        int k = i % rowSize << 5;
/* 274*/        i = bits[i];
                int l;
/* 275*/        for(l = 31; i >>> l == 0; l--);
/* 279*/        k += l;
/* 281*/        return (new int[] {
/* 281*/            k, j
                });
            }

            public final int getWidth()
            {
/* 288*/        return width;
            }

            public final int getHeight()
            {
/* 295*/        return height;
            }

            public final boolean equals(Object obj)
            {
/* 300*/        if(!(obj instanceof BitMatrix))
/* 301*/            return false;
/* 303*/        obj = (BitMatrix)obj;
/* 304*/        return width == ((BitMatrix) (obj)).width && height == ((BitMatrix) (obj)).height && rowSize == ((BitMatrix) (obj)).rowSize && Arrays.equals(bits, ((BitMatrix) (obj)).bits);
            }

            public final int hashCode()
            {
/* 310*/        int i = width;
/* 311*/        i = i * 31 + width;
/* 312*/        i = i * 31 + height;
/* 313*/        i = i * 31 + rowSize;
/* 314*/        return i = i * 31 + Arrays.hashCode(bits);
            }

            public final String toString()
            {
/* 320*/        StringBuilder stringbuilder = new StringBuilder(height * (width + 1));
/* 321*/        for(int i = 0; i < height; i++)
                {
/* 322*/            for(int j = 0; j < width; j++)
/* 323*/                stringbuilder.append(get(j, i) ? "X " : "  ");

/* 325*/            stringbuilder.append('\n');
                }

/* 327*/        return stringbuilder.toString();
            }

            public final BitMatrix clone()
            {
/* 332*/        return new BitMatrix(width, height, rowSize, (int[])bits.clone());
            }

            public final volatile Object clone()
                throws CloneNotSupportedException
            {
/*  36*/        return clone();
            }

            private final int width;
            private final int height;
            private final int rowSize;
            private final int bits[];
}
