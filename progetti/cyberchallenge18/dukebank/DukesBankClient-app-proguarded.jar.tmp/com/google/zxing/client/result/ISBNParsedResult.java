// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ISBNParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class ISBNParsedResult extends ParsedResult
{

            ISBNParsedResult(String s)
            {
/*  27*/        super(ParsedResultType.ISBN);
/*  28*/        isbn = s;
            }

            public final String getISBN()
            {
/*  32*/        return isbn;
            }

            public final String getDisplayResult()
            {
/*  37*/        return isbn;
            }

            private final String isbn;
}
