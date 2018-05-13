// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassReflectionHelperUtilities.java

package org.glassfish.hk2.utilities.reflection.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.glassfish.hk2.utilities.reflection.Pretty;

// Referenced classes of package org.glassfish.hk2.utilities.reflection.internal:
//            MethodWrapperImpl

public class ClassReflectionHelperUtilities
{

            public ClassReflectionHelperUtilities()
            {
            }

            private static Set getObjectMethods()
            {
/*  68*/        return (Set)AccessController.doPrivileged(new PrivilegedAction() {

                    public final Set run()
                    {
/*  72*/                HashSet hashset = new HashSet();
                        Method amethod[];
/*  74*/                int i = (amethod = java/lang/Object.getDeclaredMethods()).length;
/*  74*/                for(int j = 0; j < i; j++)
                        {
/*  74*/                    Method method = amethod[j];
/*  75*/                    hashset.add(new MethodWrapperImpl(method));
                        }

/*  78*/                return hashset;
                    }

                    public final volatile Object run()
                    {
/*  68*/                return run();
                    }

        });
            }

            private static Set getObjectFields()
            {
/*  86*/        return (Set)AccessController.doPrivileged(new PrivilegedAction() {

                    public final Set run()
                    {
/*  90*/                HashSet hashset = new HashSet();
                        Field afield[];
/*  92*/                int i = (afield = java/lang/Object.getDeclaredFields()).length;
/*  92*/                for(int j = 0; j < i; j++)
                        {
/*  92*/                    Field field = afield[j];
/*  93*/                    hashset.add(field);
                        }

/*  96*/                return hashset;
                    }

                    public final volatile Object run()
                    {
/*  86*/                return run();
                    }

        });
            }

            private static Method[] secureGetDeclaredMethods(Class class1)
            {
/* 104*/        return (Method[])AccessController.doPrivileged(new PrivilegedAction(class1) {

                    public final Method[] run()
                    {
/* 108*/                return clazz.getDeclaredMethods();
                    }

                    public final volatile Object run()
                    {
/* 104*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 104*/                clazz = class1;
/* 104*/                super();
                    }
        });
            }

            private static Field[] secureGetDeclaredFields(Class class1)
            {
/* 115*/        return (Field[])AccessController.doPrivileged(new PrivilegedAction(class1) {

                    public final Field[] run()
                    {
/* 119*/                return clazz.getDeclaredFields();
                    }

                    public final volatile Object run()
                    {
/* 115*/                return run();
                    }

                    final Class val$clazz;

                    
                    {
/* 115*/                clazz = class1;
/* 115*/                super();
                    }
        });
            }

            private static Set getDeclaredMethodWrappers(Class class1)
            {
/* 133*/        class1 = secureGetDeclaredMethods(class1);
/* 135*/        HashSet hashset = new HashSet();
/* 136*/        int i = (class1 = class1).length;
/* 136*/        for(int j = 0; j < i; j++)
                {
/* 136*/            Method method = class1[j];
/* 137*/            hashset.add(new MethodWrapperImpl(method));
                }

/* 140*/        return hashset;
            }

            private static Set getDeclaredFieldWrappers(Class class1)
            {
/* 151*/        class1 = secureGetDeclaredFields(class1);
/* 153*/        HashSet hashset = new HashSet();
/* 154*/        int i = (class1 = class1).length;
/* 154*/        for(int j = 0; j < i; j++)
                {
/* 154*/            Object obj = class1[j];
/* 155*/            hashset.add(obj);
                }

/* 158*/        return hashset;
            }

            static Set getAllFieldWrappers(Class class1)
            {
/* 162*/        if(class1 == null)
/* 162*/            return Collections.emptySet();
/* 163*/        if(java/lang/Object.equals(class1))
/* 163*/            return OBJECT_FIELDS;
/* 164*/        if(class1.isInterface())
                {
/* 164*/            return Collections.emptySet();
                } else
                {
                    HashSet hashset;
/* 166*/            (hashset = new HashSet()).addAll(getDeclaredFieldWrappers(class1));
/* 169*/            hashset.addAll(getAllFieldWrappers(class1.getSuperclass()));
/* 171*/            return hashset;
                }
            }

            static Set getAllMethodWrappers(Class class1)
            {
/* 175*/        if(class1 == null)
/* 175*/            return Collections.emptySet();
/* 176*/        if(java/lang/Object.equals(class1))
/* 176*/            return OBJECT_METHODS;
/* 178*/        HashSet hashset = new HashSet();
/* 180*/        if(class1.isInterface())
                {
                    Object aobj[];
/* 181*/            int i = (aobj = class1.getDeclaredMethods()).length;
/* 181*/            for(int j = 0; j < i; j++)
                    {
/* 181*/                Object obj = aobj[j];
/* 182*/                obj = new MethodWrapperImpl(((Method) (obj)));
/* 184*/                hashset.add(obj);
                    }

/* 187*/            i = (aobj = class1.getInterfaces()).length;
/* 187*/            for(int k = 0; k < i; k++)
                    {
/* 187*/                Class class2 = aobj[k];
/* 188*/                hashset.addAll(getAllMethodWrappers(class2));
                    }

                } else
                {
/* 192*/            hashset.addAll(getDeclaredMethodWrappers(class1));
/* 193*/            hashset.addAll(getAllMethodWrappers(class1.getSuperclass()));
                }
/* 196*/        return hashset;
            }

            static boolean isPostConstruct(Method method)
            {
/* 200*/        if(method.isAnnotationPresent(javax/annotation/PostConstruct))
/* 201*/            if(method.getParameterTypes().length != 0)
/* 202*/                throw new IllegalArgumentException((new StringBuilder("The method ")).append(Pretty.method(method)).append(" annotated with @PostConstruct must not have any arguments").toString());
/* 205*/            else
/* 205*/                return true;
/* 208*/        if(method.getParameterTypes().length != 0)
/* 208*/            return false;
/* 209*/        else
/* 209*/            return "postConstruct".equals(method.getName());
            }

            static boolean isPreDestroy(Method method)
            {
/* 213*/        if(method.isAnnotationPresent(javax/annotation/PreDestroy))
/* 214*/            if(method.getParameterTypes().length != 0)
/* 215*/                throw new IllegalArgumentException((new StringBuilder("The method ")).append(Pretty.method(method)).append(" annotated with @PreDestroy must not have any arguments").toString());
/* 219*/            else
/* 219*/                return true;
/* 222*/        if(method.getParameterTypes().length != 0)
/* 222*/            return false;
/* 223*/        else
/* 223*/            return "preDestroy".equals(method.getName());
            }

            static final String CONVENTION_POST_CONSTRUCT = "postConstruct";
            static final String CONVENTION_PRE_DESTROY = "preDestroy";
            private static final Set OBJECT_METHODS = getObjectMethods();
            private static final Set OBJECT_FIELDS = getObjectFields();

}
