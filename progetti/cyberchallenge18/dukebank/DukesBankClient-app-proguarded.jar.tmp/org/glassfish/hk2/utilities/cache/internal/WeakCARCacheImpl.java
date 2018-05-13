// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WeakCARCacheImpl.java

package org.glassfish.hk2.utilities.cache.internal;

import java.util.Map;
import org.glassfish.hk2.utilities.cache.*;
import org.glassfish.hk2.utilities.general.*;

public class WeakCARCacheImpl
    implements WeakCARCache
{
    static class CarValue
    {

                private final Object value;
                private volatile boolean referenceBit;




                private CarValue(Object obj)
                {
/* 312*/            referenceBit = false;
/* 315*/            value = obj;
                }

    }


            public WeakCARCacheImpl(Computable computable1, int i, boolean flag)
            {
/*  70*/        p = 0;
/*  73*/        computable = computable1;
/*  74*/        maxSize = i;
/*  76*/        t1 = GeneralUtilities.getWeakHashClock(flag);
/*  77*/        t2 = GeneralUtilities.getWeakHashClock(flag);
/*  78*/        b1 = GeneralUtilities.getWeakHashLRU(flag);
/*  79*/        b2 = GeneralUtilities.getWeakHashLRU(flag);
            }

            private Object getValueFromT(Object obj)
            {
                CarValue carvalue;
/*  83*/        if((carvalue = (CarValue)t1.get(obj)) != null)
                {
/*  86*/            carvalue.referenceBit = true;
/*  87*/            return carvalue.value;
                }
/*  90*/        if((carvalue = (CarValue)t2.get(obj)) != null)
                {
/*  93*/            carvalue.referenceBit = true;
/*  94*/            return carvalue.value;
                } else
                {
/*  97*/            return null;
                }
            }

            public Object compute(Object obj)
            {
                Object obj1;
/* 106*/        if((obj1 = getValueFromT(obj)) != null)
/* 107*/            return obj1;
/* 109*/        WeakCARCacheImpl weakcarcacheimpl = this;
/* 109*/        JVM INSTR monitorenter ;
/* 110*/        if((obj1 = getValueFromT(obj)) != null)
/* 111*/            return obj1;
/* 116*/        obj1 = computable.compute(obj);
                  goto _L1
/* 118*/        JVM INSTR dup ;
                ComputationErrorException computationerrorexception;
/* 120*/        computationerrorexception;
/* 120*/        getComputation();
/* 120*/        weakcarcacheimpl;
/* 120*/        JVM INSTR monitorexit ;
/* 120*/        return;
_L1:
                int i;
/* 123*/        if((i = getValueSize()) >= maxSize)
                {
/* 125*/            replace();
/* 127*/            boolean flag = b1.contains(obj);
/* 128*/            boolean flag2 = b2.contains(obj);
/* 129*/            if(!flag && !flag2)
/* 130*/                if(t1.size() + b1.size() >= maxSize)
/* 131*/                    b1.remove();
/* 133*/                else
/* 133*/                if(t1.size() + t2.size() + b1.size() + b2.size() >= 2 * maxSize)
/* 134*/                    b2.remove();
                }
/* 139*/        boolean flag1 = b1.contains(obj);
/* 140*/        boolean flag3 = b2.contains(obj);
/* 142*/        if(!flag1 && !flag3)
/* 143*/            t1.put(obj, new CarValue(obj1));
/* 145*/        else
/* 145*/        if(flag1)
                {
                    int j;
/* 146*/            if((j = b1.size()) == 0)
/* 147*/                j = 1;
                    int l;
/* 149*/            if((j = (l = b2.size()) / j) <= 0)
/* 152*/                j = 1;
/* 154*/            p = p + j;
/* 155*/            if(p > maxSize)
/* 155*/                p = maxSize;
/* 157*/            b1.remove(obj);
/* 158*/            t2.put(obj, new CarValue(obj1));
                } else
                {
                    int k;
/* 162*/            if((k = b2.size()) == 0)
/* 163*/                k = 1;
                    int i1;
/* 165*/            if((k = (i1 = b1.size()) / k) <= 0)
/* 168*/                k = 1;
/* 170*/            p = p - k;
/* 171*/            if(p < 0)
/* 171*/                p = 0;
/* 173*/            b2.remove(obj);
/* 174*/            t2.put(obj, new CarValue(obj1));
                }
/* 176*/        weakcarcacheimpl;
/* 176*/        JVM INSTR monitorexit ;
                  goto _L2
/* 176*/        obj;
/* 176*/        throw obj;
_L2:
/* 178*/        return obj1;
            }

            private void replace()
            {
/* 182*/        boolean flag = false;
/* 183*/        while(!flag) 
                {
                    int i;
/* 184*/            if((i = p) <= 0)
/* 185*/                i = 1;
                    java.util.Map.Entry entry;
                    CarValue carvalue1;
/* 187*/            if(t1.size() >= i)
                    {
/* 188*/                if(!((CarValue)(entry = t1.next()).getValue()).referenceBit)
                        {
/* 191*/                    flag = true;
/* 193*/                    t1.remove(entry.getKey());
/* 194*/                    b1.add(entry.getKey());
                        } else
                        {
                            CarValue carvalue;
/* 197*/                    (carvalue = (CarValue)entry.getValue()).referenceBit = false;
/* 200*/                    t1.remove(entry.getKey());
/* 201*/                    t2.put(entry.getKey(), carvalue);
                        }
                    } else
/* 205*/            if(!((CarValue)(entry = t2.next()).getValue()).referenceBit)
                    {
/* 208*/                flag = true;
/* 210*/                t2.remove(entry.getKey());
/* 211*/                b2.add(entry.getKey());
                    } else
                    {
/* 214*/                (carvalue1 = (CarValue)entry.getValue()).referenceBit = false;
                    }
                }
            }

            public synchronized int getKeySize()
            {
/* 226*/        return t1.size() + t2.size() + b1.size() + b2.size();
            }

            public synchronized int getValueSize()
            {
/* 234*/        return t1.size() + t2.size();
            }

            public synchronized void clear()
            {
/* 242*/        t1.clear();
/* 243*/        t2.clear();
/* 244*/        b1.clear();
/* 245*/        b2.clear();
/* 247*/        p = 0;
            }

            public int getMaxSize()
            {
/* 255*/        return maxSize;
            }

            public Computable getComputable()
            {
/* 263*/        return computable;
            }

            public synchronized boolean remove(Object obj)
            {
/* 271*/        if(t1.remove(obj) == null)
                {
/* 272*/            if(t2.remove(obj) == null)
                    {
/* 273*/                if(!b1.remove(obj))
/* 274*/                    return b2.remove(obj);
/* 277*/                else
/* 277*/                    return true;
                    } else
                    {
/* 280*/                return true;
                    }
                } else
                {
/* 283*/            return true;
                }
            }

            public synchronized void releaseMatching(CacheKeyFilter cachekeyfilter)
            {
/* 291*/        if(cachekeyfilter == null)
                {
/* 291*/            return;
                } else
                {
/* 293*/            b2.releaseMatching(cachekeyfilter);
/* 294*/            b1.releaseMatching(cachekeyfilter);
/* 295*/            t1.releaseMatching(cachekeyfilter);
/* 296*/            t2.releaseMatching(cachekeyfilter);
/* 297*/            return;
                }
            }

            public synchronized void clearStaleReferences()
            {
/* 304*/        t1.clearStaleReferences();
/* 305*/        t2.clearStaleReferences();
/* 306*/        b1.clearStaleReferences();
/* 307*/        b2.clearStaleReferences();
            }

            public int getT1Size()
            {
/* 325*/        return t1.size();
            }

            public int getT2Size()
            {
/* 333*/        return t2.size();
            }

            public int getB1Size()
            {
/* 341*/        return b1.size();
            }

            public int getB2Size()
            {
/* 349*/        return b2.size();
            }

            public int getP()
            {
/* 357*/        return p;
            }

            public String dumpAllLists()
            {
                StringBuffer stringbuffer;
/* 365*/        (stringbuffer = new StringBuffer((new StringBuilder("p=")).append(p).append("\nT1: ").append(t1.toString()).append("\n").toString())).append((new StringBuilder("T2: ")).append(t2.toString()).append("\n").toString());
/* 367*/        stringbuffer.append((new StringBuilder("B1: ")).append(b1.toString()).append("\n").toString());
/* 368*/        stringbuffer.append((new StringBuilder("B2: ")).append(b2.toString()).append("\n").toString());
/* 370*/        return stringbuffer.toString();
            }

            public String toString()
            {
/* 375*/        return (new StringBuilder("WeakCARCacheImpl(t1size=")).append(t1.size()).append(",t2Size=").append(t2.size()).append(",b1Size=").append(b1.size()).append(",b2Size=").append(b2.size()).append(",p=").append(p).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final Computable computable;
            private final int maxSize;
            private final WeakHashClock t1;
            private final WeakHashClock t2;
            private final WeakHashLRU b1;
            private final WeakHashLRU b2;
            private int p;
}
