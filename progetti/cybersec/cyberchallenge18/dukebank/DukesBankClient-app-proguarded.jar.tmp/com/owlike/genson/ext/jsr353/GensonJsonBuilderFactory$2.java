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
//            GensonJsonArray, GensonJsonBuilderFactory, GensonJsonNumber, GensonJsonString

class this._cls0
    implements JsonArrayBuilder
{

            public JsonArrayBuilder add(JsonValue jsonvalue)
            {
/*  92*/        if(jsonvalue == null)
/*  92*/            addNull();
/*  93*/        else
/*  93*/            values.add(jsonvalue);
/*  95*/        return this;
            }

            public JsonArrayBuilder add(String s)
            {
/* 100*/        if(s == null)
/* 100*/            return addNull();
/* 101*/        else
/* 101*/            return add(((JsonValue) (new GensonJsonString(s))));
            }

            public JsonArrayBuilder add(BigDecimal bigdecimal)
            {
/* 106*/        if(bigdecimal == null)
/* 106*/            return addNull();
/* 107*/        else
/* 107*/            return add(((JsonValue) (new Number(bigdecimal))));
            }

            public JsonArrayBuilder add(BigInteger biginteger)
            {
/* 112*/        if(biginteger == null)
/* 112*/            return addNull();
/* 113*/        else
/* 113*/            return add(((JsonValue) (new ber(biginteger))));
            }

            public JsonArrayBuilder add(int i)
            {
/* 118*/        return add(((JsonValue) (new ber(i))));
            }

            public JsonArrayBuilder add(long l)
            {
/* 123*/        return add(((JsonValue) (new ber(l))));
            }

            public JsonArrayBuilder add(double d)
            {
/* 128*/        return add(((JsonValue) (new Number(d))));
            }

            public JsonArrayBuilder add(boolean flag)
            {
/* 133*/        return add(flag ? JsonValue.TRUE : JsonValue.FALSE);
            }

            public JsonArrayBuilder addNull()
            {
/* 138*/        return add(JsonValue.NULL);
            }

            public JsonArrayBuilder add(JsonObjectBuilder jsonobjectbuilder)
            {
/* 143*/        if(jsonobjectbuilder == null)
/* 143*/            return addNull();
/* 144*/        else
/* 144*/            return add(((JsonValue) (jsonobjectbuilder.build())));
            }

            public JsonArrayBuilder add(JsonArrayBuilder jsonarraybuilder)
            {
/* 149*/        if(jsonarraybuilder == null)
/* 149*/            return addNull();
/* 150*/        else
/* 150*/            return add(((JsonValue) (jsonarraybuilder.build())));
            }

            public JsonArray build()
            {
/* 155*/        return new GensonJsonArray(Collections.unmodifiableList(values));
            }

            private final List values = new ArrayList();
            final GensonJsonBuilderFactory this$0;

            ber()
            {
/*  87*/        this$0 = GensonJsonBuilderFactory.this;
/*  87*/        super();
            }
}
