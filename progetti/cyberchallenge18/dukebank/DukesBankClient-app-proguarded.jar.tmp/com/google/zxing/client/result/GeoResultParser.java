// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   GeoResultParser.java

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, GeoParsedResult, ParsedResult

public final class GeoResultParser extends ResultParser
{

            public GeoResultParser()
            {
            }

            public final GeoParsedResult parse(Result result)
            {
                String s;
/*  39*/        result = getMassagedText(result);
/*  40*/        if(!(result = GEO_URL_PATTERN.matcher(result)).matches())
/*  42*/            return null;
/*  45*/        s = result.group(4);
                double d;
/*  51*/        if((d = Double.parseDouble(result.group(1))) > 90D || d < -90D)
/*  53*/            return null;
                double d1;
/*  55*/        if((d1 = Double.parseDouble(result.group(2))) > 180D || d1 < -180D)
/*  57*/            return null;
                double d2;
/*  59*/        if(result.group(3) == null)
                {
/*  60*/            d2 = 0.0D;
/*  60*/            break MISSING_BLOCK_LABEL_124;
                }
/*  62*/        if((d2 = Double.parseDouble(result.group(3))) < 0.0D)
/*  64*/            return null;
/*  69*/        break MISSING_BLOCK_LABEL_124;
/*  67*/        JVM INSTR pop ;
/*  68*/        return null;
/*  70*/        return new GeoParsedResult(d, d1, d2, s);
            }

            public final volatile ParsedResult parse(Result result)
            {
/*  32*/        return parse(result);
            }

            private static final Pattern GEO_URL_PATTERN = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

}
