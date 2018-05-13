// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientResponseContext.java

package javax.ws.rs.client;

import java.io.InputStream;
import java.net.URI;
import java.util.*;
import javax.ws.rs.core.*;

public interface ClientResponseContext
{

    public abstract int getStatus();

    public abstract void setStatus(int i);

    public abstract javax.ws.rs.core.Response.StatusType getStatusInfo();

    public abstract void setStatusInfo(javax.ws.rs.core.Response.StatusType statustype);

    public abstract MultivaluedMap getHeaders();

    public abstract String getHeaderString(String s);

    public abstract Set getAllowedMethods();

    public abstract Date getDate();

    public abstract Locale getLanguage();

    public abstract int getLength();

    public abstract MediaType getMediaType();

    public abstract Map getCookies();

    public abstract EntityTag getEntityTag();

    public abstract Date getLastModified();

    public abstract URI getLocation();

    public abstract Set getLinks();

    public abstract boolean hasLink(String s);

    public abstract Link getLink(String s);

    public abstract javax.ws.rs.core.Link.Builder getLinkBuilder(String s);

    public abstract boolean hasEntity();

    public abstract InputStream getEntityStream();

    public abstract void setEntityStream(InputStream inputstream);
}
