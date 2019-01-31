// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Predicate, Predicates

static class <init>
    implements Serializable, Predicate
{

            public boolean apply(Object obj)
            {
/* 426*/        return target.equals(obj);
            }

            public int hashCode()
            {
/* 429*/        return target.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 432*/        if(obj instanceof target)
                {
/* 433*/            obj = (target)obj;
/* 434*/            return target.equals(((target) (obj)).target);
                } else
                {
/* 436*/            return false;
                }
            }

            public String toString()
            {
/* 439*/        String s = String.valueOf(String.valueOf(target));
/* 439*/        return (new StringBuilder(20 + s.length())).append("Predicates.equalTo(").append(s).append(")").toString();
            }

            private final Object target;

            private (Object obj)
            {
/* 422*/        target = obj;
            }

            target(Object obj, target target1)
            {
/* 417*/        this(obj);
            }
}
