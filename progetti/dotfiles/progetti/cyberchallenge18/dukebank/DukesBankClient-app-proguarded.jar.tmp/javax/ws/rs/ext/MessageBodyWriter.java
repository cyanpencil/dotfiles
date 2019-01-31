// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyWriter.java

package javax.ws.rs.ext;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public interface MessageBodyWriter
{

    public abstract boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype);

    public abstract long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype);

    public abstract void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
        throws IOException, WebApplicationException;
}
