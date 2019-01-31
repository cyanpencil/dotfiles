// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingCollection.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingObject, ObjectArrays

public abstract class ForwardingCollection extends ForwardingObject
    implements Collection
{

            protected ForwardingCollection()
            {
            }

            protected abstract Collection _mthdelegate();

            public Iterator iterator()
            {
/*  59*/        return _mthdelegate().iterator();
            }

            public int size()
            {
/*  64*/        return _mthdelegate().size();
            }

            public boolean removeAll(Collection collection)
            {
/*  69*/        return _mthdelegate().removeAll(collection);
            }

            public boolean isEmpty()
            {
/*  74*/        return _mthdelegate().isEmpty();
            }

            public boolean contains(Object obj)
            {
/*  79*/        return _mthdelegate().contains(obj);
            }

            public boolean add(Object obj)
            {
/*  84*/        return _mthdelegate().add(obj);
            }

            public boolean remove(Object obj)
            {
/*  89*/        return _mthdelegate().remove(obj);
            }

            public boolean containsAll(Collection collection)
            {
/*  94*/        return _mthdelegate().containsAll(collection);
            }

            public boolean addAll(Collection collection)
            {
/*  99*/        return _mthdelegate().addAll(collection);
            }

            public boolean retainAll(Collection collection)
            {
/* 104*/        return _mthdelegate().retainAll(collection);
            }

            public void clear()
            {
/* 109*/        _mthdelegate().clear();
            }

            public Object[] toArray()
            {
/* 114*/        return _mthdelegate().toArray();
            }

            public Object[] toArray(Object aobj[])
            {
/* 119*/        return _mthdelegate().toArray(aobj);
            }

            protected Object[] standardToArray()
            {
/* 241*/        Object aobj[] = new Object[size()];
/* 242*/        return toArray(aobj);
            }

            protected Object[] standardToArray(Object aobj[])
            {
/* 253*/        return ObjectArrays.toArrayImpl(this, aobj);
            }

            protected volatile Object _mthdelegate()
            {
/*  47*/        return _mthdelegate();
            }
}
