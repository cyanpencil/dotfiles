// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClassMetadataConverter.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.annotation.HandleClassMetadata;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory, ClassMetadataConverter

public static class classMetadataWithStaticType extends ChainedFactory
{

            protected Converter create(Type type, Genson genson, Converter converter)
            {
/*  52*/        if(converter == null)
/*  53*/            throw new IllegalArgumentException("nextConverter must be not null for ClassMetadataConverter, as ClassMetadataConverter can not be the last converter in the chain!");
/*  57*/        type = TypeUtil.getRawClass(type);
/*  58*/        if(genson.isWithClassMetadata() && !Wrapper.toAnnotatedElement(converter).isAnnotationPresent(com/owlike/genson/annotation/HandleClassMetadata))
/*  60*/            return new ClassMetadataConverter(type, converter, classMetadataWithStaticType);
/*  62*/        else
/*  62*/            return converter;
            }

            private final boolean classMetadataWithStaticType;

            public (boolean flag)
            {
/*  46*/        classMetadataWithStaticType = flag;
            }
}
