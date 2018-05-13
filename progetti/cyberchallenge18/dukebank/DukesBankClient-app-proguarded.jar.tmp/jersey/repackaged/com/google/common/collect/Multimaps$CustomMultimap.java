// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, Multimaps

static class factory extends AbstractMapBasedMultimap
{

            protected Collection createCollection()
            {
/* 126*/        return (Collection)factory.get();
            }

            transient Supplier factory;

            (Map map, Supplier supplier)
            {
/* 121*/        super(map);
/* 122*/        factory = (Supplier)Preconditions.checkNotNull(supplier);
            }
}
