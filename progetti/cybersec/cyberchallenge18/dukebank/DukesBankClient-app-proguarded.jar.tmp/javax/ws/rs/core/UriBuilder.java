// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriBuilder.java

package javax.ws.rs.core;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs.core:
//            Link, UriBuilderException

public abstract class UriBuilder
{

            protected UriBuilder()
            {
            }

            protected static UriBuilder newInstance()
            {
/*  95*/        return RuntimeDelegate.getInstance().createUriBuilder();
            }

            public static UriBuilder fromUri(URI uri1)
            {
/* 106*/        return newInstance().uri(uri1);
            }

            public static UriBuilder fromUri(String s)
            {
/* 119*/        return newInstance().uri(s);
            }

            public static UriBuilder fromLink(Link link)
            {
/* 132*/        if(link == null)
/* 133*/            throw new IllegalArgumentException("The provider 'link' parameter value is 'null'.");
/* 135*/        else
/* 135*/            return fromUri(link.getUri());
            }

            public static UriBuilder fromPath(String s)
                throws IllegalArgumentException
            {
/* 148*/        return newInstance().path(s);
            }

            public static UriBuilder fromResource(Class class1)
            {
/* 162*/        return newInstance().path(class1);
            }

            public static UriBuilder fromMethod(Class class1, String s)
            {
/* 182*/        return newInstance().path(class1, s);
            }

            public abstract UriBuilder clone();

            public abstract UriBuilder uri(URI uri1);

            public abstract UriBuilder uri(String s);

            public abstract UriBuilder scheme(String s);

            public abstract UriBuilder schemeSpecificPart(String s);

            public abstract UriBuilder userInfo(String s);

            public abstract UriBuilder host(String s);

            public abstract UriBuilder port(int i);

            public abstract UriBuilder replacePath(String s);

            public abstract UriBuilder path(String s);

            public abstract UriBuilder path(Class class1);

            public abstract UriBuilder path(Class class1, String s);

            public abstract UriBuilder path(Method method);

            public transient abstract UriBuilder segment(String as[]);

            public abstract UriBuilder replaceMatrix(String s);

            public transient abstract UriBuilder matrixParam(String s, Object aobj[]);

            public transient abstract UriBuilder replaceMatrixParam(String s, Object aobj[]);

            public abstract UriBuilder replaceQuery(String s);

            public transient abstract UriBuilder queryParam(String s, Object aobj[]);

            public transient abstract UriBuilder replaceQueryParam(String s, Object aobj[]);

            public abstract UriBuilder fragment(String s);

            public abstract UriBuilder resolveTemplate(String s, Object obj);

            public abstract UriBuilder resolveTemplate(String s, Object obj, boolean flag);

            public abstract UriBuilder resolveTemplateFromEncoded(String s, Object obj);

            public abstract UriBuilder resolveTemplates(Map map);

            public abstract UriBuilder resolveTemplates(Map map, boolean flag)
                throws IllegalArgumentException;

            public abstract UriBuilder resolveTemplatesFromEncoded(Map map);

            public abstract URI buildFromMap(Map map);

            public abstract URI buildFromMap(Map map, boolean flag)
                throws IllegalArgumentException, UriBuilderException;

            public abstract URI buildFromEncodedMap(Map map)
                throws IllegalArgumentException, UriBuilderException;

            public transient abstract URI build(Object aobj[])
                throws IllegalArgumentException, UriBuilderException;

            public abstract URI build(Object aobj[], boolean flag)
                throws IllegalArgumentException, UriBuilderException;

            public transient abstract URI buildFromEncoded(Object aobj[])
                throws IllegalArgumentException, UriBuilderException;

            public abstract String toTemplate();

            public volatile Object clone()
                throws CloneNotSupportedException
            {
/*  80*/        return clone();
            }
}
