// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.*;
import com.owlike.genson.convert.*;
import com.owlike.genson.ext.GensonBundle;
import com.owlike.genson.reflect.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.*;

public class JAXBBundle extends GensonBundle
{
    class JaxbAnnotationsResolver extends com.owlike.genson.reflect.BeanMutatorAccessorResolver.PropertyBaseResolver
    {

                public Trilean isAccessor(Field field, Class class1)
                {
/* 392*/            if(ignore(field, field.getType(), class1))
/* 392*/                return Trilean.FALSE;
/* 393*/            if(include(field, field.getType(), class1))
/* 393*/                return Trilean.TRUE;
/* 394*/            else
/* 394*/                return analyzeAccessTypeInfo(field, field, XmlAccessType.FIELD, class1);
                }

                public Trilean isMutator(Field field, Class class1)
                {
/* 399*/            if(ignore(field, field.getType(), class1))
/* 399*/                return Trilean.FALSE;
/* 400*/            if(include(field, field.getType(), class1))
/* 400*/                return Trilean.TRUE;
/* 401*/            else
/* 401*/                return analyzeAccessTypeInfo(field, field, XmlAccessType.FIELD, class1);
                }

                public Trilean isAccessor(Method method, Class class1)
                {
/* 406*/            if(ignore(method, method.getReturnType(), class1))
/* 406*/                return Trilean.FALSE;
/* 408*/            String s = null;
/* 409*/            if(method.getName().startsWith("get") && method.getName().length() > 3)
/* 410*/                s = method.getName().substring(3);
/* 411*/            else
/* 411*/            if(method.getName().startsWith("is") && method.getName().length() > 2 && method.getReturnType() == Boolean.TYPE || method.getReturnType() == java/lang/Boolean)
/* 414*/                s = method.getName().substring(2);
/* 416*/            if(s != null)
                    {
/* 417*/                if(include(method, method.getReturnType(), class1))
/* 417*/                    return Trilean.TRUE;
/* 418*/                if(find(javax/xml/bind/annotation/XmlTransient, class1, (new StringBuilder("set")).append(s).toString(), new Class[] {
/* 418*/        method.getReturnType()
    }) != null)
/* 419*/                    return Trilean.FALSE;
                    }
/* 422*/            return analyzeAccessTypeInfo(method, method, XmlAccessType.PROPERTY, class1);
                }

                public Trilean isMutator(Method method, Class class1)
                {
/* 427*/            Object obj = method.getParameterTypes().length != 1 ? java/lang/Object : ((Object) (method.getParameterTypes()[0]));
/* 429*/            if(ignore(method, ((Class) (obj)), class1))
/* 429*/                return Trilean.FALSE;
/* 431*/            if(method.getName().startsWith("set") && method.getName().length() > 3)
                    {
/* 432*/                if(include(method, method.getReturnType(), class1))
/* 432*/                    return Trilean.TRUE;
/* 434*/                String s = method.getName().substring(3);
/* 435*/                if(find(javax/xml/bind/annotation/XmlTransient, class1, (new StringBuilder("get")).append(s).toString(), new Class[0]) != null)
/* 436*/                    return Trilean.FALSE;
/* 437*/                if((obj.equals(Boolean.TYPE) || obj.equals(java/lang/Boolean)) && find(javax/xml/bind/annotation/XmlTransient, class1, (new StringBuilder("is")).append(s).toString(), new Class[0]) != null)
/* 439*/                    return Trilean.FALSE;
                    }
/* 443*/            return analyzeAccessTypeInfo(method, method, XmlAccessType.PROPERTY, class1);
                }

