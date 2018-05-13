// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, Maps, Table, Tables, 
//            Collections2, TransformedIterator

abstract class AbstractTable
    implements Table
{
    class Values extends AbstractCollection
    {

                public Iterator iterator()
                {
/* 183*/            return valuesIterator();
                }

                public boolean contains(Object obj)
                {
/* 188*/            return containsValue(obj);
                }

                public void clear()
                {
/* 193*/            AbstractTable.this.clear();
                }

                public int size()
                {
/* 198*/            return AbstractTable.this.size();
                }

                final AbstractTable this$0;

                Values()
                {
/* 180*/            this$0 = AbstractTable.this;
/* 180*/            super();
                }
    }

    class CellSet extends AbstractSet
    {

                public boolean contains(Object obj)
                {
/* 123*/            if(obj instanceof Table.Cell)
                    {
/* 124*/                obj = (Table.Cell)obj;
                        Map map;
/* 125*/                return (map = (Map)Maps.safeGet(rowMap(), ((Table.Cell) (obj)).getRowKey())) != null && Collections2.safeContains(map.entrySet(), Maps.immutableEntry(((Table.Cell) (obj)).getColumnKey(), ((Table.Cell) (obj)).getValue()));
                    } else
                    {
/* 129*/                return false;
                    }
                }

                public boolean remove(Object obj)
                {
/* 134*/            if(obj instanceof Table.Cell)
                    {
/* 135*/                obj = (Table.Cell)obj;
                        Map map;
/* 136*/                return (map = (Map)Maps.safeGet(rowMap(), ((Table.Cell) (obj)).getRowKey())) != null && Collections2.safeRemove(map.entrySet(), Maps.immutableEntry(((Table.Cell) (obj)).getColumnKey(), ((Table.Cell) (obj)).getValue()));
                    } else
                    {
/* 140*/                return false;
                    }
                }

                public void clear()
                {
/* 145*/            AbstractTable.this.clear();
                }

                public Iterator iterator()
                {
/* 150*/            return cellIterator();
                }

                public int size()
                {
/* 155*/            return AbstractTable.this.size();
                }

                final AbstractTable this$0;

                CellSet()
                {
/* 120*/            this$0 = AbstractTable.this;
/* 120*/            super();
                }
    }


            AbstractTable()
            {
            }

            public boolean containsRow(Object obj)
            {
/*  38*/        return Maps.safeContainsKey(rowMap(), obj);
            }

            public boolean containsColumn(Object obj)
            {
/*  43*/        return Maps.safeContainsKey(columnMap(), obj);
            }

            public Set rowKeySet()
            {
/*  48*/        return rowMap().keySet();
            }

            public Set columnKeySet()
            {
/*  53*/        return columnMap().keySet();
            }

            public boolean containsValue(Object obj)
            {
                Map map;
/*  58*/        for(Iterator iterator = rowMap().values().iterator(); iterator.hasNext();)
/*  58*/            if((map = (Map)iterator.next()).containsValue(obj))
/*  60*/                return true;

/*  63*/        return false;
            }

            public boolean contains(Object obj, Object obj1)
            {
/*  68*/        return (obj = (Map)Maps.safeGet(rowMap(), obj)) != null && Maps.safeContainsKey(((Map) (obj)), obj1);
            }

            public Object get(Object obj, Object obj1)
            {
/*  74*/        if((obj = (Map)Maps.safeGet(rowMap(), obj)) == null)
/*  75*/            return null;
/*  75*/        else
/*  75*/            return Maps.safeGet(((Map) (obj)), obj1);
            }

            public boolean isEmpty()
            {
/*  80*/        return size() == 0;
            }

            public void clear()
            {
/*  85*/        Iterators.clear(cellSet().iterator());
            }

            public Object remove(Object obj, Object obj1)
            {
/*  90*/        if((obj = (Map)Maps.safeGet(rowMap(), obj)) == null)
/*  91*/            return null;
/*  91*/        else
/*  91*/            return Maps.safeRemove(((Map) (obj)), obj1);
            }

            public Object put(Object obj, Object obj1, Object obj2)
            {
/*  96*/        return row(obj).put(obj1, obj2);
            }

            public void putAll(Table table)
            {
                Table.Cell cell;
/* 101*/        for(table = table.cellSet().iterator(); table.hasNext(); put(cell.getRowKey(), cell.getColumnKey(), cell.getValue()))
/* 101*/            cell = (Table.Cell)table.next();

            }

            public Set cellSet()
            {
                Set set;
/* 110*/        if((set = cellSet) == null)
/* 111*/            return cellSet = createCellSet();
/* 111*/        else
/* 111*/            return set;
            }

            Set createCellSet()
            {
/* 115*/        return new CellSet();
            }

            abstract Iterator cellIterator();

            public Collection values()
            {
                Collection collection;
/* 163*/        if((collection = values) == null)
/* 164*/            return values = createValues();
/* 164*/        else
/* 164*/            return collection;
            }

            Collection createValues()
            {
/* 168*/        return new Values();
            }

            Iterator valuesIterator()
            {
/* 172*/        return new TransformedIterator(cellSet().iterator()) {

                    Object transform(Table.Cell cell)
                    {
/* 175*/                return cell.getValue();
                    }

                    volatile Object transform(Object obj)
                    {
/* 172*/                return transform((Table.Cell)obj);
                    }

                    final AbstractTable this$0;

                    
                    {
/* 172*/                this$0 = AbstractTable.this;
/* 172*/                super(iterator);
                    }
        };
            }

            public boolean equals(Object obj)
            {
/* 203*/        return Tables.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 207*/        return cellSet().hashCode();
            }

            public String toString()
            {
/* 214*/        return rowMap().toString();
            }

            private transient Set cellSet;
            private transient Collection values;
}
