// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewCookieProvider.java

package org.glassfish.jersey.message.internal;

import java.text.SimpleDateFormat;
import javax.ws.rs.core.NewCookie;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpDateFormat, HttpHeaderReader, StringBuilderUtils, Utils

public class NewCookieProvider
    implements HeaderDelegateProvider
{

            public NewCookieProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  62*/        return class1 == javax/ws/rs/core/NewCookie;
            }

            public String toString(NewCookie newcookie)
            {
/*  68*/        Utils.throwIllegalArgumentExceptionIfNull(newcookie, LocalizationMessages.NEW_COOKIE_IS_NULL());
                StringBuilder stringbuilder;
/*  70*/        (stringbuilder = new StringBuilder()).append(newcookie.getName()).append('=');
/*  73*/        StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, newcookie.getValue());
/*  75*/        stringbuilder.append(";Version=").append(newcookie.getVersion());
/*  77*/        if(newcookie.getComment() != null)
                {
/*  78*/            stringbuilder.append(";Comment=");
/*  79*/            StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, newcookie.getComment());
                }
/*  81*/        if(newcookie.getDomain() != null)
                {
/*  82*/            stringbuilder.append(";Domain=");
/*  83*/            StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, newcookie.getDomain());
                }
/*  85*/        if(newcookie.getPath() != null)
                {
/*  86*/            stringbuilder.append(";Path=");
/*  87*/            StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, newcookie.getPath());
                }
/*  89*/        if(newcookie.getMaxAge() != -1)
                {
/*  90*/            stringbuilder.append(";Max-Age=");
/*  91*/            stringbuilder.append(newcookie.getMaxAge());
                }
/*  93*/        if(newcookie.isSecure())
/*  94*/            stringbuilder.append(";Secure");
/*  96*/        if(newcookie.isHttpOnly())
/*  97*/            stringbuilder.append(";HttpOnly");
/*  99*/        if(newcookie.getExpiry() != null)
                {
/* 100*/            stringbuilder.append(";Expires=");
/* 101*/            stringbuilder.append(HttpDateFormat.getPreferredDateFormat().format(newcookie.getExpiry()));
                }
/* 104*/        return stringbuilder.toString();
            }

            public NewCookie fromString(String s)
            {
/* 109*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.NEW_COOKIE_IS_NULL());
/* 110*/        return HttpHeaderReader.readNewCookie(s);
            }

            public volatile String toString(Object obj)
            {
/*  57*/        return toString((NewCookie)obj);
            }

            public volatile Object fromString(String s)
            {
/*  57*/        return fromString(s);
            }
}
