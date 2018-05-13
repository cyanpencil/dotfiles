// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConstructorInterceptorHandler.java

package org.jvnet.hk2.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.util.*;
import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.ConstructorInvocation;
import org.glassfish.hk2.api.HK2Invocation;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

// Referenced classes of package org.jvnet.hk2.internal:
//            ConstructorAction

public class ConstructorInterceptorHandler
{
    static class ConstructorInvocationImpl
        implements ConstructorInvocation, HK2Invocation
    {

                public Object[] getArguments()
                {
/* 149*/            return args;
                }

                public AccessibleObject getStaticPart()
                {
/* 154*/            return c;
                }

                public Object getThis()
                {
/* 159*/            return myThis;
                }

                public Object proceed()
                    throws Throwable
                {
                    int i;
/* 164*/            if((i = index + 1) >= interceptors.size())
                    {
/* 166*/                myThis = finalAction.makeMe(c, args, neutralCCL);
/* 167*/                return myThis;
                    } else
                    {
/* 171*/                ConstructorInterceptor constructorinterceptor = (ConstructorInterceptor)interceptors.get(i);
/* 173*/                myThis = constructorinterceptor.construct(new ConstructorInvocationImpl(c, args, neutralCCL, finalAction, i, interceptors, userData));
/* 175*/                return myThis;
                    }
                }

                public Constructor getConstructor()
                {
/* 181*/            return c;
                }

                public void setUserData(String s, Object obj)
                {
/* 190*/            if(s == null)
/* 190*/                throw new IllegalArgumentException();
/* 192*/            if(userData == null)
/* 192*/                userData = new HashMap();
/* 194*/            if(obj == null)
                    {
/* 195*/                userData.remove(s);
/* 195*/                return;
                    } else
                    {
/* 198*/                userData.put(s, obj);
/* 201*/                return;
                    }
                }

                public Object getUserData(String s)
                {
/* 209*/            if(s == null)
/* 209*/                throw new IllegalArgumentException();
/* 211*/            if(userData == null)
/* 211*/                return null;
/* 212*/            else
/* 212*/                return userData.get(s);
                }

                private final Constructor c;
                private final Object args[];
                private final boolean neutralCCL;
                private Object myThis;
                private final int index;
                private final ConstructorAction finalAction;
                private final List interceptors;
                private HashMap userData;

                private ConstructorInvocationImpl(Constructor constructor, Object aobj[], boolean flag, ConstructorAction constructoraction, int i, List list, HashMap hashmap)
                {
/* 124*/            myThis = null;
/* 137*/            c = constructor;
/* 138*/            args = aobj;
/* 139*/            neutralCCL = flag;
/* 140*/            finalAction = constructoraction;
/* 141*/            index = i;
/* 142*/            interceptors = list;
/* 143*/            userData = hashmap;
                }

    }


            public ConstructorInterceptorHandler()
            {
            }

            public static Object construct(Constructor constructor, Object aobj[], boolean flag, List list, ConstructorAction constructoraction)
                throws Throwable
            {
/*  81*/        if(list == null || list.isEmpty())
/*  82*/            return constructoraction.makeMe(constructor, aobj, flag);
/*  85*/        if(!(list instanceof RandomAccess))
/*  86*/            list = new ArrayList(list);
                ConstructorInterceptor constructorinterceptor;
/*  89*/        if((aobj = ((Object []) ((constructorinterceptor = (ConstructorInterceptor)list.get(0)).construct(new ConstructorInvocationImpl(constructor, aobj, flag, constructoraction, 0, list, null))))) == null)
/* 100*/            throw new AssertionError((new StringBuilder("ConstructorInterceptor construct method returned null for ")).append(constructor).toString());
/* 103*/        else
/* 103*/            return ((Object) (aobj));
            }

            public static Object construct(Constructor constructor, Object aobj[], boolean flag, List list)
                throws Throwable
            {
/* 117*/        return construct(constructor, aobj, flag, list, DEFAULT_ACTION);
            }

            private static final ConstructorAction DEFAULT_ACTION = new ConstructorAction() {

                public final Object makeMe(Constructor constructor, Object aobj[], boolean flag)
                    throws Throwable
                {
/*  64*/            return ReflectionHelper.makeMe(constructor, aobj, flag);
                }

    };

}
