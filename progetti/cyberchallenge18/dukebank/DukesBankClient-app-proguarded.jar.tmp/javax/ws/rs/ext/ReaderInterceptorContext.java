// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderInterceptorContext.java

package javax.ws.rs.ext;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

// Referenced classes of package javax.ws.rs.ext:
//            InterceptorContext

public interface ReaderInterceptorContext
    extends InterceptorContext
{

    public abstract Object proceed()
        throws IOException, WebApplicationException;

    public abstract InputStream getInputStream();

    public abstract void setInputStream(InputStream inputstream);

    public abstract MultivaluedMap getHeaders();
}
