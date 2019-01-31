// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Tokenizer.java

package org.glassfish.jersey.internal.util;

import java.util.*;
import java.util.regex.Pattern;

public final class Tokenizer
{

            private Tokenizer()
            {
            }

            public static String[] tokenize(String as[])
            {
/*  74*/        return tokenize(as, " ,;\n");
            }

            public static String[] tokenize(String as[], String s)
            {
/*  90*/        LinkedList linkedlist = new LinkedList();
/*  92*/        int i = (as = as).length;
/*  92*/        for(int j = 0; j < i; j++)
                {
                    String s1;
/*  92*/            if((s1 = as[j]) != null && !s1.isEmpty() && !(s1 = s1.trim()).isEmpty())
/* 100*/                tokenize(s1, s, ((Collection) (linkedlist)));
                }

/* 103*/        return (String[])linkedlist.toArray(new String[linkedlist.size()]);
            }

            public static String[] tokenize(String s)
            {
/* 116*/        return tokenize(s, " ,;\n");
            }

            public static String[] tokenize(String s, String s1)
            {
/* 132*/        return (String[])(s = tokenize(s, s1, ((Collection) (new LinkedList())))).toArray(new String[s.size()]);
            }

            private static Collection tokenize(String s, String s1, Collection collection)
            {
                StringBuilder stringbuilder;
/* 137*/        (stringbuilder = new StringBuilder(s1.length() * 3)).append('[');
/* 139*/        int i = (s1 = s1.toCharArray()).length;
/* 139*/        for(int j = 0; j < i; j++)
                {
/* 139*/            char c = s1[j];
/* 140*/            stringbuilder.append(Pattern.quote(String.valueOf(c)));
                }

/* 142*/        stringbuilder.append(']');
                String as[];
/* 144*/        int k = (as = s1 = s.split(stringbuilder.toString())).length;
/* 145*/        for(int l = 0; l < k; l++)
/* 145*/            if((s = as[l]) != null && !s.isEmpty() && !(s = s.trim()).isEmpty())
/* 155*/                collection.add(s);

/* 158*/        return collection;
            }

            public static final String COMMON_DELIMITERS = " ,;\n";
}
