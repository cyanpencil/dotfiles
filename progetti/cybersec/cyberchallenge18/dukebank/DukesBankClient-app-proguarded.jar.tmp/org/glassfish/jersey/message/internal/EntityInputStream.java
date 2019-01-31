// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EntityInputStream.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import javax.ws.rs.ProcessingException;
import org.glassfish.jersey.internal.LocalizationMessages;

public class EntityInputStream extends InputStream
{

            public static EntityInputStream create(InputStream inputstream)
            {
/*  74*/        if(inputstream instanceof EntityInputStream)
/*  75*/            return (EntityInputStream)inputstream;
/*  78*/        else
/*  78*/            return new EntityInputStream(inputstream);
            }

            public EntityInputStream(InputStream inputstream)
            {
/*  62*/        closed = false;
/*  87*/        input = inputstream;
            }

            public int read()
                throws IOException
            {
/*  92*/        return input.read();
            }

            public int read(byte abyte0[])
                throws IOException
            {
/*  97*/        return input.read(abyte0);
            }

            public int read(byte abyte0[], int i, int j)
                throws IOException
            {
/* 102*/        return input.read(abyte0, i, j);
            }

            public long skip(long l)
                throws IOException
            {
/* 107*/        return input.skip(l);
            }

            public int available()
                throws IOException
            {
/* 112*/        return input.available();
            }

            public void mark(int i)
            {
/* 117*/        input.mark(i);
            }

            public boolean markSupported()
            {
/* 122*/        return input.markSupported();
            }

            public void reset()
            {
/* 137*/        try
                {
/* 137*/            input.reset();
/* 140*/            return;
                }
/* 138*/        catch(IOException ioexception)
                {
/* 139*/            throw new ProcessingException(LocalizationMessages.MESSAGE_CONTENT_BUFFER_RESET_FAILED(), ioexception);
                }
            }

            public void close()
                throws ProcessingException
            {
                InputStream inputstream;
/* 152*/        if((inputstream = input) == null)
/* 154*/            return;
                Exception exception;
/* 156*/        if(!closed)
                {
/* 158*/            try
                    {
/* 158*/                inputstream.close();
                    }
/* 159*/            catch(IOException ioexception)
                    {
/* 161*/                throw new ProcessingException(LocalizationMessages.MESSAGE_CONTENT_INPUT_STREAM_CLOSE_FAILED(), ioexception);
                    }
/* 163*/            finally
                    {
/* 163*/                closed = true;
                    }
/* 163*/            closed = true;
/* 164*/            return;
                } else
                {
/* 166*/            return;
                }
/* 163*/        throw exception;
            }

            public boolean isEmpty()
            {
                Object obj;
/* 177*/        ensureNotClosed();
/* 179*/        if((obj = input) == null)
/* 181*/            return true;
/* 186*/        if(!((InputStream) (obj)).markSupported())
/* 187*/            break MISSING_BLOCK_LABEL_45;
/* 187*/        ((InputStream) (obj)).mark(1);
/* 188*/        int i = ((InputStream) (obj)).read();
/* 189*/        ((InputStream) (obj)).reset();
/* 190*/        if(i == -1)
/* 190*/            return true;
/* 190*/        return false;
/* 193*/        if(((InputStream) (obj)).available() > 0)
/* 194*/            return false;
/* 198*/        break MISSING_BLOCK_LABEL_58;
/* 196*/        JVM INSTR pop ;
                int j;
/* 200*/        if((j = ((InputStream) (obj)).read()) == -1)
/* 202*/            return true;
/* 206*/        if(obj instanceof PushbackInputStream)
                {
/* 207*/            obj = (PushbackInputStream)obj;
                } else
                {
/* 209*/            obj = new PushbackInputStream(((InputStream) (obj)), 1);
/* 210*/            input = ((InputStream) (obj));
                }
/* 212*/        ((PushbackInputStream) (obj)).unread(j);
/* 214*/        return false;
                IOException ioexception;
/* 216*/        ioexception;
/* 217*/        throw new ProcessingException(ioexception);
            }

            public void ensureNotClosed()
                throws IllegalStateException
            {
/* 227*/        if(closed)
/* 228*/            throw new IllegalStateException(LocalizationMessages.ERROR_ENTITY_STREAM_CLOSED());
/* 230*/        else
/* 230*/            return;
            }

            public boolean isClosed()
            {
/* 238*/        return closed;
            }

            public final InputStream getWrappedStream()
            {
/* 247*/        return input;
            }

            public final void setWrappedStream(InputStream inputstream)
            {
/* 256*/        input = inputstream;
            }

            private InputStream input;
            private boolean closed;
}
