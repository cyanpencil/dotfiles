// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MediaType.java

package javax.ws.rs.core;

import java.util.*;
import javax.ws.rs.ext.RuntimeDelegate;

public class MediaType
{

            public static MediaType valueOf(String s)
            {
/* 179*/        return (MediaType)RuntimeDelegate.getInstance().createHeaderDelegate(javax/ws/rs/core/MediaType).fromString(s);
            }

            private static TreeMap createParametersMap(Map map)
            {
/* 183*/        TreeMap treemap = new TreeMap(new Comparator() {

                    public final int compare(String s, String s1)
                    {
/* 187*/                return s.compareToIgnoreCase(s1);
                    }

                    public final volatile int compare(Object obj, Object obj1)
                    {
/* 183*/                return compare((String)obj, (String)obj1);
                    }

        });
/* 190*/        if(map != null)
                {
                    java.util.Map.Entry entry;
/* 191*/            for(map = map.entrySet().iterator(); map.hasNext(); treemap.put(((String)entry.getKey()).toLowerCase(), entry.getValue()))
/* 191*/                entry = (java.util.Map.Entry)map.next();

                }
/* 195*/        return treemap;
            }

            public MediaType(String s, String s1, Map map)
            {
/* 210*/        this(s, s1, null, ((Map) (createParametersMap(map))));
            }

            public MediaType(String s, String s1)
            {
/* 222*/        this(s, s1, null, null);
            }

            public MediaType(String s, String s1, String s2)
            {
/* 237*/        this(s, s1, s2, null);
            }

            public MediaType()
            {
/* 245*/        this("*", "*", null, null);
            }

            private MediaType(String s, String s1, String s2, Map map)
            {
/* 250*/        type = s != null ? s : "*";
/* 251*/        subtype = s1 != null ? s1 : "*";
/* 253*/        if(map == null)
/* 254*/            map = new TreeMap(new Comparator() {

                        public int compare(String s3, String s4)
                        {
/* 258*/                    return s3.compareToIgnoreCase(s4);
                        }

                        public volatile int compare(Object obj, Object obj1)
                        {
/* 254*/                    return compare((String)obj, (String)obj1);
                        }

                        final MediaType this$0;

                    
                    {
/* 254*/                this$0 = MediaType.this;
/* 254*/                super();
                    }
            });
/* 263*/        if(s2 != null && !s2.isEmpty())
/* 264*/            map.put("charset", s2);
/* 266*/        parameters = Collections.unmodifiableMap(map);
            }

            public String getType()
            {
/* 275*/        return type;
            }

            public boolean isWildcardType()
            {
/* 284*/        return getType().equals("*");
            }

            public String getSubtype()
            {
/* 293*/        return subtype;
            }

            public boolean isWildcardSubtype()
            {
/* 302*/        return getSubtype().equals("*");
            }

            public Map getParameters()
            {
/* 311*/        return parameters;
            }

            public MediaType withCharset(String s)
            {
/* 325*/        return new MediaType(type, subtype, s, createParametersMap(parameters));
            }

            public boolean isCompatible(MediaType mediatype)
            {
/* 337*/        return mediatype != null && (type.equals("*") || mediatype.type.equals("*") || type.equalsIgnoreCase(mediatype.type) && (subtype.equals("*") || mediatype.subtype.equals("*")) || type.equalsIgnoreCase(mediatype.type) && subtype.equalsIgnoreCase(mediatype.subtype));
            }

            public boolean equals(Object obj)
            {
/* 365*/        if(!(obj instanceof MediaType))
/* 366*/            return false;
/* 369*/        obj = (MediaType)obj;
/* 370*/        return type.equalsIgnoreCase(((MediaType) (obj)).type) && subtype.equalsIgnoreCase(((MediaType) (obj)).subtype) && parameters.equals(((MediaType) (obj)).parameters);
            }

            public int hashCode()
            {
/* 391*/        return (new StringBuilder()).append(type.toLowerCase()).append(subtype.toLowerCase()).toString().hashCode() + parameters.hashCode();
            }

            public String toString()
            {
/* 402*/        return RuntimeDelegate.getInstance().createHeaderDelegate(javax/ws/rs/core/MediaType).toString(this);
            }

            private String type;
            private String subtype;
            private Map parameters;
            public static final String CHARSET_PARAMETER = "charset";
            public static final String MEDIA_TYPE_WILDCARD = "*";
            public static final String WILDCARD = "*/*";
            public static final MediaType WILDCARD_TYPE = new MediaType();
            public static final String APPLICATION_XML = "application/xml";
            public static final MediaType APPLICATION_XML_TYPE = new MediaType("application", "xml");
            public static final String APPLICATION_ATOM_XML = "application/atom+xml";
            public static final MediaType APPLICATION_ATOM_XML_TYPE = new MediaType("application", "atom+xml");
            public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
            public static final MediaType APPLICATION_XHTML_XML_TYPE = new MediaType("application", "xhtml+xml");
            public static final String APPLICATION_SVG_XML = "application/svg+xml";
            public static final MediaType APPLICATION_SVG_XML_TYPE = new MediaType("application", "svg+xml");
            public static final String APPLICATION_JSON = "application/json";
            public static final MediaType APPLICATION_JSON_TYPE = new MediaType("application", "json");
            public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
            public static final MediaType APPLICATION_FORM_URLENCODED_TYPE = new MediaType("application", "x-www-form-urlencoded");
            public static final String MULTIPART_FORM_DATA = "multipart/form-data";
            public static final MediaType MULTIPART_FORM_DATA_TYPE = new MediaType("multipart", "form-data");
            public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
            public static final MediaType APPLICATION_OCTET_STREAM_TYPE = new MediaType("application", "octet-stream");
            public static final String TEXT_PLAIN = "text/plain";
            public static final MediaType TEXT_PLAIN_TYPE = new MediaType("text", "plain");
            public static final String TEXT_XML = "text/xml";
            public static final MediaType TEXT_XML_TYPE = new MediaType("text", "xml");
            public static final String TEXT_HTML = "text/html";
            public static final MediaType TEXT_HTML_TYPE = new MediaType("text", "html");

}
