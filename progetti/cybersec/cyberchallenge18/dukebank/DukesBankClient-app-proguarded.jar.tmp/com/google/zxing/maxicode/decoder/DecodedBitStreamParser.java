// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   DecodedBitStreamParser.java

package com.google.zxing.maxicode.decoder;

import com.google.zxing.common.DecoderResult;
import java.text.DecimalFormat;
import java.text.NumberFormat;

final class DecodedBitStreamParser
{

            private DecodedBitStreamParser()
            {
            }

            static DecoderResult decode(byte abyte0[], int i)
            {
/*  64*/        StringBuilder stringbuilder = new StringBuilder(144);
/*  65*/        switch(i)
                {
/*  64*/        default:
                    break;

/*  69*/        case 2: // '\002'
/*  69*/        case 3: // '\003'
                    String s;
/*  69*/            if(i == 2)
                    {
/*  70*/                int j = getPostCode2(abyte0);
                        DecimalFormat decimalformat;
/*  71*/                s = (decimalformat = new DecimalFormat("0000000000".substring(0, getPostCode2Length(abyte0)))).format(j);
                    } else
                    {
/*  74*/                s = getPostCode3(abyte0);
                    }
/*  76*/            String s1 = THREE_DIGITS.format(getCountry(abyte0));
/*  77*/            String s2 = THREE_DIGITS.format(getServiceClass(abyte0));
/*  78*/            stringbuilder.append(getMessage(abyte0, 10, 84));
/*  79*/            if(stringbuilder.toString().startsWith("[)>\03601\035"))
/*  80*/                stringbuilder.insert(9, (new StringBuilder()).append(s).append('\035').append(s1).append('\035').append(s2).append('\035').toString());
/*  82*/            else
/*  82*/                stringbuilder.insert(0, (new StringBuilder()).append(s).append('\035').append(s1).append('\035').append(s2).append('\035').toString());
                    break;

/*  86*/        case 4: // '\004'
/*  86*/            stringbuilder.append(getMessage(abyte0, 1, 93));
                    break;

/*  89*/        case 5: // '\005'
/*  89*/            stringbuilder.append(getMessage(abyte0, 1, 77));
                    break;
                }
/*  92*/        return new DecoderResult(abyte0, stringbuilder.toString(), null, String.valueOf(i));
            }

            private static int getBit(int i, byte abyte0[])
            {
/*  96*/        i--;
/*  97*/        return (abyte0[i / 6] & 1 << 5 - i % 6) != 0 ? 1 : 0;
            }

            private static int getInt(byte abyte0[], byte abyte1[])
            {
/* 101*/        int i = 0;
/* 102*/        for(int j = 0; j < abyte1.length; j++)
/* 103*/            i += getBit(abyte1[j], abyte0) << abyte1.length - j - 1;

/* 105*/        return i;
            }

            private static int getCountry(byte abyte0[])
            {
/* 109*/        return getInt(abyte0, new byte[] {
/* 109*/            53, 54, 43, 44, 45, 46, 47, 48, 37, 38
                });
            }

            private static int getServiceClass(byte abyte0[])
            {
/* 113*/        return getInt(abyte0, new byte[] {
/* 113*/            55, 56, 57, 58, 59, 60, 49, 50, 51, 52
                });
            }

            private static int getPostCode2Length(byte abyte0[])
            {
/* 117*/        return getInt(abyte0, new byte[] {
/* 117*/            39, 40, 41, 42, 31, 32
                });
            }

            private static int getPostCode2(byte abyte0[])
            {
/* 121*/        return getInt(abyte0, new byte[] {
/* 121*/            33, 34, 35, 36, 25, 26, 27, 28, 29, 30, 
/* 121*/            19, 20, 21, 22, 23, 24, 13, 14, 15, 16, 
/* 121*/            17, 18, 7, 8, 9, 10, 11, 12, 1, 2
                });
            }

            private static String getPostCode3(byte abyte0[])
            {
/* 126*/        return String.valueOf(new char[] {
/* 126*/            SETS[0].charAt(getInt(abyte0, new byte[] {
/* 126*/                39, 40, 41, 42, 31, 32
                    })), SETS[0].charAt(getInt(abyte0, new byte[] {
/* 126*/                33, 34, 35, 36, 25, 26
                    })), SETS[0].charAt(getInt(abyte0, new byte[] {
/* 126*/                27, 28, 29, 30, 19, 20
                    })), SETS[0].charAt(getInt(abyte0, new byte[] {
/* 126*/                21, 22, 23, 24, 13, 14
                    })), SETS[0].charAt(getInt(abyte0, new byte[] {
/* 126*/                15, 16, 17, 18, 7, 8
                    })), SETS[0].charAt(getInt(abyte0, new byte[] {
/* 126*/                9, 10, 11, 12, 1, 2
                    }))
                });
            }

