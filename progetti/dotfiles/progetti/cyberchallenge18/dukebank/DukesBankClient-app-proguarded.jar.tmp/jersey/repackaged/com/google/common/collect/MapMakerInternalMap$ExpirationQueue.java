// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractQueue;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap, AbstractSequentialIterator

static final class _cls1.previousExpirable extends AbstractQueue
{

            public final boolean offer(_cls1.previousExpirable previousexpirable)
            {
/*3280*/        MapMakerInternalMap.connectExpirables(previousexpirable.etPreviousExpirable(), previousexpirable.etNextExpirable());
/*3283*/        MapMakerInternalMap.connectExpirables(head.etPreviousExpirable(), previousexpirable);
/*3284*/        MapMakerInternalMap.connectExpirables(previousexpirable, head);
/*3286*/        return true;
            }

            public final head peek()
            {
                head head1;
/*3291*/        if((head1 = head.etNextExpirable()) == head)
/*3292*/            return null;
/*3292*/        else
/*3292*/            return head1;
            }

            public final head poll()
            {
                head head1;
/*3297*/        if((head1 = head.etNextExpirable()) == head)
                {
/*3299*/            return null;
                } else
                {
/*3302*/            remove(head1);
/*3303*/            return head1;
                }
            }

            public final boolean remove(Object obj)
            {
/*3309*/        remove remove1 = ((remove) (obj = (remove)obj)).etPreviousExpirable();
/*3311*/        remove remove2 = ((etPreviousExpirable) (obj)).etNextExpirable();
/*3312*/        MapMakerInternalMap.connectExpirables(remove1, remove2);
/*3313*/        MapMakerInternalMap.nullifyExpirable((() (obj)));
/*3315*/        return remove2 != CE;
            }

            public final boolean contains(Object obj)
            {
/*3321*/        return ((CE) (obj = (CE)obj)).etNextExpirable() != CE;
            }

            public final boolean isEmpty()
            {
/*3327*/        return head.etNextExpirable() == head;
            }

            public final int size()
            {
/*3332*/        int i = 0;
/*3333*/        for(head head1 = head.etNextExpirable(); head1 != head; head1 = head1.etNextExpirable())
/*3334*/            i++;

/*3336*/        return i;
            }

            public final void clear()
            {
                etNextExpirable etnextexpirable1;
/*3341*/        for(etNextExpirable etnextexpirable = head.etNextExpirable(); etnextexpirable != head; etnextexpirable = etnextexpirable1)
                {
/*3343*/            etnextexpirable1 = etnextexpirable.etNextExpirable();
/*3344*/            MapMakerInternalMap.nullifyExpirable(etnextexpirable);
                }

/*3348*/        head.etNextExpirable(head);
/*3349*/        head.etPreviousExpirable(head);
            }

            public final Iterator iterator()
            {
/*3354*/        return new AbstractSequentialIterator(peek()) {

                    protected MapMakerInternalMap.ReferenceEntry computeNext(MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/*3357*/                if((referenceentry = referenceentry.getNextExpirable()) == head)
/*3358*/                    return null;
/*3358*/                else
/*3358*/                    return referenceentry;
                    }

                    protected volatile Object computeNext(Object obj)
                    {
/*3354*/                return computeNext((MapMakerInternalMap.ReferenceEntry)obj);
                    }

                    final MapMakerInternalMap.ExpirationQueue this$0;

                    
                    {
/*3354*/                this$0 = MapMakerInternalMap.ExpirationQueue.this;
/*3354*/                super(referenceentry);
                    }
        };
            }

            public final volatile Object peek()
            {
/*3239*/        return peek();
            }

            public final volatile Object poll()
            {
/*3239*/        return poll();
            }

            public final volatile boolean offer(Object obj)
            {
/*3239*/        return offer((offer)obj);
            }

            final offer head = new MapMakerInternalMap.AbstractReferenceEntry() {

                public long getExpirationTime()
                {
/*3244*/            return 0x7fffffffffffffffL;
                }

                public void setExpirationTime(long l)
                {
                }

                public MapMakerInternalMap.ReferenceEntry getNextExpirable()
                {
/*3254*/            return nextExpirable;
                }

                public void setNextExpirable(MapMakerInternalMap.ReferenceEntry referenceentry)
                {
/*3259*/            nextExpirable = referenceentry;
                }

                public MapMakerInternalMap.ReferenceEntry getPreviousExpirable()
                {
/*3266*/            return previousExpirable;
                }

                public void setPreviousExpirable(MapMakerInternalMap.ReferenceEntry referenceentry)
                {
/*3271*/            previousExpirable = referenceentry;
                }

                MapMakerInternalMap.ReferenceEntry nextExpirable;
                MapMakerInternalMap.ReferenceEntry previousExpirable;
                final MapMakerInternalMap.ExpirationQueue this$0;

                    
                    {
/*3240*/                this$0 = MapMakerInternalMap.ExpirationQueue.this;
/*3240*/                super();
/*3250*/                nextExpirable = this;
/*3262*/                previousExpirable = this;
                    }
    };

            _cls1.previousExpirable()
            {
            }
}
