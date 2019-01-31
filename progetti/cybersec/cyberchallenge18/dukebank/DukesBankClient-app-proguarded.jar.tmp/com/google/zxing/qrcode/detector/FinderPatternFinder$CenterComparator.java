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
/* 664*/        if(finderpattern1.getCount() == finderpattern.getCount())
                {
/* 665*/            finderpattern1 = Math.abs(finderpattern1.getEstimatedModuleSize() - average);
/* 666*/            finderpattern = Math.abs(finderpattern.getEstimatedModuleSize() - average);
/* 667*/            if(finderpattern1 < finderpattern)
/* 667*/                return 1;
/* 667*/            return finderpattern1 != finderpattern ? -1 : 0;
                } else
                {
/* 669*/            return finderpattern1.getCount() - finderpattern.getCount();
                }
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 657*/        return compare((FinderPattern)obj, (FinderPattern)obj1);
            }

            private final float average;

            private (float f)
            {
/* 660*/        average = f;
            }

            average(float f, average average1)
            {
/* 657*/        this(f);
            }
}
