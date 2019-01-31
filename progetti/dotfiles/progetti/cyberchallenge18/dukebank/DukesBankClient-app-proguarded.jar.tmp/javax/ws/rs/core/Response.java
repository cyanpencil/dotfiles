// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Response.java

package javax.ws.rs.core;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;
import javax.ws.rs.ext.RuntimeDelegate;

// Referenced classes of package javax.ws.rs.core:
//            Link, MultivaluedMap, GenericType, MediaType, 
//            EntityTag, Variant, CacheControl, NewCookie

public abstract class Response
{
    public static final class Status extends Enum
        implements StatusType
    {
        public static final class Family extends Enum
        {

                    public static Family[] values()
                    {
/*1412*/                return (Family[])$VALUES.clone();
                    }

                    public static Family valueOf(String s)
                    {
/*1412*/                return (Family)Enum.valueOf(javax/ws/rs/core/Response$Status$Family, s);
                    }

                    public static Family familyOf(int i)
                    {
/*1446*/                switch(i / 100)
                        {
/*1448*/                case 1: // '\001'
/*1448*/                    return INFORMATIONAL;

/*1450*/                case 2: // '\002'
/*1450*/                    return SUCCESSFUL;

/*1452*/                case 3: // '\003'
/*1452*/                    return REDIRECTION;

/*1454*/                case 4: // '\004'
/*1454*/                    return CLIENT_ERROR;

/*1456*/                case 5: // '\005'
/*1456*/                    return SERVER_ERROR;
                        }
/*1458*/                return OTHER;
                    }

                    public static final Family INFORMATIONAL;
                    public static final Family SUCCESSFUL;
                    public static final Family REDIRECTION;
                    public static final Family CLIENT_ERROR;
                    public static final Family SERVER_ERROR;
                    public static final Family OTHER;
                    private static final Family $VALUES[];

                    static 
                    {
/*1417*/                INFORMATIONAL = new Family("INFORMATIONAL", 0);
/*1421*/                SUCCESSFUL = new Family("SUCCESSFUL", 1);
/*1425*/                REDIRECTION = new Family("REDIRECTION", 2);
/*1429*/                CLIENT_ERROR = new Family("CLIENT_ERROR", 3);
/*1433*/                SERVER_ERROR = new Family("SERVER_ERROR", 4);
/*1437*/                OTHER = new Family("OTHER", 5);
/*1412*/                $VALUES = (new Family[] {
/*1412*/                    INFORMATIONAL, SUCCESSFUL, REDIRECTION, CLIENT_ERROR, SERVER_ERROR, OTHER
                        });
                    }

                    private Family(String s, int i)
                    {
/*1412*/                super(s, i);
                    }
        }


                public static Status[] values()
                {
/*1224*/            return (Status[])$VALUES.clone();
                }

                public static Status valueOf(String s)
                {
/*1224*/            return (Status)Enum.valueOf(javax/ws/rs/core/Response$Status, s);
                }

                public final Family getFamily()
                {
/*1476*/            return family;
                }

                public final int getStatusCode()
                {
/*1486*/            return code;
                }

                public final String getReasonPhrase()
                {
/*1496*/            return toString();
                }

                public final String toString()
                {
/*1506*/            return reason;
                }

                public static Status fromStatusCode(int i)
                {
                    Status astatus[];
/*1516*/            int j = (astatus = values()).length;
/*1516*/            for(int k = 0; k < j; k++)
                    {
                        Status status1;
/*1516*/                if((status1 = astatus[k]).code == i)
/*1518*/                    return status1;
                    }

/*1521*/            return null;
                }

                public static final Status OK;
                public static final Status CREATED;
                public static final Status ACCEPTED;
                public static final Status NO_CONTENT;
                public static final Status RESET_CONTENT;
                public static final Status PARTIAL_CONTENT;
                public static final Status MOVED_PERMANENTLY;
                public static final Status FOUND;
                public static final Status SEE_OTHER;
                public static final Status NOT_MODIFIED;
                public static final Status USE_PROXY;
                public static final Status TEMPORARY_REDIRECT;
                public static final Status BAD_REQUEST;
                public static final Status UNAUTHORIZED;
                public static final Status PAYMENT_REQUIRED;
                public static final Status FORBIDDEN;
                public static final Status NOT_FOUND;
                public static final Status METHOD_NOT_ALLOWED;
                public static final Status NOT_ACCEPTABLE;
                public static final Status PROXY_AUTHENTICATION_REQUIRED;
                public static final Status REQUEST_TIMEOUT;
                public static final Status CONFLICT;
                public static final Status GONE;
                public static final Status LENGTH_REQUIRED;
                public static final Status PRECONDITION_FAILED;
                public static final Status REQUEST_ENTITY_TOO_LARGE;
                public static final Status REQUEST_URI_TOO_LONG;
                public static final Status UNSUPPORTED_MEDIA_TYPE;
                public static final Status REQUESTED_RANGE_NOT_SATISFIABLE;
                public static final Status EXPECTATION_FAILED;
                public static final Status INTERNAL_SERVER_ERROR;
                public static final Status NOT_IMPLEMENTED;
                public static final Status BAD_GATEWAY;
                public static final Status SERVICE_UNAVAILABLE;
                public static final Status GATEWAY_TIMEOUT;
                public static final Status HTTP_VERSION_NOT_SUPPORTED;
                private final int code;
                private final String reason;
                private final Family family;
                private static final Status $VALUES[];

                static 
                {
/*1229*/            OK = new Status("OK", 0, 200, "OK");
/*1233*/            CREATED = new Status("CREATED", 1, 201, "Created");
/*1237*/            ACCEPTED = new Status("ACCEPTED", 2, 202, "Accepted");
/*1241*/            NO_CONTENT = new Status("NO_CONTENT", 3, 204, "No Content");
/*1247*/            RESET_CONTENT = new Status("RESET_CONTENT", 4, 205, "Reset Content");
/*1253*/            PARTIAL_CONTENT = new Status("PARTIAL_CONTENT", 5, 206, "Partial Content");
/*1257*/            MOVED_PERMANENTLY = new Status("MOVED_PERMANENTLY", 6, 301, "Moved Permanently");
/*1263*/            FOUND = new Status("FOUND", 7, 302, "Found");
/*1267*/            SEE_OTHER = new Status("SEE_OTHER", 8, 303, "See Other");
/*1271*/            NOT_MODIFIED = new Status("NOT_MODIFIED", 9, 304, "Not Modified");
/*1277*/            USE_PROXY = new Status("USE_PROXY", 10, 305, "Use Proxy");
/*1281*/            TEMPORARY_REDIRECT = new Status("TEMPORARY_REDIRECT", 11, 307, "Temporary Redirect");
/*1285*/            BAD_REQUEST = new Status("BAD_REQUEST", 12, 400, "Bad Request");
/*1289*/            UNAUTHORIZED = new Status("UNAUTHORIZED", 13, 401, "Unauthorized");
/*1295*/            PAYMENT_REQUIRED = new Status("PAYMENT_REQUIRED", 14, 402, "Payment Required");
/*1299*/            FORBIDDEN = new Status("FORBIDDEN", 15, 403, "Forbidden");
/*1303*/            NOT_FOUND = new Status("NOT_FOUND", 16, 404, "Not Found");
/*1309*/            METHOD_NOT_ALLOWED = new Status("METHOD_NOT_ALLOWED", 17, 405, "Method Not Allowed");
/*1313*/            NOT_ACCEPTABLE = new Status("NOT_ACCEPTABLE", 18, 406, "Not Acceptable");
/*1319*/            PROXY_AUTHENTICATION_REQUIRED = new Status("PROXY_AUTHENTICATION_REQUIRED", 19, 407, "Proxy Authentication Required");
/*1325*/            REQUEST_TIMEOUT = new Status("REQUEST_TIMEOUT", 20, 408, "Request Timeout");
/*1329*/            CONFLICT = new Status("CONFLICT", 21, 409, "Conflict");
/*1333*/            GONE = new Status("GONE", 22, 410, "Gone");
/*1339*/            LENGTH_REQUIRED = new Status("LENGTH_REQUIRED", 23, 411, "Length Required");
/*1343*/            PRECONDITION_FAILED = new Status("PRECONDITION_FAILED", 24, 412, "Precondition Failed");
/*1349*/            REQUEST_ENTITY_TOO_LARGE = new Status("REQUEST_ENTITY_TOO_LARGE", 25, 413, "Request Entity Too Large");
/*1355*/            REQUEST_URI_TOO_LONG = new Status("REQUEST_URI_TOO_LONG", 26, 414, "Request-URI Too Long");
/*1359*/            UNSUPPORTED_MEDIA_TYPE = new Status("UNSUPPORTED_MEDIA_TYPE", 27, 415, "Unsupported Media Type");
/*1365*/            REQUESTED_RANGE_NOT_SATISFIABLE = new Status("REQUESTED_RANGE_NOT_SATISFIABLE", 28, 416, "Requested Range Not Satisfiable");
/*1371*/            EXPECTATION_FAILED = new Status("EXPECTATION_FAILED", 29, 417, "Expectation Failed");
/*1375*/            INTERNAL_SERVER_ERROR = new Status("INTERNAL_SERVER_ERROR", 30, 500, "Internal Server Error");
/*1381*/            NOT_IMPLEMENTED = new Status("NOT_IMPLEMENTED", 31, 501, "Not Implemented");
/*1387*/            BAD_GATEWAY = new Status("BAD_GATEWAY", 32, 502, "Bad Gateway");
/*1391*/            SERVICE_UNAVAILABLE = new Status("SERVICE_UNAVAILABLE", 33, 503, "Service Unavailable");
/*1397*/            GATEWAY_TIMEOUT = new Status("GATEWAY_TIMEOUT", 34, 504, "Gateway Timeout");
/*1403*/            HTTP_VERSION_NOT_SUPPORTED = new Status("HTTP_VERSION_NOT_SUPPORTED", 35, 505, "HTTP Version Not Supported");
/*1224*/            $VALUES = (new Status[] {
/*1224*/                OK, CREATED, ACCEPTED, NO_CONTENT, RESET_CONTENT, PARTIAL_CONTENT, MOVED_PERMANENTLY, FOUND, SEE_OTHER, NOT_MODIFIED, 
/*1224*/                USE_PROXY, TEMPORARY_REDIRECT, BAD_REQUEST, UNAUTHORIZED, PAYMENT_REQUIRED, FORBIDDEN, NOT_FOUND, METHOD_NOT_ALLOWED, NOT_ACCEPTABLE, PROXY_AUTHENTICATION_REQUIRED, 
/*1224*/                REQUEST_TIMEOUT, CONFLICT, GONE, LENGTH_REQUIRED, PRECONDITION_FAILED, REQUEST_ENTITY_TOO_LARGE, REQUEST_URI_TOO_LONG, UNSUPPORTED_MEDIA_TYPE, REQUESTED_RANGE_NOT_SATISFIABLE, EXPECTATION_FAILED, 
/*1224*/                INTERNAL_SERVER_ERROR, NOT_IMPLEMENTED, BAD_GATEWAY, SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT, HTTP_VERSION_NOT_SUPPORTED
                    });
                }

                private Status(String s, int i, int j, String s1)
                {
/*1463*/            super(s, i);
/*1464*/            code = j;
/*1465*/            reason = s1;
/*1466*/            family = Family.familyOf(j);
                }
    }

