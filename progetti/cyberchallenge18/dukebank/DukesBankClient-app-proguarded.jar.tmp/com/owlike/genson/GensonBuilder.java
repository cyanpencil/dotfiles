// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonBuilder.java

package com.owlike.genson;

import com.owlike.genson.convert.BasicConvertersFactory;
import com.owlike.genson.convert.BeanViewConverter;
import com.owlike.genson.convert.ChainedFactory;
import com.owlike.genson.convert.CircularClassReferenceConverterFactory;
import com.owlike.genson.convert.ClassMetadataConverter;
import com.owlike.genson.convert.ContextualFactory;
import com.owlike.genson.convert.DefaultConverters;
import com.owlike.genson.convert.NullConverterFactory;
import com.owlike.genson.convert.RuntimeTypeConverter;
import com.owlike.genson.ext.GensonBundle;
import com.owlike.genson.reflect.ASMCreatorParameterNameResolver;
import com.owlike.genson.reflect.AbstractBeanDescriptorProvider;
import com.owlike.genson.reflect.BaseBeanDescriptorProvider;
import com.owlike.genson.reflect.BeanDescriptorProvider;
import com.owlike.genson.reflect.BeanMutatorAccessorResolver;
import com.owlike.genson.reflect.BeanPropertyFactory;
import com.owlike.genson.reflect.BeanViewDescriptorProvider;
import com.owlike.genson.reflect.PropertyFilter;
import com.owlike.genson.reflect.PropertyNameResolver;
import com.owlike.genson.reflect.RenamingPropertyNameResolver;
import com.owlike.genson.reflect.RuntimePropertyFilter;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.reflect.VisibilityFilter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.owlike.genson:
//            Converter, Deserializer, GenericType, Genson, 
//            Serializer, Factory

public class GensonBuilder
{

            public GensonBuilder()
            {
/*  40*/        skipNull = false;
/*  41*/        htmlSafe = false;
/*  42*/        withClassMetadata = false;
/*  43*/        throwExcOnNoDebugInfo = false;
/*  44*/        useGettersAndSetters = true;
/*  45*/        useFields = true;
/*  46*/        withBeanViewConverter = false;
/*  47*/        useRuntimeTypeForSerialization = false;
/*  48*/        withDebugInfoPropertyNameResolver = false;
/*  49*/        strictDoubleParse = false;
/*  50*/        indent = false;
/*  51*/        metadata = false;
/*  52*/        failOnMissingProperty = false;
/*  54*/        _bundles = new ArrayList();
/*  58*/        propertyFilter = VisibilityFilter.PACKAGE_PUBLIC;
/*  59*/        methodFilter = VisibilityFilter.PACKAGE_PUBLIC;
/*  60*/        constructorFilter = VisibilityFilter.PACKAGE_PUBLIC;
/*  62*/        classLoader = getClass().getClassLoader();
/*  64*/        dateFormat = SimpleDateFormat.getDateTimeInstance();
/*  65*/        useDateAsTimestamp = true;
/*  66*/        classMetadataWithStaticType = true;
/*  77*/        failOnNullPrimitive = false;
/*  78*/        runtimePropertyFilter = RuntimePropertyFilter.noFilter;
/*  81*/        defaultValues.put(Integer.TYPE, Integer.valueOf(0));
/*  82*/        defaultValues.put(Long.TYPE, Long.valueOf(0L));
/*  83*/        defaultValues.put(Short.TYPE, Short.valueOf((short)0));
/*  84*/        defaultValues.put(Double.TYPE, Double.valueOf(0.0D));
/*  85*/        defaultValues.put(Float.TYPE, Float.valueOf(0.0F));
/*  86*/        defaultValues.put(Boolean.TYPE, Boolean.valueOf(false));
/*  87*/        defaultValues.put(Byte.TYPE, Byte.valueOf((byte)0));
/*  88*/        defaultValues.put(Character.TYPE, Character.valueOf('\0'));
            }

            public GensonBuilder addAlias(String s, Class class1)
            {
/* 102*/        withClassMetadata = true;
/* 103*/        withClassAliases.put(s, class1);
/* 104*/        return this;
            }

