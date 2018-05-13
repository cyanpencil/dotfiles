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
/*1219*/        if(!s.equals("J"))
/*1220*/            throw new CannotCompileException("type mismatch");
/*1221*/        else
/*1221*/            return;
            }

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1227*/        bytecode.addAload(0);
/*1228*/        bytecode.addLdc2w(value);
/*1229*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1230*/        return 3;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1236*/        bytecode.addLdc2w(value);
/*1237*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1238*/        return 2;
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/*1242*/        if(ctclass == CtClass.longType)
/*1243*/            return constpool.addLongInfo(value);
/*1245*/        else
/*1245*/            return 0;
            }

            long value;

            (long l)
            {
/*1216*/        value = l;
            }
}
