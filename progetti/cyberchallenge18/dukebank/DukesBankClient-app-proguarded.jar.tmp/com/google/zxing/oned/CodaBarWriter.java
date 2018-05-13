// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CodaBarWriter.java

package com.google.zxing.oned;

import java.util.Arrays;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, CodaBarReader

public final class CodaBarWriter extends OneDimensionalCodeWriter
{

            public CodaBarWriter()
            {
            }

            public final boolean[] encode(String s)
            {
/*  35*/        if(s.length() < 2)
/*  36*/            throw new IllegalArgumentException("Codabar should start/end with start/stop symbols");
/*  39*/        int i = Character.toUpperCase(s.charAt(0));
/*  40*/        char c = Character.toUpperCase(s.charAt(s.length() - 1));
/*  41*/        boolean flag1 = CodaBarReader.arrayContains(START_END_CHARS, i) && CodaBarReader.arrayContains(START_END_CHARS, c);
/*  44*/        boolean flag = CodaBarReader.arrayContains(ALT_START_END_CHARS, i) && CodaBarReader.arrayContains(ALT_START_END_CHARS, c);
/*  47*/        if(!flag1 && !flag)
/*  48*/            throw new IllegalArgumentException((new StringBuilder("Codabar should start/end with ")).append(Arrays.toString(START_END_CHARS)).append(", or start/end with ").append(Arrays.toString(ALT_START_END_CHARS)).toString());
/*  54*/        flag = 20;
/*  55*/        for(int j = 1; j < s.length() - 1; j++)
                {
/*  56*/            if(Character.isDigit(s.charAt(j)) || s.charAt(j) == '-' || s.charAt(j) == '$')
                    {
/*  57*/                flag += 9;
/*  57*/                continue;
                    }
/*  58*/            if(CodaBarReader.arrayContains(CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED, s.charAt(j)))
/*  59*/                flag += 10;
/*  61*/            else
/*  61*/                throw new IllegalArgumentException((new StringBuilder("Cannot encode : '")).append(s.charAt(j)).append('\'').toString());
                }

/*  65*/        boolean aflag[] = new boolean[flag += s.length() - 1];
/*  68*/        flag = false;
/*  69*/        for(int k = 0; k < s.length(); k++)
                {
/*  70*/            int l = Character.toUpperCase(s.charAt(k));
/*  71*/            if(k == 0 || k == s.length() - 1)
/*  73*/                switch(l)
                        {
/*  75*/                case 84: // 'T'
/*  75*/                    l = 65;
                            break;

/*  78*/                case 78: // 'N'
/*  78*/                    l = 66;
                            break;

/*  81*/                case 42: // '*'
/*  81*/                    l = 67;
                            break;

/*  84*/                case 69: // 'E'
/*  84*/                    l = 68;
                            break;
                        }
/*  88*/            int i1 = 0;
/*  89*/            int j1 = 0;
/*  89*/            do
                    {
/*  89*/                if(j1 >= CodaBarReader.ALPHABET.length)
/*  91*/                    break;
/*  91*/                if(l == CodaBarReader.ALPHABET[j1])
                        {
/*  92*/                    i1 = CodaBarReader.CHARACTER_ENCODINGS[j1];
/*  93*/                    break;
                        }
/*  89*/                j1++;
                    } while(true);
/*  96*/            boolean flag2 = true;
/*  97*/            l = 0;
/*  98*/            for(int k1 = 0; k1 < 7;)
                    {
/* 100*/                aflag[flag] = flag2;
/* 101*/                flag++;
/* 102*/                if((i1 >> 6 - k1 & 1) == 0 || l == 1)
                        {
/* 103*/                    flag2 = !flag2;
/* 104*/                    k1++;
/* 105*/                    l = 0;
                        } else
                        {
/* 107*/                    l++;
                        }
                    }

/* 110*/            if(k < s.length() - 1)
                    {
/* 111*/                aflag[flag] = false;
/* 112*/                flag++;
                    }
                }

/* 115*/        return aflag;
            }

            private static final char START_END_CHARS[] = {
/*  28*/        'A', 'B', 'C', 'D'
            };
            private static final char ALT_START_END_CHARS[] = {
/*  29*/        'T', 'N', '*', 'E'
            };
            private static final char CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED[] = {
/*  30*/        '/', ':', '+', '.'
            };

}
