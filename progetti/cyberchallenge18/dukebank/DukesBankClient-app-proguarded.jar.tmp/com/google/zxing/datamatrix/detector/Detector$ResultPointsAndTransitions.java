// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.datamatrix.detector;

import com.google.zxing.ResultPoint;

// Referenced classes of package com.google.zxing.datamatrix.detector:
//            Detector

static final class <init>
{

            final ResultPoint getFrom()
            {
/* 412*/        return from;
            }

            final ResultPoint getTo()
            {
/* 416*/        return to;
            }

            public final int getTransitions()
            {
/* 420*/        return transitions;
            }

            public final String toString()
            {
/* 425*/        return (new StringBuilder()).append(from).append("/").append(to).append('/').append(transitions).toString();
            }

            private final ResultPoint from;
            private final ResultPoint to;
            private final int transitions;

            private (ResultPoint resultpoint, ResultPoint resultpoint1, int i)
            {
/* 406*/        from = resultpoint;
/* 407*/        to = resultpoint1;
/* 408*/        transitions = i;
            }

            transitions(ResultPoint resultpoint, ResultPoint resultpoint1, int i, transitions transitions1)
            {
/* 399*/        this(resultpoint, resultpoint1, i);
            }
}
