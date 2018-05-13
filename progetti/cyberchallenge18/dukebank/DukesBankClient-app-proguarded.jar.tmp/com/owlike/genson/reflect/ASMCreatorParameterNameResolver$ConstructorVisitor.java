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

class parameterNamesMap extends parameterNamesMap
{

            public void visitEnd()
            {
/* 285*/        if(paramNames.size() == paramTypes.length)
                {
/* 287*/            Class aclass[] = new Class[paramTypes.length];
/* 289*/            for(int i = 0; i < paramTypes.length; i++)
/* 290*/                aclass[i] = resolveClass(paramTypes[i]);

/* 293*/            try
                    {
/* 293*/                java.lang.reflect.Constructor constructor = forClass.getDeclaredConstructor(aclass);
/* 294*/                parameterNamesMap.put(constructor, ((Object) (paramNames.toArray(new String[paramNames.size()]))));
/* 301*/                return;
                    }
/* 296*/            catch(SecurityException securityexception)
                    {
/* 297*/                throw new JsonBindingException((new StringBuilder("Unable to locate constructor with signature ")).append(signature()).toString(), securityexception);
                    }
/* 299*/            catch(NoSuchMethodException _ex) { }
                }
            }

            public String signature()
            {
/* 306*/        StringBuilder stringbuilder = (new StringBuilder(forClass.getSimpleName())).append("(");
/* 307*/        for(int i = 0; i < paramTypes.length; i++)
                {
/* 308*/            String s = paramNames.isEmpty() ? "?" : (String)paramNames.get(i);
/* 309*/            stringbuilder.append(paramTypes[i].getClassName()).append((new StringBuilder(" ")).append(s).toString());
/* 310*/            if(i < paramTypes.length - 1)
/* 311*/                stringbuilder.append(", ");
                }

/* 314*/        stringbuilder.append(")");
/* 316*/        return stringbuilder.toString();
            }

            private final Map parameterNamesMap;
            final ASMCreatorParameterNameResolver this$0;

            public (Class class1, boolean flag, String s, Map map)
            {
/* 279*/        this$0 = ASMCreatorParameterNameResolver.this;
/* 280*/        super(ASMCreatorParameterNameResolver.this, class1, flag, s, ASMCreatorParameterNameResolver.access$000(ASMCreatorParameterNameResolver.this));
/* 281*/        parameterNamesMap = map;
            }
}
