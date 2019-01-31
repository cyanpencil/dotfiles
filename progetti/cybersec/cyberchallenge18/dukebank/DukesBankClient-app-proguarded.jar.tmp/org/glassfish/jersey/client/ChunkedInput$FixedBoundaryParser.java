// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkedInput.java

package org.glassfish.jersey.client;

import java.util.Arrays;

// Referenced classes of package org.glassfish.jersey.client:
//            ChunkedInput

static class delimiter extends er
{

            byte[] getDelimiter(byte byte0, int i, byte abyte0[])
            {
/* 266*/        return delimiter;
            }

            byte[] getDelimiter(int i, byte abyte0[])
            {
/* 271*/        return delimiter;
            }

            int getDelimiterBufferSize()
            {
/* 276*/        return delimiter.length;
            }

            private final byte delimiter[];

            public er(byte abyte0[])
            {
/* 261*/        delimiter = Arrays.copyOf(abyte0, abyte0.length);
            }
}
