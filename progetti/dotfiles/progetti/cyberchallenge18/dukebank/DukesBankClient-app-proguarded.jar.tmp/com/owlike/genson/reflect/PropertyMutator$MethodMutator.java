// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyMutator.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyMutator

public static class _setter extends PropertyMutator
{

            public void mutate(Object obj, Object obj1)
            {
/*  67*/        try
                {
/*  67*/            _setter.invoke(obj, new Object[] {
/*  67*/                obj1
                    });
/*  74*/            return;
                }
                // Misplaced declaration of an exception variable
/*  68*/        catch(Object obj)
                {
/*  69*/            throw couldNotMutate(((Exception) (obj)));
                }
                // Misplaced declaration of an exception variable
/*  70*/        catch(Object obj)
                {
/*  71*/            throw couldNotMutate(((Exception) (obj)));
                }
                // Misplaced declaration of an exception variable
/*  72*/        catch(Object obj)
                {
/*  73*/            throw couldNotMutate(((Exception) (obj)));
                }
            }

            public String signature()
            {
/*  79*/        return _setter.toGenericString();
            }

            public int priority()
            {
/*  84*/        return 100;
            }

            public volatile int compareTo(Object obj)
            {
/*  53*/        return super.compareTo((PropertyMutator)obj);
            }

            protected final Method _setter;

            public (String s, Method method, Type type, Class class1)
            {
/*  57*/        super(s, type, method.getDeclaringClass(), class1, method.getAnnotations(), method.getModifiers());
/*  58*/        _setter = method;
/*  59*/        if(!_setter.isAccessible())
/*  60*/            _setter.setAccessible(true);
            }
}
