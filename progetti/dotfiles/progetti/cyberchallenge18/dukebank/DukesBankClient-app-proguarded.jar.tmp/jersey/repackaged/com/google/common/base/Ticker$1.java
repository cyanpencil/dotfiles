// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Ticker.java

package jersey.repackaged.com.google.common.base;


// Referenced classes of package jersey.repackaged.com.google.common.base:
//            Ticker, Platform

static class  extends Ticker
{

            public final long read()
            {
/*  60*/        return Platform.systemNanoTime();
            }

            ()
            {
            }
}
