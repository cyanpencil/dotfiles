// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyAccessor.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import com.owlike.genson.stream.JsonWriter;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanProperty

public abstract class PropertyAccessor extends BeanProperty
    implements Comparable
{
    public static class FieldAccessor extends PropertyAccessor
    {

                public Object access(Object obj)
                {
/*  99*/            return _field.get(obj);
/* 100*/            obj;
/* 101*/            throw couldNotAccess(((Exception) (obj)));
/* 102*/            obj;
/* 103*/            throw couldNotAccess(((Exception) (obj)));
                }

                public String signature()
                {
/* 109*/            return _field.toGenericString();
                }

                public int priority()
                {
/* 114*/            return 50;
                }

                public volatile int compareTo(Object obj)
                {
/*  85*/            return compareTo((PropertyAccessor)obj);
                }

                protected final Field _field;

                public FieldAccessor(String s, Field field, Type type, Class class1)
                {
/*  89*/            super(s, type, field.getDeclaringClass(), class1, field.getAnnotations(), field.getModifiers());
/*  90*/            _field = field;
/*  91*/            if(!_field.isAccessible())
/*  92*/                _field.setAccessible(true);
                }
    }

    public static class MethodAccessor extends PropertyAccessor
    {

                public Object access(Object obj)
                {
/*  64*/            return _getter.invoke(obj, new Object[0]);
/*  65*/            obj;
/*  66*/            throw couldNotAccess(((Exception) (obj)));
/*  67*/            obj;
/*  68*/            throw couldNotAccess(((Exception) (obj)));
/*  69*/            obj;
/*  70*/            throw couldNotAccess(((Exception) (obj)));
                }

                String signature()
                {
/*  76*/            return _getter.toGenericString();
                }

                int priority()
                {
/*  81*/            return 100;
                }

                public volatile int compareTo(Object obj)
                {
/*  50*/            return compareTo((PropertyAccessor)obj);
                }

                protected final Method _getter;

                public MethodAccessor(String s, Method method, Type type, Class class1)
                {
/*  54*/            super(s, type, method.getDeclaringClass(), class1, method.getAnnotations(), method.getModifiers());
/*  55*/            _getter = method;
/*  56*/            if(!_getter.isAccessible())
/*  57*/                _getter.setAccessible(true);
                }
    }


            protected PropertyAccessor(String s, Type type, Class class1, Class class2, Annotation aannotation[], int i)
            {
/*  19*/        super(s, type, class1, class2, aannotation, i);
/*  20*/        escapedName = JsonWriter.escapeString(s);
            }

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
            {
/*  24*/        obj = access(obj);
/*  25*/        objectwriter.writeEscapedName(escapedName);
/*  27*/        try
                {
/*  27*/            propertySerializer.serialize(obj, objectwriter, context);
/*  30*/            return;
                }
                // Misplaced declaration of an exception variable
/*  28*/        catch(Object obj)
                {
/*  29*/            throw couldNotSerialize(((Throwable) (obj)));
                }
            }

            public abstract Object access(Object obj);

            public int compareTo(PropertyAccessor propertyaccessor)
            {
/*  36*/        return propertyaccessor.priority() - priority();
            }

            protected JsonBindingException couldNotAccess(Exception exception)
            {
/*  40*/        return new JsonBindingException((new StringBuilder("Could not access value of property named '")).append(name).append("' using accessor ").append(signature()).append(" from class ").append(declaringClass.getName()).toString(), exception);
            }

            protected JsonBindingException couldNotSerialize(Throwable throwable)
            {
/*  46*/        return new JsonBindingException((new StringBuilder("Could not serialize property '")).append(name).append("' from class ").append(declaringClass.getName()).toString(), throwable);
            }

            public volatile int compareTo(Object obj)
            {
/*  13*/        return compareTo((PropertyAccessor)obj);
            }

            Serializer propertySerializer;
            private final char escapedName[];
}
