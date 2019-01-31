// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   URIParsedResult.java

package com.google.zxing.client.result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType, ResultParser

public final class URIParsedResult extends ParsedResult
{

            public URIParsedResult(String s, String s1)
            {
/*  32*/        super(ParsedResultType.URI);
/*  33*/        uri = massageURI(s);
/*  34*/        title = s1;
            }

            public final String getURI()
            {
/*  38*/        return uri;
            }

            public final String getTitle()
            {
/*  42*/        return title;
            }

            public final boolean isPossiblyMaliciousURI()
            {
/*  54*/        return USER_IN_HOST.matcher(uri).find();
            }

            public final String getDisplayResult()
            {
/*  59*/        StringBuilder stringbuilder = new StringBuilder(30);
/*  60*/        maybeAppend(title, stringbuilder);
/*  61*/        maybeAppend(uri, stringbuilder);
/*  62*/        return stringbuilder.toString();
            }

            private static String massageURI(String s)
            {
                int i;
/*  70*/        if((i = (s = s.trim()).indexOf(':')) < 0)
/*  74*/            s = (new StringBuilder("http://")).append(s).toString();
/*  75*/        else
/*  75*/        if(isColonFollowedByPortNumber(s, i))
/*  77*/            s = (new StringBuilder("http://")).append(s).toString();
/*  79*/        return s;
            }

            private static boolean isColonFollowedByPortNumber(String s, int i)
            {
/*  83*/        i++;
                int j;
/*  84*/        if((j = s.indexOf('/', i)) < 0)
/*  86*/            j = s.length();
/*  88*/        return ResultParser.isSubstringOfDigits(s, i, j - i);
            }

            private static final Pattern USER_IN_HOST = Pattern.compile(":/*([^/@]+)@[^/]+");
            private final String uri;
            private final String title;

}
