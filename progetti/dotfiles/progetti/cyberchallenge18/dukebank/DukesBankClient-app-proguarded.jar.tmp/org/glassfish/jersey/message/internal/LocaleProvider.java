// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocaleProvider.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Locale;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            LanguageTag, Utils

public class LocaleProvider
    implements HeaderDelegateProvider
{

            public LocaleProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  63*/        return java/util/Locale.isAssignableFrom(class1);
            }

            public String toString(Locale locale)
            {
/*  69*/        Utils.throwIllegalArgumentExceptionIfNull(locale, LocalizationMessages.LOCALE_IS_NULL());
/*  71*/        if(locale.getCountry().length() == 0)
/*  72*/            return locale.getLanguage();
/*  74*/        else
/*  74*/            return (new StringBuilder()).append(locale.getLanguage()).append('-').append(locale.getCountry()).toString();
            }

            public Locale fromString(String s)
            {
/*  81*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.LOCALE_IS_NULL());
                LanguageTag languagetag;
/*  84*/        return (languagetag = new LanguageTag(s)).getAsLocale();
                ParseException parseexception;
/*  86*/        parseexception;
/*  87*/        throw new IllegalArgumentException((new StringBuilder("Error parsing date '")).append(s).append("'").toString(), parseexception);
            }

            public volatile String toString(Object obj)
            {
/*  58*/        return toString((Locale)obj);
            }

            public volatile Object fromString(String s)
            {
/*  58*/        return fromString(s);
            }
}
