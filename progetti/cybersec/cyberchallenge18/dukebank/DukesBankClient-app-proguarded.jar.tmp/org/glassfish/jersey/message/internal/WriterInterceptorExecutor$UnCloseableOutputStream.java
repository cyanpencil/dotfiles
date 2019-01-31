// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WriterInterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            WriterInterceptorExecutor

static class writer extends OutputStream
{

            public void write(int i)
                throws IOException
            {
/* 289*/        original.write(i);
            }

            public void write(byte abyte0[])
                throws IOException
            {
/* 294*/        original.write(abyte0);
            }

            public void write(byte abyte0[], int i, int j)
                throws IOException
            {
/* 299*/        original.write(abyte0, i, j);
            }

            public void flush()
                throws IOException
            {
/* 304*/        original.flush();
            }

            public void close()
                throws IOException
            {
/* 309*/        if(WriterInterceptorExecutor.access$100().isLoggable(Level.FINE))
/* 310*/            WriterInterceptorExecutor.access$100().log(Level.FINE, LocalizationMessages.MBW_TRYING_TO_CLOSE_STREAM(writer.getClass()));
            }

            private final OutputStream original;
            private final MessageBodyWriter writer;

            private (OutputStream outputstream, MessageBodyWriter messagebodywriter)
            {
/* 283*/        original = outputstream;
/* 284*/        writer = messagebodywriter;
            }

}
