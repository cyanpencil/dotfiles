// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CallExpr.java

package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.compiler.MemberResolver;

// Referenced classes of package javassist.compiler.ast:
//            Expr, ASTList, Visitor, ASTree

public class CallExpr extends Expr
{

            private CallExpr(ASTree astree, ASTList astlist)
            {
/*  30*/        super(67, astree, astlist);
/*  31*/        method = null;
            }

            public void setMethod(javassist.compiler.MemberResolver.Method method1)
            {
/*  35*/        method = method1;
            }

            public javassist.compiler.MemberResolver.Method getMethod()
            {
/*  39*/        return method;
            }

            public static CallExpr makeCall(ASTree astree, ASTree astree1)
            {
/*  43*/        return new CallExpr(astree, new ASTList(astree1));
            }

            public void accept(Visitor visitor)
                throws CompileError
            {
/*  46*/        visitor.atCallExpr(this);
            }

            private javassist.compiler.MemberResolver.Method method;
}
