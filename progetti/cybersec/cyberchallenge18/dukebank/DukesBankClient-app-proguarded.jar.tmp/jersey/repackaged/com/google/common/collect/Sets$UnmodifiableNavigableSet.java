// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ForwardingSortedSet, Iterators, Sets

static final class delegate extends ForwardingSortedSet
    implements Serializable, NavigableSet
{

            protected final SortedSet _mthdelegate()
            {
/*1408*/        return Collections.unmodifiableSortedSet(_flddelegate);
            }

            public final Object lower(Object obj)
            {
/*1413*/        return _flddelegate.lower(obj);
            }

            public final Object floor(Object obj)
            {
/*1418*/        return _flddelegate.floor(obj);
            }

            public final Object ceiling(Object obj)
            {
/*1423*/        return _flddelegate.ceiling(obj);
            }

            public final Object higher(Object obj)
            {
/*1428*/        return _flddelegate.higher(obj);
            }

            public final Object pollFirst()
            {
/*1433*/        throw new UnsupportedOperationException();
            }

            public final Object pollLast()
            {
/*1438*/        throw new UnsupportedOperationException();
            }

            public final NavigableSet descendingSet()
            {
                delegate delegate1;
/*1445*/        if((delegate1 = descendingSet) == null)
/*1447*/            (delegate1 = descendingSet = new <init>(_flddelegate.descendingSet())).descendingSet = this;
/*1451*/        return delegate1;
            }

            public final Iterator descendingIterator()
            {
/*1456*/        return Iterators.unmodifiableIterator(_flddelegate.descendingIterator());
            }

            public final NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/*1465*/        return Sets.unmodifiableNavigableSet(_flddelegate.subSet(obj, flag, obj1, flag1));
            }

            public final NavigableSet headSet(Object obj, boolean flag)
            {
/*1474*/        return Sets.unmodifiableNavigableSet(_flddelegate.headSet(obj, flag));
            }

            public final NavigableSet tailSet(Object obj, boolean flag)
            {
/*1479*/        return Sets.unmodifiableNavigableSet(_flddelegate.tailSet(obj, flag));
            }

            protected final volatile Set _mthdelegate()
            {
/*1397*/        return _mthdelegate();
            }

            protected final volatile Collection _mthdelegate()
            {
/*1397*/        return _mthdelegate();
            }

            protected final volatile Object _mthdelegate()
            {
/*1397*/        return _mthdelegate();
            }

            private final NavigableSet _flddelegate;
            private transient delegate descendingSet;

            (NavigableSet navigableset)
            {
/*1403*/        _flddelegate = (NavigableSet)Preconditions.checkNotNull(navigableset);
            }
}
