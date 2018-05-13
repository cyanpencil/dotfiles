// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LuminanceSource.java

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            InvertedLuminanceSource

public abstract class LuminanceSource
{

            protected LuminanceSource(int i, int j)
            {
/*  34*/        width = i;
/*  35*/        height = j;
            }

            public abstract byte[] getRow(int i, byte abyte0[]);

            public abstract byte[] getMatrix();

            public final int getWidth()
            {
/*  66*/        return width;
            }

            public final int getHeight()
            {
/*  73*/        return height;
            }

            public boolean isCropSupported()
            {
/*  80*/        return false;
            }

            public LuminanceSource crop(int i, int j, int k, int l)
            {
/*  94*/        throw new UnsupportedOperationException("This luminance source does not support cropping.");
            }

            public boolean isRotateSupported()
            {
/* 101*/        return false;
            }

            public LuminanceSource invert()
            {
/* 109*/        return new InvertedLuminanceSource(this);
            }

            public LuminanceSource rotateCounterClockwise()
            {
/* 119*/        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
            }

            public LuminanceSource rotateCounterClockwise45()
            {
/* 129*/        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
            }

            public final String toString()
            {
/* 134*/        byte abyte0[] = new byte[width];
/* 135*/        StringBuilder stringbuilder = new StringBuilder(height * (width + 1));
/* 136*/        for(int i = 0; i < height; i++)
                {
/* 137*/            abyte0 = getRow(i, abyte0);
/* 138*/            for(int j = 0; j < width; j++)
                    {
                        int k;
/* 139*/                if((k = abyte0[j] & 0xff) < 64)
/* 142*/                    k = 35;
/* 143*/                else
/* 143*/                if(k < 128)
/* 144*/                    k = 43;
/* 145*/                else
/* 145*/                if(k < 192)
/* 146*/                    k = 46;
/* 148*/                else
/* 148*/                    k = 32;
/* 150*/                stringbuilder.append(k);
                    }

/* 152*/            stringbuilder.append('\n');
                }

/* 154*/        return stringbuilder.toString();
            }

            private final int width;
            private final int height;
}
