// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class previousExpirable extends previousExpirable
    implements previousExpirable
{

            public final long getExpirationTime()
            {
/*1023*/        return time;
            }

            public final void setExpirationTime(long l)
            {
/*1028*/        time = l;
            }

            public final time getNextExpirable()
            {
/*1036*/        return nextExpirable;
            }

            public final void setNextExpirable(nextExpirable nextexpirable)
            {
/*1041*/        nextExpirable = nextexpirable;
            }

            public final nextExpirable getPreviousExpirable()
            {
/*1049*/        return previousExpirable;
            }

            public final void setPreviousExpirable(previousExpirable previousexpirable)
            {
/*1054*/        previousExpirable = previousexpirable;
            }

            volatile long time;
            previousExpirable nextExpirable;
            previousExpirable previousExpirable;

            (Object obj, int i,  )
            {
/*1014*/        super(obj, i, );
/*1019*/        time = 0x7fffffffffffffffL;
/*1032*/        nextExpirable = MapMakerInternalMap.nullEntry();
/*1045*/        previousExpirable = MapMakerInternalMap.nullEntry();
            }
}
