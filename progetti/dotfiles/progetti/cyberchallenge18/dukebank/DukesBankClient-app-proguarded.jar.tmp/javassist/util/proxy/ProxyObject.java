// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ProxyObject.java

package javassist.util.proxy;


// Referenced classes of package javassist.util.proxy:
//            Proxy, MethodHandler

public interface ProxyObject
    extends Proxy
{

    public abstract void setHandler(MethodHandler methodhandler);

    public abstract MethodHandler getHandler();
}
