// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CircularClassReferenceConverterFactory.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.owlike.genson.convert:
//            ChainedFactory

public class CircularClassReferenceConverterFactory extends ChainedFactory
{
    static final class CircularConverter extends Wrapper
        implements Converter
    {

                public final void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*  26*/            ((Converter)wrapped).serialize(obj, objectwriter, context);
                }

                public final Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*  30*/            return ((Converter)wrapped).deserialize(objectreader, context);
                }

                final void setDelegateConverter(Converter converter)
                {
/*  34*/            decorate(converter);
                }

                protected CircularConverter()
                {
                }
    }


            public CircularClassReferenceConverterFactory()
            {
            }

            public Converter create(Type type, Genson genson)
            {
                Map map;
/*  43*/        if((map = (Map)_circularConverters.get()) == null)
                {
/*  45*/            HashMap hashmap = new HashMap();
/*  46*/            _circularConverters.set(hashmap);
                }
/*  49*/        if(((Map)_circularConverters.get()).containsKey(type))
/*  50*/            return (Converter)((Map)_circularConverters.get()).get(type);
/*  53*/        CircularConverter circularconverter = new CircularConverter();
/*  54*/        ((Map)_circularConverters.get()).put(type, circularconverter);
/*  55*/        genson = (Converter)next().create(type, genson);
/*  56*/        circularconverter.setDelegateConverter(genson);
/*  57*/        genson = genson;
/*  59*/        ((Map)_circularConverters.get()).remove(type);
/*  59*/        return genson;
/*  59*/        genson;
/*  59*/        ((Map)_circularConverters.get()).remove(type);
/*  59*/        throw genson;
            }

            protected Converter create(Type type, Genson genson, Converter converter)
            {
/*  66*/        throw new UnsupportedOperationException();
            }

            public volatile Object create(Type type, Genson genson)
            {
/*  19*/        return create(type, genson);
            }

            private final ThreadLocal _circularConverters = new ThreadLocal();
}