                public Trilean analyzeAccessTypeInfo(AccessibleObject accessibleobject, Member member, XmlAccessType xmlaccesstype, Class class1)
                {
/* 448*/            if((accessibleobject = (XmlAccessorType)find(javax/xml/bind/annotation/XmlAccessorType, accessibleobject, class1)) != null)
                    {
/* 451*/                if(accessibleobject.value() == xmlaccesstype && VisibilityFilter.PRIVATE.isVisible(member))
/* 452*/                    return Trilean.TRUE;
/* 453*/                if(accessibleobject.value() != xmlaccesstype && accessibleobject.value() != XmlAccessType.PUBLIC_MEMBER)
/* 455*/                    return Trilean.FALSE;
                    }
/* 458*/            return Trilean.UNKNOWN;
                }

                private boolean ignore(AccessibleObject accessibleobject, Class class1, Class class2)
                {
/* 462*/            return (accessibleobject = (XmlTransient)find(javax/xml/bind/annotation/XmlTransient, accessibleobject, class1)) != null;
                }

                private boolean include(AccessibleObject accessibleobject, Class class1, Class class2)
                {
/* 469*/            return find(javax/xml/bind/annotation/XmlAttribute, accessibleobject, class1) != null || find(javax/xml/bind/annotation/XmlElement, accessibleobject, class1) != null;
                }

                final JAXBBundle this$0;

                private JaxbAnnotationsResolver()
                {
/* 388*/            this$0 = JAXBBundle.this;
/* 388*/            super();
                }

    }

    class JaxbNameResolver
        implements PropertyNameResolver
    {

                public String resolve(int i, Constructor constructor)
                {
/* 357*/            return null;
                }

                public String resolve(int i, Method method)
                {
/* 362*/            return null;
                }

                public String resolve(Field field)
                {
/* 367*/            return extractName(field);
                }

                public String resolve(Method method)
                {
/* 372*/            return extractName(method);
                }

                private String extractName(AccessibleObject accessibleobject)
                {
/* 376*/            String s = null;
                    XmlAttribute xmlattribute;
/* 377*/            if((xmlattribute = (XmlAttribute)accessibleobject.getAnnotation(javax/xml/bind/annotation/XmlAttribute)) != null)
/* 379*/                s = xmlattribute.name();
/* 381*/            else
/* 381*/            if((accessibleobject = (XmlElement)accessibleobject.getAnnotation(javax/xml/bind/annotation/XmlElement)) != null)
/* 382*/                s = accessibleobject.name();
/* 384*/            if("##default".equals(s))
/* 384*/                return null;
/* 384*/            else
/* 384*/                return s;
                }

                private static final String DEFAULT_NAME = "##default";
                final JAXBBundle this$0;

                private JaxbNameResolver()
                {
/* 352*/            this$0 = JAXBBundle.this;
/* 352*/            super();
                }

    }

