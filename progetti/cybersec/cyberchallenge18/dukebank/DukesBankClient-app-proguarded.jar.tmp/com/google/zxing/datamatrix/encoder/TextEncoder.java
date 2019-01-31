// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   TextEncoder.java

package com.google.zxing.datamatrix.encoder;


// Referenced classes of package com.google.zxing.datamatrix.encoder:
//            C40Encoder, HighLevelEncoder

final class TextEncoder extends C40Encoder
{

            TextEncoder()
            {
            }

            public final int getEncodingMode()
            {
/*  23*/        return 2;
            }

            final int encodeChar(char c, StringBuilder stringbuilder)
            {
/*  28*/        if(c == ' ')
                {
/*  29*/            stringbuilder.append('\003');
/*  30*/            return 1;
                }
/*  32*/        if(c >= '0' && c <= '9')
                {
/*  33*/            stringbuilder.append((char)((c - 48) + 4));
/*  34*/            return 1;
                }
/*  36*/        if(c >= 'a' && c <= 'z')
                {
/*  37*/            stringbuilder.append((char)((c - 97) + 14));
/*  38*/            return 1;
                }
/*  40*/        if(c >= 0 && c <= '\037')
                {
/*  41*/            stringbuilder.append('\0');
/*  42*/            stringbuilder.append(c);
/*  43*/            return 2;
                }
/*  45*/        if(c >= '!' && c <= '/')
                {
/*  46*/            stringbuilder.append('\001');
/*  47*/            stringbuilder.append((char)(c - 33));
/*  48*/            return 2;
                }
/*  50*/        if(c >= ':' && c <= '@')
                {
/*  51*/            stringbuilder.append('\001');
/*  52*/            stringbuilder.append((char)((c - 58) + 15));
/*  53*/            return 2;
                }
/*  55*/        if(c >= '[' && c <= '_')
                {
/*  56*/            stringbuilder.append('\001');
/*  57*/            stringbuilder.append((char)((c - 91) + 22));
/*  58*/            return 2;
                }
/*  60*/        if(c == '`')
                {
/*  61*/            stringbuilder.append('\002');
/*  62*/            stringbuilder.append((char)(c - 96));
/*  63*/            return 2;
                }
/*  65*/        if(c >= 'A' && c <= 'Z')
                {
/*  66*/            stringbuilder.append('\002');
/*  67*/            stringbuilder.append((char)((c - 65) + 1));
/*  68*/            return 2;
                }
/*  70*/        if(c >= '{' && c <= '\177')
                {
/*  71*/            stringbuilder.append('\002');
/*  72*/            stringbuilder.append((char)((c - 123) + 27));
/*  73*/            return 2;
                }
/*  75*/        if(c >= '\200')
                {
/*  76*/            stringbuilder.append("\001\036");
/*  78*/            return c = 2 + encodeChar((char)(c - 128), stringbuilder);
                } else
                {
/*  81*/            HighLevelEncoder.illegalCharacter(c);
/*  82*/            return -1;
                }
            }
}
