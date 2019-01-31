// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   EdifactEncoder.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            Encoder, EncoderContext, HighLevelEncoder, SymbolInfo

final class EdifactEncoder
    implements Encoder
{

            EdifactEncoder()
            {
            }

            public final int getEncodingMode()
            {
/*  23*/        return 4;
            }

            public final void encode(EncoderContext encodercontext)
            {
/*  29*/        StringBuilder stringbuilder = new StringBuilder();
/*  30*/        do
                {
/*  30*/            if(!encodercontext.hasMoreCharacters())
/*  31*/                break;
                    int i;
/*  31*/            encodeChar(i = encodercontext.getCurrentChar(), stringbuilder);
/*  33*/            encodercontext.pos++;
/*  35*/            if((i = stringbuilder.length()) < 4)
/*  37*/                continue;
/*  37*/            encodercontext.writeCodewords(encodeToCodewords(stringbuilder, 0));
/*  38*/            stringbuilder.delete(0, 4);
/*  40*/            if((i = HighLevelEncoder.lookAheadTest(encodercontext.getMessage(), encodercontext.pos, getEncodingMode())) == getEncodingMode())
/*  42*/                continue;
/*  42*/            encodercontext.signalEncoderChange(0);
/*  43*/            break;
                } while(true);
/*  47*/        stringbuilder.append('\037');
/*  48*/        handleEOD(encodercontext, stringbuilder);
            }

            private static void handleEOD(EncoderContext encodercontext, CharSequence charsequence)
            {
                int i;
/*  59*/        if((i = charsequence.length()) == 0)
                {
/*  98*/            encodercontext.signalEncoderChange(0);
/*  98*/            return;
                }
                int k;
/*  63*/        if(i != 1)
/*  65*/            break MISSING_BLOCK_LABEL_60;
/*  65*/        encodercontext.updateSymbolInfo();
/*  66*/        k = encodercontext.getSymbolInfo().getDataCapacity() - encodercontext.getCodewordCount();
                int i1;
/*  67*/        if((i1 = encodercontext.getRemainingCharacters()) == 0 && k <= 2)
                {
/*  98*/            encodercontext.signalEncoderChange(0);
/*  98*/            return;
                }
/*  73*/        if(i > 4)
/*  74*/            throw new IllegalStateException("Count must not exceed 4");
/*  76*/        int l = i - 1;
/*  77*/        String s = encodeToCodewords(charsequence, 0);
/*  78*/        charsequence = (charsequence = encodercontext.hasMoreCharacters() ? 0 : 1) == 0 || l > 2 ? 0 : 1;
/*  81*/        if(l <= 2)
                {
/*  82*/            encodercontext.updateSymbolInfo(encodercontext.getCodewordCount() + l);
                    int j;
/*  83*/            if((j = encodercontext.getSymbolInfo().getDataCapacity() - encodercontext.getCodewordCount()) >= 3)
                    {
/*  85*/                charsequence = 0;
/*  86*/                encodercontext.updateSymbolInfo(encodercontext.getCodewordCount() + s.length());
                    }
                }
/*  91*/        if(charsequence != 0)
                {
/*  92*/            encodercontext.resetSymbolInfo();
/*  93*/            encodercontext.pos -= l;
                } else
                {
/*  95*/            encodercontext.writeCodewords(s);
                }
/*  98*/        encodercontext.signalEncoderChange(0);
/*  99*/        return;
/*  98*/        charsequence;
/*  98*/        encodercontext.signalEncoderChange(0);
/*  98*/        throw charsequence;
            }

            private static void encodeChar(char c, StringBuilder stringbuilder)
            {
/* 103*/        if(c >= ' ' && c <= '?')
                {
/* 104*/            stringbuilder.append(c);
/* 104*/            return;
                }
/* 105*/        if(c >= '@' && c <= '^')
                {
/* 106*/            stringbuilder.append((char)(c - 64));
/* 106*/            return;
                } else
                {
/* 108*/            HighLevelEncoder.illegalCharacter(c);
/* 110*/            return;
                }
            }

            private static String encodeToCodewords(CharSequence charsequence, int i)
            {
                int j;
/* 113*/        if((j = charsequence.length() - i) == 0)
/* 115*/            throw new IllegalStateException("StringBuilder must not be empty");
/* 117*/        char c = charsequence.charAt(i);
/* 118*/        char c1 = j < 2 ? '\0' : charsequence.charAt(i + 1);
/* 119*/        char c2 = j < 3 ? '\0' : charsequence.charAt(i + 2);
/* 120*/        charsequence = j < 4 ? 0 : ((CharSequence) (charsequence.charAt(i + 3)));
/* 122*/        i = (char)((charsequence = (c << 18) + (c1 << 12) + (c2 << 6) + charsequence) >> 16 & 0xff);
/* 124*/        c = (char)(charsequence >> 8 & 0xff);
/* 125*/        charsequence = (char)(charsequence & 0xff);
                StringBuilder stringbuilder;
/* 126*/        (stringbuilder = new StringBuilder(3)).append(i);
/* 128*/        if(j >= 2)
/* 129*/            stringbuilder.append(c);
/* 131*/        if(j >= 3)
/* 132*/            stringbuilder.append(charsequence);
/* 134*/        return stringbuilder.toString();
            }
}
