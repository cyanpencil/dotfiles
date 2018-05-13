// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LRUCacheCheapRead.java

package org.glassfish.hk2.utilities.cache.internal;

import java.util.Comparator;

// Referenced classes of package org.glassfish.hk2.utilities.cache.internal:
//            LRUCacheCheapRead

static class <init>
    implements Comparator
{

            public int compare(<init> <init>1, <init> <init>2)
            {
                long l;
/* 137*/        if((l = <init>1.<init> - <init>2.<init>) > 0L)
/* 138*/            return 1;
/* 138*/        return l != 0L ? -1 : 0;
            }

            public volatile int compare(Object obj, Object obj1)
            {
/* 133*/        return compare((compare)obj, (compare)obj1);
            }

            private ()
            {
            }

            ( )
            {
/* 133*/        this();
            }
}
