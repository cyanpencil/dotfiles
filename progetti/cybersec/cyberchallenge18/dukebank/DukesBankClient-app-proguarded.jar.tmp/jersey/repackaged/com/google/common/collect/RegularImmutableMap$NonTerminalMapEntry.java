// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RegularImmutableMap.java

package jersey.repackaged.com.google.common.collect;


// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            ImmutableMapEntry, RegularImmutableMap

static final class nextInKeyBucket extends ImmutableMapEntry
{

            final ImmutableMapEntry getNextInKeyBucket()
            {
/* 123*/        return nextInKeyBucket;
            }

            private final ImmutableMapEntry nextInKeyBucket;

            Y(Object obj, Object obj1, ImmutableMapEntry immutablemapentry)
            {
/* 112*/        super(obj, obj1);
/* 113*/        nextInKeyBucket = immutablemapentry;
            }

            nextInKeyBucket(ImmutableMapEntry immutablemapentry, ImmutableMapEntry immutablemapentry1)
            {
/* 117*/        super(immutablemapentry);
/* 118*/        nextInKeyBucket = immutablemapentry1;
            }
}
