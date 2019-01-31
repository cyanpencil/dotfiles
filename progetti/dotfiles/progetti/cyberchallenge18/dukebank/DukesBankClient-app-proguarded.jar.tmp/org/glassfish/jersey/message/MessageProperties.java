// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MessageProperties.java

package org.glassfish.jersey.message;


public final class MessageProperties
{

            private MessageProperties()
            {
            }

            public static final String JAXB_PROCESS_XML_ROOT_ELEMENT = "jersey.config.jaxb.collections.processXmlRootElement";
            public static final String XML_SECURITY_DISABLE = "jersey.config.xml.security.disable";
            public static final String XML_FORMAT_OUTPUT = "jersey.config.xml.formatOutput";
            public static final String IO_BUFFER_SIZE = "jersey.config.io.bufferSize";
            public static final int IO_DEFAULT_BUFFER_SIZE = 8192;
            public static final String DEFLATE_WITHOUT_ZLIB = "jersey.config.deflate.nozlib";
            public static final String LEGACY_WORKERS_ORDERING = "jersey.config.workers.legacyOrdering";
}
