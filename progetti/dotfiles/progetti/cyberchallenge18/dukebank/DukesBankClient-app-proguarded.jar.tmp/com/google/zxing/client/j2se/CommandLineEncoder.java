// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommandLineEncoder.java

package com.google.zxing.client.j2se;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Locale;

// Referenced classes of package com.google.zxing.client.j2se:
//            MatrixToImageWriter

public final class CommandLineEncoder
{

            private CommandLineEncoder()
            {
            }

            public static void main(String args[])
                throws Exception
            {
/*  43*/        if(args.length == 0)
                {
/*  44*/            printUsage();
/*  45*/            return;
                }
/*  48*/        BarcodeFormat barcodeformat = DEFAULT_BARCODE_FORMAT;
/*  49*/        String s = "PNG";
/*  50*/        String s1 = "out";
/*  51*/        int i = 300;
/*  52*/        int j = 300;
/*  53*/        String s2 = null;
/*  55*/        int k = (args = args).length;
/*  55*/        for(int l = 0; l < k; l++)
                {
                    String s3;
                    String args1[];
/*  55*/            String s4 = (args1 = (s3 = args[l]).split("="))[0];
/*  57*/            byte byte0 = -1;
/*  57*/            switch(s4.hashCode())
                    {
/*  57*/            case 1921591862: 
/*  57*/                if(s4.equals("--barcode_format"))
/*  57*/                    byte0 = 0;
                        break;

/*  57*/            case -1220233989: 
/*  57*/                if(s4.equals("--image_format"))
/*  57*/                    byte0 = 1;
                        break;

/*  57*/            case 1394501281: 
/*  57*/                if(s4.equals("--output"))
/*  57*/                    byte0 = 2;
                        break;

/*  57*/            case -1610568666: 
/*  57*/                if(s4.equals("--width"))
/*  57*/                    byte0 = 3;
                        break;

/*  57*/            case 1178984135: 
/*  57*/                if(s4.equals("--height"))
/*  57*/                    byte0 = 4;
                        break;
                    }
/*  57*/            switch(byte0)
                    {
/*  59*/            case 0: // '\0'
/*  59*/                barcodeformat = BarcodeFormat.valueOf(args1[1]);
/*  60*/                break;

/*  62*/            case 1: // '\001'
/*  62*/                s = args1[1];
/*  63*/                break;

/*  65*/            case 2: // '\002'
/*  65*/                s1 = args1[1];
/*  66*/                break;

/*  68*/            case 3: // '\003'
/*  68*/                i = Integer.parseInt(args1[1]);
/*  69*/                break;

/*  71*/            case 4: // '\004'
/*  71*/                j = Integer.parseInt(args1[1]);
/*  72*/                break;

/*  74*/            default:
/*  74*/                if(s3.startsWith("-"))
                        {
/*  75*/                    System.err.println((new StringBuilder("Unknown command line option ")).append(s3).toString());
/*  76*/                    printUsage();
/*  77*/                    return;
                        }
/*  79*/                s2 = s3;
                        break;
                    }
                }

/*  84*/        if(s2 == null)
                {
/*  85*/            printUsage();
/*  86*/            return;
                }
/*  89*/        if("out".equals(s1))
/*  90*/            s1 = (new StringBuilder()).append(s1).append('.').append(s.toLowerCase(Locale.ENGLISH)).toString();
/*  93*/        MatrixToImageWriter.writeToPath(args = (new MultiFormatWriter()).encode(s2, barcodeformat, i, j), s, Paths.get(s1, new String[0]));
            }

            private static void printUsage()
            {
/*  98*/        System.err.println("Encodes barcode images using the ZXing library\n");
/*  99*/        System.err.println("usage: CommandLineEncoder [ options ] content_to_encode");
/* 100*/        System.err.println("  --barcode_format=format: Format to encode, from BarcodeFormat class. Not all formats are supported. Defaults to QR_CODE.");
/* 102*/        System.err.println("  --image_format=format: image output format, such as PNG, JPG, GIF. Defaults to PNG");
/* 103*/        System.err.println("  --output=filename: File to write to. Defaults to out.png");
/* 104*/        System.err.println("  --width=pixels: Image width. Defaults to 300");
/* 105*/        System.err.println("  --height=pixels: Image height. Defaults to 300");
            }

            private static final BarcodeFormat DEFAULT_BARCODE_FORMAT;
            private static final String DEFAULT_IMAGE_FORMAT = "PNG";
            private static final String DEFAULT_OUTPUT_FILE = "out";
            private static final int DEFAULT_WIDTH = 300;
            private static final int DEFAULT_HEIGHT = 300;

            static 
            {
/*  33*/        DEFAULT_BARCODE_FORMAT = BarcodeFormat.QR_CODE;
            }
}
