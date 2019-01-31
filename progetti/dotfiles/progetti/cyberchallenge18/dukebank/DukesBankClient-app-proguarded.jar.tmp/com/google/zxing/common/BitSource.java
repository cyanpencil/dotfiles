// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BitSource.java

package com.google.zxing.common;


public final class BitSource
{

            public BitSource(byte abyte0[])
            {
/*  39*/        bytes = abyte0;
            }

            public final int getBitOffset()
            {
/*  46*/        return bitOffset;
            }

            public final int getByteOffset()
            {
/*  53*/        return byteOffset;
            }

            public final int readBits(int i)
            {
/*  63*/        if(i <= 0 || i > 32 || i > available())
/*  64*/            throw new IllegalArgumentException(String.valueOf(i));
/*  67*/        int j = 0;
/*  70*/        if(bitOffset > 0)
                {
/*  71*/            int k = 8 - bitOffset;
/*  72*/            int i1 = i >= k ? k : i;
/*  73*/            j = k - i1;
/*  74*/            k = (255 >> 8 - i1) << j;
/*  75*/            j = (bytes[byteOffset] & k) >> j;
/*  76*/            i -= i1;
/*  77*/            bitOffset += i1;
/*  78*/            if(bitOffset == 8)
                    {
/*  79*/                bitOffset = 0;
/*  80*/                byteOffset++;
                    }
                }
/*  85*/        if(i > 0)
                {
/*  86*/            for(; i >= 8; i -= 8)
                    {
/*  87*/                j = j << 8 | bytes[byteOffset] & 0xff;
/*  88*/                byteOffset++;
                    }

/*  93*/            if(i > 0)
                    {
/*  94*/                int l = 8 - i;
/*  95*/                int j1 = (255 >> l) << l;
/*  96*/                j = j << i | (bytes[byteOffset] & j1) >> l;
/*  97*/                bitOffset += i;
                    }
                }
/* 101*/        return j;
            }

            public final int available()
            {
/* 108*/        return 8 * (bytes.length - byteOffset) - bitOffset;
            }

            private final byte bytes[];
            private int byteOffset;
            private int bitOffset;
}
