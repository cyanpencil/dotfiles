// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultConverters.java

package com.owlike.genson.convert;

import com.owlike.genson.Converter;
import java.util.Collection;
import java.util.EnumSet;

// Referenced classes of package com.owlike.genson.convert:
//            DefaultConverters

public static class eClass extends er
{

            protected Collection create()
            {
/* 155*/        return EnumSet.noneOf(eClass);
            }

            private final Class eClass;

            public er(Class class1, Converter converter)
            {
/* 148*/        super(class1, converter);
/* 149*/        eClass = class1;
            }
}
