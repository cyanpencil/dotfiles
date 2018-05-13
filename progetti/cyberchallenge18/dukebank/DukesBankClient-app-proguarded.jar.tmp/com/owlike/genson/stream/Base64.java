// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Base64.java

package com.owlike.genson.stream;

import java.util.Arrays;

class Base64
{

            Base64()
            {
            }

            public static final char[] encodeToChar(byte abyte0[], boolean flag)
            {
                int i;
/* 100*/        if((i = abyte0 == null ? 0 : abyte0.length) == 0)
/* 102*/            return new char[0];
/* 104*/        int j = (i / 3) * 3;
                int k;
/* 105*/        char ac[] = new char[k = (k = (i - 1) / 3 + 1 << 2) + (flag ? (k - 1) / 76 << 1 : 0)];
/* 110*/        int l = 0;
/* 110*/        int i1 = 0;
/* 110*/        int k1 = 0;
/* 110*/        do
                {
/* 110*/            if(l >= j)
/* 112*/                break;
/* 112*/            int l1 = (abyte0[l++] & 0xff) << 16 | (abyte0[l++] & 0xff) << 8 | abyte0[l++] & 0xff;
/* 115*/            ac[i1++] = CA[l1 >>> 18 & 0x3f];
/* 116*/            ac[i1++] = CA[l1 >>> 12 & 0x3f];
/* 117*/            ac[i1++] = CA[l1 >>> 6 & 0x3f];
/* 118*/            ac[i1++] = CA[l1 & 0x3f];
/* 121*/            if(flag && ++k1 == 19 && i1 < k - 2)
                    {
/* 122*/                ac[i1++] = '\r';
/* 123*/                ac[i1++] = '\n';
/* 124*/                k1 = 0;
                    }
                } while(true);
/* 129*/        if((l = i - j) > 0)
                {
/* 132*/            int j1 = (abyte0[j] & 0xff) << 10 | (l != 2 ? 0 : (abyte0[i - 1] & 0xff) << 2);
/* 135*/            ac[k - 4] = CA[j1 >> 12];
/* 136*/            ac[k - 3] = CA[j1 >>> 6 & 0x3f];
/* 137*/            ac[k - 2] = l != 2 ? '=' : CA[j1 & 0x3f];
/* 138*/            ac[k - 1] = '=';
                }
/* 140*/        return ac;
            }

            public static final byte[] decode(char ac[])
            {
                int i;
/* 153*/        if((i = ac == null ? 0 : ac.length) == 0)
/* 155*/            return new byte[0];
/* 159*/        int j = 0;
/* 160*/        for(int k = 0; k < i; k++)
/* 161*/            if(IA[ac[k]] < 0)
/* 162*/                j++;

/* 165*/        if((i - j) % 4 != 0)
/* 166*/            return null;
/* 168*/        int l = 0;
/* 169*/        int i1 = i;
/* 169*/        do
                {
/* 169*/            if(i1 <= 1 || IA[ac[--i1]] > 0)
/* 170*/                break;
/* 170*/            if(ac[i1] == '=')
/* 171*/                l++;
                } while(true);
/* 173*/        byte abyte0[] = new byte[i1 = ((i - j) * 6 >> 3) - l];
/* 177*/        j = 0;
/* 177*/        l = 0;
/* 177*/        do
                {
/* 177*/            if(l >= i1)
/* 179*/                break;
/* 179*/            int j1 = 0;
/* 180*/            for(int k1 = 0; k1 < 4; k1++)
                    {
                        int l1;
/* 181*/                if((l1 = IA[ac[j++]]) >= 0)
/* 183*/                    j1 |= l1 << 18 - k1 * 6;
/* 185*/                else
/* 185*/                    k1--;
                    }

/* 188*/            abyte0[l++] = (byte)(j1 >> 16);
/* 189*/            if(l < i1)
                    {
/* 190*/                abyte0[l++] = (byte)(j1 >> 8);
/* 191*/                if(l < i1)
/* 192*/                    abyte0[l++] = (byte)j1;
                    }
                } while(true);
/* 195*/        return abyte0;
            }

