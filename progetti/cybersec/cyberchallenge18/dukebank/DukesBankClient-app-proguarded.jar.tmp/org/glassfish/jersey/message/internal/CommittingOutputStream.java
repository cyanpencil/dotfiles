// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommittingOutputStream.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            NullOutputStream, OutboundMessageContext

final class CommittingOutputStream extends OutputStream
{

            public CommittingOutputStream()
            {
/* 110*/        bufferSize = 0;
/* 118*/        directWrite = true;
            }

            public final void setStreamProvider(OutboundMessageContext.StreamProvider streamprovider)
            {
/* 146*/        if(isClosed)
/* 147*/            throw new IllegalStateException(LocalizationMessages.OUTPUT_STREAM_CLOSED());
/* 149*/        Preconditions.checkNotNull(streamprovider);
/* 151*/        if(streamProvider != null)
/* 152*/            LOGGER.log(Level.WARNING, LocalizationMessages.COMMITTING_STREAM_ALREADY_INITIALIZED());
/* 154*/        streamProvider = streamprovider;
            }

            public final void enableBuffering(int i)
            {
/* 165*/        Preconditions.checkState(!isCommitted && (buffer == null || buffer.size() == 0), COMMITTING_STREAM_BUFFERING_ILLEGAL_STATE);
/* 167*/        bufferSize = i;
/* 168*/        if(i <= 0)
                {
/* 169*/            directWrite = true;
/* 170*/            buffer = null;
/* 170*/            return;
                } else
                {
/* 172*/            directWrite = false;
/* 173*/            buffer = new ByteArrayOutputStream(i);
/* 175*/            return;
                }
            }

            public final void enableBuffering()
            {
/* 181*/        enableBuffering(8192);
            }

            public final boolean isCommitted()
            {
/* 190*/        return isCommitted;
            }

            private void commitStream()
                throws IOException
            {
/* 194*/        commitStream(-1);
            }

            private void commitStream(int i)
                throws IOException
            {
/* 198*/        if(!isCommitted)
                {
/* 199*/            Preconditions.checkState(streamProvider != null, STREAM_PROVIDER_NULL);
/* 200*/            adaptedOutput = streamProvider.getOutputStream(i);
/* 201*/            if(adaptedOutput == null)
/* 202*/                adaptedOutput = new NullOutputStream();
/* 205*/            directWrite = true;
/* 206*/            isCommitted = true;
                }
            }

            public final void write(byte abyte0[])
                throws IOException
            {
/* 212*/        if(directWrite)
                {
/* 213*/            commitStream();
/* 214*/            adaptedOutput.write(abyte0);
/* 214*/            return;
                }
/* 216*/        if(abyte0.length + buffer.size() > bufferSize)
                {
/* 217*/            flushBuffer(false);
/* 218*/            adaptedOutput.write(abyte0);
/* 218*/            return;
                } else
                {
/* 220*/            buffer.write(abyte0);
/* 223*/            return;
                }
            }

            public final void write(byte abyte0[], int i, int j)
                throws IOException
            {
/* 227*/        if(directWrite)
                {
/* 228*/            commitStream();
/* 229*/            adaptedOutput.write(abyte0, i, j);
/* 229*/            return;
                }
/* 231*/        if(j + buffer.size() > bufferSize)
                {
/* 232*/            flushBuffer(false);
/* 233*/            adaptedOutput.write(abyte0, i, j);
/* 233*/            return;
                } else
                {
/* 235*/            buffer.write(abyte0, i, j);
/* 238*/            return;
                }
            }

            public final void write(int i)
                throws IOException
            {
/* 242*/        if(directWrite)
                {
/* 243*/            commitStream();
/* 244*/            adaptedOutput.write(i);
/* 244*/            return;
                }
/* 246*/        if(buffer.size() + 1 > bufferSize)
                {
/* 247*/            flushBuffer(false);
/* 248*/            adaptedOutput.write(i);
/* 248*/            return;
                } else
                {
/* 250*/            buffer.write(i);
/* 253*/            return;
                }
            }

            final void commit()
                throws IOException
            {
/* 261*/        flushBuffer(true);
/* 262*/        commitStream();
            }

            public final void close()
                throws IOException
            {
/* 267*/        if(isClosed)
/* 268*/            return;
/* 271*/        isClosed = true;
/* 273*/        if(streamProvider == null)
/* 274*/            streamProvider = NULL_STREAM_PROVIDER;
/* 276*/        commit();
/* 277*/        adaptedOutput.close();
            }

            public final boolean isClosed()
            {
/* 286*/        return isClosed;
            }

            public final void flush()
                throws IOException
            {
/* 291*/        if(isCommitted())
/* 292*/            adaptedOutput.flush();
            }

            private void flushBuffer(boolean flag)
                throws IOException
            {
/* 297*/        if(!directWrite)
                {
/* 299*/            if(flag)
/* 300*/                flag = buffer != null ? ((boolean) (buffer.size())) : false;
/* 302*/            else
/* 302*/                flag = -1;
/* 305*/            commitStream(flag);
/* 306*/            if(buffer != null)
/* 307*/                buffer.writeTo(adaptedOutput);
                }
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/CommittingOutputStream.getName());
            private static final OutboundMessageContext.StreamProvider NULL_STREAM_PROVIDER = new OutboundMessageContext.StreamProvider() {

                public final OutputStream getOutputStream(int i)
                    throws IOException
                {
/*  92*/            return new NullOutputStream();
                }

    };
            static final int DEFAULT_BUFFER_SIZE = 8192;
            private OutputStream adaptedOutput;
            private OutboundMessageContext.StreamProvider streamProvider;
            private int bufferSize;
            private ByteArrayOutputStream buffer;
            private boolean directWrite;
            private boolean isCommitted;
            private boolean isClosed;
            private static final String STREAM_PROVIDER_NULL = LocalizationMessages.STREAM_PROVIDER_NULL();
            private static final String COMMITTING_STREAM_BUFFERING_ILLEGAL_STATE = LocalizationMessages.COMMITTING_STREAM_BUFFERING_ILLEGAL_STATE();

}
