// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RuntimeSupport.java

package javassist.util.proxy;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.lang.reflect.Method;

// Referenced classes of package javassist.util.proxy:
//            MethodHandler, Proxy, ProxyFactory, ProxyObject, 
//            SecurityActions, SerializedProxy

public class RuntimeSupport
{
    static class DefaultMethodHandler
        implements Serializable, MethodHandler
    {

                public Object invoke(Object obj, Method method, Method method1, Object aobj[])
                    throws Exception
                {
/*  38*/            return method1.invoke(obj, aobj);
                }

                DefaultMethodHandler()
                {
                }
    }


            public RuntimeSupport()
            {
            }

            public static void find2Methods(Class class1, String s, String s1, int i, String s2, Method amethod[])
            {
/*  53*/        amethod[i + 1] = s1 != null ? findMethod(class1, s1, s2) : null;
/*  55*/        amethod[i] = findSuperClassMethod(class1, s, s2);
            }

            /**
             * @deprecated Method find2Methods is deprecated
             */

            public static void find2Methods(Object obj, String s, String s1, int i, String s2, Method amethod[])
            {
/*  72*/        amethod[i + 1] = s1 != null ? findMethod(obj, s1, s2) : null;
/*  74*/        amethod[i] = findSuperMethod(obj, s, s2);
            }

            /**
             * @deprecated Method findMethod is deprecated
             */

            public static Method findMethod(Object obj, String s, String s1)
            {
                Method method;
/*  87*/        if((method = findMethod2(obj.getClass(), s, s1)) == null)
/*  89*/            error(obj.getClass(), s, s1);
/*  91*/        return method;
            }

            public static Method findMethod(Class class1, String s, String s1)
            {
                Method method;
/* 101*/        if((method = findMethod2(class1, s, s1)) == null)
/* 103*/            error(class1, s, s1);
/* 105*/        return method;
            }

            public static Method findSuperMethod(Object obj, String s, String s1)
            {
/* 116*/        return findSuperClassMethod(((Class) (obj = obj.getClass())), s, s1);
            }

            public static Method findSuperClassMethod(Class class1, String s, String s1)
            {
                Method method;
/* 127*/        if((method = findSuperMethod2(class1.getSuperclass(), s, s1)) == null)
/* 129*/            method = searchInterfaces(class1, s, s1);
/* 131*/        if(method == null)
/* 132*/            error(class1, s, s1);
/* 134*/        return method;
            }

            private static void error(Class class1, String s, String s1)
            {
/* 138*/        throw new RuntimeException((new StringBuilder("not found ")).append(s).append(":").append(s1).append(" in ").append(class1.getName()).toString());
            }

            private static Method findSuperMethod2(Class class1, String s, String s1)
            {
                Object obj;
/* 143*/        if((obj = findMethod2(class1, s, s1)) != null)
/* 145*/            return ((Method) (obj));
/* 147*/        if((obj = class1.getSuperclass()) != null && (obj = findSuperMethod2(((Class) (obj)), s, s1)) != null)
/* 151*/            return ((Method) (obj));
/* 154*/        else
/* 154*/            return searchInterfaces(class1, s, s1);
            }

            private static Method searchInterfaces(Class class1, String s, String s1)
            {
/* 158*/        Method method = null;
/* 159*/        class1 = class1.getInterfaces();
/* 160*/        for(int i = 0; i < class1.length; i++)
/* 161*/            if((method = findSuperMethod2(class1[i], s, s1)) != null)
/* 163*/                return method;

/* 166*/        return method;
            }

            private static Method findMethod2(Class class1, String s, String s1)
            {
/* 170*/        int i = (class1 = SecurityActions.getDeclaredMethods(class1)).length;
/* 172*/        for(int j = 0; j < i; j++)
/* 173*/            if(class1[j].getName().equals(s) && makeDescriptor(class1[j]).equals(s1))
/* 175*/                return class1[j];

/* 177*/        return null;
            }

            public static String makeDescriptor(Method method)
            {
                Class aclass[];
/* 184*/        return makeDescriptor(aclass = method.getParameterTypes(), method.getReturnType());
            }

            public static String makeDescriptor(Class aclass[], Class class1)
            {
                StringBuffer stringbuffer;
/* 195*/        (stringbuffer = new StringBuffer()).append('(');
/* 197*/        for(int i = 0; i < aclass.length; i++)
/* 198*/            makeDesc(stringbuffer, aclass[i]);

/* 200*/        stringbuffer.append(')');
/* 201*/        if(class1 != null)
/* 202*/            makeDesc(stringbuffer, class1);
/* 204*/        return stringbuffer.toString();
            }

            public static String makeDescriptor(String s, Class class1)
            {
/* 214*/        makeDesc(s = new StringBuffer(s), class1);
/* 216*/        return s.toString();
            }

            private static void makeDesc(StringBuffer stringbuffer, Class class1)
            {
/* 220*/        if(class1.isArray())
                {
/* 221*/            stringbuffer.append('[');
/* 222*/            makeDesc(stringbuffer, class1.getComponentType());
/* 222*/            return;
                }
/* 224*/        if(class1.isPrimitive())
                {
/* 225*/            if(class1 == Void.TYPE)
                    {
/* 226*/                stringbuffer.append('V');
/* 226*/                return;
                    }
/* 227*/            if(class1 == Integer.TYPE)
                    {
/* 228*/                stringbuffer.append('I');
/* 228*/                return;
                    }
/* 229*/            if(class1 == Byte.TYPE)
                    {
/* 230*/                stringbuffer.append('B');
/* 230*/                return;
                    }
/* 231*/            if(class1 == Long.TYPE)
                    {
/* 232*/                stringbuffer.append('J');
/* 232*/                return;
                    }
/* 233*/            if(class1 == Double.TYPE)
                    {
/* 234*/                stringbuffer.append('D');
/* 234*/                return;
                    }
/* 235*/            if(class1 == Float.TYPE)
                    {
/* 236*/                stringbuffer.append('F');
/* 236*/                return;
                    }
/* 237*/            if(class1 == Character.TYPE)
                    {
/* 238*/                stringbuffer.append('C');
/* 238*/                return;
                    }
/* 239*/            if(class1 == Short.TYPE)
                    {
/* 240*/                stringbuffer.append('S');
/* 240*/                return;
                    }
/* 241*/            if(class1 == Boolean.TYPE)
                    {
/* 242*/                stringbuffer.append('Z');
/* 242*/                return;
                    } else
                    {
/* 244*/                throw new RuntimeException((new StringBuilder("bad type: ")).append(class1.getName()).toString());
                    }
                } else
                {
/* 247*/            stringbuffer.append('L').append(class1.getName().replace('.', '/')).append(';');
/* 249*/            return;
                }
            }

            public static SerializedProxy makeSerializedProxy(Object obj)
                throws InvalidClassException
            {
/* 261*/        Class class1 = obj.getClass();
/* 263*/        MethodHandler methodhandler = null;
/* 264*/        if(obj instanceof ProxyObject)
/* 265*/            methodhandler = ((ProxyObject)obj).getHandler();
/* 266*/        else
/* 266*/        if(obj instanceof Proxy)
/* 267*/            methodhandler = ProxyFactory.getHandler((Proxy)obj);
/* 269*/        return new SerializedProxy(class1, ProxyFactory.getFilterSignature(class1), methodhandler);
            }

            public static MethodHandler default_interceptor = new DefaultMethodHandler();

}
