// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanPropertyFactory.java

package com.owlike.genson.reflect;

import com.owlike.genson.Genson;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            PropertyAccessor, BeanCreator, PropertyMutator, TypeUtil

public interface BeanPropertyFactory
{
    public static class StandardFactory
        implements BeanPropertyFactory
    {

                public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
                {
/*  96*/            genson = TypeUtil.getRawClass(type);
/*  97*/            type = TypeUtil.expandType(field.getGenericType(), type);
/*  98*/            return new PropertyAccessor.FieldAccessor(s, field, type, genson);
                }

                public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
                {
/* 103*/            genson = TypeUtil.expandType(method.getGenericReturnType(), type);
/* 104*/            return new PropertyAccessor.MethodAccessor(s, method, genson, TypeUtil.getRawClass(type));
                }

                public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
                {
/* 109*/            genson = TypeUtil.getRawClass(type);
/* 110*/            type = TypeUtil.expandType(field.getGenericType(), type);
/* 111*/            return new PropertyMutator.FieldMutator(s, field, type, genson);
                }

                public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
                {
/* 115*/            genson = TypeUtil.expandType(method.getGenericParameterTypes()[0], type);
/* 116*/            return new PropertyMutator.MethodMutator(s, method, genson, TypeUtil.getRawClass(type));
                }

                public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
                {
/* 124*/            return new BeanCreator.MethodBeanCreator(method, as, expandTypes(method.getGenericParameterTypes(), type), TypeUtil.getRawClass(type));
                }

                public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
                {
/* 130*/            return new BeanCreator.ConstructorBeanCreator(TypeUtil.getRawClass(type), constructor, as, expandTypes(constructor.getGenericParameterTypes(), type));
                }

                public Type[] expandTypes(Type atype[], Type type)
                {
/* 135*/            Type atype1[] = new Type[atype.length];
/* 136*/            for(int i = 0; i < atype.length; i++)
/* 137*/                atype1[i] = TypeUtil.expandType(atype[i], type);

/* 139*/            return atype1;
                }

                public StandardFactory()
                {
                }
    }

    public static class CompositeFactory
        implements BeanPropertyFactory
    {

                public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
                {
                    Object obj;
/*  38*/            for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  38*/                if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createAccessor(s, field, type, genson)) != null)
/*  40*/                    return ((PropertyAccessor) (obj));

/*  42*/            throw new RuntimeException((new StringBuilder("Failed to create a accessor for field ")).append(field).toString());
                }

                public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
                {
                    Object obj;
/*  48*/            for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  48*/                if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createAccessor(s, method, type, genson)) != null)
/*  50*/                    return ((PropertyAccessor) (obj));

/*  52*/            throw new RuntimeException((new StringBuilder("Failed to create a accessor for method ")).append(method).toString());
                }

                public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
                {
                    Object obj;
/*  58*/            for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  58*/                if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createCreator(type, constructor, as, genson)) != null)
/*  60*/                    return ((BeanCreator) (obj));

/*  62*/            throw new RuntimeException((new StringBuilder("Failed to create a BeanCreator for constructor ")).append(constructor).toString());
                }

                public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
                {
                    Object obj;
/*  68*/            for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  68*/                if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createCreator(type, method, as, genson)) != null)
/*  70*/                    return ((BeanCreator) (obj));

/*  72*/            throw new RuntimeException((new StringBuilder("Failed to create a BeanCreator for method ")).append(method).toString());
                }

                public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
                {
                    Object obj;
/*  77*/            for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  77*/                if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createMutator(s, field, type, genson)) != null)
/*  79*/                    return ((PropertyMutator) (obj));

/*  81*/            throw new RuntimeException((new StringBuilder("Failed to create a mutator for field ")).append(field).toString());
                }

                public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
                {
                    Object obj;
/*  86*/            for(Iterator iterator = factories.iterator(); iterator.hasNext();)
/*  86*/                if((obj = ((BeanPropertyFactory) (obj = (BeanPropertyFactory)iterator.next())).createMutator(s, method, type, genson)) != null)
/*  88*/                    return ((PropertyMutator) (obj));

/*  90*/            throw new RuntimeException((new StringBuilder("Failed to create a mutator for method ")).append(method).toString());
                }

                private final List factories;

                public CompositeFactory(List list)
                {
/*  33*/            factories = new ArrayList(list);
                }
    }


    public abstract PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson);

    public abstract PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson);

    public abstract BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson);

    public abstract BeanCreator createCreator(Type type, Method method, String as[], Genson genson);

    public abstract PropertyMutator createMutator(String s, Field field, Type type, Genson genson);

    public abstract PropertyMutator createMutator(String s, Method method, Type type, Genson genson);
}
