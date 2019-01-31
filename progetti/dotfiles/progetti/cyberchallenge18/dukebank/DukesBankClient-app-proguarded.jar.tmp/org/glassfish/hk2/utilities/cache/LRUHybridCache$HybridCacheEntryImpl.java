// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUHybridCache.java

package org.glassfish.hk2.utilities.cache;


// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            HybridCacheEntry, LRUHybridCache

final class dropMe
    implements HybridCacheEntry
{

            public final Object getValue()
            {
/* 226*/        return value;
            }

            public final boolean dropMe()
            {
/* 231*/        return dropMe;
            }

            public final void removeFromCache()
            {
/* 236*/        remove(key);
            }

            public final int hashCode()
            {
                int i;
/* 242*/        return i = 115 + (key == null ? 0 : key.hashCode());
            }

            public final boolean equals(Object obj)
            {
/* 249*/        if(obj == null)
/* 250*/            return false;
/* 252*/        if(getClass() != obj.getClass())
/* 253*/            return false;
/* 255*/        obj = (key)obj;
/* 256*/        return key == ((key) (obj)).key || key != null && key.equals(((key) (obj)).key);
            }

            private final Object key;
            private final Object value;
            private final boolean dropMe;
            final LRUHybridCache this$0;

            public (Object obj, Object obj1, boolean flag)
            {
/* 218*/        this$0 = LRUHybridCache.this;
/* 218*/        super();
/* 219*/        key = obj;
/* 220*/        value = obj1;
/* 221*/        dropMe = flag;
            }
}
