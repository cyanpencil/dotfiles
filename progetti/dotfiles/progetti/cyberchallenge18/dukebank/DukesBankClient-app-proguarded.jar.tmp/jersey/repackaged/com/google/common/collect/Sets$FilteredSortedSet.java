// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Sets.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Predicate;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Sets

static class  extends 
    implements SortedSet
{

            public Comparator comparator()
            {
/* 838*/        return ((SortedSet)unfiltered).comparator();
            }

            public SortedSet subSet(Object obj, Object obj1)
            {
/* 843*/        return new <init>(((SortedSet)unfiltered).subSet(obj, obj1), predicate);
            }

            public SortedSet headSet(Object obj)
            {
/* 849*/        return new <init>(((SortedSet)unfiltered).headSet(obj), predicate);
            }

            public SortedSet tailSet(Object obj)
            {
/* 854*/        return new <init>(((SortedSet)unfiltered).tailSet(obj), predicate);
            }

            public Object first()
            {
/* 859*/        return iterator().next();
            }

            public Object last()
            {
/* 864*/        SortedSet sortedset = (SortedSet)unfiltered;
/* 866*/        do
                {
/* 866*/            Object obj = sortedset.last();
/* 867*/            if(predicate.apply(obj))
/* 868*/                return obj;
/* 870*/            sortedset = sortedset.headSet(obj);
                } while(true);
            }

            (SortedSet sortedset, Predicate predicate)
            {
/* 833*/        super(sortedset, predicate);
            }
}
