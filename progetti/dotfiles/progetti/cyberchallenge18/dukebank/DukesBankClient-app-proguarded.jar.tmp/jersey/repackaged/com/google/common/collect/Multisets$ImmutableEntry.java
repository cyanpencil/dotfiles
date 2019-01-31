// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multisets.java

package jersey.repackaged.com.google.common.collect;

import java.io.Serializable;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, Multisets

static final class kNonnegative extends kNonnegative
    implements Serializable
{

            public final Object getElement()
            {
/* 228*/        return element;
            }

            public final int getCount()
            {
/* 233*/        return count;
            }

            final Object element;
            final int count;

            (Object obj, int i)
            {
/* 221*/        element = obj;
/* 222*/        count = i;
/* 223*/        CollectPreconditions.checkNonnegative(i, "count");
            }
}
