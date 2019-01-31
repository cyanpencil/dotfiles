// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   IterableProviderImpl.java

package org.jvnet.hk2.internal;

import java.util.*;

// Referenced classes of package org.jvnet.hk2.internal:
//            IterableProviderImpl

static class <init>
    implements Iterable
{

            public Iterator iterator()
            {
/* 245*/        return new r(handles, null);
            }

            private final List handles;

            private r(List list)
            {
/* 237*/        handles = new LinkedList(list);
            }

            handles(List list, handles handles1)
            {
/* 233*/        this(list);
            }
}
