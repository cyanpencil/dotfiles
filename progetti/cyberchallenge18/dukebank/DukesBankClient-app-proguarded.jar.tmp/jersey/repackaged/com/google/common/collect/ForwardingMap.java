// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingObject, Maps

public abstract class ForwardingMap extends ForwardingObject
    implements Map
{

            protected ForwardingMap()
            {
            }

            protected abstract Map _mthdelegate();

            public int size()
            {
/*  70*/        return _mthdelegate().size();
            }

            public boolean isEmpty()
            {
/*  75*/        return _mthdelegate().isEmpty();
            }

            public Object remove(Object obj)
            {
/*  80*/        return _mthdelegate().remove(obj);
            }

            public void clear()
            {
/*  85*/        _mthdelegate().clear();
            }

            public boolean containsKey(Object obj)
            {
/*  90*/        return _mthdelegate().containsKey(obj);
            }

            public boolean containsValue(Object obj)
            {
/*  95*/        return _mthdelegate().containsValue(obj);
            }

            public Object get(Object obj)
            {
/* 100*/        return _mthdelegate().get(obj);
            }

            public Object put(Object obj, Object obj1)
            {
/* 105*/        return _mthdelegate().put(obj, obj1);
            }

            public void putAll(Map map)
            {
/* 110*/        _mthdelegate().putAll(map);
            }

            public Set keySet()
            {
/* 115*/        return _mthdelegate().keySet();
            }

            public Collection values()
            {
/* 120*/        return _mthdelegate().values();
            }

            public Set entrySet()
            {
/* 125*/        return _mthdelegate().entrySet();
            }

            public boolean equals(Object obj)
            {
/* 129*/        return obj == this || _mthdelegate().equals(obj);
            }

            public int hashCode()
            {
/* 133*/        return _mthdelegate().hashCode();
            }

            protected String standardToString()
            {
/* 307*/        return Maps.toStringImpl(this);
            }

            protected volatile Object _mthdelegate()
            {
/*  58*/        return _mthdelegate();
            }
}
