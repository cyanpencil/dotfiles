// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMapEntrySet, ImmutableMap, ImmutableSet, UnmodifiableIterator, 
//            AbstractMapEntry

class this._cls0 extends ImmutableMapEntrySet
{

            ImmutableMap map()
            {
/* 477*/        return this._cls0.this;
            }

            public UnmodifiableIterator iterator()
            {
/* 482*/        final UnmodifiableIterator backingIterator = cess._mth000(this._cls0.this).entrySet().iterator();
/* 483*/        return new UnmodifiableIterator() {

                    public boolean hasNext()
                    {
/* 485*/                return backingIterator.hasNext();
                    }

                    public java.util.Map.Entry next()
                    {
/* 489*/                final java.util.Map.Entry backingEntry = (java.util.Map.Entry)backingIterator.next();
/* 490*/                return new AbstractMapEntry() {

                            public Object getKey()
                            {
/* 492*/                        return backingEntry.getKey();
                            }

                            public ImmutableSet getValue()
                            {
/* 496*/                        return ImmutableSet.of(backingEntry.getValue());
                            }

                            public volatile Object getValue()
                            {
/* 490*/                        return getValue();
                            }

                            final java.util.Map.Entry val$backingEntry;
                            final _cls1 this$2;

                            
                            {
/* 490*/                        this$2 = _cls1.this;
/* 490*/                        backingEntry = entry;
/* 490*/                        super();
                            }
                };
                    }

                    public volatile Object next()
                    {
/* 483*/                return next();
                    }

                    final Iterator val$backingIterator;
                    final ImmutableMap.MapViewOfValuesAsSingletonSets._cls1 this$1;

                    
                    {
/* 483*/                this$1 = ImmutableMap.MapViewOfValuesAsSingletonSets._cls1.this;
/* 483*/                backingIterator = iterator1;
/* 483*/                super();
                    }
        };
            }

            public volatile Iterator iterator()
            {
/* 475*/        return iterator();
            }

            final iterator this$0;

            _cls1.val.backingIterator()
            {
/* 475*/        this$0 = this._cls0.this;
/* 475*/        super();
            }
}
