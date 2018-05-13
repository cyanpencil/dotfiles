// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Invocation.java

package javax.ws.rs.client;

import java.util.Locale;
import java.util.concurrent.Future;
import javax.ws.rs.core.*;

// Referenced classes of package javax.ws.rs.client:
//            InvocationCallback, SyncInvoker, Entity, AsyncInvoker

public interface Invocation
{
    public static interface Builder
        extends SyncInvoker
    {

        public abstract Invocation build(String s);

        public abstract Invocation build(String s, Entity entity);

        public abstract Invocation buildGet();

        public abstract Invocation buildDelete();

        public abstract Invocation buildPost(Entity entity);

        public abstract Invocation buildPut(Entity entity);

        public abstract AsyncInvoker async();

        public transient abstract Builder accept(String as[]);

        public transient abstract Builder accept(MediaType amediatype[]);

        public transient abstract Builder acceptLanguage(Locale alocale[]);

        public transient abstract Builder acceptLanguage(String as[]);

        public transient abstract Builder acceptEncoding(String as[]);

        public abstract Builder cookie(Cookie cookie1);

        public abstract Builder cookie(String s, String s1);

        public abstract Builder cacheControl(CacheControl cachecontrol);

        public abstract Builder header(String s, Object obj);

        public abstract Builder headers(MultivaluedMap multivaluedmap);

        public abstract Builder property(String s, Object obj);
    }


    public abstract Invocation property(String s, Object obj);

    public abstract Response invoke();

    public abstract Object invoke(Class class1);

    public abstract Object invoke(GenericType generictype);

    public abstract Future submit();

    public abstract Future submit(Class class1);

    public abstract Future submit(GenericType generictype);

    public abstract Future submit(InvocationCallback invocationcallback);
}
