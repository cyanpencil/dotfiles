// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplate.java

package org.glassfish.jersey.uri;

import java.util.Map;

// Referenced classes of package org.glassfish.jersey.uri:
//            UriComponent, UriTemplate

class val.valueOffset
    implements val.valueOffset
{

            public String valueFor(String s, String s1)
            {
/*1013*/        if((s1 = ((String) (val$mapValues.get(s)))) == null && offset < val$values.length)
                {
/*1015*/            s1 = val$values[offset++];
/*1016*/            val$mapValues.put(s, s1);
                }
/*1018*/        if(s1 == null)
/*1019*/            throw new IllegalArgumentException(String.format("The template variable '%s' has no value", new Object[] {
/*1019*/                s
                    }));
/*1022*/        if(val$encode)
/*1023*/            return UriComponent.encode(s1.toString(), val$componentType);
/*1025*/        else
/*1025*/            return UriComponent.contextualEncode(s1.toString(), val$componentType);
            }

            private int offset;
            final int val$valueOffset;
            final Map val$mapValues;
            final String val$values[];
            final boolean val$encode;
            final val.componentType val$componentType;


            ()
            {
/*1007*/        val$valueOffset = i;
/*1007*/        val$mapValues = map;
/*1007*/        val$values = as;
/*1007*/        val$encode = flag;
/*1007*/        val$componentType = ;
/*1007*/        super();
/*1008*/        offset = val$valueOffset;
            }
}
