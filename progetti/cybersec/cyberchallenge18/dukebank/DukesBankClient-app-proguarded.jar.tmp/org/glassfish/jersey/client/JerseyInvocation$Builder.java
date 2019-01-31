// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import java.net.URI;
import java.util.Locale;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.MapPropertiesDelegate;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRequest, JerseyInvocation, ClientConfig, ClientRuntime, 
//            InboundJaxrsResponse

public static class init>
    implements javax.ws.rs.client..Builder
{

            ClientRequest request()
            {
/* 183*/        return requestContext;
            }

            private void storeEntity(Entity entity)
            {
/* 187*/        if(entity != null)
                {
/* 188*/            requestContext.variant(entity.getVariant());
/* 189*/            requestContext.setEntity(entity.getEntity());
/* 190*/            requestContext.setEntityAnnotations(entity.getAnnotations());
                }
            }

            public JerseyInvocation build(String s)
            {
/* 196*/        requestContext.setMethod(s);
/* 197*/        return new JerseyInvocation(this, true);
            }

            public JerseyInvocation build(String s, Entity entity)
            {
/* 202*/        requestContext.setMethod(s);
/* 203*/        storeEntity(entity);
/* 204*/        return new JerseyInvocation(this, true);
            }

            public JerseyInvocation buildGet()
            {
/* 209*/        requestContext.setMethod("GET");
/* 210*/        return new JerseyInvocation(this, true);
            }

            public JerseyInvocation buildDelete()
            {
/* 215*/        requestContext.setMethod("DELETE");
/* 216*/        return new JerseyInvocation(this, true);
            }

            public JerseyInvocation buildPost(Entity entity)
            {
/* 221*/        requestContext.setMethod("POST");
/* 222*/        storeEntity(entity);
/* 223*/        return new JerseyInvocation(this, true);
            }

            public JerseyInvocation buildPut(Entity entity)
            {
/* 228*/        requestContext.setMethod("PUT");
/* 229*/        storeEntity(entity);
/* 230*/        return new JerseyInvocation(this, true);
            }

            public AsyncInvoker async()
            {
/* 235*/        return new oker(this);
            }

            public transient oker accept(String as[])
            {
/* 240*/        requestContext.accept(as);
/* 241*/        return this;
            }

            public transient requestContext accept(MediaType amediatype[])
            {
/* 246*/        requestContext.accept(amediatype);
/* 247*/        return this;
            }

            public transient javax.ws.rs.client..Builder acceptEncoding(String as[])
            {
/* 252*/        requestContext.getHeaders().addAll("Accept-Encoding", (Object[])as);
/* 253*/        return this;
            }

            public transient requestContext acceptLanguage(Locale alocale[])
            {
/* 258*/        requestContext.acceptLanguage(alocale);
/* 259*/        return this;
            }

            public transient age acceptLanguage(String as[])
            {
/* 264*/        requestContext.acceptLanguage(as);
/* 265*/        return this;
            }

            public age cookie(Cookie cookie1)
            {
/* 270*/        requestContext.cookie(cookie1);
/* 271*/        return this;
            }

            public requestContext cookie(String s, String s1)
            {
/* 276*/        requestContext.cookie(new Cookie(s, s1));
/* 277*/        return this;
            }

            public requestContext cacheControl(CacheControl cachecontrol)
            {
/* 282*/        requestContext.cacheControl(cachecontrol);
/* 283*/        return this;
            }

            public l header(String s, Object obj)
            {
/* 288*/        MultivaluedMap multivaluedmap = requestContext.getHeaders();
/* 290*/        if(obj == null)
/* 291*/            multivaluedmap.remove(s);
/* 293*/        else
/* 293*/            multivaluedmap.add(s, obj);
/* 296*/        if("User-Agent".equalsIgnoreCase(s))
/* 297*/            requestContext.ignoreUserAgent(obj == null);
/* 300*/        return this;
            }

            public gent headers(MultivaluedMap multivaluedmap)
            {
/* 305*/        requestContext.replaceHeaders(multivaluedmap);
/* 306*/        return this;
            }

            public Response get()
                throws ProcessingException
            {
/* 311*/        return method("GET");
            }

            public Object get(Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 316*/        return method("GET", class1);
            }

            public Object get(GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 321*/        return method("GET", generictype);
            }

            public Response put(Entity entity)
                throws ProcessingException
            {
/* 326*/        return method("PUT", entity);
            }

            public Object put(Entity entity, Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 332*/        return method("PUT", entity, class1);
            }

            public Object put(Entity entity, GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 338*/        return method("PUT", entity, generictype);
            }

            public Response post(Entity entity)
                throws ProcessingException
            {
/* 343*/        return method("POST", entity);
            }

            public Object post(Entity entity, Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 349*/        return method("POST", entity, class1);
            }

            public Object post(Entity entity, GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 355*/        return method("POST", entity, generictype);
            }

            public Response delete()
                throws ProcessingException
            {
/* 360*/        return method("DELETE");
            }

            public Object delete(Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 365*/        return method("DELETE", class1);
            }

            public Object delete(GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 370*/        return method("DELETE", generictype);
            }

            public Response head()
                throws ProcessingException
            {
/* 375*/        return method("HEAD");
            }

            public Response options()
                throws ProcessingException
            {
/* 380*/        return method("OPTIONS");
            }

            public Object options(Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 385*/        return method("OPTIONS", class1);
            }

            public Object options(GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 390*/        return method("OPTIONS", generictype);
            }

            public Response trace()
                throws ProcessingException
            {
/* 395*/        return method("TRACE");
            }

            public Object trace(Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 400*/        return method("TRACE", class1);
            }

            public Object trace(GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 405*/        return method("TRACE", generictype);
            }

            public Response method(String s)
                throws ProcessingException
            {
/* 410*/        requestContext.setMethod(s);
/* 411*/        return (new JerseyInvocation(this)).invoke();
            }

            public Object method(String s, Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 416*/        if(class1 == null)
                {
/* 417*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 419*/            requestContext.setMethod(s);
/* 420*/            return (new JerseyInvocation(this)).invoke(class1);
                }
            }

            public Object method(String s, GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 426*/        if(generictype == null)
                {
/* 427*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 429*/            requestContext.setMethod(s);
/* 430*/            return (new JerseyInvocation(this)).invoke(generictype);
                }
            }

            public Response method(String s, Entity entity)
                throws ProcessingException
            {
/* 435*/        requestContext.setMethod(s);
/* 436*/        storeEntity(entity);
/* 437*/        return (new JerseyInvocation(this)).invoke();
            }

            public Object method(String s, Entity entity, Class class1)
                throws ProcessingException, WebApplicationException
            {
/* 443*/        if(class1 == null)
                {
/* 444*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 446*/            requestContext.setMethod(s);
/* 447*/            storeEntity(entity);
/* 448*/            return (new JerseyInvocation(this)).invoke(class1);
                }
            }

            public Object method(String s, Entity entity, GenericType generictype)
                throws ProcessingException, WebApplicationException
            {
/* 454*/        if(generictype == null)
                {
/* 455*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 457*/            requestContext.setMethod(s);
/* 458*/            storeEntity(entity);
/* 459*/            return (new JerseyInvocation(this)).invoke(generictype);
                }
            }

            public storeEntity property(String s, Object obj)
            {
/* 464*/        requestContext.setProperty(s, obj);
/* 465*/        return this;
            }

            public volatile javax.ws.rs.client..Builder property(String s, Object obj)
            {
/* 163*/        return property(s, obj);
            }

            public volatile javax.ws.rs.client..Builder headers(MultivaluedMap multivaluedmap)
            {
/* 163*/        return headers(multivaluedmap);
            }

            public volatile javax.ws.rs.client..Builder header(String s, Object obj)
            {
/* 163*/        return header(s, obj);
            }

            public volatile javax.ws.rs.client..Builder cacheControl(CacheControl cachecontrol)
            {
/* 163*/        return cacheControl(cachecontrol);
            }

            public volatile javax.ws.rs.client..Builder cookie(String s, String s1)
            {
/* 163*/        return cookie(s, s1);
            }

            public volatile javax.ws.rs.client..Builder cookie(Cookie cookie1)
            {
/* 163*/        return cookie(cookie1);
            }

            public volatile javax.ws.rs.client..Builder acceptLanguage(String as[])
            {
/* 163*/        return acceptLanguage(as);
            }

            public volatile javax.ws.rs.client..Builder acceptLanguage(Locale alocale[])
            {
/* 163*/        return acceptLanguage(alocale);
            }

            public volatile javax.ws.rs.client..Builder accept(MediaType amediatype[])
            {
/* 163*/        return accept(amediatype);
            }

            public volatile javax.ws.rs.client..Builder accept(String as[])
            {
/* 163*/        return accept(as);
            }

            public volatile Invocation buildPut(Entity entity)
            {
/* 163*/        return buildPut(entity);
            }

            public volatile Invocation buildPost(Entity entity)
            {
/* 163*/        return buildPost(entity);
            }

            public volatile Invocation buildDelete()
            {
/* 163*/        return buildDelete();
            }

            public volatile Invocation buildGet()
            {
/* 163*/        return buildGet();
            }

            public volatile Invocation build(String s, Entity entity)
            {
/* 163*/        return build(s, entity);
            }

            public volatile Invocation build(String s)
            {
/* 163*/        return build(s);
            }

            private final ClientRequest requestContext;



            protected questScope(URI uri, ClientConfig clientconfig)
            {
/* 174*/        requestContext = new ClientRequest(uri, clientconfig, new MapPropertiesDelegate());
            }

            // Unreferenced inner class org/glassfish/jersey/client/JerseyInvocation$1

/* anonymous class */
    class JerseyInvocation._cls1
        implements Producer
    {

                public Response call()
                    throws ProcessingException
                {
/* 684*/            return new InboundJaxrsResponse(runtime.invoke(JerseyInvocation.access$600(JerseyInvocation.this, JerseyInvocation.access$500(JerseyInvocation.this))), requestScope);
                }

                public volatile Object call()
                {
/* 681*/            return call();
                }

                final ClientRuntime val$runtime;
                final RequestScope val$requestScope;
                final JerseyInvocation this$0;

                    
                    {
/* 681*/                this$0 = final_jerseyinvocation;
/* 681*/                runtime = clientruntime;
/* 681*/                requestScope = RequestScope.this;
/* 681*/                super();
                    }
    }

}
