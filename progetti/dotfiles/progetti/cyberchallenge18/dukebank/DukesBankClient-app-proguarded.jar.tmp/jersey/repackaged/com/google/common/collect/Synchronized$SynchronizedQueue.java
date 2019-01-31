// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Queue;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class tion extends tion
    implements Queue
{

            Queue _mthdelegate()
            {
/*1582*/        return (Queue)super._mthdelegate();
            }

            public Object element()
            {
/*1587*/        Object obj = mutex;
/*1587*/        JVM INSTR monitorenter ;
/*1588*/        return _mthdelegate().element();
                Exception exception;
/*1589*/        exception;
/*1589*/        throw exception;
            }

            public boolean offer(Object obj)
            {
/*1594*/        Object obj1 = mutex;
/*1594*/        JVM INSTR monitorenter ;
/*1595*/        return _mthdelegate().offer(obj);
/*1596*/        obj;
/*1596*/        throw obj;
            }

            public Object peek()
            {
/*1601*/        Object obj = mutex;
/*1601*/        JVM INSTR monitorenter ;
/*1602*/        return _mthdelegate().peek();
                Exception exception;
/*1603*/        exception;
/*1603*/        throw exception;
            }

            public Object poll()
            {
/*1608*/        Object obj = mutex;
/*1608*/        JVM INSTR monitorenter ;
/*1609*/        return _mthdelegate().poll();
                Exception exception;
/*1610*/        exception;
/*1610*/        throw exception;
            }

            public Object remove()
            {
/*1615*/        Object obj = mutex;
/*1615*/        JVM INSTR monitorenter ;
/*1616*/        return _mthdelegate().remove();
                Exception exception;
/*1617*/        exception;
/*1617*/        throw exception;
            }

            volatile Collection _mthdelegate()
            {
/*1574*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/*1574*/        return _mthdelegate();
            }

            tion(Queue queue, Object obj)
            {
/*1578*/        super(queue, obj, null);
            }
}
