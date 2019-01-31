// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

final class tCacheSet extends tCacheSet
{

            public final Iterator iterator()
            {
/*4468*/        return new ator(LocalCache.this);
            }

            public final boolean contains(Object obj)
            {
/*4473*/        return map.containsKey(obj);
            }

            public final boolean remove(Object obj)
            {
/*4478*/        return map.remove(obj) != null;
            }

            final LocalCache this$0;

            ator(ConcurrentMap concurrentmap)
            {
/*4462*/        this$0 = LocalCache.this;
/*4463*/        super(LocalCache.this, concurrentmap);
            }
}
