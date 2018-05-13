// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorVisibility.java

package org.glassfish.hk2.api;


public final class DescriptorVisibility extends Enum
{

            public static DescriptorVisibility[] values()
            {
/*  48*/        return (DescriptorVisibility[])$VALUES.clone();
            }

            public static DescriptorVisibility valueOf(String s)
            {
/*  48*/        return (DescriptorVisibility)Enum.valueOf(org/glassfish/hk2/api/DescriptorVisibility, s);
            }

            private DescriptorVisibility(String s, int i)
            {
/*  48*/        super(s, i);
            }

            public static final DescriptorVisibility NORMAL;
            public static final DescriptorVisibility LOCAL;
            private static final DescriptorVisibility $VALUES[];

            static 
            {
/*  53*/        NORMAL = new DescriptorVisibility("NORMAL", 0);
/*  60*/        LOCAL = new DescriptorVisibility("LOCAL", 1);
/*  48*/        $VALUES = (new DescriptorVisibility[] {
/*  48*/            NORMAL, LOCAL
                });
            }
}
