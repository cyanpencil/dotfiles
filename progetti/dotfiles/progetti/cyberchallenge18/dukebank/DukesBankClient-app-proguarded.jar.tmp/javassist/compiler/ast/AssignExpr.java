// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   AssignExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            Expr, ASTList, Visitor, ASTree

public class AssignExpr extends Expr
{

            private AssignExpr(int i, ASTree astree, ASTList astlist)
            {
/*  30*/        super(i, astree, astlist);
            }

            public static AssignExpr makeAssign(int i, ASTree astree, ASTree astree1)
            {
/*  35*/        return new AssignExpr(i, astree, new ASTList(astree1));
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  39*/        visitor.atAssignExpr(this);
            }
}
