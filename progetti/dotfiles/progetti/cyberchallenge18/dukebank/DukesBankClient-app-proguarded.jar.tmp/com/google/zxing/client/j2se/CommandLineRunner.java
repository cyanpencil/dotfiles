// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommandLineRunner.java

package com.google.zxing.client.j2se;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.j2se:
//            Config, DecodeWorker

public final class CommandLineRunner
{

            private CommandLineRunner()
            {
            }

            public static void main(String args[])
                throws Exception
            {
/*  55*/        if(args.length == 0)
                {
/*  56*/            printUsage();
/*  57*/            return;
                }
/*  60*/        Config config = new Config();
/*  61*/        ConcurrentLinkedQueue concurrentlinkedqueue = new ConcurrentLinkedQueue();
/*  63*/        int i = (args = args).length;
/*  63*/        for(int j = 0; j < i; j++)
                {
                    String s;
                    String args1[];
/*  63*/            String s1 = (args1 = (s = args[j]).split("="))[0];
/*  65*/            byte byte0 = -1;
/*  65*/            switch(s1.hashCode())
                    {
/*  65*/            case -1341087876: 
/*  65*/                if(s1.equals("--try_harder"))
/*  65*/                    byte0 = 0;
                        break;

/*  65*/            case -878421031: 
/*  65*/                if(s1.equals("--pure_barcode"))
/*  65*/                    byte0 = 1;
                        break;

/*  65*/            case -214932473: 
/*  65*/                if(s1.equals("--products_only"))
/*  65*/                    byte0 = 2;
                        break;

/*  65*/            case 1579122251: 
/*  65*/                if(s1.equals("--dump_results"))
/*  65*/                    byte0 = 3;
                        break;

/*  65*/            case 1991318821: 
/*  65*/                if(s1.equals("--dump_black_point"))
/*  65*/                    byte0 = 4;
                        break;

/*  65*/            case -1619438695: 
/*  65*/                if(s1.equals("--multi"))
/*  65*/                    byte0 = 5;
                        break;

/*  65*/            case -1629690150: 
/*  65*/                if(s1.equals("--brief"))
/*  65*/                    byte0 = 6;
                        break;

/*  65*/            case 1654174354: 
/*  65*/                if(s1.equals("--recursive"))
/*  65*/                    byte0 = 7;
                        break;

/*  65*/            case 1332932656: 
/*  65*/                if(s1.equals("--crop"))
/*  65*/                    byte0 = 8;
                        break;

/*  65*/            case 113896203: 
/*  65*/                if(s1.equals("--possibleFormats"))
/*  65*/                    byte0 = 9;
                        break;
                    }
/*  65*/            switch(byte0)
                    {
/*  67*/            case 0: // '\0'
/*  67*/                config.setTryHarder(true);
/*  68*/                break;

/*  70*/            case 1: // '\001'
/*  70*/                config.setPureBarcode(true);
/*  71*/                break;

/*  73*/            case 2: // '\002'
/*  73*/                config.setProductsOnly(true);
/*  74*/                break;

/*  76*/            case 3: // '\003'
/*  76*/                config.setDumpResults(true);
/*  77*/                break;

/*  79*/            case 4: // '\004'
/*  79*/                config.setDumpBlackPoint(true);
/*  80*/                break;

/*  82*/            case 5: // '\005'
/*  82*/                config.setMulti(true);
/*  83*/                break;

/*  85*/            case 6: // '\006'
/*  85*/                config.setBrief(true);
/*  86*/                break;

/*  88*/            case 7: // '\007'
/*  88*/                config.setRecursive(true);
/*  89*/                break;

/*  91*/            case 8: // '\b'
/*  91*/                s = new int[4];
/*  92*/                args1 = COMMA.split(args1[1]);
/*  93*/                for(int i1 = 0; i1 < 4; i1++)
/*  94*/                    s[i1] = Integer.parseInt(args1[i1]);

/*  96*/                config.setCrop(s);
/*  97*/                break;

/*  99*/            case 9: // '\t'
/*  99*/                config.setPossibleFormats(COMMA.split(args1[1]));
/* 100*/                break;

/* 102*/            default:
/* 102*/                if(s.startsWith("-"))
                        {
/* 103*/                    System.err.println((new StringBuilder("Unknown command line option ")).append(s).toString());
/* 104*/                    printUsage();
/* 105*/                    return;
                        }
/* 107*/                addArgumentToInputs(Paths.get(s, new String[0]), config, concurrentlinkedqueue);
                        break;
                    }
                }

/* 111*/        config.setHints(buildHints(config));
/* 113*/        args = Math.min(concurrentlinkedqueue.size(), Runtime.getRuntime().availableProcessors());
/* 114*/        i = 0;
/* 115*/        if(args > 1)
                {
/* 116*/            ExecutorService executorservice = Executors.newFixedThreadPool(args);
/* 117*/            ArrayList arraylist = new ArrayList(args);
/* 118*/            for(int l = 0; l < args; l++)
/* 119*/                arraylist.add(executorservice.submit(new DecodeWorker(config, concurrentlinkedqueue)));

/* 121*/            executorservice.shutdown();
/* 122*/            for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
                    {
/* 122*/                Future future = (Future)iterator.next();
/* 123*/                i += ((Integer)future.get()).intValue();
                    }

                } else
                {
/* 126*/            i = 0 + (new DecodeWorker(config, concurrentlinkedqueue)).call().intValue();
                }
                int k;
/* 129*/        if((k = concurrentlinkedqueue.size()) > 1)
/* 131*/            System.out.println((new StringBuilder("\nDecoded ")).append(i).append(" files out of ").append(k).append(" successfully (").append((i * 100) / k).append("%)\n").toString());
            }

