// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EmailAddressParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class EmailAddressParsedResult extends ParsedResult
{

            EmailAddressParsedResult(String s, String s1, String s2, String s3)
            {
/*  33*/        super(ParsedResultType.EMAIL_ADDRESS);
/*  34*/        emailAddress = s;
/*  35*/        subject = s1;
/*  36*/        body = s2;
/*  37*/        mailtoURI = s3;
            }

            public final String getEmailAddress()
            {
/*  41*/        return emailAddress;
            }

            public final String getSubject()
            {
/*  45*/        return subject;
            }

            public final String getBody()
            {
/*  49*/        return body;
            }

            public final String getMailtoURI()
            {
/*  53*/        return mailtoURI;
            }

            public final String getDisplayResult()
            {
/*  58*/        StringBuilder stringbuilder = new StringBuilder(30);
/*  59*/        maybeAppend(emailAddress, stringbuilder);
/*  60*/        maybeAppend(subject, stringbuilder);
/*  61*/        maybeAppend(body, stringbuilder);
/*  62*/        return stringbuilder.toString();
            }

            private final String emailAddress;
            private final String subject;
            private final String body;
            private final String mailtoURI;
}
