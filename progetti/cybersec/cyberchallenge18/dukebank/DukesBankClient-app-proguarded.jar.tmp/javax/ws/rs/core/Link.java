// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Link.java

package javax.ws.rs.core;

import java.net.URI;
import java.util.*;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

// Referenced classes of package javax.ws.rs.core:
//            UriBuilder

public abstract class Link
{
    public static class JaxbAdapter extends XmlAdapter
    {

                public Link unmarshal(JaxbLink jaxblink)
                {
/* 594*/            Builder builder = Link.fromUri(jaxblink.getUri());
                    java.util.Map.Entry entry;
/* 595*/            for(jaxblink = jaxblink.getParams().entrySet().iterator(); jaxblink.hasNext(); builder.param(((QName)entry.getKey()).getLocalPart(), entry.getValue().toString()))
/* 595*/                entry = (java.util.Map.Entry)jaxblink.next();

/* 598*/            return builder.build(new Object[0]);
                }

                public JaxbLink marshal(Link link)
                {
/* 609*/            JaxbLink jaxblink = new JaxbLink(link.getUri());
                    java.util.Map.Entry entry;
                    String s;
/* 610*/            for(link = link.getParams().entrySet().iterator(); link.hasNext(); jaxblink.getParams().put(new QName("", s), entry.getValue()))
/* 610*/                s = (String)(entry = (java.util.Map.Entry)link.next()).getKey();

/* 614*/            return jaxblink;
                }

                public volatile Object marshal(Object obj)
                    throws Exception
                {
/* 584*/            return marshal((Link)obj);
                }

                public volatile Object unmarshal(Object obj)
                    throws Exception
                {
/* 584*/            return unmarshal((JaxbLink)obj);
                }

                public JaxbAdapter()
                {
                }
    }

    public static class JaxbLink
    {

                public URI getUri()
                {
/* 491*/            return uri;
                }

                public Map getParams()
                {
/* 501*/            if(params == null)
/* 502*/                params = new HashMap();
/* 504*/            return params;
                }

                void setUri(URI uri1)
                {
/* 513*/            uri = uri1;
                }

                void setParams(Map map)
                {
/* 522*/            params = map;
                }

                public boolean equals(Object obj)
                {
/* 527*/            if(this == obj)
/* 527*/                return true;
/* 528*/            if(!(obj instanceof JaxbLink))
/* 528*/                return false;
/* 530*/            obj = (JaxbLink)obj;
/* 532*/            if(uri == null ? ((JaxbLink) (obj)).uri != null : !uri.equals(((JaxbLink) (obj)).uri))
/* 533*/                return false;
/* 536*/            if(params == ((JaxbLink) (obj)).params)
/* 537*/                return true;
/* 539*/            if(params == null)
/* 541*/                return ((JaxbLink) (obj)).params.isEmpty();
/* 543*/            if(((JaxbLink) (obj)).params == null)
/* 545*/                return params.isEmpty();
/* 548*/            else
/* 548*/                return params.equals(((JaxbLink) (obj)).params);
                }

                public int hashCode()
                {
/* 553*/            int i = uri == null ? 0 : uri.hashCode();
/* 554*/            return i = i * 31 + (params == null || params.isEmpty() ? 0 : params.hashCode());
                }

                private URI uri;
                private Map params;

                public JaxbLink()
                {
                }

                public JaxbLink(URI uri1)
                {
/* 470*/            uri = uri1;
                }

                public JaxbLink(URI uri1, Map map)
                {
/* 480*/            uri = uri1;
/* 481*/            params = map;
                }
    }

    public static interface Builder
    {

        public abstract Builder link(Link link1);

        public abstract Builder link(String s);

        public abstract Builder uri(URI uri1);

        public abstract Builder uri(String s);

        public abstract Builder baseUri(URI uri1);

        public abstract Builder baseUri(String s);

        public abstract Builder uriBuilder(UriBuilder uribuilder);

        public abstract Builder rel(String s);

        public abstract Builder title(String s);

        public abstract Builder type(String s);

        public abstract Builder param(String s, String s1);

        public transient abstract Link build(Object aobj[]);

        public transient abstract Link buildRelativized(URI uri1, Object aobj[]);
    }


            public Link()
            {
            }

            public abstract URI getUri();

            public abstract UriBuilder getUriBuilder();

            public abstract String getRel();

            public abstract List getRels();

            public abstract String getTitle();

            public abstract String getType();

            public abstract Map getParams();

            public abstract String toString();

            public static Link valueOf(String s)
            {
                Builder builder;
/* 172*/        (builder = RuntimeDelegate.getInstance().createLinkBuilder()).link(s);
/* 174*/        return builder.build(new Object[0]);
            }

            public static Builder fromUri(URI uri)
            {
                Builder builder;
/* 185*/        (builder = RuntimeDelegate.getInstance().createLinkBuilder()).uri(uri);
/* 187*/        return builder;
            }

            public static Builder fromUri(String s)
            {
                Builder builder;
/* 198*/        (builder = RuntimeDelegate.getInstance().createLinkBuilder()).uri(s);
/* 200*/        return builder;
            }

            public static Builder fromUriBuilder(UriBuilder uribuilder)
            {
                Builder builder;
/* 210*/        (builder = RuntimeDelegate.getInstance().createLinkBuilder()).uriBuilder(uribuilder);
/* 212*/        return builder;
            }

            public static Builder fromLink(Link link)
            {
                Builder builder;
/* 222*/        (builder = RuntimeDelegate.getInstance().createLinkBuilder()).link(link);
/* 224*/        return builder;
            }

            public static Builder fromPath(String s)
            {
/* 237*/        return fromUriBuilder(UriBuilder.fromPath(s));
            }

            public static Builder fromResource(Class class1)
            {
/* 257*/        return fromUriBuilder(UriBuilder.fromResource(class1));
            }

            public static Builder fromMethod(Class class1, String s)
            {
/* 279*/        return fromUriBuilder(UriBuilder.fromMethod(class1, s));
            }

            public static final String TITLE = "title";
            public static final String REL = "rel";
            public static final String TYPE = "type";
}
