// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedInformation.java

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedObject

final class DecodedInformation extends DecodedObject
{

            DecodedInformation(int i, String s)
            {
/*  40*/        super(i);
/*  41*/        newString = s;
/*  42*/        remaining = false;
/*  43*/        remainingValue = 0;
            }

            DecodedInformation(int i, String s, int j)
            {
/*  47*/        super(i);
/*  48*/        remaining = true;
/*  49*/        remainingValue = j;
/*  50*/        newString = s;
            }

            final String getNewString()
            {
/*  54*/        return newString;
            }

            final boolean isRemaining()
            {
/*  58*/        return remaining;
            }

            final int getRemainingValue()
            {
/*  62*/        return remainingValue;
            }

            private final String newString;
            private final int remainingValue;
            private final boolean remaining;
}
