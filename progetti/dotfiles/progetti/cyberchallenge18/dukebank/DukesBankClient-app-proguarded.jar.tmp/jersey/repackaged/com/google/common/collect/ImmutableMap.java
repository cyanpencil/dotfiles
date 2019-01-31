// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMap.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, ImmutableBiMap, ImmutableCollection, ImmutableEnumMap, 
//            ImmutableMapEntry, ImmutableMapKeySet, ImmutableMapValues, ImmutableSet, 
//            ImmutableSetMultimap, ImmutableSortedMap, Maps, RegularImmutableMap, 
//            ObjectArrays, ImmutableMapEntrySet, UnmodifiableIterator, AbstractMapEntry

public abstract class ImmutableMap
    implements Serializable, Map
{
    static class SerializedForm
        implements Serializable
    {

                private final Object keys[];
                private final Object values[];

                SerializedForm(ImmutableMap immutablemap)
                {
/* 531*/            keys = new Object[immutablemap.size()];
/* 532*/            values = new Object[immutablemap.size()];
/* 533*/            int i = 0;
/* 534*/            for(immutablemap = immutablemap.entrySet().iterator(); immutablemap.hasNext();)
                    {
/* 534*/                java.util.Map.Entry entry = (java.util.Map.Entry)immutablemap.next();
/* 535*/                keys[i] = entry.getKey();
/* 536*/                values[i] = entry.getValue();
/* 537*/                i++;
                    }

                }
    }

    static final class MapViewOfValuesAsSingletonSets extends ImmutableMap
    {

                public final int size()
                {
/* 458*/            return _flddelegate.size();
                }

                public final boolean containsKey(Object obj)
                {
/* 462*/            return _flddelegate.containsKey(obj);
                }

                public final ImmutableSet get(Object obj)
                {
/* 466*/            if((obj = _flddelegate.get(obj)) == null)
/* 467*/                return null;
/* 467*/            else
/* 467*/                return ImmutableSet.of(obj);
                }

                final boolean isPartialView()
                {
/* 471*/            return false;
                }

                final ImmutableSet createEntrySet()
                {
/* 475*/            return new ImmutableMapEntrySet() {

                        ImmutableMap map()
                        {
/* 477*/                    return MapViewOfValuesAsSingletonSets.this;
                        }

                        public UnmodifiableIterator iterator()
                        {
/* 482*/                    final UnmodifiableIterator backingIterator = _flddelegate.entrySet().iterator();
/* 483*/                    return new UnmodifiableIterator() {

                                public boolean hasNext()
                                {
/* 485*/                            return backingIterator.hasNext();
                                }

                                public java.util.Map.Entry next()
                                {
/* 489*/                            final java.util.Map.Entry backingEntry = (java.util.Map.Entry)backingIterator.next();
/* 490*/                            return new AbstractMapEntry() {

                                        public Object getKey()
                                        {
/* 492*/                                    return backingEntry.getKey();
                                        }

                                        public ImmutableSet getValue()
                                        {
/* 496*/                                    return ImmutableSet.of(backingEntry.getValue());
                                        }

                                        public volatile Object getValue()
                                        {
/* 490*/                                    return getValue();
                                        }

                                        final java.util.Map.Entry val$backingEntry;
                                        final _cls1 this$2;

                                    
                                    {
/* 490*/                                this$2 = _cls1.this;
/* 490*/                                backingEntry = entry;
/* 490*/                                super();
                                    }
                            };
                                }

                                public volatile Object next()
                                {
/* 483*/                            return next();
                                }

                                final Iterator val$backingIterator;
                                final _cls1 this$1;

                                
                                {
/* 483*/                            this$1 = _cls1.this;
/* 483*/                            backingIterator = iterator1;
/* 483*/                            super();
                                }
                    };
                        }

                        public volatile Iterator iterator()
                        {
/* 475*/                    return iterator();
                        }

                        final MapViewOfValuesAsSingletonSets this$0;

                        
                        {
/* 475*/                    this$0 = MapViewOfValuesAsSingletonSets.this;
/* 475*/                    super();
                        }
            };
                }

                public final volatile Object get(Object obj)
                {
/* 449*/            return get(obj);
                }

                public final volatile Set entrySet()
                {
/* 449*/            return entrySet();
                }

                public final volatile Collection values()
                {
/* 449*/            return values();
                }

                public final volatile Set keySet()
                {
/* 449*/            return keySet();
                }

                private final ImmutableMap _flddelegate;


                MapViewOfValuesAsSingletonSets(ImmutableMap immutablemap)
                {
/* 454*/            _flddelegate = (ImmutableMap)Preconditions.checkNotNull(immutablemap);
                }
    }

    public static class Builder
    {

                private void ensureCapacity(int i)
                {
/* 194*/            if(i > entries.length)
/* 195*/                entries = (ImmutableMapEntry.TerminalEntry[])ObjectArrays.arraysCopyOf(entries, ImmutableCollection.Builder.expandedCapacity(entries.length, i));
                }

                public Builder put(Object obj, Object obj1)
                {
/* 205*/            ensureCapacity(size + 1);
/* 206*/            obj = ImmutableMap.entryOf(obj, obj1);
/* 208*/            entries[size++] = ((ImmutableMapEntry.TerminalEntry) (obj));
/* 209*/            return this;
                }

                public ImmutableMap build()
                {
/* 248*/            switch(size)
                    {
/* 250*/            case 0: // '\0'
/* 250*/                return ImmutableMap.of();

/* 252*/            case 1: // '\001'
/* 252*/                return ImmutableMap.of(entries[0].getKey(), entries[0].getValue());
                    }
/* 254*/            return new RegularImmutableMap(size, entries);
                }

                ImmutableMapEntry.TerminalEntry entries[];
                int size;

                public Builder()
                {
/* 184*/            this(4);
                }

                Builder(int i)
                {
/* 189*/            entries = new ImmutableMapEntry.TerminalEntry[i];
/* 190*/            size = 0;
                }
    }


            public static ImmutableMap of()
            {
/*  70*/        return ImmutableBiMap.of();
            }

            public static ImmutableMap of(Object obj, Object obj1)
            {
/*  80*/        return ImmutableBiMap.of(obj, obj1);
            }

            public static ImmutableMap of(Object obj, Object obj1, Object obj2, Object obj3)
            {
/*  89*/        return new RegularImmutableMap(new ImmutableMapEntry.TerminalEntry[] {
/*  89*/            entryOf(obj, obj1), entryOf(obj2, obj3)
                });
            }

            public static ImmutableMap of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
            {
/*  99*/        return new RegularImmutableMap(new ImmutableMapEntry.TerminalEntry[] {
/*  99*/            entryOf(obj, obj1), entryOf(obj2, obj3), entryOf(obj4, obj5)
                });
            }

            public static ImmutableMap of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7)
            {
/* 110*/        return new RegularImmutableMap(new ImmutableMapEntry.TerminalEntry[] {
/* 110*/            entryOf(obj, obj1), entryOf(obj2, obj3), entryOf(obj4, obj5), entryOf(obj6, obj7)
                });
            }

            public static ImmutableMap of(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, 
                    Object obj8, Object obj9)
            {
/* 121*/        return new RegularImmutableMap(new ImmutableMapEntry.TerminalEntry[] {
/* 121*/            entryOf(obj, obj1), entryOf(obj2, obj3), entryOf(obj4, obj5), entryOf(obj6, obj7), entryOf(obj8, obj9)
                });
            }

            static ImmutableMapEntry.TerminalEntry entryOf(Object obj, Object obj1)
            {
/* 135*/        CollectPreconditions.checkEntryNotNull(obj, obj1);
/* 136*/        return new ImmutableMapEntry.TerminalEntry(obj, obj1);
            }

            public static Builder builder()
            {
/* 144*/        return new Builder();
            }

            static void checkNoConflict(boolean flag, String s, java.util.Map.Entry entry, java.util.Map.Entry entry1)
            {
/* 149*/        if(!flag)
                {
/* 150*/            flag = String.valueOf(String.valueOf(s));
/* 150*/            s = String.valueOf(String.valueOf(entry));
/* 150*/            entry = String.valueOf(String.valueOf(entry1));
/* 150*/            throw new IllegalArgumentException((new StringBuilder(34 + flag.length() + s.length() + entry.length())).append("Multiple entries with same ").append(flag).append(": ").append(s).append(" and ").append(entry).toString());
                } else
                {
/* 153*/            return;
                }
            }

            public static ImmutableMap copyOf(Map map)
            {
                ImmutableMap immutablemap;
/* 273*/        if((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap))
                {
/* 278*/            if(!(immutablemap = (ImmutableMap)map).isPartialView())
/* 280*/                return immutablemap;
                } else
/* 282*/        if(map instanceof EnumMap)
/* 283*/            return copyOfEnumMapUnsafe(map);
                java.util.Map.Entry aentry[];
/* 285*/        switch((aentry = (java.util.Map.Entry[])map.entrySet().toArray(EMPTY_ENTRY_ARRAY)).length)
                {
/* 288*/        case 0: // '\0'
/* 288*/            return of();

/* 291*/        case 1: // '\001'
/* 291*/            return of((map = aentry[0]).getKey(), map.getValue());
                }
/* 294*/        return new RegularImmutableMap(aentry);
            }

            private static ImmutableMap copyOfEnumMapUnsafe(Map map)
            {
/* 301*/        return copyOfEnumMap((EnumMap)map);
            }

            private static ImmutableMap copyOfEnumMap(Map map)
            {
                java.util.Map.Entry entry;
/* 306*/        for(Iterator iterator = (map = new EnumMap(map)).entrySet().iterator(); iterator.hasNext(); CollectPreconditions.checkEntryNotNull((entry = (java.util.Map.Entry)iterator.next()).getKey(), entry.getValue()));
/* 310*/        return ImmutableEnumMap.asImmutable(map);
            }

            ImmutableMap()
            {
            }

            /**
             * @deprecated Method put is deprecated
             */

            public final Object put(Object obj, Object obj1)
            {
/* 326*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method remove is deprecated
             */

            public final Object remove(Object obj)
            {
/* 338*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method putAll is deprecated
             */

            public final void putAll(Map map)
            {
/* 350*/        throw new UnsupportedOperationException();
            }

            /**
             * @deprecated Method clear is deprecated
             */

            public final void clear()
            {
/* 362*/        throw new UnsupportedOperationException();
            }

            public boolean isEmpty()
            {
/* 367*/        return size() == 0;
            }

            public boolean containsKey(Object obj)
            {
/* 372*/        return get(obj) != null;
            }

            public boolean containsValue(Object obj)
            {
/* 377*/        return values().contains(obj);
            }

            public abstract Object get(Object obj);

            public ImmutableSet entrySet()
            {
                ImmutableSet immutableset;
/* 392*/        if((immutableset = entrySet) == null)
/* 393*/            return entrySet = createEntrySet();
/* 393*/        else
/* 393*/            return immutableset;
            }

            abstract ImmutableSet createEntrySet();

            public ImmutableSet keySet()
            {
                ImmutableSet immutableset;
/* 406*/        if((immutableset = keySet) == null)
/* 407*/            return keySet = createKeySet();
/* 407*/        else
/* 407*/            return immutableset;
            }

            ImmutableSet createKeySet()
            {
/* 411*/        return new ImmutableMapKeySet(this);
            }

            public ImmutableCollection values()
            {
                ImmutableCollection immutablecollection;
/* 422*/        if((immutablecollection = values) == null)
/* 423*/            return values = new ImmutableMapValues(this);
/* 423*/        else
/* 423*/            return immutablecollection;
            }

            public ImmutableSetMultimap asMultimap()
            {
                ImmutableSetMultimap immutablesetmultimap;
/* 436*/        if((immutablesetmultimap = multimapView) == null)
/* 437*/            return multimapView = createMultimapView();
/* 437*/        else
/* 437*/            return immutablesetmultimap;
            }

            private ImmutableSetMultimap createMultimapView()
            {
/* 441*/        ImmutableMap immutablemap = viewMapValuesAsSingletonSets();
/* 442*/        return new ImmutableSetMultimap(immutablemap, immutablemap.size(), null);
            }

            private ImmutableMap viewMapValuesAsSingletonSets()
            {
/* 446*/        return new MapViewOfValuesAsSingletonSets(this);
            }

            public boolean equals(Object obj)
            {
/* 507*/        return Maps.equalsImpl(this, obj);
            }

            abstract boolean isPartialView();

            public int hashCode()
            {
/* 515*/        return entrySet().hashCode();
            }

            public String toString()
            {
/* 519*/        return Maps.toStringImpl(this);
            }

            Object writeReplace()
            {
/* 554*/        return new SerializedForm(this);
            }

            public volatile Set entrySet()
            {
/*  60*/        return entrySet();
            }

            public volatile Collection values()
            {
/*  60*/        return values();
            }

            public volatile Set keySet()
            {
/*  60*/        return keySet();
            }

            private static final java.util.Map.Entry EMPTY_ENTRY_ARRAY[] = new java.util.Map.Entry[0];
            private transient ImmutableSet entrySet;
            private transient ImmutableSet keySet;
            private transient ImmutableCollection values;
            private transient ImmutableSetMultimap multimapView;

}
