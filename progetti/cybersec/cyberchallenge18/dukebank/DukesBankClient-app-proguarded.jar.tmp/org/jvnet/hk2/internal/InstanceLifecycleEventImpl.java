// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstanceLifecycleEventImpl.java

package org.jvnet.hk2.internal;

import java.util.Collections;
import java.util.Map;
import org.glassfish.hk2.api.*;

public class InstanceLifecycleEventImpl
    implements InstanceLifecycleEvent
{

            InstanceLifecycleEventImpl(InstanceLifecycleEventType instancelifecycleeventtype, Object obj, Map map, ActiveDescriptor activedescriptor)
            {
/*  63*/        eventType = instancelifecycleeventtype;
/*  64*/        lifecycleObject = obj;
/*  65*/        if(map == null)
/*  66*/            knownInjectees = null;
/*  69*/        else
/*  69*/            knownInjectees = Collections.unmodifiableMap(map);
/*  71*/        descriptor = activedescriptor;
            }

            InstanceLifecycleEventImpl(InstanceLifecycleEventType instancelifecycleeventtype, Object obj, ActiveDescriptor activedescriptor)
            {
/*  76*/        this(instancelifecycleeventtype, obj, null, activedescriptor);
            }

            public InstanceLifecycleEventType getEventType()
            {
/*  84*/        return eventType;
            }

            public ActiveDescriptor getActiveDescriptor()
            {
/*  92*/        return descriptor;
            }

            public Object getLifecycleObject()
            {
/* 100*/        return lifecycleObject;
            }

            public Map getKnownInjectees()
            {
/* 108*/        return knownInjectees;
            }

            private final InstanceLifecycleEventType eventType;
            private final ActiveDescriptor descriptor;
            private final Object lifecycleObject;
            private final Map knownInjectees;
}
