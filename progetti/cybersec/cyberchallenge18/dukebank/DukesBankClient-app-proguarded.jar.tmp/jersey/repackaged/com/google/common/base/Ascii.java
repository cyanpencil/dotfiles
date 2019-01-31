// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Ascii.java

package jersey.repackaged.com.google.common.base;


public final class Ascii
{

            public static String toLowerCase(String s)
            {
/* 438*/        int i = s.length();
/* 439*/        for(int j = 0; j < i; j++)
/* 440*/            if(isUpperCase(s.charAt(j)))
                    {
/* 441*/                s = s.toCharArray();
/* 442*/                for(; j < i; j++)
                        {
                            char c;
/* 443*/                    if(isUpperCase(c = s[j]))
/* 445*/                        s[j] = (char)(c ^ 0x20);
                        }

/* 448*/                return String.valueOf(s);
                    }

/* 451*/        return s;
            }

            public static boolean isUpperCase(char c)
            {
/* 547*/        return c >= 'A' && c <= 'Z';
            }
}
