// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingObject, Multimap, Multiset

public abstract class ForwardingMultimap extends ForwardingObject
    implements Multimap
{

            protected ForwardingMultimap()
            {
            }

            protected abstract Multimap _mthdelegate();

            public Map asMap()
            {
/*  48*/        return _mthdelegate().asMap();
            }

            public void clear()
            {
/*  53*/        _mthdelegate().clear();
            }

            public boolean containsEntry(Object obj, Object obj1)
            {
/*  58*/        return _mthdelegate().containsEntry(obj, obj1);
            }

            public boolean containsKey(Object obj)
            {
/*  63*/        return _mthdelegate().containsKey(obj);
            }

            public boolean containsValue(Object obj)
            {
/*  68*/        return _mthdelegate().containsValue(obj);
            }

            public Collection entries()
            {
/*  73*/        return _mthdelegate().entries();
            }

            public Collection get(Object obj)
            {
/*  78*/        return _mthdelegate().get(obj);
            }

            public boolean isEmpty()
            {
/*  83*/        return _mthdelegate().isEmpty();
            }

            public Multiset keys()
            {
/*  88*/        return _mthdelegate().keys();
            }

            public Set keySet()
            {
/*  93*/        return _mthdelegate().keySet();
            }

            public boolean put(Object obj, Object obj1)
            {
/*  98*/        return _mthdelegate().put(obj, obj1);
            }

            public boolean putAll(Object obj, Iterable iterable)
            {
/* 103*/        return _mthdelegate().putAll(obj, iterable);
            }

            public boolean putAll(Multimap multimap)
            {
/* 108*/        return _mthdelegate().putAll(multimap);
            }

            public boolean remove(Object obj, Object obj1)
            {
/* 113*/        return _mthdelegate().remove(obj, obj1);
            }

            public Collection removeAll(Object obj)
            {
/* 118*/        return _mthdelegate().removeAll(obj);
            }

            public Collection replaceValues(Object obj, Iterable iterable)
            {
/* 123*/        return _mthdelegate().replaceValues(obj, iterable);
            }

            public int size()
            {
/* 128*/        return _mthdelegate().size();
            }

            public Collection values()
            {
/* 133*/        return _mthdelegate().values();
            }

            public boolean equals(Object obj)
            {
/* 137*/        return obj == this || _mthdelegate().equals(obj);
            }

            public int hashCode()
            {
/* 141*/        return _mthdelegate().hashCode();
            }

            protected volatile Object _mthdelegate()
            {
/*  37*/        return _mthdelegate();
            }
}
