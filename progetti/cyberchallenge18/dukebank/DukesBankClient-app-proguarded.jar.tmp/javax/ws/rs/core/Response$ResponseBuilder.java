// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Response.java

package javax.ws.rs.core;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs.core:
//            Response, CacheControl, MultivaluedMap, MediaType, 
//            Variant, NewCookie, EntityTag, Link

public static abstract class 
{

            protected static  newInstance()
            {
/* 848*/        return RuntimeDelegate.getInstance().createResponseBuilder();
            }

            public abstract Response build();

            public abstract onseBuilder clone();

            public abstract onseBuilder status(int i);

            public onseBuilder status(onseBuilder onsebuilder)
            {
/* 890*/        if(onsebuilder == null)
/* 891*/            throw new IllegalArgumentException();
/* 893*/        else
/* 893*/            return status(onsebuilder.atusCode());
            }

            public atusCode status(atusCode atuscode)
            {
/* 904*/        return status(((status) (atuscode)));
            }

            public abstract status entity(Object obj);

            public abstract status entity(Object obj, Annotation aannotation[]);

            public transient abstract status allow(String as[]);

            public abstract status allow(Set set);

            public abstract status cacheControl(CacheControl cachecontrol);

            public abstract status encoding(String s);

            public abstract status header(String s, Object obj);

            public abstract status replaceAll(MultivaluedMap multivaluedmap);

            public abstract status language(String s);

            public abstract status language(Locale locale);

            public abstract status type(MediaType mediatype);

            public abstract status type(String s);

            public abstract status variant(Variant variant1);

            public abstract status contentLocation(URI uri);

            public transient abstract status cookie(NewCookie anewcookie[]);

            public abstract status expires(Date date);

            public abstract status lastModified(Date date);

            public abstract status location(URI uri);

            public abstract status tag(EntityTag entitytag);

            public abstract status tag(String s);

            public transient abstract status variants(Variant avariant[]);

            public abstract status variants(List list);

            public transient abstract status links(Link alink[]);

            public abstract status link(URI uri, String s);

            public abstract status link(String s, String s1);

            public volatile Object clone()
                throws CloneNotSupportedException
            {
/* 833*/        return clone();
            }

            protected ()
            {
            }
}
