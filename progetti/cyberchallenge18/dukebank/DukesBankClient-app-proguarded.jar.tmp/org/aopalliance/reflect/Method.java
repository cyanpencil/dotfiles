// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Method.java

package org.aopalliance.reflect;


// Referenced classes of package org.aopalliance.reflect:
//            Member, CodeLocator, Code

public interface Method
    extends Member
{

    public abstract CodeLocator getCallLocator();

    public abstract CodeLocator getCallLocator(int i);

    public abstract Code getBody();
}
