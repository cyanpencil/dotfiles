// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            AbstractIndexedListIterator, Iterators

static class edListIterator extends AbstractIndexedListIterator
{

            protected final Object get(int i)
            {
/*1060*/        return val$array[val$offset + i];
            }

            final Object val$array[];
            final int val$offset;

            edListIterator(int i, int j, Object aobj[], int k)
            {
/*1058*/        val$array = aobj;
/*1058*/        val$offset = k;
/*1058*/        super(i, j);
            }
}
