// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   NewArray.java

package javassist.expr;

import javassist.CtClass;
import javassist.bytecode.Bytecode;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            NewArray

static class dimension
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
                int i;
/* 256*/        if((i = jvstcodegen.getMethodArgsLength(astlist)) != dimension)
/* 258*/            throw new CompileError("$proceed() with a wrong number of parameters");
/* 261*/        jvstcodegen.atMethodArgs(astlist, new int[i], new int[i], new String[i]);
/* 263*/        bytecode.addOpcode(opcode);
/* 264*/        if(opcode == 189)
/* 265*/            bytecode.addIndex(index);
/* 266*/        else
/* 266*/        if(opcode == 188)
                {
/* 267*/            bytecode.add(index);
                } else
                {
/* 269*/            bytecode.addIndex(index);
/* 270*/            bytecode.add(dimension);
/* 271*/            bytecode.growStack(1 - dimension);
                }
/* 274*/        jvstcodegen.setType(arrayType);
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 280*/        jvsttypechecker.setType(arrayType);
            }

            CtClass arrayType;
            int opcode;
            int index;
            int dimension;

            (CtClass ctclass, int i, int j, int k)
            {
/* 247*/        arrayType = ctclass;
/* 248*/        opcode = i;
/* 249*/        index = j;
/* 250*/        dimension = k;
            }
}
