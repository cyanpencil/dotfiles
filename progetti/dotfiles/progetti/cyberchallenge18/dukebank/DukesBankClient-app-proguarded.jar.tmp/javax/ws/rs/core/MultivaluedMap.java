// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultivaluedMap.java

package javax.ws.rs.core;

import java.util.List;
import java.util.Map;

public interface MultivaluedMap
    extends Map
{

    public abstract void putSingle(Object obj, Object obj1);

    public abstract void add(Object obj, Object obj1);

    public abstract Object getFirst(Object obj);

    public transient abstract void addAll(Object obj, Object aobj[]);

    public abstract void addAll(Object obj, List list);

    public abstract void addFirst(Object obj, Object obj1);

    public abstract boolean equalsIgnoreValueOrder(MultivaluedMap multivaluedmap);
}
