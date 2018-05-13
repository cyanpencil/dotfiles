// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BufferedImageLuminanceSource.java

package com.google.zxing.client.j2se;

import com.google.zxing.LuminanceSource;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public final class BufferedImageLuminanceSource extends LuminanceSource
{

            public BufferedImageLuminanceSource(BufferedImage bufferedimage)
            {
/*  42*/        this(bufferedimage, 0, 0, bufferedimage.getWidth(), bufferedimage.getHeight());
            }

            public BufferedImageLuminanceSource(BufferedImage bufferedimage, int i, int j, int k, int l)
            {
/*  46*/        super(k, l);
/*  48*/        if(bufferedimage.getType() == 10)
                {
/*  49*/            image = bufferedimage;
                } else
                {
/*  51*/            int i1 = bufferedimage.getWidth();
/*  52*/            int j1 = bufferedimage.getHeight();
/*  53*/            if(i + k > i1 || j + l > j1)
/*  54*/                throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
/*  57*/            image = new BufferedImage(i1, j1, 10);
/*  59*/            WritableRaster writableraster = image.getRaster();
/*  60*/            int ai[] = new int[k];
/*  61*/            for(int k1 = j; k1 < j + l; k1++)
                    {
/*  62*/                bufferedimage.getRGB(i, k1, k, 1, ai, 0, i1);
/*  63*/                for(int l1 = 0; l1 < k; l1++)
                        {
                            int i2;
/*  64*/                    if(((i2 = ai[l1]) & 0xff000000) == 0)
/*  70*/                        i2 = -1;
/*  74*/                    ai[l1] = 306 * (i2 >> 16 & 0xff) + 601 * (i2 >> 8 & 0xff) + 117 * (i2 & 0xff) + 512 >> 10;
                        }

/*  80*/                writableraster.setPixels(i, k1, k, 1, ai);
                    }

                }
/*  84*/        left = i;
/*  85*/        top = j;
            }

            public final byte[] getRow(int i, byte abyte0[])
            {
/*  90*/        if(i < 0 || i >= getHeight())
/*  91*/            throw new IllegalArgumentException((new StringBuilder("Requested row is outside the image: ")).append(i).toString());
/*  93*/        int j = getWidth();
/*  94*/        if(abyte0 == null || abyte0.length < j)
/*  95*/            abyte0 = new byte[j];
/*  98*/        image.getRaster().getDataElements(left, top + i, j, 1, abyte0);
/*  99*/        return abyte0;
            }

            public final byte[] getMatrix()
            {
/* 104*/        int i = getWidth();
/* 105*/        int j = getHeight();
                int k;
/* 106*/        byte abyte0[] = new byte[k = i * j];
/* 109*/        image.getRaster().getDataElements(left, top, i, j, abyte0);
/* 110*/        return abyte0;
            }

            public final boolean isCropSupported()
            {
/* 115*/        return true;
            }

            public final LuminanceSource crop(int i, int j, int k, int l)
            {
/* 120*/        return new BufferedImageLuminanceSource(image, left + i, top + j, k, l);
            }

            public final boolean isRotateSupported()
            {
/* 130*/        return true;
            }

            public final LuminanceSource rotateCounterClockwise()
            {
/* 135*/        int i = image.getWidth();
/* 136*/        int j = image.getHeight();
/* 139*/        AffineTransform affinetransform = new AffineTransform(0.0D, -1D, 1.0D, 0.0D, 0.0D, i);
                BufferedImage bufferedimage;
                Graphics2D graphics2d;
/* 142*/        (graphics2d = (bufferedimage = new BufferedImage(j, i, 10)).createGraphics()).drawImage(image, affinetransform, null);
/* 147*/        graphics2d.dispose();
/* 150*/        int k = getWidth();
/* 151*/        return new BufferedImageLuminanceSource(bufferedimage, top, i - (left + k), getHeight(), k);
            }

            public final LuminanceSource rotateCounterClockwise45()
            {
/* 156*/        int i = getWidth();
/* 157*/        int j = getHeight();
/* 159*/        int k = left + i / 2;
/* 160*/        int l = top + j / 2;
/* 163*/        AffineTransform affinetransform = AffineTransform.getRotateInstance(-0.78539816339744828D, k, l);
/* 165*/        int j1 = Math.max(image.getWidth(), image.getHeight());
                BufferedImage bufferedimage;
                Graphics2D graphics2d;
/* 166*/        (graphics2d = (bufferedimage = new BufferedImage(j1, j1, 10)).createGraphics()).drawImage(image, affinetransform, null);
/* 171*/        graphics2d.dispose();
/* 173*/        i = Math.max(i, j) / 2;
/* 174*/        j = Math.max(0, k - i);
/* 175*/        int i1 = Math.max(0, l - i);
/* 176*/        k = Math.min(j1 - 1, k + i);
/* 177*/        i = Math.min(j1 - 1, l + i);
/* 179*/        return new BufferedImageLuminanceSource(bufferedimage, j, i1, k - j, i - i1);
            }

            private static final double MINUS_45_IN_RADIANS = -0.78539816339744828D;
            private final BufferedImage image;
            private final int left;
            private final int top;
}
