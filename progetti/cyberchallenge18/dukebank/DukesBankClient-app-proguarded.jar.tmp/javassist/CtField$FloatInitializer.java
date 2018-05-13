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
/*1255*/        if(!s.equals("F"))
/*1256*/            throw new CannotCompileException("type mismatch");
/*1257*/        else
/*1257*/            return;
            }

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1263*/        bytecode.addAload(0);
/*1264*/        bytecode.addFconst(value);
/*1265*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1266*/        return 3;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1272*/        bytecode.addFconst(value);
/*1273*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1274*/        return 2;
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/*1278*/        if(ctclass == CtClass.floatType)
/*1279*/            return constpool.addFloatInfo(value);
/*1281*/        else
/*1281*/            return 0;
            }

            float value;

            (float f)
            {
/*1252*/        value = f;
            }
}
