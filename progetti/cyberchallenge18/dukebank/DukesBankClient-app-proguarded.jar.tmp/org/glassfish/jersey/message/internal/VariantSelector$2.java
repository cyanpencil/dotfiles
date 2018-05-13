// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   VariantSelector.java

package org.glassfish.jersey.message.internal;

import java.util.Locale;
import javax.ws.rs.core.Variant;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AcceptableLanguageTag, VariantSelector

static class riantHolder
    implements mensionChecker
{

            public final Locale getDimension(riantHolder riantholder)
            {
/* 138*/        return riantHolder.access._mth000(riantholder).getLanguage();
            }

            public final boolean isCompatible(AcceptableLanguageTag acceptablelanguagetag, Locale locale)
            {
/* 143*/        return acceptablelanguagetag.isCompatible(locale);
            }

            public final int getQualitySource(riantHolder riantholder, Locale locale)
            {
/* 148*/        return 0;
            }

            public final String getVaryHeaderValue()
            {
/* 153*/        return "Accept-Language";
            }

            public final volatile boolean isCompatible(Object obj, Object obj1)
            {
/* 134*/        return isCompatible((AcceptableLanguageTag)obj, (Locale)obj1);
            }

            public final volatile int getQualitySource(riantHolder riantholder, Object obj)
            {
/* 134*/        return getQualitySource(riantholder, (Locale)obj);
            }

            public final volatile Object getDimension(riantHolder riantholder)
            {
/* 134*/        return getDimension(riantholder);
            }

            riantHolder()
            {
            }
}
