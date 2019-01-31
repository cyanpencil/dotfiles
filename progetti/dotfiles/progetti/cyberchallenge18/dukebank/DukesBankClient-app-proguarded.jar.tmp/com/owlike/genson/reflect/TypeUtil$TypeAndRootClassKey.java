// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeUtil.java

package com.owlike.genson.reflect;

import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            TypeUtil

static final class _hash
{

            public final int hashCode()
            {
/* 585*/        return _hash;
            }

            public final boolean equals(Object obj)
            {
/* 590*/        if(this == obj)
/* 591*/            return true;
/* 592*/        if(obj == null)
/* 593*/            return false;
/* 594*/        if(!(obj instanceof _hash))
/* 595*/            return false;
/* 596*/        obj = (_hash)obj;
/* 597*/        return rootType.equals(((rootType) (obj)).rootType) && type.equals(((type) (obj)).type);
            }

            private final Type type;
            private final Type rootType;
            private int _hash;

            public (Type type1, Type type2)
            {
/* 575*/        if(type1 == null || type2 == null)
                {
/* 576*/            throw new IllegalArgumentException("type and rootType must be not null!");
                } else
                {
/* 577*/            type = type1;
/* 578*/            rootType = type2;
/* 579*/            _hash = 31 + type2.hashCode();
/* 580*/            _hash = 31 + type1.hashCode();
/* 581*/            return;
                }
            }
}
