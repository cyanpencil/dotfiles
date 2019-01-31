// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Constants.java

package org.glassfish.hk2.utilities.reflection;

import java.util.HashMap;

public class Constants
{

            public Constants()
            {
            }

            public static final String SYSTEM_LOADER_NAME = "SystemLoader";
            public static final HashMap PRIMITIVE_MAP;

            static 
            {
/*  53*/        (PRIMITIVE_MAP = new HashMap()).put(Character.TYPE, java/lang/Character);
/*  57*/        PRIMITIVE_MAP.put(Byte.TYPE, java/lang/Byte);
/*  58*/        PRIMITIVE_MAP.put(Short.TYPE, java/lang/Short);
/*  59*/        PRIMITIVE_MAP.put(Integer.TYPE, java/lang/Integer);
/*  60*/        PRIMITIVE_MAP.put(Long.TYPE, java/lang/Long);
/*  61*/        PRIMITIVE_MAP.put(Float.TYPE, java/lang/Float);
/*  62*/        PRIMITIVE_MAP.put(Double.TYPE, java/lang/Double);
            }
}
