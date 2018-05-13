// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;

import java.lang.ref.ReferenceQueue;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class previousExpirable extends previousExpirable
    implements previousExpirable
{

            public final long getExpirationTime()
            {
/*1536*/        return time;
            }

            public final void setExpirationTime(long l)
            {
/*1541*/        time = l;
            }

            public final time getNextExpirable()
            {
/*1549*/        return nextExpirable;
            }

            public final void setNextExpirable(nextExpirable nextexpirable)
            {
/*1554*/        nextExpirable = nextexpirable;
            }

            public final nextExpirable getPreviousExpirable()
            {
/*1562*/        return previousExpirable;
            }

            public final void setPreviousExpirable(previousExpirable previousexpirable)
            {
/*1567*/        previousExpirable = previousexpirable;
            }

            volatile long time;
            previousExpirable nextExpirable;
            previousExpirable previousExpirable;

            (ReferenceQueue referencequeue, Object obj, int i,  )
            {
/*1527*/        super(referencequeue, obj, i, );
/*1532*/        time = 0x7fffffffffffffffL;
/*1545*/        nextExpirable = MapMakerInternalMap.nullEntry();
/*1558*/        previousExpirable = MapMakerInternalMap.nullEntry();
            }
}
