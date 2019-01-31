// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonWriterFactory.java

package com.owlike.genson.ext.jsr353;

import java.io.Writer;
import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonWriterFactory

class written
    implements JsonWriter
{

            public void writeArray(JsonArray jsonarray)
            {
/*  33*/        checkWritten();
/*  34*/        generator.write(jsonarray);
            }

            public void writeObject(JsonObject jsonobject)
            {
/*  39*/        checkWritten();
/*  40*/        generator.write(jsonobject);
            }

            public void write(JsonStructure jsonstructure)
            {
/*  45*/        checkWritten();
/*  46*/        generator.write(jsonstructure);
            }

            public void close()
            {
/*  51*/        generator.close();
            }

            private void checkWritten()
            {
/*  55*/        if(written)
                {
/*  55*/            throw new IllegalStateException();
                } else
                {
/*  56*/            written = true;
/*  57*/            return;
                }
            }

            private final JsonGenerator generator;
            private boolean written;
            final Writer val$writer;
            final GensonJsonWriterFactory this$0;

            ()
            {
/*  27*/        this$0 = final_gensonjsonwriterfactory;
/*  27*/        val$writer = Writer.this;
/*  27*/        super();
/*  28*/        generator = GensonJsonWriterFactory.access$000(GensonJsonWriterFactory.this).createGenerator(val$writer);
/*  29*/        written = false;
            }
}
