// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParameterizedTypeImpl.java

package org.glassfish.hk2.utilities.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

// Referenced classes of package org.glassfish.hk2.utilities.reflection:
//            Pretty

public class ParameterizedTypeImpl
    implements ParameterizedType
{

            public transient ParameterizedTypeImpl(Type type, Type atype[])
            {
/*  62*/        rawType = type;
/*  63*/        actualTypeArguments = atype;
            }

            public Type[] getActualTypeArguments()
            {
/*  71*/        return actualTypeArguments;
            }

            public Type getRawType()
            {
/*  79*/        return rawType;
            }

            public Type getOwnerType()
            {
/*  88*/        return null;
            }

            public int hashCode()
            {
/*  93*/        int i = Arrays.hashCode(actualTypeArguments);
/*  94*/        if(rawType == null)
/*  94*/            return i;
/*  95*/        else
/*  95*/            return i ^ rawType.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 100*/        if(obj == null)
/* 100*/            return false;
/* 101*/        if(!(obj instanceof ParameterizedType))
/* 101*/            return false;
/* 103*/        obj = (ParameterizedType)obj;
/* 105*/        if(!rawType.equals(((ParameterizedType) (obj)).getRawType()))
/* 105*/            return false;
/* 107*/        if((obj = ((ParameterizedType) (obj)).getActualTypeArguments()).length != actualTypeArguments.length)
/* 110*/            return false;
/* 113*/        for(int i = 0; i < obj.length; i++)
/* 114*/            if(!actualTypeArguments[i].equals(obj[i]))
/* 115*/                return false;

/* 119*/        return true;
            }

            public String toString()
            {
/* 123*/        return Pretty.pType(this);
            }

            private final Type rawType;
            private final Type actualTypeArguments[];
}
