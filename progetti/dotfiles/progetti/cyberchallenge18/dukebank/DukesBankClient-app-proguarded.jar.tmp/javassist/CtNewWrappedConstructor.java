// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtNewWrappedConstructor.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.Descriptor;
import javassist.bytecode.MethodInfo;

// Referenced classes of package javassist:
//            CtNewWrappedMethod, CannotCompileException, CtClass, CtConstructor, 
//            CtMethod, NotFoundException

class CtNewWrappedConstructor extends CtNewWrappedMethod
{

            CtNewWrappedConstructor()
            {
            }

            public static CtConstructor wrapped(CtClass actclass[], CtClass actclass1[], int i, CtMethod ctmethod, CtMethod.ConstParameter constparameter, CtClass ctclass)
                throws CannotCompileException
            {
                CtConstructor ctconstructor;
/*  36*/        (ctconstructor = new CtConstructor(actclass, ctclass)).setExceptionTypes(actclass1);
/*  38*/        actclass = makeBody(ctclass, ctclass.getClassFile2(), i, ctmethod, actclass, constparameter);
/*  41*/        ctconstructor.getMethodInfo2().setCodeAttribute(actclass.toCodeAttribute());
/*  43*/        return ctconstructor;
                NotFoundException notfoundexception;
/*  45*/        notfoundexception;
/*  46*/        throw new CannotCompileException(notfoundexception);
            }

            protected static Bytecode makeBody(CtClass ctclass, ClassFile classfile, int i, CtMethod ctmethod, CtClass actclass[], CtMethod.ConstParameter constparameter)
                throws CannotCompileException
            {
/*  59*/        int l = classfile.getSuperclassId();
                Bytecode bytecode;
/*  60*/        (bytecode = new Bytecode(classfile.getConstPool(), 0, 0)).setMaxLocals(false, actclass, 0);
/*  62*/        bytecode.addAload(0);
/*  63*/        if(i == 0)
                {
/*  64*/            i = 1;
/*  65*/            bytecode.addInvokespecial(l, "<init>", "()V");
                } else
/*  67*/        if(i == 2)
                {
/*  68*/            i = bytecode.addLoadParameters(actclass, 1) + 1;
/*  69*/            bytecode.addInvokespecial(l, "<init>", Descriptor.ofConstructor(actclass));
                } else
                {
/*  73*/            i = compileParameterList(bytecode, actclass, 1);
                    int j;
                    String s;
/*  75*/            if(constparameter == null)
                    {
/*  76*/                j = 2;
/*  77*/                s = CtMethod.ConstParameter.defaultConstDescriptor();
                    } else
                    {
/*  80*/                j = constparameter.compile(bytecode) + 2;
/*  81*/                s = constparameter.constDescriptor();
                    }
/*  84*/            if(i < j)
/*  85*/                i = j;
/*  87*/            bytecode.addInvokespecial(l, "<init>", s);
                }
/*  90*/        if(ctmethod == null)
                {
/*  91*/            bytecode.add(177);
                } else
                {
/*  93*/            int k = makeBody0(ctclass, classfile, ctmethod, false, actclass, CtClass.voidType, constparameter, bytecode);
/*  96*/            if(i < k)
/*  97*/                i = k;
                }
/* 100*/        bytecode.setMaxStack(i);
/* 101*/        return bytecode;
            }

            private static final int PASS_NONE = 0;
            private static final int PASS_PARAMS = 2;
}
