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

public static final class caseSensitive
    implements Factory
{

            public final Converter create(Type type, Genson genson)
            {
/*1059*/        if((type = TypeUtil.getRawClass(type)).isEnum() || java/lang/Enum.isAssignableFrom(type))
/*1060*/            return new caseSensitive(type, caseSensitive);
/*1060*/        else
/*1060*/            return null;
            }

            public final volatile Object create(Type type, Genson genson)
            {
/*1049*/        return create(type, genson);
            }

            public static final create instance = new <init>(true);
            public final boolean caseSensitive;


            public (boolean flag)
            {
/*1054*/        caseSensitive = flag;
            }
}
