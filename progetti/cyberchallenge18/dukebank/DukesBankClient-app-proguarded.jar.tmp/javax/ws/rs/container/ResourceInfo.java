// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ResourceInfo.java

package javax.ws.rs.container;

import java.lang.reflect.Method;

public interface ResourceInfo
{

    public abstract Method getResourceMethod();

    public abstract Class getResourceClass();
}
