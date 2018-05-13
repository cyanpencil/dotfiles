// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableEnumMap.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, Iterables, ImmutableSet, Iterators, 
//            UnmodifiableIterator, ImmutableMapEntrySet, Maps

final class ImmutableEnumMap extends ImmutableMap
{
    static class EnumSerializedForm
        implements Serializable
    {

                final EnumMap _flddelegate;

                EnumSerializedForm(EnumMap enummap)
                {
/* 144*/            _flddelegate = enummap;
                }
    }


            static ImmutableMap asImmutable(EnumMap enummap)
            {
/*  38*/        switch(enummap.size())
                {
/*  40*/        case 0: // '\0'
/*  40*/            return ImmutableMap.of();

/*  42*/        case 1: // '\001'
/*  42*/            return ImmutableMap.of((enummap = (java.util.Map.Entry)Iterables.getOnlyElement(enummap.entrySet())).getKey(), enummap.getValue());
                }
/*  46*/        return new ImmutableEnumMap(enummap);
            }

            private ImmutableEnumMap(EnumMap enummap)
            {
/*  53*/        _flddelegate = enummap;
/*  54*/        Preconditions.checkArgument(!enummap.isEmpty());
            }

            final ImmutableSet createKeySet()
            {
/*  59*/        return new ImmutableSet() {

                    public boolean contains(Object obj)
                    {
/*  63*/                return _flddelegate.containsKey(obj);
                    }

                    public int size()
                    {
/*  68*/                return ImmutableEnumMap.this.size();
                    }

                    public UnmodifiableIterator iterator()
                    {
/*  73*/                return Iterators.unmodifiableIterator(_flddelegate.keySet().iterator());
                    }

                    boolean isPartialView()
                    {
/*  78*/                return true;
                    }

                    public volatile Iterator iterator()
                    {
/*  59*/                return iterator();
                    }

                    final ImmutableEnumMap this$0;

                    
                    {
/*  59*/                this$0 = ImmutableEnumMap.this;
/*  59*/                super();
                    }
        };
            }

            public final int size()
            {
/*  85*/        return _flddelegate.size();
            }

            public final boolean containsKey(Object obj)
            {
/*  90*/        return _flddelegate.containsKey(obj);
            }

            public final Object get(Object obj)
            {
/*  95*/        return _flddelegate.get(obj);
            }

            final ImmutableSet createEntrySet()
            {
/* 100*/        return new ImmutableMapEntrySet() {

                    ImmutableMap map()
                    {
/* 104*/                return ImmutableEnumMap.this;
                    }

                    public UnmodifiableIterator iterator()
                    {
/* 109*/                return new UnmodifiableIterator() {

                            public boolean hasNext()
                            {
/* 114*/                        return backingIterator.hasNext();
                            }

                            public java.util.Map.Entry next()
                            {
                                java.util.Map.Entry entry;
/* 119*/                        return Maps.immutableEntry((entry = (java.util.Map.Entry)backingIterator.next()).getKey(), entry.getValue());
                            }

                            public volatile Object next()
                            {
/* 109*/                        return next();
                            }

                            private final Iterator backingIterator;
                            final _cls2 this$1;

                            
                            {
/* 109*/                        this$1 = _cls2.this;
/* 109*/                        super();
/* 110*/                        backingIterator = _flddelegate.entrySet().iterator();
                            }
                };
                    }

                    public volatile Iterator iterator()
                    {
/* 100*/                return iterator();
                    }

                    final ImmutableEnumMap this$0;

                    
                    {
/* 100*/                this$0 = ImmutableEnumMap.this;
/* 100*/                super();
                    }
        };
            }

            final boolean isPartialView()
            {
/* 129*/        return false;
            }

            final Object writeReplace()
            {
/* 134*/        return new EnumSerializedForm(_flddelegate);
            }

            private final transient EnumMap _flddelegate;

}
