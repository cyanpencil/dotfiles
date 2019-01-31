// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DigestAuthenticator.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            DigestAuthenticator

static final class md extends Enum
{

            public static md[] values()
            {
/* 403*/        return (md[])$VALUES.clone();
            }

            public static m_3B_.clone valueOf(String s)
            {
/* 403*/        return (m_3B_.clone)Enum.valueOf(org/glassfish/jersey/client/authentication/DigestAuthenticator$Algorithm, s);
            }

            public final String toString()
            {
/* 416*/        return md;
            }

            public static md parse(String s)
            {
/* 420*/        if(s == null || s.isEmpty())
/* 421*/            return UNSPECIFIED;
/* 423*/        if((s = s.trim()).contains(MD5_SESS.md) || s.contains(MD5_SESS.md.toLowerCase()))
/* 425*/            return MD5_SESS;
/* 427*/        else
/* 427*/            return MD5;
            }

            public static final MD5_SESS UNSPECIFIED;
            public static final MD5_SESS MD5;
            public static final MD5_SESS MD5_SESS;
            private final String md;
            private static final MD5_SESS $VALUES[];

            static 
            {
/* 405*/        UNSPECIFIED = new <init>("UNSPECIFIED", 0, null);
/* 406*/        MD5 = new <init>("MD5", 1, "MD5");
/* 407*/        MD5_SESS = new <init>("MD5_SESS", 2, "MD5-sess");
/* 403*/        $VALUES = (new .VALUES[] {
/* 403*/            UNSPECIFIED, MD5, MD5_SESS
                });
            }

            private (String s, int i, String s1)
            {
/* 410*/        super(s, i);
/* 411*/        md = s1;
            }
}
