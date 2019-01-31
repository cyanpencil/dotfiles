// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Configurable.java

package javax.ws.rs.core;

import java.util.Map;

// Referenced classes of package javax.ws.rs.core:
//            Configuration

public interface Configurable
{

    public abstract Configuration getConfiguration();

    public abstract Configurable property(String s, Object obj);

    public abstract Configurable register(Class class1);

    public abstract Configurable register(Class class1, int i);

    public transient abstract Configurable register(Class class1, Class aclass[]);

    public abstract Configurable register(Class class1, Map map);

    public abstract Configurable register(Object obj);

    public abstract Configurable register(Object obj, int i);

    public transient abstract Configurable register(Object obj, Class aclass[]);

    public abstract Configurable register(Object obj, Map map);
}
