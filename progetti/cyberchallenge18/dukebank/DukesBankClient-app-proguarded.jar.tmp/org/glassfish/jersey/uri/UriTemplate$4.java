// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplate.java

package org.glassfish.jersey.uri;

import java.util.Map;

// Referenced classes of package org.glassfish.jersey.uri:
//            UriComponent, UriTemplate

static class val.type
    implements mplateValueStrategy
{

            public final String valueFor(String s, String s1)
            {
                Object obj;
/*1068*/        if((obj = val$mapValues.get(s)) != null)
                {
/*1071*/            if(val$encode)
/*1072*/                obj = UriComponent.encode(obj.toString(), val$type);
/*1074*/            else
/*1074*/                obj = UriComponent.contextualEncode(obj.toString(), val$type);
/*1076*/            return obj.toString();
                }
/*1078*/        if(val$mapValues.containsKey(s))
/*1079*/            throw new IllegalArgumentException(String.format("The value associated of the template value map for key '%s' is 'null'.", new Object[] {
/*1079*/                s
                    }));
/*1085*/        else
/*1085*/            return s1;
            }

            final Map val$mapValues;
            final boolean val$encode;
            final ype val$type;

            ype(Map map, boolean flag, ype ype)
            {
/*1064*/        val$mapValues = map;
/*1064*/        val$encode = flag;
/*1064*/        val$type = ype;
/*1064*/        super();
            }
}
