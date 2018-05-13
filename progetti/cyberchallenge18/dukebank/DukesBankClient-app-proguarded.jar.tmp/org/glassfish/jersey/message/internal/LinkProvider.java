// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   LinkProvider.java

package org.glassfish.jersey.message.internal;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.Tokenizer;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            JerseyLink, Utils

public class LinkProvider
    implements HeaderDelegateProvider
{

            public LinkProvider()
            {
            }

            public boolean supports(Class class1)
            {
/*  72*/        return javax/ws/rs/core/Link.isAssignableFrom(class1);
            }

            public Link fromString(String s)
                throws IllegalArgumentException
            {
/*  77*/        return initBuilder(new JerseyLink.Builder(), s).build(new Object[0]);
            }

            static JerseyLink.Builder initBuilder(JerseyLink.Builder builder, String s)
            {
/*  88*/        Utils.throwIllegalArgumentExceptionIfNull(s, LocalizationMessages.LINK_IS_NULL());
/*  90*/        try
                {
                    int i;
                    Object obj;
/*  90*/            if((s = s.trim()).startsWith("<"))
                    {
/*  93*/                if((i = s.indexOf('>')) != -1)
                        {
/*  95*/                    builder.uri(s.substring(1, i).trim());
/*  96*/                    obj = s.substring(i + 1).trim();
                        } else
                        {
/*  98*/                    throw new IllegalArgumentException((new StringBuilder("Missing token > in ")).append(s).toString());
                        }
                    } else
                    {
/* 101*/                throw new IllegalArgumentException((new StringBuilder("Missing starting token < in ")).append(s).toString());
                    }
                    String s1;
                    String s2;
/* 104*/            for(obj = new StringTokenizer(((String) (obj)), ";=\"", true); ((StringTokenizer) (obj)).hasMoreTokens(); builder.param(s1, s2))
                    {
/* 106*/                checkToken(((StringTokenizer) (obj)), ";");
/* 107*/                s1 = ((StringTokenizer) (obj)).nextToken().trim();
/* 108*/                checkToken(((StringTokenizer) (obj)), "=");
/* 110*/                if((s2 = nextNonEmptyToken(((StringTokenizer) (obj)))).equals("\""))
                        {
/* 112*/                    s2 = ((StringTokenizer) (obj)).nextToken();
/* 113*/                    checkToken(((StringTokenizer) (obj)), "\"");
                        }
                    }

                }
/* 118*/        catch(Throwable throwable)
                {
/* 119*/            if(LOGGER.isLoggable(Level.FINER))
/* 120*/                LOGGER.log(Level.FINER, (new StringBuilder("Error parsing link value '")).append(s).append("'").toString(), throwable);
/* 122*/            builder = null;
                }
/* 124*/        if(builder == null)
/* 125*/            throw new IllegalArgumentException((new StringBuilder("Unable to parse link ")).append(s).toString());
/* 127*/        else
/* 127*/            return builder;
            }

            private static String nextNonEmptyToken(StringTokenizer stringtokenizer)
                throws IllegalArgumentException
            {
                String s;
/* 133*/        while((s = stringtokenizer.nextToken().trim()).length() == 0) ;
/* 136*/        return s;
            }

            private static void checkToken(StringTokenizer stringtokenizer, String s)
                throws IllegalArgumentException
            {
                String s1;
/* 142*/        while((s1 = stringtokenizer.nextToken().trim()).length() == 0) ;
/* 144*/        if(!s1.equals(s))
/* 145*/            throw new IllegalArgumentException((new StringBuilder("Expected token ")).append(s).append(" but found ").append(s1).toString());
/* 147*/        else
/* 147*/            return;
            }

            public String toString(Link link)
            {
/* 151*/        return stringfy(link);
            }

            static String stringfy(Link link)
            {
/* 161*/        Utils.throwIllegalArgumentExceptionIfNull(link, LocalizationMessages.LINK_IS_NULL());
/* 163*/        Object obj = link.getParams();
                StringBuilder stringbuilder;
/* 164*/        (stringbuilder = new StringBuilder()).append('<').append(link.getUri()).append('>');
/* 167*/        for(link = ((Map) (obj)).entrySet().iterator(); link.hasNext(); stringbuilder.append("; ").append((String)((java.util.Map.Entry) (obj)).getKey()).append("=\"").append((String)((java.util.Map.Entry) (obj)).getValue()).append("\""))
/* 167*/            obj = (java.util.Map.Entry)link.next();

/* 170*/        return stringbuilder.toString();
            }

            static List getLinkRelations(String s)
            {
/* 180*/        if(s == null)
/* 180*/            return null;
/* 180*/        else
/* 180*/            return Arrays.asList(Tokenizer.tokenize(s, "\" "));
            }

            public volatile String toString(Object obj)
            {
/*  65*/        return toString((Link)obj);
            }

            public volatile Object fromString(String s)
            {
/*  65*/        return fromString(s);
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/LinkProvider.getName());

}
