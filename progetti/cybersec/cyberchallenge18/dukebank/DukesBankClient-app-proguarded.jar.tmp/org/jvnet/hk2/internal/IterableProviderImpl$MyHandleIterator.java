// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IterableProviderImpl.java

package org.jvnet.hk2.internal;

import java.util.*;
import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.jvnet.hk2.internal:
//            IterableProviderImpl

static class <init>
    implements Iterator
{

            public boolean hasNext()
            {
/* 262*/        return !handles.isEmpty();
            }

            public ServiceHandle next()
            {
/* 270*/        return (ServiceHandle)handles.removeFirst();
            }

            public void remove()
            {
/* 278*/        throw new UnsupportedOperationException();
            }

            public volatile Object next()
            {
/* 250*/        return next();
            }

            private final LinkedList handles;

            private (List list)
            {
/* 254*/        handles = new LinkedList(list);
            }

            handles(List list, handles handles1)
            {
/* 250*/        this(list);
            }
}
