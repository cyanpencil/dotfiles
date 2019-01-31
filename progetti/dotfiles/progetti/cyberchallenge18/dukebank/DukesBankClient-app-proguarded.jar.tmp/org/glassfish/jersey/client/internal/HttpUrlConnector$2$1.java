// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpUrlConnector.java

package org.glassfish.jersey.client.internal;

import java.io.*;
import java.net.HttpURLConnection;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.internal.util.collection.UnsafeValue;
import org.glassfish.jersey.internal.util.collection.Values;

// Referenced classes of package org.glassfish.jersey.client.internal:
//            HttpUrlConnector

class this._cls0
    implements UnsafeValue
{

            public InputStream get()
                throws IOException
            {
/* 180*/        if(uc.getResponseCode() < javax.ws.rs.core.EQUEST.atusCode())
/* 181*/            return uc.getInputStream();
                InputStream inputstream;
/* 183*/        if((inputstream = uc.getErrorStream()) != null)
/* 184*/            return inputstream;
/* 184*/        else
/* 184*/            return new ByteArrayInputStream(new byte[0]);
            }

            public volatile Object get()
                throws Throwable
            {
/* 177*/        return get();
            }

            final get this$0;

            osed()
            {
/* 177*/        this$0 = this._cls0.this;
/* 177*/        super();
            }

            // Unreferenced inner class org/glassfish/jersey/client/internal/HttpUrlConnector$2

/* anonymous class */
    static class HttpUrlConnector._cls2 extends InputStream
    {

                private void throwIOExceptionIfClosed()
                    throws IOException
                {
/* 207*/            if(closed)
/* 208*/                throw new IOException("Stream closed");
/* 210*/            else
/* 210*/                return;
                }

                public final int read()
                    throws IOException
                {
/* 214*/            int i = ((InputStream)in.get()).read();
/* 215*/            throwIOExceptionIfClosed();
/* 216*/            return i;
                }

                public final int read(byte abyte0[])
                    throws IOException
                {
/* 221*/            abyte0 = ((InputStream)in.get()).read(abyte0);
/* 222*/            throwIOExceptionIfClosed();
/* 223*/            return abyte0;
                }

                public final int read(byte abyte0[], int i, int j)
                    throws IOException
                {
/* 228*/            abyte0 = ((InputStream)in.get()).read(abyte0, i, j);
/* 229*/            throwIOExceptionIfClosed();
/* 230*/            return abyte0;
                }

                public final long skip(long l)
                    throws IOException
                {
/* 235*/            long l1 = ((InputStream)in.get()).skip(l);
/* 236*/            throwIOExceptionIfClosed();
/* 237*/            return l1;
                }

                public final int available()
                    throws IOException
                {
/* 242*/            int i = ((InputStream)in.get()).available();
/* 243*/            throwIOExceptionIfClosed();
/* 244*/            return i;
                }

                public final void close()
                    throws IOException
                {
/* 250*/            ((InputStream)in.get()).close();
/* 252*/            closed = true;
/* 253*/            return;
                    Exception exception;
/* 252*/            exception;
/* 252*/            closed = true;
/* 252*/            throw exception;
                }

                public final void mark(int i)
                {
/* 259*/            try
                    {
/* 259*/                ((InputStream)in.get()).mark(i);
/* 262*/                return;
                    }
                    // Misplaced declaration of an exception variable
/* 260*/            catch(int i)
                    {
/* 261*/                throw new IllegalStateException("Unable to retrieve the underlying input stream.", i);
                    }
                }

                public final void reset()
                    throws IOException
                {
/* 267*/            ((InputStream)in.get()).reset();
/* 268*/            throwIOExceptionIfClosed();
                }

                public final boolean markSupported()
                {
/* 274*/            return ((InputStream)in.get()).markSupported();
                    IOException ioexception;
/* 275*/            ioexception;
/* 276*/            throw new IllegalStateException("Unable to retrieve the underlying input stream.", ioexception);
                }

                private final UnsafeValue in = Values.lazy(new HttpUrlConnector._cls2._cls1());
                private volatile boolean closed;
                final HttpURLConnection val$uc;

                    
                    {
/* 176*/                uc = httpurlconnection;
/* 176*/                super();
/* 189*/                closed = false;
                    }
    }

}
