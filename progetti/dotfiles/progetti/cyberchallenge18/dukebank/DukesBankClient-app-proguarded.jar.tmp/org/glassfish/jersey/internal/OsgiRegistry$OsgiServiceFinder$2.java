// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   OsgiRegistry.java

package org.glassfish.jersey.internal;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.glassfish.jersey.internal:
//            OsgiRegistry

class val.providerClasses
    implements Iterator
{

            public boolean hasNext()
            {
/* 187*/        return it.hasNext();
            }

            public Class next()
            {
/* 193*/        return (Class)it.next();
            }

            public void remove()
            {
/* 198*/        throw new UnsupportedOperationException();
            }

            public volatile Object next()
            {
/* 181*/        return next();
            }

            Iterator it;
            final List val$providerClasses;
            final next this$1;

            ()
            {
/* 181*/        this$1 = final_;
/* 181*/        val$providerClasses = List.this;
/* 181*/        super();
/* 183*/        it = val$providerClasses.iterator();
            }
}
