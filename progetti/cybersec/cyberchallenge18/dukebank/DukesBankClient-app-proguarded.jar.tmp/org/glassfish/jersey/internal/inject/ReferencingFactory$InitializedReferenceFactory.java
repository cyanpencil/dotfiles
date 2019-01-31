// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReferencingFactory.java

package org.glassfish.jersey.internal.inject;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Refs;

// Referenced classes of package org.glassfish.jersey.internal.inject:
//            ReferencingFactory

static class initialValue
    implements Factory
{

            public Ref provide()
            {
/*  80*/        return Refs.of(initialValue);
            }

            public void dispose(Ref ref)
            {
            }

            public volatile void dispose(Object obj)
            {
/*  70*/        dispose((Ref)obj);
            }

            public volatile Object provide()
            {
/*  70*/        return provide();
            }

            private final Object initialValue;

            public Y(Object obj)
            {
/*  75*/        initialValue = obj;
            }
}
