// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyWebTarget.java

package org.glassfish.jersey.client;

import java.net.URI;
import java.util.*;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import jersey.repackaged.com.google.common.base.Preconditions;

// Referenced classes of package org.glassfish.jersey.client:
//            ClientConfig, ClientRequest, Initializable, JerseyClient, 
//            JerseyInvocation

public class JerseyWebTarget
    implements WebTarget, Initializable
{

            JerseyWebTarget(String s, JerseyClient jerseyclient)
            {
/*  71*/        this(UriBuilder.fromUri(s), jerseyclient.getConfiguration());
            }

            JerseyWebTarget(URI uri, JerseyClient jerseyclient)
            {
/*  81*/        this(UriBuilder.fromUri(uri), jerseyclient.getConfiguration());
            }

            JerseyWebTarget(UriBuilder uribuilder, JerseyClient jerseyclient)
            {
/*  91*/        this(uribuilder.clone(), jerseyclient.getConfiguration());
            }

            JerseyWebTarget(Link link, JerseyClient jerseyclient)
            {
/* 102*/        this(UriBuilder.fromUri(link.getUri()), jerseyclient.getConfiguration());
            }

            protected JerseyWebTarget(UriBuilder uribuilder, JerseyWebTarget jerseywebtarget)
            {
/* 112*/        this(uribuilder, jerseywebtarget.config);
            }

            protected JerseyWebTarget(UriBuilder uribuilder, ClientConfig clientconfig)
            {
/* 122*/        clientconfig.checkClient();
/* 124*/        targetUri = uribuilder;
/* 125*/        config = clientconfig.snapshot();
            }

            public URI getUri()
            {
/* 130*/        checkNotClosed();
/* 132*/        return targetUri.build(new Object[0]);
                IllegalArgumentException illegalargumentexception;
/* 133*/        illegalargumentexception;
/* 134*/        throw new IllegalStateException(illegalargumentexception.getMessage(), illegalargumentexception);
            }

            private void checkNotClosed()
            {
/* 139*/        config.getClient().checkNotClosed();
            }

            public UriBuilder getUriBuilder()
            {
/* 144*/        checkNotClosed();
/* 145*/        return targetUri.clone();
            }

            public JerseyWebTarget path(String s)
                throws NullPointerException
            {
/* 150*/        checkNotClosed();
/* 151*/        Preconditions.checkNotNull(s, "path is 'null'.");
/* 153*/        return new JerseyWebTarget(getUriBuilder().path(s), this);
            }

            public transient JerseyWebTarget matrixParam(String s, Object aobj[])
                throws NullPointerException
            {
/* 158*/        checkNotClosed();
/* 159*/        Preconditions.checkNotNull(s, "Matrix parameter name must not be 'null'.");
/* 161*/        if(aobj == null || aobj.length == 0 || aobj.length == 1 && aobj[0] == null)
                {
/* 162*/            return new JerseyWebTarget(getUriBuilder().replaceMatrixParam(s, null), this);
                } else
                {
/* 165*/            checkForNullValues(s, aobj);
/* 166*/            return new JerseyWebTarget(getUriBuilder().matrixParam(s, aobj), this);
                }
            }

            public transient JerseyWebTarget queryParam(String s, Object aobj[])
                throws NullPointerException
            {
/* 171*/        checkNotClosed();
/* 172*/        return new JerseyWebTarget(setQueryParam(getUriBuilder(), s, aobj), this);
            }

            private static UriBuilder setQueryParam(UriBuilder uribuilder, String s, Object aobj[])
            {
/* 176*/        if(aobj == null || aobj.length == 0 || aobj.length == 1 && aobj[0] == null)
                {
/* 177*/            return uribuilder.replaceQueryParam(s, null);
                } else
                {
/* 180*/            checkForNullValues(s, aobj);
/* 181*/            return uribuilder.queryParam(s, aobj);
                }
            }

            private static void checkForNullValues(String s, Object aobj[])
            {
/* 185*/        Preconditions.checkNotNull(s, "name is 'null'.");
/* 187*/        LinkedList linkedlist = new LinkedList();
/* 188*/        for(int i = 0; i < aobj.length; i++)
/* 189*/            if(aobj[i] == null)
/* 190*/                linkedlist.add(Integer.valueOf(i));

                int j;
/* 193*/        if((j = linkedlist.size()) > 0)
                {
                    String s1;
/* 197*/            if(j == 1)
                    {
/* 198*/                aobj = "value";
/* 199*/                s1 = "index";
                    } else
                    {
/* 201*/                aobj = "values";
/* 202*/                s1 = "indexes";
                    }
/* 205*/            throw new NullPointerException(String.format("'null' %s detected for parameter '%s' on %s : %s", new Object[] {
/* 205*/                aobj, s, s1, linkedlist.toString()
                    }));
                } else
                {
/* 209*/            return;
                }
            }

            public JerseyInvocation.Builder request()
            {
/* 213*/        checkNotClosed();
/* 214*/        return new JerseyInvocation.Builder(getUri(), config.snapshot());
            }

            public transient JerseyInvocation.Builder request(String as[])
            {
/* 219*/        checkNotClosed();
                JerseyInvocation.Builder builder;
/* 220*/        (builder = new JerseyInvocation.Builder(getUri(), config.snapshot())).request().accept(as);
/* 222*/        return builder;
            }

            public transient JerseyInvocation.Builder request(MediaType amediatype[])
            {
/* 227*/        checkNotClosed();
                JerseyInvocation.Builder builder;
/* 228*/        (builder = new JerseyInvocation.Builder(getUri(), config.snapshot())).request().accept(amediatype);
/* 230*/        return builder;
            }

            public JerseyWebTarget resolveTemplate(String s, Object obj)
                throws NullPointerException
            {
/* 235*/        return resolveTemplate(s, obj, true);
            }

            public JerseyWebTarget resolveTemplate(String s, Object obj, boolean flag)
                throws NullPointerException
            {
/* 240*/        checkNotClosed();
/* 241*/        Preconditions.checkNotNull(s, "name is 'null'.");
/* 242*/        Preconditions.checkNotNull(obj, "value is 'null'.");
/* 244*/        return new JerseyWebTarget(getUriBuilder().resolveTemplate(s, obj, flag), this);
            }

            public JerseyWebTarget resolveTemplateFromEncoded(String s, Object obj)
                throws NullPointerException
            {
/* 250*/        checkNotClosed();
/* 251*/        Preconditions.checkNotNull(s, "name is 'null'.");
/* 252*/        Preconditions.checkNotNull(obj, "value is 'null'.");
/* 254*/        return new JerseyWebTarget(getUriBuilder().resolveTemplateFromEncoded(s, obj), this);
            }

            public JerseyWebTarget resolveTemplates(Map map)
                throws NullPointerException
            {
/* 259*/        return resolveTemplates(map, true);
            }

            public JerseyWebTarget resolveTemplates(Map map, boolean flag)
                throws NullPointerException
            {
/* 265*/        checkNotClosed();
/* 266*/        checkTemplateValues(map);
/* 268*/        if(map.isEmpty())
/* 269*/            return this;
/* 271*/        else
/* 271*/            return new JerseyWebTarget(getUriBuilder().resolveTemplates(map, flag), this);
            }

            public JerseyWebTarget resolveTemplatesFromEncoded(Map map)
                throws NullPointerException
            {
/* 278*/        checkNotClosed();
/* 279*/        checkTemplateValues(map);
/* 281*/        if(map.isEmpty())
/* 282*/            return this;
/* 284*/        else
/* 284*/            return new JerseyWebTarget(getUriBuilder().resolveTemplatesFromEncoded(map), this);
            }

            private void checkTemplateValues(Map map)
                throws NullPointerException
            {
/* 297*/        Preconditions.checkNotNull(map, "templateValues is 'null'.");
                java.util.Map.Entry entry;
/* 299*/        for(map = map.entrySet().iterator(); map.hasNext(); Preconditions.checkNotNull(entry.getValue(), "value is 'null'."))
/* 299*/            Preconditions.checkNotNull((entry = (java.util.Map.Entry)map.next()).getKey(), "name is 'null'.");

            }

            public JerseyWebTarget register(Class class1)
            {
/* 307*/        checkNotClosed();
/* 308*/        config.register(class1);
/* 309*/        return this;
            }

            public JerseyWebTarget register(Object obj)
            {
/* 314*/        checkNotClosed();
/* 315*/        config.register(obj);
/* 316*/        return this;
            }

            public JerseyWebTarget register(Class class1, int i)
            {
/* 321*/        checkNotClosed();
/* 322*/        config.register(class1, i);
/* 323*/        return this;
            }

            public transient JerseyWebTarget register(Class class1, Class aclass[])
            {
/* 328*/        checkNotClosed();
/* 329*/        config.register(class1, aclass);
/* 330*/        return this;
            }

            public JerseyWebTarget register(Class class1, Map map)
            {
/* 335*/        checkNotClosed();
/* 336*/        config.register(class1, map);
/* 337*/        return this;
            }

            public JerseyWebTarget register(Object obj, int i)
            {
/* 342*/        checkNotClosed();
/* 343*/        config.register(obj, i);
/* 344*/        return this;
            }

            public transient JerseyWebTarget register(Object obj, Class aclass[])
            {
/* 349*/        checkNotClosed();
/* 350*/        config.register(obj, aclass);
/* 351*/        return this;
            }

            public JerseyWebTarget register(Object obj, Map map)
            {
/* 356*/        checkNotClosed();
/* 357*/        config.register(obj, map);
/* 358*/        return this;
            }

            public JerseyWebTarget property(String s, Object obj)
            {
/* 363*/        checkNotClosed();
/* 364*/        config.property(s, obj);
/* 365*/        return this;
            }

            public ClientConfig getConfiguration()
            {
/* 370*/        checkNotClosed();
/* 371*/        return config.getConfiguration();
            }

            public JerseyWebTarget preInitialize()
            {
/* 376*/        config.preInitialize();
/* 377*/        return this;
            }

            public String toString()
            {
/* 382*/        return (new StringBuilder("JerseyWebTarget { ")).append(targetUri.toTemplate()).append(" }").toString();
            }

            public volatile javax.ws.rs.client.Invocation.Builder request(MediaType amediatype[])
            {
/*  59*/        return request(amediatype);
            }

            public volatile javax.ws.rs.client.Invocation.Builder request(String as[])
            {
/*  59*/        return request(as);
            }

            public volatile javax.ws.rs.client.Invocation.Builder request()
            {
/*  59*/        return request();
            }

            public volatile WebTarget queryParam(String s, Object aobj[])
            {
/*  59*/        return queryParam(s, aobj);
            }

            public volatile WebTarget matrixParam(String s, Object aobj[])
            {
/*  59*/        return matrixParam(s, aobj);
            }

            public volatile WebTarget resolveTemplatesFromEncoded(Map map)
            {
/*  59*/        return resolveTemplatesFromEncoded(map);
            }

            public volatile WebTarget resolveTemplates(Map map, boolean flag)
            {
/*  59*/        return resolveTemplates(map, flag);
            }

            public volatile WebTarget resolveTemplates(Map map)
            {
/*  59*/        return resolveTemplates(map);
            }

            public volatile WebTarget resolveTemplateFromEncoded(String s, Object obj)
            {
/*  59*/        return resolveTemplateFromEncoded(s, obj);
            }

            public volatile WebTarget resolveTemplate(String s, Object obj, boolean flag)
            {
/*  59*/        return resolveTemplate(s, obj, flag);
            }

            public volatile WebTarget resolveTemplate(String s, Object obj)
            {
/*  59*/        return resolveTemplate(s, obj);
            }

            public volatile WebTarget path(String s)
            {
/*  59*/        return path(s);
            }

            public volatile Configurable register(Object obj, Map map)
            {
/*  59*/        return register(obj, map);
            }

            public volatile Configurable register(Object obj, Class aclass[])
            {
/*  59*/        return register(obj, aclass);
            }

            public volatile Configurable register(Object obj, int i)
            {
/*  59*/        return register(obj, i);
            }

            public volatile Configurable register(Object obj)
            {
/*  59*/        return register(obj);
            }

            public volatile Configurable register(Class class1, Map map)
            {
/*  59*/        return register(class1, map);
            }

            public volatile Configurable register(Class class1, Class aclass[])
            {
/*  59*/        return register(class1, aclass);
            }

            public volatile Configurable register(Class class1, int i)
            {
/*  59*/        return register(class1, i);
            }

            public volatile Configurable register(Class class1)
            {
/*  59*/        return register(class1);
            }

            public volatile Configurable property(String s, Object obj)
            {
/*  59*/        return property(s, obj);
            }

            public volatile Configuration getConfiguration()
            {
/*  59*/        return getConfiguration();
            }

            public volatile Initializable preInitialize()
            {
/*  59*/        return preInitialize();
            }

            private final ClientConfig config;
            private final UriBuilder targetUri;
}
