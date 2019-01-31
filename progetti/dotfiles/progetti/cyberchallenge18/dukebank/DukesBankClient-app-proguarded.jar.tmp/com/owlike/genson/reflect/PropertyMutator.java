// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyMutator.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanProperty

public abstract class PropertyMutator extends BeanProperty
    implements Comparable
{
    public static class FieldMutator extends PropertyMutator
    {

                public void mutate(Object obj, Object obj1)
                {
/* 102*/            try
                    {
/* 102*/                _field.set(obj, obj1);
/* 107*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 103*/            catch(Object obj)
                    {
/* 104*/                throw couldNotMutate(((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/* 105*/            catch(Object obj)
                    {
/* 106*/                throw couldNotMutate(((Exception) (obj)));
                    }
                }

                public String signature()
                {
/* 112*/            return _field.toGenericString();
                }

                public int priority()
                {
/* 117*/            return 0;
                }

                public volatile int compareTo(Object obj)
                {
/*  88*/            return compareTo((PropertyMutator)obj);
                }

                protected final Field _field;

                public FieldMutator(String s, Field field, Type type, Class class1)
                {
/*  92*/            super(s, type, field.getDeclaringClass(), class1, field.getAnnotations(), field.getModifiers());
/*  93*/            _field = field;
/*  94*/            if(!_field.isAccessible())
/*  95*/                _field.setAccessible(true);
                }
    }

    public static class MethodMutator extends PropertyMutator
    {

                public void mutate(Object obj, Object obj1)
                {
/*  67*/            try
                    {
/*  67*/                _setter.invoke(obj, new Object[] {
/*  67*/                    obj1
                        });
/*  74*/                return;
                    }
                    // Misplaced declaration of an exception variable
/*  68*/            catch(Object obj)
                    {
/*  69*/                throw couldNotMutate(((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  70*/            catch(Object obj)
                    {
/*  71*/                throw couldNotMutate(((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  72*/            catch(Object obj)
                    {
/*  73*/                throw couldNotMutate(((Exception) (obj)));
                    }
                }

                public String signature()
                {
/*  79*/            return _setter.toGenericString();
                }

                public int priority()
                {
/*  84*/            return 100;
                }

                public volatile int compareTo(Object obj)
                {
/*  53*/            return compareTo((PropertyMutator)obj);
                }

                protected final Method _setter;

                public MethodMutator(String s, Method method, Type type, Class class1)
                {
/*  57*/            super(s, type, method.getDeclaringClass(), class1, method.getAnnotations(), method.getModifiers());
/*  58*/            _setter = method;
/*  59*/            if(!_setter.isAccessible())
/*  60*/                _setter.setAccessible(true);
                }
    }


            protected PropertyMutator(String s, Type type, Class class1, Class class2, Annotation aannotation[], int i)
            {
/*  17*/        super(s, type, class1, class2, aannotation, i);
            }

            public Object deserialize(ObjectReader objectreader, Context context)
            {
/*  22*/        return propertyDeserializer.deserialize(objectreader, context);
/*  23*/        objectreader;
/*  24*/        throw couldNotDeserialize(objectreader);
            }

            public void deserialize(Object obj, ObjectReader objectreader, Context context)
            {
/*  31*/        try
                {
/*  31*/            objectreader = ((ObjectReader) (propertyDeserializer.deserialize(objectreader, context)));
                }
                // Misplaced declaration of an exception variable
/*  32*/        catch(Object obj)
                {
/*  33*/            throw couldNotDeserialize(((Throwable) (obj)));
                }
/*  35*/        mutate(obj, objectreader);
            }

            public abstract void mutate(Object obj, Object obj1);

            public int compareTo(PropertyMutator propertymutator)
            {
/*  41*/        return propertymutator.priority() - priority();
            }

            protected JsonBindingException couldNotMutate(Exception exception)
            {
/*  45*/        return new JsonBindingException((new StringBuilder("Could not mutate value of property named '")).append(name).append("' using mutator ").append(signature()).toString(), exception);
            }

            protected JsonBindingException couldNotDeserialize(Throwable throwable)
            {
/*  50*/        return new JsonBindingException((new StringBuilder("Could not deserialize to property '")).append(name).append("' of class ").append(declaringClass).toString(), throwable);
            }

            public volatile int compareTo(Object obj)
            {
/*  12*/        return compareTo((PropertyMutator)obj);
            }

            Deserializer propertyDeserializer;
}
