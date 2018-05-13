// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Creator.java

package org.jvnet.hk2.internal;

import java.util.List;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceHandle;

// Referenced classes of package org.jvnet.hk2.internal:
//            SystemDescriptor

public interface Creator
{

    public abstract List getInjectees();

    public abstract Object create(ServiceHandle servicehandle, SystemDescriptor systemdescriptor)
        throws MultiException;

    public abstract void dispose(Object obj)
        throws MultiException;
}
