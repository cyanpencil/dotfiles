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

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1330*/        bytecode.addAload(0);
/*1331*/        bytecode.addLdc(value);
/*1332*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1333*/        return 2;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1339*/        bytecode.addLdc(value);
/*1340*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1341*/        return 1;
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/*1345*/        if(ctclass.getName().equals("java.lang.String"))
/*1346*/            return constpool.addStringInfo(value);
/*1348*/        else
/*1348*/            return 0;
            }

            String value;

            (String s)
            {
/*1324*/        value = s;
            }
}