    class JaxbBeanPropertyFactory
        implements BeanPropertyFactory
    {

                public PropertyAccessor createAccessor(String s, Field field, Type type, Genson genson)
                {
/* 284*/            if((genson = getType(field, field.getGenericType(), type)) != null)
/* 286*/                return new com.owlike.genson.reflect.PropertyAccessor.FieldAccessor(s, field, genson, TypeUtil.getRawClass(type));
/* 289*/            else
/* 289*/                return null;
                }

                public PropertyAccessor createAccessor(String s, Method method, Type type, Genson genson)
                {
/* 295*/            if((genson = getType(method, method.getReturnType(), type)) != null)
/* 297*/                return new com.owlike.genson.reflect.PropertyAccessor.MethodAccessor(s, method, genson, TypeUtil.getRawClass(type));
/* 300*/            else
/* 300*/                return null;
                }

                public PropertyMutator createMutator(String s, Field field, Type type, Genson genson)
                {
/* 305*/            if((genson = getType(field, field.getGenericType(), type)) != null)
/* 307*/                return new com.owlike.genson.reflect.PropertyMutator.FieldMutator(s, field, genson, TypeUtil.getRawClass(type));
/* 310*/            else
/* 310*/                return null;
                }

                public PropertyMutator createMutator(String s, Method method, Type type, Genson genson)
                {
/* 315*/            if(method.getParameterTypes().length == 1 && (genson = getType(method, method.getReturnType(), type)) != null)
/* 318*/                return new com.owlike.genson.reflect.PropertyMutator.MethodMutator(s, method, genson, TypeUtil.getRawClass(type));
/* 322*/            else
/* 322*/                return null;
                }

                public BeanCreator createCreator(Type type, Constructor constructor, String as[], Genson genson)
                {
/* 328*/            return null;
                }

                public BeanCreator createCreator(Type type, Method method, String as[], Genson genson)
                {
/* 334*/            return null;
                }

                private Type getType(AccessibleObject accessibleobject, Type type, Type type1)
                {
/* 338*/            if((type1 = (XmlElement)accessibleobject.getAnnotation(javax/xml/bind/annotation/XmlElement)) != null && type1.type() != javax/xml/bind/annotation/XmlElement$DEFAULT)
                    {
/* 340*/                if(!TypeUtil.getRawClass(type).isAssignableFrom(type1.type()) && (accessibleobject = (XmlJavaTypeAdapter)accessibleobject.getAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter)) == null)
/* 343*/                    throw new ClassCastException((new StringBuilder("Inavlid XmlElement annotation, ")).append(type).append(" is not assignable from ").append(type1.type()).toString());
/* 346*/                else
/* 346*/                    return type1.type();
                    } else
                    {
/* 348*/                return null;
                    }
                }

                final JAXBBundle this$0;

