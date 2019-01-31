// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JSR353Bundle.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.*;
import com.owlike.genson.ext.GensonBundle;
import com.owlike.genson.stream.*;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.*;
import javax.json.*;
import javax.json.spi.JsonProvider;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonGenerator

public class JSR353Bundle extends GensonBundle
{
    public class JsonValueConverter
        implements Converter
    {

                public void serialize(JsonValue jsonvalue, ObjectWriter objectwriter, Context context)
                {
/*  44*/            javax.json.JsonValue.ValueType valuetype = jsonvalue.getValueType();
/*  45*/            if(javax.json.JsonValue.ValueType.STRING == valuetype)
                    {
/*  45*/                objectwriter.writeValue(((JsonString)jsonvalue).getString());
/*  45*/                return;
                    }
/*  46*/            if(javax.json.JsonValue.ValueType.ARRAY == valuetype)
                    {
/*  46*/                writeArray((JsonArray)jsonvalue, objectwriter, context);
/*  46*/                return;
                    }
/*  47*/            if(javax.json.JsonValue.ValueType.OBJECT == valuetype)
                    {
/*  47*/                writeObject((JsonObject)jsonvalue, objectwriter, context);
/*  47*/                return;
                    }
/*  48*/            if(javax.json.JsonValue.ValueType.NULL == valuetype)
                    {
/*  48*/                objectwriter.writeNull();
/*  48*/                return;
                    }
/*  49*/            if(javax.json.JsonValue.ValueType.NUMBER == valuetype)
                    {
/*  50*/                if((jsonvalue = (JsonNumber)jsonvalue).isIntegral())
                        {
/*  51*/                    objectwriter.writeValue(jsonvalue.longValue());
                        } else
                        {
/*  52*/                    objectwriter.writeValue(jsonvalue.bigDecimalValue());
/*  53*/                    return;
                        }
                    } else
                    {
/*  53*/                if(javax.json.JsonValue.ValueType.FALSE == valuetype)
                        {
/*  53*/                    objectwriter.writeValue(false);
/*  53*/                    return;
                        }
/*  54*/                if(javax.json.JsonValue.ValueType.TRUE == valuetype)
                        {
/*  54*/                    objectwriter.writeValue(true);
/*  54*/                    return;
                        } else
                        {
/*  56*/                    throw new IllegalStateException((new StringBuilder("Unknown ValueType ")).append(valuetype).toString());
                        }
                    }
                }

                private void writeArray(JsonArray jsonarray, ObjectWriter objectwriter, Context context)
                {
/*  61*/            objectwriter.beginArray();
                    JsonValue jsonvalue;
/*  62*/            for(jsonarray = jsonarray.iterator(); jsonarray.hasNext(); serialize(jsonvalue, objectwriter, context))
/*  62*/                jsonvalue = (JsonValue)jsonarray.next();

/*  64*/            objectwriter.endArray();
                }

                private void writeObject(JsonObject jsonobject, ObjectWriter objectwriter, Context context)
                {
/*  68*/            objectwriter.beginObject();
                    java.util.Map.Entry entry;
/*  69*/            for(jsonobject = jsonobject.entrySet().iterator(); jsonobject.hasNext(); serialize((JsonValue)entry.getValue(), objectwriter, context))
                    {
/*  69*/                entry = (java.util.Map.Entry)jsonobject.next();
/*  70*/                objectwriter.writeName((String)entry.getKey());
                    }

/*  73*/            objectwriter.endObject();
                }

                public JsonValue deserialize(ObjectReader objectreader, Context context)
                {
/*  78*/            ValueType valuetype = objectreader.getValueType();
/*  79*/            if(ValueType.OBJECT == valuetype)
/*  80*/                return deserObject(objectreader, context);
/*  81*/            if(ValueType.ARRAY == valuetype)
/*  82*/                return deserArray(objectreader, context);
/*  86*/            if(ValueType.STRING == valuetype)
/*  87*/                return (JsonValue)JSR353Bundle.factory.createArrayBuilder().add(objectreader.valueAsString()).build().get(0);
/*  88*/            if(ValueType.BOOLEAN == valuetype)
/*  89*/                if(objectreader.valueAsBoolean())
/*  89*/                    return JsonValue.TRUE;
/*  89*/                else
/*  89*/                    return JsonValue.FALSE;
/*  90*/            if(ValueType.NULL == valuetype)
/*  91*/                return JsonValue.NULL;
/*  92*/            if(ValueType.INTEGER == valuetype)
/*  93*/                return (JsonValue)JSR353Bundle.factory.createArrayBuilder().add(objectreader.valueAsLong()).build().get(0);
/*  94*/            if(ValueType.DOUBLE == valuetype)
/*  95*/                return (JsonValue)JSR353Bundle.factory.createArrayBuilder().add(objectreader.valueAsDouble()).build().get(0);
/*  99*/            else
/*  99*/                throw new IllegalStateException((new StringBuilder("Unsupported ValueType ")).append(valuetype).toString());
                }

                public JsonValue deserObject(ObjectReader objectreader, Context context)
                {
/* 103*/            JsonObjectBuilder jsonobjectbuilder = JSR353Bundle.factory.createObjectBuilder();
/* 104*/            objectreader.beginObject();
/* 106*/            while(objectreader.hasNext()) 
                    {
/* 107*/                ValueType valuetype = objectreader.next();
/* 108*/                String s = objectreader.name();
/* 109*/                if(ValueType.STRING == valuetype)
/* 110*/                    jsonobjectbuilder.add(s, objectreader.valueAsString());
/* 111*/                else
/* 111*/                if(ValueType.BOOLEAN == valuetype)
/* 112*/                    jsonobjectbuilder.add(s, objectreader.valueAsBoolean());
/* 113*/                else
/* 113*/                if(ValueType.NULL == valuetype)
/* 114*/                    jsonobjectbuilder.addNull(s);
/* 115*/                else
/* 115*/                if(ValueType.INTEGER == valuetype)
/* 116*/                    jsonobjectbuilder.add(s, objectreader.valueAsLong());
/* 117*/                else
/* 117*/                if(ValueType.DOUBLE == valuetype)
/* 118*/                    jsonobjectbuilder.add(s, objectreader.valueAsDouble());
/* 119*/                else
/* 119*/                    jsonobjectbuilder.add(s, deserialize(objectreader, context));
                    }
/* 122*/            objectreader.endObject();
/* 123*/            return jsonobjectbuilder.build();
                }

                public JsonValue deserArray(ObjectReader objectreader, Context context)
                {
/* 127*/            JsonArrayBuilder jsonarraybuilder = JSR353Bundle.factory.createArrayBuilder();
/* 128*/            objectreader.beginArray();
/* 130*/            while(objectreader.hasNext()) 
                    {
/* 131*/                ValueType valuetype = objectreader.next();
/* 132*/                if(ValueType.STRING == valuetype)
/* 133*/                    jsonarraybuilder.add(objectreader.valueAsString());
/* 134*/                else
/* 134*/                if(ValueType.BOOLEAN == valuetype)
/* 135*/                    jsonarraybuilder.add(objectreader.valueAsBoolean());
/* 136*/                else
/* 136*/                if(ValueType.NULL == valuetype)
/* 137*/                    jsonarraybuilder.addNull();
/* 138*/                else
/* 138*/                if(ValueType.INTEGER == valuetype)
/* 139*/                    jsonarraybuilder.add(objectreader.valueAsLong());
/* 140*/                else
/* 140*/                if(ValueType.DOUBLE == valuetype)
/* 141*/                    jsonarraybuilder.add(objectreader.valueAsDouble());
/* 142*/                else
/* 142*/                    jsonarraybuilder.add(deserialize(objectreader, context));
                    }
/* 145*/            objectreader.endArray();
/* 146*/            return jsonarraybuilder.build();
                }

                public volatile Object deserialize(ObjectReader objectreader, Context context)
                    throws Exception
                {
/*  40*/            return deserialize(objectreader, context);
                }

                public volatile void serialize(Object obj, ObjectWriter objectwriter, Context context)
                    throws Exception
                {
/*  40*/            serialize((JsonValue)obj, objectwriter, context);
                }

                final JSR353Bundle this$0;

                public JsonValueConverter()
                {
/*  40*/            this$0 = JSR353Bundle.this;
/*  40*/            super();
                }
    }


