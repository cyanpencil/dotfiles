// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   BinExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            Expr, ASTList, Visitor, ASTree

public class BinExpr extends Expr
{

            private BinExpr(int i, ASTree astree, ASTList astlist)
            {
/*  34*/        super(i, astree, astlist);
            }

            public static BinExpr makeBin(int i, ASTree astree, ASTree astree1)
            {
/*  38*/        return new BinExpr(i, astree, new ASTList(astree1));
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  41*/        visitor.atBinExpr(this);
            }
}
