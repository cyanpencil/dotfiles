// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FutureCallback.java

package jersey.repackaged.com.google.common.util.concurrent;


public interface FutureCallback
{

    public abstract void onSuccess(Object obj);

    public abstract void onFailure(Throwable throwable);
}