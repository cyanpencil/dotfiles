// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

static final class previousEvictable extends previousEvictable
    implements previousEvictable
{

            public final long getExpirationTime()
            {
/*1105*/        return time;
            }

            public final void setExpirationTime(long l)
            {
/*1110*/        time = l;
            }

            public final time getNextExpirable()
            {
/*1118*/        return nextExpirable;
            }

            public final void setNextExpirable(nextExpirable nextexpirable)
            {
/*1123*/        nextExpirable = nextexpirable;
            }

            public final nextExpirable getPreviousExpirable()
            {
/*1131*/        return previousExpirable;
            }

            public final void setPreviousExpirable(previousExpirable previousexpirable)
            {
/*1136*/        previousExpirable = previousexpirable;
            }

            public final previousExpirable getNextEvictable()
            {
/*1146*/        return nextEvictable;
            }

            public final void setNextEvictable(nextEvictable nextevictable)
            {
/*1151*/        nextEvictable = nextevictable;
            }

            public final nextEvictable getPreviousEvictable()
            {
/*1159*/        return previousEvictable;
            }

            public final void setPreviousEvictable(previousEvictable previousevictable)
            {
/*1164*/        previousEvictable = previousevictable;
            }

            volatile long time;
            previousEvictable nextExpirable;
            previousEvictable previousExpirable;
            previousEvictable nextEvictable;
            previousEvictable previousEvictable;

            (Object obj, int i,  )
            {
/*1096*/        super(obj, i, );
/*1101*/        time = 0x7fffffffffffffffL;
/*1114*/        nextExpirable = MapMakerInternalMap.nullEntry();
/*1127*/        previousExpirable = MapMakerInternalMap.nullEntry();
/*1142*/        nextEvictable = MapMakerInternalMap.nullEntry();
/*1155*/        previousEvictable = MapMakerInternalMap.nullEntry();
            }
}
