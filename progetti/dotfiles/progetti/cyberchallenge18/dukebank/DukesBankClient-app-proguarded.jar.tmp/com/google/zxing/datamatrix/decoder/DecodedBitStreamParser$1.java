// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            DecodedBitStreamParser

static class de
{

            static final int $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[];

            static 
            {
/*  92*/        $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode = new int[de.values().length];
/*  92*/        try
                {
/*  92*/            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[de.C40_ENCODE.ordinal()] = 1;
                }
/*  92*/        catch(NoSuchFieldError _ex) { }
/*  92*/        try
                {
/*  92*/            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[de.TEXT_ENCODE.ordinal()] = 2;
                }
/*  92*/        catch(NoSuchFieldError _ex) { }
/*  92*/        try
                {
/*  92*/            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[de.ANSIX12_ENCODE.ordinal()] = 3;
                }
/*  92*/        catch(NoSuchFieldError _ex) { }
/*  92*/        try
                {
/*  92*/            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[de.EDIFACT_ENCODE.ordinal()] = 4;
                }
/*  92*/        catch(NoSuchFieldError _ex) { }
/*  92*/        try
                {
/*  92*/            $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode[de.BASE256_ENCODE.ordinal()] = 5;
                }
/*  92*/        catch(NoSuchFieldError _ex) { }
            }
}
