// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Base256Encoder.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            Encoder, EncoderContext, HighLevelEncoder, SymbolInfo

final class Base256Encoder
    implements Encoder
{

            Base256Encoder()
            {
            }

            public final int getEncodingMode()
            {
/*  23*/        return 5;
            }

            public final void encode(EncoderContext encodercontext)
            {
                StringBuilder stringbuilder;
/*  28*/        (stringbuilder = new StringBuilder()).append('\0');
/*  30*/        do
                {
/*  30*/            if(!encodercontext.hasMoreCharacters())
/*  31*/                break;
/*  31*/            int i = encodercontext.getCurrentChar();
/*  32*/            stringbuilder.append(i);
/*  34*/            encodercontext.pos++;
/*  36*/            if((i = HighLevelEncoder.lookAheadTest(encodercontext.getMessage(), encodercontext.pos, getEncodingMode())) == getEncodingMode())
/*  38*/                continue;
/*  38*/            encodercontext.signalEncoderChange(i);
/*  39*/            break;
                } while(true);
/*  42*/        int j = stringbuilder.length() - 1;
/*  44*/        int k = encodercontext.getCodewordCount() + j + 1;
/*  45*/        encodercontext.updateSymbolInfo(k);
/*  46*/        k = encodercontext.getSymbolInfo().getDataCapacity() - k <= 0 ? 0 : 1;
/*  47*/        if(encodercontext.hasMoreCharacters() || k)
/*  48*/            if(j <= 249)
/*  49*/                stringbuilder.setCharAt(0, (char)j);
/*  50*/            else
/*  50*/            if(j > 249 && j <= 1555)
                    {
/*  51*/                stringbuilder.setCharAt(0, (char)(j / 250 + 249));
/*  52*/                stringbuilder.insert(1, (char)(j % 250));
                    } else
                    {
/*  54*/                throw new IllegalStateException((new StringBuilder("Message length not in valid ranges: ")).append(j).toString());
                    }
/*  58*/        j = 0;
/*  58*/        for(int l = stringbuilder.length(); j < l; j++)
/*  59*/            encodercontext.writeCodeword(randomize255State(stringbuilder.charAt(j), encodercontext.getCodewordCount() + 1));

            }

            private static char randomize255State(char c, int i)
            {
/*  65*/        i = (i * 149) % 255 + 1;
/*  66*/        if((c += i) <= 255)
/*  68*/            return (char)c;
/*  70*/        else
/*  70*/            return (char)(c - 256);
            }
}
