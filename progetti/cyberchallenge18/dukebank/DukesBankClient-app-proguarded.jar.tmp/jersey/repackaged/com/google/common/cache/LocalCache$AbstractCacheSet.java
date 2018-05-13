// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.AbstractSet;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

abstract class map extends AbstractSet
{

            public int size()
            {
/*4446*/        return map.size();
            }

            public boolean isEmpty()
            {
/*4451*/        return map.isEmpty();
            }

            public void clear()
            {
/*4456*/        map.clear();
            }

            final ConcurrentMap map;
            final LocalCache this$0;

            (ConcurrentMap concurrentmap)
            {
/*4440*/        this$0 = LocalCache.this;
/*4440*/        super();
/*4441*/        map = concurrentmap;
            }
}
