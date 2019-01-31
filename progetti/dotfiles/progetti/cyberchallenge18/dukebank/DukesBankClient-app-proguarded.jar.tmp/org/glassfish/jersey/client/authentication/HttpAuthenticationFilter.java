// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpAuthenticationFilter.java

package org.glassfish.jersey.client.authentication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.internal.LocalizationMessages;

// Referenced classes of package org.glassfish.jersey.client.authentication:
//            BasicAuthenticator, DigestAuthenticator, HttpAuthenticationFeature, RequestAuthenticationException

class HttpAuthenticationFilter
    implements ClientRequestFilter, ClientResponseFilter
{
    static class Credentials
    {

                String getUsername()
                {
/* 376*/            return username;
                }

                byte[] getPassword()
                {
/* 385*/            return password;
                }

                private final String username;
                private final byte password[];

                Credentials(String s, byte abyte0[])
                {
/* 355*/            username = s;
/* 356*/            password = abyte0;
                }

                Credentials(String s, String s1)
                {
/* 366*/            username = s;
/* 367*/            password = s1 != null ? s1.getBytes(HttpAuthenticationFilter.CHARACTER_SET) : null;
                }
    }

    static final class Type extends Enum
    {

                public static Type[] values()
                {
/*  85*/            return (Type[])$VALUES.clone();
                }

                public static Type valueOf(String s)
                {
/*  85*/            return (Type)Enum.valueOf(org/glassfish/jersey/client/authentication/HttpAuthenticationFilter$Type, s);
                }

                public static final Type BASIC;
                public static final Type DIGEST;
                private static final Type $VALUES[];

                static 
                {
/*  89*/            BASIC = new Type("BASIC", 0);
/*  93*/            DIGEST = new Type("DIGEST", 1);
/*  85*/            $VALUES = (new Type[] {
/*  85*/                BASIC, DIGEST
                    });
                }

                private Type(String s, int i)
                {
/*  85*/            super(s, i);
                }
    }


            HttpAuthenticationFilter(HttpAuthenticationFeature.Mode mode1, Credentials credentials, Credentials credentials1, Configuration configuration)
            {
/* 131*/        final int final_i = (configuration = getMaximumCacheLimit(configuration)) << 1;
/* 137*/        uriCache = Collections.synchronizedMap(new LinkedHashMap(final_i) {

                    protected boolean removeEldestEntry(java.util.Map.Entry entry)
                    {
/* 142*/                return size() > uriCacheLimit;
                    }

                    private static final long serialVersionUID = 0x6ea19a05734c9L;
                    final int val$uriCacheLimit;
                    final HttpAuthenticationFilter this$0;

                    
                    {
/* 137*/                this$0 = HttpAuthenticationFilter.this;
/* 137*/                uriCacheLimit = j;
/* 137*/                super(final_i);
                    }
        });
/* 146*/        mode = mode1;
        static class _cls2
        {

                    static final int $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[];

                    static 
                    {
/* 147*/                $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode = new int[HttpAuthenticationFeature.Mode.values().length];
/* 147*/                try
                        {
/* 147*/                    $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE.ordinal()] = 1;
                        }
/* 147*/                catch(NoSuchFieldError _ex) { }
/* 147*/                try
                        {
/* 147*/                    $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[HttpAuthenticationFeature.Mode.BASIC_NON_PREEMPTIVE.ordinal()] = 2;
                        }
/* 147*/                catch(NoSuchFieldError _ex) { }
/* 147*/                try
                        {
/* 147*/                    $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[HttpAuthenticationFeature.Mode.DIGEST.ordinal()] = 3;
                        }
/* 147*/                catch(NoSuchFieldError _ex) { }
/* 147*/                try
                        {
/* 147*/                    $SwitchMap$org$glassfish$jersey$client$authentication$HttpAuthenticationFeature$Mode[HttpAuthenticationFeature.Mode.UNIVERSAL.ordinal()] = 4;
                        }
/* 147*/                catch(NoSuchFieldError _ex) { }
                    }
        }

/* 147*/        switch(_cls2..SwitchMap.org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.Mode[mode1.ordinal()])
                {
/* 150*/        case 1: // '\001'
/* 150*/        case 2: // '\002'
/* 150*/            basicAuth = new BasicAuthenticator(credentials);
/* 151*/            digestAuth = null;
/* 152*/            return;

/* 154*/        case 3: // '\003'
/* 154*/            basicAuth = null;
/* 155*/            digestAuth = new DigestAuthenticator(credentials1, configuration);
/* 156*/            return;

/* 158*/        case 4: // '\004'
/* 158*/            basicAuth = new BasicAuthenticator(credentials);
/* 159*/            digestAuth = new DigestAuthenticator(credentials1, configuration);
/* 160*/            return;
                }
/* 162*/        throw new IllegalStateException("Not implemented.");
            }

            private int getMaximumCacheLimit(Configuration configuration)
            {
/* 167*/        if((configuration = ((Integer)ClientProperties.getValue(configuration.getProperties(), "jersey.config.client.digestAuthUriCacheSizeLimit", Integer.valueOf(10000))).intValue()) <= 0)
/* 170*/            configuration = 10000;
/* 172*/        return configuration;
            }

            public void filter(ClientRequestContext clientrequestcontext)
                throws IOException
            {
/* 177*/        if("true".equals(clientrequestcontext.getProperty("org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.reused")))
/* 178*/            return;
/* 181*/        if(clientrequestcontext.getHeaders().containsKey("Authorization"))
/* 182*/            return;
/* 185*/        Type type = null;
                Type type1;
/* 186*/        if(mode == HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE)
                {
/* 187*/            basicAuth.filterRequest(clientrequestcontext);
/* 188*/            type = Type.BASIC;
                } else
/* 189*/        if(mode != HttpAuthenticationFeature.Mode.BASIC_NON_PREEMPTIVE)
/* 191*/            if(mode == HttpAuthenticationFeature.Mode.DIGEST)
                    {
/* 192*/                if(digestAuth.filterRequest(clientrequestcontext))
/* 193*/                    type = Type.DIGEST;
                    } else
/* 195*/            if(mode == HttpAuthenticationFeature.Mode.UNIVERSAL && (type1 = (Type)uriCache.get(getCacheKey(clientrequestcontext))) != null)
                    {
/* 199*/                clientrequestcontext.setProperty("org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.operation", type1);
/* 200*/                if(type1 == Type.BASIC)
                        {
/* 201*/                    basicAuth.filterRequest(clientrequestcontext);
/* 202*/                    type = Type.BASIC;
                        } else
/* 203*/                if(type1 == Type.DIGEST && digestAuth.filterRequest(clientrequestcontext))
/* 205*/                    type = Type.DIGEST;
                    }
/* 211*/        if(type != null)
/* 212*/            clientrequestcontext.setProperty("org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.operation", type);
            }

            public void filter(ClientRequestContext clientrequestcontext, ClientResponseContext clientresponsecontext)
                throws IOException
            {
/* 218*/        if("true".equals(clientrequestcontext.getProperty("org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.reused")))
/* 219*/            return;
/* 222*/        Type type = null;
                boolean flag;
/* 225*/        if(clientresponsecontext.getStatus() == javax.ws.rs.core.Response.Status.UNAUTHORIZED.getStatusCode())
                {
                    String s;
                    String s1;
/* 226*/            if((s1 = (String)clientresponsecontext.getHeaders().getFirst("WWW-Authenticate")) != null)
/* 228*/                if((s = s1.trim().toUpperCase()).startsWith("BASIC"))
/* 230*/                    type = Type.BASIC;
/* 231*/                else
/* 231*/                if(s.startsWith("DIGEST"))
/* 232*/                    type = Type.DIGEST;
/* 235*/                else
/* 235*/                    return;
/* 238*/            flag = true;
                } else
                {
/* 240*/            flag = false;
                }
/* 243*/        if(mode != HttpAuthenticationFeature.Mode.BASIC_PREEMPTIVE)
/* 245*/            if(mode == HttpAuthenticationFeature.Mode.BASIC_NON_PREEMPTIVE)
                    {
/* 246*/                if(flag && type == Type.BASIC)
                        {
/* 247*/                    basicAuth.filterResponseAndAuthenticate(clientrequestcontext, clientresponsecontext);
/* 247*/                    return;
                        }
                    } else
/* 249*/            if(mode == HttpAuthenticationFeature.Mode.DIGEST)
                    {
/* 250*/                if(flag && type == Type.DIGEST)
                        {
/* 251*/                    digestAuth.filterResponse(clientrequestcontext, clientresponsecontext);
/* 251*/                    return;
                        }
                    } else
/* 253*/            if(mode == HttpAuthenticationFeature.Mode.UNIVERSAL)
                    {
                        Type type1;
/* 254*/                if((type1 = (Type)clientrequestcontext.getProperty("org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.operation")) != null)
/* 256*/                    updateCache(clientrequestcontext, !flag, type1);
/* 259*/                if(flag)
                        {
/* 260*/                    boolean flag1 = false;
/* 263*/                    if(type == Type.BASIC)
/* 264*/                        flag1 = basicAuth.filterResponseAndAuthenticate(clientrequestcontext, clientresponsecontext);
/* 265*/                    else
/* 265*/                    if(type == Type.DIGEST)
/* 266*/                        flag1 = digestAuth.filterResponse(clientrequestcontext, clientresponsecontext);
/* 268*/                    updateCache(clientrequestcontext, flag1, type);
                        }
                    }
            }

            private String getCacheKey(ClientRequestContext clientrequestcontext)
            {
/* 274*/        return (new StringBuilder()).append(clientrequestcontext.getUri().toString()).append(":").append(clientrequestcontext.getMethod()).toString();
            }

            private void updateCache(ClientRequestContext clientrequestcontext, boolean flag, Type type)
            {
/* 278*/        clientrequestcontext = getCacheKey(clientrequestcontext);
/* 279*/        if(flag)
                {
/* 280*/            uriCache.put(clientrequestcontext, type);
/* 280*/            return;
                } else
                {
/* 282*/            uriCache.remove(clientrequestcontext);
/* 284*/            return;
                }
            }

            static boolean repeatRequest(ClientRequestContext clientrequestcontext, ClientResponseContext clientresponsecontext, String s)
            {
/* 297*/        Object obj = ClientBuilder.newClient(clientrequestcontext.getConfiguration());
/* 298*/        String s1 = clientrequestcontext.getMethod();
/* 299*/        Object obj1 = clientrequestcontext.getMediaType();
/* 300*/        Object obj2 = clientrequestcontext.getUri();
/* 302*/        obj = ((WebTarget) (obj = ((Client) (obj)).target(((URI) (obj2))))).request(new MediaType[] {
/* 304*/            obj1
                });
/* 306*/        obj1 = new MultivaluedHashMap();
/* 308*/        obj2 = clientrequestcontext.getHeaders().entrySet().iterator();
/* 308*/        do
                {
/* 308*/            if(!((Iterator) (obj2)).hasNext())
/* 308*/                break;
/* 308*/            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj2)).next();
/* 309*/            if(!"Authorization".equals(entry.getKey()))
/* 312*/                ((MultivaluedMap) (obj1)).put(entry.getKey(), entry.getValue());
                } while(true);
