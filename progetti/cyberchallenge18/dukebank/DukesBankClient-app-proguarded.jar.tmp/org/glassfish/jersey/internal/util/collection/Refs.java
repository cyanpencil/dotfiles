// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Refs.java

package org.glassfish.jersey.internal.util.collection;

import jersey.repackaged.com.google.common.base.MoreObjects;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            Ref

public final class Refs
{
    static final class ThreadSafeRefImpl
        implements Ref
    {

                public final Object get()
                {
/* 171*/            return reference;
                }

                public final void set(Object obj)
                    throws IllegalStateException
                {
/* 176*/            reference = obj;
                }

                public final String toString()
                {
/* 181*/            return MoreObjects.toStringHelper(this).add("reference", reference).toString();
                }

                public final boolean equals(Object obj)
                {
/* 186*/            if(obj == null)
/* 187*/                return false;
/* 189*/            if(!(obj instanceof Ref))
/* 190*/                return false;
/* 193*/            obj = ((Ref)obj).get();
                    Object obj1;
/* 194*/            return (obj1 = reference) == obj || obj1 != null && obj1.equals(obj);
                }

                public final int hashCode()
                {
/* 200*/            Object obj = reference;
                    int i;
/* 202*/            return i = 235 + (obj == null ? 0 : obj.hashCode());
                }

                private volatile Object reference;

                public ThreadSafeRefImpl()
                {
/* 162*/            reference = null;
                }

                public ThreadSafeRefImpl(Object obj)
                {
/* 166*/            reference = obj;
                }
    }

    static final class DefaultRefImpl
        implements Ref
    {

                public final Object get()
                {
/* 119*/            return reference;
                }

                public final void set(Object obj)
                    throws IllegalStateException
                {
/* 124*/            reference = obj;
                }

                public final String toString()
                {
/* 129*/            return MoreObjects.toStringHelper(this).add("reference", reference).toString();
                }

                public final boolean equals(Object obj)
                {
/* 134*/            if(obj == null)
/* 135*/                return false;
/* 137*/            if(!(obj instanceof Ref))
/* 138*/                return false;
/* 141*/            obj = ((Ref)obj).get();
                    Object obj1;
/* 142*/            return (obj1 = reference) == obj || obj1 != null && obj1.equals(obj);
                }

                public final int hashCode()
                {
                    int i;
/* 149*/            return i = 235 + (reference == null ? 0 : reference.hashCode());
                }

                private Object reference;

                public DefaultRefImpl()
                {
/* 110*/            reference = null;
                }

                public DefaultRefImpl(Object obj)
                {
/* 114*/            reference = obj;
                }
    }

    static final class ImmutableRefImpl
        implements Ref
    {

                public final Object get()
                {
/*  68*/            return reference;
                }

                public final void set(Object obj)
                    throws IllegalStateException
                {
/*  73*/            throw new IllegalStateException("This implementation of Ref interface is immutable.");
                }

                public final String toString()
                {
/*  78*/            return MoreObjects.toStringHelper(this).add("reference", reference).toString();
                }

                public final boolean equals(Object obj)
                {
/*  83*/            if(obj == null)
/*  84*/                return false;
/*  86*/            if(!(obj instanceof Ref))
/*  87*/                return false;
/*  90*/            obj = ((Ref)obj).get();
/*  91*/            return reference == obj || reference != null && reference.equals(obj);
                }

                public final int hashCode()
                {
                    int i;
/*  97*/            return i = 235 + (reference == null ? 0 : reference.hashCode());
                }

                private final Object reference;

                public ImmutableRefImpl(Object obj)
                {
/*  63*/            reference = obj;
                }
    }


            private Refs()
            {
            }

            public static Ref of(Object obj)
            {
/* 217*/        return new DefaultRefImpl(obj);
            }

            public static Ref emptyRef()
            {
/* 227*/        return new DefaultRefImpl();
            }

            public static Ref threadSafe()
            {
/* 238*/        return new ThreadSafeRefImpl();
            }

            public static Ref threadSafe(Object obj)
            {
/* 252*/        return new ThreadSafeRefImpl(obj);
            }

            public static Ref immutableRef(Object obj)
            {
/* 268*/        return new ImmutableRefImpl(obj);
            }
}
