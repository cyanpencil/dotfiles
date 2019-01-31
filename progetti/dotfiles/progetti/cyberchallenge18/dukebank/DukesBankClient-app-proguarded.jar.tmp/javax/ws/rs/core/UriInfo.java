// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriInfo.java

package javax.ws.rs.core;

import java.net.URI;
import java.util.List;

// Referenced classes of package javax.ws.rs.core:
//            UriBuilder, MultivaluedMap

public interface UriInfo
{

    public abstract String getPath();

    public abstract String getPath(boolean flag);

    public abstract List getPathSegments();

    public abstract List getPathSegments(boolean flag);

    public abstract URI getRequestUri();

    public abstract UriBuilder getRequestUriBuilder();

    public abstract URI getAbsolutePath();

    public abstract UriBuilder getAbsolutePathBuilder();

    public abstract URI getBaseUri();

    public abstract UriBuilder getBaseUriBuilder();

    public abstract MultivaluedMap getPathParameters();

    public abstract MultivaluedMap getPathParameters(boolean flag);

    public abstract MultivaluedMap getQueryParameters();

    public abstract MultivaluedMap getQueryParameters(boolean flag);

    public abstract List getMatchedURIs();

    public abstract List getMatchedURIs(boolean flag);

    public abstract List getMatchedResources();

    public abstract URI resolve(URI uri);

    public abstract URI relativize(URI uri);
}
