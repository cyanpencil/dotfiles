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
//            GensonJsonArray, GensonJsonNumber, GensonJsonString, GensonJsonObject

public class GensonJsonBuilderFactory
    implements JsonBuilderFactory
{

            public GensonJsonBuilderFactory()
            {
            }

            public JsonObjectBuilder createObjectBuilder()
            {
/*  12*/        return new JsonObjectBuilder() {

                    public JsonObjectBuilder add(String s, JsonValue jsonvalue)
                    {
/*  17*/                if(jsonvalue == null)
/*  17*/                    addNull(s);
/*  18*/                else
/*  18*/                    values.put(s, jsonvalue);
/*  20*/                return this;
                    }

                    public JsonObjectBuilder add(String s, String s1)
                    {
/*  25*/                if(s1 == null)
/*  25*/                    return addNull(s);
/*  26*/                else
/*  26*/                    return add(s, ((JsonValue) (new GensonJsonString(s1))));
                    }

                    public JsonObjectBuilder add(String s, BigInteger biginteger)
                    {
/*  31*/                if(biginteger == null)
/*  31*/                    return addNull(s);
/*  32*/                else
/*  32*/                    return add(s, ((JsonValue) (new GensonJsonNumber.IntJsonNumber(biginteger))));
                    }

                    public JsonObjectBuilder add(String s, BigDecimal bigdecimal)
                    {
/*  37*/                if(bigdecimal == null)
/*  37*/                    return addNull(s);
/*  38*/                else
/*  38*/                    return add(s, ((JsonValue) (new GensonJsonNumber.DoubleJsonNumber(bigdecimal))));
                    }

                    public JsonObjectBuilder add(String s, int i)
                    {
/*  43*/                return add(s, ((JsonValue) (new GensonJsonNumber.IntJsonNumber(i))));
                    }

                    public JsonObjectBuilder add(String s, long l)
                    {
/*  48*/                return add(s, ((JsonValue) (new GensonJsonNumber.IntJsonNumber(l))));
                    }

                    public JsonObjectBuilder add(String s, double d)
                    {
/*  53*/                return add(s, ((JsonValue) (new GensonJsonNumber.DoubleJsonNumber(d))));
                    }

                    public JsonObjectBuilder add(String s, boolean flag)
                    {
/*  58*/                return add(s, flag ? JsonValue.TRUE : JsonValue.FALSE);
                    }

                    public JsonObjectBuilder addNull(String s)
                    {
/*  63*/                return add(s, JsonValue.NULL);
                    }

                    public JsonObjectBuilder add(String s, JsonObjectBuilder jsonobjectbuilder)
                    {
/*  68*/                if(jsonobjectbuilder == null)
/*  68*/                    return addNull(s);
/*  69*/                else
/*  69*/                    return add(s, ((JsonValue) (jsonobjectbuilder.build())));
                    }

                    public JsonObjectBuilder add(String s, JsonArrayBuilder jsonarraybuilder)
                    {
/*  74*/                if(jsonarraybuilder == null)
/*  74*/                    return addNull(s);
/*  75*/                else
/*  75*/                    return add(s, ((JsonValue) (jsonarraybuilder.build())));
                    }

                    public JsonObject build()
                    {
/*  80*/                return new GensonJsonObject(Collections.unmodifiableMap(values));
                    }

                    private final Map values = new LinkedHashMap();
                    final GensonJsonBuilderFactory this$0;

                    
                    {
/*  12*/                this$0 = GensonJsonBuilderFactory.this;
/*  12*/                super();
                    }
        };
            }

            public JsonArrayBuilder createArrayBuilder()
            {
/*  87*/        return new JsonArrayBuilder() {

                    public JsonArrayBuilder add(JsonValue jsonvalue)
                    {
/*  92*/                if(jsonvalue == null)
/*  92*/                    addNull();
/*  93*/                else
/*  93*/                    values.add(jsonvalue);
/*  95*/                return this;
                    }

                    public JsonArrayBuilder add(String s)
                    {
/* 100*/                if(s == null)
/* 100*/                    return addNull();
/* 101*/                else
/* 101*/                    return add(((JsonValue) (new GensonJsonString(s))));
                    }

                    public JsonArrayBuilder add(BigDecimal bigdecimal)
                    {
/* 106*/                if(bigdecimal == null)
/* 106*/                    return addNull();
/* 107*/                else
/* 107*/                    return add(((JsonValue) (new GensonJsonNumber.DoubleJsonNumber(bigdecimal))));
                    }

                    public JsonArrayBuilder add(BigInteger biginteger)
                    {
/* 112*/                if(biginteger == null)
/* 112*/                    return addNull();
/* 113*/                else
/* 113*/                    return add(((JsonValue) (new GensonJsonNumber.IntJsonNumber(biginteger))));
                    }

                    public JsonArrayBuilder add(int i)
                    {
/* 118*/                return add(((JsonValue) (new GensonJsonNumber.IntJsonNumber(i))));
                    }

                    public JsonArrayBuilder add(long l)
                    {
/* 123*/                return add(((JsonValue) (new GensonJsonNumber.IntJsonNumber(l))));
                    }

                    public JsonArrayBuilder add(double d)
                    {
/* 128*/                return add(((JsonValue) (new GensonJsonNumber.DoubleJsonNumber(d))));
                    }

                    public JsonArrayBuilder add(boolean flag)
                    {
/* 133*/                return add(flag ? JsonValue.TRUE : JsonValue.FALSE);
                    }

                    public JsonArrayBuilder addNull()
                    {
/* 138*/                return add(JsonValue.NULL);
                    }

                    public JsonArrayBuilder add(JsonObjectBuilder jsonobjectbuilder)
                    {
/* 143*/                if(jsonobjectbuilder == null)
/* 143*/                    return addNull();
/* 144*/                else
/* 144*/                    return add(((JsonValue) (jsonobjectbuilder.build())));
                    }

                    public JsonArrayBuilder add(JsonArrayBuilder jsonarraybuilder)
                    {
/* 149*/                if(jsonarraybuilder == null)
/* 149*/                    return addNull();
/* 150*/                else
/* 150*/                    return add(((JsonValue) (jsonarraybuilder.build())));
                    }

                    public JsonArray build()
                    {
/* 155*/                return new GensonJsonArray(Collections.unmodifiableList(values));
                    }

                    private final List values = new ArrayList();
                    final GensonJsonBuilderFactory this$0;

                    
                    {
/*  87*/                this$0 = GensonJsonBuilderFactory.this;
/*  87*/                super();
                    }
        };
            }

            public Map getConfigInUse()
            {
/* 162*/        return Collections.emptyMap();
            }
}
