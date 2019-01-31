// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstanceLifecycleEvent.java

package org.glassfish.hk2.api;

import java.util.Map;

// Referenced classes of package org.glassfish.hk2.api:
//            InstanceLifecycleEventType, ActiveDescriptor

public interface InstanceLifecycleEvent
{

    public abstract InstanceLifecycleEventType getEventType();

    public abstract ActiveDescriptor getActiveDescriptor();

    public abstract Object getLifecycleObject();

    public abstract Map getKnownInjectees();
}
