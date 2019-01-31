// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectWriter;
import java.util.Collection;
import java.util.TreeSet;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class er extends er
{

            public void serialize(Collection collection, ObjectWriter objectwriter, Context context)
                throws Exception
            {
                TreeSet treeset;
/*  80*/        if((treeset = (TreeSet)collection).comparator() != null)
                {
/*  82*/            throw new UnsupportedOperationException("Serialization and deserialization of TreeSet with Comparator is not supported. You need to implement a custom Converter to handle it.");
                } else
                {
/*  85*/            super.serialize(collection, objectwriter, context);
/*  86*/            return;
                }
            }

            protected Collection create()
            {
/*  90*/        return new TreeSet();
            }

            public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/*  71*/        serialize((Collection)obj, objectwriter, context);
            }

            public er(Class class1, Converter converter)
            {
/*  75*/        super(class1, converter);
            }
}
