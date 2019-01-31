// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanReflectionHelper.java

package org.glassfish.hk2.utilities.reflection;

import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.lang.reflect.Method;
import java.util.*;
import org.glassfish.hk2.utilities.general.GeneralUtilities;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            ClassReflectionHelper, MethodWrapper

public class BeanReflectionHelper
{

            public BeanReflectionHelper()
            {
            }

            public static String getBeanPropertyNameFromGetter(Method method)
            {
/*  70*/        return isAGetter(new MethodWrapper(method) {

                    public final Method getMethod()
                    {
/*  74*/                return method;
                    }

                    final Method val$method;

                    
                    {
/*  70*/                method = method1;
/*  70*/                super();
                    }
        });
            }

            private static String isAGetter(MethodWrapper methodwrapper)
            {
/*  88*/        String s = (methodwrapper = methodwrapper.getMethod()).getName();
/*  91*/        if(Void.TYPE.equals(methodwrapper.getReturnType()))
/*  93*/            return null;
                Class aclass[];
/*  96*/        if((aclass = methodwrapper.getParameterTypes()).length != 0)
/*  99*/            return null;
/* 102*/        if((methodwrapper.getModifiers() & 1) == 0)
/* 104*/            return null;
/* 108*/        if(s.startsWith("get") && s.length() > 3)
/* 109*/            methodwrapper = 3;
/* 111*/        else
/* 111*/        if(s.startsWith("is") && s.length() > 2)
/* 112*/            methodwrapper = 2;
/* 116*/        else
/* 116*/            return null;
/* 119*/        if(!Character.isUpperCase(s.charAt(methodwrapper)))
/* 121*/            return null;
/* 124*/        else
/* 124*/            return Introspector.decapitalize(methodwrapper = s.substring(methodwrapper));
            }

            private static Method findMethod(Method method, Class class1)
            {
                Class aclass[];
/* 130*/        method = method.getName();
/* 131*/        aclass = new Class[0];
/* 134*/        return class1.getMethod(method, aclass);
/* 136*/        JVM INSTR pop ;
/* 137*/        return null;
            }

            private static Object getValue(Object obj, Method method)
            {
/* 143*/        return method.invoke(obj, new Object[0]);
/* 145*/        JVM INSTR pop ;
/* 146*/        return null;
            }

            private static PropertyChangeEvent[] getMapChangeEvents(Map map, Map map1)
            {
/* 151*/        LinkedList linkedlist = new LinkedList();
/* 153*/        map = map.entrySet().iterator();
/* 153*/        do
                {
/* 153*/            if(!map.hasNext())
/* 153*/                break;
                    Object obj;
/* 153*/            String s = (String)((java.util.Map.Entry) (obj = (java.util.Map.Entry)map.next())).getKey();
/* 155*/            obj = ((java.util.Map.Entry) (obj)).getValue();
/* 156*/            Object obj1 = map1.get(s);
/* 158*/            if(!GeneralUtilities.safeEquals(obj, obj1))
/* 159*/                linkedlist.add(new PropertyChangeEvent(map1, s, obj, obj1));
                } while(true);
/* 166*/        return (PropertyChangeEvent[])linkedlist.toArray(new PropertyChangeEvent[linkedlist.size()]);
            }

            public static PropertyChangeEvent[] getChangeEvents(ClassReflectionHelper classreflectionhelper, Object obj, Object obj1)
            {
/* 180*/        if(obj instanceof Map)
/* 181*/            return getMapChangeEvents((Map)obj, (Map)obj1);
/* 184*/        LinkedList linkedlist = new LinkedList();
/* 186*/        classreflectionhelper = (classreflectionhelper = classreflectionhelper.getAllMethods(obj.getClass())).iterator();
/* 188*/        do
                {
/* 188*/            if(!classreflectionhelper.hasNext())
/* 188*/                break;
                    Object obj2;
                    String s;
                    Object obj3;
/* 188*/            if((s = isAGetter(((MethodWrapper) (obj2 = (MethodWrapper)classreflectionhelper.next())))) != null && (obj3 = findMethod(((Method) (obj2 = ((MethodWrapper) (obj2)).getMethod())), obj1.getClass())) != null)
                    {
/* 197*/                obj2 = getValue(obj, ((Method) (obj2)));
/* 198*/                obj3 = getValue(obj1, ((Method) (obj3)));
/* 200*/                if(!GeneralUtilities.safeEquals(obj2, obj3))
/* 203*/                    linkedlist.add(new PropertyChangeEvent(obj1, s, obj2, obj3));
                    }
                } while(true);
/* 209*/        return (PropertyChangeEvent[])linkedlist.toArray(new PropertyChangeEvent[linkedlist.size()]);
            }

            public static Map convertJavaBeanToBeanLikeMap(ClassReflectionHelper classreflectionhelper, Object obj)
            {
/* 221*/        HashMap hashmap = new HashMap();
/* 223*/        classreflectionhelper = (classreflectionhelper = classreflectionhelper.getAllMethods(obj.getClass())).iterator();
/* 225*/        do
                {
/* 225*/            if(!classreflectionhelper.hasNext())
/* 225*/                break;
                    Object obj1;
                    String s;
/* 225*/            if((s = isAGetter(((MethodWrapper) (obj1 = (MethodWrapper)classreflectionhelper.next())))) != null && !"class".equals(s))
                    {
/* 230*/                obj1 = ((MethodWrapper) (obj1)).getMethod();
/* 232*/                obj1 = getValue(obj, ((Method) (obj1)));
/* 234*/                hashmap.put(s, obj1);
                    }
                } while(true);
/* 237*/        return hashmap;
            }

            private static final String GET = "get";
            private static final String IS = "is";
}
