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
/* 119*/        return reference;
            }

            public final void set(Object obj)
                throws IllegalStateException
            {
/* 124*/        reference = obj;
            }

            public final String toString()
            {
/* 129*/        return MoreObjects.toStringHelper(this).add("reference", reference).toString();
            }

            public final boolean equals(Object obj)
            {
/* 134*/        if(obj == null)
/* 135*/            return false;
/* 137*/        if(!(obj instanceof Ref))
/* 138*/            return false;
/* 141*/        obj = ((Ref)obj).get();
                Object obj1;
/* 142*/        return (obj1 = reference) == obj || obj1 != null && obj1.equals(obj);
            }

            public final int hashCode()
            {
                int i;
/* 149*/        return i = 235 + (reference == null ? 0 : reference.hashCode());
            }

            private Object reference;

            public r()
            {
/* 110*/        reference = null;
            }

            public reference(Object obj)
            {
/* 114*/        reference = obj;
            }
}
