// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multimaps.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Maps, Multimap, Multimaps

static final class NotNull extends tractMap
{
    class EntrySet extends Maps.EntrySet
    {

                Map map()
                {
/*1693*/            return Multimaps.AsMap.this;
                }

                public Iterator iterator()
                {
/*1697*/            return Maps.asMapEntryIterator(multimap.keySet(), new Function() {

                        public Collection apply(Object obj)
                        {
/*1700*/                    return multimap.get(obj);
                        }

                        public volatile Object apply(Object obj)
                        {
/*1697*/                    return apply(obj);
                        }

                        final EntrySet this$1;

                        
                        {
/*1697*/                    this$1 = EntrySet.this;
/*1697*/                    super();
                        }
            });
                }

                public boolean remove(Object obj)
                {
/*1706*/            if(!contains(obj))
                    {
/*1707*/                return false;
                    } else
                    {
/*1709*/                obj = (java.util.Map.Entry)obj;
/*1710*/                removeValuesForKey(((java.util.Map.Entry) (obj)).getKey());
/*1711*/                return true;
                    }
                }

                final Multimaps.AsMap this$0;

                EntrySet()
                {
/*1691*/            this$0 = Multimaps.AsMap.this;
/*1691*/            super();
                }
    }


            public final int size()
            {
/*1680*/        return multimap.keySet().size();
            }

            protected final Set createEntrySet()
            {
/*1684*/        return new EntrySet();
            }

            final void removeValuesForKey(Object obj)
            {
/*1688*/        multimap.keySet().remove(obj);
            }

            public final Collection get(Object obj)
            {
/*1717*/        if(containsKey(obj))
/*1717*/            return multimap.get(obj);
/*1717*/        else
/*1717*/            return null;
            }

            public final Collection remove(Object obj)
            {
/*1721*/        if(containsKey(obj))
/*1721*/            return multimap.removeAll(obj);
/*1721*/        else
/*1721*/            return null;
            }

            public final Set keySet()
            {
/*1725*/        return multimap.keySet();
            }

            public final boolean isEmpty()
            {
/*1729*/        return multimap.isEmpty();
            }

            public final boolean containsKey(Object obj)
            {
/*1733*/        return multimap.containsKey(obj);
            }

            public final void clear()
            {
/*1737*/        multimap.clear();
            }

            public final volatile Object remove(Object obj)
            {
/*1671*/        return remove(obj);
            }

            public final volatile Object get(Object obj)
            {
/*1671*/        return get(obj);
            }

            private final Multimap multimap;


            nit>(Multimap multimap1)
            {
/*1676*/        multimap = (Multimap)Preconditions.checkNotNull(multimap1);
            }
}
