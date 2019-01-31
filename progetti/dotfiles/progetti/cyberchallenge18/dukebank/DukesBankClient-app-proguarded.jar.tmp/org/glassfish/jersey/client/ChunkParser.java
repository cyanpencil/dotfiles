// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ChunkParser.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.io.InputStream;

public interface ChunkParser
{

    public abstract byte[] readChunk(InputStream inputstream)
        throws IOException;
}
