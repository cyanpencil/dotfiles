// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   StringL.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTree, Visitor

public class StringL extends ASTree
{

            public StringL(String s)
            {
/*  28*/        text = s;
            }

            public String get()
            {
/*  31*/        return text;
            }

            public String toString()
            {
/*  33*/        return (new StringBuilder("\"")).append(text).append("\"").toString();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  35*/        visitor.atStringL(this);
            }

            protected String text;
}
