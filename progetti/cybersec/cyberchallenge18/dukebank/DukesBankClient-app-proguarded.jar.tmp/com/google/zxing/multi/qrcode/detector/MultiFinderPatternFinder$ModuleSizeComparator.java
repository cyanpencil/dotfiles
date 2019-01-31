// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MultiFinderPatternFinder.java

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.qrcode.detector.FinderPattern;
import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package com.google.zxing.multi.qrcode.detector:
//            MultiFinderPatternFinder

static final class <init>
    implements Serializable, Comparator
{

            public final int compare(FinderPattern finderpattern, FinderPattern finderpattern1)
            {
/*  82*/        if((double)(finderpattern = finderpattern1.getEstimatedModuleSize() - finderpattern.getEstimatedModuleSize()) < 0.0D)
/*  83*/            return -1;
/*  83*/        return (double)finderpattern <= 0.0D ? 0 : 1;
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/*  79*/        return compare((FinderPattern)obj, (FinderPattern)obj1);
            }

            private ()
            {
            }

            ( )
            {
/*  79*/        this();
            }
}
