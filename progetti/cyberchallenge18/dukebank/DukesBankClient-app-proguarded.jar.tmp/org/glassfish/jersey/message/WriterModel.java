// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   WriterModel.java

package org.glassfish.jersey.message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.message.internal.MessageBodyFactory;

// Referenced classes of package org.glassfish.jersey.message:
//            AbstractEntityProviderModel

public final class WriterModel extends AbstractEntityProviderModel
{

            public WriterModel(MessageBodyWriter messagebodywriter, List list, Boolean boolean1)
            {
/*  68*/        super(messagebodywriter, list, boolean1.booleanValue(), javax/ws/rs/ext/MessageBodyWriter);
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  86*/        return MessageBodyFactory.isWriteable((MessageBodyWriter)super.provider(), class1, type, aannotation, mediatype);
            }
}