            public transient GensonBuilder withConverters(Converter aconverter[])
            {
/* 114*/        int i = (aconverter = aconverter).length;
/* 114*/        for(int j = 0; j < i; j++)
                {
/* 114*/            Converter converter = aconverter[j];
                    Type type;
/* 115*/            type = TypeUtil.expandType(type = TypeUtil.typeOf(0, TypeUtil.lookupGenericType(com/owlike/genson/Converter, converter.getClass())), converter.getClass());
/* 118*/            registerConverter(converter, type);
                }

/* 120*/        return this;
            }

            public GensonBuilder withConverter(Converter converter, Class class1)
            {
/* 131*/        registerConverter(converter, class1);
/* 132*/        return this;
            }

            public GensonBuilder withConverter(Converter converter, GenericType generictype)
            {
/* 143*/        registerConverter(converter, generictype.getType());
/* 144*/        return this;
            }

            private void registerConverter(Converter converter, Type type)
            {
/* 148*/        if(serializersMap.containsKey(type))
/* 149*/            throw new IllegalStateException((new StringBuilder("Can not register converter ")).append(converter.getClass()).append(". A custom serializer is already registered for type ").append(type).toString());
/* 152*/        if(deserializersMap.containsKey(type))
                {
/* 153*/            throw new IllegalStateException((new StringBuilder("Can not register converter ")).append(converter.getClass()).append(". A custom deserializer is already registered for type ").append(type).toString());
                } else
                {
/* 156*/            serializersMap.put(type, converter);
/* 157*/            deserializersMap.put(type, converter);
/* 158*/            return;
                }
            }

            public transient GensonBuilder withSerializers(Serializer aserializer[])
            {
/* 161*/        int i = (aserializer = aserializer).length;
/* 161*/        for(int j = 0; j < i; j++)
                {
/* 161*/            Serializer serializer = aserializer[j];
                    Type type;
/* 162*/            type = TypeUtil.expandType(type = TypeUtil.typeOf(0, TypeUtil.lookupGenericType(com/owlike/genson/Serializer, serializer.getClass())), serializer.getClass());
/* 165*/            registerSerializer(serializer, type);
                }

/* 167*/        return this;
            }

            public GensonBuilder withSerializer(Serializer serializer, Class class1)
            {
/* 171*/        registerSerializer(serializer, class1);
/* 172*/        return this;
            }

            public GensonBuilder withSerializer(Serializer serializer, GenericType generictype)
            {
/* 176*/        registerSerializer(serializer, generictype.getType());
/* 177*/        return this;
            }

            private void registerSerializer(Serializer serializer, Type type)
            {
/* 181*/        if(serializersMap.containsKey(type))
                {
/* 182*/            throw new IllegalStateException((new StringBuilder("Can not register serializer ")).append(serializer.getClass()).append(". A custom serializer is already registered for type ").append(type).toString());
                } else
                {
/* 185*/            serializersMap.put(type, serializer);
/* 186*/            return;
                }
            }

            public transient GensonBuilder withDeserializers(Deserializer adeserializer[])
            {
/* 189*/        int i = (adeserializer = adeserializer).length;
/* 189*/        for(int j = 0; j < i; j++)
                {
/* 189*/            Deserializer deserializer = adeserializer[j];
                    Type type;
/* 190*/            type = TypeUtil.expandType(type = TypeUtil.typeOf(0, TypeUtil.lookupGenericType(com/owlike/genson/Deserializer, deserializer.getClass())), deserializer.getClass());
/* 193*/            registerDeserializer(deserializer, type);
                }

/* 195*/        return this;
            }

            public GensonBuilder withDeserializer(Deserializer deserializer, Class class1)
            {
/* 199*/        registerDeserializer(deserializer, class1);
/* 200*/        return this;
            }

            public GensonBuilder withDeserializer(Deserializer deserializer, GenericType generictype)
            {
/* 205*/        registerDeserializer(deserializer, generictype.getType());
/* 206*/        return this;
            }

            private void registerDeserializer(Deserializer deserializer, Type type)
            {
/* 210*/        if(deserializersMap.containsKey(type))
                {
/* 211*/            throw new IllegalStateException((new StringBuilder("Can not register deserializer ")).append(deserializer.getClass()).append(". A custom deserializer is already registered for type ").append(type).toString());
                } else
                {
/* 214*/            deserializersMap.put(type, deserializer);
/* 215*/            return;
                }
            }

