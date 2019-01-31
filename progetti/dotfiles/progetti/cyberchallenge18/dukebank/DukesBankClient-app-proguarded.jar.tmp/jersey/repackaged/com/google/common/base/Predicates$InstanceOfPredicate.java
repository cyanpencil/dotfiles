// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions, Predicate, Predicates

static class <init>
    implements Serializable, Predicate
{

            public boolean apply(Object obj)
            {
/* 455*/        return clazz.isInstance(obj);
            }

            public int hashCode()
            {
/* 458*/        return clazz.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 461*/        if(obj instanceof clazz)
                {
/* 462*/            obj = (clazz)obj;
/* 463*/            return clazz == ((clazz) (obj)).clazz;
                } else
                {
/* 465*/            return false;
                }
            }

            public String toString()
            {
/* 468*/        String s = String.valueOf(String.valueOf(clazz.getName()));
/* 468*/        return (new StringBuilder(23 + s.length())).append("Predicates.instanceOf(").append(s).append(")").toString();
            }

            private final Class clazz;

            private (Class class1)
            {
/* 451*/        clazz = (Class)Preconditions.checkNotNull(class1);
            }

            clazz(Class class1, clazz clazz1)
            {
/* 446*/        this(class1);
            }
}
