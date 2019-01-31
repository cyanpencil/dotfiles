// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            SortedSetMultimap, Synchronized, SetMultimap, Multimap

static class  extends 
    implements SortedSetMultimap
{

            SortedSetMultimap _mthdelegate()
            {
/* 789*/        return (SortedSetMultimap)super.te();
            }

            public SortedSet get(Object obj)
            {
/* 792*/        Object obj1 = mutex;
/* 792*/        JVM INSTR monitorenter ;
/* 793*/        return Synchronized.access$100(_mthdelegate().get(obj), mutex);
/* 794*/        obj;
/* 794*/        throw obj;
            }

            public SortedSet removeAll(Object obj)
            {
/* 797*/        Object obj1 = mutex;
/* 797*/        JVM INSTR monitorenter ;
/* 798*/        return _mthdelegate().removeAll(obj);
/* 799*/        obj;
/* 799*/        throw obj;
            }

            public SortedSet replaceValues(Object obj, Iterable iterable)
            {
/* 803*/        Object obj1 = mutex;
/* 803*/        JVM INSTR monitorenter ;
/* 804*/        return _mthdelegate().replaceValues(obj, iterable);
/* 805*/        obj;
/* 805*/        throw obj;
            }

            public Comparator valueComparator()
            {
/* 809*/        Object obj = mutex;
/* 809*/        JVM INSTR monitorenter ;
/* 810*/        return _mthdelegate().valueComparator();
                Exception exception;
/* 811*/        exception;
/* 811*/        throw exception;
            }

            public volatile Set replaceValues(Object obj, Iterable iterable)
            {
/* 782*/        return replaceValues(obj, iterable);
            }

            public volatile Set removeAll(Object obj)
            {
/* 782*/        return removeAll(obj);
            }

            public volatile Set get(Object obj)
            {
/* 782*/        return get(obj);
            }

            volatile SetMultimap _mthdelegate()
            {
/* 782*/        return _mthdelegate();
            }

            public volatile Collection get(Object obj)
            {
/* 782*/        return get(obj);
            }

            public volatile Collection removeAll(Object obj)
            {
/* 782*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 782*/        return replaceValues(obj, iterable);
            }

            volatile Multimap _mthdelegate()
            {
/* 782*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 782*/        return _mthdelegate();
            }

            (SortedSetMultimap sortedsetmultimap, Object obj)
            {
/* 786*/        super(sortedsetmultimap, obj);
            }
}
