// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class <init> extends <init>
    implements Collection
{

            Collection _mthdelegate()
            {
/* 114*/        return (Collection)super.gate();
            }

            public boolean add(Object obj)
            {
/* 119*/        Object obj1 = mutex;
/* 119*/        JVM INSTR monitorenter ;
/* 120*/        return _mthdelegate().add(obj);
/* 121*/        obj;
/* 121*/        throw obj;
            }

            public boolean addAll(Collection collection)
            {
/* 126*/        Object obj = mutex;
/* 126*/        JVM INSTR monitorenter ;
/* 127*/        return _mthdelegate().addAll(collection);
/* 128*/        collection;
/* 128*/        throw collection;
            }

            public void clear()
            {
/* 133*/        synchronized(mutex)
                {
/* 134*/            _mthdelegate().clear();
                }
            }

            public boolean contains(Object obj)
            {
/* 140*/        Object obj1 = mutex;
/* 140*/        JVM INSTR monitorenter ;
/* 141*/        return _mthdelegate().contains(obj);
/* 142*/        obj;
/* 142*/        throw obj;
            }

            public boolean containsAll(Collection collection)
            {
/* 147*/        Object obj = mutex;
/* 147*/        JVM INSTR monitorenter ;
/* 148*/        return _mthdelegate().containsAll(collection);
/* 149*/        collection;
/* 149*/        throw collection;
            }

            public boolean isEmpty()
            {
/* 154*/        Object obj = mutex;
/* 154*/        JVM INSTR monitorenter ;
/* 155*/        return _mthdelegate().isEmpty();
                Exception exception;
/* 156*/        exception;
/* 156*/        throw exception;
            }

            public Iterator iterator()
            {
/* 161*/        return _mthdelegate().iterator();
            }

            public boolean remove(Object obj)
            {
/* 166*/        Object obj1 = mutex;
/* 166*/        JVM INSTR monitorenter ;
/* 167*/        return _mthdelegate().remove(obj);
/* 168*/        obj;
/* 168*/        throw obj;
            }

            public boolean removeAll(Collection collection)
            {
/* 173*/        Object obj = mutex;
/* 173*/        JVM INSTR monitorenter ;
/* 174*/        return _mthdelegate().removeAll(collection);
/* 175*/        collection;
/* 175*/        throw collection;
            }

            public boolean retainAll(Collection collection)
            {
/* 180*/        Object obj = mutex;
/* 180*/        JVM INSTR monitorenter ;
/* 181*/        return _mthdelegate().retainAll(collection);
/* 182*/        collection;
/* 182*/        throw collection;
            }

            public int size()
            {
/* 187*/        Object obj = mutex;
/* 187*/        JVM INSTR monitorenter ;
/* 188*/        return _mthdelegate().size();
                Exception exception;
/* 189*/        exception;
/* 189*/        throw exception;
            }

            public Object[] toArray()
            {
/* 194*/        Object obj = mutex;
/* 194*/        JVM INSTR monitorenter ;
/* 195*/        return _mthdelegate().toArray();
                Exception exception;
/* 196*/        exception;
/* 196*/        throw exception;
            }

            public Object[] toArray(Object aobj[])
            {
/* 201*/        Object obj = mutex;
/* 201*/        JVM INSTR monitorenter ;
/* 202*/        return _mthdelegate().toArray(aobj);
/* 203*/        aobj;
/* 203*/        throw aobj;
            }

            volatile Object _mthdelegate()
            {
/* 105*/        return _mthdelegate();
            }

            private (Collection collection, Object obj)
            {
/* 109*/        super(collection, obj);
            }

            t>(Collection collection, Object obj, t> t>)
            {
/* 105*/        this(collection, obj);
            }
}
