// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Set;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultiset, CollectPreconditions, Iterators, Multiset, 
//            Multisets, Sets, UnmodifiableIterator

static final class predicate extends AbstractMultiset
{

            public final UnmodifiableIterator iterator()
            {
/* 289*/        return Iterators.filter(unfiltered.iterator(), predicate);
            }

            final Set createElementSet()
            {
/* 294*/        return Sets.filter(unfiltered.elementSet(), predicate);
            }

            final Set createEntrySet()
            {
/* 299*/        return Sets.filter(unfiltered.entrySet(), new Predicate() {

                    public boolean apply(Multiset.Entry entry)
                    {
/* 302*/                return predicate.apply(entry.getElement());
                    }

                    public volatile boolean apply(Object obj)
                    {
/* 299*/                return apply((Multiset.Entry)obj);
                    }

                    final Multisets.FilteredMultiset this$0;

                    
                    {
/* 299*/                this$0 = Multisets.FilteredMultiset.this;
/* 299*/                super();
                    }
        });
            }

            final Iterator entryIterator()
            {
/* 309*/        throw new AssertionError("should never be called");
            }

            final int distinctElements()
            {
/* 314*/        return elementSet().size();
            }

            public final int count(Object obj)
            {
                int i;
/* 319*/        if((i = unfiltered.count(obj)) > 0)
                {
/* 322*/            obj = obj;
/* 323*/            if(predicate.apply(obj))
/* 323*/                return i;
/* 323*/            else
/* 323*/                return 0;
                } else
                {
/* 325*/            return 0;
                }
            }

            public final int add(Object obj, int i)
            {
/* 330*/        Preconditions.checkArgument(predicate.apply(obj), "Element %s does not match predicate %s", new Object[] {
/* 330*/            obj, predicate
                });
/* 332*/        return unfiltered.add(obj, i);
            }

            public final int remove(Object obj, int i)
            {
/* 337*/        CollectPreconditions.checkNonnegative(i, "occurrences");
/* 338*/        if(i == 0)
/* 339*/            return count(obj);
/* 341*/        if(contains(obj))
/* 341*/            return unfiltered.remove(obj, i);
/* 341*/        else
/* 341*/            return 0;
            }

            public final void clear()
            {
/* 347*/        elementSet().clear();
            }

            public final volatile Iterator iterator()
            {
/* 278*/        return iterator();
            }

            final Multiset unfiltered;
            final Predicate predicate;

            _cls1.this._cls0(Multiset multiset, Predicate predicate1)
            {
/* 283*/        unfiltered = (Multiset)Preconditions.checkNotNull(multiset);
/* 284*/        predicate = (Predicate)Preconditions.checkNotNull(predicate1);
            }
}
