// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   PDF417ResultMetadata.java

package com.google.zxing.pdf417;


public final class PDF417ResultMetadata
{

            public PDF417ResultMetadata()
            {
            }

            public final int getSegmentIndex()
            {
/*  30*/        return segmentIndex;
            }

            public final void setSegmentIndex(int i)
            {
/*  34*/        segmentIndex = i;
            }

            public final String getFileId()
            {
/*  38*/        return fileId;
            }

            public final void setFileId(String s)
            {
/*  42*/        fileId = s;
            }

            public final int[] getOptionalData()
            {
/*  46*/        return optionalData;
            }

            public final void setOptionalData(int ai[])
            {
/*  50*/        optionalData = ai;
            }

            public final boolean isLastSegment()
            {
/*  54*/        return lastSegment;
            }

            public final void setLastSegment(boolean flag)
            {
/*  58*/        lastSegment = flag;
            }

            private int segmentIndex;
            private String fileId;
            private int optionalData[];
            private boolean lastSegment;
}
