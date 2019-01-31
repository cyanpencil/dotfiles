// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InboundJaxrsResponse.java

package org.glassfish.jersey.client;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.MoreObjects;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientResponse

class InboundJaxrsResponse extends Response
{

            public InboundJaxrsResponse(ClientResponse clientresponse, RequestScope requestscope)
            {
/*  86*/        context = clientresponse;
/*  87*/        scope = requestscope;
/*  88*/        if(scope != null)
                {
/*  89*/            scopeInstance = requestscope.referenceCurrent();
/*  89*/            return;
                } else
                {
/*  91*/            scopeInstance = null;
/*  93*/            return;
                }
            }

            public int getStatus()
            {
/*  97*/        return context.getStatus();
            }

            public javax.ws.rs.core.Response.StatusType getStatusInfo()
            {
/* 102*/        return context.getStatusInfo();
            }

            public Object getEntity()
                throws IllegalStateException
            {
/* 107*/        return context.getEntity();
            }

            public Object readEntity(final Class entityType)
                throws ProcessingException, IllegalStateException
            {
/* 112*/        return runInScopeIfPossible(new Producer() {

                    public Object call()
                    {
/* 115*/                return context.readEntity(entityType);
                    }

                    final Class val$entityType;
                    final InboundJaxrsResponse this$0;

                    
                    {
/* 112*/                this$0 = InboundJaxrsResponse.this;
/* 112*/                entityType = class1;
/* 112*/                super();
                    }
        });
            }

            public Object readEntity(final GenericType entityType)
                throws ProcessingException, IllegalStateException
            {
/* 123*/        return runInScopeIfPossible(new Producer() {

                    public Object call()
                    {
/* 126*/                return context.readEntity(entityType);
                    }

                    final GenericType val$entityType;
                    final InboundJaxrsResponse this$0;

                    
                    {
/* 123*/                this$0 = InboundJaxrsResponse.this;
/* 123*/                entityType = generictype;
/* 123*/                super();
                    }
        });
            }

            public Object readEntity(final Class entityType, final Annotation annotations[])
                throws ProcessingException, IllegalStateException
            {
/* 134*/        return runInScopeIfPossible(new Producer() {

                    public Object call()
                    {
/* 137*/                return context.readEntity(entityType, annotations);
                    }

                    final Class val$entityType;
                    final Annotation val$annotations[];
                    final InboundJaxrsResponse this$0;

                    
                    {
/* 134*/                this$0 = InboundJaxrsResponse.this;
/* 134*/                entityType = class1;
/* 134*/                annotations = aannotation;
/* 134*/                super();
                    }
        });
            }

            public Object readEntity(final GenericType entityType, final Annotation annotations[])
                throws ProcessingException, IllegalStateException
            {
/* 146*/        return runInScopeIfPossible(new Producer() {

                    public Object call()
                    {
/* 149*/                return context.readEntity(entityType, annotations);
                    }

                    final GenericType val$entityType;
                    final Annotation val$annotations[];
                    final InboundJaxrsResponse this$0;

                    
                    {
/* 146*/                this$0 = InboundJaxrsResponse.this;
/* 146*/                entityType = generictype;
/* 146*/                annotations = aannotation;
/* 146*/                super();
                    }
        });
            }

            public boolean hasEntity()
            {
/* 156*/        return context.hasEntity();
            }

            public boolean bufferEntity()
                throws ProcessingException
            {
/* 161*/        return context.bufferEntity();
            }

            public void close()
                throws ProcessingException
            {
/* 167*/        context.close();
                Exception exception;
/* 169*/        if(scopeInstance != null)
                {
/* 170*/            scopeInstance.release();
/* 170*/            return;
                } else
                {
/* 173*/            return;
                }
/* 169*/        exception;
/* 169*/        if(scopeInstance != null)
/* 170*/            scopeInstance.release();
/* 170*/        throw exception;
            }

            public String getHeaderString(String s)
            {
/* 177*/        return context.getHeaderString(s);
            }

            public MultivaluedMap getStringHeaders()
            {
/* 182*/        return context.getHeaders();
            }

            public MediaType getMediaType()
            {
/* 187*/        return context.getMediaType();
            }

            public Locale getLanguage()
            {
/* 192*/        return context.getLanguage();
            }

            public int getLength()
            {
/* 197*/        return context.getLength();
            }

            public Map getCookies()
            {
/* 202*/        return context.getResponseCookies();
            }

            public EntityTag getEntityTag()
            {
/* 207*/        return context.getEntityTag();
            }

            public Date getDate()
            {
/* 212*/        return context.getDate();
            }

            public Date getLastModified()
            {
/* 217*/        return context.getLastModified();
            }

            public Set getAllowedMethods()
            {
/* 222*/        return context.getAllowedMethods();
            }

            public URI getLocation()
            {
/* 227*/        return context.getLocation();
            }

            public Set getLinks()
            {
/* 232*/        return context.getLinks();
            }

            public boolean hasLink(String s)
            {
/* 237*/        return context.hasLink(s);
            }

            public Link getLink(String s)
            {
/* 242*/        return context.getLink(s);
            }

            public javax.ws.rs.core.Link.Builder getLinkBuilder(String s)
            {
/* 247*/        return context.getLinkBuilder(s);
            }

            public MultivaluedMap getMetadata()
            {
                MultivaluedMap multivaluedmap;
/* 253*/        return multivaluedmap = context.getHeaders();
            }

            public String toString()
            {
/* 259*/        return MoreObjects.toStringHelper(this).add("context", context).toString();
            }

            private Object runInScopeIfPossible(Producer producer)
            {
/* 266*/        if(scope != null && scopeInstance != null)
/* 267*/            return scope.runInScope(scopeInstance, producer);
/* 269*/        else
/* 269*/            return producer.call();
            }

            private final ClientResponse context;
            private final RequestScope scope;
            private final org.glassfish.jersey.process.internal.RequestScope.Instance scopeInstance;

}
