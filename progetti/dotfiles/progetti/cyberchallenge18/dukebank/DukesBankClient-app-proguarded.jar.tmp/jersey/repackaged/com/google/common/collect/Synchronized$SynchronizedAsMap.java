// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Synchronized.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Synchronized

static class nit> extends nit>
{

            public Collection get(Object obj)
            {
/*1166*/        Object obj1 = mutex;
/*1166*/        JVM INSTR monitorenter ;
/*1167*/        return (obj = (Collection)super.t(obj)) != null ? Synchronized.access$400(((Collection) (obj)), mutex) : null;
/*1170*/        obj;
/*1170*/        throw obj;
            }

            public Set entrySet()
            {
/*1174*/        Object obj = mutex;
/*1174*/        JVM INSTR monitorenter ;
/*1175*/        if(asMapEntrySet == null)
/*1176*/            asMapEntrySet = new ntries(_mthdelegate().entrySet(), mutex);
/*1179*/        return asMapEntrySet;
                Exception exception;
/*1180*/        exception;
/*1180*/        throw exception;
            }

            public Collection values()
            {
/*1184*/        Object obj = mutex;
/*1184*/        JVM INSTR monitorenter ;
/*1185*/        if(asMapValues == null)
/*1186*/            asMapValues = new alues(_mthdelegate().values(), mutex);
/*1189*/        return asMapValues;
                Exception exception;
/*1190*/        exception;
/*1190*/        throw exception;
            }

            public boolean containsValue(Object obj)
            {
/*1195*/        return values().contains(obj);
            }

            public volatile Object get(Object obj)
            {
/*1156*/        return get(obj);
            }

            transient Set asMapEntrySet;
            transient Collection asMapValues;

            alues(Map map, Object obj)
            {
/*1162*/        super(map, obj);
            }
}
