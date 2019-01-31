// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            SetMultimap, Synchronized, Multimap

static class it> extends it>
    implements SetMultimap
{

            SetMultimap _mthdelegate()
            {
/* 745*/        return (SetMultimap)super.egate();
            }

            public Set get(Object obj)
            {
/* 748*/        Object obj1 = mutex;
/* 748*/        JVM INSTR monitorenter ;
/* 749*/        return Synchronized.set(_mthdelegate().get(obj), mutex);
/* 750*/        obj;
/* 750*/        throw obj;
            }

            public Set removeAll(Object obj)
            {
/* 753*/        Object obj1 = mutex;
/* 753*/        JVM INSTR monitorenter ;
/* 754*/        return _mthdelegate().removeAll(obj);
/* 755*/        obj;
/* 755*/        throw obj;
            }

            public Set replaceValues(Object obj, Iterable iterable)
            {
/* 759*/        Object obj1 = mutex;
/* 759*/        JVM INSTR monitorenter ;
/* 760*/        return _mthdelegate().replaceValues(obj, iterable);
/* 761*/        obj;
/* 761*/        throw obj;
            }

            public Set entries()
            {
/* 764*/        Object obj = mutex;
/* 764*/        JVM INSTR monitorenter ;
/* 765*/        if(entrySet == null)
/* 766*/            entrySet = Synchronized.set(_mthdelegate().entries(), mutex);
/* 768*/        return entrySet;
                Exception exception;
/* 769*/        exception;
/* 769*/        throw exception;
            }

            public volatile Collection entries()
            {
/* 736*/        return entries();
            }

            public volatile Collection removeAll(Object obj)
            {
/* 736*/        return removeAll(obj);
            }

            public volatile Collection replaceValues(Object obj, Iterable iterable)
            {
/* 736*/        return replaceValues(obj, iterable);
            }

            public volatile Collection get(Object obj)
            {
/* 736*/        return get(obj);
            }

            volatile Multimap _mthdelegate()
            {
/* 736*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 736*/        return _mthdelegate();
            }

            transient Set entrySet;

            (SetMultimap setmultimap, Object obj)
            {
/* 742*/        super(setmultimap, obj);
            }
}
