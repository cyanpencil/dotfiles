// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingNavigableSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingSortedSet

public abstract class ForwardingNavigableSet extends ForwardingSortedSet
    implements NavigableSet
{

            protected ForwardingNavigableSet()
            {
            }

            protected abstract NavigableSet _mthdelegate();

            public Object lower(Object obj)
            {
/*  58*/        return _mthdelegate().lower(obj);
            }

            public Object floor(Object obj)
            {
/*  72*/        return _mthdelegate().floor(obj);
            }

            public Object ceiling(Object obj)
            {
/*  86*/        return _mthdelegate().ceiling(obj);
            }

            public Object higher(Object obj)
            {
/* 100*/        return _mthdelegate().higher(obj);
            }

            public Object pollFirst()
            {
/* 114*/        return _mthdelegate().pollFirst();
            }

            public Object pollLast()
            {
/* 128*/        return _mthdelegate().pollLast();
            }

            public NavigableSet descendingSet()
            {
/* 150*/        return _mthdelegate().descendingSet();
            }

            public Iterator descendingIterator()
            {
/* 172*/        return _mthdelegate().descendingIterator();
            }

            public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 181*/        return _mthdelegate().subSet(obj, flag, obj1, flag1);
            }

            public NavigableSet headSet(Object obj, boolean flag)
            {
/* 211*/        return _mthdelegate().headSet(obj, flag);
            }

            public NavigableSet tailSet(Object obj, boolean flag)
            {
/* 226*/        return _mthdelegate().tailSet(obj, flag);
            }

            protected volatile SortedSet _mthdelegate()
            {
/*  47*/        return _mthdelegate();
            }

            protected volatile Set _mthdelegate()
            {
/*  47*/        return _mthdelegate();
            }

            protected volatile Collection _mthdelegate()
            {
/*  47*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  47*/        return _mthdelegate();
            }
}
