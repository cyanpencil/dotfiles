// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Symbol.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, Visitor

public class Symbol extends ASTree
{

            public Symbol(String s)
            {
/*  28*/        identifier = s;
            }

            public String get()
            {
/*  31*/        return identifier;
            }

            public String toString()
            {
/*  33*/        return identifier;
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  35*/        visitor.atSymbol(this);
            }

            protected String identifier;
}
