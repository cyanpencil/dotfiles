// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ExpandedPair.java

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import java.io.Serializable;

final class ExpandedPair
{

            ExpandedPair(DataCharacter datacharacter, DataCharacter datacharacter1, FinderPattern finderpattern, boolean flag)
            {
/*  46*/        leftChar = datacharacter;
/*  47*/        rightChar = datacharacter1;
/*  48*/        finderPattern = finderpattern;
/*  49*/        mayBeLast = flag;
            }

            final boolean mayBeLast()
            {
/*  53*/        return mayBeLast;
            }

            final DataCharacter getLeftChar()
            {
/*  57*/        return leftChar;
            }

            final DataCharacter getRightChar()
            {
/*  61*/        return rightChar;
            }

            final FinderPattern getFinderPattern()
            {
/*  65*/        return finderPattern;
            }

            public final boolean mustBeLast()
            {
/*  69*/        return rightChar == null;
            }

            public final String toString()
            {
/*  74*/        return (new StringBuilder("[ ")).append(leftChar).append(" , ").append(rightChar).append(" : ").append(finderPattern != null ? ((Object) (Integer.valueOf(finderPattern.getValue()))) : "null").append(" ]").toString();
            }

            public final boolean equals(Object obj)
            {
/*  81*/        if(!(obj instanceof ExpandedPair))
/*  82*/            return false;
/*  84*/        obj = (ExpandedPair)obj;
/*  85*/        return equalsOrNull(leftChar, ((ExpandedPair) (obj)).leftChar) && equalsOrNull(rightChar, ((ExpandedPair) (obj)).rightChar) && equalsOrNull(finderPattern, ((ExpandedPair) (obj)).finderPattern);
            }

            private static boolean equalsOrNull(Object obj, Object obj1)
            {
/*  92*/        if(obj == null)
/*  92*/            return obj1 == null;
/*  92*/        else
/*  92*/            return obj.equals(obj1);
            }

            public final int hashCode()
            {
/*  97*/        return hashNotNull(leftChar) ^ hashNotNull(rightChar) ^ hashNotNull(finderPattern);
            }

            private static int hashNotNull(Object obj)
            {
/* 101*/        if(obj == null)
/* 101*/            return 0;
/* 101*/        else
/* 101*/            return obj.hashCode();
            }

            private final boolean mayBeLast;
            private final DataCharacter leftChar;
            private final DataCharacter rightChar;
            private final FinderPattern finderPattern;
}
