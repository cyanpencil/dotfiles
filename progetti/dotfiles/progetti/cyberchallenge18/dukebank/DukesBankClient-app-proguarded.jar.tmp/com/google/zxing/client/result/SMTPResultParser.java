// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SMTPResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, EmailAddressParsedResult, ParsedResult

public final class SMTPResultParser extends ResultParser
{

            public SMTPResultParser()
            {
            }

            public final EmailAddressParsedResult parse(Result result)
            {
/*  31*/        if(!(result = getMassagedText(result)).startsWith("smtp:") && !result.startsWith("SMTP:"))
/*  33*/            return null;
/*  35*/        result = result.substring(5);
/*  36*/        String s = null;
/*  37*/        String s1 = null;
                int i;
/*  38*/        if((i = result.indexOf(':')) >= 0)
                {
/*  40*/            s = result.substring(i + 1);
/*  41*/            result = result.substring(0, i);
/*  42*/            if((i = s.indexOf(':')) >= 0)
                    {
/*  44*/                s1 = s.substring(i + 1);
/*  45*/                s = s.substring(0, i);
                    }
                }
/*  48*/        String s2 = (new StringBuilder("mailto:")).append(result).toString();
/*  49*/        return new EmailAddressParsedResult(result, s, s1, s2);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  27*/        return parse(result);
            }
}
