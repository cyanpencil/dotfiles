// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMap, ImmutableSet, Sets, AbstractIndexedListIterator

static final class ize extends AbstractSet
{

            public final int size()
            {
/*1291*/        return 1 << inputSet.size();
            }

            public final boolean isEmpty()
            {
/*1295*/        return false;
            }

            public final Iterator iterator()
            {
/*1299*/        return new AbstractIndexedListIterator(size()) {

                    protected Set get(int i)
                    {
/*1301*/                return new Sets.SubSet(inputSet, i);
                    }

                    protected volatile Object get(int i)
                    {
/*1299*/                return get(i);
                    }

                    final Sets.PowerSet this$0;

                    
                    {
/*1299*/                this$0 = Sets.PowerSet.this;
/*1299*/                super(i);
                    }
        };
            }

            public final boolean contains(Object obj)
            {
/*1307*/        if(obj instanceof Set)
                {
/*1308*/            obj = (Set)obj;
/*1309*/            return inputSet.keySet().containsAll(((java.util.Collection) (obj)));
                } else
                {
/*1311*/            return false;
                }
            }

            public final boolean equals(Object obj)
            {
/*1315*/        if(obj instanceof ontainsAll)
                {
/*1316*/            obj = (ontainsAll)obj;
/*1317*/            return inputSet.equals(((quals) (obj)).inputSet);
                } else
                {
/*1319*/            return super.equals(obj);
                }
            }

            public final int hashCode()
            {
/*1328*/        return inputSet.keySet().hashCode() << inputSet.size() - 1;
            }

            public final String toString()
            {
/*1332*/        String s = String.valueOf(String.valueOf(inputSet));
/*1332*/        return (new StringBuilder(10 + s.length())).append("powerSet(").append(s).append(")").toString();
            }

            final ImmutableMap inputSet;

            uilder(Set set)
            {
/*1280*/        uilder uilder = ImmutableMap.builder();
/*1281*/        int i = 0;
                Object obj;
/*1282*/        for(set = ((Set)Preconditions.checkNotNull(set)).iterator(); set.hasNext(); uilder.put(obj, Integer.valueOf(i++)))
/*1282*/            obj = set.next();

/*1285*/        inputSet = uilder.build();
/*1286*/        Preconditions.checkArgument(inputSet.size() <= 30, "Too many elements to create power set: %s > 30", new Object[] {
/*1286*/            Integer.valueOf(inputSet.size())
                });
            }
}
