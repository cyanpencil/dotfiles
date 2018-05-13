// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtField, CtClass

static class dim extends dim
{

            void check(String s)
                throws CannotCompileException
            {
/*1392*/        if(s.charAt(0) != '[')
/*1393*/            throw new CannotCompileException("type mismatch");
/*1394*/        else
/*1394*/            return;
            }

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1400*/        bytecode.addAload(0);
/*1401*/        actclass = bytecode.addMultiNewarray(ctclass, dim);
/*1402*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1403*/        return actclass + 1;
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1409*/        javac = bytecode.addMultiNewarray(ctclass, dim);
/*1410*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1411*/        return javac;
            }

            CtClass type;
            int dim[];

            (CtClass ctclass, int ai[])
            {
/*1389*/        type = ctclass;
/*1389*/        dim = ai;
            }
}
