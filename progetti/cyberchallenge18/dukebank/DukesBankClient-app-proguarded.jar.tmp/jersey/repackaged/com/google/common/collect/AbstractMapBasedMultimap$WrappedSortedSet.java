// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractMapBasedMultimap.java

package jersey.repackaged.com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractMapBasedMultimap

class  extends 
    implements SortedSet
{

            SortedSet getSortedSetDelegate()
            {
/* 637*/        return (SortedSet)getDelegate();
            }

            public Comparator comparator()
            {
/* 642*/        return getSortedSetDelegate().comparator();
            }

            public Object first()
            {
/* 647*/        refreshIfEmpty();
/* 648*/        return getSortedSetDelegate().first();
            }

            public Object last()
            {
/* 653*/        refreshIfEmpty();
/* 654*/        return getSortedSetDelegate().last();
            }

            public SortedSet headSet(Object obj)
            {
/* 659*/        refreshIfEmpty();
/* 660*/        return new <init>(getKey(), getSortedSetDelegate().headSet(obj), (() (getAncestor() != null ? getAncestor() : (() (this)))));
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/* 667*/        refreshIfEmpty();
/* 668*/        return new <init>(getKey(), getSortedSetDelegate().subSet(obj, obj1), (() (getAncestor() != null ? getAncestor() : (() (this)))));
            }

            public SortedSet tailSet(Object obj)
            {
/* 675*/        refreshIfEmpty();
/* 676*/        return new <init>(getKey(), getSortedSetDelegate().tailSet(obj), (() (getAncestor() != null ? getAncestor() : (() (this)))));
            }

            final AbstractMapBasedMultimap this$0;

            (Object obj, SortedSet sortedset,  )
            {
/* 632*/        this$0 = AbstractMapBasedMultimap.this;
/* 633*/        super(AbstractMapBasedMultimap.this, obj, sortedset, );
            }
}
