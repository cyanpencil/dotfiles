// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DataSourceProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import javax.activation.DataSource;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            DataSourceProvider, ReaderWriter

public static class type
    implements DataSource
{
    static class DSByteArrayOutputStream extends ByteArrayOutputStream
    {

                public byte[] getBuf()
                {
/*  91*/            return buf;
                }

                public int getCount()
                {
/*  95*/            return count;
                }

                DSByteArrayOutputStream()
                {
                }
    }


            public InputStream getInputStream()
                throws IOException
            {
/* 121*/        if(data == null)
/* 122*/            throw new IOException("no data");
/* 124*/        if(len < 0)
/* 125*/            len = data.length;
/* 127*/        return new ByteArrayInputStream(data, 0, len);
            }

            public OutputStream getOutputStream()
                throws IOException
            {
/* 132*/        throw new IOException("cannot do this");
            }

            public String getContentType()
            {
/* 137*/        return type;
            }

            public String getName()
            {
/* 142*/        return name;
            }

            public void setName(String s)
            {
/* 146*/        name = s;
            }

            private final String type;
            private byte data[];
            private int len;
            private String name;

            public DSByteArrayOutputStream(InputStream inputstream, String s)
                throws IOException
            {
/*  85*/        len = -1;
/*  86*/        name = "";
/* 100*/        DSByteArrayOutputStream dsbytearrayoutputstream = new DSByteArrayOutputStream();
/* 101*/        ReaderWriter.writeTo(inputstream, dsbytearrayoutputstream);
/* 102*/        data = dsbytearrayoutputstream.getBuf();
/* 103*/        len = dsbytearrayoutputstream.getCount();
/* 112*/        if(data.length - len > 0x40000)
                {
/* 113*/            data = dsbytearrayoutputstream.toByteArray();
/* 114*/            len = data.length;
                }
/* 116*/        type = s;
            }
}
