// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Predicates.java

package jersey.repackaged.com.google.common.base;

import java.io.Serializable;
import java.util.List;

// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Joiner, Predicate, Predicates

static class <init>
    implements Serializable, Predicate
{

            public boolean apply(Object obj)
            {
/* 392*/        for(int i = 0; i < components.size(); i++)
/* 393*/            if(((Predicate)components.get(i)).apply(obj))
/* 394*/                return true;

/* 397*/        return false;
            }

            public int hashCode()
            {
/* 401*/        return components.hashCode() + 0x53c91cf;
            }

            public boolean equals(Object obj)
            {
/* 404*/        if(obj instanceof components)
                {
/* 405*/            obj = (components)obj;
/* 406*/            return components.equals(((components) (obj)).components);
                } else
                {
/* 408*/            return false;
                }
            }

            public String toString()
            {
/* 411*/        String s = String.valueOf(String.valueOf(Predicates.access$800().join(components)));
/* 411*/        return (new StringBuilder(15 + s.length())).append("Predicates.or(").append(s).append(")").toString();
            }

            private final List components;

            private (List list)
            {
/* 387*/        components = list;
            }

            components(List list, components components1)
            {
/* 383*/        this(list);
            }
}
