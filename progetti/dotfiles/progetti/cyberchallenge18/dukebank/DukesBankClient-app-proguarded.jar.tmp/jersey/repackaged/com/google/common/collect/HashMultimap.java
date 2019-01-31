// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.io.*;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractSetMultimap, Maps, Multimap, Serialization, 
//            Sets, Multiset

public final class HashMultimap extends AbstractSetMultimap
{

            public static HashMultimap create()
            {
/*  61*/        return new HashMultimap();
            }

            public static HashMultimap create(int i, int j)
            {
/*  75*/        return new HashMultimap(i, j);
            }

            public static HashMultimap create(Multimap multimap)
            {
/*  87*/        return new HashMultimap(multimap);
            }

            private HashMultimap()
            {
/*  91*/        super(new HashMap());
/*  53*/        expectedValuesPerKey = 2;
            }

            private HashMultimap(int i, int j)
            {
/*  95*/        super(Maps.newHashMapWithExpectedSize(i));
/*  53*/        expectedValuesPerKey = 2;
/*  96*/        Preconditions.checkArgument(j >= 0);
/*  97*/        expectedValuesPerKey = j;
            }

            private HashMultimap(Multimap multimap)
            {
/* 101*/        super(Maps.newHashMapWithExpectedSize(multimap.keySet().size()));
/*  53*/        expectedValuesPerKey = 2;
/* 103*/        putAll(multimap);
            }

            final Set createCollection()
            {
/* 114*/        return Sets.newHashSetWithExpectedSize(expectedValuesPerKey);
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/* 124*/        objectoutputstream.defaultWriteObject();
/* 125*/        objectoutputstream.writeInt(expectedValuesPerKey);
/* 126*/        Serialization.writeMultimap(this, objectoutputstream);
            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/* 132*/        objectinputstream.defaultReadObject();
/* 133*/        expectedValuesPerKey = objectinputstream.readInt();
                int i;
/* 134*/        HashMap hashmap = Maps.newHashMapWithExpectedSize(i = Serialization.readCount(objectinputstream));
/* 136*/        setMap(hashmap);
/* 137*/        Serialization.populateMultimap(this, objectinputstream, i);
            }

            public final volatile boolean equals(Object obj)
            {
/*  49*/        return super.equals(obj);
            }

            public final volatile boolean put(Object obj, Object obj1)
            {
/*  49*/        return super.put(obj, obj1);
            }

            public final volatile Map asMap()
            {
/*  49*/        return super.asMap();
            }

            public final volatile Set replaceValues(Object obj, Iterable iterable)
            {
/*  49*/        return super.replaceValues(obj, iterable);
            }

            public final volatile Set removeAll(Object obj)
            {
/*  49*/        return super.removeAll(obj);
            }

            public final volatile Set entries()
            {
/*  49*/        return super.entries();
            }

            public final volatile Set get(Object obj)
            {
/*  49*/        return super.get(obj);
            }

            public final volatile Collection values()
            {
/*  49*/        return super.values();
            }

            public final volatile void clear()
            {
/*  49*/        super.clear();
            }

            public final volatile boolean containsKey(Object obj)
            {
/*  49*/        return super.containsKey(obj);
            }

            public final volatile int size()
            {
/*  49*/        return super.size();
            }

            final volatile Collection createCollection()
            {
/*  49*/        return createCollection();
            }

            public final volatile String toString()
            {
/*  49*/        return super.toString();
            }

            public final volatile int hashCode()
            {
/*  49*/        return super.hashCode();
            }

            public final volatile Multiset keys()
            {
/*  49*/        return super.keys();
            }

            public final volatile Set keySet()
            {
/*  49*/        return super.keySet();
            }

            public final volatile boolean putAll(Multimap multimap)
            {
/*  49*/        return super.putAll(multimap);
            }

            public final volatile boolean putAll(Object obj, Iterable iterable)
            {
/*  49*/        return super.putAll(obj, iterable);
            }

            public final volatile boolean remove(Object obj, Object obj1)
            {
/*  49*/        return super.remove(obj, obj1);
            }

            public final volatile boolean containsEntry(Object obj, Object obj1)
            {
/*  49*/        return super.containsEntry(obj, obj1);
            }

            public final volatile boolean containsValue(Object obj)
            {
/*  49*/        return super.containsValue(obj);
            }

            public final volatile boolean isEmpty()
            {
/*  49*/        return super.isEmpty();
            }

            private static final int DEFAULT_VALUES_PER_KEY = 2;
            transient int expectedValuesPerKey;
            private static final long serialVersionUID = 0L;
}
