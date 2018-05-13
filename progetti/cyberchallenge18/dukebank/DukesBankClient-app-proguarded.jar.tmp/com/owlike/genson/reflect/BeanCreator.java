// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanCreator.java

package com.owlike.genson.reflect;

import com.owlike.genson.JsonBindingException;
import com.owlike.genson.Wrapper;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyMutator

public abstract class BeanCreator extends Wrapper
    implements Comparable
{
    public static class BeanCreatorProperty extends PropertyMutator
    {

                public int getIndex()
                {
/* 180*/            return index;
                }

                public Annotation[] getAnnotations()
                {
/* 184*/            return annotations;
                }

                public int priority()
                {
/* 189*/            return -1000;
                }

                public String signature()
                {
/* 194*/            return (new StringBuilder(type.toString())).append(' ').append(name).append(" from ").append(creator.signature()).toString();
                }

                public int getModifiers()
                {
/* 200*/            return creator.getModifiers();
                }

                public void mutate(Object obj, Object obj1)
                {
/* 205*/            if(doThrowMutateException)
/* 206*/                throw new IllegalStateException((new StringBuilder("Method mutate should not be called on a mutator of type ")).append(getClass().getName()).append(", this property exists only as constructor parameter!").toString());
/* 211*/            else
/* 211*/                return;
                }

                protected final int index;
                protected final Annotation annotations[];
                protected final BeanCreator creator;
                protected final boolean doThrowMutateException;

                protected BeanCreatorProperty(String s, Type type, int i, Annotation aannotation[], Class class1, Class class2, BeanCreator beancreator)
                {
/* 166*/            this(s, type, i, aannotation, class1, class2, beancreator, false);
                }

                protected BeanCreatorProperty(String s, Type type, int i, Annotation aannotation[], Class class1, Class class2, BeanCreator beancreator, 
                        boolean flag)
                {
/* 172*/            super(s, type, class1, class2, aannotation, 0);
/* 173*/            index = i;
/* 174*/            annotations = aannotation;
/* 175*/            creator = beancreator;
/* 176*/            doThrowMutateException = flag;
                }
    }

    public static class MethodBeanCreator extends BeanCreator
    {

                public transient Object create(Object aobj[])
                {
/* 132*/            return ofClass.cast(_creator.invoke(null, aobj));
/* 133*/            aobj;
/* 134*/            throw couldNotCreate(((Exception) (aobj)));
/* 135*/            aobj;
/* 136*/            throw couldNotCreate(((Exception) (aobj)));
/* 137*/            aobj;
/* 138*/            throw couldNotCreate(((Exception) (aobj)));
                }

                protected String signature()
                {
/* 144*/            return _creator.toGenericString();
                }

                public int priority()
                {
/* 149*/            return 100;
                }

                public int getModifiers()
                {
/* 154*/            return _creator.getModifiers();
                }

                public volatile int compareTo(Object obj)
                {
/* 113*/            return compareTo((BeanCreator)obj);
                }

                protected final Method _creator;

                public MethodBeanCreator(Method method, String as[], Type atype[], Class class1)
                {
/* 118*/            super(method.getReturnType(), method.getDeclaringClass(), class1, as, atype, method.getParameterAnnotations());
/* 120*/            if(!Modifier.isStatic(method.getModifiers()))
/* 121*/                throw new IllegalStateException("Only static methods can be used as creators!");
/* 122*/            _creator = method;
/* 123*/            if(!_creator.isAccessible())
/* 124*/                _creator.setAccessible(true);
/* 126*/            decorate(_creator);
                }
    }

    public static class ConstructorBeanCreator extends BeanCreator
    {

                public transient Object create(Object aobj[])
                {
/*  85*/            return constructor.newInstance(aobj);
/*  86*/            aobj;
/*  87*/            throw couldNotCreate(((Exception) (aobj)));
/*  88*/            aobj;
/*  89*/            throw couldNotCreate(((Exception) (aobj)));
/*  90*/            aobj;
/*  91*/            throw couldNotCreate(((Exception) (aobj)));
/*  92*/            aobj;
/*  93*/            throw couldNotCreate(((Exception) (aobj)));
                }

                protected String signature()
                {
/*  99*/            return constructor.toGenericString();
                }

                public int priority()
                {
/* 104*/            return 50;
                }

                public int getModifiers()
                {
/* 109*/            return constructor.getModifiers();
                }

                public volatile int compareTo(Object obj)
                {
/*  69*/            return compareTo((BeanCreator)obj);
                }

                protected final Constructor constructor;

                public ConstructorBeanCreator(Class class1, Constructor constructor1, String as[], Type atype[])
                {
/*  75*/            super(class1, class1, class1, as, atype, constructor1.getParameterAnnotations());
/*  76*/            constructor = constructor1;
/*  77*/            if(!constructor1.isAccessible())
/*  78*/                constructor1.setAccessible(true);
/*  80*/            decorate(constructor1);
                }
    }


            public BeanCreator(Class class1, Class class2, Class class3, String as[], Type atype[], Annotation aannotation[][])
            {
/*  26*/        ofClass = class1;
/*  27*/        parameters = new LinkedHashMap(as.length);
/*  28*/        for(class1 = 0; class1 < as.length; class1++)
/*  29*/            parameters.put(as[class1], new BeanCreatorProperty(as[class1], atype[class1], class1, aannotation[class1], class2, class3, this));

/*  34*/        for(class1 = parameters.values().iterator(); class1.hasNext();)
                {
/*  34*/            class2 = (BeanCreatorProperty)class1.next();
/*  35*/            paramsAndAliases.put(class2.getName(), class2);
/*  36*/            as = (class3 = class2.aliases()).length;
/*  36*/            atype = 0;
/*  36*/            while(atype < as) 
                    {
/*  36*/                aannotation = class3[atype];
/*  36*/                paramsAndAliases.put(aannotation, class2);
/*  36*/                atype++;
                    }
                }

            }

            public int contains(List list)
            {
/*  41*/        int i = 0;
/*  42*/        list = list.iterator();
/*  42*/        do
                {
/*  42*/            if(!list.hasNext())
/*  42*/                break;
/*  42*/            String s = (String)list.next();
/*  43*/            if(parameters.containsKey(s))
/*  43*/                i++;
                } while(true);
/*  44*/        return i;
            }

            public int compareTo(BeanCreator beancreator)
            {
                int i;
/*  48*/        if((i = beancreator.priority() - priority()) != 0)
/*  49*/            return i;
/*  49*/        else
/*  49*/            return parameters.size() - beancreator.parameters.size();
            }

            public transient abstract Object create(Object aobj[]);

            protected abstract String signature();

            public abstract int priority();

            protected JsonBindingException couldNotCreate(Exception exception)
            {
/*  59*/        return new JsonBindingException((new StringBuilder("Could not create bean of type ")).append(ofClass.getName()).append(" using creator ").append(signature()).toString(), exception);
            }

            public Map getProperties()
            {
/*  64*/        return new LinkedHashMap(parameters);
            }

            public abstract int getModifiers();

            public volatile int compareTo(Object obj)
            {
/*  18*/        return compareTo((BeanCreator)obj);
            }

            protected final Class ofClass;
            protected final Map parameters;
            protected final Map paramsAndAliases = new LinkedHashMap();
}
