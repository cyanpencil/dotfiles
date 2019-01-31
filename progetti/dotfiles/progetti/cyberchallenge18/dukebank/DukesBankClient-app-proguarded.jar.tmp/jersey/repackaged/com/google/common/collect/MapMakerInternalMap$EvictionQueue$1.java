// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapMakerInternalMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapMakerInternalMap

class previousEvictable extends eEntry
{

            public eEntry getNextEvictable()
            {
/*3118*/        return nextEvictable;
            }

            public void setNextEvictable(nextEvictable nextevictable)
            {
/*3123*/        nextEvictable = nextevictable;
            }

            public nextEvictable getPreviousEvictable()
            {
/*3130*/        return previousEvictable;
            }

            public void setPreviousEvictable(previousEvictable previousevictable)
            {
/*3135*/        previousEvictable = previousevictable;
            }

            previousEvictable nextEvictable;
            previousEvictable previousEvictable;
            final previousEvictable this$0;

            eEntry()
            {
/*3112*/        this$0 = this._cls0.this;
/*3112*/        super();
/*3114*/        nextEvictable = this;
/*3126*/        previousEvictable = this;
            }
}
