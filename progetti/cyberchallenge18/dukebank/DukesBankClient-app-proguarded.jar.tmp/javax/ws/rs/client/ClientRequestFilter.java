// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientRequestFilter.java

package javax.ws.rs.client;

import java.io.IOException;

// Referenced classes of package javax.ws.rs.client:
//            ClientRequestContext

public interface ClientRequestFilter
{

    public abstract void filter(ClientRequestContext clientrequestcontext)
        throws IOException;
}
