// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, Collections2, FilteredMultimap, FilteredMultimapValues, 
//            Maps, Multimap, SetMultimap, Sets, 
//            Multiset, CollectPreconditions, Multimaps, Multisets, 
//            Lists, Iterators, AbstractIterator

class FilteredEntryMultimap extends AbstractMultimap
    implements FilteredMultimap
{
    class Keys extends Multimaps.Keys
    {

                public int remove(Object obj, int i)
                {
/* 336*/            CollectPreconditions.checkNonnegative(i, "occurrences");
/* 337*/            if(i == 0)
/* 338*/                return count(obj);
                    Object obj1;
/* 340*/            if((obj1 = (Collection)unfiltered.asMap().get(obj)) == null)
/* 342*/                return 0;
/* 345*/            obj = obj;
/* 346*/            int j = 0;
/* 347*/            obj1 = ((Collection) (obj1)).iterator();
/* 348*/            do
                    {
/* 348*/                if(!((Iterator) (obj1)).hasNext())
/* 349*/                    break;
/* 349*/                Object obj2 = ((Iterator) (obj1)).next();
/* 350*/                if(satisfies(obj, obj2) && ++j <= i)
/* 353*/                    ((Iterator) (obj1)).remove();
                    } while(true);
/* 357*/            return j;
                }

                public Set entrySet()
                {
/* 362*/            return new Multisets.EntrySet() {

                        Multiset multiset()
                        {
/* 366*/                    return Keys.this;
                        }

                        public Iterator iterator()
                        {
/* 371*/                    return entryIterator();
                        }

                        public int size()
                        {
/* 376*/                    return keySet().size();
                        }

                        private boolean removeEntriesIf(final Predicate predicate)
                        {
/* 380*/                    return FilteredEntryMultimap.this.removeEntriesIf(new Predicate() {

                                public boolean apply(java.util.Map.Entry entry)
                                {
/* 384*/                            return predicate.apply(Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size()));
                                }

                                public volatile boolean apply(Object obj)
                                {
/* 381*/                            return apply((java.util.Map.Entry)obj);
                                }

                                final Predicate val$predicate;
                                final _cls1 this$2;

                                
                                {
/* 381*/                            this$2 = _cls1.this;
/* 381*/                            predicate = predicate1;
/* 381*/                            super();
                                }
                    });
                        }

                        public boolean removeAll(Collection collection)
                        {
/* 392*/                    return removeEntriesIf(Predicates.in(collection));
                        }

                        public boolean retainAll(Collection collection)
                        {
/* 397*/                    return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                        }

                        final Keys this$1;

                        
                        {
/* 362*/                    this$1 = Keys.this;
/* 362*/                    super();
                        }
            };
                }

                final FilteredEntryMultimap this$0;

                Keys()
                {
/* 330*/            this$0 = FilteredEntryMultimap.this;
/* 331*/            super(FilteredEntryMultimap.this);
                }
    }

    class AsMap extends Maps.ImprovedAbstractMap
    {

                public boolean containsKey(Object obj)
                {
/* 171*/            return get(obj) != null;
                }

                public void clear()
                {
/* 176*/            FilteredEntryMultimap.this.clear();
                }

                public Collection get(Object obj)
                {
                    Collection collection;
/* 181*/            if((collection = (Collection)unfiltered.asMap().get(obj)) == null)
/* 183*/                return null;
/* 186*/            obj = obj;
/* 187*/            if((collection = FilteredEntryMultimap.filterCollection(collection, new ValuePredicate(obj))).isEmpty())
/* 188*/                return null;
/* 188*/            else
/* 188*/                return collection;
                }

                public Collection remove(Object obj)
                {
                    Object obj1;
/* 193*/            if((obj1 = (Collection)unfiltered.asMap().get(obj)) == null)
/* 195*/                return null;
/* 198*/            obj = obj;
/* 199*/            ArrayList arraylist = Lists.newArrayList();
/* 200*/            obj1 = ((Collection) (obj1)).iterator();
/* 201*/            do
                    {
/* 201*/                if(!((Iterator) (obj1)).hasNext())
/* 202*/                    break;
/* 202*/                Object obj2 = ((Iterator) (obj1)).next();
/* 203*/                if(satisfies(obj, obj2))
                        {
/* 204*/                    ((Iterator) (obj1)).remove();
/* 205*/                    arraylist.add(obj2);
                        }
                    } while(true);
/* 208*/            if(arraylist.isEmpty())
/* 209*/                return null;
/* 210*/            if(unfiltered instanceof SetMultimap)
/* 211*/                return Collections.unmodifiableSet(Sets.newLinkedHashSet(arraylist));
/* 213*/            else
/* 213*/                return Collections.unmodifiableList(arraylist);
                }

                Set createKeySet()
                {
/* 219*/            return new Maps.KeySet(this) {

                        public boolean removeAll(Collection collection)
                        {
/* 222*/                    return removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(collection)));
                        }

                        public boolean retainAll(Collection collection)
                        {
/* 227*/                    return removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                        }

                        public boolean remove(Object obj)
                        {
/* 232*/                    return AsMap.this.remove(obj) != null;
                        }

                        final AsMap this$1;

                        
                        {
/* 219*/                    this$1 = AsMap.this;
/* 219*/                    super(map);
                        }
            };
                }

                Set createEntrySet()
                {
/* 239*/            return new Maps.EntrySet() {

                        Map map()
                        {
/* 242*/                    return AsMap.this;
                        }

                        public Iterator iterator()
                        {
/* 247*/                    return new AbstractIterator() {

                                protected java.util.Map.Entry computeNext()
                                {
/* 253*/                            while(backingIterator.hasNext()) 
                                    {
                                        Object obj;
/* 254*/                                Object obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)backingIterator.next())).getKey();
/* 256*/                                if(!((Collection) (obj = FilteredEntryMultimap.filterCollection((Collection)((java.util.Map.Entry) (obj)).getValue(), new ValuePredicate(obj1)))).isEmpty())
/* 259*/                                    return Maps.immutableEntry(obj1, obj);
                                    }
/* 262*/                            return (java.util.Map.Entry)endOfData();
                                }

                                protected volatile Object computeNext()
                                {
/* 247*/                            return computeNext();
                                }

                                final Iterator backingIterator;
                                final _cls2 this$2;

                                
                                {
/* 247*/                            this$2 = _cls2.this;
/* 247*/                            super();
/* 248*/                            backingIterator = unfiltered.asMap().entrySet().iterator();
                                }
                    };
                        }

                        public boolean removeAll(Collection collection)
                        {
/* 269*/                    return removeEntriesIf(Predicates.in(collection));
                        }

                        public boolean retainAll(Collection collection)
                        {
/* 274*/                    return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                        }

                        public int size()
                        {
/* 279*/                    return Iterators.size(iterator());
                        }

                        final AsMap this$1;

                        
                        {
/* 239*/                    this$1 = AsMap.this;
/* 239*/                    super();
                        }
            };
                }

                Collection createValues()
                {
/* 286*/            return new Maps.Values(this) {

                        public boolean remove(Object obj)
                        {
/* 289*/label0:
                            {
/* 289*/                        if(!(obj instanceof Collection))
/* 290*/                            break label0;
/* 290*/                        obj = (Collection)obj;
/* 291*/                        Iterator iterator = unfiltered.asMap().entrySet().iterator();
                                java.util.Map.Entry entry;
                                Object obj1;
/* 293*/                        do
                                {
/* 293*/                            if(!iterator.hasNext())
/* 294*/                                break label0;
/* 294*/                            obj1 = (entry = (java.util.Map.Entry)iterator.next()).getKey();
                                } while(((Collection) (obj1 = FilteredEntryMultimap.filterCollection((Collection)entry.getValue(), new ValuePredicate(obj1)))).isEmpty() || !((Collection) (obj)).equals(obj1));
/* 299*/                        if(((Collection) (obj1)).size() == ((Collection)entry.getValue()).size())
/* 300*/                            iterator.remove();
/* 302*/                        else
/* 302*/                            ((Collection) (obj1)).clear();
/* 304*/                        return true;
                            }
/* 308*/                    return false;
                        }

                        public boolean removeAll(Collection collection)
                        {
/* 313*/                    return removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                        }

                        public boolean retainAll(Collection collection)
                        {
/* 318*/                    return removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                        }

                        final AsMap this$1;

                        
                        {
/* 286*/                    this$1 = AsMap.this;
/* 286*/                    super(map);
                        }
            };
                }

                public volatile Object remove(Object obj)
                {
/* 168*/            return remove(obj);
                }

                public volatile Object get(Object obj)
                {
/* 168*/            return get(obj);
                }

                final FilteredEntryMultimap this$0;

                AsMap()
                {
/* 168*/            this$0 = FilteredEntryMultimap.this;
/* 168*/            super();
                }
    }

    final class ValuePredicate
        implements Predicate
    {

                public final boolean apply(Object obj)
                {
/*  84*/            return satisfies(key, obj);
                }

                private final Object key;
                final FilteredEntryMultimap this$0;

                ValuePredicate(Object obj)
                {
/*  78*/            this$0 = FilteredEntryMultimap.this;
/*  78*/            super();
/*  79*/            key = obj;
                }
    }


            FilteredEntryMultimap(Multimap multimap, Predicate predicate1)
            {
/*  51*/        unfiltered = (Multimap)Preconditions.checkNotNull(multimap);
/*  52*/        predicate = (Predicate)Preconditions.checkNotNull(predicate1);
            }

            public Multimap unfiltered()
            {
/*  57*/        return unfiltered;
            }

            public Predicate entryPredicate()
            {
/*  62*/        return predicate;
            }

            public int size()
            {
/*  67*/        return entries().size();
            }

            private boolean satisfies(Object obj, Object obj1)
            {
/*  71*/        return predicate.apply(Maps.immutableEntry(obj, obj1));
            }

            static Collection filterCollection(Collection collection, Predicate predicate1)
            {
/*  90*/        if(collection instanceof Set)
/*  91*/            return Sets.filter((Set)collection, predicate1);
/*  93*/        else
/*  93*/            return Collections2.filter(collection, predicate1);
            }

            public boolean containsKey(Object obj)
            {
/*  99*/        return asMap().get(obj) != null;
            }

            public Collection removeAll(Object obj)
            {
/* 104*/        return (Collection)MoreObjects.firstNonNull(asMap().remove(obj), unmodifiableEmptyCollection());
            }

            Collection unmodifiableEmptyCollection()
            {
/* 109*/        if(unfiltered instanceof SetMultimap)
/* 109*/            return Collections.emptySet();
/* 109*/        else
/* 109*/            return Collections.emptyList();
            }

            public void clear()
            {
/* 116*/        entries().clear();
            }

            public Collection get(Object obj)
            {
/* 121*/        return filterCollection(unfiltered.get(obj), new ValuePredicate(obj));
            }

            Collection createEntries()
            {
/* 126*/        return filterCollection(unfiltered.entries(), predicate);
            }

            Collection createValues()
            {
/* 131*/        return new FilteredMultimapValues(this);
            }

            Iterator entryIterator()
            {
/* 136*/        throw new AssertionError("should never be called");
            }

            Map createAsMap()
            {
/* 141*/        return new AsMap();
            }

            public Set keySet()
            {
/* 146*/        return asMap().keySet();
            }

            boolean removeEntriesIf(Predicate predicate1)
            {
/* 150*/        Iterator iterator = unfiltered.asMap().entrySet().iterator();
/* 151*/        boolean flag = false;
/* 152*/        do
                {
/* 152*/            if(!iterator.hasNext())
/* 153*/                break;
                    java.util.Map.Entry entry;
/* 153*/            Object obj = (entry = (java.util.Map.Entry)iterator.next()).getKey();
                    Collection collection;
/* 155*/            if(!(collection = filterCollection((Collection)entry.getValue(), new ValuePredicate(obj))).isEmpty() && predicate1.apply(Maps.immutableEntry(obj, collection)))
                    {
/* 157*/                if(collection.size() == ((Collection)entry.getValue()).size())
/* 158*/                    iterator.remove();
/* 160*/                else
/* 160*/                    collection.clear();
/* 162*/                flag = true;
                    }
                } while(true);
/* 165*/        return flag;
            }

            Multiset createKeys()
            {
/* 326*/        return new Keys();
            }

            final Multimap unfiltered;
            final Predicate predicate;

}
