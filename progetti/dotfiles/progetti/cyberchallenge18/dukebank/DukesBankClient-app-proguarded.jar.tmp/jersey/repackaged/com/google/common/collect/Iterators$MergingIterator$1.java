// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.Comparator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            Iterators, PeekingIterator

class val.itemComparator
    implements Comparator
{

            public int compare(PeekingIterator peekingiterator, PeekingIterator peekingiterator1)
            {
/*1283*/        return val$itemComparator.compare(peekingiterator.peek(), peekingiterator1.peek());
            }

            public volatile int compare(Object obj, Object obj1)
            {
/*1280*/        return compare((PeekingIterator)obj, (PeekingIterator)obj1);
            }

            final Comparator val$itemComparator;
            final compare this$0;

            ()
            {
/*1280*/        this$0 = final_;
/*1280*/        val$itemComparator = Comparator.this;
/*1280*/        super();
            }
}
