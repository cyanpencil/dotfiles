// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class  extends 
    implements SortedMap
{

            SortedMap _mthdelegate()
            {
/*1061*/        return (SortedMap)super.te();
            }

            public Comparator comparator()
            {
/*1065*/        Object obj = mutex;
/*1065*/        JVM INSTR monitorenter ;
/*1066*/        return _mthdelegate().comparator();
                Exception exception;
/*1067*/        exception;
/*1067*/        throw exception;
            }

            public Object firstKey()
            {
/*1071*/        Object obj = mutex;
/*1071*/        JVM INSTR monitorenter ;
/*1072*/        return _mthdelegate().firstKey();
                Exception exception;
/*1073*/        exception;
/*1073*/        throw exception;
            }

            public SortedMap headMap(Object obj)
            {
/*1077*/        Object obj1 = mutex;
/*1077*/        JVM INSTR monitorenter ;
/*1078*/        return Synchronized.sortedMap(_mthdelegate().headMap(obj), mutex);
/*1079*/        obj;
/*1079*/        throw obj;
            }

            public Object lastKey()
            {
/*1083*/        Object obj = mutex;
/*1083*/        JVM INSTR monitorenter ;
/*1084*/        return _mthdelegate().lastKey();
                Exception exception;
/*1085*/        exception;
/*1085*/        throw exception;
            }

            public SortedMap subMap(Object obj, Object obj1)
            {
/*1089*/        Object obj2 = mutex;
/*1089*/        JVM INSTR monitorenter ;
/*1090*/        return Synchronized.sortedMap(_mthdelegate().subMap(obj, obj1), mutex);
/*1091*/        obj;
/*1091*/        throw obj;
            }

            public SortedMap tailMap(Object obj)
            {
/*1095*/        Object obj1 = mutex;
/*1095*/        JVM INSTR monitorenter ;
/*1096*/        return Synchronized.sortedMap(_mthdelegate().tailMap(obj), mutex);
/*1097*/        obj;
/*1097*/        throw obj;
            }

            volatile Map _mthdelegate()
            {
/*1053*/        return _mthdelegate();
            }

            volatile Object _mthdelegate()
            {
/*1053*/        return _mthdelegate();
            }

            (SortedMap sortedmap, Object obj)
            {
/*1057*/        super(sortedmap, obj);
            }
}