            private static String getMessage(byte abyte0[], int i, int j)
            {
/* 139*/        StringBuilder stringbuilder = new StringBuilder();
/* 140*/        int k = -1;
/* 141*/        int l = 0;
/* 142*/        int i1 = 0;
/* 143*/        for(int j1 = i; j1 < i + j; j1++)
                {
                    int k1;
/* 144*/            switch(k1 = SETS[l].charAt(abyte0[j1]))
                    {
/* 147*/            case 65527: 
/* 147*/                l = 0;
/* 148*/                k = -1;
                        break;

/* 151*/            case 65528: 
/* 151*/                l = 1;
/* 152*/                k = -1;
                        break;

/* 159*/            case 65520: 
/* 159*/            case 65521: 
/* 159*/            case 65522: 
/* 159*/            case 65523: 
/* 159*/            case 65524: 
/* 159*/                i1 = l;
/* 160*/                l = k1 - 65520;
/* 161*/                k = 1;
                        break;

/* 164*/            case 65525: 
/* 164*/                i1 = l;
/* 165*/                l = 0;
/* 166*/                k = 2;
                        break;

/* 169*/            case 65526: 
/* 169*/                i1 = l;
/* 170*/                l = 0;
/* 171*/                k = 3;
                        break;

/* 174*/            case 65531: 
/* 174*/                k1 = (abyte0[++j1] << 24) + (abyte0[++j1] << 18) + (abyte0[++j1] << 12) + (abyte0[++j1] << 6) + abyte0[++j1];
/* 175*/                stringbuilder.append(NINE_DIGITS.format(k1));
                        break;

/* 178*/            case 65529: 
/* 178*/                k = -1;
                        break;

/* 181*/            case 65530: 
/* 181*/            default:
/* 181*/                stringbuilder.append(k1);
                        break;
                    }
/* 183*/            if(k-- == 0)
/* 184*/                l = i1;
                }

/* 187*/        for(; stringbuilder.length() > 0 && stringbuilder.charAt(stringbuilder.length() - 1) == '\uFFFC'; stringbuilder.setLength(stringbuilder.length() - 1));
/* 190*/        return stringbuilder.toString();
            }

            private static final char SHIFTA = 65520;
            private static final char SHIFTB = 65521;
            private static final char SHIFTC = 65522;
            private static final char SHIFTD = 65523;
            private static final char SHIFTE = 65524;
            private static final char TWOSHIFTA = 65525;
            private static final char THREESHIFTA = 65526;
            private static final char LATCHA = 65527;
            private static final char LATCHB = 65528;
            private static final char LOCK = 65529;
            private static final char ECI = 65530;
            private static final char NS = 65531;
            private static final char PAD = 65532;
            private static final char FS = 28;
            private static final char GS = 29;
            private static final char RS = 30;
            private static final NumberFormat NINE_DIGITS = new DecimalFormat("000000000");
            private static final NumberFormat THREE_DIGITS = new DecimalFormat("000");
            private static final String SETS[] = {
/*  51*/        "\nABCDEFGHIJKLMNOPQRSTUVWXYZ\uFFFA\034\035\036\uFFFB \uFFFC\"#$%&'()*+,-./0123456789:\uFFF1\uFFF2\uFFF3\uFFF4\uFFF8", "`abcdefghijklmnopqrstuvwxyz\uFFFA\034\035\036\uFFFB{\uFFFC}~\177;<=>?[\\]^_ ,./:@!|\uFFFC\uFFF5\uFFF6\uFFFC\uFFF0\uFFF2\uFFF3\uFFF4\uFFF7", "\300\301\302\303\304\305\306\307\310\311\312\313\314\315\316\317\320\321\322\323\324\325\326\327\330\331\332\uFFFA\034\035\036\333\334\335\336\337\252\254\261\262\263\265\271\272\274\275\276\200\201\202\203\204\205\206\207\210\211\uFFF7 \uFFF9\uFFF3\uFFF4\uFFF8", "\340\341\342\343\344\345\346\347\350\351\352\353\354\355\356\357\360\361\362\363\364\365\366\367\370\371\372\uFFFA\034\035\036\uFFFB\373\374\375\376\377\241\250\253\257\260\264\267\270\273\277\212\213\214\215\216\217\220\221\222\223\224\uFFF7 \uFFF2\uFFF9\uFFF4\uFFF8", "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\uFFFA\uFFFC\uFFFC\033\uFFFB\034\035\036\037\237\240\242\243\244\245\246\247\251\255\256\266\225\226\227\230\231\232\233\234\235\236\uFFF7 \uFFF2\uFFF3\uFFF9\uFFF8", "\000\001\002\003\004\005\006\007\b\t\n\013\f\r\016\017\020\021\022\023\024\025\026\027\030\031\032\033\034\035\036\037 !\"#$%&'()*+,-./0123456789:;<=>?"
            };

}
