// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lex.java

package javassist.compiler;


// Referenced classes of package javassist.compiler:
//            KeywordTable, Token, TokenId

public class Lex
    implements TokenId
{

            public Lex(String s)
            {
/*  41*/        lastChar = -1;
/*  42*/        textBuffer = new StringBuffer();
/*  43*/        currentToken = new Token();
/*  44*/        lookAheadTokens = null;
/*  46*/        input = s;
/*  47*/        position = 0;
/*  48*/        maxlen = s.length();
/*  49*/        lineNumber = 0;
            }

            public int get()
            {
/*  53*/        if(lookAheadTokens == null)
                {
/*  54*/            return get(currentToken);
                } else
                {
                    Token token;
/*  57*/            currentToken = token = lookAheadTokens;
/*  58*/            lookAheadTokens = lookAheadTokens.next;
/*  59*/            return token.tokenId;
                }
            }

            public int lookAhead()
            {
/*  67*/        return lookAhead(0);
            }

            public int lookAhead(int i)
            {
                Token token;
/*  71*/        if((token = lookAheadTokens) == null)
                {
/*  73*/            lookAheadTokens = token = currentToken;
/*  74*/            token.next = null;
/*  75*/            get(token);
                }
/*  78*/        while(i-- > 0) 
                {
/*  79*/            if(token.next == null)
                    {
                        Token token1;
/*  81*/                token.next = token1 = new Token();
/*  82*/                get(token1);
                    }
/*  78*/            token = token.next;
                }
/*  85*/        currentToken = token;
/*  86*/        return token.tokenId;
            }

            public String getString()
            {
/*  90*/        return currentToken.textValue;
            }

            public long getLong()
            {
/*  94*/        return currentToken.longValue;
            }

            public double getDouble()
            {
/*  98*/        return currentToken.doubleValue;
            }

            private int get(Token token)
            {
                int i;
/* 104*/        while((i = readLine(token)) == 10) ;
/* 106*/        token.tokenId = i;
/* 107*/        return i;
            }

            private int readLine(Token token)
            {
                int i;
/* 111*/        if((i = getNextNonWhiteChar()) < 0)
/* 113*/            return i;
/* 114*/        if(i == 10)
                {
/* 115*/            lineNumber++;
/* 116*/            return 10;
                }
/* 118*/        if(i == 39)
/* 119*/            return readCharConst(token);
/* 120*/        if(i == 34)
/* 121*/            return readStringL(token);
/* 122*/        if(48 <= i && i <= 57)
/* 123*/            return readNumber(i, token);
/* 124*/        if(i == 46)
                {
/* 125*/            i = getc();
/* 126*/            if(48 <= i && i <= 57)
                    {
                        StringBuffer stringbuffer;
/* 127*/                (stringbuffer = textBuffer).setLength(0);
/* 129*/                stringbuffer.append('.');
/* 130*/                return readDouble(stringbuffer, i, token);
                    } else
                    {
/* 133*/                ungetc(i);
/* 134*/                return readSeparator(46);
                    }
                }
/* 137*/        if(Character.isJavaIdentifierStart((char)i))
/* 138*/            return readIdentifier(i, token);
/* 140*/        else
/* 140*/            return readSeparator(i);
            }

            private int getNextNonWhiteChar()
            {
                int i;
/* 146*/label0:
/* 146*/        do
                {
/* 146*/            if((i = getc()) != 47)
/* 148*/                continue;
/* 148*/            if((i = getc()) == 47)
                    {
/* 151*/                while((i = getc()) != 10 && i != 13 && i != -1) ;
/* 152*/                continue;
                    }
/* 153*/            if(i == 42)
/* 155*/                do
                        {
/* 155*/                    do
/* 155*/                        if((i = getc()) == -1)
/* 158*/                            continue label0;
/* 158*/                    while(i != 42);
/* 159*/                    if((i = getc()) == 47)
                            {
/* 160*/                        i = 32;
/* 161*/                        continue label0;
                            }
/* 164*/                    ungetc(i);
                        } while(true);
/* 167*/            ungetc(i);
/* 168*/            i = 47;
                } while(isBlank(i));
/* 172*/        return i;
            }

            private int readCharConst(Token token)
            {
/* 177*/        int j = 0;
                int i;
/* 178*/        while((i = getc()) != 39) 
/* 179*/            if(i == 92)
                    {
/* 180*/                j = readEscapeChar();
                    } else
                    {
/* 181*/                if(i < 32)
                        {
/* 182*/                    if(i == 10)
/* 183*/                        lineNumber++;
/* 185*/                    return 500;
                        }
/* 188*/                j = i;
                    }
/* 190*/        token.longValue = j;
/* 191*/        return 401;
            }

            private int readEscapeChar()
            {
                int i;
/* 195*/        if((i = getc()) == 110)
/* 197*/            i = 10;
/* 198*/        else
/* 198*/        if(i == 116)
/* 199*/            i = 9;
/* 200*/        else
/* 200*/        if(i == 114)
/* 201*/            i = 13;
/* 202*/        else
/* 202*/        if(i == 102)
/* 203*/            i = 12;
/* 204*/        else
/* 204*/        if(i == 10)
/* 205*/            lineNumber++;
/* 207*/        return i;
            }

            private int readStringL(Token token)
            {
                StringBuffer stringbuffer;
/* 212*/        (stringbuffer = textBuffer).setLength(0);
                int i;
/* 215*/        do
                {
/* 215*/            while((i = getc()) != 34) 
                    {
/* 216*/                if(i == 92)
/* 217*/                    i = readEscapeChar();
/* 218*/                else
/* 218*/                if(i == 10 || i < 0)
                        {
/* 219*/                    lineNumber++;
/* 220*/                    return 500;
                        }
/* 223*/                stringbuffer.append((char)i);
                    }
/* 227*/            do
/* 227*/                while((i = getc()) == 10) 
/* 229*/                    lineNumber++;
/* 230*/            while(isBlank(i));
                } while(i == 34);
/* 235*/        ungetc(i);
/* 240*/        token.textValue = stringbuffer.toString();
/* 241*/        return 406;
            }

            private int readNumber(int i, Token token)
            {
/* 245*/        long l = 0L;
/* 246*/        int j = getc();
/* 247*/        if(i == 48)
                {
/* 248*/            if(j == 88 || j == 120)
                    {
/* 250*/                do
                        {
/* 250*/                    i = getc();
/* 251*/                    if(48 <= i && i <= 57)
                            {
/* 252*/                        l = (l << 4) + (long)(i - 48);
/* 252*/                        continue;
                            }
/* 253*/                    if(65 <= i && i <= 70)
                            {
/* 254*/                        l = (l << 4) + (long)((i - 65) + 10);
/* 254*/                        continue;
                            }
/* 255*/                    if(97 > i || i > 102)
/* 256*/                        break;
/* 256*/                    l = (l << 4) + (long)((i - 97) + 10);
                        } while(true);
/* 258*/                token.longValue = l;
/* 259*/                if(i == 76 || i == 108)
                        {
/* 260*/                    return 403;
                        } else
                        {
/* 262*/                    ungetc(i);
/* 263*/                    return 402;
                        }
                    }
/* 267*/            if(48 <= j && j <= 55)
                    {
/* 268*/                l = j - 48;
/* 270*/                do
                        {
/* 270*/                    i = getc();
/* 271*/                    if(48 > i || i > 55)
/* 272*/                        break;
/* 272*/                    l = (l << 3) + (long)(i - 48);
                        } while(true);
/* 274*/                token.longValue = l;
/* 275*/                if(i == 76 || i == 108)
                        {
/* 276*/                    return 403;
                        } else
                        {
/* 278*/                    ungetc(i);
/* 279*/                    return 402;
                        }
                    }
                }
/* 285*/        l = i - 48;
/* 286*/        for(; 48 <= j && j <= 57; j = getc())
/* 287*/            l = (l * 10L + (long)j) - 48L;

/* 291*/        token.longValue = l;
/* 292*/        if(j == 70 || j == 102)
                {
/* 293*/            token.doubleValue = l;
/* 294*/            return 404;
                }
/* 296*/        if(j == 69 || j == 101 || j == 68 || j == 100 || j == 46)
                {
/* 298*/            (i = textBuffer).setLength(0);
/* 300*/            i.append(l);
/* 301*/            return readDouble(i, j, token);
                }
/* 303*/        if(j == 76 || j == 108)
                {
/* 304*/            return 403;
                } else
                {
/* 306*/            ungetc(j);
/* 307*/            return 402;
                }
            }

            private int readDouble(StringBuffer stringbuffer, int i, Token token)
            {
/* 312*/        if(i != 69 && i != 101 && i != 68 && i != 100)
                {
/* 313*/            stringbuffer.append((char)i);
/* 315*/            do
                    {
/* 315*/                i = getc();
/* 316*/                if(48 > i || i > 57)
/* 317*/                    break;
/* 317*/                stringbuffer.append((char)i);
                    } while(true);
                }
/* 323*/        if(i == 69 || i == 101)
                {
/* 324*/            stringbuffer.append((char)i);
/* 325*/            if((i = getc()) == 43 || i == 45)
                    {
/* 327*/                stringbuffer.append((char)i);
/* 328*/                i = getc();
                    }
/* 331*/            for(; 48 <= i && i <= 57; i = getc())
/* 332*/                stringbuffer.append((char)i);

                }
/* 338*/        try
                {
/* 338*/            token.doubleValue = Double.parseDouble(stringbuffer.toString());
                }
/* 340*/        catch(NumberFormatException _ex)
                {
/* 341*/            return 500;
                }
/* 344*/        if(i == 70 || i == 102)
/* 345*/            return 404;
/* 347*/        if(i != 68 && i != 100)
/* 348*/            ungetc(i);
/* 350*/        return 405;
            }

            private int readSeparator(int i)
            {
                int j;
/* 363*/        if(33 <= i && i <= 63)
                {
                    int k;
/* 364*/            if((k = equalOps[i - 33]) == 0)
/* 366*/                return i;
/* 368*/            j = getc();
/* 369*/            if(i == j)
/* 370*/                switch(i)
                        {
/* 372*/                case 61: // '='
/* 372*/                    return 358;

/* 374*/                case 43: // '+'
/* 374*/                    return 362;

/* 376*/                case 45: // '-'
/* 376*/                    return 363;

/* 378*/                case 38: // '&'
/* 378*/                    return 369;

/* 380*/                case 60: // '<'
/* 380*/                    if((i = getc()) == 61)
                            {
/* 382*/                        return 365;
                            } else
                            {
/* 384*/                        ungetc(i);
/* 385*/                        return 364;
                            }

/* 388*/                case 62: // '>'
/* 388*/                    if((i = getc()) == 61)
/* 390*/                        return 367;
/* 391*/                    if(i == 62)
                            {
/* 392*/                        if((i = getc()) == 61)
                                {
/* 394*/                            return 371;
                                } else
                                {
/* 396*/                            ungetc(i);
/* 397*/                            return 370;
                                }
                            } else
                            {
/* 401*/                        ungetc(i);
/* 402*/                        return 366;
                            }
                        }
/* 407*/            else
/* 407*/            if(j == 61)
/* 408*/                return k;
                } else
/* 411*/        if(i == 94)
                {
/* 412*/            if((j = getc()) == 61)
/* 414*/                return 360;
                } else
/* 416*/        if(i == 124)
                {
/* 417*/            if((j = getc()) == 61)
/* 419*/                return 361;
/* 420*/            if(j == 124)
/* 421*/                return 368;
                } else
                {
/* 424*/            return i;
                }
/* 426*/        ungetc(j);
/* 427*/        return i;
            }

            private int readIdentifier(int i, Token token)
            {
                StringBuffer stringbuffer;
/* 431*/        (stringbuffer = textBuffer).setLength(0);
/* 435*/        do
/* 435*/            stringbuffer.append((char)i);
/* 436*/        while(Character.isJavaIdentifierPart((char)(i = getc())));
/* 439*/        ungetc(i);
/* 441*/        i = stringbuffer.toString();
                int j;
/* 442*/        if((j = ktable.lookup(i)) >= 0)
                {
/* 444*/            return j;
                } else
                {
/* 453*/            token.textValue = i;
/* 454*/            return 400;
                }
            }

            private static boolean isBlank(int i)
            {
/* 515*/        return i == 32 || i == 9 || i == 12 || i == 13 || i == 10;
            }

            private static boolean isDigit(int i)
            {
/* 520*/        return 48 <= i && i <= 57;
            }

            private void ungetc(int i)
            {
/* 524*/        lastChar = i;
            }

            public String getTextAround()
            {
                int i;
/* 528*/        if((i = position - 10) < 0)
/* 530*/            i = 0;
                int j;
/* 532*/        if((j = position + 10) > maxlen)
/* 534*/            j = maxlen;
/* 536*/        return input.substring(i, j);
            }

            private int getc()
            {
/* 540*/        if(lastChar < 0)
                {
/* 541*/            if(position < maxlen)
/* 542*/                return input.charAt(position++);
/* 544*/            else
/* 544*/                return -1;
                } else
                {
/* 546*/            int i = lastChar;
/* 547*/            lastChar = -1;
/* 548*/            return i;
                }
            }

            private int lastChar;
            private StringBuffer textBuffer;
            private Token currentToken;
            private Token lookAheadTokens;
            private String input;
            private int position;
            private int maxlen;
            private int lineNumber;
            private static final int equalOps[] = {
/* 355*/        350, 0, 0, 0, 351, 352, 0, 0, 0, 353, 
/* 355*/        354, 0, 355, 0, 356, 0, 0, 0, 0, 0, 
/* 355*/        0, 0, 0, 0, 0, 0, 0, 357, 358, 359, 
/* 355*/        0
            };
            private static final KeywordTable ktable;

            static 
            {
/* 458*/        (ktable = new KeywordTable()).append("abstract", 300);
/* 462*/        ktable.append("boolean", 301);
/* 463*/        ktable.append("break", 302);
/* 464*/        ktable.append("byte", 303);
/* 465*/        ktable.append("case", 304);
/* 466*/        ktable.append("catch", 305);
/* 467*/        ktable.append("char", 306);
/* 468*/        ktable.append("class", 307);
/* 469*/        ktable.append("const", 308);
/* 470*/        ktable.append("continue", 309);
/* 471*/        ktable.append("default", 310);
/* 472*/        ktable.append("do", 311);
/* 473*/        ktable.append("double", 312);
/* 474*/        ktable.append("else", 313);
/* 475*/        ktable.append("extends", 314);
/* 476*/        ktable.append("false", 411);
/* 477*/        ktable.append("final", 315);
/* 478*/        ktable.append("finally", 316);
/* 479*/        ktable.append("float", 317);
/* 480*/        ktable.append("for", 318);
/* 481*/        ktable.append("goto", 319);
/* 482*/        ktable.append("if", 320);
/* 483*/        ktable.append("implements", 321);
/* 484*/        ktable.append("import", 322);
/* 485*/        ktable.append("instanceof", 323);
/* 486*/        ktable.append("int", 324);
/* 487*/        ktable.append("interface", 325);
/* 488*/        ktable.append("long", 326);
/* 489*/        ktable.append("native", 327);
/* 490*/        ktable.append("new", 328);
/* 491*/        ktable.append("null", 412);
/* 492*/        ktable.append("package", 329);
/* 493*/        ktable.append("private", 330);
/* 494*/        ktable.append("protected", 331);
/* 495*/        ktable.append("public", 332);
/* 496*/        ktable.append("return", 333);
/* 497*/        ktable.append("short", 334);
/* 498*/        ktable.append("static", 335);
/* 499*/        ktable.append("strictfp", 347);
/* 500*/        ktable.append("super", 336);
/* 501*/        ktable.append("switch", 337);
/* 502*/        ktable.append("synchronized", 338);
/* 503*/        ktable.append("this", 339);
/* 504*/        ktable.append("throw", 340);
/* 505*/        ktable.append("throws", 341);
/* 506*/        ktable.append("transient", 342);
/* 507*/        ktable.append("true", 410);
/* 508*/        ktable.append("try", 343);
/* 509*/        ktable.append("void", 344);
/* 510*/        ktable.append("volatile", 345);
/* 511*/        ktable.append("while", 346);
            }
}
