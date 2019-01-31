// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TypeUtil.java

package com.owlike.genson.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

// Referenced classes of package com.owlike.genson.reflect:
//            TypeUtil

static final class _hash extends _hash
    implements ParameterizedType
{

            public final Type[] getActualTypeArguments()
            {
/* 526*/        return typeArgs;
            }

            public final Type getOwnerType()
            {
/* 530*/        return ((ParameterizedType)originalType).getOwnerType();
            }

            public final Type getRawType()
            {
/* 534*/        return ((ParameterizedType)originalType).getRawType();
            }

            public final int hashCode()
            {
/* 539*/        return _hash;
            }

            public final boolean equals(Object obj)
            {
/* 544*/        if(this == obj)
/* 545*/            return true;
/* 546*/        if(!super._hash(obj))
/* 547*/            return false;
/* 548*/        if(getClass() != obj.getClass())
/* 549*/            return false;
/* 550*/        obj = (_hash)obj;
/* 551*/        return Arrays.equals(typeArgs, ((typeArgs) (obj)).typeArgs);
            }

            private final Type typeArgs[];
            private final int _hash;

            public (ParameterizedType parameterizedtype, Class class1, Type atype[])
            {
/* 515*/        super(parameterizedtype, class1, null);
/* 516*/        if(atype == null)
                {
/* 517*/            throw new IllegalArgumentException("Null arg not allowed!");
                } else
                {
/* 518*/            typeArgs = atype;
/* 521*/            parameterizedtype = super.typeArgs();
/* 522*/            _hash = parameterizedtype * 31 + Arrays.hashCode(atype);
/* 523*/            return;
                }
            }
}
