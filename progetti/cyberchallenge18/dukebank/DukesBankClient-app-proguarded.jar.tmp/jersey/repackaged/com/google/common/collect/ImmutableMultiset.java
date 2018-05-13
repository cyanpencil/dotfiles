// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMultiset.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, ImmutableMap, ImmutableSet, Multiset, 
//            Multisets, RegularImmutableMultiset, Sets, UnmodifiableIterator, 
//            ImmutableList, ImmutableAsList

public abstract class ImmutableMultiset extends ImmutableCollection
    implements Multiset
{
    static class SerializedForm
        implements Serializable
    {

                final Object elements[];
                final int counts[];

                SerializedForm(Multiset multiset)
                {
/* 432*/            int i = multiset.entrySet().size();
/* 433*/            elements = new Object[i];
/* 434*/            counts = new int[i];
/* 435*/            i = 0;
/* 436*/            for(multiset = multiset.entrySet().iterator(); multiset.hasNext();)
                    {
/* 436*/                Multiset.Entry entry = (Multiset.Entry)multiset.next();
/* 437*/                elements[i] = entry.getElement();
/* 438*/                counts[i] = entry.getCount();
/* 439*/                i++;
                    }

                }
    }

    static class EntrySetSerializedForm
        implements Serializable
    {

                final ImmutableMultiset multiset;

                EntrySetSerializedForm(ImmutableMultiset immutablemultiset)
                {
/* 419*/            multiset = immutablemultiset;
                }
    }

    final class EntrySet extends ImmutableSet
    {

                final boolean isPartialView()
                {
/* 359*/            return ImmutableMultiset.this.isPartialView();
                }

                public final UnmodifiableIterator iterator()
                {
/* 364*/            return asList().iterator();
                }

                final ImmutableList createAsList()
                {
/* 369*/            return new ImmutableAsList() {

                        public Multiset.Entry get(int i)
                        {
/* 372*/                    return getEntry(i);
                        }

                        ImmutableCollection delegateCollection()
                        {
/* 377*/                    return EntrySet.this;
                        }

                        public volatile Object get(int i)
                        {
/* 369*/                    return get(i);
                        }

                        final EntrySet this$1;

                        
                        {
/* 369*/                    this$1 = EntrySet.this;
/* 369*/                    super();
                        }
            };
                }

                public final int size()
                {
/* 384*/            return elementSet().size();
                }

                public final boolean contains(Object obj)
                {
/* 389*/            if(obj instanceof Multiset.Entry)
                    {
/* 390*/                if(((Multiset.Entry) (obj = (Multiset.Entry)obj)).getCount() <= 0)
/* 392*/                    return false;
                        int i;
/* 394*/                return (i = count(((Multiset.Entry) (obj)).getElement())) == ((Multiset.Entry) (obj)).getCount();
                    } else
                    {
/* 397*/                return false;
                    }
                }

                public final int hashCode()
                {
/* 402*/            return ImmutableMultiset.this.hashCode();
                }

                final Object writeReplace()
                {
/* 409*/            return new EntrySetSerializedForm(ImmutableMultiset.this);
                }

                public final volatile Iterator iterator()
                {
/* 356*/            return iterator();
                }

                final ImmutableMultiset this$0;

                private EntrySet()
                {
/* 356*/            this$0 = ImmutableMultiset.this;
/* 356*/            super();
                }

    }


            ImmutableMultiset()
            {
            }

            public UnmodifiableIterator iterator()
            {
/* 239*/        final UnmodifiableIterator entryIterator = entrySet().iterator();
/* 240*/        return new UnmodifiableIterator() {

                    public boolean hasNext()
                    {
/* 246*/                return remaining > 0 || entryIterator.hasNext();
                    }

                    public Object next()
                    {
/* 251*/                if(remaining <= 0)
                        {
/* 252*/                    Multiset.Entry entry = (Multiset.Entry)entryIterator.next();
/* 253*/                    element = entry.getElement();
/* 254*/                    remaining = entry.getCount();
                        }
/* 256*/                remaining--;
/* 257*/                return element;
                    }

                    int remaining;
                    Object element;
                    final Iterator val$entryIterator;
                    final ImmutableMultiset this$0;

                    
                    {
/* 240*/                this$0 = ImmutableMultiset.this;
/* 240*/                entryIterator = iterator1;
/* 240*/                super();
                    }
        };
            }

            public boolean contains(Object obj)
            {
/* 264*/        return count(obj) > 0;
            }

            public boolean containsAll(Collection collection)
            {
/* 269*/        return elementSet().containsAll(collection);
            }

            /**
             * @deprecated Method add is deprecated
             */

            public final int add(Object obj, int i)
            {
/* 281*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method remove is deprecated
             */

            public final int remove(Object obj, int i)
            {
/* 293*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method setCount is deprecated
             */

            public final int setCount(Object obj, int i)
            {
/* 305*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method setCount is deprecated
             */

            public final boolean setCount(Object obj, int i, int j)
            {
/* 317*/        throw new UnsupportedOperationException();
            }

            int copyIntoArray(Object aobj[], int i)
            {
/* 323*/        for(Iterator iterator1 = entrySet().iterator(); iterator1.hasNext();)
                {
/* 323*/            Multiset.Entry entry = (Multiset.Entry)iterator1.next();
/* 324*/            Arrays.fill(aobj, i, i + entry.getCount(), entry.getElement());
/* 325*/            i += entry.getCount();
                }

/* 327*/        return i;
            }

            public boolean equals(Object obj)
            {
/* 331*/        return Multisets.equalsImpl(this, obj);
            }

            public int hashCode()
            {
/* 335*/        return Sets.hashCodeImpl(entrySet());
            }

            public String toString()
            {
/* 339*/        return entrySet().toString();
            }

            public ImmutableSet entrySet()
            {
                ImmutableSet immutableset;
/* 346*/        if((immutableset = entrySet) == null)
/* 347*/            return entrySet = createEntrySet();
/* 347*/        else
/* 347*/            return immutableset;
            }

            private final ImmutableSet createEntrySet()
            {
/* 351*/        if(isEmpty())
/* 351*/            return ImmutableSet.of();
/* 351*/        else
/* 351*/            return new EntrySet();
            }

            abstract Multiset.Entry getEntry(int i);

            Object writeReplace()
            {
/* 458*/        return new SerializedForm(this);
            }

            public volatile Iterator iterator()
            {
/*  49*/        return iterator();
            }

            public volatile Set entrySet()
            {
/*  49*/        return entrySet();
            }

            private static final ImmutableMultiset EMPTY = new RegularImmutableMultiset(ImmutableMap.of(), 0);
            private transient ImmutableSet entrySet;

}
