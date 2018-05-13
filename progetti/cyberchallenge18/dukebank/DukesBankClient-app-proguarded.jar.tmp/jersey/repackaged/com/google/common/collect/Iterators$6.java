// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, Iterators

static class Iterator extends UnmodifiableIterator
{

            public final boolean hasNext()
            {
/* 617*/        return val$iterator.hasNext();
            }

            public final List next()
            {
/* 621*/        if(!hasNext())
/* 622*/            throw new NoSuchElementException();
/* 624*/        Object aobj[] = new Object[val$size];
                int i;
/* 625*/        for(i = 0; i < val$size && val$iterator.hasNext(); i++)
/* 627*/            aobj[i] = val$iterator.next();

/* 629*/        for(int j = i; j < val$size; j++)
/* 630*/            aobj[j] = null;

/* 634*/        List list = Collections.unmodifiableList(Arrays.asList(aobj));
/* 636*/        if(val$pad || i == val$size)
/* 636*/            return list;
/* 636*/        else
/* 636*/            return list.subList(0, i);
            }

            public final volatile Object next()
            {
/* 614*/        return next();
            }

            final Iterator val$iterator;
            final int val$size;
            final boolean val$pad;

            Iterator(Iterator iterator1, int i, boolean flag)
            {
/* 614*/        val$iterator = iterator1;
/* 614*/        val$size = i;
/* 614*/        val$pad = flag;
/* 614*/        super();
            }
}
