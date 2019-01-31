// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyFilter.java

package com.owlike.genson.reflect;

import com.owlike.genson.Trilean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanMutatorAccessorResolver, TypeUtil

public class PropertyFilter extends BeanMutatorAccessorResolver.PropertyBaseResolver
{

            public PropertyFilter(boolean flag, String s, Class class1, Class class2)
            {
/*  15*/        exclude = flag;
/*  16*/        field = s;
/*  17*/        declaringClass = class1;
/*  18*/        ofType = class2;
            }

            public Trilean isAccessor(Field field1, Class class1)
            {
/*  23*/        return filter(field1.getName(), class1, field1.getType(), exclude);
            }

            public Trilean isMutator(Field field1, Class class1)
            {
/*  28*/        return filter(field1.getName(), class1, field1.getType(), exclude);
            }

            public Trilean isAccessor(Method method, Class class1)
            {
/*  33*/        if((class1 = method.getName()).startsWith("is") && class1.length() > 2)
/*  35*/            return filter(class1.substring(2), method.getDeclaringClass(), method.getReturnType(), exclude);
/*  38*/        if(class1.length() > 3 && class1.startsWith("get"))
/*  40*/            return filter(class1.substring(3), method.getDeclaringClass(), method.getReturnType(), exclude);
/*  43*/        else
/*  43*/            return Trilean.UNKNOWN;
            }

            public Trilean isMutator(Method method, Class class1)
            {
/*  48*/        if((class1 = method.getName()).length() > 3 && method.getParameterTypes().length == 1 && class1.startsWith("set"))
/*  51*/            return filter(class1.substring(3), method.getDeclaringClass(), method.getParameterTypes()[0], exclude);
/*  54*/        else
/*  54*/            return Trilean.UNKNOWN;
            }

            private Trilean filter(String s, Class class1, Class class2, boolean flag)
            {
/*  59*/        if((field == null || s.equalsIgnoreCase(field)) && (declaringClass == null || declaringClass.isAssignableFrom(class1)) && (ofType == null || ofType.isAssignableFrom(TypeUtil.wrap(class2))))
                {
/*  62*/            if(flag)
/*  62*/                return Trilean.FALSE;
/*  62*/            else
/*  62*/                return Trilean.TRUE;
                } else
                {
/*  63*/            return Trilean.UNKNOWN;
                }
            }

            private final boolean exclude;
            private final String field;
            private final Class declaringClass;
            private final Class ofType;
}
