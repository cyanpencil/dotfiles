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
/* 288*/        if(jvstcodegen.getMethodArgsLength(astlist) != 1)
/* 289*/            throw new CompileError("$proceed() cannot take more than one parameter for field writing");
                int i;
/* 294*/        if(FieldAccess.isStatic(opcode))
                {
/* 295*/            i = 0;
                } else
                {
/* 297*/            i = -1;
/* 298*/            bytecode.addAload(targetVar);
                }
/* 301*/        jvstcodegen.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 302*/        jvstcodegen.doNumCast(fieldType);
/* 303*/        if(fieldType instanceof CtPrimitiveType)
/* 304*/            i -= ((CtPrimitiveType)fieldType).getDataSize();
/* 306*/        else
/* 306*/            i--;
/* 308*/        bytecode.add(opcode);
/* 309*/        bytecode.addIndex(index);
/* 310*/        bytecode.growStack(i);
/* 311*/        jvstcodegen.setType(CtClass.voidType);
/* 312*/        jvstcodegen.addNullIfVoid();
            }

            public void setReturnType(JvstTypeChecker jvsttypechecker, ASTList astlist)
                throws CompileError
            {
/* 318*/        jvsttypechecker.atMethodArgs(astlist, new int[1], new int[1], new String[1]);
/* 319*/        jvsttypechecker.setType(CtClass.voidType);
/* 320*/        jvsttypechecker.addNullIfVoid();
            }

            CtClass fieldType;
            int opcode;
            int targetVar;
            int index;

            (CtClass ctclass, int i, int j, int k)
            {
/* 279*/        fieldType = ctclass;
/* 280*/        targetVar = k;
/* 281*/        opcode = i;
/* 282*/        index = j;
            }
}
