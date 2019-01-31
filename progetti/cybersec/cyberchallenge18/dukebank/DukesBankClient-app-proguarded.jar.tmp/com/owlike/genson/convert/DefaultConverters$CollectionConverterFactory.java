// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.Type;
import java.util.*;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class 
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
/* 239*/        genson = genson.provideConverter(TypeUtil.getCollectionType(type));
/* 241*/        Class class1 = TypeUtil.getRawClass(TypeUtil.getCollectionType(type));
/* 242*/        type = TypeUtil.getRawClass(type);
/* 244*/        if(java/util/EnumSet.isAssignableFrom(type) && class1.isEnum())
/* 245*/            return new (class1, genson);
/* 246*/        if(java/util/LinkedHashSet.isAssignableFrom(type))
/* 247*/            return new t>(class1, genson);
/* 248*/        if(java/util/TreeSet.isAssignableFrom(type))
/* 249*/            return new t>(class1, genson);
/* 250*/        if(java/util/Set.isAssignableFrom(type))
/* 251*/            return new t>(class1, genson);
/* 252*/        if(java/util/LinkedList.isAssignableFrom(type))
/* 253*/            return new t>(class1, genson);
/* 254*/        if(java/util/ArrayDeque.isAssignableFrom(type))
/* 255*/            return new t>(class1, genson);
/* 256*/        if(java/util/PriorityQueue.isAssignableFrom(type))
/* 257*/            return new t>(class1, genson);
/* 259*/        else
/* 259*/            return new t>(class1, genson);
            }

            public final volatile Object create(Type type, Genson genson)
            {
/* 231*/        return create(type, genson);
            }

            public static final create instance = new <init>();


            private ()
            {
            }
}
