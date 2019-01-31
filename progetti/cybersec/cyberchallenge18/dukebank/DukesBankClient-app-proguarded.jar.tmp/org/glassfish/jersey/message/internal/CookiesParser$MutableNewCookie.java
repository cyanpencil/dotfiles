// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CookiesParser.java

package org.glassfish.jersey.message.internal;

import java.util.Date;
import javax.ws.rs.core.NewCookie;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            CookiesParser

static class value
{

            public NewCookie getImmutableNewCookie()
            {
/* 139*/        return new NewCookie(name, value, path, domain, version, comment, maxAge, expiry, secure, httpOnly);
            }

            String name;
            String value;
            String path;
            String domain;
            int version;
            String comment;
            int maxAge;
            boolean secure;
            boolean httpOnly;
            Date expiry;

            public (String s, String s1)
            {
/* 122*/        name = null;
/* 123*/        value = null;
/* 124*/        path = null;
/* 125*/        domain = null;
/* 126*/        version = 1;
/* 127*/        comment = null;
/* 128*/        maxAge = -1;
/* 129*/        secure = false;
/* 130*/        httpOnly = false;
/* 131*/        expiry = null;
/* 134*/        name = s;
/* 135*/        value = s1;
            }
}
