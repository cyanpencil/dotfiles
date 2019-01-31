// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Iterators.java

package jersey.repackaged.com.google.common.collect;

import java.util.*;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            UnmodifiableIterator, Iterators, PeekingIterator

static class queue extends UnmodifiableIterator
{

            public boolean hasNext()
            {
/*1298*/        return !queue.isEmpty();
            }

            public Object next()
            {
                PeekingIterator peekingiterator;
/*1303*/        Object obj = (peekingiterator = (PeekingIterator)queue.remove()).next();
/*1305*/        if(peekingiterator.hasNext())
/*1306*/            queue.add(peekingiterator);
/*1308*/        return obj;
            }

            final Queue queue;

            public _cls1.val.itemComparator(Iterable iterable, final Comparator itemComparator)
            {
/*1279*/        itemComparator = new Comparator() {

                    public int compare(PeekingIterator peekingiterator, PeekingIterator peekingiterator1)
                    {
/*1283*/                return itemComparator.compare(peekingiterator.peek(), peekingiterator1.peek());
                    }

                    public volatile int compare(Object obj, Object obj1)
                    {
/*1280*/                return compare((PeekingIterator)obj, (PeekingIterator)obj1);
                    }

                    final Comparator val$itemComparator;
                    final Iterators.MergingIterator this$0;

                    
                    {
/*1280*/                this$0 = Iterators.MergingIterator.this;
/*1280*/                itemComparator = comparator;
/*1280*/                super();
                    }
        };
/*1287*/        queue = new PriorityQueue(2, itemComparator);
/*1289*/        iterable = iterable.iterator();
/*1289*/        do
                {
/*1289*/            if(!iterable.hasNext())
/*1289*/                break;
/*1289*/            if((itemComparator = (Iterator)iterable.next()).hasNext())
/*1291*/                queue.add(Iterators.peekingIterator(itemComparator));
                } while(true);
            }
}
