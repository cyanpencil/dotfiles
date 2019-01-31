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

            public boolean apply(Class class1)
            {
/* 484*/        return clazz.isAssignableFrom(class1);
            }

            public int hashCode()
            {
/* 487*/        return clazz.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 490*/        if(obj instanceof clazz)
                {
/* 491*/            obj = (clazz)obj;
/* 492*/            return clazz == ((clazz) (obj)).clazz;
                } else
                {
/* 494*/            return false;
                }
            }

            public String toString()
            {
/* 497*/        String s = String.valueOf(String.valueOf(clazz.getName()));
/* 497*/        return (new StringBuilder(27 + s.length())).append("Predicates.assignableFrom(").append(s).append(")").toString();
            }

            public volatile boolean apply(Object obj)
            {
/* 474*/        return apply((Class)obj);
            }

            private final Class clazz;

            private Q(Class class1)
            {
/* 480*/        clazz = (Class)Preconditions.checkNotNull(class1);
            }

            clazz(Class class1, clazz clazz1)
            {
/* 475*/        this(class1);
            }
}
