// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImageType.java

package net.glxn.qrgen.image;


public final class ImageType extends Enum
{

            public static ImageType[] values()
            {
/*   3*/        return (ImageType[])$VALUES.clone();
            }

            public static ImageType valueOf(String s)
            {
/*   3*/        return (ImageType)Enum.valueOf(net/glxn/qrgen/image/ImageType, s);
            }

            private ImageType(String s, int i)
            {
/*   3*/        super(s, i);
            }

            public static final ImageType JPG;
            public static final ImageType GIF;
            public static final ImageType PNG;
            private static final ImageType $VALUES[];

            static 
            {
/*   4*/        JPG = new ImageType("JPG", 0);
/*   4*/        GIF = new ImageType("GIF", 1);
/*   4*/        PNG = new ImageType("PNG", 2);
/*   3*/        $VALUES = (new ImageType[] {
/*   3*/            JPG, GIF, PNG
                });
            }
}