            public JSR353Bundle()
            {
            }

            public void configure(GensonBuilder gensonbuilder)
            {
/*  32*/        gensonbuilder.withConverterFactory(new Factory() {

                    public Converter create(Type type, Genson genson)
                    {
/*  35*/                return new JsonValueConverter();
                    }

                    public volatile Object create(Type type, Genson genson)
                    {
/*  32*/                return create(type, genson);
                    }

                    final JSR353Bundle this$0;

                    
                    {
/*  32*/                this$0 = JSR353Bundle.this;
/*  32*/                super();
                    }
        });
            }

            static String toString(JsonValue jsonvalue)
            {
/* 151*/        StringWriter stringwriter = new StringWriter();
/* 152*/        Object obj = new JsonWriter(stringwriter);
/* 153*/        ((GensonJsonGenerator) (obj = new GensonJsonGenerator(((ObjectWriter) (obj))))).write(jsonvalue);
/* 155*/        ((GensonJsonGenerator) (obj)).close();
/* 156*/        return stringwriter.toString();
            }

            static boolean toBoolean(Map map, String s)
            {
/* 160*/        if(map == null)
/* 160*/            return false;
/* 162*/        if(map.containsKey(s))
                {
/* 163*/            if((map = ((Map) (map.get(s)))) instanceof Boolean)
/* 165*/                return ((Boolean)map).booleanValue();
/* 166*/            if(map instanceof String)
/* 167*/                return Boolean.parseBoolean((String)map);
/* 168*/            else
/* 168*/                return false;
                } else
                {
/* 169*/            return false;
                }
            }

            static final JsonBuilderFactory factory = JsonProvider.provider().createBuilderFactory(new HashMap());

}
