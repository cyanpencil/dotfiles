// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Stmnt.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.TokenId;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, Visitor, ASTree

public class Stmnt extends ASTList
    implements TokenId
{

            public Stmnt(int i, ASTree astree, ASTList astlist)
            {
/*  29*/        super(astree, astlist);
/*  30*/        operatorId = i;
            }

            public Stmnt(int i, ASTree astree)
            {
/*  34*/        super(astree);
/*  35*/        operatorId = i;
            }

            public Stmnt(int i)
            {
/*  39*/        this(i, null);
            }

            public static Stmnt make(int i, ASTree astree, ASTree astree1)
            {
/*  43*/        return new Stmnt(i, astree, new ASTList(astree1));
            }

            public static Stmnt make(int i, ASTree astree, ASTree astree1, ASTree astree2)
            {
/*  47*/        return new Stmnt(i, astree, new ASTList(astree1, new ASTList(astree2)));
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  50*/        visitor.atStmnt(this);
            }

            public int getOperator()
            {
/*  52*/        return operatorId;
            }

            protected String getTag()
            {
/*  55*/        if(operatorId < 128)
/*  56*/            return (new StringBuilder("stmnt:")).append((char)operatorId).toString();
/*  58*/        else
/*  58*/            return (new StringBuilder("stmnt:")).append(operatorId).toString();
            }

            protected int operatorId;
}
