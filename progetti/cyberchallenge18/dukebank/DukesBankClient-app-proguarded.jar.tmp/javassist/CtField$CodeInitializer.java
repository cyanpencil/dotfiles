// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.ConstPool;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.compiler.SymbolTable;

// Referenced classes of package javassist:
//            CtClass, CtField

static class expression extends 
{

            void compileExpr(Javac javac)
                throws CompileError
            {
/* 917*/        javac.compileExpr(expression);
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/* 922*/        javassist.compiler.ast.ASTree astree = Javac.parseExpr(expression, new SymbolTable());
/* 923*/        return getConstantValue2(constpool, ctclass, astree);
/* 925*/        JVM INSTR pop ;
/* 926*/        return 0;
            }

            private String expression;

            (String s)
            {
/* 914*/        expression = s;
            }
}
