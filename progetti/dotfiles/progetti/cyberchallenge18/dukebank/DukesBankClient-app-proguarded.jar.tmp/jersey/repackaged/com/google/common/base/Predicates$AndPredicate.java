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
/* 358*/        for(int i = 0; i < components.size(); i++)
/* 359*/            if(!((Predicate)components.get(i)).apply(obj))
/* 360*/                return false;

/* 363*/        return true;
            }

            public int hashCode()
            {
/* 367*/        return components.hashCode() + 0x12472c2c;
            }

            public boolean equals(Object obj)
            {
/* 370*/        if(obj instanceof components)
                {
/* 371*/            obj = (components)obj;
/* 372*/            return components.equals(((components) (obj)).components);
                } else
                {
/* 374*/            return false;
                }
            }

            public String toString()
            {
/* 377*/        String s = String.valueOf(String.valueOf(Predicates.access$800().join(components)));
/* 377*/        return (new StringBuilder(16 + s.length())).append("Predicates.and(").append(s).append(")").toString();
            }

            private final List components;

            private (List list)
            {
/* 353*/        components = list;
            }

            components(List list, components components1)
            {
/* 349*/        this(list);
            }
}
