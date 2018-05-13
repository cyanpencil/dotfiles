// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package org.jvnet.hk2.internal:
//            ServiceLocatorImpl, NarrowResults, ImmediateResults

class immediate
{

            final NarrowResults results;
            final ImmediateResults immediate;
            final AtomicInteger freshnessKeeper = new AtomicInteger(1);
            final ServiceLocatorImpl this$0;

            public (NarrowResults narrowresults, ImmediateResults immediateresults)
            {
/*1134*/        this$0 = ServiceLocatorImpl.this;
/*1134*/        super();
/*1135*/        results = narrowresults;
/*1136*/        immediate = immediateresults;
            }
}
