// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap, Multiset, Multisets

class this._cls1 extends this._cls1
{

            Multiset multiset()
            {
/* 366*/        return this._cls1.this;
            }

            public Iterator iterator()
            {
/* 371*/        return tryIterator();
            }

            public int size()
            {
/* 376*/        return keySet().size();
            }

            private boolean removeEntriesIf(final Predicate predicate)
            {
/* 380*/        return FilteredEntryMultimap.this.removeEntriesIf(new Predicate() {

                    public boolean apply(java.util.Map.Entry entry)
                    {
/* 384*/                return predicate.apply(Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size()));
                    }

                    public volatile boolean apply(Object obj)
                    {
/* 381*/                return apply((java.util.Map.Entry)obj);
                    }

                    final Predicate val$predicate;
                    final FilteredEntryMultimap.Keys._cls1 this$2;

                    
                    {
/* 381*/                this$2 = FilteredEntryMultimap.Keys._cls1.this;
/* 381*/                predicate = predicate1;
/* 381*/                super();
                    }
        });
            }

            public boolean removeAll(Collection collection)
            {
/* 392*/        return removeEntriesIf(Predicates.in(collection));
            }

            public boolean retainAll(Collection collection)
            {
/* 397*/        return removeEntriesIf(Predicates.not(Predicates.in(collection)));
            }

            final removeEntriesIf this$1;

            _cls1.val.predicate()
            {
/* 362*/        this$1 = this._cls1.this;
/* 362*/        super();
            }
}
