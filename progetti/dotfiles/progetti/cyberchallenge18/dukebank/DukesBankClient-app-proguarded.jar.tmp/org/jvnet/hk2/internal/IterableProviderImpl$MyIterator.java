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
/* 207*/        return !handles.isEmpty();
            }

            public Object next()
            {
                ServiceHandle servicehandle;
/* 215*/        if(handles.isEmpty())
/* 215*/            throw new NoSuchElementException();
/* 217*/        else
/* 217*/            return (servicehandle = (ServiceHandle)handles.removeFirst()).getService();
            }

            public void remove()
            {
/* 227*/        throw new UnsupportedOperationException();
            }

            private final LinkedList handles;

            private (List list)
            {
/* 199*/        handles = new LinkedList(list);
            }

            handles(List list, handles handles1)
            {
/* 195*/        this(list);
            }
}
