// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   RequestEntityProcessing.java

package org.glassfish.jersey.client;


public final class RequestEntityProcessing extends Enum
{

            public static RequestEntityProcessing[] values()
            {
/*  48*/        return (RequestEntityProcessing[])$VALUES.clone();
            }

            public static RequestEntityProcessing valueOf(String s)
            {
/*  48*/        return (RequestEntityProcessing)Enum.valueOf(org/glassfish/jersey/client/RequestEntityProcessing, s);
            }

            private RequestEntityProcessing(String s, int i)
            {
/*  48*/        super(s, i);
            }

            public static final RequestEntityProcessing BUFFERED;
            public static final RequestEntityProcessing CHUNKED;
            private static final RequestEntityProcessing $VALUES[];

            static 
            {
/*  53*/        BUFFERED = new RequestEntityProcessing("BUFFERED", 0);
/*  58*/        CHUNKED = new RequestEntityProcessing("CHUNKED", 1);
/*  48*/        $VALUES = (new RequestEntityProcessing[] {
/*  48*/            BUFFERED, CHUNKED
                });
            }
}