            public GensonBuilder withConverterFactory(Factory factory)
            {
/* 224*/        converterFactories.add(factory);
/* 225*/        return this;
            }

            public GensonBuilder withSerializerFactory(Factory factory)
            {
/* 235*/        converterFactories.add(factory);
/* 236*/        return this;
            }

            public GensonBuilder withDeserializerFactory(Factory factory)
            {
/* 246*/        converterFactories.add(factory);
/* 247*/        return this;
            }

            public transient GensonBuilder withContextualFactory(ContextualFactory acontextualfactory[])
            {
/* 255*/        contextualFactories.addAll(Arrays.asList(acontextualfactory));
/* 256*/        return this;
            }

            public GensonBuilder withConverterFactory(ChainedFactory chainedfactory)
            {
/* 271*/        if(customFactoryChain == null)
/* 271*/            customFactoryChain = chainedfactory;
/* 273*/        else
/* 273*/            customFactoryChain.append(chainedfactory);
/* 275*/        return this;
            }

            public transient GensonBuilder withBeanPropertyFactory(BeanPropertyFactory abeanpropertyfactory[])
            {
/* 284*/        beanPropertyFactories.addAll(Arrays.asList(abeanpropertyfactory));
/* 285*/        return this;
            }

            public transient GensonBuilder withBundle(GensonBundle agensonbundle[])
            {
/* 300*/        int i = (agensonbundle = agensonbundle).length;
/* 300*/        for(int j = 0; j < i; j++)
                {
                    GensonBundle gensonbundle;
/* 300*/            (gensonbundle = agensonbundle[j]).configure(this);
/* 302*/            _bundles.add(gensonbundle);
                }

/* 304*/        return this;
            }

            public GensonBuilder withClassLoader(ClassLoader classloader)
            {
/* 314*/        classLoader = classloader;
/* 315*/        return this;
            }

            public GensonBuilder set(BeanMutatorAccessorResolver beanmutatoraccessorresolver)
            {
/* 327*/        mutatorAccessorResolver = beanmutatoraccessorresolver;
/* 328*/        return this;
            }

            public GensonBuilder set(PropertyNameResolver propertynameresolver)
            {
/* 339*/        propertyNameResolver = propertynameresolver;
/* 340*/        return this;
            }

            public transient GensonBuilder with(BeanMutatorAccessorResolver abeanmutatoraccessorresolver[])
            {
/* 351*/        if(mutatorAccessorResolver == null)
/* 352*/            mutatorAccessorResolver = createBeanMutatorAccessorResolver();
/* 353*/        if(mutatorAccessorResolver instanceof com.owlike.genson.reflect.BeanMutatorAccessorResolver.CompositeResolver)
/* 354*/            ((com.owlike.genson.reflect.BeanMutatorAccessorResolver.CompositeResolver)mutatorAccessorResolver).add(abeanmutatoraccessorresolver);
/* 356*/        else
/* 356*/            throw new IllegalStateException((new StringBuilder("You can not add multiple resolvers if the base resolver is not of type ")).append(com/owlike/genson/reflect/BeanMutatorAccessorResolver$CompositeResolver.getName()).toString());
/* 359*/        return this;
            }

            public Map getSerializersMap()
            {
/* 363*/        return Collections.unmodifiableMap(serializersMap);
            }

            public Map getDeserializersMap()
            {
/* 367*/        return Collections.unmodifiableMap(deserializersMap);
            }

            public ClassLoader getClassLoader()
            {
/* 371*/        return classLoader;
            }

            public transient GensonBuilder with(PropertyNameResolver apropertynameresolver[])
            {
/* 382*/        if(propertyNameResolver == null)
/* 382*/            propertyNameResolver = createPropertyNameResolver();
/* 383*/        if(propertyNameResolver instanceof com.owlike.genson.reflect.PropertyNameResolver.CompositePropertyNameResolver)
/* 384*/            ((com.owlike.genson.reflect.PropertyNameResolver.CompositePropertyNameResolver)propertyNameResolver).add(apropertynameresolver);
/* 386*/        else
/* 386*/            throw new IllegalStateException((new StringBuilder("You can not add multiple resolvers if the base resolver is not of type ")).append(com/owlike/genson/reflect/PropertyNameResolver$CompositePropertyNameResolver.getName()).toString());
/* 389*/        return this;
            }

