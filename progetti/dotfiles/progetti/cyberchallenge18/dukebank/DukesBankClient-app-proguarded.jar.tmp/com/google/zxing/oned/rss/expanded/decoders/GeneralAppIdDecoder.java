// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GeneralAppIdDecoder.java

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            BlockParsedResult, CurrentParsingState, DecodedChar, DecodedInformation, 
//            DecodedNumeric, FieldParser

final class GeneralAppIdDecoder
{

            GeneralAppIdDecoder(BitArray bitarray)
            {
/*  44*/        information = bitarray;
            }

            final String decodeAllCodes(StringBuilder stringbuilder, int i)
                throws NotFoundException, FormatException
            {
/*  48*/        i = i;
/*  49*/        String s = null;
/*  51*/        do
                {
                    DecodedInformation decodedinformation;
/*  51*/            if((s = FieldParser.parseFieldsInGeneralPurpose((decodedinformation = decodeGeneralPurposeField(i, s)).getNewString())) != null)
/*  54*/                stringbuilder.append(s);
/*  56*/            if(decodedinformation.isRemaining())
/*  57*/                s = String.valueOf(decodedinformation.getRemainingValue());
/*  59*/            else
/*  59*/                s = null;
/*  62*/            if(i != decodedinformation.getNewPosition())
/*  65*/                i = decodedinformation.getNewPosition();
/*  68*/            else
/*  68*/                return stringbuilder.toString();
                } while(true);
            }

            private boolean isStillNumeric(int i)
            {
/*  74*/        if(i + 7 > information.getSize())
/*  75*/            return i + 4 <= information.getSize();
/*  78*/        for(int j = i; j < i + 3; j++)
/*  79*/            if(information.get(j))
/*  80*/                return true;

/*  84*/        return information.get(i + 3);
            }

            private DecodedNumeric decodeNumeric(int i)
                throws FormatException
            {
                int j;
/*  88*/        if(i + 7 > information.getSize())
                {
/*  89*/            if((j = extractNumericValueFromBitArray(i, 4)) == 0)
/*  91*/                return new DecodedNumeric(information.getSize(), 10, 10);
/*  93*/            else
/*  93*/                return new DecodedNumeric(information.getSize(), j - 1, 10);
                } else
                {
                    int k;
/*  95*/            int l = ((k = extractNumericValueFromBitArray(i, 7)) - 8) / 11;
/*  98*/            k = (k - 8) % 11;
/* 100*/            return new DecodedNumeric(i + 7, l, k);
                }
            }

            final int extractNumericValueFromBitArray(int i, int j)
            {
/* 104*/        return extractNumericValueFromBitArray(information, i, j);
            }

            static int extractNumericValueFromBitArray(BitArray bitarray, int i, int j)
            {
/* 108*/        int k = 0;
/* 109*/        for(int l = 0; l < j; l++)
/* 110*/            if(bitarray.get(i + l))
/* 111*/                k |= 1 << j - l - 1;

/* 115*/        return k;
            }

            final DecodedInformation decodeGeneralPurposeField(int i, String s)
                throws FormatException
            {
/* 119*/        buffer.setLength(0);
/* 121*/        if(s != null)
/* 122*/            buffer.append(s);
/* 125*/        current.setPosition(i);
/* 127*/        if((i = parseBlocks()) != null && i.isRemaining())
/* 129*/            return new DecodedInformation(current.getPosition(), buffer.toString(), i.getRemainingValue());
/* 131*/        else
/* 131*/            return new DecodedInformation(current.getPosition(), buffer.toString());
            }

            private DecodedInformation parseBlocks()
                throws FormatException
            {
                boolean flag;
                BlockParsedResult blockparsedresult;
                int i;
/* 138*/        do
                {
/* 138*/            i = current.getPosition();
/* 140*/            if(current.isAlpha())
/* 141*/                flag = (blockparsedresult = parseAlphaBlock()).isFinished();
/* 143*/            else
/* 143*/            if(current.isIsoIec646())
/* 144*/                flag = (blockparsedresult = parseIsoIec646Block()).isFinished();
/* 147*/            else
/* 147*/                flag = (blockparsedresult = parseNumericBlock()).isFinished();
                } while(((i = i == current.getPosition() ? 0 : 1) || flag) && !flag);
/* 157*/        return blockparsedresult.getDecodedInformation();
            }

