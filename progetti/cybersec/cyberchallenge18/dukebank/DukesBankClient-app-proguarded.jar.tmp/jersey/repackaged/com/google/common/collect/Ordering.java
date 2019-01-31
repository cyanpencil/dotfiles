// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Ordering.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ByFunctionOrdering, ComparatorOrdering, ImmutableList, Iterables, 
//            Lists, Maps, NaturalOrdering, ReverseOrdering

public abstract class Ordering
    implements Comparator
{

            public static Ordering natural()
            {
/* 106*/        return NaturalOrdering.INSTANCE;
            }

            public static Ordering from(Comparator comparator)
            {
/* 124*/        if(comparator instanceof Ordering)
/* 124*/            return (Ordering)comparator;
/* 124*/        else
/* 124*/            return new ComparatorOrdering(comparator);
            }

            protected Ordering()
            {
            }

            public Ordering reverse()
            {
/* 333*/        return new ReverseOrdering(this);
            }

            public Ordering onResultOf(Function function)
            {
/* 369*/        return new ByFunctionOrdering(function, this);
            }

            Ordering onKeys()
            {
/* 373*/        return onResultOf(Maps.keyFunction());
            }

            public abstract int compare(Object obj, Object obj1);

            public List sortedCopy(Iterable iterable)
            {
/* 816*/        Arrays.sort(iterable = ((Iterable) ((Object[])Iterables.toArray(iterable))), this);
/* 818*/        return Lists.newArrayList(Arrays.asList(iterable));
            }

            public ImmutableList immutableSortedCopy(Iterable iterable)
            {
                Object aobj[];
/* 842*/        int i = (aobj = iterable = ((Iterable) ((Object[])Iterables.toArray(iterable)))).length;
/* 843*/        for(int j = 0; j < i; j++)
                {
                    Object obj;
/* 843*/            Preconditions.checkNotNull(obj = aobj[j]);
                }

/* 846*/        Arrays.sort(iterable, this);
/* 847*/        return ImmutableList.asImmutableList(iterable);
            }
}
