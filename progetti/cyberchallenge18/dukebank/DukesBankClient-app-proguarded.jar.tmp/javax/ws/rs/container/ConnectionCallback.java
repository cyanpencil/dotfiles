// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ConnectionCallback.java

package javax.ws.rs.container;


// Referenced classes of package javax.ws.rs.container:
//            AsyncResponse

public interface ConnectionCallback
{

    public abstract void onDisconnect(AsyncResponse asyncresponse);
}
