// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableEnumMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMapEntrySet, ImmutableEnumMap, ImmutableMap, UnmodifiableIterator, 
//            Maps

class t extends ImmutableMapEntrySet
{

            ImmutableMap map()
            {
/* 104*/        return ImmutableEnumMap.this;
            }

            public UnmodifiableIterator iterator()
            {
/* 109*/        return new UnmodifiableIterator() {

                    public boolean hasNext()
                    {
/* 114*/                return backingIterator.hasNext();
                    }

                    public java.util.Map.Entry next()
                    {
                        java.util.Map.Entry entry;
/* 119*/                return Maps.immutableEntry((entry = (java.util.Map.Entry)backingIterator.next()).getKey(), entry.getValue());
                    }

                    public volatile Object next()
                    {
/* 109*/                return next();
                    }

                    private final Iterator backingIterator;
                    final ImmutableEnumMap._cls2 this$1;

                    
                    {
/* 109*/                this$1 = ImmutableEnumMap._cls2.this;
/* 109*/                super();
/* 110*/                backingIterator = ImmutableEnumMap.access$000(this$0).entrySet().iterator();
                    }
        };
            }

            public volatile Iterator iterator()
            {
/* 100*/        return iterator();
            }

            final ImmutableEnumMap this$0;

            this._cls0()
            {
/* 100*/        this$0 = ImmutableEnumMap.this;
/* 100*/        super();
            }
}
