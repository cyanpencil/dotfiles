// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TimeoutHandler.java

package javax.ws.rs.container;


// Referenced classes of package javax.ws.rs.container:
//            AsyncResponse

public interface TimeoutHandler
{

    public abstract void handleTimeout(AsyncResponse asyncresponse);
}