            public static final byte[] decodeFast(char ac[])
            {
                int i;
/* 211*/        if((i = ac.length) == 0)
/* 213*/            return new byte[0];
/* 215*/        int j = 0;
                int k;
/* 215*/        for(k = i - 1; j < k && IA[ac[j]] < 0; j++);
/* 222*/        for(; k > 0 && IA[ac[k]] < 0; k--);
/* 226*/        byte byte0 = ac[k] != '=' ? 0 : ((byte)(ac[k - 1] != '=' ? 1 : 2));
/* 227*/        int l = (k - j) + 1;
/* 228*/        i = i <= 76 ? 0 : (ac[76] != '\r' ? 0 : l / 78) << 1;
/* 230*/        byte abyte0[] = new byte[l = ((l - i) * 6 >> 3) - byte0];
/* 234*/        int i1 = 0;
/* 235*/        int j1 = 0;
/* 235*/        int l1 = (l / 3) * 3;
/* 235*/        do
                {
/* 235*/            if(i1 >= l1)
/* 237*/                break;
/* 237*/            int k2 = IA[ac[j++]] << 18 | IA[ac[j++]] << 12 | IA[ac[j++]] << 6 | IA[ac[j++]];
/* 240*/            abyte0[i1++] = (byte)(k2 >> 16);
/* 241*/            abyte0[i1++] = (byte)(k2 >> 8);
/* 242*/            abyte0[i1++] = (byte)k2;
/* 245*/            if(i > 0 && ++j1 == 19)
                    {
/* 246*/                j += 2;
/* 247*/                j1 = 0;
                    }
                } while(true);
/* 251*/        if(i1 < l)
                {
/* 253*/            int k1 = 0;
/* 254*/            for(int i2 = 0; j <= k - byte0; i2++)
/* 255*/                k1 |= IA[ac[j++]] << 18 - i2 * 6;

/* 257*/            for(int j2 = 16; i1 < l; j2 -= 8)
/* 258*/                abyte0[i1++] = (byte)(k1 >> j2);

                }
/* 261*/        return abyte0;
            }

            public static final byte[] encodeToByte(byte abyte0[], boolean flag)
            {
                int i;
/* 279*/        if((i = abyte0 == null ? 0 : abyte0.length) == 0)
/* 281*/            return new byte[0];
/* 283*/        int j = (i / 3) * 3;
                int k;
/* 284*/        byte abyte1[] = new byte[k = (k = (i - 1) / 3 + 1 << 2) + (flag ? (k - 1) / 76 << 1 : 0)];
/* 289*/        int l = 0;
/* 289*/        int i1 = 0;
/* 289*/        int k1 = 0;
/* 289*/        do
                {
/* 289*/            if(l >= j)
/* 291*/                break;
/* 291*/            int l1 = (abyte0[l++] & 0xff) << 16 | (abyte0[l++] & 0xff) << 8 | abyte0[l++] & 0xff;
/* 294*/            abyte1[i1++] = (byte)CA[l1 >>> 18 & 0x3f];
/* 295*/            abyte1[i1++] = (byte)CA[l1 >>> 12 & 0x3f];
/* 296*/            abyte1[i1++] = (byte)CA[l1 >>> 6 & 0x3f];
/* 297*/            abyte1[i1++] = (byte)CA[l1 & 0x3f];
/* 300*/            if(flag && ++k1 == 19 && i1 < k - 2)
                    {
/* 301*/                abyte1[i1++] = 13;
/* 302*/                abyte1[i1++] = 10;
/* 303*/                k1 = 0;
                    }
                } while(true);
/* 308*/        if((l = i - j) > 0)
                {
/* 311*/            int j1 = (abyte0[j] & 0xff) << 10 | (l != 2 ? 0 : (abyte0[i - 1] & 0xff) << 2);
/* 314*/            abyte1[k - 4] = (byte)CA[j1 >> 12];
/* 315*/            abyte1[k - 3] = (byte)CA[j1 >>> 6 & 0x3f];
/* 316*/            abyte1[k - 2] = l != 2 ? 61 : (byte)CA[j1 & 0x3f];
/* 317*/            abyte1[k - 1] = 61;
                }
/* 319*/        return abyte1;
            }

