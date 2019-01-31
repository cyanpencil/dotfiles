// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ClientProperties.java

package org.glassfish.jersey.client;

import java.util.Map;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public final class ClientProperties
{

            private ClientProperties()
            {
            }

            public static Object getValue(Map map, String s, Object obj)
            {
/* 425*/        return PropertiesHelper.getValue(map, s, obj, null);
            }

            public static Object getValue(Map map, String s, Object obj, Class class1)
            {
/* 443*/        return PropertiesHelper.getValue(map, s, obj, class1, null);
            }

            public static Object getValue(Map map, String s, Class class1)
            {
/* 460*/        return PropertiesHelper.getValue(map, s, class1, null);
            }

            public static final String FOLLOW_REDIRECTS = "jersey.config.client.followRedirects";
            public static final String READ_TIMEOUT = "jersey.config.client.readTimeout";
            public static final String CONNECT_TIMEOUT = "jersey.config.client.connectTimeout";
            public static final String CHUNKED_ENCODING_SIZE = "jersey.config.client.chunkedEncodingSize";
            public static final int DEFAULT_CHUNK_SIZE = 4096;
            public static final String ASYNC_THREADPOOL_SIZE = "jersey.config.client.async.threadPoolSize";
            public static final String USE_ENCODING = "jersey.config.client.useEncoding";
            public static final String FEATURE_AUTO_DISCOVERY_DISABLE = "jersey.config.client.disableAutoDiscovery";
            public static final String OUTBOUND_CONTENT_LENGTH_BUFFER = "jersey.config.client.contentLength.buffer";
            public static final String JSON_PROCESSING_FEATURE_DISABLE = "jersey.config.client.disableJsonProcessing";
            public static final String METAINF_SERVICES_LOOKUP_DISABLE = "jersey.config.client.disableMetainfServicesLookup";
            public static final String MOXY_JSON_FEATURE_DISABLE = "jersey.config.client.disableMoxyJson";
            public static final String SUPPRESS_HTTP_COMPLIANCE_VALIDATION = "jersey.config.client.suppressHttpComplianceValidation";
            public static final String DIGESTAUTH_URI_CACHE_SIZELIMIT = "jersey.config.client.digestAuthUriCacheSizeLimit";
            public static final String PROXY_URI = "jersey.config.client.proxy.uri";
            public static final String PROXY_USERNAME = "jersey.config.client.proxy.username";
            public static final String PROXY_PASSWORD = "jersey.config.client.proxy.password";
            public static final String REQUEST_ENTITY_PROCESSING = "jersey.config.client.request.entity.processing";
}