    public static interface StatusType
    {

        public abstract int getStatusCode();

        public abstract Status.Family getFamily();

        public abstract String getReasonPhrase();
    }

    public static abstract class ResponseBuilder
    {

                protected static ResponseBuilder newInstance()
                {
/* 848*/            return RuntimeDelegate.getInstance().createResponseBuilder();
                }

                public abstract Response build();

                public abstract ResponseBuilder clone();

                public abstract ResponseBuilder status(int i);

                public ResponseBuilder status(StatusType statustype)
                {
/* 890*/            if(statustype == null)
/* 891*/                throw new IllegalArgumentException();
/* 893*/            else
/* 893*/                return status(statustype.getStatusCode());
                }

                public ResponseBuilder status(Status status1)
                {
/* 904*/            return status(((StatusType) (status1)));
                }

                public abstract ResponseBuilder entity(Object obj);

                public abstract ResponseBuilder entity(Object obj, Annotation aannotation[]);

                public transient abstract ResponseBuilder allow(String as[]);

                public abstract ResponseBuilder allow(Set set);

                public abstract ResponseBuilder cacheControl(CacheControl cachecontrol);

                public abstract ResponseBuilder encoding(String s);

                public abstract ResponseBuilder header(String s, Object obj);

                public abstract ResponseBuilder replaceAll(MultivaluedMap multivaluedmap);

