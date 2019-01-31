// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   SMSMMSResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.*;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, SMSParsedResult, ParsedResult

public final class SMSMMSResultParser extends ResultParser
{

            public SMSMMSResultParser()
            {
            }

            public final SMSParsedResult parse(Result result)
            {
/*  45*/        if(!(result = getMassagedText(result)).startsWith("sms:") && !result.startsWith("SMS:") && !result.startsWith("mms:") && !result.startsWith("MMS:"))
/*  48*/            return null;
/*  52*/        Map map = parseNameValuePairs(result);
/*  53*/        String s = null;
/*  54*/        String s1 = null;
/*  55*/        boolean flag = false;
/*  56*/        if(map != null && !map.isEmpty())
                {
/*  57*/            s = (String)map.get("subject");
/*  58*/            s1 = (String)map.get("body");
/*  59*/            flag = true;
                }
                int i;
/*  63*/        if((i = result.indexOf('?', 4)) < 0 || !flag)
/*  67*/            result = result.substring(4);
/*  69*/        else
/*  69*/            result = result.substring(4, i);
/*  72*/        i = -1;
/*  74*/        ArrayList arraylist = new ArrayList(1);
/*  75*/        ArrayList arraylist1 = new ArrayList(1);
                int j;
/*  76*/        for(; (j = result.indexOf(',', i + 1)) > i; i = j)
                {
/*  77*/            i = result.substring(i + 1, j);
/*  78*/            addNumberVia(arraylist, arraylist1, i);
                }

/*  81*/        addNumberVia(arraylist, arraylist1, result.substring(i + 1));
/*  83*/        return new SMSParsedResult((String[])arraylist.toArray(new String[arraylist.size()]), (String[])arraylist1.toArray(new String[arraylist1.size()]), s, s1);
            }

            private static void addNumberVia(Collection collection, Collection collection1, String s)
            {
                int i;
/*  92*/        if((i = s.indexOf(';')) < 0)
                {
/*  94*/            collection.add(s);
/*  95*/            collection1.add(null);
/*  95*/            return;
                }
/*  97*/        collection.add(s.substring(0, i));
/*  98*/        if((collection = s.substring(i + 1)).startsWith("via="))
/* 101*/            collection = collection.substring(4);
/* 103*/        else
/* 103*/            collection = null;
/* 105*/        collection1.add(collection);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  41*/        return parse(result);
            }
}
