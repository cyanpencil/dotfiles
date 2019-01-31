// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheLoader.java

package jersey.repackaged.com.google.common.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import jersey.repackaged.com.google.common.util.concurrent.ListenableFuture;
import jersey.repackaged.com.google.common.util.concurrent.ListenableFutureTask;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheLoader

class val.oldValue
    implements Callable
{

            public Object call()
                throws Exception
            {
/* 197*/        return loader.reload(val$key, val$oldValue).get();
            }

            final Object val$key;
            final Object val$oldValue;
            final ableFuture.get this$0;

            t>()
            {
/* 194*/        this$0 = final_t>;
/* 194*/        val$key = obj;
/* 194*/        val$oldValue = Object.this;
/* 194*/        super();
            }

            // Unreferenced inner class jersey/repackaged/com/google/common/cache/CacheLoader$1

/* anonymous class */
    static class CacheLoader._cls1 extends CacheLoader
    {

                public final Object load(Object obj)
                    throws Exception
                {
/* 189*/            return loader.load(obj);
                }

                public final ListenableFuture reload(final Object key, Object obj)
                    throws Exception
                {
/* 194*/            key = ListenableFutureTask.create(((CacheLoader._cls1._cls1) (obj)). new CacheLoader._cls1._cls1());
/* 200*/            executor.execute(((Runnable) (key)));
/* 201*/            return ((ListenableFuture) (key));
                }

                public final Map loadAll(Iterable iterable)
                    throws Exception
                {
/* 206*/            return loader.loadAll(iterable);
                }

                final CacheLoader val$loader;
                final Executor val$executor;

                    
                    {
/* 186*/                loader = cacheloader;
/* 186*/                executor = executor1;
/* 186*/                super();
                    }
    }

}
