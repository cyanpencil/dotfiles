// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Link.java

package javax.ws.rs.core;

import java.net.URI;

// Referenced classes of package javax.ws.rs.core:
//            Link, UriBuilder

public static interface 
{

    public abstract  link(Link link1);

    public abstract  link(String s);

    public abstract  uri(URI uri1);

    public abstract  uri(String s);

    public abstract  baseUri(URI uri1);

    public abstract  baseUri(String s);

    public abstract  uriBuilder(UriBuilder uribuilder);

    public abstract  rel(String s);

    public abstract  title(String s);

    public abstract  type(String s);

    public abstract  param(String s, String s1);

    public transient abstract Link build(Object aobj[]);

    public transient abstract Link buildRelativized(URI uri1, Object aobj[]);
}
