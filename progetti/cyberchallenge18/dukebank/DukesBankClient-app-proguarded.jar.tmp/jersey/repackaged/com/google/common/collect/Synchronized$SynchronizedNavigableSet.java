// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class it> extends it>
    implements NavigableSet
{

            NavigableSet _mthdelegate()
            {
/*1233*/        return (NavigableSet)super.egate();
            }

            public Object ceiling(Object obj)
            {
/*1237*/        Object obj1 = mutex;
/*1237*/        JVM INSTR monitorenter ;
/*1238*/        return _mthdelegate().ceiling(obj);
/*1239*/        obj;
/*1239*/        throw obj;
            }

            public Iterator descendingIterator()
            {
/*1243*/        return _mthdelegate().descendingIterator();
            }

            public NavigableSet descendingSet()
            {
/*1249*/        Object obj = mutex;
/*1249*/        JVM INSTR monitorenter ;
                NavigableSet navigableset;
/*1250*/        if(descendingSet != null)
/*1251*/            break MISSING_BLOCK_LABEL_40;
/*1251*/        navigableset = Synchronized.navigableSet(_mthdelegate().descendingSet(), mutex);
/*1253*/        descendingSet = navigableset;
/*1254*/        return navigableset;
/*1256*/        descendingSet;
/*1256*/        obj;
/*1256*/        JVM INSTR monitorexit ;
/*1256*/        return;
                Exception exception;
/*1257*/        exception;
/*1257*/        throw exception;
            }

            public Object floor(Object obj)
            {
/*1261*/        Object obj1 = mutex;
/*1261*/        JVM INSTR monitorenter ;
/*1262*/        return _mthdelegate().floor(obj);
/*1263*/        obj;
/*1263*/        throw obj;
            }

            public NavigableSet headSet(Object obj, boolean flag)
            {
/*1267*/        Object obj1 = mutex;
/*1267*/        JVM INSTR monitorenter ;
/*1268*/        return Synchronized.navigableSet(_mthdelegate().headSet(obj, flag), mutex);
/*1270*/        obj;
/*1270*/        throw obj;
            }

            public Object higher(Object obj)
            {
/*1274*/        Object obj1 = mutex;
/*1274*/        JVM INSTR monitorenter ;
/*1275*/        return _mthdelegate().higher(obj);
/*1276*/        obj;
/*1276*/        throw obj;
            }

            public Object lower(Object obj)
            {
/*1280*/        Object obj1 = mutex;
/*1280*/        JVM INSTR monitorenter ;
/*1281*/        return _mthdelegate().lower(obj);
/*1282*/        obj;
/*1282*/        throw obj;
            }

            public Object pollFirst()
            {
/*1286*/        Object obj = mutex;
/*1286*/        JVM INSTR monitorenter ;
/*1287*/        return _mthdelegate().pollFirst();
                Exception exception;
/*1288*/        exception;
/*1288*/        throw exception;
            }

            public Object pollLast()
            {
/*1292*/        Object obj = mutex;
/*1292*/        JVM INSTR monitorenter ;
/*1293*/        return _mthdelegate().pollLast();
                Exception exception;
/*1294*/        exception;
/*1294*/        throw exception;
            }

            public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/*1299*/        Object obj2 = mutex;
/*1299*/        JVM INSTR monitorenter ;
/*1300*/        return Synchronized.navigableSet(_mthdelegate().subSet(obj, flag, obj1, flag1), mutex);
/*1302*/        obj;
/*1302*/        throw obj;
            }

            public NavigableSet tailSet(Object obj, boolean flag)
            {
/*1306*/        Object obj1 = mutex;
/*1306*/        JVM INSTR monitorenter ;
/*1307*/        return Synchronized.navigableSet(_mthdelegate().tailSet(obj, flag), mutex);
/*1309*/        obj;
/*1309*/        throw obj;
            }

            public SortedSet headSet(Object obj)
            {
/*1313*/        return headSet(obj, false);
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/*1317*/        return subSet(obj, true, obj1, false);
            }

            public SortedSet tailSet(Object obj)
            {
/*1321*/        return tailSet(obj, true);
            }

            volatile SortedSet _mthdelegate()
            {
/*1224*/        return _mthdelegate();
            }

            volatile Set _mthdelegate()
            {
/*1224*/        return _mthdelegate();
            }

            volatile Collection _mthdelegate()
            {
/*1224*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/*1224*/        return _mthdelegate();
            }

            transient NavigableSet descendingSet;

            (NavigableSet navigableset, Object obj)
            {
/*1229*/        super(navigableset, obj);
            }
}