/* 315*/        ((MultivaluedMap) (obj1)).add("Authorization", s);
/* 316*/        ((javax.ws.rs.client.Invocation.Builder) (obj)).headers(((MultivaluedMap) (obj1)));
/* 318*/        ((javax.ws.rs.client.Invocation.Builder) (obj)).property("org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.reused", "true");
/* 321*/        if(clientrequestcontext.getEntity() == null)
/* 322*/            obj2 = ((javax.ws.rs.client.Invocation.Builder) (obj)).build(s1);
/* 324*/        else
/* 324*/            obj2 = ((javax.ws.rs.client.Invocation.Builder) (obj)).build(s1, Entity.entity(clientrequestcontext.getEntity(), clientrequestcontext.getMediaType()));
                Response response;
/* 327*/        if((response = ((Invocation) (obj2)).invoke()).hasEntity())
/* 330*/            clientresponsecontext.setEntityStream((InputStream)response.readEntity(java/io/InputStream));
/* 332*/        (clientrequestcontext = clientresponsecontext.getHeaders()).clear();
/* 334*/        clientrequestcontext.putAll(response.getStringHeaders());
/* 335*/        clientresponsecontext.setStatus(response.getStatus());
/* 337*/        return clientresponsecontext.getStatus() != javax.ws.rs.core.Response.Status.UNAUTHORIZED.getStatusCode();
            }

            private static Credentials extractCredentials(ClientRequestContext clientrequestcontext, Type type)
            {
/* 390*/        String s = null;
/* 391*/        String s1 = null;
/* 392*/        if(type == null)
                {
/* 393*/            s = "jersey.config.client.http.auth.username";
/* 394*/            s1 = "jersey.config.client.http.auth.password";
                } else
/* 395*/        if(type == Type.BASIC)
                {
/* 396*/            s = "jersey.config.client.http.auth.basic.username";
/* 397*/            s1 = "jersey.config.client.http.auth.basic.password";
                } else
/* 398*/        if(type == Type.DIGEST)
                {
/* 399*/            s = "jersey.config.client.http.auth.digest.username";
/* 400*/            s1 = "jersey.config.client.http.auth.digest.password";
                }
/* 403*/        if((type = (String)clientrequestcontext.getProperty(s)) != null && !type.equals(""))
                {
/* 406*/            if((clientrequestcontext = ((ClientRequestContext) (clientrequestcontext.getProperty(s1)))) instanceof byte[])
/* 408*/                clientrequestcontext = (byte[])clientrequestcontext;
/* 409*/            else
/* 409*/            if(clientrequestcontext instanceof String)
/* 410*/                clientrequestcontext = ((String)clientrequestcontext).getBytes(CHARACTER_SET);
/* 412*/            else
/* 412*/                throw new RequestAuthenticationException(LocalizationMessages.AUTHENTICATION_CREDENTIALS_REQUEST_PASSWORD_UNSUPPORTED());
/* 415*/            return new Credentials(type, clientrequestcontext);
                } else
                {
/* 417*/            return null;
                }
            }

            static Credentials getCredentials(ClientRequestContext clientrequestcontext, Credentials credentials, Type type)
            {
/* 435*/        if((type = extractCredentials(clientrequestcontext, type)) != null)
/* 438*/            return type;
/* 440*/        if((clientrequestcontext = extractCredentials(clientrequestcontext, null)) != null)
/* 442*/            return clientrequestcontext;
/* 442*/        else
/* 442*/            return credentials;
            }

            private static final String REQUEST_PROPERTY_FILTER_REUSED = "org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.reused";
            private static final String REQUEST_PROPERTY_OPERATION = "org.glassfish.jersey.client.authentication.HttpAuthenticationFilter.operation";
            static final Charset CHARACTER_SET = Charset.forName("iso-8859-1");
            private final HttpAuthenticationFeature.Mode mode;
            private final Map uriCache;
            private final DigestAuthenticator digestAuth;
            private final BasicAuthenticator basicAuth;
            private static final int MAXIMUM_DIGEST_CACHE_SIZE = 10000;

}
