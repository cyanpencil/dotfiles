// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmediateController.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            ImmediateController

public static final class  extends Enum
{

            public static [] values()
            {
/* 111*/        return ([])$VALUES.clone();
            }

            public static e_3B_.clone valueOf(String s)
            {
/* 111*/        return (e_3B_.clone)Enum.valueOf(org/glassfish/hk2/api/ImmediateController$ImmediateServiceState, s);
            }

            public static final RUNNING SUSPENDED;
            public static final RUNNING RUNNING;
            private static final RUNNING $VALUES[];

            static 
            {
/* 117*/        SUSPENDED = new <init>("SUSPENDED", 0);
/* 122*/        RUNNING = new <init>("RUNNING", 1);
/* 111*/        $VALUES = (new .VALUES[] {
/* 111*/            SUSPENDED, RUNNING
                });
            }

            private (String s, int i)
            {
/* 111*/        super(s, i);
            }
}
