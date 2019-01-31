// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CondExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;

// Referenced classes of package javassist.compiler.ast:
//            ASTList, Visitor, ASTree

public class CondExpr extends ASTList
{

            public CondExpr(ASTree astree, ASTree astree1, ASTree astree2)
            {
/*  26*/        super(astree, new ASTList(astree1, new ASTList(astree2)));
            }

            public ASTree condExpr()
            {
/*  29*/        return head();
            }

            public void setCond(ASTree astree)
            {
/*  31*/        setHead(astree);
            }

            public ASTree thenExpr()
            {
/*  33*/        return tail().head();
            }

            public void setThen(ASTree astree)
            {
/*  35*/        tail().setHead(astree);
            }

            public ASTree elseExpr()
            {
/*  37*/        return tail().tail().head();
            }

            public void setElse(ASTree astree)
            {
/*  39*/        tail().tail().setHead(astree);
            }

            public String getTag()
            {
/*  41*/        return "?:";
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  43*/        visitor.atCondExpr(this);
            }
}
