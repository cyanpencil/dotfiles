// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TracingInfo.java

package org.glassfish.jersey.message.internal;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            TracingLogger

final class TracingInfo
{
    public static class Message
    {

                private TracingLogger.Event getEvent()
                {
/* 185*/            return event;
                }

                private long getDuration()
                {
/* 189*/            return duration;
                }

                private long getTimestamp()
                {
/* 193*/            return timestamp;
                }

                public String toString()
                {
/* 198*/            return text;
                }

                private final TracingLogger.Event event;
                private final long duration;
                private final long timestamp = System.nanoTime();
                private final String text;




                public Message(TracingLogger.Event event1, long l, String as[])
                {
/* 169*/            event = event1;
/* 170*/            duration = l;
/* 173*/            if(event1.messageFormat() != null)
                    {
/* 174*/                text = String.format(event1.messageFormat(), (Object[])as);
/* 174*/                return;
                    }
/* 176*/            event1 = new StringBuilder();
/* 177*/            i = (l = as).length;
/* 177*/            for(as = 0; as < i; as++)
                    {
/* 177*/                String s = l[as];
/* 178*/                event1.append(s).append(' ');
                    }

/* 180*/            text = event1.toString();
                }
    }


            TracingInfo()
            {
            }

            public static String formatDuration(long l)
            {
/*  62*/        if(l == 0L)
/*  63*/            return " ----";
/*  65*/        else
/*  65*/            return String.format("%5.2f", new Object[] {
/*  65*/                Double.valueOf((double)l / 1000000D)
                    });
            }

            public static String formatDuration(long l, long l1)
            {
/*  77*/        return formatDuration(l1 - l);
            }

            public static String formatPercent(long l, long l1)
            {
/*  88*/        if(l == 0L)
/*  89*/            return "  ----";
/*  91*/        else
/*  91*/            return String.format("%6.2f", new Object[] {
/*  91*/                Double.valueOf((100D * (double)l) / (double)l1)
                    });
            }

            public final String[] getMessages()
            {
/* 104*/        long l = ((Message)messageList.get(0)).getTimestamp() - ((Message)messageList.get(0)).getDuration();
/* 105*/        long l1 = ((Message)messageList.get(messageList.size() - 1)).getTimestamp();
/* 107*/        String as[] = new String[messageList.size()];
/* 109*/        for(int i = 0; i < as.length; i++)
                {
/* 110*/            Message message = (Message)messageList.get(i);
                    StringBuilder stringbuilder;
/* 111*/            (stringbuilder = new StringBuilder()).append(String.format("%-11s ", new Object[] {
/* 113*/                message.getEvent().category()
                    }));
/* 115*/            stringbuilder.append('[').append(formatDuration(message.getDuration())).append(" / ").append(formatDuration(l, message.getTimestamp())).append(" ms |").append(formatPercent(message.getDuration(), l1 - l)).append(" %] ");
/* 123*/            stringbuilder.append(message.toString());
/* 124*/            as[i] = stringbuilder.toString();
                }

/* 126*/        return as;
            }

            public final void addMessage(Message message)
            {
/* 135*/        messageList.add(message);
            }

            private final List messageList = new ArrayList();
}
