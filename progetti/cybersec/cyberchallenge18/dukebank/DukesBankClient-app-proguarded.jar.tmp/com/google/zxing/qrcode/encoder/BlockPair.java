// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BlockPair.java

package com.google.zxing.qrcode.encoder;


final class BlockPair
{

            BlockPair(byte abyte0[], byte abyte1[])
            {
/*  25*/        dataBytes = abyte0;
/*  26*/        errorCorrectionBytes = abyte1;
            }

            public final byte[] getDataBytes()
            {
/*  30*/        return dataBytes;
            }

            public final byte[] getErrorCorrectionBytes()
            {
/*  34*/        return errorCorrectionBytes;
            }

            private final byte dataBytes[];
            private final byte errorCorrectionBytes[];
}
