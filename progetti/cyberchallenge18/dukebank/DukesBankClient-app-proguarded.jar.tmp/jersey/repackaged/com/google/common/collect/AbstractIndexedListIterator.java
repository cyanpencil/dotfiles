// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractIndexedListIterator.java

package jersey.repackaged.com.google.common.collect;

import java.util.NoSuchElementException;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableListIterator

abstract class AbstractIndexedListIterator extends UnmodifiableListIterator
{

            protected abstract Object get(int i);

            protected AbstractIndexedListIterator(int i)
            {
/*  54*/        this(i, 0);
            }

            protected AbstractIndexedListIterator(int i, int j)
            {
/*  69*/        Preconditions.checkPositionIndex(j, i);
/*  70*/        size = i;
/*  71*/        position = j;
            }

            public final boolean hasNext()
            {
/*  76*/        return position < size;
            }

            public final Object next()
            {
/*  81*/        if(!hasNext())
/*  82*/            throw new NoSuchElementException();
/*  84*/        else
/*  84*/            return get(position++);
            }

            public final int nextIndex()
            {
/*  89*/        return position;
            }

            public final boolean hasPrevious()
            {
/*  94*/        return position > 0;
            }

            public final Object previous()
            {
/*  99*/        if(!hasPrevious())
/* 100*/            throw new NoSuchElementException();
/* 102*/        else
/* 102*/            return get(--position);
            }

            public final int previousIndex()
            {
/* 107*/        return position - 1;
            }

            private final int size;
            private int position;
}
