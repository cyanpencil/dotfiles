// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BasicTypesMessageProvider.java

package org.glassfish.jersey.message.internal;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.ReflectionHelper;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            AbstractMessageReaderWriterProvider, MessageBodyProcessingException

final class BasicTypesMessageProvider extends AbstractMessageReaderWriterProvider
{
    static abstract class PrimitiveTypes extends Enum
    {

                public static PrimitiveTypes[] values()
                {
/*  80*/            return (PrimitiveTypes[])$VALUES.clone();
                }

                public static PrimitiveTypes valueOf(String s)
                {
/*  80*/            return (PrimitiveTypes)Enum.valueOf(org/glassfish/jersey/message/internal/BasicTypesMessageProvider$PrimitiveTypes, s);
                }

                public static PrimitiveTypes forType(Class class1)
                {
                    PrimitiveTypes aprimitivetypes[];
/* 135*/            int i = (aprimitivetypes = values()).length;
/* 135*/            for(int j = 0; j < i; j++)
                    {
                        PrimitiveTypes primitivetypes;
/* 135*/                if((primitivetypes = aprimitivetypes[j]).supports(class1))
/* 137*/                    return primitivetypes;
                    }

/* 140*/            return null;
                }

                public abstract Object convert(String s);

                public boolean supports(Class class1)
                {
/* 154*/            return class1 == wrapper || class1 == primitive;
                }

                public static final PrimitiveTypes BYTE;
                public static final PrimitiveTypes SHORT;
                public static final PrimitiveTypes INTEGER;
                public static final PrimitiveTypes LONG;
                public static final PrimitiveTypes FLOAT;
                public static final PrimitiveTypes DOUBLE;
                public static final PrimitiveTypes BOOLEAN;
                public static final PrimitiveTypes CHAR;
                private final Class wrapper;
                private final Class primitive;
                private static final PrimitiveTypes $VALUES[];

                static 
                {
/*  81*/            BYTE = new PrimitiveTypes("BYTE", 0, java/lang/Byte, Byte.TYPE) {

                        public final Object convert(String s)
                        {
/*  84*/                    return Byte.valueOf(s);
                        }

            };
/*  87*/            SHORT = new PrimitiveTypes("SHORT", 1, java/lang/Short, Short.TYPE) {

                        public final Object convert(String s)
                        {
/*  90*/                    return Short.valueOf(s);
                        }

            };
/*  93*/            INTEGER = new PrimitiveTypes("INTEGER", 2, java/lang/Integer, Integer.TYPE) {

                        public final Object convert(String s)
                        {
/*  96*/                    return Integer.valueOf(s);
                        }

            };
/*  99*/            LONG = new PrimitiveTypes("LONG", 3, java/lang/Long, Long.TYPE) {

                        public final Object convert(String s)
                        {
/* 102*/                    return Long.valueOf(s);
                        }

            };
/* 105*/            FLOAT = new PrimitiveTypes("FLOAT", 4, java/lang/Float, Float.TYPE) {

                        public final Object convert(String s)
                        {
/* 108*/                    return Float.valueOf(s);
                        }

            };
/* 111*/            DOUBLE = new PrimitiveTypes("DOUBLE", 5, java/lang/Double, Double.TYPE) {

                        public final Object convert(String s)
                        {
/* 114*/                    return Double.valueOf(s);
                        }

            };
/* 117*/            BOOLEAN = new PrimitiveTypes("BOOLEAN", 6, java/lang/Boolean, Boolean.TYPE) {

                        public final Object convert(String s)
                        {
/* 120*/                    return Boolean.valueOf(s);
                        }

            };
/* 123*/            CHAR = new PrimitiveTypes("CHAR", 7, java/lang/Character, Character.TYPE) {

                        public final Object convert(String s)
                        {
/* 126*/                    if(s.length() != 1)
/* 127*/                        throw new MessageBodyProcessingException(LocalizationMessages.ERROR_ENTITY_PROVIDER_BASICTYPES_CHARACTER_MORECHARS());
/* 130*/                    else
/* 130*/                        return Character.valueOf(s.charAt(0));
                        }

            };
/*  80*/            $VALUES = (new PrimitiveTypes[] {
/*  80*/                BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR
                    });
                }

                private PrimitiveTypes(String s, int i, Class class1, Class class2)
                {
/* 146*/            super(s, i);
/* 147*/            wrapper = class1;
/* 148*/            primitive = class2;
                }

    }


            BasicTypesMessageProvider()
            {
            }

            public final boolean isReadable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 160*/        return canProcess(class1);
            }

            public final Object readFrom(Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, InputStream inputstream)
                throws IOException, WebApplicationException
            {
/* 171*/        if((type = readFromAsString(inputstream, mediatype)).isEmpty())
/* 173*/            throw new NoContentException(LocalizationMessages.ERROR_READING_ENTITY_MISSING());
/* 175*/        if((aannotation = PrimitiveTypes.forType(class1)) != null)
/* 177*/            return aannotation.convert(type);
/* 180*/        if((aannotation = (Constructor)AccessController.doPrivileged(ReflectionHelper.getStringConstructorPA(class1))) == null)
/* 183*/            break MISSING_BLOCK_LABEL_86;
/* 183*/        return class1.cast(aannotation.newInstance(new Object[] {
/* 183*/            type
                }));
/* 184*/        JVM INSTR pop ;
/* 185*/        throw new MessageBodyProcessingException(LocalizationMessages.ERROR_ENTITY_PROVIDER_BASICTYPES_CONSTRUCTOR(class1));
/* 189*/        if(java/util/concurrent/atomic/AtomicInteger.isAssignableFrom(class1))
/* 190*/            return new AtomicInteger(((Integer)PrimitiveTypes.INTEGER.convert(type)).intValue());
/* 193*/        if(java/util/concurrent/atomic/AtomicLong.isAssignableFrom(class1))
/* 194*/            return new AtomicLong(((Long)PrimitiveTypes.LONG.convert(type)).longValue());
/* 197*/        else
/* 197*/            throw new MessageBodyProcessingException(LocalizationMessages.ERROR_ENTITY_PROVIDER_BASICTYPES_UNKWNOWN(class1));
            }

            public final boolean isWriteable(Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 202*/        return canProcess(class1);
            }

            private boolean canProcess(Class class1)
            {
/* 207*/        if(PrimitiveTypes.forType(class1) != null)
/* 208*/            return true;
/* 210*/        if(java/lang/Number.isAssignableFrom(class1))
                {
                    Constructor constructor;
/* 211*/            if((constructor = (Constructor)AccessController.doPrivileged(ReflectionHelper.getStringConstructorPA(class1))) != null)
/* 213*/                return true;
/* 215*/            if(java/util/concurrent/atomic/AtomicInteger.isAssignableFrom(class1) || java/util/concurrent/atomic/AtomicLong.isAssignableFrom(class1))
/* 216*/                return true;
                }
/* 220*/        return false;
            }

            public final long getSize(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype)
            {
/* 225*/        return (long)obj.toString().length();
            }

            public final void writeTo(Object obj, Class class1, Type type, Annotation aannotation[], MediaType mediatype, MultivaluedMap multivaluedmap, OutputStream outputstream)
                throws IOException, WebApplicationException
            {
/* 237*/        writeToAsString(obj.toString(), outputstream, mediatype);
            }
}
