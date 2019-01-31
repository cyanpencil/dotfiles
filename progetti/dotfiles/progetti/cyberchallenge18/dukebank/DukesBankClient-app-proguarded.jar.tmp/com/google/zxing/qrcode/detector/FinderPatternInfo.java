// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FinderPatternInfo.java

package com.google.zxing.qrcode.detector;


// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern

public final class FinderPatternInfo
{

            public FinderPatternInfo(FinderPattern afinderpattern[])
            {
/*  32*/        bottomLeft = afinderpattern[0];
/*  33*/        topLeft = afinderpattern[1];
/*  34*/        topRight = afinderpattern[2];
            }

            public final FinderPattern getBottomLeft()
            {
/*  38*/        return bottomLeft;
            }

            public final FinderPattern getTopLeft()
            {
/*  42*/        return topLeft;
            }

            public final FinderPattern getTopRight()
            {
/*  46*/        return topRight;
            }

            private final FinderPattern bottomLeft;
            private final FinderPattern topLeft;
            private final FinderPattern topRight;
}
