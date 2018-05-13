// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeUtil.java

package com.owlike.genson.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            TypeUtil

static final class _hash extends _hash
    implements GenericArrayType
{

            public final Type getGenericComponentType()
            {
/* 480*/        return componentType;
            }

            public final int hashCode()
            {
/* 485*/        return _hash;
            }

            public final boolean equals(Object obj)
            {
/* 490*/        if(this == obj)
/* 491*/            return true;
/* 492*/        if(!super._hash(obj))
/* 493*/            return false;
/* 494*/        if(getClass() != obj.getClass())
/* 495*/            return false;
/* 496*/        obj = (_hash)obj;
/* 497*/        if(componentType == null)
                {
/* 498*/            if(((componentType) (obj)).componentType != null)
/* 499*/                return false;
                } else
/* 500*/        if(!componentType.equals(((componentType) (obj)).componentType))
/* 501*/            return false;
/* 502*/        return true;
            }

            private final Type componentType;
            private final int _hash;

            public A(GenericArrayType genericarraytype, Type type, Class class1)
            {
/* 469*/        super(genericarraytype, class1, null);
/* 470*/        if(type == null)
                {
/* 471*/            throw new IllegalArgumentException("Null arg not allowed!");
                } else
                {
/* 472*/            componentType = type;
/* 475*/            genericarraytype = super.componentType();
/* 476*/            _hash = genericarraytype * 31 + (type != null ? type.hashCode() : 0);
/* 477*/            return;
                }
            }
}
