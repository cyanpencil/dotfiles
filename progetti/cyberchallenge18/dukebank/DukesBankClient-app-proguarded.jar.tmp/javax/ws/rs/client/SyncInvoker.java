// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SyncInvoker.java

package javax.ws.rs.client;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

// Referenced classes of package javax.ws.rs.client:
//            Entity

public interface SyncInvoker
{

    public abstract Response get();

    public abstract Object get(Class class1);

    public abstract Object get(GenericType generictype);

    public abstract Response put(Entity entity);

    public abstract Object put(Entity entity, Class class1);

    public abstract Object put(Entity entity, GenericType generictype);

    public abstract Response post(Entity entity);

    public abstract Object post(Entity entity, Class class1);

    public abstract Object post(Entity entity, GenericType generictype);

    public abstract Response delete();

    public abstract Object delete(Class class1);

    public abstract Object delete(GenericType generictype);

    public abstract Response head();

    public abstract Response options();

    public abstract Object options(Class class1);

    public abstract Object options(GenericType generictype);

    public abstract Response trace();

    public abstract Object trace(Class class1);

    public abstract Object trace(GenericType generictype);

    public abstract Response method(String s);

    public abstract Object method(String s, Class class1);

    public abstract Object method(String s, GenericType generictype);

    public abstract Response method(String s, Entity entity);

    public abstract Object method(String s, Entity entity, Class class1);

    public abstract Object method(String s, Entity entity, GenericType generictype);
}
