// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AbstractDoCoMoResultParser.java

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ResultParser

abstract class AbstractDoCoMoResultParser extends ResultParser
{

            AbstractDoCoMoResultParser()
            {
            }

            static String[] matchDoCoMoPrefixedField(String s, String s1, boolean flag)
            {
/*  32*/        return matchPrefixedField(s, s1, ';', flag);
            }

            static String matchSingleDoCoMoPrefixedField(String s, String s1, boolean flag)
            {
/*  36*/        return matchSinglePrefixedField(s, s1, ';', flag);
            }
}
