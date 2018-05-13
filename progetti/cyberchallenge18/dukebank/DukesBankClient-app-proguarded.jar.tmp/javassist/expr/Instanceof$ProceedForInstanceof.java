// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   Instanceof.java

package javassist.expr;

import javassist.CtClass;
import javassist.bytecode.Bytecode;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            Instanceof

static class index
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 152*/        if(jvstcodegen.getMethodArgsLength(astlist) != 1)
                {
/* 153*/            throw new CompileError("$proceed() cannot take more than one parameter for instanceof");
                } else
                {
/* 157*/            jvstcodegen.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 158*/            bytecode.addOpcode(193);
/* 159*/            bytecode.addIndex(index);
/* 160*/            jvstcodegen.setType(CtClass.booleanType);
/* 161*/            return;
                }
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 166*/        jvsttypechecker.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 167*/        jvsttypechecker.setType(CtClass.booleanType);
            }

            int index;

            (int i)
            {
/* 146*/        index = i;
            }
}
