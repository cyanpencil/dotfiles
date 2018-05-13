// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RequestScope.java

package org.glassfish.jersey.process.internal;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import jersey.repackaged.com.google.common.base.MoreObjects;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.LazyUid;

// Referenced classes of package org.glassfish.jersey.process.internal:
//            RequestScope

public static final class referenceCounter
{

            private referenceCounter getReference()
            {
/* 487*/        referenceCounter.incrementAndGet();
/* 488*/        return this;
            }

            final Object get(ActiveDescriptor activedescriptor)
            {
/* 500*/        return store.get(activedescriptor);
            }

            final Object put(ActiveDescriptor activedescriptor, Object obj)
            {
/* 514*/        Preconditions.checkState(!store.containsKey(activedescriptor), "An instance for the descriptor %s was already seeded in this scope. Old instance: %s New instance: %s", new Object[] {
/* 514*/            activedescriptor, store.get(activedescriptor), obj
                });
/* 520*/        return store.put(activedescriptor, obj);
            }

            final void remove(ActiveDescriptor activedescriptor)
            {
                Object obj;
/* 530*/        if((obj = store.remove(activedescriptor)) != null)
/* 532*/            activedescriptor.dispose(obj);
            }

            private boolean contains(ActiveDescriptor activedescriptor)
            {
/* 537*/        return store.containsKey(activedescriptor);
            }

            public final void release()
            {
/* 546*/        if(referenceCounter.decrementAndGet() > 0)
/* 548*/            break MISSING_BLOCK_LABEL_89;
                ActiveDescriptor activedescriptor;
/* 548*/        for(Iterator iterator = Sets.newHashSet(store.keySet()).iterator(); iterator.hasNext(); remove(activedescriptor))
/* 548*/            activedescriptor = (ActiveDescriptor)iterator.next();

/* 552*/        RequestScope.access$300().debugLog("Released scope instance {0}", new Object[] {
/* 552*/            this
                });
/* 553*/        return;
                Exception exception;
/* 552*/        exception;
/* 552*/        RequestScope.access$300().debugLog("Released scope instance {0}", new Object[] {
/* 552*/            this
                });
/* 552*/        throw exception;
            }

            public final String toString()
            {
/* 559*/        return MoreObjects.toStringHelper(this).add("id", id.value()).add("referenceCounter", referenceCounter.get()).add("store size", store.size()).toString();
            }

            private final LazyUid id;
            private final Map store;
            private final AtomicInteger referenceCounter;



            private gHelper()
            {
/* 462*/        id = new LazyUid();
/* 473*/        store = new HashMap();
/* 474*/        referenceCounter = new AtomicInteger(1);
            }

}