            public GensonBuilder rename(String s, String s1)
            {
/* 396*/        return rename(s, null, s1, null);
            }

            public GensonBuilder rename(Class class1, String s)
            {
/* 403*/        return rename(null, null, s, class1);
            }

            public GensonBuilder rename(String s, Class class1, String s1)
            {
/* 410*/        return rename(s, class1, s1, null);
            }

            public GensonBuilder rename(String s, String s1, Class class1)
            {
/* 417*/        return rename(s, null, s1, class1);
            }

            public GensonBuilder rename(String s, Class class1, String s1, Class class2)
            {
/* 425*/        return with(new PropertyNameResolver[] {
/* 425*/            new RenamingPropertyNameResolver(s, class1, class2, s1)
                });
            }

            public GensonBuilder exclude(String s)
            {
/* 429*/        return filter(s, null, null, true);
            }

            public GensonBuilder exclude(Class class1)
            {
/* 433*/        return filter(null, null, class1, true);
            }

            public GensonBuilder exclude(String s, Class class1)
            {
/* 437*/        return filter(s, class1, null, true);
            }

            public GensonBuilder exclude(String s, Class class1, Class class2)
            {
/* 441*/        return filter(s, class1, class2, true);
            }

            public GensonBuilder include(String s)
            {
/* 445*/        return filter(s, null, null, false);
            }

            public GensonBuilder include(Class class1)
            {
/* 449*/        return filter(null, null, class1, false);
            }

            public GensonBuilder include(String s, Class class1)
            {
/* 453*/        return filter(s, class1, null, false);
            }

            public GensonBuilder include(String s, Class class1, Class class2)
            {
/* 457*/        return filter(s, class1, class2, false);
            }

            private GensonBuilder filter(String s, Class class1, Class class2, boolean flag)
            {
/* 462*/        return with(new BeanMutatorAccessorResolver[] {
/* 462*/            new PropertyFilter(flag, s, class1, class2)
                });
            }

            public GensonBuilder setSkipNull(boolean flag)
            {
/* 472*/        skipNull = flag;
/* 473*/        return this;
            }

            public boolean isSkipNull()
            {
/* 477*/        return skipNull;
            }

            public GensonBuilder setHtmlSafe(boolean flag)
            {
/* 487*/        htmlSafe = flag;
/* 488*/        return this;
            }

            public boolean isHtmlSafe()
            {
/* 492*/        return htmlSafe;
            }

            public GensonBuilder useClassMetadata(boolean flag)
            {
/* 501*/        withClassMetadata = flag;
/* 502*/        metadata = true;
/* 503*/        return this;
            }

            public GensonBuilder useDateFormat(DateFormat dateformat)
            {
/* 514*/        dateFormat = dateformat;
/* 515*/        return this;
            }

            public boolean isThrowExceptionOnNoDebugInfo()
            {
/* 519*/        return throwExcOnNoDebugInfo;
            }

            public GensonBuilder setThrowExceptionIfNoDebugInfo(boolean flag)
            {
/* 532*/        throwExcOnNoDebugInfo = flag;
/* 533*/        return this;
            }

            public GensonBuilder useMethods(boolean flag)
            {
/* 543*/        useGettersAndSetters = flag;
/* 544*/        return this;
            }

            public GensonBuilder useMethods(boolean flag, VisibilityFilter visibilityfilter)
            {
/* 548*/        useMethods(flag);
/* 549*/        return setMethodFilter(visibilityfilter);
            }

            public GensonBuilder useFields(boolean flag)
            {
/* 558*/        useFields = flag;
/* 559*/        return this;
            }

            public GensonBuilder useFields(boolean flag, VisibilityFilter visibilityfilter)
            {
/* 563*/        useFields(flag);
/* 564*/        return setFieldFilter(visibilityfilter);
            }

            public GensonBuilder useBeanViews(boolean flag)
            {
/* 571*/        withBeanViewConverter = flag;
/* 572*/        return this;
            }

            public GensonBuilder useRuntimeType(boolean flag)
            {
/* 584*/        useRuntimeTypeForSerialization = flag;
/* 585*/        return this;
            }

