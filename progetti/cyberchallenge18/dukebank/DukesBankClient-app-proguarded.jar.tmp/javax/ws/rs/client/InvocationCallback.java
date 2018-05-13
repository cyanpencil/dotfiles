// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InvocationCallback.java

package javax.ws.rs.client;


public interface InvocationCallback
{

    public abstract void completed(Object obj);

    public abstract void failed(Throwable throwable);
}
