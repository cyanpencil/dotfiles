// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingMultiset, Iterators, Multiset, Multisets

static class delegate extends ForwardingMultiset
    implements Serializable
{

            protected Multiset _mthdelegate()
            {
/* 106*/        return _flddelegate;
            }

            Set createElementSet()
            {
/* 112*/        return Collections.unmodifiableSet(_flddelegate.elementSet());
            }

            public Set elementSet()
            {
                Set set;
/* 117*/        if((set = elementSet) == null)
/* 118*/            return elementSet = createElementSet();
/* 118*/        else
/* 118*/            return set;
            }

            public Set entrySet()
            {
                Set set;
/* 125*/        if((set = entrySet) == null)
/* 126*/            return entrySet = Collections.unmodifiableSet(_flddelegate.entrySet());
/* 126*/        else
/* 126*/            return set;
            }

            public Iterator iterator()
            {
/* 136*/        return Iterators.unmodifiableIterator(_flddelegate.iterator());
            }

            public boolean add(Object obj)
            {
/* 140*/        throw new UnsupportedOperationException();
            }

            public int add(Object obj, int i)
            {
/* 144*/        throw new UnsupportedOperationException();
            }

            public boolean addAll(Collection collection)
            {
/* 148*/        throw new UnsupportedOperationException();
            }

            public boolean remove(Object obj)
            {
/* 152*/        throw new UnsupportedOperationException();
            }

            public int remove(Object obj, int i)
            {
/* 156*/        throw new UnsupportedOperationException();
            }

            public boolean removeAll(Collection collection)
            {
/* 160*/        throw new UnsupportedOperationException();
            }

            public boolean retainAll(Collection collection)
            {
/* 164*/        throw new UnsupportedOperationException();
            }

            public void clear()
            {
/* 168*/        throw new UnsupportedOperationException();
            }

            public int setCount(Object obj, int i)
            {
/* 172*/        throw new UnsupportedOperationException();
            }

            public boolean setCount(Object obj, int i, int j)
            {
/* 176*/        throw new UnsupportedOperationException();
            }

            protected volatile Collection _mthdelegate()
            {
/*  95*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  95*/        return _mthdelegate();
            }

            final Multiset _flddelegate;
            transient Set elementSet;
            transient Set entrySet;

            (Multiset multiset)
            {
/* 100*/        _flddelegate = multiset;
            }
}
