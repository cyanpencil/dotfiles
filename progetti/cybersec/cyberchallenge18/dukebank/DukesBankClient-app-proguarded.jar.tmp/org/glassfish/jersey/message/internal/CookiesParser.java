// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CookiesParser.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpDateFormat

class CookiesParser
{
    static class MutableNewCookie
    {

                public NewCookie getImmutableNewCookie()
                {
/* 139*/            return new NewCookie(name, value, path, domain, version, comment, maxAge, expiry, secure, httpOnly);
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

                public MutableNewCookie(String s, String s1)
                {
/* 122*/            name = null;
/* 123*/            value = null;
/* 124*/            path = null;
/* 125*/            domain = null;
/* 126*/            version = 1;
/* 127*/            comment = null;
/* 128*/            maxAge = -1;
/* 129*/            secure = false;
/* 130*/            httpOnly = false;
/* 131*/            expiry = null;
/* 134*/            name = s;
/* 135*/            value = s1;
                }
    }

    static class MutableCookie
    {

                public Cookie getImmutableCookie()
                {
/*  78*/            return new Cookie(name, value, path, domain, version);
                }

                String name;
                String value;
                int version;
                String path;
                String domain;

                public MutableCookie(String s, String s1)
                {
/*  68*/            version = 1;
/*  69*/            path = null;
/*  70*/            domain = null;
/*  73*/            name = s;
/*  74*/            value = s1;
                }
    }


            public static Map parseCookies(String s)
            {
/*  83*/        s = s.split("[;,]");
/*  84*/        LinkedHashMap linkedhashmap = new LinkedHashMap();
/*  85*/        int i = 0;
/*  86*/        MutableCookie mutablecookie = null;
/*  87*/        int j = (s = s).length;
/*  87*/        for(int k = 0; k < j; k++)
                {
                    String as[];
/*  87*/            String s2 = (as = (as = s[k]).split("=", 2)).length <= 0 ? "" : as[0].trim();
                    String s1;
/*  90*/            if((s1 = as.length <= 1 ? "" : as[1].trim()).startsWith("\"") && (s1.endsWith("\"") && s1.length() > 1))
/*  92*/                s1 = s1.substring(1, s1.length() - 1);
/*  94*/            if(!s2.startsWith("$"))
                    {
/*  95*/                if(mutablecookie != null)
/*  96*/                    linkedhashmap.put(mutablecookie.name, mutablecookie.getImmutableCookie());
/*  99*/                (mutablecookie = new MutableCookie(s2, s1)).version = i;
/* 100*/                continue;
                    }
/* 101*/            if(s2.startsWith("$Version"))
                    {
/* 102*/                i = Integer.parseInt(s1);
/* 102*/                continue;
                    }
/* 103*/            if(s2.startsWith("$Path") && mutablecookie != null)
                    {
/* 104*/                mutablecookie.path = s1;
/* 104*/                continue;
                    }
/* 105*/            if(s2.startsWith("$Domain") && mutablecookie != null)
/* 106*/                mutablecookie.domain = s1;
                }

/* 109*/        if(mutablecookie != null)
/* 110*/            linkedhashmap.put(mutablecookie.name, mutablecookie.getImmutableCookie());
/* 112*/        return linkedhashmap;
            }

            public static Cookie parseCookie(String s)
            {
/* 116*/        return (Cookie)((java.util.Map.Entry)(s = parseCookies(s)).entrySet().iterator().next()).getValue();
            }

            public static NewCookie parseNewCookie(String s)
            {
/* 144*/        s = s.split("[;,]");
/* 146*/        MutableNewCookie mutablenewcookie = null;
/* 147*/        for(int i = 0; i < s.length; i++)
                {
                    String as[];
/* 148*/            String s2 = (as = s[i].split("=", 2)).length <= 0 ? "" : as[0].trim();
                    String s1;
/* 150*/            if((s1 = as.length <= 1 ? "" : as[1].trim()).startsWith("\"") && (s1.endsWith("\"") && s1.length() > 1))
/* 153*/                s1 = s1.substring(1, s1.length() - 1);
/* 156*/            if(mutablenewcookie == null)
                    {
/* 157*/                mutablenewcookie = new MutableNewCookie(s2, s1);
/* 157*/                continue;
                    }
/* 159*/            if((s2 = s2.toLowerCase()).startsWith("comment"))
                    {
/* 162*/                mutablenewcookie.comment = s1;
/* 162*/                continue;
                    }
/* 163*/            if(s2.startsWith("domain"))
                    {
/* 164*/                mutablenewcookie.domain = s1;
/* 164*/                continue;
                    }
/* 165*/            if(s2.startsWith("max-age"))
                    {
/* 166*/                mutablenewcookie.maxAge = Integer.parseInt(s1);
/* 166*/                continue;
                    }
/* 167*/            if(s2.startsWith("path"))
                    {
/* 168*/                mutablenewcookie.path = s1;
/* 168*/                continue;
                    }
/* 169*/            if(s2.startsWith("secure"))
                    {
/* 170*/                mutablenewcookie.secure = true;
/* 170*/                continue;
                    }
/* 171*/            if(s2.startsWith("version"))
                    {
/* 172*/                mutablenewcookie.version = Integer.parseInt(s1);
/* 172*/                continue;
                    }
/* 173*/            if(s2.startsWith("domain"))
                    {
/* 174*/                mutablenewcookie.domain = s1;
/* 174*/                continue;
                    }
/* 175*/            if(s2.startsWith("httponly"))
                    {
/* 176*/                mutablenewcookie.httpOnly = true;
/* 176*/                continue;
                    }
/* 177*/            if(!s2.startsWith("expires"))
/* 179*/                continue;
/* 179*/            try
                    {
/* 179*/                mutablenewcookie.expiry = HttpDateFormat.readDate((new StringBuilder()).append(s1).append(", ").append(s[++i]).toString());
                    }
/* 180*/            catch(ParseException parseexception)
                    {
/* 181*/                LOGGER.log(Level.FINE, LocalizationMessages.ERROR_NEWCOOKIE_EXPIRES(s1), parseexception);
                    }
                }

/* 187*/        return mutablenewcookie.getImmutableNewCookie();
            }

            private CookiesParser()
            {
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/CookiesParser.getName());

}
