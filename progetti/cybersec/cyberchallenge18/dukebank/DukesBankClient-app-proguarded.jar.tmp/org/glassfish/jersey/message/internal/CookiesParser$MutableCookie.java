// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CookiesParser.java

package org.glassfish.jersey.message.internal;

import javax.ws.rs.core.Cookie;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            CookiesParser

static class value
{

            public Cookie getImmutableCookie()
            {
/*  78*/        return new Cookie(name, value, path, domain, version);
            }

            String name;
            String value;
            int version;
            String path;
            String domain;

            public (String s, String s1)
            {
/*  68*/        version = 1;
/*  69*/        path = null;
/*  70*/        domain = null;
/*  73*/        name = s;
/*  74*/        value = s1;
            }
}
