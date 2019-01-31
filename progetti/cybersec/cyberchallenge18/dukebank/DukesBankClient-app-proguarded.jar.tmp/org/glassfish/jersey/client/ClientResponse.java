// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientResponse.java

package org.glassfish.jersey.client;

import java.io.*;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.base.MoreObjects;
import jersey.repackaged.com.google.common.collect.Collections2;
import jersey.repackaged.com.google.common.collect.Sets;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.inject.ServiceLocatorSupplier;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.internal.*;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRequest

public class ClientResponse extends InboundMessageContext
    implements ClientResponseContext, ServiceLocatorSupplier
{

            public ClientResponse(final ClientRequest requestContext, final Response response)
            {
/*  93*/        this(response.getStatusInfo(), requestContext);
/*  94*/        headers(OutboundJaxrsResponse.from(response).getContext().getStringHeaders());
                final Object entity;
/*  96*/        if((entity = response.getEntity()) != null)
                {
/*  98*/            requestContext = new InputStream() {

                        public int read()
                            throws IOException
                        {
                            ByteArrayOutputStream bytearrayoutputstream;
/* 104*/                    if(byteArrayInputStream != null)
/* 105*/                        break MISSING_BLOCK_LABEL_99;
/* 105*/                    bytearrayoutputstream = new ByteArrayOutputStream();
/* 109*/                    Object obj = requestContext.getWorkers().writeTo(entity, entity.getClass(), null, null, response.getMediaType(), response.getMetadata(), requestContext.getPropertiesDelegate(), bytearrayoutputstream, Collections.emptyList());
/* 114*/                    if(obj != null)
/* 115*/                        ((OutputStream) (obj)).close();
/* 115*/                    break MISSING_BLOCK_LABEL_84;
/* 114*/                    JVM INSTR dup ;
/* 115*/                    obj;
/* 115*/                    throw ;
/* 118*/                    JVM INSTR pop ;
/* 122*/                    byteArrayInputStream = new ByteArrayInputStream(bytearrayoutputstream.toByteArray());
/* 125*/                    return byteArrayInputStream.read();
                        }

                        private ByteArrayInputStream byteArrayInputStream;
                        final ClientRequest val$requestContext;
                        final Object val$entity;
                        final Response val$response;
                        final ClientResponse this$0;

                    
                    {
/*  98*/                this$0 = ClientResponse.this;
/*  98*/                requestContext = clientrequest;
/*  98*/                entity = obj;
/*  98*/                response = response1;
/*  98*/                super();
/* 100*/                byteArrayInputStream = null;
                    }
            };
/* 128*/            setEntityStream(requestContext);
                }
            }

            public ClientResponse(javax.ws.rs.core.Response.StatusType statustype, ClientRequest clientrequest)
            {
/* 139*/        this(statustype, clientrequest, clientrequest.getUri());
            }

            public ClientResponse(javax.ws.rs.core.Response.StatusType statustype, ClientRequest clientrequest, URI uri)
            {
/* 150*/        status = statustype;
/* 151*/        resolvedUri = uri;
/* 152*/        requestContext = clientrequest;
/* 154*/        setWorkers(clientrequest.getWorkers());
            }

            public int getStatus()
            {
/* 159*/        return status.getStatusCode();
            }

            public void setStatus(int i)
            {
/* 164*/        status = Statuses.from(i);
            }

            public void setStatusInfo(javax.ws.rs.core.Response.StatusType statustype)
            {
/* 169*/        if(statustype == null)
                {
/* 170*/            throw new NullPointerException(LocalizationMessages.CLIENT_RESPONSE_STATUS_NULL());
                } else
                {
/* 172*/            status = statustype;
/* 173*/            return;
                }
            }

            public javax.ws.rs.core.Response.StatusType getStatusInfo()
            {
/* 177*/        return status;
            }

            public URI getResolvedRequestUri()
            {
/* 199*/        return resolvedUri;
            }

            public void setResolvedRequestUri(URI uri)
            {
/* 219*/        if(uri == null)
/* 220*/            throw new NullPointerException(LocalizationMessages.CLIENT_RESPONSE_RESOLVED_URI_NULL());
/* 222*/        if(!uri.isAbsolute())
                {
/* 223*/            throw new IllegalArgumentException(LocalizationMessages.CLIENT_RESPONSE_RESOLVED_URI_NOT_ABSOLUTE());
                } else
                {
/* 225*/            resolvedUri = uri;
/* 226*/            return;
                }
            }

            public ClientRequest getRequestContext()
            {
/* 234*/        return requestContext;
            }

            public Map getCookies()
            {
/* 239*/        return super.getResponseCookies();
            }

            public Set getLinks()
            {
/* 244*/        return Sets.newHashSet(Collections2.transform(super.getLinks(), new Function() {

                    public Link apply(Link link)
                    {
/* 247*/                if(link.getUri().isAbsolute())
/* 248*/                    return link;
/* 251*/                else
/* 251*/                    return Link.fromLink(link).baseUri(getResolvedRequestUri()).build(new Object[0]);
                    }

                    public volatile Object apply(Object obj)
                    {
/* 244*/                return apply((Link)obj);
                    }

                    final ClientResponse this$0;

                    
                    {
/* 244*/                this$0 = ClientResponse.this;
/* 244*/                super();
                    }
        }));
            }

            public String toString()
            {
/* 258*/        return MoreObjects.toStringHelper(this).add("method", requestContext.getMethod()).add("uri", requestContext.getUri()).add("status", status.getStatusCode()).add("reason", status.getReasonPhrase()).toString();
            }

            public Object getEntity()
                throws IllegalStateException
            {
/* 285*/        return getEntityStream();
            }

            public Object readEntity(Class class1)
                throws ProcessingException, IllegalStateException
            {
/* 326*/        return readEntity(class1, requestContext.getPropertiesDelegate());
            }

            public Object readEntity(GenericType generictype)
                throws ProcessingException, IllegalStateException
            {
/* 368*/        return readEntity(generictype.getRawType(), generictype.getType(), requestContext.getPropertiesDelegate());
            }

            public Object readEntity(Class class1, Annotation aannotation[])
                throws ProcessingException, IllegalStateException
            {
/* 410*/        return readEntity(class1, aannotation, requestContext.getPropertiesDelegate());
            }

            public Object readEntity(GenericType generictype, Annotation aannotation[])
                throws ProcessingException, IllegalStateException
            {
/* 454*/        return readEntity(generictype.getRawType(), generictype.getType(), aannotation, requestContext.getPropertiesDelegate());
            }

            public ServiceLocator getServiceLocator()
            {
/* 459*/        return getRequestContext().getServiceLocator();
            }

            protected Iterable getReaderInterceptors()
            {
/* 464*/        return requestContext.getReaderInterceptors();
            }

            private javax.ws.rs.core.Response.StatusType status;
            private final ClientRequest requestContext;
            private URI resolvedUri;
}
