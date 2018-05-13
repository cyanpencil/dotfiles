// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, StandardTable, AbstractIterator, AbstractMapEntry, 
//            Sets

class ll extends Map
{
    class Values extends Maps.Values
    {

                public boolean remove(Object obj)
                {
/* 548*/            return obj != null && removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
                }

                public boolean removeAll(Collection collection)
                {
/* 552*/            return removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                }

                public boolean retainAll(Collection collection)
                {
/* 556*/            return removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }

                final StandardTable.Column this$1;

                Values()
                {
/* 543*/            this$1 = StandardTable.Column.this;
/* 544*/            super(StandardTable.Column.this);
                }
    }

    class KeySet extends Maps.KeySet
    {

                public boolean contains(Object obj)
                {
/* 525*/            return StandardTable.this.contains(obj, columnKey);
                }

                public boolean remove(Object obj)
                {
/* 529*/            return StandardTable.this.remove(obj, columnKey) != null;
                }

                public boolean retainAll(Collection collection)
                {
/* 533*/            return removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }

                final StandardTable.Column this$1;

                KeySet()
                {
/* 520*/            this$1 = StandardTable.Column.this;
/* 521*/            super(StandardTable.Column.this);
                }
    }

    class EntrySetIterator extends AbstractIterator
    {

                protected java.util.Map.Entry computeNext()
                {
                    final java.util.Map.Entry entry;
/* 495*/            while(iterator.hasNext()) 
/* 496*/                if(((Map)(entry = (java.util.Map.Entry)iterator.next()).getValue()).containsKey(columnKey))
/* 498*/                    return new AbstractMapEntry() {

                                public Object getKey()
                                {
/* 500*/                            return entry.getKey();
                                }

                                public Object getValue()
                                {
/* 503*/                            return ((Map)entry.getValue()).get(columnKey);
                                }

                                public Object setValue(Object obj)
                                {
/* 506*/                            return ((Map)entry.getValue()).put(columnKey, Preconditions.checkNotNull(obj));
                                }

                                final java.util.Map.Entry val$entry;
                                final EntrySetIterator this$2;

                        
                        {
/* 498*/                    this$2 = EntrySetIterator.this;
/* 498*/                    entry = entry1;
/* 498*/                    super();
                        }
                    };
/* 511*/            return (java.util.Map.Entry)endOfData();
                }

                protected volatile Object computeNext()
                {
/* 491*/            return computeNext();
                }

                final Iterator iterator;
                final StandardTable.Column this$1;

                private EntrySetIterator()
                {
/* 491*/            this$1 = StandardTable.Col