// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ReaderModel.java

package org.glassfish.jersey.message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import org.glassfish.jersey.message.internal.MessageBodyFactory;

// Referenced classes of package org.glassfish.jersey.message:
//            AbstractEntityProviderModel

public final class ReaderModel extends AbstractEntityProviderModel
{

            public ReaderModel(MessageBodyReader messagebodyreader, List list, Boolean boolean1)
            {
/*  69*/        super(messagebodyreader, list, boolean1.booleanValue(), javax/ws/rs/ext/MessageBodyReader);
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/*  93*/        return MessageBodyFactory.isReadable((MessageBodyReader)super.provider(), class1, type, aannotation, mediatype);
            }
}
