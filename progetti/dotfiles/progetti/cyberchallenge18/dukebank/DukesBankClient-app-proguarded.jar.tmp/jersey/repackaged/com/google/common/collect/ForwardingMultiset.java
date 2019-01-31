// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingCollection, Multiset

public abstract class ForwardingMultiset extends ForwardingCollection
    implements Multiset
{

            protected ForwardingMultiset()
            {
            }

            protected abstract Multiset _mthdelegate();

            public int count(Object obj)
            {
/*  62*/        return _mthdelegate().count(obj);
            }

            public int add(Object obj, int i)
            {
/*  67*/        return _mthdelegate().add(obj, i);
            }

            public int remove(Object obj, int i)
            {
/*  72*/        return _mthdelegate().remove(obj, i);
            }

            public Set elementSet()
            {
/*  77*/        return _mthdelegate().elementSet();
            }

            public Set entrySet()
            {
/*  82*/        return _mthdelegate().entrySet();
            }

            public boolean equals(Object obj)
            {
/*  86*/        return obj == this || _mthdelegate().equals(obj);
            }

            public int hashCode()
            {
/*  90*/        return _mthdelegate().hashCode();
            }

            public int setCount(Object obj, int i)
            {
/*  95*/        return _mthdelegate().setCount(obj, i);
            }

            public boolean setCount(Object obj, int i, int j)
            {
/* 100*/        return _mthdelegate().setCount(obj, i, j);
            }

            protected volatile Collection _mthdelegate()
            {
/*  51*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  51*/        return _mthdelegate();
            }
}
