// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BookmarkDoCoMoResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, URIParsedResult, URIResultParser, ParsedResult

public final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser
{

            public BookmarkDoCoMoResultParser()
            {
            }

            public final URIParsedResult parse(Result result)
            {
/*  28*/        if(!(result = result.getText()).startsWith("MEBKM:"))
/*  30*/            return null;
/*  32*/        String s = matchSingleDoCoMoPrefixedField("TITLE:", result, true);
/*  33*/        if((result = matchDoCoMoPrefixedField("URL:", result, true)) == null)
/*  35*/            return null;
/*  37*/        if(URIResultParser.isBasicallyValidURI(result = result[0]))
/*  38*/            return new URIParsedResult(result, s);
/*  38*/        else
/*  38*/            return null;
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  24*/        return parse(result);
            }
}
