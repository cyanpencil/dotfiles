// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   JsonReader.java

package com.owlike.genson.stream;

import java.io.*;
import java.util.*;

// Referenced classes of package com.owlike.genson.stream:
//            Base64, JsonStreamException, JsonType, ObjectReader, 
//            ValueType

public class JsonReader
    implements ObjectReader
{

            public JsonReader(String s)
            {
/* 104*/        this(((Reader) (new StringReader(s))), false, false);
            }

            public JsonReader(Reader reader1, boolean flag, boolean flag1)
            {
/*  76*/        _buffer = new char[2048];
/*  82*/        _stringBuffer = new char[16];
/*  83*/        _stringBufferTail = 0;
/*  84*/        _stringBufferLength = _stringBuffer.length;
/*  90*/        _numberLen = 0;
/*  93*/        _first = true;
/*  94*/        _metadata_readen = false;
/*  95*/        _metadata = new HashMap(5);
/*  97*/        _ctx = new ArrayDeque(10);
/* 100*/        _ctx.push(JsonType.EMPTY);
/* 108*/        reader = reader1;
/* 109*/        strictDoubleParse = flag;
/* 110*/        readMetadata = flag1;
/* 112*/        reader1 = (char)readNextToken(false);
/* 113*/        if(91 == reader1)
                {
/* 113*/            valueType = ValueType.ARRAY;
/* 113*/            return;
                }
/* 114*/        if(123 == reader1)
                {
/* 114*/            valueType = ValueType.OBJECT;
/* 114*/            return;
                }
/* 117*/        if(_buflen <= 0)
/* 119*/            break MISSING_BLOCK_LABEL_231;
/* 119*/        valueType = consumeValue();
                  goto _L1
/* 120*/        JVM INSTR pop ;
/* 124*/        _cursor = -1;
/* 125*/        _col = -1;
/* 126*/        _stringValue = consumeString(34);
/* 127*/        valueType = ValueType.STRING;
                  goto _L1
/* 128*/        JVM INSTR dup ;
/* 129*/        reader1;
/* 129*/        throw ;
_L1:
/* 132*/        if(ValueType.valueOf(valueType.name()) == null)
/* 133*/            throw new JsonStreamException((new StringBuilder("Failed to instanciate reader, first character was ")).append(reader1).append(" when possible characters are [ and {").toString());
/* 136*/        break MISSING_BLOCK_LABEL_238;
/* 136*/        valueType = ValueType.NULL;
            }

            public void close()
            {
/* 142*/        try
                {
/* 142*/            reader.close();
/* 145*/            return;
                }
/* 143*/        catch(IOException ioexception)
                {
/* 144*/            throw new JsonStreamException(ioexception);
                }
            }

            public ObjectReader beginArray()
            {
/* 149*/        begin(91, JsonType.ARRAY);
/* 150*/        valueType = ValueType.ARRAY;
/* 151*/        if(_metadata_readen)
/* 151*/            _metadata.clear();
/* 152*/        return this;
            }

            public ObjectReader beginObject()
            {
/* 156*/        if(!_metadata_readen)
                {
/* 157*/            begin(123, JsonType.OBJECT);
/* 158*/            valueType = ValueType.OBJECT;
/* 159*/            if(readMetadata)
                    {
/* 160*/                _metadata.clear();
/* 161*/                readMetadata();
                    }
                }
/* 164*/        return this;
            }

            public ObjectReader nextObjectMetadata()
            {
/* 168*/        return beginObject();
            }

            public ObjectReader endArray()
            {
/* 172*/        end(93, JsonType.ARRAY);
/* 173*/        return this;
            }

            public ObjectReader endObject()
            {
/* 177*/        end(125, JsonType.OBJECT);
/* 178*/        _metadata.clear();
/* 179*/        _metadata_readen = false;
/* 180*/        return this;
            }

            public String name()
            {
/* 184*/        if(enclosingType() != JsonType.OBJECT)
/* 185*/            throw new JsonStreamException((new StringBuilder("Only json objects have names, actual type is ")).append(valueType).toString());
/* 187*/        else
/* 187*/            return currentName;
            }

            public String valueAsString()
            {
/* 191*/        if(ValueType.STRING == valueType)
/* 191*/            return _stringValue;
/* 192*/        if(ValueType.INTEGER == valueType)
/* 192*/            return (new StringBuilder()).append(_intValue).toString();
/* 193*/        if(ValueType.DOUBLE == valueType)
/* 193*/            return (new StringBuilder()).append(_doubleValue).toString();
/* 194*/        if(ValueType.NULL == valueType)
/* 194*/            return null;
/* 195*/        if(ValueType.BOOLEAN == valueType)
/* 196*/            return _booleanValue.toString();
/* 198*/        else
/* 198*/            throw new JsonStreamException("Readen value can not be converted to String");
            }

            public int valueAsInt()
            {
/* 202*/        if(ValueType.INTEGER == valueType)
                {
                    int i;
/* 203*/            if((long)(i = (int)_intValue) != _intValue)
/* 205*/                throwNumberFormatException("an int", (new StringBuilder("overflowing long value ")).append(_intValue).toString());
/* 206*/            return i;
                }
/* 207*/        if(ValueType.DOUBLE == valueType)
                {
/* 208*/            int j = (int)_doubleValue;
/* 209*/            long l = (long)_doubleValue;
/* 211*/            if((long)j != l)
/* 212*/                throwNumberFormatException("an int", (new StringBuilder("overflowing double value ")).append(_doubleValue).toString());
/* 214*/            return j;
                }
/* 215*/        if(ValueType.STRING == valueType)
/* 215*/            return Integer.parseInt(_stringValue);
/* 217*/        else
/* 217*/            throw new JsonStreamException((new StringBuilder("Expected a int but value is of type ")).append(valueType).toString());
            }

            public long valueAsLong()
            {
/* 221*/        if(ValueType.INTEGER == valueType)
/* 222*/            return _intValue;
/* 223*/        if(ValueType.DOUBLE == valueType)
                {
/* 224*/            if(-9.2233720368547758E+18D > _doubleValue || _doubleValue > 9.2233720368547758E+18D)
/* 225*/                throwNumberFormatException("a long", (new StringBuilder("overflowing double value ")).append(_doubleValue).toString());
/* 227*/            return (long)_doubleValue;
                }
/* 228*/        if(ValueType.STRING == valueType)
/* 228*/            return Long.parseLong(_stringValue);
/* 230*/        else
/* 230*/            throw new JsonStreamException((new StringBuilder("Expected a long but value is of type ")).append(valueType).toString());
            }

            public double valueAsDouble()
            {
/* 234*/        if(ValueType.DOUBLE == valueType)
/* 235*/            return _doubleValue;
/* 236*/        if(ValueType.INTEGER == valueType)
/* 238*/            return Long.valueOf(_intValue).doubleValue();
/* 239*/        if(ValueType.STRING == valueType)
/* 239*/            return Double.parseDouble(_stringValue);
/* 241*/        else
/* 241*/            throw new JsonStreamException((new StringBuilder("Expected a double but value is of type ")).append(valueType).toString());
            }

            public short valueAsShort()
            {
/* 245*/        if(ValueType.INTEGER == valueType)
                {
                    short word0;
/* 246*/            if((long)(word0 = (short)(int)_intValue) != _intValue)
/* 248*/                throwNumberFormatException("a short", (new StringBuilder("overflowing long value ")).append(_intValue).toString());
/* 249*/            return word0;
                }
/* 250*/        if(ValueType.DOUBLE == valueType)
                {
/* 251*/            short word1 = (short)(int)_doubleValue;
/* 252*/            long l = (long)_doubleValue;
/* 254*/            if((long)word1 != l)
/* 255*/                throwNumberFormatException("a short", (new StringBuilder("overflowing double value ")).append(_doubleValue).toString());
/* 257*/            return word1;
                }
/* 258*/        if(ValueType.STRING == valueType)
/* 258*/            return Short.parseShort(_stringValue);
/* 260*/        else
/* 260*/            throw new JsonStreamException((new StringBuilder("Expected a short but value is of type ")).append(valueType).toString());
            }

            public float valueAsFloat()
            {
/* 264*/        if(ValueType.DOUBLE == valueType)
                {
/* 265*/            if(1.4012984643248171E-45D > _doubleValue || _doubleValue > 3.4028234663852886E+38D)
/* 266*/                throwNumberFormatException("a float", (new StringBuilder("overflowing double value ")).append(_doubleValue).toString());
/* 267*/            return (float)_doubleValue;
                }
/* 268*/        if(ValueType.INTEGER == valueType)
/* 271*/            return Long.valueOf(_intValue).floatValue();
/* 272*/        if(ValueType.STRING == valueType)
/* 272*/            return Float.parseFloat(_stringValue);
/* 274*/        else
/* 274*/            throw new JsonStreamException((new StringBuilder("Expected a float but value is of type ")).append(valueType).toString());
            }

            public boolean valueAsBoolean()
            {
/* 278*/        if(ValueType.BOOLEAN == valueType)
/* 279*/            return _booleanValue.booleanValue();
/* 281*/        if(ValueType.STRING == valueType)
/* 281*/            return Boolean.parseBoolean(_stringValue);
/* 283*/        else
/* 283*/            throw new JsonStreamException("Readen value is not of type boolean");
            }

            public byte[] valueAsByteArray()
            {
/* 287*/        if(ValueType.STRING == valueType)
/* 287*/            return Base64.decodeFast(_stringValue);
/* 288*/        if(ValueType.NULL == valueType)
/* 288*/            return null;
/* 289*/        else
/* 289*/            throw new JsonStreamException((new StringBuilder("Expected a String to convert to byte array found ")).append(valueType).toString());
            }

            public String metadata(String s)
            {
/* 294*/        if(!_metadata_readen)
/* 294*/            nextObjectMetadata();
/* 295*/        return (String)_metadata.get(s);
            }

            public ValueType getValueType()
            {
/* 299*/        return valueType;
            }

            public ObjectReader skipValue()
            {
/* 304*/        if(ValueType.ARRAY == valueType || ValueType.OBJECT == valueType)
                {
/* 305*/            int i = 0;
/* 307*/            do
                    {
/* 307*/                if(ValueType.ARRAY == valueType)
                        {
/* 308*/                    beginArray();
/* 309*/                    i++;
                        } else
/* 310*/                if(ValueType.OBJECT == valueType)
                        {
/* 311*/                    beginObject();
/* 312*/                    i++;
                        }
/* 315*/                for(; hasNext(); skipValue())
/* 316*/                    next();

/* 320*/                JsonType jsontype = (JsonType)_ctx.peek();
/* 321*/                if(JsonType.ARRAY == jsontype)
                        {
/* 322*/                    endArray();
/* 323*/                    i--;
                        } else
/* 324*/                if(JsonType.OBJECT == jsontype)
                        {
/* 325*/                    endObject();
/* 326*/                    i--;
                        }
                    } while(i > 0);
                }
/* 331*/        return this;
            }

            public boolean hasNext()
            {
                int i;
/* 335*/        if((i = readNextToken(false)) == -1)
/* 336*/            return false;
/* 337*/        if(i < 128)
                {
/* 338*/            if(_first || _ctx.size() == 1)
/* 338*/                return _NEXT_TOKEN[i];
/* 339*/            if(i == 44)
/* 339*/                return true;
                }
/* 342*/        return false;
            }

            public ValueType next()
            {
/* 346*/        _metadata_readen = false;
/* 347*/        _first = false;
                char c;
/* 349*/        if((c = (char)readNextToken(false)) == ',')
                {
/* 352*/            _cursor++;
/* 353*/            c = (char)readNextToken(false);
                } else
/* 354*/        if(JsonType.ARRAY == _ctx.peek())
                {
/* 355*/            if(c == '[')
                    {
/* 356*/                valueType = ValueType.ARRAY;
/* 357*/                return valueType;
                    }
/* 359*/            if(c == '{')
                    {
/* 360*/                valueType = ValueType.OBJECT;
/* 361*/                return valueType;
                    }
                }
/* 365*/        if(JsonType.OBJECT == _ctx.peek())
                {
/* 366*/            currentName = consumeString(c);
/* 367*/            if(readNextToken(true) != 58)
/* 367*/                newWrongTokenException(":", _cursor - 1);
                }
/* 370*/        valueType = consumeValue();
/* 371*/        return valueType;
            }

            public JsonType enclosingType()
            {
/* 376*/        return (JsonType)_ctx.peek();
            }

            protected final ValueType consumeValue()
            {
                char c;
/* 380*/        if((c = (char)readNextToken(false)) == '"')
                {
/* 382*/            _stringValue = consumeString(c);
/* 383*/            return ValueType.STRING;
                }
/* 384*/        if(c == '[')
/* 384*/            return ValueType.ARRAY;
/* 385*/        if(c == '{')
/* 385*/            return ValueType.OBJECT;
/* 386*/        else
/* 386*/            return consumeLiteral();
            }

            protected final void readMetadata()
            {
/* 390*/        _metadata_readen = true;
/* 392*/        do
                {
/* 392*/            char c = (char)readNextToken(false);
/* 393*/            if('"' != c)
/* 393*/                return;
/* 394*/            ensureBufferHas(2, true);
/* 396*/            if('@' != _buffer[_cursor + 1])
/* 397*/                break;
/* 397*/            _cursor++;
/* 399*/            String s = consumeString(c);
/* 401*/            if(readNextToken(true) != 58)
/* 401*/                newWrongTokenException(":", _cursor - 1);
/* 403*/            String s1 = consumeString((char)readNextToken(false));
/* 404*/            _metadata.put(s, s1);
/* 405*/            if(readNextToken(false) == 44)
/* 406*/                _cursor++;
                } while(true);
            }

            protected final void begin(int i, JsonType jsontype)
            {
/* 413*/        int j = readNextToken(true);
/* 416*/        if(i == j)
/* 417*/            _ctx.push(jsontype);
/* 418*/        else
/* 418*/            newWrongTokenException((new StringBuilder()).append((char)i).toString(), _cursor - 1);
/* 419*/        _first = true;
            }

            protected final void end(int i, JsonType jsontype)
            {
/* 423*/        int j = readNextToken(true);
/* 426*/        if(i == j && jsontype == _ctx.peek())
/* 427*/            _ctx.pop();
/* 428*/        else
/* 428*/            newWrongTokenException((new StringBuilder()).append((char)i).toString(), _cursor - 1);
/* 429*/        _first = false;
            }

            protected final String consumeString(int i)
            {
/* 433*/        if(i != 34)
/* 433*/            newMisplacedTokenException(_cursor);
/* 434*/        _cursor++;
/* 435*/        i = 0;
_L2:
                int j;
/* 437*/        if(fillBuffer(true) < 0)
                {
/* 439*/            String s = new String(_stringBuffer, 0, _stringBufferTail);
/* 440*/            _stringBufferTail = 0;
/* 441*/            return s;
                }
/* 444*/        do
                {
/* 444*/            j = _cursor;
/* 445*/label0:
/* 445*/            do
                    {
/* 445*/label1:
                        {
/* 445*/label2:
                            {
/* 445*/                        if(j >= _buflen)
/* 446*/                            break label1;
/* 446*/                        if(_buffer[j] == '"')
/* 447*/                            if(i != 0)
                                    {
/* 448*/                                writeToStringBuffer(_buffer, _cursor, j - _cursor);
/* 449*/                                _cursor = j + 1;
/* 450*/                                i = new String(_stringBuffer, 0, _stringBufferTail);
/* 451*/                                _stringBufferTail = 0;
/* 452*/                                return i;
                                    } else
                                    {
/* 454*/                                i = new String(_buffer, _cursor, j - _cursor);
/* 455*/                                _cursor = j + 1;
/* 456*/                                return i;
                                    }
/* 458*/                        if(_buffer[j] != '\\')
/* 459*/                            break label2;
/* 459*/                        i = 1;
/* 460*/                        writeToStringBuffer(_buffer, _cursor, j - _cursor);
/* 461*/                        _cursor = j + 1;
/* 462*/                        if(_stringBufferLength <= _stringBufferTail + 1)
/* 462*/                            expandStringBuffer(16);
/* 463*/                        _stringBuffer[_stringBufferTail++] = readEscaped();
                            }
/* 464*/                    if(true)
/* 465*/                        break label0;
/* 465*/                    j++;
                        }
                    } while(true);
                } while(true);
/* 468*/        i = 1;
/* 469*/        writeToStringBuffer(_buffer, _cursor, j - _cursor);
/* 470*/        _cursor = j + 1;
/* 471*/        if(true) goto _L2; else goto _L1
_L1:
            }

            protected final ValueType consumeLiteral()
            {
                char c;
/* 481*/        if((c = _buffer[_cursor]) > '/' && c < ':' || c == '-')
/* 484*/            return consumeNumber();
/* 486*/        ensureBufferHas(4, true);
/* 488*/        if((_buffer[_cursor] == 'N' || _buffer[_cursor] == 'n') && (_buffer[_cursor + 1] == 'U' || _buffer[_cursor + 1] == 'u') && (_buffer[_cursor + 2] == 'L' || _buffer[_cursor + 2] == 'l') && (_buffer[_cursor + 3] == 'L' || _buffer[_cursor + 3] == 'l'))
                {
/* 492*/            _cursor += 4;
/* 493*/            return ValueType.NULL;
                }
/* 496*/        if((_buffer[_cursor] == 'T' || _buffer[_cursor] == 't') && (_buffer[_cursor + 1] == 'R' || _buffer[_cursor + 1] == 'r') && (_buffer[_cursor + 2] == 'U' || _buffer[_cursor + 2] == 'u') && (_buffer[_cursor + 3] == 'E' || _buffer[_cursor + 3] == 'e'))
                {
/* 500*/            _booleanValue = Boolean.valueOf(true);
/* 501*/            _cursor += 4;
/* 502*/            return ValueType.BOOLEAN;
                }
/* 504*/        ensureBufferHas(5, true);
/* 506*/        if((_buffer[_cursor] == 'F' || _buffer[_cursor] == 'f') && (_buffer[_cursor + 1] == 'A' || _buffer[_cursor + 1] == 'a') && (_buffer[_cursor + 2] == 'L' || _buffer[_cursor + 2] == 'l') && (_buffer[_cursor + 3] == 'S' || _buffer[_cursor + 3] == 's') && (_buffer[_cursor + 4] == 'E' || _buffer[_cursor + 4] == 'e'))
                {
/* 511*/            _booleanValue = Boolean.valueOf(false);
/* 512*/            _cursor += 5;
/* 513*/            return ValueType.BOOLEAN;
                } else
                {
/* 515*/            throw (new JsonStreamException.Builder()).message((new StringBuilder("Illegal character around row ")).append(_row).append(" and column ").append(_cursor - _col).append(" awaited for literal (number, boolean or null) but read '").append(_buffer[_cursor]).append("'!").toString()).create();
                }
            }

            private ValueType consumeNumber()
            {
/* 525*/        if(_buflen - _cursor < 378)
/* 525*/            ensureBufferHas(_buflen, false);
/* 527*/        int i = _cursor;
                boolean flag;
                int j;
/* 531*/        if(_buffer[_cursor] == '-')
                {
/* 532*/            flag = true;
/* 533*/            _cursor++;
/* 534*/            j = _cursor;
                } else
                {
/* 536*/            flag = false;
/* 537*/            j = _cursor;
                }
/* 540*/        for(; j < _buflen && _buffer[j] == '0'; j++);
/* 542*/        _cursor = j;
/* 544*/        int k = Math.min(_buflen, j + 18);
/* 547*/        long l = 0L;
                char c;
/* 548*/        for(; j < k && (c = _buffer[j]) >= '0' && c <= '9'; j++)
/* 553*/            l = 10L * l + (long)(c - 48);

/* 556*/        if(j < _buflen)
                {
                    char c1;
                    long l1;
/* 559*/            if((c1 = _buffer[j]) > '/' && c1 < ':' && (l1 = 10L * l + (long)(c1 - 48)) > l)
                    {
/* 563*/                l = l1;
/* 564*/                j++;
                    }
/* 573*/            if(j < _buflen && ((c1 = _buffer[j]) == '.' || c1 == 'e' || c1 == 'E' || c1 > '/' && c1 < ':'))
/* 576*/                if(strictDoubleParse)
                        {
/* 577*/                    _cursor = i;
/* 578*/                    return consumeStrictNumber(j);
                        } else
                        {
/* 579*/                    return consumeDouble(j, l, flag);
                        }
                }
/* 583*/        _intValue = flag ? -l : l;
/* 584*/        _numberLen = j - _cursor;
/* 585*/        _cursor = j;
/* 586*/        return ValueType.INTEGER;
            }

            private ValueType consumeDouble(int i, long l, boolean flag)
            {
/* 595*/        int j = i - _cursor;
/* 596*/        int k = l <= 0L ? 0 : i - _cursor;
/* 600*/        if(j > 17)
                {
/* 601*/            for(; i < _buflen && _buffer[i] >= '0' && _buffer[i] <= '9'; i++);
/* 607*/            if(j != i - _cursor)
/* 607*/                j = i - _cursor - j;
                } else
                {
/* 610*/            j = 0;
                }
/* 612*/        int j1 = 0;
/* 615*/        if(i < _buflen && _buffer[i] == '.')
                {
/* 616*/            int k1 = ++i;
/* 619*/            if(l == 0L)
                    {
/* 621*/                j = 0;
/* 623*/                for(; i < _buflen && _buffer[i] == '0'; i++);
                    }
                    char c;
/* 626*/            for(k = Math.min(_buflen, i + (18 - k)); i < k && (c = _buffer[i]) >= '0' && c <= '9'; i++)
/* 633*/                l = 10L * l + (long)(c - 48);

/* 635*/            j1 = i - k1;
/* 640*/            for(; i < _buflen && _buffer[i] >= '0' && _buffer[i] <= '9'; i++);
                }
/* 648*/        if(i + 1 < _buflen && (_buffer[i] == 'e' || _buffer[i] == 'E'))
                {
                    boolean flag1;
                    char c1;
/* 649*/            if((c1 = _buffer[++i]) == '-')
                    {
/* 653*/                flag1 = true;
/* 654*/                i++;
                    } else
                    {
/* 656*/                if(c1 == '+')
/* 656*/                    i++;
/* 657*/                flag1 = false;
                    }
/* 660*/            int i1 = 0;
                    char c2;
/* 661*/            for(; i < _buflen && (c2 = _buffer[i]) >= '0' && c2 <= '9'; i++)
/* 666*/                i1 = i1 * 10 + (c2 - 48);

/* 670*/            if(flag1)
/* 670*/                j1 += i1;
/* 671*/            else
/* 671*/                j += i1;
                }
/* 675*/        if((j1 = j - j1) < 0)
                {
/* 683*/            if(j1 < -308)
                    {
/* 684*/                if(j1 < -325)
                        {
/* 685*/                    _doubleValue = 0.0D;
                        } else
                        {
/* 687*/                    _doubleValue = (double)l / _POWS[-j1 - 308];
/* 688*/                    _doubleValue = _doubleValue / _POWS[308];
                        }
                    } else
                    {
/* 692*/                _doubleValue = (double)l / _POWS[-j1];
                    }
                } else
/* 695*/        if(j1 > 308)
/* 696*/            _doubleValue = (1.0D / 0.0D);
/* 700*/        else
/* 700*/            _doubleValue = (double)l * _POWS[j1];
/* 704*/        _doubleValue = flag ? -_doubleValue : _doubleValue;
/* 705*/        _numberLen = i - _cursor;
/* 706*/        _cursor = i;
/* 707*/        return ValueType.DOUBLE;
            }

            private final ValueType consumeStrictNumber(int i)
            {
/* 711*/        if(i < _buflen)
/* 713*/            for(; i < _buflen && _buffer[i] >= '0' && _buffer[i] <= '9'; i++);
/* 720*/        if(i < _buflen && _buffer[i] == '.')
/* 722*/            i = advanceWhileNumeric(++i);
                char c;
/* 726*/        if(i + 1 < _buflen && ((c = _buffer[i]) == 'e' || c == 'E'))
/* 729*/            if((c = _buffer[++i]) == '-' || c == '+' || c > '/' && c < ':')
/* 731*/                i = advanceWhileNumeric(++i);
/* 732*/            else
/* 732*/                newWrongTokenException("'-' or '+' or '' (same as +)");
/* 741*/        _numberLen = i - _cursor;
/* 742*/        _stringValue = new String(_buffer, _cursor, _numberLen);
/* 743*/        _doubleValue = Double.parseDouble(_stringValue);
/* 744*/        _cursor = i;
/* 745*/        return ValueType.DOUBLE;
            }

            private int advanceWhileNumeric(int i)
            {
/* 749*/        for(; i < _buflen; i++)
/* 750*/            if(_buffer[i] < '0' || _buffer[i] > '9')
/* 751*/                return i;

/* 754*/        return i;
            }

            protected final int readNextToken(boolean flag)
            {
/* 759*/        do
                {
/* 759*/            if(_cursor >= _buflen)
/* 759*/                fillBuffer(true);
/* 761*/            for(; _cursor < _buflen; _cursor++)
                    {
                        char c;
/* 762*/                if((c = _buffer[_cursor]) < '\200' && SKIPPED_TOKENS[c] == 0)
                        {
/* 764*/                    if(c == '/')
                            {
/* 765*/                        ensureBufferHas(2, true);
/* 766*/                        if(_buffer[_cursor + 1] == '*')
                                {
/* 767*/                            _cursor += 2;
/* 768*/                            advanceAfter(_END_OF_BLOCK_COMMENT);
                                } else
/* 769*/                        if(_buffer[_cursor + 1] == '/')
                                {
/* 770*/                            _cursor += 2;
/* 771*/                            advanceAfter(_END_OF_LINE);
/* 772*/                            _row++;
/* 773*/                            _col = _cursor;
                                } else
                                {
/* 774*/                            newWrongTokenException("start comment // or /*", _cursor);
                                }
/* 776*/                        _cursor--;
/* 776*/                        continue;
                            }
/* 777*/                    if(flag)
/* 778*/                        return _buffer[_cursor++];
/* 779*/                    else
/* 779*/                        return c;
                        }
/* 780*/                if(_buffer[_cursor] == '\n')
                        {
/* 781*/                    _row++;
/* 782*/                    _col = _cursor;
                        }
                    }

                } while(_buflen != -1);
/* 789*/        if(_cursor < _buflen)
/* 789*/            return _buffer[_cursor];
/* 789*/        else
/* 789*/            return -1;
            }

            private final void advanceAfter(char ac[])
            {
/* 793*/        int i = 0;
/* 795*/        do
                {
/* 795*/            if(_cursor >= _buflen)
/* 795*/                fillBuffer(true);
/* 797*/            for(; _cursor < _buflen && i < ac.length; _cursor++)
/* 798*/                if(_buffer[_cursor] == ac[i])
/* 799*/                    i++;
/* 800*/                else
/* 800*/                    i = 0;

/* 803*/            if(i == ac.length)
/* 804*/                return;
                } while(_buflen != -1);
            }

            protected final char readEscaped()
            {
/* 811*/        fillBuffer(true);
                int i;
/* 813*/        switch(i = _buffer[_cursor++])
                {
/* 816*/        case 98: // 'b'
/* 816*/            return '\b';

/* 818*/        case 116: // 't'
/* 818*/            return '\t';

/* 820*/        case 110: // 'n'
/* 820*/            return '\n';

/* 822*/        case 102: // 'f'
/* 822*/            return '\f';

/* 824*/        case 114: // 'r'
/* 824*/            return '\r';

/* 828*/        case 34: // '"'
/* 828*/        case 47: // '/'
/* 828*/        case 92: // '\\'
/* 828*/            return i;

/* 834*/        default:
/* 834*/            newMisplacedTokenException(_cursor - 1);
                    // fall through

/* 837*/        case 117: // 'u'
/* 837*/            i = 0;
                    break;
                }
/* 838*/        if(ensureBufferHas(4, false) < 0)
/* 839*/            throw new JsonStreamException("Expected 4 hex-digit for character escape sequence!");
/* 844*/        for(int j = 0; j < 4; j++)
                {
                    char c;
                    int k;
/* 845*/            if((k = (c = _buffer[_cursor++]) <= '\177' ? sHexValues[c] : -1) < 0)
/* 848*/                throw new JsonStreamException((new StringBuilder("Wrong character '")).append(c).append("' expected a hex-digit for character escape sequence!").toString());
/* 851*/            i = i << 4 | k;
                }

/* 854*/        return (char)i;
            }

            private final void writeToStringBuffer(char ac[], int i, int j)
            {
/* 858*/        if(_stringBufferLength <= _stringBufferTail + j)
/* 859*/            expandStringBuffer(j);
/* 861*/        System.arraycopy(ac, i, _stringBuffer, _stringBufferTail, j);
/* 862*/        _stringBufferTail += j;
            }

            private final void expandStringBuffer(int i)
            {
/* 866*/        i = new char[(_stringBufferLength << 1) + i];
/* 867*/        System.arraycopy(_stringBuffer, 0, i, 0, _stringBufferTail);
/* 868*/        _stringBuffer = i;
/* 869*/        _stringBufferLength = i.length;
            }

            private final int fillBuffer(boolean flag)
            {
/* 873*/        if(_cursor < _buflen)
/* 873*/            return _buflen;
/* 875*/        try
                {
/* 875*/            _buflen = reader.read(_buffer);
                }
                // Misplaced declaration of an exception variable
/* 876*/        catch(boolean flag)
                {
/* 877*/            throw new JsonStreamException(flag);
                }
/* 879*/        checkIllegalEnd(_buflen);
/* 880*/        _cursor = 0;
/* 881*/        _col = 0;
/* 882*/        return _buflen;
            }

            private final int ensureBufferHas(int i, boolean flag)
            {
                int j;
/* 887*/        if((j = _buflen - _cursor) >= i)
/* 889*/            return j;
/* 892*/        System.arraycopy(_buffer, _cursor, _buffer, 0, j);
_L1:
                int k;
/* 893*/        if(j >= i)
/* 894*/            break MISSING_BLOCK_LABEL_104;
/* 894*/        if((k = reader.read(_buffer, j, _buffer.length - j)) >= 0)
/* 896*/            break MISSING_BLOCK_LABEL_96;
/* 896*/        if(flag)
/* 896*/            throw new JsonStreamException("Encountered end of stream, incomplete json!");
/* 899*/        _buflen = j;
/* 900*/        _col = 0;
/* 901*/        _cursor = 0;
/* 902*/        return k;
/* 905*/        j += k;
                  goto _L1
/* 907*/        _buflen = j;
/* 908*/        _col = 0;
/* 909*/        _cursor = 0;
/* 910*/        return j;
                IOException ioexception;
/* 911*/        ioexception;
/* 912*/        throw new JsonStreamException(ioexception);
            }

            protected final boolean isEOF()
            {
/* 917*/        return _buflen < 0 || fillBuffer(false) < 0;
            }

            private final void newWrongTokenException(String s)
            {
/* 921*/        newWrongTokenException(s, _cursor);
            }

            public int column()
            {
                int i;
/* 925*/        if((i = _cursor - _col) < 0)
/* 926*/            return 0;
/* 926*/        else
/* 926*/            return i;
            }

            public int row()
            {
/* 930*/        return _row;
            }

            private final void newWrongTokenException(String s, int i)
            {
/* 935*/        if(i < 0)
/* 935*/            i = 0;
                int j;
/* 936*/        if((j = i - _col) < 0)
/* 937*/            j = 0;
/* 939*/        if(_buflen < 0)
/* 939*/            throw (new JsonStreamException((new StringBuilder("Incomplete data or malformed json : encoutered end of stream but expected ")).append(s).toString())).niceTrace();
/* 942*/        else
/* 942*/            throw (new JsonStreamException.Builder()).message((new StringBuilder("Illegal character at row ")).append(_row).append(" and column ").append(j).append(" expected ").append(s).append(" but read '").append(_buffer[i]).append("' !").toString()).locate(_row, j).create().niceTrace();
            }

            private final void newMisplacedTokenException(int i)
            {
/* 950*/        if(_buflen < 0)
/* 951*/            throw (JsonStreamException)JsonStreamException.niceTrace(new JsonStreamException("Incomplete data or malformed json : encoutered end of stream."));
/* 954*/        if(i < 0)
/* 954*/            i = 0;
                int j;
/* 955*/        if((j = i - _col) < 0)
/* 956*/            j = 0;
/* 958*/        throw (new JsonStreamException.Builder()).message((new StringBuilder("Encountred misplaced character '")).append(_buffer[i]).append("' around row ").append(_row).append(" and column ").append(j).toString()).locate(_row, j).create().niceTrace();
            }

            private final void checkIllegalEnd(int i)
            {
/* 965*/        if(i == -1 && JsonType.EMPTY != _ctx.peek())
/* 966*/            throw (new JsonStreamException("Incomplete data or malformed json : encoutered end of stream!")).niceTrace();
/* 968*/        else
/* 968*/            return;
            }

            private void throwNumberFormatException(String s, String s1)
            {
/* 971*/        int i = _cursor - _col - _numberLen;
/* 972*/        throw (NumberFormatException)JsonStreamException.niceTrace(new NumberFormatException((new StringBuilder("Wrong numeric type at row ")).append(_row).append(" and column ").append(i).append(", expected ").append(s).append(" but encoutered ").append(s1).toString()));
            }

            protected static final int SKIPPED_TOKENS[];
            private static final boolean _NEXT_TOKEN[];
            private static final char _END_OF_LINE[] = {
/*  47*/        '\n'
            };
            private static final char _END_OF_BLOCK_COMMENT[] = {
/*  48*/        '*', '/'
            };
            private static final int sHexValues[];
            private static final double _POWS[];
            private final Reader reader;
            private final boolean strictDoubleParse;
            private final boolean readMetadata;
            private final char _buffer[];
            private int _col;
            private int _row;
            private int _cursor;
            private int _buflen;
            private char _stringBuffer[];
            private int _stringBufferTail;
            private int _stringBufferLength;
            private String currentName;
            private String _stringValue;
            protected long _intValue;
            protected double _doubleValue;
            private int _numberLen;
            private Boolean _booleanValue;
            private ValueType valueType;
            private boolean _first;
            private boolean _metadata_readen;
            private Map _metadata;
            private final Deque _ctx;

            static 
            {
/*  19*/        (SKIPPED_TOKENS = new int[128])[9] = 1;
/*  21*/        SKIPPED_TOKENS[8] = 1;
/*  22*/        SKIPPED_TOKENS[10] = 1;
/*  23*/        SKIPPED_TOKENS[13] = 1;
/*  24*/        SKIPPED_TOKENS[12] = 1;
/*  25*/        SKIPPED_TOKENS[32] = 1;
/*  28*/        (_NEXT_TOKEN = new boolean[128])[44] = true;
/*  32*/        _NEXT_TOKEN[34] = true;
/*  33*/        _NEXT_TOKEN[123] = true;
/*  34*/        _NEXT_TOKEN[91] = true;
/*  35*/        _NEXT_TOKEN[110] = true;
/*  36*/        _NEXT_TOKEN[78] = true;
/*  37*/        _NEXT_TOKEN[45] = true;
/*  38*/        _NEXT_TOKEN[116] = true;
/*  39*/        _NEXT_TOKEN[102] = true;
/*  40*/        _NEXT_TOKEN[84] = true;
/*  41*/        _NEXT_TOKEN[70] = true;
/*  43*/        for(int i = 48; i < 58; i++)
/*  44*/            _NEXT_TOKEN[i] = true;

/*  53*/        Arrays.fill(sHexValues = new int[128], -1);
/*  57*/        for(int j = 0; j < 10; j++)
/*  58*/            sHexValues[j + 48] = j;

/*  60*/        for(int k = 0; k < 6; k++)
                {
/*  61*/            sHexValues[k + 97] = k + 10;
/*  62*/            sHexValues[k + 65] = k + 10;
                }

/*  66*/        _POWS = new double[309];
/*  69*/        for(int l = 0; l < _POWS.length; l++)
/*  70*/            _POWS[l] = Math.pow(10D, l);

            }
}
