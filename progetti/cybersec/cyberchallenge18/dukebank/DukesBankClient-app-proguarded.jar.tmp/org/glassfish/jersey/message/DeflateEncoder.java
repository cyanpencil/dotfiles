// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DeflateEncoder.java

package org.glassfish.jersey.message;

import java.io.*;
import java.util.zip.*;
import javax.ws.rs.core.Configuration;
import org.glassfish.jersey.spi.ContentEncoder;

public class DeflateEncoder extends ContentEncoder
{

            public DeflateEncoder(Configuration configuration)
            {
/*  81*/        super(new String[] {
/*  81*/            "deflate"
                });
/*  82*/        config = configuration;
            }

            public InputStream decode(String s, InputStream inputstream)
                throws IOException
            {
/*  89*/        (s = ((String) (inputstream.markSupported() ? ((String) (inputstream)) : ((String) (new BufferedInputStream(inputstream)))))).mark(1);
/*  94*/        inputstream = s.read();
/*  95*/        s.reset();
/*  99*/        if((inputstream & 0xf) == 8)
/* 101*/            return new InflaterInputStream(s);
/* 104*/        else
/* 104*/            return new InflaterInputStream(s, new Inflater(true));
            }

            public OutputStream encode(String s, OutputStream outputstream)
                throws IOException
            {
/* 114*/        if((s = ((String) (config.getProperty("jersey.config.deflate.nozlib")))) instanceof String)
/* 117*/            s = Boolean.valueOf((String)s).booleanValue();
/* 118*/        else
/* 118*/        if(s instanceof Boolean)
/* 119*/            s = ((Boolean)s).booleanValue();
/* 121*/        else
/* 121*/            s = 0;
/* 124*/        if(s != 0)
/* 124*/            return new DeflaterOutputStream(outputstream, new Deflater(-1, true));
/* 124*/        else
/* 124*/            return new DeflaterOutputStream(outputstream);
            }

            private final Configuration config;
}
