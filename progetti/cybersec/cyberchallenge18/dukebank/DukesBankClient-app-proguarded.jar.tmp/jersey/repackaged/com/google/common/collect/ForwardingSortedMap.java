// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingSortedMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingMap

public abstract class ForwardingSortedMap extends ForwardingMap
    implements SortedMap
{

            protected ForwardingSortedMap()
            {
            }

            protected abstract SortedMap _mthdelegate();

            public Comparator comparator()
            {
/*  67*/        return _mthdelegate().comparator();
            }

            public Object firstKey()
            {
/*  72*/        return _mthdelegate().firstKey();
            }

            public SortedMap headMap(Object obj)
            {
/*  77*/        return _mthdelegate().headMap(obj);
            }

            public Object lastKey()
            {
/*  82*/        return _mthdelegate().lastKey();
            }

            public SortedMap subMap(Object obj, Object obj1)
            {
/*  87*/        return _mthdelegate().subMap(obj, obj1);
            }

            public SortedMap tailMap(Object obj)
            {
/*  92*/        return _mthdelegate().tailMap(obj);
            }

            protected volatile Map _mthdelegate()
            {
/*  55*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  55*/        return _mthdelegate();
            }
}
