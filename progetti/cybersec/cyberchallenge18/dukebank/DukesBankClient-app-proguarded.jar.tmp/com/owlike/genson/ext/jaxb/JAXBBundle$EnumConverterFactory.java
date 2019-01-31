// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.*;
import com.owlike.genson.convert.ChainedFactory;
import com.owlike.genson.convert.DefaultConverters;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class this._cls0
    implements Factory
{
    class EnumConverter
        implements Converter
    {

                public void serialize(Enum enum, ObjectWriter objectwriter, Context context)
                {
/* 270*/            objectwriter.writeUnsafeValue((String)enumToValue.get(enum));
                }

                public Enum deserialize(ObjectReader objectreader, Context context)
                {
/* 275*/            return (Enum)valueToEnum.get(objectreader.valueAsString());
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 256*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 256*/            serialize((Enum)obj, objectwriter, context);
                }

                private final Map valueToEnum;
                private final Map enumToValue;
                final JAXBBundle.EnumConverterFactory this$1;

                public EnumConverter(Map map, Map map1)
                {
/* 262*/            this$1 = JAXBBundle.EnumConverterFactory.this;
/* 263*/            super();
/* 264*/            valueToEnum = map;
/* 265*/            enumToValue = map1;
                }
    }


            public Converter create(Type type, Genson genson)
            {
/* 223*/        if(!(type = TypeUtil.getRawClass(type)).isEnum() && !java/lang/Enum.isAssignableFrom(type))
/* 226*/            break MISSING_BLOCK_LABEL_203;
/* 226*/        genson = type;
                HashMap hashmap;
                HashMap hashmap1;
/* 229*/        hashmap = new HashMap();
/* 230*/        hashmap1 = new HashMap();
                Enum aenum[];
/* 231*/        int i = (aenum = (Enum[])genson.getEnumConstants()).length;
/* 231*/        for(int j = 0; j < i; j++)
                {
/* 231*/            Enum enum = aenum[j];
                    XmlEnumValue xmlenumvalue;
/* 232*/            if((xmlenumvalue = (XmlEnumValue)type.getField(enum.name()).getAnnotation(javax/xml/bind/annotation/XmlEnumValue)) != null)
                    {
/* 236*/                hashmap.put(xmlenumvalue.value(), enum);
/* 237*/                hashmap1.put(enum, xmlenumvalue.value());
                    } else
                    {
/* 239*/                hashmap.put(enum.name(), enum);
/* 240*/                hashmap1.put(enum, enum.name());
                    }
                }

/* 244*/        return new EnumConverter(hashmap, hashmap1);
                SecurityException securityexception;
/* 245*/        securityexception;
/* 246*/        throw new JsonBindingException((new StringBuilder("Unable to introspect enum ")).append(genson).toString(), securityexception);
/* 248*/        JVM INSTR pop ;
/* 253*/        return null;
            }

            public volatile Object create(Type type, Genson genson)
            {
/* 219*/        return create(type, genson);
            }

            final JAXBBundle this$0;

            private eConverter()
            {
/* 219*/        this$0 = JAXBBundle.this;
/* 219*/        super();
            }


            // Unreferenced inner class com/owlike/genson/ext/jaxb/JAXBBundle$1

/* anonymous class */
    class JAXBBundle._cls1 extends ChainedFactory
    {

                protected Converter create(Type type, Genson genson, Converter converter)
                {
/*  81*/            if((genson = (XmlRootElement)(type = TypeUtil.getRawClass(type)).getAnnotation(javax/xml/bind/annotation/XmlRootElement)) != null)
                    {
/*  85*/                type = "##default".equals(genson.name()) ? ((Type) (JAXBBundle.access$700(JAXBBundle.this, type.getSimpleName()))) : ((Type) (genson.name()));
/*  86*/                return new com.owlike.genson.convert.DefaultConverters.WrappedRootValueConverter(type, type, converter);
                    } else
                    {
/*  88*/                return null;
                    }
                }

                final JAXBBundle this$0;

                    
                    {
/*  78*/                this$0 = JAXBBundle.this;
/*  78*/                super();
                    }
    }

}
