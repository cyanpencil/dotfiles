// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractTable, Maps, Table, Lists, 
//            Sets, Collections2, AbstractIterator, Iterators, 
//            AbstractMapEntry, ForwardingMapEntry, Tables

class StandardTable extends AbstractTable
    implements Serializable
{
    class ColumnMap extends Maps.ImprovedAbstractMap
    {
        class ColumnMapValues extends Maps.Values
        {

                    public boolean remove(Object obj)
                    {
                        java.util.Map.Entry entry;
/* 858*/                for(Iterator iterator = entrySet().iterator(); iterator.hasNext();)
/* 858*/                    if(((Map)(entry = (java.util.Map.Entry)iterator.next()).getValue()).equals(obj))
                            {
/* 860*/                        removeColumn(entry.getKey());
/* 861*/                        return true;
                            }

/* 864*/                return false;
                    }

                    public boolean removeAll(Collection collection)
                    {
/* 868*/                Preconditions.checkNotNull(collection);
/* 869*/                boolean flag = false;
/* 870*/                Iterator iterator = Lists.newArrayList(columnKeySet().iterator()).iterator();
/* 870*/                do
                        {
/* 870*/                    if(!iterator.hasNext())
/* 870*/                        break;
/* 870*/                    Object obj = iterator.next();
/* 871*/                    if(collection.contains(column(obj)))
                            {
/* 872*/                        removeColumn(obj);
/* 873*/                        flag = true;
                            }
                        } while(true);
/* 876*/                return flag;
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 880*/                Preconditions.checkNotNull(collection);
/* 881*/                boolean flag = false;
/* 882*/                Iterator iterator = Lists.newArrayList(columnKeySet().iterator()).iterator();
/* 882*/                do
                        {
/* 882*/                    if(!iterator.hasNext())
/* 882*/                        break;
/* 882*/                    Object obj = iterator.next();
/* 883*/                    if(!collection.contains(column(obj)))
                            {
/* 884*/                        removeColumn(obj);
/* 885*/                        flag = true;
                            }
                        } while(true);
/* 888*/                return flag;
                    }

                    final ColumnMap this$1;

                    ColumnMapValues()
                    {
/* 853*/                this$1 = ColumnMap.this;
/* 854*/                super(ColumnMap.this);
                    }
        }

        class ColumnMapEntrySet extends TableSet
        {

                    public Iterator iterator()
                    {
/* 793*/                return Maps.asMapEntryIterator(columnKeySet(), new Function() {

                            public Map apply(Object obj)
                            {
/* 796*/                        return column(obj);
                            }

                            public volatile Object apply(Object obj)
                            {
/* 793*/                        return apply(obj);
                            }

                            final ColumnMapEntrySet this$2;

                            
                            {
/* 793*/                        this$2 = ColumnMapEntrySet.this;
/* 793*/                        super();
                            }
                });
                    }

                    public int size()
                    {
/* 802*/                return columnKeySet().size();
                    }

                    public boolean contains(Object obj)
                    {
/* 806*/                if(obj instanceof java.util.Map.Entry)
                        {
/* 807*/                    obj = (java.util.Map.Entry)obj;
/* 808*/                    if(containsColumn(((java.util.Map.Entry) (obj)).getKey()))
                            {
/* 812*/                        Object obj1 = ((java.util.Map.Entry) (obj)).getKey();
/* 813*/                        return get(obj1).equals(((java.util.Map.Entry) (obj)).getValue());
                            }
                        }
/* 816*/                return false;
                    }

                    public boolean remove(Object obj)
                    {
/* 820*/                if(contains(obj))
                        {
/* 821*/                    obj = (java.util.Map.Entry)obj;
/* 822*/                    removeColumn(((java.util.Map.Entry) (obj)).getKey());
/* 823*/                    return true;
                        } else
                        {
/* 825*/                    return false;
                        }
                    }

                    public boolean removeAll(Collection collection)
                    {
/* 835*/                Preconditions.checkNotNull(collection);
/* 836*/                return Sets.removeAllImpl(this, collection.iterator());
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 840*/                Preconditions.checkNotNull(collection);
/* 841*/                boolean flag = false;
/* 842*/                Iterator iterator1 = Lists.newArrayList(columnKeySet().iterator()).iterator();
/* 842*/                do
                        {
/* 842*/                    if(!iterator1.hasNext())
/* 842*/                        break;
/* 842*/                    Object obj = iterator1.next();
/* 843*/                    if(!collection.contains(Maps.immutableEntry(obj, column(obj))))
                            {
/* 844*/                        removeColumn(obj);
/* 845*/                        flag = true;
                            }
                        } while(true);
/* 848*/                return flag;
                    }

                    final ColumnMap this$1;

                    ColumnMapEntrySet()
                    {
/* 791*/                this$1 = ColumnMap.this;
/* 791*/                super();
                    }
        }


                public Map get(Object obj)
                {
/* 768*/            if(containsColumn(obj))
/* 768*/                return column(obj);
/* 768*/            else
/* 768*/                return null;
                }

                public boolean containsKey(Object obj)
                {
/* 772*/            return containsColumn(obj);
                }

                public Map remove(Object obj)
                {
/* 776*/            if(containsColumn(obj))
/* 776*/                return removeColumn(obj);
/* 776*/            else
/* 776*/                return null;
                }

                public Set createEntrySet()
                {
/* 780*/            return new ColumnMapEntrySet();
                }

                public Set keySet()
                {
/* 784*/            return columnKeySet();
                }

                Collection createValues()
                {
/* 788*/            return new ColumnMapValues();
                }

                public volatile Object remove(Object obj)
                {
/* 763*/            return remove(obj);
                }

                public volatile Object get(Object obj)
                {
/* 763*/            return get(obj);
                }

                final StandardTable this$0;

                private ColumnMap()
                {
/* 763*/            this$0 = StandardTable.this;
/* 763*/            super();
                }

    }

    class RowMap extends Maps.ImprovedAbstractMap
    {
        class EntrySet extends TableSet
        {

                    public Iterator iterator()
                    {
/* 722*/                return Maps.asMapEntryIterator(backingMap.keySet(), new Function() {

                            public Map apply(Object obj)
                            {
/* 725*/                        return row(obj);
                            }

                            public volatile Object apply(Object obj)
                            {
/* 722*/                        return apply(obj);
                            }

                            final EntrySet this$2;

                            
                            {
/* 722*/                        this$2 = EntrySet.this;
/* 722*/                        super();
                            }
                });
                    }

                    public int size()
                    {
/* 731*/                return backingMap.size();
                    }

                    public boolean contains(Object obj)
                    {
/* 735*/                if(obj instanceof java.util.Map.Entry)
/* 736*/                    return ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey() != null && (((java.util.Map.Entry) (obj)).getValue() instanceof Map) && Collections2.safeContains(backingMap.entrySet(), obj);
/* 741*/                else
/* 741*/                    return false;
                    }

                    public boolean remove(Object obj)
                    {
/* 745*/                if(obj instanceof java.util.Map.Entry)
/* 746*/                    return ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey() != null && (((java.util.Map.Entry) (obj)).getValue() instanceof Map) && backingMap.entrySet().remove(obj);
/* 751*/                else
/* 751*/                    return false;
                    }

                    final RowMap this$1;

                    EntrySet()
                    {
/* 720*/                this$1 = RowMap.this;
/* 720*/                super();
                    }
        }


                public boolean containsKey(Object obj)
                {
/* 703*/            return containsRow(obj);
                }

                public Map get(Object obj)
                {
/* 709*/            if(containsRow(obj))
/* 709*/                return row(obj);
/* 709*/            else
/* 709*/                return null;
                }

                public Map remove(Object obj)
                {
/* 713*/            if(obj == null)
/* 713*/                return null;
/* 713*/            else
/* 713*/                return (Map)backingMap.remove(obj);
                }

                protected Set createEntrySet()
                {
/* 717*/            return new EntrySet();
                }

                public volatile Object remove(Object obj)
                {
/* 701*/            return remove(obj);
                }

                public volatile Object get(Object obj)
                {
/* 701*/            return get(obj);
                }

                final StandardTable this$0;

                RowMap()
                {
/* 701*/            this$0 = StandardTable.this;
/* 701*/            super();
                }
    }

    class ColumnKeyIterator extends AbstractIterator
    {

                protected Object computeNext()
                {
/* 665*/            do
/* 665*/                if(entryIterator.hasNext())
                        {
/* 666*/                    java.util.Map.Entry entry = (java.util.Map.Entry)entryIterator.next();
/* 667*/                    if(!seen.containsKey(entry.getKey()))
                            {
/* 668*/                        seen.put(entry.getKey(), entry.getValue());
/* 669*/                        return entry.getKey();
                            }
                        } else
/* 671*/                if(mapIterator.hasNext())
/* 672*/                    entryIterator = ((Map)mapIterator.next()).entrySet().iterator();
/* 674*/                else
/* 674*/                    return endOfData();
/* 674*/            while(true);
                }

                final Map seen;
                final Iterator mapIterator;
                Iterator entryIterator;
                final StandardTable this$0;

                private ColumnKeyIterator()
                {
/* 656*/            this$0 = StandardTable.this;
/* 656*/            super();
/* 659*/            seen = (Map)factory.get();
/* 660*/            mapIterator = backingMap.values().iterator();
/* 661*/            entryIterator = Iterators.emptyIterator();
                }

    }

    class ColumnKeySet extends TableSet
    {

                public Iterator iterator()
                {
/* 584*/            return createColumnKeyIterator();
                }

                public int size()
                {
/* 588*/            return Iterators.size(iterator());
                }

                public boolean remove(Object obj)
                {
/* 592*/            if(obj == null)
/* 593*/                return false;
/* 595*/            boolean flag = false;
/* 596*/            Iterator iterator1 = backingMap.values().iterator();
/* 597*/            do
                    {
/* 597*/                if(!iterator1.hasNext())
/* 598*/                    break;
                        Map map;
/* 598*/                if((map = (Map)iterator1.next()).keySet().remove(obj))
                        {
/* 600*/                    flag = true;
/* 601*/                    if(map.isEmpty())
/* 602*/                        iterator1.remove();
                        }
                    } while(true);
/* 606*/            return flag;
                }

                public boolean removeAll(Collection collection)
                {
/* 610*/            Preconditions.checkNotNull(collection);
/* 611*/            boolean flag = false;
/* 612*/            Iterator iterator1 = backingMap.values().iterator();
/* 613*/            do
                    {
/* 613*/                if(!iterator1.hasNext())
/* 614*/                    break;
                        Map map;
/* 614*/                if(Iterators.removeAll((map = (Map)iterator1.next()).keySet().iterator(), collection))
                        {
/* 618*/                    flag = true;
/* 619*/                    if(map.isEmpty())
/* 620*/                        iterator1.remove();
                        }
                    } while(true);
/* 624*/            return flag;
                }

                public boolean retainAll(Collection collection)
                {
/* 628*/            Preconditions.checkNotNull(collection);
/* 629*/            boolean flag = false;
/* 630*/            Iterator iterator1 = backingMap.values().iterator();
/* 631*/            do
                    {
/* 631*/                if(!iterator1.hasNext())
/* 632*/                    break;
                        Map map;
/* 632*/                if((map = (Map)iterator1.next()).keySet().retainAll(collection))
                        {
/* 634*/                    flag = true;
/* 635*/                    if(map.isEmpty())
/* 636*/                        iterator1.remove();
                        }
                    } while(true);
/* 640*/            return flag;
                }

                public boolean contains(Object obj)
                {
/* 644*/            return containsColumn(obj);
                }

                final StandardTable this$0;

                private ColumnKeySet()
                {
/* 582*/            this$0 = StandardTable.this;
/* 582*/            super();
                }

    }

    class Column extends Maps.ImprovedAbstractMap
    {
        class Values extends Maps.Values
        {

                    public boolean remove(Object obj)
                    {
/* 548*/                return obj != null && removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
                    }

                    public boolean removeAll(Collection collection)
                    {
/* 552*/                return removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 556*/                return removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                    }

                    final Column this$1;

                    Values()
                    {
/* 543*/                this$1 = Column.this;
/* 544*/                super(Column.this);
                    }
        }

        class KeySet extends Maps.KeySet
        {

                    public boolean contains(Object obj)
                    {
/* 525*/                return StandardTable.this.contains(obj, columnKey);
                    }

                    public boolean remove(Object obj)
                    {
/* 529*/                return StandardTable.this.remove(obj, columnKey) != null;
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 533*/                return removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                    }

                    final Column this$1;

                    KeySet()
                    {
/* 520*/                this$1 = Column.this;
/* 521*/                super(Column.this);
                    }
        }

        class EntrySetIterator extends AbstractIterator
        {

                    protected java.util.Map.Entry computeNext()
                    {
                        final java.util.Map.Entry entry;
/* 495*/                while(iterator.hasNext()) 
/* 496*/                    if(((Map)(entry = (java.util.Map.Entry)iterator.next()).getValue()).containsKey(columnKey))
/* 498*/                        return new AbstractMapEntry() {

                                    public Object getKey()
                                    {
/* 500*/                                return entry.getKey();
                                    }

                                    public Object getValue()
                                    {
/* 503*/                                return ((Map)entry.getValue()).get(columnKey);
                                    }

                                    public Object setValue(Object obj)
                                    {
/* 506*/                                return ((Map)entry.getValue()).put(columnKey, Preconditions.checkNotNull(obj));
                                    }

                                    final java.util.Map.Entry val$entry;
                                    final EntrySetIterator this$2;

                            
                            {
/* 498*/                        this$2 = EntrySetIterator.this;
/* 498*/                        entry = entry1;
/* 498*/                        super();
                            }
                        };
/* 511*/                return (java.util.Map.Entry)endOfData();
                    }

                    protected volatile Object computeNext()
                    {
/* 491*/                return computeNext();
                    }

                    final Iterator iterator;
                    final Column this$1;

                    private EntrySetIterator()
                    {
/* 491*/                this$1 = Column.this;
/* 491*/                super();
/* 492*/                iterator = backingMap.entrySet().iterator();
                    }

        }

        class EntrySet extends Sets.ImprovedAbstractSet
        {

                    public Iterator iterator()
                    {
/* 449*/                return new EntrySetIterator();
                    }

                    public int size()
                    {
/* 453*/                int i = 0;
/* 454*/                Iterator iterator1 = backingMap.values().iterator();
/* 454*/                do
                        {
/* 454*/                    if(!iterator1.hasNext())
/* 454*/                        break;
                            Map map;
/* 454*/                    if((map = (Map)iterator1.next()).containsKey(columnKey))
/* 456*/                        i++;
                        } while(true);
/* 459*/                return i;
                    }

                    public boolean isEmpty()
                    {
/* 463*/                return !containsColumn(columnKey);
                    }

                    public void clear()
                    {
/* 467*/                removeFromColumnIf(Predicates.alwaysTrue());
                    }

                    public boolean contains(Object obj)
                    {
/* 471*/                if(obj instanceof java.util.Map.Entry)
                        {
/* 472*/                    obj = (java.util.Map.Entry)obj;
/* 473*/                    return containsMapping(((java.util.Map.Entry) (obj)).getKey(), columnKey, ((java.util.Map.Entry) (obj)).getValue());
                        } else
                        {
/* 475*/                    return false;
                        }
                    }

                    public boolean remove(Object obj)
                    {
/* 479*/                if(obj instanceof java.util.Map.Entry)
                        {
/* 480*/                    obj = (java.util.Map.Entry)obj;
/* 481*/                    return removeMapping(((java.util.Map.Entry) (obj)).getKey(), columnKey, ((java.util.Map.Entry) (obj)).getValue());
                        } else
                        {
/* 483*/                    return false;
                        }
                    }

                    public boolean retainAll(Collection collection)
                    {
/* 487*/                return removeFromColumnIf(Predicates.not(Predicates.in(collection)));
                    }

                    final Column this$1;

                    private EntrySet()
                    {
/* 447*/                this$1 = Column.this;
/* 447*/                super();
                    }

        }


                public Object put(Object obj, Object obj1)
                {
/* 404*/            return StandardTable.this.put(obj, columnKey, obj1);
                }

                public Object get(Object obj)
                {
/* 408*/            return StandardTable.this.get(obj, columnKey);
                }

                public boolean containsKey(Object obj)
                {
/* 412*/            return contains(obj, columnKey);
                }

                public Object remove(Object obj)
                {
/* 416*/            return StandardTable.this.remove(obj, columnKey);
                }

                boolean removeFromColumnIf(Predicate predicate)
                {
/* 424*/            boolean flag = false;
/* 425*/            Iterator iterator = backingMap.entrySet().iterator();
/* 427*/            do
                    {
/* 427*/                if(!iterator.hasNext())
/* 428*/                    break;
                        java.util.Map.Entry entry;
                        Map map;
                        Object obj;
/* 428*/                if((obj = (map = (Map)(entry = (java.util.Map.Entry)iterator.next()).getValue()).get(columnKey)) != null && predicate.apply(Maps.immutableEntry(entry.getKey(), obj)))
                        {
/* 433*/                    map.remove(columnKey);
/* 434*/                    flag = true;
/* 435*/                    if(map.isEmpty())
/* 436*/                        iterator.remove();
                        }
                    } while(true);
/* 440*/            return flag;
                }

                Set createEntrySet()
                {
/* 444*/            return new EntrySet();
                }

                Set createKeySet()
                {
/* 516*/            return new KeySet();
                }

                Collection createValues()
                {
/* 539*/            return new Values();
                }

                final Object columnKey;
                final StandardTable this$0;

                Column(Object obj)
                {
/* 399*/            this$0 = StandardTable.this;
/* 399*/            super();
/* 400*/            columnKey = Preconditions.checkNotNull(obj);
                }
    }

    class Row extends Maps.ImprovedAbstractMap
    {
        final class RowEntrySet extends Maps.EntrySet
        {

                    final Map map()
                    {
/* 339*/                return Row.this;
                    }

                    public final int size()
                    {
                        Map map1;
/* 344*/                if((map1 = backingRowMap()) == null)
/* 345*/                    return 0;
/* 345*/                else
/* 345*/                    return map1.size();
                    }

                    public final Iterator iterator()
                    {
                        final Iterator iterator;
/* 350*/                if((iterator = backingRowMap()) == null)
                        {
/* 352*/                    return Iterators.emptyModifiableIterator();
                        } else
                        {
/* 354*/                    iterator = iterator.entrySet().iterator();
/* 355*/                    return new Iterator() {

                                public boolean hasNext()
                                {
/* 357*/                            return iterator.hasNext();
                                }

                                public java.util.Map.Entry next()
                                {
/* 360*/                            final java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
/* 361*/                            return new ForwardingMapEntry() {

                                        protected java.util.Map.Entry _mthdelegate()
                                        {
/* 363*/                                    return entry;
                                        }

                                        public Object setValue(Object obj)
                                        {
/* 366*/                                    return super.setValue(Preconditions.checkNotNull(obj));
                                        }

                                        public boolean equals(Object obj)
                                        {
/* 371*/                                    return standardEquals(obj);
                                        }

                                        protected volatile Object _mthdelegate()
                                        {
/* 361*/                                    return _mthdelegate();
                                        }

                                        final java.util.Map.Entry val$entry;
                                        final _cls1 this$3;

                                    
                                    {
/* 361*/                                this$3 = _cls1.this;
/* 361*/                                entry = entry1;
/* 361*/                                super();
                                    }
                            };
                                }

                                public void remove()
                                {
/* 378*/                            iterator.remove();
/* 379*/                            maintainEmptyInvariant();
                                }

                                public volatile Object next()
                                {
/* 355*/                            return next();
                                }

                                final Iterator val$iterator;
                                final RowEntrySet this$2;

                            
                            {
/* 355*/                        this$2 = RowEntrySet.this;
/* 355*/                        iterator = iterator1;
/* 355*/                        super();
                            }
                    };
                        }
                    }

                    final Row this$1;

                    private RowEntrySet()
                    {
/* 336*/                this$1 = Row.this;
/* 336*/                super();
                    }

        }


                Map backingRowMap()
                {
/* 268*/            if(backingRowMap == null || backingRowMap.isEmpty() && backingMap.containsKey(rowKey))
/* 268*/                return backingRowMap = computeBackingRowMap();
/* 268*/            else
/* 268*/                return backingRowMap;
                }

                Map computeBackingRowMap()
                {
/* 275*/            return (Map)backingMap.get(rowKey);
                }

                void maintainEmptyInvariant()
                {
/* 280*/            if(backingRowMap() != null && backingRowMap.isEmpty())
                    {
/* 281*/                backingMap.remove(rowKey);
/* 282*/                backingRowMap = null;
                    }
                }

                public boolean containsKey(Object obj)
                {
/* 288*/            Map map = backingRowMap();
/* 289*/            return obj != null && map != null && Maps.safeContainsKey(map, obj);
                }

                public Object get(Object obj)
                {
/* 295*/            Map map = backingRowMap();
/* 296*/            if(obj != null && map != null)
/* 296*/                return Maps.safeGet(map, obj);
/* 296*/            else
/* 296*/                return null;
                }

                public Object put(Object obj, Object obj1)
                {
/* 303*/            Preconditions.checkNotNull(obj);
/* 304*/            Preconditions.checkNotNull(obj1);
/* 305*/            if(backingRowMap != null && !backingRowMap.isEmpty())
/* 306*/                return backingRowMap.put(obj, obj1);
/* 308*/            else
/* 308*/                return StandardTable.this.put(rowKey, obj, obj1);
                }

                public Object remove(Object obj)
                {
                    Map map;
/* 313*/            if((map = backingRowMap()) == null)
                    {
/* 315*/                return null;
                    } else
                    {
/* 317*/                obj = Maps.safeRemove(map, obj);
/* 318*/                maintainEmptyInvariant();
/* 319*/                return obj;
                    }
                }

                public void clear()
                {
                    Map map;
/* 324*/            if((map = backingRowMap()) != null)
/* 326*/                map.clear();
/* 328*/            maintainEmptyInvariant();
                }

                protected Set createEntrySet()
                {
/* 333*/            return new RowEntrySet();
                }

                final Object rowKey;
                Map backingRowMap;
                final StandardTable this$0;

                Row(Object obj)
                {
/* 261*/            this$0 = StandardTable.this;
/* 261*/            super();
/* 262*/            rowKey = Preconditions.checkNotNull(obj);
                }
    }

    class CellIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/* 233*/            return rowIterator.hasNext() || columnIterator.hasNext();
                }

                public Table.Cell next()
                {
/* 237*/            if(!columnIterator.hasNext())
                    {
/* 238*/                rowEntry = (java.util.Map.Entry)rowIterator.next();
/* 239*/                columnIterator = ((Map)rowEntry.getValue()).entrySet().iterator();
                    }
/* 241*/            java.util.Map.Entry entry = (java.util.Map.Entry)columnIterator.next();
/* 242*/            return Tables.immutableCell(rowEntry.getKey(), entry.getKey(), entry.getValue());
                }

                public void remove()
                {
/* 247*/            columnIterator.remove();
/* 248*/            if(((Map)rowEntry.getValue()).isEmpty())
/* 249*/                rowIterator.remove();
                }

                public volatile Object next()
                {
/* 225*/            return next();
                }

                final Iterator rowIterator;
                java.util.Map.Entry rowEntry;
                Iterator columnIterator;
                final StandardTable this$0;

                private CellIterator()
                {
/* 225*/            this$0 = StandardTable.this;
/* 225*/            super();
/* 226*/            rowIterator = backingMap.entrySet().iterator();
/* 229*/            columnIterator = Iterators.emptyModifiableIterator();
                }

    }

    abstract class TableSet extends Sets.ImprovedAbstractSet
    {

                public boolean isEmpty()
                {
/* 199*/            return backingMap.isEmpty();
                }

                public void clear()
                {
/* 203*/            backingMap.clear();
                }

                final StandardTable this$0;

                private TableSet()
                {
/* 197*/            this$0 = StandardTable.this;
/* 197*/            super();
                }

    }


            StandardTable(Map map, Supplier supplier)
            {
/*  73*/        backingMap = map;
/*  74*/        factory = supplier;
            }

            public boolean contains(Object obj, Object obj1)
            {
/*  81*/        return obj != null && obj1 != null && super.contains(obj, obj1);
            }

            public boolean containsColumn(Object obj)
            {
/*  85*/        if(obj == null)
/*  86*/            return false;
                Map map;
/*  88*/        for(Iterator iterator = backingMap.values().iterator(); iterator.hasNext();)
/*  88*/            if(Maps.safeContainsKey(map = (Map)iterator.next(), obj))
/*  90*/                return true;

/*  93*/        return false;
            }

            public boolean containsRow(Object obj)
            {
/*  97*/        return obj != null && Maps.safeContainsKey(backingMap, obj);
            }

            public boolean containsValue(Object obj)
            {
/* 101*/        return obj != null && super.containsValue(obj);
            }

            public Object get(Object obj, Object obj1)
            {
/* 105*/        if(obj == null || obj1 == null)
/* 105*/            return null;
/* 105*/        else
/* 105*/            return super.get(obj, obj1);
            }

            public boolean isEmpty()
            {
/* 111*/        return backingMap.isEmpty();
            }

            public int size()
            {
/* 115*/        int i = 0;
/* 116*/        for(Iterator iterator = backingMap.values().iterator(); iterator.hasNext();)
                {
/* 116*/            Map map = (Map)iterator.next();
/* 117*/            i += map.size();
                }

/* 119*/        return i;
            }

            public void clear()
            {
/* 125*/        backingMap.clear();
            }

            private Map getOrCreate(Object obj)
            {
                Map map;
/* 129*/        if((map = (Map)backingMap.get(obj)) == null)
                {
/* 131*/            map = (Map)factory.get();
/* 132*/            backingMap.put(obj, map);
                }
/* 134*/        return map;
            }

            public Object put(Object obj, Object obj1, Object obj2)
            {
/* 138*/        Preconditions.checkNotNull(obj);
/* 139*/        Preconditions.checkNotNull(obj1);
/* 140*/        Preconditions.checkNotNull(obj2);
/* 141*/        return getOrCreate(obj).put(obj1, obj2);
            }

            public Object remove(Object obj, Object obj1)
            {
/* 146*/        if(obj == null || obj1 == null)
/* 147*/            return null;
                Map map;
/* 149*/        if((map = (Map)Maps.safeGet(backingMap, obj)) == null)
/* 151*/            return null;
/* 153*/        obj1 = map.remove(obj1);
/* 154*/        if(map.isEmpty())
/* 155*/            backingMap.remove(obj);
/* 157*/        return obj1;
            }

            private Map removeColumn(Object obj)
            {
/* 161*/        LinkedHashMap linkedhashmap = new LinkedHashMap();
/* 162*/        Iterator iterator = backingMap.entrySet().iterator();
/* 164*/        do
                {
/* 164*/            if(!iterator.hasNext())
/* 165*/                break;
                    java.util.Map.Entry entry;
                    Object obj1;
/* 165*/            if((obj1 = ((Map)(entry = (java.util.Map.Entry)iterator.next()).getValue()).remove(obj)) != null)
                    {
/* 168*/                linkedhashmap.put(entry.getKey(), obj1);
/* 169*/                if(((Map)entry.getValue()).isEmpty())
/* 170*/                    iterator.remove();
                    }
                } while(true);
/* 174*/        return linkedhashmap;
            }

            private boolean containsMapping(Object obj, Object obj1, Object obj2)
            {
/* 179*/        return obj2 != null && obj2.equals(get(obj, obj1));
            }

            private boolean removeMapping(Object obj, Object obj1, Object obj2)
            {
/* 184*/        if(containsMapping(obj, obj1, obj2))
                {
/* 185*/            remove(obj, obj1);
/* 186*/            return true;
                } else
                {
/* 188*/            return false;
                }
            }

            public Set cellSet()
            {
/* 218*/        return super.cellSet();
            }

            Iterator cellIterator()
            {
/* 222*/        return new CellIterator();
            }

            public Map row(Object obj)
            {
/* 255*/        return new Row(obj);
            }

            public Map column(Object obj)
            {
/* 393*/        return new Column(obj);
            }

            public Set rowKeySet()
            {
/* 562*/        return rowMap().keySet();
            }

            public Set columnKeySet()
            {
                Set set;
/* 578*/        if((set = columnKeySet) == null)
/* 579*/            return columnKeySet = new ColumnKeySet();
/* 579*/        else
/* 579*/            return set;
            }

            Iterator createColumnKeyIterator()
            {
/* 653*/        return new ColumnKeyIterator();
            }

            public Collection values()
            {
/* 687*/        return super.values();
            }

            public Map rowMap()
            {
                Map map;
/* 693*/        if((map = rowMap) == null)
/* 694*/            return rowMap = createRowMap();
/* 694*/        else
/* 694*/            return map;
            }

            Map createRowMap()
            {
/* 698*/        return new RowMap();
            }

            public Map columnMap()
            {
                ColumnMap columnmap;
/* 759*/        if((columnmap = columnMap) == null)
/* 760*/            return columnMap = new ColumnMap();
/* 760*/        else
/* 760*/            return columnmap;
            }

            final Map backingMap;
            final Supplier factory;
            private transient Set columnKeySet;
            private transient Map rowMap;
            private transient ColumnMap columnMap;
            private static final long serialVersionUID = 0L;



}
