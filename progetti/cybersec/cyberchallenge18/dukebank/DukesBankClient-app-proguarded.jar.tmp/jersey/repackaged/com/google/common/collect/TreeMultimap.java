// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TreeMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.io.*;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractSortedKeySortedSetMultimap, AbstractMapBasedMultimap, Ordering, Serialization, 
//            Sets, Multimap, Multiset

public class TreeMultimap extends AbstractSortedKeySortedSetMultimap
{

            public static TreeMultimap create()
            {
/*  89*/        return new TreeMultimap(Ordering.natural(), Ordering.natural());
            }

            public static TreeMultimap create(Comparator comparator, Comparator comparator1)
            {
/* 103*/        return new TreeMultimap((Comparator)Preconditions.checkNotNull(comparator), (Comparator)Preconditions.checkNotNull(comparator1));
            }

            public static TreeMultimap create(Multimap multimap)
            {
/* 115*/        return new TreeMultimap(Ordering.natural(), Ordering.natural(), multimap);
            }

            TreeMultimap(Comparator comparator, Comparator comparator1)
            {
/* 121*/        super(new TreeMap(comparator));
/* 122*/        keyComparator = comparator;
/* 123*/        valueComparator = comparator1;
            }

            private TreeMultimap(Comparator comparator, Comparator comparator1, Multimap multimap)
            {
/* 129*/        this(comparator, comparator1);
/* 130*/        putAll(multimap);
            }

            SortedSet createCollection()
            {
/* 142*/        return new TreeSet(valueComparator);
            }

            Collection createCollection(Object obj)
            {
/* 147*/        if(obj == null)
/* 148*/            keyComparator().compare(obj, obj);
/* 150*/        return super.createCollection(obj);
            }

            public Comparator keyComparator()
            {
/* 157*/        return keyComparator;
            }

            public Comparator valueComparator()
            {
/* 162*/        return valueComparator;
            }

            NavigableMap backingMap()
            {
/* 174*/        return (NavigableMap)super.backingMap();
            }

            public NavigableSet get(Object obj)
            {
/* 183*/        return (NavigableSet)super.get(obj);
            }

            Collection unmodifiableCollectionSubclass(Collection collection)
            {
/* 189*/        return Sets.unmodifiableNavigableSet((NavigableSet)collection);
            }

            Collection wrapCollection(Object obj, Collection collection)
            {
/* 195*/        return new AbstractMapBasedMultimap.WrappedNavigableSet(this, obj, (NavigableSet)collection, null);
            }

            public NavigableSet keySet()
            {
/* 210*/        return (NavigableSet)super.keySet();
            }

            NavigableSet createKeySet()
            {
/* 216*/        return new AbstractMapBasedMultimap.NavigableKeySet(this, backingMap());
            }

            public NavigableMap asMap()
            {
/* 231*/        return (NavigableMap)super.asMap();
            }

            NavigableMap createAsMap()
            {
/* 237*/        return new AbstractMapBasedMultimap.NavigableAsMap(this, backingMap());
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/* 247*/        objectoutputstream.defaultWriteObject();
/* 248*/        objectoutputstream.writeObject(keyComparator());
/* 249*/        objectoutputstream.writeObject(valueComparator());
/* 250*/        Serialization.writeMultimap(this, objectoutputstream);
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/* 257*/        objectinputstream.defaultReadObject();
/* 258*/        keyComparator = (Comparator)Preconditions.checkNotNull((Comparator)objectinputstream.readObject());
/* 259*/        valueComparator = (Comparator)Preconditions.checkNotNull((Comparator)objectinputstream.readObject());
/* 260*/        setMap(new TreeMap(keyComparator));
/* 261*/        Serialization.populateMultimap(this, objectinputstream);
            }

            public volatile SortedSet keySet()
            {
/*  78*/        return keySet();
            }

            volatile SortedMap backingMap()
            {
/*  78*/        return backingMap();
            }

            public volatile SortedMap asMap()
            {
/*  78*/        return asMap();
            }

            public volatile Collection values()
            {
/*  78*/        return super.values();
            }

            public volatile Map asMap()
            {
/*  78*/        return asMap();
            }

            public volatile SortedSet replaceValues(Object obj, Iterable iterable)
            {
/*  78*/        return super.replaceValues(obj, iterable);
            }

            public volatile SortedSet removeAll(Object obj)
            {
/*  78*/        return super.removeAll(obj);
            }

            public volatile SortedSet get(Object obj)
            {
/*  78*/        return get(obj);
            }

            public volatile Set get(Object obj)
            {
/*  78*/        return get(obj);
            }

            public volatile Set keySet()
            {
/*  78*/        return keySet();
            }

            public volatile Collection get(Object obj)
            {
/*  78*/        return get(obj);
            }

            public volatile boolean equals(Object obj)
            {
/*  78*/        return super.equals(obj);
            }

            public volatile boolean put(Object obj, Object obj1)
            {
/*  78*/        return super.put(obj, obj1);
            }

            public volatile Set entries()
            {
/*  78*/        return super.entries();
            }

            volatile Set createCollection()
            {
/*  78*/        return createCollection();
            }

            volatile Map createAsMap()
            {
/*  78*/        return createAsMap();
            }

            volatile Set createKeySet()
            {
/*  78*/        return createKeySet();
            }

            public volatile void clear()
            {
/*  78*/        super.clear();
            }

            public volatile boolean containsKey(Object obj)
            {
/*  78*/        return super.containsKey(obj);
            }

            public volatile int size()
            {
/*  78*/        return super.size();
            }

            volatile Map backingMap()
            {
/*  78*/        return backingMap();
            }

            volatile Collection createCollection()
            {
/*  78*/        return createCollection();
            }

            public volatile String toString()
            {
/*  78*/        return super.toString();
            }

            public volatile int hashCode()
            {
/*  78*/        return super.hashCode();
            }

            public volatile Multiset keys()
            {
/*  78*/        return super.keys();
            }

            public volatile boolean putAll(Multimap multimap)
            {
/*  78*/        return super.putAll(multimap);
            }

            public volatile boolean putAll(Object obj, Iterable iterable)
            {
/*  78*/        return super.putAll(obj, iterable);
            }

            public volatile boolean remove(Object obj, Object obj1)
            {
/*  78*/        return super.remove(obj, obj1);
            }

            public volatile boolean containsEntry(Object obj, Object obj1)
            {
/*  78*/        return super.containsEntry(obj, obj1);
            }

            public volatile boolean containsValue(Object obj)
            {
/*  78*/        return super.containsValue(obj);
            }

            public volatile boolean isEmpty()
            {
/*  78*/        return super.isEmpty();
            }

            private transient Comparator keyComparator;
            private transient Comparator valueComparator;
            private static final long serialVersionUID = 0L;
}
