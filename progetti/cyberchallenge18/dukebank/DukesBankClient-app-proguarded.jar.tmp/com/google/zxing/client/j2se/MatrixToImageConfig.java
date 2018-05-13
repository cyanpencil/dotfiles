// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MatrixToImageConfig.java

package com.google.zxing.client.j2se;


public final class MatrixToImageConfig
{

            public MatrixToImageConfig()
            {
/*  37*/        this(0xff000000, -1);
            }

            public MatrixToImageConfig(int i, int j)
            {
/*  45*/        onColor = i;
/*  46*/        offColor = j;
            }

            public final int getPixelOnColor()
            {
/*  50*/        return onColor;
            }

            public final int getPixelOffColor()
            {
/*  54*/        return offColor;
            }

            final int getBufferedImageColorModel()
            {
/*  59*/        return onColor != 0xff000000 || offColor != -1 ? 1 : 12;
            }

            public static final int BLACK = 0xff000000;
            public static final int WHITE = -1;
            private final int onColor;
            private final int offColor;
}
