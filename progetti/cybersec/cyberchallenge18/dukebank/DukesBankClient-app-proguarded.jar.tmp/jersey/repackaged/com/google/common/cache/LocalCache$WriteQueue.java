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

static final class _cls1.previousWrite extends AbstractQueue
{

            public final boolean offer(try try1)
            {
/*3635*/        LocalCache.connectWriteOrder(try1.getPreviousInWriteQueue(), try1.getNextInWriteQueue());
/*3638*/        LocalCache.connectWriteOrder(head.getPreviousInWriteQueue(), try1);
/*3639*/        LocalCache.connectWriteOrder(try1, head);
/*3641*/        return true;
            }

            public final try peek()
            {
                try try1;
/*3646*/        if((try1 = head.getNextInWriteQueue()) == head)
/*3647*/            return null;
/*3647*/        else
/*3647*/            return try1;
            }

            public final try poll()
            {
                try try1;
/*3652*/        if((try1 = head.getNextInWriteQueue()) == head)
                {
/*3654*/            return null;
                } else
                {
/*3657*/            remove(try1);
/*3658*/            return try1;
                }
            }

            public final boolean remove(Object obj)
            {
/*3664*/        try try1 = ((try) (obj = (try)obj)).getPreviousInWriteQueue();
/*3666*/        try try2 = ((try) (obj)).getNextInWriteQueue();
/*3667*/        LocalCache.connectWriteOrder(try1, try2);
/*3668*/        LocalCache.nullifyWriteOrder(((try) (obj)));
/*3670*/        return try2 != NSTANCE;
            }

            public final boolean contains(Object obj)
            {
/*3676*/        return ((try) (obj = (try)obj)).getNextInWriteQueue() != NSTANCE;
            }

            public final boolean isEmpty()
            {
/*3682*/        return head.getNextInWriteQueue() == head;
            }

            public final int size()
            {
/*3687*/        int i = 0;
/*3688*/        for(try try1 = head.getNextInWriteQueue(); try1 != head; try1 = try1.getNextInWriteQueue())
/*3690*/            i++;

/*3692*/        return i;
            }

            public final void clear()
            {
                try try2;
/*3697*/        for(try try1 = head.getNextInWriteQueue(); try1 != head; try1 = try2)
                {
/*3699*/            try2 = try1.getNextInWriteQueue();
/*3700*/            LocalCache.nullifyWriteOrder(try1);
                }

/*3704*/        head.setNextInWriteQueue(head);
/*3705*/        head.setPreviousInWriteQueue(head);
            }

            public final Iterator iterator()
            {
/*3710*/        return new AbstractSequentialIterator(peek()) {

                    protected LocalCache.ReferenceEntry computeNext(LocalCache.ReferenceEntry referenceentry)
                    {
/*3713*/                if((referenceentry = referenceentry.getNextInWriteQueue()) == head)
/*3714*/                    return null;
/*3714*/                else
/*3714*/                    return referenceentry;
                    }

                    protected volatile Object computeNext(Object obj)
                    {
/*3710*/                return computeNext((LocalCache.ReferenceEntry)obj);
                    }

                    final LocalCache.WriteQueue this$0;

                    
                    {
/*3710*/                this$0 = LocalCache.WriteQueue.this;
/*3710*/                super(referenceentry);
                    }
        };
            }

            public final volatile Object peek()
            {
/*3594*/        return peek();
            }

            public final volatile Object poll()
            {
/*3594*/        return poll();
            }

            public final volatile boolean offer(Object obj)
            {
/*3594*/        return offer((try)obj);
            }

            final try head = new LocalCache.AbstractReferenceEntry() {

                public long getWriteTime()
                {
/*3599*/            return 0x7fffffffffffffffL;
                }

                public void setWriteTime(long l)
                {
                }

                public LocalCache.ReferenceEntry getNextInWriteQueue()
                {
/*3609*/            return nextWrite;
                }

                public void setNextInWriteQueue(LocalCache.ReferenceEntry referenceentry)
                {
/*3614*/            nextWrite = referenceentry;
                }

                public LocalCache.ReferenceEntry getPreviousInWriteQueue()
                {
/*3621*/            return previousWrite;
                }

                public void setPreviousInWriteQueue(LocalCache.ReferenceEntry referenceentry)
                {
/*3626*/            previousWrite = referenceentry;
                }

                LocalCache.ReferenceEntry nextWrite;
                LocalCache.ReferenceEntry previousWrite;
                final LocalCache.WriteQueue this$0;

                    
                    {
/*3595*/                this$0 = LocalCache.WriteQueue.this;
/*3595*/                super();
/*3605*/                nextWrite = this;
/*3617*/                previousWrite = this;
                    }
    };

            _cls1.previousWrite()
            {
            }
}
