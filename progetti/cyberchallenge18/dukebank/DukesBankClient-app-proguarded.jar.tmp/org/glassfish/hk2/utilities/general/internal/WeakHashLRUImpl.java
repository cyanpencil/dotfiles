// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakHashLRUImpl.java

package org.glassfish.hk2.utilities.general.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;
import org.glassfish.hk2.utilities.general.WeakHashLRU;

// Referenced classes of package org.glassfish.hk2.utilities.general.internal:
//            DoubleNode

public class WeakHashLRUImpl
    implements WeakHashLRU
{

            public WeakHashLRUImpl(boolean flag)
            {
/*  69*/        isWeak = flag;
/*  70*/        if(flag)
                {
/*  71*/            byKey = new WeakHashMap();
/*  72*/            byKeyNotWeak = null;
/*  72*/            return;
                } else
                {
/*  75*/            byKey = null;
/*  76*/            byKeyNotWeak = new ConcurrentHashMap();
/*  78*/            return;
                }
            }

            private DoubleNode addToHead(Object obj)
            {
/*  81*/        obj = new DoubleNode(obj, VALUE, myQueue);
/*  83*/        if(mru == null)
                {
/*  84*/            mru = ((DoubleNode) (obj));
/*  85*/            lru = ((DoubleNode) (obj));
/*  86*/            return ((DoubleNode) (obj));
                } else
                {
/*  89*/            ((DoubleNode) (obj)).setNext(mru);
/*  91*/            mru.setPrevious(((DoubleNode) (obj)));
/*  92*/            mru = ((DoubleNode) (obj));
/*  94*/            return ((DoubleNode) (obj));
                }
            }

            private Object remove(DoubleNode doublenode)
            {
/*  98*/        Object obj = doublenode.getWeakKey().get();
/* 100*/        if(doublenode.getNext() != null)
/* 101*/            doublenode.getNext().setPrevious(doublenode.getPrevious());
/* 103*/        if(doublenode.getPrevious() != null)
/* 104*/            doublenode.getPrevious().setNext(doublenode.getNext());
/* 107*/        if(doublenode == mru)
/* 108*/            mru = doublenode.getNext();
/* 110*/        if(doublenode == lru)
/* 111*/            lru = doublenode.getPrevious();
/* 114*/        doublenode.setNext(null);
/* 115*/        doublenode.setPrevious(null);
/* 117*/        return obj;
            }

            public synchronized void add(Object obj)
            {
/* 125*/        if(obj == null)
/* 126*/            throw new IllegalArgumentException("key may not be null");
                DoubleNode doublenode;
/* 130*/        if(isWeak)
                {
/* 131*/            clearStale();
/* 133*/            doublenode = (DoubleNode)byKey.get(obj);
                } else
                {
/* 136*/            doublenode = (DoubleNode)byKeyNotWeak.get(obj);
                }
/* 139*/        if(doublenode != null)
/* 140*/            remove(doublenode);
/* 143*/        doublenode = addToHead(obj);
/* 145*/        if(isWeak)
                {
/* 146*/            byKey.put(obj, doublenode);
/* 146*/            return;
                } else
                {
/* 149*/            byKeyNotWeak.put(obj, doublenode);
/* 151*/            return;
                }
            }

            public boolean contains(Object obj)
            {
/* 158*/        if(!isWeak)
/* 159*/            break MISSING_BLOCK_LABEL_31;
/* 159*/        WeakHashLRUImpl weakhashlruimpl = this;
/* 159*/        JVM INSTR monitorenter ;
/* 160*/        clearStale();
/* 162*/        return byKey.containsKey(obj);
/* 163*/        obj;
/* 163*/        throw obj;
/* 166*/        return byKeyNotWeak.containsKey(obj);
            }

            public synchronized boolean remove(Object obj)
            {
/* 174*/        if(isWeak)
/* 175*/            clearStale();
/* 178*/        return removeNoClear(obj);
            }

            private boolean removeNoClear(Object obj)
            {
/* 182*/        if(obj == null)
/* 182*/            return false;
/* 185*/        if(isWeak)
/* 186*/            obj = (DoubleNode)byKey.remove(obj);
/* 189*/        else
/* 189*/            obj = (DoubleNode)byKeyNotWeak.remove(obj);
/* 191*/        if(obj == null)
                {
/* 191*/            return false;
                } else
                {
/* 193*/            remove(((DoubleNode) (obj)));
/* 195*/            return true;
                }
            }

            public int size()
            {
/* 203*/        if(!isWeak)
/* 204*/            break MISSING_BLOCK_LABEL_30;
/* 204*/        WeakHashLRUImpl weakhashlruimpl = this;
/* 204*/        JVM INSTR monitorenter ;
/* 205*/        clearStale();
/* 207*/        return byKey.size();
                Exception exception;
/* 208*/        exception;
/* 208*/        throw exception;
/* 211*/        return byKeyNotWeak.size();
            }

            public synchronized Object remove()
            {
/* 220*/        if(lru == null)
                {
/* 243*/            clearStale();
/* 243*/            return null;
                }
/* 222*/        Object obj = lru;
_L1:
                DoubleNode doublenode;
/* 223*/        if(obj == null)
/* 224*/            break MISSING_BLOCK_LABEL_64;
/* 224*/        doublenode = ((DoubleNode) (obj)).getPrevious();
                Object obj1;
/* 226*/        if((obj1 = ((DoubleNode) (obj)).getWeakKey().get()) == null)
/* 229*/            break MISSING_BLOCK_LABEL_53;
/* 229*/        removeNoClear(obj1);
/* 231*/        obj = obj1;
/* 243*/        clearStale();
/* 243*/        return obj;
/* 234*/        remove(((DoubleNode) (obj)));
/* 237*/        obj = doublenode;
                  goto _L1
/* 243*/        clearStale();
/* 243*/        return null;
                Exception exception;
/* 243*/        exception;
/* 243*/        clearStale();
/* 243*/        throw exception;
            }

            public synchronized void releaseMatching(CacheKeyFilter cachekeyfilter)
            {
/* 252*/        if(cachekeyfilter == null)
/* 252*/            return;
/* 253*/        if(isWeak)
/* 254*/            clearStale();
/* 257*/        LinkedList linkedlist = new LinkedList();
/* 258*/        for(DoubleNode doublenode = mru; doublenode != null; doublenode = doublenode.getNext())
                {
                    Object obj;
/* 260*/            if((obj = doublenode.getWeakKey().get()) != null && cachekeyfilter.matches(obj))
/* 262*/                linkedlist.add(obj);
                }

/* 268*/        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext(); removeNoClear(cachekeyfilter))
/* 268*/            cachekeyfilter = ((CacheKeyFilter) (iterator.next()));

            }

            public synchronized void clear()
            {
/* 278*/        if(isWeak)
                {
/* 279*/            clearStale();
/* 281*/            byKey.clear();
                } else
                {
/* 284*/            byKeyNotWeak.clear();
                }
/* 287*/        mru = null;
/* 288*/        lru = null;
            }

            public synchronized void clearStaleReferences()
            {
/* 296*/        clearStale();
            }

            private void clearStale()
            {
                boolean flag;
/* 300*/        for(flag = false; myQueue.poll() != null; flag = true);
/* 305*/        if(!flag)
/* 305*/            return;
                DoubleNode doublenode1;
/* 309*/        for(DoubleNode doublenode = mru; doublenode != null; doublenode = doublenode1)
                {
/* 311*/            doublenode1 = doublenode.getNext();
/* 313*/            if(doublenode.getWeakKey().get() == null)
/* 314*/                remove(doublenode);
                }

            }

            public synchronized String toString()
            {
/* 323*/        StringBuffer stringbuffer = new StringBuffer("WeakHashLRUImpl({");
/* 325*/        boolean flag = true;
/* 326*/        for(DoubleNode doublenode = mru; doublenode != null; doublenode = doublenode.getNext())
                {
                    Object obj;
/* 328*/            obj = (obj = doublenode.getWeakKey().get()) != null ? ((Object) (obj.toString())) : "null";
/* 331*/            if(flag)
                    {
/* 332*/                flag = false;
/* 334*/                stringbuffer.append(((String) (obj)));
                    } else
                    {
/* 337*/                stringbuffer.append((new StringBuilder(",")).append(((String) (obj))).toString());
                    }
                }

/* 343*/        stringbuffer.append((new StringBuilder("},")).append(System.identityHashCode(this)).append(")").toString());
/* 345*/        return stringbuffer.toString();
            }

            private static final Object VALUE = new Object();
            private final boolean isWeak;
            private final WeakHashMap byKey;
            private final ConcurrentHashMap byKeyNotWeak;
            private final ReferenceQueue myQueue = new ReferenceQueue();
            private DoubleNode mru;
            private DoubleNode lru;

}
