// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

public static class value extends iew
    implements Serializable, Set
{

            public Object getMappedValue()
            {
/*3134*/        return value;
            }

            public boolean contains(Object obj)
            {
/*3143*/        return map.containsKey(obj);
            }

            public boolean remove(Object obj)
            {
/*3156*/        return map.remove(obj) != null;
            }

            public Iterator iterator()
            {
                map amap[];
                ConcurrentHashMapV8 concurrenthashmapv8;
/*3164*/        int i = (amap = (concurrenthashmapv8 = map).table) != null ? amap.length : 0;
/*3166*/        return new (amap, i, 0, i, concurrenthashmapv8);
            }

            public boolean add(Object obj)
            {
                Object obj1;
/*3181*/        if((obj1 = value) == null)
/*3182*/            throw new UnsupportedOperationException();
/*3183*/        return map.putVal(obj, obj1, true) == null;
            }

            public boolean addAll(Collection collection)
            {
/*3198*/        boolean flag = false;
                Object obj;
/*3200*/        if((obj = value) == null)
/*3201*/            throw new UnsupportedOperationException();
/*3202*/        collection = collection.iterator();
/*3202*/        do
                {
/*3202*/            if(!collection.hasNext())
/*3202*/                break;
/*3202*/            Object obj1 = collection.next();
/*3203*/            if(map.putVal(obj1, obj, true) == null)
/*3204*/                flag = true;
                } while(true);
/*3206*/        return flag;
            }

            public int hashCode()
            {
/*3210*/        int i = 0;
/*3211*/        for(Iterator iterator1 = iterator(); iterator1.hasNext();)
                {
/*3211*/            Object obj = iterator1.next();
/*3212*/            i += obj.hashCode();
                }

/*3213*/        return i;
            }

            public boolean equals(Object obj)
            {
/*3218*/        return (obj instanceof Set) && ((obj = (Set)obj) == this || containsAll(((Collection) (obj))) && ((Set) (obj)).containsAll(this));
            }

            public volatile ConcurrentHashMapV8 getMap()
            {
/*3116*/        return super.getMap();
            }

            private static final long serialVersionUID = 0x6499de129d87293dL;
            private final Object value;

            (ConcurrentHashMapV8 concurrenthashmapv8, Object obj)
            {
/*3122*/        super(concurrenthashmapv8);
/*3123*/        value = obj;
            }
}
