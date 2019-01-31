// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterables.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            FluentIterable, Iterables, Iterators

static class le extends FluentIterable
{

            public final Iterator iterator()
            {
/* 713*/        return Iterators.transform(val$fromIterable.iterator(), val$function);
            }

            final Iterable val$fromIterable;
            final Function val$function;

            le(Iterable iterable, Function function1)
            {
/* 710*/        val$fromIterable = iterable;
/* 710*/        val$function = function1;
/* 710*/        super();
            }
}
