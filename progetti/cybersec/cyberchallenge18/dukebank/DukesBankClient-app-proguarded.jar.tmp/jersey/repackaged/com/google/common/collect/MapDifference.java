// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MapDifference.java

package jersey.repackaged.com.google.common.collect;

import java.util.Map;

public interface MapDifference
{
    public static interface ValueDifference
    {

        public abstract Object leftValue();

        public abstract Object rightValue();
    }


    public abstract Map entriesOnlyOnLeft();

    public abstract Map entriesOnlyOnRight();

    public abstract Map entriesInCommon();

    public abstract Map entriesDiffering();
}
