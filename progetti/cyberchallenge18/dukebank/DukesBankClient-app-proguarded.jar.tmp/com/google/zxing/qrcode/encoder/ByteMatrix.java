// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteMatrix.java

package com.google.zxing.qrcode.encoder;


public final class ByteMatrix
{

            public ByteMatrix(int i, int j)
            {
/*  32*/        bytes = new byte[j][i];
/*  33*/        width = i;
/*  34*/        height = j;
            }

            public final int getHeight()
            {
/*  38*/        return height;
            }

            public final int getWidth()
            {
/*  42*/        return width;
            }

            public final byte get(int i, int j)
            {
/*  46*/        return bytes[j][i];
            }

            public final byte[][] getArray()
            {
/*  53*/        return bytes;
            }

            public final void set(int i, int j, byte byte0)
            {
/*  57*/        bytes[j][i] = byte0;
            }

            public final void set(int i, int j, int k)
            {
/*  61*/        bytes[j][i] = (byte)k;
            }

            public final void set(int i, int j, boolean flag)
            {
/*  65*/        bytes[j][i] = (byte)(flag ? 1 : 0);
            }

            public final void clear(byte byte0)
            {
/*  69*/        for(int i = 0; i < height; i++)
                {
/*  70*/            for(int j = 0; j < width; j++)
/*  71*/                bytes[i][j] = byte0;

                }

            }

            public final String toString()
            {
/*  78*/        StringBuilder stringbuilder = new StringBuilder(2 * width * height + 2);
/*  79*/        for(int i = 0; i < height; i++)
                {
/*  80*/            for(int j = 0; j < width; j++)
/*  81*/                switch(bytes[i][j])
                        {
/*  83*/                case 0: // '\0'
/*  83*/                    stringbuilder.append(" 0");
                            break;

/*  86*/                case 1: // '\001'
/*  86*/                    stringbuilder.append(" 1");
                            break;

/*  89*/                default:
/*  89*/                    stringbuilder.append("  ");
                            break;
                        }

/*  93*/            stringbuilder.append('\n');
                }

/*  95*/        return stringbuilder.toString();
            }

            private final byte bytes[][];
            private final int width;
            private final int height;
}
