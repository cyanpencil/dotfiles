// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Config.java

package com.google.zxing.client.j2se;

import java.util.Map;

final class Config
{

            Config()
            {
            }

            final Map getHints()
            {
/*  38*/        return hints;
            }

            final void setHints(Map map)
            {
/*  42*/        hints = map;
            }

            final boolean isTryHarder()
            {
/*  46*/        return tryHarder;
            }

            final void setTryHarder(boolean flag)
            {
/*  50*/        tryHarder = flag;
            }

            final boolean isPureBarcode()
            {
/*  54*/        return pureBarcode;
            }

            final void setPureBarcode(boolean flag)
            {
/*  58*/        pureBarcode = flag;
            }

            final boolean isProductsOnly()
            {
/*  62*/        return productsOnly;
            }

            final void setProductsOnly(boolean flag)
            {
/*  66*/        productsOnly = flag;
            }

            final boolean isDumpResults()
            {
/*  70*/        return dumpResults;
            }

            final void setDumpResults(boolean flag)
            {
/*  74*/        dumpResults = flag;
            }

            final boolean isDumpBlackPoint()
            {
/*  78*/        return dumpBlackPoint;
            }

            final void setDumpBlackPoint(boolean flag)
            {
/*  82*/        dumpBlackPoint = flag;
            }

            final boolean isMulti()
            {
/*  86*/        return multi;
            }

            final void setMulti(boolean flag)
            {
/*  90*/        multi = flag;
            }

            final boolean isBrief()
            {
/*  94*/        return brief;
            }

            final void setBrief(boolean flag)
            {
/*  98*/        brief = flag;
            }

            final boolean isRecursive()
            {
/* 102*/        return recursive;
            }

            final void setRecursive(boolean flag)
            {
/* 106*/        recursive = flag;
            }

            final int[] getCrop()
            {
/* 110*/        return crop;
            }

            final void setCrop(int ai[])
            {
/* 114*/        crop = ai;
            }

            final String[] getPossibleFormats()
            {
/* 118*/        return possibleFormats;
            }

            final void setPossibleFormats(String as[])
            {
/* 122*/        possibleFormats = as;
            }

            private Map hints;
            private boolean tryHarder;
            private boolean pureBarcode;
            private boolean productsOnly;
            private boolean dumpResults;
            private boolean dumpBlackPoint;
            private boolean multi;
            private boolean brief;
            private boolean recursive;
            private int crop[];
            private String possibleFormats[];
}
