// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingSet, Maps, TransformedIterator, ForwardingMapEntry, 
//            UnmodifiableIterator

class this._cls0 extends ForwardingSet
{

            protected Set _mthdelegate()
            {
/*2701*/        return ntrySet;
            }

            public Iterator iterator()
            {
/*2705*/        return new TransformedIterator(ntrySet.iterator()) {

                    java.util.Map.Entry transform(final java.util.Map.Entry entry)
                    {
/*2708*/                return new ForwardingMapEntry() {

                            protected java.util.Map.Entry _mthdelegate()
                            {
/*2711*/                        return entry;
                            }

                            public Object setValue(Object obj)
                            {
/*2716*/                        Preconditions.checkArgument(apply(getKey(), obj));
/*2717*/                        return super.setValue(obj);
                            }

                            protected volatile Object _mthdelegate()
                            {
/*2708*/                        return _mthdelegate();
                            }

                            final java.util.Map.Entry val$entry;
                            final _cls1 this$2;

                            
                            {
/*2708*/                        this$2 = _cls1.this;
/*2708*/                        entry = entry1;
/*2708*/                        super();
                            }
                };
                    }

                    volatile Object transform(Object obj)
                    {
/*2705*/                return transform((java.util.Map.Entry)obj);
                    }

                    final Maps.FilteredEntryMap.EntrySet this$1;

                    
                    {
/*2705*/                this$1 = Maps.FilteredEntryMap.EntrySet.this;
/*2705*/                super(iterator1);
                    }
        };
            }

            protected volatile Collection _mthdelegate()
            {
/*2699*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*2699*/        return _mthdelegate();
            }

            final delegate this$0;

            private _cls1.this._cls1()
            {
/*2699*/        this$0 = this._cls0.this;
/*2699*/        super();
            }


            // Unreferenced inner class jersey/repackaged/com/google/common/collect/Maps$1

/* anonymous class */
    static class Maps._cls1 extends UnmodifiableIterator
    {

                public final boolean hasNext()
                {
/* 125*/            return entryIterator.hasNext();
                }

                public final Object next()
                {
/* 130*/            return ((java.util.Map.Entry)entryIterator.next()).getValue();
                }

                final UnmodifiableIterator val$entryIterator;

                    
                    {
/* 122*/                entryIterator = unmodifiableiterator;
/* 122*/                super();
                    }
    }

}
