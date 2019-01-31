// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SMSParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class SMSParsedResult extends ParsedResult
{

            public SMSParsedResult(String s, String s1, String s2, String s3)
            {
/*  33*/        super(ParsedResultType.SMS);
/*  34*/        numbers = (new String[] {
/*  34*/            s
                });
/*  35*/        vias = (new String[] {
/*  35*/            s1
                });
/*  36*/        subject = s2;
/*  37*/        body = s3;
            }

            public SMSParsedResult(String as[], String as1[], String s, String s1)
            {
/*  44*/        super(ParsedResultType.SMS);
/*  45*/        numbers = as;
/*  46*/        vias = as1;
/*  47*/        subject = s;
/*  48*/        body = s1;
            }

            public final String getSMSURI()
            {
                StringBuilder stringbuilder;
/*  52*/        (stringbuilder = new StringBuilder()).append("sms:");
/*  54*/        boolean flag = true;
/*  55*/        for(int i = 0; i < numbers.length; i++)
                {
/*  56*/            if(flag)
/*  57*/                flag = false;
/*  59*/            else
/*  59*/                stringbuilder.append(',');
/*  61*/            stringbuilder.append(numbers[i]);
/*  62*/            if(vias != null && vias[i] != null)
                    {
/*  63*/                stringbuilder.append(";via=");
/*  64*/                stringbuilder.append(vias[i]);
                    }
                }

/*  67*/        boolean flag1 = body != null;
/*  68*/        flag = subject != null;
/*  69*/        if(flag1 || flag)
                {
/*  70*/            stringbuilder.append('?');
/*  71*/            if(flag1)
                    {
/*  72*/                stringbuilder.append("body=");
/*  73*/                stringbuilder.append(body);
                    }
/*  75*/            if(flag)
                    {
/*  76*/                if(flag1)
/*  77*/                    stringbuilder.append('&');
/*  79*/                stringbuilder.append("subject=");
/*  80*/                stringbuilder.append(subject);
                    }
                }
/*  83*/        return stringbuilder.toString();
            }

            public final String[] getNumbers()
            {
/*  87*/        return numbers;
            }

            public final String[] getVias()
            {
/*  91*/        return vias;
            }

            public final String getSubject()
            {
/*  95*/        return subject;
            }

            public final String getBody()
            {
/*  99*/        return body;
            }

            public final String getDisplayResult()
            {
/* 104*/        StringBuilder stringbuilder = new StringBuilder(100);
/* 105*/        maybeAppend(numbers, stringbuilder);
/* 106*/        maybeAppend(subject, stringbuilder);
/* 107*/        maybeAppend(body, stringbuilder);
/* 108*/        return stringbuilder.toString();
            }

            private final String numbers[];
            private final String vias[];
            private final String subject;
            private final String body;
}
