// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.List;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ListMultimap, Synchronized, Multimap

static class t> extends t>
    implements ListMultimap
{

            ListMultimap _mthdelegate()
            {
/* 706*/        return (ListMultimap)super.gate();
            }

            public List get(Object obj)
            {
/* 709*/        Object obj1 = mutex;
/* 709*/        JVM INSTR monitorenter ;
/* 710*/        return Synchronized.access$200(_mthdelegate().get(obj), mutex);
/* 711*/        obj;
/* 711*/        throw obj;
            }

            public List removeAll(Object obj)
            {
/* 714*/        Object obj1 = mutex;
/* 714*/        JVM INSTR monitorenter ;
/* 715*/        return _mthdelegate().removeAll(obj);
/* 716*/        obj;
/* 716*/        throw obj;
            }

            public List replaceValues(Object obj, Iterable iterable)
            {
/* 720*/        Object obj1 = mutex;
/* 720*/        JVM INSTR monitorenter ;
/* 721*/        return _mthdelegate().replaceValues(obj, iterable);
/* 722*/        obj;
/* 722*/        throw obj;
            }

            public volatile Collection removeAll(Object obj)
            {
/* 699*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 699*/        return replaceValues(obj, iterable);
            }

            public volatile Collection get(Object obj)
            {
/* 699*/        return get(obj);
            }

            volatile Multimap _mthdelegate()
            {
/* 699*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 699*/        return _mthdelegate();
            }

            (ListMultimap listmultimap, Object obj)
            {
/* 703*/        super(listmultimap, obj);
            }
}
