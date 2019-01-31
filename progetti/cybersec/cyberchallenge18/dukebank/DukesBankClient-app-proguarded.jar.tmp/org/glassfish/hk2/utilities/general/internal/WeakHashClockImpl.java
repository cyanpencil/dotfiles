// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakHashClockImpl.java

package org.glassfish.hk2.utilities.general.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.glassfish.hk2.utilities.cache.CacheKeyFilter;
import org.glassfish.hk2.utilities.general.WeakHashClock;

// Referenced classes of package org.glassfish.hk2.utilities.general.internal:
//            DoubleNode

public class WeakHashClockImpl
    implements WeakHashClock
{

            public WeakHashClockImpl(boolean flag)
            {
/*  70*/        isWeak = flag;
/*  71*/        if(flag)
                {
/*  72*/            byKey = new WeakHashMap();
/*  73*/            byKeyNotWeak = null;
/*  73*/            return;
                } else
                {
/*  76*/            byKeyNotWeak = new ConcurrentHashMap();
/*  77*/            byKey = null;
/*  79*/            return;
                }
            }

            private DoubleNode addBeforeDot(Object obj, Object obj1)
            {
/*  82*/        obj = new DoubleNode(obj, obj1, myQueue);
/*  84*/        if(dot == null)
                {
/*  86*/            head = ((DoubleNode) (obj));
/*  87*/            tail = ((DoubleNode) (obj));
/*  88*/            dot = ((DoubleNode) (obj));
/*  89*/            return ((DoubleNode) (obj));
                }
/*  92*/        if(dot.getPrevious() == null)
                {
/*  94*/            dot.setPrevious(((DoubleNode) (obj)));
/*  96*/            ((DoubleNode) (obj)).setNext(dot);
/*  97*/            head = ((DoubleNode) (obj));
/*  98*/            return ((DoubleNode) (obj));
                } else
                {
/* 103*/            ((DoubleNode) (obj)).setNext(dot);
/* 104*/            ((DoubleNode) (obj)).setPrevious(dot.getPrevious());
/* 106*/            dot.getPrevious().setNext(((DoubleNode) (obj)));
/* 107*/            dot.setPrevious(((DoubleNode) (obj)));
/* 109*/            return ((DoubleNode) (obj));
                }
            }

            private void removeFromDLL(DoubleNode doublenode)
            {
/* 113*/        if(doublenode.getPrevious() != null)
/* 114*/            doublenode.getPrevious().setNext(doublenode.getNext());
/* 116*/        if(doublenode.getNext() != null)
/* 117*/            doublenode.getNext().setPrevious(doublenode.getPrevious());
/* 120*/        if(doublenode == head)
/* 121*/            head = doublenode.getNext();
/* 123*/        if(doublenode == tail)
/* 124*/            tail = doublenode.getPrevious();
/* 127*/        if(doublenode == dot)
                {
/* 128*/            dot = doublenode.getNext();
/* 129*/            if(dot == null)
/* 129*/                dot = head;
                }
/* 132*/        doublenode.setNext(null);
/* 133*/        doublenode.setPrevious(null);
            }

            public void put(Object obj, Object obj1)
            {
/* 141*/        if(obj == null || obj1 == null)
/* 141*/            throw new IllegalArgumentException((new StringBuilder("key ")).append(obj).append(" or value ").append(obj1).append(" is null").toString());
/* 143*/        synchronized(this)
                {
/* 144*/            if(isWeak)
/* 145*/                removeStale();
/* 148*/            obj1 = addBeforeDot(obj, obj1);
/* 150*/            if(isWeak)
/* 151*/                byKey.put(obj, obj1);
/* 154*/            else
/* 154*/                byKeyNotWeak.put(obj, obj1);
                }
            }

            public Object get(Object obj)
            {
/* 164*/        if(obj == null)
/* 164*/            return null;
/* 167*/        if(isWeak)
/* 168*/            synchronized(this)
                    {
/* 169*/                removeStale();
/* 171*/                obj = (DoubleNode)byKey.get(obj);
                    }
/* 175*/        else
/* 175*/            obj = (DoubleNode)byKeyNotWeak.get(obj);
/* 178*/        if(obj == null)
/* 178*/            return null;
/* 180*/        else
/* 180*/            return ((DoubleNode) (obj)).getValue();
            }

            public Object remove(Object obj)
            {
/* 188*/        if(obj == null)
/* 188*/            return null;
/* 190*/        WeakHashClockImpl weakhashclockimpl = this;
/* 190*/        JVM INSTR monitorenter ;
/* 192*/        if(isWeak)
                {
/* 193*/            removeStale();
/* 195*/            obj = (DoubleNode)byKey.remove(obj);
                } else
                {
/* 198*/            obj = (DoubleNode)byKeyNotWeak.remove(obj);
                }
/* 201*/        if(obj == null)
/* 201*/            return null;
/* 203*/        removeFromDLL(((DoubleNode) (obj)));
/* 205*/        ((DoubleNode) (obj)).getValue();
/* 205*/        weakhashclockimpl;
/* 205*/        JVM INSTR monitorexit ;
/* 205*/        return;
/* 206*/        obj;
/* 206*/        throw obj;
            }

            public synchronized void releaseMatching(CacheKeyFilter cachekeyfilter)
            {
/* 214*/        if(cachekeyfilter == null)
/* 214*/            return;
/* 216*/        if(isWeak)
/* 217*/            removeStale();
/* 220*/        LinkedList linkedlist = new LinkedList();
/* 221*/        for(DoubleNode doublenode = head; doublenode != null; doublenode = doublenode.getNext())
                {
                    Object obj;
/* 223*/            if((obj = doublenode.getWeakKey().get()) != null && cachekeyfilter.matches(obj))
/* 225*/                linkedlist.add(obj);
                }

/* 231*/        for(Iterator iterator = linkedlist.iterator(); iterator.hasNext(); remove(cachekeyfilter))
/* 231*/            cachekeyfilter = ((CacheKeyFilter) (iterator.next()));

            }

            public int size()
            {
/* 241*/        if(!isWeak)
/* 242*/            break MISSING_BLOCK_LABEL_30;
/* 242*/        WeakHashClockImpl weakhashclockimpl = this;
/* 242*/        JVM INSTR monitorenter ;
/* 243*/        removeStale();
/* 245*/        return byKey.size();
                Exception exception;
/* 246*/        exception;
/* 246*/        throw exception;
/* 249*/        return byKeyNotWeak.size();
            }

            private DoubleNode moveDot()
            {
/* 253*/        if(dot == null)
/* 253*/            return null;
/* 255*/        DoubleNode doublenode = dot;
/* 256*/        dot = doublenode.getNext();
/* 257*/        if(dot == null)
/* 257*/            dot = head;
/* 259*/        return doublenode;
            }

            private DoubleNode moveDotNoWeak()
            {
                DoubleNode doublenode;
                DoubleNode doublenode1;
/* 263*/        if((doublenode1 = doublenode = moveDot()) == null)
/* 265*/            return null;
                Object obj;
/* 268*/        while((obj = doublenode1.getWeakKey().get()) == null) 
                {
/* 269*/            if((doublenode1 = moveDot()) == null)
/* 270*/                return null;
/* 271*/            if(doublenode1 == doublenode)
/* 271*/                return null;
                }
/* 274*/        doublenode1.setHardenedKey(obj);
/* 276*/        return doublenode1;
            }

            public synchronized java.util.Map.Entry next()
            {
                DoubleNode doublenode;
/* 284*/        if((doublenode = moveDotNoWeak()) == null)
/* 285*/            return null;
                final Object key;
/* 287*/        key = doublenode.getHardenedKey();
/* 288*/        final Object value = doublenode.getValue();
/* 290*/        key = new java.util.Map.Entry() {

                    public Object getKey()
                    {
/* 294*/                return key;
                    }

                    public Object getValue()
                    {
/* 299*/                return value;
                    }

                    public Object setValue(Object obj)
                    {
/* 304*/                throw new AssertionError("not implemented");
                    }

                    final Object val$key;
                    final Object val$value;
                    final WeakHashClockImpl this$0;

                    
                    {
/* 290*/                this$0 = WeakHashClockImpl.this;
/* 290*/                key = obj;
/* 290*/                value = obj1;
/* 290*/                super();
                    }
        };
/* 310*/        doublenode.setHardenedKey(null);
/* 312*/        removeStale();
/* 312*/        return ((java.util.Map.Entry) (key));
                Exception exception;
/* 310*/        exception;
/* 310*/        doublenode.setHardenedKey(null);
/* 312*/        removeStale();
/* 312*/        throw exception;
            }

            public synchronized void clear()
            {
/* 321*/        if(isWeak)
/* 322*/            byKey.clear();
/* 325*/        else
/* 325*/            byKeyNotWeak.clear();
/* 328*/        head = tail = dot = null;
            }

            public synchronized void clearStaleReferences()
            {
/* 336*/        removeStale();
            }

            private void removeStale()
            {
                boolean flag;
/* 340*/        for(flag = false; myQueue.poll() != null; flag = true);
/* 345*/        if(!flag)
/* 345*/            return;
                DoubleNode doublenode1;
/* 347*/        for(DoubleNode doublenode = head; doublenode != null; doublenode = doublenode1)
                {
/* 349*/            doublenode1 = doublenode.getNext();
/* 351*/            if(doublenode.getWeakKey().get() == null)
/* 352*/                removeFromDLL(doublenode);
                }

            }

            public boolean hasWeakKeys()
            {
/* 364*/        return isWeak;
            }

            public synchronized String toString()
            {
/* 369*/        StringBuffer stringbuffer = new StringBuffer("WeakHashClockImpl({");
/* 371*/        boolean flag = true;
                DoubleNode doublenode;
/* 372*/        if((doublenode = dot) != null)
/* 375*/            do
                    {
                        Object obj;
/* 375*/                obj = (obj = doublenode.getWeakKey().get()) != null ? ((Object) (obj.toString())) : "null";
/* 378*/                if(flag)
                        {
/* 379*/                    flag = false;
/* 381*/                    stringbuffer.append(((String) (obj)));
                        } else
                        {
/* 384*/                    stringbuffer.append((new StringBuilder(",")).append(((String) (obj))).toString());
                        }
/* 387*/                if((doublenode = doublenode.getNext()) == null)
/* 388*/                    doublenode = head;
                    } while(doublenode != dot);
/* 392*/        stringbuffer.append((new StringBuilder("},")).append(System.identityHashCode(this)).append(")").toString());
/* 394*/        return stringbuffer.toString();
            }

            private final boolean isWeak;
            private final ConcurrentHashMap byKeyNotWeak;
            private final WeakHashMap byKey;
            private final ReferenceQueue myQueue = new ReferenceQueue();
            private DoubleNode head;
            private DoubleNode tail;
            private DoubleNode dot;
}
