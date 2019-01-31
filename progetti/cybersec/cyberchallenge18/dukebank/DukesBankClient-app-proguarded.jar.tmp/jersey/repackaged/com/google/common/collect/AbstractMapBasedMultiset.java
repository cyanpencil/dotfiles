// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.primitives.Ints;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMultiset, CollectPreconditions, Count, Maps, 
//            Multiset, Multisets

abstract class AbstractMapBasedMultiset extends AbstractMultiset
    implements Serializable
{
    class MapBasedMultisetIterator
        implements Iterator
    {

                public boolean hasNext()
                {
/* 171*/            return occurrencesLeft > 0 || entryIterator.hasNext();
                }

                public Object next()
                {
/* 176*/            if(occurrencesLeft == 0)
                    {
/* 177*/                currentEntry = (java.util.Map.Entry)entryIterator.next();
/* 178*/                occurrencesLeft = ((Count)currentEntry.getValue()).get();
                    }
/* 180*/            occurrencesLeft--;
/* 181*/            canRemove = true;
/* 182*/            return currentEntry.getKey();
                }

                public void remove()
                {
/* 187*/            CollectPreconditions.checkRemove(canRemove);
                    int i;
/* 188*/            if((i = ((Count)currentEntry.getValue()).get()) <= 0)
/* 190*/                throw new ConcurrentModificationException();
/* 192*/            if(((Count)currentEntry.getValue()).addAndGet(-1) == 0)
/* 193*/                entryIterator.remove();
/* 195*/            size--;
/* 196*/            canRemove = false;
                }

                final Iterator entryIterator;
                java.util.Map.Entry currentEntry;
                int occurrencesLeft;
                boolean canRemove;
                final AbstractMapBasedMultiset this$0;

                MapBasedMultisetIterator()
                {
/* 165*/            this$0 = AbstractMapBasedMultiset.this;
/* 165*/            super();
/* 166*/            entryIterator = backingMap.entrySet().iterator();
                }
    }


            protected AbstractMapBasedMultiset(Map map)
            {
/*  62*/        backingMap = (Map)Preconditions.checkNotNull(map);
/*  63*/        size = super.size();
            }

            public Set entrySet()
            {
/*  82*/        return super.entrySet();
            }

            Iterator entryIterator()
            {
/*  87*/        final Iterator backingEntries = backingMap.entrySet().iterator();
/*  89*/        return new Iterator() {

                    public boolean hasNext()
                    {
/*  94*/                return backingEntries.hasNext();
                    }

                    public Multiset.Entry next()
                    {
/*  99*/                final java.util.Map.Entry mapEntry = (java.util.Map.Entry)backingEntries.next();
/* 100*/                toRemove = mapEntry;
/* 101*/                return new Multisets.AbstractEntry() {

                            public Object getElement()
                            {
/* 104*/                        return mapEntry.getKey();
                            }

                            public int getCount()
                            {
                                Count count1;
                                Count count2;
/* 108*/                        if(((count1 = (Count)mapEntry.getValue()) == null || count1.get() == 0) && (count2 = (Count)backingMap.get(getElement())) != null)
/* 112*/                            return count2.get();
/* 115*/                        if(count1 == null)
/* 115*/                            return 0;
/* 115*/                        else
/* 115*/                            return count1.get();
                            }

                            final java.util.Map.Entry val$mapEntry;
                            final _cls1 this$1;

                            
                            {
/* 101*/                        this$1 = _cls1.this;
/* 101*/                        mapEntry = entry;
/* 101*/                        super();
                            }
                };
                    }

                    public void remove()
                    {
/* 122*/                CollectPreconditions.checkRemove(toRemove != null);
/* 123*/                size-= = (long)((Count)toRemove.getValue()).getAndSet(0);
/* 124*/                backingEntries.remove();
/* 125*/                toRemove = null;
                    }

                    public volatile Object next()
                    {
/*  89*/                return next();
                    }

                    java.util.Map.Entry toRemove;
                    final Iterator val$backingEntries;
                    final AbstractMapBasedMultiset this$0;

                    
                    {
/*  89*/                this$0 = AbstractMapBasedMultiset.this;
/*  89*/                backingEntries = iterator1;
/*  89*/                super();
                    }
        };
            }

            public void clear()
            {
                Count count1;
/* 132*/        for(Iterator iterator1 = backingMap.values().iterator(); iterator1.hasNext(); (count1 = (Count)iterator1.next()).set(0));
/* 135*/        backingMap.clear();
/* 136*/        size = 0L;
            }

            int distinctElements()
            {
/* 141*/        return backingMap.size();
            }

            public int size()
            {
/* 147*/        return Ints.saturatedCast(size);
            }

            public Iterator iterator()
            {
/* 151*/        return new MapBasedMultisetIterator();
            }

            public int count(Object obj)
            {
/* 201*/        if((obj = (Count)Maps.safeGet(backingMap, obj)) == null)
/* 202*/            return 0;
/* 202*/        else
/* 202*/            return ((Count) (obj)).get();
            }

            public int add(Object obj, int i)
            {
/* 215*/        if(i == 0)
/* 216*/            return count(obj);
/* 218*/        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", new Object[] {
/* 218*/            Integer.valueOf(i)
                });
                Count count1;
                int j;
/* 220*/        if((count1 = (Count)backingMap.get(obj)) == null)
                {
/* 223*/            j = 0;
/* 224*/            backingMap.put(obj, new Count(i));
                } else
                {
                    long l;
/* 226*/            Preconditions.checkArgument((l = (long)(j = count1.get()) + (long)i) <= 0x7fffffffL, "too many occurrences: %s", new Object[] {
/* 228*/                Long.valueOf(l)
                    });
/* 230*/            count1.getAndAdd(i);
                }
/* 232*/        size += i;
/* 233*/        return j;
            }

            public int remove(Object obj, int i)
            {
/* 237*/        if(i == 0)
/* 238*/            return count(obj);
/* 240*/        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", new Object[] {
/* 240*/            Integer.valueOf(i)
                });
                Count count1;
/* 242*/        if((count1 = (Count)backingMap.get(obj)) == null)
/* 244*/            return 0;
                int j;
/* 247*/        if((j = count1.get()) > i)
                {
/* 251*/            i = i;
                } else
                {
/* 253*/            i = j;
/* 254*/            backingMap.remove(obj);
                }
/* 257*/        count1.addAndGet(-i);
/* 258*/        size -= i;
/* 259*/        return j;
            }

            public int setCount(Object obj, int i)
            {
/* 264*/        CollectPreconditions.checkNonnegative(i, "count");
                Count count1;
                int j;
/* 268*/        if(i == 0)
                {
/* 269*/            j = getAndSet(count1 = (Count)backingMap.remove(obj), i);
                } else
                {
                    Count count2;
/* 272*/            j = getAndSet(count2 = (Count)backingMap.get(obj), i);
/* 275*/            if(count2 == null)
/* 276*/                backingMap.put(obj, new Count(i));
                }
/* 280*/        size += i - j;
/* 281*/        return j;
            }

            private static int getAndSet(Count count1, int i)
            {
/* 285*/        if(count1 == null)
/* 286*/            return 0;
/* 289*/        else
/* 289*/            return count1.getAndSet(i);
            }

            private transient Map backingMap;
            private transient long size;



}
