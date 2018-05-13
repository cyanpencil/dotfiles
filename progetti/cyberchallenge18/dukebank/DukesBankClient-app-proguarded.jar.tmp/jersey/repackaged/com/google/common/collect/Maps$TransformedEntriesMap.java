// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Iterators

static class transformer extends transformer
{

            public int size()
            {
/*1889*/        return fromMap.size();
            }

            public boolean containsKey(Object obj)
            {
/*1893*/        return fromMap.containsKey(obj);
            }

            public Object get(Object obj)
            {
                Object obj1;
/*1899*/        if((obj1 = fromMap.get(obj)) != null || fromMap.containsKey(obj))
/*1900*/            return transformer.formEntry(obj, obj1);
/*1900*/        else
/*1900*/            return null;
            }

            public Object remove(Object obj)
            {
/*1908*/        if(fromMap.containsKey(obj))
/*1908*/            return transformer.formEntry(obj, fromMap.remove(obj));
/*1908*/        else
/*1908*/            return null;
            }

            public void clear()
            {
/*1914*/        fromMap.clear();
            }

            public Set keySet()
            {
/*1918*/        return fromMap.keySet();
            }

            protected Set createEntrySet()
            {
/*1923*/        return new Maps.EntrySet() {

                    Map map()
                    {
/*1925*/                return Maps.TransformedEntriesMap.this;
                    }

                    public Iterator iterator()
                    {
/*1929*/                return Iterators.transform(fromMap.entrySet().iterator(), Maps.asEntryToEntryFunction(transformer));
                    }

                    final Maps.TransformedEntriesMap this$0;

                    
                    {
/*1923*/                this$0 = Maps.TransformedEntriesMap.this;
/*1923*/                super();
                    }
        };
            }

            final Map fromMap;
            final transformer transformer;

            _cls1.this._cls0(Map map, _cls1.this._cls0 _pcls0)
            {
/*1884*/        fromMap = (Map)Preconditions.checkNotNull(map);
/*1885*/        transformer = (transformer)Preconditions.checkNotNull(_pcls0);
            }
}
