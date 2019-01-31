// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassMetadataConverter.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.annotation.HandleClassMetadata;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.*;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters, ChainedFactory

public class ClassMetadataConverter extends Wrapper
    implements Converter
{
    public static class ClassMetadataConverterFactory extends ChainedFactory
    {

                protected Converter create(Type type, Genson genson, Converter converter)
                {
/*  52*/            if(converter == null)
/*  53*/                throw new IllegalArgumentException("nextConverter must be not null for ClassMetadataConverter, as ClassMetadataConverter can not be the last converter in the chain!");
/*  57*/            type = TypeUtil.getRawClass(type);
/*  58*/            if(genson.isWithClassMetadata() && !Wrapper.toAnnotatedElement(converter).isAnnotationPresent(com/owlike/genson/annotation/HandleClassMetadata))
/*  60*/                return new ClassMetadataConverter(type, converter, classMetadataWithStaticType);
/*  62*/            else
/*  62*/                return converter;
                }

                private final boolean classMetadataWithStaticType;

                public ClassMetadataConverterFactory(boolean flag)
                {
/*  46*/            classMetadataWithStaticType = flag;
                }
    }


            public ClassMetadataConverter(Class class1, Converter converter, boolean flag)
            {
/*  71*/        super(converter);
/*  72*/        tClass = class1;
/*  73*/        classMetadataWithStaticType = flag;
/*  74*/        skipMetadataSerialization = Wrapper.isOfType(converter, com/owlike/genson/convert/DefaultConverters$UntypedConverterFactory$UntypedConverter);
            }

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  78*/        if(!skipMetadataSerialization && obj != null && (classMetadataWithStaticType || !classMetadataWithStaticType && !tClass.equals(obj.getClass())))
/*  80*/            objectwriter.beginNextObjectMetadata().writeMetadata("class", context.genson.aliasFor(obj.getClass()));
/*  83*/        ((Converter)wrapped).serialize(obj, objectwriter, context);
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
                String s;
/*  87*/        if(!ValueType.OBJECT.equals(objectreader.getValueType()) || (s = objectreader.nextObjectMetadata().metadata("class")) == null)
/*  91*/            break MISSING_BLOCK_LABEL_102;
                Object obj;
/*  91*/        if(!(obj = context.genson.classFor(s)).equals(tClass))
/*  93*/            return ((Converter) (obj = context.genson.provideConverter(((Type) (obj))))).deserialize(objectreader, context);
/*  99*/        break MISSING_BLOCK_LABEL_102;
/*  96*/        JVM INSTR pop ;
/*  97*/        throw new JsonBindingException((new StringBuilder("Could not use @class metadata, no such class: ")).append(s).toString());
/* 102*/        return ((Converter)wrapped).deserialize(objectreader, context);
            }

            private final boolean classMetadataWithStaticType;
            private final Class tClass;
            private final boolean skipMetadataSerialization;
}
