// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GensonJsonReaderFactory.java

package com.owlike.genson.ext.jsr353;

import com.owlike.genson.EncodingAwareReaderFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import javax.json.*;
import javax.json.stream.JsonParser;

// Referenced classes of package com.owlike.genson.ext.jsr353:
//            GensonJsonBuilderFactory, GensonJsonParserFactory

public class GensonJsonReaderFactory
    implements JsonReaderFactory
{

            public GensonJsonReaderFactory()
            {
/*  22*/        this(Collections.emptyMap());
            }

            public GensonJsonReaderFactory(Map map)
            {
/*  19*/        encodingAwareReaderFactory = new EncodingAwareReaderFactory();
/*  26*/        parserFactory = new GensonJsonParserFactory(map);
/*  27*/        builderFactory = new GensonJsonBuilderFactory();
            }

            public JsonReader createReader(final Reader reader)
            {
/*  32*/        return new JsonReader() {

                    public JsonStructure read()
                    {
/*  38*/                checkNotReadedAndRead();
/*  40*/                if(parser.hasNext())
                        {
/*  41*/                    javax.json.stream.JsonParser.Event event = parser.next();
/*  42*/                    if(javax.json.stream.JsonParser.Event.START_OBJECT == event)
/*  43*/                        return read(builderFactory.createObjectBuilder()).build();
/*  44*/                    if(javax.json.stream.JsonParser.Event.START_ARRAY == event)
/*  45*/                        return read(builderFactory.createArrayBuilder()).build();
/*  46*/                    else
/*  46*/                        throw new JsonException((new StringBuilder("Expected START_OBJECT or START_ARRAY but got ")).append(event).toString());
                        } else
                        {
/*  49*/                    throw new JsonException("Empty stream");
                        }
                    }

                    public JsonObject readObject()
                    {
/*  54*/                checkNotReadedAndRead();
/*  56*/                if(parser.hasNext())
                        {
/*  57*/                    javax.json.stream.JsonParser.Event event = parser.next();
/*  58*/                    if(javax.json.stream.JsonParser.Event.START_OBJECT == event)
/*  59*/                        return read(builderFactory.createObjectBuilder()).build();
/*  60*/                    else
/*  60*/                        throw new JsonException((new StringBuilder("Expected ")).append(javax.json.stream.JsonParser.Event.START_OBJECT).append(" but got ").append(event).toString());
                        } else
                        {
/*  63*/                    throw new JsonException("Empty stream");
                        }
                    }

                    public JsonArray readArray()
                    {
/*  68*/                checkNotReadedAndRead();
/*  70*/                if(parser.hasNext())
                        {
/*  71*/                    javax.json.stream.JsonParser.Event event = parser.next();
/*  72*/                    if(javax.json.stream.JsonParser.Event.START_ARRAY == event)
/*  73*/                        return read(builderFactory.createArrayBuilder()).build();
/*  74*/                    else
/*  74*/                        throw new JsonException((new StringBuilder("Expected ")).append(javax.json.stream.JsonParser.Event.START_ARRAY).append(" but got ").append(event).toString());
                        } else
                        {
/*  77*/                    throw new JsonException("Empty stream");
                        }
                    }

                    private JsonArrayBuilder read(JsonArrayBuilder jsonarraybuilder)
                    {
/*  81*/                do
                        {
/*  81*/                    if(!parser.hasNext())
/*  82*/                        break;
/*  82*/                    javax.json.stream.JsonParser.Event event = parser.next();
                    static class _cls2
                    {

                                static final int $SwitchMap$javax$json$stream$JsonParser$Event[];

                                static 
                                {
/*  83*/                            $SwitchMap$javax$json$stream$JsonParser$Event = new int[javax.json.stream.JsonParser.Event.values().length];
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_STRING.ordinal()] = 1;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_NUMBER.ordinal()] = 2;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_NULL.ordinal()] = 3;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_FALSE.ordinal()] = 4;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_TRUE.ordinal()] = 5;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.START_OBJECT.ordinal()] = 6;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.START_ARRAY.ordinal()] = 7;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.END_ARRAY.ordinal()] = 8;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.KEY_NAME.ordinal()] = 9;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
/*  83*/                            try
                                    {
/*  83*/                                $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.END_OBJECT.ordinal()] = 10;
                                    }
/*  83*/                            catch(NoSuchFieldError _ex) { }
                                }
                    }