            private static void addArgumentToInputs(Path path, Config config, Queue queue)
                throws IOException
            {
                Throwable throwable;
/* 139*/        if(!Files.isDirectory(path, new LinkOption[0]))
/* 140*/            break MISSING_BLOCK_LABEL_193;
/* 140*/        path = Files.newDirectoryStream(path);
/* 140*/        throwable = null;
/* 141*/        Iterator iterator = path.iterator();
/* 141*/        do
                {
/* 141*/            if(!iterator.hasNext())
/* 141*/                break;
                    Path path1;
                    String s;
/* 141*/            if(!(s = (path1 = (Path)iterator.next()).getFileName().toString().toLowerCase(Locale.ENGLISH)).startsWith("."))
/* 148*/                if(Files.isDirectory(path1, new LinkOption[0]))
                        {
/* 149*/                    if(config.isRecursive())
/* 150*/                        addArgumentToInputs(path1, config, queue);
                        } else
/* 155*/                if(!s.endsWith(".txt") && !s.contains(".mono.png"))
/* 158*/                    queue.add(path1);
                } while(true);
                Throwable throwable1;
/* 160*/        if(path != null)
/* 160*/            path.close();
/* 160*/        else
/* 160*/            return;
/* 162*/        break MISSING_BLOCK_LABEL_201;
/* 140*/        JVM INSTR dup ;
/* 140*/        throwable1;
/* 140*/        throwable;
/* 140*/        throw throwable1;
/* 160*/        config;
/* 160*/        if(path != null)
/* 160*/            if(throwable != null)
/* 160*/                try
                        {
/* 160*/                    path.close();
                        }
                        // Misplaced declaration of an exception variable
/* 160*/                catch(Path path)
                        {
/* 160*/                    throwable.addSuppressed(path);
                        }
/* 160*/            else
/* 160*/                path.close();
/* 160*/        throw config;
/* 162*/        queue.add(path);
            }

