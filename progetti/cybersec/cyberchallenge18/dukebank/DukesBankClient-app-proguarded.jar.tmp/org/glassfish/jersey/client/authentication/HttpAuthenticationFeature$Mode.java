// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFeature.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFeature

static final class  extends Enum
{

            public static [] values()
            {
/* 150*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/* 150*/        return (e_3B_.clone)Enum.valueOf(org/glassfish/jersey/client/authentication/HttpAuthenticationFeature$Mode, s);
            }

            public static final UNIVERSAL BASIC_PREEMPTIVE;
            public static final UNIVERSAL BASIC_NON_PREEMPTIVE;
            public static final UNIVERSAL DIGEST;
            public static final UNIVERSAL UNIVERSAL;
            private static final UNIVERSAL $VALUES[];

            static 
            {
/* 154*/        BASIC_PREEMPTIVE = new <init>("BASIC_PREEMPTIVE", 0);
/* 158*/        BASIC_NON_PREEMPTIVE = new <init>("BASIC_NON_PREEMPTIVE", 1);
/* 162*/        DIGEST = new <init>("DIGEST", 2);
/* 166*/        UNIVERSAL = new <init>("UNIVERSAL", 3);
/* 150*/        $VALUES = (new .VALUES[] {
/* 150*/            BASIC_PREEMPTIVE, BASIC_NON_PREEMPTIVE, DIGEST, UNIVERSAL
                });
            }

            private (String s, int i)
            {
/* 150*/        super(s, i);
            }
}
