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

public static final class er
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
/* 864*/        Type type1 = type;
/* 865*/        if(TypeUtil.getRawClass(type).getTypeParameters().length == 0)
/* 866*/            type1 = TypeUtil.expandType(TypeUtil.lookupGenericType(java/util/Map, TypeUtil.getRawClass(type)), type);
/* 869*/        Type type2 = TypeUtil.typeOf(0, type1);
/* 870*/        type1 = TypeUtil.typeOf(1, type1);
                Object obj;
/* 871*/        if((obj = keyAdapter(((Class) (obj = TypeUtil.getRawClass(type2))))) != null)
/* 875*/            return createConverter(TypeUtil.getRawClass(type), ((createConverter) (obj)), genson.provideConverter(type1));
/* 877*/        else
/* 877*/            return new <init>(genson.provideConverter(type2), genson.provideConverter(type1), null);
            }

            public static <init> keyAdapter(Class class1)
            {
/* 881*/        if(java/lang/Object.equals(class1))
/* 881*/            return apter;
/* 882*/        if(java/lang/String.equals(class1))
/* 882*/            return r;
/* 883*/        if(Integer.TYPE.equals(class1) || java/lang/Integer.equals(class1))
/* 884*/            return r;
/* 885*/        if(Double.TYPE.equals(class1) || java/lang/Double.equals(class1))
/* 886*/            return pter;
/* 887*/        if(Long.TYPE.equals(class1) || java/lang/Long.equals(class1))
/* 888*/            return er;
/* 889*/        if(Float.TYPE.equals(class1) || java/lang/Float.equals(class1))
/* 890*/            return ter;
/* 891*/        if(Short.TYPE.equals(class1) || java/lang/Short.equals(class1))
/* 892*/            return ter;
/* 893*/        else
/* 893*/            return null;
            }

            private ter createConverter(Class class1, ter ter, Converter converter)
            {
/* 899*/        if(java/util/Properties.equals(class1))
/* 900*/            return new <init>(ter, converter);
/* 902*/        if(java/util/Hashtable.equals(class1))
/* 903*/            return new init>(ter, converter);
/* 905*/        if(java/util/TreeMap.equals(class1))
/* 906*/            return new it>(ter, converter);
/* 908*/        if(java/util/LinkedHashMap.equals(class1))
/* 909*/            return new er(ter, converter);
/* 911*/        else
/* 911*/            return new it>(ter, converter);
            }

            public final volatile Object create(Type type, Genson genson)
            {
/* 854*/        return create(type, genson);
            }

            public static final create instance = new <init>();


            private er()
            {
            }
}
