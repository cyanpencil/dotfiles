// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Expr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, ASTree, Visitor

public class Expr extends ASTList
    implements TokenId
{

            Expr(int i, ASTree astree, ASTList astlist)
            {
/*  35*/        super(astree, astlist);
/*  36*/        operatorId = i;
            }

            Expr(int i, ASTree astree)
            {
/*  40*/        super(astree);
/*  41*/        operatorId = i;
            }

            public static Expr make(int i, ASTree astree, ASTree astree1)
            {
/*  45*/        return new Expr(i, astree, new ASTList(astree1));
            }

            public static Expr make(int i, ASTree astree)
            {
/*  49*/        return new Expr(i, astree);
            }

            public int getOperator()
            {
/*  52*/        return operatorId;
            }

            public void setOperator(int i)
            {
/*  54*/        operatorId = i;
            }

            public ASTree oprand1()
            {
/*  56*/        return getLeft();
            }

            public void setOprand1(ASTree astree)
            {
/*  59*/        setLeft(astree);
            }

            public ASTree oprand2()
            {
/*  62*/        return getRight().getLeft();
            }

            public void setOprand2(ASTree astree)
            {
/*  65*/        getRight().setLeft(astree);
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  68*/        visitor.atExpr(this);
            }

            public String getName()
            {
                int i;
/*  71*/        if((i = operatorId) < 128)
/*  73*/            return String.valueOf((char)i);
/*  74*/        if(350 <= i && i <= 371)
/*  75*/            return opNames[i - 350];
/*  76*/        if(i == 323)
/*  77*/            return "instanceof";
/*  79*/        else
/*  79*/            return String.valueOf(i);
            }

            protected String getTag()
            {
/*  83*/        return (new StringBuilder("op:")).append(getName()).toString();
            }

            protected int operatorId;
}
