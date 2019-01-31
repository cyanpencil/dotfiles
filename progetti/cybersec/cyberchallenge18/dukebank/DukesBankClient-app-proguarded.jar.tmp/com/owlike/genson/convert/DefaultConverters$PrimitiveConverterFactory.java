// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static final class 
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
/* 605*/        if((type = TypeUtil.getRawClass(type)) == java/lang/Boolean || type == Boolean.TYPE)
/* 607*/            return ;
/* 608*/        if(type == java/lang/Integer || type == Integer.TYPE)
/* 608*/            return ;
/* 609*/        if(type == java/lang/Double || type == Double.TYPE)
/* 609*/            return ;
/* 610*/        if(type == java/lang/Long || type == Long.TYPE)
/* 610*/            return ;
/* 611*/        if(type == java/lang/Short || type == Short.TYPE)
/* 611*/            return ;
/* 612*/        if(type == java/lang/Float || type == Float.TYPE)
/* 612*/            return ;
/* 613*/        if(type == java/lang/Character || type == Character.TYPE)
/* 613*/            return ;
/* 614*/        if(type == java/lang/Byte || type == Byte.TYPE)
/* 614*/            return ;
/* 616*/        else
/* 616*/            return null;
            }

            public final volatile Object create(Type type, Genson genson)
            {
/* 598*/        return create(type, genson);
            }

            public static final create instance = new <init>();


            private ()
            {
            }
}
