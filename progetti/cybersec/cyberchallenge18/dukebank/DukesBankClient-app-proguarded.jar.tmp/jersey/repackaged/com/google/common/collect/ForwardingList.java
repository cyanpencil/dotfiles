// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingList.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingCollection

public abstract class ForwardingList extends ForwardingCollection
    implements List
{

            protected ForwardingList()
            {
            }

            protected abstract List _mthdelegate();

            public void add(int i, Object obj)
            {
/*  66*/        _mthdelegate().add(i, obj);
            }

            public boolean addAll(int i, Collection collection)
            {
/*  71*/        return _mthdelegate().addAll(i, collection);
            }

            public Object get(int i)
            {
/*  76*/        return _mthdelegate().get(i);
            }

            public int indexOf(Object obj)
            {
/*  81*/        return _mthdelegate().indexOf(obj);
            }

            public int lastIndexOf(Object obj)
            {
/*  86*/        return _mthdelegate().lastIndexOf(obj);
            }

            public ListIterator listIterator()
            {
/*  91*/        return _mthdelegate().listIterator();
            }

            public ListIterator listIterator(int i)
            {
/*  96*/        return _mthdelegate().listIterator(i);
            }

            public Object remove(int i)
            {
/* 101*/        return _mthdelegate().remove(i);
            }

            public Object set(int i, Object obj)
            {
/* 106*/        return _mthdelegate().set(i, obj);
            }

            public List subList(int i, int j)
            {
/* 111*/        return _mthdelegate().subList(i, j);
            }

            public boolean equals(Object obj)
            {
/* 115*/        return obj == this || _mthdelegate().equals(obj);
            }

            public int hashCode()
            {
/* 119*/        return _mthdelegate().hashCode();
            }

            protected volatile Collection _mthdelegate()
            {
/*  54*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  54*/        return _mthdelegate();
            }
}
