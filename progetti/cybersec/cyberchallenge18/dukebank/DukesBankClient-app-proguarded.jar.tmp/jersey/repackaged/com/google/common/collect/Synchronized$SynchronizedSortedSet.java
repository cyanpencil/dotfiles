// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class  extends 
    implements SortedSet
{

            SortedSet _mthdelegate()
            {
/* 254*/        return (SortedSet)super.te();
            }

            public Comparator comparator()
            {
/* 259*/        Object obj = mutex;
/* 259*/        JVM INSTR monitorenter ;
/* 260*/        return _mthdelegate().comparator();
                Exception exception;
/* 261*/        exception;
/* 261*/        throw exception;
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/* 266*/        Object obj2 = mutex;
/* 266*/        JVM INSTR monitorenter ;
/* 267*/        return Synchronized.access$100(_mthdelegate().subSet(obj, obj1), mutex);
/* 268*/        obj;
/* 268*/        throw obj;
            }

            public SortedSet headSet(Object obj)
            {
/* 273*/        Object obj1 = mutex;
/* 273*/        JVM INSTR monitorenter ;
/* 274*/        return Synchronized.access$100(_mthdelegate().headSet(obj), mutex);
/* 275*/        obj;
/* 275*/        throw obj;
            }

            public SortedSet tailSet(Object obj)
            {
/* 280*/        Object obj1 = mutex;
/* 280*/        JVM INSTR monitorenter ;
/* 281*/        return Synchronized.access$100(_mthdelegate().tailSet(obj), mutex);
/* 282*/        obj;
/* 282*/        throw obj;
            }

            public Object first()
            {
/* 287*/        Object obj = mutex;
/* 287*/        JVM INSTR monitorenter ;
/* 288*/        return _mthdelegate().first();
                Exception exception;
/* 289*/        exception;
/* 289*/        throw exception;
            }

            public Object last()
            {
/* 294*/        Object obj = mutex;
/* 294*/        JVM INSTR monitorenter ;
/* 295*/        return _mthdelegate().last();
                Exception exception;
/* 296*/        exception;
/* 296*/        throw exception;
            }

            volatile Set _mthdelegate()
            {
/* 247*/        return _mthdelegate();
            }

            volatile Collection _mthdelegate()
            {
/* 247*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/* 247*/        return _mthdelegate();
            }

            (SortedSet sortedset, Object obj)
            {
/* 250*/        super(sortedset, obj);
            }
}
