// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Refs.java

package org.glassfish.jersey.internal.util.collection;

import jersey.repackaged.com.google.common.base.MoreObjects;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            Ref, Refs

static final class reference
    implements Ref
{

            public final Object get()
            {
/* 171*/        return reference;
            }

            public final void set(Object obj)
                throws IllegalStateException
            {
/* 176*/        reference = obj;
            }

            public final String toString()
            {
/* 181*/        return MoreObjects.toStringHelper(this).add("reference", reference).toString();
            }

            public final boolean equals(Object obj)
            {
/* 186*/        if(obj == null)
/* 187*/            return false;
/* 189*/        if(!(obj instanceof Ref))
/* 190*/            return false;
/* 193*/        obj = ((Ref)obj).get();
                Object obj1;
/* 194*/        return (obj1 = reference) == obj || obj1 != null && obj1.equals(obj);
            }

            public final int hashCode()
            {
/* 200*/        Object obj = reference;
                int i;
/* 202*/        return i = 235 + (obj == null ? 0 : obj.hashCode());
            }

            private volatile Object reference;

            public ()
            {
/* 162*/        reference = null;
            }

            public reference(Object obj)
            {
/* 166*/        reference = obj;
            }
}
