// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Errors.java

package org.glassfish.jersey.internal;

import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.util.Producer;

// Referenced classes of package org.glassfish.jersey.internal:
//            Errors

public static class severity
{

            public Severity getSeverity()
            {
/* 486*/        return severity;
            }

            public String getMessage()
            {
/* 495*/        return message;
            }

            public Object getSource()
            {
/* 506*/        return source;
            }

            public boolean equals(Object obj)
            {
/* 511*/        if(this == obj)
/* 512*/            return true;
/* 514*/        if(obj == null || getClass() != obj.getClass())
/* 515*/            return false;
/* 518*/        obj = (source)obj;
/* 520*/        if(message == null ? ((message) (obj)).message != null : !message.equals(((message) (obj)).message))
/* 521*/            return false;
/* 523*/        if(severity != ((severity) (obj)).severity)
/* 524*/            return false;
/* 526*/        return source == null ? ((source) (obj)).source == null : source.equals(((source) (obj)).source);
            }

            public int hashCode()
            {
/* 535*/        int i = source == null ? 0 : source.hashCode();
/* 536*/        i = i * 31 + (message == null ? 0 : message.hashCode());
/* 537*/        return i = i * 31 + (severity == null ? 0 : severity.hashCode());
            }

            private final Object source;
            private final String message;
            private final Severity severity;

            private (Object obj, String s, Severity severity1)
            {
/* 475*/        source = obj;
/* 476*/        message = s;
/* 477*/        severity = severity1;
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
