// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanMutatorAccessorResolver.java

package com.owlike.genson.reflect;

import com.owlike.genson.JsonBindingException;
import com.owlike.genson.Trilean;
import com.owlike.genson.annotation.*;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            TypeUtil, VisibilityFilter

public interface BeanMutatorAccessorResolver
{
    public static class StandardMutaAccessorResolver
        implements BeanMutatorAccessorResolver
    {

                public Trilean isAccessor(Field field, Class class1)
                {
/* 291*/            return Trilean.valueOf(fieldVisibilityFilter.isVisible(field));
                }

                public Trilean isAccessor(Method method, Class class1)
                {
/* 299*/            if(!method.isBridge())
                    {
                        String s;
/* 300*/                int i = (s = method.getName()).length();
/* 302*/                if(methodVisibilityFilter.isVisible(method) && (i > 3 && s.startsWith("get") || i > 2 && s.startsWith("is") && (TypeUtil.match(TypeUtil.expandType(method.getGenericReturnType(), class1), java/lang/Boolean, false) || TypeUtil.match(method.getGenericReturnType(), Boolean.TYPE, false))) && method.getParameterTypes().length == 0)
/* 308*/                    return Trilean.TRUE;
                    }
/* 311*/            return Trilean.FALSE;
                }

                public Trilean isCreator(Constructor constructor, Class class1)
                {
/* 315*/            return Trilean.valueOf(creatorVisibilityFilter.isVisible(constructor));
                }

                public Trilean isCreator(Method method, Class class1)
                {
/* 319*/            return Trilean.FALSE;
                }

                public Trilean isMutator(Field field, Class class1)
                {
/* 323*/            return Trilean.valueOf(fieldVisibilityFilter.isVisible(field));
                }

                public Trilean isMutator(Method method, Class class1)
                {
/* 327*/            if(!method.isBridge() && methodVisibilityFilter.isVisible(method) && method.getName().length() > 3 && method.getName().startsWith("set") && method.getParameterTypes().length == 1 && method.getReturnType() == Void.TYPE)
/* 331*/                return Trilean.TRUE;
/* 334*/            else
/* 334*/                return Trilean.FALSE;
                }

                private final VisibilityFilter fieldVisibilityFilter;
                private final VisibilityFilter methodVisibilityFilter;
                private final VisibilityFilter creatorVisibilityFilter;

                public StandardMutaAccessorResolver()
                {
/* 268*/            this(VisibilityFilter.PACKAGE_PUBLIC, VisibilityFilter.PACKAGE_PUBLIC, VisibilityFilter.PACKAGE_PUBLIC);
                }

                public StandardMutaAccessorResolver(VisibilityFilter visibilityfilter, VisibilityFilter visibilityfilter1, VisibilityFilter visibilityfilter2)
                {
/* 282*/            fieldVisibilityFilter = visibilityfilter;
/* 283*/            methodVisibilityFilter = visibilityfilter1;
/* 284*/            creatorVisibilityFilter = visibilityfilter2;
                }
    }

    public static class GensonAnnotationsResolver
        implements BeanMutatorAccessorResolver
    {

                public Trilean isAccessor(Field field, Class class1)
                {
/* 173*/            if(mustIgnore(field, true) || field.getName().startsWith("this$"))
/* 174*/                return Trilean.FALSE;
/* 175*/            if(mustInclude(field, true))
/* 176*/                return Trilean.TRUE;
/* 177*/            else
/* 177*/                return Trilean.UNKNOWN;
                }

                public Trilean isAccessor(Method method, Class class1)
                {
/* 181*/            if(mustIgnore(method, true))
/* 182*/                return Trilean.FALSE;
/* 183*/            if(mustInclude(method, true) && method.getParameterTypes().length == 0)
/* 184*/                return Trilean.TRUE;
/* 186*/            else
/* 186*/                return Trilean.UNKNOWN;
                }

                public Trilean isCreator(Constructor constructor, Class class1)
                {
/* 195*/            if(mustIgnore(constructor, false))
/* 196*/                return Trilean.FALSE;
/* 197*/            else
/* 197*/                return Trilean.UNKNOWN;
                }

                public Trilean isCreator(Method method, Class class1)
                {
/* 201*/            if(method.getAnnotation(com/owlike/genson/annotation/JsonCreator) != null)
                    {
/* 202*/                if(Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers()))
/* 204*/                    return Trilean.TRUE;
/* 205*/                else
/* 205*/                    throw new JsonBindingException((new StringBuilder("Method ")).append(method.toGenericString()).append(" annotated with @JsonCreator must be static!").toString());
                    } else
                    {
/* 208*/                return Trilean.FALSE;
                    }
                }

                public Trilean isMutator(Field field, Class class1)
                {
/* 212*/            if(mustIgnore(field, false) || field.getName().startsWith("this$"))
/* 213*/                return Trilean.FALSE;
/* 214*/            if(mustInclude(field, false))
/* 215*/                return Trilean.TRUE;
/* 216*/            else
/* 216*/                return Trilean.UNKNOWN;
                }

