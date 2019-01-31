// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CacheControlProvider.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.core.CacheControl;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader, Utils

public final class CacheControlProvider
    implements HeaderDelegateProvider
{

            public CacheControlProvider()
            {
            }

            public final boolean supports(Class class1)
            {
/*  72*/        return class1 == javax/ws/rs/core/CacheControl;
            }

            public final String toString(CacheControl cachecontrol)
            {
/*  78*/        Utils.throwIllegalArgumentExceptionIfNull(cachecontrol, LocalizationMessages.CACHE_CONTROL_IS_NULL());
/*  80*/        StringBuilder stringbuilder = new StringBuilder();
/*  81*/        if(cachecontrol.isPrivate())
/*  82*/            appendQuotedWithSeparator(stringbuilder, "private", buildListValue(cachecontrol.getPrivateFields()));
/*  84*/        if(cachecontrol.isNoCache())
/*  85*/            appendQuotedWithSeparator(stringbuilder, "no-cache", buildListValue(cachecontrol.getNoCacheFields()));
/*  87*/        if(cachecontrol.isNoStore())
/*  88*/            appendWithSeparator(stringbuilder, "no-store");
/*  90*/        if(cachecontrol.isNoTransform())
/*  91*/            appendWithSeparator(stringbuilder, "no-transform");
/*  93*/        if(cachecontrol.isMustRevalidate())
/*  94*/            appendWithSeparator(stringbuilder, "must-revalidate");
/*  96*/        if(cachecontrol.isProxyRevalidate())
/*  97*/            appendWithSeparator(stringbuilder, "proxy-revalidate");
/*  99*/        if(cachecontrol.getMaxAge() != -1)
/* 100*/            appendWithSeparator(stringbuilder, "max-age", cachecontrol.getMaxAge());
/* 102*/        if(cachecontrol.getSMaxAge() != -1)
/* 103*/            appendWithSeparator(stringbuilder, "s-maxage", cachecontrol.getSMaxAge());
                java.util.Map.Entry entry;
/* 106*/        for(cachecontrol = cachecontrol.getCacheExtension().entrySet().iterator(); cachecontrol.hasNext(); appendWithSeparator(stringbuilder, (String)entry.getKey(), quoteIfWhitespace((String)entry.getValue())))
/* 106*/            entry = (java.util.Map.Entry)cachecontrol.next();

/* 110*/        return stringbuilder.toString();
            }

            private void readFieldNames(List list, HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 115*/        if(!httpheaderreader.hasNextSeparator('=', false))
                {
/* 116*/            return;
                } else
                {
/* 118*/            httpheaderreader.nextSeparator('=');
/* 119*/            list.addAll(Arrays.asList(COMMA_SEPARATED_LIST.split(httpheaderreader.nextQuotedString())));
/* 120*/            return;
                }
            }

            private int readIntValue(HttpHeaderReader httpheaderreader, String s)
                throws ParseException
            {
                int i;
/* 124*/        httpheaderreader.nextSeparator('=');
/* 125*/        i = httpheaderreader.getIndex();
/* 127*/        return Integer.parseInt(httpheaderreader.nextToken().toString());
/* 128*/        httpheaderreader;
/* 129*/        (s = new ParseException((new StringBuilder("Error parsing integer value for ")).append(s).append(" directive").toString(), i)).initCause(httpheaderreader);
/* 132*/        throw s;
            }

            private void readDirective(CacheControl cachecontrol, HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/* 139*/        String s = httpheaderreader.nextToken().toString().toLowerCase();
/* 140*/        if("private".equals(s))
                {
/* 141*/            cachecontrol.setPrivate(true);
/* 142*/            readFieldNames(cachecontrol.getPrivateFields(), httpheaderreader);
/* 142*/            return;
                }
/* 143*/        if("public".equals(s))
                {
/* 145*/            cachecontrol.getCacheExtension().put(s, null);
/* 145*/            return;
                }
/* 146*/        if("no-cache".equals(s))
                {
/* 147*/            cachecontrol.setNoCache(true);
/* 148*/            readFieldNames(cachecontrol.getNoCacheFields(), httpheaderreader);
/* 148*/            return;
                }
/* 149*/        if("no-store".equals(s))
                {
/* 150*/            cachecontrol.setNoStore(true);
/* 150*/            return;
                }
/* 151*/        if("no-transform".equals(s))
                {
/* 152*/            cachecontrol.setNoTransform(true);
/* 152*/            return;
                }
/* 153*/        if("must-revalidate".equals(s))
                {
/* 154*/            cachecontrol.setMustRevalidate(true);
/* 154*/            return;
                }
/* 155*/        if("proxy-revalidate".equals(s))
                {
/* 156*/            cachecontrol.setProxyRevalidate(true);
/* 156*/            return;
                }
/* 157*/        if("max-age".equals(s))
                {
/* 158*/            cachecontrol.setMaxAge(readIntValue(httpheaderreader, s));
/* 158*/            return;
                }
/* 159*/        if("s-maxage".equals(s))
                {
/* 160*/            cachecontrol.setSMaxAge(readIntValue(httpheaderreader, s));
/* 160*/            return;
                }
/* 162*/        String s1 = null;
/* 163*/        if(httpheaderreader.hasNextSeparator('=', false))
                {
/* 164*/            httpheaderreader.nextSeparator('=');
/* 165*/            s1 = httpheaderreader.nextTokenOrQuotedString().toString();
                }
/* 167*/        cachecontrol.getCacheExtension().put(s, s1);
            }

            public final CacheControl fromString(String s)
            {
/* 174*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.CACHE_CONTROL_IS_NULL());
                CacheControl cachecontrol;
/* 177*/        HttpHeaderReader httpheaderreader = HttpHeaderReader.newInstance(s);
/* 178*/        (cachecontrol = new CacheControl()).setNoTransform(false);
/* 180*/        do
                {
/* 180*/            if(!httpheaderreader.hasNext())
/* 181*/                break;
/* 181*/            readDirective(cachecontrol, httpheaderreader);
/* 182*/            if(httpheaderreader.hasNextSeparator(',', true))
/* 183*/                httpheaderreader.nextSeparator(',');
                } while(true);
/* 186*/        return cachecontrol;
                ParseException parseexception;
/* 187*/        parseexception;
/* 188*/        throw new IllegalArgumentException((new StringBuilder("Error parsing cache control '")).append(s).append("'").toString(), parseexception);
            }

            private void appendWithSeparator(StringBuilder stringbuilder, String s)
            {
/* 194*/        if(stringbuilder.length() > 0)
/* 195*/            stringbuilder.append(", ");
/* 197*/        stringbuilder.append(s);
            }

            private void appendQuotedWithSeparator(StringBuilder stringbuilder, String s, String s1)
            {
/* 201*/        appendWithSeparator(stringbuilder, s);
/* 202*/        if(s1 != null && !s1.isEmpty())
                {
/* 203*/            stringbuilder.append("=\"");
/* 204*/            stringbuilder.append(s1);
/* 205*/            stringbuilder.append("\"");
                }
            }

            private void appendWithSeparator(StringBuilder stringbuilder, String s, String s1)
            {
/* 210*/        appendWithSeparator(stringbuilder, s);
/* 211*/        if(s1 != null && !s1.isEmpty())
                {
/* 212*/            stringbuilder.append("=");
/* 213*/            stringbuilder.append(s1);
                }
            }

            private void appendWithSeparator(StringBuilder stringbuilder, String s, int i)
            {
/* 218*/        appendWithSeparator(stringbuilder, s);
/* 219*/        stringbuilder.append("=");
/* 220*/        stringbuilder.append(i);
            }

            private String buildListValue(List list)
            {
/* 224*/        StringBuilder stringbuilder = new StringBuilder();
                String s;
/* 225*/        for(list = list.iterator(); list.hasNext(); appendWithSeparator(stringbuilder, s))
/* 225*/            s = (String)list.next();

/* 228*/        return stringbuilder.toString();
            }

            private String quoteIfWhitespace(String s)
            {
/* 232*/        if(s == null)
/* 233*/            return null;
                Matcher matcher;
/* 235*/        if((matcher = WHITESPACE.matcher(s)).find())
/* 237*/            return (new StringBuilder("\"")).append(s).append("\"").toString();
/* 239*/        else
/* 239*/            return s;
            }

            public final volatile String toString(Object obj)
            {
/*  64*/        return toString((CacheControl)obj);
            }

            public final volatile Object fromString(String s)
            {
/*  64*/        return fromString(s);
            }

            private static final Pattern WHITESPACE = Pattern.compile("\\s");
            private static final Pattern COMMA_SEPARATED_LIST = Pattern.compile("[\\s]*,[\\s]*");

}
