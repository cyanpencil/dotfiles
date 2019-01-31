// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Genson.java

package com.owlike.genson;

import com.owlike.genson.reflect.BeanDescriptor;
import com.owlike.genson.reflect.BeanDescriptorProvider;
import com.owlike.genson.reflect.RuntimePropertyFilter;
import com.owlike.genson.stream.JsonReader;
import com.owlike.genson.stream.JsonStreamException;
import com.owlike.genson.stream.JsonWriter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import com.owlike.genson.stream.ValueType;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.owlike.genson:
//            Context, Converter, Deserializer, EncodingAwareReaderFactory, 
//            Factory, GenericType, GensonBuilder, JsonBindingException, 
//            Serializer, ThreadLocalHolder

public final class Genson
{
    /**
     * @deprecated Class Builder is deprecated
     */

    public static class Builder extends GensonBuilder
    {

                public Builder()
                {
                }
    }


            public Genson()
            {
/*  81*/        this(_default.converterFactory, _default.beanDescriptorFactory, _default.skipNull, _default.htmlSafe, _default.aliasClassMap, _default.withClassMetadata, _default.strictDoubleParse, _default.indent, _default.withMetadata, _default.failOnMissingProperty, _default.defaultValues, _default.runtimePropertyFilter);
            }

            public Genson(Factory factory, BeanDescriptorProvider beandescriptorprovider, boolean flag, boolean flag1, Map map, boolean flag2, boolean flag3, 
                    boolean flag4, boolean flag5, boolean flag6, Map map1, RuntimePropertyFilter runtimepropertyfilter)
            {
/*  60*/        converterCache = new ConcurrentHashMap();
/*  72*/        readerFactory = new EncodingAwareReaderFactory();
/* 116*/        converterFactory = factory;
/* 117*/        beanDescriptorFactory = beandescriptorprovider;
/* 118*/        skipNull = flag;
/* 119*/        htmlSafe = flag1;
/* 120*/        aliasClassMap = map;
/* 121*/        withClassMetadata = flag2;
/* 122*/        defaultValues = map1;
/* 123*/        runtimePropertyFilter = runtimepropertyfilter;
/* 124*/        classAliasMap = new HashMap(map.size());
/* 125*/        for(factory = map.entrySet().iterator(); factory.hasNext(); classAliasMap.put(beandescriptorprovider.getValue(), beandescriptorprovider.getKey()))
/* 125*/            beandescriptorprovider = (java.util.Map.Entry)factory.next();

/* 128*/        strictDoubleParse = flag3;
/* 129*/        indent = flag4;
/* 130*/        withMetadata = flag2 || flag5;
/* 131*/        failOnMissingProperty = flag6;
            }

            public final Converter provideConverter(Type type)
            {
/* 143*/        if(Boolean.TRUE.equals(ThreadLocalHolder.get("__GENSON$DO_NOT_CACHE_CONVERTER", java/lang/Boolean)))
/* 144*/            return (Converter)converterFactory.create(type, this);
                Converter converter;
/* 146*/        if((converter = (Converter)converterCache.get(type)) == null)
                {
/* 148*/            if((converter = (Converter)converterFactory.create(type, this)) == null)
/* 150*/                throw new JsonBindingException((new StringBuilder("No converter found for type ")).append(type).toString());
/* 151*/            converterCache.putIfAbsent(type, converter);
                }
/* 153*/        return converter;
            }

            public final String serialize(Object obj)
            {
/* 166*/        StringWriter stringwriter = new StringWriter();
/* 167*/        ObjectWriter objectwriter = createWriter(stringwriter);
/* 169*/        if(obj == null)
/* 169*/            serializeNull(objectwriter);
/* 170*/        else
/* 170*/            serialize(obj, ((Type) (obj.getClass())), objectwriter, new Context(this));
/* 172*/        return stringwriter.toString();
            }

            public final String serialize(Object obj, GenericType generictype)
            {
/* 185*/        StringWriter stringwriter = new StringWriter();
/* 186*/        ObjectWriter objectwriter = createWriter(stringwriter);
/* 188*/        if(obj == null)
/* 188*/            serializeNull(objectwriter);
/* 189*/        else
/* 189*/            serialize(obj, generictype.getType(), objectwriter, new Context(this));
/* 191*/        return stringwriter.toString();
            }

