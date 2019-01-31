// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContextualInput.java

package org.glassfish.hk2.utilities;

import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.ServiceHandle;

public class ContextualInput
{

            public ContextualInput(ActiveDescriptor activedescriptor, ServiceHandle servicehandle)
            {
/*  65*/        descriptor = activedescriptor;
/*  66*/        root = servicehandle;
            }

            public ActiveDescriptor getDescriptor()
            {
/*  74*/        return descriptor;
            }

            public ServiceHandle getRoot()
            {
/*  84*/        return root;
            }

            public int hashCode()
            {
/*  89*/        return descriptor.hashCode();
            }

            public boolean equals(Object obj)
            {
/*  95*/        if(obj == null)
/*  95*/            return false;
/*  96*/        if(!(obj instanceof ContextualInput))
                {
/*  96*/            return false;
                } else
                {
/*  98*/            obj = (ContextualInput)obj;
/* 100*/            return descriptor.equals(((ContextualInput) (obj)).descriptor);
                }
            }

            public String toString()
            {
/* 105*/        return (new StringBuilder("ContextualInput(")).append(descriptor.getImplementation()).append(",").append(root).append(",").append(System.identityHashCode(this)).append(")").toString();
            }

            private final ActiveDescriptor descriptor;
            private final ServiceHandle root;
}
