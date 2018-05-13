// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FilteredKeyMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, FilteredMultimap, FilteredMultimapValues, ImmutableList, 
//            ImmutableSet, Maps, Multimap, Multisets, 
//            SetMultimap, Sets, Multiset, ForwardingCollection, 
//            Collections2, ForwardingList, ForwardingSet

class FilteredKeyMultimap extends AbstractMultimap
    implements FilteredMultimap
{
    class Entries extends ForwardingCollection
    {

                protected Collection _mthdelegate()
                {
/* 186*/            return Collections2.filter(unfiltered.entries(), entryPredicate());
                }

                public boolean remove(Object obj)
                {
/* 192*/            if(obj instanceof java.util.Map.Entry)
                    {
/* 193*/                obj = (java.util.Map.Entry)obj;
/* 194*/                if(unfiltered.containsKey(((java.util.Map.Entry) (obj)).getKey()) && keyPredicate.apply(((java.util.Map.Entry) (obj)).getKey()))
/* 197*/                    return unfiltered.remove(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
                    }
/* 200*/            return false;
                }

                protected volatile Object _mthdelegate()
                {
/* 183*/            return _mthdelegate();
                }

                final FilteredKeyMultimap this$0;

                Entries()
                {
/* 183*/            this$0 = FilteredKeyMultimap.this;
/* 183*/            super();
                }
    }

    static class AddRejectingList extends ForwardingList
    {

                public boolean add(Object obj)
                {
/* 144*/            add(0, obj);
/* 145*/            return true;
                }

                public boolean addAll(Collection collection)
                {
/* 150*/            addAll(0, collection);
/* 151*/            return true;
                }

                public void add(int i, Object obj)
                {
/* 156*/            Preconditions.checkPositionIndex(i, 0);
/* 157*/            i = String.valueOf(String.valueOf(key));
/* 157*/            throw new IllegalArgumentException((new StringBuilder(32 + i.length())).append("Key does not satisfy predicate: ").append(i).toString());
                }

                public boolean addAll(int i, Collection collection)
                {
/* 162*/            Preconditions.checkNotNull(collection);
/* 163*/            Preconditions.checkPositionIndex(i, 0);
/* 164*/            i = String.valueOf(String.valueOf(key));
/* 164*/            throw new IllegalArgumentException((new StringBuilder(32 + i.length())).append("Key does not satisfy predicate: ").append(i).toString());
                }

                protected List _mthdelegate()
                {
/* 169*/            return Collections.emptyList();
                }

                protected volatile Collection _mthdelegate()
                {
/* 135*/            return _mthdelegate();
                }

                protected volatile Object _mthdelegate()
                {
/* 135*/            return _mthdelegate();
                }

                final Object key;

                AddRejectingList(Object obj)
                {
/* 139*/            key = obj;
                }
    }

    static class AddRejectingSet extends ForwardingSet
    {

                public boolean add(Object obj)
                {
/* 120*/            obj = String.valueOf(String.valueOf(key));
/* 120*/            throw new IllegalArgumentException((new StringBuilder(32 + ((String) (obj)).length())).append("Key does not satisfy predicate: ").append(((String) (obj))).toString());
                }

                public boolean addAll(Collection collection)
                {
/* 125*/            Preconditions.checkNotNull(collection);
/* 126*/            collection = String.valueOf(String.valueOf(key));
/* 126*/            throw new IllegalArgumentException((new StringBuilder(32 + collection.length())).append("Key does not satisfy predicate: ").append(collection).toString());
                }

                protected Set _mthdelegate()
                {
/* 131*/            return Collections.emptySet();
                }

                protected volatile Collection _mthdelegate()
                {
/* 111*/            return _mthdelegate();
                }

                protected volatile Object _mthdelegate()
                {
/* 111*/            return _mthdelegate();
                }

                final Object key;

                AddRejectingSet(Object obj)
                {
/* 115*/            key = obj;
                }
    }


            FilteredKeyMultimap(Multimap multimap, Predicate predicate)
            {
/*  44*/        unfiltered = (Multimap)Preconditions.checkNotNull(multimap);
/*  45*/        keyPredicate = (Predicate)Preconditions.checkNotNull(predicate);
            }

            public Multimap unfiltered()
            {
/*  50*/        return unfiltered;
            }

            public Predicate entryPredicate()
            {
/*  55*/        return Maps.keyPredicateOnEntries(keyPredicate);
            }

            public int size()
            {
/*  60*/        int i = 0;
/*  61*/        for(Iterator iterator = asMap().values().iterator(); iterator.hasNext();)
                {
/*  61*/            Collection collection = (Collection)iterator.next();
/*  62*/            i += collection.size();
                }

/*  64*/        return i;
            }

            public boolean containsKey(Object obj)
            {
/*  69*/        if(unfiltered.containsKey(obj))
                {
/*  71*/            obj = obj;
/*  72*/            return keyPredicate.apply(obj);
                } else
                {
/*  74*/            return false;
                }
            }

            public Collection removeAll(Object obj)
            {
/*  79*/        if(containsKey(obj))
/*  79*/            return unfiltered.removeAll(obj);
/*  79*/        else
/*  79*/            return unmodifiableEmptyCollection();
            }

            Collection unmodifiableEmptyCollection()
            {
/*  83*/        if(unfiltered instanceof SetMultimap)
/*  84*/            return ImmutableSet.of();
/*  86*/        else
/*  86*/            return ImmutableList.of();
            }

            public void clear()
            {
/*  92*/        keySet().clear();
            }

            Set createKeySet()
            {
/*  97*/        return Sets.filter(unfiltered.keySet(), keyPredicate);
            }

            public Collection get(Object obj)
            {
/* 102*/        if(keyPredicate.apply(obj))
/* 103*/            return unfiltered.get(obj);
/* 104*/        if(unfiltered instanceof SetMultimap)
/* 105*/            return new AddRejectingSet(obj);
/* 107*/        else
/* 107*/            return new AddRejectingList(obj);
            }

            Iterator entryIterator()
            {
/* 175*/        throw new AssertionError("should never be called");
            }

            Collection createEntries()
            {
/* 180*/        return new Entries();
            }

            Collection createValues()
            {
/* 206*/        return new FilteredMultimapValues(this);
            }

            Map createAsMap()
            {
/* 211*/        return Maps.filterKeys(unfiltered.asMap(), keyPredicate);
            }

            Multiset createKeys()
            {
/* 216*/        return Multisets.filter(unfiltered.keys(), keyPredicate);
            }

            final Multimap unfiltered;
            final Predicate keyPredicate;
}
