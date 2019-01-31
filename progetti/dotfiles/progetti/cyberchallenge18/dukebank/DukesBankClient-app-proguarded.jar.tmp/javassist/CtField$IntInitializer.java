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
//            CannotCompileException, CtField, CtClass

static class value extends value
{

            void check(String s)
                throws CannotCompileException
            {
/*1185*/        if((s = s.charAt(0)) != 'I' && s != 83 && s != 66 && s != 67 && s != 90)
/*1187*/            throw new CannotCompileException("type mismatch");
/*1188*/        else
/*1188*/            return;
            }

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1194*/        bytecode.addAload(0);
/*1195*/        bytecode.addIconst(value);
/*1196*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1197*/        return 2;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1203*/        bytecode.addIconst(value);
/*1204*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1205*/        return 1;
            }

            int getConstantValue(ConstPool constpool, CtClass ctclass)
            {
/*1209*/        return constpool.addIntegerInfo(value);
            }

            int value;

            (int i)
            {
/*1182*/        value = i;
            }
}
