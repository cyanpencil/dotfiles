// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheLoader.java

package jersey.repackaged.com.google.common.cache;

import java.io.Serializable;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            CacheLoader

static final class computingSupplier extends CacheLoader
    implements Serializable
{

            public final Object load(Object obj)
            {
/* 221*/        Preconditions.checkNotNull(obj);
/* 222*/        return computingSupplier.get();
            }

            private final Supplier computingSupplier;

            public Q(Supplier supplier)
            {
/* 216*/        computingSupplier = (Supplier)Preconditions.checkNotNull(supplier);
            }
}
