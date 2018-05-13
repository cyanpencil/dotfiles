// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageBodyWorkers.java

package org.glassfish.jersey.message;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.internal.PropertiesDelegate;

public interface MessageBodyWorkers
{

    public abstract Map getReaders(MediaType mediatype);

    public abstract Map getWriters(MediaType mediatype);

    public abstract String readersToString(Map map);

    public abstract String writersToString(Map map);

    public abstract MessageBodyReader getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype);

    public abstract MessageBodyReader getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype, PropertiesDelegate propertiesdelegate);

    public abstract MessageBodyWriter getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype);

    public abstract MessageBodyWriter getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype, PropertiesDelegate propertiesdelegate);

    public abstract List getMessageBodyReaderMediaTypes(Class class1, Type type, Annotation aannotation[]);

    public abstract List getMessageBodyReaderMediaTypesByType(Class class1);

    public abstract List getMessageBodyReadersForType(Class class1);

    public abstract List getReaderModelsForType(Class class1);

    public abstract List getMessageBodyWriterMediaTypes(Class class1, Type type, Annotation aannotation[]);

    public abstract List getMessageBodyWriterMediaTypesByType(Class class1);

    public abstract List getMessageBodyWritersForType(Class class1);

    public abstract List getWritersModelsForType(Class class1);

    public abstract MediaType getMessageBodyWriterMediaType(Class class1, Type type, Annotation aannotation[], List list);

    public abstract Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, PropertiesDelegate propertiesdelegate, InputStream inputstream, 
            Iterable iterable, boolean flag)
        throws WebApplicationException, IOException;

    public abstract OutputStream writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, PropertiesDelegate propertiesdelegate, 
            OutputStream outputstream, Iterable iterable)
        throws IOException, WebApplicationException;
}
