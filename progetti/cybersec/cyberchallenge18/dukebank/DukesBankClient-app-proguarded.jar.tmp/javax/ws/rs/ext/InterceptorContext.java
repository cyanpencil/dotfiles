// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InterceptorContext.java

package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.ws.rs.core.MediaType;

public interface InterceptorContext
{

    public abstract Object getProperty(String s);

    public abstract Collection getPropertyNames();

    public abstract void setProperty(String s, Object obj);

    public abstract void removeProperty(String s);

    public abstract Annotation[] getAnnotations();

    public abstract void setAnnotations(Annotation aannotation[]);

    public abstract Class getType();

    public abstract void setType(Class class1);

    public abstract Type getGenericType();

    public abstract void setGenericType(Type type);

    public abstract MediaType getMediaType();

    public abstract void setMediaType(MediaType mediatype);
}
