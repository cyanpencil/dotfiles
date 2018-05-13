// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteBufferInputStream.java

package org.glassfish.jersey.internal.util.collection;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.glassfish.jersey.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.internal.util.collection:
//            NonBlockingInputStream, DataStructures

public final class ByteBufferInputStream extends NonBlockingInputStream
{

            public ByteBufferInputStream()
            {
/*  71*/        eof = false;
/*  97*/        current = null;
            }

            private boolean fetchChunk(boolean flag)
                throws InterruptedException
            {
/* 101*/        if(eof)
/* 102*/            return false;
/* 108*/        do
                {
/* 108*/            if(closed.get())
                    {
/* 109*/                current = EOF;
/* 110*/                break;
                    }
/* 113*/            current = flag ? (ByteBuffer)buffers.take() : (ByteBuffer)buffers.poll();
                } while(current != null && current != EOF && !current.hasRemaining());
/* 116*/        eof = current == EOF;
/* 117*/        return !eof;
            }

            private void checkNotClosed()
                throws IOException
            {
/* 121*/        if(closed.get())
/* 122*/            throw new IOException(LocalizationMessages.INPUT_STREAM_CLOSED());
/* 124*/        else
/* 124*/            return;
            }

            private void checkThrowable()
                throws IOException
            {
                Object obj;
/* 127*/        if((obj = queueStatus.get()) != null && obj != EOF && queueStatus.compareAndSet(obj, EOF))
/* 133*/            throw new IOException((Throwable)obj);
/* 139*/        else
/* 139*/            return;
            }

            public final int available()
                throws IOException
            {
/* 143*/        if(eof || closed.get())
                {
/* 144*/            checkThrowable();
/* 145*/            return 0;
                }
/* 148*/        int i = 0;
/* 149*/        if(current != null && current.hasRemaining())
/* 150*/            i = current.remaining();
                ByteBuffer bytebuffer;
/* 152*/        for(Iterator iterator = buffers.iterator(); iterator.hasNext() && (bytebuffer = (ByteBuffer)iterator.next()) != EOF;)
/* 156*/            i += bytebuffer.remaining();

/* 159*/        checkThrowable();
/* 160*/        if(closed.get())
/* 160*/            return 0;
/* 160*/        else
/* 160*/            return i;
            }

            public final int read()
                throws IOException
            {
/* 165*/        return tryRead(true);
            }

            public final int read(byte abyte0[], int i, int j)
                throws IOException
            {
/* 170*/        return tryRead(abyte0, i, j, true);
            }

            public final int tryRead()
                throws IOException
            {
/* 174*/        return tryRead(false);
            }

            public final int tryRead(byte abyte0[])
                throws IOException
            {
/* 179*/        return tryRead(abyte0, 0, abyte0.length);
            }

            public final int tryRead(byte abyte0[], int i, int j)
                throws IOException
            {
/* 184*/        return tryRead(abyte0, i, j, false);
            }

            public final void close()
                throws IOException
            {
/* 189*/        if(closed.compareAndSet(false, true))
                {
/* 190*/            closeQueue();
/* 193*/            buffers.clear();
                }
/* 195*/        checkThrowable();
            }

            public final boolean put(ByteBuffer bytebuffer)
                throws InterruptedException
            {
/* 215*/        if(queueStatus.get() == null)
                {
/* 216*/            buffers.put(bytebuffer);
/* 217*/            return true;
                } else
                {
/* 219*/            return false;
                }
            }

            public final void closeQueue()
            {
/* 231*/        if(queueStatus.compareAndSet(null, EOF))
/* 233*/            try
                    {
/* 233*/                buffers.put(EOF);
/* 236*/                return;
                    }
/* 234*/            catch(InterruptedException _ex)
                    {
/* 235*/                Thread.currentThread().interrupt();
                    }
            }

            public final void closeQueue(Throwable throwable)
            {
/* 256*/        if(queueStatus.compareAndSet(null, throwable))
/* 258*/            try
                    {
/* 258*/                buffers.put(EOF);
/* 261*/                return;
                    }
/* 259*/            catch(InterruptedException _ex)
                    {
/* 260*/                Thread.currentThread().interrupt();
                    }
            }

            private int tryRead(byte abyte0[], int i, int j, boolean flag)
                throws IOException
            {
                int k;
/* 266*/        checkThrowable();
/* 267*/        checkNotClosed();
/* 269*/        if(abyte0 == null)
/* 270*/            throw new NullPointerException();
/* 271*/        if(i < 0 || j < 0 || j > abyte0.length - i)
/* 272*/            throw new IndexOutOfBoundsException();
/* 273*/        if(j == 0)
/* 274*/            return 0;
/* 277*/        if(eof)
/* 278*/            return -1;
/* 281*/        k = 0;
_L2:
/* 282*/        do
                {
/* 282*/            if(k >= j)
/* 283*/                break; /* Loop/switch isn't completed */
/* 283*/            if(current == null || !current.hasRemaining())
/* 284*/                continue; /* Loop/switch isn't completed */
                    int l;
/* 284*/            if((l = current.remaining()) < j - k)
                    {
/* 286*/                current.get(abyte0, i + k, l);
/* 287*/                k += l;
                    } else
                    {
/* 289*/                current.get(abyte0, i + k, j - k);
/* 290*/                return j;
                    }
                } while(true);
/* 294*/        if(fetchChunk(flag) && current != null) goto _L2; else goto _L1
_L1:
/* 295*/        break; /* Loop/switch isn't completed */
                InterruptedException interruptedexception;
/* 297*/        interruptedexception;
/* 298*/        Thread.currentThread().interrupt();
/* 299*/        if(flag)
/* 300*/            throw new IOException(interruptedexception);
/* 302*/        if(true) goto _L2; else goto _L3
_L3:
/* 306*/        if(k == 0 && eof)
/* 306*/            return -1;
/* 306*/        else
/* 306*/            return k;
            }

            private int tryRead(boolean flag)
                throws IOException
            {
/* 310*/        checkThrowable();
/* 311*/        checkNotClosed();
/* 313*/        if(eof)
/* 314*/            return -1;
/* 317*/        if(current != null && current.hasRemaining())
/* 318*/            return current.get() & 0xff;
/* 323*/        if(fetchChunk(flag) && current != null)
/* 324*/            return current.get() & 0xff;
/* 325*/        if(flag)
/* 326*/            return -1;
/* 333*/        break MISSING_BLOCK_LABEL_102;
                InterruptedException interruptedexception;
/* 328*/        interruptedexception;
/* 329*/        Thread.currentThread().interrupt();
/* 330*/        if(flag)
/* 331*/            throw new IOException(interruptedexception);
/* 335*/        return !eof ? 0x80000000 : -1;
            }

            private static final ByteBuffer EOF = ByteBuffer.wrap(new byte[0]);
            private boolean eof;
            private ByteBuffer current;
            private final BlockingQueue buffers = DataStructures.createLinkedTransferQueue();
            private final AtomicReference queueStatus = new AtomicReference(null);
            private final AtomicBoolean closed = new AtomicBoolean(false);

}
