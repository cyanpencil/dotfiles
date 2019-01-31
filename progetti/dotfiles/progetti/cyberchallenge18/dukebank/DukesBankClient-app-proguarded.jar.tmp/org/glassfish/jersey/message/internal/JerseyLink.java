// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JerseyLink.java

package org.glassfish.jersey.message.internal;

import java.net.URI;
import java.util.*;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.uri.UriTemplate;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            LinkProvider

public final class JerseyLink extends Link
{
    public static class Builder
        implements javax.ws.rs.core.Link.Builder
    {

                public Builder link(Link link1)
                {
/*  89*/            uriBuilder.uri(link1.getUri());
/*  90*/            params.clear();
/*  91*/            params.putAll(link1.getParams());
/*  92*/            return this;
                }

                public Builder link(String s)
                {
/*  97*/            LinkProvider.initBuilder(this, s);
/*  98*/            return this;
                }

                public Builder uri(URI uri1)
                {
/* 103*/            uriBuilder = UriBuilder.fromUri(uri1);
/* 104*/            return this;
                }

                public Builder uri(String s)
                {
/* 109*/            uriBuilder = UriBuilder.fromUri(s);
/* 110*/            return this;
                }

                public Builder uriBuilder(UriBuilder uribuilder)
                {
/* 115*/            uriBuilder = UriBuilder.fromUri(uribuilder.toTemplate());
/* 116*/            return this;
                }

                public javax.ws.rs.core.Link.Builder baseUri(URI uri1)
                {
/* 121*/            baseUri = uri1;
/* 122*/            return this;
                }

                public javax.ws.rs.core.Link.Builder baseUri(String s)
                {
/* 127*/            baseUri = URI.create(s);
/* 128*/            return this;
                }

                public Builder rel(String s)
                {
/* 133*/            String s1 = (String)params.get("rel");
/* 134*/            param("rel", s1 != null ? (new StringBuilder()).append(s1).append(" ").append(s).toString() : s);
/* 135*/            return this;
                }

                public Builder title(String s)
                {
/* 140*/            param("title", s);
/* 141*/            return this;
                }

                public Builder type(String s)
                {
/* 146*/            param("type", s);
/* 147*/            return this;
                }

                public Builder param(String s, String s1)
                {
/* 152*/            if(s == null || s1 == null)
                    {
/* 153*/                throw new IllegalArgumentException("Link parameter name or value is null");
                    } else
                    {
/* 155*/                params.put(s, s1);
/* 156*/                return this;
                    }
                }

                public transient JerseyLink build(Object aobj[])
                {
/* 161*/            aobj = resolveLinkUri(aobj);
/* 162*/            return new JerseyLink(((URI) (aobj)), Collections.unmodifiableMap(new HashMap(params)));
                }

                public transient Link buildRelativized(URI uri1, Object aobj[])
                {
/* 167*/            uri1 = UriTemplate.relativize(uri1, resolveLinkUri(aobj));
/* 168*/            return new JerseyLink(uri1, Collections.unmodifiableMap(new HashMap(params)));
                }

                private URI resolveLinkUri(Object aobj[])
                {
/* 172*/            aobj = uriBuilder.build(aobj);
/* 173*/            if(baseUri == null || ((URI) (aobj)).isAbsolute())
/* 174*/                return UriTemplate.normalize(((URI) (aobj)));
/* 176*/            else
/* 176*/                return UriTemplate.resolve(baseUri, ((URI) (aobj)));
                }

                public volatile Link build(Object aobj[])
                {
/*  73*/            return build(aobj);
                }

                public volatile javax.ws.rs.core.Link.Builder param(String s, String s1)
                {
/*  73*/            return param(s, s1);
                }

                public volatile javax.ws.rs.core.Link.Builder type(String s)
                {
/*  73*/            return type(s);
                }

                public volatile javax.ws.rs.core.Link.Builder title(String s)
                {
/*  73*/            return title(s);
                }

                public volatile javax.ws.rs.core.Link.Builder rel(String s)
                {
/*  73*/            return rel(s);
                }

                public volatile javax.ws.rs.core.Link.Builder uriBuilder(UriBuilder uribuilder)
                {
/*  73*/            return uriBuilder(uribuilder);
                }

                public volatile javax.ws.rs.core.Link.Builder uri(String s)
                {
/*  73*/            return uri(s);
                }

                public volatile javax.ws.rs.core.Link.Builder uri(URI uri1)
                {
/*  73*/            return uri(uri1);
                }

                public volatile javax.ws.rs.core.Link.Builder link(String s)
                {
/*  73*/            return link(s);
                }

                public volatile javax.ws.rs.core.Link.Builder link(Link link1)
                {
/*  73*/            return link(link1);
                }

                private UriBuilder uriBuilder;
                private URI baseUri;
                private Map params;

                public Builder()
                {
/*  77*/            uriBuilder = new JerseyUriBuilder();
/*  81*/            baseUri = null;
/*  85*/            params = new HashMap();
                }
    }


            private JerseyLink(URI uri1, Map map)
            {
/* 181*/        uri = uri1;
/* 182*/        params = map;
            }

            public final URI getUri()
            {
/* 187*/        return uri;
            }

            public final UriBuilder getUriBuilder()
            {
/* 192*/        return (new JerseyUriBuilder()).uri(uri);
            }

            public final String getRel()
            {
/* 197*/        return (String)params.get("rel");
            }

            public final List getRels()
            {
                String s;
/* 202*/        if((s = (String)params.get("rel")) == null)
/* 203*/            return Collections.emptyList();
/* 203*/        else
/* 203*/            return Arrays.asList(s.split(" +"));
            }

            public final String getTitle()
            {
/* 208*/        return (String)params.get("title");
            }

            public final String getType()
            {
/* 213*/        return (String)params.get("type");
            }

            public final Map getParams()
            {
/* 218*/        return params;
            }

            public final String toString()
            {
/* 223*/        return LinkProvider.stringfy(this);
            }

            public final boolean equals(Object obj)
            {
/* 228*/        if(this == obj)
/* 229*/            return true;
/* 231*/        if(obj instanceof Link)
                {
/* 232*/            obj = (Link)obj;
/* 233*/            return uri.equals(((Link) (obj)).getUri()) && params.equals(((Link) (obj)).getParams());
                } else
                {
/* 235*/            return false;
                }
            }

            public final int hashCode()
            {
/* 241*/        int i = 267 + (uri == null ? 0 : uri.hashCode());
/* 242*/        return i = i * 89 + (params == null ? 0 : params.hashCode());
            }


            private final URI uri;
            private final Map params;
}
