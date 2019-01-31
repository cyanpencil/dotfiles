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
/* 326*/        return rest.length + 1;
            }

            public Object get(int i)
            {
/* 330*/        Preconditions.checkElementIndex(i, size());
/* 331*/        if(i == 0)
/* 331*/            return first;
/* 331*/        else
/* 331*/            return rest[i - 1];
            }

            final Object first;
            final Object rest[];

            (Object obj, Object aobj[])
            {
/* 322*/        first = obj;
/* 323*/        rest = (Object[])Preconditions.checkNotNull(((Object) (aobj)));
            }
}
