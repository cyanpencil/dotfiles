// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonParserFactory.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.EncodingAwareReaderFactory;
import com.owlike.genson.stream.JsonReader;
import com.owlike.genson.stream.JsonWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonGenerator, GensonJsonParser, JSR353Bundle

public class GensonJsonParserFactory
    implements JsonParserFactory
{

            public GensonJsonParserFactory()
            {
/*  21*/        encodingAwareReaderFactory = new EncodingAwareReaderFactory();
/*  24*/        strictDoubleParse = false;
            }

            public GensonJsonParserFactory(Map map)
            {
/*  21*/        encodingAwareReaderFactory = new EncodingAwareReaderFactory();
/*  28*/        strictDoubleParse = JSR353Bundle.toBoolean(map, "GensonJsonParser.strictDoubleParse");
            }

            public JsonParser createParser(Reader reader)
            {
/*  33*/        return new GensonJsonParser(new JsonReader(reader, strictDoubleParse, false));
            }

            public JsonParser createParser(InputStream inputstream)
            {
/*  40*/        return new GensonJsonParser(new JsonReader(encodingAwareReaderFactory.createReader(inputstream), strictDoubleParse, false));
/*  43*/        JVM INSTR pop ;
/*  44*/        throw new JsonException("Failed to detect encoding");
            }

            public JsonParser createParser(InputStream inputstream, Charset charset)
            {
/*  50*/        return new GensonJsonParser(new JsonReader(new InputStreamReader(inputstream, charset), strictDoubleParse, false));
            }

            public JsonParser createParser(JsonObject jsonobject)
            {
/*  55*/        return parserForJsonStructure(jsonobject);
            }

            public JsonParser createParser(JsonArray jsonarray)
            {
/*  60*/        return parserForJsonStructure(jsonarray);
            }

            private JsonParser parserForJsonStructure(JsonStructure jsonstructure)
            {
/*  65*/        StringWriter stringwriter = new StringWriter();
                GensonJsonGenerator gensonjsongenerator;
/*  66*/        (gensonjsongenerator = new GensonJsonGenerator(new JsonWriter(stringwriter))).write(jsonstructure);
/*  68*/        gensonjsongenerator.flush();
/*  70*/        return createParser(new StringReader(stringwriter.toString()));
            }

            public Map getConfigInUse()
            {
                HashMap hashmap;
/*  75*/        (hashmap = new HashMap()).put("GensonJsonParser.strictDoubleParse", Boolean.valueOf(strictDoubleParse));
/*  77*/        return hashmap;
            }

            private final boolean strictDoubleParse;
            private final EncodingAwareReaderFactory encodingAwareReaderFactory;
}
