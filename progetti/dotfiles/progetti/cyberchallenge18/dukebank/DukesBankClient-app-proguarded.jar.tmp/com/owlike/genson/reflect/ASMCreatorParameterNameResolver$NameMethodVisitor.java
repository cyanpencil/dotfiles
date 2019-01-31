// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASMCreatorParameterNameResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.JsonBindingException;
import com.owlike.genson.internal.asm.Type;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.owlike.genson.reflect:
//            ASMCreatorParameterNameResolver

class name extends name
{

            public void visitEnd()
            {
/* 239*/        if(paramNames.size() == paramTypes.length)
                {
/* 241*/            Class aclass[] = new Class[paramTypes.length];
/* 243*/            for(int i = 0; i < paramTypes.length; i++)
/* 244*/                aclass[i] = resolveClass(paramTypes[i]);

/* 247*/            try
                    {
/* 247*/                java.lang.reflect.Method method = forClass.getMethod(name, aclass);
/* 248*/                parameterNamesMap.put(method, ((Object) (paramNames.toArray(new String[paramNames.size()]))));
/* 255*/                return;
                    }
/* 250*/            catch(SecurityException securityexception)
                    {
/* 251*/                throw new JsonBindingException((new StringBuilder("Unable to locate method with signature ")).append(signature()).toString(), securityexception);
                    }
/* 253*/            catch(NoSuchMethodException _ex) { }
                }
            }

            public String signature()
            {
/* 261*/        StringBuilder stringbuilder = (new StringBuilder(name)).append("(");
/* 262*/        for(int i = 0; i < paramTypes.length; i++)
                {
/* 263*/            String s = paramNames.isEmpty() ? "?" : (String)paramNames.get(i);
/* 264*/            stringbuilder.append(paramTypes[i].getClassName()).append((new StringBuilder(" ")).append(s).toString());
/* 265*/            if(i < paramTypes.length - 1)
/* 266*/                stringbuilder.append(", ");
                }

/* 269*/        stringbuilder.append(")");
/* 271*/        return stringbuilder.toString();
            }

            private final Map parameterNamesMap;
            private String name;
            final ASMCreatorParameterNameResolver this$0;

            public Q(String s, Class class1, boolean flag, String s1, Map map)
            {
/* 232*/        this$0 = ASMCreatorParameterNameResolver.this;
/* 233*/        super(ASMCreatorParameterNameResolver.this, class1, flag, s1, map);
/* 234*/        parameterNamesMap = map;
/* 235*/        name = s;
            }
}
