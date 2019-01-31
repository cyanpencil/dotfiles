// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Iterators

static class nditions
    implements Iterator
{

            public final boolean hasNext()
            {
/* 130*/        return false;
            }

            public final Object next()
            {
/* 134*/        throw new NoSuchElementException();
            }

            public final void remove()
            {
/* 138*/        CollectPreconditions.checkRemove(false);
            }

            nditions()
            {
            }
}
