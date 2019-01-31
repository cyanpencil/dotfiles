// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 

package com.owlike.genson.internal.asm;


public class ByteVector
{

    public ByteVector()
    {
        a = new byte[64];
    }

    public ByteVector(int i)
    {
        a = new byte[i];
    }

    public ByteVector putByte(int i)
    {
        int j;
        if((j = b) + 1 > a.length)
            a(1);
        a[j++] = (byte)i;
        b = j;
        return this;
    }

    ByteVector a(int i, int j)
    {
        int k;
        if((k = b) + 2 > a.length)
            a(2);
        byte abyte0[];
        (abyte0 = a)[k++] = (byte)i;
        abyte0[k++] = (byte)j;
        b = k;
        return this;
    }

    public ByteVector putShort(int i)
    {
        int j;
        if((j = b) + 2 > a.length)
            a(2);
        byte abyte0[];
        (abyte0 = a)[j++] = (byte)(i >>> 8);
        abyte0[j++] = (byte)i;
        b = j;
        return this;
    }

    ByteVector b(int i, int j)
    {
        int k;
        if((k = b) + 3 > a.length)
            a(3);
        byte abyte0[];
        (abyte0 = a)[k++] = (byte)i;
        abyte0[k++] = (byte)(j >>> 8);
        abyte0[k++] = (byte)j;
        b = k;
        return this;
    }

    public ByteVector putInt(int i)
    {
        int j;
        if((j = b) + 4 > a.length)
            a(4);
        byte abyte0[];
        (abyte0 = a)[j++] = i >> 24;
        abyte0[j++] = (byte)(i >>> 16);
        abyte0[j++] = (byte)(i >>> 8);
        abyte0[j++] = (byte)i;
        b = j;
        return this;
    }

    public ByteVector putLong(long l)
    {
        int i;
        if((i = b) + 8 > a.length)
            a(8);
        byte abyte0[] = a;
        int j = (int)(l >>> 32);
        abyte0[i++] = j >> 24;
        abyte0[i++] = (byte)(j >>> 16);
        abyte0[i++] = (byte)(j >>> 8);
        abyte0[i++] = (byte)j;
        j = (int)l;
        abyte0[i++] = j >> 24;
        abyte0[i++] = (byte)(j >>> 16);
        abyte0[i++] = (byte)(j >>> 8);
        abyte0[i++] = (byte)j;
        b = i;
        return this;
    }

    public ByteVector putUTF8(String s)
    {
        int i;
        if((i = s.length()) > 65535)
            throw new IllegalArgumentException();
        int j;
        if((j = b) + 2 + i > a.length)
            a(i + 2);
        byte abyte0[];
        (abyte0 = a)[j++] = (byte)(i >>> 8);
        abyte0[j++] = (byte)i;
        for(int k = 0; k < i; k++)
        {
            char c;
            if((c = s.charAt(k)) > 0 && c <= '\177')
            {
                abyte0[j++] = (byte)c;
            } else
            {
                b = j;
                return encodeUTF8(s, k, 65535);
            }
        }

        b = j;
        return this;
    }

    ByteVector encodeUTF8(String s, int i, int j)
    {
        int k = s.length();
        int l = i;
        for(int i1 = i; i1 < k; i1++)
        {
            char c;
            if((c = s.charAt(i1)) > 0 && c <= '\177')
            {
                l++;
                continue;
            }
            if(c > '\u07FF')
                l += 3;
            else
                l += 2;
        }

        if(l > j)
            throw new IllegalArgumentException();
        int j1;
        if((j1 = b - i - 2) >= 0)
        {
            a[j1] = (byte)(l >>> 8);
            a[j1 + 1] = (byte)l;
        }
        if((b + l) - i > a.length)
            a(l - i);
        j = b;
        for(i = i; i < k; i++)
        {
            char c1;
            if((c1 = s.charAt(i)) > 0 && c1 <= '\177')
            {
                a[j++] = (byte)c1;
                continue;
            }
            if(c1 > '\u07FF')
            {
                a[j++] = (byte)(0xe0 | c1 >> 12 & 0xf);
                a[j++] = (byte)(0x80 | c1 >> 6 & 0x3f);
                a[j++] = (byte)(0x80 | c1 & 0x3f);
            } else
            {
                a[j++] = (byte)(0xc0 | c1 >> 6 & 0x1f);
                a[j++] = (byte)(0x80 | c1 & 0x3f);
            }
        }

        b = j;
        return this;
    }

    public ByteVector putByteArray(byte abyte0[], int i, int j)
    {
        if(b + j > a.length)
            a(j);
        if(abyte0 != null)
            System.arraycopy(abyte0, i, a, b, j);
        b += j;
        return this;
    }

    private void a(int i)
    {
        int j = 2 * a.length;
        i = b + i;
        i = new byte[j <= i ? i : j];
        System.arraycopy(a, 0, i, 0, b);
        a = i;
    }

    byte a[];
    int b;
}
