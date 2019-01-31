// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Detector.java

package com.google.zxing.datamatrix.detector;

import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package com.google.zxing.datamatrix.detector:
//            Detector

static final class <init>
    implements Serializable, Comparator
{

            public final int compare(<init> <init>1, <init> <init>2)
            {
/* 436*/        return <init>1.ions() - <init>2.ions();
            }

            public final volatile int compare(Object obj, Object obj1)
            {
/* 432*/        return compare((compare)obj, (compare)obj1);
            }

            private ()
            {
            }

            ( )
            {
/* 432*/        this();
            }
}
