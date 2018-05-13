// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Set;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Multiset, Synchronized

static class n extends n
    implements Multiset
{

            Multiset _mthdelegate()
            {
/* 429*/        return (Multiset)super._mthdelegate();
            }

            public int count(Object obj)
            {
/* 434*/        Object obj1 = mutex;
/* 434*/        JVM INSTR monitorenter ;
/* 435*/        return _mthdelegate().count(obj);
/* 436*/        obj;
/* 436*/        throw obj;
            }

            public int add(Object obj, int i)
            {
/* 441*/        Object obj1 = mutex;
/* 441*/        JVM INSTR monitorenter ;
/* 442*/        return _mthdelegate().add(obj, i);
/* 443*/        obj;
/* 443*/        throw obj;
            }

            public int remove(Object obj, int i)
            {
/* 448*/        Object obj1 = mutex;
/* 448*/        JVM INSTR monitorenter ;
/* 449*/        return _mthdelegate().remove(obj, i);
/* 450*/        obj;
/* 450*/        throw obj;
            }

            public int setCount(Object obj, int i)
            {
/* 455*/        Object obj1 = mutex;
/* 455*/        JVM INSTR monitorenter ;
/* 456*/        return _mthdelegate().setCount(obj, i);
/* 457*/        obj;
/* 457*/        throw obj;
            }

            public boolean setCount(Object obj, int i, int j)
            {
/* 462*/        Object obj1 = mutex;
/* 462*/        JVM INSTR monitorenter ;
/* 463*/        return _mthdelegate().setCount(obj, i, j);
/* 464*/        obj;
/* 464*/        throw obj;
            }

            public Set elementSet()
            {
/* 469*/        Object obj = mutex;
/* 469*/        JVM INSTR monitorenter ;
/* 470*/        if(elementSet == null)
/* 471*/            elementSet = Synchronized.access$300(_mthdelegate().elementSet(), mutex);
/* 473*/        return elementSet;
                Exception exception;
/* 474*/        exception;
/* 474*/        throw exception;
            }

            public Set entrySet()
            {
/* 479*/        Object obj = mutex;
/* 479*/        JVM INSTR monitorenter ;
/* 480*/        if(entrySet == null)
/* 481*/            entrySet = Synchronized.access$300(_mthdelegate().entrySet(), mutex);
/* 483*/        return entrySet;
                Exception exception;
/* 484*/        exception;
/* 484*/        throw exception;
            }

            public boolean equals(Object obj)
            {
/* 488*/        if(obj == this)
/* 489*/            return true;
/* 491*/        Object obj1 = mutex;
/* 491*/        JVM INSTR monitorenter ;
/* 492*/        return _mthdelegate().equals(obj);
/* 493*/        obj;
/* 493*/        throw obj;
            }

            public int hashCode()
            {
/* 497*/        Object obj = mutex;
/* 497*/        JVM INSTR monitorenter ;
/* 498*/        return _mthdelegate().hashCode();
                Exception exception;
/* 499*/        exception;
/* 499*/        throw exception;
            }

            volatile Collection _mthdelegate()
            {
/* 419*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 419*/        return _mthdelegate();
            }

            transient Set elementSet;
            transient Set entrySet;

            n(Multiset multiset, Object obj)
            {
/* 425*/        super(multiset, obj, null);
            }
}
