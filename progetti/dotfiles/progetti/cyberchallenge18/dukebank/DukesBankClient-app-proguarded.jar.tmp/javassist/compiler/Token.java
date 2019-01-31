// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Lex.java

package javassist.compiler;


class Token
{

            Token()
            {
/*  20*/        next = null;
            }

            public Token next;
            public int tokenId;
            public long longValue;
            public double doubleValue;
            public String textValue;
}
