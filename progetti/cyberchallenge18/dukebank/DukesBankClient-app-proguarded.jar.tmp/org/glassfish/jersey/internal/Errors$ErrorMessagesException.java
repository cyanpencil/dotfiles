// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Errors.java

package org.glassfish.jersey.internal;

import java.util.List;
import org.glassfish.jersey.internal.util.Producer;

// Referenced classes of package org.glassfish.jersey.internal:
//            Errors

public static class messages extends RuntimeException
{

            public List getMessages()
            {
/* 461*/        return messages;
            }

            private final List messages;

            private A(List list)
            {
/* 452*/        messages = list;
            }


            // Unreferenced inner class org/glassfish/jersey/internal/Errors$1

/* anonymous class */
    static class Errors._cls1
        implements Producer
    {

                public final Void call()
                {
/* 271*/            task.run();
/* 272*/            return null;
                }

                public final volatile Object call()
                {
/* 267*/            return call();
                }

                final Runnable val$task;

                    
                    {
/* 267*/                task = runnable;
/* 267*/                super();
                    }
    }

}
