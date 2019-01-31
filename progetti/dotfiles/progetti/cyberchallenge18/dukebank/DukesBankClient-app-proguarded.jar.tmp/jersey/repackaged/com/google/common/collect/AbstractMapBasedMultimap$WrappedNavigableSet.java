// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap, Iterators

class it> extends it>
    implements NavigableSet
{

            NavigableSet getSortedSetDelegate()
            {
/* 691*/        return (NavigableSet)super.SortedSetDelegate();
            }

            public Object lower(Object obj)
            {
/* 696*/        return getSortedSetDelegate().lower(obj);
            }

            public Object floor(Object obj)
            {
/* 701*/        return getSortedSetDelegate().floor(obj);
            }

            public Object ceiling(Object obj)
            {
/* 706*/        return getSortedSetDelegate().ceiling(obj);
            }

            public Object higher(Object obj)
            {
/* 711*/        return getSortedSetDelegate().higher(obj);
            }

            public Object pollFirst()
            {
/* 716*/        return Iterators.pollNext(iterator());
            }

            public Object pollLast()
            {
/* 721*/        return Iterators.pollNext(descendingIterator());
            }

            private NavigableSet wrap(NavigableSet navigableset)
            {
/* 725*/        return new <init>(key, navigableset, ((key) (getAncestor() != null ? getAncestor() : ((getAncestor) (this)))));
            }

            public NavigableSet descendingSet()
            {
/* 731*/        return wrap(getSortedSetDelegate().descendingSet());
            }

            public Iterator descendingIterator()
            {
/* 736*/        return new appedIterator(this, getSortedSetDelegate().descendingIterator());
            }

            public NavigableSet subSet(Object obj, boolean flag, Object obj1, boolean flag1)
            {
/* 742*/        return wrap(getSortedSetDelegate().subSet(obj, flag, obj1, flag1));
            }

            public NavigableSet headSet(Object obj, boolean flag)
            {
/* 748*/        return wrap(getSortedSetDelegate().headSet(obj, flag));
            }

            public NavigableSet tailSet(Object obj, boolean flag)
            {
/* 753*/        return wrap(getSortedSetDelegate().tailSet(obj, flag));
            }

            volatile SortedSet getSortedSetDelegate()
            {
/* 682*/        return getSortedSetDelegate();
            }

            final AbstractMapBasedMultimap this$0;

            appedIterator(Object obj, NavigableSet navigableset, appedIterator appediterator)
            {
/* 685*/        this$0 = AbstractMapBasedMultimap.this;
/* 686*/        super(AbstractMapBasedMultimap.this, obj, navigableset, appediterator);
            }
}
