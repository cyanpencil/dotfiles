// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Function;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            TransformedIterator, Maps

static class rmedIterator extends TransformedIterator
{

            final java.util.ry transform(Object obj)
            {
/* 836*/        return Maps.immutableEntry(obj, val$function.apply(obj));
            }

            final volatile Object transform(Object obj)
            {
/* 833*/        return transform(obj);
            }

            final Function val$function;

            rmedIterator(Iterator iterator, Function function1)
            {
/* 833*/        val$function = function1;
/* 833*/        super(iterator);
            }
}
