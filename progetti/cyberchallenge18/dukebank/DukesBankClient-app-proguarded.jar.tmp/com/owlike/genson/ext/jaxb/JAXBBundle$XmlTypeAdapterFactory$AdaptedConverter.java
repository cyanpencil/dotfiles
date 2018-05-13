// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JAXBBundle.java

package com.owlike.genson.ext.jaxb;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

// Referenced classes of package com.owlike.genson.ext.jaxb:
//            JAXBBundle

class converter
    implements Converter
{

            public Object deserialize(ObjectReader objectreader, Context context)
                throws Exception
            {
/* 196*/        objectreader = ((ObjectReader) (converter.deserialize(objectreader, context)));
/* 198*/        return adapter.unmarshal(objectreader);
/* 199*/        JVM INSTR pop ;
/* 200*/        throw new JsonBindingException((new StringBuilder("Could not unmarshal object using adapter ")).append(adapter.getClass()).toString());
            }

            public void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 209*/        try
                {
/* 209*/            obj = adapter.marshal(obj);
                }
/* 210*/        catch(Exception _ex)
                {
/* 211*/            throw new JsonBindingException((new StringBuilder("Could not marshal object using adapter ")).append(adapter.getClass()).toString());
                }
/* 214*/        converter.serialize(obj, objectwriter, context);
            }

            private final XmlAdapter adapter;
            private final Converter converter;
            final converter this$1;

            public (XmlAdapter xmladapter, Converter converter1)
            {
/* 188*/        this$1 = this._cls1.this;
/* 189*/        super();
/* 190*/        adapter = xmladapter;
/* 191*/        converter = converter1;
            }
}
