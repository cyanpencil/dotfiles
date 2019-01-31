// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ServiceLocatorListener.java

package org.glassfish.hk2.api;

import java.util.Set;

// Referenced classes of package org.glassfish.hk2.api:
//            ServiceLocator

public interface ServiceLocatorListener
{

    public abstract void initialize(Set set);

    public abstract void locatorAdded(ServiceLocator servicelocator);

    public abstract void locatorDestroyed(ServiceLocator servicelocator);
}
