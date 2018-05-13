// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtField

static class value extends value
{

            void check(String s)
                throws CannotCompileException
            {
/*1291*/        if(!s.equals("D"))
/*1292*/            throw new CannotCompileException("type mismatch");
/*1293*/        else
/*1293*/            return;
            }

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1299*/        bytecode.addAload(0);
/*1300*/        bytecode.addLdc2w(value);
/*1301*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1302*/        return 3;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1308*/        bytecode.addLdc2w(value);
/*1309*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1310*/        return 2;
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/*1314*/        if(ctclass == CtClass.doubleType)
/*1315*/            return constpool.addDoubleInfo(value);
/*1317*/        else
/*1317*/            return 0;
            }

            double value;

            (double d)
            {
/*1288*/        value = d;
            }
}
