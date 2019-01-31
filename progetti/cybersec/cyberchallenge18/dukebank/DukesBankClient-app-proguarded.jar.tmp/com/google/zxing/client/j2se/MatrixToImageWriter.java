// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MatrixToImageWriter.java

package com.google.zxing.client.j2se;

import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import javax.imageio.ImageIO;

// Referenced classes of package com.google.zxing.client.j2se:
//            MatrixToImageConfig

public final class MatrixToImageWriter
{

            private MatrixToImageWriter()
            {
            }

            public static BufferedImage toBufferedImage(BitMatrix bitmatrix)
            {
/*  46*/        return toBufferedImage(bitmatrix, DEFAULT_CONFIG);
            }

            public static BufferedImage toBufferedImage(BitMatrix bitmatrix, MatrixToImageConfig matrixtoimageconfig)
            {
/*  53*/        int i = bitmatrix.getWidth();
/*  54*/        int j = bitmatrix.getHeight();
/*  55*/        BufferedImage bufferedimage = new BufferedImage(i, j, matrixtoimageconfig.getBufferedImageColorModel());
/*  56*/        int k = matrixtoimageconfig.getPixelOnColor();
/*  57*/        matrixtoimageconfig = matrixtoimageconfig.getPixelOffColor();
/*  58*/        for(int l = 0; l < i; l++)
                {
/*  59*/            for(int i1 = 0; i1 < j; i1++)
/*  60*/                bufferedimage.setRGB(l, i1, ((int) (bitmatrix.get(l, i1) ? k : ((int) (matrixtoimageconfig)))));

                }

/*  63*/        return bufferedimage;
            }

            /**
             * @deprecated Method writeToFile is deprecated
             */

            public static void writeToFile(BitMatrix bitmatrix, String s, File file)
                throws IOException
            {
/*  71*/        writeToPath(bitmatrix, s, file.toPath());
            }

            public static void writeToPath(BitMatrix bitmatrix, String s, Path path)
                throws IOException
            {
/*  80*/        writeToPath(bitmatrix, s, path, DEFAULT_CONFIG);
            }

            /**
             * @deprecated Method writeToFile is deprecated
             */

            public static void writeToFile(BitMatrix bitmatrix, String s, File file, MatrixToImageConfig matrixtoimageconfig)
                throws IOException
            {
/*  89*/        writeToPath(bitmatrix, s, file.toPath(), matrixtoimageconfig);
            }

            public static void writeToPath(BitMatrix bitmatrix, String s, Path path, MatrixToImageConfig matrixtoimageconfig)
                throws IOException
            {
/*  97*/        if(!ImageIO.write(bitmatrix = toBufferedImage(bitmatrix, matrixtoimageconfig), s, path.toFile()))
/*  99*/            throw new IOException((new StringBuilder("Could not write an image of format ")).append(s).append(" to ").append(path).toString());
/* 101*/        else
/* 101*/            return;
            }

            public static void writeToStream(BitMatrix bitmatrix, String s, OutputStream outputstream)
                throws IOException
            {
/* 109*/        writeToStream(bitmatrix, s, outputstream, DEFAULT_CONFIG);
            }

            public static void writeToStream(BitMatrix bitmatrix, String s, OutputStream outputstream, MatrixToImageConfig matrixtoimageconfig)
                throws IOException
            {
/* 117*/        if(!ImageIO.write(bitmatrix = toBufferedImage(bitmatrix, matrixtoimageconfig), s, outputstream))
/* 119*/            throw new IOException((new StringBuilder("Could not write an image of format ")).append(s).toString());
/* 121*/        else
/* 121*/            return;
            }

            private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

}
