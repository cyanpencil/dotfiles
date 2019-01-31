// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AsyncResponse.java

package javax.ws.rs.container;

import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package javax.ws.rs.container:
//            TimeoutHandler

public interface AsyncResponse
{

    public abstract boolean resume(Object obj);

    public abstract boolean resume(Throwable throwable);

    public abstract boolean cancel();

    public abstract boolean cancel(int i);

    public abstract boolean cancel(Date date);

    public abstract boolean isSuspended();

    public abstract boolean isCancelled();

    public abstract boolean isDone();

    public abstract boolean setTimeout(long l, TimeUnit timeunit);

    public abstract void setTimeoutHandler(TimeoutHandler timeouthandler);

    public abstract Collection register(Class class1);

    public transient abstract Map register(Class class1, Class aclass[]);

    public abstract Collection register(Object obj);

    public transient abstract Map register(Object obj, Object aobj[]);

    public static final long NO_TIMEOUT = 0L;
}
