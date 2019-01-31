// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewCookie.java

package javax.ws.rs.core;

import java.util.Date;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs.core:
//            Cookie

public class NewCookie extends Cookie
{

            public NewCookie(String s, String s1)
            {
/*  79*/        this(s, s1, null, null, 1, null, -1, null, false, false);
            }

            public NewCookie(String s, String s1, String s2, String s3, String s4, int i, boolean flag)
            {
/* 101*/        this(s, s1, s2, s3, 1, s4, i, null, flag, false);
            }

            public NewCookie(String s, String s1, String s2, String s3, String s4, int i, boolean flag, 
                    boolean flag1)
            {
/* 126*/        this(s, s1, s2, s3, 1, s4, i, null, flag, flag1);
            }

            public NewCookie(String s, String s1, String s2, String s3, int i, String s4, int j, 
                    boolean flag)
            {
/* 150*/        this(s, s1, s2, s3, i, s4, j, null, flag, false);
            }

            public NewCookie(String s, String s1, String s2, String s3, int i, String s4, int j, 
                    Date date, boolean flag, boolean flag1)
            {
/* 179*/        super(s, s1, s2, s3, i);
/* 180*/        comment = s4;
/* 181*/        maxAge = j;
/* 182*/        expiry = date;
/* 183*/        secure = flag;
/* 184*/        httpOnly = flag1;
            }

            public NewCookie(Cookie cookie)
            {
/* 194*/        this(cookie, null, -1, null, false, false);
            }

            public NewCookie(Cookie cookie, String s, int i, boolean flag)
            {
/* 207*/        this(cookie, s, i, null, flag, false);
            }

            public NewCookie(Cookie cookie, String s, int i, Date date, boolean flag, boolean flag1)
            {
/* 223*/        super(cookie != null ? cookie.getName() : null, cookie != null ? cookie.getValue() : null, cookie != null ? cookie.getPath() : null, cookie != null ? cookie.getDomain() : null, cookie != null ? cookie.getVersion() : 1);
/* 228*/        comment = s;
/* 229*/        maxAge = i;
/* 230*/        expiry = date;
/* 231*/        secure = flag;
/* 232*/        httpOnly = flag1;
            }

            public static NewCookie valueOf(String s)
            {
/* 244*/        return (NewCookie)_flddelegate.fromString(s);
            }

            public String getComment()
            {
/* 253*/        return comment;
            }

            public int getMaxAge()
            {
/* 272*/        return maxAge;
            }

            public Date getExpiry()
            {
/* 290*/        return expiry;
            }

            public boolean isSecure()
            {
/* 301*/        return secure;
            }

            public boolean isHttpOnly()
            {
/* 314*/        return httpOnly;
            }

            public Cookie toCookie()
            {
/* 326*/        return new Cookie(getName(), getValue(), getPath(), getDomain(), getVersion());
            }

            public String toString()
            {
/* 338*/        return _flddelegate.toString(this);
            }

            public int hashCode()
            {
/* 348*/        int i = super.hashCode();
/* 349*/        i = i * 59 + (comment == null ? 0 : comment.hashCode());
/* 350*/        i = i * 59 + maxAge;
/* 351*/        i = i + 59 + (expiry == null ? 0 : expiry.hashCode());
/* 352*/        i = i * 59 + (secure ? 1 : 0);
/* 353*/        return i = i * 59 + (httpOnly ? 1 : 0);
            }

            public boolean equals(Object obj)
            {
/* 369*/        if(obj == null)
/* 370*/            return false;
/* 372*/        if(getClass() != obj.getClass())
/* 373*/            return false;
/* 375*/        obj = (NewCookie)obj;
/* 376*/        if(getName() != ((NewCookie) (obj)).getName() && (getName() == null || !getName().equals(((NewCookie) (obj)).getName())))
/* 377*/            return false;
/* 379*/        if(getValue() != ((NewCookie) (obj)).getValue() && (getValue() == null || !getValue().equals(((NewCookie) (obj)).getValue())))
/* 380*/            return false;
/* 382*/        if(getVersion() != ((NewCookie) (obj)).getVersion())
/* 383*/            return false;
/* 385*/        if(getPath() != ((NewCookie) (obj)).getPath() && (getPath() == null || !getPath().equals(((NewCookie) (obj)).getPath())))
/* 386*/            return false;
/* 388*/        if(getDomain() != ((NewCookie) (obj)).getDomain() && (getDomain() == null || !getDomain().equals(((NewCookie) (obj)).getDomain())))
/* 389*/            return false;
/* 391*/        if(comment != ((NewCookie) (obj)).comment && (comment == null || !comment.equals(((NewCookie) (obj)).comment)))
/* 392*/            return false;
/* 394*/        if(maxAge != ((NewCookie) (obj)).maxAge)
/* 395*/            return false;
/* 398*/        if(expiry != ((NewCookie) (obj)).expiry && (expiry == null || !expiry.equals(((NewCookie) (obj)).expiry)))
/* 399*/            return false;
/* 402*/        if(secure != ((NewCookie) (obj)).secure)
/* 403*/            return false;
/* 405*/        return httpOnly == ((NewCookie) (obj)).httpOnly;
            }

            public static final int DEFAULT_MAX_AGE = -1;
            private static final javax.ws.rs.ext.RuntimeDelegate.HeaderDelegate _flddelegate = RuntimeDelegate.getInstance().createHeaderDelegate(javax/ws/rs/core/NewCookie);
            private final String comment;
            private final int maxAge;
            private final Date expiry;
            private final boolean secure;
            private final boolean httpOnly;

}
