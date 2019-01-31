// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmailAddressResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, EmailAddressParsedResult, EmailDoCoMoResultParser, ParsedResult

public final class EmailAddressResultParser extends ResultParser
{

            public EmailAddressResultParser()
            {
            }

            public final EmailAddressParsedResult parse(Result result)
            {
/*  33*/        if((result = getMassagedText(result)).startsWith("mailto:") || result.startsWith("MAILTO:"))
                {
                    String s;
                    int i;
/*  37*/            if((i = (s = result.substring(7)).indexOf('?')) >= 0)
/*  40*/                s = s.substring(0, i);
/*  42*/            s = urlDecode(s);
/*  43*/            Map map = parseNameValuePairs(result);
/*  44*/            String s1 = null;
/*  45*/            String s2 = null;
/*  46*/            if(map != null)
                    {
/*  47*/                if(s.isEmpty())
/*  48*/                    s = (String)map.get("to");
/*  50*/                s1 = (String)map.get("subject");
/*  51*/                s2 = (String)map.get("body");
                    }
/*  53*/            return new EmailAddressParsedResult(s, s1, s2, result);
                }
/*  55*/        if(!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(result))
                {
/*  56*/            return null;
                } else
                {
/*  58*/            Result result1 = result;
/*  59*/            return new EmailAddressParsedResult(result1, null, null, (new StringBuilder("mailto:")).append(result1).toString());
                }
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  29*/        return parse(result);
            }
}
