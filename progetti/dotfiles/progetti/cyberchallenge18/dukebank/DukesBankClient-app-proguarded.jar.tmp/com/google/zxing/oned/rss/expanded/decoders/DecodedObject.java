// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedObject.java

package com.google.zxing.oned.rss.expanded.decoders;


abstract class DecodedObject
{

            DecodedObject(int i)
            {
/*  37*/        newPosition = i;
            }

            final int getNewPosition()
            {
/*  41*/        return newPosition;
            }

            private final int newPosition;
}
