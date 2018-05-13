// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConcurrentHashMapV8.java

package org.glassfish.jersey.internal.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            ConcurrentHashMapV8

static final class iew extends iew
    implements Serializable, Collection
{

            public final boolean contains(Object obj)
            {
/*3238*/        return map.containsValue(obj);
            }

            public final boolean remove(Object obj)
            {
/*3242*/label0:
                {
/*3242*/            if(obj == null)
/*3243*/                break label0;
/*3243*/            Iterator iterator1 = iterator();
/*3243*/            do
/*3243*/                if(!iterator1.hasNext())
/*3244*/                    break label0;
/*3244*/            while(!obj.equals(iterator1.next()));
/*3245*/            iterator1.remove();
/*3246*/            return true;
                }
/*3250*/        return false;
            }

            public final Iterator iterator()
            {
                ConcurrentHashMapV8 concurrenthashmapv8;
                iterator aiterator[];
/*3254*/        int i = (aiterator = (concurrenthashmapv8 = map).table) != null ? aiterator.length : 0;
/*3257*/        return new or(aiterator, i, 0, i, concurrenthashmapv8);
            }

            public final boolean add(Object obj)
            {
/*3261*/        throw new UnsupportedOperationException();
            }

            public final boolean addAll(Collection collection)
            {
/*3265*/        throw new UnsupportedOperationException();
            }

            private static final long serialVersionUID = 0x1f364c905893293dL;

            or(ConcurrentHashMapV8 concurrenthashmapv8)
            {
/*3234*/        super(concurrenthashmapv8);
            }
}
