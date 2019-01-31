// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewExpr.java

package javassist.expr;

import javassist.CtClass;
import javassist.bytecode.Bytecode;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            NewExpr

static class methodIndex
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 233*/        bytecode.addOpcode(187);
/* 234*/        bytecode.addIndex(newIndex);
/* 235*/        bytecode.addOpcode(89);
/* 236*/        jvstcodegen.atMethodCallCore(newType, "<init>", astlist, false, true, -1, null);
/* 238*/        jvstcodegen.setType(newType);
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 244*/        jvsttypechecker.atMethodCallCore(newType, "<init>", astlist);
/* 245*/        jvsttypechecker.setType(newType);
            }

            CtClass newType;
            int newIndex;
            int methodIndex;

            hod(CtClass ctclass, int i, int j)
            {
/* 225*/        newType = ctclass;
/* 226*/        newIndex = i;
/* 227*/        methodIndex = j;
            }
}
