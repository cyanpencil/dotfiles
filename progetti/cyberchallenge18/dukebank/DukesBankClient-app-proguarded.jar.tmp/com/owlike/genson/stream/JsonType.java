// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonType.java

package com.owlike.genson.stream;


public final class JsonType extends Enum
{

            public static JsonType[] values()
            {
/*   3*/        return (JsonType[])$VALUES.clone();
            }

            public static JsonType valueOf(String s)
            {
/*   3*/        return (JsonType)Enum.valueOf(com/owlike/genson/stream/JsonType, s);
            }

            private JsonType(String s, int i)
            {
/*   3*/        super(s, i);
            }

            public static final JsonType EMPTY;
            public static final JsonType OBJECT;
            public static final JsonType ARRAY;
            public static final JsonType METADATA;
            private static final JsonType $VALUES[];

            static 
            {
/*   4*/        EMPTY = new JsonType("EMPTY", 0);
/*   5*/        OBJECT = new JsonType("OBJECT", 1);
/*   6*/        ARRAY = new JsonType("ARRAY", 2);
/*   7*/        METADATA = new JsonType("METADATA", 3);
/*   3*/        $VALUES = (new JsonType[] {
/*   3*/            EMPTY, OBJECT, ARRAY, METADATA
                });
            }
}
