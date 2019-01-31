// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, FilteredEntryMultimap, Multimap, Multimaps, 
//            Multiset, Multisets

class this._cls0 extends this._cls0
{

            public int remove(Object obj, int i)
            {
/* 336*/        CollectPreconditions.checkNonnegative(i, "occurrences");
/* 337*/        if(i == 0)
/* 338*/            return count(obj);
                Object obj1;
/* 340*/        if((obj1 = (Collection)unfiltered.asMap().get(obj)) == null)
/* 342*/            return 0;
/* 345*/        obj = obj;
/* 346*/        int j = 0;
/* 347*/        obj1 = ((Collection) (obj1)).iterator();
/* 348*/        do
                {
/* 348*/            if(!((Iterator) (obj1)).hasNext())
/* 349*/                break;
/* 349*/            Object obj2 = ((Iterator) (obj1)).next();
/* 350*/            if(FilteredEntryMultimap.access$000(FilteredEntryMultimap.this, obj, obj2) && ++j <= i)
/* 353*/                ((Iterator) (obj1)).remove();
                } while(true);
/* 357*/        return j;
            }

            public Set entrySet()
            {
/* 362*/        return new Multisets.EntrySet() {

                    Multiset multiset()
                    {
/* 366*/                return FilteredEntryMultimap.Keys.this;
                    }

                    public Iterator iterator()
                    {
/* 371*/                return entryIterator();
                    }

                    public int size()
                    {
/* 376*/                return keySet().size();
                    }

                    private boolean removeEntriesIf(final Predicate predicate)
                    {
/* 380*/                return FilteredEntryMultimap.this.removeEntriesIf(new Predicate() {

                            public boolean apply(java.util.Map.Entry entry)
                            {
/* 384*/                        return predicate.apply(Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size()));
                            }

                            public volatile boolean apply(Object obj)
                            {
/* 381*/                        return apply((java.util.Map.Entry)obj);
                            }

                            final Predicate val$predicate;
                            final _cls1 this$2;

                            
                            {
/* 381*/                        this$2 = _cls1.this;
/* 381*/                        predicate = predicate1;
/* 381*/                        super();
                            }
                });
                    }

                    public boolean removeAll(Collection collection)
                    {
/* 392*/                return removeEntriesIf(Predicates.in(collection));
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 397*/                return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                    }

                    final FilteredEntryMultimap.Keys this$1;

                    
                    {
/* 362*/                this$1 = FilteredEntryMultimap.Keys.this;
/* 362*/                super();
                    }
        };
            }

            final FilteredEntryMultimap this$0;

            _cls1.this._cls1()
            {
/* 330*/        this$0 = FilteredEntryMultimap.this;
/* 331*/        super(FilteredEntryMultimap.this);
            }
}
