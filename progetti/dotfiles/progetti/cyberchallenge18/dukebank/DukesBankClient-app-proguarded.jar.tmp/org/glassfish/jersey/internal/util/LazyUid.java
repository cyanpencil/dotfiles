// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LazyUid.java

package org.glassfish.jersey.internal.util;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class LazyUid
    implements Serializable
{

            public LazyUid()
            {
            }

            public String value()
            {
/*  66*/        if(uid.get() == null)
/*  67*/            uid.compareAndSet(null, UUID.randomUUID().toString());
/*  70*/        return (String)uid.get();
            }

            public boolean equals(Object obj)
            {
/*  75*/        if(obj == null)
/*  76*/            return false;
/*  78*/        if(getClass() != obj.getClass())
                {
/*  79*/            return false;
                } else
                {
/*  81*/            obj = (LazyUid)obj;
/*  82*/            return value().equals(((LazyUid) (obj)).value());
                }
            }

            public int hashCode()
            {
                int i;
/*  88*/        return i = 511 + value().hashCode();
            }

            public String toString()
            {
/*  94*/        return value();
            }

            private static final long serialVersionUID = 0x401898ca9e6bfde3L;
            private final AtomicReference uid = new AtomicReference();
}
