// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Javac.java

package javassist.compiler;

import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.Symbol;

// Referenced classes of package javassist.compiler:
//            CompileError, Javac, JvstCodeGen, JvstTypeChecker, 
//            ProceedHandler

class val.m
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 487*/        bytecode = CallExpr.makeCall(bytecode = Expr.make(35, new Symbol(val$c), new Member(val$m)), astlist);
/* 490*/        jvstcodegen.compileExpr(bytecode);
/* 491*/        jvstcodegen.addNullIfVoid();
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
                Object obj;
/* 497*/        ((Expr) (obj = CallExpr.makeCall(((javassist.compiler.ast.ASTree) (obj = Expr.make(35, new Symbol(val$c), new Member(val$m)))), astlist))).accept(jvsttypechecker);
/* 501*/        jvsttypechecker.addNullIfVoid();
            }

            final String val$c;
            final String val$m;
            final Javac this$0;

            rror()
            {
/* 483*/        this$0 = final_javac;
/* 483*/        val$c = s;
/* 483*/        val$m = String.this;
/* 483*/        super();
            }
}