            public GensonBuilder useConstructorWithArguments(boolean flag)
            {
/* 599*/        withDebugInfoPropertyNameResolver = flag;
/* 600*/        return this;
            }

            public GensonBuilder setFieldFilter(VisibilityFilter visibilityfilter)
            {
/* 604*/        propertyFilter = visibilityfilter;
/* 605*/        return this;
            }

            public GensonBuilder setMethodFilter(VisibilityFilter visibilityfilter)
            {
/* 609*/        methodFilter = visibilityfilter;
/* 610*/        return this;
            }

            public GensonBuilder setConstructorFilter(VisibilityFilter visibilityfilter)
            {
/* 614*/        constructorFilter = visibilityfilter;
/* 615*/        return this;
            }

            public GensonBuilder useStrictDoubleParse(boolean flag)
            {
/* 619*/        strictDoubleParse = flag;
/* 620*/        return this;
            }

            public GensonBuilder useIndentation(boolean flag)
            {
/* 628*/        indent = flag;
/* 629*/        return this;
            }

            public GensonBuilder useDateAsTimestamp(boolean flag)
            {
/* 633*/        useDateAsTimestamp = flag;
/* 634*/        return this;
            }

            public GensonBuilder useMetadata(boolean flag)
            {
/* 638*/        metadata = flag;
/* 639*/        return this;
            }

            public GensonBuilder useByteAsInt(boolean flag)
            {
/* 643*/        if(flag)
/* 644*/            withConverters(new Converter[] {
/* 644*/                com.owlike.genson.convert.DefaultConverters.ByteArrayAsIntArrayConverter.instance
                    });
/* 646*/        return this;
            }

            public GensonBuilder failOnMissingProperty(boolean flag)
            {
/* 657*/        failOnMissingProperty = flag;
/* 658*/        return this;
            }

            public GensonBuilder useClassMetadataWithStaticType(boolean flag)
            {
/* 677*/        classMetadataWithStaticType = flag;
/* 678*/        return this;
            }

            public GensonBuilder acceptSingleValueAsList(boolean flag)
            {
/* 686*/        if(flag)
/* 686*/            withConverterFactory(com.owlike.genson.convert.DefaultConverters.SingleValueAsListFactory.instance);
/* 687*/        return this;
            }

            public GensonBuilder useDefaultValue(Object obj, Class class1)
            {
/* 694*/        defaultValues.put(class1, obj);
/* 695*/        return this;
            }

            public GensonBuilder wrapRootValues(final String inputKey, final String outputKey)
            {
/* 715*/        return withConverterFactory(new ChainedFactory() {

                    protected Converter create(Type type, Genson genson, Converter converter)
                    {
/* 719*/                return new com.owlike.genson.convert.DefaultConverters.WrappedRootValueConverter(inputKey, outputKey, converter);
                    }

                    final String val$inputKey;
                    final String val$outputKey;
                    final GensonBuilder this$0;

                    
                    {
/* 715*/                this$0 = GensonBuilder.this;
/* 715*/                inputKey = s;
/* 715*/                outputKey = s1;
/* 715*/                super();
                    }
        });
            }

            public GensonBuilder failOnNullPrimitive(boolean flag)
            {
/* 733*/        failOnNullPrimitive = flag;
/* 734*/        return this;
            }

            public GensonBuilder useRuntimePropertyFilter(RuntimePropertyFilter runtimepropertyfilter)
            {
/* 738*/        runtimePropertyFilter = runtimepropertyfilter;
/* 739*/        return this;
            }

