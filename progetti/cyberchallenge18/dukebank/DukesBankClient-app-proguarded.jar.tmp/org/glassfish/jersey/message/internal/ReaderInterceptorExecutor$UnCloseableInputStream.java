// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderInterceptorExecutor.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.MessageBodyReader;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            ReaderInterceptorExecutor

static class reader extends InputStream
{

            public int read()
                throws IOException
            {
/* 286*/        return original.read();
            }

            public int read(byte abyte0[])
                throws IOException
            {
/* 291*/        return original.read(abyte0);
            }

            public int read(byte abyte0[], int i, int j)
                throws IOException
            {
/* 296*/        return original.read(abyte0, i, j);
            }

            public long skip(long l)
                throws IOException
            {
/* 301*/        return original.skip(l);
            }

            public int available()
                throws IOException
            {
/* 306*/        return original.available();
            }

            public synchronized void mark(int i)
            {
/* 311*/        original.mark(i);
            }

            public synchronized void reset()
                throws IOException
            {
/* 316*/        original.reset();
            }

            public boolean markSupported()
            {
/* 321*/        return original.markSupported();
            }

            public void close()
                throws IOException
            {
/* 326*/        if(ReaderInterceptorExecutor.access$300().isLoggable(Level.FINE))
/* 327*/            ReaderInterceptorExecutor.access$300().log(Level.FINE, LocalizationMessages.MBR_TRYING_TO_CLOSE_STREAM(reader.getClass()));
            }

            private InputStream unwrap()
            {
/* 332*/        return original;
            }

            private final InputStream original;
            private final MessageBodyReader reader;


            private (InputStream inputstream, MessageBodyReader messagebodyreader)
            {
/* 280*/        original = inputstream;
/* 281*/        reader = messagebodyreader;
            }

}
