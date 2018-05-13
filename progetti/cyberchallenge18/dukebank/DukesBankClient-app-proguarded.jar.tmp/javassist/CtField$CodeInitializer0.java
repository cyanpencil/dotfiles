// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.StringL;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtField

static abstract class  extends 
{

            abstract void compileExpr(Javac javac)
                throws CompileError;

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/* 859*/        bytecode.addAload(0);
/* 860*/        compileExpr(javac);
/* 861*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/* 862*/        return bytecode.getMaxStack();
/* 864*/        ctclass;
/* 865*/        throw new CannotCompileException(ctclass);
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/* 873*/        compileExpr(javac);
/* 874*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/* 875*/        return bytecode.getMaxStack();
/* 877*/        ctclass;
/* 878*/        throw new CannotCompileException(ctclass);
            }

            int getConstantValue2(ConstPool constpool, CtClass ctclass, ASTree astree)
            {
/* 883*/        if(ctclass.isPrimitive())
                {
/* 884*/            if(astree instanceof IntConst)
                    {
/* 885*/                long l = ((IntConst)astree).get();
/* 886*/                if(ctclass == CtClass.doubleType)
/* 887*/                    return constpool.addDoubleInfo(l);
/* 888*/                if(ctclass == CtClass.floatType)
/* 889*/                    return constpool.addFloatInfo(l);
/* 890*/                if(ctclass == CtClass.longType)
/* 891*/                    return constpool.addLongInfo(l);
/* 892*/                if(ctclass != CtClass.voidType)
/* 893*/                    return constpool.addIntegerInfo((int)l);
                    } else
/* 895*/            if(astree instanceof DoubleConst)
                    {
/* 896*/                double d = ((DoubleConst)astree).get();
/* 897*/                if(ctclass == CtClass.floatType)
/* 898*/                    return constpool.addFloatInfo((float)d);
/* 899*/                if(ctclass == CtClass.doubleType)
/* 900*/                    return constpool.addDoubleInfo(d);
                    }
                } else
/* 903*/        if((astree instanceof StringL) && ctclass.getName().equals("java.lang.String"))
/* 905*/            return constpool.addStringInfo(((StringL)astree).get());
/* 907*/        return 0;
            }

            ()
            {
            }
}
