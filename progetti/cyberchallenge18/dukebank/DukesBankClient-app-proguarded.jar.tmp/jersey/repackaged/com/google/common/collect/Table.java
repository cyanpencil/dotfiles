// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Table.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

public interface Table
{
    public static interface Cell
    {

        public abstract Object getRowKey();

        public abstract Object getColumnKey();

        public abstract Object getValue();
    }


    public abstract boolean contains(Object obj, Object obj1);

    public abstract boolean containsRow(Object obj);

    public abstract boolean containsColumn(Object obj);

    public abstract boolean containsValue(Object obj);

    public abstract Object get(Object obj, Object obj1);

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract void clear();

    public abstract Object put(Object obj, Object obj1, Object obj2);

    public abstract void putAll(Table table);

    public abstract Object remove(Object obj, Object obj1);

    public abstract Map row(Object obj);

    public abstract Map column(Object obj);

    public abstract Set cellSet();

    public abstract Set rowKeySet();

    public abstract Set columnKeySet();

    public abstract Collection values();

    public abstract Map rowMap();

    public abstract Map columnMap();
}