            public final transient String serialize(Object obj, Class class1, Class aclass[])
            {
/* 205*/        StringWriter stringwriter = new StringWriter();
/* 206*/        ObjectWriter objectwriter = createWriter(stringwriter);
/* 208*/        ArrayList arraylist = new ArrayList(aclass.length);
/* 209*/        int i = (aclass = aclass).length;
/* 209*/        for(int j = 0; j < i; j++)
                {
/* 209*/            Class class2 = aclass[j];
/* 209*/            arraylist.add(class2);
                }

/* 210*/        arraylist.add(class1);
/* 212*/        if(obj == null)
/* 212*/            serializeNull(objectwriter);
/* 213*/        else
/* 213*/            serialize(obj, ((Type) (obj.getClass())), objectwriter, new Context(this, arraylist));
/* 215*/        return stringwriter.toString();
            }

            public final void serialize(Object obj, Writer writer)
            {
/* 223*/        writer = createWriter(writer);
/* 225*/        if(obj == null)
                {
/* 225*/            serializeNull(writer);
/* 225*/            return;
                } else
                {
/* 226*/            serialize(obj, ((Type) (obj.getClass())), ((ObjectWriter) (writer)), new Context(this));
/* 227*/            return;
                }
            }

            public final void serialize(Object obj, OutputStream outputstream)
            {
/* 234*/        outputstream = createWriter(outputstream);
/* 236*/        if(obj == null)
                {
/* 236*/            serializeNull(outputstream);
/* 236*/            return;
                } else
                {
/* 237*/            serialize(obj, ((Type) (obj.getClass())), ((ObjectWriter) (outputstream)), new Context(this));
/* 238*/            return;
                }
            }

            public final byte[] serializeBytes(Object obj)
            {
/* 244*/        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
/* 245*/        ObjectWriter objectwriter = createWriter(bytearrayoutputstream);
/* 247*/        if(obj == null)
/* 247*/            serializeNull(objectwriter);
/* 248*/        else
/* 248*/            serialize(obj, obj.getClass(), objectwriter, new Context(this));
/* 250*/        return bytearrayoutputstream.toByteArray();
            }

            public final void serialize(Object obj, ObjectWriter objectwriter, Context context)
            {
/* 263*/        if(obj == null)
                {
/* 263*/            serializeNull(objectwriter);
/* 263*/            return;
                } else
                {
/* 264*/            serialize(obj, ((Type) (obj.getClass())), objectwriter, context);
/* 265*/            return;
                }
            }

            public final void serialize(Object obj, Type type, ObjectWriter objectwriter, Context context)
            {
/* 272*/        Converter converter = provideConverter(type);
/* 274*/        try
                {
/* 274*/            converter.serialize(obj, objectwriter, context);
/* 275*/            objectwriter.flush();
/* 278*/            return;
                }
                // Misplaced declaration of an exception variable
/* 276*/        catch(Object obj)
                {
/* 277*/            throw new JsonBindingException((new StringBuilder("Failed to serialize object of type ")).append(type).toString(), ((Throwable) (obj)));
                }
            }

            private void serializeNull(ObjectWriter objectwriter)
            {
/* 283*/        try
                {
/* 283*/            objectwriter.writeNull();
/* 284*/            objectwriter.flush();
/* 287*/            return;
                }
                // Misplaced declaration of an exception variable
/* 285*/        catch(ObjectWriter objectwriter)
                {
/* 286*/            throw new JsonBindingException("Could not serialize null value.", objectwriter);
                }
            }

            public final Object deserialize(String s, Class class1)
            {
/* 299*/        return deserialize(GenericType.of(class1), createReader(new StringReader(s)), new Context(this));
            }

            public final Object deserialize(String s, GenericType generictype)
            {
/* 314*/        return deserialize(generictype, createReader(new StringReader(s)), new Context(this));
            }

            public final Object deserialize(Reader reader, GenericType generictype)
            {
/* 322*/        return deserialize(generictype, createReader(reader), new Context(this));
            }

