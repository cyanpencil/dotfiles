// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

final class map extends AbstractCollection
{

            public final int size()
            {
/*4490*/        return map.size();
            }

            public final boolean isEmpty()
            {
/*4494*/        return map.isEmpty();
            }

            public final void clear()
            {
/*4498*/        map.clear();
            }

            public final Iterator iterator()
            {
/*4503*/        return new erator(LocalCache.this);
            }

            public final boolean contains(Object obj)
            {
/*4508*/        return map.containsValue(obj);
            }

            private final ConcurrentMap map;
            final LocalCache this$0;

            erator(ConcurrentMap concurrentmap)
            {
/*4485*/        this$0 = LocalCache.this;
/*4485*/        super();
/*4486*/        map = concurrentmap;
            }
}
