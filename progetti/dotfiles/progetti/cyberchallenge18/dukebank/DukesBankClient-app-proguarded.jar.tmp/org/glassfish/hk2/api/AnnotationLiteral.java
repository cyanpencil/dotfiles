// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AnnotationLiteral.java

package org.glassfish.hk2.api;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;

public abstract class AnnotationLiteral
    implements Serializable, Annotation
{

            protected AnnotationLiteral()
            {
/*  89*/        Class class1 = getClass();
/*  91*/        boolean flag = false;
                Class aclass[];
/*  92*/        int i = (aclass = class1.getInterfaces()).length;
/*  92*/        int j = 0;
/*  92*/        do
                {
/*  92*/            if(j >= i)
/*  92*/                break;
                    Class class2;
/*  92*/            if((class2 = aclass[j]).isAnnotation())
                    {
/*  94*/                flag = true;
/*  95*/                break;
                    }
/*  92*/            j++;
                } while(true);
/* 100*/        if(!flag)
/* 101*/            throw new IllegalStateException((new StringBuilder("The subclass ")).append(class1.getName()).append(" of AnnotationLiteral must implement an Annotation").toString());
/* 103*/        else
/* 103*/            return;
            }

            private Method[] getMembers()
            {
/* 107*/        if(members == null)
                {
/* 108*/            members = (Method[])AccessController.doPrivileged(new PrivilegedAction() {

                        public Method[] run()
                        {
/* 112*/                    return annotationType().getDeclaredMethods();
                        }

                        public volatile Object run()
                        {
/* 108*/                    return run();
                        }

                        final AnnotationLiteral this$0;

                    
                    {
/* 108*/                this$0 = AnnotationLiteral.this;
/* 108*/                super();
                    }
            });
/* 117*/            if(members.length > 0 && !annotationType().isAssignableFrom(getClass()))
/* 118*/                throw new RuntimeException((new StringBuilder()).append(getClass()).append(" does not implement the annotation type with members ").append(annotationType().getName()).toString());
                }
/* 121*/        return members;
            }

            private static Class getAnnotationLiteralSubclass(Class class1)
            {
/* 126*/        do
                {
                    Class class2;
/* 126*/            if((class2 = class1.getSuperclass()).equals(org/glassfish/hk2/api/AnnotationLiteral))
/* 129*/                return class1;
/* 131*/            if(class2.equals(java/lang/Object))
/* 133*/                return null;
/* 137*/            class1 = class2;
                } while(true);
            }

            private static Class getTypeParameter(Class class1)
            {
/* 144*/        if(((class1 = class1.getGenericSuperclass()) instanceof ParameterizedType) && (class1 = (ParameterizedType)class1).getActualTypeArguments().length == 1)
/* 150*/            return (Class)class1.getActualTypeArguments()[0];
/* 154*/        else
/* 154*/            return null;
            }

            public Class annotationType()
            {
/* 159*/        if(annotationType == null)
                {
                    Class class1;
/* 161*/            if((class1 = getAnnotationLiteralSubclass(getClass())) == null)
/* 164*/                throw new RuntimeException((new StringBuilder()).append(getClass()).append("is not a subclass of AnnotationLiteral").toString());
/* 166*/            annotationType = getTypeParameter(class1);
/* 167*/            if(annotationType == null)
/* 169*/                throw new RuntimeException((new StringBuilder()).append(getClass()).append(" does not specify the type parameter T of AnnotationLiteral<T>").toString());
                }
/* 172*/        return annotationType;
            }

            public boolean equals(Object obj)
            {
/* 178*/        if(obj instanceof Annotation)
                {
/* 180*/            obj = (Annotation)obj;
/* 181*/            if(annotationType().equals(((Annotation) (obj)).annotationType()))
                    {
                        Method amethod[];
/* 183*/                int i = (amethod = getMembers()).length;
/* 183*/                for(int j = 0; j < i; j++)
                        {
                            Object obj1;
/* 183*/                    Object obj2 = invoke(((Method) (obj1 = amethod[j])), this);
/* 186*/                    obj1 = invoke(((Method) (obj1)), obj);
/* 187*/                    if((obj2 instanceof byte[]) && (obj1 instanceof byte[]))
                            {
/* 189*/                        if(!Arrays.equals((byte[])obj2, (byte[])obj1))
/* 189*/                            return false;
/* 191*/                        continue;
                            }
/* 191*/                    if((obj2 instanceof short[]) && (obj1 instanceof short[]))
                            {
/* 193*/                        if(!Arrays.equals((short[])obj2, (short[])obj1))
/* 193*/                            return false;
/* 195*/                        continue;
                            }
/* 195*/                    if((obj2 instanceof int[]) && (obj1 instanceof int[]))
                            {
/* 197*/                        if(!Arrays.equals((int[])obj2, (int[])obj1))
/* 197*/                            return false;
/* 199*/                        continue;
                            }
/* 199*/                    if((obj2 instanceof long[]) && (obj1 instanceof long[]))
                            {
/* 201*/                        if(!Arrays.equals((long[])obj2, (long[])obj1))
/* 201*/                            return false;
/* 203*/                        continue;
                            }
/* 203*/                    if((obj2 instanceof float[]) && (obj1 instanceof float[]))
                            {
/* 205*/                        if(!Arrays.equals((float[])obj2, (float[])obj1))
/* 205*/                            return false;
/* 207*/                        continue;
                            }
/* 207*/                    if((obj2 instanceof double[]) && (obj1 instanceof double[]))
                            {
/* 209*/                        if(!Arrays.equals((double[])obj2, (double[])obj1))
/* 209*/                            return false;
/* 211*/                        continue;
                            }
/* 211*/                    if((obj2 instanceof char[]) && (obj1 instanceof char[]))
                            {
/* 213*/                        if(!Arrays.equals((char[])obj2, (char[])obj1))
/* 213*/                            return false;
/* 215*/                        continue;
                            }
/* 215*/                    if((obj2 instanceof boolean[]) && (obj1 instanceof boolean[]))
                            {
/* 217*/                        if(!Arrays.equals((boolean[])obj2, (boolean[])obj1))
/* 217*/                            return false;
/* 219*/                        continue;
                            }
/* 219*/                    if((obj2 instanceof Object[]) && (obj1 instanceof Object[]))
                            {
/* 221*/                        if(!Arrays.equals((Object[])obj2, (Object[])obj1))
/* 221*/                            return false;
/* 225*/                        continue;
                            }
/* 225*/                    if(!obj2.equals(obj1))
/* 225*/                        return false;
                        }

/* 228*/                return true;
                    }
                }
/* 231*/        return false;
            }

            public int hashCode()
            {
/* 238*/        int i = 0;
                Method amethod[];
/* 239*/        int j = (amethod = getMembers()).length;
/* 239*/        for(int k = 0; k < j; k++)
                {
/* 239*/            Method method = amethod[k];
/* 241*/            int i1 = 127 * method.getName().hashCode();
                    int l;
/* 242*/            if((l = ((int) (invoke(method, this)))) instanceof boolean[])
/* 246*/                l = Arrays.hashCode((boolean[])l);
/* 248*/            else
/* 248*/            if(l instanceof short[])
/* 250*/                l = Arrays.hashCode((short[])l);
/* 252*/            else
/* 252*/            if(l instanceof int[])
/* 254*/                l = Arrays.hashCode((int[])l);
/* 256*/            else
/* 256*/            if(l instanceof long[])
/* 258*/                l = Arrays.hashCode((long[])l);
/* 260*/            else
/* 260*/            if(l instanceof float[])
/* 262*/                l = Arrays.hashCode((float[])l);
/* 264*/            else
/* 264*/            if(l instanceof double[])
/* 266*/                l = Arrays.hashCode((double[])l);
/* 268*/            else
/* 268*/            if(l instanceof byte[])
/* 270*/                l = Arrays.hashCode((byte[])l);
/* 272*/            else
/* 272*/            if(l instanceof char[])
/* 274*/                l = Arrays.hashCode((char[])l);
/* 276*/            else
/* 276*/            if(l instanceof Object[])
/* 278*/                l = Arrays.hashCode((Object[])l);
/* 282*/            else
/* 282*/                l = l.hashCode();
/* 284*/            i += i1 ^ l;
                }

/* 286*/        return i;
            }

            private static void setAccessible(AccessibleObject accessibleobject)
            {
/* 290*/        AccessController.doPrivileged(new PrivilegedAction(accessibleobject) {

                    public final Object run()
                    {
/* 294*/                ao.setAccessible(true);
/* 295*/                return null;
                    }

                    final AccessibleObject val$ao;

                    
                    {
/* 290*/                ao = accessibleobject;
/* 290*/                super();
                    }
        });
            }

            private static Object invoke(Method method, Object obj)
            {
/* 306*/        if(!method.isAccessible())
/* 307*/            setAccessible(method);
/* 309*/        return method.invoke(obj, new Object[0]);
/* 311*/        obj;
/* 313*/        throw new RuntimeException((new StringBuilder("Error checking value of member method ")).append(method.getName()).append(" on ").append(method.getDeclaringClass()).toString(), ((Throwable) (obj)));
/* 315*/        obj;
/* 317*/        throw new RuntimeException((new StringBuilder("Error checking value of member method ")).append(method.getName()).append(" on ").append(method.getDeclaringClass()).toString(), ((Throwable) (obj)));
/* 319*/        obj;
/* 321*/        throw new RuntimeException((new StringBuilder("Error checking value of member method ")).append(method.getName()).append(" on ").append(method.getDeclaringClass()).toString(), ((Throwable) (obj)));
            }

            private static final long serialVersionUID = 0xcd68d4082abfcd58L;
            private transient Class annotationType;
            private transient Method members[];
}
