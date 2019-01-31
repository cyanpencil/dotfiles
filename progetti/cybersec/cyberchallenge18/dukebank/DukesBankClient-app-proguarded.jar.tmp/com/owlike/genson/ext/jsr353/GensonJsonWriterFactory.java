// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonWriterFactory.java

package com.owlike.genson.ext.jsr353;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonGeneratorFactory

public class GensonJsonWriterFactory
    implements JsonWriterFactory
{

            public GensonJsonWriterFactory()
            {
/*  18*/        this(Collections.emptyMap());
            }

            public GensonJsonWriterFactory(Map map)
            {
/*  22*/        generatorFactory = new GensonJsonGeneratorFactory(map);
            }

            public JsonWriter createWriter(final Writer writer)
            {
/*  27*/        return new JsonWriter() {

                    public void writeArray(JsonArray jsonarray)
                    {
/*  33*/                checkWritten();
/*  34*/                generator.write(jsonarray);
                    }

                    public void writeObject(JsonObject jsonobject)
                    {
/*  39*/                checkWritten();
/*  40*/                generator.write(jsonobject);
                    }

                    public void write(JsonStructure jsonstructure)
                    {
/*  45*/                checkWritten();
/*  46*/                generator.write(jsonstructure);
                    }

                    public void close()
                    {
/*  51*/                generator.close();
                    }

                    private void checkWritten()
                    {
/*  55*/                if(written)
                        {
/*  55*/                    throw new IllegalStateException();
                        } else
                        {
/*  56*/                    written = true;
/*  57*/                    return;
                        }
                    }

                    private final JsonGenerator generator;
                    private boolean written;
                    final Writer val$writer;
                    final GensonJsonWriterFactory this$0;

                    
                    {
/*  27*/                this$0 = GensonJsonWriterFactory.this;
/*  27*/                writer = writer1;
/*  27*/                super();
/*  28*/                generator = generatorFactory.createGenerator(writer);
/*  29*/                written = false;
                    }
        };
            }

            public JsonWriter createWriter(OutputStream outputstream)
            {
/*  64*/        return createWriter(((Writer) (new OutputStreamWriter(outputstream, "UTF-8"))));
/*  65*/        outputstream;
/*  66*/        throw new JsonException("Charset UTF-8 is not supported.", outputstream);
            }

            public JsonWriter createWriter(OutputStream outputstream, Charset charset)
            {
/*  72*/        return createWriter(((Writer) (new OutputStreamWriter(outputstream, charset))));
            }

            public Map getConfigInUse()
            {
/*  77*/        return generatorFactory.getConfigInUse();
            }

            private final JsonGeneratorFactory generatorFactory;

}
