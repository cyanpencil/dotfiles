// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplate.java

package org.glassfish.jersey.uri;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package org.glassfish.jersey.uri:
//            UriTemplate

class val.offset
    implements mplateValueStrategy
{

            public String valueFor(String s, String s1)
            {
/* 623*/        if((s1 = (String)mapValues.get(s)) == null && v < lengthPlusOffset && (s1 = val$values[v++]) != null)
/* 628*/            mapValues.put(s, s1);
/* 633*/        return s1;
            }

            private final int lengthPlusOffset;
            private int v;
            private final Map mapValues = new HashMap();
            final int val$length;
            final int val$offset;
            final String val$values[];
            final UriTemplate this$0;

            mplateValueStrategy()
            {
/* 613*/        this$0 = final_uritemplate;
/* 613*/        val$length = i;
/* 613*/        val$offset = j;
/* 613*/        val$values = _5B_Ljava.lang.String_3B_.this;
/* 613*/        super();
/* 614*/        lengthPlusOffset = val$length + val$offset;
/* 615*/        v = val$offset;
            }
}