            private BlockParsedResult parseNumericBlock()
                throws FormatException
            {
                Object obj;
/* 161*/        for(; isStillNumeric(current.getPosition()); buffer.append(((DecodedNumeric) (obj)).getSecondDigit()))
                {
/* 162*/            obj = decodeNumeric(current.getPosition());
/* 163*/            current.setPosition(((DecodedNumeric) (obj)).getNewPosition());
/* 165*/            if(((DecodedNumeric) (obj)).isFirstDigitFNC1())
                    {
/* 167*/                if(((DecodedNumeric) (obj)).isSecondDigitFNC1())
/* 168*/                    obj = new DecodedInformation(current.getPosition(), buffer.toString());
/* 170*/                else
/* 170*/                    obj = new DecodedInformation(current.getPosition(), buffer.toString(), ((DecodedNumeric) (obj)).getSecondDigit());
/* 172*/                return new BlockParsedResult(((DecodedInformation) (obj)), true);
                    }
/* 174*/            buffer.append(((DecodedNumeric) (obj)).getFirstDigit());
/* 176*/            if(((DecodedNumeric) (obj)).isSecondDigitFNC1())
                    {
/* 177*/                obj = new DecodedInformation(current.getPosition(), buffer.toString());
/* 178*/                return new BlockParsedResult(((DecodedInformation) (obj)), true);
                    }
                }

/* 183*/        if(isNumericToAlphaNumericLatch(current.getPosition()))
                {
/* 184*/            current.setAlpha();
/* 185*/            current.incrementPosition(4);
                }
/* 187*/        return new BlockParsedResult(false);
            }

            private BlockParsedResult parseIsoIec646Block()
                throws FormatException
            {
                Object obj;
/* 191*/        for(; isStillIsoIec646(current.getPosition()); buffer.append(((DecodedChar) (obj)).getValue()))
                {
/* 192*/            obj = decodeIsoIec646(current.getPosition());
/* 193*/            current.setPosition(((DecodedChar) (obj)).getNewPosition());
/* 195*/            if(((DecodedChar) (obj)).isFNC1())
                    {
/* 196*/                obj = new DecodedInformation(current.getPosition(), buffer.toString());
/* 197*/                return new BlockParsedResult(((DecodedInformation) (obj)), true);
                    }
                }

/* 202*/        if(isAlphaOr646ToNumericLatch(current.getPosition()))
                {
/* 203*/            current.incrementPosition(3);
/* 204*/            current.setNumeric();
                } else
/* 205*/        if(isAlphaTo646ToAlphaLatch(current.getPosition()))
                {
/* 206*/            if(current.getPosition() + 5 < information.getSize())
/* 207*/                current.incrementPosition(5);
/* 209*/            else
/* 209*/                current.setPosition(information.getSize());
/* 212*/            current.setAlpha();
                }
/* 214*/        return new BlockParsedResult(false);
            }

            private BlockParsedResult parseAlphaBlock()
            {
                Object obj;
/* 218*/        for(; isStillAlpha(current.getPosition()); buffer.append(((DecodedChar) (obj)).getValue()))
                {
/* 219*/            obj = decodeAlphanumeric(current.getPosition());
/* 220*/            current.setPosition(((DecodedChar) (obj)).getNewPosition());
/* 222*/            if(((DecodedChar) (obj)).isFNC1())
                    {
/* 223*/                obj = new DecodedInformation(current.getPosition(), buffer.toString());
/* 224*/                return new BlockParsedResult(((DecodedInformation) (obj)), true);
                    }
                }

/* 230*/        if(isAlphaOr646ToNumericLatch(current.getPosition()))
                {
/* 231*/            current.incrementPosition(3);
/* 232*/            current.setNumeric();
                } else
/* 233*/        if(isAlphaTo646ToAlphaLatch(current.getPosition()))
                {
/* 234*/            if(current.getPosition() + 5 < information.getSize())
/* 235*/                current.incrementPosition(5);
/* 237*/            else
/* 237*/                current.setPosition(information.getSize());
/* 240*/            current.setIsoIec646();
                }
/* 242*/        return new BlockParsedResult(false);
            }

            private boolean isStillIsoIec646(int i)
            {
/* 246*/        if(i + 5 > information.getSize())
/* 247*/            return false;
                int j;
/* 250*/        if((j = extractNumericValueFromBitArray(i, 5)) >= 5 && j < 16)
/* 252*/            return true;
/* 255*/        if(i + 7 > information.getSize())
/* 256*/            return false;
/* 259*/        if((j = extractNumericValueFromBitArray(i, 7)) >= 64 && j < 116)
/* 261*/            return true;
/* 264*/        if(i + 8 > information.getSize())
/* 265*/            return false;
/* 268*/        return (i = extractNumericValueFromBitArray(i, 8)) >= 232 && i < 253;
            }

