// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCodeDecoderMetaData.java

package com.google.zxing.qrcode.decoder;

import com.google.zxing.ResultPoint;

public final class QRCodeDecoderMetaData
{

            QRCodeDecoderMetaData(boolean flag)
            {
/*  32*/        mirrored = flag;
            }

            public final boolean isMirrored()
            {
/*  39*/        return mirrored;
            }

            public final void applyMirroredCorrection(ResultPoint aresultpoint[])
            {
/*  48*/        if(!mirrored || aresultpoint == null || aresultpoint.length < 3)
                {
/*  49*/            return;
                } else
                {
/*  51*/            ResultPoint resultpoint = aresultpoint[0];
/*  52*/            aresultpoint[0] = aresultpoint[2];
/*  53*/            aresultpoint[2] = resultpoint;
/*  55*/            return;
                }
            }

            private final boolean mirrored;
}
