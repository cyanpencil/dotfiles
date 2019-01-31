// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GrammarUtil.java

package org.glassfish.jersey.message.internal;


final class GrammarUtil
{

            private static int[] createEventTable()
            {
/*  93*/        int ai[] = new int[128];
/*  97*/        for(int i = 0; i < 32; i++)
/*  98*/            ai[i] = 4;

/* 100*/        ai[127] = 4;
/* 103*/        for(int j = 32; j < 127; j++)
/* 104*/            ai[j] = 0;

                char ac[];
/* 108*/        int k = (ac = SEPARATORS).length;
/* 108*/        for(int l = 0; l < k; l++)
                {
/* 108*/            char c = ac[l];
/* 109*/            ai[c] = 3;
                }

/* 113*/        ai[40] = 2;
/* 116*/        ai[34] = 1;
/* 119*/        k = (ac = WHITE_SPACE).length;
/* 119*/        for(int i1 = 0; i1 < k; i1++)
                {
/* 119*/            char c1 = ac[i1];
/* 120*/            ai[c1] = -1;
                }

/* 123*/        return ai;
            }

            private static boolean[] createWhiteSpaceTable()
            {
/* 127*/        boolean aflag[] = new boolean[128];
                char ac[];
/* 129*/        int i = (ac = WHITE_SPACE).length;
/* 129*/        for(int j = 0; j < i; j++)
                {
/* 129*/            char c = ac[j];
/* 130*/            aflag[c] = true;
                }

/* 133*/        return aflag;
            }

            private static boolean[] createTokenTable()
            {
/* 137*/        boolean aflag[] = new boolean[128];
/* 139*/        for(int i = 0; i <= 127; i++)
/* 140*/            aflag[i] = TYPE_TABLE[i] == 0;

/* 143*/        return aflag;
            }

            public static boolean isWhiteSpace(char c)
            {
/* 153*/        return c <= '\177' && IS_WHITE_SPACE[c];
            }

            public static boolean isToken(char c)
            {
/* 163*/        return c <= '\177' && IS_TOKEN[c];
            }

            public static int getType(char c)
            {
/* 175*/        if(c > '\177')
/* 176*/            throw new IllegalArgumentException((new StringBuilder("Unsupported character - ordinal value too high: ")).append(c).toString());
/* 178*/        else
/* 178*/            return TYPE_TABLE[c];
            }

            public static boolean isSeparator(char c)
            {
/* 188*/        return c <= '\177' && TYPE_TABLE[c] == 3;
            }

            public static boolean isTokenString(String s)
            {
/* 198*/        int i = (s = s.toCharArray()).length;
/* 198*/        for(int j = 0; j < i; j++)
                {
                    char c;
/* 198*/            if(!isToken(c = s[j]))
/* 200*/                return false;
                }

/* 203*/        return true;
            }

            public static boolean containsWhiteSpace(String s)
            {
/* 213*/        int i = (s = s.toCharArray()).length;
/* 213*/        for(int j = 0; j < i; j++)
                {
                    char c;
/* 213*/            if(isWhiteSpace(c = s[j]))
/* 215*/                return true;
                }

/* 218*/        return false;
            }

            public static String filterToken(CharSequence charsequence, int i, int j)
            {
/* 231*/        return filterToken(charsequence, i, j, false);
            }

            public static String filterToken(CharSequence charsequence, int i, int j, boolean flag)
            {
/* 246*/        StringBuilder stringbuilder = new StringBuilder();
/* 248*/        boolean flag1 = false;
/* 249*/        boolean flag2 = false;
/* 251*/        for(int k = i; k < j; k++)
                {
/* 252*/            if((i = charsequence.charAt(k)) == '\n' && flag2)
                    {
/* 256*/                flag2 = false;
/* 257*/                continue;
                    }
/* 260*/            flag2 = false;
/* 261*/            if(!flag1)
                    {
/* 263*/                if(!flag && i == 92)
                        {
/* 264*/                    flag1 = true;
/* 264*/                    continue;
                        }
/* 265*/                if(i == 13)
/* 266*/                    flag2 = true;
/* 268*/                else
/* 268*/                    stringbuilder.append(i);
                    } else
                    {
/* 274*/                stringbuilder.append(i);
/* 275*/                flag1 = false;
                    }
                }

/* 278*/        return stringbuilder.toString();
            }

            private GrammarUtil()
            {
            }

            public static final int TOKEN = 0;
            public static final int QUOTED_STRING = 1;
            public static final int COMMENT = 2;
            public static final int SEPARATOR = 3;
            public static final int CONTROL = 4;
            private static final char WHITE_SPACE[] = {
/*  73*/        '\t', '\r', '\n', ' '
            };
            private static final char SEPARATORS[] = {
/*  77*/        '(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', 
/*  77*/        '/', '[', ']', '?', '=', '{', '}', ' ', '\t'
            };
            private static final int TYPE_TABLE[] = createEventTable();
            private static final boolean IS_WHITE_SPACE[] = createWhiteSpaceTable();
            private static final boolean IS_TOKEN[] = createTokenTable();

}