            public final Object deserialize(Reader reader, Class class1)
            {
/* 330*/        return deserialize(GenericType.of(class1), createReader(reader), new Context(this));
            }

            public final Object deserialize(InputStream inputstream, Class class1)
            {
/* 338*/        return deserialize(GenericType.of(class1), createReader(inputstream), new Context(this));
            }

            public final Object deserialize(InputStream inputstream, GenericType generictype)
            {
/* 346*/        return deserialize(generictype, createReader(inputstream), new Context(this));
            }

            public final Object deserialize(byte abyte0[], Class class1)
            {
/* 353*/        return deserialize(GenericType.of(class1), createReader(abyte0), new Context(this));
            }

            public final Object deserialize(byte abyte0[], GenericType generictype)
            {
/* 360*/        return deserialize(generictype, createReader(abyte0), new Context(this));
            }

            public final transient Object deserialize(String s, GenericType generictype, Class aclass[])
            {
/* 364*/        s = new StringReader(s);
/* 365*/        return deserialize(generictype, createReader(s), new Context(this, Arrays.asList(aclass)));
            }

            public final transient Object deserialize(String s, Class class1, Class aclass[])
            {
/* 370*/        s = new StringReader(s);
/* 371*/        return deserialize(GenericType.of(class1), createReader(s), new Context(this, Arrays.asList(aclass)));
            }

            public final transient Object deserialize(GenericType generictype, Reader reader, Class aclass[])
            {
/* 376*/        return deserialize(generictype, createReader(reader), new Context(this, Arrays.asList(aclass)));
            }

            public final Object deserialize(GenericType generictype, ObjectReader objectreader, Context context)
            {
/* 380*/        Converter converter = provideConverter(generictype.getType());
/* 382*/        return converter.deserialize(objectreader, context);
/* 383*/        objectreader;
/* 384*/        throw new JsonBindingException((new StringBuilder("Could not deserialize to type ")).append(generictype.getRawClass()).toString(), objectreader);
            }

            public final Object deserializeInto(String s, Object obj)
            {
/* 392*/        return deserializeInto(createReader(new StringReader(s)), obj, new Context(this));
            }

            public final Object deserializeInto(byte abyte0[], Object obj)
            {
/* 399*/        return deserializeInto(createReader(abyte0), obj, new Context(this));
            }

            public final Object deserializeInto(InputStream inputstream, Object obj)
            {
/* 406*/        return deserializeInto(createReader(inputstream), obj, new Context(this));
            }

            public final Object deserializeInto(Reader reader, Object obj)
            {
/* 413*/        return deserializeInto(createReader(reader), obj, new Context(this));
            }

            public final Object deserializeInto(ObjectReader objectreader, Object obj, Context context)
            {
                BeanDescriptor beandescriptor;
/* 423*/        (beandescriptor = getBeanDescriptorProvider().provide(obj.getClass(), this)).deserialize(obj, objectreader, context);
/* 425*/        return obj;
            }

            public final Iterator deserializeValues(InputStream inputstream, Class class1)
            {
/* 432*/        return deserializeValues(createReader(inputstream), GenericType.of(class1));
            }

            public final Iterator deserializeValues(final ObjectReader reader, final GenericType type)
            {
                final boolean isArray;
/* 457*/        if((isArray = reader.getValueType() == ValueType.ARRAY))
/* 459*/            reader.beginArray();
/* 462*/        return new Iterator() {

                    public boolean hasNext()
                    {
/* 468*/                boolean flag = reader.hasNext();
/* 469*/                if(isArray && !flag)
/* 469*/                    reader.endArray();
/* 470*/                return flag;
                    }

                    public Object next()
                    {
/* 475*/                if(!hasNext())
/* 475*/                    throw new NoSuchElementException();
/* 476*/                reader.next();
/* 478*/                return converter.deserialize(reader, ctx);
                        Exception exception;
/* 479*/                exception;
/* 480*/                throw new JsonBindingException((new StringBuilder("Could not deserialize to type ")).append(type.getRawClass()).toString(), exception);
                    }

                    public void remove()
                    {
/* 486*/                throw new UnsupportedOperationException();
                    }

                    final Converter converter;
                    final Context ctx;
                    final GenericType val$type;
                    final ObjectReader val$reader;
                    final boolean val$isArray;
                    final Genson this$0;

                    
                    {
/* 462*/                this$0 = Genson.this;
/* 462*/                type = generictype;
/* 462*/                reader = objectreader;
/* 462*/                isArray = flag;
/* 462*/                super();
/* 463*/                converter = provideConverter(type.getType());
/* 464*/                ctx = new Context(Genson.this);
                    }
        };
            }

