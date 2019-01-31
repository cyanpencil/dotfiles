// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedChar.java

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedObject

final class DecodedChar extends DecodedObject
{

            DecodedChar(int i, char c)
            {
/*  40*/        super(i);
/*  41*/        value = c;
            }

            final char getValue()
            {
/*  45*/        return value;
            }

            final boolean isFNC1()
            {
/*  49*/        return value == '$';
            }

            private final char value;
            static final char FNC1 = 36;
}
