// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AddressBookAUResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, AddressBookParsedResult, ParsedResult

public final class AddressBookAUResultParser extends ResultParser
{

            public AddressBookAUResultParser()
            {
            }

            public final AddressBookParsedResult parse(Result result)
            {
/*  36*/        if(!(result = getMassagedText(result)).contains("MEMORY") || !result.contains("\r\n"))
                {
/*  39*/            return null;
                } else
                {
/*  44*/            String s = matchSinglePrefixedField("NAME1:", result, '\r', true);
/*  45*/            String s1 = matchSinglePrefixedField("NAME2:", result, '\r', true);
/*  47*/            String as[] = matchMultipleValuePrefix("TEL", 3, result, true);
/*  48*/            String as1[] = matchMultipleValuePrefix("MAIL", 3, result, true);
/*  49*/            String s2 = matchSinglePrefixedField("MEMORY:", result, '\r', false);
/*  50*/            result = (result = matchSinglePrefixedField("ADD:", result, '\r', true)) != null ? ((Result) (new String[] {
/*  51*/                result
                    })) : null;
/*  52*/            return new AddressBookParsedResult(maybeWrap(s), null, s1, as, null, as1, null, null, s2, result, null, null, null, null, null, null);
                }
            }

            private static String[] matchMultipleValuePrefix(String s, int i, String s1, boolean flag)
            {
/*  74*/        ArrayList arraylist = null;
                String s2;
/*  75*/        for(int j = 1; j <= i && (s2 = matchSinglePrefixedField((new StringBuilder()).append(s).append(j).append(':').toString(), s1, '\r', flag)) != null; j++)
                {
/*  80*/            if(arraylist == null)
/*  81*/                arraylist = new ArrayList(i);
/*  83*/            arraylist.add(s2);
                }

/*  85*/        if(arraylist == null)
/*  86*/            return null;
/*  88*/        else
/*  88*/            return (String[])arraylist.toArray(new String[arraylist.size()]);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  32*/        return parse(result);
            }
}
