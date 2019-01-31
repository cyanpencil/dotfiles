// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static final class <init> extends <init>
    implements Deque
{

            final Deque _mthdelegate()
            {
/*1637*/        return (Deque)super._mthdelegate();
            }

            public final void addFirst(Object obj)
            {
/*1642*/        synchronized(mutex)
                {
/*1643*/            _mthdelegate().addFirst(obj);
                }
            }

            public final void addLast(Object obj)
            {
/*1649*/        synchronized(mutex)
                {
/*1650*/            _mthdelegate().addLast(obj);
                }
            }

            public final boolean offerFirst(Object obj)
            {
/*1656*/        Object obj1 = mutex;
/*1656*/        JVM INSTR monitorenter ;
/*1657*/        return _mthdelegate().offerFirst(obj);
/*1658*/        obj;
/*1658*/        throw obj;
            }

            public final boolean offerLast(Object obj)
            {
/*1663*/        Object obj1 = mutex;
/*1663*/        JVM INSTR monitorenter ;
/*1664*/        return _mthdelegate().offerLast(obj);
/*1665*/        obj;
/*1665*/        throw obj;
            }

            public final Object removeFirst()
            {
/*1670*/        Object obj = mutex;
/*1670*/        JVM INSTR monitorenter ;
/*1671*/        return _mthdelegate().removeFirst();
                Exception exception;
/*1672*/        exception;
/*1672*/        throw exception;
            }

            public final Object removeLast()
            {
/*1677*/        Object obj = mutex;
/*1677*/        JVM INSTR monitorenter ;
/*1678*/        return _mthdelegate().removeLast();
                Exception exception;
/*1679*/        exception;
/*1679*/        throw exception;
            }

            public final Object pollFirst()
            {
/*1684*/        Object obj = mutex;
/*1684*/        JVM INSTR monitorenter ;
/*1685*/        return _mthdelegate().pollFirst();
                Exception exception;
/*1686*/        exception;
/*1686*/        throw exception;
            }

            public final Object pollLast()
            {
/*1691*/        Object obj = mutex;
/*1691*/        JVM INSTR monitorenter ;
/*1692*/        return _mthdelegate().pollLast();
                Exception exception;
/*1693*/        exception;
/*1693*/        throw exception;
            }

            public final Object getFirst()
            {
/*1698*/        Object obj = mutex;
/*1698*/        JVM INSTR monitorenter ;
/*1699*/        return _mthdelegate().getFirst();
                Exception exception;
/*1700*/        exception;
/*1700*/        throw exception;
            }

            public final Object getLast()
            {
/*1705*/        Object obj = mutex;
/*1705*/        JVM INSTR monitorenter ;
/*1706*/        return _mthdelegate().getLast();
                Exception exception;
/*1707*/        exception;
/*1707*/        throw exception;
            }

            public final Object peekFirst()
            {
/*1712*/        Object obj = mutex;
/*1712*/        JVM INSTR monitorenter ;
/*1713*/        return _mthdelegate().peekFirst();
                Exception exception;
/*1714*/        exception;
/*1714*/        throw exception;
            }

            public final Object peekLast()
            {
/*1719*/        Object obj = mutex;
/*1719*/        JVM INSTR monitorenter ;
/*1720*/        return _mthdelegate().peekLast();
                Exception exception;
/*1721*/        exception;
/*1721*/        throw exception;
            }

            public final boolean removeFirstOccurrence(Object obj)
            {
/*1726*/        Object obj1 = mutex;
/*1726*/        JVM INSTR monitorenter ;
/*1727*/        return _mthdelegate().removeFirstOccurrence(obj);
/*1728*/        obj;
/*1728*/        throw obj;
            }

            public final boolean removeLastOccurrence(Object obj)
            {
/*1733*/        Object obj1 = mutex;
/*1733*/        JVM INSTR monitorenter ;
/*1734*/        return _mthdelegate().removeLastOccurrence(obj);
/*1735*/        obj;
/*1735*/        throw obj;
            }

            public final void push(Object obj)
            {
/*1740*/        synchronized(mutex)
                {
/*1741*/            _mthdelegate().push(obj);
                }
            }

            public final Object pop()
            {
/*1747*/        Object obj = mutex;
/*1747*/        JVM INSTR monitorenter ;
/*1748*/        return _mthdelegate().pop();
                Exception exception;
/*1749*/        exception;
/*1749*/        throw exception;
            }

            public final Iterator descendingIterator()
            {
/*1754*/        Object obj = mutex;
/*1754*/        JVM INSTR monitorenter ;
/*1755*/        return _mthdelegate().descendingIterator();
                Exception exception;
/*1756*/        exception;
/*1756*/        throw exception;
            }

            final volatile Queue _mthdelegate()
            {
/*1628*/        return _mthdelegate();
            }

            final volatile Collection _mthdelegate()
            {
/*1628*/        return _mthdelegate();
            }

            final volatile Object _mthdelegate()
            {
/*1628*/        return _mthdelegate();
            }

            (Deque deque, Object obj)
            {
/*1633*/        super(deque, obj);
            }
}
