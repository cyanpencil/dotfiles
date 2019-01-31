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

            public final previousEvictable getNextEvictable()
            {
/*1071*/        return nextEvictable;
            }

            public final void setNextEvictable(nextEvictable nextevictable)
            {
/*1076*/        nextEvictable = nextevictable;
            }

            public final nextEvictable getPreviousEvictable()
            {
/*1084*/        return previousEvictable;
            }

            public final void setPreviousEvictable(previousEvictable previousevictable)
            {
/*1089*/        previousEvictable = previousevictable;
            }

            previousEvictable nextEvictable;
            previousEvictable previousEvictable;

            (Object obj, int i,  )
            {
/*1061*/        super(obj, i, );
/*1067*/        nextEvictable = MapMakerInternalMap.nullEntry();
/*1080*/        previousEvictable = MapMakerInternalMap.nullEntry();
            }
}
