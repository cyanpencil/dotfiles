// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.RedirectionException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import jersey.repackaged.com.google.common.util.concurrent.SettableFuture;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRequest, ClientResponse, ClientRuntime, InboundJaxrsResponse, 
//            ClientConfig, ResponseCallback

public class JerseyInvocation
    implements Invocation
{
    static class AsyncInvoker
        implements javax.ws.rs.client.AsyncInvoker
    {

                public Future get()
                {
/* 480*/            return method("GET");
                }

                public Future get(Class class1)
                {
/* 485*/            return method("GET", class1);
                }

                public Future get(GenericType generictype)
                {
/* 490*/            return method("GET", generictype);
                }

                public Future get(InvocationCallback invocationcallback)
                {
/* 495*/            return method("GET", invocationcallback);
                }

                public Future put(Entity entity)
                {
/* 500*/            return method("PUT", entity);
                }

                public Future put(Entity entity, Class class1)
                {
/* 505*/            return method("PUT", entity, class1);
                }

                public Future put(Entity entity, GenericType generictype)
                {
/* 510*/            return method("PUT", entity, generictype);
                }

                public Future put(Entity entity, InvocationCallback invocationcallback)
                {
/* 515*/            return method("PUT", entity, invocationcallback);
                }

                public Future post(Entity entity)
                {
/* 520*/            return method("POST", entity);
                }

                public Future post(Entity entity, Class class1)
                {
/* 525*/            return method("POST", entity, class1);
                }

                public Future post(Entity entity, GenericType generictype)
                {
/* 530*/            return method("POST", entity, generictype);
                }

                public Future post(Entity entity, InvocationCallback invocationcallback)
                {
/* 535*/            return method("POST", entity, invocationcallback);
                }

                public Future delete()
                {
/* 540*/            return method("DELETE");
                }

                public Future delete(Class class1)
                {
/* 545*/            return method("DELETE", class1);
                }

                public Future delete(GenericType generictype)
                {
/* 550*/            return method("DELETE", generictype);
                }

                public Future delete(InvocationCallback invocationcallback)
                {
/* 555*/            return method("DELETE", invocationcallback);
                }

                public Future head()
                {
/* 560*/            return method("HEAD");
                }

                public Future head(InvocationCallback invocationcallback)
                {
/* 565*/            return method("HEAD", invocationcallback);
                }

                public Future options()
                {
/* 570*/            return method("OPTIONS");
                }

                public Future options(Class class1)
                {
/* 575*/            return method("OPTIONS", class1);
                }

                public Future options(GenericType generictype)
                {
/* 580*/            return method("OPTIONS", generictype);
                }

                public Future options(InvocationCallback invocationcallback)
                {
/* 585*/            return method("OPTIONS", invocationcallback);
                }

                public Future trace()
                {
/* 590*/            return method("TRACE");
                }

                public Future trace(Class class1)
                {
/* 595*/            return method("TRACE", class1);
                }

                public Future trace(GenericType generictype)
                {
/* 600*/            return method("TRACE", generictype);
                }

                public Future trace(InvocationCallback invocationcallback)
                {
/* 605*/            return method("TRACE", invocationcallback);
                }

                public Future method(String s)
                {
/* 610*/            builder.requestContext.setMethod(s);
/* 611*/            return (new JerseyInvocation(builder)).submit();
                }

                public Future method(String s, Class class1)
                {
/* 616*/            if(class1 == null)
                    {
/* 617*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 619*/                builder.requestContext.setMethod(s);
/* 620*/                return (new JerseyInvocation(builder)).submit(class1);
                    }
                }

                public Future method(String s, GenericType generictype)
                {
/* 625*/            if(generictype == null)
                    {
/* 626*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 628*/                builder.requestContext.setMethod(s);
/* 629*/                return (new JerseyInvocation(builder)).submit(generictype);
                    }
                }

                public Future method(String s, InvocationCallback invocationcallback)
                {
/* 634*/            builder.requestContext.setMethod(s);
/* 635*/            return (new JerseyInvocation(builder)).submit(invocationcallback);
                }

                public Future method(String s, Entity entity)
                {
/* 640*/            builder.requestContext.setMethod(s);
/* 641*/            builder.storeEntity(entity);
/* 642*/            return (new JerseyInvocation(builder)).submit();
                }

                public Future method(String s, Entity entity, Class class1)
                {
/* 647*/            if(class1 == null)
                    {
/* 648*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 650*/                builder.requestContext.setMethod(s);
/* 651*/                builder.storeEntity(entity);
/* 652*/                return (new JerseyInvocation(builder)).submit(class1);
                    }
                }

                public Future method(String s, Entity entity, GenericType generictype)
                {
/* 657*/            if(generictype == null)
                    {
/* 658*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 660*/                builder.requestContext.setMethod(s);
/* 661*/                builder.storeEntity(entity);
/* 662*/                return (new JerseyInvocation(builder)).submit(generictype);
                    }
                }

                public Future method(String s, Entity entity, InvocationCallback invocationcallback)
                {
/* 667*/            builder.requestContext.setMethod(s);
/* 668*/            builder.storeEntity(entity);
/* 669*/            return (new JerseyInvocation(builder)).submit(invocationcallback);
                }

                private final Builder builder;

                private AsyncInvoker(Builder builder1)
                {
/* 474*/            builder = builder1;
/* 475*/            builder.requestContext.setAsynchronous(true);
                }

    }

    public static class Builder
        implements javax.ws.rs.client.Invocation.Builder
    {

                ClientRequest request()
                {
/* 183*/            return requestContext;
                }

                private void storeEntity(Entity entity)
                {
/* 187*/            if(entity != null)
                    {
/* 188*/                requestContext.variant(entity.getVariant());
/* 189*/                requestContext.setEntity(entity.getEntity());
/* 190*/                requestContext.setEntityAnnotations(entity.getAnnotations());
                    }
                }

                public JerseyInvocation build(String s)
                {
/* 196*/            requestContext.setMethod(s);
/* 197*/            return new JerseyInvocation(this, true);
                }

                public JerseyInvocation build(String s, Entity entity)
                {
/* 202*/            requestContext.setMethod(s);
/* 203*/            storeEntity(entity);
/* 204*/            return new JerseyInvocation(this, true);
                }

                public JerseyInvocation buildGet()
                {
/* 209*/            requestContext.setMethod("GET");
/* 210*/            return new JerseyInvocation(this, true);
                }

                public JerseyInvocation buildDelete()
                {
/* 215*/            requestContext.setMethod("DELETE");
/* 216*/            return new JerseyInvocation(this, true);
                }

                public JerseyInvocation buildPost(Entity entity)
                {
/* 221*/            requestContext.setMethod("POST");
/* 222*/            storeEntity(entity);
/* 223*/            return new JerseyInvocation(this, true);
                }

                public JerseyInvocation buildPut(Entity entity)
                {
/* 228*/            requestContext.setMethod("PUT");
/* 229*/            storeEntity(entity);
/* 230*/            return new JerseyInvocation(this, true);
                }

                public javax.ws.rs.client.AsyncInvoker async()
                {
/* 235*/            return new AsyncInvoker(this);
                }

                public transient Builder accept(String as[])
                {
/* 240*/            requestContext.accept(as);
/* 241*/            return this;
                }

                public transient Builder accept(MediaType amediatype[])
                {
/* 246*/            requestContext.accept(amediatype);
/* 247*/            return this;
                }

                public transient javax.ws.rs.client.Invocation.Builder acceptEncoding(String as[])
                {
/* 252*/            requestContext.getHeaders().addAll("Accept-Encoding", (Object[])as);
/* 253*/            return this;
                }

                public transient Builder acceptLanguage(Locale alocale[])
                {
/* 258*/            requestContext.acceptLanguage(alocale);
/* 259*/            return this;
                }

                public transient Builder acceptLanguage(String as[])
                {
/* 264*/            requestContext.acceptLanguage(as);
/* 265*/            return this;
                }

                public Builder cookie(Cookie cookie1)
                {
/* 270*/            requestContext.cookie(cookie1);
/* 271*/            return this;
                }

                public Builder cookie(String s, String s1)
                {
/* 276*/            requestContext.cookie(new Cookie(s, s1));
/* 277*/            return this;
                }

                public Builder cacheControl(CacheControl cachecontrol)
                {
/* 282*/            requestContext.cacheControl(cachecontrol);
/* 283*/            return this;
                }

                public Builder header(String s, Object obj)
                {
/* 288*/            MultivaluedMap multivaluedmap = requestContext.getHeaders();
/* 290*/            if(obj == null)
/* 291*/                multivaluedmap.remove(s);
/* 293*/            else
/* 293*/                multivaluedmap.add(s, obj);
/* 296*/            if("User-Agent".equalsIgnoreCase(s))
/* 297*/                requestContext.ignoreUserAgent(obj == null);
/* 300*/            return this;
                }

                public Builder headers(MultivaluedMap multivaluedmap)
                {
/* 305*/            requestContext.replaceHeaders(multivaluedmap);
/* 306*/            return this;
                }

                public Response get()
                    throws ProcessingException
                {
/* 311*/            return method("GET");
                }

                public Object get(Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 316*/            return method("GET", class1);
                }

                public Object get(GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 321*/            return method("GET", generictype);
                }

                public Response put(Entity entity)
                    throws ProcessingException
                {
/* 326*/            return method("PUT", entity);
                }

                public Object put(Entity entity, Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 332*/            return method("PUT", entity, class1);
                }

                public Object put(Entity entity, GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 338*/            return method("PUT", entity, generictype);
                }

                public Response post(Entity entity)
                    throws ProcessingException
                {
/* 343*/            return method("POST", entity);
                }

                public Object post(Entity entity, Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 349*/            return method("POST", entity, class1);
                }

                public Object post(Entity entity, GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 355*/            return method("POST", entity, generictype);
                }

                public Response delete()
                    throws ProcessingException
                {
/* 360*/            return method("DELETE");
                }

                public Object delete(Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 365*/            return method("DELETE", class1);
                }

                public Object delete(GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 370*/            return method("DELETE", generictype);
                }

                public Response head()
                    throws ProcessingException
                {
/* 375*/            return method("HEAD");
                }

                public Response options()
                    throws ProcessingException
                {
/* 380*/            return method("OPTIONS");
                }

                public Object options(Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 385*/            return method("OPTIONS", class1);
                }

                public Object options(GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 390*/            return method("OPTIONS", generictype);
                }

                public Response trace()
                    throws ProcessingException
                {
/* 395*/            return method("TRACE");
                }

                public Object trace(Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 400*/            return method("TRACE", class1);
                }

                public Object trace(GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 405*/            return method("TRACE", generictype);
                }

                public Response method(String s)
                    throws ProcessingException
                {
/* 410*/            requestContext.setMethod(s);
/* 411*/            return (new JerseyInvocation(this)).invoke();
                }

                public Object method(String s, Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 416*/            if(class1 == null)
                    {
/* 417*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 419*/                requestContext.setMethod(s);
/* 420*/                return (new JerseyInvocation(this)).invoke(class1);
                    }
                }

                public Object method(String s, GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 426*/            if(generictype == null)
                    {
/* 427*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 429*/                requestContext.setMethod(s);
/* 430*/                return (new JerseyInvocation(this)).invoke(generictype);
                    }
                }

                public Response method(String s, Entity entity)
                    throws ProcessingException
                {
/* 435*/            requestContext.setMethod(s);
/* 436*/            storeEntity(entity);
/* 437*/            return (new JerseyInvocation(this)).invoke();
                }

                public Object method(String s, Entity entity, Class class1)
                    throws ProcessingException, WebApplicationException
                {
/* 443*/            if(class1 == null)
                    {
/* 444*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 446*/                requestContext.setMethod(s);
/* 447*/                storeEntity(entity);
/* 448*/                return (new JerseyInvocation(this)).invoke(class1);
                    }
                }

                public Object method(String s, Entity entity, GenericType generictype)
                    throws ProcessingException, WebApplicationException
                {
/* 454*/            if(generictype == null)
                    {
/* 455*/                throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                    } else
                    {
/* 457*/                requestContext.setMethod(s);
/* 458*/                storeEntity(entity);
/* 459*/                return (new JerseyInvocation(this)).invoke(generictype);
                    }
                }

                public Builder property(String s, Object obj)
                {
/* 464*/            requestContext.setProperty(s, obj);
/* 465*/            return this;
                }

                public volatile javax.ws.rs.client.Invocation.Builder property(String s, Object obj)
                {
/* 163*/            return property(s, obj);
                }

                public volatile javax.ws.rs.client.Invocation.Builder headers(MultivaluedMap multivaluedmap)
                {
/* 163*/            return headers(multivaluedmap);
                }

                public volatile javax.ws.rs.client.Invocation.Builder header(String s, Object obj)
                {
/* 163*/            return header(s, obj);
                }

                public volatile javax.ws.rs.client.Invocation.Builder cacheControl(CacheControl cachecontrol)
                {
/* 163*/            return cacheControl(cachecontrol);
                }

                public volatile javax.ws.rs.client.Invocation.Builder cookie(String s, String s1)
                {
/* 163*/            return cookie(s, s1);
                }

                public volatile javax.ws.rs.client.Invocation.Builder cookie(Cookie cookie1)
                {
/* 163*/            return cookie(cookie1);
                }

                public volatile javax.ws.rs.client.Invocation.Builder acceptLanguage(String as[])
                {
/* 163*/            return acceptLanguage(as);
                }

                public volatile javax.ws.rs.client.Invocation.Builder acceptLanguage(Locale alocale[])
                {
/* 163*/            return acceptLanguage(alocale);
                }

                public volatile javax.ws.rs.client.Invocation.Builder accept(MediaType amediatype[])
                {
/* 163*/            return accept(amediatype);
                }

                public volatile javax.ws.rs.client.Invocation.Builder accept(String as[])
                {
/* 163*/            return accept(as);
                }

                public volatile Invocation buildPut(Entity entity)
                {
/* 163*/            return buildPut(entity);
                }

                public volatile Invocation buildPost(Entity entity)
                {
/* 163*/            return buildPost(entity);
                }

                public volatile Invocation buildDelete()
                {
/* 163*/            return buildDelete();
                }

                public volatile Invocation buildGet()
                {
/* 163*/            return buildGet();
                }

                public volatile Invocation build(String s, Entity entity)
                {
/* 163*/            return build(s, entity);
                }

                public volatile Invocation build(String s)
                {
/* 163*/            return build(s);
                }

                private final ClientRequest requestContext;



                protected Builder(URI uri, ClientConfig clientconfig)
                {
/* 174*/            requestContext = new ClientRequest(uri, clientconfig, new MapPropertiesDelegate());
                }
    }

    static final class EntityPresence extends Enum
    {

                public static EntityPresence[] values()
                {
/* 111*/            return (EntityPresence[])$VALUES.clone();
                }

                public static EntityPresence valueOf(String s)
                {
/* 111*/            return (EntityPresence)Enum.valueOf(org/glassfish/jersey/client/JerseyInvocation$EntityPresence, s);
                }

                public static final EntityPresence MUST_BE_NULL;
                public static final EntityPresence MUST_BE_PRESENT;
                public static final EntityPresence OPTIONAL;
                private static final EntityPresence $VALUES[];

                static 
                {
/* 112*/            MUST_BE_NULL = new EntityPresence("MUST_BE_NULL", 0);
/* 113*/            MUST_BE_PRESENT = new EntityPresence("MUST_BE_PRESENT", 1);
/* 114*/            OPTIONAL = new EntityPresence("OPTIONAL", 2);
/* 111*/            $VALUES = (new EntityPresence[] {
/* 111*/                MUST_BE_NULL, MUST_BE_PRESENT, OPTIONAL
                    });
                }

                private EntityPresence(String s, int i)
                {
/* 111*/            super(s, i);
                }
    }


            private JerseyInvocation(Builder builder)
            {
/* 101*/        this(builder, false);
            }

            private JerseyInvocation(Builder builder, boolean flag)
            {
/* 105*/        validateHttpMethodAndEntity(builder.requestContext);
/* 107*/        requestContext = new ClientRequest(builder.requestContext);
/* 108*/        copyRequestContext = flag;
            }

            private static Map initializeMap()
            {
                HashMap hashmap;
/* 120*/        (hashmap = new HashMap()).put("DELETE", EntityPresence.MUST_BE_NULL);
/* 123*/        hashmap.put("GET", EntityPresence.MUST_BE_NULL);
/* 124*/        hashmap.put("HEAD", EntityPresence.MUST_BE_NULL);
/* 125*/        hashmap.put("OPTIONS", EntityPresence.MUST_BE_NULL);
/* 126*/        hashmap.put("POST", EntityPresence.OPTIONAL);
/* 127*/        hashmap.put("PUT", EntityPresence.MUST_BE_PRESENT);
/* 128*/        hashmap.put("TRACE", EntityPresence.MUST_BE_NULL);
/* 129*/        return hashmap;
            }

            private void validateHttpMethodAndEntity(ClientRequest clientrequest)
            {
/* 134*/        boolean flag = PropertiesHelper.isProperty(clientrequest.getConfiguration().getProperty("jersey.config.client.suppressHttpComplianceValidation"));
                Object obj;
/* 137*/        if((obj = clientrequest.getProperty("jersey.config.client.suppressHttpComplianceValidation")) != null)
/* 139*/            flag = PropertiesHelper.isProperty(obj);
/* 142*/        obj = clientrequest.getMethod();
                EntityPresence entitypresence;
/* 144*/        if((entitypresence = (EntityPresence)METHODS.get(((String) (obj)).toUpperCase())) == EntityPresence.MUST_BE_NULL && clientrequest.hasEntity())
/* 146*/            if(flag)
                    {
/* 147*/                LOGGER.warning(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NOT_NULL(obj));
/* 147*/                return;
                    } else
                    {
/* 149*/                throw new IllegalStateException(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NOT_NULL(obj));
                    }
/* 151*/        if(entitypresence == EntityPresence.MUST_BE_PRESENT && !clientrequest.hasEntity())
                {
/* 152*/            if(flag)
                    {
/* 153*/                LOGGER.warning(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NULL(obj));
/* 153*/                return;
                    } else
                    {
/* 155*/                throw new IllegalStateException(LocalizationMessages.ERROR_HTTP_METHOD_ENTITY_NULL(obj));
                    }
                } else
                {
/* 158*/            return;
                }
            }

            private ClientRequest requestForCall(ClientRequest clientrequest)
            {
/* 674*/        if(copyRequestContext)
/* 674*/            return new ClientRequest(clientrequest);
/* 674*/        else
/* 674*/            return clientrequest;
            }

            public Response invoke()
                throws ProcessingException, WebApplicationException
            {
                final ClientRuntime runtime;
                final RequestScope requestScope;
/* 679*/        return (Response)(requestScope = (runtime = request().getClientRuntime()).getRequestScope()).runInScope(new Producer() {

                    public Response call()
                        throws ProcessingException
                    {
/* 684*/                return new InboundJaxrsResponse(runtime.invoke(requestForCall(requestContext)), requestScope);
                    }

                    public volatile Object call()
                    {
/* 681*/                return call();
                    }

                    final ClientRuntime val$runtime;
                    final RequestScope val$requestScope;
                    final JerseyInvocation this$0;

                    
                    {
/* 681*/                this$0 = JerseyInvocation.this;
/* 681*/                runtime = clientruntime;
/* 681*/                requestScope = requestscope;
/* 681*/                super();
                    }
        });
            }

            public Object invoke(final Class responseType)
                throws ProcessingException, WebApplicationException
            {
                final ClientRuntime runtime;
                final RequestScope requestScope;
/* 691*/        if(responseType == null)
/* 692*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
/* 694*/        else
/* 694*/            return (requestScope = (runtime = request().getClientRuntime()).getRequestScope()).runInScope(new Producer() {

                        public Object call()
                            throws ProcessingException
                        {
/* 700*/                    return translate(runtime.invoke(requestForCall(requestContext)), requestScope, responseType);
/* 701*/                    JVM INSTR dup ;
                            ProcessingException processingexception;
/* 702*/                    processingexception;
/* 702*/                    getCause();
/* 702*/                    JVM INSTR instanceof #3   <Class WebApplicationException>;
/* 702*/                    JVM INSTR ifeq 56;
                               goto _L1 _L2
_L1:
/* 703*/                    break MISSING_BLOCK_LABEL_48;
_L2:
/* 703*/                    break MISSING_BLOCK_LABEL_56;
/* 703*/                    throw (WebApplicationException)processingexception.getCause();
/* 705*/                    throw processingexception;
                        }

                        final ClientRuntime val$runtime;
                        final RequestScope val$requestScope;
                        final Class val$responseType;
                        final JerseyInvocation this$0;

                    
                    {
/* 696*/                this$0 = JerseyInvocation.this;
/* 696*/                runtime = clientruntime;
/* 696*/                requestScope = requestscope;
/* 696*/                responseType = class1;
/* 696*/                super();
                    }
            });
            }

            public Object invoke(final GenericType responseType)
                throws ProcessingException, WebApplicationException
            {
                final ClientRuntime runtime;
                final RequestScope requestScope;
/* 713*/        if(responseType == null)
/* 714*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
/* 716*/        else
/* 716*/            return (requestScope = (runtime = request().getClientRuntime()).getRequestScope()).runInScope(new Producer() {

                        public Object call()
                            throws ProcessingException
                        {
/* 722*/                    return translate(runtime.invoke(requestForCall(requestContext)), requestScope, responseType);
/* 723*/                    JVM INSTR dup ;
                            ProcessingException processingexception;
/* 724*/                    processingexception;
/* 724*/                    getCause();
/* 724*/                    JVM INSTR instanceof #3   <Class WebApplicationException>;
/* 724*/                    JVM INSTR ifeq 56;
                               goto _L1 _L2
_L1:
/* 725*/                    break MISSING_BLOCK_LABEL_48;
_L2:
/* 725*/                    break MISSING_BLOCK_LABEL_56;
/* 725*/                    throw (WebApplicationException)processingexception.getCause();
/* 727*/                    throw processingexception;
                        }

                        final ClientRuntime val$runtime;
                        final RequestScope val$requestScope;
                        final GenericType val$responseType;
                        final JerseyInvocation this$0;

                    
                    {
/* 718*/                this$0 = JerseyInvocation.this;
/* 718*/                runtime = clientruntime;
/* 718*/                requestScope = requestscope;
/* 718*/                responseType = generictype;
/* 718*/                super();
                    }
            });
            }

            public Future submit()
            {
/* 735*/        final SettableFuture responseFuture = SettableFuture.create();
/* 736*/        request().getClientRuntime().submit(requestForCall(requestContext), new ResponseCallback() {

                    public void completed(ClientResponse clientresponse, RequestScope requestscope)
                    {
/* 740*/                if(!responseFuture.isCancelled())
                        {
/* 741*/                    responseFuture.set(new InboundJaxrsResponse(clientresponse, requestscope));
/* 741*/                    return;
                        } else
                        {
/* 743*/                    clientresponse.close();
/* 745*/                    return;
                        }
                    }

                    public void failed(ProcessingException processingexception)
                    {
/* 749*/                if(!responseFuture.isCancelled())
/* 750*/                    responseFuture.setException(processingexception);
                    }

                    final SettableFuture val$responseFuture;
                    final JerseyInvocation this$0;

                    
                    {
/* 736*/                this$0 = JerseyInvocation.this;
/* 736*/                responseFuture = settablefuture;
/* 736*/                super();
                    }
        });
/* 755*/        return responseFuture;
            }

            public Future submit(final Class responseType)
            {
/* 760*/        if(responseType == null)
                {
/* 761*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 763*/            final SettableFuture responseFuture = SettableFuture.create();
/* 764*/            request().getClientRuntime().submit(requestForCall(requestContext), new ResponseCallback() {

                        public void completed(ClientResponse clientresponse, RequestScope requestscope)
                        {
/* 768*/                    if(responseFuture.isCancelled())
                            {
/* 769*/                        clientresponse.close();
/* 770*/                        return;
                            }
/* 773*/                    try
                            {
/* 773*/                        responseFuture.set(translate(clientresponse, requestscope, responseType));
/* 776*/                        return;
                            }
                            // Misplaced declaration of an exception variable
/* 774*/                    catch(ClientResponse clientresponse)
                            {
/* 775*/                        failed(clientresponse);
                            }
                        }

                        public void failed(ProcessingException processingexception)
                        {
/* 781*/                    if(responseFuture.isCancelled())
/* 782*/                        return;
/* 784*/                    if(processingexception.getCause() instanceof WebApplicationException)
                            {
/* 785*/                        responseFuture.setException(processingexception.getCause());
/* 785*/                        return;
                            } else
                            {
/* 787*/                        responseFuture.setException(processingexception);
/* 789*/                        return;
                            }
                        }

                        final SettableFuture val$responseFuture;
                        final Class val$responseType;
                        final JerseyInvocation this$0;

                    
                    {
/* 764*/                this$0 = JerseyInvocation.this;
/* 764*/                responseFuture = settablefuture;
/* 764*/                responseType = class1;
/* 764*/                super();
                    }
            });
/* 792*/            return responseFuture;
                }
            }

            private Object translate(ClientResponse clientresponse, RequestScope requestscope, Class class1)
                throws ProcessingException
            {
/* 797*/        if(class1 == javax/ws/rs/core/Response)
/* 798*/            return class1.cast(new InboundJaxrsResponse(clientresponse, requestscope));
/* 801*/        if(clientresponse.getStatusInfo().getFamily() != javax.ws.rs.core.Response.Status.Family.SUCCESSFUL)
/* 803*/            break MISSING_BLOCK_LABEL_131;
/* 803*/        return clientresponse.readEntity(class1);
/* 804*/        JVM INSTR dup ;
/* 805*/        class1;
/* 805*/        getClass();
/* 805*/        javax/ws/rs/ProcessingException;
/* 805*/        JVM INSTR if_acmpne 72;
                   goto _L1 _L2
_L1:
/* 806*/        break MISSING_BLOCK_LABEL_51;
_L2:
/* 806*/        break MISSING_BLOCK_LABEL_72;
/* 806*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), class1.getCause());
/* 808*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), class1);
/* 809*/        class1;
/* 810*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), class1);
/* 811*/        class1;
/* 812*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), LocalizationMessages.UNEXPECTED_ERROR_RESPONSE_PROCESSING(), class1);
/* 816*/        throw convertToException(new InboundJaxrsResponse(clientresponse, requestscope));
            }

            public Future submit(final GenericType responseType)
            {
/* 822*/        if(responseType == null)
                {
/* 823*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 825*/            final SettableFuture responseFuture = SettableFuture.create();
/* 826*/            request().getClientRuntime().submit(requestForCall(requestContext), new ResponseCallback() {

                        public void completed(ClientResponse clientresponse, RequestScope requestscope)
                        {
/* 830*/                    if(responseFuture.isCancelled())
                            {
/* 831*/                        clientresponse.close();
/* 832*/                        return;
                            }
/* 836*/                    try
                            {
/* 836*/                        responseFuture.set(translate(clientresponse, requestscope, responseType));
/* 839*/                        return;
                            }
                            // Misplaced declaration of an exception variable
/* 837*/                    catch(ClientResponse clientresponse)
                            {
/* 838*/                        failed(clientresponse);
                            }
                        }

                        public void failed(ProcessingException processingexception)
                        {
/* 844*/                    if(responseFuture.isCancelled())
/* 845*/                        return;
/* 847*/                    if(processingexception.getCause() instanceof WebApplicationException)
                            {
/* 848*/                        responseFuture.setException(processingexception.getCause());
/* 848*/                        return;
                            } else
                            {
/* 850*/                        responseFuture.setException(processingexception);
/* 852*/                        return;
                            }
                        }

                        final SettableFuture val$responseFuture;
                        final GenericType val$responseType;
                        final JerseyInvocation this$0;

                    
                    {
/* 826*/                this$0 = JerseyInvocation.this;
/* 826*/                responseFuture = settablefuture;
/* 826*/                responseType = generictype;
/* 826*/                super();
                    }
            });
/* 855*/            return responseFuture;
                }
            }

            private Object translate(ClientResponse clientresponse, RequestScope requestscope, GenericType generictype)
                throws ProcessingException
            {
/* 860*/        if(generictype.getRawType() == javax/ws/rs/core/Response)
/* 862*/            return new InboundJaxrsResponse(clientresponse, requestscope);
/* 865*/        if(clientresponse.getStatusInfo().getFamily() != javax.ws.rs.core.Response.Status.Family.SUCCESSFUL)
/* 867*/            break MISSING_BLOCK_LABEL_103;
/* 867*/        return clientresponse.readEntity(generictype);
/* 868*/        generictype;
/* 869*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), generictype.getCause());
/* 870*/        generictype;
/* 871*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), generictype);
/* 872*/        generictype;
/* 873*/        throw new ResponseProcessingException(new InboundJaxrsResponse(clientresponse, requestscope), LocalizationMessages.UNEXPECTED_ERROR_RESPONSE_PROCESSING(), generictype);
/* 877*/        throw convertToException(new InboundJaxrsResponse(clientresponse, requestscope));
            }

            public Future submit(InvocationCallback invocationcallback)
            {
/* 883*/        return submit(null, invocationcallback);
            }

            public Future submit(final GenericType callbackParamClass, final InvocationCallback callback)
            {
/* 902*/        final SettableFuture responseFuture = SettableFuture.create();
/* 905*/        org.glassfish.jersey.internal.util.ReflectionHelper.DeclaringClassInterfacePair declaringclassinterfacepair = ReflectionHelper.getClass(callback.getClass(), javax/ws/rs/client/InvocationCallback);
                final Type callbackParamType;
/* 911*/        if(callbackParamClass == null)
                {
/* 913*/            callbackParamClass = ReflectionHelper.erasure(callbackParamType = ReflectionHelper.getParameterizedTypeArguments(declaringclassinterfacepair)[0]);
                } else
                {
/* 916*/            callbackParamType = callbackParamClass.getType();
/* 917*/            callbackParamClass = ReflectionHelper.erasure(callbackParamClass.getRawType());
                }
/* 920*/        callbackParamClass = new ResponseCallback() {

                    public void completed(ClientResponse clientresponse, RequestScope requestscope)
                    {
/* 924*/                if(responseFuture.isCancelled())
                        {
/* 925*/                    clientresponse.close();
/* 926*/                    failed(new ProcessingException(new CancellationException(LocalizationMessages.ERROR_REQUEST_CANCELLED())));
/* 928*/                    return;
                        }
/* 932*/                if(callbackParamClass == javax/ws/rs/core/Response)
                        {
/* 933*/                    clientresponse = ((ClientResponse) (callbackParamClass.cast(new InboundJaxrsResponse(clientresponse, requestscope))));
/* 934*/                    responseFuture.set(clientresponse);
/* 935*/                    callback.completed(clientresponse);
/* 935*/                    return;
                        }
/* 936*/                if(clientresponse.getStatusInfo().getFamily() == javax.ws.rs.core.Response.Status.Family.SUCCESSFUL)
                        {
/* 937*/                    clientresponse = ((ClientResponse) (clientresponse.readEntity(new GenericType(callbackParamType))));
/* 938*/                    responseFuture.set(clientresponse);
/* 939*/                    callback.completed(clientresponse);
/* 939*/                    return;
                        } else
                        {
/* 941*/                    failed(convertToException(new InboundJaxrsResponse(clientresponse, requestscope)));
/* 943*/                    return;
                        }
                    }

                    public void failed(ProcessingException processingexception1)
                    {
/* 948*/                if(processingexception1.getCause() instanceof WebApplicationException)
/* 949*/                    responseFuture.setException(processingexception1.getCause());
/* 950*/                else
/* 950*/                if(!responseFuture.isCancelled())
/* 951*/                    responseFuture.setException(processingexception1);
/* 954*/                callback.failed(((Throwable) ((processingexception1.getCause() instanceof CancellationException) ? processingexception1.getCause() : ((Throwable) (processingexception1)))));
/* 955*/                return;
                        Exception exception;
/* 954*/                exception;
/* 954*/                callback.failed(((Throwable) ((processingexception1.getCause() instanceof CancellationException) ? processingexception1.getCause() : ((Throwable) (processingexception1)))));
/* 954*/                throw exception;
                    }

                    final SettableFuture val$responseFuture;
                    final Class val$callbackParamClass;
                    final InvocationCallback val$callback;
                    final Type val$callbackParamType;
                    final JerseyInvocation this$0;

                    
                    {
/* 920*/                this$0 = JerseyInvocation.this;
/* 920*/                responseFuture = settablefuture;
/* 920*/                callbackParamClass = class1;
/* 920*/                callback = invocationcallback;
/* 920*/                callbackParamType = type;
/* 920*/                super();
                    }
        };
/* 958*/        request().getClientRuntime().submit(requestForCall(requestContext), callbackParamClass);
/* 972*/        break MISSING_BLOCK_LABEL_167;
/* 959*/        JVM INSTR dup ;
                Throwable throwable;
/* 961*/        throwable;
/* 961*/        JVM INSTR instanceof #29  <Class ProcessingException>;
/* 961*/        JVM INSTR ifeq 112;
                   goto _L1 _L2
_L1:
/* 962*/        break MISSING_BLOCK_LABEL_95;
_L2:
/* 962*/        break MISSING_BLOCK_LABEL_112;
                ProcessingException processingexception;
/* 962*/        processingexception = (ProcessingException)throwable;
/* 963*/        responseFuture.setException(processingexception);
/* 963*/        break MISSING_BLOCK_LABEL_159;
/* 964*/        if(throwable instanceof WebApplicationException)
                {
/* 965*/            processingexception = new ProcessingException(throwable);
/* 966*/            responseFuture.setException(throwable);
                } else
                {
/* 968*/            processingexception = new ProcessingException(throwable);
/* 969*/            responseFuture.setException(processingexception);
                }
/* 971*/        callback.failed(processingexception);
/* 974*/        return responseFuture;
            }

            public JerseyInvocation property(String s, Object obj)
            {
/* 979*/        requestContext.setProperty(s, obj);
/* 980*/        return this;
            }

            private ProcessingException convertToException(Response response)
            {
/* 987*/        response.bufferEntity();
                int i;
                Object obj;
/* 990*/        if((obj = javax.ws.rs.core.Response.Status.fromStatusCode(i = response.getStatus())) == null) goto _L2; else goto _L1
_L1:
        static class _cls8
        {

                    static final int $SwitchMap$javax$ws$rs$core$Response$Status[];
                    static final int $SwitchMap$javax$ws$rs$core$Response$Status$Family[];

                    static 
                    {
/*1039*/                $SwitchMap$javax$ws$rs$core$Response$Status$Family = new int[javax.ws.rs.core.Response.Status.Family.values().length];
/*1039*/                try
                        {
/*1039*/                    $SwitchMap$javax$ws$rs$core$Response$Status$Family[javax.ws.rs.core.Response.Status.Family.REDIRECTION.ordinal()] = 1;
                        }
/*1039*/                catch(NoSuchFieldError _ex) { }
/*1039*/                try
                        {
/*1039*/                    $SwitchMap$javax$ws$rs$core$Response$Status$Family[javax.ws.rs.core.Response.Status.Family.CLIENT_ERROR.ordinal()] = 2;
                        }
/*1039*/                catch(NoSuchFieldError _ex) { }
/*1039*/                try
                        {
/*1039*/                    $SwitchMap$javax$ws$rs$core$Response$Status$Family[javax.ws.rs.core.Response.Status.Family.SERVER_ERROR.ordinal()] = 3;
                        }
/*1039*/                catch(NoSuchFieldError _ex) { }
/* 997*/                $SwitchMap$javax$ws$rs$core$Response$Status = new int[javax.ws.rs.core.Response.Status.values().length];
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.BAD_REQUEST.ordinal()] = 1;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.UNAUTHORIZED.ordinal()] = 2;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.FORBIDDEN.ordinal()] = 3;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.NOT_FOUND.ordinal()] = 4;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.METHOD_NOT_ALLOWED.ordinal()] = 5;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE.ordinal()] = 6;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.UNSUPPORTED_MEDIA_TYPE.ordinal()] = 7;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR.ordinal()] = 8;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
/* 997*/                try
                        {
/* 997*/                    $SwitchMap$javax$ws$rs$core$Response$Status[javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE.ordinal()] = 9;
                        }
