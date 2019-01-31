// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class previousEvictable extends previousEvictable
    implements previousEvictable
{

            public final previousEvictable getNextEvictable()
            {
/*1585*/        return nextEvictable;
            }

            public final void setNextEvictable(nextEvictable nextevictable)
            {
/*1590*/        nextEvictable = nextevictable;
            }

            public final nextEvictable getPreviousEvictable()
            {
/*1598*/        return previousEvictable;
            }

            public final void setPreviousEvictable(previousEvictable previousevictable)
            {
/*1603*/        previousEvictable = previousevictable;
            }

            previousEvictable nextEvictable;
            previousEvictable previousEvictable;

            (ReferenceQueue referencequeue, Object obj, int i,  )
            {
/*1575*/        super(referencequeue, obj, i, );
/*1581*/        nextEvictable = MapMakerInternalMap.nullEntry();
/*1594*/        previousEvictable = MapMakerInternalMap.nullEntry();
            }
}
