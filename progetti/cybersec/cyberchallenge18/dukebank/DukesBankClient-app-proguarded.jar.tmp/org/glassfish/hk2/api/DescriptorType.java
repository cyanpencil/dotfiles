// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DescriptorType.java

package org.glassfish.hk2.api;


public final class DescriptorType extends Enum
{

            public static DescriptorType[] values()
            {
/*  47*/        return (DescriptorType[])$VALUES.clone();
            }

            public static DescriptorType valueOf(String s)
            {
/*  47*/        return (DescriptorType)Enum.valueOf(org/glassfish/hk2/api/DescriptorType, s);
            }

            private DescriptorType(String s, int i)
            {
/*  47*/        super(s, i);
            }

            public static final DescriptorType CLASS;
            public static final DescriptorType PROVIDE_METHOD;
            private static final DescriptorType $VALUES[];

            static 
            {
/*  52*/        CLASS = new DescriptorType("CLASS", 0);
/*  60*/        PROVIDE_METHOD = new DescriptorType("PROVIDE_METHOD", 1);
/*  47*/        $VALUES = (new DescriptorType[] {
/*  47*/            CLASS, PROVIDE_METHOD
                });
            }
}