/* 997*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 997*/        _cls8..SwitchMap.javax.ws.rs.core.Response.Status[((javax.ws.rs.core.Response.Status) (obj)).ordinal()];
/* 997*/        JVM INSTR tableswitch 1 9: default 184
            //                           1 76
            //                           2 88
            //                           3 100
            //                           4 112
            //                           5 124
            //                           6 136
            //                           7 148
            //                           8 160
            //                           9 172;
                   goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
/* 999*/        obj = new BadRequestException(response);
/*1000*/        break; /* Loop/switch isn't completed */
_L4:
/*1002*/        obj = new NotAuthorizedException(response);
/*1003*/        break; /* Loop/switch isn't completed */
_L5:
/*1005*/        obj = new ForbiddenException(response);
/*1006*/        break; /* Loop/switch isn't completed */
_L6:
/*1008*/        obj = new NotFoundException(response);
/*1009*/        break; /* Loop/switch isn't completed */
_L7:
/*1011*/        obj = new NotAllowedException(response);
/*1012*/        break; /* Loop/switch isn't completed */
_L8:
/*1014*/        obj = new NotAcceptableException(response);
/*1015*/        break; /* Loop/switch isn't completed */
_L9:
/*1017*/        obj = new NotSupportedException(response);
/*1018*/        break; /* Loop/switch isn't completed */
_L10:
/*1020*/        obj = new InternalServerErrorException(response);
/*1021*/        break; /* Loop/switch isn't completed */
_L11:
/*1023*/        obj = new ServiceUnavailableException(response);
/*1024*/        break; /* Loop/switch isn't completed */
_L2:
/*1026*/        obj = response.getStatusInfo().getFamily();
/*1027*/        obj = createExceptionForFamily(response, ((javax.ws.rs.core.Response.Status.Family) (obj)));
/*1031*/        return new ResponseProcessingException(response, ((Throwable) (obj)));
                Throwable throwable;
/*1032*/        throwable;
/*1033*/        return new ResponseProcessingException(response, LocalizationMessages.RESPONSE_TO_EXCEPTION_CONVERSION_FAILED(), throwable);
            }

            private WebApplicationException createExceptionForFamily(Response response, javax.ws.rs.core.Response.Status.Family family)
            {
/*1039*/        switch(_cls8..SwitchMap.javax.ws.rs.core.Response.Status.Family[family.ordinal()])
                {
/*1041*/        case 1: // '\001'
/*1041*/            response = new RedirectionException(response);
                    break;

/*1044*/        case 2: // '\002'
/*1044*/            response = new ClientErrorException(response);
                    break;

/*1047*/        case 3: // '\003'
/*1047*/            response = new ServerErrorException(response);
                    break;

/*1050*/        default:
/*1050*/            response = new WebApplicationException(response);
                    break;
                }
/*1052*/        return response;
            }

            ClientRequest request()
            {
/*1061*/        return requestContext;
            }

            public volatile Invocation property(String s, Object obj)
            {
/*  92*/        return property(s, obj);
            }



            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/client/JerseyInvocation.getName());
            private final ClientRequest requestContext;
            private final boolean copyRequestContext;
            private static final Map METHODS = initializeMap();






}
