// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Invocation.java

package javax.ws.rs.client;

import java.util.Locale;
import javax.ws.rs.core.*;

// Referenced classes of package javax.ws.rs.client:
//            Invocation, SyncInvoker, Entity, AsyncInvoker

public static interface 
    extends SyncInvoker
{

    public abstract Invocation build(String s);

    public abstract Invocation build(String s, Entity entity);

    public abstract Invocation buildGet();

    public abstract Invocation buildDelete();

    public abstract Invocation buildPost(Entity entity);

    public abstract Invocation buildPut(Entity entity);

    public abstract AsyncInvoker async();

    public transient abstract  accept(String as[]);

    public transient abstract  accept(MediaType amediatype[]);

    public transient abstract  acceptLanguage(Locale alocale[]);

    public transient abstract  acceptLanguage(String as[]);

    public transient abstract  acceptEncoding(String as[]);

    public abstract  cookie(Cookie cookie1);

    public abstract  cookie(String s, String s1);

    public abstract  cacheControl(CacheControl cachecontrol);

    public abstract  header(String s, Object obj);

    public abstract  headers(MultivaluedMap multivaluedmap);

    public abstract  property(String s, Object obj);
}
