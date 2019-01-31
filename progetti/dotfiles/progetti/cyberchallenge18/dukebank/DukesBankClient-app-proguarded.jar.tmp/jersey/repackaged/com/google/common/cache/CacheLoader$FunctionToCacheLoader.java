// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheLoader.java

package jersey.repackaged.com.google.common.cache;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheLoader

static final class computingFunction extends CacheLoader
    implements Serializable
{

            public final Object load(Object obj)
            {
/* 151*/        return computingFunction.apply(Preconditions.checkNotNull(obj));
            }

            private final Function computingFunction;

            public Q(Function function)
            {
/* 146*/        computingFunction = (Function)Preconditions.checkNotNull(function);
            }
}