            public static final byte[] decode(byte abyte0[])
            {
/* 332*/        int i = abyte0.length;
/* 336*/        int j = 0;
/* 337*/        for(int k = 0; k < i; k++)
/* 338*/            if(IA[abyte0[k] & 0xff] < 0)
/* 339*/                j++;

/* 342*/        if((i - j) % 4 != 0)
/* 343*/            return null;
/* 345*/        int l = 0;
/* 346*/        int i1 = i;
/* 346*/        do
                {
/* 346*/            if(i1 <= 1 || IA[abyte0[--i1] & 0xff] > 0)
/* 347*/                break;
/* 347*/            if(abyte0[i1] == 61)
/* 348*/                l++;
                } while(true);
/* 350*/        byte abyte1[] = new byte[i1 = ((i - j) * 6 >> 3) - l];
/* 354*/        j = 0;
/* 354*/        l = 0;
/* 354*/        do
                {
/* 354*/            if(l >= i1)
/* 356*/                break;
/* 356*/            int j1 = 0;
/* 357*/            for(int k1 = 0; k1 < 4; k1++)
                    {
                        int l1;
/* 358*/                if((l1 = IA[abyte0[j++] & 0xff]) >= 0)
/* 360*/                    j1 |= l1 << 18 - k1 * 6;
/* 362*/                else
/* 362*/                    k1--;
                    }

/* 366*/            abyte1[l++] = (byte)(j1 >> 16);
/* 367*/            if(l < i1)
                    {
/* 368*/                abyte1[l++] = (byte)(j1 >> 8);
/* 369*/                if(l < i1)
/* 370*/                    abyte1[l++] = (byte)j1;
                    }
                } while(true);
/* 374*/        return abyte1;
            }

            public static final byte[] decodeFast(byte abyte0[])
            {
                int i;
/* 391*/        if((i = abyte0.length) == 0)
/* 393*/            return new byte[0];
/* 395*/        int j = 0;
                int k;
/* 395*/        for(k = i - 1; j < k && IA[abyte0[j] & 0xff] < 0; j++);
/* 402*/        for(; k > 0 && IA[abyte0[k] & 0xff] < 0; k--);
/* 406*/        byte byte0 = abyte0[k] != 61 ? 0 : ((byte)(abyte0[k - 1] != 61 ? 1 : 2));
/* 407*/        int l = (k - j) + 1;
/* 408*/        i = i <= 76 ? 0 : (abyte0[76] != 13 ? 0 : l / 78) << 1;
/* 410*/        byte abyte1[] = new byte[l = ((l - i) * 6 >> 3) - byte0];
/* 414*/        int i1 = 0;
/* 415*/        int j1 = 0;
/* 415*/        int l1 = (l / 3) * 3;
/* 415*/        do
                {
/* 415*/            if(i1 >= l1)
/* 417*/                break;
/* 417*/            int k2 = IA[abyte0[j++]] << 18 | IA[abyte0[j++]] << 12 | IA[abyte0[j++]] << 6 | IA[abyte0[j++]];
/* 420*/            abyte1[i1++] = (byte)(k2 >> 16);
/* 421*/            abyte1[i1++] = (byte)(k2 >> 8);
/* 422*/            abyte1[i1++] = (byte)k2;
/* 425*/            if(i > 0 && ++j1 == 19)
                    {
/* 426*/                j += 2;
/* 427*/                j1 = 0;
                    }
                } while(true);
/* 431*/        if(i1 < l)
                {
/* 433*/            int k1 = 0;
/* 434*/            for(int i2 = 0; j <= k - byte0; i2++)
/* 435*/                k1 |= IA[abyte0[j++]] << 18 - i2 * 6;

/* 437*/            for(int j2 = 16; i1 < l; j2 -= 8)
/* 438*/                abyte1[i1++] = (byte)(k1 >> j2);

                }
/* 441*/        return abyte1;
            }

            public static final String encodeToString(byte abyte0[], boolean flag)
            {
/* 459*/        return new String(encodeToChar(abyte0, flag));
            }

