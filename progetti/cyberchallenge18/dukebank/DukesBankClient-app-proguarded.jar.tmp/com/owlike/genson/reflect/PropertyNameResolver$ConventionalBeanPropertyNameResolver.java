// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PropertyNameResolver.java

package com.owlike.genson.reflect;

import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyNameResolver

public static class Y
    implements PropertyNameResolver
{

            public String resolve(int i, Constructor constructor)
            {
/* 119*/        return null;
            }

            public String resolve(Field field)
            {
/* 123*/        return field.getName();
            }

            public String resolve(Method method)
            {
/* 127*/        method = method.getName();
/* 128*/        byte byte0 = -1;
/* 130*/        if(method.startsWith("get"))
/* 131*/            byte0 = 3;
/* 132*/        else
/* 132*/        if(method.startsWith("is"))
/* 133*/            byte0 = 2;
/* 134*/        else
/* 134*/        if(method.startsWith("set"))
/* 135*/            byte0 = 3;
/* 137*/        if(byte0 >= 0 && byte0 < method.length())
/* 138*/            return (new StringBuilder()).append(Character.toLowerCase(method.charAt(byte0))).append(method.substring(byte0 + 1)).toString();
/* 140*/        else
/* 140*/            return null;
            }

            public String resolve(int i, Method method)
            {
/* 144*/        return null;
            }

            public Y()
            {
            }
}