                public abstract ResponseBuilder language(String s);

                public abstract ResponseBuilder language(Locale locale);

                public abstract ResponseBuilder type(MediaType mediatype);

                public abstract ResponseBuilder type(String s);

                public abstract ResponseBuilder variant(Variant variant1);

                public abstract ResponseBuilder contentLocation(URI uri);

                public transient abstract ResponseBuilder cookie(NewCookie anewcookie[]);

                public abstract ResponseBuilder expires(Date date);

                public abstract ResponseBuilder lastModified(Date date);

                public abstract ResponseBuilder location(URI uri);

                public abstract ResponseBuilder tag(EntityTag entitytag);

                public abstract ResponseBuilder tag(String s);

                public transient abstract ResponseBuilder variants(Variant avariant[]);

                public abstract ResponseBuilder variants(List list);

                public transient abstract ResponseBuilder links(Link alink[]);

                public abstract ResponseBuilder link(URI uri, String s);

                public abstract ResponseBuilder link(String s, String s1);

                public volatile Object clone()
                    throws CloneNotSupportedException
                {
/* 833*/            return clone();
                }

                protected ResponseBuilder()
                {
                }
    }


            protected Response()
            {
            }

            public abstract int getStatus();

            public abstract StatusType getStatusInfo();

            public abstract Object getEntity();

