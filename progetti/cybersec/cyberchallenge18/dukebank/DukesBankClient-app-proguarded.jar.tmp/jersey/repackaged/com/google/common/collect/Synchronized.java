// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            BiMap, ImmutableBiMap, ImmutableListMultimap, ImmutableMultimap, 
//            ImmutableMultiset, ImmutableSetMultimap, ListMultimap, Multimap, 
//            Multiset, SetMultimap, SortedSetMultimap, ForwardingIterator, 
//            Collections2, Iterators, Maps, ObjectArrays, 
//            Sets, ForwardingMapEntry

final class Synchronized
{
    static final class SynchronizedDeque extends SynchronizedQueue
        implements Deque
    {

                final Deque _mthdelegate()
                {
/*1637*/            return (Deque)super._mthdelegate();
                }

                public final void addFirst(Object obj)
                {
/*1642*/            synchronized(mutex)
                    {
/*1643*/                _mthdelegate().addFirst(obj);
                    }
                }

                public final void addLast(Object obj)
                {
/*1649*/            synchronized(mutex)
                    {
/*1650*/                _mthdelegate().addLast(obj);
                    }
                }

                public final boolean offerFirst(Object obj)
                {
/*1656*/            Object obj1 = mutex;
/*1656*/            JVM INSTR monitorenter ;
/*1657*/            return _mthdelegate().offerFirst(obj);
/*1658*/            obj;
/*1658*/            throw obj;
                }

                public final boolean offerLast(Object obj)
                {
/*1663*/            Object obj1 = mutex;
/*1663*/            JVM INSTR monitorenter ;
/*1664*/            return _mthdelegate().offerLast(obj);
/*1665*/            obj;
/*1665*/            throw obj;
                }

                public final Object removeFirst()
                {
/*1670*/            Object obj = mutex;
/*1670*/            JVM INSTR monitorenter ;
/*1671*/            return _mthdelegate().removeFirst();
                    Exception exception;
/*1672*/            exception;
/*1672*/            throw exception;
                }

                public final Object removeLast()
                {
/*1677*/            Object obj = mutex;
/*1677*/            JVM INSTR monitorenter ;
/*1678*/            return _mthdelegate().removeLast();
                    Exception exception;
/*1679*/            exception;
/*1679*/            throw exception;
                }

                public final Object pollFirst()
                {
/*1684*/            Object obj = mutex;
/*1684*/            JVM INSTR monitorenter ;
/*1685*/            return _mthdelegate().pollFirst();
                    Exception exception;
/*1686*/            exception;
/*1686*/            throw exception;
                }

                public final Object pollLast()
                {
/*1691*/            Object obj = mutex;
/*1691*/            JVM INSTR monitorenter ;
/*1692*/            return _mthdelegate().pollLast();
                    Exception exception;
/*1693*/            exception;
/*1693*/            throw exception;
                }

                public final Object getFirst()
                {
/*1698*/            Object obj = mutex;
/*1698*/            JVM INSTR monitorenter ;
/*1699*/            return _mthdelegate().getFirst();
                    Exception exception;
/*1700*/            exception;
/*1700*/            throw exception;
                }

                public final Object getLast()
                {
/*1705*/            Object obj = mutex;
/*1705*/            JVM INSTR monitorenter ;
/*1706*/            return _mthdelegate().getLast();
                    Exception exception;
/*1707*/            exception;
/*1707*/            throw exception;
                }

                public final Object peekFirst()
                {
/*1712*/            Object obj = mutex;
/*1712*/            JVM INSTR monitorenter ;
/*1713*/            return _mthdelegate().peekFirst();
                    Exception exception;
/*1714*/            exception;
/*1714*/            throw exception;
                }

                public final Object peekLast()
                {
/*1719*/            Object obj = mutex;
/*1719*/            JVM INSTR monitorenter ;
/*1720*/            return _mthdelegate().peekLast();
                    Exception exception;
/*1721*/            exception;
/*1721*/            throw exception;
                }

                public final boolean removeFirstOccurrence(Object obj)
                {
/*1726*/            Object obj1 = mutex;
/*1726*/            JVM INSTR monitorenter ;
/*1727*/            return _mthdelegate().removeFirstOccurrence(obj);
/*1728*/            obj;
/*1728*/            throw obj;
                }

                public final boolean removeLastOccurrence(Object obj)
                {
/*1733*/            Object obj1 = mutex;
/*1733*/            JVM INSTR monitorenter ;
/*1734*/            return _mthdelegate().removeLastOccurrence(obj);
/*1735*/            obj;
/*1735*/            throw obj;
                }

                public final void push(Object obj)
                {
/*1740*/            synchronized(mutex)
                    {
/*1741*/                _mthdelegate().push(obj);
                    }
                }

                public final Object pop()
                {
/*1747*/            Object obj = mutex;
/*1747*/            JVM INSTR monitorenter ;
/*1748*/            return _mthdelegate().pop();
                    Exception exception;
/*1749*/            exception;
/*1749*/            throw exception;
                }

                public final Iterator descendingIterator()
                {
/*1754*/            Object obj = mutex;
/*1754*/            JVM INSTR monitorenter ;
/*1755*/            return _mthdelegate().descendingIterator();
                    Exception exception;
/*1756*/            exception;
/*1756*/            throw exception;
                }

                final volatile Queue _mthdelegate()
                {
/*1628*/            return _mthdelegate();
                }

                final volatile Collection _mthdelegate()
                {
/*1628*/            return _mthdelegate();
                }

                final volatile Object _mthdelegate()
                {
/*1628*/            return _mthdelegate();
                }

                SynchronizedDeque(Deque deque1, Object obj)
                {
/*1633*/            super(deque1, obj);
                }
    }

    static class SynchronizedQueue extends SynchronizedCollection
        implements Queue
    {

                Queue _mthdelegate()
                {
/*1582*/            return (Queue)super._mthdelegate();
                }

                public Object element()
                {
/*1587*/            Object obj = mutex;
/*1587*/            JVM INSTR monitorenter ;
/*1588*/            return _mthdelegate().element();
                    Exception exception;
/*1589*/            exception;
/*1589*/            throw exception;
                }

                public boolean offer(Object obj)
                {
/*1594*/            Object obj1 = mutex;
/*1594*/            JVM INSTR monitorenter ;
/*1595*/            return _mthdelegate().offer(obj);
/*1596*/            obj;
/*1596*/            throw obj;
                }

                public Object peek()
                {
/*1601*/            Object obj = mutex;
/*1601*/            JVM INSTR monitorenter ;
/*1602*/            return _mthdelegate().peek();
                    Exception exception;
/*1603*/            exception;
/*1603*/            throw exception;
                }

                public Object poll()
                {
/*1608*/            Object obj = mutex;
/*1608*/            JVM INSTR monitorenter ;
/*1609*/            return _mthdelegate().poll();
                    Exception exception;
/*1610*/            exception;
/*1610*/            throw exception;
                }

                public Object remove()
                {
/*1615*/            Object obj = mutex;
/*1615*/            JVM INSTR monitorenter ;
/*1616*/            return _mthdelegate().remove();
                    Exception exception;
/*1617*/            exception;
/*1617*/            throw exception;
                }

                volatile Collection _mthdelegate()
                {
/*1574*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/*1574*/            return _mthdelegate();
                }

                SynchronizedQueue(Queue queue1, Object obj)
                {
/*1578*/            super(queue1, obj);
                }
    }

    static class SynchronizedEntry extends SynchronizedObject
        implements java.util.Map.Entry
    {

                java.util.Map.Entry _mthdelegate()
                {
/*1532*/            return (java.util.Map.Entry)super._mthdelegate();
                }

                public boolean equals(Object obj)
                {
/*1536*/            Object obj1 = mutex;
/*1536*/            JVM INSTR monitorenter ;
/*1537*/            return _mthdelegate().equals(obj);
/*1538*/            obj;
/*1538*/            throw obj;
                }

                public int hashCode()
                {
/*1542*/            Object obj = mutex;
/*1542*/            JVM INSTR monitorenter ;
/*1543*/            return _mthdelegate().hashCode();
                    Exception exception;
/*1544*/            exception;
/*1544*/            throw exception;
                }

                public Object getKey()
                {
/*1548*/            Object obj = mutex;
/*1548*/            JVM INSTR monitorenter ;
/*1549*/            return _mthdelegate().getKey();
                    Exception exception;
/*1550*/            exception;
/*1550*/            throw exception;
                }

                public Object getValue()
                {
/*1554*/            Object obj = mutex;
/*1554*/            JVM INSTR monitorenter ;
/*1555*/            return _mthdelegate().getValue();
                    Exception exception;
/*1556*/            exception;
/*1556*/            throw exception;
                }

                public Object setValue(Object obj)
                {
/*1560*/            Object obj1 = mutex;
/*1560*/            JVM INSTR monitorenter ;
/*1561*/            return _mthdelegate().setValue(obj);
/*1562*/            obj;
/*1562*/            throw obj;
                }

                volatile Object _mthdelegate()
                {
/*1522*/            return _mthdelegate();
                }

                SynchronizedEntry(java.util.Map.Entry entry, Object obj)
                {
/*1527*/            super(entry, obj);
                }
    }

