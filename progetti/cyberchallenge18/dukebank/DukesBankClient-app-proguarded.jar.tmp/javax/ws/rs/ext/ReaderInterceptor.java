// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderInterceptor.java

package javax.ws.rs.ext;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;

// Referenced classes of package javax.ws.rs.ext:
//            ReaderInterceptorContext

public interface ReaderInterceptor
{

    public abstract Object aroundReadFrom(ReaderInterceptorContext readerinterceptorcontext)
        throws IOException, WebApplicationException;
}
