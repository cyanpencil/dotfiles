// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanMutatorAccessorResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.Trilean;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanMutatorAccessorResolver, TypeUtil, VisibilityFilter

public static class creatorVisibilityFilter
    implements BeanMutatorAccessorResolver
{

            public Trilean isAccessor(Field field, Class class1)
            {
/* 291*/        return Trilean.valueOf(fieldVisibilityFilter.isVisible(field));
            }

            public Trilean isAccessor(Method method, Class class1)
            {
/* 299*/        if(!method.isBridge())
                {
                    String s;
/* 300*/            int i = (s = method.getName()).length();
/* 302*/            if(methodVisibilityFilter.isVisible(method) && (i > 3 && s.startsWith("get") || i > 2 && s.startsWith("is") && (TypeUtil.match(TypeUtil.expandType(method.getGenericReturnType(), class1), java/lang/Boolean, false) || TypeUtil.match(method.getGenericReturnType(), Boolean.TYPE, false))) && method.getParameterTypes().length == 0)
/* 308*/                return Trilean.TRUE;
                }
/* 311*/        return Trilean.FALSE;
            }

            public Trilean isCreator(Constructor constructor, Class class1)
            {
/* 315*/        return Trilean.valueOf(creatorVisibilityFilter.isVisible(constructor));
            }

            public Trilean isCreator(Method method, Class class1)
            {
/* 319*/        return Trilean.FALSE;
            }

            public Trilean isMutator(Field field, Class class1)
            {
/* 323*/        return Trilean.valueOf(fieldVisibilityFilter.isVisible(field));
            }

            public Trilean isMutator(Method method, Class class1)
            {
/* 327*/        if(!method.isBridge() && methodVisibilityFilter.isVisible(method) && method.getName().length() > 3 && method.getName().startsWith("set") && method.getParameterTypes().length == 1 && method.getReturnType() == Void.TYPE)
/* 331*/            return Trilean.TRUE;
/* 334*/        else
/* 334*/            return Trilean.FALSE;
            }

            private final VisibilityFilter fieldVisibilityFilter;
            private final VisibilityFilter methodVisibilityFilter;
            private final VisibilityFilter creatorVisibilityFilter;

            public ()
            {
/* 268*/        this(VisibilityFilter.PACKAGE_PUBLIC, VisibilityFilter.PACKAGE_PUBLIC, VisibilityFilter.PACKAGE_PUBLIC);
            }

            public <init>(VisibilityFilter visibilityfilter, VisibilityFilter visibilityfilter1, VisibilityFilter visibilityfilter2)
            {
/* 282*/        fieldVisibilityFilter = visibilityfilter;
/* 283*/        methodVisibilityFilter = visibilityfilter1;
/* 284*/        creatorVisibilityFilter = visibilityfilter2;
            }
}
