// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContainerResponseFilter.java

package javax.ws.rs.container;

import java.io.IOException;

// Referenced classes of package javax.ws.rs.container:
//            ContainerRequestContext, ContainerResponseContext

public interface ContainerResponseFilter
{

    public abstract void filter(ContainerRequestContext containerrequestcontext, ContainerResponseContext containerresponsecontext)
        throws IOException;
}
