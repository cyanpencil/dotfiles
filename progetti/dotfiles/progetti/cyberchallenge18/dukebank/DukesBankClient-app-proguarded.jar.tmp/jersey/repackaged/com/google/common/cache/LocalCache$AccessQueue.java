// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.util.AbstractQueue;
import java.util.Iterator;
import jersey.repackaged.com.google.common.collect.AbstractSequentialIterator;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static final class _cls1.previousAccess extends AbstractQueue
{

            public final boolean offer(ry ry)
            {
/*3772*/        LocalCache.connectAccessOrder(ry.getPreviousInAccessQueue(), ry.getNextInAccessQueue());
/*3775*/        LocalCache.connectAccessOrder(head.getPreviousInAccessQueue(), ry);
/*3776*/        LocalCache.connectAccessOrder(ry, head);
/*3778*/        return true;
            }

            public final ry peek()
            {
                ry ry;
/*3783*/        if((ry = head.getNextInAccessQueue()) == head)
/*3784*/            return null;
/*3784*/        else
/*3784*/            return ry;
            }

            public final ry poll()
            {
                ry ry;
/*3789*/        if((ry = head.getNextInAccessQueue()) == head)
                {
/*3791*/            return null;
                } else
                {
/*3794*/            remove(ry);
/*3795*/            return ry;
                }
            }

            public final boolean remove(Object obj)
            {
/*3801*/        ry ry = ((ry) (obj = (ry)obj)).getPreviousInAccessQueue();
/*3803*/        ry ry1 = ((ry) (obj)).getNextInAccessQueue();
/*3804*/        LocalCache.connectAccessOrder(ry, ry1);
/*3805*/        LocalCache.nullifyAccessOrder(((ry) (obj)));
/*3807*/        return ry1 != STANCE;
            }

            public final boolean contains(Object obj)
            {
/*3813*/        return ((ry) (obj = (ry)obj)).getNextInAccessQueue() != STANCE;
            }

            public final boolean isEmpty()
            {
/*3819*/        return head.getNextInAccessQueue() == head;
            }

            public final int size()
            {
/*3824*/        int i = 0;
/*3825*/        for(ry ry = head.getNextInAccessQueue(); ry != head; ry = ry.getNextInAccessQueue())
/*3827*/            i++;

/*3829*/        return i;
            }

            public final void clear()
            {
                ry ry1;
/*3834*/        for(ry ry = head.getNextInAccessQueue(); ry != head; ry = ry1)
                {
/*3836*/            ry1 = ry.getNextInAccessQueue();
/*3837*/            LocalCache.nullifyAccessOrder(ry);
                }

/*3841*/        head.setNextInAccessQueue(head);
/*3842*/        head.setPreviousInAccessQueue(head);
            }

            public final Iterator iterator()
            {
/*3847*/        return new AbstractSequentialIterator(peek()) {

                    protected LocalCache.ReferenceEntry computeNext(LocalCache.ReferenceEntry referenceentry)
                    {
/*3850*/                if((referenceentry = referenceentry.getNextInAccessQueue()) == head)
/*3851*/                    return null;
/*3851*/                else
/*3851*/                    return referenceentry;
                    }

                    protected volatile Object computeNext(Object obj)
                    {
/*3847*/                return computeNext((LocalCache.ReferenceEntry)obj);
                    }

                    final LocalCache.AccessQueue this$0;

                    
                    {
/*3847*/                this$0 = LocalCache.AccessQueue.this;
/*3847*/                super(referenceentry);
                    }
        };
            }

            public final volatile Object peek()
            {
/*3731*/        return peek();
            }

            public final volatile Object poll()
            {
/*3731*/        return poll();
            }

            public final volatile boolean offer(Object obj)
            {
/*3731*/        return offer((ry)obj);
            }

            final ry head = new LocalCache.AbstractReferenceEntry() {

                public long getAccessTime()
                {
/*3736*/            return 0x7fffffffffffffffL;
                }

                public void setAccessTime(long l)
                {
                }

                public LocalCache.ReferenceEntry getNextInAccessQueue()
                {
/*3746*/            return nextAccess;
                }

                public void setNextInAccessQueue(LocalCache.ReferenceEntry referenceentry)
                {
/*3751*/            nextAccess = referenceentry;
                }

                public LocalCache.ReferenceEntry getPreviousInAccessQueue()
                {
/*3758*/            return previousAccess;
                }

                public void setPreviousInAccessQueue(LocalCache.ReferenceEntry referenceentry)
                {
/*3763*/            previousAccess = referenceentry;
                }

                LocalCache.ReferenceEntry nextAccess;
                LocalCache.ReferenceEntry previousAccess;
                final LocalCache.AccessQueue this$0;

                    
                    {
/*3732*/                this$0 = LocalCache.AccessQueue.this;
/*3732*/                super();
/*3742*/                nextAccess = this;
/*3754*/                previousAccess = this;
                    }
    };

            _cls1.previousAccess()
            {
            }
}
