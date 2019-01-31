// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InjectionResolver.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            Injectee, ServiceHandle

public interface InjectionResolver
{

    public abstract Object resolve(Injectee injectee, ServiceHandle servicehandle);

    public abstract boolean isConstructorParameterIndicator();

    public abstract boolean isMethodParameterIndicator();

    public static final String SYSTEM_RESOLVER_NAME = "SystemInjectResolver";
}
