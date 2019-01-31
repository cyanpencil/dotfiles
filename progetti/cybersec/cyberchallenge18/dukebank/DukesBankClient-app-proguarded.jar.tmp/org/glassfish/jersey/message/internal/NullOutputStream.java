// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NullOutputStream.java

package org.glassfish.jersey.message.internal;

import java.io.IOException;
import java.io.OutputStream;
import org.glassfish.jersey.internal.LocalizationMessages;

public class NullOutputStream extends OutputStream
{

            public NullOutputStream()
            {
            }

            public void write(int i)
                throws IOException
            {
/*  64*/        checkClosed();
            }

            public void write(byte abyte0[], int i, int j)
                throws IOException
            {
/*  69*/        checkClosed();
/*  70*/        if(abyte0 == null)
/*  71*/            throw new NullPointerException();
/*  72*/        if(i < 0 || i > abyte0.length || j < 0 || i + j > abyte0.length || i + j < 0)
/*  73*/            throw new IndexOutOfBoundsException();
/*  75*/        else
/*  75*/            return;
            }

            public void flush()
                throws IOException
            {
/*  79*/        checkClosed();
            }

            private void checkClosed()
                throws IOException
            {
/*  83*/        if(isClosed)
/*  84*/            throw new IOException(LocalizationMessages.OUTPUT_STREAM_CLOSED());
/*  86*/        else
/*  86*/            return;
            }

            public void close()
                throws IOException
            {
/*  90*/        isClosed = true;
            }

            private boolean isClosed;
}
