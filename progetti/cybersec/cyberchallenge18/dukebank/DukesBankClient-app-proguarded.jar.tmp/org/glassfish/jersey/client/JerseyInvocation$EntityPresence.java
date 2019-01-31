// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;


// Referenced classes of package org.glassfish.jersey.client:
//            JerseyInvocation

static final class A extends Enum
{

            public static A[] values()
            {
/* 111*/        return (A[])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/* 111*/        return (e_3B_.clone)Enum.valueOf(org/glassfish/jersey/client/JerseyInvocation$EntityPresence, s);
            }

            public static final OPTIONAL MUST_BE_NULL;
            public static final OPTIONAL MUST_BE_PRESENT;
            public static final OPTIONAL OPTIONAL;
            private static final OPTIONAL $VALUES[];

            static 
            {
/* 112*/        MUST_BE_NULL = new <init>("MUST_BE_NULL", 0);
/* 113*/        MUST_BE_PRESENT = new <init>("MUST_BE_PRESENT", 1);
/* 114*/        OPTIONAL = new <init>("OPTIONAL", 2);
/* 111*/        $VALUES = (new .VALUES[] {
/* 111*/            MUST_BE_NULL, MUST_BE_PRESENT, OPTIONAL
                });
            }

            private A(String s, int i)
            {
/* 111*/        super(s, i);
            }
}
