// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Encoder.java

package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.Mode;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            Encoder

static class 
{

            static final int $SwitchMap$com$google$zxing$qrcode$decoder$Mode[];

            static 
            {
/* 471*/        $SwitchMap$com$google$zxing$qrcode$decoder$Mode = new int[Mode.values().length];
/* 471*/        try
                {
/* 471*/            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.NUMERIC.ordinal()] = 1;
                }
/* 471*/        catch(NoSuchFieldError _ex) { }
/* 471*/        try
                {
/* 471*/            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;
                }
/* 471*/        catch(NoSuchFieldError _ex) { }
/* 471*/        try
                {
/* 471*/            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 3;
                }
/* 471*/        catch(NoSuchFieldError _ex) { }
/* 471*/        try
                {
/* 471*/            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.KANJI.ordinal()] = 4;
                }
/* 471*/        catch(NoSuchFieldError _ex) { }
            }
}
