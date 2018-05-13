// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyAccessor.java

package com.owlike.genson.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyAccessor

public static class _field extends PropertyAccessor
{

            public Object access(Object obj)
            {
/*  99*/        return _field.get(obj);
/* 100*/        obj;
/* 101*/        throw couldNotAccess(((Exception) (obj)));
/* 102*/        obj;
/* 103*/        throw couldNotAccess(((Exception) (obj)));
            }

            public String signature()
            {
/* 109*/        return _field.toGenericString();
            }

            public int priority()
            {
/* 114*/        return 50;
            }

            public volatile int compareTo(Object obj)
            {
/*  85*/        return super.compareTo((PropertyAccessor)obj);
            }

            protected final Field _field;

            public (String s, Field field, Type type, Class class1)
            {
/*  89*/        super(s, type, field.getDeclaringClass(), class1, field.getAnnotations(), field.getModifiers());
/*  90*/        _field = field;
/*  91*/        if(!_field.isAccessible())
/*  92*/            _field.setAccessible(true);
            }
}
