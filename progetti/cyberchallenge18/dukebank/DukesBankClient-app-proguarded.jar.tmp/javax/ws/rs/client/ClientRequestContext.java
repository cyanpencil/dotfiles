// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientRequestContext.java

package javax.ws.rs.client;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.*;
import javax.ws.rs.core.*;

// Referenced classes of package javax.ws.rs.client:
//            Client

public interface ClientRequestContext
{

    public abstract Object getProperty(String s);

    public abstract Collection getPropertyNames();

    public abstract void setProperty(String s, Object obj);

    public abstract void removeProperty(String s);

    public abstract URI getUri();

    public abstract void setUri(URI uri);

    public abstract String getMethod();

    public abstract void setMethod(String s);

    public abstract MultivaluedMap getHeaders();

    public abstract MultivaluedMap getStringHeaders();

    public abstract String getHeaderString(String s);

    public abstract Date getDate();

    public abstract Locale getLanguage();

    public abstract MediaType getMediaType();

    public abstract List getAcceptableMediaTypes();

    public abstract List getAcceptableLanguages();

    public abstract Map getCookies();

    public abstract boolean hasEntity();

    public abstract Object getEntity();

    public abstract Class getEntityClass();

    public abstract Type getEntityType();

    public abstract void setEntity(Object obj);

    public abstract void setEntity(Object obj, Annotation aannotation[], MediaType mediatype);

    public abstract Annotation[] getEntityAnnotations();

    public abstract OutputStream getEntityStream();

    public abstract void setEntityStream(OutputStream outputstream);

    public abstract Client getClient();

    public abstract Configuration getConfiguration();

    public abstract void abortWith(Response response);
}
