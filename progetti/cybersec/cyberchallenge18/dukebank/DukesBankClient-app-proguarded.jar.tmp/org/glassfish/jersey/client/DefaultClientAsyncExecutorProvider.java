// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DefaultClientAsyncExecutorProvider.java

package org.glassfish.jersey.client;

import java.util.logging.Logger;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.collection.*;
import org.glassfish.jersey.spi.ThreadPoolExecutorProvider;

class DefaultClientAsyncExecutorProvider extends ThreadPoolExecutorProvider
{

            public DefaultClientAsyncExecutorProvider(final int poolSize)
            {
/*  74*/        super("jersey-client-async-executor");
/*  76*/        asyncThreadPoolSize = Values.lazy(new Value() {

                    public Integer get()
                    {
/*  79*/                if(poolSize <= 0)
                        {
/*  80*/                    DefaultClientAsyncExecutorProvider.LOGGER.config(LocalizationMessages.IGNORED_ASYNC_THREADPOOL_SIZE(Integer.valueOf(poolSize)));
/*  82*/                    return Integer.valueOf(0x7fffffff);
                        } else
                        {
/*  84*/                    DefaultClientAsyncExecutorProvider.LOGGER.config(LocalizationMessages.USING_FIXED_ASYNC_THREADPOOL(Integer.valueOf(poolSize)));
/*  85*/                    return Integer.valueOf(poolSize);
                        }
                    }

                    public volatile Object get()
                    {
/*  76*/                return get();
                    }

                    final int val$poolSize;
                    final DefaultClientAsyncExecutorProvider this$0;

                    
                    {
/*  76*/                this$0 = DefaultClientAsyncExecutorProvider.this;
/*  76*/                poolSize = i;
/*  76*/                super();
                    }
        });
            }

            protected int getMaximumPoolSize()
            {
/*  93*/        return ((Integer)asyncThreadPoolSize.get()).intValue();
            }

            protected int getCorePoolSize()
            {
                Integer integer;
/*  99*/        if((integer = Integer.valueOf(getMaximumPoolSize())).intValue() != 0x7fffffff)
/* 101*/            return integer.intValue();
/* 103*/        else
/* 103*/            return 0;
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/client/DefaultClientAsyncExecutorProvider.getName());
            private final LazyValue asyncThreadPoolSize;


}
