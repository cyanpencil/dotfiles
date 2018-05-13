// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMapKeySet.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableSet, ImmutableList, ImmutableMap, UnmodifiableIterator, 
//            ImmutableAsList, ImmutableCollection

final class ImmutableMapKeySet extends ImmutableSet
{
    static class KeySetSerializedForm
        implements Serializable
    {

                final ImmutableMap map;

                KeySetSerializedForm(ImmutableMap immutablemap)
                {
/*  88*/            map = immutablemap;
                }
    }


            ImmutableMapKeySet(ImmutableMap immutablemap)
            {
/*  38*/        map = immutablemap;
            }

            public final int size()
            {
/*  43*/        return map.size();
            }

            public final UnmodifiableIterator iterator()
            {
/*  48*/        return asList().iterator();
            }

            public final boolean contains(Object obj)
            {
/*  53*/        return map.containsKey(obj);
            }

            final ImmutableList createAsList()
            {
/*  58*/        final ImmutableList entryList = map.entrySet().asList();
/*  59*/        return new ImmutableAsList() {

                    public Object get(int i)
                    {
/*  63*/                return ((java.util.Map.Entry)entryList.get(i)).getKey();
                    }

                    ImmutableCollection delegateCollection()
                    {
/*  68*/                return ImmutableMapKeySet.this;
                    }

                    final ImmutableList val$entryList;
                    final ImmutableMapKeySet this$0;

                    
                    {
/*  59*/                this$0 = ImmutableMapKeySet.this;
/*  59*/                entryList = immutablelist;
/*  59*/                super();
                    }
        };
            }

            final boolean isPartialView()
            {
/*  76*/        return true;
            }

            final Object writeReplace()
            {
/*  81*/        return new KeySetSerializedForm(map);
            }

            public final volatile Iterator iterator()
            {
/*  33*/        return iterator();
            }

            private final ImmutableMap map;
}
