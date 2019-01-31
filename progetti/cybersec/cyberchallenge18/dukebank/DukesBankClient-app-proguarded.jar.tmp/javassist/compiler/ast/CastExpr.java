// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CastExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, ASTree, Visitor

public class CastExpr extends ASTList
    implements TokenId
{

            public CastExpr(ASTList astlist, int i, ASTree astree)
            {
/*  30*/        super(astlist, new ASTList(astree));
/*  31*/        castType = 307;
/*  32*/        arrayDim = i;
            }

            public CastExpr(int i, int j, ASTree astree)
            {
/*  36*/        super(null, new ASTList(astree));
/*  37*/        castType = i;
/*  38*/        arrayDim = j;
            }

            public int getType()
            {
/*  43*/        return castType;
            }

            public int getArrayDim()
            {
/*  45*/        return arrayDim;
            }

            public ASTList getClassName()
            {
/*  47*/        return (ASTList)getLeft();
            }

            public ASTree getOprand()
            {
/*  49*/        return getRight().getLeft();
            }

            public void setOprand(ASTree astree)
            {
/*  51*/        getRight().setLeft(astree);
            }

            public String getTag()
            {
/*  53*/        return (new StringBuilder("cast:")).append(castType).append(":").append(arrayDim).toString();
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  55*/        visitor.atCastExpr(this);
            }

            protected int castType;
            protected int arrayDim;
}
