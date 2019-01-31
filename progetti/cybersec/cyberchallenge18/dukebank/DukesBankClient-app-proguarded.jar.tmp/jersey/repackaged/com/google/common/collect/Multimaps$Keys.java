// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultiset, CollectPreconditions, Maps, Multimap, 
//            Multimaps, Multiset, Multisets, TransformedIterator

static class multimap extends AbstractMultiset
{
    class KeysEntrySet extends Multisets.EntrySet
    {

                Multiset multiset()
                {
/*1552*/            return Multimaps.Keys.this;
                }

                public Iterator iterator()
                {
/*1556*/            return entryIterator();
                }

                public int size()
                {
/*1560*/            return distinctElements();
                }

                public boolean isEmpty()
                {
/*1564*/            return multimap.isEmpty();
                }

                public boolean contains(Object obj)
                {
/*1568*/            if(obj instanceof Multiset.Entry)
                    {
/*1569*/                obj = (Multiset.Entry)obj;
                        Collection collection;
/*1570*/                return (collection = (Collection)multimap.asMap().get(((Multiset.Entry) (obj)).getElement())) != null && collection.size() == ((Multiset.Entry) (obj)).getCount();
                    } else
                    {
/*1573*/                return false;
                    }
                }

                public boolean remove(Object obj)
                {
/*1577*/            if(obj instanceof Multiset.Entry)
                    {
/*1578*/                obj = (Multiset.Entry)obj;
                        Collection collection;
/*1579*/                if((collection = (Collection)multimap.asMap().get(((Multiset.Entry) (obj)).getElement())) != null && collection.size() == ((Multiset.Entry) (obj)).getCount())
                        {
/*1581*/                    collection.clear();
/*1582*/                    return true;
                        }
                    }
/*1585*/            return false;
                }

                final Multimaps.Keys this$0;

                KeysEntrySet()
                {
/*1550*/            this$0 = Multimaps.Keys.this;
/*1550*/            super();
                }
    }


            Iterator entryIterator()
            {
/*1522*/        return new TransformedIterator(multimap.asMap().entrySet().iterator()) {

                    Multiset.Entry transform(final java.util.Map.Entry backingEntry)
                    {
/*1527*/                return new Multisets.AbstractEntry() {

                            public Object getElement()
                            {
/*1530*/                        return backingEntry.getKey();
                            }

                            public int getCount()
                            {
/*1535*/                        return ((Collection)backingEntry.getValue()).size();
                            }

                            final java.util.Map.Entry val$backingEntry;
                            final _cls1 this$1;

                            
                            {
/*1527*/                        this$1 = _cls1.this;
/*1527*/                        backingEntry = entry;
/*1527*/                        super();
                            }
                };
                    }

                    volatile Object transform(Object obj)
                    {
/*1523*/                return transform((java.util.Map.Entry)obj);
                    }

                    final Multimaps.Keys this$0;

                    
                    {
/*1523*/                this$0 = Multimaps.Keys.this;
/*1523*/                super(iterator1);
                    }
        };
            }

            int distinctElements()
            {
/*1543*/        return multimap.asMap().size();
            }

            Set createEntrySet()
            {
/*1547*/        return new KeysEntrySet();
            }

            public boolean contains(Object obj)
            {
/*1590*/        return multimap.containsKey(obj);
            }

            public Iterator iterator()
            {
/*1594*/        return Maps.keyIterator(multimap.entries().iterator());
            }

            public int count(Object obj)
            {
/*1598*/        if((obj = (Collection)Maps.safeGet(multimap.asMap(), obj)) == null)
/*1599*/            return 0;
/*1599*/        else
/*1599*/            return ((Collection) (obj)).size();
            }

            public int remove(Object obj, int i)
            {
/*1603*/        CollectPreconditions.checkNonnegative(i, "occurrences");
/*1604*/        if(i == 0)
/*1605*/            return count(obj);
/*1608*/        if((obj = (Collection)Maps.safeGet(multimap.asMap(), obj)) == null)
/*1611*/            return 0;
/*1614*/        int j = ((Collection) (obj)).size();
/*1615*/        if(i >= j)
                {
/*1616*/            ((Collection) (obj)).clear();
                } else
                {
/*1618*/            obj = ((Collection) (obj)).iterator();
/*1619*/            for(int k = 0; k < i; k++)
                    {
/*1620*/                ((Iterator) (obj)).next();
/*1621*/                ((Iterator) (obj)).remove();
                    }

                }
/*1624*/        return j;
            }

            public void clear()
            {
/*1628*/        multimap.clear();
            }

            public Set elementSet()
            {
/*1632*/        return multimap.keySet();
            }

            final Multimap multimap;

            ator(Multimap multimap1)
            {
/*1518*/        multimap = multimap1;
            }
}
