// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyErrorService.java

package org.glassfish.jersey.internal;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.inject.Singleton;
import org.glassfish.hk2.api.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.hk2.utilities.binding.ServiceBindingBuilder;

// Referenced classes of package org.glassfish.jersey.internal:
//            Errors, LocalizationMessages

public final class JerseyErrorService
    implements ErrorService
{
    public static final class Binder extends AbstractBinder
    {

                protected final void configure()
                {
/*  67*/            bind(org/glassfish/jersey/internal/JerseyErrorService).to(org/glassfish/hk2/api/ErrorService).in(javax/inject/Singleton);
                }

                public Binder()
                {
                }
    }


            public JerseyErrorService()
            {
            }

            public final void onFailure(final ErrorInformation error)
                throws MultiException
            {
        static class _cls2
        {

                    static final int $SwitchMap$org$glassfish$hk2$api$ErrorType[];

                    static 
                    {
/*  75*/                $SwitchMap$org$glassfish$hk2$api$ErrorType = new int[ErrorType.values().length];
/*  75*/                try
                        {
/*  75*/                    $SwitchMap$org$glassfish$hk2$api$ErrorType[ErrorType.FAILURE_TO_REIFY.ordinal()] = 1;
                        }
/*  75*/                catch(NoSuchFieldError _ex) { }
                    }
        }

                final String msg;
/*  75*/        switch(_cls2..SwitchMap.org.glassfish.hk2.api.ErrorType[error.getErrorType().ordinal()])
                {
/*  77*/        case 1: // '\001'
/*  77*/            msg = LocalizationMessages.HK_2_REIFICATION_ERROR(error.getDescriptor().getImplementation(), printStackTrace(error.getAssociatedException()));
                    break;

/*  81*/        default:
/*  81*/            msg = LocalizationMessages.HK_2_UNKNOWN_ERROR(printStackTrace(error.getAssociatedException()));
                    break;
                }
/*  86*/        try
                {
/*  86*/            Errors.warning(error.getInjectee(), msg);
/*  95*/            return;
                }
/*  87*/        catch(IllegalStateException _ex)
                {
/*  88*/            Errors.process(new Runnable() {

                        public void run()
                        {
/*  91*/                    Errors.warning(this, LocalizationMessages.HK_2_FAILURE_OUTSIDE_ERROR_SCOPE());
/*  92*/                    Errors.warning(error.getInjectee(), msg);
                        }

                        final ErrorInformation val$error;
                        final String val$msg;
                        final JerseyErrorService this$0;

                    
                    {
/*  88*/                this$0 = JerseyErrorService.this;
/*  88*/                error = errorinformation;
/*  88*/                msg = s;
/*  88*/                super();
                    }
            });
                }
            }

            private String printStackTrace(Throwable throwable)
            {
/*  99*/        StringWriter stringwriter = new StringWriter();
/* 100*/        throwable.printStackTrace(new PrintWriter(stringwriter));
/* 101*/        return stringwriter.toString();
            }
}
