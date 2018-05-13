// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Collections2.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Collections2, Iterators

static class function extends AbstractCollection
{

            public void clear()
            {
/* 261*/        fromCollection.clear();
            }

            public boolean isEmpty()
            {
/* 265*/        return fromCollection.isEmpty();
            }

            public Iterator iterator()
            {
/* 269*/        return Iterators.transform(fromCollection.iterator(), function);
            }

            public int size()
            {
/* 273*/        return fromCollection.size();
            }

            final Collection fromCollection;
            final Function function;

            (Collection collection, Function function1)
            {
/* 256*/        fromCollection = (Collection)Preconditions.checkNotNull(collection);
/* 257*/        function = (Function)Preconditions.checkNotNull(function1);
            }
}
