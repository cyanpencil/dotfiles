// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ASCIIEncoder.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            Encoder, EncoderContext, HighLevelEncoder

final class ASCIIEncoder
    implements Encoder
{

            ASCIIEncoder()
            {
            }

            public final int getEncodingMode()
            {
/*  23*/        return 0;
            }

            public final void encode(EncoderContext encodercontext)
            {
                int i;
/*  29*/        if((i = HighLevelEncoder.determineConsecutiveDigitCount(encodercontext.getMessage(), encodercontext.pos)) >= 2)
                {
/*  31*/            encodercontext.writeCodeword(encodeASCIIDigits(encodercontext.getMessage().charAt(encodercontext.pos), encodercontext.getMessage().charAt(encodercontext.pos + 1)));
/*  33*/            encodercontext.pos += 2;
/*  33*/            return;
                }
/*  35*/        i = encodercontext.getCurrentChar();
                int j;
/*  36*/        if((j = HighLevelEncoder.lookAheadTest(encodercontext.getMessage(), encodercontext.pos, getEncodingMode())) != getEncodingMode())
                {
/*  38*/            switch(j)
                    {
/*  40*/            case 5: // '\005'
/*  40*/                encodercontext.writeCodeword('\347');
/*  41*/                encodercontext.signalEncoderChange(5);
/*  42*/                return;

/*  44*/            case 1: // '\001'
/*  44*/                encodercontext.writeCodeword('\346');
/*  45*/                encodercontext.signalEncoderChange(1);
/*  46*/                return;

/*  48*/            case 3: // '\003'
/*  48*/                encodercontext.writeCodeword('\356');
/*  49*/                encodercontext.signalEncoderChange(3);
/*  50*/                return;

/*  52*/            case 2: // '\002'
/*  52*/                encodercontext.writeCodeword('\357');
/*  53*/                encodercontext.signalEncoderChange(2);
/*  54*/                return;

/*  56*/            case 4: // '\004'
/*  56*/                encodercontext.writeCodeword('\360');
/*  57*/                encodercontext.signalEncoderChange(4);
/*  58*/                return;
                    }
/*  60*/            throw new IllegalStateException((new StringBuilder("Illegal mode: ")).append(j).toString());
                }
/*  62*/        if(HighLevelEncoder.isExtendedASCII(i))
                {
/*  63*/            encodercontext.writeCodeword('\353');
/*  64*/            encodercontext.writeCodeword((char)((i - 128) + 1));
/*  65*/            encodercontext.pos++;
/*  65*/            return;
                } else
                {
/*  67*/            encodercontext.writeCodeword((char)(i + 1));
/*  68*/            encodercontext.pos++;
/*  72*/            return;
                }
            }

            private static char encodeASCIIDigits(char c, char c1)
            {
/*  75*/        if(HighLevelEncoder.isDigit(c) && HighLevelEncoder.isDigit(c1))
/*  76*/            return (char)((c = (c - 48) * 10 + (c1 - 48)) + 130);
/*  79*/        else
/*  79*/            throw new IllegalArgumentException((new StringBuilder("not digits: ")).append(c).append(c1).toString());
            }
}
