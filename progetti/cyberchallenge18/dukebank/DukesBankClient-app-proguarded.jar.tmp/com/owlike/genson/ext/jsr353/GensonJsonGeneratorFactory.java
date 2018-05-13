// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonGeneratorFactory.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.stream.JsonWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonException;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonGenerator, JSR353Bundle

public class GensonJsonGeneratorFactory
    implements JsonGeneratorFactory
{

            public GensonJsonGeneratorFactory()
            {
/*  23*/        prettyPrint = false;
/*  24*/        htmlSafe = false;
/*  25*/        skipNull = false;
            }

            public GensonJsonGeneratorFactory(Map map)
            {
/*  29*/        prettyPrint = JSR353Bundle.toBoolean(map, "javax.json.stream.JsonGenerator.prettyPrinting");
/*  30*/        htmlSafe = JSR353Bundle.toBoolean(map, "GensonJsonGenerator.htmlSafe");
/*  31*/        skipNull = JSR353Bundle.toBoolean(map, "GensonJsonGenerator.skipNull");
            }

            public JsonGenerator createGenerator(Writer writer)
            {
/*  36*/        return new GensonJsonGenerator(new JsonWriter(writer, skipNull, htmlSafe, prettyPrint));
            }

            public JsonGenerator createGenerator(OutputStream outputstream)
            {
/*  42*/        return new GensonJsonGenerator(new JsonWriter(new OutputStreamWriter(outputstream, "UTF-8"), skipNull, htmlSafe, prettyPrint));
/*  44*/        outputstream;
/*  45*/        throw new JsonException("Charset UTF-8 is not supported.", outputstream);
            }

            public JsonGenerator createGenerator(OutputStream outputstream, Charset charset)
            {
/*  51*/        return new GensonJsonGenerator(new JsonWriter(new OutputStreamWriter(outputstream), skipNull, htmlSafe, prettyPrint));
            }

            public Map getConfigInUse()
            {
                HashMap hashmap;
/*  57*/        (hashmap = new HashMap()).put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(prettyPrint));
/*  59*/        hashmap.put("GensonJsonGenerator.htmlSafe", Boolean.valueOf(htmlSafe));
/*  60*/        hashmap.put("GensonJsonGenerator.skipNull", Boolean.valueOf(skipNull));
/*  61*/        return hashmap;
            }

            private final boolean prettyPrint;
            private final boolean htmlSafe;
            private final boolean skipNull;
}
