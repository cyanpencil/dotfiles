// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractListMultimap, Multimaps

static class factory extends AbstractListMultimap
{

            protected List createCollection()
            {
/* 208*/        return (List)factory.get();
            }

            protected volatile Collection createCollection()
            {
/* 197*/        return createCollection();
            }

            transient Supplier factory;

            (Map map, Supplier supplier)
            {
/* 203*/        super(map);
/* 204*/        factory = (Supplier)Preconditions.checkNotNull(supplier);
            }
}