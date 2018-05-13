// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class ct extends ct
    implements Map
{

            Map _mthdelegate()
            {
/* 934*/        return (Map)super._mthdelegate();
            }

            public void clear()
            {
/* 939*/        synchronized(mutex)
                {
/* 940*/            _mthdelegate().clear();
                }
            }

            public boolean containsKey(Object obj)
            {
/* 946*/        Object obj1 = mutex;
/* 946*/        JVM INSTR monitorenter ;
/* 947*/        return _mthdelegate().containsKey(obj);
/* 948*/        obj;
/* 948*/        throw obj;
            }

            public boolean containsValue(Object obj)
            {
/* 953*/        Object obj1 = mutex;
/* 953*/        JVM INSTR monitorenter ;
/* 954*/        return _mthdelegate().containsValue(obj);
/* 955*/        obj;
/* 955*/        throw obj;
            }

            public Set entrySet()
            {
/* 960*/        Object obj = mutex;
/* 960*/        JVM INSTR monitorenter ;
/* 961*/        if(entrySet == null)
/* 962*/            entrySet = Synchronized.set(_mthdelegate().entrySet(), mutex);
/* 964*/        return entrySet;
                Exception exception;
/* 965*/        exception;
/* 965*/        throw exception;
            }

            public Object get(Object obj)
            {
/* 970*/        Object obj1 = mutex;
/* 970*/        JVM INSTR monitorenter ;
/* 971*/        return _mthdelegate().get(obj);
/* 972*/        obj;
/* 972*/        throw obj;
            }

            public boolean isEmpty()
            {
/* 977*/        Object obj = mutex;
/* 977*/        JVM INSTR monitorenter ;
/* 978*/        return _mthdelegate().isEmpty();
                Exception exception;
/* 979*/        exception;
/* 979*/        throw exception;
            }

            public Set keySet()
            {
/* 984*/        Object obj = mutex;
/* 984*/        JVM INSTR monitorenter ;
/* 985*/        if(keySet == null)
/* 986*/            keySet = Synchronized.set(_mthdelegate().keySet(), mutex);
/* 988*/        return keySet;
                Exception exception;
/* 989*/        exception;
/* 989*/        throw exception;
            }

            public Object put(Object obj, Object obj1)
            {
/* 994*/        Object obj2 = mutex;
/* 994*/        JVM INSTR monitorenter ;
/* 995*/        return _mthdelegate().put(obj, obj1);
/* 996*/        obj;
/* 996*/        throw obj;
            }

            public void putAll(Map map)
            {
/*1001*/        synchronized(mutex)
                {
/*1002*/            _mthdelegate().putAll(map);
                }
            }

            public Object remove(Object obj)
            {
/*1008*/        Object obj1 = mutex;
/*1008*/        JVM INSTR monitorenter ;
/*1009*/        return _mthdelegate().remove(obj);
/*1010*/        obj;
/*1010*/        throw obj;
            }

            public int size()
            {
/*1015*/        Object obj = mutex;
/*1015*/        JVM INSTR monitorenter ;
/*1016*/        return _mthdelegate().size();
                Exception exception;
/*1017*/        exception;
/*1017*/        throw exception;
            }

            public Collection values()
            {
/*1022*/        Object obj = mutex;
/*1022*/        JVM INSTR monitorenter ;
/*1023*/        if(values == null)
/*1024*/            values = Synchronized.access$500(_mthdelegate().values(), mutex);
/*1026*/        return values;
                Exception exception;
/*1027*/        exception;
/*1027*/        throw exception;
            }

            public boolean equals(Object obj)
            {
/*1031*/        if(obj == this)
/*1032*/            return true;
/*1034*/        Object obj1 = mutex;
/*1034*/        JVM INSTR monitorenter ;
/*1035*/        return _mthdelegate().equals(obj);
/*1036*/        obj;
/*1036*/        throw obj;
            }

            public int hashCode()
            {
/*1040*/        Object obj = mutex;
/*1040*/        JVM INSTR monitorenter ;
/*1041*/        return _mthdelegate().hashCode();
                Exception exception;
/*1042*/        exception;
/*1042*/        throw exception;
            }

            volatile Object _mthdelegate()
            {
/* 922*/        return _mthdelegate();
            }

            transient Set keySet;
            transient Collection values;
            transient Set entrySet;

            ct(Map map, Object obj)
            {
/* 929*/        super(map, obj);
            }
}
