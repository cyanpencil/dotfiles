// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LocalCache.java

package jersey.repackaged.com.google.common.cache;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;

// Referenced classes of package jersey.repackaged.com.google.common.cache:
//            LocalCache

static interface I
{

    public abstract Object get();

    public abstract Object waitForValue()
        throws ExecutionException;

    public abstract int getWeight();

    public abstract I getEntry();

    public abstract I copyFor(ReferenceQueue referencequeue, Object obj, I i);

    public abstract void notifyNewValue(Object obj);

    public abstract boolean isLoading();

    public abstract boolean isActive();
}
