// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.RandomAccess;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Lists

static class  extends AbstractList
    implements Serializable, RandomAccess
{

            public int size()
            {
/* 371*/        return rest.length + 2;
            }

            public Object get(int i)
            {
/* 374*/        switch(i)
                {
/* 376*/        case 0: // '\0'
/* 376*/            return first;

/* 378*/        case 1: // '\001'
/* 378*/            return second;
                }
/* 381*/        Preconditions.checkElementIndex(i, size());
/* 382*/        return rest[i - 2];
            }

            final Object first;
            final Object second;
            final Object rest[];

            (Object obj, Object obj1, Object aobj[])
            {
/* 366*/        first = obj;
/* 367*/        second = obj1;
/* 368*/        rest = (Object[])Preconditions.checkNotNull(((Object) (aobj)));
            }
}
