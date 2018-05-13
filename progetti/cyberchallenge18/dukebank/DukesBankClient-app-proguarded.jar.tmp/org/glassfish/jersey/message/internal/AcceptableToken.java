// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AcceptableToken.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            Token, HttpHeaderReader, Qualified

public class AcceptableToken extends Token
    implements Qualified
{

            public AcceptableToken(String s)
                throws ParseException
            {
/*  55*/        this(HttpHeaderReader.newInstance(s));
            }

            public AcceptableToken(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/*  52*/        quality = 1000;
/*  60*/        httpheaderreader.hasNext();
/*  62*/        token = httpheaderreader.nextToken().toString();
/*  64*/        if(httpheaderreader.hasNext())
/*  65*/            quality = HttpHeaderReader.readQualityFactorParameter(httpheaderreader);
            }

            public int getQuality()
            {
/*  71*/        return quality;
            }

            protected int quality;
}
