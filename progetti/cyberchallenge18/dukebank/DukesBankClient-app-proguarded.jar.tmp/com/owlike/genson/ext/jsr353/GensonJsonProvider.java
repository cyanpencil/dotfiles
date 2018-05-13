// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonProvider.java

package com.owlike.genson.ext.jsr353;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import javax.json.*;
import javax.json.spi.JsonProvider;
import javax.json.stream.*;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonBuilderFactory, GensonJsonGeneratorFactory, GensonJsonParserFactory, GensonJsonReaderFactory, 
//            GensonJsonWriterFactory

public class GensonJsonProvider extends JsonProvider
{

            public GensonJsonProvider()
            {
            }

            public JsonParser createParser(Reader reader)
            {
/*  27*/        return parserFactory.createParser(reader);
            }

            public JsonParser createParser(InputStream inputstream)
            {
/*  32*/        return parserFactory.createParser(inputstream);
            }

            public JsonParserFactory createParserFactory(Map map)
            {
/*  37*/        return new GensonJsonParserFactory(map);
            }

            public JsonGenerator createGenerator(Writer writer)
            {
/*  42*/        return generatorFactory.createGenerator(writer);
            }

            public JsonGenerator createGenerator(OutputStream outputstream)
            {
/*  47*/        return generatorFactory.createGenerator(outputstream);
            }

            public JsonGeneratorFactory createGeneratorFactory(Map map)
            {
/*  52*/        return new GensonJsonGeneratorFactory(map);
            }

            public JsonReader createReader(Reader reader)
            {
/*  57*/        return readerFactory.createReader(reader);
            }

            public JsonReader createReader(InputStream inputstream)
            {
/*  62*/        return readerFactory.createReader(inputstream);
            }

            public JsonWriter createWriter(Writer writer)
            {
/*  67*/        return writerFactory.createWriter(writer);
            }

            public JsonWriter createWriter(OutputStream outputstream)
            {
/*  72*/        return writerFactory.createWriter(outputstream);
            }

            public JsonWriterFactory createWriterFactory(Map map)
            {
/*  77*/        return new GensonJsonWriterFactory(map);
            }

            public JsonReaderFactory createReaderFactory(Map map)
            {
/*  82*/        return new GensonJsonReaderFactory(map);
            }

            public JsonObjectBuilder createObjectBuilder()
            {
/*  87*/        return builderFactory.createObjectBuilder();
            }

            public JsonArrayBuilder createArrayBuilder()
            {
/*  92*/        return builderFactory.createArrayBuilder();
            }

            public JsonBuilderFactory createBuilderFactory(Map map)
            {
/*  97*/        return builderFactory;
            }

            private final GensonJsonGeneratorFactory generatorFactory = new GensonJsonGeneratorFactory();
            private final GensonJsonParserFactory parserFactory = new GensonJsonParserFactory();
            private final GensonJsonReaderFactory readerFactory = new GensonJsonReaderFactory(Collections.singletonMap("GensonJsonParser.strictDoubleParse", Boolean.valueOf(true)));
            private final GensonJsonWriterFactory writerFactory = new GensonJsonWriterFactory();
            private final GensonJsonBuilderFactory builderFactory = new GensonJsonBuilderFactory();
}