                private JaxbBeanPropertyFactory()
                {
/* 280*/            this$0 = JAXBBundle.this;
/* 280*/            super();
                }

    }

    class EnumConverterFactory
        implements Factory
    {
        class EnumConverter
            implements Converter
        {

                    public void serialize(Enum enum, ObjectWriter objectwriter, Context context)
                    {
/* 270*/                objectwriter.writeUnsafeValue((String)enumToValue.get(enum));
                    }

                    public Enum deserialize(ObjectReader objectreader, Context context)
                    {
/* 275*/                return (Enum)valueToEnum.get(objectreader.valueAsString());
                    }

                    public volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/* 256*/                return deserialize(objectreader, context);
                    }

                    public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/* 256*/                serialize((Enum)obj, objectwriter, context);
                    }

                    private final Map valueToEnum;
                    private final Map enumToValue;
                    final EnumConverterFactory this$1;

                    public EnumConverter(Map map, Map map1)
                    {
/* 262*/                this$1 = EnumConverterFactory.this;
/* 263*/                super();
/* 264*/                valueToEnum = map;
/* 265*/                enumToValue = map1;
                    }
        }


                public Converter create(Type type, Genson genson)
                {
/* 223*/            if(!(type = TypeUtil.getRawClass(type)).isEnum() && !java/lang/Enum.isAssignableFrom(type))
/* 226*/                break MISSING_BLOCK_LABEL_203;
/* 226*/            genson = type;
                    HashMap hashmap;
                    HashMap hashmap1;
/* 229*/            hashmap = new HashMap();
/* 230*/            hashmap1 = new HashMap();
                    Enum aenum[];
/* 231*/            int i = (aenum = (Enum[])genson.getEnumConstants()).length;
/* 231*/            for(int j = 0; j < i; j++)
                    {
/* 231*/                Enum enum = aenum[j];
                        XmlEnumValue xmlenumvalue;
/* 232*/                if((xmlenumvalue = (XmlEnumValue)type.getField(enum.name()).getAnnotation(javax/xml/bind/annotation/XmlEnumValue)) != null)
                        {
/* 236*/                    hashmap.put(xmlenumvalue.value(), enum);
/* 237*/                    hashmap1.put(enum, xmlenumvalue.value());
                        } else
                        {
/* 239*/                    hashmap.put(enum.name(), enum);
/* 240*/                    hashmap1.put(enum, enum.name());
                        }
                    }

/* 244*/            return new EnumConverter(hashmap, hashmap1);
                    SecurityException securityexception;
/* 245*/            securityexception;
/* 246*/            throw new JsonBindingException((new StringBuilder("Unable to introspect enum ")).append(genson).toString(), securityexception);
/* 248*/            JVM INSTR pop ;
/* 253*/            return null;
                }

                public volatile Object create(Type type, Genson genson)
                {
/* 219*/            return create(type, genson);
                }

                final JAXBBundle this$0;

                private EnumConverterFactory()
                {
/* 219*/            this$0 = JAXBBundle.this;
/* 219*/            super();
                }

    }

    class XmlTypeAdapterFactory
        implements ContextualFactory
    {
        class AdaptedConverter
            implements Converter
        {

                    public Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/* 196*/                objectreader = ((ObjectReader) (converter.deserialize(objectreader, context)));
/* 198*/                return adapter.unmarshal(objectreader);
/* 199*/                JVM INSTR pop ;
/* 200*/                throw new JsonBindingException((new StringBuilder("Could not unmarshal object using adapter ")).append(adapter.getClass()).toString());
                    }

                    public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/* 209*/                try
                        {
/* 209*/                    obj = adapter.marshal(obj);
                        }
/* 210*/                catch(Exception _ex)
                        {
/* 211*/                    throw new JsonBindingException((new StringBuilder("Could not marshal object using adapter ")).append(adapter.getClass()).toString());
                        }
/* 214*/                converter.serialize(obj, objectwriter, context);
                    }

                    private final XmlAdapter adapter;
                    private final Converter converter;
                    final XmlTypeAdapterFactory this$1;

                    public AdaptedConverter(XmlAdapter xmladapter, Converter converter1)
                    {
/* 188*/                this$1 = XmlTypeAdapterFactory.this;
/* 189*/                super();
/* 190*/                adapter = xmladapter;
/* 191*/                converter = converter1;
                    }
        }


                public Converter create(BeanProperty beanproperty, Genson genson)
                {
/* 138*/            Object obj = (XmlJavaTypeAdapter)beanproperty.getAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter);
/* 139*/            Object obj1 = null;
/* 141*/            if(obj != null)
                    {
/* 142*/                obj = ((XmlJavaTypeAdapter) (obj)).value();
/* 143*/                obj1 = TypeUtil.expandType(TypeUtil.lookupGenericType(javax/xml/bind/annotation/adapters/XmlAdapter, ((Class) (obj))), ((Type) (obj)));
/* 145*/                Type type = TypeUtil.typeOf(0, ((Type) (obj1)));
/* 146*/                obj1 = TypeUtil.typeOf(1, ((Type) (obj1)));
                        Object obj2;
/* 147*/                if(TypeUtil.getRawClass(((Type) (obj2 = beanproperty.getType()))).isPrimitive())
/* 150*/                    obj2 = TypeUtil.wrap(TypeUtil.getRawClass(((Type) (obj2))));
                        Object obj3;
/* 152*/                if((obj3 = (obj3 = (XmlElement)beanproperty.getAnnotation(javax/xml/bind/annotation/XmlElement)) == null || ((XmlElement) (obj3)).type() == javax/xml/bind/annotation/XmlElement$DEFAULT ? null : ((Object) (((XmlElement) (obj3)).type()))) != null)
                        {
/* 157*/                    if(!TypeUtil.match(type, ((Type) (obj3)), false))
/* 158*/                        throw new ClassCastException((new StringBuilder("The BoundType of XMLAdapter ")).append(obj).append(" is not assignable from property ").append(beanproperty.getName()).append(" declared in ").append(beanproperty.getDeclaringClass()).toString());
                        } else
/* 162*/                if(!TypeUtil.match(((Type) (obj2)), ((Type) (obj1)), false))
/* 163*/                    throw new ClassCastException((new StringBuilder("The BoundType of XMLAdapter ")).append(obj).append(" is not assignable from property ").append(beanproperty.getName()).append(" declared in ").append(beanproperty.getDeclaringClass()).toString());
/* 168*/                try
                        {
/* 168*/                    beanproperty = (XmlAdapter)((Class) (obj)).newInstance();
/* 170*/                    genson = genson.provideConverter(((Type) (obj3 == null ? type : ((Type) (obj3)))));
/* 172*/                    obj1 = new AdaptedConverter(beanproperty, genson);
                        }
                        // Misplaced declaration of an exception variable
/* 173*/                catch(BeanProperty beanproperty)
                        {
/* 174*/                    throw new JsonBindingException((new StringBuilder("Could not instantiate XmlAdapter of type ")).append(obj).toString(), beanproperty);
                        }
                        // Misplaced declaration of an exception variable
/* 176*/                catch(BeanProperty beanproperty)
                        {
/* 177*/                    throw new JsonBindingException((new StringBuilder("Could not instantiate XmlAdapter of type ")).append(obj).toString(), beanproperty);
                        }
                    }
