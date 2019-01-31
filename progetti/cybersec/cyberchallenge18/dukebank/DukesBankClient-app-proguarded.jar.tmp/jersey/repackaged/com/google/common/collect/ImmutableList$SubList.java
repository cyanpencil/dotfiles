// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableList.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableList

class length extends ImmutableList
{

            public int size()
            {
/* 413*/        return length;
            }

            public Object get(int i)
            {
/* 418*/        Preconditions.checkElementIndex(i, length);
/* 419*/        return ImmutableList.this.get(i + offset);
            }

            public ImmutableList subList(int i, int j)
            {
/* 424*/        Preconditions.checkPositionIndexes(i, j, length);
/* 425*/        return ImmutableList.this.subList(i + offset, j + offset);
            }

            boolean isPartialView()
            {
/* 430*/        return true;
            }

            public volatile List subList(int i, int j)
            {
/* 402*/        return subList(i, j);
            }

            public volatile ListIterator listIterator(int i)
            {
/* 402*/        return super.listIterator(i);
            }

            public volatile ListIterator listIterator()
            {
/* 402*/        return super.listIterator();
            }

            public volatile Iterator iterator()
            {
/* 402*/        return super.iterator();
            }

            final transient int offset;
            final transient int length;
            final ImmutableList this$0;

            (int i, int j)
            {
/* 406*/        this$0 = ImmutableList.this;
/* 406*/        super();
/* 407*/        offset = i;
/* 408*/        length = j;
            }
}
