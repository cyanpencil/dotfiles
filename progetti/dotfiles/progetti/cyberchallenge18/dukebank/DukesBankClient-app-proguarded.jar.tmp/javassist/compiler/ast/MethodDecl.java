// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   MethodDecl.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, Declarator, Stmnt, Symbol, 
//            Visitor, ASTree

public class MethodDecl extends ASTList
{

            public MethodDecl(ASTree astree, ASTList astlist)
            {
/*  25*/        super(astree, astlist);
            }

            public boolean isConstructor()
            {
                Symbol symbol;
/*  29*/        return (symbol = getReturn().getVariable()) != null && "<init>".equals(symbol.get());
            }

            public ASTList getModifiers()
            {
/*  33*/        return (ASTList)getLeft();
            }

            public Declarator getReturn()
            {
/*  35*/        return (Declarator)tail().head();
            }

            public ASTList getParams()
            {
/*  37*/        return (ASTList)sublist(2).head();
            }

            public ASTList getThrows()
            {
/*  39*/        return (ASTList)sublist(3).head();
            }

            public Stmnt getBody()
            {
/*  41*/        return (Stmnt)sublist(4).head();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  44*/        visitor.atMethodDecl(this);
            }

            public static final String initName = "<init>";
}
