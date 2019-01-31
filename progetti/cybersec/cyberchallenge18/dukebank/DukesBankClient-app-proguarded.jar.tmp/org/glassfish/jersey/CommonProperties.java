// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CommonProperties.java

package org.glassfish.jersey;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.RuntimeType;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public final class CommonProperties
{

            private CommonProperties()
            {
            }

            public static Object getValue(Map map, String s, Class class1)
            {
/* 263*/        return PropertiesHelper.getValue(map, s, class1, LEGACY_FALLBACK_MAP);
            }

            public static Object getValue(Map map, String s, Object obj)
            {
/* 282*/        return PropertiesHelper.getValue(map, s, obj, LEGACY_FALLBACK_MAP);
            }

            public static Object getValue(Map map, RuntimeType runtimetype, String s, Object obj)
            {
/* 307*/        return PropertiesHelper.getValue(map, runtimetype, s, obj, LEGACY_FALLBACK_MAP);
            }

            public static Object getValue(Map map, RuntimeType runtimetype, String s, Object obj, Class class1)
            {
/* 333*/        return PropertiesHelper.getValue(map, runtimetype, s, obj, class1, LEGACY_FALLBACK_MAP);
            }

            public static Object getValue(Map map, RuntimeType runtimetype, String s, Class class1)
            {
/* 358*/        return PropertiesHelper.getValue(map, runtimetype, s, class1, LEGACY_FALLBACK_MAP);
            }

            private static final Map LEGACY_FALLBACK_MAP;
            public static final String FEATURE_AUTO_DISCOVERY_DISABLE = "jersey.config.disableAutoDiscovery";
            public static final String FEATURE_AUTO_DISCOVERY_DISABLE_CLIENT = "jersey.config.client.disableAutoDiscovery";
            public static final String FEATURE_AUTO_DISCOVERY_DISABLE_SERVER = "jersey.config.server.disableAutoDiscovery";
            public static final String JSON_PROCESSING_FEATURE_DISABLE = "jersey.config.disableJsonProcessing";
            public static final String JSON_PROCESSING_FEATURE_DISABLE_CLIENT = "jersey.config.client.disableJsonProcessing";
            public static final String JSON_PROCESSING_FEATURE_DISABLE_SERVER = "jersey.config.server.disableJsonProcessing";
            public static final String METAINF_SERVICES_LOOKUP_DISABLE = "jersey.config.disableMetainfServicesLookup";
            public static final String METAINF_SERVICES_LOOKUP_DISABLE_CLIENT = "jersey.config.client.disableMetainfServicesLookup";
            public static final String METAINF_SERVICES_LOOKUP_DISABLE_SERVER = "jersey.config.server.disableMetainfServicesLookup";
            public static final String MOXY_JSON_FEATURE_DISABLE = "jersey.config.disableMoxyJson";
            public static final String MOXY_JSON_FEATURE_DISABLE_CLIENT = "jersey.config.client.disableMoxyJson";
            public static final String MOXY_JSON_FEATURE_DISABLE_SERVER = "jersey.config.server.disableMoxyJson";
            public static final String OUTBOUND_CONTENT_LENGTH_BUFFER = "jersey.config.contentLength.buffer";
            public static final String OUTBOUND_CONTENT_LENGTH_BUFFER_CLIENT = "jersey.config.client.contentLength.buffer";
            public static final String OUTBOUND_CONTENT_LENGTH_BUFFER_SERVER = "jersey.config.server.contentLength.buffer";

            static 
            {
/*  60*/        (LEGACY_FALLBACK_MAP = new HashMap()).put("jersey.config.client.contentLength.buffer", "jersey.config.contentLength.buffer.client");
/*  65*/        LEGACY_FALLBACK_MAP.put("jersey.config.server.contentLength.buffer", "jersey.config.contentLength.buffer.server");
/*  67*/        LEGACY_FALLBACK_MAP.put("jersey.config.client.disableAutoDiscovery", "jersey.config.disableAutoDiscovery.client");
/*  69*/        LEGACY_FALLBACK_MAP.put("jersey.config.server.disableAutoDiscovery", "jersey.config.disableAutoDiscovery.server");
/*  71*/        LEGACY_FALLBACK_MAP.put("jersey.config.client.disableJsonProcessing", "jersey.config.disableJsonProcessing.client");
/*  73*/        LEGACY_FALLBACK_MAP.put("jersey.config.server.disableJsonProcessing", "jersey.config.disableJsonProcessing.server");
/*  75*/        LEGACY_FALLBACK_MAP.put("jersey.config.client.disableMetainfServicesLookup", "jersey.config.disableMetainfServicesLookup.client");
/*  77*/        LEGACY_FALLBACK_MAP.put("jersey.config.server.disableMetainfServicesLookup", "jersey.config.disableMetainfServicesLookup.server");
/*  79*/        LEGACY_FALLBACK_MAP.put("jersey.config.client.disableMoxyJson", "jersey.config.disableMoxyJson.client");
/*  81*/        LEGACY_FALLBACK_MAP.put("jersey.config.server.disableMoxyJson", "jersey.config.disableMoxyJson.server");
            }
}
