// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientRequest.java

package org.glassfish.jersey.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;
import org.glassfish.jersey.internal.util.ExceptionUtils;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.internal.OutboundMessageContext;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientConfig, ClientRuntime, JerseyClient

public class ClientRequest extends OutboundMessageContext
    implements ClientRequestContext, ServiceLocatorSupplier
{

            protected ClientRequest(URI uri, ClientConfig clientconfig, PropertiesDelegate propertiesdelegate)
            {
/* 121*/        clientconfig.checkClient();
/* 123*/        requestUri = uri;
/* 124*/        clientConfig = clientconfig;
/* 125*/        propertiesDelegate = propertiesdelegate;
            }

            public ClientRequest(ClientRequest clientrequest)
            {
/* 134*/        super(clientrequest);
/* 135*/        requestUri = clientrequest.requestUri;
/* 136*/        httpMethod = clientrequest.httpMethod;
/* 137*/        workers = clientrequest.workers;
/* 138*/        clientConfig = clientrequest.clientConfig.snapshot();
/* 139*/        asynchronous = clientrequest.isAsynchronous();
/* 140*/        readerInterceptors = clientrequest.readerInterceptors;
/* 141*/        writerInterceptors = clientrequest.writerInterceptors;
/* 142*/        propertiesDelegate = new MapPropertiesDelegate(clientrequest.propertiesDelegate);
/* 143*/        ignoreUserAgent = clientrequest.ignoreUserAgent;
            }

            public Object resolveProperty(String s, Class class1)
            {
/* 165*/        return resolveProperty(s, null, class1);
            }

            public Object resolveProperty(String s, Object obj)
            {
/* 188*/        return resolveProperty(s, obj, obj.getClass());
            }

            private Object resolveProperty(String s, Object obj, Class class1)
            {
                Object obj1;
/* 193*/        if((obj1 = clientConfig.getProperty(s)) != null)
/* 195*/            obj = obj1;
/* 199*/        if((obj1 = propertiesDelegate.getProperty(s)) == null)
/* 201*/            obj1 = obj;
/* 204*/        if(obj1 == null)
/* 204*/            return null;
/* 204*/        else
/* 204*/            return PropertiesHelper.convertValue(obj1, class1);
            }

            public Object getProperty(String s)
            {
/* 209*/        return propertiesDelegate.getProperty(s);
            }

            public Collection getPropertyNames()
            {
/* 214*/        return propertiesDelegate.getPropertyNames();
            }

            public void setProperty(String s, Object obj)
            {
/* 219*/        propertiesDelegate.setProperty(s, obj);
            }

            public void removeProperty(String s)
            {
/* 224*/        propertiesDelegate.removeProperty(s);
            }

            PropertiesDelegate getPropertiesDelegate()
            {
/* 233*/        return propertiesDelegate;
            }

            ClientRuntime getClientRuntime()
            {
/* 242*/        return clientConfig.getRuntime();
            }

            public URI getUri()
            {
/* 247*/        return requestUri;
            }

            public void setUri(URI uri)
            {
/* 252*/        requestUri = uri;
            }

            public String getMethod()
            {
/* 257*/        return httpMethod;
            }

            public void setMethod(String s)
            {
/* 262*/        httpMethod = s;
            }

            public JerseyClient getClient()
            {
/* 267*/        return clientConfig.getClient();
            }

            public void abortWith(Response response)
            {
/* 272*/        abortResponse = response;
            }

            public Response getAbortResponse()
            {
/* 281*/        return abortResponse;
            }

            public Configuration getConfiguration()
            {
/* 286*/        return clientConfig.getRuntime().getConfig();
            }

            ClientConfig getClientConfig()
            {
/* 295*/        return clientConfig;
            }

            public Map getCookies()
            {
/* 300*/        return super.getRequestCookies();
            }

            public MessageBodyWorkers getWorkers()
            {
/* 309*/        return workers;
            }

            public void setWorkers(MessageBodyWorkers messagebodyworkers)
            {
/* 318*/        workers = messagebodyworkers;
            }

            public transient void accept(MediaType amediatype[])
            {
/* 327*/        getHeaders().addAll("Accept", (Object[])amediatype);
            }

            public transient void accept(String as[])
            {
/* 336*/        getHeaders().addAll("Accept", (Object[])as);
            }

            public transient void acceptLanguage(Locale alocale[])
            {
/* 345*/        getHeaders().addAll("Accept-Language", (Object[])alocale);
            }

            public transient void acceptLanguage(String as[])
            {
/* 354*/        getHeaders().addAll("Accept-Language", (Object[])as);
            }

            public void cookie(Cookie cookie1)
            {
/* 363*/        getHeaders().add("Cookie", cookie1);
            }

            public void cacheControl(CacheControl cachecontrol)
            {
/* 372*/        getHeaders().add("Cache-Control", cachecontrol);
            }

            public void encoding(String s)
            {
/* 381*/        if(s == null)
                {
/* 382*/            getHeaders().remove("Content-Encoding");
/* 382*/            return;
                } else
                {
/* 384*/            getHeaders().putSingle("Content-Encoding", s);
/* 386*/            return;
                }
            }

            public void language(String s)
            {
/* 394*/        if(s == null)
                {
/* 395*/            getHeaders().remove("Content-Language");
/* 395*/            return;
                } else
                {
/* 397*/            getHeaders().putSingle("Content-Language", s);
/* 399*/            return;
                }
            }

            public void language(Locale locale)
            {
/* 407*/        if(locale == null)
                {
/* 408*/            getHeaders().remove("Content-Language");
/* 408*/            return;
                } else
                {
/* 410*/            getHeaders().putSingle("Content-Language", locale);
/* 412*/            return;
                }
            }

            public void type(MediaType mediatype)
            {
/* 420*/        setMediaType(mediatype);
            }

            public void type(String s)
            {
/* 429*/        type(s != null ? MediaType.valueOf(s) : null);
            }

            public void variant(Variant variant1)
            {
/* 439*/        if(variant1 == null)
                {
/* 440*/            type(((MediaType) (null)));
/* 441*/            language(((String) (null)));
/* 442*/            encoding(null);
/* 442*/            return;
                } else
                {
/* 444*/            type(variant1.getMediaType());
/* 445*/            language(variant1.getLanguage());
/* 446*/            encoding(variant1.getEncoding());
/* 448*/            retur