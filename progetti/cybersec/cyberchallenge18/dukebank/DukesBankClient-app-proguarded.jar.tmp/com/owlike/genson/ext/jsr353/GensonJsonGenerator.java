// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonGenerator.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.stream.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import javax.json.*;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

public class GensonJsonGenerator
    implements JsonGenerator
{

            public GensonJsonGenerator(ObjectWriter objectwriter)
            {
/*  31*/        writer = objectwriter;
            }

            public JsonGenerator writeStartObject()
            {
/*  37*/        try
                {
/*  37*/            writer.beginObject();
/*  38*/            _ctx.push(JsonType.OBJECT);
                }
/*  39*/        catch(Exception exception)
                {
/*  40*/            _wrapAndThrow(exception);
                }
/*  42*/        return this;
            }

            public JsonGenerator writeStartObject(String s)
            {
/*  48*/        try
                {
/*  48*/            writer.writeName(s).beginObject();
/*  49*/            _ctx.push(JsonType.OBJECT);
                }
                // Misplaced declaration of an exception variable
/*  50*/        catch(String s)
                {
/*  51*/            _wrapAndThrow(s);
                }
/*  53*/        return this;
            }

            public JsonGenerator writeStartArray()
            {
/*  59*/        try
                {
/*  59*/            writer.beginArray();
/*  60*/            _ctx.push(JsonType.ARRAY);
                }
/*  61*/        catch(Exception exception)
                {
/*  62*/            _wrapAndThrow(exception);
                }
/*  64*/        return this;
            }

            public JsonGenerator writeStartArray(String s)
            {
/*  70*/        try
                {
/*  70*/            writer.writeName(s).beginArray();
/*  71*/            _ctx.push(JsonType.ARRAY);
                }
                // Misplaced declaration of an exception variable
/*  72*/        catch(String s)
                {
/*  73*/            _wrapAndThrow(s);
                }
/*  75*/        return this;
            }

            public JsonGenerator write(String s, JsonValue jsonvalue)
            {
/*  81*/        try
                {
/*  81*/            writer.writeName(s);
                }
                // Misplaced declaration of an exception variable
/*  82*/        catch(String s)
                {
/*  83*/            throw new JsonException(s.getMessage(), s.getCause());
                }
/*  85*/        return write(jsonvalue);
            }

            public JsonGenerator write(String s, String s1)
            {
/*  91*/        try
                {
/*  91*/            writer.writeName(s).writeValue(s1);
                }
                // Misplaced declaration of an exception variable
/*  92*/        catch(String s)
                {
/*  93*/            _wrapAndThrow(s);
                }
/*  95*/        return this;
            }

            public JsonGenerator write(String s, BigInteger biginteger)
            {
/* 101*/        try
                {
/* 101*/            writer.writeName(s).writeValue(biginteger);
                }
                // Misplaced declaration of an exception variable
/* 102*/        catch(String s)
                {
/* 103*/            _wrapAndThrow(s);
                }
/* 105*/        return this;
            }

            public JsonGenerator write(String s, BigDecimal bigdecimal)
            {
/* 111*/        try
                {
/* 111*/            writer.writeName(s).writeValue(bigdecimal);
                }
                // Misplaced declaration of an exception variable
/* 112*/        catch(String s)
                {
/* 113*/            _wrapAndThrow(s);
                }
/* 115*/        return this;
            }

            public JsonGenerator write(String s, int i)
            {
/* 121*/        try
                {
/* 121*/            writer.writeName(s).writeValue(i);
                }
                // Misplaced declaration of an exception variable
/* 122*/        catch(String s)
                {
/* 123*/            _wrapAndThrow(s);
                }
/* 125*/        return this;
            }

            public JsonGenerator write(String s, long l)
            {
/* 131*/        try
                {
/* 131*/            writer.writeName(s).writeValue(l);
                }
                // Misplaced declaration of an exception variable
/* 132*/        catch(String s)
                {
/* 133*/            _wrapAndThrow(s);
                }
/* 135*/        return this;
            }

            public JsonGenerator write(String s, double d)
            {
/* 141*/        try
                {
/* 141*/            writer.writeName(s).writeValue(d);
                }
                // Misplaced declaration of an exception variable
/* 142*/        catch(String s)
                {
/* 143*/            _wrapAndThrow(s);
                }
/* 145*/        return this;
            }

            public JsonGenerator write(String s, boolean flag)
            {
/* 151*/        try
                {
/* 151*/            writer.writeName(s).writeValue(flag);
                }
                // Misplaced declaration of an exception variable
/* 152*/        catch(String s)
                {
/* 153*/            _wrapAndThrow(s);
                }
/* 155*/        return this;
            }

            public JsonGenerator writeNull(String s)
            {
/* 161*/        try
                {
/* 161*/            writer.writeName(s).writeNull();
                }
                // Misplaced declaration of an exception variable
/* 162*/        catch(String s)
                {
/* 163*/            _wrapAndThrow(s);
                }
/* 165*/        return this;
            }

            public JsonGenerator writeEnd()
            {
/* 170*/        JsonType jsontype = (JsonType)_ctx.pop();
/* 172*/        try
                {
/* 172*/            if(JsonType.OBJECT == jsontype)
/* 172*/                writer.endObject();
/* 173*/            else
/* 173*/            if(JsonType.ARRAY == jsontype)
/* 173*/                writer.endArray();
/* 174*/            else
/* 174*/                throw new JsonGenerationException("Must call writeStartObject or writeStartArray before calling writeEnd.");
                }
/* 176*/        catch(JsonStreamException jsonstreamexception)
                {
/* 177*/            throw new JsonGenerationException(jsonstreamexception.getMessage(), jsonstreamexception.getCause());
                }
/* 179*/        return this;
            }

            public JsonGenerator write(JsonValue jsonvalue)
            {
/* 184*/        Object obj = jsonvalue.getValueType();
/* 185*/        if(javax.json.JsonValue.ValueType.ARRAY == obj)
                {
/* 186*/            writeStartArray();
/* 187*/            for(jsonvalue = (jsonvalue = (JsonArray)jsonvalue).iterator(); jsonvalue.hasNext(); write(((JsonValue) (obj))))
/* 188*/                obj = (JsonValue)jsonvalue.next();

/* 190*/            writeEnd();
                } else
/* 191*/        if(javax.json.JsonValue.ValueType.OBJECT == obj)
                {
/* 192*/            writeStartObject();
/* 193*/            for(jsonvalue = (jsonvalue = (JsonObject)jsonvalue).entrySet().iterator(); jsonvalue.hasNext(); write((String)((java.util.Map.Entry) (obj)).getKey(), (JsonValue)((java.util.Map.Entry) (obj)).getValue()))
/* 194*/                obj = (java.util.Map.Entry)jsonvalue.next();

/* 196*/            writeEnd();
                } else
/* 197*/        if(javax.json.JsonValue.ValueType.FALSE == obj)
/* 198*/            write(false);
/* 199*/        else
/* 199*/        if(javax.json.JsonValue.ValueType.TRUE == obj)
/* 200*/            write(true);
/* 201*/        else
/* 201*/        if(javax.json.JsonValue.ValueType.NULL == obj)
/* 202*/            writeNull();
/* 203*/        else
/* 203*/        if(javax.json.JsonValue.ValueType.STRING == obj)
/* 204*/            write(((JsonString)jsonvalue).getString());
/* 205*/        else
/* 205*/        if(javax.json.JsonValue.ValueType.NUMBER == obj)
/* 206*/            if((jsonvalue = (JsonNumber)jsonvalue).isIntegral())
/* 207*/                write(jsonvalue.bigIntegerValueExact());
/* 208*/            else
/* 208*/                write(jsonvalue.bigDecimalValue());
/* 210*/        return this;
            }

            public JsonGenerator write(String s)
            {
/* 216*/        try
                {
/* 216*/            writer.writeValue(s);
                }
                // Misplaced declaration of an exception variable
/* 217*/        catch(String s)
                {
/* 218*/            _wrapAndThrow(s);
                }
/* 220*/        return this;
            }

            public JsonGenerator write(BigDecimal bigdecimal)
            {
/* 226*/        try
                {
/* 226*/            writer.writeValue(bigdecimal);
                }
                // Misplaced declaration of an exception variable
/* 227*/        catch(BigDecimal bigdecimal)
                {
/* 228*/            _wrapAndThrow(bigdecimal);
                }
/* 230*/        return this;
            }

            public JsonGenerator write(BigInteger biginteger)
            {
/* 236*/        try
                {
/* 236*/            writer.writeValue(biginteger);
                }
                // Misplaced declaration of an exception variable
/* 237*/        catch(BigInteger biginteger)
                {
/* 238*/            _wrapAndThrow(biginteger);
                }
/* 240*/        return this;
            }

            public JsonGenerator write(int i)
            {
/* 246*/        try
                {
/* 246*/            writer.writeValue(i);
                }
                // Misplaced declaration of an exception variable
/* 247*/        catch(int i)
                {
/* 248*/            _wrapAndThrow(i);
                }
/* 250*/        return this;
            }

            public JsonGenerator write(long l)
            {
/* 256*/        try
                {
/* 256*/            writer.writeValue(l);
                }
                // Misplaced declaration of an exception variable
/* 257*/        catch(long l)
                {
/* 258*/            _wrapAndThrow(l);
                }
/* 260*/        return this;
            }

            public JsonGenerator write(double d)
            {
/* 266*/        writer.writeValue(d);
/* 271*/        break MISSING_BLOCK_LABEL_23;
/* 267*/        JVM INSTR dup ;
/* 268*/        d;
/* 268*/        throw ;
/* 269*/        d;
/* 270*/        _wrapAndThrow(d);
/* 272*/        return this;
            }

            public JsonGenerator write(boolean flag)
            {
/* 278*/        try
                {
/* 278*/            writer.writeValue(flag);
                }
                // Misplaced declaration of an exception variable
/* 279*/        catch(boolean flag)
                {
/* 280*/            _wrapAndThrow(flag);
                }
/* 282*/        return this;
            }

            public JsonGenerator writeNull()
            {
/* 288*/        try
                {
/* 288*/            writer.writeNull();
                }
/* 289*/        catch(Exception exception)
                {
/* 290*/            _wrapAndThrow(exception);
                }
/* 292*/        return this;
            }

            public void close()
            {
/* 297*/        flush();
/* 298*/        writer.close();
            }

            public void flush()
            {
/* 303*/        writer.flush();
            }

            private void _wrapAndThrow(Exception exception)
            {
/* 308*/        if(exception instanceof JsonStreamException)
/* 309*/            exception = new JsonGenerationException(exception.getMessage(), exception);
/* 310*/        else
/* 310*/            exception = new JsonException(exception.getMessage(), exception);
/* 312*/        throw (JsonException)JsonStreamException.niceTrace(exception);
            }

            public static final String SKIP_NULL = "GensonJsonGenerator.skipNull";
            public static final String HTML_SAFE = "GensonJsonGenerator.htmlSafe";
            private final ObjectWriter writer;
            private final Deque _ctx = new ArrayDeque();
}
