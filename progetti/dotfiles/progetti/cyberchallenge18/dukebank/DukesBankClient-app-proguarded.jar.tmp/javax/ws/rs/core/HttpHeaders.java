// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HttpHeaders.java

package javax.ws.rs.core;

import java.util.*;

// Referenced classes of package javax.ws.rs.core:
//            MultivaluedMap, MediaType

public interface HttpHeaders
{

    public abstract List getRequestHeader(String s);

    public abstract String getHeaderString(String s);

    public abstract MultivaluedMap getRequestHeaders();

    public abstract List getAcceptableMediaTypes();

    public abstract List getAcceptableLanguages();

    public abstract MediaType getMediaType();

    public abstract Locale getLanguage();

    public abstract Map getCookies();

    public abstract Date getDate();

    public abstract int getLength();

    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_CHARSET = "Accept-Charset";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String ALLOW = "Allow";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_ID = "Content-ID";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LOCATION = "Content-Location";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DATE = "Date";
    public static final String ETAG = "ETag";
    public static final String EXPIRES = "Expires";
    public static final String HOST = "Host";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String LOCATION = "Location";
    public static final String LINK = "Link";
    public static final String RETRY_AFTER = "Retry-After";
    public static final String USER_AGENT = "User-Agent";
    public static final String VARY = "Vary";
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    public static final String COOKIE = "Cookie";
    public static final String SET_COOKIE = "Set-Cookie";
}
