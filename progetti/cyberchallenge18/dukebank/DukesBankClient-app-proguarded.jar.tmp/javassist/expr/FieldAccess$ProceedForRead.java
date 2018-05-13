// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   FieldAccess.java

package javassist.expr;

import javassist.CtClass;
import javassist.CtPrimitiveType;
import javassist.bytecode.Bytecode;
import javassist.compiler.*;
import javassist.compiler.ast.ASTList;

// Referenced classes of package javassist.expr:
//            FieldAccess

static class index
    implements ProceedHandler
{

            public void doit(JvstCodeGen jvstcodegen, Bytecode bytecode, ASTList astlist)
                throws CompileError
            {
/* 240*/        if(astlist != null && !jvstcodegen.isParamListName(astlist))
/* 241*/            throw new CompileError("$proceed() cannot take a parameter for field reading");
/* 245*/        if(FieldAccess.isStatic(opcode))
                {
/* 246*/            astlist = 0;
                } else
                {
/* 248*/            astlist = -1;
/* 249*/            bytecode.addAload(targetVar);
                }
/* 252*/        if(fieldType instanceof CtPrimitiveType)
/* 253*/            astlist += ((CtPrimitiveType)fieldType).getDataSize();
/* 255*/        else
/* 255*/            astlist++;
/* 257*/        bytecode.add(opcode);
/* 258*/        bytecode.addIndex(index);
/* 259*/        bytecode.growStack(astlist);
/* 260*/        jvstcodegen.setType(fieldType);
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 266*/        jvsttypechecker.setType(fieldType);
            }

            CtClass fieldType;
            int opcode;
            int targetVar;
            int index;

            (CtClass ctclass, int i, int j, int k)
            {
/* 231*/        fieldType = ctclass;
/* 232*/        targetVar = k;
/* 233*/        opcode = i;
/* 234*/        index = j;
            }
}
