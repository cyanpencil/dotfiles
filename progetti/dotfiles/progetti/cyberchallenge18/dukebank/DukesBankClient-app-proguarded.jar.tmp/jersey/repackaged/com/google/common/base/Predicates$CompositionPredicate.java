// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Function, Preconditions, Predicate, Predicates

static class <init>
    implements Serializable, Predicate
{

            public boolean apply(Object obj)
            {
/* 552*/        return p.apply(f.apply(obj));
            }

            public boolean equals(Object obj)
            {
/* 556*/        if(obj instanceof f)
                {
/* 557*/            obj = (f)obj;
/* 558*/            return f.equals(((f) (obj)).f) && p.equals(((p) (obj)).p);
                } else
                {
/* 560*/            return false;
                }
            }

            public int hashCode()
            {
/* 564*/        return f.hashCode() ^ p.hashCode();
            }

            public String toString()
            {
/* 568*/        String s = String.valueOf(String.valueOf(p.toString()));
/* 568*/        String s1 = String.valueOf(String.valueOf(f.toString()));
/* 568*/        return (new StringBuilder(2 + s.length() + s1.length())).append(s).append("(").append(s1).append(")").toString();
            }

            final Predicate p;
            final Function f;

            private (Predicate predicate, Function function)
            {
/* 546*/        p = (Predicate)Preconditions.checkNotNull(predicate);
/* 547*/        f = (Function)Preconditions.checkNotNull(function);
            }

            f(Predicate predicate, Function function, f f1)
            {
/* 540*/        this(predicate, function);
            }
}
