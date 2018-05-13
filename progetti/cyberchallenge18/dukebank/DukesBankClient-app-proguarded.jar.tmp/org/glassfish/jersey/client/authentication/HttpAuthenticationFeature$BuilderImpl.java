// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFeature.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFeature, HttpAuthenticationFilter

static class mode
    implements , lder
{

            public lder credentials(String s, String s1)
            {
/* 280*/        return credentials(s, s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null);
            }

            public  credentials(String s, byte abyte0[])
            {
/* 285*/        credentialsForBasic(s, abyte0);
/* 286*/        credentialsForDigest(s, abyte0);
/* 287*/        return this;
            }

            public lder credentialsForBasic(String s, String s1)
            {
/* 292*/        return credentialsForBasic(s, s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null);
            }

            public lder credentialsForBasic(String s, byte abyte0[])
            {
/* 298*/        usernameBasic = s;
/* 299*/        passwordBasic = abyte0;
/* 300*/        return this;
            }

            public lder credentialsForDigest(String s, String s1)
            {
/* 305*/        return credentialsForDigest(s, s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null);
            }

            public lder credentialsForDigest(String s, byte abyte0[])
            {
/* 311*/        usernameDigest = s;
/* 312*/        passwordDigest = abyte0;
/* 313*/        return this;
            }

            public HttpAuthenticationFeature build()
            {
/* 318*/        return new HttpAuthenticationFeature(mode, usernameBasic != null ? new init>(usernameBasic, passwordBasic) : null, usernameDigest != null ? new init>(usernameDigest, passwordDigest) : null);
            }

            public  nonPreemptive()
            {
/* 327*/        if(mode == REEMPTIVE)
/* 328*/            mode = ON_PREEMPTIVE;
/* 330*/        return this;
            }

            private String usernameBasic;
            private byte passwordBasic[];
            private String usernameDigest;
            private byte passwordDigest[];
            private ON_PREEMPTIVE mode;

            public ( )
            {
/* 275*/        mode = ;
            }
}
