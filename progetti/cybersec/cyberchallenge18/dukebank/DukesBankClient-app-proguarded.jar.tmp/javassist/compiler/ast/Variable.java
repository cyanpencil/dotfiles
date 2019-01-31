// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Variable.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            Symbol, Declarator, Visitor

public class Variable extends Symbol
{

            public Variable(String s, Declarator declarator1)
            {
/*  28*/        super(s);
/*  29*/        declarator = declarator1;
            }

            public Declarator getDeclarator()
            {
/*  32*/        return declarator;
            }

            public String toString()
            {
/*  35*/        return (new StringBuilder()).append(identifier).append(":").append(declarator.getType()).toString();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  38*/        visitor.atVariable(this);
            }

            protected Declarator declarator;
}
