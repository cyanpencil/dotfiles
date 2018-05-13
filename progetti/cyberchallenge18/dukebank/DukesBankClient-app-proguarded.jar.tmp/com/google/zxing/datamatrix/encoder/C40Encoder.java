// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   C40Encoder.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            Encoder, EncoderContext, HighLevelEncoder, SymbolInfo

class C40Encoder
    implements Encoder
{

            C40Encoder()
            {
            }

            public int getEncodingMode()
            {
/*  23*/        return 1;
            }

            public void encode(EncoderContext encodercontext)
            {
/*  29*/        StringBuilder stringbuilder = new StringBuilder();
/*  30*/        do
                {
/*  30*/            if(!encodercontext.hasMoreCharacters())
/*  31*/                break;
/*  31*/            int i = encodercontext.getCurrentChar();
/*  32*/            encodercontext.pos++;
/*  34*/            i = encodeChar(i, stringbuilder);
/*  36*/            int j = stringbuilder.length() / 3 << 1;
/*  38*/            j = encodercontext.getCodewordCount() + j;
/*  39*/            encodercontext.updateSymbolInfo(j);
/*  40*/            j = encodercontext.getSymbolInfo().getDataCapacity() - j;
/*  42*/            if(!encodercontext.hasMoreCharacters())
                    {
/*  44*/                StringBuilder stringbuilder1 = new StringBuilder();
/*  45*/                if(stringbuilder.length() % 3 == 2 && (j < 2 || j > 2))
/*  47*/                    i = backtrackOneCharacter(encodercontext, stringbuilder, stringbuilder1, i);
/*  51*/                for(; stringbuilder.length() % 3 == 1 && (i <= 3 && j != 1 || i > 3); i = backtrackOneCharacter(encodercontext, stringbuilder, stringbuilder1, i));
/*  53*/                break;
                    }
                    int k;
/*  58*/            if((k = stringbuilder.length()) % 3 != 0 || (i = HighLevelEncoder.lookAheadTest(encodercontext.getMessage(), encodercontext.pos, getEncodingMode())) == getEncodingMode())
/*  62*/                continue;
/*  62*/            encodercontext.signalEncoderChange(i);
/*  63*/            break;
                } while(true);
/*  67*/        handleEOD(encodercontext, stringbuilder);
            }

            private int backtrackOneCharacter(EncoderContext encodercontext, StringBuilder stringbuilder, StringBuilder stringbuilder1, int i)
            {
/*  72*/        int j = stringbuilder.length();
/*  73*/        stringbuilder.delete(j - i, j);
/*  74*/        encodercontext.pos--;
/*  75*/        stringbuilder = encodercontext.getCurrentChar();
/*  76*/        i = encodeChar(stringbuilder, stringbuilder1);
/*  77*/        encodercontext.resetSymbolInfo();
/*  78*/        return i;
            }

            static void writeNextTriplet(EncoderContext encodercontext, StringBuilder stringbuilder)
            {
/*  82*/        encodercontext.writeCodewords(encodeToCodewords(stringbuilder, 0));
/*  83*/        stringbuilder.delete(0, 3);
            }

            void handleEOD(EncoderContext encodercontext, StringBuilder stringbuilder)
            {
/*  93*/        int i = stringbuilder.length() / 3 << 1;
/*  94*/        int j = stringbuilder.length() % 3;
/*  96*/        i = encodercontext.getCodewordCount() + i;
/*  97*/        encodercontext.updateSymbolInfo(i);
/*  98*/        i = encodercontext.getSymbolInfo().getDataCapacity() - i;
/* 100*/        if(j == 2)
                {
/* 101*/            stringbuilder.append('\0');
/* 102*/            for(; stringbuilder.length() >= 3; writeNextTriplet(encodercontext, stringbuilder));
/* 105*/            if(encodercontext.hasMoreCharacters())
/* 106*/                encodercontext.writeCodeword('\376');
                } else
/* 108*/        if(i == 1 && j == 1)
                {
/* 109*/            for(; stringbuilder.length() >= 3; writeNextTriplet(encodercontext, stringbuilder));
/* 112*/            if(encodercontext.hasMoreCharacters())
/* 113*/                encodercontext.writeCodeword('\376');
/* 116*/            encodercontext.pos--;
                } else
/* 117*/        if(j == 0)
                {
/* 118*/            for(; stringbuilder.length() >= 3; writeNextTriplet(encodercontext, stringbuilder));
/* 121*/            if(i > 0 || encodercontext.hasMoreCharacters())
/* 122*/                encodercontext.writeCodeword('\376');
                } else
                {
/* 125*/            throw new IllegalStateException("Unexpected case. Please report!");
                }
/* 127*/        encodercontext.signalEncoderChange(0);
            }

            int encodeChar(char c, StringBuilder stringbuilder)
            {
/* 131*/        if(c == ' ')
                {
/* 132*/            stringbuilder.append('\003');
/* 133*/            return 1;
                }
/* 134*/        if(c >= '0' && c <= '9')
                {
/* 135*/            stringbuilder.append((char)((c - 48) + 4));
/* 136*/            return 1;
                }
/* 137*/        if(c >= 'A' && c <= 'Z')
                {
/* 138*/            stringbuilder.append((char)((c - 65) + 14));
/* 139*/            return 1;
                }
/* 140*/        if(c >= 0 && c <= '\037')
                {
/* 141*/            stringbuilder.append('\0');
/* 142*/            stringbuilder.append(c);
/* 143*/            return 2;
                }
/* 144*/        if(c >= '!' && c <= '/')
                {
/* 145*/            stringbuilder.append('\001');
/* 146*/            stringbuilder.append((char)(c - 33));
/* 147*/            return 2;
                }
/* 148*/        if(c >= ':' && c <= '@')
                {
/* 149*/            stringbuilder.append('\001');
/* 150*/            stringbuilder.append((char)((c - 58) + 15));
/* 151*/            return 2;
                }
/* 152*/        if(c >= '[' && c <= '_')
                {
/* 153*/            stringbuilder.append('\001');
/* 154*/            stringbuilder.append((char)((c - 91) + 22));
/* 155*/            return 2;
                }
/* 156*/        if(c >= '`' && c <= '\177')
                {
/* 157*/            stringbuilder.append('\002');
/* 158*/            stringbuilder.append((char)(c - 96));
/* 159*/            return 2;
                }
/* 160*/        if(c >= '\200')
                {
/* 161*/            stringbuilder.append("\001\036");
/* 163*/            return c = 2 + encodeChar((char)(c - 128), stringbuilder);
                } else
                {
/* 166*/            throw new IllegalArgumentException((new StringBuilder("Illegal character: ")).append(c).toString());
                }
            }

            private static String encodeToCodewords(CharSequence charsequence, int i)
            {
/* 171*/        char c = charsequence.charAt(i);
/* 172*/        char c1 = charsequence.charAt(i + 1);
/* 173*/        charsequence = charsequence.charAt(i + 2);
/* 174*/        i = (char)((charsequence = c * 1600 + c1 * 40 + charsequence + 1) / 256);
/* 176*/        charsequence = (char)(charsequence % 256);
/* 177*/        return new String(new char[] {
/* 177*/            i, charsequence
                });
            }
}