    static class SynchronizedNavigableMap extends SynchronizedSortedMap
        implements NavigableMap
    {

                NavigableMap _mthdelegate()
                {
/*1360*/            return (NavigableMap)super._mthdelegate();
                }

                public java.util.Map.Entry ceilingEntry(Object obj)
                {
/*1364*/            Object obj1 = mutex;
/*1364*/            JVM INSTR monitorenter ;
/*1365*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().ceilingEntry(obj), mutex);
/*1366*/            obj;
/*1366*/            throw obj;
                }

                public Object ceilingKey(Object obj)
                {
/*1370*/            Object obj1 = mutex;
/*1370*/            JVM INSTR monitorenter ;
/*1371*/            return _mthdelegate().ceilingKey(obj);
/*1372*/            obj;
/*1372*/            throw obj;
                }

                public NavigableSet descendingKeySet()
                {
/*1378*/            Object obj = mutex;
/*1378*/            JVM INSTR monitorenter ;
/*1379*/            if(descendingKeySet == null)
/*1380*/                return descendingKeySet = Synchronized.navigableSet(_mthdelegate().descendingKeySet(), mutex);
/*1383*/            descendingKeySet;
/*1383*/            obj;
/*1383*/            JVM INSTR monitorexit ;
/*1383*/            return;
                    Exception exception;
/*1384*/            exception;
/*1384*/            throw exception;
                }

                public NavigableMap descendingMap()
                {
/*1390*/            Object obj = mutex;
/*1390*/            JVM INSTR monitorenter ;
/*1391*/            if(descendingMap == null)
/*1392*/                return descendingMap = Synchronized.navigableMap(_mthdelegate().descendingMap(), mutex);
/*1395*/            descendingMap;
/*1395*/            obj;
/*1395*/            JVM INSTR monitorexit ;
/*1395*/            return;
                    Exception exception;
/*1396*/            exception;
/*1396*/            throw exception;
                }

                public java.util.Map.Entry firstEntry()
                {
/*1400*/            Object obj = mutex;
/*1400*/            JVM INSTR monitorenter ;
/*1401*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().firstEntry(), mutex);
                    Exception exception;
/*1402*/            exception;
/*1402*/            throw exception;
                }

                public java.util.Map.Entry floorEntry(Object obj)
                {
/*1406*/            Object obj1 = mutex;
/*1406*/            JVM INSTR monitorenter ;
/*1407*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().floorEntry(obj), mutex);
/*1408*/            obj;
/*1408*/            throw obj;
                }

                public Object floorKey(Object obj)
                {
/*1412*/            Object obj1 = mutex;
/*1412*/            JVM INSTR monitorenter ;
/*1413*/            return _mthdelegate().floorKey(obj);
/*1414*/            obj;
/*1414*/            throw obj;
                }

                public NavigableMap headMap(Object obj, boolean flag)
                {
/*1418*/            Object obj1 = mutex;
/*1418*/            JVM INSTR monitorenter ;
/*1419*/            return Synchronized.navigableMap(_mthdelegate().headMap(obj, flag), mutex);
/*1421*/            obj;
/*1421*/            throw obj;
                }

                public java.util.Map.Entry higherEntry(Object obj)
                {
/*1425*/            Object obj1 = mutex;
/*1425*/            JVM INSTR monitorenter ;
/*1426*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().higherEntry(obj), mutex);
/*1427*/            obj;
/*1427*/            throw obj;
                }

                public Object higherKey(Object obj)
                {
/*1431*/            Object obj1 = mutex;
/*1431*/            JVM INSTR monitorenter ;
/*1432*/            return _mthdelegate().higherKey(obj);
/*1433*/            obj;
/*1433*/            throw obj;
                }

                public java.util.Map.Entry lastEntry()
                {
/*1437*/            Object obj = mutex;
/*1437*/            JVM INSTR monitorenter ;
/*1438*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().lastEntry(), mutex);
                    Exception exception;
/*1439*/            exception;
/*1439*/            throw exception;
                }

                public java.util.Map.Entry lowerEntry(Object obj)
                {
/*1443*/            Object obj1 = mutex;
/*1443*/            JVM INSTR monitorenter ;
/*1444*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().lowerEntry(obj), mutex);
/*1445*/            obj;
/*1445*/            throw obj;
                }

                public Object lowerKey(Object obj)
                {
/*1449*/            Object obj1 = mutex;
/*1449*/            JVM INSTR monitorenter ;
/*1450*/            return _mthdelegate().lowerKey(obj);
/*1451*/            obj;
/*1451*/            throw obj;
                }

                public Set keySet()
                {
/*1455*/            return navigableKeySet();
                }

                public NavigableSet navigableKeySet()
                {
/*1461*/            Object obj = mutex;
/*1461*/            JVM INSTR monitorenter ;
/*1462*/            if(navigableKeySet == null)
/*1463*/                return navigableKeySet = Synchronized.navigableSet(_mthdelegate().navigableKeySet(), mutex);
/*1466*/            navigableKeySet;
/*1466*/            obj;
/*1466*/            JVM INSTR monitorexit ;
/*1466*/            return;
                    Exception exception;
/*1467*/            exception;
/*1467*/            throw exception;
                }

                public java.util.Map.Entry pollFirstEntry()
                {
/*1471*/            Object obj = mutex;
/*1471*/            JVM INSTR monitorenter ;
/*1472*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().pollFirstEntry(), mutex);
                    Exception exception;
/*1473*/            exception;
/*1473*/            throw exception;
                }

                public java.util.Map.Entry pollLastEntry()
                {
/*1477*/            Object obj = mutex;
/*1477*/            JVM INSTR monitorenter ;
/*1478*/            return Synchronized.nullableSynchronizedEntry(_mthdelegate().pollLastEntry(), mutex);
                    Exception exception;
/*1479*/            exception;
/*1479*/            throw exception;
                }

                public NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*1484*/            Object obj2 = mutex;
/*1484*/            JVM INSTR monitorenter ;
/*1485*/            return Synchronized.navigableMap(_mthdelegate().subMap(obj, flag, obj1, flag1), mutex);
/*1488*/            obj;
/*1488*/            throw obj;
                }

                public NavigableMap tailMap(Object obj, boolean flag)
                {
/*1492*/            Object obj1 = mutex;
/*1492*/            JVM INSTR monitorenter ;
/*1493*/            return Synchronized.navigableMap(_mthdelegate().tailMap(obj, flag), mutex);
/*1495*/            obj;
/*1495*/            throw obj;
                }

                public SortedMap headMap(Object obj)
                {
/*1499*/            return headMap(obj, false);
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*1503*/            return subMap(obj, true, obj1, false);
                }

                public SortedMap tailMap(Object obj)
                {
/*1507*/            return tailMap(obj, true);
                }

                volatile SortedMap _mthdelegate()
                {
/*1350*/            return _mthdelegate();
                }

                volatile Map _mthdelegate()
                {
/*1350*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/*1350*/            return _mthdelegate();
                }

                transient NavigableSet descendingKeySet;
                transient NavigableMap descendingMap;
                transient NavigableSet navigableKeySet;

