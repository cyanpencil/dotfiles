// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataMask.java

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            DataMask

static final class <init> extends DataMask
{

            final boolean isMasked(int i, int j)
            {
/* 128*/        return ((i >>> 1) + j / 3 & 1) == 0;
            }

            private ()
            {
/* 125*/        super(null);
            }

            ( )
            {
/* 125*/        this();
            }
}
