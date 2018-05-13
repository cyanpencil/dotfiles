// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicConvertersFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

// Referenced classes of package com.owlike.genson.convert:
//            BasicConvertersFactory

class deserializer extends Wrapper
    implements Converter
{

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 118*/        serializer.serialize(obj, objectwriter, context);
            }

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 122*/        return deserializer.deserialize(objectreader, context);
            }

            public Annotation getAnnotation(Class class1)
            {
/* 127*/        Annotation annotation = null;
/* 128*/        if(serializer != null)
/* 128*/            annotation = toAnnotatedElement(serializer).getAnnotation(class1);
/* 129*/        if(deserializer != null && annotation == null)
/* 130*/            annotation = toAnnotatedElement(deserializer).getAnnotation(class1);
/* 131*/        return annotation;
            }

            public Annotation[] getAnnotations()
            {
/* 136*/        if(serializer != null && deserializer != null)
/* 137*/            return (Annotation[])Operations.union([Ljava/lang/annotation/Annotation;, new Annotation[][] {
/* 137*/                toAnnotatedElement(serializer).getAnnotations(), toAnnotatedElement(deserializer).getAnnotations()
                    });
/* 139*/        if(serializer != null)
/* 139*/            return toAnnotatedElement(serializer).getAnnotations();
/* 140*/        if(deserializer != null)
/* 140*/            return toAnnotatedElement(deserializer).getAnnotations();
/* 142*/        else
/* 142*/            return new Annotation[0];
            }

            public Annotation[] getDeclaredAnnotations()
            {
/* 147*/        if(serializer != null && deserializer != null)
/* 148*/            return (Annotation[])Operations.union([Ljava/lang/annotation/Annotation;, new Annotation[][] {
/* 148*/                toAnnotatedElement(serializer).getDeclaredAnnotations(), toAnnotatedElement(deserializer).getDeclaredAnnotations()
                    });
/* 151*/        if(serializer != null)
/* 151*/            return toAnnotatedElement(serializer).getDeclaredAnnotations();
/* 152*/        if(deserializer != null)
/* 153*/            return toAnnotatedElement(deserializer).getDeclaredAnnotations();
/* 155*/        else
/* 155*/            return new Annotation[0];
            }

            public boolean isAnnotationPresent(Class class1)
            {
/* 160*/        if(serializer != null)
/* 161*/            return toAnnotatedElement(serializer).isAnnotationPresent(class1);
/* 162*/        if(deserializer != null)
/* 163*/            return toAnnotatedElement(deserializer).isAnnotationPresent(class1);
/* 164*/        else
/* 164*/            return false;
            }

            private final Serializer serializer;
            private final Deserializer deserializer;
            final BasicConvertersFactory this$0;

            public I(Serializer serializer1, Deserializer deserializer1)
            {
/* 112*/        this$0 = BasicConvertersFactory.this;
/* 112*/        super();
/* 113*/        serializer = serializer1;
/* 114*/        deserializer = deserializer1;
            }
}