            public Genson create()
            {
/* 750*/        if(propertyNameResolver == null)
/* 750*/            propertyNameResolver = createPropertyNameResolver();
/* 751*/        if(mutatorAccessorResolver == null)
/* 752*/            mutatorAccessorResolver = createBeanMutatorAccessorResolver();
/* 754*/        Object obj = getDefaultConverters();
/* 755*/        addDefaultSerializers(((List) (obj)));
/* 756*/        addDefaultDeserializers(((List) (obj)));
/* 757*/        addDefaultSerializers(getDefaultSerializers());
/* 758*/        addDefaultDeserializers(getDefaultDeserializers());
/* 760*/        obj = new ArrayList();
/* 761*/        addDefaultConverterFactories(((List) (obj)));
/* 762*/        converterFactories.addAll(((java.util.Collection) (obj)));
/* 764*/        obj = new ArrayList();
/* 765*/        addDefaultSerializerFactories(((List) (obj)));
/* 766*/        converterFactories.addAll(((java.util.Collection) (obj)));
/* 768*/        obj = new ArrayList();
/* 769*/        addDefaultDeserializerFactories(((List) (obj)));
/* 770*/        converterFactories.addAll(((java.util.Collection) (obj)));
/* 772*/        obj = new ArrayList();
/* 773*/        addDefaultContextualFactories(((List) (obj)));
/* 774*/        contextualFactories.addAll(((java.util.Collection) (obj)));
/* 776*/        beanDescriptorProvider = createBeanDescriptorProvider();
/* 778*/        if(withBeanViewConverter)
                {
                    ArrayList arraylist;
/* 779*/            (arraylist = new ArrayList()).add(new com.owlike.genson.reflect.BeanViewDescriptorProvider.BeanViewMutatorAccessorResolver());
/* 781*/            arraylist.add(mutatorAccessorResolver);
/* 782*/            beanViewDescriptorProvider = new BeanViewDescriptorProvider(new com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualConverterFactory(contextualFactories), registeredViews, createBeanPropertyFactory(), new com.owlike.genson.reflect.BeanMutatorAccessorResolver.CompositeResolver(arraylist), getPropertyNameResolver());
                }
/* 789*/        return create(createConverterFactory(), withClassAliases);
            }

            private void addDefaultSerializers(List list)
            {
/* 793*/        if(list != null)
                {
/* 794*/            list = list.iterator();
/* 794*/            do
                    {
/* 794*/                if(!list.hasNext())
/* 794*/                    break;
/* 794*/                Serializer serializer = (Serializer)list.next();
                        Type type;
/* 795*/                type = TypeUtil.expandType(type = TypeUtil.typeOf(0, TypeUtil.lookupGenericType(com/owlike/genson/Serializer, serializer.getClass())), serializer.getClass());
/* 798*/                if(!serializersMap.containsKey(type))
/* 799*/                    serializersMap.put(type, serializer);
                    } while(true);
                }
            }

            private void addDefaultDeserializers(List list)
            {
/* 805*/        if(list != null)
                {
/* 806*/            list = list.iterator();
/* 806*/            do
                    {
/* 806*/                if(!list.hasNext())
/* 806*/                    break;
/* 806*/                Deserializer deserializer = (Deserializer)list.next();
                        Type type;
/* 807*/                type = TypeUtil.expandType(type = TypeUtil.typeOf(0, TypeUtil.lookupGenericType(com/owlike/genson/Deserializer, deserializer.getClass())), deserializer.getClass());
/* 809*/                if(!deserializersMap.containsKey(type))
/* 810*/                    deserializersMap.put(type, deserializer);
                    } while(true);
                }
            }

            protected Genson create(Factory factory, Map map)
            {
/* 825*/        return new Genson(factory, getBeanDescriptorProvider(), isSkipNull(), isHtmlSafe(), map, withClassMetadata, strictDoubleParse, indent, metadata, failOnMissingProperty, defaultValues, runtimePropertyFilter);
            }

            protected Factory createConverterFactory()
            {
                CircularClassReferenceConverterFactory circularclassreferenceconverterfactory;
/* 839*/        (circularclassreferenceconverterfactory = new CircularClassReferenceConverterFactory()).append(new NullConverterFactory(failOnNullPrimitive));
/* 843*/        if(useRuntimeTypeForSerialization)
/* 843*/            circularclassreferenceconverterfactory.append(new com.owlike.genson.convert.RuntimeTypeConverter.RuntimeTypeConverterFactory());
/* 845*/        circularclassreferenceconverterfactory.append(new com.owlike.genson.convert.ClassMetadataConverter.ClassMetadataConverterFactory(classMetadataWithStaticType));
/* 847*/        if(customFactoryChain != null)
/* 847*/            circularclassreferenceconverterfactory.append(customFactoryChain);
/* 849*/        if(withBeanViewConverter)
/* 849*/            circularclassreferenceconverterfactory.append(new com.owlike.genson.convert.BeanViewConverter.BeanViewConverterFactory(getBeanViewDescriptorProvider()));
/* 852*/        com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualFactoryDecorator contextualfactorydecorator = new com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualFactoryDecorator(new BasicConvertersFactory(getSerializersMap(), getDeserializersMap(), getFactories(), getBeanDescriptorProvider()));
/* 856*/        circularclassreferenceconverterfactory.append(contextualfactorydecorator);
/* 858*/        return circularclassreferenceconverterfactory;
            }

