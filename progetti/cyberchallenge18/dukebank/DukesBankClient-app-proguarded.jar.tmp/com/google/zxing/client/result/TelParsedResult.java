// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TelParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class TelParsedResult extends ParsedResult
{

            public TelParsedResult(String s, String s1, String s2)
            {
/*  29*/        super(ParsedResultType.TEL);
/*  30*/        number = s;
/*  31*/        telURI = s1;
/*  32*/        title = s2;
            }

            public final String getNumber()
            {
/*  36*/        return number;
            }

            public final String getTelURI()
            {
/*  40*/        return telURI;
            }

            public final String getTitle()
            {
/*  44*/        return title;
            }

            public final String getDisplayResult()
            {
/*  49*/        StringBuilder stringbuilder = new StringBuilder(20);
/*  50*/        maybeAppend(number, stringbuilder);
/*  51*/        maybeAppend(title, stringbuilder);
/*  52*/        return stringbuilder.toString();
            }

            private final String number;
            private final String telURI;
            private final String title;
}