            private static Map buildHints(Config config)
            {
/* 168*/        ArrayList arraylist = new ArrayList();
                String as[];
/* 169*/        if((as = config.getPossibleFormats()) != null && as.length > 0)
                {
/* 171*/            int i = (as = as).length;
/* 171*/            for(int j = 0; j < i; j++)
                    {
/* 171*/                String s = as[j];
/* 172*/                arraylist.add(BarcodeFormat.valueOf(s));
                    }

                } else
                {
/* 175*/            arraylist.add(BarcodeFormat.UPC_A);
/* 176*/            arraylist.add(BarcodeFormat.UPC_E);
/* 177*/            arraylist.add(BarcodeFormat.EAN_13);
/* 178*/            arraylist.add(BarcodeFormat.EAN_8);
/* 179*/            arraylist.add(BarcodeFormat.RSS_14);
/* 180*/            arraylist.add(BarcodeFormat.RSS_EXPANDED);
/* 181*/            if(!config.isProductsOnly())
                    {
/* 182*/                arraylist.add(BarcodeFormat.CODE_39);
/* 183*/                arraylist.add(BarcodeFormat.CODE_93);
/* 184*/                arraylist.add(BarcodeFormat.CODE_128);
/* 185*/                arraylist.add(BarcodeFormat.ITF);
/* 186*/                arraylist.add(BarcodeFormat.QR_CODE);
/* 187*/                arraylist.add(BarcodeFormat.DATA_MATRIX);
/* 188*/                arraylist.add(BarcodeFormat.AZTEC);
/* 189*/                arraylist.add(BarcodeFormat.PDF_417);
/* 190*/                arraylist.add(BarcodeFormat.CODABAR);
/* 191*/                arraylist.add(BarcodeFormat.MAXICODE);
                    }
                }
                EnumMap enummap;
/* 194*/        (enummap = new EnumMap(com/google/zxing/DecodeHintType)).put(DecodeHintType.POSSIBLE_FORMATS, arraylist);
/* 196*/        if(config.isTryHarder())
/* 197*/            enummap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
/* 199*/        if(config.isPureBarcode())
/* 200*/            enummap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
/* 202*/        return enummap;
            }

            private static void printUsage()
            {
/* 206*/        System.err.println("Decode barcode images using the ZXing library");
/* 207*/        System.err.println();
/* 208*/        System.err.println("usage: CommandLineRunner { file | dir | url } [ options ]");
/* 209*/        System.err.println("  --try_harder: Use the TRY_HARDER hint, default is normal (mobile) mode");
/* 210*/        System.err.println("  --pure_barcode: Input image is a pure monochrome barcode image, not a photo");
/* 211*/        System.err.println("  --products_only: Only decode the UPC and EAN families of barcodes");
/* 212*/        System.err.println("  --dump_results: Write the decoded contents to input.txt");
/* 213*/        System.err.println("  --dump_black_point: Compare black point algorithms as input.mono.png");
/* 214*/        System.err.println("  --multi: Scans image for multiple barcodes");
/* 215*/        System.err.println("  --brief: Only output one line per file, omitting the contents");
/* 216*/        System.err.println("  --recursive: Descend into subdirectories");
/* 217*/        System.err.println("  --crop=left,top,width,height: Only examine cropped region of input image(s)");
                StringBuilder stringbuilder;
/* 218*/        (stringbuilder = new StringBuilder()).append("  --possibleFormats=barcodeFormat[,barcodeFormat2...] where barcodeFormat is any of: ");
                BarcodeFormat abarcodeformat[];
/* 220*/        int i = (abarcodeformat = BarcodeFormat.values()).length;
/* 220*/        for(int j = 0; j < i; j++)
                {
/* 220*/            BarcodeFormat barcodeformat = abarcodeformat[j];
/* 221*/            stringbuilder.append(barcodeformat).append(',');
                }

/* 223*/        stringbuilder.setLength(stringbuilder.length() - 1);
/* 224*/        System.err.println(stringbuilder);
            }

            private static final Pattern COMMA = Pattern.compile(",");

}
