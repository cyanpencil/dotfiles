// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BizcardResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.zxing.client.result:
//            AbstractDoCoMoResultParser, AddressBookParsedResult, ParsedResult

public final class BizcardResultParser extends AbstractDoCoMoResultParser
{

            public BizcardResultParser()
            {
            }

            public final AddressBookParsedResult parse(Result result)
            {
/*  39*/        if(!(result = getMassagedText(result)).startsWith("BIZCARD:"))
                {
/*  41*/            return null;
                } else
                {
/*  43*/            String s = matchSingleDoCoMoPrefixedField("N:", result, true);
/*  44*/            String s1 = matchSingleDoCoMoPrefixedField("X:", result, true);
/*  45*/            s = buildName(s, s1);
/*  46*/            s1 = matchSingleDoCoMoPrefixedField("T:", result, true);
/*  47*/            String s2 = matchSingleDoCoMoPrefixedField("C:", result, true);
/*  48*/            String as[] = matchDoCoMoPrefixedField("A:", result, true);
/*  49*/            String s3 = matchSingleDoCoMoPrefixedField("B:", result, true);
/*  50*/            String s4 = matchSingleDoCoMoPrefixedField("M:", result, true);
/*  51*/            String s5 = matchSingleDoCoMoPrefixedField("F:", result, true);
/*  52*/            result = matchSingleDoCoMoPrefixedField("E:", result, true);
/*  54*/            return new AddressBookParsedResult(maybeWrap(s), null, null, buildPhoneNumbers(s3, s4, s5), null, maybeWrap(result), null, null, null, as, null, s2, null, s1, null, null);
                }
            }

            private static String[] buildPhoneNumbers(String s, String s1, String s2)
            {
/*  75*/        ArrayList arraylist = new ArrayList(3);
/*  76*/        if(s != null)
/*  77*/            arraylist.add(s);
/*  79*/        if(s1 != null)
/*  80*/            arraylist.add(s1);
/*  82*/        if(s2 != null)
/*  83*/            arraylist.add(s2);
/*  85*/        if((s = arraylist.size()) == 0)
/*  87*/            return null;
/*  89*/        else
/*  89*/            return (String[])arraylist.toArray(new String[s]);
            }

            private static String buildName(String s, String s1)
            {
/*  93*/        if(s == null)
/*  94*/            return s1;
/*  96*/        if(s1 == null)
/*  96*/            return s;
/*  96*/        else
/*  96*/            return (new StringBuilder()).append(s).append(' ').append(s1).toString();
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  31*/        return parse(result);
            }
}
