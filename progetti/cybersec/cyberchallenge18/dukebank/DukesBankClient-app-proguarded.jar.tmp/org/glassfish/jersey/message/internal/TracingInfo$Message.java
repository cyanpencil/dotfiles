// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingInfo.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingInfo, TracingLogger

public static class text
{

            private text getEvent()
            {
/* 185*/        return event;
            }

            private long getDuration()
            {
/* 189*/        return duration;
            }

            private long getTimestamp()
            {
/* 193*/        return timestamp;
            }

            public String toString()
            {
/* 198*/        return text;
            }

            private final text event;
            private final long duration;
            private final long timestamp = System.nanoTime();
            private final String text;




            public ( , long l, String as[])
            {
/* 169*/        event = ;
/* 170*/        duration = l;
/* 173*/        if(.messageFormat() != null)
                {
/* 174*/            text = String.format(.messageFormat(), (Object[])as);
/* 174*/            return;
                }
/* 176*/         = new StringBuilder();
/* 177*/        i = (l = as).length;
/* 177*/        for(as = 0; as < i; as++)
                {
/* 177*/            String s = l[as];
/* 178*/            .append(s).append(' ');
                }

/* 180*/        text = .toString();
            }
}
