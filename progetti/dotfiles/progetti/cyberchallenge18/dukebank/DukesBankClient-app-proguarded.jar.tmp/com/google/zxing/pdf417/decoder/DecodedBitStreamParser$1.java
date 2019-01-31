// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.pdf417.decoder;


// Referenced classes of package com.google.zxing.pdf417.decoder:
//            DecodedBitStreamParser

static class de
{

            static final int $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[];

            static 
            {
/* 276*/        $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = new int[de.values().length];
/* 276*/        try
                {
/* 276*/            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[de.ALPHA.ordinal()] = 1;
                }
/* 276*/        catch(NoSuchFieldError _ex) { }
/* 276*/        try
                {
/* 276*/            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[de.LOWER.ordinal()] = 2;
                }
/* 276*/        catch(NoSuchFieldError _ex) { }
/* 276*/        try
                {
/* 276*/            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[de.MIXED.ordinal()] = 3;
                }
/* 276*/        catch(NoSuchFieldError _ex) { }
/* 276*/        try
                {
/* 276*/            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[de.PUNCT.ordinal()] = 4;
                }
/* 276*/        catch(NoSuchFieldError _ex) { }
/* 276*/        try
                {
/* 276*/            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[de.ALPHA_SHIFT.ordinal()] = 5;
                }
/* 276*/        catch(NoSuchFieldError _ex) { }
/* 276*/        try
                {
/* 276*/            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[de.PUNCT_SHIFT.ordinal()] = 6;
                }
/* 276*/        catch(NoSuchFieldError _ex) { }
            }
}
