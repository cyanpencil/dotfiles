// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TextParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class TextParsedResult extends ParsedResult
{

            public TextParsedResult(String s, String s1)
            {
/*  31*/        super(ParsedResultType.TEXT);
/*  32*/        text = s;
/*  33*/        language = s1;
            }

            public final String getText()
            {
/*  37*/        return text;
            }

            public final String getLanguage()
            {
/*  41*/        return language;
            }

            public final String getDisplayResult()
            {
/*  46*/        return text;
            }

            private final String text;
            private final String language;
}
