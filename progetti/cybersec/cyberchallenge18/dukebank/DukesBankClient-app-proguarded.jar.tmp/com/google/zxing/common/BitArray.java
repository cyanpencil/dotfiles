// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BitArray.java

package com.google.zxing.common;

import java.util.Arrays;

public final class BitArray
    implements Cloneable
{

            public BitArray()
            {
/*  32*/        size = 0;
/*  33*/        bits = new int[1];
            }

            public BitArray(int i)
            {
/*  37*/        size = i;
/*  38*/        bits = makeArray(i);
            }

            BitArray(int ai[], int i)
            {
/*  43*/        bits = ai;
/*  44*/        size = i;
            }

            public final int getSize()
            {
/*  48*/        return size;
            }

            public final int getSizeInBytes()
            {
/*  52*/        return (size + 7) / 8;
            }

            private void ensureCapacity(int i)
            {
/*  56*/        if(i > bits.length << 5)
                {
/*  57*/            i = makeArray(i);
/*  58*/            System.arraycopy(bits, 0, i, 0, bits.length);
/*  59*/            bits = i;
                }
            }

            public final boolean get(int i)
            {
/*  68*/        return (bits[i / 32] & 1 << (i & 0x1f)) != 0;
            }

            public final void set(int i)
            {
/*  77*/        bits[i / 32] |= 1 << (i & 0x1f);
            }

            public final void flip(int i)
            {
/*  86*/        bits[i / 32] ^= 1 << (i & 0x1f);
            }

            public final int getNextSet(int i)
            {
/*  96*/        if(i >= size)
/*  97*/            return size;
/*  99*/        int j = i / 32;
                int k;
/* 100*/        for(k = (k = bits[j]) & ~((1 << (i & 0x1f)) - 1); k == 0; k = bits[j])
/* 104*/            if(++j == bits.length)
/* 105*/                return size;

/* 109*/        if((i = (j << 5) + Integer.numberOfTrailingZeros(k)) > size)
/* 110*/            return size;
/* 110*/        else
/* 110*/            return i;
            }

            public final int getNextUnset(int i)
            {
/* 117*/        if(i >= size)
/* 118*/            return size;
/* 120*/        int j = i / 32;
                int k;
/* 121*/        for(k = (k = ~bits[j]) & ~((1 << (i & 0x1f)) - 1); k == 0; k = ~bits[j])
/* 125*/            if(++j == bits.length)
/* 126*/                return size;

/* 130*/        if((i = (j << 5) + Integer.numberOfTrailingZeros(k)) > size)
/* 131*/            return size;
/* 131*/        else
/* 131*/            return i;
            }

            public final void setBulk(int i, int j)
            {
/* 142*/        bits[i / 32] = j;
            }

            public final void setRange(int i, int j)
            {
/* 152*/        if(j < i)
/* 153*/            throw new IllegalArgumentException();
/* 155*/        if(j == i)
/* 156*/            return;
/* 158*/        j--;
/* 159*/        int k = i / 32;
/* 160*/        int l = j / 32;
/* 161*/        for(int i1 = k; i1 <= l; i1++)
                {
/* 162*/            int j1 = i1 <= k ? i & 0x1f : 0;
/* 163*/            int k1 = i1 >= l ? j & 0x1f : 31;
                    int l1;
/* 165*/            if(j1 == 0 && k1 == 31)
                    {
/* 166*/                l1 = -1;
                    } else
                    {
/* 168*/                l1 = 0;
/* 169*/                for(j1 = j1; j1 <= k1; j1++)
/* 170*/                    l1 |= 1 << j1;

                    }
/* 173*/            bits[i1] |= l1;
                }

            }

            public final void clear()
            {
/* 181*/        int i = bits.length;
/* 182*/        for(int j = 0; j < i; j++)
/* 183*/            bits[j] = 0;

            }

            public final boolean isRange(int i, int j, boolean flag)
            {
/* 197*/        if(j < i)
/* 198*/            throw new IllegalArgumentException();
/* 200*/        if(j == i)
/* 201*/            return true;
/* 203*/        j--;
/* 204*/        int k = i / 32;
/* 205*/        int l = j / 32;
/* 206*/        for(int i1 = k; i1 <= l; i1++)
                {
/* 207*/            int j1 = i1 <= k ? i & 0x1f : 0;
/* 208*/            int k1 = i1 >= l ? j & 0x1f : 31;
                    int l1;
/* 210*/            if(j1 == 0 && k1 == 31)
                    {
/* 211*/                l1 = -1;
                    } else
                    {
/* 213*/                l1 = 0;
/* 214*/                for(j1 = j1; j1 <= k1; j1++)
/* 215*/                    l1 |= 1 << j1;

                    }
/* 221*/            if((bits[i1] & l1) != (flag ? l1 : 0))
/* 222*/                return false;
                }

/* 225*/        return true;
            }

            public final void appendBit(boolean flag)
            {
/* 229*/        ensureCapacity(size + 1);
/* 230*/        if(flag)
/* 231*/            bits[size / 32] |= 1 << (size & 0x1f);
/* 233*/        size++;
            }

            public final void appendBits(int i, int j)
            {
/* 242*/        if(j < 0 || j > 32)
/* 243*/            throw new IllegalArgumentException("Num bits must be between 0 and 32");
/* 245*/        ensureCapacity(size + j);
/* 246*/        for(j = j; j > 0; j--)
/* 247*/            appendBit((i >> j - 1 & 1) == 1);

            }

            public final void appendBitArray(BitArray bitarray)
            {
/* 252*/        int i = bitarray.size;
/* 253*/        ensureCapacity(size + i);
/* 254*/        for(int j = 0; j < i; j++)
/* 255*/            appendBit(bitarray.get(j));

            }

            public final void xor(BitArray bitarray)
            {
/* 260*/        if(bits.length != bitarray.bits.length)
/* 261*/            throw new IllegalArgumentException("Sizes don't match");
/* 263*/        for(int i = 0; i < bits.length; i++)
/* 266*/            bits[i] ^= bitarray.bits[i];

            }

            public final void toBytes(int i, byte abyte0[], int j, int k)
            {
/* 279*/        for(int l = 0; l < k; l++)
                {
/* 280*/            int i1 = 0;
/* 281*/            for(int j1 = 0; j1 < 8; j1++)
                    {
/* 282*/                if(get(i))
/* 283*/                    i1 |= 1 << 7 - j1;
/* 285*/                i++;
                    }

/* 287*/            abyte0[j + l] = (byte)i1;
                }

            }

            public final int[] getBitArray()
            {
/* 296*/        return bits;
            }

            public final void reverse()
            {
/* 303*/        int ai[] = new int[bits.length];
                int i;
/* 305*/        int k = (i = (size - 1) / 32) + 1;
/* 307*/        for(int l = 0; l < k; l++)
                {
                    long l1;
/* 308*/            l1 = (l1 = (l1 = (l1 = (l1 = (l1 = bits[l]) >> 1 & 0x55555555L | (l1 & 0x55555555L) << 1) >> 2 & 0x33333333L | (l1 & 0x33333333L) << 2) >> 4 & 0xf0f0f0fL | (l1 & 0xf0f0f0fL) << 4) >> 8 & 0xff00ffL | (l1 & 0xff00ffL) << 8) >> 16 & 65535L | (l1 & 65535L) << 16;
/* 314*/            ai[i - l] = (int)l1;
                }

/* 317*/        if(size != k << 5)
                {
/* 318*/            int i1 = (k << 5) - size;
/* 319*/            int j1 = 1;
/* 320*/            for(int k1 = 0; k1 < 31 - i1; k1++)
/* 321*/                j1 = j1 << 1 | 1;

/* 323*/            int i2 = ai[0] >> i1 & j1;
/* 324*/            for(int j = 1; j < k; j++)
                    {
/* 325*/                int j2 = ai[j];
/* 326*/                i2 |= j2 << 32 - i1;
/* 327*/                ai[j - 1] = i2;
/* 328*/                i2 = j2 >> i1 & j1;
                    }

/* 330*/            ai[k - 1] = i2;
                }
/* 332*/        bits = ai;
            }

            private static int[] makeArray(int i)
            {
/* 336*/        return new int[(i + 31) / 32];
            }

            public final boolean equals(Object obj)
            {
/* 341*/        if(!(obj instanceof BitArray))
/* 342*/            return false;
/* 344*/        obj = (BitArray)obj;
/* 345*/        return size == ((BitArray) (obj)).size && Arrays.equals(bits, ((BitArray) (obj)).bits);
            }

            public final int hashCode()
            {
/* 350*/        return 31 * size + Arrays.hashCode(bits);
            }

            public final String toString()
            {
/* 355*/        StringBuilder stringbuilder = new StringBuilder(size);
/* 356*/        for(int i = 0; i < size; i++)
                {
/* 357*/            if((i & 7) == 0)
/* 358*/                stringbuilder.append(' ');
/* 360*/            stringbuilder.append(get(i) ? 'X' : '.');
                }

/* 362*/        return stringbuilder.toString();
            }

            public final BitArray clone()
            {
/* 367*/        return new BitArray((int[])bits.clone(), size);
            }

            public final volatile Object clone()
                throws CloneNotSupportedException
            {
/*  26*/        return clone();
            }

            private int bits[];
            private int size;
}
