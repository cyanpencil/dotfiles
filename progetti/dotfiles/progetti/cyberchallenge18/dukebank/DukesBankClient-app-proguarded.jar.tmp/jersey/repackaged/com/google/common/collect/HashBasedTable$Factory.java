// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HashBasedTable.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Supplier;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            HashBasedTable, Maps

static class expectedSize
    implements Serializable, Supplier
{

            public Map get()
            {
/*  65*/        return Maps.newHashMapWithExpectedSize(expectedSize);
            }

            public volatile Object get()
            {
/*  57*/        return get();
            }

            final int expectedSize;

            (int i)
            {
/*  61*/        expectedSize = i;
            }
}
