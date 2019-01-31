// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResourceContext.java

package javax.ws.rs.container;


public interface ResourceContext
{

    public abstract Object getResource(Class class1);

    public abstract Object initResource(Object obj);
}
