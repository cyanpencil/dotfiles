// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BeanViewDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import com.owlike.genson.annotation.JsonCreator;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.owlike.genson.reflect:
//            BaseBeanDescriptorProvider, AbstractBeanDescriptorProvider, BeanDescriptor, TypeUtil, 
//            BeanPropertyFactory, BeanMutatorAccessorResolver, PropertyNameResolver, PropertyMutator, 
//            PropertyAccessor, BeanCreator

public class BeanViewDescriptorProvider extends BaseBeanDescriptorProvider
{
    static class BeanViewPropertyMutator extends PropertyMutator.MethodMutator
    {

                public void mutate(Object obj, Object obj1)
                {
/* 259*/            try
                    {
/* 259*/                _setter.invoke(_view, new Object[] {
/* 259*/                    obj1, obj
                        });
/* 266*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 260*/            catch(Object obj)
                    {
/* 261*/                throw couldNotMutate(((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/* 262*/            catch(Object obj)
                    {
/* 263*/                throw couldNotMutate(((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/* 264*/            catch(Object obj)
                    {
/* 265*/                throw couldNotMutate(((Exception) (obj)));
                    }
                }

                private final BeanView _view;

                public BeanViewPropertyMutator(String s, Method method, Type type, BeanView beanview, Class class1)
                {
/* 252*/            super(s, method, type, class1);
/* 253*/            _view = beanview;
                }
    }

    static class BeanViewPropertyAccessor extends PropertyAccessor.MethodAccessor
    {

                public Object access(Object obj)
                {
/* 236*/            return _getter.invoke(_view, new Object[] {
/* 236*/                obj
                    });
/* 237*/            obj;
/* 238*/            throw couldNotAccess(((Exception) (obj)));
/* 239*/            obj;
/* 240*/            throw couldNotAccess(((Exception) (obj)));
/* 241*/            obj;
/* 242*/            throw couldNotAccess(((Exception) (obj)));
                }

                private final BeanView _view;

                public BeanViewPropertyAccessor(String s, Method method, Type type, BeanView beanview, Class class1)
                {
/* 229*/            super(s, method, type, class1);
/* 230*/            _view = beanview;
                }
    }

    public static class BeanViewMutatorAccessorResolver
        implements BeanMutatorAccessorResolver
    {

                public Trilean isAccessor(Field field, Class class1)
                {
/* 173*/            return Trilean.FALSE;
                }

                public Trilean isAccessor(Method method, Class class1)
                {
                    Type type;
/* 177*/            type = TypeUtil.expandType(type = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, class1), class1);
/* 179*/            type = TypeUtil.typeOf(0, type);
/* 180*/            class1 = method.getModifiers();
/* 181*/            return Trilean.valueOf((method.getName().startsWith("get") || method.getName().startsWith("is") && (TypeUtil.match(method.getGenericReturnType(), java/lang/Boolean, false) || Boolean.TYPE.equals(method.getReturnType()))) && TypeUtil.match(type, method.getGenericParameterTypes()[0], false) && Modifier.isPublic(class1) && !Modifier.isAbstract(class1) && !Modifier.isNative(class1));
                }

                public Trilean isCreator(Constructor constructor, Class class1)
                {
/* 191*/            return Trilean.valueOf(Modifier.isPublic(constructor = constructor.getModifiers()) || !Modifier.isPrivate(constructor) && !Modifier.isProtected(constructor));
                }

                public Trilean isCreator(Method method, Class class1)
                {
/* 197*/            if(method.getAnnotation(com/owlike/genson/annotation/JsonCreator) != null)
                    {
/* 198*/                if(Modifier.isStatic(method.getModifiers()))
/* 198*/                    return Trilean.TRUE;
/* 199*/                else
/* 199*/                    throw new JsonBindingException((new StringBuilder("Method ")).append(method.toGenericString()).append(" annotated with @Creator must be static!").toString());
                    } else
                    {
/* 202*/                return Trilean.FALSE;
                    }
                }

                public Trilean isMutator(Field field, Class class1)
                {
/* 206*/            return Trilean.FALSE;
                }

                public Trilean isMutator(Method method, Class class1)
                {
                    Type type;
/* 210*/            type = TypeUtil.expandType(type = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, class1), class1);
/* 212*/            type = TypeUtil.typeOf(0, type);
/* 213*/            class1 = method.getModifiers();
/* 214*/            return Trilean.valueOf(method.getName().startsWith("set") && Void.TYPE.equals(method.getReturnType()) && method.getGenericParameterTypes().length == 2 && TypeUtil.match(type, method.getGenericParameterTypes()[1], false) && Modifier.isPublic(class1) && !Modifier.isAbstract(class1) && !Modifier.isNative(class1));
                }

                public BeanViewMutatorAccessorResolver()
                {
                }
    }

    public static class BeanViewPropertyFactory
        implements BeanPropertyFactory
    {

                public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
                {
/* 119*/            if((genson = (BeanView)views.get(TypeUtil.getRawClass(type))) != null)
                    {
/* 121*/                Object obj = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, genson.getClass());
/* 123*/                obj = TypeUtil.getRawClass(TypeUtil.typeOf(0, TypeUtil.expandType(((Type) (obj)), genson.getClass())));
/* 126*/                type = TypeUtil.expandType(method.getGenericReturnType(), type);
/* 127*/                return new BeanViewPropertyAccessor(s, method, type, genson, ((Class) (obj)));
                    } else
                    {
/* 128*/                return null;
                    }
                }

                public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
                {
/* 133*/            if((genson = (BeanView)views.get(TypeUtil.getRawClass(type))) != null)
                    {
/* 135*/                Object obj = TypeUtil.lookupGenericType(com/owlike/genson/BeanView, genson.getClass());
/* 137*/                obj = TypeUtil.getRawClass(TypeUtil.typeOf(0, TypeUtil.expandType(((Type) (obj)), genson.getClass())));
/* 140*/                type = TypeUtil.expandType(method.getGenericParameterTypes()[0], type);
/* 141*/                return new BeanViewPropertyMutator(s, method, type, genson, ((Class) (obj)));
                    } else
                    {
/* 142*/                return null;
                    }
                }

                public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
                {
/* 148*/            return null;
                }

                public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
                {
/* 154*/            return null;
                }

                public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
                {
/* 160*/            return null;
                }

                public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
                {
/* 166*/            return null;
                }

                private final Map views;

                public BeanViewPropertyFactory(Map map)
                {
/* 113*/            views = map;
                }
    }


            public BeanViewDescriptorProvider(AbstractBeanDescriptorProvider.ContextualConverterFactory contextualconverterfactory, Map map, BeanPropertyFactory beanpropertyfactory, BeanMutatorAccessorResolver beanmutatoraccessorresolver, PropertyNameResolver propertynameresolver)
            {
/*  40*/        super(contextualconverterfactory, beanpropertyfactory, beanmutatoraccessorresolver, propertynameresolver, true, false, true);
/*  34*/        descriptors = new ConcurrentHashMap();
/*  41*/        views = map;
            }

            public BeanDescriptor provide(Class class1, Type type, Genson genson)
            {
/*  48*/        Class class2 = TypeUtil.getRawClass(type);
/*  49*/        if(!com/owlike/genson/BeanView.isAssignableFrom(class2))
/*  50*/            throw new IllegalArgumentException((new StringBuilder("Expected argument of type ")).append(com/owlike/genson/BeanView.getName()).append(" but provided ").append(class2).toString());
                Object obj;
/*  53*/        if((obj = (BeanDescriptor)descriptors.get(class2)) == null)
                {
/*  55*/            obj = TypeUtil.getRawClass(TypeUtil.expandType(com/owlike/genson/BeanView.getTypeParameters()[0], type));
/*  57*/            if(!class1.isAssignableFrom(((Class) (obj))))
/*  58*/                throw new IllegalArgumentException((new StringBuilder("Expected type for ofClass parameter is ")).append(obj).append(" but provided is ").append(class1).toString());
/*  64*/            try
                    {
/*  64*/                if(!views.containsKey(class2))
                        {
/*  65*/                    if(!((Constructor) (obj = class2.getDeclaredConstructor(new Class[0]))).isAccessible())
/*  67*/                        ((Constructor) (obj)).setAccessible(true);
/*  68*/                    views.put(class2, ((Constructor) (obj)).newInstance(new Object[0]));
                        }
/*  70*/                obj = super.provide(class1, type, genson);
/*  71*/                descriptors.put(class2, obj);
                    }
                    // Misplaced declaration of an exception variable
/*  72*/            catch(Object obj)
                    {
/*  73*/                throw couldNotInstantiateBeanView(class1, ((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  74*/            catch(Object obj)
                    {
/*  75*/                throw couldNotInstantiateBeanView(class1, ((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  76*/            catch(Object obj)
                    {
/*  77*/                throw couldNotInstantiateBeanView(class1, ((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  78*/            catch(Object obj)
                    {
/*  79*/                throw couldNotInstantiateBeanView(class1, ((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  80*/            catch(Object obj)
                    {
/*  81*/                throw couldNotInstantiateBeanView(class1, ((Exception) (obj)));
                    }
                    // Misplaced declaration of an exception variable
/*  82*/            catch(Object obj)
                    {
/*  83*/                throw couldNotInstantiateBeanView(class1, ((Exception) (obj)));
                    }
                }
/*  86*/        return ((BeanDescriptor) (obj));
            }

            private JsonBindingException couldNotInstantiateBeanView(Class class1, Exception exception)
            {
/*  91*/        return new JsonBindingException((new StringBuilder("Could not instantiate BeanView ")).append(class1.getName()).append(", BeanView implementations must have a public no arg constructor.").toString(), exception);
            }

            public List provideBeanCreators(Type type, Genson genson)
            {
/*  98*/        ArrayList arraylist = new ArrayList();
/*  99*/        for(Class class1 = TypeUtil.getRawClass(type); class1 != null && !java/lang/Object.equals(class1); class1 = class1.getSuperclass())
/* 101*/            provideMethodCreators(class1, arraylist, type, genson);

/* 103*/        Type type1 = TypeUtil.expandType(com/owlike/genson/BeanView.getTypeParameters()[0], type);
/* 104*/        type = super.provideBeanCreators(type1, genson);
/* 105*/        arraylist.addAll(type);
/* 106*/        return arraylist;
            }

            private Map views;
            private Map descriptors;
}