            private DecodedChar decodeIsoIec646(int i)
                throws FormatException
            {
                int j;
/* 274*/        if((j = extractNumericValueFromBitArray(i, 5)) == 15)
/* 276*/            return new DecodedChar(i + 5, '$');
/* 279*/        if(j >= 5 && j < 15)
/* 280*/            return new DecodedChar(i + 5, (char)((j + 48) - 5));
/* 283*/        if((j = extractNumericValueFromBitArray(i, 7)) >= 64 && j < 90)
/* 286*/            return new DecodedChar(i + 7, (char)(j + 1));
/* 289*/        if(j >= 90 && j < 116)
/* 290*/            return new DecodedChar(i + 7, (char)(j + 7));
/* 293*/        switch(j = extractNumericValueFromBitArray(i, 8))
                {
/* 297*/        case 232: 
/* 297*/            j = 33;
                    break;

/* 300*/        case 233: 
/* 300*/            j = 34;
                    break;

/* 303*/        case 234: 
/* 303*/            j = 37;
                    break;

/* 306*/        case 235: 
/* 306*/            j = 38;
                    break;

/* 309*/        case 236: 
/* 309*/            j = 39;
                    break;

/* 312*/        case 237: 
/* 312*/            j = 40;
                    break;

/* 315*/        case 238: 
/* 315*/            j = 41;
                    break;

/* 318*/        case 239: 
/* 318*/            j = 42;
                    break;

/* 321*/        case 240: 
/* 321*/            j = 43;
                    break;

/* 324*/        case 241: 
/* 324*/            j = 44;
                    break;

/* 327*/        case 242: 
/* 327*/            j = 45;
                    break;

/* 330*/        case 243: 
/* 330*/            j = 46;
                    break;

/* 333*/        case 244: 
/* 333*/            j = 47;
                    break;

/* 336*/        case 245: 
/* 336*/            j = 58;
                    break;

/* 339*/        case 246: 
/* 339*/            j = 59;
                    break;

/* 342*/        case 247: 
/* 342*/            j = 60;
                    break;

/* 345*/        case 248: 
/* 345*/            j = 61;
                    break;

/* 348*/        case 249: 
/* 348*/            j = 62;
                    break;

/* 351*/        case 250: 
/* 351*/            j = 63;
                    break;

/* 354*/        case 251: 
/* 354*/            j = 95;
                    break;

/* 357*/        case 252: 
/* 357*/            j = 32;
                    break;

/* 360*/        default:
/* 360*/            throw FormatException.getFormatInstance();
                }
/* 362*/        return new DecodedChar(i + 8, j);
            }

            private boolean isStillAlpha(int i)
            {
/* 366*/        if(i + 5 > information.getSize())
/* 367*/            return false;
                int j;
/* 371*/        if((j = extractNumericValueFromBitArray(i, 5)) >= 5 && j < 16)
/* 373*/            return true;
/* 376*/        if(i + 6 > information.getSize())
/* 377*/            return false;
/* 380*/        return (i = extractNumericValueFromBitArray(i, 6)) >= 16 && i < 63;
            }

            private DecodedChar decodeAlphanumeric(int i)
            {
                int j;
/* 385*/        if((j = extractNumericValueFromBitArray(i, 5)) == 15)
/* 387*/            return new DecodedChar(i + 5, '$');
/* 390*/        if(j >= 5 && j < 15)
/* 391*/            return new DecodedChar(i + 5, (char)((j + 48) - 5));
/* 394*/        if((j = extractNumericValueFromBitArray(i, 6)) >= 32 && j < 58)
/* 397*/            return new DecodedChar(i + 6, (char)(j + 33));
/* 401*/        switch(j)
                {
/* 403*/        case 58: // ':'
/* 403*/            j = 42;
                    break;

/* 406*/        case 59: // ';'
/* 406*/            j = 44;
                    break;

/* 409*/        case 60: // '<'
/* 409*/            j = 45;
                    break;

/* 412*/        case 61: // '='
/* 412*/            j = 46;
                    break;

/* 415*/        case 62: // '>'
/* 415*/            j = 47;
                    break;

/* 418*/        default:
/* 418*/            throw new IllegalStateException((new StringBuilder("Decoding invalid alphanumeric value: ")).append(j).toString());
                }
/* 420*/        return new DecodedChar(i + 6, j);
            }

            private boolean isAlphaTo646ToAlphaLatch(int i)
            {
/* 424*/        if(i + 1 > information.getSize())
/* 425*/            return false;
/* 428*/        for(int j = 0; j < 5 && j + i < information.getSize(); j++)
                {
/* 429*/            if(j == 2)
                    {
/* 430*/                if(!information.get(i + 2))
/* 431*/                    return false;
/* 433*/                continue;
                    }
/* 433*/            if(information.get(i + j))
/* 434*/                return false;
                }

/* 438*/        return true;
            }

            private boolean isAlphaOr646ToNumericLatch(int i)
            {
/* 443*/        if(i + 3 > information.getSize())
/* 444*/            return false;
/* 447*/        for(int j = i; j < i + 3; j++)
/* 448*/            if(information.get(j))
/* 449*/                return false;

/* 452*/        return true;
            }

            private boolean isNumericToAlphaNumericLatch(int i)
            {
/* 458*/        if(i + 1 > information.getSize())
/* 459*/            return false;
/* 462*/        for(int j = 0; j < 4 && j + i < information.getSize(); j++)
/* 463*/            if(information.get(i + j))
/* 464*/                return false;

/* 467*/        return true;
            }

            private final BitArray information;
            private final CurrentParsingState current = new CurrentParsingState();
            private final StringBuilder buffer = new StringBuilder();
}
