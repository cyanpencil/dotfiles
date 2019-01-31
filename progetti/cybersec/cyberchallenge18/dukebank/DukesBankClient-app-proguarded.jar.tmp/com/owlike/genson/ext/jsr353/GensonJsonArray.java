// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonArray.java

package com.owlike.genson.ext.jsr353;

import java.util.AbstractList;
import java.util.List;
import javax.json.*;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            JSR353Bundle

class GensonJsonArray extends AbstractList
    implements JsonArray
{

            GensonJsonArray(List list)
            {
/*  10*/        values = list;
            }

            public JsonObject getJsonObject(int i)
            {
/*  15*/        return (JsonObject)javax/json/JsonObject.cast(values.get(i));
            }

            public JsonArray getJsonArray(int i)
            {
/*  20*/        return (JsonArray)javax/json/JsonArray.cast(values.get(i));
            }

            public JsonNumber getJsonNumber(int i)
            {
/*  25*/        return (JsonNumber)javax/json/JsonNumber.cast(values.get(i));
            }

            public JsonString getJsonString(int i)
            {
/*  30*/        return (JsonString)javax/json/JsonString.cast(values.get(i));
            }

            public List getValuesAs(Class class1)
            {
/*  35*/        return values;
            }

            public String getString(int i)
            {
/*  40*/        return getJsonString(i).getString();
            }

            public String getString(int i, String s)
            {
/*  45*/        if(isNull(i))
/*  45*/            return s;
/*  46*/        else
/*  46*/            return getString(i);
            }

            public int getInt(int i)
            {
/*  51*/        return getJsonNumber(i).intValue();
            }

            public int getInt(int i, int j)
            {
/*  56*/        if(isNull(i))
/*  56*/            return j;
/*  57*/        else
/*  57*/            return getInt(i);
            }

            public boolean getBoolean(int i)
            {
/*  62*/        i = (JsonValue)values.get(i);
/*  63*/        if(JsonValue.TRUE.equals(i))
/*  63*/            return true;
/*  64*/        if(JsonValue.FALSE.equals(i))
/*  64*/            return false;
/*  65*/        else
/*  65*/            throw new ClassCastException();
            }

            public boolean getBoolean(int i, boolean flag)
            {
/*  70*/        if(isNull(i))
/*  70*/            return flag;
/*  71*/        else
/*  71*/            return getBoolean(i);
            }

            public boolean isNull(int i)
            {
/*  76*/        return JsonValue.NULL.equals(values.get(i));
            }

            public JsonValue get(int i)
            {
/*  81*/        return (JsonValue)values.get(i);
            }

            public int size()
            {
/*  86*/        return values.size();
            }

            public javax.json.JsonValue.ValueType getValueType()
            {
/*  91*/        return javax.json.JsonValue.ValueType.ARRAY;
            }

            public int hashCode()
            {
/*  96*/        return values.hashCode();
            }

            public boolean equals(Object obj)
            {
/* 101*/        return values.equals(obj);
            }

            public String toString()
            {
/* 106*/        return JSR353Bundle.toString(this);
            }

            public volatile Object get(int i)
            {
/*   6*/        return get(i);
            }

            private final List values;
}
