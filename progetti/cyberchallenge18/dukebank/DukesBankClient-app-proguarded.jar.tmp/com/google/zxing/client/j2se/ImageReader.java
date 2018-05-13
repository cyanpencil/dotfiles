// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ImageReader.java

package com.google.zxing.client.j2se;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public final class ImageReader
{

            private ImageReader()
            {
            }

            public static BufferedImage readImage(URI uri)
                throws IOException
            {
/*  40*/        if("data".equals(uri.getScheme()))
/*  41*/            return readDataURIImage(uri);
                Object obj;
/*  45*/        try
                {
/*  45*/            obj = ImageIO.read(uri.toURL());
                }
                // Misplaced declaration of an exception variable
/*  46*/        catch(Object obj)
                {
/*  47*/            throw new IOException((new StringBuilder("Resource not found: ")).append(uri).toString(), ((Throwable) (obj)));
                }
/*  49*/        if(obj == null)
/*  50*/            throw new IOException((new StringBuilder("Could not load ")).append(uri).toString());
/*  52*/        else
/*  52*/            return ((BufferedImage) (obj));
            }

            public static BufferedImage readDataURIImage(URI uri)
                throws IOException
            {
/*  56*/        if(!(uri = uri.toString()).startsWith("data:image/"))
/*  58*/            throw new IOException("Unsupported data URI MIME type");
                int i;
/*  60*/        if((i = uri.indexOf("base64,")) < 0)
                {
/*  62*/            throw new IOException("Unsupported data URI encoding");
                } else
                {
/*  64*/            uri = DatatypeConverter.parseBase64Binary(uri = URLDecoder.decode(uri = uri.substring(i + 7), "UTF-8"));
/*  67*/            return ImageIO.read(new ByteArrayInputStream(uri));
                }
            }

            private static final String BASE64TOKEN = "base64,";
}