            public static final byte[] decode(String s)
            {
                int i;
/* 474*/        if((i = s == null ? 0 : s.length()) == 0)
/* 476*/            return new byte[0];
/* 480*/        int j = 0;
/* 481*/        for(int k = 0; k < i; k++)
/* 482*/            if(IA[s.charAt(k)] < 0)
/* 483*/                j++;

/* 486*/        if((i - j) % 4 != 0)
/* 487*/            return null;
/* 490*/        int l = 0;
/* 491*/        int i1 = i;
/* 491*/        do
                {
/* 491*/            if(i1 <= 1 || IA[s.charAt(--i1)] > 0)
/* 492*/                break;
/* 492*/            if(s.charAt(i1) == '=')
/* 493*/                l++;
                } while(true);
/* 495*/        byte abyte0[] = new byte[i1 = ((i - j) * 6 >> 3) - l];
/* 499*/        j = 0;
/* 499*/        l = 0;
/* 499*/        do
                {
/* 499*/            if(l >= i1)
/* 501*/                break;
/* 501*/            int j1 = 0;
/* 502*/            for(int k1 = 0; k1 < 4; k1++)
                    {
                        int l1;
/* 503*/                if((l1 = IA[s.charAt(j++)]) >= 0)
/* 505*/                    j1 |= l1 << 18 - k1 * 6;
/* 507*/                else
/* 507*/                    k1--;
                    }

/* 510*/            abyte0[l++] = (byte)(j1 >> 16);
/* 511*/            if(l < i1)
                    {
/* 512*/                abyte0[l++] = (byte)(j1 >> 8);
/* 513*/                if(l < i1)
/* 514*/                    abyte0[l++] = (byte)j1;
                    }
                } while(true);
/* 517*/        return abyte0;
            }

            public static final byte[] decodeFast(String s)
            {
                int i;
/* 533*/        if((i = s.length()) == 0)
/* 535*/            return new byte[0];
/* 537*/        int j = 0;
                int k;
/* 537*/        for(k = i - 1; j < k && IA[s.charAt(j) & 0xff] < 0; j++);
/* 544*/        for(; k > 0 && IA[s.charAt(k) & 0xff] < 0; k--);
/* 548*/        byte byte0 = s.charAt(k) != '=' ? 0 : ((byte)(s.charAt(k - 1) != '=' ? 1 : 2));
/* 549*/        int l = (k - j) + 1;
/* 550*/        i = i <= 76 ? 0 : (s.charAt(76) != '\r' ? 0 : l / 78) << 1;
/* 552*/        byte abyte0[] = new byte[l = ((l - i) * 6 >> 3) - byte0];
/* 556*/        int i1 = 0;
/* 557*/        int j1 = 0;
/* 557*/        int l1 = (l / 3) * 3;
/* 557*/        do
                {
/* 557*/            if(i1 >= l1)
/* 559*/                break;
/* 559*/            int k2 = IA[s.charAt(j++)] << 18 | IA[s.charAt(j++)] << 12 | IA[s.charAt(j++)] << 6 | IA[s.charAt(j++)];
/* 562*/            abyte0[i1++] = (byte)(k2 >> 16);
/* 563*/            abyte0[i1++] = (byte)(k2 >> 8);
/* 564*/            abyte0[i1++] = (byte)k2;
/* 567*/            if(i > 0 && ++j1 == 19)
                    {
/* 568*/                j += 2;
/* 569*/                j1 = 0;
                    }
                } while(true);
/* 573*/        if(i1 < l)
                {
/* 575*/            int k1 = 0;
/* 576*/            for(int i2 = 0; j <= k - byte0; i2++)
/* 577*/                k1 |= IA[s.charAt(j++)] << 18 - i2 * 6;

/* 579*/            for(int j2 = 16; i1 < l; j2 -= 8)
/* 580*/                abyte0[i1++] = (byte)(k1 >> j2);

                }
/* 583*/        return abyte0;
            }

            private static final char CA[];
            private static final int IA[];

            static 
            {
/*  75*/        CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
/*  76*/        Arrays.fill(IA = new int[256], -1);
/*  80*/        int i = 0;
/*  80*/        for(int j = CA.length; i < j; i++)
/*  81*/            IA[CA[i]] = i;

/*  82*/        IA[61] = 0;
            }
}
