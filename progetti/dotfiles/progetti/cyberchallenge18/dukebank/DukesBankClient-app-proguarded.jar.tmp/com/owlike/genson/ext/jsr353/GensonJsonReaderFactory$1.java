// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonReaderFactory.java

package com.owlike.genson.ext.jsr353;

import java.io.Reader;
import javax.json.*;
import javax.json.stream.JsonParser;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonBuilderFactory, GensonJsonParserFactory, GensonJsonReaderFactory

class readed
    implements JsonReader
{

            public JsonStructure read()
            {
/*  38*/        checkNotReadedAndRead();
/*  40*/        if(parser.hasNext())
                {
/*  41*/            javax.json.stream.rFactory rfactory = parser.next();
/*  42*/            if(javax.json.stream.ECT == rfactory)
/*  43*/                return read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createObjectBuilder()).build();
/*  44*/            if(javax.json.stream.AY == rfactory)
/*  45*/                return read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createArrayBuilder()).build();
/*  46*/            else
/*  46*/                throw new JsonException((new StringBuilder("Expected START_OBJECT or START_ARRAY but got ")).append(rfactory).toString());
                } else
                {
/*  49*/            throw new JsonException("Empty stream");
                }
            }

            public JsonObject readObject()
            {
/*  54*/        checkNotReadedAndRead();
/*  56*/        if(parser.hasNext())
                {
/*  57*/            javax.json.stream.rFactory rfactory = parser.next();
/*  58*/            if(javax.json.stream.ECT == rfactory)
/*  59*/                return read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createObjectBuilder()).build();
/*  60*/            else
/*  60*/                throw new JsonException((new StringBuilder("Expected ")).append(javax.json.stream.ECT).append(" but got ").append(rfactory).toString());
                } else
                {
/*  63*/            throw new JsonException("Empty stream");
                }
            }

            public JsonArray readArray()
            {
/*  68*/        checkNotReadedAndRead();
/*  70*/        if(parser.hasNext())
                {
/*  71*/            javax.json.stream.rFactory rfactory = parser.next();
/*  72*/            if(javax.json.stream.AY == rfactory)
/*  73*/                return read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createArrayBuilder()).build();
/*  74*/            else
/*  74*/                throw new JsonException((new StringBuilder("Expected ")).append(javax.json.stream.AY).append(" but got ").append(rfactory).toString());
                } else
                {
/*  77*/            throw new JsonException("Empty stream");
                }
            }

            private JsonArrayBuilder read(JsonArrayBuilder jsonarraybuilder)
            {
/*  81*/        do
                {
/*  81*/            if(!parser.hasNext())
/*  82*/                break;
/*  82*/            javax.json.stream.rFactory rfactory = parser.next();
/*  83*/            switch(.SwitchMap.javax.json.stream.JsonParser.Event[rfactory.t()])
                    {
/*  85*/            case 1: // '\001'
/*  85*/                jsonarraybuilder.add(parser.getString());
                        break;

/*  88*/            case 2: // '\002'
/*  88*/                if(parser.isIntegralNumber())
/*  88*/                    jsonarraybuilder.add(parser.getLong());
/*  89*/                else
/*  89*/                    jsonarraybuilder.add(parser.getBigDecimal());
                        break;

/*  92*/            case 3: // '\003'
/*  92*/                jsonarraybuilder.addNull();
                        break;

/*  95*/            case 4: // '\004'
/*  95*/                jsonarraybuilder.add(JsonValue.FALSE);
                        break;

/*  98*/            case 5: // '\005'
/*  98*/                jsonarraybuilder.add(JsonValue.TRUE);
                        break;

/* 101*/            case 6: // '\006'
/* 101*/                jsonarraybuilder.add(read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createObjectBuilder()));
                        break;

/* 106*/            case 7: // '\007'
/* 106*/                jsonarraybuilder.add(read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createArrayBuilder()));
                        break;

/* 111*/            case 8: // '\b'
/* 111*/                return jsonarraybuilder;

/* 113*/            default:
/* 113*/                throw new JsonException((new StringBuilder("Unexpected event ")).append(rfactory).toString());
                    }
                } while(true);
/* 117*/        throw new IllegalStateException();
            }

            private JsonObjectBuilder read(JsonObjectBuilder jsonobjectbuilder)
            {
/* 121*/        String s = null;
/* 123*/        do
                {
/* 123*/            if(!parser.hasNext())
/* 124*/                break;
/* 124*/            javax.json.stream.rFactory rfactory = parser.next();
/* 125*/            switch(.SwitchMap.javax.json.stream.JsonParser.Event[rfactory.t()])
                    {
/* 127*/            case 9: // '\t'
/* 127*/                s = parser.getString();
                        break;

/* 130*/            case 1: // '\001'
/* 130*/                jsonobjectbuilder.add(s, parser.getString());
                        break;

/* 133*/            case 2: // '\002'
/* 133*/                if(parser.isIntegralNumber())
/* 133*/                    jsonobjectbuilder.add(s, parser.getLong());
/* 134*/                else
/* 134*/                    jsonobjectbuilder.add(s, parser.getBigDecimal());
                        break;

/* 137*/            case 3: // '\003'
/* 137*/                jsonobjectbuilder.addNull(s);
                        break;

/* 140*/            case 4: // '\004'
/* 140*/                jsonobjectbuilder.add(s, JsonValue.FALSE);
                        break;

/* 143*/            case 5: // '\005'
/* 143*/                jsonobjectbuilder.add(s, JsonValue.TRUE);
                        break;

/* 146*/            case 6: // '\006'
/* 146*/                jsonobjectbuilder.add(s, read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createObjectBuilder()));
                        break;

/* 151*/            case 7: // '\007'
/* 151*/                jsonobjectbuilder.add(s, read(GensonJsonReaderFactory.access$100(GensonJsonReaderFactory.this).createArrayBuilder()));
                        break;

/* 156*/            case 10: // '\n'
/* 156*/                return jsonobjectbuilder;

/* 158*/            case 8: // '\b'
/* 158*/            default:
/* 158*/                throw new JsonException((new StringBuilder("Unknown Event ")).append(rfactory).toString());
                    }
                } while(true);
/* 162*/        throw new IllegalStateException();
            }

            public void close()
            {
/* 167*/        parser.close();
            }

            private void checkNotReadedAndRead()
            {
/* 171*/        if(readed)
                {
/* 171*/            throw new IllegalStateException();
                } else
                {
/* 172*/            readed = true;
/* 173*/            return;
                }
            }

            private final JsonParser parser;
            private boolean readed;
            final Reader val$reader;
            final GensonJsonReaderFactory this$0;

            ()
            {
/*  32*/        this$0 = final_gensonjsonreaderfactory;
/*  32*/        val$reader = Reader.this;
/*  32*/        super();
/*  33*/        parser = GensonJsonReaderFactory.access$000(GensonJsonReaderFactory.this).createParser(val$reader);
/*  34*/        readed = false;
            }
}
