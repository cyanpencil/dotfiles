// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EncodingFilter.java

package org.glassfish.jersey.client.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MultivaluedMap;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.spi.ContentEncoder;

public final class EncodingFilter
    implements ClientRequestFilter
{

            public EncodingFilter()
            {
/*  80*/        supportedEncodings = null;
            }

            public final void filter(ClientRequestContext clientrequestcontext)
                throws IOException
            {
/*  84*/        if(getSupportedEncodings().isEmpty())
/*  85*/            return;
/*  88*/        clientrequestcontext.getHeaders().addAll("Accept-Encoding", getSupportedEncodings());
                String s;
/*  90*/        if((s = (String)clientrequestcontext.getConfiguration().getProperty("jersey.config.client.useEncoding")) != null)
                {
/*  92*/            if(!getSupportedEncodings().contains(s))
                    {
/*  93*/                Logger.getLogger(getClass().getName()).warning(LocalizationMessages.USE_ENCODING_IGNORED("jersey.config.client.useEncoding", s, getSupportedEncodings()));
/*  93*/                return;
                    }
/*  96*/            if(clientrequestcontext.hasEntity() && clientrequestcontext.getHeaders().getFirst("Content-Encoding") == null)
/*  98*/                clientrequestcontext.getHeaders().putSingle("Content-Encoding", s);
                }
            }

            final List getSupportedEncodings()
            {
/* 108*/        if(supportedEncodings == null)
                {
/* 109*/            TreeSet treeset = Sets.newTreeSet();
                    Object obj;
                    ContentEncoder contentencoder;
/* 110*/            for(obj = ((List) (obj = serviceLocator.getAllServices(org/glassfish/jersey/spi/ContentEncoder, new Annotation[0]))).iterator(); ((Iterator) (obj)).hasNext(); treeset.addAll(contentencoder.getSupportedEncodings()))
/* 111*/                contentencoder = (ContentEncoder)((Iterator) (obj)).next();

/* 114*/            supportedEncodings = new ArrayList(treeset);
                }
/* 116*/        return supportedEncodings;
            }

            private ServiceLocator serviceLocator;
            private volatile List supportedEncodings;
}