            public abstract Object readEntity(Class class1);

            public abstract Object readEntity(GenericType generictype);

            public abstract Object readEntity(Class class1, Annotation aannotation[]);

            public abstract Object readEntity(GenericType generictype, Annotation aannotation[]);

            public abstract boolean hasEntity();

            public abstract boolean bufferEntity();

            public abstract void close();

            public abstract MediaType getMediaType();

            public abstract Locale getLanguage();

            public abstract int getLength();

            public abstract Set getAllowedMethods();

            public abstract Map getCookies();

            public abstract EntityTag getEntityTag();

            public abstract Date getDate();

            public abstract Date getLastModified();

            public abstract URI getLocation();

            public abstract Set getLinks();

            public abstract boolean hasLink(String s);

            public abstract Link getLink(String s);

            public abstract Link.Builder getLinkBuilder(String s);

            public abstract MultivaluedMap getMetadata();

            public MultivaluedMap getHeaders()
            {
/* 510*/        return getMetadata();
            }

            public abstract MultivaluedMap getStringHeaders();

            public abstract String getHeaderString(String s);

            public static ResponseBuilder fromResponse(Response response)
            {
/* 569*/        ResponseBuilder responsebuilder = status(response.getStatus());
/* 570*/        if(response.hasEntity())
/* 571*/            responsebuilder.entity(response.getEntity());
/* 573*/        for(Iterator iterator = response.getHeaders().keySet().iterator(); iterator.hasNext();)
                {
/* 573*/            String s = (String)iterator.next();
                    Object obj;
/* 574*/            obj = ((List) (obj = (List)response.getHeaders().get(s))).iterator();
/* 575*/            while(((Iterator) (obj)).hasNext()) 
                    {
/* 575*/                Object obj1 = ((Iterator) (obj)).next();
/* 576*/                responsebuilder.header(s, obj1);
                    }
                }

/* 579*/        return responsebuilder;
            }

            public static ResponseBuilder status(StatusType statustype)
            {
/* 590*/        return ResponseBuilder.newInstance().status(statustype);
            }

            public static ResponseBuilder status(Status status1)
            {
/* 601*/        return status(((StatusType) (status1)));
            }

            public static ResponseBuilder status(int i)
            {
/* 613*/        return ResponseBuilder.newInstance().status(i);
            }

            public static ResponseBuilder ok()
            {
/* 622*/        return status(Status.OK);
            }

            public static ResponseBuilder ok(Object obj)
            {
                ResponseBuilder responsebuilder;
/* 634*/        (responsebuilder = ok()).entity(obj);
/* 636*/        return responsebuilder;
            }

            public static ResponseBuilder ok(Object obj, MediaType mediatype)
            {
/* 649*/        return ok().entity(obj).type(mediatype);
            }

            public static ResponseBuilder ok(Object obj, String s)
            {
/* 662*/        return ok().entity(obj).type(s);
            }

            public static ResponseBuilder ok(Object obj, Variant variant)
            {
/* 675*/        return ok().entity(obj).variant(variant);
            }

            public static ResponseBuilder serverError()
            {
/* 684*/        return status(Status.INTERNAL_SERVER_ERROR);
            }

            public static ResponseBuilder created(URI uri)
            {
/* 699*/        return status(Status.CREATED).location(uri);
            }

            public static ResponseBuilder accepted()
            {
/* 709*/        return status(Status.ACCEPTED);
            }

            public static ResponseBuilder accepted(Object obj)
            {
/* 722*/        return accepted().entity(obj);
            }

            public static ResponseBuilder noContent()
            {
/* 731*/        return status(Status.NO_CONTENT);
            }

            public static ResponseBuilder notModified()
            {
/* 740*/        return status(Status.NOT_MODIFIED);
            }

            public static ResponseBuilder notModified(EntityTag entitytag)
            {
/* 752*/        return notModified().tag(entitytag);
            }

            public static ResponseBuilder notModified(String s)
            {
/* 768*/        return notModified().tag(s);
            }

            public static ResponseBuilder seeOther(URI uri)
            {
/* 784*/        return status(Status.SEE_OTHER).location(uri);
            }

            public static ResponseBuilder temporaryRedirect(URI uri)
            {
/* 799*/        return status(Status.TEMPORARY_REDIRECT).location(uri);
            }

            public static ResponseBuilder notAcceptable(List list)
            {
/* 810*/        return status(Status.NOT_ACCEPTABLE).variants(list);
            }
}
