// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ContainerRequestContext.java

package javax.ws.rs.container;

import java.io.InputStream;
import java.net.URI;
import java.util.*;
import javax.ws.rs.core.*;

public interface ContainerRequestContext
{

    public abstract Object getProperty(String s);

    public abstract Collection getPropertyNames();

    public abstract void setProperty(String s, Object obj);

    public abstract void removeProperty(String s);

    public abstract UriInfo getUriInfo();

    public abstract void setRequestUri(URI uri);

    public abstract void setRequestUri(URI uri, URI uri1);

    public abstract Request getRequest();

    public abstract String getMethod();

    public abstract void setMethod(String s);

    public abstract MultivaluedMap getHeaders();

    public abstract String getHeaderString(String s);

    public abstract Date getDate();

    public abstract Locale getLanguage();

    public abstract int getLength();

    public abstract MediaType getMediaType();

    public abstract List getAcceptableMediaTypes();

    public abstract List getAcceptableLanguages();

    public abstract Map getCookies();

    public abstract boolean hasEntity();

    public abstract InputStream getEntityStream();

    public abstract void setEntityStream(InputStream inputstream);

    public abstract SecurityContext getSecurityContext();

    public abstract void setSecurityContext(SecurityContext securitycontext);

    public abstract void abortWith(Response response);
}
