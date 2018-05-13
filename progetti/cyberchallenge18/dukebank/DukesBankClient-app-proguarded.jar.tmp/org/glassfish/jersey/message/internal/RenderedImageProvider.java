// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RenderedImageProvider.java

package org.glassfish.jersey.message.internal;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageInputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider

public final class RenderedImageProvider extends AbstractMessageReaderWriterProvider
{

            public RenderedImageProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  80*/        return java/awt/image/RenderedImage == class1 || java/awt/image/BufferedImage == class1;
            }

            public final RenderedImage readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException
            {
/*  91*/        if(IMAGE_MEDIA_TYPE.isCompatible(mediatype))
                {
/*  92*/            if(!(class1 = ImageIO.getImageReadersByMIMEType(mediatype.toString())).hasNext())
                    {
/*  94*/                throw new IOException((new StringBuilder("The image-based media type ")).append(mediatype).append("is not supported for reading").toString());
                    } else
                    {
/*  96*/                class1 = (ImageReader)class1.next();
/*  98*/                type = ImageIO.createImageInputStream(inputstream);
/*  99*/                class1.setInput(type, true, true);
/* 100*/                aannotation = class1.read(0, class1.getDefaultReadParam());
/* 101*/                type.close();
/* 102*/                class1.dispose();
/* 103*/                return aannotation;
                    }
                } else
                {
/* 105*/            return ImageIO.read(inputstream);
                }
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 111*/        return java/awt/image/RenderedImage.isAssignableFrom(class1);
            }

            public final void writeTo(RenderedImage renderedimage, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException
            {
/* 123*/        if((class1 = getWriterFormatName(mediatype)) == null)
                {
/* 125*/            throw new IOException((new StringBuilder("The image-based media type ")).append(mediatype).append(" is not supported for writing").toString());
                } else
                {
/* 127*/            ImageIO.write(renderedimage, class1, outputstream);
/* 128*/            return;
                }
            }

            private String getWriterFormatName(MediaType mediatype)
            {
/* 131*/        return getWriterFormatName(mediatype.toString());
            }

            private String getWriterFormatName(String s)
            {
/* 135*/        if(!(s = ImageIO.getImageWritersByMIMEType(s)).hasNext())
/* 137*/            return null;
/* 140*/        else
/* 140*/            return ((ImageWriter)s.next()).getOriginatingProvider().getFormatNames()[0];
            }

            public final volatile Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/*  71*/        return readFrom(class1, type, aannotation, mediatype, multivaluedmap, inputstream);
            }

            public final volatile void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/*  71*/        writeTo((RenderedImage)obj, class1, type, aannotation, mediatype, multivaluedmap, outputstream);
            }

            private static final MediaType IMAGE_MEDIA_TYPE = new MediaType("image", "*");

}
