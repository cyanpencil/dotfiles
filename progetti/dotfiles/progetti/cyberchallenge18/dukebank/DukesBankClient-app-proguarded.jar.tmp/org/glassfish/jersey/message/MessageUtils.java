// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageUtils.java

package org.glassfish.jersey.message;

import java.nio.charset.Charset;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.message.internal.ReaderWriter;

public final class MessageUtils
{

            public static Charset getCharset(MediaType mediatype)
            {
/*  65*/        return ReaderWriter.getCharset(mediatype);
            }

            private MessageUtils()
            {
/*  72*/        throw new AssertionError("No instances allowed.");
            }
}
