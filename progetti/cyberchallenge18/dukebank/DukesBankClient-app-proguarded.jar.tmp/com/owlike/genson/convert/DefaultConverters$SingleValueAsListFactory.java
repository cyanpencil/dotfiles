// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.stream.*;
import java.lang.reflect.Type;
import java.util.Collection;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class y.instance
    implements Factory
{

            public final Converter create(final Type defaultConverter, Genson genson)
            {
/* 209*/        defaultConverter = (y.instance)defaultFactory.create(defaultConverter, genson);
/* 211*/        return new Converter() {

                    public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/* 214*/                defaultConverter.serialize(collection, objectwriter, context);
                    }

                    public Collection deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
                        ValueType valuetype;
/* 219*/                if((valuetype = objectreader.getValueType()) != ValueType.ARRAY && valuetype != ValueType.NULL)
                        {
/* 221*/                    objectreader = ((ObjectReader) (defaultConverter.getElementConverter().deserialize(objectreader, context)));
/* 222*/                    (context = defaultConverter.create()).add(objectreader);
/* 224*/                    return context;
                        } else
                        {
/* 225*/                    return defaultConverter.deserialize(objectreader, context);
                        }
                    }

                    public volatile Object deserialize(ObjectReader objectreader, Context context)
                        throws Exception
                    {
/* 211*/                return deserialize(objectreader, context);
                    }

                    public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                        throws Exception
                    {
/* 211*/                serialize((Collection)obj, objectwriter, context);
                    }

                    final DefaultConverters.CollectionConverter val$defaultConverter;
                    final DefaultConverters.SingleValueAsListFactory this$0;

                    
                    {
/* 211*/                this$0 = DefaultConverters.SingleValueAsListFactory.this;
/* 211*/                defaultConverter = collectionconverter;
/* 211*/                super();
                    }
        };
            }

            public final volatile Object create(Type type, Genson genson)
            {
/* 200*/        return create(type, genson);
            }

            public static final create instance = new <init>();
            Factory defaultFactory;


            private _cls1.val.defaultConverter()
            {
/* 203*/        defaultFactory = y.instance;
            }
}
