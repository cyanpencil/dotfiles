// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashBasedTable.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            StandardTable, CollectPreconditions, Maps, Table

public class HashBasedTable extends StandardTable
{
    static class Factory
        implements Serializable, Supplier
    {

                public Map get()
                {
/*  65*/            return Maps.newHashMapWithExpectedSize(expectedSize);
                }

                public volatile Object get()
                {
/*  57*/            return get();
                }

                final int expectedSize;

                Factory(int i)
                {
/*  61*/            expectedSize = i;
                }
    }


            public static HashBasedTable create()
            {
/*  74*/        return new HashBasedTable(new HashMap(), new Factory(0));
            }

            public static HashBasedTable create(int i, int j)
            {
/*  89*/        CollectPreconditions.checkNonnegative(j, "expectedCellsPerRow");
/*  90*/        i = Maps.newHashMapWithExpectedSize(i);
/*  92*/        return new HashBasedTable(i, new Factory(j));
            }

            public static HashBasedTable create(Table table)
            {
                HashBasedTable hashbasedtable;
/* 106*/        (hashbasedtable = create()).putAll(table);
/* 108*/        return hashbasedtable;
            }

            HashBasedTable(Map map, Factory factory)
            {
/* 112*/        super(map, factory);
            }

            public boolean contains(Object obj, Object obj1)
            {
/* 119*/        return super.contains(obj, obj1);
            }

            public boolean containsColumn(Object obj)
            {
/* 123*/        return super.containsColumn(obj);
            }

            public boolean containsRow(Object obj)
            {
/* 127*/        return super.containsRow(obj);
            }

            public boolean containsValue(Object obj)
            {
/* 131*/        return super.containsValue(obj);
            }

            public Object get(Object obj, Object obj1)
            {
/* 135*/        return super.get(obj, obj1);
            }

            public boolean equals(Object obj)
            {
/* 139*/        return super.equals(obj);
            }

            public Object remove(Object obj, Object obj1)
            {
/* 144*/        return super.remove(obj, obj1);
            }

            public volatile Map columnMap()
            {
/*  55*/        return super.columnMap();
            }

            public volatile Map rowMap()
            {
/*  55*/        return super.rowMap();
            }

            public volatile Collection values()
            {
/*  55*/        return super.values();
            }

            public volatile Set columnKeySet()
            {
/*  55*/        return super.columnKeySet();
            }

            public volatile Set rowKeySet()
            {
/*  55*/        return super.rowKeySet();
            }

            public volatile Map column(Object obj)
            {
/*  55*/        return super.column(obj);
            }

            public volatile Map row(Object obj)
            {
/*  55*/        return super.row(obj);
            }

            public volatile Set cellSet()
            {
/*  55*/        return super.cellSet();
            }

            public volatile Object put(Object obj, Object obj1, Object obj2)
            {
/*  55*/        return super.put(obj, obj1, obj2);
            }

            public volatile void clear()
            {
/*  55*/        super.clear();
            }

            public volatile int size()
            {
/*  55*/        return super.size();
            }

            public volatile boolean isEmpty()
            {
/*  55*/        return super.isEmpty();
            }

            public volatile String toString()
            {
/*  55*/        return super.toString();
            }

            public volatile int hashCode()
            {
/*  55*/        return super.hashCode();
            }

            public volatile void putAll(Table table)
            {
/*  55*/        super.putAll(table);
            }

            private static final long serialVersionUID = 0L;
}
