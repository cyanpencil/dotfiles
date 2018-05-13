// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmailDoCoMoResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, EmailAddressParsedResult, ParsedResult

public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser
{

            public EmailDoCoMoResultParser()
            {
            }

            public final EmailAddressParsedResult parse(Result result)
            {
/*  36*/        if(!(result = getMassagedText(result)).startsWith("MATMSG:"))
/*  38*/            return null;
                String as[];
/*  40*/        if((as = matchDoCoMoPrefixedField("TO:", result, true)) == null)
/*  42*/            return null;
                String s;
/*  44*/        if(!isBasicallyValidEmailAddress(s = as[0]))
                {
/*  46*/            return null;
                } else
                {
/*  48*/            String s1 = matchSingleDoCoMoPrefixedField("SUB:", result, false);
/*  49*/            result = matchSingleDoCoMoPrefixedField("BODY:", result, false);
/*  50*/            return new EmailAddressParsedResult(s, s1, result, (new StringBuilder("mailto:")).append(s).toString());
                }
            }

            static boolean isBasicallyValidEmailAddress(String s)
            {
/*  60*/        return s != null && ATEXT_ALPHANUMERIC.matcher(s).matches() && s.indexOf('@') >= 0;
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  30*/        return parse(result);
            }

            private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

}