/*  83*/                    switch(_cls2..SwitchMap.javax.json.stream.JsonParser.Event[event.ordinal()])
                            {
/*  85*/                    case 1: // '\001'
/*  85*/                        jsonarraybuilder.add(parser.getString());
                                break;

/*  88*/                    case 2: // '\002'
/*  88*/                        if(parser.isIntegralNumber())
/*  88*/                            jsonarraybuilder.add(parser.getLong());
/*  89*/                        else
/*  89*/                            jsonarraybuilder.add(parser.getBigDecimal());
                                break;

/*  92*/                    case 3: // '\003'
/*  92*/                        jsonarraybuilder.addNull();
                                break;

/*  95*/                    case 4: // '\004'
/*  95*/                        jsonarraybuilder.add(JsonValue.FALSE);
                                break;

/*  98*/                    case 5: // '\005'
/*  98*/                        jsonarraybuilder.add(JsonValue.TRUE);
                                break;

/* 101*/                    case 6: // '\006'
/* 101*/                        jsonarraybuilder.add(read(builderFactory.createObjectBuilder()));
                                break;

/* 106*/                    case 7: // '\007'
/* 106*/                        jsonarraybuilder.add(read(builderFactory.createArrayBuilder()));
                                break;

/* 111*/                    case 8: // '\b'
/* 111*/                        return jsonarraybuilder;

/* 113*/                    default:
/* 113*/                        throw new JsonException((new StringBuilder("Unexpected event ")).append(event).toString());
                            }
                        } while(true);
/* 117*/                throw new IllegalStateException();
                    }

                    private JsonObjectBuilder read(JsonObjectBuilder jsonobjectbuilder)
                    {
/* 121*/                String s = null;
/* 123*/                do
                        {
/* 123*/                    if(!parser.hasNext())
/* 124*/                        break;
/* 124*/                    javax.json.stream.JsonParser.Event event = parser.next();
/* 125*/                    switch(_cls2..SwitchMap.javax.json.stream.JsonParser.Event[event.ordinal()])
                            {
/* 127*/                    case 9: // '\t'
/* 127*/                        s = parser.getString();
                                break;

/* 130*/                    case 1: // '\001'
/* 130*/                        jsonobjectbuilder.add(s, parser.getString());
                                break;

/* 133*/                    case 2: // '\002'
/* 133*/                        if(parser.isIntegralNumber())
/* 133*/                            jsonobjectbuilder.add(s, parser.getLong());
/* 134*/                        else
/* 134*/                            jsonobjectbuilder.add(s, parser.getBigDecimal());
                                break;

/* 137*/                    case 3: // '\003'
/* 137*/                        jsonobjectbuilder.addNull(s);
                                break;

/* 140*/                    case 4: // '\004'
/* 140*/                        jsonobjectbuilder.add(s, JsonValue.FALSE);
                                break;

/* 143*/                    case 5: // '\005'
/* 143*/                        jsonobjectbuilder.add(s, JsonValue.TRUE);
                                break;

/* 146*/                    case 6: // '\006'
/* 146*/                        jsonobjectbuilder.add(s, read(builderFactory.createObjectBuilder()));
                                break;

/* 151*/                    case 7: // '\007'
/* 151*/                        jsonobjectbuilder.add(s, read(builderFactory.createArrayBuilder()));
                                break;

/* 156*/                    case 10: // '\n'
/* 156*/                        return jsonobjectbuilder;

/* 158*/                    case 8: // '\b'
/* 158*/                    default:
/* 158*/                        throw new JsonException((new StringBuilder("Unknown Event ")).append(event).toString());
                            }
                        } while(true);
/* 162*/                throw new IllegalStateException();
                    }

                    public void close()
                    {
/* 167*/                parser.close();
                    }

                    private void checkNotReadedAndRead()
                    {
/* 171*/                if(readed)
                        {
/* 171*/                    throw new IllegalStateException();
                        } else
                        {
/* 172*/                    readed = true;
/* 173*/                    return;
                        }
                    }

                    private final JsonParser parser;
                    private boolean readed;
                    final Reader val$reader;
                    final GensonJsonReaderFactory this$0;

                    
                    {
/*  32*/                this$0 = GensonJsonReaderFactory.this;
/*  32*/                reader = reader1;
/*  32*/                super();
/*  33*/                parser = parserFactory.createParser(reader);
/*  34*/                readed = false;
                    }
        };
            }

            public JsonReader createReader(InputStream inputstream)
            {
/* 180*/        return createReader(encodingAwareReaderFactory.createReader(inputstream));
/* 181*/        inputstream;
/* 182*/        throw new JsonException("Failed to detect encoding.", inputstream);
            }

            public JsonReader createReader(InputStream inputstream, Charset charset)
            {
/* 188*/        return createReader(((Reader) (new InputStreamReader(inputstream, charset))));
            }

            public Map getConfigInUse()
            {
/* 193*/        return parserFactory.getConfigInUse();
            }

            private final GensonJsonParserFactory parserFactory;
            private final GensonJsonBuilderFactory builderFactory;
            private final EncodingAwareReaderFactory encodingAwareReaderFactory;


}