                public Trilean isMutator(Method method, Class class1)
                {
/* 220*/            if(mustIgnore(method, false))
/* 221*/                return Trilean.FALSE;
/* 222*/            if(mustInclude(method, false) && method.getParameterTypes().length == 1)
/* 223*/                return Trilean.TRUE;
/* 225*/            else
/* 225*/                return Trilean.UNKNOWN;
                }

                protected boolean mustIgnore(AccessibleObject accessibleobject, boolean flag)
                {
/* 229*/            if((accessibleobject = (JsonIgnore)accessibleobject.getAnnotation(com/owlike/genson/annotation/JsonIgnore)) != null)
                    {
/* 231*/                if(flag)
/* 232*/                    return !accessibleobject.serialize();
/* 234*/                return !accessibleobject.deserialize();
                    } else
                    {
/* 236*/                return false;
                    }
                }

                protected boolean mustInclude(AccessibleObject accessibleobject, boolean flag)
                {
/* 240*/            if((accessibleobject = (JsonProperty)accessibleobject.getAnnotation(com/owlike/genson/annotation/JsonProperty)) != null)
                    {
/* 242*/                if(flag)
/* 243*/                    return accessibleobject.serialize();
/* 245*/                else
/* 245*/                    return accessibleobject.deserialize();
                    } else
                    {
/* 247*/                return false;
                    }
                }

                public GensonAnnotationsResolver()
                {
                }
    }

    public static class CompositeResolver
        implements BeanMutatorAccessorResolver
    {

                public transient CompositeResolver add(BeanMutatorAccessorResolver abeanmutatoraccessorresolver[])
                {
/* 105*/            components.addAll(0, Arrays.asList(abeanmutatoraccessorresolver));
/* 106*/            return this;
                }

                public Trilean isAccessor(Field field, Class class1)
                {
/* 111*/            Trilean trilean = Trilean.UNKNOWN;
/* 112*/            for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isAccessor(field, class1));
/* 116*/            return trilean;
                }

                public Trilean isAccessor(Method method, Class class1)
                {
/* 121*/            Trilean trilean = Trilean.UNKNOWN;
/* 122*/            for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isAccessor(method, class1));
/* 126*/            return trilean;
                }

                public Trilean isCreator(Constructor constructor, Class class1)
                {
/* 131*/            Trilean trilean = Trilean.UNKNOWN;
/* 132*/            for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isCreator(constructor, class1));
/* 136*/            return trilean;
                }

                public Trilean isCreator(Method method, Class class1)
                {
/* 141*/            Trilean trilean = Trilean.UNKNOWN;
/* 142*/            for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isCreator(method, class1));
/* 146*/            return trilean;
                }

                public Trilean isMutator(Field field, Class class1)
                {
/* 151*/            Trilean trilean = Trilean.UNKNOWN;
/* 152*/            for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isMutator(field, class1));
/* 156*/            return trilean;
                }

                public Trilean isMutator(Method method, Class class1)
                {
/* 161*/            Trilean trilean = Trilean.UNKNOWN;
/* 162*/            for(Iterator iterator = components.iterator(); trilean == null || trilean.equals(Trilean.UNKNOWN) && iterator.hasNext(); trilean = ((BeanMutatorAccessorResolver)iterator.next()).isMutator(method, class1));
/* 166*/            return trilean;
                }

                private List components;

                public CompositeResolver(List list)
                {
/*  97*/            if(list == null || list.isEmpty())
                    {
/*  98*/                throw new IllegalArgumentException("The composite resolver must have at least one resolver as component!");
                    } else
                    {
/* 101*/                components = new LinkedList(list);
/* 102*/                return;
                    }
                }
    }

    public static class PropertyBaseResolver
        implements BeanMutatorAccessorResolver
    {

                public Trilean isAccessor(Field field, Class class1)
                {
/*  64*/            return Trilean.UNKNOWN;
                }

                public Trilean isAccessor(Method method, Class class1)
                {
/*  69*/            return Trilean.UNKNOWN;
                }

                public Trilean isCreator(Constructor constructor, Class class1)
                {
/*  74*/            return Trilean.UNKNOWN;
                }

                public Trilean isCreator(Method method, Class class1)
                {
/*  79*/            return Trilean.UNKNOWN;
                }

                public Trilean isMutator(Field field, Class class1)
                {
/*  84*/            return Trilean.UNKNOWN;
                }

                public Trilean isMutator(Method method, Class class1)
                {
/*  89*/            return Trilean.UNKNOWN;
                }

                public PropertyBaseResolver()
                {
                }
    }


    public abstract Trilean isCreator(Constructor constructor, Class class1);

    public abstract Trilean isCreator(Method method, Class class1);

    public abstract Trilean isAccessor(Field field, Class class1);

    public abstract Trilean isAccessor(Method method, Class class1);

    public abstract Trilean isMutator(Field field, Class class1);

    public abstract Trilean isMutator(Method method, Class class1);
}
