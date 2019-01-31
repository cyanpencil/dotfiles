// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import java.util.SortedMap;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapDifference, Maps, SortedMapDifference

static class  extends 
    implements SortedMapDifference
{

            public SortedMap entriesDiffering()
            {
/* 617*/        return (SortedMap)super.sDiffering();
            }

            public SortedMap entriesInCommon()
            {
/* 621*/        return (SortedMap)super.sInCommon();
            }

            public SortedMap entriesOnlyOnLeft()
            {
/* 625*/        return (SortedMap)super.sOnlyOnLeft();
            }

            public SortedMap entriesOnlyOnRight()
            {
/* 629*/        return (SortedMap)super.sOnlyOnRight();
            }

            public volatile Map entriesDiffering()
            {
/* 608*/        return entriesDiffering();
            }

            public volatile Map entriesInCommon()
            {
/* 608*/        return entriesInCommon();
            }

            public volatile Map entriesOnlyOnRight()
            {
/* 608*/        return entriesOnlyOnRight();
            }

            public volatile Map entriesOnlyOnLeft()
            {
/* 608*/        return entriesOnlyOnLeft();
            }

            (SortedMap sortedmap, SortedMap sortedmap1, SortedMap sortedmap2, SortedMap sortedmap3)
            {
/* 613*/        super(sortedmap, sortedmap1, sortedmap2, sortedmap3);
            }
}
