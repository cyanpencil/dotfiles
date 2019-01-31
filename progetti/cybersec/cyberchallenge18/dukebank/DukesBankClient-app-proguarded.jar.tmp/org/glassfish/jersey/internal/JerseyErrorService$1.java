// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyErrorService.java

package org.glassfish.jersey.internal;

import org.glassfish.hk2.api.ErrorInformation;

// Referenced classes of package org.glassfish.jersey.internal:
//            Errors, JerseyErrorService, LocalizationMessages

class val.msg
    implements Runnable
{

            public void run()
            {
/*  91*/        Errors.warning(this, LocalizationMessages.HK_2_FAILURE_OUTSIDE_ERROR_SCOPE());
/*  92*/        Errors.warning(val$error.getInjectee(), val$msg);
            }

            final ErrorInformation val$error;
            final String val$msg;
            final JerseyErrorService this$0;

            ()
            {
/*  88*/        this$0 = final_jerseyerrorservice;
/*  88*/        val$error = errorinformation;
/*  88*/        val$msg = String.this;
/*  88*/        super();
            }
}
