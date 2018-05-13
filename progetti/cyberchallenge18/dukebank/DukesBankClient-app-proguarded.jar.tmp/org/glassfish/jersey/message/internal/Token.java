// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Token.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader

public class Token
{

            protected Token()
            {
            }

            public Token(String s)
                throws ParseException
            {
/*  58*/        this(HttpHeaderReader.newInstance(s));
            }

            public Token(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/*  63*/        httpheaderreader.hasNext();
/*  65*/        token = httpheaderreader.nextToken().toString();
/*  67*/        if(httpheaderreader.hasNext())
/*  68*/            throw new ParseException("Invalid token", httpheaderreader.getIndex());
/*  70*/        else
/*  70*/            return;
            }

            public String getToken()
            {
/*  73*/        return token;
            }

            public final boolean isCompatible(String s)
            {
/*  77*/        if(token.equals("*"))
/*  78*/            return true;
/*  81*/        else
/*  81*/            return token.equals(s);
            }

            protected String token;
}
