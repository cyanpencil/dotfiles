// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParameterizedHeader.java

package org.glassfish.jersey.message.internal;

import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

// Referenced classes of package org.glassfish.jersey.message.internal:
//            HttpHeaderReader

public class ParameterizedHeader
{

            public ParameterizedHeader(String s)
                throws ParseException
            {
/*  69*/        this(HttpHeaderReader.newInstance(s));
            }

            public ParameterizedHeader(HttpHeaderReader httpheaderreader)
                throws ParseException
            {
/*  79*/        httpheaderreader.hasNext();
/*  81*/        value = "";
_L3:
/*  82*/        if(!httpheaderreader.hasNext() || httpheaderreader.hasNextSeparator(';', false)) goto _L2; else goto _L1
_L1:
/*  83*/        httpheaderreader.next();
/*  84*/        new StringBuilder();
/*  84*/        this;
/*  84*/        JVM INSTR dup_x1 ;
/*  84*/        value;
/*  84*/        append();
/*  84*/        httpheaderreader.getEventValue();
/*  84*/        append();
/*  84*/        toString();
/*  84*/        value;
                  goto _L3
_L2:
/*  87*/        if(httpheaderreader.hasNext())
/*  88*/            parameters = HttpHeaderReader.readParameters(httpheaderreader);
/*  90*/        if(parameters == null)
                {
/*  91*/            parameters = Collections.emptyMap();
/*  91*/            return;
                } else
                {
/*  93*/            parameters = Collections.unmodifiableMap(parameters);
/*  95*/            return;
                }
            }

            public String getValue()
            {
/* 103*/        return value;
            }

            public Map getParameters()
            {
/* 112*/        return parameters;
            }

            private String value;
            private Map parameters;
}