            protected BeanMutatorAccessorResolver createBeanMutatorAccessorResolver()
            {
                ArrayList arraylist;
/* 862*/        (arraylist = new ArrayList()).add(new com.owlike.genson.reflect.BeanMutatorAccessorResolver.GensonAnnotationsResolver());
/* 865*/        arraylist.add(new com.owlike.genson.reflect.BeanMutatorAccessorResolver.StandardMutaAccessorResolver(propertyFilter, methodFilter, constructorFilter));
/* 868*/        return new com.owlike.genson.reflect.BeanMutatorAccessorResolver.CompositeResolver(arraylist);
            }

            protected PropertyNameResolver createPropertyNameResolver()
            {
                ArrayList arraylist;
/* 884*/        (arraylist = new ArrayList()).add(new com.owlike.genson.reflect.PropertyNameResolver.AnnotationPropertyNameResolver());
/* 886*/        arraylist.add(new com.owlike.genson.reflect.PropertyNameResolver.ConventionalBeanPropertyNameResolver());
/* 887*/        if(withDebugInfoPropertyNameResolver)
/* 888*/            arraylist.add(new ASMCreatorParameterNameResolver(isThrowExceptionOnNoDebugInfo()));
/* 890*/        return new com.owlike.genson.reflect.PropertyNameResolver.CompositePropertyNameResolver(arraylist);
            }

            protected List getDefaultConverters()
            {
                ArrayList arraylist;
/* 900*/        (arraylist = new ArrayList()).add(com.owlike.genson.convert.DefaultConverters.StringConverter.instance);
/* 902*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.NumberConverter.instance);
/* 903*/        arraylist.add(new com.owlike.genson.convert.DefaultConverters.DateConverter(dateFormat, useDateAsTimestamp));
/* 904*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.URLConverter.instance);
/* 905*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.URIConverter.instance);
/* 906*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.TimestampConverter.instance);
/* 907*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.BigDecimalConverter.instance);
/* 908*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.BigIntegerConverter.instance);
/* 909*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.UUIDConverter.instance);
/* 910*/        arraylist.add(com.owlike.genson.convert.DefaultConverters.FileConverter.instance);
/* 911*/        return arraylist;
            }

            protected void addDefaultConverterFactories(List list)
            {
/* 920*/        list.add(com.owlike.genson.convert.DefaultConverters.ArrayConverterFactory.instance);
/* 921*/        list.add(com.owlike.genson.convert.DefaultConverters.CollectionConverterFactory.instance);
/* 922*/        list.add(com.owlike.genson.convert.DefaultConverters.MapConverterFactory.instance);
/* 923*/        list.add(com.owlike.genson.convert.DefaultConverters.EnumConverterFactory.instance);
/* 924*/        list.add(com.owlike.genson.convert.DefaultConverters.PrimitiveConverterFactory.instance);
/* 925*/        list.add(com.owlike.genson.convert.DefaultConverters.UntypedConverterFactory.instance);
/* 926*/        list.add(new com.owlike.genson.convert.DefaultConverters.CalendarConverterFactory(new com.owlike.genson.convert.DefaultConverters.DateConverter(dateFormat, useDateAsTimestamp)));
            }

            protected void addDefaultContextualFactories(List list)
            {
/* 932*/        list.add(new com.owlike.genson.convert.DefaultConverters.DateContextualFactory());
/* 933*/        list.add(new com.owlike.genson.convert.DefaultConverters.PropertyConverterFactory());
            }

            protected List getDefaultSerializers()
            {
/* 937*/        return null;
            }

            protected void addDefaultSerializerFactories(List list)
            {
            }

            protected List getDefaultDeserializers()
            {
/* 945*/        return null;
            }

            protected void addDefaultDeserializerFactories(List list)
            {
            }

