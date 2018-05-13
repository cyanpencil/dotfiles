// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ForwardingSortedSet.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingSet

public abstract class ForwardingSortedSet extends ForwardingSet
    implements SortedSet
{

            protected ForwardingSortedSet()
            {
            }

            protected abstract SortedSet _mthdelegate();

            public Comparator comparator()
            {
/*  67*/        return _mthdelegate().comparator();
            }

            public Object first()
            {
/*  72*/        return _mthdelegate().first();
            }

            public SortedSet headSet(Object obj)
            {
/*  77*/        return _mthdelegate().headSet(obj);
            }

            public Object last()
            {
/*  82*/        return _mthdelegate().last();
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/*  87*/        return _mthdelegate().subSet(obj, obj1);
            }

            public SortedSet tailSet(Object obj)
            {
/*  92*/        return _mthdelegate().tailSet(obj);
            }

            protected volatile Set _mthdelegate()
            {
/*  56*/        return _mthdelegate();
            }

            protected volatile Collection _mthdelegate()
            {
/*  56*/        return _mthdelegate();
            }

            protected volatile Object _mthdelegate()
            {
/*  56*/        return _mthdelegate();
            }
}
