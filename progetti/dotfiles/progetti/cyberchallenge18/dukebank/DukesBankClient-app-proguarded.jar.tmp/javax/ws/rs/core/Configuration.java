// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Configuration.java

package javax.ws.rs.core;

import java.util.*;
import javax.ws.rs.RuntimeType;

// Referenced classes of package javax.ws.rs.core:
//            Feature

public interface Configuration
{

    public abstract RuntimeType getRuntimeType();

    public abstract Map getProperties();

    public abstract Object getProperty(String s);

    public abstract Collection getPropertyNames();

    public abstract boolean isEnabled(Feature feature);

    public abstract boolean isEnabled(Class class1);

    public abstract boolean isRegistered(Object obj);

    public abstract boolean isRegistered(Class class1);

    public abstract Map getContracts(Class class1);

    public abstract Set getClasses();

    public abstract Set getInstances();
}
