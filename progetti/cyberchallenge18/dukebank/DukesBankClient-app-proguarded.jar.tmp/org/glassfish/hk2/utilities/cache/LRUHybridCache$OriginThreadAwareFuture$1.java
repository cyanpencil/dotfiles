// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUHybridCache.java

package org.glassfish.hk2.utilities.cache;

import java.util.concurrent.Callable;

// Referenced classes of package org.glassfish.hk2.utilities.cache:
//            Computable, HybridCacheEntry, LRUHybridCache

class val.key
    implements Callable
{

            public HybridCacheEntry call()
                throws Exception
            {
                HybridCacheEntry hybridcacheentry;
/* 115*/        hybridcacheentry = hybridcacheentry = (HybridCacheEntry)LRUHybridCache.access$000(_fld0).compute(val$key);
/* 118*/        cess._mth102(this._cls1.this, -1L);
/* 118*/        return hybridcacheentry;
                Exception exception;
/* 118*/        exception;
/* 118*/        cess._mth102(this._cls1.this, -1L);
/* 118*/        throw exception;
            }

            public volatile Object call()
                throws Exception
            {
/* 111*/        return call();
            }

            final LRUHybridCache val$this$0;
            final Object val$key;
            final call this$1;

            ()
            {
/* 111*/        this$1 = final_;
/* 111*/        val$this$0 = lruhybridcache;
/* 111*/        val$key = Object.this;
/* 111*/        super();
            }
}
