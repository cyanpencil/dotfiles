// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractSortedSetMultimap, Multimaps

static class valueComparator extends AbstractSortedSetMultimap
{

            protected SortedSet createCollection()
            {
/* 366*/        return (SortedSet)factory.get();
            }

            public Comparator valueComparator()
            {
/* 370*/        return valueComparator;
            }

            protected volatile Set createCollection()
            {
/* 353*/        return createCollection();
            }

            protected volatile Collection createCollection()
            {
/* 353*/        return createCollection();
            }

            transient Supplier factory;
            transient Comparator valueComparator;

            (Map map, Supplier supplier)
            {
/* 360*/        super(map);
/* 361*/        factory = (Supplier)Preconditions.checkNotNull(supplier);
/* 362*/        valueComparator = ((SortedSet)supplier.get()).comparator();
            }
}
