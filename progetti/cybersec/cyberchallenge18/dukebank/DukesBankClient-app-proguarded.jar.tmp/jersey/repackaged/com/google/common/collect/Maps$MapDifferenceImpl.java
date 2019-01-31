// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Maps.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;
import jersey.repackaged.com.google.common.base.Objects;

// Referenced classes of package jersey.repackaged.com.google.common.collect:
//            MapDifference, Maps

static class differences
    implements MapDifference
{

            public boolean areEqual()
            {
/* 470*/        return onlyOnLeft.isEmpty() && onlyOnRight.isEmpty() && differences.isEmpty();
            }

            public Map entriesOnlyOnLeft()
            {
/* 475*/        return onlyOnLeft;
            }

            public Map entriesOnlyOnRight()
            {
/* 480*/        return onlyOnRight;
            }

            public Map entriesInCommon()
            {
/* 485*/        return onBoth;
            }

            public Map entriesDiffering()
            {
/* 490*/        return differences;
            }

            public boolean equals(Object obj)
            {
/* 494*/        if(obj == this)
/* 495*/            return true;
/* 497*/        if(obj instanceof MapDifference)
                {
/* 498*/            obj = (MapDifference)obj;
/* 499*/            return entriesOnlyOnLeft().equals(((MapDifference) (obj)).entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(((MapDifference) (obj)).entriesOnlyOnRight()) && entriesInCommon().equals(((MapDifference) (obj)).entriesInCommon()) && entriesDiffering().equals(((MapDifference) (obj)).entriesDiffering());
                } else
                {
/* 504*/            return false;
                }
            }

            public int hashCode()
            {
/* 508*/        return Objects.hashCode(new Object[] {
/* 508*/            entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering()
                });
            }

            public String toString()
            {
/* 513*/        if(areEqual())
/* 514*/            return "equal";
/* 517*/        StringBuilder stringbuilder = new StringBuilder("not equal");
/* 518*/        if(!onlyOnLeft.isEmpty())
/* 519*/            stringbuilder.append(": only on left=").append(onlyOnLeft);
/* 521*/        if(!onlyOnRight.isEmpty())
/* 522*/            stringbuilder.append(": only on right=").append(onlyOnRight);
/* 524*/        if(!differences.isEmpty())
/* 525*/            stringbuilder.append(": value differences=").append(differences);
/* 527*/        return stringbuilder.toString();
            }

            final Map onlyOnLeft;
            final Map onlyOnRight;
            final Map onBoth;
            final Map differences;

            erence(Map map, Map map1, Map map2, Map map3)
            {
/* 462*/        onlyOnLeft = Maps.access$100(map);
/* 463*/        onlyOnRight = Maps.access$100(map1);
/* 464*/        onBoth = Maps.access$100(map2);
/* 465*/        differences = Maps.access$100(map3);
            }
}
