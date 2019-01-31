// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Sets, Iterables, Lists, 
//            ForwardingSet, UnmodifiableIterator, TransformedIterator, ForwardingMapEntry

static class predicate extends ap
{
    class KeySet extends Maps.KeySet
    {

                public boolean remove(Object obj)
                {
/*2736*/            if(containsKey(obj))
                    {
/*2737*/                unfiltered.remove(obj);
/*2738*/                return true;
                    } else
                    {
/*2740*/                return false;
                    }
                }

                private boolean removeIf(Predicate predicate)
                {
/*2744*/            return Iterables.removeIf(unfiltered.entrySet(), Predicates.and(Maps.FilteredEntryMap.this.predicate, Maps.keyPredicateOnEntries(predicate)));
                }

                public boolean removeAll(Collection collection)
                {
/*2750*/            return removeIf(Predicates.in(collection));
                }

                public boolean retainAll(Collection collection)
                {
/*2755*/            return removeIf(Predicates.not(Predicates.in(collection)));
                }

                public Object[] toArray()
                {
/*2760*/            return Lists.newArrayList(iterator()).toArray();
                }

                public Object[] toArray(Object aobj[])
                {
/*2764*/            return Lists.newArrayList(iterator()).toArray(aobj);
                }

                final Maps.FilteredEntryMap this$0;

                KeySet()
                {
/*2731*/            this$0 = Maps.FilteredEntryMap.this;
/*2732*/            super(Maps.FilteredEntryMap.this);
                }
    }

    class EntrySet extends ForwardingSet
    {

                protected Set _mthdelegate()
                {
/*2701*/            return filteredEntrySet;
                }

                public Iterator iterator()
                {
/*2705*/            return new TransformedIterator(filteredEntrySet.iterator()) {

                        java.util.Map.Entry transform(final java.util.Map.Entry entry)
                        {
/*2708*/                    return new ForwardingMapEntry() {

                                protected java.util.Map.Entry _mthdelegate()
                                {
/*2711*/                            return entry;
                                }

                                public Object setValue(Object obj)
                                {
/*2716*/                            Preconditions.checkArgument(apply(getKey(), obj));
/*2717*/                            return super.setValue(obj);
                                }

                                protected volatile Object _mthdelegate()
                                {
/*2708*/                            return _mthdelegate();
                                }

                                final java.util.Map.Entry val$entry;
                                final _cls1 this$2;

                                
                                {
/*2708*/                            this$2 = _cls1.this;
/*2708*/                            entry = entry1;
/*2708*/                            super();
                                }
                    };
                        }

                        volatile Object transform(Object obj)
                        {
/*2705*/                    return transform((java.util.Map.Entry)obj);
                        }

                        final EntrySet this$1;

                        
                        {
/*2705*/                    this$1 = EntrySet.this;
/*2705*/                    super(i