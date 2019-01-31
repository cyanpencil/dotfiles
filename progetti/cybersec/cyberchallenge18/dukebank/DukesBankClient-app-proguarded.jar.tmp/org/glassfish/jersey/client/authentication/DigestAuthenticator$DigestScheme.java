// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DigestAuthenticator.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            DigestAuthenticator

final class nc
{

            public final int incrementCounter()
            {
/* 456*/        return ++nc;
            }

            public final String getNonce()
            {
/* 460*/        return nonce;
            }

            public final String getRealm()
            {
/* 464*/        return realm;
            }

            public final String getOpaque()
            {
/* 468*/        return opaque;
            }

            public final opaque getAlgorithm()
            {
/* 472*/        return algorithm;
            }

            public final algorithm getQop()
            {
/* 476*/        return qop;
            }

            public final boolean isStale()
            {
/* 480*/        return stale;
            }

            public final int getNc()
            {
/* 484*/        return nc;
            }

            private final String realm;
            private final String nonce;
            private final String opaque;
            private final nc algorithm;
            private final nc qop;
            private final boolean stale;
            private volatile int nc;
            final DigestAuthenticator this$0;

            Q(String s, String s1, String s2, Q q, Q q1, boolean flag)
            {
/* 445*/        this$0 = DigestAuthenticator.this;
/* 445*/        super();
/* 446*/        realm = s;
/* 447*/        nonce = s1;
/* 448*/        opaque = s2;
/* 449*/        qop = q;
/* 450*/        algorithm = q1;
/* 451*/        stale = flag;
/* 452*/        nc = 0;
            }
}
