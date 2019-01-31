// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.concurrent.Callable;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheLoader, LocalCache

class val.valueLoader extends CacheLoader
{

            public Object load(Object obj)
                throws Exception
            {
/*4742*/        return val$valueLoader.call();
            }

            final Callable val$valueLoader;
            final val.valueLoader this$0;

            ()
            {
/*4739*/        this$0 = final_;
/*4739*/        val$valueLoader = Callable.this;
/*4739*/        super();
            }
}
