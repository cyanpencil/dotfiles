// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Decoder.java

package com.google.zxing.aztec.decoder;


// Referenced classes of package com.google.zxing.aztec.decoder:
//            Decoder

static class ble
{

            static final int $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[];

            static 
            {
/* 175*/        $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = new int[ble.values().length];
/* 175*/        try
                {
/* 175*/            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[ble.UPPER.ordinal()] = 1;
                }
/* 175*/        catch(NoSuchFieldError _ex) { }
/* 175*/        try
                {
/* 175*/            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[ble.LOWER.ordinal()] = 2;
                }
/* 175*/        catch(NoSuchFieldError _ex) { }
/* 175*/        try
                {
/* 175*/            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[ble.MIXED.ordinal()] = 3;
                }
/* 175*/        catch(NoSuchFieldError _ex) { }
/* 175*/        try
                {
/* 175*/            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[ble.PUNCT.ordinal()] = 4;
                }
/* 175*/        catch(NoSuchFieldError _ex) { }
/* 175*/        try
                {
/* 175*/            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[ble.DIGIT.ordinal()] = 5;
                }
/* 175*/        catch(NoSuchFieldError _ex) { }
            }
}
