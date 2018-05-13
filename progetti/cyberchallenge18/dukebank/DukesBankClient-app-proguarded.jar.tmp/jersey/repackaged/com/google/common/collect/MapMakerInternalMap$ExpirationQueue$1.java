// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

class previousExpirable extends ntry
{

            public long getExpirationTime()
            {
/*3244*/        return 0x7fffffffffffffffL;
            }

            public void setExpirationTime(long l)
            {
            }

            public ntry getNextExpirable()
            {
/*3254*/        return nextExpirable;
            }

            public void setNextExpirable(nextExpirable nextexpirable)
            {
/*3259*/        nextExpirable = nextexpirable;
            }

            public nextExpirable getPreviousExpirable()
            {
/*3266*/        return previousExpirable;
            }

            public void setPreviousExpirable(previousExpirable previousexpirable)
            {
/*3271*/        previousExpirable = previousexpirable;
            }

            previousExpirable nextExpirable;
            previousExpirable previousExpirable;
            final previousExpirable this$0;

            ntry()
            {
/*3240*/        this$0 = this._cls0.this;
/*3240*/        super();
/*3250*/        nextExpirable = this;
/*3262*/        previousExpirable = this;
            }
}
