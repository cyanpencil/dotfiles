// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Cast.java

package javassist.expr;

import javassist.CtClass;
import javassist.bytecode.Bytecode;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Cast

static class retType
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 148*/        if(jvstcodegen.getMethodArgsLength(astlist) != 1)
                {
/* 149*/            throw new CompileError("$proceed() cannot take more than one parameter for cast");
                } else
                {
/* 153*/            jvstcodegen.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 154*/            bytecode.addOpcode(192);
/* 155*/            bytecode.addIndex(index);
/* 156*/            jvstcodegen.setType(retType);
/* 157*/            return;
                }
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 162*/        jvsttypechecker.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 163*/        jvsttypechecker.setType(retType);
            }

            int index;
            CtClass retType;

            (int i, CtClass ctclass)
            {
/* 141*/        index = i;
/* 142*/        retType = ctclass;
            }
}
