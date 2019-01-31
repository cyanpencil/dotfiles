// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContentEncoder.java

package org.glassfish.jersey.spi;

import java.io.*;
import java.util.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.*;
import jersey.repackaged.com.google.common.collect.Sets;

public abstract class ContentEncoder
    implements ReaderInterceptor, WriterInterceptor
{

            protected transient ContentEncoder(String as[])
            {
/*  81*/        if(as.length == 0)
                {
/*  82*/            throw new IllegalArgumentException();
                } else
                {
/*  84*/            supportedEncodings = Collections.unmodifiableSet(Sets.newHashSet(Arrays.asList(as)));
/*  85*/            return;
                }
            }

            public final Set getSupportedEncodings()
            {
/*  92*/        return supportedEncodings;
            }

            public abstract InputStream decode(String s, InputStream inputstream)
                throws IOException;

            public abstract OutputStream encode(String s, OutputStream outputstream)
                throws IOException;

            public final Object aroundReadFrom(ReaderInterceptorContext readerinterceptorcontext)
                throws IOException, WebApplicationException
            {
                String s;
/* 123*/        if((s = (String)readerinterceptorcontext.getHeaders().getFirst("Content-Encoding")) != null && getSupportedEncodings().contains(s))
/* 125*/            readerinterceptorcontext.setInputStream(decode(s, readerinterceptorcontext.getInputStream()));
/* 127*/        return readerinterceptorcontext.proceed();
            }

            public final void aroundWriteTo(WriterInterceptorContext writerinterceptorcontext)
                throws IOException, WebApplicationException
            {
                String s;
/* 134*/        if((s = (String)writerinterceptorcontext.getHeaders().getFirst("Content-Encoding")) != null && getSupportedEncodings().contains(s))
/* 136*/            writerinterceptorcontext.setOutputStream(encode(s, writerinterceptorcontext.getOutputStream()));
/* 138*/        writerinterceptorcontext.proceed();
            }

            private final Set supportedEncodings;
}
