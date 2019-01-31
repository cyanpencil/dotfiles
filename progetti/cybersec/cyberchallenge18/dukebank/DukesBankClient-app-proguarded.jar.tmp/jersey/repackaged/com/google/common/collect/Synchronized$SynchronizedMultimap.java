// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multimap, Synchronized, Multiset

static class nit> extends nit>
    implements Multimap
{

            Multimap _mthdelegate()
            {
/* 524*/        return (Multimap)super.legate();
            }

            public int size()
            {
/* 533*/        Object obj = mutex;
/* 533*/        JVM INSTR monitorenter ;
/* 534*/        return _mthdelegate().size();
                Exception exception;
/* 535*/        exception;
/* 535*/        throw exception;
            }

            public boolean isEmpty()
            {
/* 540*/        Object obj = mutex;
/* 540*/        JVM INSTR monitorenter ;
/* 541*/        return _mthdelegate().isEmpty();
                Exception exception;
/* 542*/        exception;
/* 542*/        throw exception;
            }

            public boolean containsKey(Object obj)
            {
/* 547*/        Object obj1 = mutex;
/* 547*/        JVM INSTR monitorenter ;
/* 548*/        return _mthdelegate().containsKey(obj);
/* 549*/        obj;
/* 549*/        throw obj;
            }

            public boolean containsValue(Object obj)
            {
/* 554*/        Object obj1 = mutex;
/* 554*/        JVM INSTR monitorenter ;
/* 555*/        return _mthdelegate().containsValue(obj);
/* 556*/        obj;
/* 556*/        throw obj;
            }

            public boolean containsEntry(Object obj, Object obj1)
            {
/* 561*/        Object obj2 = mutex;
/* 561*/        JVM INSTR monitorenter ;
/* 562*/        return _mthdelegate().containsEntry(obj, obj1);
/* 563*/        obj;
/* 563*/        throw obj;
            }

            public Collection get(Object obj)
            {
/* 568*/        Object obj1 = mutex;
/* 568*/        JVM INSTR monitorenter ;
/* 569*/        return Synchronized.access$400(_mthdelegate().get(obj), mutex);
/* 570*/        obj;
/* 570*/        throw obj;
            }

            public boolean put(Object obj, Object obj1)
            {
/* 575*/        Object obj2 = mutex;
/* 575*/        JVM INSTR monitorenter ;
/* 576*/        return _mthdelegate().put(obj, obj1);
/* 577*/        obj;
/* 577*/        throw obj;
            }

            public boolean putAll(Object obj, Iterable iterable)
            {
/* 582*/        Object obj1 = mutex;
/* 582*/        JVM INSTR monitorenter ;
/* 583*/        return _mthdelegate().putAll(obj, iterable);
/* 584*/        obj;
/* 584*/        throw obj;
            }

            public boolean putAll(Multimap multimap)
            {
/* 589*/        Object obj = mutex;
/* 589*/        JVM INSTR monitorenter ;
/* 590*/        return _mthdelegate().putAll(multimap);
/* 591*/        multimap;
/* 591*/        throw multimap;
            }

            public Collection replaceValues(Object obj, Iterable iterable)
            {
/* 596*/        Object obj1 = mutex;
/* 596*/        JVM INSTR monitorenter ;
/* 597*/        return _mthdelegate().replaceValues(obj, iterable);
/* 598*/        obj;
/* 598*/        throw obj;
            }

            public boolean remove(Object obj, Object obj1)
            {
/* 603*/        Object obj2 = mutex;
/* 603*/        JVM INSTR monitorenter ;
/* 604*/        return _mthdelegate().remove(obj, obj1);
/* 605*/        obj;
/* 605*/        throw obj;
            }

            public Collection removeAll(Object obj)
            {
/* 610*/        Object obj1 = mutex;
/* 610*/        JVM INSTR monitorenter ;
/* 611*/        return _mthdelegate().removeAll(obj);
/* 612*/        obj;
/* 612*/        throw obj;
            }

            public void clear()
            {
/* 617*/        synchronized(mutex)
                {
/* 618*/            _mthdelegate().clear();
                }
            }

            public Set keySet()
            {
/* 624*/        Object obj = mutex;
/* 624*/        JVM INSTR monitorenter ;
/* 625*/        if(keySet == null)
/* 626*/            keySet = Synchronized.access$300(_mthdelegate().keySet(), mutex);
/* 628*/        return keySet;
                Exception exception;
/* 629*/        exception;
/* 629*/        throw exception;
            }

            public Collection values()
            {
/* 634*/        Object obj = mutex;
/* 634*/        JVM INSTR monitorenter ;
/* 635*/        if(valuesCollection == null)
/* 636*/            valuesCollection = Synchronized.access$500(_mthdelegate().values(), mutex);
/* 638*/        return valuesCollection;
                Exception exception;
/* 639*/        exception;
/* 639*/        throw exception;
            }

            public Collection entries()
            {
/* 644*/        Object obj = mutex;
/* 644*/        JVM INSTR monitorenter ;
/* 645*/        if(entries == null)
/* 646*/            entries = Synchronized.access$400(_mthdelegate().entries(), mutex);
/* 648*/        return entries;
                Exception exception;
/* 649*/        exception;
/* 649*/        throw exception;
            }

            public Map asMap()
            {
/* 654*/        Object obj = mutex;
/* 654*/        JVM INSTR monitorenter ;
/* 655*/        if(asMap == null)
/* 656*/            asMap = new it>(_mthdelegate().asMap(), mutex);
/* 658*/        return asMap;
                Exception exception;
/* 659*/        exception;
/* 659*/        throw exception;
            }

            public Multiset keys()
            {
/* 664*/        Object obj = mutex;
/* 664*/        JVM INSTR monitorenter ;
/* 665*/        if(keys == null)
/* 666*/            keys = Synchronized.multiset(_mthdelegate().keys(), mutex);
/* 668*/        return keys;
                Exception exception;
/* 669*/        exception;
/* 669*/        throw exception;
            }

            public boolean equals(Object obj)
            {
/* 673*/        if(obj == this)
/* 674*/            return true;
/* 676*/        Object obj1 = mutex;
/* 676*/        JVM INSTR monitorenter ;
/* 677*/        return _mthdelegate().equals(obj);
/* 678*/        obj;
/* 678*/        throw obj;
            }

            public int hashCode()
            {
/* 682*/        Object obj = mutex;
/* 682*/        JVM INSTR monitorenter ;
/* 683*/        return _mthdelegate().hashCode();
                Exception exception;
/* 684*/        exception;
/* 684*/        throw exception;
            }

            volatile Object _mthdelegate()
            {
/* 514*/        return _mthdelegate();
            }

            transient Set keySet;
            transient Collection valuesCollection;
            transient Collection entries;
            transient Map asMap;
            transient Multiset keys;

            (Multimap multimap, Object obj)
            {
/* 528*/        super(multimap, obj);
            }
}
