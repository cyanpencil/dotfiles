// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectWriter;
import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class t> extends t>
{

            public final void serialize(Map map, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 696*/        if(((TreeMap)map).comparator() != null)
                {
/* 697*/            throw new UnsupportedOperationException("Serialization and deserialization of TreeMap with Comparator is not supported. You need to implement a custom Converter to handle it.");
                } else
                {
/* 700*/            super.alize(map, objectwriter, context);
/* 701*/            return;
                }
            }

            protected final Map create()
            {
/* 705*/        return new TreeMap();
            }

            public final volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                throws Exception
            {
/* 688*/        serialize((Map)obj, objectwriter, context);
            }

            public ( , Converter converter)
            {
/* 690*/        super(, converter);
            }
}
