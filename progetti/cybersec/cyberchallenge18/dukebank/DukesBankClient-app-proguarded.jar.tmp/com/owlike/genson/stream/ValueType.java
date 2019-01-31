// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ValueType.java

package com.owlike.genson.stream;

import java.util.List;
import java.util.Map;

public final class ValueType extends Enum
{

            public static ValueType[] values()
            {
/*   6*/        return (ValueType[])$VALUES.clone();
            }

            public static ValueType valueOf(String s)
            {
/*   6*/        return (ValueType)Enum.valueOf(com/owlike/genson/stream/ValueType, s);
            }

            private ValueType(String s, int i, Class class1)
            {
/*  17*/        super(s, i);
/*  18*/        clazz = class1;
            }

            public final Class toClass()
            {
/*  22*/        return clazz;
            }

            public static final ValueType ARRAY;
            public static final ValueType OBJECT;
            public static final ValueType STRING;
            public static final ValueType INTEGER;
            public static final ValueType DOUBLE;
            public static final ValueType BOOLEAN;
            public static final ValueType NULL;
            private final Class clazz;
            private static final ValueType $VALUES[];

            static 
            {
/*   7*/        ARRAY = new ValueType("ARRAY", 0, java/util/List);
/*   8*/        OBJECT = new ValueType("OBJECT", 1, java/util/Map);
/*   9*/        STRING = new ValueType("STRING", 2, java/lang/String);
/*  10*/        INTEGER = new ValueType("INTEGER", 3, java/lang/Long);
/*  11*/        DOUBLE = new ValueType("DOUBLE", 4, java/lang/Double);
/*  12*/        BOOLEAN = new ValueType("BOOLEAN", 5, java/lang/Boolean);
/*  13*/        NULL = new ValueType("NULL", 6, null);
/*   6*/        $VALUES = (new ValueType[] {
/*   6*/            ARRAY, OBJECT, STRING, INTEGER, DOUBLE, BOOLEAN, NULL
                });
            }
}
