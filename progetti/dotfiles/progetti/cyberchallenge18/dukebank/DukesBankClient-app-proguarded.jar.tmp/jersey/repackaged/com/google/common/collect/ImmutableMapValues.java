// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableMapValues.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection, ImmutableMap, ImmutableSet, Iterators, 
//            Maps, UnmodifiableIterator, ImmutableList, ImmutableAsList

final class ImmutableMapValues extends ImmutableCollection
{
    static class SerializedForm
        implements Serializable
    {

                final ImmutableMap map;

                SerializedForm(ImmutableMap immutablemap)
                {
/*  86*/            map = immutablemap;
                }
    }


            ImmutableMapValues(ImmutableMap immutablemap)
            {
/*  38*/        map = immutablemap;
            }

            public final int size()
            {
/*  43*/        return map.size();
            }

            public final UnmodifiableIterator iterator()
            {
/*  48*/        return Maps.valueIterator(map.entrySet().iterator());
            }

            public final boolean contains(Object obj)
            {
/*  53*/        return obj != null && Iterators.contains(iterator(), obj);
            }

            final boolean isPartialView()
            {
/*  58*/        return true;
            }

            final ImmutableList createAsList()
            {
/*  63*/        final ImmutableList entryList = map.entrySet().asList();
/*  64*/        return new ImmutableAsList() {

                    public Object get(int i)
                    {
/*  67*/                return ((java.util.Map.Entry)entryList.get(i)).getValue();
                    }

                    ImmutableCollection delegateCollection()
                    {
/*  72*/                return ImmutableMapValues.this;
                    }

                    final ImmutableList val$entryList;
                    final ImmutableMapValues this$0;

                    
                    {
/*  64*/                this$0 = ImmutableMapValues.this;
/*  64*/                entryList = immutablelist;
/*  64*/                super();
                    }
        };
            }

            final Object writeReplace()
            {
/*  79*/        return new SerializedForm(map);
            }

            public final volatile Iterator iterator()
            {
/*  33*/        return iterator();
            }

            private final ImmutableMap map;
}
