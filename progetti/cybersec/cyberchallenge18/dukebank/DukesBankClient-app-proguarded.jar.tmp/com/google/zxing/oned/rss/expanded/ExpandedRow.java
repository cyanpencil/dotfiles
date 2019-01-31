// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExpandedRow.java

package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow
{

            ExpandedRow(List list, int i, boolean flag)
            {
/*  33*/        pairs = new ArrayList(list);
/*  34*/        rowNumber = i;
/*  35*/        wasReversed = flag;
            }

            final List getPairs()
            {
/*  39*/        return pairs;
            }

            final int getRowNumber()
            {
/*  43*/        return rowNumber;
            }

            final boolean isReversed()
            {
/*  47*/        return wasReversed;
            }

            final boolean isEquivalent(List list)
            {
/*  51*/        return pairs.equals(list);
            }

            public final String toString()
            {
/*  56*/        return (new StringBuilder("{ ")).append(pairs).append(" }").toString();
            }

            public final boolean equals(Object obj)
            {
/*  64*/        if(!(obj instanceof ExpandedRow))
/*  65*/            return false;
/*  67*/        obj = (ExpandedRow)obj;
/*  68*/        return pairs.equals(((ExpandedRow) (obj)).getPairs()) && wasReversed == ((ExpandedRow) (obj)).wasReversed;
            }

            public final int hashCode()
            {
/*  73*/        return pairs.hashCode() ^ Boolean.valueOf(wasReversed).hashCode();
            }

            private final List pairs;
            private final int rowNumber;
            private final boolean wasReversed;
}
