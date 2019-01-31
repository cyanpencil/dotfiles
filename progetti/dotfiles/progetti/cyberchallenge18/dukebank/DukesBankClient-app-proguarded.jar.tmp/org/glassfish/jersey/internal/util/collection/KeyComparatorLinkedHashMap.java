// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorLinkedHashMap.java

package org.glassfish.jersey.internal.util.collection;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorHashMap, KeyComparator

public class KeyComparatorLinkedHashMap extends KeyComparatorHashMap
    implements Map
{
    class EntryIterator extends LinkedHashIterator
    {

                public java.util.Map.Entry next()
                {
/* 363*/            return nextEntry();
                }

                public volatile Object next()
                {
/* 359*/            return next();
                }

                final KeyComparatorLinkedHashMap this$0;

                private EntryIterator()
                {
/* 359*/            this$0 = KeyComparatorLinkedHashMap.this;
/* 359*/            super();
                }

    }

    class ValueIterator extends LinkedHashIterator
    {

                public Object next()
                {
/* 355*/            return nextEntry().value;
                }

                final KeyComparatorLinkedHashMap this$0;

                private ValueIterator()
                {
/* 351*/            this$0 = KeyComparatorLinkedHashMap.this;
/* 351*/            super();
                }

    }

    class KeyIterator extends LinkedHashIterator
    {

                public Object next()
                {
/* 347*/            return nextEntry().getKey();
                }

                final KeyComparatorLinkedHashMap this$0;

                private KeyIterator()
                {
/* 343*/            this$0 = KeyComparatorLinkedHashMap.this;
/* 343*/            super();
                }

    }

    abstract class LinkedHashIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/* 312*/            return nextEntry != header;
                }

                public void remove()
                {
/* 317*/            if(lastReturned == null)
/* 318*/                throw new IllegalStateException();
/* 320*/            if(modCount != expectedModCount)
                    {
/* 321*/                throw new ConcurrentModificationException();
                    } else
                    {
/* 324*/                KeyComparatorLinkedHashMap.this.remove(lastReturned.key);
/* 325*/                lastReturned = null;
/* 326*/                expectedModCount = modCount;
/* 327*/                return;
                    }
                }

                KeyComparatorLinkedHashMap.Entry nextEntry()
                {
/* 330*/            if(modCount != expectedModCount)
/* 331*/                throw new ConcurrentModificationException();
/* 333*/            if(nextEntry == header)
                    {
/* 334*/                throw new NoSuchElementException();
                    } else
                    {
/* 337*/                KeyComparatorLinkedHashMap.Entry entry = lastReturned = nextEntry;
/* 338*/                nextEntry = entry.after;
/* 339*/                return entry;
                    }
                }

                KeyComparatorLinkedHashMap.Entry nextEntry;
                KeyComparatorLinkedHashMap.Entry lastReturned;
                int expectedModCount;
                final KeyComparatorLinkedHashMap this$0;

                private LinkedHashIterator()
                {
/* 299*/            this$0 = KeyComparatorLinkedHashMap.this;
/* 299*/            super();
/* 301*/            nextEntry = header.after;
/* 302*/            lastReturned = null;
/* 308*/            expectedModCount = modCount;
                }

    }

    static class Entry extends KeyComparatorHashMap.Entry
    {

                private void remove()
                {
/* 253*/            before.after = after;
/* 254*/            after.before = before;
                }

                private void addBefore(Entry entry)
                {
/* 261*/            after = entry;
/* 262*/            before = entry.before;
/* 263*/            before.after = this;
/* 264*/            after.before = this;
                }

                void recordAccess(KeyComparatorHashMap keycomparatorhashmap)
                {
/* 275*/            if(((KeyComparatorLinkedHashMap) (keycomparatorhashmap = (KeyComparatorLinkedHashMap)keycomparatorhashmap)).accessOrder)
                    {
/* 277*/                keycomparatorhashmap.modCount++;
/* 278*/                remove();
/* 279*/                addBefore(((KeyComparatorLinkedHashMap) (keycomparatorhashmap)).header);
                    }
                }

                void recordRemoval(KeyComparatorHashMap keycomparatorhashmap)
                {
/* 285*/            remove();
                }

                public boolean equals(Object obj)
                {
/* 290*/            return super.equals(obj);
                }

                public int hashCode()
                {
/* 295*/            return super.hashCode();
                }

                Entry before;
                Entry after;


                Entry(int i, Object obj, Object obj1, KeyComparatorHashMap.Entry entry)
                {
/* 246*/            super(i, obj, obj1, entry);
                }
    }


            public KeyComparatorLinkedHashMap(int i, float f, KeyComparator keycomparator)
            {
/*  84*/        super(i, f, keycomparator);
/*  85*/        accessOrder = false;
            }

            public KeyComparatorLinkedHashMap(int i, KeyComparator keycomparator)
            {
/*  98*/        super(i, keycomparator);
/*  99*/        accessOrder = false;
            }

            public KeyComparatorLinkedHashMap(KeyComparator keycomparator)
            {
/* 108*/        super(keycomparator);
/* 109*/        accessOrder = false;
            }

            public KeyComparatorLinkedHashMap(Map map, KeyComparator keycomparator)
            {
/* 124*/        super(map, keycomparator);
/* 125*/        accessOrder = false;
            }

            public KeyComparatorLinkedHashMap(int i, float f, boolean flag, KeyComparator keycomparator)
            {
/* 143*/        super(i, f, keycomparator);
/* 144*/        accessOrder = flag;
            }

            void init()
            {
/* 154*/        header = new Entry(-1, null, null, null);
/* 155*/        header.before = header.after = header;
            }

            void transfer(KeyComparatorHashMap.Entry aentry[])
            {
/* 166*/        int i = aentry.length;
/* 167*/        for(Entry entry = header.after; entry != header; entry = entry.after)
                {
/* 168*/            int j = indexFor(entry.hash, i);
/* 169*/            entry.next = aentry[j];
/* 170*/            aentry[j] = entry;
                }

            }

            public boolean containsValue(Object obj)
            {
/* 185*/        if(obj == null)
                {
/* 186*/            for(Entry entry = header.after; entry != header; entry = entry.after)
/* 187*/                if(entry.value == null)
/* 188*/                    return true;

                } else
                {
/* 192*/            for(Entry entry1 = header.after; entry1 != header; entry1 = entry1.after)
/* 193*/                if(obj.equals(entry1.value))
/* 194*/                    return true;

                }
/* 198*/        return false;
            }

            public Object get(Object obj)
            {
/* 219*/        if((obj = (Entry)getEntry(obj)) == null)
                {
/* 221*/            return null;
                } else
                {
/* 223*/            ((Entry) (obj)).recordAccess(this);
/* 224*/            return ((Entry) (obj)).value;
                }
            }

            public void clear()
            {
/* 233*/        super.clear();
/* 234*/        header.before = header.after = header;
            }

            Iterator newKeyIterator()
            {
/* 370*/        return new KeyIterator();
            }

            Iterator newValueIterator()
            {
/* 375*/        return new ValueIterator();
            }

            Iterator newEntryIterator()
            {
/* 380*/        return new EntryIterator();
            }

            void addEntry(int i, Object obj, Object obj1, int j)
            {
/* 390*/        createEntry(i, obj, obj1, j);
/* 393*/        i = header.after;
/* 394*/        if(removeEldestEntry(i))
                {
/* 395*/            removeEntryForKey(((Entry) (i)).key);
/* 395*/            return;
                }
/* 397*/        if(size >= threshold)
/* 398*/            resize(2 * table.length);
            }

            void createEntry(int i, Object obj, Object obj1, int j)
            {
/* 409*/        KeyComparatorHashMap.Entry entry = table[j];
/* 410*/        i = new Entry(i, obj, obj1, entry);
/* 411*/        table[j] = i;
/* 412*/        i.addBefore(header);
/* 413*/        size++;
            }

            protected boolean removeEldestEntry(java.util.Map.Entry entry)
            {
/* 458*/        return false;
            }

            private static final long serialVersionUID = 0x34c04e5c106cc0fbL;
            private transient Entry header;
            private final boolean accessOrder;


}
