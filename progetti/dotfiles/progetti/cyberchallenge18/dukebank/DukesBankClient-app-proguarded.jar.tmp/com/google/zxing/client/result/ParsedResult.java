// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   ParsedResult.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResultType

public abstract class ParsedResult
{

            protected ParsedResult(ParsedResultType parsedresulttype)
            {
/*  35*/        type = parsedresulttype;
            }

            public final ParsedResultType getType()
            {
/*  39*/        return type;
            }

            public abstract String getDisplayResult();

            public final String toString()
            {
/*  46*/        return getDisplayResult();
            }

            public static void maybeAppend(String s, StringBuilder stringbuilder)
            {
/*  50*/        if(s != null && !s.isEmpty())
                {
/*  52*/            if(stringbuilder.length() > 0)
/*  53*/                stringbuilder.append('\n');
/*  55*/            stringbuilder.append(s);
                }
            }

            public static void maybeAppend(String as[], StringBuilder stringbuilder)
            {
/*  60*/        if(as != null)
                {
/*  61*/            int i = (as = as).length;
/*  61*/            for(int j = 0; j < i; j++)
                    {
                        String s;
/*  61*/                maybeAppend(s = as[j], stringbuilder);
                    }

                }
            }

            private final ParsedResultType type;
}
