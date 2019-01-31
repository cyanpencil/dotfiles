// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StandardTable.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, StandardTable, Collections2

class Map extends Map
{
    class EntrySet extends StandardTable.TableSet
    {

                public Iterator iterator()
                {
/* 722*/            return Maps.asMapEntryIterator(backingMap.keySet(), new Function() {

                        public Map apply(Object obj)
                        {
/* 725*/                    return row(obj);
                        }

                        public volatile Object apply(Object obj)
                        {
/* 722*/                    return apply(obj);
                        }

                        final EntrySet this$2;

                        
                        {
/* 722*/                    this$2 = EntrySet.this;
/* 722*/                    super();
                        }
            });
                }

                public int size()
                {
/* 731*/            return backingMap.size();
                }

                public boolean contains(Object obj)
                {
/* 735*/            if(obj instanceof java.util.Map.Entry)
/* 736*/                return ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey() != null && (((java.util.Map.Entry) (obj)).getValue() instanceof Map) && Collections2.safeContains(backingMap.entrySet(), obj);
/* 741*/            else
/* 741*/                return false;
                }

                public boolean remove(Object obj)
                {
/* 745*/            if(obj instanceof java.util.Map.Entry)
/* 746*/                return ((java.util.Map.Entry) (obj = (java.util.Map.Entry)obj)).getKey() != null && (((java.util.Map.Entry) (obj)).getValue() instanceof Map) && backingMap.entrySet().remove(obj);
/* 751*/            else
/* 751*/                return false;
                }

                final StandardTable.RowMap this$1;

                EntrySet()
                {
/* 720*/            this$1 = StandardTable.RowMap.this;
/* 720*/            super(this$0, null);
                }
    }


            public boolean containsKey(Object obj)
            {
/* 703*/        return containsRow(obj);
            }

            public Map get(Object obj)
            {
/* 709*/        if(containsRow(obj))
/* 709*/            return row(obj);
/* 709*/        else
/* 709*/            return null;
            }

            public Map remove(Object obj)
            {
/* 713*/        if(obj == null)
/* 713*/            return null;
/* 713*/        else
/* 713*/            return (Map)backingMap.remove(obj);
            }

            protected Set createEntrySet()
            {
/* 717*/        return new EntrySet();
            }

            public volatile Object remove(Object obj)
            {
/* 701*/        return remove(obj);
            }

            public volatile Object get(Object obj)
            {
/* 701*/        return get(obj);
            }

            final StandardTable this$0;

            this._cls0()
            {
/* 701*/        this$0 = StandardTable.this;
/* 701*/        super();
            }
}
