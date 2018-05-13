// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultivaluedMap.java

package org.glassfish.jersey.internal.util.collection;

import java.util.*;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class ImmutableMultivaluedMap
    implements MultivaluedMap
{

            public static ImmutableMultivaluedMap empty()
            {
/*  67*/        return new ImmutableMultivaluedMap(new MultivaluedHashMap());
            }

            public ImmutableMultivaluedMap(MultivaluedMap multivaluedmap)
            {
/*  78*/        if(multivaluedmap == null)
                {
/*  79*/            throw new NullPointerException("ImmutableMultivaluedMap delegate must not be 'null'.");
                } else
                {
/*  81*/            _flddelegate = multivaluedmap;
/*  82*/            return;
                }
            }

            public boolean equalsIgnoreValueOrder(MultivaluedMap multivaluedmap)
            {
/*  86*/        return _flddelegate.equalsIgnoreValueOrder(multivaluedmap);
            }

            public void putSingle(Object obj, Object obj1)
            {
/*  91*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public void add(Object obj, Object obj1)
            {
/*  96*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public Object getFirst(Object obj)
            {
/* 101*/        return _flddelegate.getFirst(obj);
            }

            public transient void addAll(Object obj, Object aobj[])
            {
/* 106*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public void addAll(Object obj, List list)
            {
/* 111*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public void addFirst(Object obj, Object obj1)
            {
/* 116*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public int size()
            {
/* 121*/        return _flddelegate.size();
            }

            public boolean isEmpty()
            {
/* 126*/        return _flddelegate.isEmpty();
            }

            public boolean containsKey(Object obj)
            {
/* 131*/        return _flddelegate.containsKey(obj);
            }

            public boolean containsValue(Object obj)
            {
/* 136*/        return _flddelegate.containsValue(obj);
            }

            public List get(Object obj)
            {
/* 141*/        return (List)_flddelegate.get(obj);
            }

            public List put(Object obj, List list)
            {
/* 146*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public List remove(Object obj)
            {
/* 151*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public void putAll(Map map)
            {
/* 156*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public void clear()
            {
/* 161*/        throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
            }

            public Set keySet()
            {
/* 166*/        return Collections.unmodifiableSet(_flddelegate.keySet());
            }

            public Collection values()
            {
/* 171*/        return Collections.unmodifiableCollection(_flddelegate.values());
            }

            public Set entrySet()
            {
/* 176*/        return Collections.unmodifiableSet(_flddelegate.entrySet());
            }

            public String toString()
            {
/* 181*/        return _flddelegate.toString();
            }

            public boolean equals(Object obj)
            {
/* 186*/        if(this == obj)
/* 187*/            return true;
/* 189*/        if(!(obj instanceof ImmutableMultivaluedMap))
/* 190*/            return false;
/* 193*/        obj = (ImmutableMultivaluedMap)obj;
/* 195*/        return _flddelegate.equals(((ImmutableMultivaluedMap) (obj))._flddelegate);
            }

            public int hashCode()
            {
/* 204*/        return _flddelegate.hashCode();
            }

            public volatile Object remove(Object obj)
            {
/*  59*/        return remove(obj);
            }

            public volatile Object put(Object obj, Object obj1)
            {
/*  59*/        return put(obj, (List)obj1);
            }

            public volatile Object get(Object obj)
            {
/*  59*/        return get(obj);
            }

            private final MultivaluedMap _flddelegate;
}
