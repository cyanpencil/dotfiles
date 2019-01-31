// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MoreExecutors.java

package jersey.repackaged.com.google.common.util.concurrent;

import java.util.concurrent.Executor;

// Referenced classes of package jersey.repackaged.com.google.common.util.concurrent:
//            MoreExecutors

static final class  extends Enum
    implements Executor
{

            public final void execute(Runnable runnable)
            {
/* 457*/        runnable.run();
            }

            public static final INSTANCE INSTANCE;
            private static final INSTANCE $VALUES[];

            static 
            {
/* 455*/        INSTANCE = new <init>("INSTANCE", 0);
/* 454*/        $VALUES = (new .VALUES[] {
/* 454*/            INSTANCE
                });
            }

            private (String s, int i)
            {
/* 454*/        super(s, i);
            }
}
