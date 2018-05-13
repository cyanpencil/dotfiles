// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NonBlockingInputStream.java

package org.glassfish.jersey.internal.util.collection;

import java.io.IOException;
import java.io.InputStream;

public abstract class NonBlockingInputStream extends InputStream
{

            public NonBlockingInputStream()
            {
            }

            public int available()
                throws IOException
            {
/*  91*/        throw new UnsupportedOperationException();
            }

            public abstract int tryRead()
                throws IOException;

            public abstract int tryRead(byte abyte0[])
                throws IOException;

            public abstract int tryRead(byte abyte0[], int i, int j)
                throws IOException;

            public static final int NOTHING = 0x80000000;
}
