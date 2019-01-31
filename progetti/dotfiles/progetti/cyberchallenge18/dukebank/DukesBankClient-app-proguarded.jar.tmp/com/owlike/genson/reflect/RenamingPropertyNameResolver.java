// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RenamingPropertyNameResolver.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyNameResolver

public class RenamingPropertyNameResolver
    implements PropertyNameResolver
{

            public RenamingPropertyNameResolver(String s, Class class1, Class class2, String s1)
            {
/*  15*/        field = s;
/*  16*/        fromClass = class1;
/*  17*/        ofType = class2;
/*  18*/        toName = s1;
            }

            public String resolve(int i, Constructor constructor)
            {
/*  23*/        return null;
            }

            public String resolve(int i, Method method)
            {
/*  28*/        return null;
            }

            public String resolve(Field field1)
            {
/*  33*/        return tryToRename(field1.getName(), field1.getDeclaringClass(), field1.getType());
            }

            public String resolve(Method method)
            {
                String s;
/*  39*/        if((s = method.getName()).startsWith("is") && s.length() > 2)
/*  41*/            return tryToRename(s.substring(2), method.getDeclaringClass(), method.getReturnType());
/*  44*/        if(s.length() > 3)
                {
/*  45*/            if(s.startsWith("get"))
/*  46*/                return tryToRename(s.substring(3), method.getDeclaringClass(), method.getReturnType());
/*  48*/            if(s.startsWith("set") && method.getParameterTypes().length == 1)
/*  49*/                return tryToRename(s.substring(3), method.getDeclaringClass(), method.getParameterTypes()[0]);
                }
/*  52*/        return null;
            }

            private String tryToRename(String s, Class class1, Class class2)
            {
/*  57*/        if((field == null || s.equalsIgnoreCase(field)) && (fromClass == null || fromClass.isAssignableFrom(class1)) && (ofType == null || ofType.isAssignableFrom(class2)))
/*  60*/            return toName;
/*  61*/        else
/*  61*/            return null;
            }

            private final String field;
            private final Class fromClass;
            private final Class ofType;
            private final String toName;
}
