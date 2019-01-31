// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringUtils.java

package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Map;

public final class StringUtils
{

            private StringUtils()
            {
            }

            public static String guessEncoding(byte abyte0[], Map map)
            {
/*  52*/        if(map != null && (map = (String)map.get(DecodeHintType.CHARACTER_SET)) != null)
/*  55*/            return map;
/*  60*/        map = abyte0.length;
/*  61*/        boolean flag = true;
/*  62*/        boolean flag1 = true;
/*  63*/        boolean flag2 = true;
/*  64*/        int i = 0;
/*  66*/        int j = 0;
/*  67*/        int k = 0;
/*  68*/        int l = 0;
/*  69*/        int i1 = 0;
/*  71*/        int j1 = 0;
/*  73*/        int k1 = 0;
/*  74*/        int l1 = 0;
/*  75*/        int i2 = 0;
/*  76*/        int j2 = 0;
/*  79*/        int k2 = 0;
/*  81*/        boolean flag3 = abyte0.length > 3 && abyte0[0] == -17 && abyte0[1] == -69 && abyte0[2] == -65;
/*  86*/        for(int l2 = 0; l2 < map && (flag || flag1 || flag2); l2++)
                {
                    int i3;
/*  90*/label0:
                    {
/*  90*/                i3 = abyte0[l2] & 0xff;
/*  93*/                if(!flag2)
/*  94*/                    break label0;
/*  94*/                if(i > 0)
                        {
/*  95*/                    if((i3 & 0x80) != 0)
                            {
/*  98*/                        i--;
/*  98*/                        break label0;
                            }
                        } else
                        {
/* 100*/                    if((i3 & 0x80) == 0)
/* 101*/                        break label0;
/* 101*/                    if((i3 & 0x40) != 0)
                            {
/* 104*/                        i++;
/* 105*/                        if((i3 & 0x20) == 0)
                                {
/* 106*/                            j++;
/* 106*/                            break label0;
                                }
/* 108*/                        i++;
/* 109*/                        if((i3 & 0x10) == 0)
                                {
/* 110*/                            k++;
/* 110*/                            break label0;
                                }
/* 112*/                        i++;
/* 113*/                        if((i3 & 8) == 0)
                                {
/* 114*/                            l++;
/* 114*/                            break label0;
                                }
                            }
                        }
/* 116*/                flag2 = false;
                    }
/* 127*/            if(flag)
/* 128*/                if(i3 > 127 && i3 < 160)
/* 129*/                    flag = false;
/* 130*/                else
/* 130*/                if(i3 > 159 && (i3 < 192 || i3 == 215 || i3 == 247))
/* 132*/                    k2++;
/* 142*/            if(!flag1)
/* 143*/                continue;
/* 143*/            if(i1 > 0)
                    {
/* 144*/                if(i3 < 64 || i3 == 127 || i3 > 252)
/* 145*/                    flag1 = false;
/* 147*/                else
/* 147*/                    i1--;
/* 147*/                continue;
                    }
/* 149*/            if(i3 == 128 || i3 == 160 || i3 > 239)
                    {
/* 150*/                flag1 = false;
/* 150*/                continue;
                    }
/* 151*/            if(i3 > 160 && i3 < 224)
                    {
/* 152*/                j1++;
/* 153*/                l1 = 0;
/* 154*/                if(++k1 > i2)
/* 156*/                    i2 = k1;
/* 156*/                continue;
                    }
/* 158*/            if(i3 > 127)
                    {
/* 159*/                i1++;
/* 161*/                k1 = 0;
/* 162*/                if(++l1 > j2)
/* 164*/                    j2 = l1;
                    } else
                    {
/* 168*/                k1 = 0;
/* 169*/                l1 = 0;
                    }
                }

/* 174*/        if(flag2 && i > 0)
/* 175*/            flag2 = false;
/* 177*/        if(flag1 && i1 > 0)
/* 178*/            flag1 = false;
/* 182*/        if(flag2 && (flag3 || j + k + l > 0))
/* 183*/            return "UTF8";
/* 186*/        if(flag1 && (ASSUME_SHIFT_JIS || i2 >= 3 || j2 >= 3))
/* 187*/            return "SJIS";
/* 194*/        if(flag && flag1)
/* 195*/            if(i2 == 2 && j1 == 2 || k2 * 10 >= map)
/* 195*/                return "SJIS";
/* 195*/            else
/* 195*/                return "ISO8859_1";
/* 200*/        if(flag)
/* 201*/            return "ISO8859_1";
/* 203*/        if(flag1)
/* 204*/            return "SJIS";
/* 206*/        if(flag2)
/* 207*/            return "UTF8";
/* 210*/        else
/* 210*/            return PLATFORM_DEFAULT_ENCODING;
            }

            private static final String PLATFORM_DEFAULT_ENCODING = System.getProperty("file.encoding");
            public static final String SHIFT_JIS = "SJIS";
            public static final String GB2312 = "GB2312";
            private static final String EUC_JP = "EUC_JP";
            private static final String UTF8 = "UTF8";
            private static final String ISO88591 = "ISO8859_1";
            private static final boolean ASSUME_SHIFT_JIS = "SJIS".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING) || "EUC_JP".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING);

}
