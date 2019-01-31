// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicTypesMessageProvider.java

package org.glassfish.jersey.message.internal;

import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            BasicTypesMessageProvider, MessageBodyProcessingException

static abstract class primitive extends Enum
{

            public static primitive[] values()
            {
/*  80*/        return (primitive[])$VALUES.clone();
            }

            public static s_3B_.clone valueOf(String s)
            {
/*  80*/        return (s_3B_.clone)Enum.valueOf(org/glassfish/jersey/message/internal/BasicTypesMessageProvider$PrimitiveTypes, s);
            }

            public static s_3B_.clone forType(Class class1)
            {
                s_3B_.clone aclone[];
/* 135*/        int i = (aclone = values()).length;
/* 135*/        for(int j = 0; j < i; j++)
                {
                    s_3B_.clone clone;
/* 135*/            if((clone = aclone[j]).supports(class1))
/* 137*/                return clone;
                }

/* 140*/        return null;
            }

            public abstract Object convert(String s);

            public boolean supports(Class class1)
            {
/* 154*/        return class1 == wrapper || class1 == primitive;
            }

            public static final CHAR BYTE;
            public static final CHAR SHORT;
            public static final CHAR INTEGER;
            public static final CHAR LONG;
            public static final CHAR FLOAT;
            public static final CHAR DOUBLE;
            public static final CHAR BOOLEAN;
            public static final CHAR CHAR;
            private final Class wrapper;
            private final Class primitive;
            private static final CHAR $VALUES[];

            static 
            {
/*  81*/        BYTE = new BasicTypesMessageProvider.PrimitiveTypes("BYTE", 0, java/lang/Byte, Byte.TYPE) {

                    public final Object convert(String s)
                    {
/*  84*/                return Byte.valueOf(s);
                    }

        };
/*  87*/        SHORT = new BasicTypesMessageProvider.PrimitiveTypes("SHORT", 1, java/lang/Short, Short.TYPE) {

                    public final Object convert(String s)
                    {
/*  90*/                return Short.valueOf(s);
                    }

        };
/*  93*/        INTEGER = new BasicTypesMessageProvider.PrimitiveTypes("INTEGER", 2, java/lang/Integer, Integer.TYPE) {

                    public final Object convert(String s)
                    {
/*  96*/                return Integer.valueOf(s);
                    }

        };
/*  99*/        LONG = new BasicTypesMessageProvider.PrimitiveTypes("LONG", 3, java/lang/Long, Long.TYPE) {

                    public final Object convert(String s)
                    {
/* 102*/                return Long.valueOf(s);
                    }

        };
/* 105*/        FLOAT = new BasicTypesMessageProvider.PrimitiveTypes("FLOAT", 4, java/lang/Float, Float.TYPE) {

                    public final Object convert(String s)
                    {
/* 108*/                return Float.valueOf(s);
                    }

        };
/* 111*/        DOUBLE = new BasicTypesMessageProvider.PrimitiveTypes("DOUBLE", 5, java/lang/Double, Double.TYPE) {

                    public final Object convert(String s)
                    {
/* 114*/                return Double.valueOf(s);
                    }

        };
/* 117*/        BOOLEAN = new BasicTypesMessageProvider.PrimitiveTypes("BOOLEAN", 6, java/lang/Boolean, Boolean.TYPE) {

                    public final Object convert(String s)
                    {
/* 120*/                return Boolean.valueOf(s);
                    }

        };
/* 123*/        CHAR = new BasicTypesMessageProvider.PrimitiveTypes("CHAR", 7, java/lang/Character, Character.TYPE) {

                    public final Object convert(String s)
                    {
/* 126*/                if(s.length() != 1)
/* 127*/                    throw new MessageBodyProcessingException(LocalizationMessages.ERROR_ENTITY_PROVIDER_BASICTYPES_CHARACTER_MORECHARS());
/* 130*/                else
/* 130*/                    return Character.valueOf(s.charAt(0));
                    }

        };
/*  80*/        $VALUES = (new .VALUES[] {
/*  80*/            BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR
                });
            }

            private _cls1(String s, int i, Class class1, Class class2)
            {
/* 146*/        super(s, i);
/* 147*/        wrapper = class1;
/* 148*/        primitive = class2;
            }

}
