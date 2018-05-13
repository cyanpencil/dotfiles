// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtNewConstructor.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.MethodInfo;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtConstructor, CtMethod, 
//            CtNewWrappedConstructor, NotFoundException, ClassMap

public class CtNewConstructor
{

            public CtNewConstructor()
            {
            }

            public static CtConstructor make(String s, CtClass ctclass)
                throws CannotCompileException
            {
/*  68*/        ctclass = new Javac(ctclass);
/*  70*/        if((s = ctclass.compile(s)) instanceof CtConstructor)
/*  73*/            return (CtConstructor)s;
/*  80*/        else
/*  80*/            throw new CannotCompileException("not a constructor");
/*  76*/        s;
/*  77*/        throw new CannotCompileException(s);
            }

            public static CtConstructor make(CtClass actclass[], CtClass actclass1[], String s, CtClass ctclass)
                throws CannotCompileException
            {
/* 101*/        (actclass = new CtConstructor(actclass, ctclass)).setExceptionTypes(actclass1);
/* 103*/        actclass.setBody(s);
/* 104*/        return actclass;
/* 106*/        actclass;
/* 107*/        throw new CannotCompileException(actclass);
            }

            public static CtConstructor copy(CtConstructor ctconstructor, CtClass ctclass, ClassMap classmap)
                throws CannotCompileException
            {
/* 127*/        return new CtConstructor(ctconstructor, ctclass, classmap);
            }

            public static CtConstructor defaultConstructor(CtClass ctclass)
                throws CannotCompileException
            {
/* 139*/        CtConstructor ctconstructor = new CtConstructor(null, ctclass);
/* 141*/        Object obj = ctclass.getClassFile2().getConstPool();
/* 142*/        ((Bytecode) (obj = new Bytecode(((javassist.bytecode.ConstPool) (obj)), 1, 1))).addAload(0);
/* 145*/        try
                {
/* 145*/            ((Bytecode) (obj)).addInvokespecial(ctclass.getSuperclass(), "<init>", "()V");
                }
                // Misplaced declaration of an exception variable
/* 148*/        catch(CtClass ctclass)
                {
/* 149*/            throw new CannotCompileException(ctclass);
                }
/* 152*/        ((Bytecode) (obj)).add(177);
/* 155*/        ctconstructor.getMethodInfo2().setCodeAttribute(((Bytecode) (obj)).toCodeAttribute());
/* 156*/        return ctconstructor;
            }

            public static CtConstructor skeleton(CtClass actclass[], CtClass actclass1[], CtClass ctclass)
                throws CannotCompileException
            {
/* 181*/        return make(actclass, actclass1, 0, null, null, ctclass);
            }

            public static CtConstructor make(CtClass actclass[], CtClass actclass1[], CtClass ctclass)
                throws CannotCompileException
            {
/* 200*/        return make(actclass, actclass1, 2, null, null, ctclass);
            }

            public static CtConstructor make(CtClass actclass[], CtClass actclass1[], int i, CtMethod ctmethod, CtMethod.ConstParameter constparameter, CtClass ctclass)
                throws CannotCompileException
            {
/* 314*/        return CtNewWrappedConstructor.wrapped(actclass, actclass1, i, ctmethod, constparameter, ctclass);
            }

            public static final int PASS_NONE = 0;
            public static final int PASS_ARRAY = 1;
            public static final int PASS_PARAMS = 2;
}
