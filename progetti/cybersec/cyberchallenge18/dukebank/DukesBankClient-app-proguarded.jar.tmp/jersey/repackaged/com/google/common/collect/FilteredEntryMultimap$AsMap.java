// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredEntryMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicates;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FilteredEntryMultimap, Lists, Maps, Multimap, 
//            SetMultimap, Sets, Iterators, AbstractIterator

class it> extends it>
{

            public boolean containsKey(Object obj)
            {
/* 171*/        return get(obj) != null;
            }

            public void clear()
            {
/* 176*/        FilteredEntryMultimap.this.clear();
            }

            public Collection get(Object obj)
            {
                Collection collection;
/* 181*/        if((collection = (Collection)unfiltered.asMap().get(obj)) == null)
/* 183*/            return null;
/* 186*/        obj = obj;
/* 187*/        if((collection = FilteredEntryMultimap.filterCollection(collection, new redicate(FilteredEntryMultimap.this, obj))).isEmpty())
/* 188*/            return null;
/* 188*/        else
/* 188*/            return collection;
            }

            public Collection remove(Object obj)
            {
                Object obj1;
/* 193*/        if((obj1 = (Collection)unfiltered.asMap().get(obj)) == null)
/* 195*/            return null;
/* 198*/        obj = obj;
/* 199*/        ArrayList arraylist = Lists.newArrayList();
/* 200*/        obj1 = ((Collection) (obj1)).iterator();
/* 201*/        do
                {
/* 201*/            if(!((Iterator) (obj1)).hasNext())
/* 202*/                break;
/* 202*/            Object obj2 = ((Iterator) (obj1)).next();
/* 203*/            if(FilteredEntryMultimap.access$000(FilteredEntryMultimap.this, obj, obj2))
                    {
/* 204*/                ((Iterator) (obj1)).remove();
/* 205*/                arraylist.add(obj2);
                    }
                } while(true);
/* 208*/        if(arraylist.isEmpty())
/* 209*/            return null;
/* 210*/        if(unfiltered instanceof SetMultimap)
/* 211*/            return Collections.unmodifiableSet(Sets.newLinkedHashSet(arraylist));
/* 213*/        else
/* 213*/            return Collections.unmodifiableList(arraylist);
            }

            Set createKeySet()
            {
/* 219*/        return new Maps.KeySet(this) {

                    public boolean removeAll(Collection collection)
                    {
/* 222*/                return removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(collection)));
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 227*/                return removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                    }

                    public boolean remove(Object obj)
                    {
/* 232*/                return FilteredEntryMultimap.AsMap.this.remove(obj) != null;
                    }

                    final FilteredEntryMultimap.AsMap this$1;

                    
                    {
/* 219*/                this$1 = FilteredEntryMultimap.AsMap.this;
/* 219*/                super(map);
                    }
        };
            }

            Set createEntrySet()
            {
/* 239*/        return new Maps.EntrySet() {

                    Map map()
                    {
/* 242*/                return FilteredEntryMultimap.AsMap.this;
                    }

                    public Iterator iterator()
                    {
/* 247*/                return new AbstractIterator() {

                            protected java.util.Map.Entry computeNext()
                            {
/* 253*/                        while(backingIterator.hasNext()) 
                                {
                                    Object obj;
/* 254*/                            Object obj1 = ((java.util.Map.Entry) (obj = (java.util.Map.Entry)backingIterator.next())).getKey();
/* 256*/                            if(!((Collection) (obj = FilteredEntryMultimap.filterCollection((Collection)((java.util.Map.Entry) (obj)).getValue(), new FilteredEntryMultimap.ValuePredicate(this$0, obj1)))).isEmpty())
/* 259*/                                return Maps.immutableEntry(obj1, obj);
                                }
/* 262*/                        return (java.util.Map.Entry)endOfData();
                            }

                            protected volatile Object computeNext()
                            {
/* 247*/                        return computeNext();
                            }

                            final Iterator backingIterator;
                            final _cls2 this$2;

                            
                            {
/* 247*/                        this$2 = _cls2.this;
/* 247*/                        super();
/* 248*/                        backingIterator = unfiltered.asMap().entrySet().iterator();
                            }
                };
                    }

                    public boolean removeAll(Collection collection)
                    {
/* 269*/                return removeEntriesIf(Predicates.in(collection));
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 274*/                return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                    }

                    public int size()
                    {
/* 279*/                return Iterators.size(iterator());
                    }

                    final FilteredEntryMultimap.AsMap this$1;

                    
                    {
/* 239*/                this$1 = FilteredEntryMultimap.AsMap.this;
/* 239*/                super();
                    }
        };
            }

            Collection createValues()
            {
/* 286*/        return new Maps.Values(this) {

                    public boolean remove(Object obj)
                    {
/* 289*/label0:
                        {
/* 289*/                    if(!(obj instanceof Collection))
/* 290*/                        break label0;
/* 290*/                    obj = (Collection)obj;
/* 291*/                    Iterator iterator = unfiltered.asMap().entrySet().iterator();
                            java.util.Map.Entry entry;
                            Object obj1;
/* 293*/                    do
                            {
/* 293*/                        if(!iterator.hasNext())
/* 294*/                            break label0;
/* 294*/                        obj1 = (entry = (java.util.Map.Entry)iterator.next()).getKey();
                            } while(((Collection) (obj1 = FilteredEntryMultimap.filterCollection((Collection)entry.getValue(), new FilteredEntryMultimap.ValuePredicate(this$0, obj1)))).isEmpty() || !((Collection) (obj)).equals(obj1));
/* 299*/                    if(((Collection) (obj1)).size() == ((Collection)entry.getValue()).size())
/* 300*/                        iterator.remove();
/* 302*/                    else
/* 302*/                        ((Collection) (obj1)).clear();
/* 304*/                    return true;
                        }
/* 308*/                return false;
                    }

                    public boolean removeAll(Collection collection)
                    {
/* 313*/                return removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 318*/                return removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                    }

                    final FilteredEntryMultimap.AsMap this$1;

                    
                    {
/* 286*/                this$1 = FilteredEntryMultimap.AsMap.this;
/* 286*/                super(map);
                    }
        };
            }

            public volatile Object remove(Object obj)
            {
/* 168*/        return remove(obj);
            }

            public volatile Object get(Object obj)
            {
/* 168*/        return get(obj);
            }

            final FilteredEntryMultimap this$0;

            _cls1.this._cls1()
            {
/* 168*/        this$0 = FilteredEntryMultimap.this;
/* 168*/        super();
            }
}
