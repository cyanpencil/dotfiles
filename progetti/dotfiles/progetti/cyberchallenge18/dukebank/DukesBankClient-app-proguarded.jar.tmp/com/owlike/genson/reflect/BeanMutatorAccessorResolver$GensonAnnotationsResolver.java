// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanMutatorAccessorResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.JsonBindingException;
import com.owlike.genson.Trilean;
import com.owlike.genson.annotation.*;
import java.lang.reflect.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanMutatorAccessorResolver

public static class 
    implements BeanMutatorAccessorResolver
{

            public Trilean isAccessor(Field field, Class class1)
            {
/* 173*/        if(mustIgnore(field, true) || field.getName().startsWith("this$"))
/* 174*/            return Trilean.FALSE;
/* 175*/        if(mustInclude(field, true))
/* 176*/            return Trilean.TRUE;
/* 177*/        else
/* 177*/            return Trilean.UNKNOWN;
            }

            public Trilean isAccessor(Method method, Class class1)
            {
/* 181*/        if(mustIgnore(method, true))
/* 182*/            return Trilean.FALSE;
/* 183*/        if(mustInclude(method, true) && method.getParameterTypes().length == 0)
/* 184*/            return Trilean.TRUE;
/* 186*/        else
/* 186*/            return Trilean.UNKNOWN;
            }

            public Trilean isCreator(Constructor constructor, Class class1)
            {
/* 195*/        if(mustIgnore(constructor, false))
/* 196*/            return Trilean.FALSE;
/* 197*/        else
/* 197*/            return Trilean.UNKNOWN;
            }

            public Trilean isCreator(Method method, Class class1)
            {
/* 201*/        if(method.getAnnotation(com/owlike/genson/annotation/JsonCreator) != null)
                {
/* 202*/            if(Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers()))
/* 204*/                return Trilean.TRUE;
/* 205*/            else
/* 205*/                throw new JsonBindingException((new StringBuilder("Method ")).append(method.toGenericString()).append(" annotated with @JsonCreator must be static!").toString());
                } else
                {
/* 208*/            return Trilean.FALSE;
                }
            }

            public Trilean isMutator(Field field, Class class1)
            {
/* 212*/        if(mustIgnore(field, false) || field.getName().startsWith("this$"))
/* 213*/            return Trilean.FALSE;
/* 214*/        if(mustInclude(field, false))
/* 215*/            return Trilean.TRUE;
/* 216*/        else
/* 216*/            return Trilean.UNKNOWN;
            }

            public Trilean isMutator(Method method, Class class1)
            {
/* 220*/        if(mustIgnore(method, false))
/* 221*/            return Trilean.FALSE;
/* 222*/        if(mustInclude(method, false) && method.getParameterTypes().length == 1)
/* 223*/            return Trilean.TRUE;
/* 225*/        else
/* 225*/            return Trilean.UNKNOWN;
            }

            protected boolean mustIgnore(AccessibleObject accessibleobject, boolean flag)
            {
/* 229*/        if((accessibleobject = (JsonIgnore)accessibleobject.getAnnotation(com/owlike/genson/annotation/JsonIgnore)) != null)
                {
/* 231*/            if(flag)
/* 232*/                return !accessibleobject.serialize();
/* 234*/            return !accessibleobject.deserialize();
                } else
                {
/* 236*/            return false;
                }
            }

            protected boolean mustInclude(AccessibleObject accessibleobject, boolean flag)
            {
/* 240*/        if((accessibleobject = (JsonProperty)accessibleobject.getAnnotation(com/owlike/genson/annotation/JsonProperty)) != null)
                {
/* 242*/            if(flag)
/* 243*/                return accessibleobject.serialize();
/* 245*/            else
/* 245*/                return accessibleobject.deserialize();
                } else
                {
/* 247*/            return false;
                }
            }

            public ()
            {
            }
}
