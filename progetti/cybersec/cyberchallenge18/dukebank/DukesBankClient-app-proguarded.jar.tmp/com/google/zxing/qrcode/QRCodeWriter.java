// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCodeWriter.java

package com.google.zxing.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Map;

public final class QRCodeWriter
    implements Writer
{

            public QRCodeWriter()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
                throws WriterException
            {
/*  44*/        return encode(s, barcodeformat, i, j, null);
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  54*/        if(s.isEmpty())
/*  55*/            throw new IllegalArgumentException("Found empty contents");
/*  58*/        if(barcodeformat != BarcodeFormat.QR_CODE)
/*  59*/            throw new IllegalArgumentException((new StringBuilder("Can only encode QR_CODE, but got ")).append(barcodeformat).toString());
/*  62*/        if(i < 0 || j < 0)
/*  63*/            throw new IllegalArgumentException((new StringBuilder("Requested dimensions are too small: ")).append(i).append('x').append(j).toString());
/*  67*/        barcodeformat = ErrorCorrectionLevel.L;
/*  68*/        int k = 4;
/*  69*/        if(map != null)
                {
                    Object obj;
/*  70*/            if((obj = (ErrorCorrectionLevel)map.get(EncodeHintType.ERROR_CORRECTION)) != null)
/*  72*/                barcodeformat = ((BarcodeFormat) (obj));
/*  74*/            if((obj = (Integer)map.get(EncodeHintType.MARGIN)) != null)
/*  76*/                k = ((Integer) (obj)).intValue();
                }
                QRCode qrcode;
/*  80*/        return renderResult(qrcode = Encoder.encode(s, barcodeformat, map), i, j, k);
            }

            private static BitMatrix renderResult(QRCode qrcode, int i, int j, int k)
            {
/*  87*/        if((qrcode = qrcode.getMatrix()) == null)
/*  89*/            throw new IllegalStateException();
/*  91*/        int l = qrcode.getWidth();
/*  92*/        int i1 = qrcode.getHeight();
/*  93*/        int j1 = l + (k << 1);
/*  94*/        k = i1 + (k << 1);
/*  95*/        i = Math.max(i, j1);
/*  96*/        j = Math.max(j, k);
/*  98*/        k = Math.min(i / j1, j / k);
/* 103*/        j1 = (i - l * k) / 2;
/* 104*/        int k1 = (j - i1 * k) / 2;
/* 106*/        i = new BitMatrix(i, j);
/* 108*/        j = 0;
/* 108*/        for(k1 = k1; j < i1; k1 += k)
                {
/* 110*/            int l1 = 0;
/* 110*/            for(int i2 = j1; l1 < l; i2 += k)
                    {
/* 111*/                if(qrcode.get(l1, j) == 1)
/* 112*/                    i.setRegion(i2, k1, k, k);
/* 110*/                l1++;
                    }

/* 108*/            j++;
                }

/* 117*/        return i;
            }

            private static final int QUIET_ZONE_SIZE = 4;
}
