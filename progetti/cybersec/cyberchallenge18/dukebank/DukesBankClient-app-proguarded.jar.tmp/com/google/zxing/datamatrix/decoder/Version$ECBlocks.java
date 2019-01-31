// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Version.java

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

static final class <init>
{

            final int getECCodewords()
            {
/* 133*/        return ecCodewords;
            }

            final ecCodewords[] getECBlocks()
            {
/* 137*/        return ecBlocks;
            }

            private final int ecCodewords;
            private final ecBlocks ecBlocks[];

            private (int i,  )
            {
/* 123*/        ecCodewords = i;
/* 124*/        ecBlocks = (new ecBlocks[] {
/* 124*/            
                });
            }

            private ecBlocks(int i, ecBlocks ecblocks, ecBlocks ecblocks1)
            {
/* 128*/        ecCodewords = i;
/* 129*/        ecBlocks = (new ecBlocks[] {
/* 129*/            ecblocks, ecblocks1
                });
            }

            ecBlocks(int i, ecBlocks ecblocks, ecBlocks ecblocks1)
            {
/* 118*/        this(i, ecblocks);
            }

            <init>(int i, <init> <init>1, <init> <init>2, <init> <init>3)
            {
/* 118*/        this(i, <init>1, <init>2);
            }
}
