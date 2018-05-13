// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Multiset.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

public interface Multiset
    extends Collection
{
    public static interface Entry
    {

        public abstract Object getElement();

        public abstract int getCount();
    }


    public abstract int count(Object obj);

    public abstract int add(Object obj, int i);

    public abstract int remove(Object obj, int i);

    public abstract int setCount(Object obj, int i);

    public abstract boolean setCount(Object obj, int i, int j);

    public abstract Set elementSet();

    public abstract Set entrySet();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract Iterator iterator();

    public abstract boolean contains(Object obj);

    public abstract boolean containsAll(Collection collection);

    public abstract boolean remove(Object obj);
}
