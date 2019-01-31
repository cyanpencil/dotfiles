// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedNumeric.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedObject

final class DecodedNumeric extends DecodedObject
{

            DecodedNumeric(int i, int j, int k)
                throws FormatException
            {
/*  43*/        super(i);
/*  45*/        if(j < 0 || j > 10 || k < 0 || k > 10)
                {
/*  46*/            throw FormatException.getFormatInstance();
                } else
                {
/*  49*/            firstDigit = j;
/*  50*/            secondDigit = k;
/*  51*/            return;
                }
            }

            final int getFirstDigit()
            {
/*  54*/        return firstDigit;
            }

            final int getSecondDigit()
            {
/*  58*/        return secondDigit;
            }

            final int getValue()
            {
/*  62*/        return firstDigit * 10 + secondDigit;
            }

            final boolean isFirstDigitFNC1()
            {
/*  66*/        return firstDigit == 10;
            }

            final boolean isSecondDigitFNC1()
            {
/*  70*/        return secondDigit == 10;
            }

            final boolean isAnyFNC1()
            {
/*  74*/        return firstDigit == 10 || secondDigit == 10;
            }

            private final int firstDigit;
            private final int secondDigit;
            static final int FNC1 = 10;
}
