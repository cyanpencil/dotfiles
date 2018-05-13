// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   UriTemplate.java

package org.glassfish.jersey.uri;

import java.util.Map;

// Referenced classes of package org.glassfish.jersey.uri:
//            UriTemplate

class val.values
    implements mplateValueStrategy
{

            public String valueFor(String s, String s1)
            {
/* 577*/        return (String)val$values.get(s);
            }

            final Map val$values;
            final UriTemplate this$0;

            mplateValueStrategy()
            {
/* 574*/        this$0 = final_uritemplate;
/* 574*/        val$values = Map.this;
/* 574*/        super();
            }
}
