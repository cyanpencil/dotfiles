// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.util.AbstractQueue;
import java.util.Iterator;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap, AbstractSequentialIterator

static final class _cls1.previousEvictable extends AbstractQueue
{

            public final boolean offer( )
            {
/*3144*/        MapMakerInternalMap.connectEvictables(.getPreviousEvictable(), .getNextEvictable());
/*3147*/        MapMakerInternalMap.connectEvictables(head.getPreviousEvictable(), );
/*3148*/        MapMakerInternalMap.connectEvictables(, head);
/*3150*/        return true;
            }

            public final  peek()
            {
                 ;
/*3155*/        if(( = head.getNextEvictable()) == head)
/*3156*/            return null;
/*3156*/        else
/*3156*/            return ;
            }

            public final  poll()
            {
                 ;
/*3161*/        if(( = head.getNextEvictable()) == head)
                {
/*3163*/            return null;
                } else
                {
/*3166*/            remove();
/*3167*/            return ;
                }
            }

            public final boolean remove(Object obj)
            {
/*3173*/          = (() (obj = ()obj)).getPreviousEvictable();
/*3175*/         1 = (() (obj)).getNextEvictable();
/*3176*/        MapMakerInternalMap.connectEvictables(, 1);
/*3177*/        MapMakerInternalMap.nullifyEvictable((() (obj)));
/*3179*/        return 1 != ANCE;
            }

            public final boolean contains(Object obj)
            {
/*3185*/        return (() (obj = ()obj)).getNextEvictable() != ANCE;
            }

            public final boolean isEmpty()
            {
/*3191*/        return head.getNextEvictable() == head;
            }

            public final int size()
            {
/*3196*/        int i = 0;
/*3197*/        for(  = head.getNextEvictable();  != head;  = .getNextEvictable())
/*3198*/            i++;

/*3200*/        return i;
            }

            public final void clear()
            {
                 1;
/*3205*/        for(  = head.getNextEvictable();  != head;  = 1)
                {
/*3207*/            1 = .getNextEvictable();
/*3208*/            MapMakerInternalMap.nullifyEvictable();
                }

/*3212*/        head.setNextEvictable(head);
/*3213*/        head.setPreviousEvictable(head);
            }

            public final Iterator iterator()
            {
/*3218*/        return new AbstractSequentialIterator(peek()) {

                    protected MapMakerInternalMap.ReferenceEntry computeNext(MapMakerInternalMap.ReferenceEntry referenceentry)
                    {
/*3221*/                if((referenceentry = referenceentry.getNextEvictable()) == head)
/*3222*/                    return null;
/*3222*/                else
/*3222*/                    return referenceentry;
                    }

                    protected volatile Object computeNext(Object obj)
                    {
/*3218*/                return computeNext((MapMakerInternalMap.ReferenceEntry)obj);
                    }

                    final MapMakerInternalMap.EvictionQueue this$0;

                    
                    {
/*3218*/                this$0 = MapMakerInternalMap.EvictionQueue.this;
/*3218*/                super(referenceentry);
                    }
        };
            }

            public final volatile Object peek()
            {
/*3111*/        return peek();
            }

            public final volatile Object poll()
            {
/*3111*/        return poll();
            }

            public final volatile boolean offer(Object obj)
            {
/*3111*/        return offer(()obj);
            }

            final  head = new MapMakerInternalMap.AbstractReferenceEntry() {

                public MapMakerInternalMap.ReferenceEntry getNextEvictable()
                {
/*3118*/            return nextEvictable;
                }

                public void setNextEvictable(MapMakerInternalMap.ReferenceEntry referenceentry)
                {
/*3123*/            nextEvictable = referenceentry;
                }

                public MapMakerInternalMap.ReferenceEntry getPreviousEvictable()
                {
/*3130*/            return previousEvictable;
                }

                public void setPreviousEvictable(MapMakerInternalMap.ReferenceEntry referenceentry)
                {
/*3135*/            previousEvictable = referenceentry;
                }

                MapMakerInternalMap.ReferenceEntry nextEvictable;
                MapMakerInternalMap.ReferenceEntry previousEvictable;
                final MapMakerInternalMap.EvictionQueue this$0;

                    
                    {
/*3112*/                this$0 = MapMakerInternalMap.EvictionQueue.this;
/*3112*/                super();
/*3114*/                nextEvictable = this;
/*3126*/                previousEvictable = this;
                    }
    };

            _cls1.previousEvictable()
            {
            }
}