                SynchronizedNavigableMap(NavigableMap navigablemap, Object obj)
                {
/*1356*/            super(navigablemap, obj);
                }
    }

    static class SynchronizedNavigableSet extends SynchronizedSortedSet
        implements NavigableSet
    {

                NavigableSet _mthdelegate()
                {
/*1233*/            return (NavigableSet)super._mthdelegate();
                }

                public Object ceiling(Object obj)
                {
/*1237*/            Object obj1 = mutex;
/*1237*/            JVM INSTR monitorenter ;
/*1238*/            return _mthdelegate().ceiling(obj);
/*1239*/            obj;
/*1239*/            throw obj;
                }

                public Iterator descendingIterator()
                {
/*1243*/            return _mthdelegate().descendingIterator();
                }

                public NavigableSet descendingSet()
                {
/*1249*/            Object obj = mutex;
/*1249*/            JVM INSTR monitorenter ;
                    NavigableSet navigableset;
/*1250*/            if(descendingSet != null)
/*1251*/                break MISSING_BLOCK_LABEL_40;
/*1251*/            navigableset = Synchronized.navigableSet(_mthdelegate().descendingSet(), mutex);
/*1253*/            descendingSet = navigableset;
/*1254*/            return navigableset;
/*1256*/            descendingSet;
/*1256*/            obj;
/*1256*/            JVM INSTR monitorexit ;
/*1256*/            return;
                    Exception exception;
/*1257*/            exception;
/*1257*/            throw exception;
                }

                public Object floor(Object obj)
                {
/*1261*/            Object obj1 = mutex;
/*1261*/            JVM INSTR monitorenter ;
/*1262*/            return _mthdelegate().floor(obj);
/*1263*/            obj;
/*1263*/            throw obj;
                }

                public NavigableSet headSet(Object obj, boolean flag)
                {
/*1267*/            Object obj1 = mutex;
/*1267*/            JVM INSTR monitorenter ;
/*1268*/            return Synchronized.navigableSet(_mthdelegate().headSet(obj, flag), mutex);
/*1270*/            obj;
/*1270*/            throw obj;
                }

                public Object higher(Object obj)
                {
/*1274*/            Object obj1 = mutex;
/*1274*/            JVM INSTR monitorenter ;
/*1275*/            return _mthdelegate().higher(obj);
/*1276*/            obj;
/*1276*/            throw obj;
                }

                public Object lower(Object obj)
                {
/*1280*/            Object obj1 = mutex;
/*1280*/            JVM INSTR monitorenter ;
/*1281*/            return _mthdelegate().lower(obj);
/*1282*/            obj;
/*1282*/            throw obj;
                }

                public Object pollFirst()
                {
/*1286*/            Object obj = mutex;
/*1286*/            JVM INSTR monitorenter ;
/*1287*/            return _mthdelegate().pollFirst();
                    Exception exception;
/*1288*/            exception;
/*1288*/            throw exception;
                }

                public Object pollLast()
                {
/*1292*/            Object obj = mutex;
/*1292*/            JVM INSTR monitorenter ;
/*1293*/            return _mthdelegate().pollLast();
                    Exception exception;
/*1294*/            exception;
/*1294*/            throw exception;
                }

                public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*1299*/            Object obj2 = mutex;
/*1299*/            JVM INSTR monitorenter ;
/*1300*/            return Synchronized.navigableSet(_mthdelegate().subSet(obj, flag, obj1, flag1), mutex);
/*1302*/            obj;
/*1302*/            throw obj;
                }

                public NavigableSet tailSet(Object obj, boolean flag)
                {
/*1306*/            Object obj1 = mutex;
/*1306*/            JVM INSTR monitorenter ;
/*1307*/            return Synchronized.navigableSet(_mthdelegate().tailSet(obj, flag), mutex);
/*1309*/            obj;
/*1309*/            throw obj;
                }

                public SortedSet headSet(Object obj)
                {
/*1313*/            return headSet(obj, false);
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/*1317*/            return subSet(obj, true, obj1, false);
                }

                public SortedSet tailSet(Object obj)
                {
/*1321*/            return tailSet(obj, true);
                }

                volatile SortedSet _mthdelegate()
                {
/*1224*/            return _mthdelegate();
                }

                volatile Set _mthdelegate()
                {
/*1224*/            return _mthdelegate();
                }

                volatile Collection _mthdelegate()
                {
/*1224*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/*1224*/            return _mthdelegate();
                }

                transient NavigableSet descendingSet;

                SynchronizedNavigableSet(NavigableSet navigableset, Object obj)
                {
/*1229*/            super(navigableset, obj);
                }
    }

    static class SynchronizedAsMapValues extends SynchronizedCollection
    {

                public Iterator iterator()
                {
/*1210*/            final Iterator iterator = super.iterator();
/*1211*/            return new ForwardingIterator() {

                        protected Iterator _mthdelegate()
                        {
/*1213*/                    return iterator;
                        }

                        public Collection next()
                        {
/*1216*/                    return Synchronized.typePreservingCollection((Collection)next(), mutex);
                        }

                        public volatile Object next()
                        {
/*1211*/                    return next();
                        }

                        protected volatile Object _mthdelegate()
                        {
/*1211*/                    return _mthdelegate();
                        }

                        final Iterator val$iterator;
                        final SynchronizedAsMapValues this$0;

                        
                        {
/*1211*/                    this$0 = SynchronizedAsMapValues.this;
/*1211*/                    iterator = iterator1;
/*1211*/                    super();
                        }
            };
                }

                SynchronizedAsMapValues(Collection collection1, Object obj)
                {
/*1205*/            super(collection1, obj);
                }
    }

    static class SynchronizedAsMap extends SynchronizedMap
    {

                public Collection get(Object obj)
                {
/*1166*/            Object obj1 = mutex;
/*1166*/            JVM INSTR monitorenter ;
/*1167*/            return (obj = (Collection)super.get(obj)) != null ? Synchronized.typePreservingCollection(((Collection) (obj)), mutex) : null;
/*1170*/            obj;
/*1170*/            throw obj;
                }

                public Set entrySet()
                {
/*1174*/            Object obj = mutex;
/*1174*/            JVM INSTR monitorenter ;
/*1175*/            if(asMapEntrySet == null)
/*1176*/                asMapEntrySet = new SynchronizedAsMapEntries(_mthdelegate().entrySet(), mutex);
/*1179*/            return asMapEntrySet;
                    Exception exception;
/*1180*/            exception;
/*1180*/            throw exception;
                }

                public Collection values()
                {
/*1184*/            Object obj = mutex;
/*1184*/            JVM INSTR monitorenter ;
/*1185*/            if(asMapValues == null)
/*1186*/                asMapValues = new SynchronizedAsMapValues(_mthdelegate().values(), mutex);
/*1189*/            return asMapValues;
                    Exception exception;
/*1190*/            exception;
/*1190*/            throw exception;
                }

                public boolean containsValue(Object obj)
                {
/*1195*/            return values().contains(obj);
                }

                public volatile Object get(Object obj)
                {
/*1156*/            return get(obj);
                }

                transient Set asMapEntrySet;
                transient Collection asMapValues;

                SynchronizedAsMap(Map map, Object obj)
                {
/*1162*/            super(map, obj);
                }
    }

    static class SynchronizedBiMap extends SynchronizedMap
        implements Serializable, BiMap
    {

                BiMap _mthdelegate()
                {
/*1123*/            return (BiMap)super._mthdelegate();
                }

                public Set values()
                {
/*1127*/            Object obj = mutex;
/*1127*/            JVM INSTR monitorenter ;
/*1128*/            if(valueSet == null)
/*1129*/                valueSet = Synchronized.set(_mthdelegate().values(), mutex);
/*1131*/            return valueSet;
                    Exception exception;
/*1132*/            exception;
/*1132*/            throw exception;
                }

                public BiMap inverse()
                {
/*1144*/            Object obj = mutex;
/*1144*/            JVM INSTR monitorenter ;
/*1145*/            if(inverse == null)
/*1146*/                inverse = new SynchronizedBiMap(_mthdelegate().inverse(), mutex, this);
/*1149*/            return inverse;
                    Exception exception;
/*1150*/            exception;
/*1150*/            throw exception;
                }

                public volatile Collection values()
                {
/*1111*/            return values();
                }

                volatile Map _mthdelegate()
                {
/*1111*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/*1111*/            return _mthdelegate();
                }

                private transient Set valueSet;
                private transient BiMap inverse;

                private SynchronizedBiMap(BiMap bimap, Object obj, BiMap bimap1)
                {
/*1118*/            super(bimap, obj);
/*1119*/            inverse = bimap1;
                }

    }

    static class SynchronizedSortedMap extends SynchronizedMap
        implements SortedMap
    {

                SortedMap _mthdelegate()
                {
/*1061*/            return (SortedMap)super._mthdelegate();
                }

                public Comparator comparator()
                {
/*1065*/            Object obj = mutex;
/*1065*/            JVM INSTR monitorenter ;
/*1066*/            return _mthdelegate().comparator();
                    Exception exception;
/*1067*/            exception;
/*1067*/            throw exception;
                }

                public Object firstKey()
                {
/*1071*/            Object obj = mutex;
/*1071*/            JVM INSTR monitorenter ;
/*1072*/            return _mthdelegate().firstKey();
                    Exception exception;
/*1073*/            exception;
/*1073*/            throw exception;
                }

                public SortedMap headMap(Object obj)
                {
/*1077*/            Object obj1 = mutex;
/*1077*/            JVM INSTR monitorenter ;
/*1078*/            return Synchronized.sortedMap(_mthdelegate().headMap(obj), mutex);
/*1079*/            obj;
/*1079*/            throw obj;
                }

                public Object lastKey()
                {
/*1083*/            Object obj = mutex;
/*1083*/            JVM INSTR monitorenter ;
/*1084*/            return _mthdelegate().lastKey();
                    Exception exception;
/*1085*/            exception;
/*1085*/            throw exception;
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*1089*/            Object obj2 = mutex;
/*1089*/            JVM INSTR monitorenter ;
/*1090*/            return Synchronized.sortedMap(_mthdelegate().subMap(obj, obj1), mutex);
/*1091*/            obj;
/*1091*/            throw obj;
                }

                public SortedMap tailMap(Object obj)
                {
/*1095*/            Object obj1 = mutex;
/*1095*/            JVM INSTR monitorenter ;
/*1096*/            return Synchronized.sortedMap(_mthdelegate().tailMap(obj), mutex);
/*1097*/            obj;
/*1097*/            throw obj;
                }

                volatile Map _mthdelegate()
                {
/*1053*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/*1053*/            return _mthdelegate();
                }

                SynchronizedSortedMap(SortedMap sortedmap, Object obj)
                {
/*1057*/            super(sortedmap, obj);
                }
    }

    static class SynchronizedMap extends SynchronizedObject
        implements Map
    {

                Map _mthdelegate()
                {
/* 934*/            return (Map)super._mthdelegate();
                }

                public void clear()
                {
/* 939*/            synchronized(mutex)
                    {
/* 940*/                _mthdelegate().clear();
                    }
                }

                public boolean containsKey(Object obj)
                {
/* 946*/            Object obj1 = mutex;
/* 946*/            JVM INSTR monitorenter ;
/* 947*/            return _mthdelegate().containsKey(obj);
/* 948*/            obj;
/* 948*/            throw obj;
                }

                public boolean containsValue(Object obj)
                {
/* 953*/            Object obj1 = mutex;
/* 953*/            JVM INSTR monitorenter ;
/* 954*/            return _mthdelegate().containsValue(obj);
/* 955*/            obj;
/* 955*/            throw obj;
                }

                public Set entrySet()
                {
/* 960*/            Object obj = mutex;
/* 960*/            JVM INSTR monitorenter ;
/* 961*/            if(entrySet == null)
/* 962*/                entrySet = Synchronized.set(_mthdelegate().entrySet(), mutex);
/* 964*/            return entrySet;
                    Exception exception;
/* 965*/            exception;
/* 965*/            throw exception;
                }

                public Object get(Object obj)
                {
/* 970*/            Object obj1 = mutex;
/* 970*/            JVM INSTR monitorenter ;
/* 971*/            return _mthdelegate().get(obj);
/* 972*/            obj;
/* 972*/            throw obj;
                }

                public boolean isEmpty()
                {
/* 977*/            Object obj = mutex;
/* 977*/            JVM INSTR monitorenter ;
/* 978*/            return _mthdelegate().isEmpty();
                    Exception exception;
/* 979*/            exception;
/* 979*/            throw exception;
                }

                public Set keySet()
                {
/* 984*/            Object obj = mutex;
/* 984*/            JVM INSTR monitorenter ;
/* 985*/            if(keySet == null)
/* 986*/                keySet = Synchronized.set(_mthdelegate().keySet(), mutex);
/* 988*/            return keySet;
                    Exception exception;
/* 989*/            exception;
/* 989*/            throw exception;
                }

                public Object put(Object obj, Object obj1)
                {
/* 994*/            Object obj2 = mutex;
/* 994*/            JVM INSTR monitorenter ;
/* 995*/            return _mthdelegate().put(obj, obj1);
/* 996*/            obj;
/* 996*/            throw obj;
                }

                public void putAll(Map map)
                {
/*1001*/            synchronized(mutex)
                    {
/*1002*/                _mthdelegate().putAll(map);
                    }
                }

                public Object remove(Object obj)
                {
/*1008*/            Object obj1 = mutex;
/*1008*/            JVM INSTR monitorenter ;
/*1009*/            return _mthdelegate().remove(obj);
/*1010*/            obj;
/*1010*/            throw obj;
                }

                public int size()
                {
/*1015*/            Object obj = mutex;
/*1015*/            JVM INSTR monitorenter ;
/*1016*/            return _mthdelegate().size();
                    Exception exception;
/*1017*/            exception;
/*1017*/            throw exception;
                }

                public Collection values()
                {
/*1022*/            Object obj = mutex;
/*1022*/            JVM INSTR monitorenter ;
/*1023*/            if(values == null)
/*1024*/                values = Synchronized.collection(_mthdelegate().values(), mutex);
/*1026*/            return values;
                    Exception exception;
/*1027*/            exception;
/*1027*/            throw exception;
                }

                public boolean equals(Object obj)
                {
/*1031*/            if(obj == this)
/*1032*/                return true;
/*1034*/            Object obj1 = mutex;
/*1034*/            JVM INSTR monitorenter ;
/*1035*/            return _mthdelegate().equals(obj);
/*1036*/            obj;
/*1036*/            throw obj;
                }

                public int hashCode()
                {
/*1040*/            Object obj = mutex;
/*1040*/            JVM INSTR monitorenter ;
/*1041*/            return _mthdelegate().hashCode();
                    Exception exception;
/*1042*/            exception;
/*1042*/            throw exception;
                }

                volatile Object _mthdelegate()
                {
/* 922*/            return _mthdelegate();
                }

                transient Set keySet;
                transient Collection values;
                transient Set entrySet;

                SynchronizedMap(Map map, Object obj)
                {
/* 929*/            super(map, obj);
                }
    }

    static class SynchronizedAsMapEntries extends SynchronizedSet
    {

                public Iterator iterator()
                {
/* 848*/            final Iterator iterator = super.iterator();
/* 849*/            return new ForwardingIterator() {

                        protected Iterator _mthdelegate()
                        {
/* 851*/                    return iterator;
                        }

                        public java.util.Map.Entry next()
                        {
/* 855*/                    final java.util.Map.Entry entry = (java.util.Map.Entry)super.next();
/* 856*/                    return new ForwardingMapEntry() {

                                protected java.util.Map.Entry _mthdelegate()
                                {
/* 858*/                            return entry;
                                }

                                public Collection getValue()
                                {
/* 861*/                            return Synchronized.typePreservingCollection((Collection)entry.getValue(), mutex);
                                }

                                public volatile Object getValue()
                                {
/* 856*/                            return getValue();
                                }

                                protected volatile Object _mthdelegate()
                                {
/* 856*/                            return _mthdelegate();
                                }

                                final java.util.Map.Entry val$entry;
                                final _cls1 this$1;

                                
                                {
/* 856*/                            this$1 = _cls1.this;
/* 856*/                            entry = entry1;
/* 856*/                            super();
                                }
                    };
                        }

                        public volatile Object next()
                        {
/* 849*/                    return next();
                        }

                        protected volatile Object _mthdelegate()
                        {
/* 849*/                    return _mthdelegate();
                        }

                        final Iterator val$iterator;
                        final SynchronizedAsMapEntries this$0;

                        
                        {
/* 849*/                    this$0 = SynchronizedAsMapEntries.this;
/* 849*/                    iterator = iterator1;
/* 849*/                    super();
                        }
            };
                }

                public Object[] toArray()
                {
/* 871*/            Object obj = mutex;
/* 871*/            JVM INSTR monitorenter ;
/* 872*/            return ObjectArrays.toArrayImpl(_mthdelegate());
                    Exception exception;
/* 873*/            exception;
/* 873*/            throw exception;
                }

                public Object[] toArray(Object aobj[])
                {
/* 876*/            Object obj = mutex;
/* 876*/            JVM INSTR monitorenter ;
/* 877*/            return ObjectArrays.toArrayImpl(_mthdelegate(), aobj);
/* 878*/            aobj;
/* 878*/            throw aobj;
                }

                public boolean contains(Object obj)
                {
/* 881*/            Object obj1 = mutex;
/* 881*/            JVM INSTR monitorenter ;
/* 882*/            return Maps.containsEntryImpl(_mthdelegate(), obj);
/* 883*/            obj;
/* 883*/            throw obj;
                }

                public boolean containsAll(Collection collection1)
                {
/* 886*/            Object obj = mutex;
/* 886*/            JVM INSTR monitorenter ;
/* 887*/            return Collections2.containsAllImpl(_mthdelegate(), collection1);
/* 888*/            collection1;
/* 888*/            throw collection1;
                }

                public boolean equals(Object obj)
                {
/* 891*/            if(obj == this)
/* 892*/                return true;
/* 894*/            Object obj1 = mutex;
/* 894*/            JVM INSTR monitorenter ;
/* 895*/            return Sets.equalsImpl(_mthdelegate(), obj);
/* 896*/            obj;
/* 896*/            throw obj;
                }

                public boolean remove(Object obj)
                {
/* 899*/            Object obj1 = mutex;
/* 899*/            JVM INSTR monitorenter ;
/* 900*/            return Maps.removeEntryImpl(_mthdelegate(), obj);
/* 901*/            obj;
/* 901*/            throw obj;
                }

                public boolean removeAll(Collection collection1)
                {
/* 904*/            Object obj = mutex;
/* 904*/            JVM INSTR monitorenter ;
/* 905*/            return Iterators.removeAll(_mthdelegate().iterator(), collection1);
/* 906*/            collection1;
/* 906*/            throw collection1;
                }

                public boolean retainAll(Collection collection1)
                {
/* 909*/            Object obj = mutex;
/* 909*/            JVM INSTR monitorenter ;
/* 910*/            return Iterators.retainAll(_mthdelegate().iterator(), collection1);
/* 911*/            collection1;
/* 911*/            throw collection1;
                }

                SynchronizedAsMapEntries(Set set1, Object obj)
                {
/* 843*/            super(set1, obj);
                }
    }

    static class SynchronizedSortedSetMultimap extends SynchronizedSetMultimap
        implements SortedSetMultimap
    {

                SortedSetMultimap _mthdelegate()
                {
/* 789*/            return (SortedSetMultimap)super._mthdelegate();
                }

                public SortedSet get(Object obj)
                {
/* 792*/            Object obj1 = mutex;
/* 792*/            JVM INSTR monitorenter ;
/* 793*/            return Synchronized.sortedSet(_mthdelegate().get(obj), mutex);
/* 794*/            obj;
/* 794*/            throw obj;
                }

                public SortedSet removeAll(Object obj)
                {
/* 797*/            Object obj1 = mutex;
/* 797*/            JVM INSTR monitorenter ;
/* 798*/            return _mthdelegate().removeAll(obj);
/* 799*/            obj;
/* 799*/            throw obj;
                }

                public SortedSet replaceValues(Object obj, Iterable iterable)
                {
/* 803*/            Object obj1 = mutex;
/* 803*/            JVM INSTR monitorenter ;
/* 804*/            return _mthdelegate().replaceValues(obj, iterable);
/* 805*/            obj;
/* 805*/            throw obj;
                }

                public Comparator valueComparator()
                {
/* 809*/            Object obj = mutex;
/* 809*/            JVM INSTR monitorenter ;
/* 810*/            return _mthdelegate().valueComparator();
                    Exception exception;
/* 811*/            exception;
/* 811*/            throw exception;
                }

                public volatile Set replaceValues(Object obj, Iterable iterable)
                {
/* 782*/            return replaceValues(obj, iterable);
                }

                public volatile Set removeAll(Object obj)
                {
/* 782*/            return removeAll(obj);
                }

                public volatile Set get(Object obj)
                {
/* 782*/            return get(obj);
                }

                volatile SetMultimap _mthdelegate()
                {
/* 782*/            return _mthdelegate();
                }

                public volatile Collection get(Object obj)
                {
/* 782*/            return get(obj);
                }

                public volatile Collection removeAll(Object obj)
                {
/* 782*/            return removeAll(obj);
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 782*/            return replaceValues(obj, iterable);
                }

                volatile Multimap _mthdelegate()
                {
/* 782*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 782*/            return _mthdelegate();
                }

                SynchronizedSortedSetMultimap(SortedSetMultimap sortedsetmultimap, Object obj)
                {
/* 786*/            super(sortedsetmultimap, obj);
                }
    }

    static class SynchronizedSetMultimap extends SynchronizedMultimap
        implements SetMultimap
    {

                SetMultimap _mthdelegate()
                {
/* 745*/            return (SetMultimap)super._mthdelegate();
                }

                public Set get(Object obj)
                {
/* 748*/            Object obj1 = mutex;
/* 748*/            JVM INSTR monitorenter ;
/* 749*/            return Synchronized.set(_mthdelegate().get(obj), mutex);
/* 750*/            obj;
/* 750*/            throw obj;
                }

                public Set removeAll(Object obj)
                {
/* 753*/            Object obj1 = mutex;
/* 753*/            JVM INSTR monitorenter ;
/* 754*/            return _mthdelegate().removeAll(obj);
/* 755*/            obj;
/* 755*/            throw obj;
                }

                public Set replaceValues(Object obj, Iterable iterable)
                {
/* 759*/            Object obj1 = mutex;
/* 759*/            JVM INSTR monitorenter ;
/* 760*/            return _mthdelegate().replaceValues(obj, iterable);
/* 761*/            obj;
/* 761*/            throw obj;
                }

                public Set entries()
                {
/* 764*/            Object obj = mutex;
/* 764*/            JVM INSTR monitorenter ;
/* 765*/            if(entrySet == null)
/* 766*/                entrySet = Synchronized.set(_mthdelegate().entries(), mutex);
/* 768*/            return entrySet;
                    Exception exception;
/* 769*/            exception;
/* 769*/            throw exception;
                }

                public volatile Collection entries()
                {
/* 736*/            return entries();
                }

                public volatile Collection removeAll(Object obj)
                {
/* 736*/            return removeAll(obj);
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 736*/            return replaceValues(obj, iterable);
                }

                public volatile Collection get(Object obj)
                {
/* 736*/            return get(obj);
                }

                volatile Multimap _mthdelegate()
                {
/* 736*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 736*/            return _mthdelegate();
                }

                transient Set entrySet;

                SynchronizedSetMultimap(SetMultimap setmultimap, Object obj)
                {
/* 742*/            super(setmultimap, obj);
                }
    }

    static class SynchronizedListMultimap extends SynchronizedMultimap
        implements ListMultimap
    {

                ListMultimap _mthdelegate()
                {
/* 706*/            return (ListMultimap)super._mthdelegate();
                }

                public List get(Object obj)
                {
/* 709*/            Object obj1 = mutex;
/* 709*/            JVM INSTR monitorenter ;
/* 710*/            return Synchronized.list(_mthdelegate().get(obj), mutex);
/* 711*/            obj;
/* 711*/            throw obj;
                }

                public List removeAll(Object obj)
                {
/* 714*/            Object obj1 = mutex;
/* 714*/            JVM INSTR monitorenter ;
/* 715*/            return _mthdelegate().removeAll(obj);
/* 716*/            obj;
/* 716*/            throw obj;
                }

                public List replaceValues(Object obj, Iterable iterable)
                {
/* 720*/            Object obj1 = mutex;
/* 720*/            JVM INSTR monitorenter ;
/* 721*/            return _mthdelegate().replaceValues(obj, iterable);
/* 722*/            obj;
/* 722*/            throw obj;
                }

                public volatile Collection removeAll(Object obj)
                {
/* 699*/            return removeAll(obj);
                }

                public volatile Collection replaceValues(Object obj, Iterable iterable)
                {
/* 699*/            return replaceValues(obj, iterable);
                }

                public volatile Collection get(Object obj)
                {
/* 699*/            return get(obj);
                }

                volatile Multimap _mthdelegate()
                {
/* 699*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 699*/            return _mthdelegate();
                }

                SynchronizedListMultimap(ListMultimap listmultimap, Object obj)
                {
/* 703*/            super(listmultimap, obj);
                }
    }

    static class SynchronizedMultimap extends SynchronizedObject
        implements Multimap
    {

                Multimap _mthdelegate()
                {
/* 524*/            return (Multimap)super._mthdelegate();
                }

                public int size()
                {
/* 533*/            Object obj = mutex;
/* 533*/            JVM INSTR monitorenter ;
/* 534*/            return _mthdelegate().size();
                    Exception exception;
/* 535*/            exception;
/* 535*/            throw exception;
                }

                public boolean isEmpty()
                {
/* 540*/            Object obj = mutex;
/* 540*/            JVM INSTR monitorenter ;
/* 541*/            return _mthdelegate().isEmpty();
                    Exception exception;
/* 542*/            exception;
/* 542*/            throw exception;
                }

                public boolean containsKey(Object obj)
                {
/* 547*/            Object obj1 = mutex;
/* 547*/            JVM INSTR monitorenter ;
/* 548*/            return _mthdelegate().containsKey(obj);
/* 549*/            obj;
/* 549*/            throw obj;
                }

                public boolean containsValue(Object obj)
                {
/* 554*/            Object obj1 = mutex;
/* 554*/            JVM INSTR monitorenter ;
/* 555*/            return _mthdelegate().containsValue(obj);
/* 556*/            obj;
/* 556*/            throw obj;
                }

                public boolean containsEntry(Object obj, Object obj1)
                {
/* 561*/            Object obj2 = mutex;
/* 561*/            JVM INSTR monitorenter ;
/* 562*/            return _mthdelegate().containsEntry(obj, obj1);
/* 563*/            obj;
/* 563*/            throw obj;
                }

                public Collection get(Object obj)
                {
/* 568*/            Object obj1 = mutex;
/* 568*/            JVM INSTR monitorenter ;
/* 569*/            return Synchronized.typePreservingCollection(_mthdelegate().get(obj), mutex);
/* 570*/            obj;
/* 570*/            throw obj;
                }

                public boolean put(Object obj, Object obj1)
                {
/* 575*/            Object obj2 = mutex;
/* 575*/            JVM INSTR monitorenter ;
/* 576*/            return _mthdelegate().put(obj, obj1);
/* 577*/            obj;
/* 577*/            throw obj;
                }

                public boolean putAll(Object obj, Iterable iterable)
                {
/* 582*/            Object obj1 = mutex;
/* 582*/            JVM INSTR monitorenter ;
/* 583*/            return _mthdelegate().putAll(obj, iterable);
/* 584*/            obj;
/* 584*/            throw obj;
                }

                public boolean putAll(Multimap multimap1)
                {
/* 589*/            Object obj = mutex;
/* 589*/            JVM INSTR monitorenter ;
/* 590*/            return _mthdelegate().putAll(multimap1);
/* 591*/            multimap1;
/* 591*/            throw multimap1;
                }

                public Collection replaceValues(Object obj, Iterable iterable)
                {
/* 596*/            Object obj1 = mutex;
/* 596*/            JVM INSTR monitorenter ;
/* 597*/            return _mthdelegate().replaceValues(obj, iterable);
/* 598*/            obj;
/* 598*/            throw obj;
                }

                public boolean remove(Object obj, Object obj1)
                {
/* 603*/            Object obj2 = mutex;
/* 603*/            JVM INSTR monitorenter ;
/* 604*/            return _mthdelegate().remove(obj, obj1);
/* 605*/            obj;
/* 605*/            throw obj;
                }

                public Collection removeAll(Object obj)
                {
/* 610*/            Object obj1 = mutex;
/* 610*/            JVM INSTR monitorenter ;
/* 611*/            return _mthdelegate().removeAll(obj);
/* 612*/            obj;
/* 612*/            throw obj;
                }

                public void clear()
                {
/* 617*/            synchronized(mutex)
                    {
/* 618*/                _mthdelegate().clear();
                    }
                }

                public Set keySet()
                {
/* 624*/            Object obj = mutex;
/* 624*/            JVM INSTR monitorenter ;
/* 625*/            if(keySet == null)
/* 626*/                keySet = Synchronized.typePreservingSet(_mthdelegate().keySet(), mutex);
/* 628*/            return keySet;
                    Exception exception;
/* 629*/            exception;
/* 629*/            throw exception;
                }

                public Collection values()
                {
/* 634*/            Object obj = mutex;
/* 634*/            JVM INSTR monitorenter ;
/* 635*/            if(valuesCollection == null)
/* 636*/                valuesCollection = Synchronized.collection(_mthdelegate().values(), mutex);
/* 638*/            return valuesCollection;
                    Exception exception;
/* 639*/            exception;
/* 639*/            throw exception;
                }

                public Collection entries()
                {
/* 644*/            Object obj = mutex;
/* 644*/            JVM INSTR monitorenter ;
/* 645*/            if(entries == null)
/* 646*/                entries = Synchronized.typePreservingCollection(_mthdelegate().entries(), mutex);
/* 648*/            return entries;
                    Exception exception;
/* 649*/            exception;
/* 649*/            throw exception;
                }

                public Map asMap()
                {
/* 654*/            Object obj = mutex;
/* 654*/            JVM INSTR monitorenter ;
/* 655*/            if(asMap == null)
/* 656*/                asMap = new SynchronizedAsMap(_mthdelegate().asMap(), mutex);
/* 658*/            return asMap;
                    Exception exception;
/* 659*/            exception;
/* 659*/            throw exception;
                }

                public Multiset keys()
                {
/* 664*/            Object obj = mutex;
/* 664*/            JVM INSTR monitorenter ;
/* 665*/            if(keys == null)
/* 666*/                keys = Synchronized.multiset(_mthdelegate().keys(), mutex);
/* 668*/            return keys;
                    Exception exception;
/* 669*/            exception;
/* 669*/            throw exception;
                }

                public boolean equals(Object obj)
                {
/* 673*/            if(obj == this)
/* 674*/                return true;
/* 676*/            Object obj1 = mutex;
/* 676*/            JVM INSTR monitorenter ;
/* 677*/            return _mthdelegate().equals(obj);
/* 678*/            obj;
/* 678*/            throw obj;
                }

                public int hashCode()
                {
/* 682*/            Object obj = mutex;
/* 682*/            JVM INSTR monitorenter ;
/* 683*/            return _mthdelegate().hashCode();
                    Exception exception;
/* 684*/            exception;
/* 684*/            throw exception;
                }

                volatile Object _mthdelegate()
                {
/* 514*/            return _mthdelegate();
                }

                transient Set keySet;
                transient Collection valuesCollection;
                transient Collection entries;
                transient Map asMap;
                transient Multiset keys;

                SynchronizedMultimap(Multimap multimap1, Object obj)
                {
/* 528*/            super(multimap1, obj);
                }
    }

    static class SynchronizedMultiset extends SynchronizedCollection
        implements Multiset
    {

                Multiset _mthdelegate()
                {
/* 429*/            return (Multiset)super._mthdelegate();
                }

                public int count(Object obj)
                {
/* 434*/            Object obj1 = mutex;
/* 434*/            JVM INSTR monitorenter ;
/* 435*/            return _mthdelegate().count(obj);
/* 436*/            obj;
/* 436*/            throw obj;
                }

                public int add(Object obj, int i)
                {
/* 441*/            Object obj1 = mutex;
/* 441*/            JVM INSTR monitorenter ;
/* 442*/            return _mthdelegate().add(obj, i);
/* 443*/            obj;
/* 443*/            throw obj;
                }

                public int remove(Object obj, int i)
                {
/* 448*/            Object obj1 = mutex;
/* 448*/            JVM INSTR monitorenter ;
/* 449*/            return _mthdelegate().remove(obj, i);
/* 450*/            obj;
/* 450*/            throw obj;
                }

                public int setCount(Object obj, int i)
                {
/* 455*/            Object obj1 = mutex;
/* 455*/            JVM INSTR monitorenter ;
/* 456*/            return _mthdelegate().setCount(obj, i);
/* 457*/            obj;
/* 457*/            throw obj;
                }

                public boolean setCount(Object obj, int i, int j)
                {
/* 462*/            Object obj1 = mutex;
/* 462*/            JVM INSTR monitorenter ;
/* 463*/            return _mthdelegate().setCount(obj, i, j);
/* 464*/            obj;
/* 464*/            throw obj;
                }

                public Set elementSet()
                {
/* 469*/            Object obj = mutex;
/* 469*/            JVM INSTR monitorenter ;
/* 470*/            if(elementSet == null)
/* 471*/                elementSet = Synchronized.typePreservingSet(_mthdelegate().elementSet(), mutex);
/* 473*/            return elementSet;
                    Exception exception;
/* 474*/            exception;
/* 474*/            throw exception;
                }

                public Set entrySet()
                {
/* 479*/            Object obj = mutex;
/* 479*/            JVM INSTR monitorenter ;
/* 480*/            if(entrySet == null)
/* 481*/                entrySet = Synchronized.typePreservingSet(_mthdelegate().entrySet(), mutex);
/* 483*/            return entrySet;
                    Exception exception;
/* 484*/            exception;
/* 484*/            throw exception;
                }

                public boolean equals(Object obj)
                {
/* 488*/            if(obj == this)
/* 489*/                return true;
/* 491*/            Object obj1 = mutex;
/* 491*/            JVM INSTR monitorenter ;
/* 492*/            return _mthdelegate().equals(obj);
/* 493*/            obj;
/* 493*/            throw obj;
                }

                public int hashCode()
                {
/* 497*/            Object obj = mutex;
/* 497*/            JVM INSTR monitorenter ;
/* 498*/            return _mthdelegate().hashCode();
                    Exception exception;
/* 499*/            exception;
/* 499*/            throw exception;
                }

                volatile Collection _mthdelegate()
                {
/* 419*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 419*/            return _mthdelegate();
                }

                transient Set elementSet;
                transient Set entrySet;

                SynchronizedMultiset(Multiset multiset1, Object obj)
                {
/* 425*/            super(multiset1, obj);
                }
    }

    static class SynchronizedRandomAccessList extends SynchronizedList
        implements RandomAccess
    {

                SynchronizedRandomAccessList(List list1, Object obj)
                {
/* 405*/            super(list1, obj);
                }
    }

    static class SynchronizedList extends SynchronizedCollection
        implements List
    {

                List _mthdelegate()
                {
/* 315*/            return (List)super._mthdelegate();
                }

                public void add(int i, Object obj)
                {
/* 320*/            synchronized(mutex)
                    {
/* 321*/                _mthdelegate().add(i, obj);
                    }
                }

                public boolean addAll(int i, Collection collection1)
                {
/* 327*/            Object obj = mutex;
/* 327*/            JVM INSTR monitorenter ;
/* 328*/            return _mthdelegate().addAll(i, collection1);
/* 329*/            i;
/* 329*/            throw i;
                }

                public Object get(int i)
                {
/* 334*/            Object obj = mutex;
/* 334*/            JVM INSTR monitorenter ;
/* 335*/            return _mthdelegate().get(i);
/* 336*/            i;
/* 336*/            throw i;
                }

                public int indexOf(Object obj)
                {
/* 341*/            Object obj1 = mutex;
/* 341*/            JVM INSTR monitorenter ;
/* 342*/            return _mthdelegate().indexOf(obj);
/* 343*/            obj;
/* 343*/            throw obj;
                }

                public int lastIndexOf(Object obj)
                {
/* 348*/            Object obj1 = mutex;
/* 348*/            JVM INSTR monitorenter ;
/* 349*/            return _mthdelegate().lastIndexOf(obj);
/* 350*/            obj;
/* 350*/            throw obj;
                }

                public ListIterator listIterator()
                {
/* 355*/            return _mthdelegate().listIterator();
                }

                public ListIterator listIterator(int i)
                {
/* 360*/            return _mthdelegate().listIterator(i);
                }

                public Object remove(int i)
                {
/* 365*/            Object obj = mutex;
/* 365*/            JVM INSTR monitorenter ;
/* 366*/            return _mthdelegate().remove(i);
/* 367*/            i;
/* 367*/            throw i;
                }

                public Object set(int i, Object obj)
                {
/* 372*/            Object obj1 = mutex;
/* 372*/            JVM INSTR monitorenter ;
/* 373*/            return _mthdelegate().set(i, obj);
/* 374*/            i;
/* 374*/            throw i;
                }

                public List subList(int i, int j)
                {
/* 379*/            Object obj = mutex;
/* 379*/            JVM INSTR monitorenter ;
/* 380*/            return Synchronized.list(_mthdelegate().subList(i, j), mutex);
/* 381*/            i;
/* 381*/            throw i;
                }

                public boolean equals(Object obj)
                {
/* 385*/            if(obj == this)
/* 386*/                return true;
/* 388*/            Object obj1 = mutex;
/* 388*/            JVM INSTR monitorenter ;
/* 389*/            return _mthdelegate().equals(obj);
/* 390*/            obj;
/* 390*/            throw obj;
                }

                public int hashCode()
                {
/* 394*/            Object obj = mutex;
/* 394*/            JVM INSTR monitorenter ;
/* 395*/            return _mthdelegate().hashCode();
                    Exception exception;
/* 396*/            exception;
/* 396*/            throw exception;
                }

                volatile Collection _mthdelegate()
                {
/* 308*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 308*/            return _mthdelegate();
                }

                SynchronizedList(List list1, Object obj)
                {
/* 311*/            super(list1, obj);
                }
    }

    static class SynchronizedSortedSet extends SynchronizedSet
        implements SortedSet
    {

                SortedSet _mthdelegate()
                {
/* 254*/            return (SortedSet)super._mthdelegate();
                }

                public Comparator comparator()
                {
/* 259*/            Object obj = mutex;
/* 259*/            JVM INSTR monitorenter ;
/* 260*/            return _mthdelegate().comparator();
                    Exception exception;
/* 261*/            exception;
/* 261*/            throw exception;
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/* 266*/            Object obj2 = mutex;
/* 266*/            JVM INSTR monitorenter ;
/* 267*/            return Synchronized.sortedSet(_mthdelegate().subSet(obj, obj1), mutex);
/* 268*/            obj;
/* 268*/            throw obj;
                }

                public SortedSet headSet(Object obj)
                {
/* 273*/            Object obj1 = mutex;
/* 273*/            JVM INSTR monitorenter ;
/* 274*/            return Synchronized.sortedSet(_mthdelegate().headSet(obj), mutex);
/* 275*/            obj;
/* 275*/            throw obj;
                }

                public SortedSet tailSet(Object obj)
                {
/* 280*/            Object obj1 = mutex;
/* 280*/            JVM INSTR monitorenter ;
/* 281*/            return Synchronized.sortedSet(_mthdelegate().tailSet(obj), mutex);
/* 282*/            obj;
/* 282*/            throw obj;
                }

                public Object first()
                {
/* 287*/            Object obj = mutex;
/* 287*/            JVM INSTR monitorenter ;
/* 288*/            return _mthdelegate().first();
                    Exception exception;
/* 289*/            exception;
/* 289*/            throw exception;
                }

                public Object last()
                {
/* 294*/            Object obj = mutex;
/* 294*/            JVM INSTR monitorenter ;
/* 295*/            return _mthdelegate().last();
                    Exception exception;
/* 296*/            exception;
/* 296*/            throw exception;
                }

                volatile Set _mthdelegate()
                {
/* 247*/            return _mthdelegate();
                }

                volatile Collection _mthdelegate()
                {
/* 247*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 247*/            return _mthdelegate();
                }

                SynchronizedSortedSet(SortedSet sortedset, Object obj)
                {
/* 250*/            super(sortedset, obj);
                }
    }

    static class SynchronizedSet extends SynchronizedCollection
        implements Set
    {

                Set _mthdelegate()
                {
/* 221*/            return (Set)super._mthdelegate();
                }

                public boolean equals(Object obj)
                {
/* 225*/            if(obj == this)
/* 226*/                return true;
/* 228*/            Object obj1 = mutex;
/* 228*/            JVM INSTR monitorenter ;
/* 229*/            return _mthdelegate().equals(obj);
/* 230*/            obj;
/* 230*/            throw obj;
                }

                public int hashCode()
                {
/* 234*/            Object obj = mutex;
/* 234*/            JVM INSTR monitorenter ;
/* 235*/            return _mthdelegate().hashCode();
                    Exception exception;
/* 236*/            exception;
/* 236*/            throw exception;
                }

                volatile Collection _mthdelegate()
                {
/* 213*/            return _mthdelegate();
                }

                volatile Object _mthdelegate()
                {
/* 213*/            return _mthdelegate();
                }

                SynchronizedSet(Set set1, Object obj)
                {
/* 217*/            super(set1, obj);
                }
    }

    static class SynchronizedCollection extends SynchronizedObject
        implements Collection
    {

                Collection _mthdelegate()
                {
/* 114*/            return (Collection)super._mthdelegate();
                }

                public boolean add(Object obj)
                {
/* 119*/            Object obj1 = mutex;
/* 119*/            JVM INSTR monitorenter ;
/* 120*/            return _mthdelegate().add(obj);
/* 121*/            obj;
/* 121*/            throw obj;
                }

                public boolean addAll(Collection collection1)
                {
/* 126*/            Object obj = mutex;
/* 126*/            JVM INSTR monitorenter ;
/* 127*/            return _mthdelegate().addAll(collection1);
/* 128*/            collection1;
/* 128*/            throw collection1;
                }

                public void clear()
                {
/* 133*/            synchronized(mutex)
                    {
/* 134*/                _mthdelegate().clear();
                    }
                }

                public boolean contains(Object obj)
                {
/* 140*/            Object obj1 = mutex;
/* 140*/            JVM INSTR monitorenter ;
/* 141*/            return _mthdelegate().contains(obj);
/* 142*/            obj;
/* 142*/            throw obj;
                }

                public boolean containsAll(Collection collection1)
                {
/* 147*/            Object obj = mutex;
/* 147*/            JVM INSTR monitorenter ;
/* 148*/            return _mthdelegate().containsAll(collection1);
/* 149*/            collection1;
/* 149*/            throw collection1;
                }

                public boolean isEmpty()
                {
/* 154*/            Object obj = mutex;
/* 154*/            JVM INSTR monitorenter ;
/* 155*/            return _mthdelegate().isEmpty();
                    Exception exception;
/* 156*/            exception;
/* 156*/            throw exception;
                }

                public Iterator iterator()
                {
/* 161*/            return _mthdelegate().iterator();
                }

                public boolean remove(Object obj)
                {
/* 166*/            Object obj1 = mutex;
/* 166*/            JVM INSTR monitorenter ;
/* 167*/            return _mthdelegate().remove(obj);
/* 168*/            obj;
/* 168*/            throw obj;
                }

                public boolean removeAll(Collection collection1)
                {
/* 173*/            Object obj = mutex;
/* 173*/            JVM INSTR monitorenter ;
/* 174*/            return _mthdelegate().removeAll(collection1);
/* 175*/            collection1;
/* 175*/            throw collection1;
                }

                public boolean retainAll(Collection collection1)
                {
/* 180*/            Object obj = mutex;
/* 180*/            JVM INSTR monitorenter ;
/* 181*/            return _mthdelegate().retainAll(collection1);
/* 182*/            collection1;
/* 182*/            throw collection1;
                }

                public int size()
                {
/* 187*/            Object obj = mutex;
/* 187*/            JVM INSTR monitorenter ;
/* 188*/            return _mthdelegate().size();
                    Exception exception;
/* 189*/            exception;
/* 189*/            throw exception;
                }

                public Object[] toArray()
                {
/* 194*/            Object obj = mutex;
/* 194*/            JVM INSTR monitorenter ;
/* 195*/            return _mthdelegate().toArray();
                    Exception exception;
/* 196*/            exception;
/* 196*/            throw exception;
                }

                public Object[] toArray(Object aobj[])
                {
/* 201*/            Object obj = mutex;
/* 201*/            JVM INSTR monitorenter ;
/* 202*/            return _mthdelegate().toArray(aobj);
/* 203*/            aobj;
/* 203*/            throw aobj;
                }

                volatile Object _mthdelegate()
                {
/* 105*/            return _mthdelegate();
                }

                private SynchronizedCollection(Collection collection1, Object obj)
                {
/* 109*/            super(collection1, obj);
                }

    }

    static class SynchronizedObject
        implements Serializable
    {

                Object _mthdelegate()
                {
/*  73*/            return _flddelegate;
                }

                public String toString()
                {
/*  79*/            Object obj = mutex;
/*  79*/            JVM INSTR monitorenter ;
/*  80*/            return _flddelegate.toString();
                    Exception exception;
/*  81*/            exception;
/*  81*/            throw exception;
                }

                final Object _flddelegate;
                final Object mutex;

                SynchronizedObject(Object obj, Object obj1)
                {
/*  68*/            _flddelegate = Preconditions.checkNotNull(obj);
/*  69*/            mutex = obj1 != null ? obj1 : ((Object) (this));
                }
    }


            private static Collection collection(Collection collection1, Object obj)
            {
/* 102*/        return new SynchronizedCollection(collection1, obj);
            }

            static Set set(Set set1, Object obj)
            {
/* 210*/        return new SynchronizedSet(set1, obj);
            }

            private static SortedSet sortedSet(SortedSet sortedset, Object obj)
            {
/* 244*/        return new SynchronizedSortedSet(sortedset, obj);
            }

            private static List list(List list1, Object obj)
            {
/* 303*/        if(list1 instanceof RandomAccess)
/* 303*/            return new SynchronizedRandomAccessList(list1, obj);
/* 303*/        else
/* 303*/            return new SynchronizedList(list1, obj);
            }

            static Multiset multiset(Multiset multiset1, Object obj)
            {
/* 412*/        if((multiset1 instanceof SynchronizedMultiset) || (multiset1 instanceof ImmutableMultiset))
/* 414*/            return multiset1;
/* 416*/        else
/* 416*/            return new SynchronizedMultiset(multiset1, obj);
            }

            static Multimap multimap(Multimap multimap1, Object obj)
            {
/* 507*/        if((multimap1 instanceof SynchronizedMultimap) || (multimap1 instanceof ImmutableMultimap))
/* 509*/            return multimap1;
/* 511*/        else
/* 511*/            return new SynchronizedMultimap(multimap1, obj);
            }

            static ListMultimap listMultimap(ListMultimap listmultimap, Object obj)
            {
/* 692*/        if((listmultimap instanceof SynchronizedListMultimap) || (listmultimap instanceof ImmutableListMultimap))
/* 694*/            return listmultimap;
/* 696*/        else
/* 696*/            return new SynchronizedListMultimap(listmultimap, obj);
            }

            static SetMultimap setMultimap(SetMultimap setmultimap, Object obj)
            {
/* 729*/        if((setmultimap instanceof SynchronizedSetMultimap) || (setmultimap instanceof ImmutableSetMultimap))
/* 731*/            return setmultimap;
/* 733*/        else
/* 733*/            return new SynchronizedSetMultimap(setmultimap, obj);
            }

            static SortedSetMultimap sortedSetMultimap(SortedSetMultimap sortedsetmultimap, Object obj)
            {
/* 776*/        if(sortedsetmultimap instanceof SynchronizedSortedSetMultimap)
/* 777*/            return sortedsetmultimap;
/* 779*/        else
/* 779*/            return new SynchronizedSortedSetMultimap(sortedsetmultimap, obj);
            }

            private static Collection typePreservingCollection(Collection collection1, Object obj)
            {
/* 818*/        if(collection1 instanceof SortedSet)
/* 819*/            return sortedSet((SortedSet)collection1, obj);
/* 821*/        if(collection1 instanceof Set)
/* 822*/            return set((Set)collection1, obj);
/* 824*/        if(collection1 instanceof List)
/* 825*/            return list((List)collection1, obj);
/* 827*/        else
/* 827*/            return collection(collection1, obj);
            }

            private static Set typePreservingSet(Set set1, Object obj)
            {
/* 832*/        if(set1 instanceof SortedSet)
/* 833*/            return sortedSet((SortedSet)set1, obj);
/* 835*/        else
/* 835*/            return set(set1, obj);
            }

            static SortedMap sortedMap(SortedMap sortedmap, Object obj)
            {
/*1050*/        return new SynchronizedSortedMap(sortedmap, obj);
            }

            static BiMap biMap(BiMap bimap, Object obj)
            {
/*1104*/        if((bimap instanceof SynchronizedBiMap) || (bimap instanceof ImmutableBiMap))
/*1106*/            return bimap;
/*1108*/        else
/*1108*/            return new SynchronizedBiMap(bimap, obj, null);
            }

            static NavigableSet navigableSet(NavigableSet navigableset, Object obj)
            {
/*1330*/        return new SynchronizedNavigableSet(navigableset, obj);
            }

            static NavigableSet navigableSet(NavigableSet navigableset)
            {
/*1335*/        return navigableSet(navigableset, null);
            }

            static NavigableMap navigableMap(NavigableMap navigablemap)
            {
/*1341*/        return navigableMap(navigablemap, null);
            }

            static NavigableMap navigableMap(NavigableMap navigablemap, Object obj)
            {
/*1347*/        return new SynchronizedNavigableMap(navigablemap, obj);
            }

            private static java.util.Map.Entry nullableSynchronizedEntry(java.util.Map.Entry entry, Object obj)
            {
/*1516*/        if(entry == null)
/*1517*/            return null;
/*1519*/        else
/*1519*/            return new SynchronizedEntry(entry, obj);
            }

            static Queue queue(Queue queue1, Object obj)
            {
/*1569*/        if(queue1 instanceof SynchronizedQueue)
/*1569*/            return queue1;
/*1569*/        else
/*1569*/            return new SynchronizedQueue(queue1, obj);
            }

            static Deque deque(Deque deque1, Object obj)
            {
/*1625*/        return new SynchronizedDeque(deque1, obj);
            }






}
