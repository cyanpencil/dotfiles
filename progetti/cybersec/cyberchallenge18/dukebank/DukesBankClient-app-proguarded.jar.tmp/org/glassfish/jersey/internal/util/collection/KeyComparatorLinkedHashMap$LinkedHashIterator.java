// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeyComparatorLinkedHashMap.java

package org.glassfish.jersey.internal.util.collection;

import java.util.*;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            KeyComparatorLinkedHashMap

abstract class expectedModCount
    implements Iterator
{

            public boolean hasNext()
            {
/* 312*/        return nextEntry != KeyComparatorLinkedHashMap.access$100(KeyComparatorLinkedHashMap.this);
            }

            public void remove()
            {
/* 317*/        if(lastReturned == null)
/* 318*/            throw new IllegalStateException();
/* 320*/        if(modCount != expectedModCount)
                {
/* 321*/            throw new ConcurrentModificationException();
                } else
                {
/* 324*/            KeyComparatorLinkedHashMap.this.remove(lastReturned.lastReturned);
/* 325*/            lastReturned = null;
/* 326*/            expectedModCount = modCount;
/* 327*/            return;
                }
            }

            expectedModCount nextEntry()
            {
/* 330*/        if(modCount != expectedModCount)
/* 331*/            throw new ConcurrentModificationException();
/* 333*/        if(nextEntry == KeyComparatorLinkedHashMap.access$100(KeyComparatorLinkedHashMap.this))
                {
/* 334*/            throw new NoSuchElementException();
                } else
                {
/* 337*/            expectedModCount expectedmodcount = lastReturned = nextEntry;
/* 338*/            nextEntry = expectedmodcount.nextEntry;
/* 339*/            return expectedmodcount;
                }
            }

            nextEntry nextEntry;
            nextEntry lastReturned;
            int expectedModCount;
            final KeyComparatorLinkedHashMap this$0;

            private a()
            {
/* 299*/        this$0 = KeyComparatorLinkedHashMap.this;
/* 299*/        super();
/* 301*/        nextEntry = KeyComparatorLinkedHashMap.access$100(KeyComparatorLinkedHashMap.this)._fld0;
/* 302*/        lastReturned = null;
/* 308*/        expectedModCount = modCount;
            }

}
