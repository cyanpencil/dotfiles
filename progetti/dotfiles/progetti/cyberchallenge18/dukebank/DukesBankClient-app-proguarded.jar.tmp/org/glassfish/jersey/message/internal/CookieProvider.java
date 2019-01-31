// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CookieProvider.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Cookie;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, StringBuilderUtils, Utils

public class CookieProvider
    implements HeaderDelegateProvider
{

            public CookieProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  62*/        return class1 == javax/ws/rs/core/Cookie;
            }

            public String toString(Cookie cookie)
            {
/*  68*/        Utils.throwIllegalArgumentExceptionIfNull(cookie, LocalizationMessages.COOKIE_IS_NULL());
                StringBuilder stringbuilder;
/*  70*/        (stringbuilder = new StringBuilder()).append("$Version=").append(cookie.getVersion()).append(';');
/*  74*/        stringbuilder.append(cookie.getName()).append('=');
/*  75*/        StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, cookie.getValue());
/*  77*/        if(cookie.getDomain() != null)
                {
/*  78*/            stringbuilder.append(";$Domain=");
/*  79*/            StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, cookie.getDomain());
                }
/*  81*/        if(cookie.getPath() != null)
                {
/*  82*/            stringbuilder.append(";$Path=");
/*  83*/            StringBuilderUtils.appendQuotedIfWhitespace(stringbuilder, cookie.getPath());
                }
/*  85*/        return stringbuilder.toString();
            }

            public Cookie fromString(String s)
            {
/*  90*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.COOKIE_IS_NULL());
/*  91*/        return HttpHeaderReader.readCookie(s);
            }

            public volatile String toString(Object obj)
            {
/*  57*/        return toString((Cookie)obj);
            }

            public volatile Object fromString(String s)
            {
/*  57*/        return fromString(s);
            }
}
