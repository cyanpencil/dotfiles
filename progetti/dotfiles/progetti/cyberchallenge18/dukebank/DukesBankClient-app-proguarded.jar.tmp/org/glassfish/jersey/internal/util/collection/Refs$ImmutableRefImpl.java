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
/*  68*/        return reference;
            }

            public final void set(Object obj)
                throws IllegalStateException
            {
/*  73*/        throw new IllegalStateException("This implementation of Ref interface is immutable.");
            }

            public final String toString()
            {
/*  78*/        return MoreObjects.toStringHelper(this).add("reference", reference).toString();
            }

            public final boolean equals(Object obj)
            {
/*  83*/        if(obj == null)
/*  84*/            return false;
/*  86*/        if(!(obj instanceof Ref))
/*  87*/            return false;
/*  90*/        obj = ((Ref)obj).get();
/*  91*/        return reference == obj || reference != null && reference.equals(obj);
            }

            public final int hashCode()
            {
                int i;
/*  97*/        return i = 235 + (reference == null ? 0 : reference.hashCode());
            }

            private final Object reference;

            public I(Object obj)
            {
/*  63*/        reference = obj;
            }
}
