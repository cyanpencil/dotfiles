// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multiset

public interface Multimap
{

    public abstract int size();

    public abstract boolean isEmpty();

    public abstract boolean containsKey(Object obj);

    public abstract boolean containsValue(Object obj);

    public abstract boolean containsEntry(Object obj, Object obj1);

    public abstract boolean put(Object obj, Object obj1);

    public abstract boolean remove(Object obj, Object obj1);

    public abstract boolean putAll(Object obj, Iterable iterable);

    public abstract boolean putAll(Multimap multimap);

    public abstract Collection replaceValues(Object obj, Iterable iterable);

    public abstract Collection removeAll(Object obj);

    public abstract void clear();

    public abstract Collection get(Object obj);

    public abstract Set keySet();

    public abstract Multiset keys();

    public abstract Collection values();

    public abstract Collection entries();

    public abstract Map asMap();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();
}
