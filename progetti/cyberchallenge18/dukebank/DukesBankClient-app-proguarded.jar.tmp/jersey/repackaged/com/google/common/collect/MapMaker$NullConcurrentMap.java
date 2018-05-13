// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMaker.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMaker

static class removalCause extends AbstractMap
    implements Serializable, ConcurrentMap
{

            public boolean containsKey(Object obj)
            {
/* 767*/        return false;
            }

            public boolean containsValue(Object obj)
            {
/* 772*/        return false;
            }

            public Object get(Object obj)
            {
/* 777*/        return null;
            }

            void notifyRemoval(Object obj, Object obj1)
            {
/* 781*/        obj = new n(obj, obj1, removalCause);
/* 783*/        removalListener.Removal(((n) (obj)));
            }

            public Object put(Object obj, Object obj1)
            {
/* 788*/        Preconditions.checkNotNull(obj);
/* 789*/        Preconditions.checkNotNull(obj1);
/* 790*/        notifyRemoval(obj, obj1);
/* 791*/        return null;
            }

            public Object putIfAbsent(Object obj, Object obj1)
            {
/* 796*/        return put(obj, obj1);
            }

            public Object remove(Object obj)
            {
/* 801*/        return null;
            }

            public boolean remove(Object obj, Object obj1)
            {
/* 806*/        return false;
            }

            public Object replace(Object obj, Object obj1)
            {
/* 811*/        Preconditions.checkNotNull(obj);
/* 812*/        Preconditions.checkNotNull(obj1);
/* 813*/        return null;
            }

            public boolean replace(Object obj, Object obj1, Object obj2)
            {
/* 818*/        Preconditions.checkNotNull(obj);
/* 819*/        Preconditions.checkNotNull(obj2);
/* 820*/        return false;
            }

            public Set entrySet()
            {
/* 825*/        return Collections.emptySet();
            }

            private final put removalListener;
            private final put removalCause;

            n(MapMaker mapmaker)
            {
/* 759*/        removalListener = mapmaker.getRemovalListener();
/* 760*/        removalCause = mapmaker.nullRemovalCause;
            }
}
