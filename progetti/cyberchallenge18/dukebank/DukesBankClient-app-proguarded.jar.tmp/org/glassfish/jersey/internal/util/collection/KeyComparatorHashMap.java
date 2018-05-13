// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorHashMap.java

package org.glassfish.jersey.internal.util.collection;

import java.io.*;
import java.util.*;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparator

public class KeyComparatorHashMap extends AbstractMap
    implements Serializable, Cloneable, Map
{
    class EntrySet extends AbstractSet
    {

                public Iterator iterator()
                {
/* 928*/            return newEntryIterator();
                }

                public boolean contains(Object obj)
                {
/* 933*/            if(!(obj instanceof java.util.Map.Entry))
/* 934*/                return false;
/* 936*/            obj = (java.util.Map.Entry)obj;
                    KeyComparatorHashMap.Entry entry;
/* 937*/            return (entry = getEntry(((java.util.Map.Entry) (obj)).getKey())) != null && entry.equals(obj);
                }

                public boolean remove(Object obj)
                {
/* 943*/            return removeMapping(obj) != null;
                }

                public int size()
                {
/* 948*/            return KeyComparatorHashMap.this.size;
                }

                public void clear()
                {
/* 953*/            KeyComparatorHashMap.this.clear();
                }

                final KeyComparatorHashMap this$0;

                private EntrySet()
                {
/* 924*/            this$0 = KeyComparatorHashMap.this;
/* 924*/            super();
                }

    }

    class EntryIterator extends HashIterator
    {

                public java.util.Map.Entry next()
                {
/* 886*/            return nextEntry();
                }

                public volatile Object next()
                {
/* 882*/            return next();
                }

                final KeyComparatorHashMap this$0;

                private EntryIterator()
                {
/* 882*/            this$0 = KeyComparatorHashMap.this;
/* 882*/            super();
                }

    }

    class KeyIterator extends HashIterator
    {

                public Object next()
                {
/* 878*/            return nextEntry().getKey();
                }

                final KeyComparatorHashMap this$0;

                private KeyIterator()
                {
/* 874*/            this$0 = KeyComparatorHashMap.this;
/* 874*/            super();
                }

    }

    class ValueIterator extends HashIterator
    {

                public Object next()
                {
/* 870*/            return nextEntry().value;
                }

                final KeyComparatorHashMap this$0;

                private ValueIterator()
                {
/* 866*/            this$0 = KeyComparatorHashMap.this;
/* 866*/            super();
                }

    }

    abstract class HashIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/* 828*/            return next != null;
                }

                KeyComparatorHashMap.Entry nextEntry()
                {
/* 832*/            if(modCount != expectedModCount)
/* 833*/                throw new ConcurrentModificationException();
                    KeyComparatorHashMap.Entry entry;
/* 835*/            if((entry = next) == null)
/* 837*/                throw new NoSuchElementException();
/* 840*/            KeyComparatorHashMap.Entry entry1 = entry.next;
/* 841*/            KeyComparatorHashMap.Entry aentry[] = table;
                    int i;
/* 842*/            for(i = index; entry1 == null && i > 0; entry1 = aentry[--i]);
/* 846*/            index = i;
/* 847*/            next = entry1;
/* 848*/            return current = entry;
                }

                public void remove()
                {
/* 853*/            if(current == null)
/* 854*/                throw new IllegalStateException();
/* 856*/            if(modCount != expectedModCount)
                    {
/* 857*/                throw new ConcurrentModificationException();
                    } else
                    {
/* 859*/                Object obj = current.key;
/* 860*/                current = null;
/* 861*/                removeEntryForKey(obj);
/* 862*/                expectedModCount = modCount;
/* 863*/                return;
                    }
                }

                KeyComparatorHashMap.Entry next;
                int expectedModCount;
                int index;
                KeyComparatorHashMap.Entry current;
                final KeyComparatorHashMap this$0;

                HashIterator()
                {
/* 812*/            this$0 = KeyComparatorHashMap.this;
/* 812*/            super();
/* 813*/            expectedModCount = modCount;
                    KeyComparatorHashMap.Entry aentry[];
/* 814*/            int i = (aentry = table).length;
/* 816*/            KeyComparatorHashMap.Entry entry = null;
/* 817*/            if(size != 0)
/* 819*/                while(i > 0 && (entry = aentry[--i]) == null) ;
/* 822*/            next = entry;
/* 823*/            index = i;
                }
    }

    static class Entry
        implements java.util.Map.Entry
    {

                public Object getKey()
                {
/* 717*/            return KeyComparatorHashMap.unmaskNull(key);
                }

                public Object getValue()
                {
/* 722*/            return value;
                }

                public Object setValue(Object obj)
                {
/* 727*/            Object obj1 = value;
/* 728*/            value = obj;
/* 729*/            return obj1;
                }

                public boolean equals(Object obj)
                {
/* 734*/            if(!(obj instanceof java.util.Map.Entry))
/* 735*/                return false;
/* 737*/            obj = (java.util.Map.Entry)obj;
/* 738*/            Object obj1 = getKey();
/* 739*/            Object obj3 = ((java.util.Map.Entry) (obj)).getKey();
/* 740*/            if(obj1 == obj3 || obj1 != null && obj1.equals(obj3))
                    {
/* 741*/                Object obj2 = getValue();
/* 742*/                obj = ((java.util.Map.Entry) (obj)).getValue();
/* 743*/                if(obj2 == obj || obj2 != null && obj2.equals(obj))
/* 744*/                    return true;
                    }
/* 747*/            return false;
                }

                public int hashCode()
                {
/* 752*/            return (key != KeyComparatorHashMap.NULL_KEY ? key.hashCode() : 0) ^ (value != null ? value.hashCode() : 0);
                }

                public String toString()
                {
/* 757*/            return (new StringBuilder()).append(getKey()).append("=").append(getValue()).toString();
                }

                void recordAccess(KeyComparatorHashMap keycomparatorhashmap)
                {
                }

                void recordRemoval(KeyComparatorHashMap keycomparatorhashmap)
                {
                }

                final Object key;
                Object value;
                final int hash;
                Entry next;

                Entry(int i, Object obj, Object obj1, Entry entry)
                {
/* 709*/            value = obj1;
/* 710*/            next = entry;
/* 711*/            key = obj;
/* 712*/            hash = i;
                }
    }


            public KeyComparatorHashMap(int i, float f, KeyComparator keycomparator)
            {
/* 904*/        entrySet = null;
/* 123*/        if(i < 0)
/* 124*/            throw new IllegalArgumentException(LocalizationMessages.ILLEGAL_INITIAL_CAPACITY(Integer.valueOf(i)));
/* 126*/        if(i > 0x40000000)
/* 127*/            i = 0x40000000;
/* 129*/        if(f <= 0.0F || Float.isNaN(f))
/* 130*/            throw new IllegalArgumentException(LocalizationMessages.ILLEGAL_LOAD_FACTOR(Float.valueOf(f)));
                int j;
/* 134*/        for(j = 1; j < i; j <<= 1);
/* 139*/        loadFactor = f;
/* 140*/        threshold = (int)((float)j * f);
/* 141*/        table = new Entry[j];
/* 142*/        keyComparator = keycomparator;
/* 144*/        init();
            }

            public KeyComparatorHashMap(int i, KeyComparator keycomparator)
            {
/* 157*/        this(i, 0.75F, keycomparator);
            }

            public KeyComparatorHashMap(KeyComparator keycomparator)
            {
/* 167*/        this(16, 0.75F, keycomparator);
            }

            public KeyComparatorHashMap(Map map, KeyComparator keycomparator)
            {
/* 181*/        this(Math.max((int)((float)map.size() / 0.75F) + 1, 16), 0.75F, keycomparator);
/* 182*/        putAllForCreate(map);
            }

            public int getModCount()
            {
/* 194*/        return modCount;
            }

            void init()
            {
            }

            static Object maskNull(Object obj)
            {
/* 219*/        if(obj == null)
/* 219*/            return NULL_KEY;
/* 219*/        else
/* 219*/            return obj;
            }

            static boolean isNull(Object obj)
            {
/* 223*/        return obj == NULL_KEY;
            }

            static Object unmaskNull(Object obj)
            {
/* 230*/        if(obj == NULL_KEY)
/* 230*/            return null;
/* 230*/        else
/* 230*/            return obj;
            }

            static int hash(Object obj)
            {
/* 244*/        return ((int) (obj = (obj = (obj = (obj = (obj = obj.hashCode()) + ~(obj << 9)) ^ obj >>> 14) + (obj << 4)) ^ obj >>> 10));
            }

            static boolean eq(Object obj, Object obj1)
            {
/* 257*/        return obj == obj1 || obj.equals(obj1);
            }

            static int indexFor(int i, int j)
            {
/* 264*/        return i & j - 1;
            }

            public int size()
            {
/* 274*/        return size;
            }

            public boolean isEmpty()
            {
/* 284*/        return size == 0;
            }

            int keyComparatorHash(Object obj)
            {
/* 288*/        if(isNull(obj))
/* 288*/            return hash(obj.hashCode());
/* 288*/        else
/* 288*/            return hash(keyComparator.hash(obj));
            }

            int hash(int i)
            {
/* 292*/        return i = (i = (i = (i += ~(i << 9)) ^ i >>> 14) + (i << 4)) ^ i >>> 10;
            }

            boolean keyComparatorEq(Object obj, Object obj1)
            {
/* 303*/        if(isNull(obj))
/* 304*/            return obj == obj1;
/* 305*/        if(isNull(obj1))
/* 306*/            return obj == obj1;
/* 308*/        return obj == obj1 || keyComparator.equals(obj, obj1);
            }

            public Object get(Object obj)
            {
/* 327*/        obj = maskNull(obj);
                int i;
/* 328*/        int j = indexFor(i = keyComparatorHash(obj), table.length);
/* 330*/        Entry entry = table[j];
/* 332*/        do
                {
/* 332*/            if(entry == null)
/* 333*/                return null;
/* 335*/            if(entry.hash == i && keyComparatorEq(obj, entry.key))
/* 336*/                return entry.value;
/* 338*/            entry = entry.next;
                } while(true);
            }

            public boolean containsKey(Object obj)
            {
/* 352*/        obj = maskNull(obj);
                int i;
/* 353*/        int j = indexFor(i = keyComparatorHash(obj), table.length);
/* 355*/        for(Entry entry = table[j]; entry != null; entry = entry.next)
/* 357*/            if(entry.hash == i && keyComparatorEq(obj, entry.key))
/* 358*/                return true;

/* 362*/        return false;
            }

            Entry getEntry(Object obj)
            {
/* 371*/        obj = maskNull(obj);
                int i;
/* 372*/        int j = indexFor(i = keyComparatorHash(obj), table.length);
                Entry entry;
/* 374*/        for(entry = table[j]; entry != null && (entry.hash != i || !keyComparatorEq(obj, entry.key)); entry = entry.next);
/* 378*/        return entry;
            }

            public Object put(Object obj, Object obj1)
            {
/* 395*/        obj = maskNull(obj);
                int i;
/* 396*/        int j = indexFor(i = keyComparatorHash(obj), table.length);
/* 399*/        for(Entry entry = table[j]; entry != null; entry = entry.next)
/* 400*/            if(entry.hash == i && keyComparatorEq(obj, entry.key))
                    {
/* 401*/                obj = entry.value;
/* 402*/                entry.value = obj1;
/* 403*/                entry.recordAccess(this);
/* 404*/                return obj;
                    }

/* 408*/        modCount++;
/* 409*/        addEntry(i, obj, obj1, j);
/* 410*/        return null;
            }

            private void putForCreate(Object obj, Object obj1)
            {
/* 420*/        obj = maskNull(obj);
                int i;
/* 421*/        int j = indexFor(i = keyComparatorHash(obj), table.length);
/* 429*/        for(Entry entry = table[j]; entry != null; entry = entry.next)
/* 430*/            if(entry.hash == i && keyComparatorEq(obj, entry.key))
                    {
/* 431*/                entry.value = obj1;
/* 432*/                return;
                    }

/* 436*/        createEntry(i, obj, obj1, j);
            }

            private void putAllForCreate(Map map)
            {
                java.util.Map.Entry entry;
/* 440*/        for(map = map.entrySet().iterator(); map.hasNext(); putForCreate(entry.getKey(), entry.getValue()))
/* 440*/            entry = (java.util.Map.Entry)map.next();

            }

            void resize(int i)
            {
                Entry aentry[];
                int j;
/* 460*/        if((j = (aentry = table).length) == 0x40000000)
                {
/* 463*/            threshold = 0x7fffffff;
/* 464*/            return;
                } else
                {
/* 467*/            Entry aentry1[] = new Entry[i];
/* 468*/            transfer(aentry1);
/* 469*/            table = aentry1;
/* 470*/            threshold = (int)((float)i * loadFactor);
/* 471*/            return;
                }
            }

            void transfer(Entry aentry[])
            {
/* 477*/        Entry aentry1[] = table;
/* 478*/        int i = aentry.length;
/* 479*/        for(int j = 0; j < aentry1.length; j++)
                {
                    Entry entry;
/* 480*/            if((entry = aentry1[j]) == null)
/* 482*/                continue;
/* 482*/            aentry1[j] = null;
                    Entry entry1;
/* 484*/            do
                    {
/* 484*/                entry1 = entry.next;
/* 485*/                int k = indexFor(entry.hash, i);
/* 486*/                entry.next = aentry[k];
/* 487*/                aentry[k] = entry;
                    } while((entry = entry1) != null);
                }

            }

            public void putAll(Map map)
            {
                int i;
/* 504*/        if((i = map.size()) == 0)
/* 506*/            return;
/* 518*/        if(i > threshold)
                {
/* 519*/            if((i = (int)((float)i / loadFactor + 1.0F)) > 0x40000000)
/* 521*/                i = 0x40000000;
                    int j;
/* 523*/            for(j = table.length; j < i; j <<= 1);
/* 527*/            if(j > table.length)
/* 528*/                resize(j);
                }
                java.util.Map.Entry entry;
/* 532*/        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); put(entry.getKey(), entry.getValue()))
/* 532*/            entry = (java.util.Map.Entry)iterator.next();

            }

            public Object remove(Object obj)
            {
/* 548*/        if((obj = removeEntryForKey(obj)) == null)
/* 549*/            return null;
/* 549*/        else
/* 549*/            return ((Entry) (obj)).value;
            }

            Entry removeEntryForKey(Object obj)
            {
/* 558*/        obj = maskNull(obj);
                int i;
/* 559*/        int j = indexFor(i = keyComparatorHash(obj), table.length);
                Entry entry;
                Entry entry1;
                Entry entry2;
/* 561*/        for(entry1 = entry = table[j]; entry1 != null; entry1 = entry2)
                {
/* 565*/            entry2 = entry1.next;
/* 566*/            if(entry1.hash == i && keyComparatorEq(obj, entry1.key))
                    {
/* 567*/                modCount++;
/* 568*/                size--;
/* 569*/                if(entry == entry1)
/* 570*/                    table[j] = entry2;
/* 572*/                else
/* 572*/                    entry.next = entry2;
/* 574*/                entry1.recordRemoval(this);
/* 575*/                return entry1;
                    }
/* 577*/            entry = entry1;
                }

/* 581*/        return entry1;
            }

            Entry removeMapping(Object obj)
            {
/* 588*/        if(!(obj instanceof java.util.Map.Entry))
/* 589*/            return null;
/* 592*/        Object obj1 = maskNull(((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey());
                int i;
/* 594*/        int j = indexFor(i = keyComparatorHash(obj1), table.length);
                Entry entry;
                Entry entry1;
                Entry entry2;
/* 596*/        for(entry1 = entry = table[j]; entry1 != null; entry1 = entry2)
                {
/* 600*/            entry2 = entry1.next;
/* 601*/            if(entry1.hash == i && entry1.equals(obj))
                    {
/* 602*/                modCount++;
/* 603*/                size--;
/* 604*/                if(entry == entry1)
/* 605*/                    table[j] = entry2;
/* 607*/                else
/* 607*/                    entry.next = entry2;
/* 609*/                entry1.recordRemoval(this);
/* 610*/                return entry1;
                    }
/* 612*/            entry = entry1;
                }

/* 616*/        return entry1;
            }

            public void clear()
            {
/* 624*/        modCount++;
/* 625*/        Entry aentry[] = table;
/* 626*/        for(int i = 0; i < aentry.length; i++)
/* 627*/            aentry[i] = null;

/* 629*/        size = 0;
            }

            public boolean containsValue(Object obj)
            {
/* 642*/        if(obj == null)
/* 643*/            return containsNullValue();
/* 646*/        Entry aentry[] = table;
/* 647*/        for(int i = 0; i < aentry.length; i++)
                {
/* 648*/            for(Entry entry = aentry[i]; entry != null; entry = entry.next)
/* 649*/                if(obj.equals(entry.value))
/* 650*/                    return true;

                }

/* 654*/        return false;
            }

            private boolean containsNullValue()
            {
                Entry aentry[];
/* 661*/        int i = (aentry = aentry = table).length;
/* 662*/        for(int j = 0; j < i; j++)
                {
                    Entry entry;
/* 662*/            for(entry = entry = aentry[j]; entry != null; entry = entry.next)
/* 664*/                if(entry.value == null)
/* 665*/                    return true;

                }

/* 669*/        return false;
            }

            public Object clone()
            {
/* 680*/        KeyComparatorHashMap keycomparatorhashmap = null;
/* 683*/        try
                {
/* 683*/            (keycomparatorhashmap = (KeyComparatorHashMap)super.clone()).table = new Entry[table.length];
/* 686*/            keycomparatorhashmap.entrySet = null;
/* 687*/            keycomparatorhashmap.modCount = 0;
/* 688*/            keycomparatorhashmap.size = 0;
/* 689*/            keycomparatorhashmap.init();
/* 690*/            keycomparatorhashmap.putAllForCreate(this);
                }
/* 691*/        catch(CloneNotSupportedException _ex) { }
/* 695*/        return keycomparatorhashmap;
            }

            void addEntry(int i, Object obj, Object obj1, int j)
            {
/* 784*/        Entry entry = table[j];
/* 785*/        table[j] = new Entry(i, obj, obj1, entry);
/* 786*/        if(size++ >= threshold)
/* 787*/            resize(2 * table.length);
            }

            void createEntry(int i, Object obj, Object obj1, int j)
            {
/* 800*/        Entry entry = table[j];
/* 801*/        table[j] = new Entry(i, obj, obj1, entry);
/* 802*/        size++;
            }

            Iterator newKeyIterator()
            {
/* 892*/        return new KeyIterator();
            }

            Iterator newValueIterator()
            {
/* 896*/        return new ValueIterator();
            }

            Iterator newEntryIterator()
            {
/* 900*/        return new EntryIterator();
            }

            public Set entrySet()
            {
                Set set;
/* 920*/        if((set = entrySet) != null)
/* 921*/            return set;
/* 921*/        else
/* 921*/            return entrySet = new EntrySet();
            }

            private void writeObject(ObjectOutputStream objectoutputstream)
                throws IOException
            {
/* 970*/        Iterator iterator = entrySet().iterator();
/* 973*/        objectoutputstream.defaultWriteObject();
/* 976*/        objectoutputstream.writeInt(table.length);
/* 979*/        objectoutputstream.writeInt(size);
                java.util.Map.Entry entry;
/* 982*/        for(; iterator.hasNext(); objectoutputstream.writeObject(entry.getValue()))
                {
/* 983*/            entry = (java.util.Map.Entry)iterator.next();
/* 984*/            objectoutputstream.writeObject(entry.getKey());
                }

            }

            private void readObject(ObjectInputStream objectinputstream)
                throws IOException, ClassNotFoundException
            {
/* 995*/        objectinputstream.defaultReadObject();
/* 998*/        int i = objectinputstream.readInt();
/* 999*/        table = new Entry[i];
/*1001*/        init();
/*1004*/        i = objectinputstream.readInt();
/*1007*/        for(int j = 0; j < i; j++)
                {
/*1008*/            Object obj = objectinputstream.readObject();
/*1009*/            Object obj1 = objectinputstream.readObject();
/*1010*/            putForCreate(obj, obj1);
                }

            }

            int capacity()
            {
/*1016*/        return table.length;
            }

            float loadFactor()
            {
/*1020*/        return loadFactor;
            }

            private static final long serialVersionUID = 0x29a31d00b6edfb37L;
            static final int DEFAULT_INITIAL_CAPACITY = 16;
            static final int MAXIMUM_CAPACITY = 0x40000000;
            static final float DEFAULT_LOAD_FACTOR = 0.75F;
            transient Entry table[];
            transient int size;
            int threshold;
            final float loadFactor;
            volatile transient int modCount;
            final KeyComparator keyComparator;
            static final Object NULL_KEY = new Object();
            private transient Set entrySet;

}