            public final String aliasFor(Class class1)
            {
                String s;
/* 496*/        if((s = (String)classAliasMap.get(class1)) == null)
                {
/* 498*/            s = class1.getName();
/* 499*/            classAliasMap.put(class1, s);
                }
/* 501*/        return s;
            }

            public final Class classFor(String s)
                throws ClassNotFoundException
            {
                Class class1;
/* 514*/        if((class1 = (Class)aliasClassMap.get(s)) == null)
                {
/* 516*/            class1 = Class.forName(s);
/* 517*/            aliasClassMap.put(s, class1);
                }
/* 519*/        return class1;
            }

            public final ObjectWriter createWriter(OutputStream outputstream)
            {
/* 527*/        return new JsonWriter(new OutputStreamWriter(outputstream, UTF8_CHARSET), skipNull, htmlSafe, indent);
            }

            public final ObjectWriter createWriter(OutputStream outputstream, Charset charset)
            {
/* 534*/        return createWriter(((Writer) (new OutputStreamWriter(outputstream, charset))));
            }

            public final ObjectWriter createWriter(Writer writer)
            {
/* 541*/        return new JsonWriter(writer, skipNull, htmlSafe, indent);
            }

            public final ObjectReader createReader(byte abyte0[])
            {
/* 549*/        return createReader(readerFactory.createReader(new ByteArrayInputStream(abyte0)));
/* 550*/        abyte0;
/* 551*/        throw new JsonStreamException("Failed to detect encoding.", abyte0);
            }

            public final ObjectReader createReader(InputStream inputstream)
            {
/* 561*/        return createReader(readerFactory.createReader(inputstream));
/* 562*/        inputstream;
/* 563*/        throw new JsonStreamException("Failed to detect encoding.", inputstream);
            }

            public final ObjectReader createReader(InputStream inputstream, Charset charset)
            {
/* 571*/        return createReader(((Reader) (new InputStreamReader(inputstream, charset))));
            }

            public final ObjectReader createReader(Reader reader)
            {
/* 578*/        return new JsonReader(reader, strictDoubleParse, withMetadata);
            }

            public final boolean isSkipNull()
            {
/* 582*/        return skipNull;
            }

            public final boolean isHtmlSafe()
            {
/* 586*/        return htmlSafe;
            }

            public final boolean isWithClassMetadata()
            {
/* 590*/        return withClassMetadata;
            }

            public final BeanDescriptorProvider getBeanDescriptorProvider()
            {
/* 594*/        return beanDescriptorFactory;
            }

            public final boolean failOnMissingProperty()
            {
/* 598*/        return failOnMissingProperty;
            }

            public final Object defaultValue(Class class1)
            {
/* 605*/        return defaultValues.get(class1);
            }

            public final RuntimePropertyFilter runtimePropertyFilter()
            {
/* 609*/        return runtimePropertyFilter;
            }

            private static final Genson _default = (new GensonBuilder()).create();
            private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
            private final ConcurrentHashMap converterCache;
            private final Factory converterFactory;
            private final BeanDescriptorProvider beanDescriptorFactory;
            private final Map classAliasMap;
            private final Map aliasClassMap;
            private final boolean skipNull;
            private final boolean htmlSafe;
            private final boolean withClassMetadata;
            private final boolean withMetadata;
            private final boolean strictDoubleParse;
            private final boolean indent;
            private final boolean failOnMissingProperty;
            private final EncodingAwareReaderFactory readerFactory;
            private final Map defaultValues;
            private final RuntimePropertyFilter runtimePropertyFilter;

}
