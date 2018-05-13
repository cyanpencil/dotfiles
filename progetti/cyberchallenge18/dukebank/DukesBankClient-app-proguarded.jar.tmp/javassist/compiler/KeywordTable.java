// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   KeywordTable.java

package javassist.compiler;

import java.util.HashMap;

public final class KeywordTable extends HashMap
{

            public KeywordTable()
            {
            }

            public final int lookup(String s)
            {
/*  23*/        if((s = ((String) (get(s)))) == null)
/*  25*/            return -1;
/*  27*/        else
/*  27*/            return ((Integer)s).intValue();
            }

            public final void append(String s, int i)
            {
/*  31*/        put(s, new Integer(i));
            }
}
