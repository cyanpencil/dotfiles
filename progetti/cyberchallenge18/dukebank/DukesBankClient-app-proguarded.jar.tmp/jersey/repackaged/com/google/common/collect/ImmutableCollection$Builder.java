// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableCollection.java

package jersey.repackaged.com.google.common.collect;

import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableCollection

public static abstract class 
{

            static int expandedCapacity(int i, int j)
            {
/* 219*/        if(j < 0)
/* 220*/            throw new AssertionError("cannot store more than MAX_VALUE elements");
/* 223*/        if((i = i + (i >> 1) + 1) < j)
/* 225*/            i = Integer.highestOneBit(j - 1) << 1;
/* 227*/        if(i < 0)
/* 228*/            i = 0x7fffffff;
/* 231*/        return i;
            }

            public abstract  add(Object obj);

            public  addAll(Iterator iterator)
            {
/* 300*/        for(; iterator.hasNext(); add(iterator.next()));
/* 303*/        return this;
            }

            ()
            {
            }
}
