// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Hk2ThreadLocal.java

package org.glassfish.hk2.utilities.general;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Hk2ThreadLocal
{

            public Hk2ThreadLocal()
            {
/*  58*/        wLock = readWriteLock.writeLock();
/*  59*/        rLock = readWriteLock.readLock();
            }

            protected Object initialValue()
            {
/*  82*/        return null;
            }

            public Object get()
            {
                long l;
/*  94*/        l = Thread.currentThread().getId();
/*  96*/        rLock.lock();
                Object obj1;
/*  98*/        if(!locals.containsKey(Long.valueOf(l)))
/*  99*/            break MISSING_BLOCK_LABEL_49;
/*  99*/        obj1 = locals.get(Long.valueOf(l));
/* 103*/        rLock.unlock();
/* 103*/        return obj1;
/* 103*/        rLock.unlock();
/* 104*/        break MISSING_BLOCK_LABEL_69;
/* 103*/        l;
/* 103*/        rLock.unlock();
/* 103*/        throw l;
/* 108*/        wLock.lock();
/* 110*/        if(!locals.containsKey(Long.valueOf(l)))
/* 111*/            break MISSING_BLOCK_LABEL_111;
/* 111*/        obj1 = locals.get(Long.valueOf(l));
/* 120*/        wLock.unlock();
/* 120*/        return obj1;
                Object obj;
/* 114*/        Object obj2 = initialValue();
/* 115*/        locals.put(Long.valueOf(l), obj2);
/* 117*/        obj = obj2;
/* 120*/        wLock.unlock();
/* 120*/        return obj;
                Exception exception;
/* 120*/        exception;
/* 120*/        wLock.unlock();
/* 120*/        throw exception;
            }

            public void set(Object obj)
            {
                long l;
/* 135*/        l = Thread.currentThread().getId();
/* 137*/        wLock.lock();
/* 139*/        locals.put(Long.valueOf(l), obj);
/* 142*/        wLock.unlock();
/* 143*/        return;
/* 142*/        obj;
/* 142*/        wLock.unlock();
/* 142*/        throw obj;
            }

            public void remove()
            {
                long l;
/* 157*/        l = Thread.currentThread().getId();
/* 159*/        wLock.lock();
/* 161*/        locals.remove(Long.valueOf(l));
/* 164*/        wLock.unlock();
/* 165*/        return;
                Exception exception;
/* 164*/        exception;
/* 164*/        wLock.unlock();
/* 164*/        throw exception;
            }

            public void removeAll()
            {
/* 179*/        wLock.lock();
/* 181*/        locals.clear();
/* 184*/        wLock.unlock();
/* 185*/        return;
                Exception exception;
/* 184*/        exception;
/* 184*/        wLock.unlock();
/* 184*/        throw exception;
            }

            private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
            private final java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock wLock;
            private final java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock rLock;
            private final HashMap locals = new HashMap();
}
