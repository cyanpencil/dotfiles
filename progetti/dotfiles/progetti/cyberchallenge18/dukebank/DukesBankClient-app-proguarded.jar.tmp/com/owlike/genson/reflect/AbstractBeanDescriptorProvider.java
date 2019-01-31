// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractBeanDescriptorProvider.java

package com.owlike.genson.reflect;

import com.owlike.genson.*;
import com.owlike.genson.convert.ContextualFactory;
import java.lang.reflect.Type;
import java.util.*;

// Referenced classes of package com.owlike.genson.reflect:
//            BeanCreator, BeanDescriptor, BeanDescriptorProvider, BeanProperty, 
//            PropertyAccessor, PropertyMutator, TypeUtil

public abstract class AbstractBeanDescriptorProvider
    implements BeanDescriptorProvider
{
    public static final class ContextualFactoryDecorator
        implements Factory
    {

                public final Converter create(Type type, Genson genson)
                {
                    Converter converter;
/*  73*/            if((converter = (Converter)ThreadLocalHolder.get("__GENSON$CREATION_CONTEXT", com/owlike/genson/Converter)) != null)
/*  74*/                return converter;
/*  75*/            else
/*  75*/                return (Converter)delegatedFactory.create(type, genson);
                }

                public final volatile Object create(Type type, Genson genson)
                {
/*  64*/            return create(type, genson);
                }

                private final Factory delegatedFactory;

                public ContextualFactoryDecorator(Factory factory)
                {
/*  68*/            delegatedFactory = factory;
                }
    }

    public static final class ContextualConverterFactory
    {

                final Converter provide(BeanProperty beanproperty, Genson genson)
                {
/*  43*/            Object obj = beanproperty.getType();
/*  44*/            for(Iterator iterator = contextualFactories.iterator(); iterator.hasNext();)
                    {
/*  46*/                Object obj1 = (ContextualFactory)iterator.next();
                        Type type;
/*  48*/                type = TypeUtil.expandType(type = TypeUtil.lookupGenericType(com/owlike/genson/convert/ContextualFactory, obj1.getClass()), obj1.getClass());
/*  50*/                type = TypeUtil.typeOf(0, type);
/*  52*/                if((obj instanceof Class) && ((Class)obj).isPrimitive())
/*  53*/                    obj = TypeUtil.wrap((Class)obj);
/*  55*/                if(TypeUtil.match(((Type) (obj)), type, false) && (obj1 = ((ContextualFactory) (obj1)).create(beanproperty, genson)) != null)
/*  57*/                    return ((Converter) (obj1));
                    }

/*  60*/            return null;
                }

                private final List contextualFactories;

                public ContextualConverterFactory(List list)
                {
/*  38*/            contextualFactories = list == null ? ((List) (new ArrayList())) : ((List) (new ArrayList(list)));
                }
    }


            protected AbstractBeanDescriptorProvider(ContextualConverterFactory contextualconverterfactory)
            {
/*  82*/        contextualConverterFactory = contextualconverterfactory;
            }

            public BeanDescriptor provide(Class class1, Genson genson)
            {
/*  87*/        return provide(class1, ((Type) (class1)), genson);
            }

            public BeanDescriptor provide(Class class1, Type type, Genson genson)
            {
/*  92*/        LinkedHashMap linkedhashmap = new LinkedHashMap();
/*  93*/        Object obj = new LinkedHashMap();
/*  95*/        List list = provideBeanCreators(type, genson);
/*  97*/        provideBeanPropertyAccessors(type, ((Map) (obj)), genson);
/*  98*/        provideBeanPropertyMutators(type, linkedhashmap, genson);
/* 100*/        ArrayList arraylist = new ArrayList(((Map) (obj)).size());
/* 101*/        obj = ((Map) (obj)).entrySet().iterator();
/* 101*/        do
                {
/* 101*/            if(!((Iterator) (obj)).hasNext())
/* 101*/                break;
/* 101*/            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
                    PropertyAccessor propertyaccessor1;
/* 102*/            if((propertyaccessor1 = checkAndMergeAccessors((String)entry.getKey(), (LinkedList)entry.getValue())) != null)
/* 104*/                arraylist.add(propertyaccessor1);
                } while(true);
/* 107*/        obj = new HashMap(linkedhashmap.size());
/* 108*/        Object obj1 = linkedhashmap.entrySet().iterator();
/* 108*/        do
                {
/* 108*/            if(!((Iterator) (obj1)).hasNext())
/* 108*/                break;
/* 108*/            java.util.Map.Entry entry1 = (java.util.Map.Entry)((Iterator) (obj1)).next();
                    PropertyMutator propertymutator;
/* 109*/            if((propertymutator = checkAndMergeMutators((String)entry1.getKey(), (LinkedList)entry1.getValue())) != null)
/* 110*/                ((Map) (obj)).put(propertymutator.name, propertymutator);
                } while(true);
/* 113*/        if((obj1 = checkAndMerge(type, list)) != null)
/* 114*/            mergeAccessorsWithCreatorProperties(type, arraylist, ((BeanCreator) (obj1)));
/* 115*/        if(obj1 != null)
/* 115*/            mergeMutatorsWithCreatorProperties(type, ((Map) (obj)), ((BeanCreator) (obj1)));
                PropertyAccessor propertyaccessor;
/* 118*/        for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
/* 118*/            (propertyaccessor = (PropertyAccessor)iterator.next()).propertySerializer = provide(((BeanProperty) (propertyaccessor)), genson);

                PropertyMutator propertymutator1;
/* 123*/        for(Iterator iterator1 = ((Map) (obj)).values().iterator(); iterator1.hasNext();)
/* 123*/            (propertymutator1 = (PropertyMutator)iterator1.next()).propertyDeserializer = provide(((BeanProperty) (propertymutator1)), genson);

/* 128*/        if(obj1 != null)
                {
                    PropertyMutator propertymutator2;
/* 129*/            for(Iterator iterator2 = ((BeanCreator) (obj1)).parameters.values().iterator(); iterator2.hasNext();)
/* 129*/                (propertymutator2 = (PropertyMutator)iterator2.next()).propertyDeserializer = provide(((BeanProperty) (propertymutator2)), genson);

                }
/* 134*/        for(Iterator iterator3 = ((Map) (obj)).values().iterator(); iterator3.hasNext();)
                {
                    PropertyMutator propertymutator3;
                    String as[];
/* 134*/            int i = (as = (propertymutator3 = (PropertyMutator)iterator3.next()).aliases()).length;
/* 135*/            int j = 0;
/* 135*/            while(j < i) 
                    {
/* 135*/                String s = as[j];
/* 135*/                ((Map) (obj)).put(s, propertymutator3);
/* 135*/                j++;
                    }
                }

/* 142*/        BeanDescriptor beandescriptor = create(class1, type, ((BeanCreator) (obj1)), arraylist, ((Map) (obj)), genson);
/* 143*/        if(!class1.isAssignableFrom(beandescriptor.getOfClass()))
/* 144*/            throw new ClassCastException((new StringBuilder("Actual implementation of BeanDescriptorProvider ")).append(getClass()).append(" seems to do something wrong. Expected BeanDescriptor for type ").append(class1).append(" but provided BeanDescriptor for type ").append(beandescriptor.getOfClass()).toString());
/* 148*/        else
/* 148*/            return beandescriptor;
            }

            private Converter provide(BeanProperty beanproperty, Genson genson)
            {
                Converter converter;
/* 159*/        if((converter = contextualConverterFactory.provide(beanproperty, genson)) != null)
                {
/* 162*/            ThreadLocalHolder.store("__GENSON$DO_NOT_CACHE_CONVERTER", Boolean.valueOf(true));
/* 163*/            ThreadLocalHolder.store("__GENSON$CREATION_CONTEXT", converter);
                }
/* 166*/        beanproperty = genson.provideConverter(beanproperty.type);
/* 168*/        if(converter != null)
                {
/* 169*/            ThreadLocalHolder.remove("__GENSON$DO_NOT_CACHE_CONVERTER", java/lang/Boolean);
/* 170*/            ThreadLocalHolder.remove("__GENSON$CREATION_CONTEXT", com/owlike/genson/Converter);
                }
/* 170*/        return beanproperty;
/* 168*/        beanproperty;
/* 168*/        if(converter != null)
                {
/* 169*/            ThreadLocalHolder.remove("__GENSON$DO_NOT_CACHE_CONVERTER", java/lang/Boolean);
/* 170*/            ThreadLocalHolder.remove("__GENSON$CREATION_CONTEXT", com/owlike/genson/Converter);
                }
/* 170*/        throw beanproperty;
            }

            protected BeanDescriptor create(Class class1, Type type, BeanCreator beancreator, List list, Map map, Genson genson)
            {
/* 189*/        return new BeanDescriptor(class1, TypeUtil.getRawClass(type), list, map, beancreator, genson.failOnMissingProperty());
            }

            protected abstract List provideBeanCreators(Type type, Genson genson);

            protected abstract void provideBeanPropertyMutators(Type type, Map map, Genson genson);

            protected abstract void provideBeanPropertyAccessors(Type type, Map map, Genson genson);

            protected abstract BeanCreator checkAndMerge(Type type, List list);

            protected abstract PropertyMutator checkAndMergeMutators(String s, LinkedList linkedlist);

            protected abstract void mergeMutatorsWithCreatorProperties(Type type, Map map, BeanCreator beancreator);

            protected abstract void mergeAccessorsWithCreatorProperties(Type type, List list, BeanCreator beancreator);

            protected abstract PropertyAccessor checkAndMergeAccessors(String s, LinkedList linkedlist);

            static final String CONTEXT_KEY = "__GENSON$CREATION_CONTEXT";
            static final String DO_NOT_CACHE_CONVERTER_KEY = "__GENSON$DO_NOT_CACHE_CONVERTER";
            private final ContextualConverterFactory contextualConverterFactory;
}
