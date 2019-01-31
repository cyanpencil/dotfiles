// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, ImmutableSet, ImmutableMapEntrySet, UnmodifiableIterator, 
//            AbstractMapEntry

static final class delegate extends ImmutableMap
{

            public final int size()
            {
/* 458*/        return _flddelegate.size();
            }

            public final boolean containsKey(Object obj)
            {
/* 462*/        return _flddelegate.containsKey(obj);
            }

            public final ImmutableSet get(Object obj)
            {
/* 466*/        if((obj = _flddelegate.get(obj)) == null)
/* 467*/            return null;
/* 467*/        else
/* 467*/            return ImmutableSet.of(obj);
            }

            final boolean isPartialView()
            {
/* 471*/        return false;
            }

            final ImmutableSet createEntrySet()
            {
/* 475*/        return new ImmutableMapEntrySet() {

                    ImmutableMap map()
                    {
/* 477*/                return ImmutableMap.MapViewOfValuesAsSingletonSets.this;
                    }

                    public UnmodifiableIterator iterator()
                    {
/* 482*/                final UnmodifiableIterator backingIterator = _flddelegate.entrySet().iterator();
/* 483*/                return new UnmodifiableIterator() {

                            public boolean hasNext()
                            {
/* 485*/                        return backingIterator.hasNext();
                            }

                            public java.util.Map.Entry next()
                            {
/* 489*/                        final java.util.Map.Entry backingEntry = (java.util.Map.Entry)backingIterator.next();
/* 490*/                        return new AbstractMapEntry() {

                                    public Object getKey()
                                    {
/* 492*/                                return backingEntry.getKey();
                                    }

                                    public ImmutableSet getValue()
                                    {
/* 496*/                                return ImmutableSet.of(backingEntry.getValue());
                                    }

                                    public volatile Object getValue()
                                    {
/* 490*/                                return getValue();
                                    }

                                    final java.util.Map.Entry val$backingEntry;
                                    final _cls1 this$2;

                                
                                {
/* 490*/                            this$2 = _cls1.this;
/* 490*/                            backingEntry = entry;
/* 490*/                            super();
                                }
                        };
                            }

                            public volatile Object next()
                            {
/* 483*/                        return next();
                            }

                            final Iterator val$backingIterator;
                            final _cls1 this$1;

                            
                            {
/* 483*/                        this$1 = _cls1.this;
/* 483*/                        backingIterator = iterator1;
/* 483*/                        super();
                            }
                };
                    }

                    public volatile Iterator iterator()
                    {
/* 475*/                return iterator();
                    }

                    final ImmutableMap.MapViewOfValuesAsSingletonSets this$0;

                    
                    {
/* 475*/                this$0 = ImmutableMap.MapViewOfValuesAsSingletonSets.this;
/* 475*/                super();
                    }
        };
            }

            public final volatile Object get(Object obj)
            {
/* 449*/        return get(obj);
            }

            public final volatile Set entrySet()
            {
/* 449*/        return super.entrySet();
            }

            public final volatile Collection values()
            {
/* 449*/        return super.values();
            }

            public final volatile Set keySet()
            {
/* 449*/        return super.keySet();
            }

            private final ImmutableMap _flddelegate;


            _cls1.this._cls0(ImmutableMap immutablemap)
            {
/* 454*/        _flddelegate = (ImmutableMap)Preconditions.checkNotNull(immutablemap);
            }
}
