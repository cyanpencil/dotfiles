// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InvertedLuminanceSource.java

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            LuminanceSource

public final class InvertedLuminanceSource extends LuminanceSource
{

            public InvertedLuminanceSource(LuminanceSource luminancesource)
            {
/*  30*/        super(luminancesource.getWidth(), luminancesource.getHeight());
/*  31*/        _flddelegate = luminancesource;
            }

            public final byte[] getRow(int i, byte abyte0[])
            {
/*  36*/        abyte0 = _flddelegate.getRow(i, abyte0);
/*  37*/        i = getWidth();
/*  38*/        for(int j = 0; j < i; j++)
/*  39*/            abyte0[j] = (byte)(255 - (abyte0[j] & 0xff));

/*  41*/        return abyte0;
            }

            public final byte[] getMatrix()
            {
/*  46*/        byte abyte0[] = _flddelegate.getMatrix();
                int i;
/*  47*/        byte abyte1[] = new byte[i = getWidth() * getHeight()];
/*  49*/        for(int j = 0; j < i; j++)
/*  50*/            abyte1[j] = (byte)(255 - (abyte0[j] & 0xff));

/*  52*/        return abyte1;
            }

            public final boolean isCropSupported()
            {
/*  57*/        return _flddelegate.isCropSupported();
            }

            public final LuminanceSource crop(int i, int j, int k, int l)
            {
/*  62*/        return new InvertedLuminanceSource(_flddelegate.crop(i, j, k, l));
            }

            public final boolean isRotateSupported()
            {
/*  67*/        return _flddelegate.isRotateSupported();
            }

            public final LuminanceSource invert()
            {
/*  75*/        return _flddelegate;
            }

            public final LuminanceSource rotateCounterClockwise()
            {
/*  80*/        return new InvertedLuminanceSource(_flddelegate.rotateCounterClockwise());
            }

            public final LuminanceSource rotateCounterClockwise45()
            {
/*  85*/        return new InvertedLuminanceSource(_flddelegate.rotateCounterClockwise45());
            }

            private final LuminanceSource _flddelegate;
}
