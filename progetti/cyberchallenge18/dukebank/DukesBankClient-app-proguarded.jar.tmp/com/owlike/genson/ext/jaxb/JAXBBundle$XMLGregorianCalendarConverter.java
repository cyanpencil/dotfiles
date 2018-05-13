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
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class dateFormat
    implements Converter
{

            public void serialize(XMLGregorianCalendar xmlgregoriancalendar, ObjectWriter objectwriter, Context context)
            {
/* 117*/        converter.(xmlgregoriancalendar.toGregorianCalendar().getTime(), objectwriter, context);
            }

            public synchronized XMLGregorianCalendar deserialize(ObjectReader objectreader, Context context)
            {
/* 122*/        context = new GregorianCalendar();
/* 124*/        try
                {
/* 124*/            context.setTime(dateFormat.parse(objectreader.valueAsString()));
                }
                // Misplaced declaration of an exception variable
/* 125*/        catch(Context context)
                {
/* 126*/            throw new JsonBindingException((new StringBuilder("Could not parse date ")).append(objectreader.valueAsString()).toString(), context);
                }
/* 130*/        return JAXBBundle.access$800(JAXBBundle.this).newXMLGregorianCalendar(context);
            }

            public volatile Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 109*/        return deserialize(objectreader, context);
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 109*/        serialize((XMLGregorianCalendar)obj, objectwriter, context);
            }

            private final com.owlike.genson.convert..serialize converter;
            private final SimpleDateFormat dateFormat;
            final JAXBBundle this$0;

            private r()
            {
/* 111*/        this$0 = JAXBBundle.this;
/* 111*/        super();
/* 112*/        converter = new com.owlike.genson.convert..converter();
/* 113*/        dateFormat = new SimpleDateFormat("yyyy-MM-DD'T'hh:mm:ssZ");
            }


            // Unreferenced inner class com/owlike/genson/ext/jaxb/JAXBBundle$1

/* anonymous class */
    class JAXBBundle._cls1 extends ChainedFactory
    {

                protected Converter create(Type type, Genson genson, Converter converter1)
                {
/*  81*/            if((genson = (XmlRootElement)(type = TypeUtil.getRawClass(type)).getAnnotation(javax/xml/bind/annotation/XmlRootElement)) != null)
                    {
/*  85*/                type = "##default".equals(genson.name()) ? ((Type) (JAXBBundle.access$700(JAXBBundle.this, type.getSimpleName()))) : ((Type) (genson.name()));
/*  86*/                return new com.owlike.genson.convert.DefaultConverters.WrappedRootValueConverter(type, type, converter1);
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
