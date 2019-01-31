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

            public final long getExpirationTime()
            {
/*1620*/        return time;
            }

            public final void setExpirationTime(long l)
            {
/*1625*/        time = l;
            }

            public final time getNextExpirable()
            {
/*1633*/        return nextExpirable;
            }

            public final void setNextExpirable(nextExpirable nextexpirable)
            {
/*1638*/        nextExpirable = nextexpirable;
            }

            public final nextExpirable getPreviousExpirable()
            {
/*1646*/        return previousExpirable;
            }

            public final void setPreviousExpirable(previousExpirable previousexpirable)
            {
/*1651*/        previousExpirable = previousexpirable;
            }

            public final previousExpirable getNextEvictable()
            {
/*1661*/        return nextEvictable;
            }

            public final void setNextEvictable(nextEvictable nextevictable)
            {
/*1666*/        nextEvictable = nextevictable;
            }

            public final nextEvictable getPreviousEvictable()
            {
/*1674*/        return previousEvictable;
            }

            public final void setPreviousEvictable(previousEvictable previousevictable)
            {
/*1679*/        previousEvictable = previousevictable;
            }

            volatile long time;
            previousEvictable nextExpirable;
            previousEvictable previousExpirable;
            previousEvictable nextEvictable;
            previousEvictable previousEvictable;

            a(ReferenceQueue referencequeue, Object obj, int i, a a)
            {
/*1611*/        super(referencequeue, obj, i, a);
/*1616*/        time = 0x7fffffffffffffffL;
/*1629*/        nextExpirable = MapMakerInternalMap.nullEntry();
/*1642*/        previousExpirable = MapMakerInternalMap.nullEntry();
/*1657*/        nextEvictable = MapMakerInternalMap.nullEntry();
/*1670*/        previousEvictable = MapMakerInternalMap.nullEntry();
            }
}
