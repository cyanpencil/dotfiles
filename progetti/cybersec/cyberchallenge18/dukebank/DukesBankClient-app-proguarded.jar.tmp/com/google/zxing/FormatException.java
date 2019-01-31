// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FormatException.java

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            ReaderException

public final class FormatException extends ReaderException
{

            private FormatException()
            {
            }

            public static FormatException getFormatInstance()
            {
/*  35*/        return instance;
            }

            private static final FormatException instance = new FormatException();

}
