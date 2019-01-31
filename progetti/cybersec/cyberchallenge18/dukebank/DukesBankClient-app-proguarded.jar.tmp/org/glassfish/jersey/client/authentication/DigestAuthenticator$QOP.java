// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DigestAuthenticator.java

package org.glassfish.jersey.client.authentication;

import org.glassfish.jersey.client.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.client.authentication:
//            DigestAuthenticator

static final class qop extends Enum
{

            public static qop[] values()
            {
/* 376*/        return (qop[])$VALUES.clone();
            }

            public static P_3B_.clone valueOf(String s)
            {
/* 376*/        return (P_3B_.clone)Enum.valueOf(org/glassfish/jersey/client/authentication/DigestAuthenticator$QOP, s);
            }

            public final String toString()
            {
/* 389*/        return qop;
            }

            public static qop parse(String s)
            {
/* 393*/        if(s == null || s.isEmpty())
/* 394*/            return UNSPECIFIED;
/* 396*/        if(s.contains("auth"))
/* 397*/            return AUTH;
/* 399*/        else
/* 399*/            throw new UnsupportedOperationException(LocalizationMessages.DIGEST_FILTER_QOP_UNSUPPORTED(s));
            }

            public static final AUTH UNSPECIFIED;
            public static final AUTH AUTH;
            private final String qop;
            private static final AUTH $VALUES[];

            static 
            {
/* 378*/        UNSPECIFIED = new <init>("UNSPECIFIED", 0, null);
/* 379*/        AUTH = new <init>("AUTH", 1, "auth");
/* 376*/        $VALUES = (new .VALUES[] {
/* 376*/            UNSPECIFIED, AUTH
                });
            }

            private (String s, int i, String s1)
            {
/* 383*/        super(s, i);
/* 384*/        qop = s1;
            }
}
