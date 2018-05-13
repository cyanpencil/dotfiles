// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AztecCode.java

package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitMatrix;

public final class AztecCode
{

            public AztecCode()
            {
            }

            public final boolean isCompact()
            {
/*  38*/        return compact;
            }

            public final void setCompact(boolean flag)
            {
/*  42*/        compact = flag;
            }

            public final int getSize()
            {
/*  49*/        return size;
            }

            public final void setSize(int i)
            {
/*  53*/        size = i;
            }

            public final int getLayers()
            {
/*  60*/        return layers;
            }

            public final void setLayers(int i)
            {
/*  64*/        layers = i;
            }

            public final int getCodeWords()
            {
/*  71*/        return codeWords;
            }

            public final void setCodeWords(int i)
            {
/*  75*/        codeWords = i;
            }

            public final BitMatrix getMatrix()
            {
/*  82*/        return matrix;
            }

            public final void setMatrix(BitMatrix bitmatrix)
            {
/*  86*/        matrix = bitmatrix;
            }

            private boolean compact;
            private int size;
            private int layers;
            private int codeWords;
            private BitMatrix matrix;
}
