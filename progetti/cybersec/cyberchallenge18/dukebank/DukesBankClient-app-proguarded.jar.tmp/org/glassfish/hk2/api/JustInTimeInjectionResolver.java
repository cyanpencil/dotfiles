// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JustInTimeInjectionResolver.java

package org.glassfish.hk2.api;


// Referenced classes of package org.glassfish.hk2.api:
//            Injectee

public interface JustInTimeInjectionResolver
{

    public abstract boolean justInTimeResolution(Injectee injectee);
}
