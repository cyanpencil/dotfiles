// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonObject.java

package com.owlike.genson.ext.jsr353;

import java.util.*;
import javax.json.*;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            JSR353Bundle

class GensonJsonObject extends AbstractMap
    implements JsonObject
{

            GensonJsonObject(Map map)
            {
/*  12*/        values = map;
            }

            public Set entrySet()
            {
/*  17*/        return values.entrySet();
            }

            public JsonArray getJsonArray(String s)
            {
/*  22*/        return (JsonArray)javax/json/JsonArray.cast(values.get(s));
            }

            public JsonObject getJsonObject(String s)
            {
/*  27*/        return (JsonObject)javax/json/JsonObject.cast(values.get(s));
            }

            public JsonNumber getJsonNumber(String s)
            {
/*  32*/        return (JsonNumber)javax/json/JsonNumber.cast(values.get(s));
            }

            public JsonString getJsonString(String s)
            {
/*  37*/        return (JsonString)javax/json/JsonString.cast(values.get(s));
            }

            public String getString(String s)
            {
/*  42*/        return getJsonString(s).getString();
            }

            public String getString(String s, String s1)
            {
/*  47*/        if(isNull(s))
/*  47*/            return s1;
/*  48*/        else
/*  48*/            return getString(s);
            }

            public int getInt(String s)
            {
/*  53*/        return getJsonNumber(s).intValue();
            }

            public int getInt(String s, int i)
            {
/*  58*/        if(isNull(s))
/*  58*/            return i;
/*  59*/        else
/*  59*/            return getInt(s);
            }

            public boolean getBoolean(String s)
            {
/*  64*/        s = (JsonValue)values.get(s);
/*  65*/        if(JsonValue.TRUE.equals(s))
/*  65*/            return true;
/*  66*/        if(JsonValue.FALSE.equals(s))
/*  66*/            return false;
/*  67*/        else
/*  67*/            throw new ClassCastException();
            }

            public boolean getBoolean(String s, boolean flag)
            {
/*  72*/        if(isNull(s))
/*  72*/            return flag;
/*  73*/        else
/*  73*/            return getBoolean(s);
            }

            public boolean isNull(String s)
            {
/*  78*/        s = (JsonValue)values.get(s);
/*  79*/        return JsonValue.NULL.equals(s) || s == null;
            }

            public javax.json.JsonValue.ValueType getValueType()
            {
/*  84*/        return javax.json.JsonValue.ValueType.OBJECT;
            }

            public int hashCode()
            {
/*  90*/        return values.hashCode();
            }

            public boolean equals(Object obj)
            {
/*  95*/        return values.equals(obj);
            }

            public String toString()
            {
/* 100*/        return JSR353Bundle.toString(this);
            }

            private final Map values;
}