            protected BeanDescriptorProvider createBeanDescriptorProvider()
            {
/* 962*/        com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualConverterFactory contextualconverterfactory = new com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualConverterFactory(contextualFactories);
/* 963*/        BeanPropertyFactory beanpropertyfactory = createBeanPropertyFactory();
/* 965*/        ArrayList arraylist = new ArrayList();
/* 966*/        Iterator iterator = _bundles.iterator();
/* 966*/        do
                {
/* 966*/            if(!iterator.hasNext())
/* 966*/                break;
                    Object obj;
/* 966*/            if((obj = ((GensonBundle) (obj = (GensonBundle)iterator.next())).createBeanDescriptorProvider(contextualconverterfactory, beanpropertyfactory, getMutatorAccessorResolver(), getPropertyNameResolver(), this)) != null)
/* 972*/                arraylist.add(obj);
                } while(true);
/* 975*/        arraylist.add(new BaseBeanDescriptorProvider(new com.owlike.genson.reflect.AbstractBeanDescriptorProvider.ContextualConverterFactory(contextualFactories), createBeanPropertyFactory(), getMutatorAccessorResolver(), getPropertyNameResolver(), useGettersAndSetters, useFields, true));
/* 981*/        return new com.owlike.genson.reflect.BeanDescriptorProvider.CompositeBeanDescriptorProvider(arraylist);
            }

            protected BeanPropertyFactory createBeanPropertyFactory()
            {
/* 985*/        if(withBeanViewConverter)
/* 986*/            beanPropertyFactories.add(new com.owlike.genson.reflect.BeanViewDescriptorProvider.BeanViewPropertyFactory(registeredViews));
/* 988*/        beanPropertyFactories.add(new com.owlike.genson.reflect.BeanPropertyFactory.StandardFactory());
/* 989*/        return new com.owlike.genson.reflect.BeanPropertyFactory.CompositeFactory(beanPropertyFactories);
            }

            protected final PropertyNameResolver getPropertyNameResolver()
            {
/* 993*/        return propertyNameResolver;
            }

            protected final BeanMutatorAccessorResolver getMutatorAccessorResolver()
            {
/* 997*/        return mutatorAccessorResolver;
            }

            protected final BeanDescriptorProvider getBeanDescriptorProvider()
            {
/*1001*/        return beanDescriptorProvider;
            }

            protected final BeanViewDescriptorProvider getBeanViewDescriptorProvider()
            {
/*1005*/        return beanViewDescriptorProvider;
            }

            public final List getFactories()
            {
/*1009*/        return Collections.unmodifiableList(converterFactories);
            }

            public final boolean isDateAsTimestamp()
            {
/*1013*/        return useDateAsTimestamp;
            }

            private final Map serializersMap = new HashMap();
            private final Map deserializersMap = new HashMap();
            private final List converterFactories = new ArrayList();
            private final List contextualFactories = new ArrayList();
            private final List beanPropertyFactories = new ArrayList();
            private boolean skipNull;
            private boolean htmlSafe;
            private boolean withClassMetadata;
            private boolean throwExcOnNoDebugInfo;
            private boolean useGettersAndSetters;
            private boolean useFields;
            private boolean withBeanViewConverter;
            private boolean useRuntimeTypeForSerialization;
            private boolean withDebugInfoPropertyNameResolver;
            private boolean strictDoubleParse;
            private boolean indent;
            private boolean metadata;
            private boolean failOnMissingProperty;
            private List _bundles;
            private PropertyNameResolver propertyNameResolver;
            private BeanMutatorAccessorResolver mutatorAccessorResolver;
            private VisibilityFilter propertyFilter;
            private VisibilityFilter methodFilter;
            private VisibilityFilter constructorFilter;
            private ClassLoader classLoader;
            private BeanDescriptorProvider beanDescriptorProvider;
            private DateFormat dateFormat;
            private boolean useDateAsTimestamp;
            private boolean classMetadataWithStaticType;
            private BeanViewDescriptorProvider beanViewDescriptorProvider;
            private final Map withClassAliases = new HashMap();
            private final Map registeredViews = new HashMap();
            private ChainedFactory customFactoryChain;
            private final Map defaultValues = new HashMap();
            private boolean failOnNullPrimitive;
            private RuntimePropertyFilter runtimePropertyFilter;
}
