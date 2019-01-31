// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ByteStreams.java

package jersey.repackaged.com.google.common.io;

import java.io.OutputStream;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package jersey.repackaged.com.google.common.io:
//            ByteStreams

static class s extends OutputStream
{

            public final void write(int i)
            {
            }

            public final void write(byte abyte0[])
            {
/* 508*/        Preconditions.checkNotNull(abyte0);
            }

            public final void write(byte abyte0[], int i, int j)
            {
/* 512*/        Preconditions.checkNotNull(abyte0);
            }

            public final String toString()
            {
/* 517*/        return "ByteStreams.nullOutputStream()";
            }

            s()
            {
            }
}
