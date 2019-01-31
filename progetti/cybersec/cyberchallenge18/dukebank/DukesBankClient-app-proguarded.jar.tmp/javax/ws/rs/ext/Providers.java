// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Providers.java

package javax.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.core.MediaType;

// Referenced classes of package javax.ws.rs.ext:
//            MessageBodyReader, MessageBodyWriter, ExceptionMapper, ContextResolver

public interface Providers
{

    public abstract MessageBodyReader getMessageBodyReader(Class class1, Type type, Annotation aannotation[], MediaType mediatype);

    public abstract MessageBodyWriter getMessageBodyWriter(Class class1, Type type, Annotation aannotation[], MediaType mediatype);

    public abstract ExceptionMapper getExceptionMapper(Class class1);

    public abstract ContextResolver getContextResolver(Class class1, MediaType mediatype);
}
