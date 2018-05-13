// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BarcodeRow.java

package com.google.zxing.pdf417.encoder;


final class BarcodeRow
{

            BarcodeRow(int i)
            {
/*  32*/        row = new byte[i];
/*  33*/        currentLocation = 0;
            }

            final void set(int i, byte byte0)
            {
/*  43*/        row[i] = byte0;
            }

            final void set(int i, boolean flag)
            {
/*  53*/        row[i] = (byte)(flag ? 1 : 0);
            }

            final void addBar(boolean flag, int i)
            {
/*  61*/        for(int j = 0; j < i; j++)
/*  62*/            set(currentLocation++, flag);

            }

            final byte[] getScaledRow(int i)
            {
/*  79*/        byte abyte0[] = new byte[row.length * i];
/*  80*/        for(int j = 0; j < abyte0.length; j++)
/*  81*/            abyte0[j] = row[j / i];

/*  83*/        return abyte0;
            }

            private final byte row[];
            private int currentLocation;
}
