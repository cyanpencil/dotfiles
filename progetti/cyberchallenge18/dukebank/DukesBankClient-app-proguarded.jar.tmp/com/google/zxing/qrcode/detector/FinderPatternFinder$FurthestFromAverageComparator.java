// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FinderPatternFinder.java

package com.google.zxing.qrcode.detector;

import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern, FinderPatternFinder

static final class <init>
    implements Serializable, Comparator
{

            public final int compare(FinderPattern finderpattern, FinderPattern finderpattern1)
            {
/* 648*/        finderpattern1 = Math.abs(finderpattern1.getEstimatedModuleSize() - average);
/* 649*/        finderpattern = Math.abs(finderpattern.getEstimatedModuleSize() - average);
/* 650*/        if(finderpattern1 < finderpattern)
/* 650*/            return -1;
/* 650*/        return finderpattern1 != finderpattern ? 1 : 0;
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 641*/        return compare((FinderPattern)obj, (FinderPattern)obj1);
            }

            private final float average;

            private (float f)
            {
/* 644*/        average = f;
            }

            average(float f, average average1)
            {
/* 641*/        this(f);
            }
}
