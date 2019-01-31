// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import com.owlike.genson.annotation.JsonCreator;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanMutatorAccessorResolver, BeanViewDescriptorProvider, TypeUtil

public static class 
    implements BeanMutatorAccessorResolver
{

            public Trilean isAccessor(Field field, Class class1)
            {
/* 173*/        return Trilean.FALSE;
            }

            public Trilean isAccessor(Method method, Class class1)
            {
                Type type;
/* 177*/        type = TypeUtil.expandType(type = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, class1), class1);
/* 179*/        type = TypeUtil.typeOf(0, type);
/* 180*/        class1 = method.getModifiers();
/* 181*/        return Trilean.valueOf((method.getName().startsWith("get") || method.getName().startsWith("is") && (TypeUtil.match(method.getGenericReturnType(), java/lang/Boolean, false) || Boolean.TYPE.equals(method.getReturnType()))) && TypeUtil.match(type, method.getGenericParameterTypes()[0], false) && Modifier.isPublic(class1) && !Modifier.isAbstract(class1) && !Modifier.isNative(class1));
            }

            public Trilean isCreator(Constructor constructor, Class class1)
            {
/* 191*/        return Trilean.valueOf(Modifier.isPublic(constructor = constructor.getModifiers()) || !Modifier.isPrivate(constructor) && !Modifier.isProtected(constructor));
            }

            public Trilean isCreator(Method method, Class class1)
            {
/* 197*/        if(method.getAnnotation(com/owlike/genson/annotation/JsonCreator) != null)
                {
/* 198*/            if(Modifier.isStatic(method.getModifiers()))
/* 198*/                return Trilean.TRUE;
/* 199*/            else
/* 199*/                throw new JsonBindingException((new StringBuilder("Method ")).append(method.toGenericString()).append(" annotated with @Creator must be static!").toString());
                } else
                {
/* 202*/            return Trilean.FALSE;
                }
            }

            public Trilean isMutator(Field field, Class class1)
            {
/* 206*/        return Trilean.FALSE;
            }

            public Trilean isMutator(Method method, Class class1)
            {
                Type type;
/* 210*/        type = TypeUtil.expandType(type = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, class1), class1);
/* 212*/        type = TypeUtil.typeOf(0, type);
/* 213*/        class1 = method.getModifiers();
/* 214*/        return Trilean.valueOf(method.getName().startsWith("set") && Void.TYPE.equals(method.getReturnType()) && method.getGenericParameterTypes().length == 2 && TypeUtil.match(type, method.getGenericParameterTypes()[1], false) && Modifier.isPublic(class1) && !Modifier.isAbstract(class1) && !Modifier.isNative(class1));
            }

            public ()
            {
            }
}
