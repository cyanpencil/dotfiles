// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap, Iterators, Maps, AbstractIterator, 
//            Multimap

class this._cls1 extends this._cls1
{

            Map map()
            {
/* 242*/        return this._cls1.this;
            }

            public Iterator iterator()
            {
/* 247*/        return new AbstractIterator() {

                    protected java.util.Map.Entry computeNext()
                    {
/* 253*/                while(backingIterator.hasNext()) 
                        {
                            Object obj;
/* 254*/                    Object obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)backingIterator.next())).getKey();
/* 256*/                    if(!((Collection) (obj = FilteredEntryMultimap.filterCollection((Collection)((java.util.Map.Entry) (obj)).getValue(), new FilteredEntryMultimap.ValuePredicate(this$0, obj1)))).isEmpty())
/* 259*/                        return Maps.immutableEntry(obj1, obj);
                        }
/* 262*/                return (java.util.Map.Entry)endOfData();
                    }

                    protected volatile Object computeNext()
                    {
/* 247*/                return computeNext();
                    }

                    final Iterator backingIterator;
                    final FilteredEntryMultimap.AsMap._cls2 this$2;

                    
                    {
/* 247*/                this$2 = FilteredEntryMultimap.AsMap._cls2.this;
/* 247*/                super();
/* 248*/                backingIterator = unfiltered.asMap().entrySet().iterator();
                    }
        };
            }

            public boolean removeAll(Collection collection)
            {
/* 269*/        return removeEntriesIf(Predicates.in(collection));
            }

            public boolean retainAll(Collection collection)
            {
/* 274*/        return removeEntriesIf(Predicates.not(Predicates.in(collection)));
            }

            public int size()
            {
/* 279*/        return Iterators.size(iterator());
            }

            final iterator this$1;

            ed()
            {
/* 239*/        this$1 = this._cls1.this;
/* 239*/        super();
            }
}
