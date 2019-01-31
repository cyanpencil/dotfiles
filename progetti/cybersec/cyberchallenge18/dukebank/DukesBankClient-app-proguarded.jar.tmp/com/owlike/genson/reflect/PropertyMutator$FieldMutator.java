// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyMutator.java

package com.owlike.genson.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyMutator

public static class _field extends PropertyMutator
{

            public void mutate(Object obj, Object obj1)
            {
/* 102*/        try
                {
/* 102*/            _field.set(obj, obj1);
/* 107*/            return;
                }
                // Misplaced declaration of an exception variable
/* 103*/        catch(Object obj)
                {
/* 104*/            throw couldNotMutate(((Exception) (obj)));
                }
                // Misplaced declaration of an exception variable
/* 105*/        catch(Object obj)
                {
/* 106*/            throw couldNotMutate(((Exception) (obj)));
                }
            }

            public String signature()
            {
/* 112*/        return _field.toGenericString();
            }

            public int priority()
            {
/* 117*/        return 0;
            }

            public volatile int compareTo(Object obj)
            {
/*  88*/        return super.compareTo((PropertyMutator)obj);
            }

            protected final Field _field;

            public (String s, Field field, Type type, Class class1)
            {
/*  92*/        super(s, type, field.getDeclaringClass(), class1, field.getAnnotations(), field.getModifiers());
/*  93*/        _field = field;
/*  94*/        if(!_field.isAccessible())
/*  95*/            _field.setAccessible(true);
            }
}
