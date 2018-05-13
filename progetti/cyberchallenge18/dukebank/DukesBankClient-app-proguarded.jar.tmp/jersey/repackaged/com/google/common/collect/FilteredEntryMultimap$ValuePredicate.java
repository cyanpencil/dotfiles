// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap

final class key
    implements Predicate
{

            public final boolean apply(Object obj)
            {
/*  84*/        return FilteredEntryMultimap.access$000(FilteredEntryMultimap.this, key, obj);
            }

            private final Object key;
            final FilteredEntryMultimap this$0;

            (Object obj)
            {
/*  78*/        this$0 = FilteredEntryMultimap.this;
/*  78*/        super();
/*  79*/        key = obj;
            }
}
