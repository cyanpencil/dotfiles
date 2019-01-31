// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WriterInterceptor.java

package javax.ws.rs.ext;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;

// Referenced classes of package javax.ws.rs.ext:
//            WriterInterceptorContext

public interface WriterInterceptor
{

    public abstract void aroundWriteTo(WriterInterceptorContext writerinterceptorcontext)
        throws IOException, WebApplicationException;
}
