// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeUtil.java

package com.owlike.genson.reflect;

import java.lang.reflect.Type;

// Referenced classes of package com.owlike.genson.reflect:
//            TypeUtil

static abstract class <init>
{

            public Type getOriginalType()
            {
/* 428*/        return originalType;
            }

            public Class getRootClass()
            {
/* 433*/        return rootClass;
            }

            public int hashCode()
            {
/* 438*/        return _hash;
            }

            public boolean equals(Object obj)
            {
/* 443*/        if(this == obj)
/* 444*/            return true;
/* 445*/        if(obj == null)
/* 446*/            return false;
/* 447*/        if(getClass() != obj.getClass())
/* 448*/            return false;
/* 450*/        obj = (_hash)obj;
/* 451*/        if(originalType == null)
                {
/* 452*/            if(((originalType) (obj)).originalType != null)
/* 453*/                return false;
                } else
/* 454*/        if(!originalType.equals(((originalType) (obj)).originalType))
/* 455*/            return false;
/* 456*/        return true;
            }

            protected final Type originalType;
            protected final Class rootClass;
            private final int _hash;

            private (Type type, Class class1)
            {
/* 416*/        if(type == null || class1 == null)
                {
/* 417*/            throw new IllegalArgumentException("Null arg not allowed!");
                } else
                {
/* 418*/            originalType = type;
/* 419*/            rootClass = class1;
/* 423*/            _hash = 31 + (type != null ? type.hashCode() : 0);
/* 424*/            return;
                }
            }

            _hash(Type type, Class class1, _hash _phash)
            {
/* 410*/        this(type, class1);
            }
}
