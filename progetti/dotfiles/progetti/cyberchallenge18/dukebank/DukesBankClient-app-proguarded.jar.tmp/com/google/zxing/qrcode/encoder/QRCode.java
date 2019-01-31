// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   QRCode.java

package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.*;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix

public final class QRCode
{

            public QRCode()
            {
/*  38*/        maskPattern = -1;
            }

            public final Mode getMode()
            {
/*  42*/        return mode;
            }

            public final ErrorCorrectionLevel getECLevel()
            {
/*  46*/        return ecLevel;
            }

            public final Version getVersion()
            {
/*  50*/        return version;
            }

            public final int getMaskPattern()
            {
/*  54*/        return maskPattern;
            }

            public final ByteMatrix getMatrix()
            {
/*  58*/        return matrix;
            }

            public final String toString()
            {
                StringBuilder stringbuilder;
/*  63*/        (stringbuilder = new StringBuilder(200)).append("<<\n");
/*  65*/        stringbuilder.append(" mode: ");
/*  66*/        stringbuilder.append(mode);
/*  67*/        stringbuilder.append("\n ecLevel: ");
/*  68*/        stringbuilder.append(ecLevel);
/*  69*/        stringbuilder.append("\n version: ");
/*  70*/        stringbuilder.append(version);
/*  71*/        stringbuilder.append("\n maskPattern: ");
/*  72*/        stringbuilder.append(maskPattern);
/*  73*/        if(matrix == null)
                {
/*  74*/            stringbuilder.append("\n matrix: null\n");
                } else
                {
/*  76*/            stringbuilder.append("\n matrix:\n");
/*  77*/            stringbuilder.append(matrix);
                }
/*  79*/        stringbuilder.append(">>\n");
/*  80*/        return stringbuilder.toString();
            }

            public final void setMode(Mode mode1)
            {
/*  84*/        mode = mode1;
            }

            public final void setECLevel(ErrorCorrectionLevel errorcorrectionlevel)
            {
/*  88*/        ecLevel = errorcorrectionlevel;
            }

            public final void setVersion(Version version1)
            {
/*  92*/        version = version1;
            }

            public final void setMaskPattern(int i)
            {
/*  96*/        maskPattern = i;
            }

            public final void setMatrix(ByteMatrix bytematrix)
            {
/* 100*/        matrix = bytematrix;
            }

            public static boolean isValidMaskPattern(int i)
            {
/* 105*/        return i >= 0 && i < 8;
            }

            public static final int NUM_MASK_PATTERNS = 8;
            private Mode mode;
            private ErrorCorrectionLevel ecLevel;
            private Version version;
            private int maskPattern;
            private ByteMatrix matrix;
}
