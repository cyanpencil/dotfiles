// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.ConstPool;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.compiler.ast.ASTree;

// Referenced classes of package javassist:
//            CtField, CtClass

static class expression extends expression
{

            void compileExpr(Javac javac)
                throws CompileError
            {
/* 937*/        javac.compileExpr(expression);
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/* 941*/        return getConstantValue2(constpool, ctclass, expression);
            }

            private ASTree expression;

            (ASTree astree)
            {
/* 934*/        expression = astree;
            }
}
