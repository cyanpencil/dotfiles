// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFilter.java

package org.glassfish.jersey.client.authentication;


// Referenced classes of package org.glassfish.jersey.client.authentication:
//            HttpAuthenticationFilter

static final class  extends Enum
{

            public static [] values()
            {
/*  85*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/*  85*/        return (e_3B_.clone)Enum.valueOf(org/glassfish/jersey/client/authentication/HttpAuthenticationFilter$Type, s);
            }

            public static final DIGEST BASIC;
            public static final DIGEST DIGEST;
            private static final DIGEST $VALUES[];

            static 
            {
/*  89*/        BASIC = new <init>("BASIC", 0);
/*  93*/        DIGEST = new <init>("DIGEST", 1);
/*  85*/        $VALUES = (new .VALUES[] {
/*  85*/            BASIC, DIGEST
                });
            }

            private (String s, int i)
            {
/*  85*/        super(s, i);
            }
}