/* 181*/            return ((Converter) (obj1));
                }

                final JAXBBundle this$0;

                private XmlTypeAdapterFactory()
                {
/* 135*/            this$0 = JAXBBundle.this;
/* 135*/            super();
                }

    }

    class XMLGregorianCalendarConverter
        implements Converter
    {

                public void serialize(XMLGregorianCalendar xmlgregoriancalendar, ObjectWriter objectwriter, Context context)
                {
/* 117*/            converter.serialize(xmlgregoriancalendar.toGregorianCalendar().getTime(), objectwriter, context);
                }

                public synchronized XMLGregorianCalendar deserialize(ObjectReader objectreader, Context context)
                {
/* 122*/            context = new GregorianCalendar();
/* 124*/            try
                    {
/* 124*/                context.setTime(dateFormat.parse(objectreader.valueAsString()));
                    }
                    // Misplaced declaration of an exception variable
/* 125*/            catch(Context context)
                    {
/* 126*/                throw new JsonBindingException((new StringBuilder("Could not parse date ")).append(objectreader.valueAsString()).toString(), context);
                    }
/* 130*/            return dateFactory.newXMLGregorianCalendar(context);
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 109*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 109*/            serialize((XMLGregorianCalendar)obj, objectwriter, context);
                }

                private final com.owlike.genson.convert.DefaultConverters.DateConverter converter;
                private final SimpleDateFormat dateFormat;
                final JAXBBundle this$0;

                private XMLGregorianCalendarConverter()
                {
/* 111*/            this$0 = JAXBBundle.this;
/* 111*/            super();
/* 112*/            converter = new com.owlike.genson.convert.DefaultConverters.DateConverter();
/* 113*/            dateFormat = new SimpleDateFormat("yyyy-MM-DD'T'hh:mm:ssZ");
                }

    }

    class DurationConveter
        implements Converter
    {

                public void serialize(Duration duration, ObjectWriter objectwriter, Context context)
                {
/* 100*/            objectwriter.writeValue(duration.toString());
                }

                public Duration deserialize(ObjectReader objectreader, Context context)
                {
/* 105*/            return dateFactory.newDuration(objectreader.valueAsString());
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*  97*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*  97*/            serialize((Duration)obj, objectwriter, context);
                }

                final JAXBBundle this$0;

                private DurationConveter()
                {
/*  97*/            this$0 = JAXBBundle.this;
/*  97*/            super();
                }

    }


            public JAXBBundle()
            {
/*  46*/        wrapRootValues = false;
/*  50*/        try
                {
/*  50*/            dateFactory = DatatypeFactory.newInstance();
/*  53*/            return;
                }
/*  51*/        catch(DatatypeConfigurationException datatypeconfigurationexception)
                {
/*  52*/            throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.", datatypeconfigurationexception);
                }
            }

            public JAXBBundle wrapRootValues(boolean flag)
            {
/*  61*/        wrapRootValues = flag;
/*  62*/        return this;
            }

            public void configure(GensonBuilder gensonbuilder)
            {
/*  70*/        gensonbuilder.withConverters(new Converter[] {
/*  70*/            new XMLGregorianCalendarConverter(), new DurationConveter()
                }).with(new BeanMutatorAccessorResolver[] {
/*  70*/            new com.owlike.genson.reflect.BeanMutatorAccessorResolver.GensonAnnotationsResolver(), new JaxbAnnotationsResolver()
                }).with(new PropertyNameResolver[] {
/*  70*/            new com.owlike.genson.reflect.PropertyNameResolver.AnnotationPropertyNameResolver(), new JaxbNameResolver()
                }).withConverterFactory(new EnumConverterFactory()).withBeanPropertyFactory(new BeanPropertyFactory[] {
/*  70*/            new JaxbBeanPropertyFactory()
                }).withContextualFactory(new ContextualFactory[] {
/*  70*/            new XmlTypeAdapterFactory()
                });
/*  77*/        if(wrapRootValues)
/*  78*/            gensonbuilder.withConverterFactory(new ChainedFactory() {

                        protected Converter create(Type type, Genson genson, Converter converter)
                        {
/*  81*/                    if((genson = (XmlRootElement)(type = TypeUtil.getRawClass(type)).getAnnotation(javax/xml/bind/annotation/XmlRootElement)) != null)
                            {
/*  85*/                        type = "##default".equals(genson.name()) ? ((Type) (firstCharToLower(type.getSimpleName()))) : ((Type) (genson.name()));
/*  86*/                        return new com.owlike.genson.convert.DefaultConverters.WrappedRootValueConverter(type, type, converter);
                            } else
                            {
/*  88*/                        return null;
                            }
                        }

                        final JAXBBundle this$0;

                    
                    {
/*  78*/                this$0 = JAXBBundle.this;
/*  78*/                super();
                    }
            });
            }

            private String firstCharToLower(String s)
            {
/*  94*/        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.length() <= 0 ? "" : s.substring(1)).toString();
            }

            private Annotation find(Class class1, AccessibleObject accessibleobject, Class class2)
            {
/* 478*/        if((accessibleobject = accessibleobject.getAnnotation(class1)) != null)
/* 479*/            return accessibleobject;
/* 480*/        else
/* 480*/            return find(class1, class2);
            }

            private Annotation find(Class class1, Class class2)
            {
                Annotation annotation;
/* 484*/        if((annotation = class2.getAnnotation(class1)) == null && class2.getPackage() != null)
/* 486*/            annotation = class2.getPackage().getAnnotation(class1);
/* 487*/        return annotation;
            }

            private transient Annotation find(Class class1, Class class2, String s, Class aclass[])
            {
/* 493*/        class2 = class2;
_L4:
/* 493*/        if(class2 == null) goto _L2; else goto _L1
_L1:
                Method amethod[];
                int i;
                int j;
/* 495*/        i = (amethod = class2.getDeclaredMethods()).length;
/* 495*/        j = 0;
_L3:
/* 495*/        if(j >= i)
/* 495*/            continue; /* Loop/switch isn't completed */
                Method method;
/* 495*/        if((method = amethod[j]).getName().equals(s) && Arrays.equals(method.getParameterTypes(), aclass))
                {
/* 498*/            if(method.isAnnotationPresent(class1))
/* 499*/                return method.getAnnotation(class1);
/* 495*/            continue; /* Loop/switch isn't completed */
                }
/* 495*/        j++;
                  goto _L3
                SecurityException securityexception;
/* 503*/        securityexception;
/* 504*/        throw new RuntimeException(securityexception);
/* 493*/        class2 = class2.getSuperclass();
                  goto _L4
_L2:
/* 507*/        return null;
            }

            private final DatatypeFactory dateFactory;
            private boolean wrapRootValues;




}
