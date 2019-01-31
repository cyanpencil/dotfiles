// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyInvocation.java

package org.glassfish.jersey.client;

import java.util.concurrent.Future;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.Producer;
import org.glassfish.jersey.process.internal.RequestScope;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientRequest, JerseyInvocation, ClientRuntime, InboundJaxrsResponse

static class builder
    implements AsyncInvoker
{

            public Future get()
            {
/* 480*/        return method("GET");
            }

            public Future get(Class class1)
            {
/* 485*/        return method("GET", class1);
            }

            public Future get(GenericType generictype)
            {
/* 490*/        return method("GET", generictype);
            }

            public Future get(InvocationCallback invocationcallback)
            {
/* 495*/        return method("GET", invocationcallback);
            }

            public Future put(Entity entity)
            {
/* 500*/        return method("PUT", entity);
            }

            public Future put(Entity entity, Class class1)
            {
/* 505*/        return method("PUT", entity, class1);
            }

            public Future put(Entity entity, GenericType generictype)
            {
/* 510*/        return method("PUT", entity, generictype);
            }

            public Future put(Entity entity, InvocationCallback invocationcallback)
            {
/* 515*/        return method("PUT", entity, invocationcallback);
            }

            public Future post(Entity entity)
            {
/* 520*/        return method("POST", entity);
            }

            public Future post(Entity entity, Class class1)
            {
/* 525*/        return method("POST", entity, class1);
            }

            public Future post(Entity entity, GenericType generictype)
            {
/* 530*/        return method("POST", entity, generictype);
            }

            public Future post(Entity entity, InvocationCallback invocationcallback)
            {
/* 535*/        return method("POST", entity, invocationcallback);
            }

            public Future delete()
            {
/* 540*/        return method("DELETE");
            }

            public Future delete(Class class1)
            {
/* 545*/        return method("DELETE", class1);
            }

            public Future delete(GenericType generictype)
            {
/* 550*/        return method("DELETE", generictype);
            }

            public Future delete(InvocationCallback invocationcallback)
            {
/* 555*/        return method("DELETE", invocationcallback);
            }

            public Future head()
            {
/* 560*/        return method("HEAD");
            }

            public Future head(InvocationCallback invocationcallback)
            {
/* 565*/        return method("HEAD", invocationcallback);
            }

            public Future options()
            {
/* 570*/        return method("OPTIONS");
            }

            public Future options(Class class1)
            {
/* 575*/        return method("OPTIONS", class1);
            }

            public Future options(GenericType generictype)
            {
/* 580*/        return method("OPTIONS", generictype);
            }

            public Future options(InvocationCallback invocationcallback)
            {
/* 585*/        return method("OPTIONS", invocationcallback);
            }

            public Future trace()
            {
/* 590*/        return method("TRACE");
            }

            public Future trace(Class class1)
            {
/* 595*/        return method("TRACE", class1);
            }

            public Future trace(GenericType generictype)
            {
/* 600*/        return method("TRACE", generictype);
            }

            public Future trace(InvocationCallback invocationcallback)
            {
/* 605*/        return method("TRACE", invocationcallback);
            }

            public Future method(String s)
            {
/* 610*/        s._mth000(builder).setMethod(s);
/* 611*/        return (new JerseyInvocation(builder)).submit();
            }

            public Future method(String s, Class class1)
            {
/* 616*/        if(class1 == null)
                {
/* 617*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 619*/            s._mth000(builder).setMethod(s);
/* 620*/            return (new JerseyInvocation(builder)).submit(class1);
                }
            }

            public Future method(String s, GenericType generictype)
            {
/* 625*/        if(generictype == null)
                {
/* 626*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 628*/            s._mth000(builder).setMethod(s);
/* 629*/            return (new JerseyInvocation(builder)).submit(generictype);
                }
            }

            public Future method(String s, InvocationCallback invocationcallback)
            {
/* 634*/        s._mth000(builder).setMethod(s);
/* 635*/        return (new JerseyInvocation(builder)).submit(invocationcallback);
            }

            public Future method(String s, Entity entity)
            {
/* 640*/        s._mth000(builder).setMethod(s);
/* 641*/        s._mth400(builder, entity);
/* 642*/        return (new JerseyInvocation(builder)).submit();
            }

            public Future method(String s, Entity entity, Class class1)
            {
/* 647*/        if(class1 == null)
                {
/* 648*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 650*/            s._mth000(builder).setMethod(s);
/* 651*/            s._mth400(builder, entity);
/* 652*/            return (new JerseyInvocation(builder)).submit(class1);
                }
            }

            public Future method(String s, Entity entity, GenericType generictype)
            {
/* 657*/        if(generictype == null)
                {
/* 658*/            throw new IllegalArgumentException(LocalizationMessages.RESPONSE_TYPE_IS_NULL());
                } else
                {
/* 660*/            s._mth000(builder).setMethod(s);
/* 661*/            s._mth400(builder, entity);
/* 662*/            return (new JerseyInvocation(builder)).submit(generictype);
                }
            }

            public Future method(String s, Entity entity, InvocationCallback invocationcallback)
            {
/* 667*/        s._mth000(builder).setMethod(s);
/* 668*/        s._mth400(builder, entity);
/* 669*/        return (new JerseyInvocation(builder)).submit(invocationcallback);
            }

            private final builder builder;

            private Scope(Scope scope)
            {
/* 474*/        builder = scope;
/* 475*/        s._mth000(builder).setAsynchronous(true);
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
