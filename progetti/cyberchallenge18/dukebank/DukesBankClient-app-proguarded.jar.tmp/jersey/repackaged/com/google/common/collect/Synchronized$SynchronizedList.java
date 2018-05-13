// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class ction extends ction
    implements List
{

            List _mthdelegate()
            {
/* 315*/        return (List)super._mthdelegate();
            }

            public void add(int i, Object obj)
            {
/* 320*/        synchronized(mutex)
                {
/* 321*/            _mthdelegate().add(i, obj);
                }
            }

            public boolean addAll(int i, Collection collection)
            {
/* 327*/        Object obj = mutex;
/* 327*/        JVM INSTR monitorenter ;
/* 328*/        return _mthdelegate().addAll(i, collection);
/* 329*/        i;
/* 329*/        throw i;
            }

            public Object get(int i)
            {
/* 334*/        Object obj = mutex;
/* 334*/        JVM INSTR monitorenter ;
/* 335*/        return _mthdelegate().get(i);
/* 336*/        i;
/* 336*/        throw i;
            }

            public int indexOf(Object obj)
            {
/* 341*/        Object obj1 = mutex;
/* 341*/        JVM INSTR monitorenter ;
/* 342*/        return _mthdelegate().indexOf(obj);
/* 343*/        obj;
/* 343*/        throw obj;
            }

            public int lastIndexOf(Object obj)
            {
/* 348*/        Object obj1 = mutex;
/* 348*/        JVM INSTR monitorenter ;
/* 349*/        return _mthdelegate().lastIndexOf(obj);
/* 350*/        obj;
/* 350*/        throw obj;
            }

            public ListIterator listIterator()
            {
/* 355*/        return _mthdelegate().listIterator();
            }

            public ListIterator listIterator(int i)
            {
/* 360*/        return _mthdelegate().listIterator(i);
            }

            public Object remove(int i)
            {
/* 365*/        Object obj = mutex;
/* 365*/        JVM INSTR monitorenter ;
/* 366*/        return _mthdelegate().remove(i);
/* 367*/        i;
/* 367*/        throw i;
            }

            public Object set(int i, Object obj)
            {
/* 372*/        Object obj1 = mutex;
/* 372*/        JVM INSTR monitorenter ;
/* 373*/        return _mthdelegate().set(i, obj);
/* 374*/        i;
/* 374*/        throw i;
            }

            public List subList(int i, int j)
            {
/* 379*/        Object obj = mutex;
/* 379*/        JVM INSTR monitorenter ;
/* 380*/        return Synchronized.access$200(_mthdelegate().subList(i, j), mutex);
/* 381*/        i;
/* 381*/        throw i;
            }

            public boolean equals(Object obj)
            {
/* 385*/        if(obj == this)
/* 386*/            return true;
/* 388*/        Object obj1 = mutex;
/* 388*/        JVM INSTR monitorenter ;
/* 389*/        return _mthdelegate().equals(obj);
/* 390*/        obj;
/* 390*/        throw obj;
            }

            public int hashCode()
            {
/* 394*/        Object obj = mutex;
/* 394*/        JVM INSTR monitorenter ;
/* 395*/        return _mthdelegate().hashCode();
                Exception exception;
/* 396*/        exception;
/* 396*/        throw exception;
            }

            volatile Collection _mthdelegate()
            {
/* 308*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 308*/        return _mthdelegate();
            }

            ction(List list, Object obj)
            {
/* 311*/        super(list, obj, null);
            }
}
