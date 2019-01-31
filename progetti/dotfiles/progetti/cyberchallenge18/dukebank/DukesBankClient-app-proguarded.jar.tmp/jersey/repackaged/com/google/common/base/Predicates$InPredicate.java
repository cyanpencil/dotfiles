// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;
import java.util.Collection;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Preconditions, Predicate, Predicates

static class <init>
    implements Serializable, Predicate
{

            public boolean apply(Object obj)
            {
/* 513*/        return target.contains(obj);
/* 514*/        JVM INSTR pop ;
/* 515*/        return false;
/* 516*/        JVM INSTR pop ;
/* 517*/        return false;
            }

            public boolean equals(Object obj)
            {
/* 522*/        if(obj instanceof target)
                {
/* 523*/            obj = (target)obj;
/* 524*/            return target.equals(((target) (obj)).target);
                } else
                {
/* 526*/            return false;
                }
            }

            public int hashCode()
            {
/* 530*/        return target.hashCode();
            }

            public String toString()
            {
/* 534*/        String s = String.valueOf(String.valueOf(target));
/* 534*/        return (new StringBuilder(15 + s.length())).append("Predicates.in(").append(s).append(")").toString();
            }

            private final Collection target;

            private (Collection collection)
            {
/* 507*/        target = (Collection)Preconditions.checkNotNull(collection);
            }

            ull(Collection collection, ull ull)
            {
/* 503*/        this(collection);
            }
}
