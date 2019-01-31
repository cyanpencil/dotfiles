// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) lnc 
// Source File Name:   CtField.java

package javassist;

import javassist.bytecode.Bytecode;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;

// Referenced classes of package javassist:
//            CannotCompileException, CtClass, CtField, CtNewWrappedMethod

static class  extends 
{

            int compile(CtClass ctclass, String s, Bytecode bytecode, CtClass actclass[], Javac javac)
                throws CannotCompileException
            {
/*1025*/        bytecode.addAload(0);
/*1026*/        bytecode.addNew(objectType);
/*1027*/        bytecode.add(89);
/*1028*/        bytecode.addAload(0);
/*1030*/        if(stringParams == null)
/*1031*/            javac = 4;
/*1033*/        else
/*1033*/            javac = compileStringParameter(bytecode) + 4;
/*1035*/        if(withConstructorParams)
/*1036*/            javac += CtNewWrappedMethod.compileParameterList(bytecode, actclass, 1);
/*1039*/        bytecode.addInvokespecial(objectType, "<init>", getDescriptor());
/*1040*/        bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1041*/        return javac;
            }

            private String getDescriptor()
            {
/*1048*/        if(stringParams == null)
/*1049*/            if(withConstructorParams)
/*1050*/                return "(Ljava/lang/Object;[Ljava/lang/Object;)V";
/*1052*/            else
/*1052*/                return "(Ljava/lang/Object;)V";
/*1054*/        if(withConstructorParams)
/*1055*/            return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)V";
/*1057*/        else
/*1057*/            return "(Ljava/lang/Object;[Ljava/lang/String;)V";
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1068*/        bytecode.addNew(objectType);
/*1069*/        bytecode.add(89);
/*1071*/        int i = 2;
/*1072*/        if(stringParams == null)
                {
/*1073*/            javac = "()V";
                } else
                {
/*1075*/            javac = "([Ljava/lang/String;)V";
/*1076*/            i = 2 + compileStringParameter(bytecode);
                }
/*1079*/        bytecode.addInvokespecial(objectType, "<init>", javac);
/*1080*/        bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctclass));
/*1081*/        return i;
            }

            protected final int compileStringParameter(Bytecode bytecode)
                throws CannotCompileException
            {
/*1087*/        int i = stringParams.length;
/*1088*/        bytecode.addIconst(i);
/*1089*/        bytecode.addAnewarray("java.lang.String");
/*1090*/        for(int j = 0; j < i; j++)
                {
/*1091*/            bytecode.add(89);
/*1092*/            bytecode.addIconst(j);
/*1093*/            bytecode.addLdc(stringParams[j]);
/*1094*/            bytecode.add(83);
                }

/*1097*/        return 4;
            }

            CtClass objectType;
            String stringParams[];
            boolean withConstructorParams;

            ()
            {
            }
}
