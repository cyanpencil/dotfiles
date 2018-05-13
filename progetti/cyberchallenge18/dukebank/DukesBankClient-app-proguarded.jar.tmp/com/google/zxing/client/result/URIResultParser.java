// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   URIResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, URIParsedResult, ParsedResult

public final class URIResultParser extends ResultParser
{

            public URIResultParser()
            {
            }

            public final URIParsedResult parse(Result result)
            {
/*  39*/        if((result = getMassagedText(result)).startsWith("URL:") || result.startsWith("URI:"))
/*  43*/            return new URIParsedResult(result.substring(4).trim(), null);
/*  45*/        if(isBasicallyValidURI(result = result.trim()))
/*  46*/            return new URIParsedResult(result, null);
/*  46*/        else
/*  46*/            return null;
            }

            static boolean isBasicallyValidURI(String s)
            {
/*  50*/        if(s.contains(" "))
/*  52*/            return false;
                Matcher matcher;
/*  54*/        if((matcher = URL_WITH_PROTOCOL_PATTERN.matcher(s)).find() && matcher.start() == 0)
/*  56*/            return true;
/*  58*/        return (matcher = URL_WITHOUT_PROTOCOL_PATTERN.matcher(s)).find() && matcher.start() == 0;
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  29*/        return parse(result);
            }

            private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z0-9]{2,}:");
            private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

}
