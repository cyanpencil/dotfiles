// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   HeaderUtils.java

package org.glassfish.jersey.message.internal;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.AbstractMultivaluedMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.RuntimeDelegate;
import jersey.repackaged.com.google.common.base.Function;
import jersey.repackaged.com.google.common.collect.*;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;
import org.glassfish.jersey.internal.util.collection.StringKeyIgnoreCaseMultivaluedMap;

public final class HeaderUtils
{

            public static AbstractMultivaluedMap createInbound()
            {
/*  82*/        return new StringKeyIgnoreCaseMultivaluedMap();
            }

            public static MultivaluedMap empty()
            {
/*  95*/        return ImmutableMultivaluedMap.empty();
            }

            public static AbstractMultivaluedMap createOutbound()
            {
/* 104*/        return new StringKeyIgnoreCaseMultivaluedMap();
            }

            public static String asString(Object obj, RuntimeDelegate runtimedelegate)
            {
/* 127*/        if(obj == null)
/* 128*/            return null;
/* 130*/        if(obj instanceof String)
/* 131*/            return (String)obj;
/* 133*/        if(runtimedelegate == null)
/* 134*/            runtimedelegate = RuntimeDelegate.getInstance();
/* 137*/        if((runtimedelegate = runtimedelegate.createHeaderDelegate(obj.getClass())) != null)
/* 138*/            return runtimedelegate.toString(obj);
/* 138*/        else
/* 138*/            return obj.toString();
            }

            public static List asStringList(List list, RuntimeDelegate runtimedelegate)
            {
/* 151*/        if(list == null || list.isEmpty())
/* 152*/            return Collections.emptyList();
/* 156*/        if(runtimedelegate == null)
/* 157*/            runtimedelegate = RuntimeDelegate.getInstance();
/* 159*/        else
/* 159*/            runtimedelegate = runtimedelegate;
/* 162*/        return Lists.transform(list, new Function(runtimedelegate) {

                    public final String apply(Object obj)
                    {
/* 165*/                if(obj == null)
/* 165*/                    return "[null]";
/* 165*/                else
/* 165*/                    return HeaderUtils.asString(obj, delegate);
                    }

                    public final volatile Object apply(Object obj)
                    {
/* 162*/                return apply(obj);
                    }

                    final RuntimeDelegate val$delegate;

                    
                    {
/* 162*/                delegate = runtimedelegate;
/* 162*/                super();
                    }
        });
            }

            public static MultivaluedMap asStringHeaders(MultivaluedMap multivaluedmap)
            {
/* 179*/        if(multivaluedmap == null)
                {
/* 180*/            return null;
                } else
                {
/* 183*/            RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
/* 184*/            return new AbstractMultivaluedMap(Maps.transformValues(multivaluedmap, new Function(runtimedelegate) {

                        public final List apply(List list)
                        {
/* 188*/                    return HeaderUtils.asStringList(list, rd);
                        }

                        public final volatile Object apply(Object obj)
                        {
/* 185*/                    return apply((List)obj);
                        }

                        final RuntimeDelegate val$rd;

                    
                    {
/* 185*/                rd = runtimedelegate;
/* 185*/                super();
                    }
            })) {

            };
                }
            }

            public static Map asStringHeadersSingleValue(MultivaluedMap multivaluedmap)
            {
/* 205*/        if(multivaluedmap == null)
/* 206*/            return null;
/* 209*/        RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
/* 210*/        jersey.repackaged.com.google.common.collect.ImmutableMap.Builder builder = new jersey.repackaged.com.google.common.collect.ImmutableMap.Builder();
                java.util.Map.Entry entry;
/* 211*/        for(multivaluedmap = multivaluedmap.entrySet().iterator(); multivaluedmap.hasNext(); builder.put(entry.getKey(), asHeaderString((List)entry.getValue(), runtimedelegate)))
/* 211*/            entry = (java.util.Map.Entry)multivaluedmap.next();

/* 214*/        return builder.build();
            }

            public static String asHeaderString(List list, RuntimeDelegate runtimedelegate)
            {
/* 234*/        if(list == null)
/* 235*/            return null;
/* 237*/        if(!(list = asStringList(list, runtimedelegate).iterator()).hasNext())
/* 239*/            return "";
/* 242*/        runtimedelegate = new StringBuilder((String)list.next());
/* 243*/        for(; list.hasNext(); runtimedelegate.append(',').append((String)list.next()));
/* 247*/        return runtimedelegate.toString();
            }

            public static void checkHeaderChanges(Map map, MultivaluedMap multivaluedmap, String s)
            {
/* 265*/        if(LOGGER.isLoggable(Level.WARNING))
                {
/* 266*/            RuntimeDelegate runtimedelegate = RuntimeDelegate.getInstance();
/* 267*/            HashSet hashset = new HashSet();
/* 268*/            Iterator iterator = multivaluedmap.entrySet().iterator();
/* 268*/            do
                    {
/* 268*/                if(!iterator.hasNext())
/* 268*/                    break;
/* 268*/                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
/* 269*/                if(!map.containsKey(entry.getKey()))
                        {
/* 270*/                    hashset.add(entry.getKey());
                        } else
                        {
/* 272*/                    String s1 = (String)map.get(entry.getKey());
/* 273*/                    String s2 = asHeaderString((List)multivaluedmap.get(entry.getKey()), runtimedelegate);
/* 274*/                    if(!s1.equals(s2))
/* 275*/                        hashset.add(entry.getKey());
                        }
                    } while(true);
/* 279*/            if(!hashset.isEmpty() && LOGGER.isLoggable(Level.WARNING))
/* 281*/                LOGGER.warning(LocalizationMessages.SOME_HEADERS_NOT_SENT(s, hashset.toString()));
                }
            }

            private HeaderUtils()
            {
/* 292*/        throw new AssertionError("No instances allowed.");
            }

            private static final Logger LOGGER = Logger.getLogger(org/glassfish/jersey/message/internal/HeaderUtils.getName());

}
