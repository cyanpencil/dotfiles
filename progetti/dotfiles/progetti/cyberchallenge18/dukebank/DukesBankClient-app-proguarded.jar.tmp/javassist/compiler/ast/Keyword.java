// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Keyword.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, Visitor

public class Keyword extends ASTree
{

            public Keyword(int i)
            {
/*  28*/        tokenId = i;
            }

            public int get()
            {
/*  31*/        return tokenId;
            }

            public String toString()
            {
/*  33*/        return (new StringBuilder("id:")).append(tokenId).toString();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  35*/        visitor.atKeyword(this);
            }

            protected int tokenId;
}
