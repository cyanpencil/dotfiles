// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Javac.java

package javassist.compiler;

import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.ASTree;

// Referenced classes of package javassist.compiler:
//            CompileError, Javac, JvstCodeGen, JvstTypeChecker, 
//            ProceedHandler

class val.desc
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 533*/        jvstcodegen.compileInvokeSpecial(val$texpr, val$cname, val$method, val$desc, astlist);
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 539*/        jvsttypechecker.compileInvokeSpecial(val$texpr, val$cname, val$method, val$desc, astlist);
            }

            final ASTree val$texpr;
            final String val$cname;
            final String val$method;
            final String val$desc;
            final Javac this$0;

            ee()
            {
/* 529*/        this$0 = final_javac;
/* 529*/        val$texpr = astree;
/* 529*/        val$cname = s;
/* 529*/        val$method = s1;
/* 529*/        val$desc = String.this;
/* 529*/        super();
            }
}
