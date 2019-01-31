// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions, Predicate, Predicates

static class ll
    implements Serializable, Predicate
{

            public boolean apply(Object obj)
            {
/* 328*/        return !predicate.apply(obj);
            }

            public int hashCode()
            {
/* 331*/        return ~predicate.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 334*/        if(obj instanceof predicate)
                {
/* 335*/            obj = (predicate)obj;
/* 336*/            return predicate.equals(((predicate) (obj)).predicate);
                } else
                {
/* 338*/            return false;
                }
            }

            public String toString()
            {
/* 341*/        String s = String.valueOf(String.valueOf(predicate.toString()));
/* 341*/        return (new StringBuilder(16 + s.length())).append("Predicates.not(").append(s).append(")").toString();
            }

            final Predicate predicate;

            (Predicate predicate1)
            {
/* 324*/        predicate = (Predicate)Preconditions.checkNotNull(predicate1);
            }
}
