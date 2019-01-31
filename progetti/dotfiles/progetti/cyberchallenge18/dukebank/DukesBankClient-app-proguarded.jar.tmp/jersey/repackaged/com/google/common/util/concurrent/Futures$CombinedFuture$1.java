// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Futures.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.Iterator;
import jersey.repackaged.com.google.common.collect.ImmutableCollection;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            Futures, ListenableFuture

class this._cls0
    implements Runnable
{

            public void run()
            {
/*1641*/        if(Cancelled())
                {
                    ListenableFuture listenablefuture;
/*1642*/            for(Iterator iterator = tures.iterator(); iterator.hasNext(); (listenablefuture = (ListenableFuture)iterator.next()).cancel(sInterrupted()));
                }
/*1648*/        tures = null;
/*1652*/        lues = null;
/*1655*/        mbiner = null;
            }

            final mbiner this$0;

            ()
            {
/*1637*/        this$0 = this._cls0.this;
/*1637*/        super();
            }
}
