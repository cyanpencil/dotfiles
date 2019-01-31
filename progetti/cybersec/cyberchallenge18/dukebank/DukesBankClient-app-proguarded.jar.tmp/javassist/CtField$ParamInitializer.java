// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtField

static class  extends 
{

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/* 958*/        if(actclass != null && nthParam < actclass.length)
                {
/* 959*/            bytecode.addAload(0);
/* 960*/            actclass = nthParamToLocal(nthParam, actclass, false);
/* 961*/            actclass = bytecode.addLoad(actclass, ctclass) + 1;
/* 962*/            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/* 963*/            return actclass;
                } else
                {
/* 966*/            return 0;
                }
            }

            static int nthParamToLocal(int i, CtClass actclass[], boolean flag)
            {
/* 979*/        CtClass ctclass = CtClass.longType;
/* 980*/        CtClass ctclass1 = CtClass.doubleType;
/* 982*/        if(flag)
/* 983*/            flag = false;
/* 985*/        else
/* 985*/            flag = true;
/* 987*/        for(int j = 0; j < i; j++)
                {
                    CtClass ctclass2;
/* 988*/            if((ctclass2 = actclass[j]) == ctclass || ctclass2 == ctclass1)
/* 990*/                flag += 2;
/* 992*/            else
/* 992*/                flag++;
                }

/* 995*/        return ((flag) ? 1 : 0);
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1001*/        return 0;
            }

            int nthParam;

            ()
            {
            }
}
