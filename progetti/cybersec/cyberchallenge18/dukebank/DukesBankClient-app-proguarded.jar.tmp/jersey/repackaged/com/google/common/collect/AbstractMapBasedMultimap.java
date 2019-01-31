// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultimap, Maps, Iterators, Collections2, 
//            CollectPreconditions, Sets

abstract class AbstractMapBasedMultimap extends AbstractMultimap
    implements Serializable
{
    class NavigableAsMap extends SortedAsMap
        implements NavigableMap
    {

                NavigableMap sortedMap()
                {
/*1427*/            return (NavigableMap)super.sortedMap();
                }

                public java.util.Map.Entry lowerEntry(Object obj)
                {
/*1432*/            if((obj = sortedMap().lowerEntry(obj)) == null)
/*1433*/                return null;
/*1433*/            else
/*1433*/                return wrapEntry(((java.util.Map.Entry) (obj)));
                }

                public Object lowerKey(Object obj)
                {
/*1438*/            return sortedMap().lowerKey(obj);
                }

                public java.util.Map.Entry floorEntry(Object obj)
                {
/*1443*/            if((obj = sortedMap().floorEntry(obj)) == null)
/*1444*/                return null;
/*1444*/            else
/*1444*/                return wrapEntry(((java.util.Map.Entry) (obj)));
                }

                public Object floorKey(Object obj)
                {
/*1449*/            return sortedMap().floorKey(obj);
                }

                public java.util.Map.Entry ceilingEntry(Object obj)
                {
/*1454*/            if((obj = sortedMap().ceilingEntry(obj)) == null)
/*1455*/                return null;
/*1455*/            else
/*1455*/                return wrapEntry(((java.util.Map.Entry) (obj)));
                }

                public Object ceilingKey(Object obj)
                {
/*1460*/            return sortedMap().ceilingKey(obj);
                }

                public java.util.Map.Entry higherEntry(Object obj)
                {
/*1465*/            if((obj = sortedMap().higherEntry(obj)) == null)
/*1466*/                return null;
/*1466*/            else
/*1466*/                return wrapEntry(((java.util.Map.Entry) (obj)));
                }

                public Object higherKey(Object obj)
                {
/*1471*/            return sortedMap().higherKey(obj);
                }

                public java.util.Map.Entry firstEntry()
                {
                    java.util.Map.Entry entry;
/*1476*/            if((entry = sortedMap().firstEntry()) == null)
/*1477*/                return null;
/*1477*/            else
/*1477*/                return wrapEntry(entry);
                }

                public java.util.Map.Entry lastEntry()
                {
                    java.util.Map.Entry entry;
/*1482*/            if((entry = sortedMap().lastEntry()) == null)
/*1483*/                return null;
/*1483*/            else
/*1483*/                return wrapEntry(entry);
                }

                public java.util.Map.Entry pollFirstEntry()
                {
/*1488*/            return pollAsMapEntry(entrySet().iterator());
                }

                public java.util.Map.Entry pollLastEntry()
                {
/*1493*/            return pollAsMapEntry(descendingMap().entrySet().iterator());
                }

                java.util.Map.Entry pollAsMapEntry(Iterator iterator)
                {
/*1497*/            if(!iterator.hasNext())
                    {
/*1498*/                return null;
                    } else
                    {
/*1500*/                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                        Collection collection;
/*1501*/                (collection = createCollection()).addAll((Collection)entry.getValue());
/*1503*/                iterator.remove();
/*1504*/                return Maps.immutableEntry(entry.getKey(), unmodifiableCollectionSubclass(collection));
                    }
                }

                public NavigableMap descendingMap()
                {
/*1509*/            return new NavigableAsMap(sortedMap().descendingMap());
                }

                public NavigableSet keySet()
                {
/*1514*/            return (NavigableSet)super.keySet();
                }

                NavigableSet createKeySet()
                {
/*1519*/            return new NavigableKeySet(sortedMap());
                }

                public NavigableSet navigableKeySet()
                {
/*1524*/            return keySet();
                }

                public NavigableSet descendingKeySet()
                {
/*1529*/            return descendingMap().navigableKeySet();
                }

                public NavigableMap subMap(Object obj, Object obj1)
                {
/*1534*/            return subMap(obj, true, obj1, false);
                }

                public NavigableMap subMap(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*1540*/            return new NavigableAsMap(sortedMap().subMap(obj, flag, obj1, flag1));
                }

                public NavigableMap headMap(Object obj)
                {
/*1545*/            return headMap(obj, false);
                }

                public NavigableMap headMap(Object obj, boolean flag)
                {
/*1550*/            return new NavigableAsMap(sortedMap().headMap(obj, flag));
                }

                public NavigableMap tailMap(Object obj)
                {
/*1555*/            return tailMap(obj, true);
                }

                public NavigableMap tailMap(Object obj, boolean flag)
                {
/*1560*/            return new NavigableAsMap(sortedMap().tailMap(obj, flag));
                }

                volatile SortedSet createKeySet()
                {
/*1418*/            return createKeySet();
                }

                public volatile SortedSet keySet()
                {
/*1418*/            return keySet();
                }

                public volatile SortedMap tailMap(Object obj)
                {
/*1418*/            return tailMap(obj);
                }

                public volatile SortedMap subMap(Object obj, Object obj1)
                {
/*1418*/            return subMap(obj, obj1);
                }

                public volatile SortedMap headMap(Object obj)
                {
/*1418*/            return headMap(obj);
                }

                volatile SortedMap sortedMap()
                {
/*1418*/            return sortedMap();
                }

                public volatile Set keySet()
                {
/*1418*/            return keySet();
                }

                volatile Set createKeySet()
                {
/*1418*/            return createKeySet();
                }

                final AbstractMapBasedMultimap this$0;

                NavigableAsMap(NavigableMap navigablemap)
                {
/*1421*/            this$0 = AbstractMapBasedMultimap.this;
/*1422*/            super(navigablemap);
                }
    }

    class SortedAsMap extends AsMap
        implements SortedMap
    {

                SortedMap sortedMap()
                {
/*1370*/            return (SortedMap)submap;
                }

                public Comparator comparator()
                {
/*1375*/            return sortedMap().comparator();
                }

                public Object firstKey()
                {
/*1380*/            return sortedMap().firstKey();
                }

                public Object lastKey()
                {
/*1385*/            return sortedMap().lastKey();
                }

                public SortedMap headMap(Object obj)
                {
/*1390*/            return new SortedAsMap(sortedMap().headMap(obj));
                }

                public SortedMap subMap(Object obj, Object obj1)
                {
/*1395*/            return new SortedAsMap(sortedMap().subMap(obj, obj1));
                }

                public SortedMap tailMap(Object obj)
                {
/*1400*/            return new SortedAsMap(sortedMap().tailMap(obj));
                }

                public SortedSet keySet()
                {
                    SortedSet sortedset;
/*1408*/            if((sortedset = sortedKeySet) == null)
/*1409*/                return sortedKeySet = createKeySet();
/*1409*/            else
/*1409*/                return sortedset;
                }

                SortedSet createKeySet()
                {
/*1414*/            return new SortedKeySet(sortedMap());
                }

                public volatile Set keySet()
                {
/*1363*/            return keySet();
                }

                volatile Set createKeySet()
                {
/*1363*/            return createKeySet();
                }

                SortedSet sortedKeySet;
                final AbstractMapBasedMultimap this$0;

                SortedAsMap(SortedMap sortedmap)
                {
/*1365*/            this$0 = AbstractMapBasedMultimap.this;
/*1366*/            super(sortedmap);
                }
    }

    class AsMap extends Maps.ImprovedAbstractMap
    {
        class AsMapIterator
            implements Iterator
        {

                    public boolean hasNext()
                    {
/*1344*/                return delegateIterator.hasNext();
                    }

                    public java.util.Map.Entry next()
                    {
/*1349*/                java.util.Map.Entry entry = (java.util.Map.Entry)delegateIterator.next();
/*1350*/                collection = (Collection)entry.getValue();
/*1351*/                return wrapEntry(entry);
                    }

                    public void remove()
                    {
/*1356*/                delegateIterator.remove();
/*1357*/                totalSize-= = collection.size();
/*1358*/                collection.clear();
                    }

                    public volatile Object next()
                    {
/*1337*/                return next();
                    }

                    final Iterator delegateIterator;
                    Collection collection;
                    final AsMap this$1;

                    AsMapIterator()
                    {
/*1337*/                this$1 = AsMap.this;
/*1337*/                super();
/*1338*/                delegateIterator = submap.entrySet().iterator();
                    }
        }

        class AsMapEntries extends Maps.EntrySet
        {

                    Map map()
                    {
/*1313*/                return AsMap.this;
                    }

                    public Iterator iterator()
                    {
/*1317*/                return new AsMapIterator();
                    }

                    public boolean contains(Object obj)
                    {
/*1323*/                return Collections2.safeContains(submap.entrySet(), obj);
                    }

                    public boolean remove(Object obj)
                    {
/*1327*/                if(!contains(obj))
                        {
/*1328*/                    return false;
                        } else
                        {
/*1330*/                    obj = (java.util.Map.Entry)obj;
/*1331*/                    removeValuesForKey(((java.util.Map.Entry) (obj)).getKey());
/*1332*/                    return true;
                        }
                    }

                    final AsMap this$1;

                    AsMapEntries()
                    {
/*1310*/                this$1 = AsMap.this;
/*1310*/                super();
                    }
        }


                protected Set createEntrySet()
                {
/*1243*/            return new AsMapEntries();
                }

                public boolean containsKey(Object obj)
                {
/*1249*/            return Maps.safeContainsKey(submap, obj);
                }

                public Collection get(Object obj)
                {
                    Collection collection;
/*1253*/            if((collection = (Collection)Maps.safeGet(submap, obj)) == null)
                    {
/*1255*/                return null;
                    } else
                    {
/*1258*/                obj = obj;
/*1259*/                return wrapCollection(obj, collection);
                    }
                }

                public Set keySet()
                {
/*1263*/            return AbstractMapBasedMultimap.this.keySet();
                }

                public int size()
                {
/*1268*/            return submap.size();
                }

                public Collection remove(Object obj)
                {
/*1272*/            if((obj = (Collection)submap.remove(obj)) == null)
                    {
/*1274*/                return null;
                    } else
                    {
                        Collection collection;
/*1277*/                (collection = createCollection()).addAll(((Collection) (obj)));
/*1279*/                totalSize-= = ((Collection) (obj)).size();
/*1280*/                ((Collection) (obj)).clear();
/*1281*/                return collection;
                    }
                }

                public boolean equals(Object obj)
                {
/*1285*/            return this == obj || submap.equals(obj);
                }

                public int hashCode()
                {
/*1289*/            return submap.hashCode();
                }

                public String toString()
                {
/*1293*/            return submap.toString();
                }

                public void clear()
                {
/*1298*/            if(submap == map)
                    {
/*1299*/                AbstractMapBasedMultimap.this.clear();
/*1299*/                return;
                    } else
                    {
/*1301*/                Iterators.clear(new AsMapIterator());
/*1303*/                return;
                    }
                }

                java.util.Map.Entry wrapEntry(java.util.Map.Entry entry)
                {
                    Object obj;
/*1306*/            return Maps.immutableEntry(obj = entry.getKey(), wrapCollection(obj, (Collection)entry.getValue()));
                }

                public volatile Object remove(Object obj)
                {
/*1230*/            return remove(obj);
                }

                public volatile Object get(Object obj)
                {
/*1230*/            return get(obj);
                }

                final transient Map submap;
                final AbstractMapBasedMultimap this$0;

                AsMap(Map map1)
                {
/*1237*/            this$0 = AbstractMapBasedMultimap.this;
/*1237*/            super();
/*1238*/            submap = map1;
                }
    }

    abstract class Itr
        implements Iterator
    {

                abstract Object output(Object obj, Object obj1);

                public boolean hasNext()
                {
/*1139*/            return keyIterator.hasNext() || valueIterator.hasNext();
                }

                public Object next()
                {
/*1144*/            if(!valueIterator.hasNext())
                    {
/*1145*/                java.util.Map.Entry entry = (java.util.Map.Entry)keyIterator.next();
/*1146*/                key = entry.getKey();
/*1147*/                collection = (Collection)entry.getValue();
/*1148*/                valueIterator = collection.iterator();
                    }
/*1150*/            return output(key, valueIterator.next());
                }

                public void remove()
                {
/*1155*/            valueIterator.remove();
/*1156*/            if(collection.isEmpty())
/*1157*/                keyIterator.remove();
/*1159*/            totalSize--;
                }

                final Iterator keyIterator;
                Object key;
                Collection collection;
                Iterator valueIterator;
                final AbstractMapBasedMultimap this$0;

                Itr()
                {
/*1128*/            this$0 = AbstractMapBasedMultimap.this;
/*1128*/            super();
/*1129*/            keyIterator = map.entrySet().iterator();
/*1130*/            key = null;
/*1131*/            collection = null;
/*1132*/            valueIterator = Iterators.emptyModifiableIterator();
                }
    }

    class NavigableKeySet extends SortedKeySet
        implements NavigableSet
    {

                NavigableMap sortedMap()
                {
/*1030*/            return (NavigableMap)super.sortedMap();
                }

                public Object lower(Object obj)
                {
/*1035*/            return sortedMap().lowerKey(obj);
                }

                public Object floor(Object obj)
                {
/*1040*/            return sortedMap().floorKey(obj);
                }

                public Object ceiling(Object obj)
                {
/*1045*/            return sortedMap().ceilingKey(obj);
                }

                public Object higher(Object obj)
                {
/*1050*/            return sortedMap().higherKey(obj);
                }

                public Object pollFirst()
                {
/*1055*/            return Iterators.pollNext(iterator());
                }

                public Object pollLast()
                {
/*1060*/            return Iterators.pollNext(descendingIterator());
                }

                public NavigableSet descendingSet()
                {
/*1065*/            return new NavigableKeySet(sortedMap().descendingMap());
                }

                public Iterator descendingIterator()
                {
/*1070*/            return descendingSet().iterator();
                }

                public NavigableSet headSet(Object obj)
                {
/*1075*/            return headSet(obj, false);
                }

                public NavigableSet headSet(Object obj, boolean flag)
                {
/*1080*/            return new NavigableKeySet(sortedMap().headMap(obj, flag));
                }

                public NavigableSet subSet(Object obj, Object obj1)
                {
/*1085*/            return subSet(obj, true, obj1, false);
                }

                public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/*1091*/            return new NavigableKeySet(sortedMap().subMap(obj, flag, obj1, flag1));
                }

                public NavigableSet tailSet(Object obj)
                {
/*1097*/            return tailSet(obj, true);
                }

                public NavigableSet tailSet(Object obj, boolean flag)
                {
/*1102*/            return new NavigableKeySet(sortedMap().tailMap(obj, flag));
                }

                public volatile SortedSet tailSet(Object obj)
                {
/*1022*/            return tailSet(obj);
                }

                public volatile SortedSet subSet(Object obj, Object obj1)
                {
/*1022*/            return subSet(obj, obj1);
                }

                public volatile SortedSet headSet(Object obj)
                {
/*1022*/            return headSet(obj);
                }

                volatile SortedMap sortedMap()
                {
/*1022*/            return sortedMap();
                }

                final AbstractMapBasedMultimap this$0;

                NavigableKeySet(NavigableMap navigablemap)
                {
/*1024*/            this$0 = AbstractMapBasedMultimap.this;
/*1025*/            super(navigablemap);
                }
    }

    class SortedKeySet extends KeySet
        implements SortedSet
    {

                SortedMap sortedMap()
                {
/* 988*/            return (SortedMap)super.map();
                }

                public Comparator comparator()
                {
/* 993*/            return sortedMap().comparator();
                }

                public Object first()
                {
/* 998*/            return sortedMap().firstKey();
                }

                public SortedSet headSet(Object obj)
                {
/*1003*/            return new SortedKeySet(sortedMap().headMap(obj));
                }

                public Object last()
                {
/*1008*/            return sortedMap().lastKey();
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/*1013*/            return new SortedKeySet(sortedMap().subMap(obj, obj1));
                }

                public SortedSet tailSet(Object obj)
                {
/*1018*/            return new SortedKeySet(sortedMap().tailMap(obj));
                }

                final AbstractMapBasedMultimap this$0;

                SortedKeySet(SortedMap sortedmap)
                {
/* 983*/            this$0 = AbstractMapBasedMultimap.this;
/* 984*/            super(sortedmap);
                }
    }

    class KeySet extends Maps.KeySet
    {

                public Iterator iterator()
                {
/* 925*/            final Iterator entryIterator = map().entrySet().iterator();
/* 927*/            return new Iterator() {

                        public boolean hasNext()
                        {
/* 932*/                    return entryIterator.hasNext();
                        }

                        public Object next()
                        {
/* 936*/                    entry = (java.util.Map.Entry)entryIterator.next();
/* 937*/                    return entry.getKey();
                        }

                        public void remove()
                        {
/* 941*/                    CollectPreconditions.checkRemove(entry != null);
/* 942*/                    Collection collection = (Collection)entry.getValue();
/* 943*/                    entryIterator.remove();
/* 944*/                    totalSize-= = collection.size();
/* 945*/                    collection.clear();
                        }

                        java.util.Map.Entry entry;
                        final Iterator val$entryIterator;
                        final KeySet this$1;

                        
                        {
/* 927*/                    this$1 = KeySet.this;
/* 927*/                    entryIterator = iterator1;
/* 927*/                    super();
                        }
            };
                }

                public boolean remove(Object obj)
                {
/* 953*/            int i = 0;
/* 954*/            if((obj = (Collection)map().remove(obj)) != null)
                    {
/* 956*/                i = ((Collection) (obj)).size();
/* 957*/                ((Collection) (obj)).clear();
/* 958*/                totalSize-= = i;
                    }
/* 960*/            return i > 0;
                }

                public void clear()
                {
/* 965*/            Iterators.clear(iterator());
                }

                public boolean containsAll(Collection collection)
                {
/* 969*/            return map().keySet().containsAll(collection);
                }

                public boolean equals(Object obj)
                {
/* 973*/            return this == obj || map().keySet().equals(obj);
                }

                public int hashCode()
                {
/* 977*/            return map().keySet().hashCode();
                }

                final AbstractMapBasedMultimap this$0;

                KeySet(Map map1)
                {
/* 920*/            this$0 = AbstractMapBasedMultimap.this;
/* 921*/            super(map1);
                }
    }

    class RandomAccessWrappedList extends WrappedList
        implements RandomAccess
    {

                final AbstractMapBasedMultimap this$0;

                RandomAccessWrappedList(Object obj, List list, WrappedCollection wrappedcollection)
                {
/* 906*/            this$0 = AbstractMapBasedMultimap.this;
/* 907*/            super(obj, list, wrappedcollection);
                }
    }

    class WrappedList extends WrappedCollection
        implements List
    {
        class WrappedListIterator extends WrappedCollection.WrappedIterator
            implements ListIterator
        {

                    private ListIterator getDelegateListIterator()
                    {
/* 859*/                return (ListIterator)getDelegateIterator();
                    }

                    public boolean hasPrevious()
                    {
/* 864*/                return getDelegateListIterator().hasPrevious();
                    }

                    public Object previous()
                    {
/* 869*/                return getDelegateListIterator().previous();
                    }

                    public int nextIndex()
                    {
/* 874*/                return getDelegateListIterator().nextIndex();
                    }

                    public int previousIndex()
                    {
/* 879*/                return getDelegateListIterator().previousIndex();
                    }

                    public void set(Object obj)
                    {
/* 884*/                getDelegateListIterator().set(obj);
                    }

                    public void add(Object obj)
                    {
/* 889*/                boolean flag = isEmpty();
/* 890*/                getDelegateListIterator().add(obj);
/* 891*/                totalSize++;
/* 892*/                if(flag)
/* 893*/                    addToMap();
                    }

                    final WrappedList this$1;

                    WrappedListIterator()
                    {
/* 852*/                this$1 = WrappedList.this;
/* 852*/                super();
                    }

                    public WrappedListIterator(int i)
                    {
/* 854*/                this$1 = WrappedList.this;
/* 855*/                super(getListDelegate().listIterator(i));
                    }
        }


                List getListDelegate()
                {
/* 765*/            return (List)getDelegate();
                }

                public boolean addAll(int i, Collection collection)
                {
/* 770*/            if(collection.isEmpty())
/* 771*/                return false;
/* 773*/            int j = size();
/* 774*/            if((i = getListDelegate().addAll(i, collection)) != 0)
                    {
/* 776*/                collection = getDelegate().size();
/* 777*/                totalSize+= = collection - j;
/* 778*/                if(j == 0)
/* 779*/                    addToMap();
                    }
/* 782*/            return i;
                }

                public Object get(int i)
                {
/* 787*/            refreshIfEmpty();
/* 788*/            return getListDelegate().get(i);
                }

                public Object set(int i, Object obj)
                {
/* 793*/            refreshIfEmpty();
/* 794*/            return getListDelegate().set(i, obj);
                }

                public void add(int i, Object obj)
                {
/* 799*/            refreshIfEmpty();
/* 800*/            boolean flag = getDelegate().isEmpty();
/* 801*/            getListDelegate().add(i, obj);
/* 802*/            totalSize++;
/* 803*/            if(flag)
/* 804*/                addToMap();
                }

                public Object remove(int i)
                {
/* 810*/            refreshIfEmpty();
/* 811*/            i = ((int) (getListDelegate().remove(i)));
/* 812*/            totalSize--;
/* 813*/            removeIfEmpty();
/* 814*/            return i;
                }

                public int indexOf(Object obj)
                {
/* 819*/            refreshIfEmpty();
/* 820*/            return getListDelegate().indexOf(obj);
                }

                public int lastIndexOf(Object obj)
                {
/* 825*/            refreshIfEmpty();
/* 826*/            return getListDelegate().lastIndexOf(obj);
                }

                public ListIterator listIterator()
                {
/* 831*/            refreshIfEmpty();
/* 832*/            return new WrappedListIterator();
                }

                public ListIterator listIterator(int i)
                {
/* 837*/            refreshIfEmpty();
/* 838*/            return new WrappedListIterator(i);
                }

                public List subList(int i, int j)
                {
/* 843*/            refreshIfEmpty();
/* 844*/            return wrapList(getKey(), getListDelegate().subList(i, j), ((WrappedCollection) (getAncestor() != null ? getAncestor() : ((WrappedCollection) (this)))));
                }

                final AbstractMapBasedMultimap this$0;

                WrappedList(Object obj, List list, WrappedCollection wrappedcollection)
                {
/* 760*/            this$0 = AbstractMapBasedMultimap.this;
/* 761*/            super(obj, list, wrappedcollection);
                }
    }

    class WrappedNavigableSet extends WrappedSortedSet
        implements NavigableSet
    {

                NavigableSet getSortedSetDelegate()
                {
/* 691*/            return (NavigableSet)super.getSortedSetDelegate();
                }

                public Object lower(Object obj)
                {
/* 696*/            return getSortedSetDelegate().lower(obj);
                }

                public Object floor(Object obj)
                {
/* 701*/            return getSortedSetDelegate().floor(obj);
                }

                public Object ceiling(Object obj)
                {
/* 706*/            return getSortedSetDelegate().ceiling(obj);
                }

                public Object higher(Object obj)
                {
/* 711*/            return getSortedSetDelegate().higher(obj);
                }

                public Object pollFirst()
                {
/* 716*/            return Iterators.pollNext(iterator());
                }

                public Object pollLast()
                {
/* 721*/            return Iterators.pollNext(descendingIterator());
                }

                private NavigableSet wrap(NavigableSet navigableset)
                {
/* 725*/            return new WrappedNavigableSet(key, navigableset, ((WrappedCollection) (getAncestor() != null ? getAncestor() : ((WrappedCollection) (this)))));
                }

                public NavigableSet descendingSet()
                {
/* 731*/            return wrap(getSortedSetDelegate().descendingSet());
                }

                public Iterator descendingIterator()
                {
/* 736*/            return new WrappedCollection.WrappedIterator(getSortedSetDelegate().descendingIterator());
                }

                public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
                {
/* 742*/            return wrap(getSortedSetDelegate().subSet(obj, flag, obj1, flag1));
                }

                public NavigableSet headSet(Object obj, boolean flag)
                {
/* 748*/            return wrap(getSortedSetDelegate().headSet(obj, flag));
                }

                public NavigableSet tailSet(Object obj, boolean flag)
                {
/* 753*/            return wrap(getSortedSetDelegate().tailSet(obj, flag));
                }

                volatile SortedSet getSortedSetDelegate()
                {
/* 682*/            return getSortedSetDelegate();
                }

                final AbstractMapBasedMultimap this$0;

                WrappedNavigableSet(Object obj, NavigableSet navigableset, WrappedCollection wrappedcollection)
                {
/* 685*/            this$0 = AbstractMapBasedMultimap.this;
/* 686*/            super(obj, navigableset, wrappedcollection);
                }
    }

    class WrappedSortedSet extends WrappedCollection
        implements SortedSet
    {

                SortedSet getSortedSetDelegate()
                {
/* 637*/            return (SortedSet)getDelegate();
                }

                public Comparator comparator()
                {
/* 642*/            return getSortedSetDelegate().comparator();
                }

                public Object first()
                {
/* 647*/            refreshIfEmpty();
/* 648*/            return getSortedSetDelegate().first();
                }

                public Object last()
                {
/* 653*/            refreshIfEmpty();
/* 654*/            return getSortedSetDelegate().last();
                }

                public SortedSet headSet(Object obj)
                {
/* 659*/            refreshIfEmpty();
/* 660*/            return new WrappedSortedSet(getKey(), getSortedSetDelegate().headSet(obj), ((WrappedCollection) (getAncestor() != null ? getAncestor() : ((WrappedCollection) (this)))));
                }

                public SortedSet subSet(Object obj, Object obj1)
                {
/* 667*/            refreshIfEmpty();
/* 668*/            return new WrappedSortedSet(getKey(), getSortedSetDelegate().subSet(obj, obj1), ((WrappedCollection) (getAncestor() != null ? getAncestor() : ((WrappedCollection) (this)))));
                }

                public SortedSet tailSet(Object obj)
                {
/* 675*/            refreshIfEmpty();
/* 676*/            return new WrappedSortedSet(getKey(), getSortedSetDelegate().tailSet(obj), ((WrappedCollection) (getAncestor() != null ? getAncestor() : ((WrappedCollection) (this)))));
                }

                final AbstractMapBasedMultimap this$0;

                WrappedSortedSet(Object obj, SortedSet sortedset, WrappedCollection wrappedcollection)
                {
/* 632*/            this$0 = AbstractMapBasedMultimap.this;
/* 633*/            super(obj, sortedset, wrappedcollection);
                }
    }

    class WrappedSet extends WrappedCollection
        implements Set
    {

                public boolean removeAll(Collection collection)
                {
/* 608*/            if(collection.isEmpty())
/* 609*/                return false;
/* 611*/            int i = size();
/* 616*/            if((collection = Sets.removeAllImpl((Set)_flddelegate, collection)) != 0)
                    {
/* 618*/                int j = _flddelegate.size();
/* 619*/                totalSize+= = j - i;
/* 620*/                removeIfEmpty();
                    }
/* 622*/            return collection;
                }

                final AbstractMapBasedMultimap this$0;

                WrappedSet(Object obj, Set set)
                {
/* 602*/            this$0 = AbstractMapBasedMultimap.this;
/* 603*/            super(obj, set, null);
                }
    }

    class WrappedCollection extends AbstractCollection
    {
        class WrappedIterator
            implements Iterator
        {

                    void validateIterator()
                    {
/* 471*/                refreshIfEmpty();
/* 472*/                if(_flddelegate != originalDelegate)
/* 473*/                    throw new ConcurrentModificationException();
/* 475*/                else
/* 475*/                    return;
                    }

                    public boolean hasNext()
                    {
/* 479*/                validateIterator();
/* 480*/                return delegateIterator.hasNext();
                    }

                    public Object next()
                    {
/* 485*/                validateIterator();
/* 486*/                return delegateIterator.next();
                    }

                    public void remove()
                    {
/* 491*/                delegateIterator.remove();
/* 492*/                totalSize--;
/* 493*/                removeIfEmpty();
                    }

                    Iterator getDelegateIterator()
                    {
/* 497*/                validateIterator();
/* 498*/                return delegateIterator;
                    }

                    final Iterator delegateIterator;
                    final Collection originalDelegate;
                    final WrappedCollection this$1;

                    WrappedIterator()
                    {
/* 458*/                this$1 = WrappedCollection.this;
/* 458*/                super();
/* 456*/                originalDelegate = _flddelegate;
/* 459*/                delegateIterator = iteratorOrListIterator(_flddelegate);
                    }

                    WrappedIterator(Iterator iterator1)
                    {
/* 462*/                this$1 = WrappedCollection.this;
/* 462*/                super();
/* 456*/                originalDelegate = _flddelegate;
/* 463*/                delegateIterator = iterator1;
                    }
        }


                void refreshIfEmpty()
                {
                    Collection collection;
/* 377*/            if(ancestor != null)
                    {
/* 378*/                ancestor.refreshIfEmpty();
/* 379*/                if(ancestor.getDelegate() != ancestorDelegate)
/* 380*/                    throw new ConcurrentModificationException();
                    } else
/* 382*/            if(_flddelegate.isEmpty() && (collection = (Collection)map.get(key)) != null)
/* 385*/                _flddelegate = collection;
                }

                void removeIfEmpty()
                {
/* 395*/            if(ancestor != null)
                    {
/* 396*/                ancestor.removeIfEmpty();
/* 396*/                return;
                    }
/* 397*/            if(_flddelegate.isEmpty())
/* 398*/                map.remove(key);
                }

                Object getKey()
                {
/* 403*/            return key;
                }

                void addToMap()
                {
/* 414*/            if(ancestor != null)
                    {
/* 415*/                ancestor.addToMap();
/* 415*/                return;
                    } else
                    {
/* 417*/                map.put(key, _flddelegate);
/* 419*/                return;
                    }
                }

                public int size()
                {
/* 422*/            refreshIfEmpty();
/* 423*/            return _flddelegate.size();
                }

                public boolean equals(Object obj)
                {
/* 427*/            if(obj == this)
                    {
/* 428*/                return true;
                    } else
                    {
/* 430*/                refreshIfEmpty();
/* 431*/                return _flddelegate.equals(obj);
                    }
                }

                public int hashCode()
                {
/* 435*/            refreshIfEmpty();
/* 436*/            return _flddelegate.hashCode();
                }

                public String toString()
                {
/* 440*/            refreshIfEmpty();
/* 441*/            return _flddelegate.toString();
                }

                Collection getDelegate()
                {
/* 445*/            return _flddelegate;
                }

                public Iterator iterator()
                {
/* 449*/            refreshIfEmpty();
/* 450*/            return new WrappedIterator();
                }

                public boolean add(Object obj)
                {
/* 503*/            refreshIfEmpty();
/* 504*/            boolean flag = _flddelegate.isEmpty();
/* 505*/            if((obj = _flddelegate.add(obj)) != 0)
                    {
/* 507*/                totalSize++;
/* 508*/                if(flag)
/* 509*/                    addToMap();
                    }
/* 512*/            return ((boolean) (obj));
                }

                WrappedCollection getAncestor()
                {
/* 516*/            return ancestor;
                }

                public boolean addAll(Collection collection)
                {
/* 522*/            if(collection.isEmpty())
/* 523*/                return false;
/* 525*/            int i = size();
/* 526*/            if((collection = _flddelegate.addAll(collection)) != 0)
                    {
/* 528*/                int j = _flddelegate.size();
/* 529*/                totalSize+= = j - i;
/* 530*/                if(i == 0)
/* 531*/                    addToMap();
                    }
/* 534*/            return collection;
                }

                public boolean contains(Object obj)
                {
/* 538*/            refreshIfEmpty();
/* 539*/            return _flddelegate.contains(obj);
                }

                public boolean containsAll(Collection collection)
                {
/* 543*/            refreshIfEmpty();
/* 544*/            return _flddelegate.containsAll(collection);
                }

                public void clear()
                {
                    int i;
/* 548*/            if((i = size()) == 0)
                    {
/* 550*/                return;
                    } else
                    {
/* 552*/                _flddelegate.clear();
/* 553*/                totalSize-= = i;
/* 554*/                removeIfEmpty();
/* 555*/                return;
                    }
                }

                public boolean remove(Object obj)
                {
/* 558*/            refreshIfEmpty();
/* 559*/            if((obj = _flddelegate.remove(obj)) != 0)
                    {
/* 561*/                totalSize--;
/* 562*/                removeIfEmpty();
                    }
/* 564*/            return ((boolean) (obj));
                }

                public boolean removeAll(Collection collection)
                {
/* 568*/            if(collection.isEmpty())
/* 569*/                return false;
/* 571*/            int i = size();
/* 572*/            if((collection = _flddelegate.removeAll(collection)) != 0)
                    {
/* 574*/                int j = _flddelegate.size();
/* 575*/                totalSize+= = j - i;
/* 576*/                removeIfEmpty();
                    }
/* 578*/            return collection;
                }

                public boolean retainAll(Collection collection)
                {
/* 582*/            Preconditions.checkNotNull(collection);
/* 583*/            int i = size();
/* 584*/            if((collection = _flddelegate.retainAll(collection)) != 0)
                    {
/* 586*/                int j = _flddelegate.size();
/* 587*/                totalSize+= = j - i;
/* 588*/                removeIfEmpty();
                    }
/* 590*/            return collection;
                }

                final Object key;
                Collection _flddelegate;
                final WrappedCollection ancestor;
                final Collection ancestorDelegate;
                final AbstractMapBasedMultimap this$0;

                WrappedCollection(Object obj, Collection collection, WrappedCollection wrappedcollection)
                {
/* 361*/            this$0 = AbstractMapBasedMultimap.this;
/* 361*/            super();
/* 362*/            key = obj;
/* 363*/            _flddelegate = collection;
/* 364*/            ancestor = wrappedcollection;
/* 365*/            ancestorDelegate = wrappedcollection != null ? wrappedcollection.getDelegate() : null;
                }
    }


            protected AbstractMapBasedMultimap(Map map1)
            {
/* 123*/        Preconditions.checkArgument(map1.isEmpty());
/* 124*/        map = map1;
            }

            final void setMap(Map map1)
            {
/* 129*/        map = map1;
/* 130*/        totalSize = 0;
/* 131*/        for(map1 = map1.values().iterator(); map1.hasNext();)
                {
                    Collection collection;
/* 131*/            Preconditions.checkArgument(!(collection = (Collection)map1.next()).isEmpty());
/* 133*/            totalSize += collection.size();
                }

            }

            Collection createUnmodifiableEmptyCollection()
            {
/* 143*/        return unmodifiableCollectionSubclass(createCollection());
            }

            abstract Collection createCollection();

            Collection createCollection(Object obj)
            {
/* 169*/        return createCollection();
            }

            Map backingMap()
            {
/* 173*/        return map;
            }

            public int size()
            {
/* 180*/        return totalSize;
            }

            public boolean containsKey(Object obj)
            {
/* 185*/        return map.containsKey(obj);
            }

            public boolean put(Object obj, Object obj1)
            {
                Collection collection;
/* 192*/        if((collection = (Collection)map.get(obj)) == null)
/* 194*/            if((collection = createCollection(obj)).add(obj1))
                    {
/* 196*/                totalSize++;
/* 197*/                map.put(obj, collection);
/* 198*/                return true;
                    } else
                    {
/* 200*/                throw new AssertionError("New Collection violated the Collection spec");
                    }
/* 202*/        if(collection.add(obj1))
                {
/* 203*/            totalSize++;
/* 204*/            return true;
                } else
                {
/* 206*/            return false;
                }
            }

            private Collection getOrCreateCollection(Object obj)
            {
                Collection collection;
/* 211*/        if((collection = (Collection)map.get(obj)) == null)
                {
/* 213*/            collection = createCollection(obj);
/* 214*/            map.put(obj, collection);
                }
/* 216*/        return collection;
            }

            public Collection replaceValues(Object obj, Iterable iterable)
            {
/* 228*/        if(!(iterable = iterable.iterator()).hasNext())
/* 230*/            return removeAll(obj);
/* 234*/        obj = getOrCreateCollection(obj);
                Collection collection;
/* 235*/        (collection = createCollection()).addAll(((Collection) (obj)));
/* 238*/        totalSize -= ((Collection) (obj)).size();
/* 239*/        ((Collection) (obj)).clear();
/* 241*/        do
                {
/* 241*/            if(!iterable.hasNext())
/* 242*/                break;
/* 242*/            if(((Collection) (obj)).add(iterable.next()))
/* 243*/                totalSize++;
                } while(true);
/* 247*/        return unmodifiableCollectionSubclass(collection);
            }

            public Collection removeAll(Object obj)
            {
/* 257*/        if((obj = (Collection)map.remove(obj)) == null)
                {
/* 260*/            return createUnmodifiableEmptyCollection();
                } else
                {
                    Collection collection;
/* 263*/            (collection = createCollection()).addAll(((Collection) (obj)));
/* 265*/            totalSize -= ((Collection) (obj)).size();
/* 266*/            ((Collection) (obj)).clear();
/* 268*/            return unmodifiableCollectionSubclass(collection);
                }
            }

            Collection unmodifiableCollectionSubclass(Collection collection)
            {
/* 274*/        if(collection instanceof SortedSet)
/* 275*/            return Collections.unmodifiableSortedSet((SortedSet)collection);
/* 276*/        if(collection instanceof Set)
/* 277*/            return Collections.unmodifiableSet((Set)collection);
/* 278*/        if(collection instanceof List)
/* 279*/            return Collections.unmodifiableList((List)collection);
/* 281*/        else
/* 281*/            return Collections.unmodifiableCollection(collection);
            }

            public void clear()
            {
                Collection collection;
/* 288*/        for(Iterator iterator = map.values().iterator(); iterator.hasNext(); (collection = (Collection)iterator.next()).clear());
/* 291*/        map.clear();
/* 292*/        totalSize = 0;
            }

            public Collection get(Object obj)
            {
                Collection collection;
/* 304*/        if((collection = (Collection)map.get(obj)) == null)
/* 306*/            collection = createCollection(obj);
/* 308*/        return wrapCollection(obj, collection);
            }

            Collection wrapCollection(Object obj, Collection collection)
            {
/* 319*/        if(collection instanceof SortedSet)
/* 320*/            return new WrappedSortedSet(obj, (SortedSet)collection, null);
/* 321*/        if(collection instanceof Set)
/* 322*/            return new WrappedSet(obj, (Set)collection);
/* 323*/        if(collection instanceof List)
/* 324*/            return wrapList(obj, (List)collection, null);
/* 326*/        else
/* 326*/            return new WrappedCollection(obj, collection, null);
            }

            private List wrapList(Object obj, List list, WrappedCollection wrappedcollection)
            {
/* 332*/        if(list instanceof RandomAccess)
/* 332*/            return new RandomAccessWrappedList(obj, list, wrappedcollection);
/* 332*/        else
/* 332*/            return new WrappedList(obj, list, wrappedcollection);
            }

            private Iterator iteratorOrListIterator(Collection collection)
            {
/* 595*/        if(collection instanceof List)
/* 595*/            return ((List)collection).listIterator();
/* 595*/        else
/* 595*/            return collection.iterator();
            }

            Set createKeySet()
            {
/* 915*/        if(map instanceof SortedMap)
/* 915*/            return new SortedKeySet((SortedMap)map);
/* 915*/        else
/* 915*/            return new KeySet(map);
            }

            private int removeValuesForKey(Object obj)
            {
/*1111*/        obj = (Collection)Maps.safeRemove(map, obj);
/*1113*/        int i = 0;
/*1114*/        if(obj != null)
                {
/*1115*/            i = ((Collection) (obj)).size();
/*1116*/            ((Collection) (obj)).clear();
/*1117*/            totalSize -= i;
                }
/*1119*/        return i;
            }

            public Collection values()
            {
/*1170*/        return super.values();
            }

            Iterator valueIterator()
            {
/*1175*/        return new Itr() {

                    Object output(Object obj, Object obj1)
                    {
/*1178*/                return obj1;
                    }

                    final AbstractMapBasedMultimap this$0;

                    
                    {
/*1175*/                this$0 = AbstractMapBasedMultimap.this;
/*1175*/                super();
                    }
        };
            }

            public Collection entries()
            {
/*1201*/        return super.entries();
            }

            Iterator entryIterator()
            {
/*1214*/        return new Itr() {

                    java.util.Map.Entry output(Object obj, Object obj1)
                    {
/*1217*/                return Maps.immutableEntry(obj, obj1);
                    }

                    volatile Object output(Object obj, Object obj1)
                    {
/*1214*/                return output(obj, obj1);
                    }

                    final AbstractMapBasedMultimap this$0;

                    
                    {
/*1214*/                this$0 = AbstractMapBasedMultimap.this;
/*1214*/                super();
                    }
        };
            }

            Map createAsMap()
            {
/*1226*/        if(map instanceof SortedMap)
/*1226*/            return new SortedAsMap((SortedMap)map);
/*1226*/        else
/*1226*/            return new AsMap(map);
            }

            private transient Map map;
            private transient int totalSize;
            private static final long serialVersionUID = 0x21f766b1f568c81dL;








}
