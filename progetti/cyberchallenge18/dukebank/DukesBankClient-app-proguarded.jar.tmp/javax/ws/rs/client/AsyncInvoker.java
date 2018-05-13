// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AsyncInvoker.java

package javax.ws.rs.client;

import java.util.concurrent.Future;
import javax.ws.rs.core.GenericType;

// Referenced classes of package javax.ws.rs.client:
//            InvocationCallback, Entity

public interface AsyncInvoker
{

    public abstract Future get();

    public abstract Future get(Class class1);

    public abstract Future get(GenericType generictype);

    public abstract Future get(InvocationCallback invocationcallback);

    public abstract Future put(Entity entity);

    public abstract Future put(Entity entity, Class class1);

    public abstract Future put(Entity entity, GenericType generictype);

    public abstract Future put(Entity entity, InvocationCallback invocationcallback);

    public abstract Future post(Entity entity);

    public abstract Future post(Entity entity, Class class1);

    public abstract Future post(Entity entity, GenericType generictype);

    public abstract Future post(Entity entity, InvocationCallback invocationcallback);

    public abstract Future delete();

    public abstract Future delete(Class class1);

    public abstract Future delete(GenericType generictype);

    public abstract Future delete(InvocationCallback invocationcallback);

    public abstract Future head();

    public abstract Future head(InvocationCallback invocationcallback);

    public abstract Future options();

    public abstract Future options(Class class1);

    public abstract Future options(GenericType generictype);

    public abstract Future options(InvocationCallback invocationcallback);

    public abstract Future trace();

    public abstract Future trace(Class class1);

    public abstract Future trace(GenericType generictype);

    public abstract Future trace(InvocationCallback invocationcallback);

    public abstract Future method(String s);

    public abstract Future method(String s, Class class1);

    public abstract Future method(String s, GenericType generictype);

    public abstract Future method(String s, InvocationCallback invocationcallback);

    public abstract Future method(String s, Entity entity);

    public abstract Future method(String s, Entity entity, Class class1);

    public abstract Future method(String s, Entity entity, GenericType generictype);

    public abstract Future method(String s, Entity entity, InvocationCallback invocationcallback);
}
