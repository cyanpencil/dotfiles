// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectWriter;
import java.util.Collection;
import java.util.PriorityQueue;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class it> extends it>
{

            public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                throws Exception
            {
                PriorityQueue priorityqueue;
/* 129*/        if((priorityqueue = (PriorityQueue)collection).comparator() != null)
                {
/* 131*/            throw new UnsupportedOperationException("Serialization and deserialization of PriorityQueue with Comparator is not supported. You need to implement a custom Converter to handle it.");
                } else
                {
/* 134*/            super.ialize(collection, objectwriter, context);
/* 135*/            return;
                }
            }

            protected Collection create()
            {
/* 139*/        return new PriorityQueue();
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 120*/        serialize((Collection)obj, objectwriter, context);
            }

            public (Class class1, Converter converter)
            {
/* 124*/        super(class1, converter);
            }
}
