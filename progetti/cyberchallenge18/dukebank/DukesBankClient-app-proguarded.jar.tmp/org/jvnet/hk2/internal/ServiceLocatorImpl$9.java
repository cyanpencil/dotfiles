// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorImpl.java

package org.jvnet.hk2.internal;

import java.util.LinkedList;
import java.util.List;
import org.glassfish.hk2.utilities.cache.Computable;
import org.glassfish.hk2.utilities.cache.ComputationErrorException;

// Referenced classes of package org.jvnet.hk2.internal:
//            ImmediateResults, NarrowResults, ServiceLocatorImpl, Utilities

class this._cls0
    implements Computable
{

            public dValue compute(dCacheKey dcachekey)
            {
/*1349*/        Object obj = ServiceLocatorImpl.access$1100(ServiceLocatorImpl.this, dCacheKey.access._mth500(dcachekey), null, true, false, true);
/*1350*/        if(!((NarrowResults) (obj = (dcachekey = ServiceLocatorImpl.access$1200(ServiceLocatorImpl.this, ServiceLocatorImpl.this, ((List) (obj)), dCacheKey.access._mth700(dcachekey), null, null, false, true, null, dCacheKey.access._mth500(dcachekey), dCacheKey.access._mth900(dcachekey))).getTimelessResults())).getErrors().isEmpty())
                {
/*1362*/            Utilities.handleErrors(((NarrowResults) (obj)), new LinkedList(ServiceLocatorImpl.access$1300(ServiceLocatorImpl.this)));
/*1363*/            throw new ComputationErrorException(new dValue(ServiceLocatorImpl.this, ((NarrowResults) (obj)), dcachekey));
                } else
                {
/*1366*/            return new dValue(ServiceLocatorImpl.this, ((NarrowResults) (obj)), dcachekey);
                }
            }

            public volatile Object compute(Object obj)
                throws ComputationErrorException
            {
/*1345*/        return compute((dCacheKey)obj);
            }

            final ServiceLocatorImpl this$0;

            nErrorException()
            {
/*1345*/        this$0 = ServiceLocatorImpl.this;
/*1345*/        super();
            }
}
