// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GZipEncoder.java

package org.glassfish.jersey.message;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.glassfish.jersey.spi.ContentEncoder;

public class GZipEncoder extends ContentEncoder
{

            public GZipEncoder()
            {
/*  67*/        super(new String[] {
/*  67*/            "gzip", "x-gzip"
                });
            }

            public InputStream decode(String s, InputStream inputstream)
                throws IOException
            {
/*  73*/        return new GZIPInputStream(inputstream);
            }

            public OutputStream encode(String s, OutputStream outputstream)
                throws IOException
            {
/*  79*/        return new GZIPOutputStream(outputstream);
            }
}
