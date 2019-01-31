// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonParser.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.stream.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import javax.json.JsonException;
import javax.json.stream.*;

public class GensonJsonParser
    implements JsonParser
{
    static class Location
        implements JsonLocation
    {

                public long getStreamOffset()
                {
/* 179*/            return -1L;
                }

                public long getLineNumber()
                {
/* 184*/            return lineNumber;
                }

                public long getColumnNumber()
                {
/* 189*/            return columnNumber;
                }

                final long lineNumber;
                final long columnNumber;

                public Location(long l, long l1)
                {
/* 173*/            lineNumber = l;
/* 174*/            columnNumber = l1;
                }
    }


            public GensonJsonParser(ObjectReader objectreader)
            {
/*  22*/        parseKey = false;
/*  27*/        reader = objectreader;
            }

            public boolean hasNext()
            {
/*  33*/        if(reader.hasNext() || reader.enclosingType() != JsonType.EMPTY)
/*  33*/            return true;
/*  33*/        return false;
                JsonStreamException jsonstreamexception;
/*  34*/        jsonstreamexception;
/*  35*/        throw _wrapException(jsonstreamexception);
            }

            private javax.json.stream.JsonParser.Event currentValue(ValueType valuetype)
            {
/*  40*/        if(valuetype == ValueType.ARRAY)
                {
/*  41*/            reader.beginArray();
/*  42*/            return javax.json.stream.JsonParser.Event.START_ARRAY;
                }
/*  43*/        if(valuetype == ValueType.OBJECT)
                {
/*  44*/            reader.beginObject();
/*  45*/            return javax.json.stream.JsonParser.Event.START_OBJECT;
                }
/*  46*/        if(valuetype == ValueType.STRING)
/*  47*/            return javax.json.stream.JsonParser.Event.VALUE_STRING;
/*  48*/        if(valuetype == ValueType.NULL)
/*  49*/            return javax.json.stream.JsonParser.Event.VALUE_NULL;
/*  50*/        if(valuetype == ValueType.BOOLEAN)
/*  51*/            if(reader.valueAsBoolean())
/*  51*/                return javax.json.stream.JsonParser.Event.VALUE_TRUE;
/*  51*/            else
/*  51*/                return javax.json.stream.JsonParser.Event.VALUE_FALSE;
/*  52*/        if(valuetype == ValueType.INTEGER || valuetype == ValueType.DOUBLE)
/*  53*/            return javax.json.stream.JsonParser.Event.VALUE_NUMBER;
/*  56*/        else
/*  56*/            throw new JsonException((new StringBuilder("Unknown ValueType ")).append(valuetype).toString());
            }

            public javax.json.stream.JsonParser.Event next()
            {
/*  61*/        if(!hasNext())
/*  61*/            throw new NoSuchElementException();
                JsonType jsontype;
/*  64*/        jsontype = reader.enclosingType();
/*  67*/        if(!parseKey)
/*  68*/            break MISSING_BLOCK_LABEL_51;
/*  68*/        parseKey = false;
/*  69*/        return currentValue(reader.getValueType());
                ValueType valuetype;
/*  70*/        if(!reader.hasNext())
/*  71*/            break MISSING_BLOCK_LABEL_100;
/*  71*/        valuetype = reader.next();
/*  74*/        if(jsontype != JsonType.OBJECT)
/*  75*/            break MISSING_BLOCK_LABEL_89;
/*  75*/        parseKey = true;
/*  76*/        return javax.json.stream.JsonParser.Event.KEY_NAME;
/*  80*/        parseKey = false;
/*  81*/        return currentValue(valuetype);
/*  84*/        parseKey = false;
/*  85*/        if(jsontype != JsonType.OBJECT)
/*  86*/            break MISSING_BLOCK_LABEL_126;
/*  86*/        reader.endObject();
/*  87*/        return javax.json.stream.JsonParser.Event.END_OBJECT;
/*  88*/        if(jsontype != JsonType.ARRAY)
/*  89*/            break MISSING_BLOCK_LABEL_147;
/*  89*/        reader.endArray();
/*  90*/        return javax.json.stream.JsonParser.Event.END_ARRAY;
/*  92*/        try
                {
/*  92*/            throw new JsonException("Reached end of stream, next should not be called.");
                }
/*  94*/        catch(JsonStreamException jsonstreamexception)
                {
/*  95*/            throw _wrapException(jsonstreamexception);
                }
            }

            public String getString()
            {
/* 102*/        if(parseKey)
/* 102*/            return reader.name();
/* 103*/        return reader.valueAsString();
                JsonStreamException jsonstreamexception;
/* 104*/        jsonstreamexception;
/* 105*/        throw _wrapException(jsonstreamexception);
            }

            public boolean isIntegralNumber()
            {
/* 111*/        return reader.getValueType() == ValueType.INTEGER;
            }

            public int getInt()
            {
/* 117*/        return reader.valueAsInt();
                JsonStreamException jsonstreamexception;
/* 118*/        jsonstreamexception;
/* 119*/        throw _wrapException(jsonstreamexception);
            }

            public long getLong()
            {
/* 126*/        return reader.valueAsLong();
                JsonStreamException jsonstreamexception;
/* 127*/        jsonstreamexception;
/* 128*/        throw _wrapException(jsonstreamexception);
            }

            public BigDecimal getBigDecimal()
            {
/* 136*/        return new BigDecimal(reader.valueAsString());
                JsonStreamException jsonstreamexception;
/* 137*/        jsonstreamexception;
/* 138*/        throw _wrapException(jsonstreamexception);
            }

            public JsonLocation getLocation()
            {
/* 144*/        return new Location(reader.row(), reader.column());
            }

            public void close()
            {
/* 150*/        try
                {
/* 150*/            reader.close();
/* 153*/            return;
                }
/* 151*/        catch(IOException ioexception)
                {
/* 152*/            throw _wrapException(ioexception);
                }
            }

            private JsonException _wrapException(Exception exception)
            {
/* 158*/        if(exception instanceof JsonStreamException)
                {
/* 159*/            JsonStreamException jsonstreamexception = (JsonStreamException)exception;
/* 160*/            exception = new JsonParsingException(exception.getMessage(), exception, new Location(jsonstreamexception.getRow(), jsonstreamexception.getColumn()));
                } else
                {
/* 163*/            exception = new JsonException(exception.getMessage(), exception);
                }
/* 165*/        return (JsonException)JsonStreamException.niceTrace(exception);
            }

            public static final String STRICT_DOUBLE_PARSE = "GensonJsonParser.strictDoubleParse";
            private boolean parseKey;
            private final ObjectReader reader;
}
