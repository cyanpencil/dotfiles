// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Multiset, Multisets

abstract class AbstractMultiset extends AbstractCollection
    implements Multiset
{
    class EntrySet extends Multisets.EntrySet
    {

                Multiset multiset()
                {
/* 176*/            return AbstractMultiset.this;
                }

                public Iterator iterator()
                {
/* 180*/            return entryIterator();
                }

                public int size()
                {
/* 184*/            return distinctElements();
                }

                final AbstractMultiset this$0;

                EntrySet()
                {
/* 174*/            this$0 = AbstractMultiset.this;
/* 174*/            super();
                }
    }

    class ElementSet extends Multisets.ElementSet
    {

                Multiset multiset()
                {
/* 156*/            return AbstractMultiset.this;
                }

                final AbstractMultiset this$0;

                ElementSet()
                {
/* 153*/            this$0 = AbstractMultiset.this;
/* 153*/            super();
                }
    }


            AbstractMultiset()
            {
            }

            public int size()
            {
/*  52*/        return Multisets.sizeImpl(this);
            }

            public boolean isEmpty()
            {
/*  56*/        return entrySet().isEmpty();
            }

            public boolean contains(Object obj)
            {
/*  60*/        return count(obj) > 0;
            }

            public Iterator iterator()
            {
/*  64*/        return Multisets.iteratorImpl(this);
            }

            public int count(Object obj)
            {
                Multiset.Entry entry;
/*  69*/        for(Iterator iterator1 = entrySet().iterator(); iterator1.hasNext();)
/*  69*/            if(Objects.equal((entry = (Multiset.Entry)iterator1.next()).getElement(), obj))
/*  71*/                return entry.getCount();

/*  74*/        return 0;
            }

            public boolean add(Object obj)
            {
/*  80*/        add(obj, 1);
/*  81*/        return true;
            }

            public int add(Object obj, int i)
            {
/*  86*/        throw new UnsupportedOperationException();
            }

            public boolean remove(Object obj)
            {
/*  90*/        return remove(obj, 1) > 0;
            }

            public int remove(Object obj, int i)
            {
/*  95*/        throw new UnsupportedOperationException();
            }

            public int setCount(Object obj, int i)
            {
/* 100*/        return Multisets.setCountImpl(this, obj, i);
            }

            public boolean setCount(Object obj, int i, int j)
            {
/* 105*/        return Multisets.setCountImpl(this, obj, i, j);
            }

            public boolean addAll(Collection collection)
            {
/* 117*/        return Multisets.addAllImpl(this, collection);
            }

            public boolean removeAll(Collection collection)
            {
/* 121*/        return Multisets.removeAllImpl(this, collection);
            }

            public boolean retainAll(Collection collection)
            {
/* 125*/        return Multisets.retainAllImpl(this, collection);
            }

            public void clear()
            {
/* 129*/        Iterators.clear(entryIterator());
            }

            public Set elementSet()
            {
                Set set;
/* 138*/        if((set = elementSet) == null)
/* 140*/            elementSet = set = createElementSet();
/* 142*/        return set;
            }

            Set createElementSet()
            {
/* 150*/        return new ElementSet();
            }

            abstract Iterator entryIterator();

            abstract int distinctElements();

            public Set entrySet()
            {
                Set set;
/* 167*/        if((set = entrySet) == null)
/* 169*/            entrySet = set = createEntrySet();
/* 171*/        return set;
            }

            Set createEntrySet()
            {
/* 189*/        return new EntrySet();
            }

            public boolean equals(Object obj)
            {
/* 202*/        return Multisets.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 212*/        return entrySet().hashCode();
            }

            public String toString()
            {
/* 222*/        return entrySet().toString();
            }

            private transient Set elementSet;
            private transient Set entrySet;
}
