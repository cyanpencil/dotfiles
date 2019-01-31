// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            TransformedIterator, Iterators

static class terator extends TransformedIterator
{

            final Object transform(Object obj)
            {
/* 799*/        return val$function.apply(obj);
            }

            final Function val$function;

            terator(Iterator iterator, Function function1)
            {
/* 796*/        val$function = function1;
/* 796*/        super(iterator);
            }
}
