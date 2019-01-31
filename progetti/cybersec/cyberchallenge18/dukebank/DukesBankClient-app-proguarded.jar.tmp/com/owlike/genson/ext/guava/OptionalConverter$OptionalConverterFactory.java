// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OptionalConverter.java

package com.owlike.genson.ext.guava;

import com.owlike.genson.*;
import com.owlike.genson.reflect.TypeUtil;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.ext.guava:
//            OptionalConverter

static class 
    implements Factory
{

            public Converter create(Type type, Genson genson)
            {
/*  20*/        type = TypeUtil.typeOf(0, type);
/*  22*/        return new OptionalConverter(genson.provideConverter(type));
            }

            public volatile Object create(Type type, Genson genson)
            {
/*  16*/        return create(type, genson);
            }

            ()
            {
            }
}
