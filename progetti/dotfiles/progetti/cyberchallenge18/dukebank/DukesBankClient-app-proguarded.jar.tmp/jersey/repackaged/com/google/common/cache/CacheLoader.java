// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheLoader.java

package jersey.repackaged.com.google.common.cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import jersey.repackaged.com.google.common.base.*;
import jersey.repackaged.com.google.common.util.concurrent.*;

public abstract class CacheLoader
{
    public static final class InvalidCacheLoadException extends RuntimeException
    {

                public InvalidCacheLoadException(String s)
                {
/* 237*/            super(s);
                }
    }

    static final class UnsupportedLoadingOperationException extends UnsupportedOperationException
    {

                UnsupportedLoadingOperationException()
                {
                }
    }

    static final class SupplierToCacheLoader extends CacheLoader
        implements Serializable
    {

                public final Object load(Object obj)
                {
/* 221*/            Preconditions.checkNotNull(obj);
/* 222*/            return computingSupplier.get();
                }

                private final Supplier computingSupplier;

                public SupplierToCacheLoader(Supplier supplier)
                {
/* 216*/            computingSupplier = (Supplier)Preconditions.checkNotNull(supplier);
                }
    }

    static final class FunctionToCacheLoader extends CacheLoader
        implements Serializable
    {

                public final Object load(Object obj)
                {
/* 151*/            return computingFunction.apply(Preconditions.checkNotNull(obj));
                }

                private final Function computingFunction;

                public FunctionToCacheLoader(Function function)
                {
/* 146*/            computingFunction = (Function)Preconditions.checkNotNull(function);
                }
    }


            protected CacheLoader()
            {
            }

            public abstract Object load(Object obj)
                throws Exception;

            public ListenableFuture reload(Object obj, Object obj1)
                throws Exception
            {
/*  95*/        Preconditions.checkNotNull(obj);
/*  96*/        Preconditions.checkNotNull(obj1);
/*  97*/        return Futures.immediateFuture(load(obj));
            }

            public Map loadAll(Iterable iterable)
                throws Exception
            {
/* 125*/        throw new UnsupportedLoadingOperationException();
            }

            public static CacheLoader from(Function function)
            {
/* 138*/        return new FunctionToCacheLoader(function);
            }

            public static CacheLoader from(Supplier supplier)
            {
/* 168*/        return new SupplierToCacheLoader(supplier);
            }

            public static CacheLoader asyncReloading(CacheLoader cacheloader, Executor executor)
            {
/* 184*/        Preconditions.checkNotNull(cacheloader);
/* 185*/        Preconditions.checkNotNull(executor);
/* 186*/        return new CacheLoader(cacheloader, executor) {

                    public final Object load(Object obj)
                        throws Exception
                    {
/* 189*/                return loader.load(obj);
                    }

                    public final ListenableFuture reload(final Object key, final Object oldValue)
                        throws Exception
                    {
/* 194*/                key = ListenableFutureTask.create(new Callable() {

                            public Object call()
                                throws Exception
                            {
/* 197*/                        return loader.reload(key, oldValue).get();
                            }

                            final Object val$key;
                            final Object val$oldValue;
                            final _cls1 this$0;

                            
                            {
/* 194*/                        this$0 = _cls1.this;
/* 194*/                        key = obj;
/* 194*/                        oldValue = obj1;
/* 194*/                        super();
                            }
                });
/* 200*/                executor.execute(((Runnable) (key)));
/* 201*/                return ((ListenableFuture) (key));
                    }

                    public final Map loadAll(Iterable iterable)
                        throws Exception
                    {
/* 206*/                return loader.loadAll(iterable);
                    }

                    final CacheLoader val$loader;
                    final Executor val$executor;

                    
                    {
/* 186*/                loader = cacheloader;
/* 186*/                executor = executor1;
/* 186*/                super();
                    }
        };
            }
}
