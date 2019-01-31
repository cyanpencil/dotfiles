// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstanceLifecycleEventType.java

package org.glassfish.hk2.api;


public final class InstanceLifecycleEventType extends Enum
{

            public static InstanceLifecycleEventType[] values()
            {
/*  49*/        return (InstanceLifecycleEventType[])$VALUES.clone();
            }

            public static InstanceLifecycleEventType valueOf(String s)
            {
/*  49*/        return (InstanceLifecycleEventType)Enum.valueOf(org/glassfish/hk2/api/InstanceLifecycleEventType, s);
            }

            private InstanceLifecycleEventType(String s, int i)
            {
/*  49*/        super(s, i);
            }

            public static final InstanceLifecycleEventType PRE_PRODUCTION;
            public static final InstanceLifecycleEventType POST_PRODUCTION;
            public static final InstanceLifecycleEventType PRE_DESTRUCTION;
            private static final InstanceLifecycleEventType $VALUES[];

            static 
            {
/*  54*/        PRE_PRODUCTION = new InstanceLifecycleEventType("PRE_PRODUCTION", 0);
/*  60*/        POST_PRODUCTION = new InstanceLifecycleEventType("POST_PRODUCTION", 1);
/*  66*/        PRE_DESTRUCTION = new InstanceLifecycleEventType("PRE_DESTRUCTION", 2);
/*  49*/        $VALUES = (new InstanceLifecycleEventType[] {
/*  49*/            PRE_PRODUCTION, POST_PRODUCTION, PRE_DESTRUCTION
                });
            }
}
