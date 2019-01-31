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
/*1121*/        bytecode.addAload(0);
/*1122*/        bytecode.addAload(0);
/*1124*/        if(stringParams == null)
/*1125*/            javac = 2;
/*1127*/        else
/*1127*/            javac = compileStringParameter(bytecode) + 2;
/*1129*/        if(withConstructorParams)
/*1130*/            javac += CtNewWrappedMethod.compileParameterList(bytecode, actclass, 1);
/*1133*/        ctclass = Descriptor.of(ctclass);
/*1134*/        actclass = (new StringBuilder()).append(getDescriptor()).append(ctclass).toString();
/*1135*/        bytecode.addInvokestatic(objectType, methodName, actclass);
/*1136*/        bytecode.addPutfield(Bytecode.THIS, s, ctclass);
/*1137*/        return javac;
            }

            private String getDescriptor()
            {
/*1144*/        if(stringParams == null)
/*1145*/            if(withConstructorParams)
/*1146*/                return "(Ljava/lang/Object;[Ljava/lang/Object;)";
/*1148*/            else
/*1148*/                return "(Ljava/lang/Object;)";
/*1150*/        if(withConstructorParams)
/*1151*/            return "(Ljava/lang/Object;[Ljava/lang/String;[Ljava/lang/Object;)";
/*1153*/        else
/*1153*/            return "(Ljava/lang/Object;[Ljava/lang/String;)";
            }

            int compileIfStatic(CtClass ctclass, String s, Bytecode bytecode, Javac javac)
                throws CannotCompileException
            {
/*1164*/        int i = 1;
/*1165*/        if(stringParams == null)
                {
/*1166*/            javac = "()";
                } else
                {
/*1168*/            javac = "([Ljava/lang/String;)";
/*1169*/            i = 1 + compileStringParameter(bytecode);
                }
/*1172*/        ctclass = Descriptor.of(ctclass);
/*1173*/        bytecode.addInvokestatic(objectType, methodName, (new StringBuilder()).append(javac).append(ctclass).toString());
/*1174*/        bytecode.addPutstatic(Bytecode.THIS, s, ctclass);
/*1175*/        return i;
            }

            String methodName;

            ()
            {
            }
}
