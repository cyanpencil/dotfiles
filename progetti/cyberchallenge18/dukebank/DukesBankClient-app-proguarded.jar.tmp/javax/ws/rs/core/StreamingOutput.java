// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StreamingOutput.java

package javax.ws.rs.core;

import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;

public interface StreamingOutput
{

    public abstract void write(OutputStream outputstream)
        throws IOException, WebApplicationException;
}
