// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AcceptableLanguageTag.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            LanguageTag, HttpHeaderReader, Qualified

public class AcceptableLanguageTag extends LanguageTag
    implements Qualified
{

            public AcceptableLanguageTag(String s, String s1)
            {
/*  55*/        super(s, s1);
/*  56*/        quality = 1000;
            }

            public AcceptableLanguageTag(String s)
                throws ParseException
            {
/*  60*/        this(HttpHeaderReader.newInstance(s));
            }

            public AcceptableLanguageTag(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/*  65*/        httpheaderreader.hasNext();
/*  67*/        tag = httpheaderreader.nextToken().toString();
/*  68*/        if(!tag.equals("*"))
/*  69*/            parse(tag);
/*  71*/        else
/*  71*/            primaryTag = tag;
/*  74*/        if(httpheaderreader.hasNext())
                {
/*  75*/            quality = HttpHeaderReader.readQualityFactorParameter(httpheaderreader);
/*  75*/            return;
                } else
                {
/*  77*/            quality = 1000;
/*  79*/            return;
                }
            }

            public int getQuality()
            {
/*  83*/        return quality;
            }

            public boolean equals(Object obj)
            {
/*  88*/        if(!super.equals(obj))
/*  89*/            return false;
/*  91*/        obj = (AcceptableLanguageTag)obj;
/*  92*/        return quality == ((AcceptableLanguageTag) (obj)).quality;
            }

            public int hashCode()
            {
/* 100*/        int i = super.hashCode();
/* 101*/        return i = i * 47 + quality;
            }

            private final int quality;
}
