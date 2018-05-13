// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.*;
import com.owlike.genson.convert.*;
import com.owlike.genson.reflect.BeanProperty;
import com.owlike.genson.reflect.TypeUtil;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Type;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class this._cls0
    implements ContextualFactory
{
    class AdaptedConverter
        implements Converter
    {

                public Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/* 196*/            objectreader = ((ObjectReader) (converter.deserialize(objectreader, context)));
/* 198*/            return adapter.unmarshal(objectreader);
/* 199*/            JVM INSTR pop ;
/* 200*/            throw new JsonBindingException((new StringBuilder("Could not unmarshal object using adapter ")).append(adapter.getClass()).toString());
                }

                public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/* 209*/            try
                    {
/* 209*/                obj = adapter.marshal(obj);
                    }
/* 210*/            catch(Exception _ex)
                    {
/* 211*/                throw new JsonBindingException((new StringBuilder("Could not marshal object using adapter ")).append(adapter.getClass()).toString());
                    }
/* 214*/            converter.serialize(obj, objectwriter, context);
                }

                private final XmlAdapter adapter;
                private final Converter converter;
                final JAXBBundle.XmlTypeAdapterFactory this$1;

                public AdaptedConverter(XmlAdapter xmladapter, Converter converter1)
                {
/* 188*/            this$1 = JAXBBundle.XmlTypeAdapterFactory.this;
/* 189*/            super();
/* 190*/            adapter = xmladapter;
/* 191*/            converter = converter1;
                }
    }


            public Converter create(BeanProperty beanproperty, Genson genson)
            {
/* 138*/        Object obj = (XmlJavaTypeAdapter)beanproperty.getAnnotation(javax/xml/bind/annotation/adapters/XmlJavaTypeAdapter);
/* 139*/        Object obj1 = null;
/* 141*/        if(obj != null)
                {
/* 142*/            obj = ((XmlJavaTypeAdapter) (obj)).value();
/* 143*/            obj1 = TypeUtil.expandType(TypeUtil.lookupGenericType(javax/xml/bind/annotation/adapters/XmlAdapter, ((Class) (obj))), ((Type) (obj)));
/* 145*/            Type type = TypeUtil.typeOf(0, ((Type) (obj1)));
/* 146*/            obj1 = TypeUtil.typeOf(1, ((Type) (obj1)));
                    Object obj2;
/* 147*/            if(TypeUtil.getRawClass(((Type) (obj2 = beanproperty.getType()))).isPrimitive())
/* 150*/                obj2 = TypeUtil.wrap(TypeUtil.getRawClass(((Type) (obj2))));
                    Object obj3;
/* 152*/            if((obj3 = (obj3 = (XmlElement)beanproperty.getAnnotation(javax/xml/bind/annotation/XmlElement)) == null || ((XmlElement) (obj3)).type() == javax/xml/bind/annotation/XmlElement$DEFAULT ? null : ((Object) (((XmlElement) (obj3)).type()))) != null)
                    {
/* 157*/                if(!TypeUtil.match(type, ((Type) (obj3)), false))
/* 158*/                    throw new ClassCastException((new StringBuilder("The BoundType of XMLAdapter ")).append(obj).append(" is not assignable from property ").append(beanproperty.getName()).append(" declared in ").append(beanproperty.getDeclaringClass()).toString());
                    } else
/* 162*/            if(!TypeUtil.match(((Type) (obj2)), ((Type) (obj1)), false))
/* 163*/                throw new ClassCastException((new StringBuilder("The BoundType of XMLAdapter ")).append(obj).append(" is not assignable from property ").append(beanproperty.getName()).append(" declared in ").append(beanproperty.getDeclaringClass()).toString());
/* 168*/            try
                    {
/* 168*/                beanproperty = (XmlAdapter)((Class) (obj)).newInstance();
/* 170*/                genson = genson.provideConverter(((Type) (obj3 == null ? type : ((Type) (obj3)))));
/* 172*/                obj1 = new AdaptedConverter(beanproperty, genson);
                    }
                    // Misplaced declaration of an exception variable
/* 173*/            catch(BeanProperty beanproperty)
                    {
/* 174*/                throw new JsonBindingException((new StringBuilder("Could not instantiate XmlAdapter of type ")).append(obj).toString(), beanproperty);
                    }
                    // Misplaced declaration of an exception variable
/* 176*/            catch(BeanProperty beanproperty)
                    {
/* 177*/                throw new JsonBindingException((new StringBuilder("Could not instantiate XmlAdapter of type ")).append(obj).toString(), beanproperty);
                    }
                }
/* 181*/        return ((Converter) (obj1));
            }

            final JAXBBundle this$0;

            private Converter()
            {
/* 135*/        this$0 = JAXBBundle.this;
/* 135*/        super();
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
