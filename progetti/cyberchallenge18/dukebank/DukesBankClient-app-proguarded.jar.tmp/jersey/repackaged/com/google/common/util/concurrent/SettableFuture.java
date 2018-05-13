// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SettableFuture.java

package jersey.repackaged.com.google.common.util.concurrent;


// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            AbstractFuture

public final class SettableFuture extends AbstractFuture
{

            public static SettableFuture create()
            {
/*  34*/        return new SettableFuture();
            }

            private SettableFuture()
            {
            }

            public final boolean set(Object obj)
            {
/*  53*/        return super.set(obj);
            }

            public final boolean setException(Throwable throwable)
            {
/*  68*/        return super.setException(throwable);
            }
}
