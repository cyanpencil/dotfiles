// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Javac.java

package javassist.compiler;

import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;

// Referenced classes of package javassist.compiler:
//            CompileError, Javac, JvstCodeGen, JvstTypeChecker, 
//            ProceedHandler

class ee
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 443*/        bytecode = new Member(val$m);
/* 444*/        if(val$texpr != null)
/* 445*/            bytecode = Expr.make(46, val$texpr, bytecode);
/* 447*/        bytecode = CallExpr.makeCall(bytecode, astlist);
/* 448*/        jvstcodegen.compileExpr(bytecode);
/* 449*/        jvstcodegen.addNullIfVoid();
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 455*/        Object obj = new Member(val$m);
/* 456*/        if(val$texpr != null)
/* 457*/            obj = Expr.make(46, val$texpr, ((ASTree) (obj)));
/* 459*/        ((ASTree) (obj = CallExpr.makeCall(((ASTree) (obj)), astlist))).accept(jvsttypechecker);
/* 461*/        jvsttypechecker.addNullIfVoid();
            }

            final String val$m;
            final ASTree val$texpr;
            final Javac this$0;

            ee()
            {
/* 439*/        this$0 = final_javac;
/* 439*/        val$m = s;
/* 439*/        val$texpr = ASTree.this;
/* 439*/        super();
            }
}
