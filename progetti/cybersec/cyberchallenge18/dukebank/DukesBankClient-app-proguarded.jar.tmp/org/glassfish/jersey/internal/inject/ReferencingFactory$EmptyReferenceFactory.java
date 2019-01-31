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

static class 
    implements Factory
{

            public Ref provide()
            {
/*  61*/        return Refs.emptyRef();
            }

            public void dispose(Ref ref)
            {
            }

            public volatile void dispose(Object obj)
            {
/*  57*/        dispose((Ref)obj);
            }

            public volatile Object provide()
            {
/*  57*/        return provide();
            }

            private ()
            {
            }

}
