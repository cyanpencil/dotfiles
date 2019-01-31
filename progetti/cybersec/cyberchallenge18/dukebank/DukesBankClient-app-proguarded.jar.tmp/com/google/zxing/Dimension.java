// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Dimension.java

package com.google.zxing;


public final class Dimension
{

            public Dimension(int i, int j)
            {
/*  28*/        if(i < 0 || j < 0)
                {
/*  29*/            throw new IllegalArgumentException();
                } else
                {
/*  31*/            width = i;
/*  32*/            height = j;
/*  33*/            return;
                }
            }

            public final int getWidth()
            {
/*  36*/        return width;
            }

            public final int getHeight()
            {
/*  40*/        return height;
            }

            public final boolean equals(Object obj)
            {
/*  45*/        if(obj instanceof Dimension)
                {
/*  46*/            obj = (Dimension)obj;
/*  47*/            return width == ((Dimension) (obj)).width && height == ((Dimension) (obj)).height;
                } else
                {
/*  49*/            return false;
                }
            }

            public final int hashCode()
            {
/*  54*/        return width * 32713 + height;
            }

            public final String toString()
            {
/*  59*/        return (new StringBuilder()).append(width).append("x").append(height).toString();
            }

            private final int width;
            private final int height;
}
