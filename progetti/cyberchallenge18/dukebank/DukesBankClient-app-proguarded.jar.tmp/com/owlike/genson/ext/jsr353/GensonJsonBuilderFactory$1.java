// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonBuilderFactory.java

package com.owlike.genson.ext.jsr353;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import javax.json.*;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonBuilderFactory, GensonJsonNumber, GensonJsonObject, GensonJsonString

class this._cls0
    implements JsonObjectBuilder
{

            public JsonObjectBuilder add(String s, JsonValue jsonvalue)
            {
/*  17*/        if(jsonvalue == null)
/*  17*/            addNull(s);
/*  18*/        else
/*  18*/            values.put(s, jsonvalue);
/*  20*/        return this;
            }

            public JsonObjectBuilder add(String s, String s1)
            {
/*  25*/        if(s1 == null)
/*  25*/            return addNull(s);
/*  26*/        else
/*  26*/            return add(s, ((JsonValue) (new GensonJsonString(s1))));
            }

            public JsonObjectBuilder add(String s, BigInteger biginteger)
            {
/*  31*/        if(biginteger == null)
/*  31*/            return addNull(s);
/*  32*/        else
/*  32*/            return add(s, ((JsonValue) (new ber(biginteger))));
            }

            public JsonObjectBuilder add(String s, BigDecimal bigdecimal)
            {
/*  37*/        if(bigdecimal == null)
/*  37*/            return addNull(s);
/*  38*/        else
/*  38*/            return add(s, ((JsonValue) (new Number(bigdecimal))));
            }

            public JsonObjectBuilder add(String s, int i)
            {
/*  43*/        return add(s, ((JsonValue) (new ber(i))));
            }

            public JsonObjectBuilder add(String s, long l)
            {
/*  48*/        return add(s, ((JsonValue) (new ber(l))));
            }

            public JsonObjectBuilder add(String s, double d)
            {
/*  53*/        return add(s, ((JsonValue) (new Number(d))));
            }

            public JsonObjectBuilder add(String s, boolean flag)
            {
/*  58*/        return add(s, flag ? JsonValue.TRUE : JsonValue.FALSE);
            }

            public JsonObjectBuilder addNull(String s)
            {
/*  63*/        return add(s, JsonValue.NULL);
            }

            public JsonObjectBuilder add(String s, JsonObjectBuilder jsonobjectbuilder)
            {
/*  68*/        if(jsonobjectbuilder == null)
/*  68*/            return addNull(s);
/*  69*/        else
/*  69*/            return add(s, ((JsonValue) (jsonobjectbuilder.build())));
            }

            public JsonObjectBuilder add(String s, JsonArrayBuilder jsonarraybuilder)
            {
/*  74*/        if(jsonarraybuilder == null)
/*  74*/            return addNull(s);
/*  75*/        else
/*  75*/            return add(s, ((JsonValue) (jsonarraybuilder.build())));
            }

            public JsonObject build()
            {
/*  80*/        return new GensonJsonObject(Collections.unmodifiableMap(values));
            }

            private final Map values = new LinkedHashMap();
            final GensonJsonBuilderFactory this$0;

            ber()
            {
/*  12*/        this$0 = GensonJsonBuilderFactory.this;
/*  12*/        super();
            }
}
