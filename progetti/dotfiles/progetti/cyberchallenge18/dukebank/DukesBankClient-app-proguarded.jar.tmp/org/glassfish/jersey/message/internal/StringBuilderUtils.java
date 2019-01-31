// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringBuilderUtils.java

package org.glassfish.jersey.message.internal;


// Referenced classes of package org.glassfish.jersey.message.internal:
//            GrammarUtil

public class StringBuilderUtils
{

            public static void appendQuotedIfNonToken(StringBuilder stringbuilder, String s)
            {
/*  64*/        if(s == null)
/*  65*/            return;
                boolean flag;
/*  67*/        if(flag = !GrammarUtil.isTokenString(s))
/*  69*/            stringbuilder.append('"');
/*  71*/        appendEscapingQuotes(stringbuilder, s);
/*  72*/        if(flag)
/*  73*/            stringbuilder.append('"');
            }

            public static void appendQuotedIfWhitespace(StringBuilder stringbuilder, String s)
            {
/*  87*/        if(s == null)
/*  88*/            return;
                boolean flag;
/*  90*/        if(flag = GrammarUtil.containsWhiteSpace(s))
/*  92*/            stringbuilder.append('"');
/*  94*/        appendEscapingQuotes(stringbuilder, s);
/*  95*/        if(flag)
/*  96*/            stringbuilder.append('"');
            }

            public static void appendQuoted(StringBuilder stringbuilder, String s)
            {
/* 109*/        stringbuilder.append('"');
/* 110*/        appendEscapingQuotes(stringbuilder, s);
/* 111*/        stringbuilder.append('"');
            }

            public static void appendEscapingQuotes(StringBuilder stringbuilder, String s)
            {
/* 123*/        for(int i = 0; i < s.length(); i++)
                {
                    char c;
/* 124*/            if((c = s.charAt(i)) == '"')
/* 126*/                stringbuilder.append('\\');
/* 128*/            stringbuilder.append(c);
                }

            }

            private StringBuilderUtils()
            {
            }
}
