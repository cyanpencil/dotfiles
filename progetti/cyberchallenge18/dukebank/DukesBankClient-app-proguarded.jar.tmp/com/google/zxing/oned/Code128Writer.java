// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Code128Writer.java

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.*;

// Referenced classes of package com.google.zxing.oned:
//            OneDimensionalCodeWriter, Code128Reader

public final class Code128Writer extends OneDimensionalCodeWriter
{

            public Code128Writer()
            {
            }

            public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
                throws WriterException
            {
/*  58*/        if(barcodeformat != BarcodeFormat.CODE_128)
/*  59*/            throw new IllegalArgumentException((new StringBuilder("Can only encode CODE_128, but got ")).append(barcodeformat).toString());
/*  61*/        else
/*  61*/            return super.encode(s, barcodeformat, i, j, map);
            }

            public final boolean[] encode(String s)
            {
                int i;
/*  66*/        if((i = s.length()) <= 0 || i > 80)
/*  69*/            throw new IllegalArgumentException((new StringBuilder("Contents length should be between 1 and 80 characters, but got ")).append(i).toString());
/*  73*/        int k = 0;
/*  73*/        do
/*  73*/            if(k < i)
                    {
                        char c;
/*  74*/                if((c = s.charAt(k)) < ' ' || c > '~')
/*  76*/                    switch(c)
                            {
/*  83*/                    default:
/*  83*/                        throw new IllegalArgumentException((new StringBuilder("Bad character in input: ")).append(c).toString());

/*  66*/                    case 241: 
/*  66*/                    case 242: 
/*  66*/                    case 243: 
/*  66*/                    case 244: 
                                break;
                            }
/*  73*/                k++;
                    } else
                    {
/*  88*/                ArrayList arraylist = new ArrayList();
/*  89*/                int l = 0;
/*  90*/                int j1 = 1;
/*  91*/                int l1 = 0;
/*  92*/                int i2 = 0;
/*  94*/                do
                        {
/*  94*/                    if(i2 >= i)
/*  96*/                        break;
/*  96*/                    byte byte0 = ((byte)(l1 != 99 ? 4 : 2));
                            byte byte1;
/*  98*/                    if(isDigits(s, i2, byte0))
/*  99*/                        byte1 = 99;
/* 101*/                    else
/* 101*/                        byte1 = 100;
                            int k2;
/* 106*/                    if(byte1 == l1)
                            {
/* 109*/                        switch(s.charAt(i2))
                                {
/* 111*/                        case 241: 
/* 111*/                            k2 = 102;
/* 112*/                            break;

/* 114*/                        case 242: 
/* 114*/                            k2 = 97;
/* 115*/                            break;

/* 117*/                        case 243: 
/* 117*/                            k2 = 96;
/* 118*/                            break;

/* 120*/                        case 244: 
/* 120*/                            k2 = 100;
/* 121*/                            break;

/* 124*/                        default:
/* 124*/                            if(l1 == 100)
                                    {
/* 125*/                                k2 = s.charAt(i2) - 32;
                                    } else
                                    {
/* 127*/                                k2 = Integer.parseInt(s.substring(i2, i2 + 2));
/* 128*/                                i2++;
                                    }
                                    break;
                                }
/* 131*/                        i2++;
                            } else
                            {
/* 135*/                        if(l1 == 0)
                                {
/* 137*/                            if(byte1 == 100)
/* 138*/                                k2 = 104;
/* 141*/                            else
/* 141*/                                k2 = 105;
                                } else
                                {
/* 145*/                            k2 = byte1;
                                }
/* 147*/                        l1 = byte1;
                            }
/* 151*/                    arraylist.add(Code128Reader.CODE_PATTERNS[k2]);
/* 154*/                    l += k2 * j1;
/* 155*/                    if(i2 != 0)
/* 156*/                        j1++;
                        } while(true);
/* 161*/                l %= 103;
/* 162*/                arraylist.add(Code128Reader.CODE_PATTERNS[l]);
/* 165*/                arraylist.add(Code128Reader.CODE_PATTERNS[106]);
/* 168*/                int j2 = 0;
/* 169*/                for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
                        {
                            int ai1[];
/* 169*/                    int j = (s = ai1 = (int[])iterator.next()).length;
/* 170*/                    int i1 = 0;
/* 170*/                    while(i1 < j) 
                            {
/* 170*/                        int k1 = s[i1];
/* 171*/                        j2 += k1;
/* 170*/                        i1++;
                            }
                        }

/* 176*/                boolean aflag[] = new boolean[j2];
/* 177*/                int l2 = 0;
/* 178*/                for(s = arraylist.iterator(); s.hasNext();)
                        {
/* 178*/                    int ai[] = (int[])s.next();
/* 179*/                    l2 += appendPattern(aflag, l2, ai, true);
                        }

/* 182*/                return aflag;
                    }
/* 182*/        while(true);
            }

            private static boolean isDigits(CharSequence charsequence, int i, int j)
            {
/* 186*/        j = i + j;
/* 187*/        int k = charsequence.length();
/* 188*/        for(i = i; i < j && i < k; i++)
                {
                    char c;
/* 189*/            if((c = charsequence.charAt(i)) >= '0' && c <= '9')
/* 191*/                continue;
/* 191*/            if(c != '\361')
/* 192*/                return false;
/* 194*/            j++;
                }

/* 197*/        return j <= k;
            }

            private static final int CODE_START_B = 104;
            private static final int CODE_START_C = 105;
            private static final int CODE_CODE_B = 100;
            private static final int CODE_CODE_C = 99;
            private static final int CODE_STOP = 106;
            private static final char ESCAPE_FNC_1 = 241;
            private static final char ESCAPE_FNC_2 = 242;
            private static final char ESCAPE_FNC_3 = 243;
            private static final char ESCAPE_FNC_4 = 244;
            private static final int CODE_FNC_1 = 102;
            private static final int CODE_FNC_2 = 97;
            private static final int CODE_FNC_3 = 96;
            private static final int CODE_FNC_4_B = 100;
}
