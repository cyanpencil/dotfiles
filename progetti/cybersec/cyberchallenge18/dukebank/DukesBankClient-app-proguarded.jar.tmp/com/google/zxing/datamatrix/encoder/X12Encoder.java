// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   X12Encoder.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            C40Encoder, EncoderContext, HighLevelEncoder, SymbolInfo

final class X12Encoder extends C40Encoder
{

            X12Encoder()
            {
            }

            public final int getEncodingMode()
            {
/*  23*/        return 3;
            }

            public final void encode(EncoderContext encodercontext)
            {
/*  29*/        StringBuilder stringbuilder = new StringBuilder();
/*  30*/        do
                {
/*  30*/            if(!encodercontext.hasMoreCharacters())
/*  31*/                break;
/*  31*/            int i = encodercontext.getCurrentChar();
/*  32*/            encodercontext.pos++;
/*  34*/            encodeChar(i, stringbuilder);
/*  36*/            if((i = stringbuilder.length()) % 3 != 0)
/*  38*/                continue;
/*  38*/            writeNextTriplet(encodercontext, stringbuilder);
/*  40*/            if((i = HighLevelEncoder.lookAheadTest(encodercontext.getMessage(), encodercontext.pos, getEncodingMode())) == getEncodingMode())
/*  42*/                continue;
/*  42*/            encodercontext.signalEncoderChange(i);
/*  43*/            break;
                } while(true);
/*  47*/        handleEOD(encodercontext, stringbuilder);
            }

            final int encodeChar(char c, StringBuilder stringbuilder)
            {
/*  52*/        if(c == '\r')
/*  53*/            stringbuilder.append('\0');
/*  54*/        else
/*  54*/        if(c == '*')
/*  55*/            stringbuilder.append('\001');
/*  56*/        else
/*  56*/        if(c == '>')
/*  57*/            stringbuilder.append('\002');
/*  58*/        else
/*  58*/        if(c == ' ')
/*  59*/            stringbuilder.append('\003');
/*  60*/        else
/*  60*/        if(c >= '0' && c <= '9')
/*  61*/            stringbuilder.append((char)((c - 48) + 4));
/*  62*/        else
/*  62*/        if(c >= 'A' && c <= 'Z')
/*  63*/            stringbuilder.append((char)((c - 65) + 14));
/*  65*/        else
/*  65*/            HighLevelEncoder.illegalCharacter(c);
/*  67*/        return 1;
            }

            final void handleEOD(EncoderContext encodercontext, StringBuilder stringbuilder)
            {
/*  72*/label0:
                {
/*  72*/            encodercontext.updateSymbolInfo();
/*  73*/            int i = encodercontext.getSymbolInfo().getDataCapacity() - encodercontext.getCodewordCount();
/*  74*/            if((stringbuilder = stringbuilder.length()) == 2)
                    {
/*  76*/                encodercontext.writeCodeword('\376');
/*  77*/                encodercontext.pos -= 2;
                    } else
                    {
/*  79*/                if(stringbuilder != 1)
/*  80*/                    break label0;
/*  80*/                encodercontext.pos--;
/*  81*/                if(i > 1)
/*  82*/                    encodercontext.writeCodeword('\376');
                    }
/*  85*/            encodercontext.signalEncoderChange(0);
                }
            }
}
