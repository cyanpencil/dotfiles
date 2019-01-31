// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   InstanceOfExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            CastExpr, Visitor, ASTList, ASTree

public class InstanceOfExpr extends CastExpr
{

            public InstanceOfExpr(ASTList astlist, int i, ASTree astree)
            {
/*  26*/        super(astlist, i, astree);
            }

            public InstanceOfExpr(int i, int j, ASTree astree)
            {
/*  30*/        super(i, j, astree);
            }

            public String getTag()
            {
/*  34*/        return (new StringBuilder("instanceof:")).append(castType).append(":").append(arrayDim).toString();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  38*/        visitor.atInstanceOfExpr(this);
            }
}
