// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Pair.java

package com.google.zxing.oned.rss;


// Referenced classes of package com.google.zxing.oned.rss:
//            DataCharacter, FinderPattern

final class Pair extends DataCharacter
{

            Pair(int i, int j, FinderPattern finderpattern)
            {
/*  25*/        super(i, j);
/*  26*/        finderPattern = finderpattern;
            }

            final FinderPattern getFinderPattern()
            {
/*  30*/        return finderPattern;
            }

            final int getCount()
            {
/*  34*/        return count;
            }

            final void incrementCount()
            {
/*  38*/        count++;
            }

            private final FinderPattern finderPattern;
            private int count;
}
