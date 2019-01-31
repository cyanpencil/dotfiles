// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFeature.java

package org.glassfish.jersey.client.authentication;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFilter

public class HttpAuthenticationFeature
    implements Feature
{
    static class BuilderImpl
        implements BasicBuilder, UniversalBuilder
    {

                public Builder credentials(String s, String s1)
                {
/* 280*/            return credentials(s, s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null);
                }

                public Builder credentials(String s, byte abyte0[])
                {
/* 285*/            credentialsForBasic(s, abyte0);
/* 286*/            credentialsForDigest(s, abyte0);
/* 287*/            return this;
                }

                public UniversalBuilder credentialsForBasic(String s, String s1)
                {
/* 292*/            return credentialsForBasic(s, s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null);
                }

                public UniversalBuilder credentialsForBasic(String s, byte abyte0[])
                {
/* 298*/            usernameBasic = s;
/* 299*/            passwordBasic = abyte0;
/* 300*/            return this;
                }

                public UniversalBuilder credentialsForDigest(String s, String s1)
                {
/* 305*/            return credentialsForDigest(s, s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null);
                }

                public UniversalBuilder credentialsForDigest(String s, byte abyte0[])
                {
/* 311*/            usernameDigest = s;
/* 312*/            passwordDigest = abyte0;
/* 313*/            return this;
                }

                public HttpAuthenticationFeature build()
                {
/* 318*/            return new HttpAuthenticationFeature(mode, usernameBasic != null ? new HttpAuthenticationFilter.Credentials(usernameBasic, passwordBasic) : null, usernameDigest != null ? new HttpAuthenticationFilter.Credentials(usernameDigest, passwordDigest) : null);
                }

                public BasicBuilder nonPreemptive()
                {
/* 327*/            if(mode == Mode.BASIC_PREEMPTIVE)
/* 328*/                mode = Mode.BASIC_NON_PREEMPTIVE;
/* 330*/            return this;
                }

                private String usernameBasic;
                private byte passwordBasic[];
                private String usernameDigest;
                private byte passwordDigest[];
                private Mode mode;

                public BuilderImpl(Mode mode1)
                {
/* 275*/            mode = mode1;
                }
    }

    public static interface UniversalBuilder
        extends Builder
    {

        public abstract UniversalBuilder credentialsForBasic(String s, String s1);

        public abstract UniversalBuilder credentialsForBasic(String s, byte abyte0[]);

        public abstract UniversalBuilder credentialsForDigest(String s, String s1);

        public abstract UniversalBuilder credentialsForDigest(String s, byte abyte0[]);
    }

    public static interface BasicBuilder
        extends Builder
    {

        public abstract BasicBuilder nonPreemptive();
    }

    public static interface Builder
    {

        public abstract Builder credentials(String s, byte abyte0[]);

        public abstract Builder credentials(String s, String s1);

        public abstract HttpAuthenticationFeature build();
    }

    static final class Mode extends Enum
    {

                public static Mode[] values()
                {
/* 150*/            return (Mode[])$VALUES.clone();
                }

                public static Mode valueOf(String s)
                {
/* 150*/            return (Mode)Enum.valueOf(org/glassfish/jersey/client/authentication/HttpAuthenticationFeature$Mode, s);
                }

                public static final Mode BASIC_PREEMPTIVE;
                public static final Mode BASIC_NON_PREEMPTIVE;
                public static final Mode DIGEST;
                public static final Mode UNIVERSAL;
                private static final Mode $VALUES[];

                static 
                {
/* 154*/            BASIC_PREEMPTIVE = new Mode("BASIC_PREEMPTIVE", 0);
/* 158*/            BASIC_NON_PREEMPTIVE = new Mode("BASIC_NON_PREEMPTIVE", 1);
/* 162*/            DIGEST = new Mode("DIGEST", 2);
/* 166*/            UNIVERSAL = new Mode("UNIVERSAL", 3);
/* 150*/            $VALUES = (new Mode[] {
/* 150*/                BASIC_PREEMPTIVE, BASIC_NON_PREEMPTIVE, DIGEST, UNIVERSAL
                    });
                }

                private Mode(String s, int i)
                {
/* 150*/            super(s, i);
                }
    }


            public static BasicBuilder basicBuilder()
            {
/* 482*/        return new BuilderImpl(Mode.BASIC_PREEMPTIVE);
            }

            public static HttpAuthenticationFeature basic(String s, byte abyte0[])
            {
/* 493*/        return build(Mode.BASIC_PREEMPTIVE, s, abyte0);
            }

            public static HttpAuthenticationFeature basic(String s, String s1)
            {
/* 504*/        return build(Mode.BASIC_PREEMPTIVE, s, s1);
            }

            public static HttpAuthenticationFeature digest()
            {
/* 514*/        return build(Mode.DIGEST);
            }

            public static HttpAuthenticationFeature digest(String s, byte abyte0[])
            {
/* 525*/        return build(Mode.DIGEST, s, abyte0);
            }

            public static HttpAuthenticationFeature digest(String s, String s1)
            {
/* 536*/        return build(Mode.DIGEST, s, s1);
            }

            public static UniversalBuilder universalBuilder()
            {
/* 546*/        return new BuilderImpl(Mode.UNIVERSAL);
            }

            public static HttpAuthenticationFeature universal(String s, byte abyte0[])
            {
/* 558*/        return build(Mode.UNIVERSAL, s, abyte0);
            }

            public static HttpAuthenticationFeature universal(String s, String s1)
            {
/* 570*/        return build(Mode.UNIVERSAL, s, s1);
            }

            private static HttpAuthenticationFeature build(Mode mode1)
            {
/* 574*/        return (new BuilderImpl(mode1)).build();
            }

            private static HttpAuthenticationFeature build(Mode mode1, String s, byte abyte0[])
            {
/* 578*/        return (new BuilderImpl(mode1)).credentials(s, abyte0).build();
            }

            private static HttpAuthenticationFeature build(Mode mode1, String s, String s1)
            {
/* 582*/        return (new BuilderImpl(mode1)).credentials(s, s1).build();
            }

            private HttpAuthenticationFeature(Mode mode1, HttpAuthenticationFilter.Credentials credentials, HttpAuthenticationFilter.Credentials credentials1)
            {
/* 591*/        mode = mode1;
/* 592*/        basicCredentials = credentials;
/* 594*/        digestCredentials = credentials1;
            }

            public boolean configure(FeatureContext featurecontext)
            {
/* 599*/        featurecontext.register(new HttpAuthenticationFilter(mode, basicCredentials, digestCredentials, featurecontext.getConfiguration()));
/* 600*/        return true;
            }


            public static final String HTTP_AUTHENTICATION_USERNAME = "jersey.config.client.http.auth.username";
            public static final String HTTP_AUTHENTICATION_PASSWORD = "jersey.config.client.http.auth.password";
            public static final String HTTP_AUTHENTICATION_BASIC_USERNAME = "jersey.config.client.http.auth.basic.username";
            public static final String HTTP_AUTHENTICATION_BASIC_PASSWORD = "jersey.config.client.http.auth.basic.password";
            public static final String HTTP_AUTHENTICATION_DIGEST_USERNAME = "jersey.config.client.http.auth.digest.username";
            public static final String HTTP_AUTHENTICATION_DIGEST_PASSWORD = "jersey.config.client.http.auth.digest.password";
            private final Mode mode;
            private final HttpAuthenticationFilter.Credentials basicCredentials;
            private final HttpAuthenticationFilter.Credentials digestCredentials;
}
