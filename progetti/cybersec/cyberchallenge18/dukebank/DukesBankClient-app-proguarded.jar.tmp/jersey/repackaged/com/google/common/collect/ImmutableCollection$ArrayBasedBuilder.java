// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImmutableCollection.java

package jersey.repackaged.com.google.common.collect;

import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            CollectPreconditions, ImmutableCollection, ObjectArrays

static abstract class size extends size
{

            private void ensureCapacity(int i)
            {
/* 331*/        if(contents.length < i)
/* 332*/            contents = ObjectArrays.arraysCopyOf(contents, expandedCapacity(contents.length, i));
            }

            public contents add(Object obj)
            {
/* 339*/        Preconditions.checkNotNull(obj);
/* 340*/        ensureCapacity(size + 1);
/* 341*/        contents[size++] = obj;
/* 342*/        return this;
            }

            public volatile size add(Object obj)
            {
/* 316*/        return add(obj);
            }

            Object contents[];
            int size;

            (int i)
            {
/* 321*/        CollectPreconditions.checkNonnegative(i, "initialCapacity");
/* 322*/        contents = new Object[i];
/* 323*/        size = 0;
            }
}
