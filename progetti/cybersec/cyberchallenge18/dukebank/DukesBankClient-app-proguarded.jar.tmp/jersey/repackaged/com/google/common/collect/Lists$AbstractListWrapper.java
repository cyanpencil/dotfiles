// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lists.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Lists

static class backingList extends AbstractList
{

            public void add(int i, Object obj)
            {
/*1038*/        backingList.add(i, obj);
            }

            public boolean addAll(int i, Collection collection)
            {
/*1042*/        return backingList.addAll(i, collection);
            }

            public Object get(int i)
            {
/*1046*/        return backingList.get(i);
            }

            public Object remove(int i)
            {
/*1050*/        return backingList.remove(i);
            }

            public Object set(int i, Object obj)
            {
/*1054*/        return backingList.set(i, obj);
            }

            public boolean contains(Object obj)
            {
/*1058*/        return backingList.contains(obj);
            }

            public int size()
            {
/*1062*/        return backingList.size();
            }

            final List backingList;

            (List list)
            {
/*1034*/        backingList = (List)Preconditions.checkNotNull(list);
            }
}
