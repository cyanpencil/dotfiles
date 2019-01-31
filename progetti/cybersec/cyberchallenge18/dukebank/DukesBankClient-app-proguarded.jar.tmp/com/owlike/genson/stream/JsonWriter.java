// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonWriter.java

package com.owlike.genson.stream;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

// Referenced classes of package com.owlike.genson.stream:
//            Base64, JsonStreamException, JsonType, ObjectWriter

public class JsonWriter
    implements ObjectWriter
{
    class MetadataPair
    {

                final String name;
                final String value;
                final JsonWriter this$0;

                public MetadataPair(String s, String s1)
                {
/*  71*/            this$0 = JsonWriter.this;
/*  72*/            super();
/*  73*/            name = s;
/*  74*/            value = s1;
                }
    }


            public JsonWriter(Writer writer1)
            {
/*  79*/        this(writer1, false, false, false);
            }

            public JsonWriter(Writer writer1, boolean flag, boolean flag1, boolean flag2)
            {
/*  55*/        _ctx = new ArrayDeque(10);
/*  61*/        _buffer = new char[1024];
/*  62*/        _bufferSize = _buffer.length;
/*  63*/        _len = 0;
/*  65*/        _metadata = new ArrayList();
/*  84*/        writer = writer1;
/*  85*/        skipNull = flag;
/*  86*/        htmlSafe = flag1;
/*  87*/        indentation = flag2;
/*  88*/        _ctx.push(JsonType.EMPTY);
            }

            public JsonType enclosingType()
            {
/*  92*/        return (JsonType)_ctx.peek();
            }

            public void close()
            {
/*  96*/        flush();
/*  98*/        try
                {
/*  98*/            writer.close();
/* 101*/            return;
                }
/*  99*/        catch(IOException ioexception)
                {
/* 100*/            throw new JsonStreamException(ioexception);
                }
            }

            public void flush()
            {
/* 105*/        flushBuffer();
/* 107*/        try
                {
/* 107*/            writer.flush();
/* 110*/            return;
                }
/* 108*/        catch(IOException ioexception)
                {
/* 109*/            throw new JsonStreamException(ioexception);
                }
            }

            public JsonWriter beginArray()
            {
/* 114*/        clearMetadata();
/* 115*/        if(_ctx.peek() == JsonType.OBJECT && _name == null)
/* 116*/            throw new JsonStreamException("Englobing scope is OBJECT before begining a new value call writeName.");
/* 118*/        else
/* 118*/            return begin(JsonType.ARRAY, '[');
            }

            public JsonWriter beginObject()
            {
/* 122*/        if(_ctx.peek() == JsonType.METADATA)
                {
/* 123*/            _ctx.pop();
/* 124*/            begin(JsonType.OBJECT, '{');
                    MetadataPair metadatapair;
/* 125*/            for(Iterator iterator = _metadata.iterator(); iterator.hasNext(); writeInternalString(metadatapair.value))
                    {
/* 125*/                metadatapair = (MetadataPair)iterator.next();
/* 126*/                writeName((new StringBuilder("@")).append(metadatapair.name).toString());
/* 127*/                beforeValue();
                    }

                } else
                {
/* 130*/            begin(JsonType.OBJECT, '{');
                }
/* 131*/        return this;
            }

            protected final JsonWriter begin(JsonType jsontype, char c)
            {
/* 135*/        beforeValue();
/* 136*/        _ctx.push(jsontype);
/* 137*/        if(_len + 1 >= _bufferSize)
/* 137*/            flushBuffer();
/* 138*/        _buffer[_len++] = c;
/* 139*/        _hasPrevious = false;
/* 140*/        return this;
            }

            public JsonWriter endArray()
            {
/* 144*/        return end(JsonType.ARRAY, ']');
            }

            public JsonWriter endObject()
            {
/* 148*/        return end(JsonType.OBJECT, '}');
            }

            private final JsonWriter end(JsonType jsontype, char c)
            {
                JsonType jsontype1;
/* 152*/        if((jsontype1 = (JsonType)_ctx.pop()) != jsontype)
/* 154*/            throw new JsonStreamException((new StringBuilder("Expect type ")).append(jsontype.name()).append(" but was written ").append(jsontype1.name()).append(", you must call the adequate beginXXX method before endXXX.").toString());
/* 157*/        if(indentation)
                {
/* 158*/            _buffer[_len++] = '\n';
/* 159*/            for(jsontype = 0; jsontype < _ctx.size() - 1; jsontype++)
/* 160*/                writeToBuffer(_indentation, 0, 2);

                }
/* 163*/        if(_len + 1 >= _bufferSize)
/* 163*/            flushBuffer();
/* 165*/        _buffer[_len++] = c;
/* 166*/        _hasPrevious = true;
/* 167*/        return this;
            }

            private final JsonWriter beforeValue()
            {
                int i;
/* 171*/        if((i = (JsonType)_ctx.peek()) == JsonType.ARRAY)
                {
/* 173*/            if(_name != null)
/* 173*/                throw newIllegalKeyValuePairInJsonArray(new String(_name));
/* 174*/            if(_hasPrevious)
                    {
/* 175*/                if(_len + 1 >= _bufferSize)
/* 175*/                    flushBuffer();
/* 176*/                _buffer[_len++] = ',';
                    }
/* 178*/            indent();
                } else
/* 179*/        if(_name != null)
                {
/* 180*/            i = _name.length;
/* 182*/            if(_len + 4 + i >= _bufferSize)
/* 182*/                flushBuffer();
/* 183*/            if(_hasPrevious)
/* 183*/                _buffer[_len++] = ',';
/* 184*/            indent();
/* 185*/            if(_len + 3 + i >= _bufferSize)
/* 185*/                flushBuffer();
/* 187*/            _buffer[_len++] = '"';
/* 188*/            writeToBuffer(_name, 0, i);
/* 189*/            _buffer[_len++] = '"';
/* 191*/            _buffer[_len++] = ':';
/* 192*/            _name = null;
                } else
/* 193*/        if(i == JsonType.OBJECT)
/* 193*/            throw newIllegalSingleValueInJsonObject();
/* 195*/        return this;
            }

            private JsonStreamException newIllegalKeyValuePairInJsonArray(String s)
            {
/* 199*/        return (JsonStreamException)JsonStreamException.niceTrace(new JsonStreamException((new StringBuilder("Tried to write key/value pair with key=")).append(s).append(", Json format does not allow key/value pairs inside arrays, only allowed for Json Objects.").toString()));
            }

            private JsonStreamException newIllegalSingleValueInJsonObject()
            {
/* 207*/        return (JsonStreamException)JsonStreamException.niceTrace(new JsonStreamException("Tried to write value with no key in a JsonObject, Json format does not allow values without keys in JsonObjects, authorized only for arrays."));
            }

            private final void clearMetadata()
            {
/* 213*/        if(_ctx.peek() == JsonType.METADATA)
                {
/* 214*/            _metadata.clear();
/* 215*/            _ctx.pop();
                }
            }

            protected void indent()
            {
/* 220*/        if(indentation)
                {
/* 221*/            if(_len + 1 >= _bufferSize)
/* 221*/                flushBuffer();
/* 222*/            if(_ctx.peek() != JsonType.EMPTY)
/* 222*/                _buffer[_len++] = '\n';
/* 223*/            int i = _ctx.peek() != JsonType.METADATA ? _ctx.size() - 1 : _ctx.size() - 2;
/* 224*/            for(int j = 0; j < i; j++)
/* 225*/                writeToBuffer(_indentation, 0, 2);

                }
            }

            public JsonWriter writeName(String s)
            {
/* 230*/        _name = escapeString(s);
/* 231*/        return this;
            }

            public ObjectWriter writeEscapedName(char ac[])
            {
/* 235*/        _name = ac;
/* 236*/        return this;
            }

            public JsonWriter writeValue(int i)
            {
/* 240*/        clearMetadata();
/* 241*/        beforeValue();
/* 243*/        if(_len + 11 >= _bufferSize)
/* 243*/            flushBuffer();
/* 244*/        if(i < 0)
                {
/* 245*/            _buffer[_len++] = '-';
/* 246*/            writeInt(-(long)i);
                } else
                {
/* 247*/            writeInt(i);
                }
/* 248*/        _hasPrevious = true;
/* 249*/        return this;
            }

            public JsonWriter writeValue(double d)
            {
/* 253*/        checkValidJsonDouble(Double.valueOf(d));
/* 254*/        clearMetadata();
/* 255*/        beforeValue();
/* 256*/        writeToBuffer(Double.toString(d), 0);
/* 257*/        _hasPrevious = true;
/* 258*/        return this;
            }

            public JsonWriter writeValue(long l)
            {
/* 262*/        clearMetadata();
/* 263*/        beforeValue();
/* 265*/        if(_len + 21 >= _bufferSize)
/* 265*/            flushBuffer();
/* 268*/        if(l < 0L)
                {
/* 269*/            if(l != 0x8000000000000000L)
                    {
/* 270*/                _buffer[_len++] = '-';
/* 271*/                writeInt(-1L * l);
                    } else
                    {
/* 272*/                writeToBuffer(Long.toString(l), 0);
                    }
                } else
                {
/* 273*/            writeInt(l);
                }
/* 275*/        _hasPrevious = true;
/* 276*/        return this;
            }

            public ObjectWriter writeValue(short word0)
            {
/* 280*/        clearMetadata();
/* 281*/        beforeValue();
/* 283*/        if(_len + 5 >= _bufferSize)
/* 283*/            flushBuffer();
/* 284*/        if(word0 < 0)
                {
/* 285*/            _buffer[_len++] = '-';
/* 286*/            word0 = (short)(-word0);
                }
/* 288*/        writeInt(word0);
/* 289*/        _hasPrevious = true;
/* 290*/        return this;
            }

            public ObjectWriter writeValue(float f)
            {
/* 294*/        checkValidJsonFloat(Float.valueOf(f));
/* 295*/        clearMetadata();
/* 296*/        beforeValue();
/* 297*/        writeToBuffer(Float.toString(f), 0);
/* 298*/        _hasPrevious = true;
/* 299*/        return this;
            }

            public JsonWriter writeValue(boolean flag)
            {
/* 303*/        clearMetadata();
/* 304*/        beforeValue();
/* 305*/        if(flag)
/* 305*/            writeToBuffer(TRUE_VALUE, 0, 4);
/* 306*/        else
/* 306*/            writeToBuffer(FALSE_VALUE, 0, 5);
/* 307*/        _hasPrevious = true;
/* 308*/        return this;
            }

            protected final int writeInt(long l)
            {
/* 312*/        int i = (int)Math.log10(l) + 1;
/* 313*/        if(l == 0L)
                {
/* 314*/            _buffer[_len++] = '0';
/* 315*/            return 1;
                }
/* 318*/        int j = (_len + i) - 1;
                long l1;
/* 320*/        for(; l > 0L; l = l1)
                {
/* 321*/            l1 = l / 10L;
/* 322*/            _buffer[j--] = _INT_TO_CHARARRAY[(int)(l - l1 * 10L)];
                }

/* 326*/        _len += i;
/* 327*/        return i;
            }

            public JsonWriter writeValue(Number number)
            {
/* 331*/        checkValidJsonDouble(number);
/* 332*/        checkValidJsonFloat(number);
/* 333*/        clearMetadata();
/* 334*/        beforeValue();
/* 335*/        writeToBuffer(number.toString(), 0);
/* 336*/        _hasPrevious = true;
/* 337*/        return this;
            }

            public ObjectWriter writeBoolean(Boolean boolean1)
            {
/* 341*/        if(boolean1 == null)
/* 341*/            return writeNull();
/* 342*/        else
/* 342*/            return writeValue(boolean1.booleanValue());
            }

            public ObjectWriter writeNumber(Number number)
            {
/* 346*/        if(number == null)
/* 346*/            return writeNull();
/* 347*/        else
/* 347*/            return writeValue(number);
            }

            public ObjectWriter writeString(String s)
            {
/* 351*/        if(s == null)
/* 351*/            return writeNull();
/* 352*/        else
/* 352*/            return writeValue(s);
            }

            public ObjectWriter writeBytes(byte abyte0[])
            {
/* 356*/        if(abyte0 == null)
/* 356*/            return writeNull();
/* 357*/        else
/* 357*/            return writeValue(abyte0);
            }

            private void checkValidJsonDouble(Number number)
            {
/* 361*/        if(number.equals(Double.valueOf((0.0D / 0.0D))))
/* 362*/            throw new NumberFormatException("NaN is not a valid json number.");
/* 363*/        if(number.equals(Double.valueOf((-1.0D / 0.0D))) || number.equals(Double.valueOf((1.0D / 0.0D))))
/* 364*/            throw new NumberFormatException("Infinity is not a valid json number.");
/* 365*/        else
/* 365*/            return;
            }

            private void checkValidJsonFloat(Number number)
            {
/* 368*/        if(number.equals(Float.valueOf((0.0F / 0.0F))))
/* 369*/            throw new NumberFormatException("NaN is not a valid json number.");
/* 370*/        if(number.equals(Float.valueOf((-1.0F / 0.0F))) || number.equals(Float.valueOf((1.0F / 0.0F))))
/* 371*/            throw new NumberFormatException("Infinity is not a valid json number.");
/* 372*/        else
/* 372*/            return;
            }

            public ObjectWriter writeValue(byte abyte0[])
            {
/* 375*/        clearMetadata();
/* 376*/        beforeValue();
/* 378*/        if(_len + 1 >= _bufferSize)
/* 378*/            flushBuffer();
/* 379*/        _buffer[_len++] = '"';
/* 380*/        abyte0 = Base64.encodeToChar(abyte0, false);
/* 382*/        writeToBuffer(abyte0, 0, abyte0.length);
/* 384*/        if(_len + 1 >= _bufferSize)
/* 384*/            flushBuffer();
/* 385*/        _buffer[_len++] = '"';
/* 386*/        _hasPrevious = true;
/* 388*/        flush();
/* 389*/        return this;
            }

            public JsonWriter writeUnsafeValue(String s)
            {
/* 393*/        clearMetadata();
/* 394*/        beforeValue();
/* 395*/        if(_len + 1 >= _bufferSize)
/* 395*/            flushBuffer();
/* 396*/        _buffer[_len++] = '"';
/* 397*/        writeToBuffer(s.toCharArray(), 0, s.length());
/* 398*/        if(_len + 1 >= _bufferSize)
/* 398*/            flushBuffer();
/* 399*/        _buffer[_len++] = '"';
/* 400*/        _hasPrevious = true;
/* 401*/        return this;
            }

            public JsonWriter writeValue(String s)
            {
/* 405*/        clearMetadata();
/* 406*/        beforeValue();
/* 407*/        writeInternalString(s);
/* 408*/        return this;
            }

            public static final char[] escapeString(String s)
            {
/* 412*/        StringBuilder stringbuilder = new StringBuilder();
/* 413*/        int i = 0;
/* 414*/        int j = s.length();
/* 415*/        for(int k = 0; k < j; k++)
                {
                    int l;
/* 416*/            if((l = s.charAt(k)) < '\200')
                    {
/* 419*/                if((l = REPLACEMENT_CHARS[l]) == null)
/* 421*/                    continue;
                    } else
/* 423*/            if(l == 8232)
                    {
/* 424*/                l = "\\u2028".toCharArray();
                    } else
                    {
/* 425*/                if(l != 8233)
/* 426*/                    continue;
/* 426*/                l = "\\u2029".toCharArray();
                    }
/* 430*/            if(i < k)
/* 431*/                stringbuilder.append(s, i, k - i);
/* 433*/            stringbuilder.append(l, 0, l.length);
/* 434*/            i = k + 1;
                }

/* 436*/        if(i < j)
/* 437*/            stringbuilder.append(s, i, j);
/* 440*/        return stringbuilder.toString().toCharArray();
            }

            private final void writeInternalString(String s)
            {
/* 444*/        char ac[][] = htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
/* 445*/        if(_len + 1 >= _bufferSize)
/* 445*/            flushBuffer();
/* 446*/        _buffer[_len++] = '"';
/* 447*/        int i = 0;
/* 448*/        int j = s.length();
/* 449*/        s = s.toCharArray();
/* 450*/        for(int k = 0; k < j; k++)
                {
                    int l;
/* 451*/            if((l = s[k]) < '\200')
                    {
/* 454*/                if((l = ac[l]) == null)
/* 456*/                    continue;
                    } else
/* 458*/            if(l == 8232)
                    {
/* 459*/                l = "\\u2028".toCharArray();
                    } else
                    {
/* 460*/                if(l != 8233)
/* 461*/                    continue;
/* 461*/                l = "\\u2029".toCharArray();
                    }
/* 465*/            if(i < k)
/* 466*/                writeToBuffer(s, i, k - i);
/* 469*/            writeToBuffer(l, 0, l.length);
/* 470*/            i = k + 1;
                }

/* 472*/        if(i < j)
/* 473*/            writeToBuffer(s, i, j - i);
/* 475*/        if(_len + 1 >= _bufferSize)
/* 475*/            flushBuffer();
/* 477*/        _buffer[_len++] = '"';
/* 479*/        _hasPrevious = true;
            }

            public ObjectWriter writeNull()
            {
/* 483*/        if(skipNull)
                {
/* 484*/            _name = null;
                } else
                {
/* 486*/            beforeValue();
/* 487*/            writeToBuffer(NULL_VALUE, 0, 4);
/* 488*/            _hasPrevious = true;
                }
/* 490*/        return this;
            }

            public ObjectWriter beginNextObjectMetadata()
            {
/* 495*/        if(_ctx.peek() != JsonType.METADATA)
                {
/* 496*/            _ctx.push(JsonType.METADATA);
/* 497*/            _metadata.clear();
                }
/* 499*/        return this;
            }

            public ObjectWriter writeMetadata(String s, String s1)
            {
/* 503*/        if(_ctx.peek() == JsonType.METADATA)
/* 503*/            _metadata.add(new MetadataPair(s, s1));
/* 504*/        else
/* 504*/        if(_ctx.peek() == JsonType.OBJECT)
                {
/* 505*/            writeName((new StringBuilder("@")).append(s).toString());
/* 506*/            writeValue(s1);
                }
/* 509*/        return this;
            }

            public ObjectWriter writeBoolean(String s, Boolean boolean1)
            {
/* 513*/        writeName(s);
/* 514*/        return writeBoolean(boolean1);
            }

            public ObjectWriter writeNumber(String s, Number number)
            {
/* 518*/        writeName(s);
/* 519*/        return writeNumber(number);
            }

            public ObjectWriter writeString(String s, String s1)
            {
/* 523*/        writeName(s);
/* 524*/        return writeString(s1);
            }

            public ObjectWriter writeBytes(String s, byte abyte0[])
            {
/* 528*/        writeName(s);
/* 529*/        return writeBytes(abyte0);
            }

            private final void writeToBuffer(char ac[], int i, int j)
            {
/* 533*/        if(j < 64 && j < _bufferSize - _len)
                {
/* 534*/            System.arraycopy(ac, i, _buffer, _len, j);
/* 535*/            _len += j;
/* 535*/            return;
                }
/* 537*/        flushBuffer();
/* 539*/        try
                {
/* 539*/            writer.write(ac, i, j);
/* 542*/            return;
                }
                // Misplaced declaration of an exception variable
/* 540*/        catch(char ac[])
                {
/* 541*/            throw new JsonStreamException(ac);
                }
            }

            private final void writeToBuffer(String s, int i)
            {
/* 547*/        writeToBuffer(s, i, s.length());
            }

            private final void writeToBuffer(String s, int i, int j)
            {
/* 551*/        if(j < 64 && j < _bufferSize - _len)
                {
/* 552*/            s.getChars(i, i + j, _buffer, _len);
/* 553*/            _len += j;
/* 553*/            return;
                }
/* 555*/        flushBuffer();
/* 557*/        try
                {
/* 557*/            writer.write(s, i, j);
/* 560*/            return;
                }
                // Misplaced declaration of an exception variable
/* 558*/        catch(String s)
                {
/* 559*/            throw new JsonStreamException(s);
                }
            }

            private final void flushBuffer()
            {
/* 566*/        try
                {
/* 566*/            if(_len > 0)
                    {
/* 567*/                writer.write(_buffer, 0, _len);
/* 568*/                _len = 0;
                    }
/* 572*/            return;
                }
/* 570*/        catch(IOException ioexception)
                {
/* 571*/            throw new JsonStreamException(ioexception);
                }
            }

            public Writer unwrap()
            {
/* 576*/        return writer;
            }

            public volatile ObjectWriter writeUnsafeValue(String s)
            {
/*  10*/        return writeUnsafeValue(s);
            }

            public volatile ObjectWriter writeValue(String s)
            {
/*  10*/        return writeValue(s);
            }

            public volatile ObjectWriter writeValue(Number number)
            {
/*  10*/        return writeValue(number);
            }

            public volatile ObjectWriter writeValue(boolean flag)
            {
/*  10*/        return writeValue(flag);
            }

            public volatile ObjectWriter writeValue(long l)
            {
/*  10*/        return writeValue(l);
            }

            public volatile ObjectWriter writeValue(double d)
            {
/*  10*/        return writeValue(d);
            }

            public volatile ObjectWriter writeValue(int i)
            {
/*  10*/        return writeValue(i);
            }

            public volatile ObjectWriter writeName(String s)
            {
/*  10*/        return writeName(s);
            }

            public volatile ObjectWriter endObject()
            {
/*  10*/        return endObject();
            }

            public volatile ObjectWriter beginObject()
            {
/*  10*/        return beginObject();
            }

            public volatile ObjectWriter endArray()
            {
/*  10*/        return endArray();
            }

            public volatile ObjectWriter beginArray()
            {
/*  10*/        return beginArray();
            }

            private static final char REPLACEMENT_CHARS[][];
            private static final char HTML_SAFE_REPLACEMENT_CHARS[][];
            private static final char _INT_TO_CHARARRAY[];
            private static final char NULL_VALUE[] = {
/*  45*/        'n', 'u', 'l', 'l'
            };
            private static final char TRUE_VALUE[] = {
/*  46*/        't', 'r', 'u', 'e'
            };
            private static final char FALSE_VALUE[] = {
/*  47*/        'f', 'a', 'l', 's', 'e'
            };
            private static final int _LIMIT_WRITE_TO_BUFFER = 64;
            private final boolean htmlSafe;
            private final boolean skipNull;
            private final Writer writer;
            final Deque _ctx;
            private boolean _hasPrevious;
            private char _name[];
            private final boolean indentation;
            private static final char _indentation[] = {
/*  59*/        ' ', ' '
            };
            private final char _buffer[];
            private final int _bufferSize;
            private int _len;
            List _metadata;

            static 
            {
/*  18*/        REPLACEMENT_CHARS = new char[128][];
/*  19*/        for(int i = 0; i <= 31; i++)
/*  20*/            REPLACEMENT_CHARS[i] = String.format("\\u%04x", new Object[] {
/*  20*/                Integer.valueOf(i)
                    }).toCharArray();

/*  22*/        REPLACEMENT_CHARS[34] = "\\\"".toCharArray();
/*  23*/        REPLACEMENT_CHARS[92] = "\\\\".toCharArray();
/*  24*/        REPLACEMENT_CHARS[9] = "\\t".toCharArray();
/*  25*/        REPLACEMENT_CHARS[8] = "\\b".toCharArray();
/*  26*/        REPLACEMENT_CHARS[10] = "\\n".toCharArray();
/*  27*/        REPLACEMENT_CHARS[13] = "\\r".toCharArray();
/*  28*/        REPLACEMENT_CHARS[12] = "\\f".toCharArray();
/*  29*/        (HTML_SAFE_REPLACEMENT_CHARS = (char[][])REPLACEMENT_CHARS.clone())[39] = "\\u0027".toCharArray();
/*  31*/        HTML_SAFE_REPLACEMENT_CHARS[60] = "\\u003c".toCharArray();
/*  32*/        HTML_SAFE_REPLACEMENT_CHARS[62] = "\\u003e".toCharArray();
/*  33*/        HTML_SAFE_REPLACEMENT_CHARS[38] = "\\u0026".toCharArray();
/*  34*/        HTML_SAFE_REPLACEMENT_CHARS[61] = "\\u003d".toCharArray();
/*  37*/        _INT_TO_CHARARRAY = new char[10];
/*  40*/        for(int j = 0; j < 10; j++)
/*  41*/            _INT_TO_CHARARRAY[j] = (char)(j + 48);

            }
}
