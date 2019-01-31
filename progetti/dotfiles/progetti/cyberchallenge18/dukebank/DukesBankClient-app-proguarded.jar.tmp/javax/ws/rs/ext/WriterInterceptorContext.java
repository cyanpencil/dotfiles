// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WriterInterceptorContext.java

package javax.ws.rs.ext;

import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package javax.ws.rs.ext:
//            InterceptorContext

public interface WriterInterceptorContext
    extends InterceptorContext
{

    public abstract void proceed()
        throws IOException, WebApplicationException;

    public abstract Object getEntity();

    public abstract void setEntity(Object obj);

    public abstract OutputStream getOutputStream();

    public abstract void setOutputStream(OutputStream outputstream);

    public abstract MultivaluedMap getHeaders();
}
