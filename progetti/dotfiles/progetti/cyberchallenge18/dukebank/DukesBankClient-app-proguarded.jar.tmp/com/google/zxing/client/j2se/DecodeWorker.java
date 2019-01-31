// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodeWorker.java

package com.google.zxing.client.j2se;

import com.google.zxing.*;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.*;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;

// Referenced classes of package com.google.zxing.client.j2se:
//            BufferedImageLuminanceSource, Config, ImageReader

final class DecodeWorker
    implements Callable
{

            DecodeWorker(Config config1, Queue queue)
            {
/*  65*/        config = config1;
/*  66*/        inputs = queue;
            }

            public final Integer call()
                throws IOException
            {
/*  71*/        int i = 0;
/*  73*/        do
                {
                    Path path;
/*  73*/            if((path = (Path)inputs.poll()) == null)
/*  74*/                break;
                    Result aresult[];
                    Result result;
/*  74*/            if(Files.exists(path, new LinkOption[0]))
                    {
/*  75*/                if(config.isMulti())
                        {
/*  76*/                    if((aresult = decodeMulti(path.toUri(), config.getHints())) != null)
                            {
/*  78*/                        i++;
/*  79*/                        if(config.isDumpResults())
/*  80*/                            dumpResultMulti(path, aresult);
                            }
                        } else
/*  84*/                if((result = decode(path.toUri(), config.getHints())) != null)
                        {
/*  86*/                    i++;
/*  87*/                    if(config.isDumpResults())
/*  88*/                        dumpResult(path, result);
                        }
                    } else
/*  93*/            if(decode(path.toUri(), config.getHints()) != null)
/*  94*/                i++;
                } while(true);
/*  98*/        return Integer.valueOf(i);
            }

            private static void dumpResult(Path path, Result result)
                throws IOException
            {
                String s;
                int i;
/* 102*/        if((i = (s = path.getFileName().toString()).lastIndexOf('.')) > 0)
/* 105*/            s = (new StringBuilder()).append(s.substring(0, i)).append(".txt").toString();
/* 107*/        Files.write(path = path.getParent().resolve(s), Collections.singleton(result.getText()), StandardCharsets.UTF_8, new OpenOption[0]);
            }

            private static void dumpResultMulti(Path path, Result aresult[])
                throws IOException
            {
                Object obj;
                int i;
/* 112*/        if((i = ((String) (obj = path.getFileName().toString())).lastIndexOf('.')) > 0)
/* 115*/            obj = (new StringBuilder()).append(((String) (obj)).substring(0, i)).append(".txt").toString();
/* 117*/        path = path.getParent().resolve(((String) (obj)));
/* 118*/        obj = new ArrayList();
/* 119*/        i = (aresult = aresult).length;
/* 119*/        for(int j = 0; j < i; j++)
                {
/* 119*/            Result result = aresult[j];
/* 120*/            ((Collection) (obj)).add(result.getText());
                }

/* 122*/        Files.write(path, ((Iterable) (obj)), StandardCharsets.UTF_8, new OpenOption[0]);
            }

            private Result decode(URI uri, Map map)
                throws IOException
            {
/* 126*/        BufferedImage bufferedimage = ImageReader.readImage(uri);
                Object obj;
/* 129*/        if(config.getCrop() == null)
                {
/* 130*/            obj = new BufferedImageLuminanceSource(bufferedimage);
                } else
                {
/* 132*/            obj = config.getCrop();
/* 133*/            obj = new BufferedImageLuminanceSource(bufferedimage, obj[0], obj[1], obj[2], obj[3]);
                }
/* 135*/        obj = new BinaryBitmap(new HybridBinarizer(((com.google.zxing.LuminanceSource) (obj))));
/* 136*/        if(config.isDumpBlackPoint())
/* 137*/            dumpBlackPoint(uri, bufferedimage, ((BinaryBitmap) (obj)));
/* 139*/        map = (new MultiFormatReader()).decode(((BinaryBitmap) (obj)), map);
/* 140*/        if(config.isBrief())
                {
/* 141*/            System.out.println((new StringBuilder()).append(uri).append(": Success").toString());
                } else
                {
/* 143*/            ParsedResult parsedresult = ResultParser.parseResult(map);
/* 144*/            System.out.println((new StringBuilder()).append(uri).append(" (format: ").append(map.getBarcodeFormat()).append(", type: ").append(parsedresult.getType()).append("):\nRaw result:\n").append(map.getText()).append("\nParsed result:\n").append(parsedresult.getDisplayResult()).toString());
/* 148*/            System.out.println((new StringBuilder("Found ")).append(map.getResultPoints().length).append(" result points.").toString());
/* 149*/            for(int i = 0; i < map.getResultPoints().length; i++)
                    {
                        ResultPoint resultpoint;
/* 150*/                if((resultpoint = map.getResultPoints()[i]) != null)
/* 152*/                    System.out.println((new StringBuilder("  Point ")).append(i).append(": (").append(resultpoint.getX()).append(',').append(resultpoint.getY()).append(')').toString());
                    }

                }
/* 157*/        return map;
/* 158*/        JVM INSTR pop ;
/* 159*/        System.out.println((new StringBuilder()).append(uri).append(": No barcode found").toString());
/* 160*/        return null;
            }

            private Result[] decodeMulti(URI uri, Map map)
                throws IOException
            {
/* 165*/        Object obj = ImageReader.readImage(uri);
                Object obj1;
/* 168*/        if(config.getCrop() == null)
                {
/* 169*/            obj1 = new BufferedImageLuminanceSource(((BufferedImage) (obj)));
                } else
                {
/* 171*/            obj1 = config.getCrop();
/* 172*/            obj1 = new BufferedImageLuminanceSource(((BufferedImage) (obj)), obj1[0], obj1[1], obj1[2], obj1[3]);
                }
/* 174*/        obj1 = new BinaryBitmap(new HybridBinarizer(((com.google.zxing.LuminanceSource) (obj1))));
/* 175*/        if(config.isDumpBlackPoint())
/* 176*/            dumpBlackPoint(uri, ((BufferedImage) (obj)), ((BinaryBitmap) (obj1)));
/* 179*/        obj = new MultiFormatReader();
/* 180*/        map = ((MultipleBarcodeReader) (obj = new GenericMultipleBarcodeReader(((com.google.zxing.Reader) (obj))))).decodeMultiple(((BinaryBitmap) (obj1)), map);
/* 183*/        if(config.isBrief())
                {
/* 184*/            System.out.println((new StringBuilder()).append(uri).append(": Success").toString());
                } else
                {
                    Map map1;
/* 186*/            int i = (map1 = map).length;
/* 186*/            for(int j = 0; j < i; j++)
                    {
                        Result result;
/* 186*/                ParsedResult parsedresult = ResultParser.parseResult(result = map1[j]);
/* 188*/                System.out.println((new StringBuilder()).append(uri).append(" (format: ").append(result.getBarcodeFormat()).append(", type: ").append(parsedresult.getType()).append("):\nRaw result:\n").append(result.getText()).append("\nParsed result:\n").append(parsedresult.getDisplayResult()).toString());
/* 193*/                System.out.println((new StringBuilder("Found ")).append(result.getResultPoints().length).append(" result points.").toString());
/* 194*/                for(int k = 0; k < result.getResultPoints().length; k++)
                        {
/* 195*/                    ResultPoint resultpoint = result.getResultPoints()[k];
/* 196*/                    System.out.println((new StringBuilder("  Point ")).append(k).append(": (").append(resultpoint.getX()).append(',').append(resultpoint.getY()).append(')').toString());
                        }

                    }

                }
/* 200*/        return map;
/* 201*/        JVM INSTR pop ;
/* 202*/        System.out.println((new StringBuilder()).append(uri).append(": No barcode found").toString());
/* 203*/        return null;
            }

            private static void dumpBlackPoint(URI uri, BufferedImage bufferedimage, BinaryBitmap binarybitmap)
            {
/* 213*/        if(uri.getPath().contains(".mono.png"))
/* 214*/            return;
/* 217*/        int i = binarybitmap.getWidth();
/* 218*/        int j = binarybitmap.getHeight();
                int k;
/* 219*/        int ai[] = new int[(k = i * 3) * j];
/* 223*/        int ai1[] = new int[i];
/* 224*/        for(int i1 = 0; i1 < j; i1++)
                {
/* 225*/            bufferedimage.getRGB(0, i1, i, 1, ai1, 0, i);
/* 226*/            System.arraycopy(ai1, 0, ai, i1 * k, i);
                }

/* 230*/        BitArray bitarray = new BitArray(i);
/* 231*/        for(bufferedimage = 0; bufferedimage < j; bufferedimage++)
                {
/* 233*/            try
                    {
/* 233*/                bitarray = binarybitmap.getBlackRow(bufferedimage, bitarray);
                    }
/* 234*/            catch(NotFoundException _ex)
                    {
/* 236*/                int k1 = bufferedimage * k + i;
/* 237*/                Arrays.fill(ai, k1, k1 + i, 0xffff0000);
/* 238*/                continue;
                    }
/* 241*/            int l = bufferedimage * k + i;
/* 242*/            for(int l1 = 0; l1 < i; l1++)
/* 243*/                ai[l + l1] = bitarray.get(l1) ? 0xff000000 : -1;

                }

/* 249*/        try
                {
/* 249*/            for(bufferedimage = 0; bufferedimage < j; bufferedimage++)
                    {
/* 250*/                BitMatrix bitmatrix = binarybitmap.getBlackMatrix();
/* 251*/                int i2 = bufferedimage * k + (i << 1);
/* 252*/                for(int j1 = 0; j1 < i; j1++)
/* 253*/                    ai[i2 + j1] = bitmatrix.get(j1, bufferedimage) ? 0xff000000 : -1;

                    }

                }
/* 256*/        catch(NotFoundException _ex) { }
/* 260*/        writeResultImage(k, j, ai, uri, ".mono.png");
            }

            private static void writeResultImage(int i, int j, int ai[], URI uri, String s)
            {
                BufferedImage bufferedimage;
/* 268*/        (bufferedimage = new BufferedImage(i, j, 2)).setRGB(0, 0, i, j, ai, 0, i);
/* 272*/        i = uri.getPath();
/* 273*/        if("http".equals(uri.getScheme()) && (j = i.lastIndexOf('/')) > 0)
/* 276*/            i = (new StringBuilder(".")).append(i.substring(j)).toString();
/* 279*/        if((j = i.lastIndexOf('.')) > 0)
/* 281*/            i = i.substring(0, j);
/* 283*/        i = (new StringBuilder()).append(i).append(s).toString();
/* 285*/        try
                {
/* 285*/            if(!ImageIO.write(bufferedimage, "png", Paths.get(i, new String[0]).toFile()))
/* 286*/                System.err.println((new StringBuilder("Could not encode an image to ")).append(i).toString());
/* 290*/            return;
                }
/* 288*/        catch(IOException _ex)
                {
/* 289*/            System.err.println((new StringBuilder("Could not write to ")).append(i).toString());
                }
            }

            public final volatile Object call()
                throws Exception
            {
/*  55*/        return call();
            }

            private static final int RED = 0xffff0000;
            private static final int BLACK = 0xff000000;
            private static final int WHITE = -1;
            private final Config config;
            private final Queue inputs;
}
