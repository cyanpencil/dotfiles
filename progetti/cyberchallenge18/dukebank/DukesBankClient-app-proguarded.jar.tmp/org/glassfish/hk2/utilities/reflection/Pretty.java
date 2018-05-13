// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Pretty.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.*;
import java.util.Collection;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ReflectionHelper

public class Pretty
{

            public Pretty()
            {
            }

            public static String clazz(Class class1)
            {
/*  64*/        if(class1 == null)
/*  64*/            return "null";
                int i;
/*  66*/        if((i = (class1 = class1.getName()).lastIndexOf(".")) < 0)
/*  69*/            return class1;
/*  72*/        else
/*  72*/            return class1.substring(i + 1);
            }

            public static String pType(ParameterizedType parameterizedtype)
            {
                StringBuffer stringbuffer;
/*  82*/        (stringbuffer = new StringBuffer()).append((new StringBuilder()).append(clazz(ReflectionHelper.getRawClass(parameterizedtype))).append("<").toString());
/*  86*/        boolean flag = true;
/*  87*/        int i = (parameterizedtype = parameterizedtype.getActualTypeArguments()).length;
/*  87*/        for(int j = 0; j < i; j++)
                {
/*  87*/            Type type1 = parameterizedtype[j];
/*  88*/            if(flag)
                    {
/*  89*/                flag = false;
/*  91*/                stringbuffer.append(type(type1));
                    } else
                    {
/*  94*/                stringbuffer.append((new StringBuilder(",")).append(type(type1)).toString());
                    }
                }

/*  98*/        stringbuffer.append(">");
/* 100*/        return stringbuffer.toString();
            }

            public static String type(Type type1)
            {
/* 109*/        if(type1 == null)
/* 109*/            return "null";
/* 111*/        if(type1 instanceof Class)
/* 112*/            return clazz((Class)type1);
/* 115*/        if(type1 instanceof ParameterizedType)
/* 116*/            return pType((ParameterizedType)type1);
/* 119*/        else
/* 119*/            return type1.toString();
            }

            public static String constructor(Constructor constructor1)
            {
/* 132*/        if(constructor1 == null)
/* 132*/            return "null";
/* 134*/        else
/* 134*/            return (new StringBuilder("<init>")).append(prettyPrintParameters(constructor1.getParameterTypes())).toString();
            }

            public static String method(Method method1)
            {
/* 145*/        if(method1 == null)
/* 145*/            return "null";
/* 147*/        else
/* 147*/            return (new StringBuilder()).append(method1.getName()).append(prettyPrintParameters(method1.getParameterTypes())).toString();
            }

            public static String field(Field field1)
            {
/* 156*/        if(field1 == null)
/* 156*/            return "null";
                Object obj;
/* 158*/        if((obj = field1.getGenericType()) instanceof Class)
/* 162*/            obj = clazz((Class)obj);
/* 165*/        else
/* 165*/            obj = type(((Type) (obj)));
/* 168*/        return (new StringBuilder("field(")).append(((String) (obj))).append(" ").append(field1.getName()).append(" in ").append(field1.getDeclaringClass().getName()).append(")").toString();
            }

            public static String array(Object aobj[])
            {
/* 177*/        if(aobj == null)
/* 177*/            return "null";
/* 178*/        StringBuffer stringbuffer = new StringBuffer("{");
/* 180*/        boolean flag = true;
/* 181*/        int i = (aobj = aobj).length;
/* 181*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 181*/            if((obj = aobj[j]) != null && (obj instanceof Class))
/* 183*/                obj = clazz((Class)obj);
/* 186*/            if(flag)
                    {
/* 187*/                flag = false;
/* 189*/                stringbuffer.append(obj != null ? obj.toString() : "null");
                    } else
                    {
/* 192*/                stringbuffer.append((new StringBuilder(",")).append(obj != null ? obj.toString() : "null").toString());
                    }
                }

/* 196*/        stringbuffer.append("}");
/* 198*/        return stringbuffer.toString();
            }

            public static String collection(Collection collection1)
            {
/* 207*/        if(collection1 == null)
/* 207*/            return "null";
/* 208*/        else
/* 208*/            return array(collection1.toArray(new Object[collection1.size()]));
            }

            private static String prettyPrintParameters(Class aclass[])
            {
/* 213*/        if(aclass == null)
/* 213*/            return "null";
/* 215*/        StringBuffer stringbuffer = new StringBuffer("(");
/* 217*/        boolean flag = true;
/* 218*/        int i = (aclass = aclass).length;
/* 218*/        for(int j = 0; j < i; j++)
                {
/* 218*/            Class class1 = aclass[j];
/* 219*/            if(flag)
                    {
/* 220*/                stringbuffer.append(clazz(class1));
/* 221*/                flag = false;
                    } else
                    {
/* 224*/                stringbuffer.append((new StringBuilder(",")).append(clazz(class1)).toString());
                    }
                }

/* 228*/        stringbuffer.append(")");
/* 230*/        return stringbuffer.toString();
            }

            private static final String DOT = ".";
            private static final String NULL_STRING = "null";
            private static final String CONSTRUCTOR_NAME = "<init>";
}
