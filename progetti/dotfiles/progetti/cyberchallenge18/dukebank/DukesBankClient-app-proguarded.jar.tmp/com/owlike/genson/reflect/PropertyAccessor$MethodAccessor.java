// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyAccessor.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyAccessor

public static class _getter extends PropertyAccessor
{

            public Object access(Object obj)
            {
/*  64*/        return _getter.invoke(obj, new Object[0]);
/*  65*/        obj;
/*  66*/        throw couldNotAccess(((Exception) (obj)));
/*  67*/        obj;
/*  68*/        throw couldNotAccess(((Exception) (obj)));
/*  69*/        obj;
/*  70*/        throw couldNotAccess(((Exception) (obj)));
            }

            String signature()
            {
/*  76*/        return _getter.toGenericString();
            }

            int priority()
            {
/*  81*/        return 100;
            }

            public volatile int compareTo(Object obj)
            {
/*  50*/        return super.compareTo((PropertyAccessor)obj);
            }

            protected final Method _getter;

            public (String s, Method method, Type type, Class class1)
            {
/*  54*/        super(s, type, method.getDeclaringClass(), class1, method.getAnnotations(), method.getModifiers());
/*  55*/        _getter = method;
/*  56*/        if(!_getter.isAccessible())
/*  57*/            _getter.setAccessible(true);
            }
}
