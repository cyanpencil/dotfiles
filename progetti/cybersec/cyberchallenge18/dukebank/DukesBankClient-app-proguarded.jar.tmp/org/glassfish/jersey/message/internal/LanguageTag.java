// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LanguageTag.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Locale;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader

public class LanguageTag
{

            protected LanguageTag()
            {
            }

            public static LanguageTag valueOf(String s)
                throws IllegalArgumentException
            {
/*  61*/        LanguageTag languagetag = new LanguageTag();
/*  64*/        try
                {
/*  64*/            languagetag.parse(s);
                }
                // Misplaced declaration of an exception variable
/*  65*/        catch(String s)
                {
/*  66*/            throw new IllegalArgumentException(s);
                }
/*  69*/        return languagetag;
            }

            public LanguageTag(String s, String s1)
            {
/*  73*/        if(s1 != null && s1.length() > 0)
/*  74*/            tag = (new StringBuilder()).append(s).append("-").append(s1).toString();
/*  76*/        else
/*  76*/            tag = s;
/*  79*/        primaryTag = s;
/*  81*/        subTags = s1;
            }

            public LanguageTag(String s)
                throws ParseException
            {
/*  85*/        this(HttpHeaderReader.newInstance(s));
            }

            public LanguageTag(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/*  90*/        httpheaderreader.hasNext();
/*  92*/        tag = httpheaderreader.nextToken().toString();
/*  94*/        if(httpheaderreader.hasNext())
                {
/*  95*/            throw new ParseException("Invalid Language tag", httpheaderreader.getIndex());
                } else
                {
/*  98*/            parse(tag);
/*  99*/            return;
                }
            }

            public final boolean isCompatible(Locale locale)
            {
/* 102*/        if(tag.equals("*"))
/* 103*/            return true;
/* 106*/        if(subTags == null)
/* 107*/            return primaryTag.equalsIgnoreCase(locale.getLanguage());
/* 109*/        return primaryTag.equalsIgnoreCase(locale.getLanguage()) && subTags.equalsIgnoreCase(locale.getCountry());
            }

            public final Locale getAsLocale()
            {
/* 115*/        if(subTags == null)
/* 115*/            return new Locale(primaryTag);
/* 115*/        else
/* 115*/            return new Locale(primaryTag, subTags);
            }

            protected final void parse(String s)
                throws ParseException
            {
/* 121*/        if(!isValid(s))
/* 122*/            throw new ParseException((new StringBuilder("String, ")).append(s).append(", is not a valid language tag").toString(), 0);
                int i;
/* 125*/        if((i = s.indexOf('-')) == -1)
                {
/* 127*/            primaryTag = s;
/* 128*/            subTags = null;
/* 128*/            return;
                } else
                {
/* 130*/            primaryTag = s.substring(0, i);
/* 131*/            subTags = s.substring(i + 1, s.length());
/* 133*/            return;
                }
            }

            private boolean isValid(String s)
            {
/* 142*/        int i = 0;
/* 143*/        int j = 0;
/* 144*/        for(int k = 0; k < s.length(); k++)
                {
                    char c;
/* 145*/            if((c = s.charAt(k)) == '-')
                    {
/* 147*/                if(i == 0)
/* 148*/                    return false;
/* 150*/                i = 0;
/* 151*/                j++;
/* 151*/                continue;
                    }
/* 152*/            if('A' <= c && c <= 'Z' || 'a' <= c && c <= 'z' || j > 0 && '0' <= c && c <= '9')
                    {
/* 153*/                if(++i > 8)
/* 155*/                    return false;
                    } else
                    {
/* 158*/                return false;
                    }
                }

/* 161*/        return i != 0;
            }

            public final String getTag()
            {
/* 165*/        return tag;
            }

            public final String getPrimaryTag()
            {
/* 169*/        return primaryTag;
            }

            public final String getSubTags()
            {
/* 173*/        return subTags;
            }

            public boolean equals(Object obj)
            {
/* 178*/        if(this == obj)
/* 179*/            return true;
/* 181*/        if(!(obj instanceof LanguageTag) || obj.getClass() == getClass())
/* 182*/            return false;
/* 185*/        obj = (LanguageTag)obj;
/* 187*/        if(primaryTag == null ? ((LanguageTag) (obj)).primaryTag != null : !primaryTag.equals(((LanguageTag) (obj)).primaryTag))
/* 188*/            return false;
/* 190*/        if(subTags == null ? ((LanguageTag) (obj)).subTags != null : !subTags.equals(((LanguageTag) (obj)).subTags))
/* 191*/            return false;
/* 193*/        return tag == null ? ((LanguageTag) (obj)).tag != null : !tag.equals(((LanguageTag) (obj)).tag);
            }

            public int hashCode()
            {
/* 199*/        int i = tag == null ? 0 : tag.hashCode();
/* 200*/        i = i * 31 + (primaryTag == null ? 0 : primaryTag.hashCode());
/* 201*/        return i = i * 31 + (subTags == null ? 0 : subTags.hashCode());
            }

            public String toString()
            {
/* 207*/        return (new StringBuilder()).append(primaryTag).append(subTags != null ? subTags : "").toString();
            }

            String tag;
            String primaryTag;
            String subTags;
}
